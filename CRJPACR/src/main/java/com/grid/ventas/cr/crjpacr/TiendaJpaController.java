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
import com.grid.ventas.cr.crjpacrsrc.Caja;
import com.grid.ventas.cr.crjpacrsrc.Tienda;

/**
 *
 * @author eve0017909
 */
public class TiendaJpaController implements Serializable {

    public TiendaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tienda tienda) throws PreexistingEntityException, Exception {
        if (tienda.getCajaList() == null) {
            tienda.setCajaList(new ArrayList<Caja>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Caja> attachedCajaList = new ArrayList<Caja>();
            for (Caja cajaListCajaToAttach : tienda.getCajaList()) {
                cajaListCajaToAttach = em.getReference(cajaListCajaToAttach.getClass(),
                                                       cajaListCajaToAttach.getCajaPK());
                attachedCajaList.add(cajaListCajaToAttach);
            }
            tienda.setCajaList(attachedCajaList);
            em.persist(tienda);
            for (Caja cajaListCaja : tienda.getCajaList()) {
                Tienda oldTiendaOfCajaListCaja = cajaListCaja.getTienda();
                cajaListCaja.setTienda(tienda);
                cajaListCaja = em.merge(cajaListCaja);
                if (oldTiendaOfCajaListCaja != null) {
                    oldTiendaOfCajaListCaja.getCajaList().remove(cajaListCaja);
                    oldTiendaOfCajaListCaja = em.merge(oldTiendaOfCajaListCaja);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTienda(tienda.getNumtienda()) != null) {
                throw new PreexistingEntityException("Tienda " + tienda + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tienda tienda) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tienda persistentTienda = em.find(Tienda.class, tienda.getNumtienda());
            List<Caja> cajaListOld = persistentTienda.getCajaList();
            List<Caja> cajaListNew = tienda.getCajaList();
            List<String> illegalOrphanMessages = null;
            for (Caja cajaListOldCaja : cajaListOld) {
                if (!cajaListNew.contains(cajaListOldCaja)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Caja " + cajaListOldCaja
                            + " since its tienda field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Caja> attachedCajaListNew = new ArrayList<Caja>();
            for (Caja cajaListNewCajaToAttach : cajaListNew) {
                cajaListNewCajaToAttach = em.getReference(cajaListNewCajaToAttach.getClass(),
                                                          cajaListNewCajaToAttach.getCajaPK());
                attachedCajaListNew.add(cajaListNewCajaToAttach);
            }
            cajaListNew = attachedCajaListNew;
            tienda.setCajaList(cajaListNew);
            tienda = em.merge(tienda);
            for (Caja cajaListNewCaja : cajaListNew) {
                if (!cajaListOld.contains(cajaListNewCaja)) {
                    Tienda oldTiendaOfCajaListNewCaja = cajaListNewCaja.getTienda();
                    cajaListNewCaja.setTienda(tienda);
                    cajaListNewCaja = em.merge(cajaListNewCaja);
                    if (oldTiendaOfCajaListNewCaja != null && !oldTiendaOfCajaListNewCaja.equals(tienda)) {
                        oldTiendaOfCajaListNewCaja.getCajaList().remove(cajaListNewCaja);
                        oldTiendaOfCajaListNewCaja = em.merge(oldTiendaOfCajaListNewCaja);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Short id = tienda.getNumtienda();
                if (findTienda(id) == null) {
                    throw new NonexistentEntityException("The tienda with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Short id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tienda tienda;
            try {
                tienda = em.getReference(Tienda.class, id);
                tienda.getNumtienda();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tienda with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Caja> cajaListOrphanCheck = tienda.getCajaList();
            for (Caja cajaListOrphanCheckCaja : cajaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tienda (" + tienda + ") cannot be destroyed since the Caja "
                        + cajaListOrphanCheckCaja + " in its cajaList field has a non-nullable tienda field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(tienda);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tienda> findTiendaEntities() {
        return findTiendaEntities(true, -1, -1);
    }

    public List<Tienda> findTiendaEntities(int maxResults, int firstResult) {
        return findTiendaEntities(false, maxResults, firstResult);
    }

    private List<Tienda> findTiendaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tienda.class));
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

    public Tienda findTienda(Short id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tienda.class, id);
        } finally {
            em.close();
        }
    }

    public int getTiendaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tienda> rt = cq.from(Tienda.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
