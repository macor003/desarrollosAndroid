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
import com.grid.ventas.cr.crjpacrsrc.Estadodecaja;
import com.grid.ventas.cr.crjpacrsrc.Puntoagilprocesoestadocaja;

/**
 *
 * @author eve0017909
 */
public class EstadodecajaJpaController implements Serializable {

    public EstadodecajaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Estadodecaja estadodecaja) throws PreexistingEntityException, Exception {
        if (estadodecaja.getCajaList() == null) {
            estadodecaja.setCajaList(new ArrayList<Caja>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Puntoagilprocesoestadocaja puntoagilprocesoestadocaja = estadodecaja.getPuntoagilprocesoestadocaja();
            if (puntoagilprocesoestadocaja != null) {
                puntoagilprocesoestadocaja = em.getReference(puntoagilprocesoestadocaja.getClass(),
                                                             puntoagilprocesoestadocaja.getIdestado());
                estadodecaja.setPuntoagilprocesoestadocaja(puntoagilprocesoestadocaja);
            }
            List<Caja> attachedCajaList = new ArrayList<Caja>();
            for (Caja cajaListCajaToAttach : estadodecaja.getCajaList()) {
                cajaListCajaToAttach = em.getReference(cajaListCajaToAttach.getClass(),
                                                       cajaListCajaToAttach.getCajaPK());
                attachedCajaList.add(cajaListCajaToAttach);
            }
            estadodecaja.setCajaList(attachedCajaList);
            em.persist(estadodecaja);
            if (puntoagilprocesoestadocaja != null) {
                Estadodecaja oldEstadodecajaOfPuntoagilprocesoestadocaja = puntoagilprocesoestadocaja
                        .getEstadodecaja();
                if (oldEstadodecajaOfPuntoagilprocesoestadocaja != null) {
                    oldEstadodecajaOfPuntoagilprocesoestadocaja.setPuntoagilprocesoestadocaja(null);
                    oldEstadodecajaOfPuntoagilprocesoestadocaja = em
                            .merge(oldEstadodecajaOfPuntoagilprocesoestadocaja);
                }
                puntoagilprocesoestadocaja.setEstadodecaja(estadodecaja);
                puntoagilprocesoestadocaja = em.merge(puntoagilprocesoestadocaja);
            }
            for (Caja cajaListCaja : estadodecaja.getCajaList()) {
                Estadodecaja oldIdestadocajaOfCajaListCaja = cajaListCaja.getIdestadocaja();
                cajaListCaja.setIdestadocaja(estadodecaja);
                cajaListCaja = em.merge(cajaListCaja);
                if (oldIdestadocajaOfCajaListCaja != null) {
                    oldIdestadocajaOfCajaListCaja.getCajaList().remove(cajaListCaja);
                    oldIdestadocajaOfCajaListCaja = em.merge(oldIdestadocajaOfCajaListCaja);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEstadodecaja(estadodecaja.getIdestado()) != null) {
                throw new PreexistingEntityException("Estadodecaja " + estadodecaja + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Estadodecaja estadodecaja)
            throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estadodecaja persistentEstadodecaja = em.find(Estadodecaja.class, estadodecaja.getIdestado());
            Puntoagilprocesoestadocaja puntoagilprocesoestadocajaOld = persistentEstadodecaja
                    .getPuntoagilprocesoestadocaja();
            Puntoagilprocesoestadocaja puntoagilprocesoestadocajaNew = estadodecaja
                    .getPuntoagilprocesoestadocaja();
            List<Caja> cajaListOld = persistentEstadodecaja.getCajaList();
            List<Caja> cajaListNew = estadodecaja.getCajaList();
            List<String> illegalOrphanMessages = null;
            if (puntoagilprocesoestadocajaOld != null
                    && !puntoagilprocesoestadocajaOld.equals(puntoagilprocesoestadocajaNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Puntoagilprocesoestadocaja "
                        + puntoagilprocesoestadocajaOld + " since its estadodecaja field is not nullable.");
            }
            for (Caja cajaListOldCaja : cajaListOld) {
                if (!cajaListNew.contains(cajaListOldCaja)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Caja " + cajaListOldCaja
                            + " since its idestadocaja field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (puntoagilprocesoestadocajaNew != null) {
                puntoagilprocesoestadocajaNew = em.getReference(puntoagilprocesoestadocajaNew.getClass(),
                                                                puntoagilprocesoestadocajaNew.getIdestado());
                estadodecaja.setPuntoagilprocesoestadocaja(puntoagilprocesoestadocajaNew);
            }
            List<Caja> attachedCajaListNew = new ArrayList<Caja>();
            for (Caja cajaListNewCajaToAttach : cajaListNew) {
                cajaListNewCajaToAttach = em.getReference(cajaListNewCajaToAttach.getClass(),
                                                          cajaListNewCajaToAttach.getCajaPK());
                attachedCajaListNew.add(cajaListNewCajaToAttach);
            }
            cajaListNew = attachedCajaListNew;
            estadodecaja.setCajaList(cajaListNew);
            estadodecaja = em.merge(estadodecaja);
            if (puntoagilprocesoestadocajaNew != null
                    && !puntoagilprocesoestadocajaNew.equals(puntoagilprocesoestadocajaOld)) {
                Estadodecaja oldEstadodecajaOfPuntoagilprocesoestadocaja = puntoagilprocesoestadocajaNew
                        .getEstadodecaja();
                if (oldEstadodecajaOfPuntoagilprocesoestadocaja != null) {
                    oldEstadodecajaOfPuntoagilprocesoestadocaja.setPuntoagilprocesoestadocaja(null);
                    oldEstadodecajaOfPuntoagilprocesoestadocaja = em
                            .merge(oldEstadodecajaOfPuntoagilprocesoestadocaja);
                }
                puntoagilprocesoestadocajaNew.setEstadodecaja(estadodecaja);
                puntoagilprocesoestadocajaNew = em.merge(puntoagilprocesoestadocajaNew);
            }
            for (Caja cajaListNewCaja : cajaListNew) {
                if (!cajaListOld.contains(cajaListNewCaja)) {
                    Estadodecaja oldIdestadocajaOfCajaListNewCaja = cajaListNewCaja.getIdestadocaja();
                    cajaListNewCaja.setIdestadocaja(estadodecaja);
                    cajaListNewCaja = em.merge(cajaListNewCaja);
                    if (oldIdestadocajaOfCajaListNewCaja != null
                            && !oldIdestadocajaOfCajaListNewCaja.equals(estadodecaja)) {
                        oldIdestadocajaOfCajaListNewCaja.getCajaList().remove(cajaListNewCaja);
                        oldIdestadocajaOfCajaListNewCaja = em.merge(oldIdestadocajaOfCajaListNewCaja);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = estadodecaja.getIdestado();
                if (findEstadodecaja(id) == null) {
                    throw new NonexistentEntityException("The estadodecaja with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estadodecaja estadodecaja;
            try {
                estadodecaja = em.getReference(Estadodecaja.class, id);
                estadodecaja.getIdestado();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estadodecaja with id " + id + " no longer exists.",
                        enfe);
            }
            List<String> illegalOrphanMessages = null;
            Puntoagilprocesoestadocaja puntoagilprocesoestadocajaOrphanCheck = estadodecaja
                    .getPuntoagilprocesoestadocaja();
            if (puntoagilprocesoestadocajaOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Estadodecaja (" + estadodecaja
                        + ") cannot be destroyed since the Puntoagilprocesoestadocaja "
                        + puntoagilprocesoestadocajaOrphanCheck
                        + " in its puntoagilprocesoestadocaja field has a non-nullable estadodecaja field.");
            }
            List<Caja> cajaListOrphanCheck = estadodecaja.getCajaList();
            for (Caja cajaListOrphanCheckCaja : cajaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Estadodecaja (" + estadodecaja
                        + ") cannot be destroyed since the Caja " + cajaListOrphanCheckCaja
                        + " in its cajaList field has a non-nullable idestadocaja field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(estadodecaja);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Estadodecaja> findEstadodecajaEntities() {
        return findEstadodecajaEntities(true, -1, -1);
    }

    public List<Estadodecaja> findEstadodecajaEntities(int maxResults, int firstResult) {
        return findEstadodecajaEntities(false, maxResults, firstResult);
    }

    private List<Estadodecaja> findEstadodecajaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Estadodecaja.class));
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

    public Estadodecaja findEstadodecaja(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Estadodecaja.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstadodecajaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Estadodecaja> rt = cq.from(Estadodecaja.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
