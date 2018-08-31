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
import com.grid.ventas.cr.crjpaventassrc.Opevrtc;
import com.grid.ventas.cr.crjpaventassrc.OpevrtcPK;

/**
 *
 * @author eve0017909
 */
public class OpevrtcJpaController implements Serializable {

    public OpevrtcJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Opevrtc opevrtc) throws PreexistingEntityException, Exception {
        if (opevrtc.getOpevrtcPK() == null) {
            opevrtc.setOpevrtcPK(new OpevrtcPK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(opevrtc);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findOpevrtc(opevrtc.getOpevrtcPK()) != null) {
                throw new PreexistingEntityException("Opevrtc " + opevrtc + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Opevrtc opevrtc) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            opevrtc = em.merge(opevrtc);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                OpevrtcPK id = opevrtc.getOpevrtcPK();
                if (findOpevrtc(id) == null) {
                    throw new NonexistentEntityException("The opevrtc with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(OpevrtcPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Opevrtc opevrtc;
            try {
                opevrtc = em.getReference(Opevrtc.class, id);
                opevrtc.getOpevrtcPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The opevrtc with id " + id + " no longer exists.", enfe);
            }
            em.remove(opevrtc);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Opevrtc> findOpevrtcEntities() {
        return findOpevrtcEntities(true, -1, -1);
    }

    public List<Opevrtc> findOpevrtcEntities(int maxResults, int firstResult) {
        return findOpevrtcEntities(false, maxResults, firstResult);
    }

    private List<Opevrtc> findOpevrtcEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Opevrtc.class));
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

    public Opevrtc findOpevrtc(OpevrtcPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Opevrtc.class, id);
        } finally {
            em.close();
        }
    }

    public int getOpevrtcCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Opevrtc> rt = cq.from(Opevrtc.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
