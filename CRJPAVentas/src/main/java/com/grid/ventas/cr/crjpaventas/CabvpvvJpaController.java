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
import com.grid.ventas.cr.crjpaventassrc.Cabvpvv;
import com.grid.ventas.cr.crjpaventassrc.CabvpvvPK;

/**
 *
 * @author eve0017909
 */
public class CabvpvvJpaController implements Serializable {

    public CabvpvvJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cabvpvv cabvpvv) throws PreexistingEntityException, Exception {
        if (cabvpvv.getCabvpvvPK() == null) {
            cabvpvv.setCabvpvvPK(new CabvpvvPK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(cabvpvv);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCabvpvv(cabvpvv.getCabvpvvPK()) != null) {
                throw new PreexistingEntityException("Cabvpvv " + cabvpvv + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cabvpvv cabvpvv) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            cabvpvv = em.merge(cabvpvv);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                CabvpvvPK id = cabvpvv.getCabvpvvPK();
                if (findCabvpvv(id) == null) {
                    throw new NonexistentEntityException("The cabvpvv with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CabvpvvPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cabvpvv cabvpvv;
            try {
                cabvpvv = em.getReference(Cabvpvv.class, id);
                cabvpvv.getCabvpvvPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cabvpvv with id " + id + " no longer exists.", enfe);
            }
            em.remove(cabvpvv);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cabvpvv> findCabvpvvEntities() {
        return findCabvpvvEntities(true, -1, -1);
    }

    public List<Cabvpvv> findCabvpvvEntities(int maxResults, int firstResult) {
        return findCabvpvvEntities(false, maxResults, firstResult);
    }

    private List<Cabvpvv> findCabvpvvEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cabvpvv.class));
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

    public Cabvpvv findCabvpvv(CabvpvvPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cabvpvv.class, id);
        } finally {
            em.close();
        }
    }

    public int getCabvpvvCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cabvpvv> rt = cq.from(Cabvpvv.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
