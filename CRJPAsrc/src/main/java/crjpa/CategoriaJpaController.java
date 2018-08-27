/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crjpa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import crjpa.exceptions.IllegalOrphanException;
import crjpa.exceptions.NonexistentEntityException;
import crjpa.exceptions.PreexistingEntityException;

/**
 * 
 * @author eve0014321
 * @version $Revision: 1.0 $
 */
public class CategoriaJpaController implements Serializable {

	/**
	 * Constructor for CategoriaJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public CategoriaJpaController(EntityManagerFactory emf) {
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
	 * @param categoria
	 *            Categoria
	 * @throws PreexistingEntityException
	 * @throws Exception
	 */
	public void create(Categoria categoria) throws PreexistingEntityException, Exception {
		if (categoria.getLineaList() == null) {
			categoria.setLineaList(new ArrayList<Linea>());
		}
		if (categoria.getPromocioncategoriaList() == null) {
			categoria.setPromocioncategoriaList(new ArrayList<Promocioncategoria>());
		}
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			List<Linea> attachedLineaList = new ArrayList<Linea>();
			for (Linea lineaListLineaToAttach : categoria.getLineaList()) {
				lineaListLineaToAttach = em.getReference(lineaListLineaToAttach.getClass(),
						lineaListLineaToAttach.getId());
				attachedLineaList.add(lineaListLineaToAttach);
			}
			categoria.setLineaList(attachedLineaList);
			List<Promocioncategoria> attachedPromocioncategoriaList = new ArrayList<Promocioncategoria>();
			for (Promocioncategoria promocioncategoriaListPromocioncategoriaToAttach : categoria
					.getPromocioncategoriaList()) {
				promocioncategoriaListPromocioncategoriaToAttach = em.getReference(
						promocioncategoriaListPromocioncategoriaToAttach.getClass(),
						promocioncategoriaListPromocioncategoriaToAttach.getId());
				attachedPromocioncategoriaList.add(promocioncategoriaListPromocioncategoriaToAttach);
			}
			categoria.setPromocioncategoriaList(attachedPromocioncategoriaList);
			em.persist(categoria);
			for (Linea lineaListLinea : categoria.getLineaList()) {
				Categoria oldIdCategoriaOfLineaListLinea = lineaListLinea.getIdCategoria();
				lineaListLinea.setIdCategoria(categoria);
				lineaListLinea = em.merge(lineaListLinea);
				if (oldIdCategoriaOfLineaListLinea != null) {
					oldIdCategoriaOfLineaListLinea.getLineaList().remove(lineaListLinea);
					oldIdCategoriaOfLineaListLinea = em.merge(oldIdCategoriaOfLineaListLinea);
				}
			}
			for (Promocioncategoria promocioncategoriaListPromocioncategoria : categoria.getPromocioncategoriaList()) {
				Categoria oldIdCategoriaOfPromocioncategoriaListPromocioncategoria = promocioncategoriaListPromocioncategoria
						.getIdCategoria();
				promocioncategoriaListPromocioncategoria.setIdCategoria(categoria);
				promocioncategoriaListPromocioncategoria = em.merge(promocioncategoriaListPromocioncategoria);
				if (oldIdCategoriaOfPromocioncategoriaListPromocioncategoria != null) {
					oldIdCategoriaOfPromocioncategoriaListPromocioncategoria.getPromocioncategoriaList().remove(
							promocioncategoriaListPromocioncategoria);
					oldIdCategoriaOfPromocioncategoriaListPromocioncategoria = em
							.merge(oldIdCategoriaOfPromocioncategoriaListPromocioncategoria);
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findCategoria(categoria.getId()) != null) {
				throw new PreexistingEntityException("Categoria " + categoria + " already exists.", ex);
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
	 * @param categoria
	 *            Categoria
	 * @throws IllegalOrphanException
	 * @throws NonexistentEntityException
	 * @throws Exception
	 */
	public void edit(Categoria categoria) throws IllegalOrphanException, NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Categoria persistentCategoria = em.find(Categoria.class, categoria.getId());
			List<Linea> lineaListOld = persistentCategoria.getLineaList();
			List<Linea> lineaListNew = categoria.getLineaList();
			List<Promocioncategoria> promocioncategoriaListOld = persistentCategoria.getPromocioncategoriaList();
			List<Promocioncategoria> promocioncategoriaListNew = categoria.getPromocioncategoriaList();
			List<String> illegalOrphanMessages = null;
			for (Linea lineaListOldLinea : lineaListOld) {
				if (!lineaListNew.contains(lineaListOldLinea)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages.add("You must retain Linea " + lineaListOldLinea
							+ " since its idCategoria field is not nullable.");
				}
			}
			for (Promocioncategoria promocioncategoriaListOldPromocioncategoria : promocioncategoriaListOld) {
				if (!promocioncategoriaListNew.contains(promocioncategoriaListOldPromocioncategoria)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages.add("You must retain Promocioncategoria "
							+ promocioncategoriaListOldPromocioncategoria
							+ " since its idCategoria field is not nullable.");
				}
			}
			if (illegalOrphanMessages != null) {
				throw new IllegalOrphanException(illegalOrphanMessages);
			}
			List<Linea> attachedLineaListNew = new ArrayList<Linea>();
			for (Linea lineaListNewLineaToAttach : lineaListNew) {
				lineaListNewLineaToAttach = em.getReference(lineaListNewLineaToAttach.getClass(),
						lineaListNewLineaToAttach.getId());
				attachedLineaListNew.add(lineaListNewLineaToAttach);
			}
			lineaListNew = attachedLineaListNew;
			categoria.setLineaList(lineaListNew);
			List<Promocioncategoria> attachedPromocioncategoriaListNew = new ArrayList<Promocioncategoria>();
			for (Promocioncategoria promocioncategoriaListNewPromocioncategoriaToAttach : promocioncategoriaListNew) {
				promocioncategoriaListNewPromocioncategoriaToAttach = em.getReference(
						promocioncategoriaListNewPromocioncategoriaToAttach.getClass(),
						promocioncategoriaListNewPromocioncategoriaToAttach.getId());
				attachedPromocioncategoriaListNew.add(promocioncategoriaListNewPromocioncategoriaToAttach);
			}
			promocioncategoriaListNew = attachedPromocioncategoriaListNew;
			categoria.setPromocioncategoriaList(promocioncategoriaListNew);
			categoria = em.merge(categoria);
			for (Linea lineaListNewLinea : lineaListNew) {
				if (!lineaListOld.contains(lineaListNewLinea)) {
					Categoria oldIdCategoriaOfLineaListNewLinea = lineaListNewLinea.getIdCategoria();
					lineaListNewLinea.setIdCategoria(categoria);
					lineaListNewLinea = em.merge(lineaListNewLinea);
					if (oldIdCategoriaOfLineaListNewLinea != null
							&& !oldIdCategoriaOfLineaListNewLinea.equals(categoria)) {
						oldIdCategoriaOfLineaListNewLinea.getLineaList().remove(lineaListNewLinea);
						oldIdCategoriaOfLineaListNewLinea = em.merge(oldIdCategoriaOfLineaListNewLinea);
					}
				}
			}
			for (Promocioncategoria promocioncategoriaListNewPromocioncategoria : promocioncategoriaListNew) {
				if (!promocioncategoriaListOld.contains(promocioncategoriaListNewPromocioncategoria)) {
					Categoria oldIdCategoriaOfPromocioncategoriaListNewPromocioncategoria = promocioncategoriaListNewPromocioncategoria
							.getIdCategoria();
					promocioncategoriaListNewPromocioncategoria.setIdCategoria(categoria);
					promocioncategoriaListNewPromocioncategoria = em.merge(promocioncategoriaListNewPromocioncategoria);
					if (oldIdCategoriaOfPromocioncategoriaListNewPromocioncategoria != null
							&& !oldIdCategoriaOfPromocioncategoriaListNewPromocioncategoria.equals(categoria)) {
						oldIdCategoriaOfPromocioncategoriaListNewPromocioncategoria.getPromocioncategoriaList().remove(
								promocioncategoriaListNewPromocioncategoria);
						oldIdCategoriaOfPromocioncategoriaListNewPromocioncategoria = em
								.merge(oldIdCategoriaOfPromocioncategoriaListNewPromocioncategoria);
					}
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = categoria.getId();
				if (findCategoria(id) == null) {
					throw new NonexistentEntityException("The categoria with id " + id + " no longer exists.");
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
	 * @throws IllegalOrphanException
	 * @throws NonexistentEntityException
	 */
	public void destroy(Long id) throws IllegalOrphanException, NonexistentEntityException {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Categoria categoria;
			try {
				categoria = em.getReference(Categoria.class, id);
				categoria.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException("The categoria with id " + id + " no longer exists.", enfe);
			}
			List<String> illegalOrphanMessages = null;
			List<Linea> lineaListOrphanCheck = categoria.getLineaList();
			for (Linea lineaListOrphanCheckLinea : lineaListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages.add("This Categoria (" + categoria + ") cannot be destroyed since the Linea "
						+ lineaListOrphanCheckLinea + " in its lineaList field has a non-nullable idCategoria field.");
			}
			List<Promocioncategoria> promocioncategoriaListOrphanCheck = categoria.getPromocioncategoriaList();
			for (Promocioncategoria promocioncategoriaListOrphanCheckPromocioncategoria : promocioncategoriaListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages.add("This Categoria (" + categoria
						+ ") cannot be destroyed since the Promocioncategoria "
						+ promocioncategoriaListOrphanCheckPromocioncategoria
						+ " in its promocioncategoriaList field has a non-nullable idCategoria field.");
			}
			if (illegalOrphanMessages != null) {
				throw new IllegalOrphanException(illegalOrphanMessages);
			}
			em.remove(categoria);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method findCategoriaEntities.
	 * 
	 * @return List<Categoria>
	 */
	public List<Categoria> findCategoriaEntities() {
		return findCategoriaEntities(true, -1, -1);
	}

	/**
	 * Method findCategoriaEntities.
	 * 
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Categoria>
	 */
	public List<Categoria> findCategoriaEntities(int maxResults, int firstResult) {
		return findCategoriaEntities(false, maxResults, firstResult);
	}

	/**
	 * Method findCategoriaEntities.
	 * 
	 * @param all
	 *            boolean
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Categoria>
	 */
	private List<Categoria> findCategoriaEntities(boolean all, int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Categoria.class));
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
	 * Method findCategoria.
	 * 
	 * @param id
	 *            Long
	 * @return Categoria
	 */
	public Categoria findCategoria(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Categoria.class, id);
		} finally {
			em.close();
		}
	}

	/**
	 * Method getCategoriaCount.
	 * 
	 * @return int
	 */
	public int getCategoriaCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Categoria> rt = cq.from(Categoria.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}
