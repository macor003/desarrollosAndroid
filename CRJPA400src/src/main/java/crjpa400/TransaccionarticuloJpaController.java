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

import crjpa400.exceptions.IllegalOrphanException;
import crjpa400.exceptions.NonexistentEntityException;
import crjpa400.exceptions.PreexistingEntityException;

/**
 * 
 * @author eve0014321
 * @version $Revision: 1.0 $
 */
public class TransaccionarticuloJpaController implements Serializable {

	/**
	 * Constructor for TransaccionarticuloJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public TransaccionarticuloJpaController(EntityManagerFactory emf) {
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
	 * @param transaccionarticulo
	 *            Transaccionarticulo
	 * @throws PreexistingEntityException
	 * @throws Exception
	 */
	public void create(Transaccionarticulo transaccionarticulo)
			throws PreexistingEntityException, Exception {
		if (transaccionarticulo.getTransaccionarticuloservicioList() == null) {
			transaccionarticulo
					.setTransaccionarticuloservicioList(new ArrayList<Transaccionarticuloservicio>());
		}
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Unidadventa idUnidadventa = transaccionarticulo.getIdUnidadventa();
			if (idUnidadventa != null) {
				idUnidadventa = em.getReference(idUnidadventa.getClass(),
						idUnidadventa.getId());
				transaccionarticulo.setIdUnidadventa(idUnidadventa);
			}
			Transaccion idTransaccion = transaccionarticulo.getIdTransaccion();
			if (idTransaccion != null) {
				idTransaccion = em.getReference(idTransaccion.getClass(),
						idTransaccion.getId());
				transaccionarticulo.setIdTransaccion(idTransaccion);
			}
			Tipodescuento idTipodescuento = transaccionarticulo
					.getIdTipodescuento();
			if (idTipodescuento != null) {
				idTipodescuento = em.getReference(idTipodescuento.getClass(),
						idTipodescuento.getId());
				transaccionarticulo.setIdTipodescuento(idTipodescuento);
			}
			Tasaimpuestovalor idTasaimpuestovalor = transaccionarticulo
					.getIdTasaimpuestovalor();
			if (idTasaimpuestovalor != null) {
				idTasaimpuestovalor = em.getReference(
						idTasaimpuestovalor.getClass(),
						idTasaimpuestovalor.getId());
				transaccionarticulo.setIdTasaimpuestovalor(idTasaimpuestovalor);
			}
			Promocion idPromocion = transaccionarticulo.getIdPromocion();
			if (idPromocion != null) {
				idPromocion = em.getReference(idPromocion.getClass(),
						idPromocion.getId());
				transaccionarticulo.setIdPromocion(idPromocion);
			}
			Articulo idArticulo = transaccionarticulo.getIdArticulo();
			if (idArticulo != null) {
				idArticulo = em.getReference(idArticulo.getClass(),
						idArticulo.getId());
				transaccionarticulo.setIdArticulo(idArticulo);
			}
			List<Transaccionarticuloservicio> attachedTransaccionarticuloservicioList = new ArrayList<Transaccionarticuloservicio>();
			for (Transaccionarticuloservicio transaccionarticuloservicioListTransaccionarticuloservicioToAttach : transaccionarticulo
					.getTransaccionarticuloservicioList()) {
				transaccionarticuloservicioListTransaccionarticuloservicioToAttach = em
						.getReference(
								transaccionarticuloservicioListTransaccionarticuloservicioToAttach
										.getClass(),
								transaccionarticuloservicioListTransaccionarticuloservicioToAttach
										.getId());
				attachedTransaccionarticuloservicioList
						.add(transaccionarticuloservicioListTransaccionarticuloservicioToAttach);
			}
			transaccionarticulo
					.setTransaccionarticuloservicioList(attachedTransaccionarticuloservicioList);
			em.persist(transaccionarticulo);
			if (idUnidadventa != null) {
				idUnidadventa.getTransaccionarticuloList().add(
						transaccionarticulo);
				idUnidadventa = em.merge(idUnidadventa);
			}
			if (idTransaccion != null) {
				idTransaccion.getTransaccionarticuloList().add(
						transaccionarticulo);
				idTransaccion = em.merge(idTransaccion);
			}
			if (idTipodescuento != null) {
				idTipodescuento.getTransaccionarticuloList().add(
						transaccionarticulo);
				idTipodescuento = em.merge(idTipodescuento);
			}
			if (idTasaimpuestovalor != null) {
				idTasaimpuestovalor.getTransaccionarticuloList().add(
						transaccionarticulo);
				idTasaimpuestovalor = em.merge(idTasaimpuestovalor);
			}
			if (idPromocion != null) {
				idPromocion.getTransaccionarticuloList().add(
						transaccionarticulo);
				idPromocion = em.merge(idPromocion);
			}
			if (idArticulo != null) {
				idArticulo.getTransaccionarticuloList()
						.add(transaccionarticulo);
				idArticulo = em.merge(idArticulo);
			}
			for (Transaccionarticuloservicio transaccionarticuloservicioListTransaccionarticuloservicio : transaccionarticulo
					.getTransaccionarticuloservicioList()) {
				Transaccionarticulo oldIdTransaccionarticuloOfTransaccionarticuloservicioListTransaccionarticuloservicio = transaccionarticuloservicioListTransaccionarticuloservicio
						.getIdTransaccionarticulo();
				transaccionarticuloservicioListTransaccionarticuloservicio
						.setIdTransaccionarticulo(transaccionarticulo);
				transaccionarticuloservicioListTransaccionarticuloservicio = em
						.merge(transaccionarticuloservicioListTransaccionarticuloservicio);
				if (oldIdTransaccionarticuloOfTransaccionarticuloservicioListTransaccionarticuloservicio != null) {
					oldIdTransaccionarticuloOfTransaccionarticuloservicioListTransaccionarticuloservicio
							.getTransaccionarticuloservicioList()
							.remove(transaccionarticuloservicioListTransaccionarticuloservicio);
					oldIdTransaccionarticuloOfTransaccionarticuloservicioListTransaccionarticuloservicio = em
							.merge(oldIdTransaccionarticuloOfTransaccionarticuloservicioListTransaccionarticuloservicio);
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findTransaccionarticulo(transaccionarticulo.getId()) != null) {
				throw new PreexistingEntityException("Transaccionarticulo "
						+ transaccionarticulo + " already exists.", ex);
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
	 * @param transaccionarticulo
	 *            Transaccionarticulo
	 * @throws IllegalOrphanException
	 * @throws NonexistentEntityException
	 * @throws Exception
	 */
	public void edit(Transaccionarticulo transaccionarticulo)
			throws IllegalOrphanException, NonexistentEntityException,
			Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Transaccionarticulo persistentTransaccionarticulo = em.find(
					Transaccionarticulo.class, transaccionarticulo.getId());
			Unidadventa idUnidadventaOld = persistentTransaccionarticulo
					.getIdUnidadventa();
			Unidadventa idUnidadventaNew = transaccionarticulo
					.getIdUnidadventa();
			Transaccion idTransaccionOld = persistentTransaccionarticulo
					.getIdTransaccion();
			Transaccion idTransaccionNew = transaccionarticulo
					.getIdTransaccion();
			Tipodescuento idTipodescuentoOld = persistentTransaccionarticulo
					.getIdTipodescuento();
			Tipodescuento idTipodescuentoNew = transaccionarticulo
					.getIdTipodescuento();
			Tasaimpuestovalor idTasaimpuestovalorOld = persistentTransaccionarticulo
					.getIdTasaimpuestovalor();
			Tasaimpuestovalor idTasaimpuestovalorNew = transaccionarticulo
					.getIdTasaimpuestovalor();
			Promocion idPromocionOld = persistentTransaccionarticulo
					.getIdPromocion();
			Promocion idPromocionNew = transaccionarticulo.getIdPromocion();
			Articulo idArticuloOld = persistentTransaccionarticulo
					.getIdArticulo();
			Articulo idArticuloNew = transaccionarticulo.getIdArticulo();
			List<Transaccionarticuloservicio> transaccionarticuloservicioListOld = persistentTransaccionarticulo
					.getTransaccionarticuloservicioList();
			List<Transaccionarticuloservicio> transaccionarticuloservicioListNew = transaccionarticulo
					.getTransaccionarticuloservicioList();
			List<String> illegalOrphanMessages = null;
			for (Transaccionarticuloservicio transaccionarticuloservicioListOldTransaccionarticuloservicio : transaccionarticuloservicioListOld) {
				if (!transaccionarticuloservicioListNew
						.contains(transaccionarticuloservicioListOldTransaccionarticuloservicio)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages
							.add("You must retain Transaccionarticuloservicio "
									+ transaccionarticuloservicioListOldTransaccionarticuloservicio
									+ " since its idTransaccionarticulo field is not nullable.");
				}
			}
			if (illegalOrphanMessages != null) {
				throw new IllegalOrphanException(illegalOrphanMessages);
			}
			if (idUnidadventaNew != null) {
				idUnidadventaNew = em.getReference(idUnidadventaNew.getClass(),
						idUnidadventaNew.getId());
				transaccionarticulo.setIdUnidadventa(idUnidadventaNew);
			}
			if (idTransaccionNew != null) {
				idTransaccionNew = em.getReference(idTransaccionNew.getClass(),
						idTransaccionNew.getId());
				transaccionarticulo.setIdTransaccion(idTransaccionNew);
			}
			if (idTipodescuentoNew != null) {
				idTipodescuentoNew = em.getReference(
						idTipodescuentoNew.getClass(),
						idTipodescuentoNew.getId());
				transaccionarticulo.setIdTipodescuento(idTipodescuentoNew);
			}
			if (idTasaimpuestovalorNew != null) {
				idTasaimpuestovalorNew = em.getReference(
						idTasaimpuestovalorNew.getClass(),
						idTasaimpuestovalorNew.getId());
				transaccionarticulo
						.setIdTasaimpuestovalor(idTasaimpuestovalorNew);
			}
			if (idPromocionNew != null) {
				idPromocionNew = em.getReference(idPromocionNew.getClass(),
						idPromocionNew.getId());
				transaccionarticulo.setIdPromocion(idPromocionNew);
			}
			if (idArticuloNew != null) {
				idArticuloNew = em.getReference(idArticuloNew.getClass(),
						idArticuloNew.getId());
				transaccionarticulo.setIdArticulo(idArticuloNew);
			}
			List<Transaccionarticuloservicio> attachedTransaccionarticuloservicioListNew = new ArrayList<Transaccionarticuloservicio>();
			for (Transaccionarticuloservicio transaccionarticuloservicioListNewTransaccionarticuloservicioToAttach : transaccionarticuloservicioListNew) {
				transaccionarticuloservicioListNewTransaccionarticuloservicioToAttach = em
						.getReference(
								transaccionarticuloservicioListNewTransaccionarticuloservicioToAttach
										.getClass(),
								transaccionarticuloservicioListNewTransaccionarticuloservicioToAttach
										.getId());
				attachedTransaccionarticuloservicioListNew
						.add(transaccionarticuloservicioListNewTransaccionarticuloservicioToAttach);
			}
			transaccionarticuloservicioListNew = attachedTransaccionarticuloservicioListNew;
			transaccionarticulo
					.setTransaccionarticuloservicioList(transaccionarticuloservicioListNew);
			transaccionarticulo = em.merge(transaccionarticulo);
			if (idUnidadventaOld != null
					&& !idUnidadventaOld.equals(idUnidadventaNew)) {
				idUnidadventaOld.getTransaccionarticuloList().remove(
						transaccionarticulo);
				idUnidadventaOld = em.merge(idUnidadventaOld);
			}
			if (idUnidadventaNew != null
					&& !idUnidadventaNew.equals(idUnidadventaOld)) {
				idUnidadventaNew.getTransaccionarticuloList().add(
						transaccionarticulo);
				idUnidadventaNew = em.merge(idUnidadventaNew);
			}
			if (idTransaccionOld != null
					&& !idTransaccionOld.equals(idTransaccionNew)) {
				idTransaccionOld.getTransaccionarticuloList().remove(
						transaccionarticulo);
				idTransaccionOld = em.merge(idTransaccionOld);
			}
			if (idTransaccionNew != null
					&& !idTransaccionNew.equals(idTransaccionOld)) {
				idTransaccionNew.getTransaccionarticuloList().add(
						transaccionarticulo);
				idTransaccionNew = em.merge(idTransaccionNew);
			}
			if (idTipodescuentoOld != null
					&& !idTipodescuentoOld.equals(idTipodescuentoNew)) {
				idTipodescuentoOld.getTransaccionarticuloList().remove(
						transaccionarticulo);
				idTipodescuentoOld = em.merge(idTipodescuentoOld);
			}
			if (idTipodescuentoNew != null
					&& !idTipodescuentoNew.equals(idTipodescuentoOld)) {
				idTipodescuentoNew.getTransaccionarticuloList().add(
						transaccionarticulo);
				idTipodescuentoNew = em.merge(idTipodescuentoNew);
			}
			if (idTasaimpuestovalorOld != null
					&& !idTasaimpuestovalorOld.equals(idTasaimpuestovalorNew)) {
				idTasaimpuestovalorOld.getTransaccionarticuloList().remove(
						transaccionarticulo);
				idTasaimpuestovalorOld = em.merge(idTasaimpuestovalorOld);
			}
			if (idTasaimpuestovalorNew != null
					&& !idTasaimpuestovalorNew.equals(idTasaimpuestovalorOld)) {
				idTasaimpuestovalorNew.getTransaccionarticuloList().add(
						transaccionarticulo);
				idTasaimpuestovalorNew = em.merge(idTasaimpuestovalorNew);
			}
			if (idPromocionOld != null
					&& !idPromocionOld.equals(idPromocionNew)) {
				idPromocionOld.getTransaccionarticuloList().remove(
						transaccionarticulo);
				idPromocionOld = em.merge(idPromocionOld);
			}
			if (idPromocionNew != null
					&& !idPromocionNew.equals(idPromocionOld)) {
				idPromocionNew.getTransaccionarticuloList().add(
						transaccionarticulo);
				idPromocionNew = em.merge(idPromocionNew);
			}
			if (idArticuloOld != null && !idArticuloOld.equals(idArticuloNew)) {
				idArticuloOld.getTransaccionarticuloList().remove(
						transaccionarticulo);
				idArticuloOld = em.merge(idArticuloOld);
			}
			if (idArticuloNew != null && !idArticuloNew.equals(idArticuloOld)) {
				idArticuloNew.getTransaccionarticuloList().add(
						transaccionarticulo);
				idArticuloNew = em.merge(idArticuloNew);
			}
			for (Transaccionarticuloservicio transaccionarticuloservicioListNewTransaccionarticuloservicio : transaccionarticuloservicioListNew) {
				if (!transaccionarticuloservicioListOld
						.contains(transaccionarticuloservicioListNewTransaccionarticuloservicio)) {
					Transaccionarticulo oldIdTransaccionarticuloOfTransaccionarticuloservicioListNewTransaccionarticuloservicio = transaccionarticuloservicioListNewTransaccionarticuloservicio
							.getIdTransaccionarticulo();
					transaccionarticuloservicioListNewTransaccionarticuloservicio
							.setIdTransaccionarticulo(transaccionarticulo);
					transaccionarticuloservicioListNewTransaccionarticuloservicio = em
							.merge(transaccionarticuloservicioListNewTransaccionarticuloservicio);
					if (oldIdTransaccionarticuloOfTransaccionarticuloservicioListNewTransaccionarticuloservicio != null
							&& !oldIdTransaccionarticuloOfTransaccionarticuloservicioListNewTransaccionarticuloservicio
									.equals(transaccionarticulo)) {
						oldIdTransaccionarticuloOfTransaccionarticuloservicioListNewTransaccionarticuloservicio
								.getTransaccionarticuloservicioList()
								.remove(transaccionarticuloservicioListNewTransaccionarticuloservicio);
						oldIdTransaccionarticuloOfTransaccionarticuloservicioListNewTransaccionarticuloservicio = em
								.merge(oldIdTransaccionarticuloOfTransaccionarticuloservicioListNewTransaccionarticuloservicio);
					}
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = transaccionarticulo.getId();
				if (findTransaccionarticulo(id) == null) {
					throw new NonexistentEntityException(
							"The transaccionarticulo with id " + id
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
	 * @throws IllegalOrphanException
	 * @throws NonexistentEntityException
	 */
	public void destroy(Long id) throws IllegalOrphanException,
			NonexistentEntityException {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Transaccionarticulo transaccionarticulo;
			try {
				transaccionarticulo = em.getReference(
						Transaccionarticulo.class, id);
				transaccionarticulo.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException(
						"The transaccionarticulo with id " + id
								+ " no longer exists.", enfe);
			}
			List<String> illegalOrphanMessages = null;
			List<Transaccionarticuloservicio> transaccionarticuloservicioListOrphanCheck = transaccionarticulo
					.getTransaccionarticuloservicioList();
			for (Transaccionarticuloservicio transaccionarticuloservicioListOrphanCheckTransaccionarticuloservicio : transaccionarticuloservicioListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages
						.add("This Transaccionarticulo ("
								+ transaccionarticulo
								+ ") cannot be destroyed since the Transaccionarticuloservicio "
								+ transaccionarticuloservicioListOrphanCheckTransaccionarticuloservicio
								+ " in its transaccionarticuloservicioList field has a non-nullable idTransaccionarticulo field.");
			}
			if (illegalOrphanMessages != null) {
				throw new IllegalOrphanException(illegalOrphanMessages);
			}
			Unidadventa idUnidadventa = transaccionarticulo.getIdUnidadventa();
			if (idUnidadventa != null) {
				idUnidadventa.getTransaccionarticuloList().remove(
						transaccionarticulo);
				idUnidadventa = em.merge(idUnidadventa);
			}
			Transaccion idTransaccion = transaccionarticulo.getIdTransaccion();
			if (idTransaccion != null) {
				idTransaccion.getTransaccionarticuloList().remove(
						transaccionarticulo);
				idTransaccion = em.merge(idTransaccion);
			}
			Tipodescuento idTipodescuento = transaccionarticulo
					.getIdTipodescuento();
			if (idTipodescuento != null) {
				idTipodescuento.getTransaccionarticuloList().remove(
						transaccionarticulo);
				idTipodescuento = em.merge(idTipodescuento);
			}
			Tasaimpuestovalor idTasaimpuestovalor = transaccionarticulo
					.getIdTasaimpuestovalor();
			if (idTasaimpuestovalor != null) {
				idTasaimpuestovalor.getTransaccionarticuloList().remove(
						transaccionarticulo);
				idTasaimpuestovalor = em.merge(idTasaimpuestovalor);
			}
			Promocion idPromocion = transaccionarticulo.getIdPromocion();
			if (idPromocion != null) {
				idPromocion.getTransaccionarticuloList().remove(
						transaccionarticulo);
				idPromocion = em.merge(idPromocion);
			}
			Articulo idArticulo = transaccionarticulo.getIdArticulo();
			if (idArticulo != null) {
				idArticulo.getTransaccionarticuloList().remove(
						transaccionarticulo);
				idArticulo = em.merge(idArticulo);
			}
			em.remove(transaccionarticulo);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method findTransaccionarticuloEntities.
	 * 
	 * @return List<Transaccionarticulo>
	 */
	public List<Transaccionarticulo> findTransaccionarticuloEntities() {
		return findTransaccionarticuloEntities(true, -1, -1);
	}

	/**
	 * Method findTransaccionarticuloEntities.
	 * 
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Transaccionarticulo>
	 */
	public List<Transaccionarticulo> findTransaccionarticuloEntities(
			int maxResults, int firstResult) {
		return findTransaccionarticuloEntities(false, maxResults, firstResult);
	}

	/**
	 * Method findTransaccionarticuloEntities.
	 * 
	 * @param all
	 *            boolean
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Transaccionarticulo>
	 */
	private List<Transaccionarticulo> findTransaccionarticuloEntities(
			boolean all, int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Transaccionarticulo.class));
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
	 * Method findTransaccionarticulo.
	 * 
	 * @param id
	 *            Long
	 * @return Transaccionarticulo
	 */
	public Transaccionarticulo findTransaccionarticulo(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Transaccionarticulo.class, id);
		} finally {
			em.close();
		}
	}

	/**
	 * Method getTransaccionarticuloCount.
	 * 
	 * @return int
	 */
	public int getTransaccionarticuloCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Transaccionarticulo> rt = cq.from(Transaccionarticulo.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}
