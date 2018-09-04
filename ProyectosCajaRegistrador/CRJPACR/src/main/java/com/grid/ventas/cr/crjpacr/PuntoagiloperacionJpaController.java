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
import com.grid.ventas.cr.crjpacrsrc.Puntoagiloperacion;
import com.grid.ventas.cr.crjpacrsrc.PuntoagiloperacionPK;

/**
 *
 * @author eve0017909
 */
public class PuntoagiloperacionJpaController implements Serializable {

    public PuntoagiloperacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Puntoagiloperacion puntoagiloperacion) throws PreexistingEntityException, Exception {
        if (puntoagiloperacion.getPuntoagiloperacionPK() == null) {
            puntoagiloperacion.setPuntoagiloperacionPK(new PuntoagiloperacionPK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(puntoagiloperacion);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPuntoagiloperacion(puntoagiloperacion.getPuntoagiloperacionPK()) != null) {
                throw new PreexistingEntityException(
                        "Puntoagiloperacion " + puntoagiloperacion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Puntoagiloperacion puntoagiloperacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            puntoagiloperacion = em.merge(puntoagiloperacion);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                PuntoagiloperacionPK id = puntoagiloperacion.getPuntoagiloperacionPK();
                if (findPuntoagiloperacion(id) == null) {
                    throw new NonexistentEntityException(
                            "The puntoagiloperacion with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(PuntoagiloperacionPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Puntoagiloperacion puntoagiloperacion;
            try {
                puntoagiloperacion = em.getReference(Puntoagiloperacion.class, id);
                puntoagiloperacion.getPuntoagiloperacionPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The puntoagiloperacion with id " + id + " no longer exists.",
                        enfe);
            }
            em.remove(puntoagiloperacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Puntoagiloperacion> findPuntoagiloperacionEntities() {
        return findPuntoagiloperacionEntities(true, -1, -1);
    }

    public List<Puntoagiloperacion> findPuntoagiloperacionEntities(int maxResults, int firstResult) {
        return findPuntoagiloperacionEntities(false, maxResults, firstResult);
    }

    private List<Puntoagiloperacion> findPuntoagiloperacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Puntoagiloperacion.class));
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

    public Puntoagiloperacion findPuntoagiloperacion(PuntoagiloperacionPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Puntoagiloperacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getPuntoagiloperacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Puntoagiloperacion> rt = cq.from(Puntoagiloperacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
