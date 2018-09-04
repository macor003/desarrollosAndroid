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
public class PromocioncategoriaJpaController implements Serializable {

	/**
	 * Constructor for PromocioncategoriaJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public PromocioncategoriaJpaController(EntityManagerFactory emf) {
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
	 * @param promocioncategoria
	 *            Promocioncategoria
	 * @throws PreexistingEntityException
	 * @throws Exception
	 */
	public void create(Promocioncategoria promocioncategoria) throws PreexistingEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Promocion idPromocion = promocioncategoria.getIdPromocion();
			if (idPromocion != null) {
				idPromocion = em.getReference(idPromocion.getClass(), idPromocion.getId());
				promocioncategoria.setIdPromocion(idPromocion);
			}
			Categoria idCategoria = promocioncategoria.getIdCategoria();
			if (idCategoria != null) {
				idCategoria = em.getReference(idCategoria.getClass(), idCategoria.getId());
				promocioncategoria.setIdCategoria(idCategoria);
			}
			em.persist(promocioncategoria);
			if (idPromocion != null) {
				idPromocion.getPromocioncategoriaList().add(promocioncategoria);
				idPromocion = em.merge(idPromocion);
			}
			if (idCategoria != null) {
				idCategoria.getPromocioncategoriaList().add(promocioncategoria);
				idCategoria = em.merge(idCategoria);
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findPromocioncategoria(promocioncategoria.getId()) != null) {
				throw new PreexistingEntityException("Promocioncategoria " + promocioncategoria + " already exists.",
						ex);
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
	 * @param promocioncategoria
	 *            Promocioncategoria
	 * @throws NonexistentEntityException
	 * @throws Exception
	 */
	public void edit(Promocioncategoria promocioncategoria) throws NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Promocioncategoria persistentPromocioncategoria = em.find(Promocioncategoria.class,
					promocioncategoria.getId());
			Promocion idPromocionOld = persistentPromocioncategoria.getIdPromocion();
			Promocion idPromocionNew = promocioncategoria.getIdPromocion();
			Categoria idCategoriaOld = persistentPromocioncategoria.getIdCategoria();
			Categoria idCategoriaNew = promocioncategoria.getIdCategoria();
			if (idPromocionNew != null) {
				idPromocionNew = em.getReference(idPromocionNew.getClass(), idPromocionNew.getId());
				promocioncategoria.setIdPromocion(idPromocionNew);
			}
			if (idCategoriaNew != null) {
				idCategoriaNew = em.getReference(idCategoriaNew.getClass(), idCategoriaNew.getId());
				promocioncategoria.setIdCategoria(idCategoriaNew);
			}
			promocioncategoria = em.merge(promocioncategoria);
			if (idPromocionOld != null && !idPromocionOld.equals(idPromocionNew)) {
				idPromocionOld.getPromocioncategoriaList().remove(promocioncategoria);
				idPromocionOld = em.merge(idPromocionOld);
			}
			if (idPromocionNew != null && !idPromocionNew.equals(idPromocionOld)) {
				idPromocionNew.getPromocioncategoriaList().add(promocioncategoria);
				idPromocionNew = em.merge(idPromocionNew);
			}
			if (idCategoriaOld != null && !idCategoriaOld.equals(idCategoriaNew)) {
				idCategoriaOld.getPromocioncategoriaList().remove(promocioncategoria);
				idCategoriaOld = em.merge(idCategoriaOld);
			}
			if (idCategoriaNew != null && !idCategoriaNew.equals(idCategoriaOld)) {
				idCategoriaNew.getPromocioncategoriaList().add(promocioncategoria);
				idCategoriaNew = em.merge(idCategoriaNew);
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = promocioncategoria.getId();
				if (findPromocioncategoria(id) == null) {
					throw new NonexistentEntityException("The promocioncategoria with id " + id + " no longer exists.");
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
			Promocioncategoria promocioncategoria;
			try {
				promocioncategoria = em.getReference(Promocioncategoria.class, id);
				promocioncategoria.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException("The promocioncategoria with id " + id + " no longer exists.",
						enfe);
			}
			Promocion idPromocion = promocioncategoria.getIdPromocion();
			if (idPromocion != null) {
				idPromocion.getPromocioncategoriaList().remove(promocioncategoria);
				idPromocion = em.merge(idPromocion);
			}
			Categoria idCategoria = promocioncategoria.getIdCategoria();
			if (idCategoria != null) {
				idCategoria.getPromocioncategoriaList().remove(promocioncategoria);
				idCategoria = em.merge(idCategoria);
			}
			em.remove(promocioncategoria);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method findPromocioncategoriaEntities.
	 * 
	 * @return List<Promocioncategoria>
	 */
	public List<Promocioncategoria> findPromocioncategoriaEntities() {
		return findPromocioncategoriaEntities(true, -1, -1);
	}

	/**
	 * Method findPromocioncategoriaEntities.
	 * 
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Promocioncategoria>
	 */
	public List<Promocioncategoria> findPromocioncategoriaEntities(int maxResults, int firstResult) {
		return findPromocioncategoriaEntities(false, maxResults, firstResult);
	}

	/**
	 * Method findPromocioncategoriaEntities.
	 * 
	 * @param all
	 *            boolean
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Promocioncategoria>
	 */
	private List<Promocioncategoria> findPromocioncategoriaEntities(boolean all, int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Promocioncategoria.class));
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
	 * Method findPromocioncategoria.
	 * 
	 * @param id
	 *            Long
	 * @return Promocioncategoria
	 */
	public Promocioncategoria findPromocioncategoria(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Promocioncategoria.class, id);
		} finally {
			em.close();
		}
	}

	/**
	 * Method getPromocioncategoriaCount.
	 * 
	 * @return int
	 */
	public int getPromocioncategoriaCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Promocioncategoria> rt = cq.from(Promocioncategoria.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}
