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
import com.grid.ventas.cr.crjpaventassrc.Opevcpr;
import com.grid.ventas.cr.crjpaventassrc.OpevcprPK;

/**
 *
 * @author eve0017909
 */
public class OpevcprJpaController implements Serializable {

    public OpevcprJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Opevcpr opevcpr) throws PreexistingEntityException, Exception {
        if (opevcpr.getOpevcprPK() == null) {
            opevcpr.setOpevcprPK(new OpevcprPK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(opevcpr);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findOpevcpr(opevcpr.getOpevcprPK()) != null) {
                throw new PreexistingEntityException("Opevcpr " + opevcpr + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Opevcpr opevcpr) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            opevcpr = em.merge(opevcpr);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                OpevcprPK id = opevcpr.getOpevcprPK();
                if (findOpevcpr(id) == null) {
                    throw new NonexistentEntityException("The opevcpr with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(OpevcprPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Opevcpr opevcpr;
            try {
                opevcpr = em.getReference(Opevcpr.class, id);
                opevcpr.getOpevcprPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The opevcpr with id " + id + " no longer exists.", enfe);
            }
            em.remove(opevcpr);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Opevcpr> findOpevcprEntities() {
        return findOpevcprEntities(true, -1, -1);
    }

    public List<Opevcpr> findOpevcprEntities(int maxResults, int firstResult) {
        return findOpevcprEntities(false, maxResults, firstResult);
    }

    private List<Opevcpr> findOpevcprEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Opevcpr.class));
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

    public Opevcpr findOpevcpr(OpevcprPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Opevcpr.class, id);
        } finally {
            em.close();
        }
    }

    public int getOpevcprCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Opevcpr> rt = cq.from(Opevcpr.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
