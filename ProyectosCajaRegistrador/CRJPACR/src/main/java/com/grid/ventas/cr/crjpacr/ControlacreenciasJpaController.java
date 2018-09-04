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
import com.grid.ventas.cr.crjpacrsrc.Controlacreencias;

/**
 *
 * @author eve0017909
 */
public class ControlacreenciasJpaController implements Serializable {

    public ControlacreenciasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Controlacreencias controlacreencias) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(controlacreencias);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Controlacreencias controlacreencias) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            controlacreencias = em.merge(controlacreencias);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = controlacreencias.getCnumacreencia();
                if (findControlacreencias(id) == null) {
                    throw new NonexistentEntityException(
                            "The controlacreencias with id " + id + " no longer exists.");
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
            Controlacreencias controlacreencias;
            try {
                controlacreencias = em.getReference(Controlacreencias.class, id);
                controlacreencias.getCnumacreencia();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The controlacreencias with id " + id + " no longer exists.",
                        enfe);
            }
            em.remove(controlacreencias);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Controlacreencias> findControlacreenciasEntities() {
        return findControlacreenciasEntities(true, -1, -1);
    }

    public List<Controlacreencias> findControlacreenciasEntities(int maxResults, int firstResult) {
        return findControlacreenciasEntities(false, maxResults, firstResult);
    }

    private List<Controlacreencias> findControlacreenciasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Controlacreencias.class));
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

    public Controlacreencias findControlacreencias(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Controlacreencias.class, id);
        } finally {
            em.close();
        }
    }

    public int getControlacreenciasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Controlacreencias> rt = cq.from(Controlacreencias.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
