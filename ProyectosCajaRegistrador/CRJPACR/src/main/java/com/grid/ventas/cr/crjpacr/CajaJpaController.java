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
import com.grid.ventas.cr.crjpacrsrc.Caja;
import com.grid.ventas.cr.crjpacrsrc.CajaPK;
import com.grid.ventas.cr.crjpacrsrc.Estadodecaja;
import com.grid.ventas.cr.crjpacrsrc.Tienda;

/**
 *
 * @author eve0017909
 */
public class CajaJpaController implements Serializable {

    public CajaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Caja caja) throws PreexistingEntityException, Exception {
        if (caja.getCajaPK() == null) {
            caja.setCajaPK(new CajaPK());
        }
        caja.getCajaPK().setNumtienda(caja.getTienda().getNumtienda());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estadodecaja idestadocaja = caja.getIdestadocaja();
            if (idestadocaja != null) {
                idestadocaja = em.getReference(idestadocaja.getClass(), idestadocaja.getIdestado());
                caja.setIdestadocaja(idestadocaja);
            }
            Tienda tienda = caja.getTienda();
            if (tienda != null) {
                tienda = em.getReference(tienda.getClass(), tienda.getNumtienda());
                caja.setTienda(tienda);
            }
            em.persist(caja);
            if (idestadocaja != null) {
                idestadocaja.getCajaList().add(caja);
                idestadocaja = em.merge(idestadocaja);
            }
            if (tienda != null) {
                tienda.getCajaList().add(caja);
                tienda = em.merge(tienda);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCaja(caja.getCajaPK()) != null) {
                throw new PreexistingEntityException("Caja " + caja + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Caja caja) throws NonexistentEntityException, Exception {
        caja.getCajaPK().setNumtienda(caja.getTienda().getNumtienda());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Caja persistentCaja = em.find(Caja.class, caja.getCajaPK());
            Estadodecaja idestadocajaOld = persistentCaja.getIdestadocaja();
            Estadodecaja idestadocajaNew = caja.getIdestadocaja();
            Tienda tiendaOld = persistentCaja.getTienda();
            Tienda tiendaNew = caja.getTienda();
            if (idestadocajaNew != null) {
                idestadocajaNew = em.getReference(idestadocajaNew.getClass(), idestadocajaNew.getIdestado());
                caja.setIdestadocaja(idestadocajaNew);
            }
            if (tiendaNew != null) {
                tiendaNew = em.getReference(tiendaNew.getClass(), tiendaNew.getNumtienda());
                caja.setTienda(tiendaNew);
            }
            caja = em.merge(caja);
            if (idestadocajaOld != null && !idestadocajaOld.equals(idestadocajaNew)) {
                idestadocajaOld.getCajaList().remove(caja);
                idestadocajaOld = em.merge(idestadocajaOld);
            }
            if (idestadocajaNew != null && !idestadocajaNew.equals(idestadocajaOld)) {
                idestadocajaNew.getCajaList().add(caja);
                idestadocajaNew = em.merge(idestadocajaNew);
            }
            if (tiendaOld != null && !tiendaOld.equals(tiendaNew)) {
                tiendaOld.getCajaList().remove(caja);
                tiendaOld = em.merge(tiendaOld);
            }
            if (tiendaNew != null && !tiendaNew.equals(tiendaOld)) {
                tiendaNew.getCajaList().add(caja);
                tiendaNew = em.merge(tiendaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                CajaPK id = caja.getCajaPK();
                if (findCaja(id) == null) {
                    throw new NonexistentEntityException("The caja with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CajaPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Caja caja;
            try {
                caja = em.getReference(Caja.class, id);
                caja.getCajaPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The caja with id " + id + " no longer exists.", enfe);
            }
            Estadodecaja idestadocaja = caja.getIdestadocaja();
            if (idestadocaja != null) {
                idestadocaja.getCajaList().remove(caja);
                idestadocaja = em.merge(idestadocaja);
            }
            Tienda tienda = caja.getTienda();
            if (tienda != null) {
                tienda.getCajaList().remove(caja);
                tienda = em.merge(tienda);
            }
            em.remove(caja);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Caja> findCajaEntities() {
        return findCajaEntities(true, -1, -1);
    }

    public List<Caja> findCajaEntities(int maxResults, int firstResult) {
        return findCajaEntities(false, maxResults, firstResult);
    }

    private List<Caja> findCajaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Caja.class));
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

    public Caja findCaja(CajaPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Caja.class, id);
        } finally {
            em.close();
        }
    }

    public int getCajaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Caja> rt = cq.from(Caja.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
