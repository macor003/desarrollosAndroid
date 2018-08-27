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
public class CategorialineaincluyeJpaController implements Serializable {

	/**
	 * Constructor for CategorialineaincluyeJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public CategorialineaincluyeJpaController(EntityManagerFactory emf) {
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
	 * @param categorialineaincluye
	 *            Categorialineaincluye
	 * @throws PreexistingEntityException
	 * @throws Exception
	 */
	public void create(Categorialineaincluye categorialineaincluye)
			throws PreexistingEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Tipodocumento idTipodocumento = categorialineaincluye
					.getIdTipodocumento();
			if (idTipodocumento != null) {
				idTipodocumento = em.getReference(idTipodocumento.getClass(),
						idTipodocumento.getId());
				categorialineaincluye.setIdTipodocumento(idTipodocumento);
			}
			Giroactividadeconomica idGiroactividadeconomica = categorialineaincluye
					.getIdGiroactividadeconomica();
			if (idGiroactividadeconomica != null) {
				idGiroactividadeconomica = em.getReference(
						idGiroactividadeconomica.getClass(),
						idGiroactividadeconomica.getId());
				categorialineaincluye
						.setIdGiroactividadeconomica(idGiroactividadeconomica);
			}
			Linea idLinea = categorialineaincluye.getIdLinea();
			if (idLinea != null) {
				idLinea = em.getReference(idLinea.getClass(), idLinea.getId());
				categorialineaincluye.setIdLinea(idLinea);
			}
			em.persist(categorialineaincluye);
			if (idTipodocumento != null) {
				idTipodocumento.getCategorialineaincluyeList().add(
						categorialineaincluye);
				idTipodocumento = em.merge(idTipodocumento);
			}
			if (idGiroactividadeconomica != null) {
				idGiroactividadeconomica.getCategorialineaincluyeList().add(
						categorialineaincluye);
				idGiroactividadeconomica = em.merge(idGiroactividadeconomica);
			}
			if (idLinea != null) {
				idLinea.getCategorialineaincluyeList().add(
						categorialineaincluye);
				idLinea = em.merge(idLinea);
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findCategorialineaincluye(categorialineaincluye.getId()) != null) {
				throw new PreexistingEntityException("Categorialineaincluye "
						+ categorialineaincluye + " already exists.", ex);
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
	 * @param categorialineaincluye
	 *            Categorialineaincluye
	 * @throws NonexistentEntityException
	 * @throws Exception
	 */
	public void edit(Categorialineaincluye categorialineaincluye)
			throws NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Categorialineaincluye persistentCategorialineaincluye = em.find(
					Categorialineaincluye.class, categorialineaincluye.getId());
			Tipodocumento idTipodocumentoOld = persistentCategorialineaincluye
					.getIdTipodocumento();
			Tipodocumento idTipodocumentoNew = categorialineaincluye
					.getIdTipodocumento();
			Giroactividadeconomica idGiroactividadeconomicaOld = persistentCategorialineaincluye
					.getIdGiroactividadeconomica();
			Giroactividadeconomica idGiroactividadeconomicaNew = categorialineaincluye
					.getIdGiroactividadeconomica();
			Linea idLineaOld = persistentCategorialineaincluye.getIdLinea();
			Linea idLineaNew = categorialineaincluye.getIdLinea();
			if (idTipodocumentoNew != null) {
				idTipodocumentoNew = em.getReference(
						idTipodocumentoNew.getClass(),
						idTipodocumentoNew.getId());
				categorialineaincluye.setIdTipodocumento(idTipodocumentoNew);
			}
			if (idGiroactividadeconomicaNew != null) {
				idGiroactividadeconomicaNew = em.getReference(
						idGiroactividadeconomicaNew.getClass(),
						idGiroactividadeconomicaNew.getId());
				categorialineaincluye
						.setIdGiroactividadeconomica(idGiroactividadeconomicaNew);
			}
			if (idLineaNew != null) {
				idLineaNew = em.getReference(idLineaNew.getClass(),
						idLineaNew.getId());
				categorialineaincluye.setIdLinea(idLineaNew);
			}
			categorialineaincluye = em.merge(categorialineaincluye);
			if (idTipodocumentoOld != null
					&& !idTipodocumentoOld.equals(idTipodocumentoNew)) {
				idTipodocumentoOld.getCategorialineaincluyeList().remove(
						categorialineaincluye);
				idTipodocumentoOld = em.merge(idTipodocumentoOld);
			}
			if (idTipodocumentoNew != null
					&& !idTipodocumentoNew.equals(idTipodocumentoOld)) {
				idTipodocumentoNew.getCategorialineaincluyeList().add(
						categorialineaincluye);
				idTipodocumentoNew = em.merge(idTipodocumentoNew);
			}
			if (idGiroactividadeconomicaOld != null
					&& !idGiroactividadeconomicaOld
							.equals(idGiroactividadeconomicaNew)) {
				idGiroactividadeconomicaOld.getCategorialineaincluyeList()
						.remove(categorialineaincluye);
				idGiroactividadeconomicaOld = em
						.merge(idGiroactividadeconomicaOld);
			}
			if (idGiroactividadeconomicaNew != null
					&& !idGiroactividadeconomicaNew
							.equals(idGiroactividadeconomicaOld)) {
				idGiroactividadeconomicaNew.getCategorialineaincluyeList().add(
						categorialineaincluye);
				idGiroactividadeconomicaNew = em
						.merge(idGiroactividadeconomicaNew);
			}
			if (idLineaOld != null && !idLineaOld.equals(idLineaNew)) {
				idLineaOld.getCategorialineaincluyeList().remove(
						categorialineaincluye);
				idLineaOld = em.merge(idLineaOld);
			}
			if (idLineaNew != null && !idLineaNew.equals(idLineaOld)) {
				idLineaNew.getCategorialineaincluyeList().add(
						categorialineaincluye);
				idLineaNew = em.merge(idLineaNew);
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = categorialineaincluye.getId();
				if (findCategorialineaincluye(id) == null) {
					throw new NonexistentEntityException(
							"The categorialineaincluye with id " + id
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
			Categorialineaincluye categorialineaincluye;
			try {
				categorialineaincluye = em.getReference(
						Categorialineaincluye.class, id);
				categorialineaincluye.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException(
						"The categorialineaincluye with id " + id
								+ " no longer exists.", enfe);
			}
			Tipodocumento idTipodocumento = categorialineaincluye
					.getIdTipodocumento();
			if (idTipodocumento != null) {
				idTipodocumento.getCategorialineaincluyeList().remove(
						categorialineaincluye);
				idTipodocumento = em.merge(idTipodocumento);
			}
			Giroactividadeconomica idGiroactividadeconomica = categorialineaincluye
					.getIdGiroactividadeconomica();
			if (idGiroactividadeconomica != null) {
				idGiroactividadeconomica.getCategorialineaincluyeList().remove(
						categorialineaincluye);
				idGiroactividadeconomica = em.merge(idGiroactividadeconomica);
			}
			Linea idLinea = categorialineaincluye.getIdLinea();
			if (idLinea != null) {
				idLinea.getCategorialineaincluyeList().remove(
						categorialineaincluye);
				idLinea = em.merge(idLinea);
			}
			em.remove(categorialineaincluye);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method findCategorialineaincluyeEntities.
	 * 
	 * @return List<Categorialineaincluye>
	 */
	public List<Categorialineaincluye> findCategorialineaincluyeEntities() {
		return findCategorialineaincluyeEntities(true, -1, -1);
	}

	/**
	 * Method findCategorialineaincluyeEntities.
	 * 
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Categorialineaincluye>
	 */
	public List<Categorialineaincluye> findCategorialineaincluyeEntities(
			int maxResults, int firstResult) {
		return findCategorialineaincluyeEntities(false, maxResults, firstResult);
	}

	/**
	 * Method findCategorialineaincluyeEntities.
	 * 
	 * @param all
	 *            boolean
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Categorialineaincluye>
	 */
	private List<Categorialineaincluye> findCategorialineaincluyeEntities(
			boolean all, int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Categorialineaincluye.class));
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
	 * Method findCategorialineaincluye.
	 * 
	 * @param id
	 *            Long
	 * @return Categorialineaincluye
	 */
	public Categorialineaincluye findCategorialineaincluye(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Categorialineaincluye.class, id);
		} finally {
			em.close();
		}
	}

	/**
	 * Method getCategorialineaincluyeCount.
	 * 
	 * @return int
	 */
	public int getCategorialineaincluyeCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Categorialineaincluye> rt = cq
					.from(Categorialineaincluye.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}
