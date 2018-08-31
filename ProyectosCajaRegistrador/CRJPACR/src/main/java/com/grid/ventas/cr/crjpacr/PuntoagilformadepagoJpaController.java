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

import com.grid.ventas.cr.crjpacr.exceptions.IllegalOrphanException;
import com.grid.ventas.cr.crjpacr.exceptions.NonexistentEntityException;
import com.grid.ventas.cr.crjpacr.exceptions.PreexistingEntityException;
import com.grid.ventas.cr.crjpacrsrc.Puntoagilformadepago;

/**
 *
 * @author eve0017909
 */
public class PuntoagilformadepagoJpaController implements Serializable {

    public PuntoagilformadepagoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Puntoagilformadepago puntoagilformadepago) throws IllegalOrphanException,
            PreexistingEntityException,
            Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(puntoagilformadepago);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPuntoagilformadepago(puntoagilformadepago.getCodformadepago()) != null) {
                throw new PreexistingEntityException(
                        "Puntoagilformadepago " + puntoagilformadepago + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Puntoagilformadepago puntoagilformadepago) throws IllegalOrphanException,
            NonexistentEntityException,
            Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            puntoagilformadepago = em.merge(puntoagilformadepago);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = puntoagilformadepago.getCodformadepago();
                if (findPuntoagilformadepago(id) == null) {
                    throw new NonexistentEntityException(
                            "The puntoagilformadepago with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Puntoagilformadepago puntoagilformadepago;
            try {
                puntoagilformadepago = em.getReference(Puntoagilformadepago.class, id);
                puntoagilformadepago.getCodformadepago();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException(
                        "The puntoagilformadepago with id " + id + " no longer exists.", enfe);
            }
            em.remove(puntoagilformadepago);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Puntoagilformadepago> findPuntoagilformadepagoEntities() {
        return findPuntoagilformadepagoEntities(true, -1, -1);
    }

    public List<Puntoagilformadepago> findPuntoagilformadepagoEntities(int maxResults, int firstResult) {
        return findPuntoagilformadepagoEntities(false, maxResults, firstResult);
    }

    private List<Puntoagilformadepago> findPuntoagilformadepagoEntities(boolean all, int maxResults,
                                                                        int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Puntoagilformadepago.class));
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

    public Puntoagilformadepago findPuntoagilformadepago(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Puntoagilformadepago.class, id);
        } finally {
            em.close();
        }
    }

    public int getPuntoagilformadepagoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Puntoagilformadepago> rt = cq.from(Puntoagilformadepago.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
