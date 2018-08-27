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
import com.grid.ventas.cr.crjpaventassrc.Temcabvacr;

/**
 *
 * @author eve0017909
 */
public class TemcabvacrJpaController implements Serializable {

    public TemcabvacrJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Temcabvacr temcabvacr) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(temcabvacr);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Temcabvacr temcabvacr) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            temcabvacr = em.merge(temcabvacr);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = temcabvacr.getId();
                if (findTemcabvacr(id) == null) {
                    throw new NonexistentEntityException("The temcabvacr with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Temcabvacr temcabvacr;
            try {
                temcabvacr = em.getReference(Temcabvacr.class, id);
                temcabvacr.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The temcabvacr with id " + id + " no longer exists.", enfe);
            }
            em.remove(temcabvacr);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Temcabvacr> findTemcabvacrEntities() {
        return findTemcabvacrEntities(true, -1, -1);
    }

    public List<Temcabvacr> findTemcabvacrEntities(int maxResults, int firstResult) {
        return findTemcabvacrEntities(false, maxResults, firstResult);
    }

    private List<Temcabvacr> findTemcabvacrEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Temcabvacr.class));
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

    public Temcabvacr findTemcabvacr(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Temcabvacr.class, id);
        } finally {
            em.close();
        }
    }

    public int getTemcabvacrCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Temcabvacr> rt = cq.from(Temcabvacr.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
