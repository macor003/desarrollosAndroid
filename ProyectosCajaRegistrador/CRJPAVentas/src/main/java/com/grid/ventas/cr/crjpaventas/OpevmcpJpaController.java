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
import com.grid.ventas.cr.crjpaventassrc.Opevmcp;
import com.grid.ventas.cr.crjpaventassrc.OpevmcpPK;

/**
 *
 * @author eve0017909
 */
public class OpevmcpJpaController implements Serializable {

    public OpevmcpJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Opevmcp opevmcp) throws PreexistingEntityException, Exception {
        if (opevmcp.getOpevmcpPK() == null) {
            opevmcp.setOpevmcpPK(new OpevmcpPK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(opevmcp);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findOpevmcp(opevmcp.getOpevmcpPK()) != null) {
                throw new PreexistingEntityException("Opevmcp " + opevmcp + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Opevmcp opevmcp) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            opevmcp = em.merge(opevmcp);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                OpevmcpPK id = opevmcp.getOpevmcpPK();
                if (findOpevmcp(id) == null) {
                    throw new NonexistentEntityException("The opevmcp with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(OpevmcpPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Opevmcp opevmcp;
            try {
                opevmcp = em.getReference(Opevmcp.class, id);
                opevmcp.getOpevmcpPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The opevmcp with id " + id + " no longer exists.", enfe);
            }
            em.remove(opevmcp);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Opevmcp> findOpevmcpEntities() {
        return findOpevmcpEntities(true, -1, -1);
    }

    public List<Opevmcp> findOpevmcpEntities(int maxResults, int firstResult) {
        return findOpevmcpEntities(false, maxResults, firstResult);
    }

    private List<Opevmcp> findOpevmcpEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Opevmcp.class));
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

    public Opevmcp findOpevmcp(OpevmcpPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Opevmcp.class, id);
        } finally {
            em.close();
        }
    }

    public int getOpevmcpCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Opevmcp> rt = cq.from(Opevmcp.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
