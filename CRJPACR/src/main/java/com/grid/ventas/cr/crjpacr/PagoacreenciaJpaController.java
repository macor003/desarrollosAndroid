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
import com.grid.ventas.cr.crjpacrsrc.Pagoacreencia;
import com.grid.ventas.cr.crjpacrsrc.PagoacreenciaPK;

/**
 *
 * @author eve0017909
 */
public class PagoacreenciaJpaController implements Serializable {

    public PagoacreenciaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pagoacreencia pagoacreencia) throws PreexistingEntityException, Exception {
        if (pagoacreencia.getPagoacreenciaPK() == null) {
            pagoacreencia.setPagoacreenciaPK(new PagoacreenciaPK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(pagoacreencia);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPagoacreencia(pagoacreencia.getPagoacreenciaPK()) != null) {
                throw new PreexistingEntityException("Pagoacreencia " + pagoacreencia + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pagoacreencia pagoacreencia) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            pagoacreencia = em.merge(pagoacreencia);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                PagoacreenciaPK id = pagoacreencia.getPagoacreenciaPK();
                if (findPagoacreencia(id) == null) {
                    throw new NonexistentEntityException("The pagoacreencia with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(PagoacreenciaPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pagoacreencia pagoacreencia;
            try {
                pagoacreencia = em.getReference(Pagoacreencia.class, id);
                pagoacreencia.getPagoacreenciaPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pagoacreencia with id " + id + " no longer exists.",
                        enfe);
            }
            em.remove(pagoacreencia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Pagoacreencia> findPagoacreenciaEntities() {
        return findPagoacreenciaEntities(true, -1, -1);
    }

    public List<Pagoacreencia> findPagoacreenciaEntities(int maxResults, int firstResult) {
        return findPagoacreenciaEntities(false, maxResults, firstResult);
    }

    private List<Pagoacreencia> findPagoacreenciaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pagoacreencia.class));
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

    public Pagoacreencia findPagoacreencia(PagoacreenciaPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pagoacreencia.class, id);
        } finally {
            em.close();
        }
    }

    public int getPagoacreenciaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pagoacreencia> rt = cq.from(Pagoacreencia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
