/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crjpa400;

import java.io.Serializable;
import java.util.ArrayList;
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
public class MovimientoacreenciaJpaController implements Serializable {

	/**
	 * Constructor for MovimientoacreenciaJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public MovimientoacreenciaJpaController(EntityManagerFactory emf) {
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
	 * @param movimientoacreencia
	 *            Movimientoacreencia
	 * @throws PreexistingEntityException
	 * @throws Exception
	 */
	public void create(Movimientoacreencia movimientoacreencia)
			throws PreexistingEntityException, Exception {
		if (movimientoacreencia.getFormadepagomovacreenciaList() == null) {
			movimientoacreencia
					.setFormadepagomovacreenciaList(new ArrayList<Formadepagomovacreencia>());
		}
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Tipoacreenciaoperacion idTipoacreenciaoperacion = movimientoacreencia
					.getIdTipoacreenciaoperacion();
			if (idTipoacreenciaoperacion != null) {
				idTipoacreenciaoperacion = em.getReference(
						idTipoacreenciaoperacion.getClass(),
						idTipoacreenciaoperacion.getId());
				movimientoacreencia
						.setIdTipoacreenciaoperacion(idTipoacreenciaoperacion);
			}
			Acreencia idAcreencia = movimientoacreencia.getIdAcreencia();
			if (idAcreencia != null) {
				idAcreencia = em.getReference(idAcreencia.getClass(),
						idAcreencia.getId());
				movimientoacreencia.setIdAcreencia(idAcreencia);
			}
			List<Formadepagomovacreencia> attachedFormadepagomovacreenciaList = new ArrayList<Formadepagomovacreencia>();
			for (Formadepagomovacreencia formadepagomovacreenciaListFormadepagomovacreenciaToAttach : movimientoacreencia
					.getFormadepagomovacreenciaList()) {
				formadepagomovacreenciaListFormadepagomovacreenciaToAttach = em
						.getReference(
								formadepagomovacreenciaListFormadepagomovacreenciaToAttach
										.getClass(),
								formadepagomovacreenciaListFormadepagomovacreenciaToAttach
										.getId());
				attachedFormadepagomovacreenciaList
						.add(formadepagomovacreenciaListFormadepagomovacreenciaToAttach);
			}
			movimientoacreencia
					.setFormadepagomovacreenciaList(attachedFormadepagomovacreenciaList);
			em.persist(movimientoacreencia);
			if (idTipoacreenciaoperacion != null) {
				idTipoacreenciaoperacion.getMovimientoacreenciaList().add(
						movimientoacreencia);
				idTipoacreenciaoperacion = em.merge(idTipoacreenciaoperacion);
			}
			if (idAcreencia != null) {
				idAcreencia.getMovimientoacreenciaList().add(
						movimientoacreencia);
				idAcreencia = em.merge(idAcreencia);
			}
			for (Formadepagomovacreencia formadepagomovacreenciaListFormadepagomovacreencia : movimientoacreencia
					.getFormadepagomovacreenciaList()) {
				Movimientoacreencia oldIdMovimientoacreenciaOfFormadepagomovacreenciaListFormadepagomovacreencia = formadepagomovacreenciaListFormadepagomovacreencia
						.getIdMovimientoacreencia();
				formadepagomovacreenciaListFormadepagomovacreencia
						.setIdMovimientoacreencia(movimientoacreencia);
				formadepagomovacreenciaListFormadepagomovacreencia = em
						.merge(formadepagomovacreenciaListFormadepagomovacreencia);
				if (oldIdMovimientoacreenciaOfFormadepagomovacreenciaListFormadepagomovacreencia != null) {
					oldIdMovimientoacreenciaOfFormadepagomovacreenciaListFormadepagomovacreencia
							.getFormadepagomovacreenciaList()
							.remove(formadepagomovacreenciaListFormadepagomovacreencia);
					oldIdMovimientoacreenciaOfFormadepagomovacreenciaListFormadepagomovacreencia = em
							.merge(oldIdMovimientoacreenciaOfFormadepagomovacreenciaListFormadepagomovacreencia);
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findMovimientoacreencia(movimientoacreencia.getId()) != null) {
				throw new PreexistingEntityException("Movimientoacreencia "
						+ movimientoacreencia + " already exists.", ex);
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
	 * @param movimientoacreencia
	 *            Movimientoacreencia
	 * @throws NonexistentEntityException
	 * @throws Exception
	 */
	public void edit(Movimientoacreencia movimientoacreencia)
			throws NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Movimientoacreencia persistentMovimientoacreencia = em.find(
					Movimientoacreencia.class, movimientoacreencia.getId());
			Tipoacreenciaoperacion idTipoacreenciaoperacionOld = persistentMovimientoacreencia
					.getIdTipoacreenciaoperacion();
			Tipoacreenciaoperacion idTipoacreenciaoperacionNew = movimientoacreencia
					.getIdTipoacreenciaoperacion();
			Acreencia idAcreenciaOld = persistentMovimientoacreencia
					.getIdAcreencia();
			Acreencia idAcreenciaNew = movimientoacreencia.getIdAcreencia();
			List<Formadepagomovacreencia> formadepagomovacreenciaListOld = persistentMovimientoacreencia
					.getFormadepagomovacreenciaList();
			List<Formadepagomovacreencia> formadepagomovacreenciaListNew = movimientoacreencia
					.getFormadepagomovacreenciaList();
			if (idTipoacreenciaoperacionNew != null) {
				idTipoacreenciaoperacionNew = em.getReference(
						idTipoacreenciaoperacionNew.getClass(),
						idTipoacreenciaoperacionNew.getId());
				movimientoacreencia
						.setIdTipoacreenciaoperacion(idTipoacreenciaoperacionNew);
			}
			if (idAcreenciaNew != null) {
				idAcreenciaNew = em.getReference(idAcreenciaNew.getClass(),
						idAcreenciaNew.getId());
				movimientoacreencia.setIdAcreencia(idAcreenciaNew);
			}
			List<Formadepagomovacreencia> attachedFormadepagomovacreenciaListNew = new ArrayList<Formadepagomovacreencia>();
			for (Formadepagomovacreencia formadepagomovacreenciaListNewFormadepagomovacreenciaToAttach : formadepagomovacreenciaListNew) {
				formadepagomovacreenciaListNewFormadepagomovacreenciaToAttach = em
						.getReference(
								formadepagomovacreenciaListNewFormadepagomovacreenciaToAttach
										.getClass(),
								formadepagomovacreenciaListNewFormadepagomovacreenciaToAttach
										.getId());
				attachedFormadepagomovacreenciaListNew
						.add(formadepagomovacreenciaListNewFormadepagomovacreenciaToAttach);
			}
			formadepagomovacreenciaListNew = attachedFormadepagomovacreenciaListNew;
			movimientoacreencia
					.setFormadepagomovacreenciaList(formadepagomovacreenciaListNew);
			movimientoacreencia = em.merge(movimientoacreencia);
			if (idTipoacreenciaoperacionOld != null
					&& !idTipoacreenciaoperacionOld
							.equals(idTipoacreenciaoperacionNew)) {
				idTipoacreenciaoperacionOld.getMovimientoacreenciaList()
						.remove(movimientoacreencia);
				idTipoacreenciaoperacionOld = em
						.merge(idTipoacreenciaoperacionOld);
			}
			if (idTipoacreenciaoperacionNew != null
					&& !idTipoacreenciaoperacionNew
							.equals(idTipoacreenciaoperacionOld)) {
				idTipoacreenciaoperacionNew.getMovimientoacreenciaList().add(
						movimientoacreencia);
				idTipoacreenciaoperacionNew = em
						.merge(idTipoacreenciaoperacionNew);
			}
			if (idAcreenciaOld != null
					&& !idAcreenciaOld.equals(idAcreenciaNew)) {
				idAcreenciaOld.getMovimientoacreenciaList().remove(
						movimientoacreencia);
				idAcreenciaOld = em.merge(idAcreenciaOld);
			}
			if (idAcreenciaNew != null
					&& !idAcreenciaNew.equals(idAcreenciaOld)) {
				idAcreenciaNew.getMovimientoacreenciaList().add(
						movimientoacreencia);
				idAcreenciaNew = em.merge(idAcreenciaNew);
			}
			for (Formadepagomovacreencia formadepagomovacreenciaListOldFormadepagomovacreencia : formadepagomovacreenciaListOld) {
				if (!formadepagomovacreenciaListNew
						.contains(formadepagomovacreenciaListOldFormadepagomovacreencia)) {
					formadepagomovacreenciaListOldFormadepagomovacreencia
							.setIdMovimientoacreencia(null);
					formadepagomovacreenciaListOldFormadepagomovacreencia = em
							.merge(formadepagomovacreenciaListOldFormadepagomovacreencia);
				}
			}
			for (Formadepagomovacreencia formadepagomovacreenciaListNewFormadepagomovacreencia : formadepagomovacreenciaListNew) {
				if (!formadepagomovacreenciaListOld
						.contains(formadepagomovacreenciaListNewFormadepagomovacreencia)) {
					Movimientoacreencia oldIdMovimientoacreenciaOfFormadepagomovacreenciaListNewFormadepagomovacreencia = formadepagomovacreenciaListNewFormadepagomovacreencia
							.getIdMovimientoacreencia();
					formadepagomovacreenciaListNewFormadepagomovacreencia
							.setIdMovimientoacreencia(movimientoacreencia);
					formadepagomovacreenciaListNewFormadepagomovacreencia = em
							.merge(formadepagomovacreenciaListNewFormadepagomovacreencia);
					if (oldIdMovimientoacreenciaOfFormadepagomovacreenciaListNewFormadepagomovacreencia != null
							&& !oldIdMovimientoacreenciaOfFormadepagomovacreenciaListNewFormadepagomovacreencia
									.equals(movimientoacreencia)) {
						oldIdMovimientoacreenciaOfFormadepagomovacreenciaListNewFormadepagomovacreencia
								.getFormadepagomovacreenciaList()
								.remove(formadepagomovacreenciaListNewFormadepagomovacreencia);
						oldIdMovimientoacreenciaOfFormadepagomovacreenciaListNewFormadepagomovacreencia = em
								.merge(oldIdMovimientoacreenciaOfFormadepagomovacreenciaListNewFormadepagomovacreencia);
					}
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = movimientoacreencia.getId();
				if (findMovimientoacreencia(id) == null) {
					throw new NonexistentEntityException(
							"The movimientoacreencia with id " + id
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
	 *            Long
	 * @throws NonexistentEntityException
	 */
	public void destroy(Long id) throws NonexistentEntityException {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Movimientoacreencia movimientoacreencia;
			try {
				movimientoacreencia = em.getReference(
						Movimientoacreencia.class, id);
				movimientoacreencia.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException(
						"The movimientoacreencia with id " + id
								+ " no longer exists.", enfe);
			}
			Tipoacreenciaoperacion idTipoacreenciaoperacion = movimientoacreencia
					.getIdTipoacreenciaoperacion();
			if (idTipoacreenciaoperacion != null) {
				idTipoacreenciaoperacion.getMovimientoacreenciaList().remove(
						movimientoacreencia);
				idTipoacreenciaoperacion = em.merge(idTipoacreenciaoperacion);
			}
			Acreencia idAcreencia = movimientoacreencia.getIdAcreencia();
			if (idAcreencia != null) {
				idAcreencia.getMovimientoacreenciaList().remove(
						movimientoacreencia);
				idAcreencia = em.merge(idAcreencia);
			}
			List<Formadepagomovacreencia> formadepagomovacreenciaList = movimientoacreencia
					.getFormadepagomovacreenciaList();
			for (Formadepagomovacreencia formadepagomovacreenciaListFormadepagomovacreencia : formadepagomovacreenciaList) {
				formadepagomovacreenciaListFormadepagomovacreencia
						.setIdMovimientoacreencia(null);
				formadepagomovacreenciaListFormadepagomovacreencia = em
						.merge(formadepagomovacreenciaListFormadepagomovacreencia);
			}
			em.remove(movimientoacreencia);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method findMovimientoacreenciaEntities.
	 * 
	 * @return List<Movimientoacreencia>
	 */
	public List<Movimientoacreencia> findMovimientoacreenciaEntities() {
		return findMovimientoacreenciaEntities(true, -1, -1);
	}

	/**
	 * Method findMovimientoacreenciaEntities.
	 * 
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Movimientoacreencia>
	 */
	public List<Movimientoacreencia> findMovimientoacreenciaEntities(
			int maxResults, int firstResult) {
		return findMovimientoacreenciaEntities(false, maxResults, firstResult);
	}

	/**
	 * Method findMovimientoacreenciaEntities.
	 * 
	 * @param all
	 *            boolean
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Movimientoacreencia>
	 */
	private List<Movimientoacreencia> findMovimientoacreenciaEntities(
			boolean all, int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Movimientoacreencia.class));
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
	 * Method findMovimientoacreencia.
	 * 
	 * @param id
	 *            Long
	 * @return Movimientoacreencia
	 */
	public Movimientoacreencia findMovimientoacreencia(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Movimientoacreencia.class, id);
		} finally {
			em.close();
		}
	}

	/**
	 * Method getMovimientoacreenciaCount.
	 * 
	 * @return int
	 */
	public int getMovimientoacreenciaCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Movimientoacreencia> rt = cq.from(Movimientoacreencia.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}
