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
public class TipoclientetipodocumentoJpaController implements Serializable {

	/**
	 * Constructor for TipoclientetipodocumentoJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public TipoclientetipodocumentoJpaController(EntityManagerFactory emf) {
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
	 * @param tipoclientetipodocumento
	 *            Tipoclientetipodocumento
	 * @throws PreexistingEntityException
	 * @throws Exception
	 */
	public void create(Tipoclientetipodocumento tipoclientetipodocumento)
			throws PreexistingEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Tipocliente idTipocliente = tipoclientetipodocumento
					.getIdTipocliente();
			if (idTipocliente != null) {
				idTipocliente = em.getReference(idTipocliente.getClass(),
						idTipocliente.getId());
				tipoclientetipodocumento.setIdTipocliente(idTipocliente);
			}
			Tipodocumento idTipodocumento = tipoclientetipodocumento
					.getIdTipodocumento();
			if (idTipodocumento != null) {
				idTipodocumento = em.getReference(idTipodocumento.getClass(),
						idTipodocumento.getId());
				tipoclientetipodocumento.setIdTipodocumento(idTipodocumento);
			}
			em.persist(tipoclientetipodocumento);
			if (idTipocliente != null) {
				idTipocliente.getTipoclientetipodocumentoList().add(
						tipoclientetipodocumento);
				idTipocliente = em.merge(idTipocliente);
			}
			if (idTipodocumento != null) {
				idTipodocumento.getTipoclientetipodocumentoList().add(
						tipoclientetipodocumento);
				idTipodocumento = em.merge(idTipodocumento);
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findTipoclientetipodocumento(tipoclientetipodocumento.getId()) != null) {
				throw new PreexistingEntityException(
						"Tipoclientetipodocumento " + tipoclientetipodocumento
								+ " already exists.", ex);
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
	 * @param tipoclientetipodocumento
	 *            Tipoclientetipodocumento
	 * @throws NonexistentEntityException
	 * @throws Exception
	 */
	public void edit(Tipoclientetipodocumento tipoclientetipodocumento)
			throws NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Tipoclientetipodocumento persistentTipoclientetipodocumento = em
					.find(Tipoclientetipodocumento.class,
							tipoclientetipodocumento.getId());
			Tipocliente idTipoclienteOld = persistentTipoclientetipodocumento
					.getIdTipocliente();
			Tipocliente idTipoclienteNew = tipoclientetipodocumento
					.getIdTipocliente();
			Tipodocumento idTipodocumentoOld = persistentTipoclientetipodocumento
					.getIdTipodocumento();
			Tipodocumento idTipodocumentoNew = tipoclientetipodocumento
					.getIdTipodocumento();
			if (idTipoclienteNew != null) {
				idTipoclienteNew = em.getReference(idTipoclienteNew.getClass(),
						idTipoclienteNew.getId());
				tipoclientetipodocumento.setIdTipocliente(idTipoclienteNew);
			}
			if (idTipodocumentoNew != null) {
				idTipodocumentoNew = em.getReference(
						idTipodocumentoNew.getClass(),
						idTipodocumentoNew.getId());
				tipoclientetipodocumento.setIdTipodocumento(idTipodocumentoNew);
			}
			tipoclientetipodocumento = em.merge(tipoclientetipodocumento);
			if (idTipoclienteOld != null
					&& !idTipoclienteOld.equals(idTipoclienteNew)) {
				idTipoclienteOld.getTipoclientetipodocumentoList().remove(
						tipoclientetipodocumento);
				idTipoclienteOld = em.merge(idTipoclienteOld);
			}
			if (idTipoclienteNew != null
					&& !idTipoclienteNew.equals(idTipoclienteOld)) {
				idTipoclienteNew.getTipoclientetipodocumentoList().add(
						tipoclientetipodocumento);
				idTipoclienteNew = em.merge(idTipoclienteNew);
			}
			if (idTipodocumentoOld != null
					&& !idTipodocumentoOld.equals(idTipodocumentoNew)) {
				idTipodocumentoOld.getTipoclientetipodocumentoList().remove(
						tipoclientetipodocumento);
				idTipodocumentoOld = em.merge(idTipodocumentoOld);
			}
			if (idTipodocumentoNew != null
					&& !idTipodocumentoNew.equals(idTipodocumentoOld)) {
				idTipodocumentoNew.getTipoclientetipodocumentoList().add(
						tipoclientetipodocumento);
				idTipodocumentoNew = em.merge(idTipodocumentoNew);
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = tipoclientetipodocumento.getId();
				if (findTipoclientetipodocumento(id) == null) {
					throw new NonexistentEntityException(
							"The tipoclientetipodocumento with id " + id
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
			Tipoclientetipodocumento tipoclientetipodocumento;
			try {
				tipoclientetipodocumento = em.getReference(
						Tipoclientetipodocumento.class, id);
				tipoclientetipodocumento.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException(
						"The tipoclientetipodocumento with id " + id
								+ " no longer exists.", enfe);
			}
			Tipocliente idTipocliente = tipoclientetipodocumento
					.getIdTipocliente();
			if (idTipocliente != null) {
				idTipocliente.getTipoclientetipodocumentoList().remove(
						tipoclientetipodocumento);
				idTipocliente = em.merge(idTipocliente);
			}
			Tipodocumento idTipodocumento = tipoclientetipodocumento
					.getIdTipodocumento();
			if (idTipodocumento != null) {
				idTipodocumento.getTipoclientetipodocumentoList().remove(
						tipoclientetipodocumento);
				idTipodocumento = em.merge(idTipodocumento);
			}
			em.remove(tipoclientetipodocumento);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method findTipoclientetipodocumentoEntities.
	 * 
	 * @return List<Tipoclientetipodocumento>
	 */
	public List<Tipoclientetipodocumento> findTipoclientetipodocumentoEntities() {
		return findTipoclientetipodocumentoEntities(true, -1, -1);
	}

	/**
	 * Method findTipoclientetipodocumentoEntities.
	 * 
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Tipoclientetipodocumento>
	 */
	public List<Tipoclientetipodocumento> findTipoclientetipodocumentoEntities(
			int maxResults, int firstResult) {
		return findTipoclientetipodocumentoEntities(false, maxResults,
				firstResult);
	}

	/**
	 * Method findTipoclientetipodocumentoEntities.
	 * 
	 * @param all
	 *            boolean
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Tipoclientetipodocumento>
	 */
	private List<Tipoclientetipodocumento> findTipoclientetipodocumentoEntities(
			boolean all, int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Tipoclientetipodocumento.class));
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
	 * Method findTipoclientetipodocumento.
	 * 
	 * @param id
	 *            Long
	 * @return Tipoclientetipodocumento
	 */
	public Tipoclientetipodocumento findTipoclientetipodocumento(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Tipoclientetipodocumento.class, id);
		} finally {
			em.close();
		}
	}

	/**
	 * Method getTipoclientetipodocumentoCount.
	 * 
	 * @return int
	 */
	public int getTipoclientetipodocumentoCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Tipoclientetipodocumento> rt = cq
					.from(Tipoclientetipodocumento.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}
