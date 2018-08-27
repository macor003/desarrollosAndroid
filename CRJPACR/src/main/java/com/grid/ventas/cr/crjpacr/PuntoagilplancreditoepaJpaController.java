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
import com.grid.ventas.cr.crjpacrsrc.Puntoagilplancreditoepa;

/**
 *
 * @author eve0017909
 */
public class PuntoagilplancreditoepaJpaController implements Serializable {

    public PuntoagilplancreditoepaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Puntoagilplancreditoepa puntoagilplancreditoepa)
            throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(puntoagilplancreditoepa);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPuntoagilplancreditoepa(puntoagilplancreditoepa.getIdpuntoagilplancreditoepa()) != null) {
                throw new PreexistingEntityException(
                        "Puntoagilplancreditoepa " + puntoagilplancreditoepa + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Puntoagilplancreditoepa puntoagilplancreditoepa)
            throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            puntoagilplancreditoepa = em.merge(puntoagilplancreditoepa);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = puntoagilplancreditoepa.getIdpuntoagilplancreditoepa();
                if (findPuntoagilplancreditoepa(id) == null) {
                    throw new NonexistentEntityException(
                            "The puntoagilplancreditoepa with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Puntoagilplancreditoepa puntoagilplancreditoepa;
            try {
                puntoagilplancreditoepa = em.getReference(Puntoagilplancreditoepa.class, id);
                puntoagilplancreditoepa.getIdpuntoagilplancreditoepa();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException(
                        "The puntoagilplancreditoepa with id " + id + " no longer exists.", enfe);
            }
            em.remove(puntoagilplancreditoepa);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Puntoagilplancreditoepa> findPuntoagilplancreditoepaEntities() {
        return findPuntoagilplancreditoepaEntities(true, -1, -1);
    }

    public List<Puntoagilplancreditoepa> findPuntoagilplancreditoepaEntities(int maxResults, int firstResult) {
        return findPuntoagilplancreditoepaEntities(false, maxResults, firstResult);
    }

    private List<Puntoagilplancreditoepa> findPuntoagilplancreditoepaEntities(boolean all, int maxResults,
                                                                              int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Puntoagilplancreditoepa.class));
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

    public Puntoagilplancreditoepa findPuntoagilplancreditoepa(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Puntoagilplancreditoepa.class, id);
        } finally {
            em.close();
        }
    }

    public int getPuntoagilplancreditoepaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Puntoagilplancreditoepa> rt = cq.from(Puntoagilplancreditoepa.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
