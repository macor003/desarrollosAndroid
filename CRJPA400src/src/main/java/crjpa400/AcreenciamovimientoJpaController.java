/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crjpa400;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import crjpa400.exceptions.NonexistentEntityException;
import crjpa400.exceptions.PreexistingEntityException;

/**
 * 
 * @author eve0014321
 * @version $Revision: 1.0 $
 */
public class AcreenciamovimientoJpaController implements Serializable {

	/**
	 * Constructor for AcreenciamovimientoJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public AcreenciamovimientoJpaController(EntityManagerFactory emf) {
		this.emf = emf;
	}

	/**
	 * Field emf.
	 */
	private EntityManagerFactory emf = null;

	/**
	 * Method getEntityManager.
	 * 
	 * @return EntityManager
	 */
	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	/**
	 * Method create.
	 * 
	 * @param acreenciamovimiento
	 *            Acreenciamovimiento
	 * @throws PreexistingEntityException
	 * @throws Exception
	 */
	public void create(Acreenciamovimiento acreenciamovimiento)
			throws PreexistingEntityException, Exception {
//		if (acreenciamovimiento.getAcreenciamovimientoPK() == null) {
//			acreenciamovimiento
//					.setAcreenciamovimientoPK(new AcreenciamovimientoPK());
//		}
		if (acreenciamovimiento.getIpaId() == null) {
			acreenciamovimiento.setIpaId(0l);
		}
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Moneda idMoneda = acreenciamovimiento.getIdMoneda();
			if (idMoneda != null) {
				idMoneda = em.getReference(idMoneda.getClass(),
						idMoneda.getId());
				acreenciamovimiento.setIdMoneda(idMoneda);
			}
			Operacionacreencia idOperacionacreencia = acreenciamovimiento
					.getIdOperacionacreencia();
			if (idOperacionacreencia != null) {
				idOperacionacreencia = em.getReference(
						idOperacionacreencia.getClass(),
						idOperacionacreencia.getId());
				acreenciamovimiento
						.setIdOperacionacreencia(idOperacionacreencia);
			}
			Tipoacreencia idTipoacreencia = acreenciamovimiento
					.getIdTipoacreencia();
			if (idTipoacreencia != null) {
				idTipoacreencia = em.getReference(idTipoacreencia.getClass(),
						idTipoacreencia.getId());
				acreenciamovimiento.setIdTipoacreencia(idTipoacreencia);
			}
			Formadepago idFormadepago = acreenciamovimiento.getIdFormadepago();
			if (idFormadepago != null) {
				idFormadepago = em.getReference(idFormadepago.getClass(),
						idFormadepago.getId());
				acreenciamovimiento.setIdFormadepago(idFormadepago);
			}
			em.persist(acreenciamovimiento);
			if (idMoneda != null) {
				idMoneda.getAcreenciamovimientoList().add(acreenciamovimiento);
				idMoneda = em.merge(idMoneda);
			}
			if (idOperacionacreencia != null) {
				idOperacionacreencia.getAcreenciamovimientoList().add(
						acreenciamovimiento);
				idOperacionacreencia = em.merge(idOperacionacreencia);
			}
			if (idTipoacreencia != null) {
				idTipoacreencia.getAcreenciamovimientoList().add(
						acreenciamovimiento);
				idTipoacreencia = em.merge(idTipoacreencia);
			}
			if (idFormadepago != null) {
				idFormadepago.getAcreenciamovimientoList().add(
						acreenciamovimiento);
				idFormadepago = em.merge(idFormadepago);
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findAcreenciamovimiento(acreenciamovimiento
					.getIpaId()) != null) {
				throw new PreexistingEntityException("Acreenciamovimiento "
						+ acreenciamovimiento + " already exists.", ex);
			}
			throw ex;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method edit.
	 * 
	 * @param acreenciamovimiento
	 *            Acreenciamovimiento
	 * @throws NonexistentEntityException
	 * @throws Exception
	 */
	public void edit(Acreenciamovimiento acreenciamovimiento)
			throws NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Acreenciamovimiento persistentAcreenciamovimiento = em.find(
					Acreenciamovimiento.class,
					acreenciamovimiento.getIpaId());
			Moneda idMonedaOld = persistentAcreenciamovimiento.getIdMoneda();
			Moneda idMonedaNew = acreenciamovimiento.getIdMoneda();
			Operacionacreencia idOperacionacreenciaOld = persistentAcreenciamovimiento
					.getIdOperacionacreencia();
			Operacionacreencia idOperacionacreenciaNew = acreenciamovimiento
					.getIdOperacionacreencia();
			Tipoacreencia idTipoacreenciaOld = persistentAcreenciamovimiento
					.getIdTipoacreencia();
			Tipoacreencia idTipoacreenciaNew = acreenciamovimiento
					.getIdTipoacreencia();
			Formadepago idFormadepagoOld = persistentAcreenciamovimiento
					.getIdFormadepago();
			Formadepago idFormadepagoNew = acreenciamovimiento
					.getIdFormadepago();
			if (idMonedaNew != null) {
				idMonedaNew = em.getReference(idMonedaNew.getClass(),
						idMonedaNew.getId());
				acreenciamovimiento.setIdMoneda(idMonedaNew);
			}
			if (idOperacionacreenciaNew != null) {
				idOperacionacreenciaNew = em.getReference(
						idOperacionacreenciaNew.getClass(),
						idOperacionacreenciaNew.getId());
				acreenciamovimiento
						.setIdOperacionacreencia(idOperacionacreenciaNew);
			}
			if (idTipoacreenciaNew != null) {
				idTipoacreenciaNew = em.getReference(
						idTipoacreenciaNew.getClass(),
						idTipoacreenciaNew.getId());
				acreenciamovimiento.setIdTipoacreencia(idTipoacreenciaNew);
			}
			if (idFormadepagoNew != null) {
				idFormadepagoNew = em.getReference(idFormadepagoNew.getClass(),
						idFormadepagoNew.getId());
				acreenciamovimiento.setIdFormadepago(idFormadepagoNew);
			}
			acreenciamovimiento = em.merge(acreenciamovimiento);
			if (idMonedaOld != null && !idMonedaOld.equals(idMonedaNew)) {
				idMonedaOld.getAcreenciamovimientoList().remove(
						acreenciamovimiento);
				idMonedaOld = em.merge(idMonedaOld);
			}
			if (idMonedaNew != null && !idMonedaNew.equals(idMonedaOld)) {
				idMonedaNew.getAcreenciamovimientoList().add(
						acreenciamovimiento);
				idMonedaNew = em.merge(idMonedaNew);
			}
			if (idOperacionacreenciaOld != null
					&& !idOperacionacreenciaOld.equals(idOperacionacreenciaNew)) {
				idOperacionacreenciaOld.getAcreenciamovimientoList().remove(
						acreenciamovimiento);
				idOperacionacreenciaOld = em.merge(idOperacionacreenciaOld);
			}
			if (idOperacionacreenciaNew != null
					&& !idOperacionacreenciaNew.equals(idOperacionacreenciaOld)) {
				idOperacionacreenciaNew.getAcreenciamovimientoList().add(
						acreenciamovimiento);
				idOperacionacreenciaNew = em.merge(idOperacionacreenciaNew);
			}
			if (idTipoacreenciaOld != null
					&& !idTipoacreenciaOld.equals(idTipoacreenciaNew)) {
				idTipoacreenciaOld.getAcreenciamovimientoList().remove(
						acreenciamovimiento);
				idTipoacreenciaOld = em.merge(idTipoacreenciaOld);
			}
			if (idTipoacreenciaNew != null
					&& !idTipoacreenciaNew.equals(idTipoacreenciaOld)) {
				idTipoacreenciaNew.getAcreenciamovimientoList().add(
						acreenciamovimiento);
				idTipoacreenciaNew = em.merge(idTipoacreenciaNew);
			}
			if (idFormadepagoOld != null
					&& !idFormadepagoOld.equals(idFormadepagoNew)) {
				idFormadepagoOld.getAcreenciamovimientoList().remove(
						acreenciamovimiento);
				idFormadepagoOld = em.merge(idFormadepagoOld);
			}
			if (idFormadepagoNew != null
					&& !idFormadepagoNew.equals(idFormadepagoOld)) {
				idFormadepagoNew.getAcreenciamovimientoList().add(
						acreenciamovimiento);
				idFormadepagoNew = em.merge(idFormadepagoNew);
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = acreenciamovimiento
						.getIpaId();
				if (findAcreenciamovimiento(id) == null) {
					throw new NonexistentEntityException(
							"The acreenciamovimiento with id " + id
									+ " no longer exists.");
				}
			}
			throw ex;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method destroy.
	 * 
	 * @param id
	 *            AcreenciamovimientoPK
	 * @throws NonexistentEntityException
	 */
	public void destroy(AcreenciamovimientoPK id)
			throws NonexistentEntityException {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Acreenciamovimiento acreenciamovimiento;
			try {
				acreenciamovimiento = em.getReference(
						Acreenciamovimiento.class, id);
				acreenciamovimiento.getIpaId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException(
						"The acreenciamovimiento with id " + id
								+ " no longer exists.", enfe);
			}
			Moneda idMoneda = acreenciamovimiento.getIdMoneda();
			if (idMoneda != null) {
				idMoneda.getAcreenciamovimientoList().remove(
						acreenciamovimiento);
				idMoneda = em.merge(idMoneda);
			}
			Operacionacreencia idOperacionacreencia = acreenciamovimiento
					.getIdOperacionacreencia();
			if (idOperacionacreencia != null) {
				idOperacionacreencia.getAcreenciamovimientoList().remove(
						acreenciamovimiento);
				idOperacionacreencia = em.merge(idOperacionacreencia);
			}
			Tipoacreencia idTipoacreencia = acreenciamovimiento
					.getIdTipoacreencia();
			if (idTipoacreencia != null) {
				idTipoacreencia.getAcreenciamovimientoList().remove(
						acreenciamovimiento);
				idTipoacreencia = em.merge(idTipoacreencia);
			}
			Formadepago idFormadepago = acreenciamovimiento.getIdFormadepago();
			if (idFormadepago != null) {
				idFormadepago.getAcreenciamovimientoList().remove(
						acreenciamovimiento);
				idFormadepago = em.merge(idFormadepago);
			}
			em.remove(acreenciamovimiento);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method findAcreenciamovimientoEntities.
	 * 
	 * @return List<Acreenciamovimiento>
	 */
	public List<Acreenciamovimiento> findAcreenciamovimientoEntities() {
		return findAcreenciamovimientoEntities(true, -1, -1);
	}

	/**
	 * Method findAcreenciamovimientoEntities.
	 * 
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Acreenciamovimiento>
	 */
	public List<Acreenciamovimiento> findAcreenciamovimientoEntities(
			int maxResults, int firstResult) {
		return findAcreenciamovimientoEntities(false, maxResults, firstResult);
	}

	/**
	 * Method findAcreenciamovimientoEntities.
	 * 
	 * @param all
	 *            boolean
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Acreenciamovimiento>
	 */
	private List<Acreenciamovimiento> findAcreenciamovimientoEntities(
			boolean all, int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Acreenciamovimiento.class));
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

	/**
	 * Method findAcreenciamovimiento.
	 * 
	 * @param id
	 *            AcreenciamovimientoPK
	 * @return Acreenciamovimiento
	 */
	public Acreenciamovimiento findAcreenciamovimiento(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Acreenciamovimiento.class, id);
		} finally {
			em.close();
		}
	}

	/**
	 * Method getAcreenciamovimientoCount.
	 * 
	 * @return int
	 */
	public int getAcreenciamovimientoCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Acreenciamovimiento> rt = cq.from(Acreenciamovimiento.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}
