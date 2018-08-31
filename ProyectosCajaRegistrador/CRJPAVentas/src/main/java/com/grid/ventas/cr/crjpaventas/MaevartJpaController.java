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
import com.grid.ventas.cr.crjpaventassrc.Maevart;

/**
 *
 * @author eve0017909
 */
public class MaevartJpaController implements Serializable {

    public MaevartJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Maevart maevart) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(maevart);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMaevart(maevart.getIdMaevart()) != null) {
                throw new PreexistingEntityException("Maevart " + maevart + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Maevart maevart) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            maevart = em.merge(maevart);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = maevart.getIdMaevart();
                if (findMaevart(id) == null) {
                    throw new NonexistentEntityException("The maevart with id " + id + " no longer exists.");
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
            Maevart maevart;
            try {
                maevart = em.getReference(Maevart.class, id);
                maevart.getIdMaevart();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The maevart with id " + id + " no longer exists.", enfe);
            }
            em.remove(maevart);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Maevart> findMaevartEntities() {
        return findMaevartEntities(true, -1, -1);
    }

    public List<Maevart> findMaevartEntities(int maxResults, int firstResult) {
        return findMaevartEntities(false, maxResults, firstResult);
    }

    private List<Maevart> findMaevartEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Maevart.class));
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

    public Maevart findMaevart(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Maevart.class, id);
        } finally {
            em.close();
        }
    }

    public int getMaevartCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Maevart> rt = cq.from(Maevart.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
