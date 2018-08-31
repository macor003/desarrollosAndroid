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
import com.grid.ventas.cr.crjpaventassrc.Maecec;
import com.grid.ventas.cr.crjpaventassrc.MaececPK;

/**
 *
 * @author eve0017909
 */
public class MaececJpaController implements Serializable {

    public MaececJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Maecec maecec) throws PreexistingEntityException, Exception {
        if (maecec.getMaececPK() == null) {
            maecec.setMaececPK(new MaececPK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(maecec);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMaecec(maecec.getMaececPK()) != null) {
                throw new PreexistingEntityException("Maecec " + maecec + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Maecec maecec) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            maecec = em.merge(maecec);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                MaececPK id = maecec.getMaececPK();
                if (findMaecec(id) == null) {
                    throw new NonexistentEntityException("The maecec with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(MaececPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Maecec maecec;
            try {
                maecec = em.getReference(Maecec.class, id);
                maecec.getMaececPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The maecec with id " + id + " no longer exists.", enfe);
            }
            em.remove(maecec);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Maecec> findMaececEntities() {
        return findMaececEntities(true, -1, -1);
    }

    public List<Maecec> findMaececEntities(int maxResults, int firstResult) {
        return findMaececEntities(false, maxResults, firstResult);
    }

    private List<Maecec> findMaececEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Maecec.class));
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

    public Maecec findMaecec(MaececPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Maecec.class, id);
        } finally {
            em.close();
        }
    }

    public int getMaececCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Maecec> rt = cq.from(Maecec.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
