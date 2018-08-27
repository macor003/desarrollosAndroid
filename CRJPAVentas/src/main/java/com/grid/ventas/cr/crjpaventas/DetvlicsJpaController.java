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
import com.grid.ventas.cr.crjpaventassrc.Detvlics;
import com.grid.ventas.cr.crjpaventassrc.DetvlicsPK;

/**
 *
 * @author eve0017909
 */
public class DetvlicsJpaController implements Serializable {

    public DetvlicsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Detvlics detvlics) throws PreexistingEntityException, Exception {
        if (detvlics.getDetvlicsPK() == null) {
            detvlics.setDetvlicsPK(new DetvlicsPK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(detvlics);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDetvlics(detvlics.getDetvlicsPK()) != null) {
                throw new PreexistingEntityException("Detvlics " + detvlics + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Detvlics detvlics) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            detvlics = em.merge(detvlics);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                DetvlicsPK id = detvlics.getDetvlicsPK();
                if (findDetvlics(id) == null) {
                    throw new NonexistentEntityException("The detvlics with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(DetvlicsPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detvlics detvlics;
            try {
                detvlics = em.getReference(Detvlics.class, id);
                detvlics.getDetvlicsPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detvlics with id " + id + " no longer exists.", enfe);
            }
            em.remove(detvlics);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Detvlics> findDetvlicsEntities() {
        return findDetvlicsEntities(true, -1, -1);
    }

    public List<Detvlics> findDetvlicsEntities(int maxResults, int firstResult) {
        return findDetvlicsEntities(false, maxResults, firstResult);
    }

    private List<Detvlics> findDetvlicsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Detvlics.class));
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

    public Detvlics findDetvlics(DetvlicsPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Detvlics.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetvlicsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Detvlics> rt = cq.from(Detvlics.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
