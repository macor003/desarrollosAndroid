/*
 * To change this license header, choose License Headers in Project Properties. To change
 * this template file, choose Tools | Templates and open the template in the editor.
 */
package com.grid.ventas.cr.crjpacr;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.grid.ventas.cr.crjpacr.exceptions.IllegalOrphanException;
import com.grid.ventas.cr.crjpacr.exceptions.NonexistentEntityException;
import com.grid.ventas.cr.crjpacr.exceptions.PreexistingEntityException;
import com.grid.ventas.cr.crjpacrsrc.Detalletransaccion;
import com.grid.ventas.cr.crjpacrsrc.Usuario;
import com.grid.ventas.cr.crjpacrsrc.UsuarioPK;

/**
 *
 * @author eve0017909
 */
public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) throws PreexistingEntityException, Exception {
        if (usuario.getUsuarioPK() == null) {
            usuario.setUsuarioPK(new UsuarioPK());
        }
        if (usuario.getDetalletransaccionList() == null) {
            usuario.setDetalletransaccionList(new ArrayList<Detalletransaccion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Detalletransaccion> attachedDetalletransaccionList = new ArrayList<Detalletransaccion>();
            for (Detalletransaccion detalletransaccionListDetalletransaccionToAttach : usuario
                    .getDetalletransaccionList()) {
                detalletransaccionListDetalletransaccionToAttach = em
                        .getReference(detalletransaccionListDetalletransaccionToAttach.getClass(),
                                      detalletransaccionListDetalletransaccionToAttach.getDetalletransaccionPK());
                attachedDetalletransaccionList.add(detalletransaccionListDetalletransaccionToAttach);
            }
            usuario.setDetalletransaccionList(attachedDetalletransaccionList);
            em.persist(usuario);
            for (Detalletransaccion detalletransaccionListDetalletransaccion : usuario
                    .getDetalletransaccionList()) {
                Usuario oldUsuarioOfDetalletransaccionListDetalletransaccion = detalletransaccionListDetalletransaccion
                        .getUsuario();
                detalletransaccionListDetalletransaccion.setUsuario(usuario);
                detalletransaccionListDetalletransaccion = em.merge(detalletransaccionListDetalletransaccion);
                if (oldUsuarioOfDetalletransaccionListDetalletransaccion != null) {
                    oldUsuarioOfDetalletransaccionListDetalletransaccion.getDetalletransaccionList()
                            .remove(detalletransaccionListDetalletransaccion);
                    oldUsuarioOfDetalletransaccionListDetalletransaccion = em
                            .merge(oldUsuarioOfDetalletransaccionListDetalletransaccion);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findUsuario(usuario.getUsuarioPK()) != null) {
                throw new PreexistingEntityException("Usuario " + usuario + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuario usuario) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getUsuarioPK());
            List<Detalletransaccion> detalletransaccionListOld = persistentUsuario.getDetalletransaccionList();
            List<Detalletransaccion> detalletransaccionListNew = usuario.getDetalletransaccionList();
            List<String> illegalOrphanMessages = null;
            for (Detalletransaccion detalletransaccionListOldDetalletransaccion : detalletransaccionListOld) {
                if (!detalletransaccionListNew.contains(detalletransaccionListOldDetalletransaccion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detalletransaccion "
                            + detalletransaccionListOldDetalletransaccion
                            + " since its usuario field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Detalletransaccion> attachedDetalletransaccionListNew = new ArrayList<Detalletransaccion>();
            for (Detalletransaccion detalletransaccionListNewDetalletransaccionToAttach : detalletransaccionListNew) {
                detalletransaccionListNewDetalletransaccionToAttach = em
                        .getReference(detalletransaccionListNewDetalletransaccionToAttach.getClass(),
                                      detalletransaccionListNewDetalletransaccionToAttach
                                              .getDetalletransaccionPK());
                attachedDetalletransaccionListNew.add(detalletransaccionListNewDetalletransaccionToAttach);
            }
            detalletransaccionListNew = attachedDetalletransaccionListNew;
            usuario.setDetalletransaccionList(detalletransaccionListNew);
            usuario = em.merge(usuario);
            for (Detalletransaccion detalletransaccionListNewDetalletransaccion : detalletransaccionListNew) {
                if (!detalletransaccionListOld.contains(detalletransaccionListNewDetalletransaccion)) {
                    Usuario oldUsuarioOfDetalletransaccionListNewDetalletransaccion = detalletransaccionListNewDetalletransaccion
                            .getUsuario();
                    detalletransaccionListNewDetalletransaccion.setUsuario(usuario);
                    detalletransaccionListNewDetalletransaccion = em
                            .merge(detalletransaccionListNewDetalletransaccion);
                    if (oldUsuarioOfDetalletransaccionListNewDetalletransaccion != null
                            && !oldUsuarioOfDetalletransaccionListNewDetalletransaccion.equals(usuario)) {
                        oldUsuarioOfDetalletransaccionListNewDetalletransaccion.getDetalletransaccionList()
                                .remove(detalletransaccionListNewDetalletransaccion);
                        oldUsuarioOfDetalletransaccionListNewDetalletransaccion = em
                                .merge(oldUsuarioOfDetalletransaccionListNewDetalletransaccion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                UsuarioPK id = usuario.getUsuarioPK();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(UsuarioPK id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getUsuarioPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Detalletransaccion> detalletransaccionListOrphanCheck = usuario.getDetalletransaccionList();
            for (Detalletransaccion detalletransaccionListOrphanCheckDetalletransaccion : detalletransaccionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages
                        .add("This Usuario (" + usuario + ") cannot be destroyed since the Detalletransaccion "
                                + detalletransaccionListOrphanCheckDetalletransaccion
                                + " in its detalletransaccionList field has a non-nullable usuario field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
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

    public Usuario findUsuario(UsuarioPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
