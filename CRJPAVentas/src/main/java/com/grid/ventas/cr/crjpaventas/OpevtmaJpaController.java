/*
 * To change this license header, choose License Headers in Project Properties. To change
 * this template file, choose Tools | Templates and open the template in the editor.
 */
package com.grid.ventas.cr.crjpaventas;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.grid.ventas.cr.crjpaventas.exceptions.NonexistentEntityException;
import com.grid.ventas.cr.crjpaventas.exceptions.PreexistingEntityException;
import com.grid.ventas.cr.crjpaventassrc.Opevtma;
import com.grid.ventas.cr.crjpaventassrc.OpevtmaPK;

/**
 *
 * @author eve0017909
 */
public class OpevtmaJpaController implements Serializable {

    public OpevtmaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Opevtma opevtma) throws PreexistingEntityException, Exception {
        if (opevtma.getOpevtmaPK() == null) {
            opevtma.setOpevtmaPK(new OpevtmaPK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(opevtma);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findOpevtma(opevtma.getOpevtmaPK()) != null) {
                throw new PreexistingEntityException("Opevtma " + opevtma + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Opevtma opevtma) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            opevtma = em.merge(opevtma);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                OpevtmaPK id = opevtma.getOpevtmaPK();
                if (findOpevtma(id) == null) {
                    throw new NonexistentEntityException("The opevtma with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(OpevtmaPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Opevtma opevtma;
            try {
                opevtma = em.getReference(Opevtma.class, id);
                opevtma.getOpevtmaPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The opevtma with id " + id + " no longer exists.", enfe);
            }
            em.remove(opevtma);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Opevtma> findOpevtmaEntities() {
        return findOpevtmaEntities(true, -1, -1);
    }

    public List<Opevtma> findOpevtmaEntities(int maxResults, int firstResult) {
        return findOpevtmaEntities(false, maxResults, firstResult);
    }

    private List<Opevtma> findOpevtmaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Opevtma.class));
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

    public Opevtma findOpevtma(OpevtmaPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Opevtma.class, id);
        } finally {
            em.close();
        }
    }

    public int getOpevtmaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Opevtma> rt = cq.from(Opevtma.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
