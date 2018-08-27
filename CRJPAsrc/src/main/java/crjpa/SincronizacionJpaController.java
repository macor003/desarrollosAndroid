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
public class SincronizacionJpaController implements Serializable {

	/**
	 * Field TIPO_INICIAL_I. (value is ""I"")
	 */
	public static final String TIPO_INICIAL_I = "I";
	/**
	 * Field TIPO_INICIAL_A. (value is ""A"")
	 */
	public static final String TIPO_INICIAL_A = "A";
	/**
	 * Field TIPO_TIEMPO. (value is ""T"")
	 */
	public static final String TIPO_TIEMPO = "T";
	/**
	 * Field TIPO_FINAL. (value is ""F"")
	 */
	public static final String TIPO_FINAL = "F";
	/**
	 * Field ES_CONCILIACION. (value is ""C"")
	 */
	public static final String TIPO_CONCILIACION = "C";
	/**
	 * Field ES_DESCARGA_S. (value is ""S"")
	 */
	public static final String ES_DESCARGA_S = "S";
	/**
	 * Field ES_DESCARGA_N. (value is ""N"")
	 */
	public static final String ES_DESCARGA_N = "N";

	/**
	 * Constructor for SincronizacionJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public SincronizacionJpaController(EntityManagerFactory emf) {
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
	 * @param sincronizacion
	 *            Sincronizacion
	 * @throws PreexistingEntityException
	 * @throws Exception
	 */
	public void create(Sincronizacion sincronizacion) throws PreexistingEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			em.persist(sincronizacion);
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findSincronizacion(sincronizacion.getId()) != null) {
				throw new PreexistingEntityException("Sincronizacion " + sincronizacion + " already exists.", ex);
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
	 * @param sincronizacion
	 *            Sincronizacion
	 * @throws NonexistentEntityException
	 * @throws Exception
	 */
	public void edit(Sincronizacion sincronizacion) throws NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			sincronizacion = em.merge(sincronizacion);
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = sincronizacion.getId();
				if (findSincronizacion(id) == null) {
					throw new NonexistentEntityException("The sincronizacion with id " + id + " no longer exists.");
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
			Sincronizacion sincronizacion;
			try {
				sincronizacion = em.getReference(Sincronizacion.class, id);
				sincronizacion.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException("The sincronizacion with id " + id + " no longer exists.", enfe);
			}
			em.remove(sincronizacion);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method findSincronizacionEntities.
	 * 
	 * @return List<Sincronizacion>
	 */
	public List<Sincronizacion> findSincronizacionEntities() {
		return findSincronizacionEntities(true, -1, -1);
	}

	/**
	 * Method findByTipoAndEsDescarga.
	 * 
	 * @param tipo
	 *            String
	 * @param esdescarga
	 *            String
	 * @return List<Sincronizacion>
	 */
	public List<Sincronizacion> findByTipoAndEsDescarga(String tipo, String esdescarga) {
		EntityManager em = getEntityManager();
		try {
			Query query = em
					.createQuery("SELECT s FROM Sincronizacion s WHERE s.esdescarga = :esdescarga AND s.tipo = :tipo");
			query.setParameter("esdescarga", esdescarga);
			query.setParameter("tipo", tipo);

			return query.getResultList();
		} finally {
			em.close();
		}
	}

	/**
	 * Method findSincronizacionEntities.
	 * 
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Sincronizacion>
	 */
	public List<Sincronizacion> findSincronizacionEntities(int maxResults, int firstResult) {
		return findSincronizacionEntities(false, maxResults, firstResult);
	}

	/**
	 * Method findSincronizacionEntities.
	 * 
	 * @param all
	 *            boolean
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Sincronizacion>
	 */
	private List<Sincronizacion> findSincronizacionEntities(boolean all, int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Sincronizacion.class));
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
	 * Method findSincronizacion.
	 * 
	 * @param id
	 *            Long
	 * @return Sincronizacion
	 */
	public Sincronizacion findSincronizacion(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Sincronizacion.class, id);
		} finally {
			em.close();
		}
	}

	/**
	 * Method getSincronizacionCount.
	 * 
	 * @return int
	 */
	public int getSincronizacionCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Sincronizacion> rt = cq.from(Sincronizacion.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}
