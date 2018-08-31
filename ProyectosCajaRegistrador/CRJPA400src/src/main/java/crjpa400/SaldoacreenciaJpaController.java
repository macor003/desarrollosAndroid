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
public class SaldoacreenciaJpaController implements Serializable {

	/**
	 * Constructor for SaldoacreenciaJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public SaldoacreenciaJpaController(EntityManagerFactory emf) {
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
	 * @param saldoacreencia
	 *            Saldoacreencia
	 * @throws PreexistingEntityException
	 * @throws Exception
	 */
	public void create(Saldoacreencia saldoacreencia)
			throws PreexistingEntityException, Exception {
		if (saldoacreencia.getSaldoacreenciaPK() == null) {
			saldoacreencia.setSaldoacreenciaPK(new SaldoacreenciaPK());
		}
		saldoacreencia.getSaldoacreenciaPK().setIdAcreencia(
				saldoacreencia.getAcreencia().getId());
		saldoacreencia.getSaldoacreenciaPK().setIdTipoacreencia(
				saldoacreencia.getTipoacreencia().getId());
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Tipoacreencia tipoacreencia = saldoacreencia.getTipoacreencia();
			if (tipoacreencia != null) {
				tipoacreencia = em.getReference(tipoacreencia.getClass(),
						tipoacreencia.getId());
				saldoacreencia.setTipoacreencia(tipoacreencia);
			}
			Acreencia acreencia = saldoacreencia.getAcreencia();
			if (acreencia != null) {
				acreencia = em.getReference(acreencia.getClass(),
						acreencia.getId());
				saldoacreencia.setAcreencia(acreencia);
			}
			em.persist(saldoacreencia);
			if (tipoacreencia != null) {
				tipoacreencia.getSaldoacreenciaList().add(saldoacreencia);
				tipoacreencia = em.merge(tipoacreencia);
			}
			if (acreencia != null) {
				acreencia.getSaldoacreenciaList().add(saldoacreencia);
				acreencia = em.merge(acreencia);
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findSaldoacreencia(saldoacreencia.getSaldoacreenciaPK()) != null) {
				throw new PreexistingEntityException("Saldoacreencia "
						+ saldoacreencia + " already exists.", ex);
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
	 * @param saldoacreencia
	 *            Saldoacreencia
	 * @throws NonexistentEntityException
	 * @throws Exception
	 */
	public void edit(Saldoacreencia saldoacreencia)
			throws NonexistentEntityException, Exception {
		saldoacreencia.getSaldoacreenciaPK().setIdAcreencia(
				saldoacreencia.getAcreencia().getId());
		saldoacreencia.getSaldoacreenciaPK().setIdTipoacreencia(
				saldoacreencia.getTipoacreencia().getId());
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Saldoacreencia persistentSaldoacreencia = em.find(
					Saldoacreencia.class, saldoacreencia.getSaldoacreenciaPK());
			Tipoacreencia tipoacreenciaOld = persistentSaldoacreencia
					.getTipoacreencia();
			Tipoacreencia tipoacreenciaNew = saldoacreencia.getTipoacreencia();
			Acreencia acreenciaOld = persistentSaldoacreencia.getAcreencia();
			Acreencia acreenciaNew = saldoacreencia.getAcreencia();
			if (tipoacreenciaNew != null) {
				tipoacreenciaNew = em.getReference(tipoacreenciaNew.getClass(),
						tipoacreenciaNew.getId());
				saldoacreencia.setTipoacreencia(tipoacreenciaNew);
			}
			if (acreenciaNew != null) {
				acreenciaNew = em.getReference(acreenciaNew.getClass(),
						acreenciaNew.getId());
				saldoacreencia.setAcreencia(acreenciaNew);
			}
			saldoacreencia = em.merge(saldoacreencia);
			if (tipoacreenciaOld != null
					&& !tipoacreenciaOld.equals(tipoacreenciaNew)) {
				tipoacreenciaOld.getSaldoacreenciaList().remove(saldoacreencia);
				tipoacreenciaOld = em.merge(tipoacreenciaOld);
			}
			if (tipoacreenciaNew != null
					&& !tipoacreenciaNew.equals(tipoacreenciaOld)) {
				tipoacreenciaNew.getSaldoacreenciaList().add(saldoacreencia);
				tipoacreenciaNew = em.merge(tipoacreenciaNew);
			}
			if (acreenciaOld != null && !acreenciaOld.equals(acreenciaNew)) {
				acreenciaOld.getSaldoacreenciaList().remove(saldoacreencia);
				acreenciaOld = em.merge(acreenciaOld);
			}
			if (acreenciaNew != null && !acreenciaNew.equals(acreenciaOld)) {
				acreenciaNew.getSaldoacreenciaList().add(saldoacreencia);
				acreenciaNew = em.merge(acreenciaNew);
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				SaldoacreenciaPK id = saldoacreencia.getSaldoacreenciaPK();
				if (findSaldoacreencia(id) == null) {
					throw new NonexistentEntityException(
							"The saldoacreencia with id " + id
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
	 *            SaldoacreenciaPK
	 * @throws NonexistentEntityException
	 */
	public void destroy(SaldoacreenciaPK id) throws NonexistentEntityException {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Saldoacreencia saldoacreencia;
			try {
				saldoacreencia = em.getReference(Saldoacreencia.class, id);
				saldoacreencia.getSaldoacreenciaPK();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException(
						"The saldoacreencia with id " + id
								+ " no longer exists.", enfe);
			}
			Tipoacreencia tipoacreencia = saldoacreencia.getTipoacreencia();
			if (tipoacreencia != null) {
				tipoacreencia.getSaldoacreenciaList().remove(saldoacreencia);
				tipoacreencia = em.merge(tipoacreencia);
			}
			Acreencia acreencia = saldoacreencia.getAcreencia();
			if (acreencia != null) {
				acreencia.getSaldoacreenciaList().remove(saldoacreencia);
				acreencia = em.merge(acreencia);
			}
			em.remove(saldoacreencia);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method findSaldoacreenciaEntities.
	 * 
	 * @return List<Saldoacreencia>
	 */
	public List<Saldoacreencia> findSaldoacreenciaEntities() {
		return findSaldoacreenciaEntities(true, -1, -1);
	}

	/**
	 * Method findSaldoacreenciaEntities.
	 * 
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Saldoacreencia>
	 */
	public List<Saldoacreencia> findSaldoacreenciaEntities(int maxResults,
			int firstResult) {
		return findSaldoacreenciaEntities(false, maxResults, firstResult);
	}

	/**
	 * Method findSaldoacreenciaEntities.
	 * 
	 * @param all
	 *            boolean
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Saldoacreencia>
	 */
	private List<Saldoacreencia> findSaldoacreenciaEntities(boolean all,
			int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Saldoacreencia.class));
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
	 * Method findSaldoacreencia.
	 * 
	 * @param id
	 *            SaldoacreenciaPK
	 * @return Saldoacreencia
	 */
	public Saldoacreencia findSaldoacreencia(SaldoacreenciaPK id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Saldoacreencia.class, id);
		} finally {
			em.close();
		}
	}

	/**
	 * Method getSaldoacreenciaCount.
	 * 
	 * @return int
	 */
	public int getSaldoacreenciaCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Saldoacreencia> rt = cq.from(Saldoacreencia.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}
