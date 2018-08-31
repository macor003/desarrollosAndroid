/*
 * To change this license header, choose License Headers in Project Properties. To change
 * this template file, choose Tools | Templates and open the template in the editor.
 */
package com.grid.ventas.cr.crjpaventas;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.grid.ventas.cr.crjpaventas.exceptions.NonexistentEntityException;
import com.grid.ventas.cr.crjpaventas.exceptions.PreexistingEntityException;
import com.grid.ventas.cr.crjpaventassrc.Cabvacr;
import com.grid.ventas.cr.crjpaventassrc.CabvacrPK;

/**
 *
 * @author eve0017909
 */
public class CabvacrJpaController extends AbstractJPAController {

    private static String entityName = "Cabvacr";

    private EntityManagerFactory emf;

    public CabvacrJpaController(EntityManagerFactory emf) {
        super(AbstractJPAController.SERVERSOURCE, entityName);
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cabvacr cabvacr) throws PreexistingEntityException, Exception {
        if (cabvacr.getCabvacrPK() == null) {
            cabvacr.setCabvacrPK(new CabvacrPK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(cabvacr);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCabvacr(cabvacr.getCabvacrPK()) != null) {
                throw new PreexistingEntityException("Cabvacr " + cabvacr + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cabvacr cabvacr) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            cabvacr = em.merge(cabvacr);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                CabvacrPK id = cabvacr.getCabvacrPK();
                if (findCabvacr(id) == null) {
                    throw new NonexistentEntityException("The cabvacr with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CabvacrPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cabvacr cabvacr;
            try {
                cabvacr = em.getReference(Cabvacr.class, id);
                cabvacr.getCabvacrPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cabvacr with id " + id + " no longer exists.", enfe);
            }
            em.remove(cabvacr);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cabvacr> findCabvacrEntities() {
        return findCabvacrEntities(true, -1, -1);
    }

    public List<Cabvacr> findCabvacrEntities(int maxResults, int firstResult) {
        return findCabvacrEntities(false, maxResults, firstResult);
    }

    private List<Cabvacr> findCabvacrEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cabvacr.class));
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

    public Cabvacr findCabvacr(CabvacrPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cabvacr.class, id);
        } finally {
            em.close();
        }
    }

    public int getCabvacrCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cabvacr> rt = cq.from(Cabvacr.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
