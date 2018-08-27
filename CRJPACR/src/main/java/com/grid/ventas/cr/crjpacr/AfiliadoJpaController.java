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
import com.grid.ventas.cr.crjpacrsrc.Afiliado;
import com.grid.ventas.cr.crjpacrsrc.Saldocliente;

/**
 *
 * @author eve0017909
 */
public class AfiliadoJpaController implements Serializable {

    public AfiliadoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Afiliado afiliado) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Saldocliente saldocliente = afiliado.getSaldocliente();
            if (saldocliente != null) {
                saldocliente = em.getReference(saldocliente.getClass(), saldocliente.getCodcliente());
                afiliado.setSaldocliente(saldocliente);
            }
            em.persist(afiliado);
            if (saldocliente != null) {
                Afiliado oldAfiliadoOfSaldocliente = saldocliente.getAfiliado();
                if (oldAfiliadoOfSaldocliente != null) {
                    oldAfiliadoOfSaldocliente.setSaldocliente(null);
                    oldAfiliadoOfSaldocliente = em.merge(oldAfiliadoOfSaldocliente);
                }
                saldocliente.setAfiliado(afiliado);
                saldocliente = em.merge(saldocliente);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAfiliado(afiliado.getCodafiliado()) != null) {
                throw new PreexistingEntityException("Afiliado " + afiliado + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Afiliado afiliado) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Afiliado persistentAfiliado = em.find(Afiliado.class, afiliado.getCodafiliado());
            Saldocliente saldoclienteOld = persistentAfiliado.getSaldocliente();
            Saldocliente saldoclienteNew = afiliado.getSaldocliente();
            List<String> illegalOrphanMessages = null;
            if (saldoclienteOld != null && !saldoclienteOld.equals(saldoclienteNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Saldocliente " + saldoclienteOld
                        + " since its afiliado field is not nullable.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (saldoclienteNew != null) {
                saldoclienteNew = em.getReference(saldoclienteNew.getClass(), saldoclienteNew.getCodcliente());
                afiliado.setSaldocliente(saldoclienteNew);
            }
            afiliado = em.merge(afiliado);
            if (saldoclienteNew != null && !saldoclienteNew.equals(saldoclienteOld)) {
                Afiliado oldAfiliadoOfSaldocliente = saldoclienteNew.getAfiliado();
                if (oldAfiliadoOfSaldocliente != null) {
                    oldAfiliadoOfSaldocliente.setSaldocliente(null);
                    oldAfiliadoOfSaldocliente = em.merge(oldAfiliadoOfSaldocliente);
                }
                saldoclienteNew.setAfiliado(afiliado);
                saldoclienteNew = em.merge(saldoclienteNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = afiliado.getCodafiliado();
                if (findAfiliado(id) == null) {
                    throw new NonexistentEntityException("The afiliado with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Afiliado afiliado;
            try {
                afiliado = em.getReference(Afiliado.class, id);
                afiliado.getCodafiliado();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The afiliado with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Saldocliente saldoclienteOrphanCheck = afiliado.getSaldocliente();
            if (saldoclienteOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Afiliado (" + afiliado
                        + ") cannot be destroyed since the Saldocliente " + saldoclienteOrphanCheck
                        + " in its saldocliente field has a non-nullable afiliado field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(afiliado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Afiliado> findAfiliadoEntities() {
        return findAfiliadoEntities(true, -1, -1);
    }

    public List<Afiliado> findAfiliadoEntities(int maxResults, int firstResult) {
        return findAfiliadoEntities(false, maxResults, firstResult);
    }

    private List<Afiliado> findAfiliadoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Afiliado.class));
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

    public Afiliado findAfiliado(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Afiliado.class, id);
        } finally {
            em.close();
        }
    }

    public int getAfiliadoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Afiliado> rt = cq.from(Afiliado.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
