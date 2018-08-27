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
import com.grid.ventas.cr.crjpacrsrc.Afiliadoextra;

/**
 *
 * @author eve0017909
 */
public class AfiliadoextraJpaController implements Serializable {

    public AfiliadoextraJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Afiliadoextra afiliadoextra) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(afiliadoextra);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAfiliadoextra(afiliadoextra.getCodafiliado()) != null) {
                throw new PreexistingEntityException("Afiliadoextra " + afiliadoextra + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Afiliadoextra afiliadoextra) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            afiliadoextra = em.merge(afiliadoextra);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = afiliadoextra.getCodafiliado();
                if (findAfiliadoextra(id) == null) {
                    throw new NonexistentEntityException("The afiliadoextra with id " + id + " no longer exists.");
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
            Afiliadoextra afiliadoextra;
            try {
                afiliadoextra = em.getReference(Afiliadoextra.class, id);
                afiliadoextra.getCodafiliado();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The afiliadoextra with id " + id + " no longer exists.",
                        enfe);
            }
            em.remove(afiliadoextra);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Afiliadoextra> findAfiliadoextraEntities() {
        return findAfiliadoextraEntities(true, -1, -1);
    }

    public List<Afiliadoextra> findAfiliadoextraEntities(int maxResults, int firstResult) {
        return findAfiliadoextraEntities(false, maxResults, firstResult);
    }

    private List<Afiliadoextra> findAfiliadoextraEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Afiliadoextra.class));
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

    public Afiliadoextra findAfiliadoextra(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Afiliadoextra.class, id);
        } finally {
            em.close();
        }
    }

    public int getAfiliadoextraCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Afiliadoextra> rt = cq.from(Afiliadoextra.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
