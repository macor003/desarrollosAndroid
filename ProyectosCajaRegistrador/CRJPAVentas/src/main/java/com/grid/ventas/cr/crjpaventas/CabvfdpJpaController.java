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
import com.grid.ventas.cr.crjpaventassrc.Cabvfdp;

/**
 *
 * @author eve0017909
 */
public class CabvfdpJpaController implements Serializable {

    public CabvfdpJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cabvfdp cabvfdp) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(cabvfdp);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCabvfdp(cabvfdp.getCodfcfdp()) != null) {
                throw new PreexistingEntityException("Cabvfdp " + cabvfdp + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cabvfdp cabvfdp) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            cabvfdp = em.merge(cabvfdp);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Short id = cabvfdp.getCodfcfdp();
                if (findCabvfdp(id) == null) {
                    throw new NonexistentEntityException("The cabvfdp with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Short id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cabvfdp cabvfdp;
            try {
                cabvfdp = em.getReference(Cabvfdp.class, id);
                cabvfdp.getCodfcfdp();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cabvfdp with id " + id + " no longer exists.", enfe);
            }
            em.remove(cabvfdp);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cabvfdp> findCabvfdpEntities() {
        return findCabvfdpEntities(true, -1, -1);
    }

    public List<Cabvfdp> findCabvfdpEntities(int maxResults, int firstResult) {
        return findCabvfdpEntities(false, maxResults, firstResult);
    }

    private List<Cabvfdp> findCabvfdpEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cabvfdp.class));
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

    public Cabvfdp findCabvfdp(Short id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cabvfdp.class, id);
        } finally {
            em.close();
        }
    }

    public int getCabvfdpCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cabvfdp> rt = cq.from(Cabvfdp.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
