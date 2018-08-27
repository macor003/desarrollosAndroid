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
public class GiroactividadeconomicaJpaController implements Serializable {

	/**
	 * Constructor for GiroactividadeconomicaJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public GiroactividadeconomicaJpaController(EntityManagerFactory emf) {
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
	 * @param giroactividadeconomica
	 *            Giroactividadeconomica
	 * @throws PreexistingEntityException
	 * @throws Exception
	 */
	public void create(Giroactividadeconomica giroactividadeconomica) throws PreexistingEntityException, Exception {
		if (giroactividadeconomica.getTransaccionclienteList() == null) {
			giroactividadeconomica.setTransaccionclienteList(new ArrayList<Transaccioncliente>());
		}
		if (giroactividadeconomica.getClienteList() == null) {
			giroactividadeconomica.setClienteList(new ArrayList<Cliente>());
		}
		if (giroactividadeconomica.getCategorialineaincluyeList() == null) {
			giroactividadeconomica.setCategorialineaincluyeList(new ArrayList<Categorialineaincluye>());
		}
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			List<Transaccioncliente> attachedTransaccionclienteList = new ArrayList<Transaccioncliente>();
			for (Transaccioncliente transaccionclienteListTransaccionclienteToAttach : giroactividadeconomica
					.getTransaccionclienteList()) {
				transaccionclienteListTransaccionclienteToAttach = em.getReference(
						transaccionclienteListTransaccionclienteToAttach.getClass(),
						transaccionclienteListTransaccionclienteToAttach.getId());
				attachedTransaccionclienteList.add(transaccionclienteListTransaccionclienteToAttach);
			}
			giroactividadeconomica.setTransaccionclienteList(attachedTransaccionclienteList);
			List<Cliente> attachedClienteList = new ArrayList<Cliente>();
			for (Cliente clienteListClienteToAttach : giroactividadeconomica.getClienteList()) {
				clienteListClienteToAttach = em.getReference(clienteListClienteToAttach.getClass(),
						clienteListClienteToAttach.getNumeroIdentificacionCliente());
				attachedClienteList.add(clienteListClienteToAttach);
			}
			giroactividadeconomica.setClienteList(attachedClienteList);
			List<Categorialineaincluye> attachedCategorialineaincluyeList = new ArrayList<Categorialineaincluye>();
			for (Categorialineaincluye categorialineaincluyeListCategorialineaincluyeToAttach : giroactividadeconomica
					.getCategorialineaincluyeList()) {
				categorialineaincluyeListCategorialineaincluyeToAttach = em.getReference(
						categorialineaincluyeListCategorialineaincluyeToAttach.getClass(),
						categorialineaincluyeListCategorialineaincluyeToAttach.getId());
				attachedCategorialineaincluyeList.add(categorialineaincluyeListCategorialineaincluyeToAttach);
			}
			giroactividadeconomica.setCategorialineaincluyeList(attachedCategorialineaincluyeList);
			em.persist(giroactividadeconomica);
			for (Transaccioncliente transaccionclienteListTransaccioncliente : giroactividadeconomica
					.getTransaccionclienteList()) {
				Giroactividadeconomica oldIdGiroactividadeconomicaOfTransaccionclienteListTransaccioncliente = transaccionclienteListTransaccioncliente
						.getIdGiroactividadeconomica();
				transaccionclienteListTransaccioncliente.setIdGiroactividadeconomica(giroactividadeconomica);
				transaccionclienteListTransaccioncliente = em.merge(transaccionclienteListTransaccioncliente);
				if (oldIdGiroactividadeconomicaOfTransaccionclienteListTransaccioncliente != null) {
					oldIdGiroactividadeconomicaOfTransaccionclienteListTransaccioncliente.getTransaccionclienteList()
							.remove(transaccionclienteListTransaccioncliente);
					oldIdGiroactividadeconomicaOfTransaccionclienteListTransaccioncliente = em
							.merge(oldIdGiroactividadeconomicaOfTransaccionclienteListTransaccioncliente);
				}
			}
			for (Cliente clienteListCliente : giroactividadeconomica.getClienteList()) {
				Giroactividadeconomica oldIdGiroactividadeconomicaOfClienteListCliente = clienteListCliente
						.getIdGiroactividadeconomica();
				clienteListCliente.setIdGiroactividadeconomica(giroactividadeconomica);
				clienteListCliente = em.merge(clienteListCliente);
				if (oldIdGiroactividadeconomicaOfClienteListCliente != null) {
					oldIdGiroactividadeconomicaOfClienteListCliente.getClienteList().remove(clienteListCliente);
					oldIdGiroactividadeconomicaOfClienteListCliente = em
							.merge(oldIdGiroactividadeconomicaOfClienteListCliente);
				}
			}
			for (Categorialineaincluye categorialineaincluyeListCategorialineaincluye : giroactividadeconomica
					.getCategorialineaincluyeList()) {
				Giroactividadeconomica oldIdGiroactividadeconomicaOfCategorialineaincluyeListCategorialineaincluye = categorialineaincluyeListCategorialineaincluye
						.getIdGiroactividadeconomica();
				categorialineaincluyeListCategorialineaincluye.setIdGiroactividadeconomica(giroactividadeconomica);
				categorialineaincluyeListCategorialineaincluye = em
						.merge(categorialineaincluyeListCategorialineaincluye);
				if (oldIdGiroactividadeconomicaOfCategorialineaincluyeListCategorialineaincluye != null) {
					oldIdGiroactividadeconomicaOfCategorialineaincluyeListCategorialineaincluye
							.getCategorialineaincluyeList().remove(categorialineaincluyeListCategorialineaincluye);
					oldIdGiroactividadeconomicaOfCategorialineaincluyeListCategorialineaincluye = em
							.merge(oldIdGiroactividadeconomicaOfCategorialineaincluyeListCategorialineaincluye);
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findGiroactividadeconomica(giroactividadeconomica.getId()) != null) {
				throw new PreexistingEntityException("Giroactividadeconomica " + giroactividadeconomica
						+ " already exists.", ex);
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
	 * @param giroactividadeconomica
	 *            Giroactividadeconomica
	 * @throws IllegalOrphanException
	 * @throws NonexistentEntityException
	 * @throws Exception
	 */
	public void edit(Giroactividadeconomica giroactividadeconomica) throws IllegalOrphanException,
			NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Giroactividadeconomica persistentGiroactividadeconomica = em.find(Giroactividadeconomica.class,
					giroactividadeconomica.getId());
			List<Transaccioncliente> transaccionclienteListOld = persistentGiroactividadeconomica
					.getTransaccionclienteList();
			List<Transaccioncliente> transaccionclienteListNew = giroactividadeconomica.getTransaccionclienteList();
			List<Cliente> clienteListOld = persistentGiroactividadeconomica.getClienteList();
			List<Cliente> clienteListNew = giroactividadeconomica.getClienteList();
			List<Categorialineaincluye> categorialineaincluyeListOld = persistentGiroactividadeconomica
					.getCategorialineaincluyeList();
			List<Categorialineaincluye> categorialineaincluyeListNew = giroactividadeconomica
					.getCategorialineaincluyeList();
			List<String> illegalOrphanMessages = null;
			for (Categorialineaincluye categorialineaincluyeListOldCategorialineaincluye : categorialineaincluyeListOld) {
				if (!categorialineaincluyeListNew.contains(categorialineaincluyeListOldCategorialineaincluye)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages.add("You must retain Categorialineaincluye "
							+ categorialineaincluyeListOldCategorialineaincluye
							+ " since its idGiroactividadeconomica field is not nullable.");
				}
			}
			if (illegalOrphanMessages != null) {
				throw new IllegalOrphanException(illegalOrphanMessages);
			}
			List<Transaccioncliente> attachedTransaccionclienteListNew = new ArrayList<Transaccioncliente>();
			for (Transaccioncliente transaccionclienteListNewTransaccionclienteToAttach : transaccionclienteListNew) {
				transaccionclienteListNewTransaccionclienteToAttach = em.getReference(
						transaccionclienteListNewTransaccionclienteToAttach.getClass(),
						transaccionclienteListNewTransaccionclienteToAttach.getId());
				attachedTransaccionclienteListNew.add(transaccionclienteListNewTransaccionclienteToAttach);
			}
			transaccionclienteListNew = attachedTransaccionclienteListNew;
			giroactividadeconomica.setTransaccionclienteList(transaccionclienteListNew);
			List<Cliente> attachedClienteListNew = new ArrayList<Cliente>();
			for (Cliente clienteListNewClienteToAttach : clienteListNew) {
				clienteListNewClienteToAttach = em.getReference(clienteListNewClienteToAttach.getClass(),
						clienteListNewClienteToAttach.getNumeroIdentificacionCliente());
				attachedClienteListNew.add(clienteListNewClienteToAttach);
			}
			clienteListNew = attachedClienteListNew;
			giroactividadeconomica.setClienteList(clienteListNew);
			List<Categorialineaincluye> attachedCategorialineaincluyeListNew = new ArrayList<Categorialineaincluye>();
			for (Categorialineaincluye categorialineaincluyeListNewCategorialineaincluyeToAttach : categorialineaincluyeListNew) {
				categorialineaincluyeListNewCategorialineaincluyeToAttach = em.getReference(
						categorialineaincluyeListNewCategorialineaincluyeToAttach.getClass(),
						categorialineaincluyeListNewCategorialineaincluyeToAttach.getId());
				attachedCategorialineaincluyeListNew.add(categorialineaincluyeListNewCategorialineaincluyeToAttach);
			}
			categorialineaincluyeListNew = attachedCategorialineaincluyeListNew;
			giroactividadeconomica.setCategorialineaincluyeList(categorialineaincluyeListNew);
			giroactividadeconomica = em.merge(giroactividadeconomica);
			for (Transaccioncliente transaccionclienteListOldTransaccioncliente : transaccionclienteListOld) {
				if (!transaccionclienteListNew.contains(transaccionclienteListOldTransaccioncliente)) {
					transaccionclienteListOldTransaccioncliente.setIdGiroactividadeconomica(null);
					transaccionclienteListOldTransaccioncliente = em.merge(transaccionclienteListOldTransaccioncliente);
				}
			}
			for (Transaccioncliente transaccionclienteListNewTransaccioncliente : transaccionclienteListNew) {
				if (!transaccionclienteListOld.contains(transaccionclienteListNewTransaccioncliente)) {
					Giroactividadeconomica oldIdGiroactividadeconomicaOfTransaccionclienteListNewTransaccioncliente = transaccionclienteListNewTransaccioncliente
							.getIdGiroactividadeconomica();
					transaccionclienteListNewTransaccioncliente.setIdGiroactividadeconomica(giroactividadeconomica);
					transaccionclienteListNewTransaccioncliente = em.merge(transaccionclienteListNewTransaccioncliente);
					if (oldIdGiroactividadeconomicaOfTransaccionclienteListNewTransaccioncliente != null
							&& !oldIdGiroactividadeconomicaOfTransaccionclienteListNewTransaccioncliente
									.equals(giroactividadeconomica)) {
						oldIdGiroactividadeconomicaOfTransaccionclienteListNewTransaccioncliente
								.getTransaccionclienteList().remove(transaccionclienteListNewTransaccioncliente);
						oldIdGiroactividadeconomicaOfTransaccionclienteListNewTransaccioncliente = em
								.merge(oldIdGiroactividadeconomicaOfTransaccionclienteListNewTransaccioncliente);
					}
				}
			}
			for (Cliente clienteListOldCliente : clienteListOld) {
				if (!clienteListNew.contains(clienteListOldCliente)) {
					clienteListOldCliente.setIdGiroactividadeconomica(null);
					clienteListOldCliente = em.merge(clienteListOldCliente);
				}
			}
			for (Cliente clienteListNewCliente : clienteListNew) {
				if (!clienteListOld.contains(clienteListNewCliente)) {
					Giroactividadeconomica oldIdGiroactividadeconomicaOfClienteListNewCliente = clienteListNewCliente
							.getIdGiroactividadeconomica();
					clienteListNewCliente.setIdGiroactividadeconomica(giroactividadeconomica);
					clienteListNewCliente = em.merge(clienteListNewCliente);
					if (oldIdGiroactividadeconomicaOfClienteListNewCliente != null
							&& !oldIdGiroactividadeconomicaOfClienteListNewCliente.equals(giroactividadeconomica)) {
						oldIdGiroactividadeconomicaOfClienteListNewCliente.getClienteList().remove(
								clienteListNewCliente);
						oldIdGiroactividadeconomicaOfClienteListNewCliente = em
								.merge(oldIdGiroactividadeconomicaOfClienteListNewCliente);
					}
				}
			}
			for (Categorialineaincluye categorialineaincluyeListNewCategorialineaincluye : categorialineaincluyeListNew) {
				if (!categorialineaincluyeListOld.contains(categorialineaincluyeListNewCategorialineaincluye)) {
					Giroactividadeconomica oldIdGiroactividadeconomicaOfCategorialineaincluyeListNewCategorialineaincluye = categorialineaincluyeListNewCategorialineaincluye
							.getIdGiroactividadeconomica();
					categorialineaincluyeListNewCategorialineaincluye
							.setIdGiroactividadeconomica(giroactividadeconomica);
					categorialineaincluyeListNewCategorialineaincluye = em
							.merge(categorialineaincluyeListNewCategorialineaincluye);
					if (oldIdGiroactividadeconomicaOfCategorialineaincluyeListNewCategorialineaincluye != null
							&& !oldIdGiroactividadeconomicaOfCategorialineaincluyeListNewCategorialineaincluye
									.equals(giroactividadeconomica)) {
						oldIdGiroactividadeconomicaOfCategorialineaincluyeListNewCategorialineaincluye
								.getCategorialineaincluyeList().remove(
										categorialineaincluyeListNewCategorialineaincluye);
						oldIdGiroactividadeconomicaOfCategorialineaincluyeListNewCategorialineaincluye = em
								.merge(oldIdGiroactividadeconomicaOfCategorialineaincluyeListNewCategorialineaincluye);
					}
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = giroactividadeconomica.getId();
				if (findGiroactividadeconomica(id) == null) {
					throw new NonexistentEntityException("The giroactividadeconomica with id " + id
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
	public void destroy(Long id) throws IllegalOrphanException, NonexistentEntityException {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Giroactividadeconomica giroactividadeconomica;
			try {
				giroactividadeconomica = em.getReference(Giroactividadeconomica.class, id);
				giroactividadeconomica.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException("The giroactividadeconomica with id " + id + " no longer exists.",
						enfe);
			}
			List<String> illegalOrphanMessages = null;
			List<Categorialineaincluye> categorialineaincluyeListOrphanCheck = giroactividadeconomica
					.getCategorialineaincluyeList();
			for (Categorialineaincluye categorialineaincluyeListOrphanCheckCategorialineaincluye : categorialineaincluyeListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages.add("This Giroactividadeconomica (" + giroactividadeconomica
						+ ") cannot be destroyed since the Categorialineaincluye "
						+ categorialineaincluyeListOrphanCheckCategorialineaincluye
						+ " in its categorialineaincluyeList field has a non-nullable idGiroactividadeconomica field.");
			}
			if (illegalOrphanMessages != null) {
				throw new IllegalOrphanException(illegalOrphanMessages);
			}
			List<Transaccioncliente> transaccionclienteList = giroactividadeconomica.getTransaccionclienteList();
			for (Transaccioncliente transaccionclienteListTransaccioncliente : transaccionclienteList) {
				transaccionclienteListTransaccioncliente.setIdGiroactividadeconomica(null);
				transaccionclienteListTransaccioncliente = em.merge(transaccionclienteListTransaccioncliente);
			}
			List<Cliente> clienteList = giroactividadeconomica.getClienteList();
			for (Cliente clienteListCliente : clienteList) {
				clienteListCliente.setIdGiroactividadeconomica(null);
				clienteListCliente = em.merge(clienteListCliente);
			}
			em.remove(giroactividadeconomica);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method findGiroactividadeconomicaEntities.
	 * 
	 * @return List<Giroactividadeconomica>
	 */
	public List<Giroactividadeconomica> findGiroactividadeconomicaEntities() {
		return findGiroactividadeconomicaEntities(true, -1, -1);
	}

	/**
	 * Method findGiroactividadeconomicaEntities.
	 * 
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Giroactividadeconomica>
	 */
	public List<Giroactividadeconomica> findGiroactividadeconomicaEntities(int maxResults, int firstResult) {
		return findGiroactividadeconomicaEntities(false, maxResults, firstResult);
	}

	/**
	 * Method findGiroactividadeconomicaEntities.
	 * 
	 * @param all
	 *            boolean
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Giroactividadeconomica>
	 */
	private List<Giroactividadeconomica> findGiroactividadeconomicaEntities(boolean all, int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Giroactividadeconomica.class));
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
	 * Method findGiroactividadeconomica.
	 * 
	 * @param id
	 *            Long
	 * @return Giroactividadeconomica
	 */
	public Giroactividadeconomica findGiroactividadeconomica(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Giroactividadeconomica.class, id);
		} finally {
			em.close();
		}
	}

	/**
	 * Method getGiroactividadeconomicaCount.
	 * 
	 * @return int
	 */
	public int getGiroactividadeconomicaCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Giroactividadeconomica> rt = cq.from(Giroactividadeconomica.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}
