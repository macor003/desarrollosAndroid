/*
 * To change this license header, choose License Headers in Project Properties. To change
 * this template file, choose Tools | Templates and open the template in the editor.
 */
package com.grid.ventas.cr.crjpacr;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.grid.ventas.cr.crjpacr.exceptions.NonexistentEntityException;
import com.grid.ventas.cr.crjpacr.exceptions.PreexistingEntityException;
import com.grid.ventas.cr.crjpacrsrc.Devolucionventa;
import com.grid.ventas.cr.crjpacrsrc.DevolucionventaPK;

/**
 *
 * @author eve0017909
 */
public class DevolucionventaJpaController implements Serializable {

    public DevolucionventaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Devolucionventa devolucionventa) throws PreexistingEntityException, Exception {
        if (devolucionventa.getDevolucionventaPK() == null) {
            devolucionventa.setDevolucionventaPK(new DevolucionventaPK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(devolucionventa);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDevolucionventa(devolucionventa.getDevolucionventaPK()) != null) {
                throw new PreexistingEntityException("Devolucionventa " + devolucionventa + " already exists.",
                        ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Devolucionventa devolucionventa) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            devolucionventa = em.merge(devolucionventa);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                DevolucionventaPK id = devolucionventa.getDevolucionventaPK();
                if (findDevolucionventa(id) == null) {
                    throw new NonexistentEntityException(
                            "The devolucionventa with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(DevolucionventaPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Devolucionventa devolucionventa;
            try {
                devolucionventa = em.getReference(Devolucionventa.class, id);
                devolucionventa.getDevolucionventaPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The devolucionventa with id " + id + " no longer exists.",
                        enfe);
            }
            em.remove(devolucionventa);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Devolucionventa> findDevolucionventaEntities() {
        return findDevolucionventaEntities(true, -1, -1);
    }

    public List<Devolucionventa> findDevolucionventaEntities(int maxResults, int firstResult) {
        return findDevolucionventaEntities(false, maxResults, firstResult);
    }

    private List<Devolucionventa> findDevolucionventaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Devolucionventa.class));
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

    public Devolucionventa findDevolucionventa(DevolucionventaPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Devolucionventa.class, id);
        } finally {
            em.close();
        }
    }

    public int getDevolucionventaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Devolucionventa> rt = cq.from(Devolucionventa.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
