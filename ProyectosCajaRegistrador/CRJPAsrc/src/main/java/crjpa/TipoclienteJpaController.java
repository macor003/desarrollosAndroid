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
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import crjpa.exceptions.PreexistingEntityException;

/**
 * 
 * @author eve0014321
 * @version $Revision: 1.0 $
 */
public class TipoclienteJpaController implements Serializable {

	/**
	 * Constructor for TipoclienteJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public TipoclienteJpaController(EntityManagerFactory emf) {
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
	 * @param tipocliente
	 *            Tipocliente
	 * @throws PreexistingEntityException
	 * @throws Exception
	 */
	public void create(Tipocliente tipocliente) throws PreexistingEntityException, Exception {
		if (tipocliente.getTipoclientetipodocumentoList() == null) {
			tipocliente.setTipoclientetipodocumentoList(new ArrayList<Tipoclientetipodocumento>());
		}
		// if (tipocliente.getClienteList() == null) {
		// tipocliente.setClienteList(new ArrayList<Cliente>());
		// }
		if (tipocliente.getTipoidentificacionclienteList() == null) {
			tipocliente.setTipoidentificacionclienteList(new ArrayList<Tipoidentificacioncliente>());
		}
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			List<Tipoclientetipodocumento> attachedTipoclientetipodocumentoList = new ArrayList<Tipoclientetipodocumento>();
			for (Tipoclientetipodocumento tipoclientetipodocumentoListTipoclientetipodocumentoToAttach : tipocliente
					.getTipoclientetipodocumentoList()) {
				tipoclientetipodocumentoListTipoclientetipodocumentoToAttach = em.getReference(
						tipoclientetipodocumentoListTipoclientetipodocumentoToAttach.getClass(),
						tipoclientetipodocumentoListTipoclientetipodocumentoToAttach.getId());
				attachedTipoclientetipodocumentoList.add(tipoclientetipodocumentoListTipoclientetipodocumentoToAttach);
			}
			tipocliente.setTipoclientetipodocumentoList(attachedTipoclientetipodocumentoList);
			List<Cliente> attachedClienteList = new ArrayList<Cliente>();
			// for (Cliente clienteListClienteToAttach :
			// tipocliente.getClienteList()) {
			// clienteListClienteToAttach =
			// em.getReference(clienteListClienteToAttach.getClass(),
			// clienteListClienteToAttach.getNumeroIdentificacionCliente());
			// attachedClienteList.add(clienteListClienteToAttach);
			// }
			// tipocliente.setClienteList(attachedClienteList);
			List<Tipoidentificacioncliente> attachedTipoidentificacionclienteList = new ArrayList<Tipoidentificacioncliente>();
			for (Tipoidentificacioncliente tipoidentificacionclienteListTipoidentificacionclienteToAttach : tipocliente
					.getTipoidentificacionclienteList()) {
				tipoidentificacionclienteListTipoidentificacionclienteToAttach = em.getReference(
						tipoidentificacionclienteListTipoidentificacionclienteToAttach.getClass(),
						tipoidentificacionclienteListTipoidentificacionclienteToAttach.getId());
				attachedTipoidentificacionclienteList
						.add(tipoidentificacionclienteListTipoidentificacionclienteToAttach);
			}
			tipocliente.setTipoidentificacionclienteList(attachedTipoidentificacionclienteList);
			em.persist(tipocliente);
			for (Tipoclientetipodocumento tipoclientetipodocumentoListTipoclientetipodocumento : tipocliente
					.getTipoclientetipodocumentoList()) {
				Tipocliente oldIdTipoclienteOfTipoclientetipodocumentoListTipoclientetipodocumento = tipoclientetipodocumentoListTipoclientetipodocumento
						.getIdTipocliente();
				tipoclientetipodocumentoListTipoclientetipodocumento.setIdTipocliente(tipocliente);
				tipoclientetipodocumentoListTipoclientetipodocumento = em
						.merge(tipoclientetipodocumentoListTipoclientetipodocumento);
				if (oldIdTipoclienteOfTipoclientetipodocumentoListTipoclientetipodocumento != null) {
					oldIdTipoclienteOfTipoclientetipodocumentoListTipoclientetipodocumento
							.getTipoclientetipodocumentoList().remove(
									tipoclientetipodocumentoListTipoclientetipodocumento);
					oldIdTipoclienteOfTipoclientetipodocumentoListTipoclientetipodocumento = em
							.merge(oldIdTipoclienteOfTipoclientetipodocumentoListTipoclientetipodocumento);
				}
			}
			// for (Cliente clienteListCliente : tipocliente.getClienteList()) {
			// Tipocliente oldIdTipoclienteOfClienteListCliente =
			// clienteListCliente.getIdTipocliente();
			// clienteListCliente.setIdTipocliente(tipocliente);
			// clienteListCliente = em.merge(clienteListCliente);
			// if (oldIdTipoclienteOfClienteListCliente != null) {
			// oldIdTipoclienteOfClienteListCliente.getClienteList().remove(clienteListCliente);
			// oldIdTipoclienteOfClienteListCliente =
			// em.merge(oldIdTipoclienteOfClienteListCliente);
			// }
			// }
			for (Tipoidentificacioncliente tipoidentificacionclienteListTipoidentificacioncliente : tipocliente
					.getTipoidentificacionclienteList()) {
				Tipocliente oldIdTipoclienteOfTipoidentificacionclienteListTipoidentificacioncliente = tipoidentificacionclienteListTipoidentificacioncliente
						.getIdTipocliente();
				tipoidentificacionclienteListTipoidentificacioncliente.setIdTipocliente(tipocliente);
				tipoidentificacionclienteListTipoidentificacioncliente = em
						.merge(tipoidentificacionclienteListTipoidentificacioncliente);
				if (oldIdTipoclienteOfTipoidentificacionclienteListTipoidentificacioncliente != null) {
					oldIdTipoclienteOfTipoidentificacionclienteListTipoidentificacioncliente
							.getTipoidentificacionclienteList().remove(
									tipoidentificacionclienteListTipoidentificacioncliente);
					oldIdTipoclienteOfTipoidentificacionclienteListTipoidentificacioncliente = em
							.merge(oldIdTipoclienteOfTipoidentificacionclienteListTipoidentificacioncliente);
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findTipocliente(tipocliente.getId()) != null) {
				throw new PreexistingEntityException("Tipocliente " + tipocliente + " already exists.", ex);
			}
			throw ex;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	// public void edit(Tipocliente tipocliente) throws IllegalOrphanException,
	// NonexistentEntityException, Exception {
	// EntityManager em = null;
	// try {
	// em = getEntityManager();
	// em.getTransaction().begin();
	// Tipocliente persistentTipocliente = em.find(Tipocliente.class,
	// tipocliente.getId());
	// List<Tipoclientetipodocumento> tipoclientetipodocumentoListOld =
	// persistentTipocliente.getTipoclientetipodocumentoList();
	// List<Tipoclientetipodocumento> tipoclientetipodocumentoListNew =
	// tipocliente.getTipoclientetipodocumentoList();
	// // List<Cliente> clienteListOld = persistentTipocliente.getClienteList();
	// // List<Cliente> clienteListNew = tipocliente.getClienteList();
	// List<Tipoidentificacioncliente> tipoidentificacionclienteListOld =
	// persistentTipocliente.getTipoidentificacionclienteList();
	// List<Tipoidentificacioncliente> tipoidentificacionclienteListNew =
	// tipocliente.getTipoidentificacionclienteList();
	// List<String> illegalOrphanMessages = null;
	// for (Tipoclientetipodocumento
	// tipoclientetipodocumentoListOldTipoclientetipodocumento :
	// tipoclientetipodocumentoListOld) {
	// if
	// (!tipoclientetipodocumentoListNew.contains(tipoclientetipodocumentoListOldTipoclientetipodocumento))
	// {
	// if (illegalOrphanMessages == null) {
	// illegalOrphanMessages = new ArrayList<String>();
	// }
	// illegalOrphanMessages.add("You must retain Tipoclientetipodocumento " +
	// tipoclientetipodocumentoListOldTipoclientetipodocumento +
	// " since its idTipocliente field is not nullable.");
	// }
	// }
	// // for (Cliente clienteListOldCliente : clienteListOld) {
	// // if (!clienteListNew.contains(clienteListOldCliente)) {
	// // if (illegalOrphanMessages == null) {
	// // illegalOrphanMessages = new ArrayList<String>();
	// // }
	// // illegalOrphanMessages.add("You must retain Cliente " +
	// clienteListOldCliente +
	// " since its idTipocliente field is not nullable.");
	// // }
	// // }
	// for (Tipoidentificacioncliente
	// tipoidentificacionclienteListOldTipoidentificacioncliente :
	// tipoidentificacionclienteListOld) {
	// if
	// (!tipoidentificacionclienteListNew.contains(tipoidentificacionclienteListOldTipoidentificacioncliente))
	// {
	// if (illegalOrphanMessages == null) {
	// illegalOrphanMessages = new ArrayList<String>();
	// }
	// illegalOrphanMessages.add("You must retain Tipoidentificacioncliente " +
	// tipoidentificacionclienteListOldTipoidentificacioncliente +
	// " since its idTipocliente field is not nullable.");
	// }
	// }
	// if (illegalOrphanMessages != null) {
	// throw new IllegalOrphanException(illegalOrphanMessages);
	// }
	// List<Tipoclientetipodocumento> attachedTipoclientetipodocumentoListNew =
	// new ArrayList<Tipoclientetipodocumento>();
	// for (Tipoclientetipodocumento
	// tipoclientetipodocumentoListNewTipoclientetipodocumentoToAttach :
	// tipoclientetipodocumentoListNew) {
	// tipoclientetipodocumentoListNewTipoclientetipodocumentoToAttach =
	// em.getReference(tipoclientetipodocumentoListNewTipoclientetipodocumentoToAttach.getClass(),
	// tipoclientetipodocumentoListNewTipoclientetipodocumentoToAttach.getId());
	// attachedTipoclientetipodocumentoListNew.add(tipoclientetipodocumentoListNewTipoclientetipodocumentoToAttach);
	// }
	// tipoclientetipodocumentoListNew =
	// attachedTipoclientetipodocumentoListNew;
	// tipocliente.setTipoclientetipodocumentoList(tipoclientetipodocumentoListNew);
	// List<Cliente> attachedClienteListNew = new ArrayList<Cliente>();
	// // for (Cliente clienteListNewClienteToAttach : clienteListNew) {
	// // clienteListNewClienteToAttach =
	// em.getReference(clienteListNewClienteToAttach.getClass(),
	// clienteListNewClienteToAttach.getNumeroIdentificacionCliente());
	// // attachedClienteListNew.add(clienteListNewClienteToAttach);
	// // }
	// // clienteListNew = attachedClienteListNew;
	// // tipocliente.setClienteList(clienteListNew);
	// List<Tipoidentificacioncliente> attachedTipoidentificacionclienteListNew
	// = new ArrayList<Tipoidentificacioncliente>();
	// for (Tipoidentificacioncliente
	// tipoidentificacionclienteListNewTipoidentificacionclienteToAttach :
	// tipoidentificacionclienteListNew) {
	// tipoidentificacionclienteListNewTipoidentificacionclienteToAttach =
	// em.getReference(tipoidentificacionclienteListNewTipoidentificacionclienteToAttach.getClass(),
	// tipoidentificacionclienteListNewTipoidentificacionclienteToAttach.getId());
	// attachedTipoidentificacionclienteListNew.add(tipoidentificacionclienteListNewTipoidentificacionclienteToAttach);
	// }
	// tipoidentificacionclienteListNew =
	// attachedTipoidentificacionclienteListNew;
	// tipocliente.setTipoidentificacionclienteList(tipoidentificacionclienteListNew);
	// tipocliente = em.merge(tipocliente);
	// for (Tipoclientetipodocumento
	// tipoclientetipodocumentoListNewTipoclientetipodocumento :
	// tipoclientetipodocumentoListNew) {
	// if
	// (!tipoclientetipodocumentoListOld.contains(tipoclientetipodocumentoListNewTipoclientetipodocumento))
	// {
	// Tipocliente
	// oldIdTipoclienteOfTipoclientetipodocumentoListNewTipoclientetipodocumento
	// =
	// tipoclientetipodocumentoListNewTipoclientetipodocumento.getIdTipocliente();
	// tipoclientetipodocumentoListNewTipoclientetipodocumento.setIdTipocliente(tipocliente);
	// tipoclientetipodocumentoListNewTipoclientetipodocumento =
	// em.merge(tipoclientetipodocumentoListNewTipoclientetipodocumento);
	// if
	// (oldIdTipoclienteOfTipoclientetipodocumentoListNewTipoclientetipodocumento
	// != null &&
	// !oldIdTipoclienteOfTipoclientetipodocumentoListNewTipoclientetipodocumento.equals(tipocliente))
	// {
	// oldIdTipoclienteOfTipoclientetipodocumentoListNewTipoclientetipodocumento.getTipoclientetipodocumentoList().remove(tipoclientetipodocumentoListNewTipoclientetipodocumento);
	// oldIdTipoclienteOfTipoclientetipodocumentoListNewTipoclientetipodocumento
	// =
	// em.merge(oldIdTipoclienteOfTipoclientetipodocumentoListNewTipoclientetipodocumento);
	// }
	// }
	// }
	// for (Cliente clienteListNewCliente : clienteListNew) {
	// if (!clienteListOld.contains(clienteListNewCliente)) {
	// Tipocliente oldIdTipoclienteOfClienteListNewCliente =
	// clienteListNewCliente.getIdTipocliente();
	// clienteListNewCliente.setIdTipocliente(tipocliente);
	// clienteListNewCliente = em.merge(clienteListNewCliente);
	// if (oldIdTipoclienteOfClienteListNewCliente != null &&
	// !oldIdTipoclienteOfClienteListNewCliente.equals(tipocliente)) {
	// oldIdTipoclienteOfClienteListNewCliente.getClienteList().remove(clienteListNewCliente);
	// oldIdTipoclienteOfClienteListNewCliente =
	// em.merge(oldIdTipoclienteOfClienteListNewCliente);
	// }
	// }
	// }
	// for (Tipoidentificacioncliente
	// tipoidentificacionclienteListNewTipoidentificacioncliente :
	// tipoidentificacionclienteListNew) {
	// if
	// (!tipoidentificacionclienteListOld.contains(tipoidentificacionclienteListNewTipoidentificacioncliente))
	// {
	// Tipocliente
	// oldIdTipoclienteOfTipoidentificacionclienteListNewTipoidentificacioncliente
	// =
	// tipoidentificacionclienteListNewTipoidentificacioncliente.getIdTipocliente();
	// tipoidentificacionclienteListNewTipoidentificacioncliente.setIdTipocliente(tipocliente);
	// tipoidentificacionclienteListNewTipoidentificacioncliente =
	// em.merge(tipoidentificacionclienteListNewTipoidentificacioncliente);
	// if
	// (oldIdTipoclienteOfTipoidentificacionclienteListNewTipoidentificacioncliente
	// != null &&
	// !oldIdTipoclienteOfTipoidentificacionclienteListNewTipoidentificacioncliente.equals(tipocliente))
	// {
	// oldIdTipoclienteOfTipoidentificacionclienteListNewTipoidentificacioncliente.getTipoidentificacionclienteList().remove(tipoidentificacionclienteListNewTipoidentificacioncliente);
	// oldIdTipoclienteOfTipoidentificacionclienteListNewTipoidentificacioncliente
	// =
	// em.merge(oldIdTipoclienteOfTipoidentificacionclienteListNewTipoidentificacioncliente);
	// }
	// }
	// }
	// em.getTransaction().commit();
	// } catch (Exception ex) {
	// String msg = ex.getLocalizedMessage();
	// if (msg == null || msg.length() == 0) {
	// Long id = tipocliente.getId();
	// if (findTipocliente(id) == null) {
	// throw new NonexistentEntityException("The tipocliente with id " + id +
	// " no longer exists.");
	// }
	// }
	// throw ex;
	// } finally {
	// if (em != null) {
	// em.close();
	// }
	// }
	// }

	// public void destroy(Long id) throws IllegalOrphanException,
	// NonexistentEntityException {
	// EntityManager em = null;
	// try {
	// em = getEntityManager();
	// em.getTransaction().begin();
	// Tipocliente tipocliente;
	// try {
	// tipocliente = em.getReference(Tipocliente.class, id);
	// tipocliente.getId();
	// } catch (EntityNotFoundException enfe) {
	// throw new NonexistentEntityException("The tipocliente with id " + id +
	// " no longer exists.", enfe);
	// }
	// List<String> illegalOrphanMessages = null;
	// List<Tipoclientetipodocumento> tipoclientetipodocumentoListOrphanCheck =
	// tipocliente.getTipoclientetipodocumentoList();
	// for (Tipoclientetipodocumento
	// tipoclientetipodocumentoListOrphanCheckTipoclientetipodocumento :
	// tipoclientetipodocumentoListOrphanCheck) {
	// if (illegalOrphanMessages == null) {
	// illegalOrphanMessages = new ArrayList<String>();
	// }
	// illegalOrphanMessages.add("This Tipocliente (" + tipocliente +
	// ") cannot be destroyed since the Tipoclientetipodocumento " +
	// tipoclientetipodocumentoListOrphanCheckTipoclientetipodocumento +
	// " in its tipoclientetipodocumentoList field has a non-nullable idTipocliente field.");
	// }
	// List<Cliente> clienteListOrphanCheck = tipocliente.getClienteList();
	// for (Cliente clienteListOrphanCheckCliente : clienteListOrphanCheck) {
	// if (illegalOrphanMessages == null) {
	// illegalOrphanMessages = new ArrayList<String>();
	// }
	// illegalOrphanMessages.add("This Tipocliente (" + tipocliente +
	// ") cannot be destroyed since the Cliente " +
	// clienteListOrphanCheckCliente +
	// " in its clienteList field has a non-nullable idTipocliente field.");
	// }
	// List<Tipoidentificacioncliente> tipoidentificacionclienteListOrphanCheck
	// = tipocliente.getTipoidentificacionclienteList();
	// for (Tipoidentificacioncliente
	// tipoidentificacionclienteListOrphanCheckTipoidentificacioncliente :
	// tipoidentificacionclienteListOrphanCheck) {
	// if (illegalOrphanMessages == null) {
	// illegalOrphanMessages = new ArrayList<String>();
	// }
	// illegalOrphanMessages.add("This Tipocliente (" + tipocliente +
	// ") cannot be destroyed since the Tipoidentificacioncliente " +
	// tipoidentificacionclienteListOrphanCheckTipoidentificacioncliente +
	// " in its tipoidentificacionclienteList field has a non-nullable idTipocliente field.");
	// }
	// if (illegalOrphanMessages != null) {
	// throw new IllegalOrphanException(illegalOrphanMessages);
	// }
	// em.remove(tipocliente);
	// em.getTransaction().commit();
	// } finally {
	// if (em != null) {
	// em.close();
	// }
	// }
	// }

	/**
	 * Method findTipoclienteEntities.
	 * 
	 * @return List<Tipocliente>
	 */
	public List<Tipocliente> findTipoclienteEntities() {
		return findTipoclienteEntities(true, -1, -1);
	}

	/**
	 * Method findTipoclienteEntities.
	 * 
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Tipocliente>
	 */
	public List<Tipocliente> findTipoclienteEntities(int maxResults, int firstResult) {
		return findTipoclienteEntities(false, maxResults, firstResult);
	}

	/**
	 * Method findTipoclienteEntities.
	 * 
	 * @param all
	 *            boolean
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Tipocliente>
	 */
	private List<Tipocliente> findTipoclienteEntities(boolean all, int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Tipocliente.class));
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
	 * Method findTipocliente.
	 * 
	 * @param id
	 *            Long
	 * @return Tipocliente
	 */
	public Tipocliente findTipocliente(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Tipocliente.class, id);
		} finally {
			em.close();
		}
	}

	/**
	 * Method getTipoclienteCount.
	 * 
	 * @return int
	 */
	public int getTipoclienteCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Tipocliente> rt = cq.from(Tipocliente.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}
