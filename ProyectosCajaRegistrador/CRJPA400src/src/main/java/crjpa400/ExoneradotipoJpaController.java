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
public class ExoneradotipoJpaController implements Serializable {

	/**
	 * Constructor for ExoneradotipoJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public ExoneradotipoJpaController(EntityManagerFactory emf) {
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
	 * @param exoneradotipo
	 *            Exoneradotipo
	 * @throws PreexistingEntityException
	 * @throws Exception
	 */
	public void create(Exoneradotipo exoneradotipo)
			throws PreexistingEntityException, Exception {
		if (exoneradotipo.getExoneradoarticuloList() == null) {
			exoneradotipo
					.setExoneradoarticuloList(new ArrayList<Exoneradoarticulo>());
		}
		if (exoneradotipo.getClienteList() == null) {
			exoneradotipo.setClienteList(new ArrayList<Cliente>());
		}
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			List<Exoneradoarticulo> attachedExoneradoarticuloList = new ArrayList<Exoneradoarticulo>();
			for (Exoneradoarticulo exoneradoarticuloListExoneradoarticuloToAttach : exoneradotipo
					.getExoneradoarticuloList()) {
				exoneradoarticuloListExoneradoarticuloToAttach = em
						.getReference(
								exoneradoarticuloListExoneradoarticuloToAttach
										.getClass(),
								exoneradoarticuloListExoneradoarticuloToAttach
										.getExoneradoarticuloPK());
				attachedExoneradoarticuloList
						.add(exoneradoarticuloListExoneradoarticuloToAttach);
			}
			exoneradotipo
					.setExoneradoarticuloList(attachedExoneradoarticuloList);
			List<Cliente> attachedClienteList = new ArrayList<Cliente>();
			for (Cliente clienteListClienteToAttach : exoneradotipo
					.getClienteList()) {
				clienteListClienteToAttach = em.getReference(
						clienteListClienteToAttach.getClass(),
						clienteListClienteToAttach
								.getNumeroIdentificacionCliente());
				attachedClienteList.add(clienteListClienteToAttach);
			}
			exoneradotipo.setClienteList(attachedClienteList);
			em.persist(exoneradotipo);
			for (Exoneradoarticulo exoneradoarticuloListExoneradoarticulo : exoneradotipo
					.getExoneradoarticuloList()) {
				Exoneradotipo oldExoneradotipoOfExoneradoarticuloListExoneradoarticulo = exoneradoarticuloListExoneradoarticulo
						.getExoneradotipo();
				exoneradoarticuloListExoneradoarticulo
						.setExoneradotipo(exoneradotipo);
				exoneradoarticuloListExoneradoarticulo = em
						.merge(exoneradoarticuloListExoneradoarticulo);
				if (oldExoneradotipoOfExoneradoarticuloListExoneradoarticulo != null) {
					oldExoneradotipoOfExoneradoarticuloListExoneradoarticulo
							.getExoneradoarticuloList().remove(
									exoneradoarticuloListExoneradoarticulo);
					oldExoneradotipoOfExoneradoarticuloListExoneradoarticulo = em
							.merge(oldExoneradotipoOfExoneradoarticuloListExoneradoarticulo);
				}
			}
			for (Cliente clienteListCliente : exoneradotipo.getClienteList()) {
				Exoneradotipo oldIdExoneradotipoOfClienteListCliente = clienteListCliente
						.getIdExoneradotipo();
				clienteListCliente.setIdExoneradotipo(exoneradotipo);
				clienteListCliente = em.merge(clienteListCliente);
				if (oldIdExoneradotipoOfClienteListCliente != null) {
					oldIdExoneradotipoOfClienteListCliente.getClienteList()
							.remove(clienteListCliente);
					oldIdExoneradotipoOfClienteListCliente = em
							.merge(oldIdExoneradotipoOfClienteListCliente);
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findExoneradotipo(exoneradotipo.getId()) != null) {
				throw new PreexistingEntityException("Exoneradotipo "
						+ exoneradotipo + " already exists.", ex);
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
	 * @param exoneradotipo
	 *            Exoneradotipo
	 * @throws IllegalOrphanException
	 * @throws NonexistentEntityException
	 * @throws Exception
	 */
	public void edit(Exoneradotipo exoneradotipo)
			throws IllegalOrphanException, NonexistentEntityException,
			Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Exoneradotipo persistentExoneradotipo = em.find(
					Exoneradotipo.class, exoneradotipo.getId());
			List<Exoneradoarticulo> exoneradoarticuloListOld = persistentExoneradotipo
					.getExoneradoarticuloList();
			List<Exoneradoarticulo> exoneradoarticuloListNew = exoneradotipo
					.getExoneradoarticuloList();
			List<Cliente> clienteListOld = persistentExoneradotipo
					.getClienteList();
			List<Cliente> clienteListNew = exoneradotipo.getClienteList();
			List<String> illegalOrphanMessages = null;
			for (Exoneradoarticulo exoneradoarticuloListOldExoneradoarticulo : exoneradoarticuloListOld) {
				if (!exoneradoarticuloListNew
						.contains(exoneradoarticuloListOldExoneradoarticulo)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages
							.add("You must retain Exoneradoarticulo "
									+ exoneradoarticuloListOldExoneradoarticulo
									+ " since its exoneradotipo field is not nullable.");
				}
			}
			if (illegalOrphanMessages != null) {
				throw new IllegalOrphanException(illegalOrphanMessages);
			}
			List<Exoneradoarticulo> attachedExoneradoarticuloListNew = new ArrayList<Exoneradoarticulo>();
			for (Exoneradoarticulo exoneradoarticuloListNewExoneradoarticuloToAttach : exoneradoarticuloListNew) {
				exoneradoarticuloListNewExoneradoarticuloToAttach = em
						.getReference(
								exoneradoarticuloListNewExoneradoarticuloToAttach
										.getClass(),
								exoneradoarticuloListNewExoneradoarticuloToAttach
										.getExoneradoarticuloPK());
				attachedExoneradoarticuloListNew
						.add(exoneradoarticuloListNewExoneradoarticuloToAttach);
			}
			exoneradoarticuloListNew = attachedExoneradoarticuloListNew;
			exoneradotipo.setExoneradoarticuloList(exoneradoarticuloListNew);
			List<Cliente> attachedClienteListNew = new ArrayList<Cliente>();
			for (Cliente clienteListNewClienteToAttach : clienteListNew) {
				clienteListNewClienteToAttach = em.getReference(
						clienteListNewClienteToAttach.getClass(),
						clienteListNewClienteToAttach
								.getNumeroIdentificacionCliente());
				attachedClienteListNew.add(clienteListNewClienteToAttach);
			}
			clienteListNew = attachedClienteListNew;
			exoneradotipo.setClienteList(clienteListNew);
			exoneradotipo = em.merge(exoneradotipo);
			for (Exoneradoarticulo exoneradoarticuloListNewExoneradoarticulo : exoneradoarticuloListNew) {
				if (!exoneradoarticuloListOld
						.contains(exoneradoarticuloListNewExoneradoarticulo)) {
					Exoneradotipo oldExoneradotipoOfExoneradoarticuloListNewExoneradoarticulo = exoneradoarticuloListNewExoneradoarticulo
							.getExoneradotipo();
					exoneradoarticuloListNewExoneradoarticulo
							.setExoneradotipo(exoneradotipo);
					exoneradoarticuloListNewExoneradoarticulo = em
							.merge(exoneradoarticuloListNewExoneradoarticulo);
					if (oldExoneradotipoOfExoneradoarticuloListNewExoneradoarticulo != null
							&& !oldExoneradotipoOfExoneradoarticuloListNewExoneradoarticulo
									.equals(exoneradotipo)) {
						oldExoneradotipoOfExoneradoarticuloListNewExoneradoarticulo
								.getExoneradoarticuloList()
								.remove(exoneradoarticuloListNewExoneradoarticulo);
						oldExoneradotipoOfExoneradoarticuloListNewExoneradoarticulo = em
								.merge(oldExoneradotipoOfExoneradoarticuloListNewExoneradoarticulo);
					}
				}
			}
			for (Cliente clienteListOldCliente : clienteListOld) {
				if (!clienteListNew.contains(clienteListOldCliente)) {
					clienteListOldCliente.setIdExoneradotipo(null);
					clienteListOldCliente = em.merge(clienteListOldCliente);
				}
			}
			for (Cliente clienteListNewCliente : clienteListNew) {
				if (!clienteListOld.contains(clienteListNewCliente)) {
					Exoneradotipo oldIdExoneradotipoOfClienteListNewCliente = clienteListNewCliente
							.getIdExoneradotipo();
					clienteListNewCliente.setIdExoneradotipo(exoneradotipo);
					clienteListNewCliente = em.merge(clienteListNewCliente);
					if (oldIdExoneradotipoOfClienteListNewCliente != null
							&& !oldIdExoneradotipoOfClienteListNewCliente
									.equals(exoneradotipo)) {
						oldIdExoneradotipoOfClienteListNewCliente
								.getClienteList().remove(clienteListNewCliente);
						oldIdExoneradotipoOfClienteListNewCliente = em
								.merge(oldIdExoneradotipoOfClienteListNewCliente);
					}
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = exoneradotipo.getId();
				if (findExoneradotipo(id) == null) {
					throw new NonexistentEntityException(
							"The exoneradotipo with id " + id
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
			Exoneradotipo exoneradotipo;
			try {
				exoneradotipo = em.getReference(Exoneradotipo.class, id);
				exoneradotipo.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException(
						"The exoneradotipo with id " + id
								+ " no longer exists.", enfe);
			}
			List<String> illegalOrphanMessages = null;
			List<Exoneradoarticulo> exoneradoarticuloListOrphanCheck = exoneradotipo
					.getExoneradoarticuloList();
			for (Exoneradoarticulo exoneradoarticuloListOrphanCheckExoneradoarticulo : exoneradoarticuloListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages
						.add("This Exoneradotipo ("
								+ exoneradotipo
								+ ") cannot be destroyed since the Exoneradoarticulo "
								+ exoneradoarticuloListOrphanCheckExoneradoarticulo
								+ " in its exoneradoarticuloList field has a non-nullable exoneradotipo field.");
			}
			if (illegalOrphanMessages != null) {
				throw new IllegalOrphanException(illegalOrphanMessages);
			}
			List<Cliente> clienteList = exoneradotipo.getClienteList();
			for (Cliente clienteListCliente : clienteList) {
				clienteListCliente.setIdExoneradotipo(null);
				clienteListCliente = em.merge(clienteListCliente);
			}
			em.remove(exoneradotipo);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method findExoneradotipoEntities.
	 * 
	 * @return List<Exoneradotipo>
	 */
	public List<Exoneradotipo> findExoneradotipoEntities() {
		return findExoneradotipoEntities(true, -1, -1);
	}

	/**
	 * Method findExoneradotipoEntities.
	 * 
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Exoneradotipo>
	 */
	public List<Exoneradotipo> findExoneradotipoEntities(int maxResults,
			int firstResult) {
		return findExoneradotipoEntities(false, maxResults, firstResult);
	}

	/**
	 * Method findExoneradotipoEntities.
	 * 
	 * @param all
	 *            boolean
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Exoneradotipo>
	 */
	private List<Exoneradotipo> findExoneradotipoEntities(boolean all,
			int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Exoneradotipo.class));
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
	 * Method findExoneradotipo.
	 * 
	 * @param id
	 *            Long
	 * @return Exoneradotipo
	 */
	public Exoneradotipo findExoneradotipo(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Exoneradotipo.class, id);
		} finally {
			em.close();
		}
	}

	/**
	 * Method getExoneradotipoCount.
	 * 
	 * @return int
	 */
	public int getExoneradotipoCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Exoneradotipo> rt = cq.from(Exoneradotipo.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}
