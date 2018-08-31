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
import com.grid.ventas.cr.crjpacrsrc.Puntoagilcaja;
import com.grid.ventas.cr.crjpacrsrc.PuntoagilcajaPK;

/**
 *
 * @author eve0017909
 */
public class PuntoagilcajaJpaController implements Serializable {

    public PuntoagilcajaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Puntoagilcaja puntoagilcaja) throws PreexistingEntityException, Exception {
        if (puntoagilcaja.getPuntoagilcajaPK() == null) {
            puntoagilcaja.setPuntoagilcajaPK(new PuntoagilcajaPK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(puntoagilcaja);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPuntoagilcaja(puntoagilcaja.getPuntoagilcajaPK()) != null) {
                throw new PreexistingEntityException("Puntoagilcaja " + puntoagilcaja + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Puntoagilcaja puntoagilcaja) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            puntoagilcaja = em.merge(puntoagilcaja);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                PuntoagilcajaPK id = puntoagilcaja.getPuntoagilcajaPK();
                if (findPuntoagilcaja(id) == null) {
                    throw new NonexistentEntityException("The puntoagilcaja with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(PuntoagilcajaPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Puntoagilcaja puntoagilcaja;
            try {
                puntoagilcaja = em.getReference(Puntoagilcaja.class, id);
                puntoagilcaja.getPuntoagilcajaPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The puntoagilcaja with id " + id + " no longer exists.",
                        enfe);
            }
            em.remove(puntoagilcaja);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Puntoagilcaja> findPuntoagilcajaEntities() {
        return findPuntoagilcajaEntities(true, -1, -1);
    }

    public List<Puntoagilcaja> findPuntoagilcajaEntities(int maxResults, int firstResult) {
        return findPuntoagilcajaEntities(false, maxResults, firstResult);
    }

    private List<Puntoagilcaja> findPuntoagilcajaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Puntoagilcaja.class));
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

    public Puntoagilcaja findPuntoagilcaja(PuntoagilcajaPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Puntoagilcaja.class, id);
        } finally {
            em.close();
        }
    }

    public int getPuntoagilcajaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Puntoagilcaja> rt = cq.from(Puntoagilcaja.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
