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
import com.grid.ventas.cr.crjpaventassrc.Cabvdev;
import com.grid.ventas.cr.crjpaventassrc.CabvdevPK;

/**
 *
 * @author eve0017909
 */
public class CabvdevJpaController implements Serializable {

    public CabvdevJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cabvdev cabvdev) throws PreexistingEntityException, Exception {
        if (cabvdev.getCabvdevPK() == null) {
            cabvdev.setCabvdevPK(new CabvdevPK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(cabvdev);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCabvdev(cabvdev.getCabvdevPK()) != null) {
                throw new PreexistingEntityException("Cabvdev " + cabvdev + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cabvdev cabvdev) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            cabvdev = em.merge(cabvdev);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                CabvdevPK id = cabvdev.getCabvdevPK();
                if (findCabvdev(id) == null) {
                    throw new NonexistentEntityException("The cabvdev with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CabvdevPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cabvdev cabvdev;
            try {
                cabvdev = em.getReference(Cabvdev.class, id);
                cabvdev.getCabvdevPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cabvdev with id " + id + " no longer exists.", enfe);
            }
            em.remove(cabvdev);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cabvdev> findCabvdevEntities() {
        return findCabvdevEntities(true, -1, -1);
    }

    public List<Cabvdev> findCabvdevEntities(int maxResults, int firstResult) {
        return findCabvdevEntities(false, maxResults, firstResult);
    }

    private List<Cabvdev> findCabvdevEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cabvdev.class));
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

    public Cabvdev findCabvdev(CabvdevPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cabvdev.class, id);
        } finally {
            em.close();
        }
    }

    public int getCabvdevCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cabvdev> rt = cq.from(Cabvdev.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
