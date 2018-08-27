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
import com.grid.ventas.cr.crjpaventassrc.Detvpuw;
import com.grid.ventas.cr.crjpaventassrc.DetvpuwPK;

/**
 *
 * @author eve0017909
 */
public class DetvpuwJpaController implements Serializable {

    public DetvpuwJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Detvpuw detvpuw) throws PreexistingEntityException, Exception {
        if (detvpuw.getDetvpuwPK() == null) {
            detvpuw.setDetvpuwPK(new DetvpuwPK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(detvpuw);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDetvpuw(detvpuw.getDetvpuwPK()) != null) {
                throw new PreexistingEntityException("Detvpuw " + detvpuw + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Detvpuw detvpuw) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            detvpuw = em.merge(detvpuw);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                DetvpuwPK id = detvpuw.getDetvpuwPK();
                if (findDetvpuw(id) == null) {
                    throw new NonexistentEntityException("The detvpuw with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(DetvpuwPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detvpuw detvpuw;
            try {
                detvpuw = em.getReference(Detvpuw.class, id);
                detvpuw.getDetvpuwPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detvpuw with id " + id + " no longer exists.", enfe);
            }
            em.remove(detvpuw);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Detvpuw> findDetvpuwEntities() {
        return findDetvpuwEntities(true, -1, -1);
    }

    public List<Detvpuw> findDetvpuwEntities(int maxResults, int firstResult) {
        return findDetvpuwEntities(false, maxResults, firstResult);
    }

    private List<Detvpuw> findDetvpuwEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Detvpuw.class));
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

    public Detvpuw findDetvpuw(DetvpuwPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Detvpuw.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetvpuwCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Detvpuw> rt = cq.from(Detvpuw.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
