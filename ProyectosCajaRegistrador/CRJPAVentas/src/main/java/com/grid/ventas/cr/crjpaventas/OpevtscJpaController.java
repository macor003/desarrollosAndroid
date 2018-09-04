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
import com.grid.ventas.cr.crjpaventassrc.Opevtsc;
import com.grid.ventas.cr.crjpaventassrc.OpevtscPK;

/**
 *
 * @author eve0017909
 */
public class OpevtscJpaController implements Serializable {

    public OpevtscJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Opevtsc opevtsc) throws PreexistingEntityException, Exception {
        if (opevtsc.getOpevtscPK() == null) {
            opevtsc.setOpevtscPK(new OpevtscPK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(opevtsc);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findOpevtsc(opevtsc.getOpevtscPK()) != null) {
                throw new PreexistingEntityException("Opevtsc " + opevtsc + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Opevtsc opevtsc) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            opevtsc = em.merge(opevtsc);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                OpevtscPK id = opevtsc.getOpevtscPK();
                if (findOpevtsc(id) == null) {
                    throw new NonexistentEntityException("The opevtsc with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(OpevtscPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Opevtsc opevtsc;
            try {
                opevtsc = em.getReference(Opevtsc.class, id);
                opevtsc.getOpevtscPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The opevtsc with id " + id + " no longer exists.", enfe);
            }
            em.remove(opevtsc);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Opevtsc> findOpevtscEntities() {
        return findOpevtscEntities(true, -1, -1);
    }

    public List<Opevtsc> findOpevtscEntities(int maxResults, int firstResult) {
        return findOpevtscEntities(false, maxResults, firstResult);
    }

    private List<Opevtsc> findOpevtscEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Opevtsc.class));
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

    public Opevtsc findOpevtsc(OpevtscPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Opevtsc.class, id);
        } finally {
            em.close();
        }
    }

    public int getOpevtscCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Opevtsc> rt = cq.from(Opevtsc.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
