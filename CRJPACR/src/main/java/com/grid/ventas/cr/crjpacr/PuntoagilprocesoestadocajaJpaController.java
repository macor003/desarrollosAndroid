/*
 * To change this license header, choose License Headers in Project Properties. To change
 * this template file, choose Tools | Templates and open the template in the editor.
 */
package com.grid.ventas.cr.crjpacr;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.grid.ventas.cr.crjpacr.exceptions.IllegalOrphanException;
import com.grid.ventas.cr.crjpacr.exceptions.NonexistentEntityException;
import com.grid.ventas.cr.crjpacr.exceptions.PreexistingEntityException;
import com.grid.ventas.cr.crjpacrsrc.Estadodecaja;
import com.grid.ventas.cr.crjpacrsrc.Puntoagilprocesoestadocaja;

/**
 *
 * @author eve0017909
 */
public class PuntoagilprocesoestadocajaJpaController implements Serializable {

    public PuntoagilprocesoestadocajaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Puntoagilprocesoestadocaja puntoagilprocesoestadocaja)
            throws IllegalOrphanException, PreexistingEntityException, Exception {
        List<String> illegalOrphanMessages = null;
        Estadodecaja estadodecajaOrphanCheck = puntoagilprocesoestadocaja.getEstadodecaja();
        if (estadodecajaOrphanCheck != null) {
            Puntoagilprocesoestadocaja oldPuntoagilprocesoestadocajaOfEstadodecaja = estadodecajaOrphanCheck
                    .getPuntoagilprocesoestadocaja();
            if (oldPuntoagilprocesoestadocajaOfEstadodecaja != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Estadodecaja " + estadodecajaOrphanCheck
                        + " already has an item of type Puntoagilprocesoestadocaja whose estadodecaja column cannot be null. Please make another selection for the estadodecaja field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estadodecaja estadodecaja = puntoagilprocesoestadocaja.getEstadodecaja();
            if (estadodecaja != null) {
                estadodecaja = em.getReference(estadodecaja.getClass(), estadodecaja.getIdestado());
                puntoagilprocesoestadocaja.setEstadodecaja(estadodecaja);
            }
            em.persist(puntoagilprocesoestadocaja);
            if (estadodecaja != null) {
                estadodecaja.setPuntoagilprocesoestadocaja(puntoagilprocesoestadocaja);
                estadodecaja = em.merge(estadodecaja);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPuntoagilprocesoestadocaja(puntoagilprocesoestadocaja.getIdestado()) != null) {
                throw new PreexistingEntityException(
                        "Puntoagilprocesoestadocaja " + puntoagilprocesoestadocaja + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Puntoagilprocesoestadocaja puntoagilprocesoestadocaja)
            throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Puntoagilprocesoestadocaja persistentPuntoagilprocesoestadocaja = em
                    .find(Puntoagilprocesoestadocaja.class, puntoagilprocesoestadocaja.getIdestado());
            Estadodecaja estadodecajaOld = persistentPuntoagilprocesoestadocaja.getEstadodecaja();
            Estadodecaja estadodecajaNew = puntoagilprocesoestadocaja.getEstadodecaja();
            List<String> illegalOrphanMessages = null;
            if (estadodecajaNew != null && !estadodecajaNew.equals(estadodecajaOld)) {
                Puntoagilprocesoestadocaja oldPuntoagilprocesoestadocajaOfEstadodecaja = estadodecajaNew
                        .getPuntoagilprocesoestadocaja();
                if (oldPuntoagilprocesoestadocajaOfEstadodecaja != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Estadodecaja " + estadodecajaNew
                            + " already has an item of type Puntoagilprocesoestadocaja whose estadodecaja column cannot be null. Please make another selection for the estadodecaja field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (estadodecajaNew != null) {
                estadodecajaNew = em.getReference(estadodecajaNew.getClass(), estadodecajaNew.getIdestado());
                puntoagilprocesoestadocaja.setEstadodecaja(estadodecajaNew);
            }
            puntoagilprocesoestadocaja = em.merge(puntoagilprocesoestadocaja);
            if (estadodecajaOld != null && !estadodecajaOld.equals(estadodecajaNew)) {
                estadodecajaOld.setPuntoagilprocesoestadocaja(null);
                estadodecajaOld = em.merge(estadodecajaOld);
            }
            if (estadodecajaNew != null && !estadodecajaNew.equals(estadodecajaOld)) {
                estadodecajaNew.setPuntoagilprocesoestadocaja(puntoagilprocesoestadocaja);
                estadodecajaNew = em.merge(estadodecajaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = puntoagilprocesoestadocaja.getIdestado();
                if (findPuntoagilprocesoestadocaja(id) == null) {
                    throw new NonexistentEntityException(
                            "The puntoagilprocesoestadocaja with id " + id + " no longer exists.");
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
            Puntoagilprocesoestadocaja puntoagilprocesoestadocaja;
            try {
                puntoagilprocesoestadocaja = em.getReference(Puntoagilprocesoestadocaja.class, id);
                puntoagilprocesoestadocaja.getIdestado();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException(
                        "The puntoagilprocesoestadocaja with id " + id + " no longer exists.", enfe);
            }
            Estadodecaja estadodecaja = puntoagilprocesoestadocaja.getEstadodecaja();
            if (estadodecaja != null) {
                estadodecaja.setPuntoagilprocesoestadocaja(null);
                estadodecaja = em.merge(estadodecaja);
            }
            em.remove(puntoagilprocesoestadocaja);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Puntoagilprocesoestadocaja> findPuntoagilprocesoestadocajaEntities() {
        return findPuntoagilprocesoestadocajaEntities(true, -1, -1);
    }

    public List<Puntoagilprocesoestadocaja> findPuntoagilprocesoestadocajaEntities(int maxResults,
                                                                                   int firstResult) {
        return findPuntoagilprocesoestadocajaEntities(false, maxResults, firstResult);
    }

    private List<Puntoagilprocesoestadocaja> findPuntoagilprocesoestadocajaEntities(boolean all, int maxResults,
                                                                                    int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Puntoagilprocesoestadocaja.class));
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

    public Puntoagilprocesoestadocaja findPuntoagilprocesoestadocaja(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Puntoagilprocesoestadocaja.class, id);
        } finally {
            em.close();
        }
    }

    public int getPuntoagilprocesoestadocajaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Puntoagilprocesoestadocaja> rt = cq.from(Puntoagilprocesoestadocaja.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
