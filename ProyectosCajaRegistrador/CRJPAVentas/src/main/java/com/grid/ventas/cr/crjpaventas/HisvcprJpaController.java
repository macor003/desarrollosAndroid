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
import com.grid.ventas.cr.crjpaventassrc.Hisvcpr;
import com.grid.ventas.cr.crjpaventassrc.HisvcprPK;

/**
 *
 * @author eve0017909
 */
public class HisvcprJpaController implements Serializable {

    public HisvcprJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Hisvcpr hisvcpr) throws PreexistingEntityException, Exception {
        if (hisvcpr.getHisvcprPK() == null) {
            hisvcpr.setHisvcprPK(new HisvcprPK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(hisvcpr);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findHisvcpr(hisvcpr.getHisvcprPK()) != null) {
                throw new PreexistingEntityException("Hisvcpr " + hisvcpr + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Hisvcpr hisvcpr) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            hisvcpr = em.merge(hisvcpr);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                HisvcprPK id = hisvcpr.getHisvcprPK();
                if (findHisvcpr(id) == null) {
                    throw new NonexistentEntityException("The hisvcpr with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(HisvcprPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Hisvcpr hisvcpr;
            try {
                hisvcpr = em.getReference(Hisvcpr.class, id);
                hisvcpr.getHisvcprPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hisvcpr with id " + id + " no longer exists.", enfe);
            }
            em.remove(hisvcpr);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Hisvcpr> findHisvcprEntities() {
        return findHisvcprEntities(true, -1, -1);
    }

    public List<Hisvcpr> findHisvcprEntities(int maxResults, int firstResult) {
        return findHisvcprEntities(false, maxResults, firstResult);
    }

    private List<Hisvcpr> findHisvcprEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Hisvcpr.class));
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

    public Hisvcpr findHisvcpr(HisvcprPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Hisvcpr.class, id);
        } finally {
            em.close();
        }
    }

    public int getHisvcprCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Hisvcpr> rt = cq.from(Hisvcpr.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
