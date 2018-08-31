/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crjpa;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import crjpa.exceptions.NonexistentEntityException;
import crjpa.exceptions.PreexistingEntityException;

/**
 * 
 * @author eve0014321
 * @version $Revision: 1.0 $
 */
public class MotivorebajaJpaController implements Serializable {

	/**
	 * Constructor for MotivorebajaJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public MotivorebajaJpaController(EntityManagerFactory emf) {
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
	 * @param motivorebaja
	 *            Motivorebaja
	 * @throws PreexistingEntityException
	 * @throws Exception
	 */
	public void create(Motivorebaja motivorebaja) throws PreexistingEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Tipodescuento idTipodescuento = motivorebaja.getIdTipodescuento();
			if (idTipodescuento != null) {
				idTipodescuento = em.getReference(idTipodescuento.getClass(), idTipodescuento.getId());
				motivorebaja.setIdTipodescuento(idTipodescuento);
			}
			em.persist(motivorebaja);
			if (idTipodescuento != null) {
				idTipodescuento.getMotivorebajaList().add(motivorebaja);
				idTipodescuento = em.merge(idTipodescuento);
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findMotivorebaja(motivorebaja.getId()) != null) {
				throw new PreexistingEntityException("Motivorebaja " + motivorebaja + " already exists.", ex);
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
	 * @param motivorebaja
	 *            Motivorebaja
	 * @throws NonexistentEntityException
	 * @throws Exception
	 */
	public void edit(Motivorebaja motivorebaja) throws NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Motivorebaja persistentMotivorebaja = em.find(Motivorebaja.class, motivorebaja.getId());
			Tipodescuento idTipodescuentoOld = persistentMotivorebaja.getIdTipodescuento();
			Tipodescuento idTipodescuentoNew = motivorebaja.getIdTipodescuento();
			if (idTipodescuentoNew != null) {
				idTipodescuentoNew = em.getReference(idTipodescuentoNew.getClass(), idTipodescuentoNew.getId());
				motivorebaja.setIdTipodescuento(idTipodescuentoNew);
			}
			motivorebaja = em.merge(motivorebaja);
			if (idTipodescuentoOld != null && !idTipodescuentoOld.equals(idTipodescuentoNew)) {
				idTipodescuentoOld.getMotivorebajaList().remove(motivorebaja);
				idTipodescuentoOld = em.merge(idTipodescuentoOld);
			}
			if (idTipodescuentoNew != null && !idTipodescuentoNew.equals(idTipodescuentoOld)) {
				idTipodescuentoNew.getMotivorebajaList().add(motivorebaja);
				idTipodescuentoNew = em.merge(idTipodescuentoNew);
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = motivorebaja.getId();
				if (findMotivorebaja(id) == null) {
					throw new NonexistentEntityException("The motivorebaja with id " + id + " no longer exists.");
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
			Motivorebaja motivorebaja;
			try {
				motivorebaja = em.getReference(Motivorebaja.class, id);
				motivorebaja.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException("The motivorebaja with id " + id + " no longer exists.", enfe);
			}
			Tipodescuento idTipodescuento = motivorebaja.getIdTipodescuento();
			if (idTipodescuento != null) {
				idTipodescuento.getMotivorebajaList().remove(motivorebaja);
				idTipodescuento = em.merge(idTipodescuento);
			}
			em.remove(motivorebaja);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method findMotivorebajaEntities.
	 * 
	 * @return List<Motivorebaja>
	 */
	public List<Motivorebaja> findMotivorebajaEntities() {
		return findMotivorebajaEntities(true, -1, -1);
	}

	/**
	 * Method findMotivorebajaEntities.
	 * 
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Motivorebaja>
	 */
	public List<Motivorebaja> findMotivorebajaEntities(int maxResults, int firstResult) {
		return findMotivorebajaEntities(false, maxResults, firstResult);
	}

	/**
	 * Method findMotivorebajaEntities.
	 * 
	 * @param all
	 *            boolean
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Motivorebaja>
	 */
	private List<Motivorebaja> findMotivorebajaEntities(boolean all, int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Motivorebaja.class));
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
	 * Method findMotivorebaja.
	 * 
	 * @param id
	 *            Long
	 * @return Motivorebaja
	 */
	public Motivorebaja findMotivorebaja(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Motivorebaja.class, id);
		} finally {
			em.close();
		}
	}

	/**
	 * Method getMotivorebajaCount.
	 * 
	 * @return int
	 */
	public int getMotivorebajaCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Motivorebaja> rt = cq.from(Motivorebaja.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}
