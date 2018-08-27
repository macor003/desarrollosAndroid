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
import com.grid.ventas.cr.crjpaventassrc.Hisvrez;
import com.grid.ventas.cr.crjpaventassrc.HisvrezPK;

/**
 *
 * @author eve0017909
 */
public class HisvrezJpaController implements Serializable {

    public HisvrezJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Hisvrez hisvrez) throws PreexistingEntityException, Exception {
        if (hisvrez.getHisvrezPK() == null) {
            hisvrez.setHisvrezPK(new HisvrezPK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(hisvrez);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findHisvrez(hisvrez.getHisvrezPK()) != null) {
                throw new PreexistingEntityException("Hisvrez " + hisvrez + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Hisvrez hisvrez) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            hisvrez = em.merge(hisvrez);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                HisvrezPK id = hisvrez.getHisvrezPK();
                if (findHisvrez(id) == null) {
                    throw new NonexistentEntityException("The hisvrez with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(HisvrezPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Hisvrez hisvrez;
            try {
                hisvrez = em.getReference(Hisvrez.class, id);
                hisvrez.getHisvrezPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hisvrez with id " + id + " no longer exists.", enfe);
            }
            em.remove(hisvrez);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Hisvrez> findHisvrezEntities() {
        return findHisvrezEntities(true, -1, -1);
    }

    public List<Hisvrez> findHisvrezEntities(int maxResults, int firstResult) {
        return findHisvrezEntities(false, maxResults, firstResult);
    }

    private List<Hisvrez> findHisvrezEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Hisvrez.class));
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

    public Hisvrez findHisvrez(HisvrezPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Hisvrez.class, id);
        } finally {
            em.close();
        }
    }

    public int getHisvrezCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Hisvrez> rt = cq.from(Hisvrez.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
