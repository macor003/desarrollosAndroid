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
import com.grid.ventas.cr.crjpaventassrc.Cabvlics;
import com.grid.ventas.cr.crjpaventassrc.CabvlicsPK;

/**
 *
 * @author eve0017909
 */
public class CabvlicsJpaController implements Serializable {

    public CabvlicsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cabvlics cabvlics) throws PreexistingEntityException, Exception {
        if (cabvlics.getCabvlicsPK() == null) {
            cabvlics.setCabvlicsPK(new CabvlicsPK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(cabvlics);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCabvlics(cabvlics.getCabvlicsPK()) != null) {
                throw new PreexistingEntityException("Cabvlics " + cabvlics + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cabvlics cabvlics) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            cabvlics = em.merge(cabvlics);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                CabvlicsPK id = cabvlics.getCabvlicsPK();
                if (findCabvlics(id) == null) {
                    throw new NonexistentEntityException("The cabvlics with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CabvlicsPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cabvlics cabvlics;
            try {
                cabvlics = em.getReference(Cabvlics.class, id);
                cabvlics.getCabvlicsPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cabvlics with id " + id + " no longer exists.", enfe);
            }
            em.remove(cabvlics);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cabvlics> findCabvlicsEntities() {
        return findCabvlicsEntities(true, -1, -1);
    }

    public List<Cabvlics> findCabvlicsEntities(int maxResults, int firstResult) {
        return findCabvlicsEntities(false, maxResults, firstResult);
    }

    private List<Cabvlics> findCabvlicsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cabvlics.class));
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

    public Cabvlics findCabvlics(CabvlicsPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cabvlics.class, id);
        } finally {
            em.close();
        }
    }

    public int getCabvlicsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cabvlics> rt = cq.from(Cabvlics.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
