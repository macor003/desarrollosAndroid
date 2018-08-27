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
import com.grid.ventas.cr.crjpacrsrc.Controldeformularios;
import com.grid.ventas.cr.crjpacrsrc.ControldeformulariosPK;

/**
 *
 * @author eve0017909
 */
public class ControldeformulariosJpaController implements Serializable {

    public ControldeformulariosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Controldeformularios controldeformularios) throws PreexistingEntityException, Exception {
        if (controldeformularios.getControldeformulariosPK() == null) {
            controldeformularios.setControldeformulariosPK(new ControldeformulariosPK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(controldeformularios);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findControldeformularios(controldeformularios.getControldeformulariosPK()) != null) {
                throw new PreexistingEntityException(
                        "Controldeformularios " + controldeformularios + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Controldeformularios controldeformularios) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            controldeformularios = em.merge(controldeformularios);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                ControldeformulariosPK id = controldeformularios.getControldeformulariosPK();
                if (findControldeformularios(id) == null) {
                    throw new NonexistentEntityException(
                            "The controldeformularios with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(ControldeformulariosPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Controldeformularios controldeformularios;
            try {
                controldeformularios = em.getReference(Controldeformularios.class, id);
                controldeformularios.getControldeformulariosPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException(
                        "The controldeformularios with id " + id + " no longer exists.", enfe);
            }
            em.remove(controldeformularios);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Controldeformularios> findControldeformulariosEntities() {
        return findControldeformulariosEntities(true, -1, -1);
    }

    public List<Controldeformularios> findControldeformulariosEntities(int maxResults, int firstResult) {
        return findControldeformulariosEntities(false, maxResults, firstResult);
    }

    private List<Controldeformularios> findControldeformulariosEntities(boolean all, int maxResults,
                                                                        int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Controldeformularios.class));
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

    public Controldeformularios findControldeformularios(ControldeformulariosPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Controldeformularios.class, id);
        } finally {
            em.close();
        }
    }

    public int getControldeformulariosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Controldeformularios> rt = cq.from(Controldeformularios.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
