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
public class FamiliaJpaController implements Serializable {

	/**
	 * Constructor for FamiliaJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public FamiliaJpaController(EntityManagerFactory emf) {
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
	 * @param familia
	 *            Familia
	 * @throws PreexistingEntityException
	 * @throws Exception
	 */
	public void create(Familia familia) throws PreexistingEntityException, Exception {
		if (familia.getArticuloList() == null) {
			familia.setArticuloList(new ArrayList<Articulo>());
		}
		if (familia.getPromocionfamiliaList() == null) {
			familia.setPromocionfamiliaList(new ArrayList<Promocionfamilia>());
		}
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Linea idLinea = familia.getIdLinea();
			if (idLinea != null) {
				idLinea = em.getReference(idLinea.getClass(), idLinea.getId());
				familia.setIdLinea(idLinea);
			}
			List<Articulo> attachedArticuloList = new ArrayList<Articulo>();
			for (Articulo articuloListArticuloToAttach : familia.getArticuloList()) {
				articuloListArticuloToAttach = em.getReference(articuloListArticuloToAttach.getClass(),
						articuloListArticuloToAttach.getId());
				attachedArticuloList.add(articuloListArticuloToAttach);
			}
			familia.setArticuloList(attachedArticuloList);
			List<Promocionfamilia> attachedPromocionfamiliaList = new ArrayList<Promocionfamilia>();
			for (Promocionfamilia promocionfamiliaListPromocionfamiliaToAttach : familia.getPromocionfamiliaList()) {
				promocionfamiliaListPromocionfamiliaToAttach = em.getReference(
						promocionfamiliaListPromocionfamiliaToAttach.getClass(),
						promocionfamiliaListPromocionfamiliaToAttach.getId());
				attachedPromocionfamiliaList.add(promocionfamiliaListPromocionfamiliaToAttach);
			}
			familia.setPromocionfamiliaList(attachedPromocionfamiliaList);
			em.persist(familia);
			if (idLinea != null) {
				idLinea.getFamiliaList().add(familia);
				idLinea = em.merge(idLinea);
			}
			for (Articulo articuloListArticulo : familia.getArticuloList()) {
				Familia oldIdFamiliaOfArticuloListArticulo = articuloListArticulo.getIdFamilia();
				articuloListArticulo.setIdFamilia(familia);
				articuloListArticulo = em.merge(articuloListArticulo);
				if (oldIdFamiliaOfArticuloListArticulo != null) {
					oldIdFamiliaOfArticuloListArticulo.getArticuloList().remove(articuloListArticulo);
					oldIdFamiliaOfArticuloListArticulo = em.merge(oldIdFamiliaOfArticuloListArticulo);
				}
			}
			for (Promocionfamilia promocionfamiliaListPromocionfamilia : familia.getPromocionfamiliaList()) {
				Familia oldIdFamiliaOfPromocionfamiliaListPromocionfamilia = promocionfamiliaListPromocionfamilia
						.getIdFamilia();
				promocionfamiliaListPromocionfamilia.setIdFamilia(familia);
				promocionfamiliaListPromocionfamilia = em.merge(promocionfamiliaListPromocionfamilia);
				if (oldIdFamiliaOfPromocionfamiliaListPromocionfamilia != null) {
					oldIdFamiliaOfPromocionfamiliaListPromocionfamilia.getPromocionfamiliaList().remove(
							promocionfamiliaListPromocionfamilia);
					oldIdFamiliaOfPromocionfamiliaListPromocionfamilia = em
							.merge(oldIdFamiliaOfPromocionfamiliaListPromocionfamilia);
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findFamilia(familia.getId()) != null) {
				throw new PreexistingEntityException("Familia " + familia + " already exists.", ex);
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
	 * @param familia
	 *            Familia
	 * @throws IllegalOrphanException
	 * @throws NonexistentEntityException
	 * @throws Exception
	 */
	public void edit(Familia familia) throws IllegalOrphanException, NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Familia persistentFamilia = em.find(Familia.class, familia.getId());
			Linea idLineaOld = persistentFamilia.getIdLinea();
			Linea idLineaNew = familia.getIdLinea();
			List<Articulo> articuloListOld = persistentFamilia.getArticuloList();
			List<Articulo> articuloListNew = familia.getArticuloList();
			List<Promocionfamilia> promocionfamiliaListOld = persistentFamilia.getPromocionfamiliaList();
			List<Promocionfamilia> promocionfamiliaListNew = familia.getPromocionfamiliaList();
			List<String> illegalOrphanMessages = null;
			for (Promocionfamilia promocionfamiliaListOldPromocionfamilia : promocionfamiliaListOld) {
				if (!promocionfamiliaListNew.contains(promocionfamiliaListOldPromocionfamilia)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages.add("You must retain Promocionfamilia "
							+ promocionfamiliaListOldPromocionfamilia + " since its idFamilia field is not nullable.");
				}
			}
			if (illegalOrphanMessages != null) {
				throw new IllegalOrphanException(illegalOrphanMessages);
			}
			if (idLineaNew != null) {
				idLineaNew = em.getReference(idLineaNew.getClass(), idLineaNew.getId());
				familia.setIdLinea(idLineaNew);
			}
			List<Articulo> attachedArticuloListNew = new ArrayList<Articulo>();
			for (Articulo articuloListNewArticuloToAttach : articuloListNew) {
				articuloListNewArticuloToAttach = em.getReference(articuloListNewArticuloToAttach.getClass(),
						articuloListNewArticuloToAttach.getId());
				attachedArticuloListNew.add(articuloListNewArticuloToAttach);
			}
			articuloListNew = attachedArticuloListNew;
			familia.setArticuloList(articuloListNew);
			List<Promocionfamilia> attachedPromocionfamiliaListNew = new ArrayList<Promocionfamilia>();
			for (Promocionfamilia promocionfamiliaListNewPromocionfamiliaToAttach : promocionfamiliaListNew) {
				promocionfamiliaListNewPromocionfamiliaToAttach = em.getReference(
						promocionfamiliaListNewPromocionfamiliaToAttach.getClass(),
						promocionfamiliaListNewPromocionfamiliaToAttach.getId());
				attachedPromocionfamiliaListNew.add(promocionfamiliaListNewPromocionfamiliaToAttach);
			}
			promocionfamiliaListNew = attachedPromocionfamiliaListNew;
			familia.setPromocionfamiliaList(promocionfamiliaListNew);
			familia = em.merge(familia);
			if (idLineaOld != null && !idLineaOld.equals(idLineaNew)) {
				idLineaOld.getFamiliaList().remove(familia);
				idLineaOld = em.merge(idLineaOld);
			}
			if (idLineaNew != null && !idLineaNew.equals(idLineaOld)) {
				idLineaNew.getFamiliaList().add(familia);
				idLineaNew = em.merge(idLineaNew);
			}
			for (Articulo articuloListOldArticulo : articuloListOld) {
				if (!articuloListNew.contains(articuloListOldArticulo)) {
					articuloListOldArticulo.setIdFamilia(null);
					articuloListOldArticulo = em.merge(articuloListOldArticulo);
				}
			}
			for (Articulo articuloListNewArticulo : articuloListNew) {
				if (!articuloListOld.contains(articuloListNewArticulo)) {
					Familia oldIdFamiliaOfArticuloListNewArticulo = articuloListNewArticulo.getIdFamilia();
					articuloListNewArticulo.setIdFamilia(familia);
					articuloListNewArticulo = em.merge(articuloListNewArticulo);
					if (oldIdFamiliaOfArticuloListNewArticulo != null
							&& !oldIdFamiliaOfArticuloListNewArticulo.equals(familia)) {
						oldIdFamiliaOfArticuloListNewArticulo.getArticuloList().remove(articuloListNewArticulo);
						oldIdFamiliaOfArticuloListNewArticulo = em.merge(oldIdFamiliaOfArticuloListNewArticulo);
					}
				}
			}
			for (Promocionfamilia promocionfamiliaListNewPromocionfamilia : promocionfamiliaListNew) {
				if (!promocionfamiliaListOld.contains(promocionfamiliaListNewPromocionfamilia)) {
					Familia oldIdFamiliaOfPromocionfamiliaListNewPromocionfamilia = promocionfamiliaListNewPromocionfamilia
							.getIdFamilia();
					promocionfamiliaListNewPromocionfamilia.setIdFamilia(familia);
					promocionfamiliaListNewPromocionfamilia = em.merge(promocionfamiliaListNewPromocionfamilia);
					if (oldIdFamiliaOfPromocionfamiliaListNewPromocionfamilia != null
							&& !oldIdFamiliaOfPromocionfamiliaListNewPromocionfamilia.equals(familia)) {
						oldIdFamiliaOfPromocionfamiliaListNewPromocionfamilia.getPromocionfamiliaList().remove(
								promocionfamiliaListNewPromocionfamilia);
						oldIdFamiliaOfPromocionfamiliaListNewPromocionfamilia = em
								.merge(oldIdFamiliaOfPromocionfamiliaListNewPromocionfamilia);
					}
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = familia.getId();
				if (findFamilia(id) == null) {
					throw new NonexistentEntityException("The familia with id " + id + " no longer exists.");
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
			Familia familia;
			try {
				familia = em.getReference(Familia.class, id);
				familia.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException("The familia with id " + id + " no longer exists.", enfe);
			}
			List<String> illegalOrphanMessages = null;
			List<Promocionfamilia> promocionfamiliaListOrphanCheck = familia.getPromocionfamiliaList();
			for (Promocionfamilia promocionfamiliaListOrphanCheckPromocionfamilia : promocionfamiliaListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages.add("This Familia (" + familia
						+ ") cannot be destroyed since the Promocionfamilia "
						+ promocionfamiliaListOrphanCheckPromocionfamilia
						+ " in its promocionfamiliaList field has a non-nullable idFamilia field.");
			}
			if (illegalOrphanMessages != null) {
				throw new IllegalOrphanException(illegalOrphanMessages);
			}
			Linea idLinea = familia.getIdLinea();
			if (idLinea != null) {
				idLinea.getFamiliaList().remove(familia);
				idLinea = em.merge(idLinea);
			}
			List<Articulo> articuloList = familia.getArticuloList();
			for (Articulo articuloListArticulo : articuloList) {
				articuloListArticulo.setIdFamilia(null);
				articuloListArticulo = em.merge(articuloListArticulo);
			}
			em.remove(familia);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method findFamiliaEntities.
	 * 
	 * @return List<Familia>
	 */
	public List<Familia> findFamiliaEntities() {
		return findFamiliaEntities(true, -1, -1);
	}

	/**
	 * Method findFamiliaEntities.
	 * 
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Familia>
	 */
	public List<Familia> findFamiliaEntities(int maxResults, int firstResult) {
		return findFamiliaEntities(false, maxResults, firstResult);
	}

	/**
	 * Method findFamiliaEntities.
	 * 
	 * @param all
	 *            boolean
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Familia>
	 */
	private List<Familia> findFamiliaEntities(boolean all, int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Familia.class));
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
	 * Method findFamilia.
	 * 
	 * @param id
	 *            Long
	 * @return Familia
	 */
	public Familia findFamilia(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Familia.class, id);
		} finally {
			em.close();
		}
	}

	/**
	 * Method getFamiliaCount.
	 * 
	 * @return int
	 */
	public int getFamiliaCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Familia> rt = cq.from(Familia.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}
