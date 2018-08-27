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
import com.grid.ventas.cr.crjpaventassrc.Opermee;
import com.grid.ventas.cr.crjpaventassrc.OpermeePK;

/**
 *
 * @author eve0017909
 */
public class OpermeeJpaController implements Serializable {

    public OpermeeJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Opermee opermee) throws PreexistingEntityException, Exception {
        if (opermee.getOpermeePK() == null) {
            opermee.setOpermeePK(new OpermeePK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(opermee);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findOpermee(opermee.getOpermeePK()) != null) {
                throw new PreexistingEntityException("Opermee " + opermee + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Opermee opermee) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            opermee = em.merge(opermee);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                OpermeePK id = opermee.getOpermeePK();
                if (findOpermee(id) == null) {
                    throw new NonexistentEntityException("The opermee with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(OpermeePK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Opermee opermee;
            try {
                opermee = em.getReference(Opermee.class, id);
                opermee.getOpermeePK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The opermee with id " + id + " no longer exists.", enfe);
            }
            em.remove(opermee);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Opermee> findOpermeeEntities() {
        return findOpermeeEntities(true, -1, -1);
    }

    public List<Opermee> findOpermeeEntities(int maxResults, int firstResult) {
        return findOpermeeEntities(false, maxResults, firstResult);
    }

    private List<Opermee> findOpermeeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Opermee.class));
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

    public Opermee findOpermee(OpermeePK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Opermee.class, id);
        } finally {
            em.close();
        }
    }

    public int getOpermeeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Opermee> rt = cq.from(Opermee.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
