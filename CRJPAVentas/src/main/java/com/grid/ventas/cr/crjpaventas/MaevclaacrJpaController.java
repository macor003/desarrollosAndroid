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
import com.grid.ventas.cr.crjpaventassrc.Maevclaacr;
import com.grid.ventas.cr.crjpaventassrc.MaevclaacrPK;

/**
 *
 * @author eve0017909
 */
public class MaevclaacrJpaController implements Serializable {

    public MaevclaacrJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Maevclaacr maevclaacr) throws PreexistingEntityException, Exception {
        if (maevclaacr.getMaevclaacrPK() == null) {
            maevclaacr.setMaevclaacrPK(new MaevclaacrPK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(maevclaacr);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMaevclaacr(maevclaacr.getMaevclaacrPK()) != null) {
                throw new PreexistingEntityException("Maevclaacr " + maevclaacr + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Maevclaacr maevclaacr) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            maevclaacr = em.merge(maevclaacr);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                MaevclaacrPK id = maevclaacr.getMaevclaacrPK();
                if (findMaevclaacr(id) == null) {
                    throw new NonexistentEntityException("The maevclaacr with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(MaevclaacrPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Maevclaacr maevclaacr;
            try {
                maevclaacr = em.getReference(Maevclaacr.class, id);
                maevclaacr.getMaevclaacrPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The maevclaacr with id " + id + " no longer exists.", enfe);
            }
            em.remove(maevclaacr);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Maevclaacr> findMaevclaacrEntities() {
        return findMaevclaacrEntities(true, -1, -1);
    }

    public List<Maevclaacr> findMaevclaacrEntities(int maxResults, int firstResult) {
        return findMaevclaacrEntities(false, maxResults, firstResult);
    }

    private List<Maevclaacr> findMaevclaacrEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Maevclaacr.class));
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

    public Maevclaacr findMaevclaacr(MaevclaacrPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Maevclaacr.class, id);
        } finally {
            em.close();
        }
    }

    public int getMaevclaacrCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Maevclaacr> rt = cq.from(Maevclaacr.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
