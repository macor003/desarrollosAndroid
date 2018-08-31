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
import com.grid.ventas.cr.crjpacrsrc.Girocontribuyente;

/**
 *
 * @author eve0017909
 */
public class GirocontribuyenteJpaController implements Serializable {

    public GirocontribuyenteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Girocontribuyente girocontribuyente) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(girocontribuyente);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findGirocontribuyente(girocontribuyente.getCodigo()) != null) {
                throw new PreexistingEntityException("Girocontribuyente " + girocontribuyente + " already exists.",
                        ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Girocontribuyente girocontribuyente) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            girocontribuyente = em.merge(girocontribuyente);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = girocontribuyente.getCodigo();
                if (findGirocontribuyente(id) == null) {
                    throw new NonexistentEntityException(
                            "The girocontribuyente with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Girocontribuyente girocontribuyente;
            try {
                girocontribuyente = em.getReference(Girocontribuyente.class, id);
                girocontribuyente.getCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The girocontribuyente with id " + id + " no longer exists.",
                        enfe);
            }
            em.remove(girocontribuyente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Girocontribuyente> findGirocontribuyenteEntities() {
        return findGirocontribuyenteEntities(true, -1, -1);
    }

    public List<Girocontribuyente> findGirocontribuyenteEntities(int maxResults, int firstResult) {
        return findGirocontribuyenteEntities(false, maxResults, firstResult);
    }

    private List<Girocontribuyente> findGirocontribuyenteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Girocontribuyente.class));
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

    public Girocontribuyente findGirocontribuyente(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Girocontribuyente.class, id);
        } finally {
            em.close();
        }
    }

    public int getGirocontribuyenteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Girocontribuyente> rt = cq.from(Girocontribuyente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
