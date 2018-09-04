/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates and open the template in the editor.
 */
package com.becoblohm.cr.crjpa.controller;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.becoblohm.cr.crjpa.controller.exceptions.JpaException;
import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.becoblohm.cr.models.Session;

import crjpa.Sesion;

/**
 * 
 * @author Yelitza Farfan (programador11)
 * 
 * @version $Revision: 1.0 $
 */
public class SesionJpaController extends AbstractJPAController {

    /**
     * Field jpacontroller.
     */
    private final crjpa.SesionJpaController jpacontroller;

    /**
     * Field emf.
     */
    private final EntityManagerFactory emf;

    /**
     * Field entityName.
     */
    private static String entityName = "Sesion";

    /**
     * Constructor for SesionJpaController.
     * 
     * @param emf EntityManagerFactory
     */
    public SesionJpaController(EntityManagerFactory emf) {
        super(AbstractJPAController.POSSOURCE, entityName);
        this.emf = emf;
        jpacontroller = new crjpa.SesionJpaController(this.emf);

    }

    // public void edit(Session session){
    // Sesion sesion = new Sesion();
    // sesion = toJPA(session);
    // try {
    // jpacontroller.edit(sesion);
    // } catch (IllegalOrphanException e) {
    //
    // e.printStackTrace();
    // } catch (NonexistentEntityException e) {
    //
    // e.printStackTrace();
    // } catch (Exception e) {
    //
    // e.printStackTrace();
    // }
    // }

