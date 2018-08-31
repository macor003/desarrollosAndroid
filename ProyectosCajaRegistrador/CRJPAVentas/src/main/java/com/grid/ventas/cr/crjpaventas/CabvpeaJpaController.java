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
import com.grid.ventas.cr.crjpaventassrc.Cabvpea;
import com.grid.ventas.cr.crjpaventassrc.CabvpeaPK;

/**
 *
 * @author eve0017909
 */
public class CabvpeaJpaController implements Serializable {

    public CabvpeaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cabvpea cabvpea) throws PreexistingEntityException, Exception {
        if (cabvpea.getCabvpeaPK() == null) {
            cabvpea.setCabvpeaPK(new CabvpeaPK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(cabvpea);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCabvpea(cabvpea.getCabvpeaPK()) != null) {
                throw new PreexistingEntityException("Cabvpea " + cabvpea + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cabvpea cabvpea) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            cabvpea = em.merge(cabvpea);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                CabvpeaPK id = cabvpea.getCabvpeaPK();
                if (findCabvpea(id) == null) {
                    throw new NonexistentEntityException("The cabvpea with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CabvpeaPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cabvpea cabvpea;
            try {
                cabvpea = em.getReference(Cabvpea.class, id);
                cabvpea.getCabvpeaPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cabvpea with id " + id + " no longer exists.", enfe);
            }
            em.remove(cabvpea);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cabvpea> findCabvpeaEntities() {
        return findCabvpeaEntities(true, -1, -1);
    }

    public List<Cabvpea> findCabvpeaEntities(int maxResults, int firstResult) {
        return findCabvpeaEntities(false, maxResults, firstResult);
    }

    private List<Cabvpea> findCabvpeaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cabvpea.class));
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

    public Cabvpea findCabvpea(CabvpeaPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cabvpea.class, id);
        } finally {
            em.close();
        }
    }

    public int getCabvpeaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cabvpea> rt = cq.from(Cabvpea.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
