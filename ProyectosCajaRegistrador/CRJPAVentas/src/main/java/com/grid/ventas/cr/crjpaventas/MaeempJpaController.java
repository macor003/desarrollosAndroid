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
import com.grid.ventas.cr.crjpaventassrc.Maeemp;

/**
 *
 * @author eve0017909
 */
public class MaeempJpaController implements Serializable {

    public MaeempJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Maeemp maeemp) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(maeemp);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMaeemp(maeemp.getCodemp()) != null) {
                throw new PreexistingEntityException("Maeemp " + maeemp + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Maeemp maeemp) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            maeemp = em.merge(maeemp);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = maeemp.getCodemp();
                if (findMaeemp(id) == null) {
                    throw new NonexistentEntityException("The maeemp with id " + id + " no longer exists.");
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
            Maeemp maeemp;
            try {
                maeemp = em.getReference(Maeemp.class, id);
                maeemp.getCodemp();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The maeemp with id " + id + " no longer exists.", enfe);
            }
            em.remove(maeemp);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Maeemp> findMaeempEntities() {
        return findMaeempEntities(true, -1, -1);
    }

    public List<Maeemp> findMaeempEntities(int maxResults, int firstResult) {
        return findMaeempEntities(false, maxResults, firstResult);
    }

    private List<Maeemp> findMaeempEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Maeemp.class));
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

    public Maeemp findMaeemp(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Maeemp.class, id);
        } finally {
            em.close();
        }
    }

    public int getMaeempCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Maeemp> rt = cq.from(Maeemp.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
