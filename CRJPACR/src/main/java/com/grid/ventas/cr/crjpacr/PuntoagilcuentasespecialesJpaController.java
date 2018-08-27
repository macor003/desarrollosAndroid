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
import com.grid.ventas.cr.crjpacrsrc.Puntoagilcuentasespeciales;

/**
 *
 * @author eve0017909
 */
public class PuntoagilcuentasespecialesJpaController implements Serializable {

    public PuntoagilcuentasespecialesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Puntoagilcuentasespeciales puntoagilcuentasespeciales)
            throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(puntoagilcuentasespeciales);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPuntoagilcuentasespeciales(puntoagilcuentasespeciales
                    .getIdpuntoagilcuentasespeciales()) != null) {
                throw new PreexistingEntityException(
                        "Puntoagilcuentasespeciales " + puntoagilcuentasespeciales + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Puntoagilcuentasespeciales puntoagilcuentasespeciales)
            throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            puntoagilcuentasespeciales = em.merge(puntoagilcuentasespeciales);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = puntoagilcuentasespeciales.getIdpuntoagilcuentasespeciales();
                if (findPuntoagilcuentasespeciales(id) == null) {
                    throw new NonexistentEntityException(
                            "The puntoagilcuentasespeciales with id " + id + " no longer exists.");
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
            Puntoagilcuentasespeciales puntoagilcuentasespeciales;
            try {
                puntoagilcuentasespeciales = em.getReference(Puntoagilcuentasespeciales.class, id);
                puntoagilcuentasespeciales.getIdpuntoagilcuentasespeciales();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException(
                        "The puntoagilcuentasespeciales with id " + id + " no longer exists.", enfe);
            }
            em.remove(puntoagilcuentasespeciales);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Puntoagilcuentasespeciales> findPuntoagilcuentasespecialesEntities() {
        return findPuntoagilcuentasespecialesEntities(true, -1, -1);
    }

    public List<Puntoagilcuentasespeciales> findPuntoagilcuentasespecialesEntities(int maxResults,
                                                                                   int firstResult) {
        return findPuntoagilcuentasespecialesEntities(false, maxResults, firstResult);
    }

    private List<Puntoagilcuentasespeciales> findPuntoagilcuentasespecialesEntities(boolean all, int maxResults,
                                                                                    int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Puntoagilcuentasespeciales.class));
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

    public Puntoagilcuentasespeciales findPuntoagilcuentasespeciales(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Puntoagilcuentasespeciales.class, id);
        } finally {
            em.close();
        }
    }

    public int getPuntoagilcuentasespecialesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Puntoagilcuentasespeciales> rt = cq.from(Puntoagilcuentasespeciales.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
