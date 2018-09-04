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
import com.grid.ventas.cr.crjpacrsrc.Acreencias;
import com.grid.ventas.cr.crjpacrsrc.AcreenciasPK;

/**
 *
 * @author eve0017909
 */
public class AcreenciasJpaController implements Serializable {

    public AcreenciasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Acreencias acreencias) throws PreexistingEntityException, Exception {
        if (acreencias.getAcreenciasPK() == null) {
            acreencias.setAcreenciasPK(new AcreenciasPK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(acreencias);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAcreencias(acreencias.getAcreenciasPK()) != null) {
                throw new PreexistingEntityException("Acreencias " + acreencias + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Acreencias acreencias) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            acreencias = em.merge(acreencias);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                AcreenciasPK id = acreencias.getAcreenciasPK();
                if (findAcreencias(id) == null) {
                    throw new NonexistentEntityException("The acreencias with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(AcreenciasPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Acreencias acreencias;
            try {
                acreencias = em.getReference(Acreencias.class, id);
                acreencias.getAcreenciasPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The acreencias with id " + id + " no longer exists.", enfe);
            }
            em.remove(acreencias);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Acreencias> findAcreenciasEntities() {
        return findAcreenciasEntities(true, -1, -1);
    }

    public List<Acreencias> findAcreenciasEntities(int maxResults, int firstResult) {
        return findAcreenciasEntities(false, maxResults, firstResult);
    }

    private List<Acreencias> findAcreenciasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Acreencias.class));
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

    public Acreencias findAcreencias(AcreenciasPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Acreencias.class, id);
        } finally {
            em.close();
        }
    }

    public int getAcreenciasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Acreencias> rt = cq.from(Acreencias.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
