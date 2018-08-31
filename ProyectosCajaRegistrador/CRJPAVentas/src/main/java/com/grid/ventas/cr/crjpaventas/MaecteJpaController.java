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
import com.grid.ventas.cr.crjpaventassrc.Maecte;

/**
 *
 * @author eve0017909
 */
public class MaecteJpaController implements Serializable {

    public MaecteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Maecte maecte) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(maecte);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMaecte(maecte.getRifcte()) != null) {
                throw new PreexistingEntityException("Maecte " + maecte + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Maecte maecte) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            maecte = em.merge(maecte);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = maecte.getRifcte();
                if (findMaecte(id) == null) {
                    throw new NonexistentEntityException("The maecte with id " + id + " no longer exists.");
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
            Maecte maecte;
            try {
                maecte = em.getReference(Maecte.class, id);
                maecte.getRifcte();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The maecte with id " + id + " no longer exists.", enfe);
            }
            em.remove(maecte);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Maecte> findMaecteEntities() {
        return findMaecteEntities(true, -1, -1);
    }

    public List<Maecte> findMaecteEntities(int maxResults, int firstResult) {
        return findMaecteEntities(false, maxResults, firstResult);
    }

    private List<Maecte> findMaecteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Maecte.class));
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

    public Maecte findMaecte(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Maecte.class, id);
        } finally {
            em.close();
        }
    }

    public int getMaecteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Maecte> rt = cq.from(Maecte.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
