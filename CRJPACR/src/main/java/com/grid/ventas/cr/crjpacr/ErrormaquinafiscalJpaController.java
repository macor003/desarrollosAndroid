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
import com.grid.ventas.cr.crjpacrsrc.Errormaquinafiscal;
import com.grid.ventas.cr.crjpacrsrc.ErrormaquinafiscalPK;

/**
 *
 * @author eve0017909
 */
public class ErrormaquinafiscalJpaController implements Serializable {

    public ErrormaquinafiscalJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Errormaquinafiscal errormaquinafiscal) throws PreexistingEntityException, Exception {
        if (errormaquinafiscal.getErrormaquinafiscalPK() == null) {
            errormaquinafiscal.setErrormaquinafiscalPK(new ErrormaquinafiscalPK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(errormaquinafiscal);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findErrormaquinafiscal(errormaquinafiscal.getErrormaquinafiscalPK()) != null) {
                throw new PreexistingEntityException(
                        "Errormaquinafiscal " + errormaquinafiscal + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Errormaquinafiscal errormaquinafiscal) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            errormaquinafiscal = em.merge(errormaquinafiscal);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                ErrormaquinafiscalPK id = errormaquinafiscal.getErrormaquinafiscalPK();
                if (findErrormaquinafiscal(id) == null) {
                    throw new NonexistentEntityException(
                            "The errormaquinafiscal with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(ErrormaquinafiscalPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Errormaquinafiscal errormaquinafiscal;
            try {
                errormaquinafiscal = em.getReference(Errormaquinafiscal.class, id);
                errormaquinafiscal.getErrormaquinafiscalPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The errormaquinafiscal with id " + id + " no longer exists.",
                        enfe);
            }
            em.remove(errormaquinafiscal);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Errormaquinafiscal> findErrormaquinafiscalEntities() {
        return findErrormaquinafiscalEntities(true, -1, -1);
    }

    public List<Errormaquinafiscal> findErrormaquinafiscalEntities(int maxResults, int firstResult) {
        return findErrormaquinafiscalEntities(false, maxResults, firstResult);
    }

    private List<Errormaquinafiscal> findErrormaquinafiscalEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Errormaquinafiscal.class));
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

    public Errormaquinafiscal findErrormaquinafiscal(ErrormaquinafiscalPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Errormaquinafiscal.class, id);
        } finally {
            em.close();
        }
    }

    public int getErrormaquinafiscalCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Errormaquinafiscal> rt = cq.from(Errormaquinafiscal.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
