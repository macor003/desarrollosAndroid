/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.epa.cr.legacy;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.becoblohm.cr.crjpa400.controller.exceptions.JpaException;
import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.becoblohm.cr.models.Session;

import crjpa400.Caja;
import crjpa400.CajaJpaController;
import crjpa400.Sesion;

/**
 */
public class SesionJpaController extends AbstractJPAController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 633677473563112843L;
	/**
	 * Field jpacontroller.
	 */
	private crjpa400.SesionJpaController jpacontroller;
	/**
	 * Field emf.
	 */
	private EntityManagerFactory emf;
	/**
	 * Field entityName.
	 */
	private static String entityName = "Sesion";

	/**
	 * Constructor for SesionJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public SesionJpaController(EntityManagerFactory emf) {
		super(AbstractJPAController.SERVERSOURCE, entityName);
		this.emf = emf;
		jpacontroller = new crjpa400.SesionJpaController(this.emf);
	}

	/**
	 * Method fromJPA.
	 * 
	 * @param tmp
	 *            Sesion
	 * @return Session
	 */
	public Session fromJPA(Sesion tmp) {
		Session session = new Session();
		session.setAnulCount((int) tmp.getAnulaciones());
		session.setBegin(tmp.getFechaInicio());
		session.setCashier(UsuarioJpaController.fromJpa(tmp.getIdUsuario()));
		session.setDevCount((int) tmp.getDevoluciones());
		session.setEnd(tmp.getFechaCierre());
		session.setId(tmp.getId());
		session.setPosId(String.valueOf(tmp.getIdCaja().getId()));
		session.setSalesCount((int) tmp.getVentas());
		OpcionJpaController opcionJpaController = new OpcionJpaController(this.emf);
		session.setStoreId(opcionJpaController.findById(new Long(1)));

		return session;
	}

	/**
	 * Method toJpa.
	 * 
	 * @param session
	 *            Session
	 * @return Sesion
	 */
	public Sesion toJpa(Session session) {
		Sesion sesion = new Sesion();

		sesion.setFechaCierre(session.getEnd());
		sesion.setFechaCierreVpos(session.getVposClosureDate());
		sesion.setFechaInicio(session.getBegin());
		sesion.setFechaUltimoReportez(session.getzReport_printDate());
		sesion.setId(session.getId());
		sesion.setVersioncr(session.getPosVersion());

		CajaJpaController cajaJpa = new CajaJpaController(this.emf);
		Caja caja = cajaJpa.findCaja(new Long(session.getPosId()));
		sesion.setIdCaja(caja);

		UsuarioJpaController usuarioJpa = new UsuarioJpaController(this.emf);
		sesion.setIdUsuario(usuarioJpa.toJpa(session.getCashier()));

		return sesion;
	}

	/**
	 * Method findSesion.
	 * 
	 * @param id
	 *            long
	 * @return Session
	 */
	public Session findSesion(long id) {
		Sesion tmp = jpacontroller.findSesion(id);
		if (tmp != null) {
			return fromJPA(jpacontroller.findSesion(id));
		}
		return null;
	}

	/**
	 * Method create.
	 * 
	 * @param currentSession
	 *            Session
	 * @return Session
	 * @throws JpaException
	 */
	public Session create(Session currentSession) throws JpaException {
		Sesion sesion = toJpa(currentSession);
		try {
			jpacontroller.create(sesion);
		} catch (Exception e) {

			e.printStackTrace();
			throw new JpaException();
		}

		currentSession.setId(sesion.getId());
		return fromJPA(sesion);
	}

	/**
	 * Method findLastOpenSession.
	 * 
	 * @param userId
	 *            Long
	 * @return Session
	 */
	public Session findLastOpenSession(Long userId) {
		Sesion tmp;
		Session returnValue;
		EntityManager em = null;
		try {
			em = emf.createEntityManager();
			String queryString = "SELECT s FROM Sesion s WHERE s.idUsuario.id=:id AND s.fechaCierre IS NULL ";
			Query query = em.createQuery(queryString);
			query.setParameter("id", userId);
			query.setMaxResults(1);
			tmp = (Sesion) query.getSingleResult();
			returnValue = fromJPA(tmp);
		} catch (NoResultException ex) {
			returnValue = null;
		} catch (Exception ex) {
			returnValue = null;
		} finally {
			if (em != null) {
				em.close();
			}
		}
		return returnValue;
	}

	/**
	 * Method findSessionConflicts.
	 * 
	 * @param userId
	 *            Long
	 * @param posType
	 *            Long
	 * @return boolean
	 */
	public boolean findSessionConflicts(Long userId, Long posType) {
		List<Sesion> tmp;
		boolean returnValue;
		EntityManager em = null;
		try {
			em = emf.createEntityManager();
			String queryString;
			if (posType == 0) {
				queryString = "SELECT s FROM Sesion s WHERE s.idUsuario.id=:id AND s.fechaCierre IS NULL ";
			} else {
				queryString = "SELECT s FROM Sesion s WHERE s.idUsuario.id=:id AND s.fechaCierre IS NULL and s.idCaja.tipoCaja<>:tipo";
			}
			System.out.println("---------------------------------" + queryString);
			Query query = em.createQuery(queryString);
			query.setParameter("id", userId);
			if (posType == 1) {
				query.setParameter("tipo", posType);
			}
			query.setMaxResults(1);
			tmp = query.getResultList();
			returnValue = !tmp.isEmpty();
		} catch (NoResultException ex) {
			returnValue = false;
		} catch (Exception ex) {
			returnValue = false;
		} finally {
			if (em != null) {
				em.close();
			}
		}
		return returnValue;
	}

	/**
	 * Method findLastSession.
	 * 
	 * @param caja
	 *            long
	 * @return long
	 */
	public long findLastSession(long caja) {
		long returnValue;
		EntityManager em = null;
		try {
			em = emf.createEntityManager();
			String queryString = "SELECT MAX(t.id) FROM " + entityName + " t WHERE t.idCaja.id = :id";
			Query query = em.createQuery(queryString);
			query.setParameter("id", caja);
			query.setMaxResults(1);
			Object singleResult = query.getSingleResult();
			if (singleResult == null) {
				returnValue = 0L;
			} else {
				returnValue = (Long) singleResult;
			}
		} catch (NoResultException ex) {
			ex.printStackTrace();
			returnValue = 0;
		} catch (Exception ex) {
			ex.printStackTrace();
			returnValue = 0;
		} finally {
			if (em != null) {
				em.close();
			}
		}
		return returnValue;
	}

	/**
	 * Method edit.
	 * 
	 * @param session
	 *            Session
	 * @throws JpaException
	 */
	public void edit(Session session) throws JpaException {
		Sesion sesion = toJpa(session);
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
			throw new JpaException(e);
		} finally {
			if (em.isOpen()) {
				em.clear();
				em.close();
			}
		}
	}
}
