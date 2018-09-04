/*
 * To change this license header, choose License Headers in Project Properties. To change
 * this template file, choose Tools | Templates and open the template in the editor.
 */
package com.grid.ventas.cr.crjpacr;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.grid.ventas.cr.crjpacr.exceptions.NonexistentEntityException;
import com.grid.ventas.cr.crjpacr.exceptions.PreexistingEntityException;
import com.grid.ventas.cr.crjpacrsrc.Detalletransaccion;
import com.grid.ventas.cr.crjpacrsrc.DetalletransaccionPK;
import com.grid.ventas.cr.crjpacrsrc.Usuario;

/**
 *
 * @author eve0017909
 */
public class DetalletransaccionJpaController implements Serializable {

    public DetalletransaccionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Detalletransaccion detalletransaccion) throws PreexistingEntityException, Exception {
        if (detalletransaccion.getDetalletransaccionPK() == null) {
            detalletransaccion.setDetalletransaccionPK(new DetalletransaccionPK());
        }
        detalletransaccion.getDetalletransaccionPK()
                .setNumtienda(detalletransaccion.getUsuario().getUsuarioPK().getNumtienda());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario usuario = detalletransaccion.getUsuario();
            if (usuario != null) {
                usuario = em.getReference(usuario.getClass(), usuario.getUsuarioPK());
                detalletransaccion.setUsuario(usuario);
            }
            em.persist(detalletransaccion);
            if (usuario != null) {
                usuario.getDetalletransaccionList().add(detalletransaccion);
                usuario = em.merge(usuario);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDetalletransaccion(detalletransaccion.getDetalletransaccionPK()) != null) {
                throw new PreexistingEntityException(
                        "Detalletransaccion " + detalletransaccion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Detalletransaccion detalletransaccion) throws NonexistentEntityException, Exception {
        detalletransaccion.getDetalletransaccionPK()
                .setNumtienda(detalletransaccion.getUsuario().getUsuarioPK().getNumtienda());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detalletransaccion persistentDetalletransaccion = em
                    .find(Detalletransaccion.class, detalletransaccion.getDetalletransaccionPK());
            Usuario usuarioOld = persistentDetalletransaccion.getUsuario();
            Usuario usuarioNew = detalletransaccion.getUsuario();
            if (usuarioNew != null) {
                usuarioNew = em.getReference(usuarioNew.getClass(), usuarioNew.getUsuarioPK());
                detalletransaccion.setUsuario(usuarioNew);
            }
            detalletransaccion = em.merge(detalletransaccion);
            if (usuarioOld != null && !usuarioOld.equals(usuarioNew)) {
                usuarioOld.getDetalletransaccionList().remove(detalletransaccion);
                usuarioOld = em.merge(usuarioOld);
            }
            if (usuarioNew != null && !usuarioNew.equals(usuarioOld)) {
                usuarioNew.getDetalletransaccionList().add(detalletransaccion);
                usuarioNew = em.merge(usuarioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                DetalletransaccionPK id = detalletransaccion.getDetalletransaccionPK();
                if (findDetalletransaccion(id) == null) {
                    throw new NonexistentEntityException(
                            "The detalletransaccion with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(DetalletransaccionPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detalletransaccion detalletransaccion;
            try {
                detalletransaccion = em.getReference(Detalletransaccion.class, id);
                detalletransaccion.getDetalletransaccionPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detalletransaccion with id " + id + " no longer exists.",
                        enfe);
            }
            Usuario usuario = detalletransaccion.getUsuario();
            if (usuario != null) {
                usuario.getDetalletransaccionList().remove(detalletransaccion);
                usuario = em.merge(usuario);
            }
            em.remove(detalletransaccion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Detalletransaccion> findDetalletransaccionEntities() {
        return findDetalletransaccionEntities(true, -1, -1);
    }

    public List<Detalletransaccion> findDetalletransaccionEntities(int maxResults, int firstResult) {
        return findDetalletransaccionEntities(false, maxResults, firstResult);
    }

    private List<Detalletransaccion> findDetalletransaccionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Detalletransaccion.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Detalletransaccion findDetalletransaccion(DetalletransaccionPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Detalletransaccion.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetalletransaccionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Detalletransaccion> rt = cq.from(Detalletransaccion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