    /**
     * Method edit.
     * 
     * @param session Session
     * @throws JpaException
     */
    public void edit(Session session) throws JpaException {
        Sesion sesion = new Sesion();
        sesion = toJPA(session);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(sesion);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            throw new JpaException();
        } finally {
            if (em.isOpen()) {
                em.clear();
                em.close();
            }
        }
    }

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Method toJPA.
     * 
     * @param currentSession Session
     * @return Sesion
     */
    public Sesion toJPA(Session currentSession) {
        Sesion sesion = new Sesion();
        UsuarioJpaController usuarioJpaController = new UsuarioJpaController(emf);
        // sesion = new
        // Sesion(currentSession.getId(),Integer.parseInt(currentSession.getPosId()),currentSession.getSalesCount(),currentSession.getAnulCount(),
        // currentSession.getDevCount(),currentSession.getDateTime(),currentSession.getClosureDate(),currentSession.getVposClosureDate(),currentSession.getzReport_printDate());
        if (currentSession.getId() > 0) {
            sesion.setId(currentSession.getId());
        } else {
            sesion.setId(null);
        }
        sesion.setAnulaciones(0);
        sesion.setIdCaja(Integer.valueOf(currentSession.getPosId()));
        sesion.setDevoluciones(currentSession.getDevCount());
        sesion.setFechaCierre(currentSession.getEnd());
        sesion.setFechaCierreVpos(currentSession.getVposClosureDate());
        sesion.setFechaInicio(currentSession.getBegin());
        sesion.setFechaUltimoReportez(currentSession.getzReport_printDate());
        sesion.setIdUsuario(usuarioJpaController.toJPA(currentSession.getCashier()));
        sesion.setVentas(currentSession.getSalesCount());
        sesion.setEstasincronizado("N");
        sesion.setVersioncr(currentSession.getPosVersion());
        sesion.setIdTienda(currentSession.getStoreAgilId());

        if (currentSession.getId() > 0) {
            Sesion tmp = this.jpacontroller.findSesion(currentSession.getId());
            if (tmp != null) {
                sesion.setEntregaList(tmp.getEntregaList());
                sesion.setReportezList(tmp.getReportezList());
                sesion.setTransaccionList(tmp.getTransaccionList());
            }
        }

        return sesion;
    }

    /**
     * Method findSesion.
     * 
     * @param id long
     * @return Sesion
     */
    protected Sesion findSesion(long id) {
        return jpacontroller.findSesion(id);
    }

    /**
     * Method findSession.
     * 
     * @param id long
     * @return Session
     */
    public Session findSession(long id) {
        return fromJPA(jpacontroller.findSesion(id));
    }

    // public Sesion findSesionOpen() {
    //
    // crjpa.Sesion singleResult=null;
    // EntityManager em = jpacontroller.getEntityManager();
    // Query query =
    // em.createQuery("SELECT s FROM Sesion s WHERE s.fechaCierre is NULL");
    //
    // try {
    // query.setMaxResults(1);
    // singleResult= (Sesion) query.getSingleResult();
    // } finally {
    // em.close();
    // }
    // return singleResult;
    // }

    /**
     * Method findSesionOpen.
     * 
     * @return Session
     */
    public Session findSesionOpen() {

        crjpa.Sesion singleResult = null;
        EntityManager em = jpacontroller.getEntityManager();
        Query query = em.createQuery("SELECT s FROM Sesion s WHERE s.fechaCierre is NULL");

        try {
            query.setMaxResults(1);
            singleResult = (Sesion) query.getSingleResult();
            return fromJPA(singleResult);
        } catch (NoResultException ex) {
            return null;
        } finally {
            em.clear();
            em.close();
        }
    }

    /**
     * Conslta la sesion por defecto creada para tareas automaticas
     * 
     * @return Session
     */
    public Session findSesionDeafault(long idDefault) {

        crjpa.Sesion singleResult = null;
        EntityManager em = jpacontroller.getEntityManager();
        Query query = em.createQuery("SELECT s FROM Sesion s WHERE s.id =:id");

        try {
            query.setParameter("id", idDefault);
            query.setMaxResults(1);
            singleResult = (Sesion) query.getSingleResult();
            return fromJPA(singleResult);
        } catch (NoResultException ex) {
            return null;
        } finally {
            em.clear();
            em.close();
        }
    }

    /**
     * Method findLastClosureVPosSesion.
     * 
     * @return Session
     */
    public Session findLastClosureVPosSesion() {
        crjpa.Sesion singleResult = null;
        EntityManager em = jpacontroller.getEntityManager();
        Query query = em
                .createQuery("SELECT s FROM Sesion s WHERE s.fechaCierreVpos = (SELECT MAX(f.fechaCierreVpos) FROM Sesion f) ");
        Session result = null;

        try {
            query.setMaxResults(1);
            singleResult = (Sesion) query.getSingleResult();
            result = fromJPA(singleResult);
        } catch (javax.persistence.NoResultException ex) {
            result = null;
        } finally {
            em.clear();
            em.close();
        }
        return result;
    }

    /**
     * Method findLastZReportDate.
     * 
     * @return Session
     */
    public Session findLastZReportDate() {
        crjpa.Sesion singleResult = null;
        EntityManager em = emf.createEntityManager();
        Query query = em
                .createQuery("SELECT s FROM Sesion s WHERE s.fechaUltimoReportez = (SELECT MAX(f.fechaUltimoReportez) FROM Sesion f) ");
        Session result = null;
        try {
            query.setMaxResults(1);
            singleResult = (Sesion) query.getSingleResult();
            result = fromJPA(singleResult);
        } catch (javax.persistence.NoResultException ex) {
            result = null;
            ex.printStackTrace();
        } finally {
            em.clear();
            em.close();
        }
        return result;
    }

    /**
     * Method findLastSession.
     * 
     * @return Session
     */
    public Session findLastSession() {
        crjpa.Sesion singleResult = null;
        EntityManager em = jpacontroller.getEntityManager();
        Query query = em.createQuery("SELECT s FROM Sesion s ORDER BY s.id DESC ");
        try {
            query.setMaxResults(1);
            singleResult = (Sesion) query.getSingleResult();
        } catch (javax.persistence.NoResultException ex) {
            singleResult = null;
        } finally {
            em.clear();
            em.close();
        }
        return fromJPA(singleResult);
    }

    /**
     * Metodo que busca la sesion mas reciente de un rango de fechas
     * 
     * @param dateIni Fecha inicial
     * @param dateFin Fecha final
     * @return Session
     */
    public Session findSessionByDates(Date dateIni, Date dateFin) {
        crjpa.Sesion singleResult = null;
        EntityManager em = jpacontroller.getEntityManager();
        Query query = em
                .createQuery("SELECT s FROM Sesion s WHERE s.fechaCierre >=:dateIni and s.fechaCierre <=:dateFin  ORDER BY s.id DESC ");
        try {
            query.setParameter("dateIni", dateIni);
            query.setParameter("dateFin", dateFin);
            query.setMaxResults(1);
            singleResult = (Sesion) query.getSingleResult();
        } catch (javax.persistence.NoResultException ex) {
            singleResult = null;
        } finally {
            em.clear();
            em.close();
        }

        if (singleResult == null) {
            return null;
        }
        return fromJPA(singleResult);
    }

    /**
     * Method fromJPA.
     * 
     * @param tmp Sesion
     * @return Session
     */
    public Session fromJPA(Sesion tmp) {
        Session session = new Session();
        UsuarioJpaController usuario = new UsuarioJpaController(emf);
        session.setAnulCount(tmp.getAnulaciones());
        session.setBegin(tmp.getFechaInicio());
        session.setCashier(usuario.fromJPA(tmp.getIdUsuario()));
        session.setDevCount(tmp.getDevoluciones());
        session.setEnd(tmp.getFechaCierre());
        session.setId(tmp.getId());
        session.setPosId(String.valueOf(tmp.getIdCaja()));
        session.setSalesCount(tmp.getVentas());
        System.out.println("ID: " + tmp.getId() + " Z:" + tmp.getFechaUltimoReportez());
        session.setzReport_printDate(tmp.getFechaUltimoReportez());
        session.setVposClosureDate(tmp.getFechaCierreVpos());
        session.setStoreAgilId(tmp.getIdTienda());

        // session.setStoreId(Global.getStore());

        return session;
    }

    // public void create(Session currentSession) {
    // Sesion sesion = toJPA(currentSession);
    // try {
    // jpacontroller.create(sesion);
    // } catch (PreexistingEntityException e) {
    //
    // e.printStackTrace();
    // } catch (Exception e) {
    //
    // e.printStackTrace();
    // }
    //
    // currentSession.setId(sesion.getId());
    //
    // }

    /**
     * Method create.
     * 
     * @param currentSession Session
     * @throws JpaException
     */
    public void create(Session currentSession) throws JpaException {
        Sesion sesion = toJPA(currentSession);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(sesion);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            throw new JpaException();
        } finally {
            if (em.isOpen()) {
                em.clear();
                em.close();
            }
        }
        currentSession.setId(sesion.getId());
    }

}
