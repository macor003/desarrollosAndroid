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
import com.grid.ventas.cr.crjpaventassrc.Maevimp;
import com.grid.ventas.cr.crjpaventassrc.MaevimpPK;

/**
 *
 * @author eve0017909
 */
public class MaevimpJpaController implements Serializable {

    public MaevimpJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Maevimp maevimp) throws PreexistingEntityException, Exception {
        if (maevimp.getMaevimpPK() == null) {
            maevimp.setMaevimpPK(new MaevimpPK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(maevimp);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMaevimp(maevimp.getMaevimpPK()) != null) {
                throw new PreexistingEntityException("Maevimp " + maevimp + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Maevimp maevimp) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            maevimp = em.merge(maevimp);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                MaevimpPK id = maevimp.getMaevimpPK();
                if (findMaevimp(id) == null) {
                    throw new NonexistentEntityException("The maevimp with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(MaevimpPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Maevimp maevimp;
            try {
                maevimp = em.getReference(Maevimp.class, id);
                maevimp.getMaevimpPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The maevimp with id " + id + " no longer exists.", enfe);
            }
            em.remove(maevimp);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Maevimp> findMaevimpEntities() {
        return findMaevimpEntities(true, -1, -1);
    }

    public List<Maevimp> findMaevimpEntities(int maxResults, int firstResult) {
        return findMaevimpEntities(false, maxResults, firstResult);
    }

    private List<Maevimp> findMaevimpEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Maevimp.class));
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

    public Maevimp findMaevimp(MaevimpPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Maevimp.class, id);
        } finally {
            em.close();
        }
    }

    public int getMaevimpCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Maevimp> rt = cq.from(Maevimp.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
