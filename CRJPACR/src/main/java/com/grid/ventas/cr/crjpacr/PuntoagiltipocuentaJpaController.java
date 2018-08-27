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
import com.grid.ventas.cr.crjpacrsrc.Puntoagiltipocuenta;

/**
 *
 * @author eve0017909
 */
public class PuntoagiltipocuentaJpaController implements Serializable {

    public PuntoagiltipocuentaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Puntoagiltipocuenta puntoagiltipocuenta) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(puntoagiltipocuenta);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPuntoagiltipocuenta(puntoagiltipocuenta.getIdpuntoagiltipocuenta()) != null) {
                throw new PreexistingEntityException(
                        "Puntoagiltipocuenta " + puntoagiltipocuenta + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Puntoagiltipocuenta puntoagiltipocuenta) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            puntoagiltipocuenta = em.merge(puntoagiltipocuenta);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = puntoagiltipocuenta.getIdpuntoagiltipocuenta();
                if (findPuntoagiltipocuenta(id) == null) {
                    throw new NonexistentEntityException(
                            "The puntoagiltipocuenta with id " + id + " no longer exists.");
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
            Puntoagiltipocuenta puntoagiltipocuenta;
            try {
                puntoagiltipocuenta = em.getReference(Puntoagiltipocuenta.class, id);
                puntoagiltipocuenta.getIdpuntoagiltipocuenta();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException(
                        "The puntoagiltipocuenta with id " + id + " no longer exists.", enfe);
            }
            em.remove(puntoagiltipocuenta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Puntoagiltipocuenta> findPuntoagiltipocuentaEntities() {
        return findPuntoagiltipocuentaEntities(true, -1, -1);
    }

    public List<Puntoagiltipocuenta> findPuntoagiltipocuentaEntities(int maxResults, int firstResult) {
        return findPuntoagiltipocuentaEntities(false, maxResults, firstResult);
    }

    private List<Puntoagiltipocuenta> findPuntoagiltipocuentaEntities(boolean all, int maxResults,
                                                                      int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Puntoagiltipocuenta.class));
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

    public Puntoagiltipocuenta findPuntoagiltipocuenta(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Puntoagiltipocuenta.class, id);
        } finally {
            em.close();
        }
    }

    public int getPuntoagiltipocuentaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Puntoagiltipocuenta> rt = cq.from(Puntoagiltipocuenta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
