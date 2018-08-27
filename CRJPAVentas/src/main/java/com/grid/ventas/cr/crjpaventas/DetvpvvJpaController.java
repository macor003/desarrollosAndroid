/*
 * To change this license header, choose License Headers in Project Properties. To change
 * this template file, choose Tools | Templates and open the template in the editor.
 */
package com.grid.ventas.cr.crjpaventas;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.grid.ventas.cr.crjpaventas.exceptions.NonexistentEntityException;
import com.grid.ventas.cr.crjpaventas.exceptions.PreexistingEntityException;
import com.grid.ventas.cr.crjpaventassrc.Detvpvv;
import com.grid.ventas.cr.crjpaventassrc.DetvpvvPK;

/**
 *
 * @author eve0017909
 */
public class DetvpvvJpaController implements Serializable {

    public DetvpvvJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Detvpvv detvpvv) throws PreexistingEntityException, Exception {
        if (detvpvv.getDetvpvvPK() == null) {
            detvpvv.setDetvpvvPK(new DetvpvvPK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(detvpvv);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDetvpvv(detvpvv.getDetvpvvPK()) != null) {
                throw new PreexistingEntityException("Detvpvv " + detvpvv + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Detvpvv detvpvv) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            detvpvv = em.merge(detvpvv);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                DetvpvvPK id = detvpvv.getDetvpvvPK();
                if (findDetvpvv(id) == null) {
                    throw new NonexistentEntityException("The detvpvv with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(DetvpvvPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detvpvv detvpvv;
            try {
                detvpvv = em.getReference(Detvpvv.class, id);
                detvpvv.getDetvpvvPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detvpvv with id " + id + " no longer exists.", enfe);
            }
            em.remove(detvpvv);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Detvpvv> findDetvpvvEntities() {
        return findDetvpvvEntities(true, -1, -1);
    }

    public List<Detvpvv> findDetvpvvEntities(int maxResults, int firstResult) {
        return findDetvpvvEntities(false, maxResults, firstResult);
    }

    private List<Detvpvv> findDetvpvvEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Detvpvv.class));
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

    public Detvpvv findDetvpvv(DetvpvvPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Detvpvv.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetvpvvCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Detvpvv> rt = cq.from(Detvpvv.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
