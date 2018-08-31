/*
 * To change this license header, choose License Headers in Project Properties. To change
 * this template file, choose Tools | Templates and open the template in the editor.
 */
package com.grid.ventas.cr.crjpacr;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.grid.ventas.cr.crjpacr.exceptions.NonexistentEntityException;
import com.grid.ventas.cr.crjpacr.exceptions.PreexistingEntityException;
import com.grid.ventas.cr.crjpacrsrc.Donacion;
import com.grid.ventas.cr.crjpacrsrc.DonacionPK;

/**
 *
 * @author eve0017909
 */
public class DonacionJpaController implements Serializable {

    public DonacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Donacion donacion) throws PreexistingEntityException, Exception {
        if (donacion.getDonacionPK() == null) {
            donacion.setDonacionPK(new DonacionPK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(donacion);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDonacion(donacion.getDonacionPK()) != null) {
                throw new PreexistingEntityException("Donacion " + donacion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Donacion donacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            donacion = em.merge(donacion);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                DonacionPK id = donacion.getDonacionPK();
                if (findDonacion(id) == null) {
                    throw new NonexistentEntityException("The donacion with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(DonacionPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Donacion donacion;
            try {
                donacion = em.getReference(Donacion.class, id);
                donacion.getDonacionPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The donacion with id " + id + " no longer exists.", enfe);
            }
            em.remove(donacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Donacion> findDonacionEntities() {
        return findDonacionEntities(true, -1, -1);
    }

    public List<Donacion> findDonacionEntities(int maxResults, int firstResult) {
        return findDonacionEntities(false, maxResults, firstResult);
    }

    private List<Donacion> findDonacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Donacion.class));
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

    public Donacion findDonacion(DonacionPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Donacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getDonacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Donacion> rt = cq.from(Donacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
