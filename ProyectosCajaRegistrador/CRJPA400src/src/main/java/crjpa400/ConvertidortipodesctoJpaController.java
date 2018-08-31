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
public class ConvertidortipodesctoJpaController implements Serializable {

	/**
	 * Constructor for ConvertidortipodesctoJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public ConvertidortipodesctoJpaController(EntityManagerFactory emf) {
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
	 * @param convertidortipodescto
	 *            Convertidortipodescto
	 * @throws PreexistingEntityException
	 * @throws Exception
	 */
	public void create(Convertidortipodescto convertidortipodescto)
			throws PreexistingEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Tipodescuento idTipodescuento = convertidortipodescto
					.getIdTipodescuento();
			if (idTipodescuento != null) {
				idTipodescuento = em.getReference(idTipodescuento.getClass(),
						idTipodescuento.getId());
				convertidortipodescto.setIdTipodescuento(idTipodescuento);
			}
			em.persist(convertidortipodescto);
			if (idTipodescuento != null) {
				idTipodescuento.getConvertidortipodesctoList().add(
						convertidortipodescto);
				idTipodescuento = em.merge(idTipodescuento);
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findConvertidortipodescto(convertidortipodescto.getId()) != null) {
				throw new PreexistingEntityException("Convertidortipodescto "
						+ convertidortipodescto + " already exists.", ex);
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
	 * @param convertidortipodescto
	 *            Convertidortipodescto
	 * @throws NonexistentEntityException
	 * @throws Exception
	 */
	public void edit(Convertidortipodescto convertidortipodescto)
			throws NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Convertidortipodescto persistentConvertidortipodescto = em.find(
					Convertidortipodescto.class, convertidortipodescto.getId());
			Tipodescuento idTipodescuentoOld = persistentConvertidortipodescto
					.getIdTipodescuento();
			Tipodescuento idTipodescuentoNew = convertidortipodescto
					.getIdTipodescuento();
			if (idTipodescuentoNew != null) {
				idTipodescuentoNew = em.getReference(
						idTipodescuentoNew.getClass(),
						idTipodescuentoNew.getId());
				convertidortipodescto.setIdTipodescuento(idTipodescuentoNew);
			}
			convertidortipodescto = em.merge(convertidortipodescto);
			if (idTipodescuentoOld != null
					&& !idTipodescuentoOld.equals(idTipodescuentoNew)) {
				idTipodescuentoOld.getConvertidortipodesctoList().remove(
						convertidortipodescto);
				idTipodescuentoOld = em.merge(idTipodescuentoOld);
			}
			if (idTipodescuentoNew != null
					&& !idTipodescuentoNew.equals(idTipodescuentoOld)) {
				idTipodescuentoNew.getConvertidortipodesctoList().add(
						convertidortipodescto);
				idTipodescuentoNew = em.merge(idTipodescuentoNew);
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = convertidortipodescto.getId();
				if (findConvertidortipodescto(id) == null) {
					throw new NonexistentEntityException(
							"The convertidortipodescto with id " + id
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
			Convertidortipodescto convertidortipodescto;
			try {
				convertidortipodescto = em.getReference(
						Convertidortipodescto.class, id);
				convertidortipodescto.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException(
						"The convertidortipodescto with id " + id
								+ " no longer exists.", enfe);
			}
			Tipodescuento idTipodescuento = convertidortipodescto
					.getIdTipodescuento();
			if (idTipodescuento != null) {
				idTipodescuento.getConvertidortipodesctoList().remove(
						convertidortipodescto);
				idTipodescuento = em.merge(idTipodescuento);
			}
			em.remove(convertidortipodescto);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method findConvertidortipodesctoEntities.
	 * 
	 * @return List<Convertidortipodescto>
	 */
	public List<Convertidortipodescto> findConvertidortipodesctoEntities() {
		return findConvertidortipodesctoEntities(true, -1, -1);
	}

	/**
	 * Method findConvertidortipodesctoEntities.
	 * 
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Convertidortipodescto>
	 */
	public List<Convertidortipodescto> findConvertidortipodesctoEntities(
			int maxResults, int firstResult) {
		return findConvertidortipodesctoEntities(false, maxResults, firstResult);
	}

	/**
	 * Method findConvertidortipodesctoEntities.
	 * 
	 * @param all
	 *            boolean
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Convertidortipodescto>
	 */
	private List<Convertidortipodescto> findConvertidortipodesctoEntities(
			boolean all, int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Convertidortipodescto.class));
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
	 * Method findConvertidortipodescto.
	 * 
	 * @param id
	 *            Long
	 * @return Convertidortipodescto
	 */
	public Convertidortipodescto findConvertidortipodescto(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Convertidortipodescto.class, id);
		} finally {
			em.close();
		}
	}

	/**
	 * Method getConvertidortipodesctoCount.
	 * 
	 * @return int
	 */
	public int getConvertidortipodesctoCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Convertidortipodescto> rt = cq
					.from(Convertidortipodescto.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}
