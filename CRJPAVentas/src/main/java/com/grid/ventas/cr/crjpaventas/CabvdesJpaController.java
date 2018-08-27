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
import com.grid.ventas.cr.crjpaventassrc.Cabvdes;
import com.grid.ventas.cr.crjpaventassrc.CabvdesPK;

/**
 *
 * @author eve0017909
 */
public class CabvdesJpaController implements Serializable {

    public CabvdesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cabvdes cabvdes) throws PreexistingEntityException, Exception {
        if (cabvdes.getCabvdesPK() == null) {
            cabvdes.setCabvdesPK(new CabvdesPK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(cabvdes);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCabvdes(cabvdes.getCabvdesPK()) != null) {
                throw new PreexistingEntityException("Cabvdes " + cabvdes + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cabvdes cabvdes) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            cabvdes = em.merge(cabvdes);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                CabvdesPK id = cabvdes.getCabvdesPK();
                if (findCabvdes(id) == null) {
                    throw new NonexistentEntityException("The cabvdes with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CabvdesPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cabvdes cabvdes;
            try {
                cabvdes = em.getReference(Cabvdes.class, id);
                cabvdes.getCabvdesPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cabvdes with id " + id + " no longer exists.", enfe);
            }
            em.remove(cabvdes);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cabvdes> findCabvdesEntities() {
        return findCabvdesEntities(true, -1, -1);
    }

    public List<Cabvdes> findCabvdesEntities(int maxResults, int firstResult) {
        return findCabvdesEntities(false, maxResults, firstResult);
    }

    private List<Cabvdes> findCabvdesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cabvdes.class));
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

    public Cabvdes findCabvdes(CabvdesPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cabvdes.class, id);
        } finally {
            em.close();
        }
    }

    public int getCabvdesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cabvdes> rt = cq.from(Cabvdes.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
