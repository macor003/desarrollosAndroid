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
public class OrdendeventaJpaController implements Serializable {

	/**
	 * Constructor for OrdendeventaJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public OrdendeventaJpaController(EntityManagerFactory emf) {
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
	 * @param ordendeventa
	 *            Ordendeventa
	 * @throws PreexistingEntityException
	 * @throws Exception
	 */
	public void create(Ordendeventa ordendeventa)
			throws PreexistingEntityException, Exception {
		if (ordendeventa.getOrdendeventaarticuloList() == null) {
			ordendeventa
					.setOrdendeventaarticuloList(new ArrayList<Ordendeventaarticulo>());
		}
		if (ordendeventa.getAbonoList() == null) {
			ordendeventa.setAbonoList(new ArrayList<Abono>());
		}
		if (ordendeventa.getOrdendeventatransaccionList() == null) {
			ordendeventa
					.setOrdendeventatransaccionList(new ArrayList<Ordendeventatransaccion>());
		}
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Cliente numeroIdentificacionCliente = ordendeventa
					.getNumeroIdentificacionCliente();
			if (numeroIdentificacionCliente != null) {
				numeroIdentificacionCliente = em.getReference(
						numeroIdentificacionCliente.getClass(),
						numeroIdentificacionCliente
								.getNumeroIdentificacionCliente());
				ordendeventa
						.setNumeroIdentificacionCliente(numeroIdentificacionCliente);
			}
			Tipoordendeventa idTipoordendeventa = ordendeventa
					.getIdTipoordendeventa();
			if (idTipoordendeventa != null) {
				idTipoordendeventa = em.getReference(
						idTipoordendeventa.getClass(),
						idTipoordendeventa.getId());
				ordendeventa.setIdTipoordendeventa(idTipoordendeventa);
			}
			List<Ordendeventaarticulo> attachedOrdendeventaarticuloList = new ArrayList<Ordendeventaarticulo>();
			for (Ordendeventaarticulo ordendeventaarticuloListOrdendeventaarticuloToAttach : ordendeventa
					.getOrdendeventaarticuloList()) {
				ordendeventaarticuloListOrdendeventaarticuloToAttach = em
						.getReference(
								ordendeventaarticuloListOrdendeventaarticuloToAttach
										.getClass(),
								ordendeventaarticuloListOrdendeventaarticuloToAttach
										.getId());
				attachedOrdendeventaarticuloList
						.add(ordendeventaarticuloListOrdendeventaarticuloToAttach);
			}
			ordendeventa
					.setOrdendeventaarticuloList(attachedOrdendeventaarticuloList);
			List<Abono> attachedAbonoList = new ArrayList<Abono>();
			for (Abono abonoListAbonoToAttach : ordendeventa.getAbonoList()) {
				abonoListAbonoToAttach = em.getReference(
						abonoListAbonoToAttach.getClass(),
						abonoListAbonoToAttach.getId());
				attachedAbonoList.add(abonoListAbonoToAttach);
			}
			ordendeventa.setAbonoList(attachedAbonoList);
			List<Ordendeventatransaccion> attachedOrdendeventatransaccionList = new ArrayList<Ordendeventatransaccion>();
			for (Ordendeventatransaccion ordendeventatransaccionListOrdendeventatransaccionToAttach : ordendeventa
					.getOrdendeventatransaccionList()) {
				ordendeventatransaccionListOrdendeventatransaccionToAttach = em
						.getReference(
								ordendeventatransaccionListOrdendeventatransaccionToAttach
										.getClass(),
								ordendeventatransaccionListOrdendeventatransaccionToAttach
										.getId());
				attachedOrdendeventatransaccionList
						.add(ordendeventatransaccionListOrdendeventatransaccionToAttach);
			}
			ordendeventa
					.setOrdendeventatransaccionList(attachedOrdendeventatransaccionList);
			em.persist(ordendeventa);
			if (numeroIdentificacionCliente != null) {
				numeroIdentificacionCliente.getOrdendeventaList().add(
						ordendeventa);
				numeroIdentificacionCliente = em
						.merge(numeroIdentificacionCliente);
			}
			if (idTipoordendeventa != null) {
				idTipoordendeventa.getOrdendeventaList().add(ordendeventa);
				idTipoordendeventa = em.merge(idTipoordendeventa);
			}
			for (Ordendeventaarticulo ordendeventaarticuloListOrdendeventaarticulo : ordendeventa
					.getOrdendeventaarticuloList()) {
				Ordendeventa oldIdOrdendeventaOfOrdendeventaarticuloListOrdendeventaarticulo = ordendeventaarticuloListOrdendeventaarticulo
						.getIdOrdendeventa();
				ordendeventaarticuloListOrdendeventaarticulo
						.setIdOrdendeventa(ordendeventa);
				ordendeventaarticuloListOrdendeventaarticulo = em
						.merge(ordendeventaarticuloListOrdendeventaarticulo);
				if (oldIdOrdendeventaOfOrdendeventaarticuloListOrdendeventaarticulo != null) {
					oldIdOrdendeventaOfOrdendeventaarticuloListOrdendeventaarticulo
							.getOrdendeventaarticuloList()
							.remove(ordendeventaarticuloListOrdendeventaarticulo);
					oldIdOrdendeventaOfOrdendeventaarticuloListOrdendeventaarticulo = em
							.merge(oldIdOrdendeventaOfOrdendeventaarticuloListOrdendeventaarticulo);
				}
			}
			for (Abono abonoListAbono : ordendeventa.getAbonoList()) {
				Ordendeventa oldIdOrdendeventaOfAbonoListAbono = abonoListAbono
						.getIdOrdendeventa();
				abonoListAbono.setIdOrdendeventa(ordendeventa);
				abonoListAbono = em.merge(abonoListAbono);
				if (oldIdOrdendeventaOfAbonoListAbono != null) {
					oldIdOrdendeventaOfAbonoListAbono.getAbonoList().remove(
							abonoListAbono);
					oldIdOrdendeventaOfAbonoListAbono = em
							.merge(oldIdOrdendeventaOfAbonoListAbono);
				}
			}
			for (Ordendeventatransaccion ordendeventatransaccionListOrdendeventatransaccion : ordendeventa
					.getOrdendeventatransaccionList()) {
				Ordendeventa oldIdOrdendeventaOfOrdendeventatransaccionListOrdendeventatransaccion = ordendeventatransaccionListOrdendeventatransaccion
						.getIdOrdendeventa();
				ordendeventatransaccionListOrdendeventatransaccion
						.setIdOrdendeventa(ordendeventa);
				ordendeventatransaccionListOrdendeventatransaccion = em
						.merge(ordendeventatransaccionListOrdendeventatransaccion);
				if (oldIdOrdendeventaOfOrdendeventatransaccionListOrdendeventatransaccion != null) {
					oldIdOrdendeventaOfOrdendeventatransaccionListOrdendeventatransaccion
							.getOrdendeventatransaccionList()
							.remove(ordendeventatransaccionListOrdendeventatransaccion);
					oldIdOrdendeventaOfOrdendeventatransaccionListOrdendeventatransaccion = em
							.merge(oldIdOrdendeventaOfOrdendeventatransaccionListOrdendeventatransaccion);
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findOrdendeventa(ordendeventa.getId()) != null) {
				throw new PreexistingEntityException("Ordendeventa "
						+ ordendeventa + " already exists.", ex);
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
	 * @param ordendeventa
	 *            Ordendeventa
	 * @throws IllegalOrphanException
	 * @throws NonexistentEntityException
	 * @throws Exception
	 */
	public void edit(Ordendeventa ordendeventa) throws IllegalOrphanException,
			NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Ordendeventa persistentOrdendeventa = em.find(Ordendeventa.class,
					ordendeventa.getId());
			Cliente numeroIdentificacionClienteOld = persistentOrdendeventa
					.getNumeroIdentificacionCliente();
			Cliente numeroIdentificacionClienteNew = ordendeventa
					.getNumeroIdentificacionCliente();
			Tipoordendeventa idTipoordendeventaOld = persistentOrdendeventa
					.getIdTipoordendeventa();
			Tipoordendeventa idTipoordendeventaNew = ordendeventa
					.getIdTipoordendeventa();
			List<Ordendeventaarticulo> ordendeventaarticuloListOld = persistentOrdendeventa
					.getOrdendeventaarticuloList();
			List<Ordendeventaarticulo> ordendeventaarticuloListNew = ordendeventa
					.getOrdendeventaarticuloList();
			List<Abono> abonoListOld = persistentOrdendeventa.getAbonoList();
			List<Abono> abonoListNew = ordendeventa.getAbonoList();
			List<Ordendeventatransaccion> ordendeventatransaccionListOld = persistentOrdendeventa
					.getOrdendeventatransaccionList();
			List<Ordendeventatransaccion> ordendeventatransaccionListNew = ordendeventa
					.getOrdendeventatransaccionList();
			List<String> illegalOrphanMessages = null;
			for (Ordendeventaarticulo ordendeventaarticuloListOldOrdendeventaarticulo : ordendeventaarticuloListOld) {
				if (!ordendeventaarticuloListNew
						.contains(ordendeventaarticuloListOldOrdendeventaarticulo)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages
							.add("You must retain Ordendeventaarticulo "
									+ ordendeventaarticuloListOldOrdendeventaarticulo
									+ " since its idOrdendeventa field is not nullable.");
				}
			}
			for (Abono abonoListOldAbono : abonoListOld) {
				if (!abonoListNew.contains(abonoListOldAbono)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages
							.add("You must retain Abono "
									+ abonoListOldAbono
									+ " since its idOrdendeventa field is not nullable.");
				}
			}
			for (Ordendeventatransaccion ordendeventatransaccionListOldOrdendeventatransaccion : ordendeventatransaccionListOld) {
				if (!ordendeventatransaccionListNew
						.contains(ordendeventatransaccionListOldOrdendeventatransaccion)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages
							.add("You must retain Ordendeventatransaccion "
									+ ordendeventatransaccionListOldOrdendeventatransaccion
									+ " since its idOrdendeventa field is not nullable.");
				}
			}
			if (illegalOrphanMessages != null) {
				throw new IllegalOrphanException(illegalOrphanMessages);
			}
			if (numeroIdentificacionClienteNew != null) {
				numeroIdentificacionClienteNew = em.getReference(
						numeroIdentificacionClienteNew.getClass(),
						numeroIdentificacionClienteNew
								.getNumeroIdentificacionCliente());
				ordendeventa
						.setNumeroIdentificacionCliente(numeroIdentificacionClienteNew);
			}
			if (idTipoordendeventaNew != null) {
				idTipoordendeventaNew = em.getReference(
						idTipoordendeventaNew.getClass(),
						idTipoordendeventaNew.getId());
				ordendeventa.setIdTipoordendeventa(idTipoordendeventaNew);
			}
			List<Ordendeventaarticulo> attachedOrdendeventaarticuloListNew = new ArrayList<Ordendeventaarticulo>();
			for (Ordendeventaarticulo ordendeventaarticuloListNewOrdendeventaarticuloToAttach : ordendeventaarticuloListNew) {
				ordendeventaarticuloListNewOrdendeventaarticuloToAttach = em
						.getReference(
								ordendeventaarticuloListNewOrdendeventaarticuloToAttach
										.getClass(),
								ordendeventaarticuloListNewOrdendeventaarticuloToAttach
										.getId());
				attachedOrdendeventaarticuloListNew
						.add(ordendeventaarticuloListNewOrdendeventaarticuloToAttach);
			}
			ordendeventaarticuloListNew = attachedOrdendeventaarticuloListNew;
			ordendeventa
					.setOrdendeventaarticuloList(ordendeventaarticuloListNew);
			List<Abono> attachedAbonoListNew = new ArrayList<Abono>();
			for (Abono abonoListNewAbonoToAttach : abonoListNew) {
				abonoListNewAbonoToAttach = em.getReference(
						abonoListNewAbonoToAttach.getClass(),
						abonoListNewAbonoToAttach.getId());
				attachedAbonoListNew.add(abonoListNewAbonoToAttach);
			}
			abonoListNew = attachedAbonoListNew;
			ordendeventa.setAbonoList(abonoListNew);
			List<Ordendeventatransaccion> attachedOrdendeventatransaccionListNew = new ArrayList<Ordendeventatransaccion>();
			for (Ordendeventatransaccion ordendeventatransaccionListNewOrdendeventatransaccionToAttach : ordendeventatransaccionListNew) {
				ordendeventatransaccionListNewOrdendeventatransaccionToAttach = em
						.getReference(
								ordendeventatransaccionListNewOrdendeventatransaccionToAttach
										.getClass(),
								ordendeventatransaccionListNewOrdendeventatransaccionToAttach
										.getId());
				attachedOrdendeventatransaccionListNew
						.add(ordendeventatransaccionListNewOrdendeventatransaccionToAttach);
			}
			ordendeventatransaccionListNew = attachedOrdendeventatransaccionListNew;
			ordendeventa
					.setOrdendeventatransaccionList(ordendeventatransaccionListNew);
			ordendeventa = em.merge(ordendeventa);
			if (numeroIdentificacionClienteOld != null
					&& !numeroIdentificacionClienteOld
							.equals(numeroIdentificacionClienteNew)) {
				numeroIdentificacionClienteOld.getOrdendeventaList().remove(
						ordendeventa);
				numeroIdentificacionClienteOld = em
						.merge(numeroIdentificacionClienteOld);
			}
			if (numeroIdentificacionClienteNew != null
					&& !numeroIdentificacionClienteNew
							.equals(numeroIdentificacionClienteOld)) {
				numeroIdentificacionClienteNew.getOrdendeventaList().add(
						ordendeventa);
				numeroIdentificacionClienteNew = em
						.merge(numeroIdentificacionClienteNew);
			}
			if (idTipoordendeventaOld != null
					&& !idTipoordendeventaOld.equals(idTipoordendeventaNew)) {
				idTipoordendeventaOld.getOrdendeventaList()
						.remove(ordendeventa);
				idTipoordendeventaOld = em.merge(idTipoordendeventaOld);
			}
			if (idTipoordendeventaNew != null
					&& !idTipoordendeventaNew.equals(idTipoordendeventaOld)) {
				idTipoordendeventaNew.getOrdendeventaList().add(ordendeventa);
				idTipoordendeventaNew = em.merge(idTipoordendeventaNew);
			}
			for (Ordendeventaarticulo ordendeventaarticuloListNewOrdendeventaarticulo : ordendeventaarticuloListNew) {
				if (!ordendeventaarticuloListOld
						.contains(ordendeventaarticuloListNewOrdendeventaarticulo)) {
					Ordendeventa oldIdOrdendeventaOfOrdendeventaarticuloListNewOrdendeventaarticulo = ordendeventaarticuloListNewOrdendeventaarticulo
							.getIdOrdendeventa();
					ordendeventaarticuloListNewOrdendeventaarticulo
							.setIdOrdendeventa(ordendeventa);
					ordendeventaarticuloListNewOrdendeventaarticulo = em
							.merge(ordendeventaarticuloListNewOrdendeventaarticulo);
					if (oldIdOrdendeventaOfOrdendeventaarticuloListNewOrdendeventaarticulo != null
							&& !oldIdOrdendeventaOfOrdendeventaarticuloListNewOrdendeventaarticulo
									.equals(ordendeventa)) {
						oldIdOrdendeventaOfOrdendeventaarticuloListNewOrdendeventaarticulo
								.getOrdendeventaarticuloList()
								.remove(ordendeventaarticuloListNewOrdendeventaarticulo);
						oldIdOrdendeventaOfOrdendeventaarticuloListNewOrdendeventaarticulo = em
								.merge(oldIdOrdendeventaOfOrdendeventaarticuloListNewOrdendeventaarticulo);
					}
				}
			}
			for (Abono abonoListNewAbono : abonoListNew) {
				if (!abonoListOld.contains(abonoListNewAbono)) {
					Ordendeventa oldIdOrdendeventaOfAbonoListNewAbono = abonoListNewAbono
							.getIdOrdendeventa();
					abonoListNewAbono.setIdOrdendeventa(ordendeventa);
					abonoListNewAbono = em.merge(abonoListNewAbono);
					if (oldIdOrdendeventaOfAbonoListNewAbono != null
							&& !oldIdOrdendeventaOfAbonoListNewAbono
									.equals(ordendeventa)) {
						oldIdOrdendeventaOfAbonoListNewAbono.getAbonoList()
								.remove(abonoListNewAbono);
						oldIdOrdendeventaOfAbonoListNewAbono = em
								.merge(oldIdOrdendeventaOfAbonoListNewAbono);
					}
				}
			}
			for (Ordendeventatransaccion ordendeventatransaccionListNewOrdendeventatransaccion : ordendeventatransaccionListNew) {
				if (!ordendeventatransaccionListOld
						.contains(ordendeventatransaccionListNewOrdendeventatransaccion)) {
					Ordendeventa oldIdOrdendeventaOfOrdendeventatransaccionListNewOrdendeventatransaccion = ordendeventatransaccionListNewOrdendeventatransaccion
							.getIdOrdendeventa();
					ordendeventatransaccionListNewOrdendeventatransaccion
							.setIdOrdendeventa(ordendeventa);
					ordendeventatransaccionListNewOrdendeventatransaccion = em
							.merge(ordendeventatransaccionListNewOrdendeventatransaccion);
					if (oldIdOrdendeventaOfOrdendeventatransaccionListNewOrdendeventatransaccion != null
							&& !oldIdOrdendeventaOfOrdendeventatransaccionListNewOrdendeventatransaccion
									.equals(ordendeventa)) {
						oldIdOrdendeventaOfOrdendeventatransaccionListNewOrdendeventatransaccion
								.getOrdendeventatransaccionList()
								.remove(ordendeventatransaccionListNewOrdendeventatransaccion);
						oldIdOrdendeventaOfOrdendeventatransaccionListNewOrdendeventatransaccion = em
								.merge(oldIdOrdendeventaOfOrdendeventatransaccionListNewOrdendeventatransaccion);
					}
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = ordendeventa.getId();
				if (findOrdendeventa(id) == null) {
					throw new NonexistentEntityException(
							"The ordendeventa with id " + id
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
			Ordendeventa ordendeventa;
			try {
				ordendeventa = em.getReference(Ordendeventa.class, id);
				ordendeventa.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException(
						"The ordendeventa with id " + id + " no longer exists.",
						enfe);
			}
			List<String> illegalOrphanMessages = null;
			List<Ordendeventaarticulo> ordendeventaarticuloListOrphanCheck = ordendeventa
					.getOrdendeventaarticuloList();
			for (Ordendeventaarticulo ordendeventaarticuloListOrphanCheckOrdendeventaarticulo : ordendeventaarticuloListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages
						.add("This Ordendeventa ("
								+ ordendeventa
								+ ") cannot be destroyed since the Ordendeventaarticulo "
								+ ordendeventaarticuloListOrphanCheckOrdendeventaarticulo
								+ " in its ordendeventaarticuloList field has a non-nullable idOrdendeventa field.");
			}
			List<Abono> abonoListOrphanCheck = ordendeventa.getAbonoList();
			for (Abono abonoListOrphanCheckAbono : abonoListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages
						.add("This Ordendeventa ("
								+ ordendeventa
								+ ") cannot be destroyed since the Abono "
								+ abonoListOrphanCheckAbono
								+ " in its abonoList field has a non-nullable idOrdendeventa field.");
			}
			List<Ordendeventatransaccion> ordendeventatransaccionListOrphanCheck = ordendeventa
					.getOrdendeventatransaccionList();
			for (Ordendeventatransaccion ordendeventatransaccionListOrphanCheckOrdendeventatransaccion : ordendeventatransaccionListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages
						.add("This Ordendeventa ("
								+ ordendeventa
								+ ") cannot be destroyed since the Ordendeventatransaccion "
								+ ordendeventatransaccionListOrphanCheckOrdendeventatransaccion
								+ " in its ordendeventatransaccionList field has a non-nullable idOrdendeventa field.");
			}
			if (illegalOrphanMessages != null) {
				throw new IllegalOrphanException(illegalOrphanMessages);
			}
			Cliente numeroIdentificacionCliente = ordendeventa
					.getNumeroIdentificacionCliente();
			if (numeroIdentificacionCliente != null) {
				numeroIdentificacionCliente.getOrdendeventaList().remove(
						ordendeventa);
				numeroIdentificacionCliente = em
						.merge(numeroIdentificacionCliente);
			}
			Tipoordendeventa idTipoordendeventa = ordendeventa
					.getIdTipoordendeventa();
			if (idTipoordendeventa != null) {
				idTipoordendeventa.getOrdendeventaList().remove(ordendeventa);
				idTipoordendeventa = em.merge(idTipoordendeventa);
			}
			em.remove(ordendeventa);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method findOrdendeventaEntities.
	 * 
	 * @return List<Ordendeventa>
	 */
	public List<Ordendeventa> findOrdendeventaEntities() {
		return findOrdendeventaEntities(true, -1, -1);
	}

	/**
	 * Method findOrdendeventaEntities.
	 * 
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Ordendeventa>
	 */
	public List<Ordendeventa> findOrdendeventaEntities(int maxResults,
			int firstResult) {
		return findOrdendeventaEntities(false, maxResults, firstResult);
	}

	/**
	 * Method findOrdendeventaEntities.
	 * 
	 * @param all
	 *            boolean
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Ordendeventa>
	 */
	private List<Ordendeventa> findOrdendeventaEntities(boolean all,
			int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Ordendeventa.class));
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
	 * Method findOrdendeventa.
	 * 
	 * @param id
	 *            Long
	 * @return Ordendeventa
	 */
	public Ordendeventa findOrdendeventa(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Ordendeventa.class, id);
		} finally {
			em.close();
		}
	}

	/**
	 * Method getOrdendeventaCount.
	 * 
	 * @return int
	 */
	public int getOrdendeventaCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Ordendeventa> rt = cq.from(Ordendeventa.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}
