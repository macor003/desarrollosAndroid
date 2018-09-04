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
import com.grid.ventas.cr.crjpaventassrc.Hisvdnd;
import com.grid.ventas.cr.crjpaventassrc.HisvdndPK;

/**
 *
 * @author eve0017909
 */
public class HisvdndJpaController implements Serializable {

    public HisvdndJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Hisvdnd hisvdnd) throws PreexistingEntityException, Exception {
        if (hisvdnd.getHisvdndPK() == null) {
            hisvdnd.setHisvdndPK(new HisvdndPK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(hisvdnd);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findHisvdnd(hisvdnd.getHisvdndPK()) != null) {
                throw new PreexistingEntityException("Hisvdnd " + hisvdnd + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Hisvdnd hisvdnd) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            hisvdnd = em.merge(hisvdnd);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                HisvdndPK id = hisvdnd.getHisvdndPK();
                if (findHisvdnd(id) == null) {
                    throw new NonexistentEntityException("The hisvdnd with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(HisvdndPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Hisvdnd hisvdnd;
            try {
                hisvdnd = em.getReference(Hisvdnd.class, id);
                hisvdnd.getHisvdndPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hisvdnd with id " + id + " no longer exists.", enfe);
            }
            em.remove(hisvdnd);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Hisvdnd> findHisvdndEntities() {
        return findHisvdndEntities(true, -1, -1);
    }

    public List<Hisvdnd> findHisvdndEntities(int maxResults, int firstResult) {
        return findHisvdndEntities(false, maxResults, firstResult);
    }

    private List<Hisvdnd> findHisvdndEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Hisvdnd.class));
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

    public Hisvdnd findHisvdnd(HisvdndPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Hisvdnd.class, id);
        } finally {
            em.close();
        }
    }

    public int getHisvdndCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Hisvdnd> rt = cq.from(Hisvdnd.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
