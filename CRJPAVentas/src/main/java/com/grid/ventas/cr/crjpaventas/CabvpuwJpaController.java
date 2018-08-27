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
import com.grid.ventas.cr.crjpaventassrc.Cabvpuw;
import com.grid.ventas.cr.crjpaventassrc.CabvpuwPK;

/**
 *
 * @author eve0017909
 */
public class CabvpuwJpaController implements Serializable {

    public CabvpuwJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cabvpuw cabvpuw) throws PreexistingEntityException, Exception {
        if (cabvpuw.getCabvpuwPK() == null) {
            cabvpuw.setCabvpuwPK(new CabvpuwPK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(cabvpuw);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCabvpuw(cabvpuw.getCabvpuwPK()) != null) {
                throw new PreexistingEntityException("Cabvpuw " + cabvpuw + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cabvpuw cabvpuw) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            cabvpuw = em.merge(cabvpuw);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                CabvpuwPK id = cabvpuw.getCabvpuwPK();
                if (findCabvpuw(id) == null) {
                    throw new NonexistentEntityException("The cabvpuw with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CabvpuwPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cabvpuw cabvpuw;
            try {
                cabvpuw = em.getReference(Cabvpuw.class, id);
                cabvpuw.getCabvpuwPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cabvpuw with id " + id + " no longer exists.", enfe);
            }
            em.remove(cabvpuw);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cabvpuw> findCabvpuwEntities() {
        return findCabvpuwEntities(true, -1, -1);
    }

    public List<Cabvpuw> findCabvpuwEntities(int maxResults, int firstResult) {
        return findCabvpuwEntities(false, maxResults, firstResult);
    }

    private List<Cabvpuw> findCabvpuwEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cabvpuw.class));
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

    public Cabvpuw findCabvpuw(CabvpuwPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cabvpuw.class, id);
        } finally {
            em.close();
        }
    }

    public int getCabvpuwCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cabvpuw> rt = cq.from(Cabvpuw.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
