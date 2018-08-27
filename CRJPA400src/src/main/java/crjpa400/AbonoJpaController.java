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
public class AbonoJpaController implements Serializable {

	/**
	 * Constructor for AbonoJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public AbonoJpaController(EntityManagerFactory emf) {
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
	 * @param abono
	 *            Abono
	 * @throws PreexistingEntityException
	 * @throws Exception
	 */
	public void create(Abono abono) throws PreexistingEntityException,
			Exception {
		if (abono.getAbonoList() == null) {
			abono.setAbonoList(new ArrayList<Abono>());
		}
		if (abono.getAbonoformadepagoList() == null) {
			abono.setAbonoformadepagoList(new ArrayList<Abonoformadepago>());
		}
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Ordendeventa idOrdendeventa = abono.getIdOrdendeventa();
			if (idOrdendeventa != null) {
				idOrdendeventa = em.getReference(idOrdendeventa.getClass(),
						idOrdendeventa.getId());
				abono.setIdOrdendeventa(idOrdendeventa);
			}
			Usuario idUsuario = abono.getIdUsuario();
			if (idUsuario != null) {
				idUsuario = em.getReference(idUsuario.getClass(),
						idUsuario.getId());
				abono.setIdUsuario(idUsuario);
			}
			Sesion idSesion = abono.getIdSesion();
			if (idSesion != null) {
				idSesion = em.getReference(idSesion.getClass(),
						idSesion.getId());
				abono.setIdSesion(idSesion);
			}
			Abono idAnulacionabono = abono.getIdAnulacionabono();
			if (idAnulacionabono != null) {
				idAnulacionabono = em.getReference(idAnulacionabono.getClass(),
						idAnulacionabono.getId());
				abono.setIdAnulacionabono(idAnulacionabono);
			}
			List<Abono> attachedAbonoList = new ArrayList<Abono>();
			for (Abono abonoListAbonoToAttach : abono.getAbonoList()) {
				abonoListAbonoToAttach = em.getReference(
						abonoListAbonoToAttach.getClass(),
						abonoListAbonoToAttach.getId());
				attachedAbonoList.add(abonoListAbonoToAttach);
			}
			abono.setAbonoList(attachedAbonoList);
			List<Abonoformadepago> attachedAbonoformadepagoList = new ArrayList<Abonoformadepago>();
			for (Abonoformadepago abonoformadepagoListAbonoformadepagoToAttach : abono
					.getAbonoformadepagoList()) {
				abonoformadepagoListAbonoformadepagoToAttach = em
						.getReference(
								abonoformadepagoListAbonoformadepagoToAttach
										.getClass(),
								abonoformadepagoListAbonoformadepagoToAttach
										.getId());
				attachedAbonoformadepagoList
						.add(abonoformadepagoListAbonoformadepagoToAttach);
			}
			abono.setAbonoformadepagoList(attachedAbonoformadepagoList);
			em.persist(abono);
			if (idOrdendeventa != null) {
				idOrdendeventa.getAbonoList().add(abono);
				idOrdendeventa = em.merge(idOrdendeventa);
			}
			if (idUsuario != null) {
				idUsuario.getAbonoList().add(abono);
				idUsuario = em.merge(idUsuario);
			}
			if (idSesion != null) {
				idSesion.getAbonoList().add(abono);
				idSesion = em.merge(idSesion);
			}
			if (idAnulacionabono != null) {
				idAnulacionabono.getAbonoList().add(abono);
				idAnulacionabono = em.merge(idAnulacionabono);
			}
			for (Abono abonoListAbono : abono.getAbonoList()) {
				Abono oldIdAnulacionabonoOfAbonoListAbono = abonoListAbono
						.getIdAnulacionabono();
				abonoListAbono.setIdAnulacionabono(abono);
				abonoListAbono = em.merge(abonoListAbono);
				if (oldIdAnulacionabonoOfAbonoListAbono != null) {
					oldIdAnulacionabonoOfAbonoListAbono.getAbonoList().remove(
							abonoListAbono);
					oldIdAnulacionabonoOfAbonoListAbono = em
							.merge(oldIdAnulacionabonoOfAbonoListAbono);
				}
			}
			for (Abonoformadepago abonoformadepagoListAbonoformadepago : abono
					.getAbonoformadepagoList()) {
				Abono oldIdAbonoOfAbonoformadepagoListAbonoformadepago = abonoformadepagoListAbonoformadepago
						.getIdAbono();
				abonoformadepagoListAbonoformadepago.setIdAbono(abono);
				abonoformadepagoListAbonoformadepago = em
						.merge(abonoformadepagoListAbonoformadepago);
				if (oldIdAbonoOfAbonoformadepagoListAbonoformadepago != null) {
					oldIdAbonoOfAbonoformadepagoListAbonoformadepago
							.getAbonoformadepagoList().remove(
									abonoformadepagoListAbonoformadepago);
					oldIdAbonoOfAbonoformadepagoListAbonoformadepago = em
							.merge(oldIdAbonoOfAbonoformadepagoListAbonoformadepago);
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findAbono(abono.getId()) != null) {
				throw new PreexistingEntityException("Abono " + abono
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
	 * @param abono
	 *            Abono
	 * @throws IllegalOrphanException
	 * @throws NonexistentEntityException
	 * @throws Exception
	 */
	public void edit(Abono abono) throws IllegalOrphanException,
			NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Abono persistentAbono = em.find(Abono.class, abono.getId());
			Ordendeventa idOrdendeventaOld = persistentAbono
					.getIdOrdendeventa();
			Ordendeventa idOrdendeventaNew = abono.getIdOrdendeventa();
			Usuario idUsuarioOld = persistentAbono.getIdUsuario();
			Usuario idUsuarioNew = abono.getIdUsuario();
			Sesion idSesionOld = persistentAbono.getIdSesion();
			Sesion idSesionNew = abono.getIdSesion();
			Abono idAnulacionabonoOld = persistentAbono.getIdAnulacionabono();
			Abono idAnulacionabonoNew = abono.getIdAnulacionabono();
			List<Abono> abonoListOld = persistentAbono.getAbonoList();
			List<Abono> abonoListNew = abono.getAbonoList();
			List<Abonoformadepago> abonoformadepagoListOld = persistentAbono
					.getAbonoformadepagoList();
			List<Abonoformadepago> abonoformadepagoListNew = abono
					.getAbonoformadepagoList();
			List<String> illegalOrphanMessages = null;
			for (Abonoformadepago abonoformadepagoListOldAbonoformadepago : abonoformadepagoListOld) {
				if (!abonoformadepagoListNew
						.contains(abonoformadepagoListOldAbonoformadepago)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages
							.add("You must retain Abonoformadepago "
									+ abonoformadepagoListOldAbonoformadepago
									+ " since its idAbono field is not nullable.");
				}
			}
			if (illegalOrphanMessages != null) {
				throw new IllegalOrphanException(illegalOrphanMessages);
			}
			if (idOrdendeventaNew != null) {
				idOrdendeventaNew = em
						.getReference(idOrdendeventaNew.getClass(),
								idOrdendeventaNew.getId());
				abono.setIdOrdendeventa(idOrdendeventaNew);
			}
			if (idUsuarioNew != null) {
				idUsuarioNew = em.getReference(idUsuarioNew.getClass(),
						idUsuarioNew.getId());
				abono.setIdUsuario(idUsuarioNew);
			}
			if (idSesionNew != null) {
				idSesionNew = em.getReference(idSesionNew.getClass(),
						idSesionNew.getId());
				abono.setIdSesion(idSesionNew);
			}
			if (idAnulacionabonoNew != null) {
				idAnulacionabonoNew = em.getReference(
						idAnulacionabonoNew.getClass(),
						idAnulacionabonoNew.getId());
				abono.setIdAnulacionabono(idAnulacionabonoNew);
			}
			List<Abono> attachedAbonoListNew = new ArrayList<Abono>();
			for (Abono abonoListNewAbonoToAttach : abonoListNew) {
				abonoListNewAbonoToAttach = em.getReference(
						abonoListNewAbonoToAttach.getClass(),
						abonoListNewAbonoToAttach.getId());
				attachedAbonoListNew.add(abonoListNewAbonoToAttach);
			}
			abonoListNew = attachedAbonoListNew;
			abono.setAbonoList(abonoListNew);
			List<Abonoformadepago> attachedAbonoformadepagoListNew = new ArrayList<Abonoformadepago>();
			for (Abonoformadepago abonoformadepagoListNewAbonoformadepagoToAttach : abonoformadepagoListNew) {
				abonoformadepagoListNewAbonoformadepagoToAttach = em
						.getReference(
								abonoformadepagoListNewAbonoformadepagoToAttach
										.getClass(),
								abonoformadepagoListNewAbonoformadepagoToAttach
										.getId());
				attachedAbonoformadepagoListNew
						.add(abonoformadepagoListNewAbonoformadepagoToAttach);
			}
			abonoformadepagoListNew = attachedAbonoformadepagoListNew;
			abono.setAbonoformadepagoList(abonoformadepagoListNew);
			abono = em.merge(abono);
			if (idOrdendeventaOld != null
					&& !idOrdendeventaOld.equals(idOrdendeventaNew)) {
				idOrdendeventaOld.getAbonoList().remove(abono);
				idOrdendeventaOld = em.merge(idOrdendeventaOld);
			}
			if (idOrdendeventaNew != null
					&& !idOrdendeventaNew.equals(idOrdendeventaOld)) {
				idOrdendeventaNew.getAbonoList().add(abono);
				idOrdendeventaNew = em.merge(idOrdendeventaNew);
			}
			if (idUsuarioOld != null && !idUsuarioOld.equals(idUsuarioNew)) {
				idUsuarioOld.getAbonoList().remove(abono);
				idUsuarioOld = em.merge(idUsuarioOld);
			}
			if (idUsuarioNew != null && !idUsuarioNew.equals(idUsuarioOld)) {
				idUsuarioNew.getAbonoList().add(abono);
				idUsuarioNew = em.merge(idUsuarioNew);
			}
			if (idSesionOld != null && !idSesionOld.equals(idSesionNew)) {
				idSesionOld.getAbonoList().remove(abono);
				idSesionOld = em.merge(idSesionOld);
			}
			if (idSesionNew != null && !idSesionNew.equals(idSesionOld)) {
				idSesionNew.getAbonoList().add(abono);
				idSesionNew = em.merge(idSesionNew);
			}
			if (idAnulacionabonoOld != null
					&& !idAnulacionabonoOld.equals(idAnulacionabonoNew)) {
				idAnulacionabonoOld.getAbonoList().remove(abono);
				idAnulacionabonoOld = em.merge(idAnulacionabonoOld);
			}
			if (idAnulacionabonoNew != null
					&& !idAnulacionabonoNew.equals(idAnulacionabonoOld)) {
				idAnulacionabonoNew.getAbonoList().add(abono);
				idAnulacionabonoNew = em.merge(idAnulacionabonoNew);
			}
			for (Abono abonoListOldAbono : abonoListOld) {
				if (!abonoListNew.contains(abonoListOldAbono)) {
					abonoListOldAbono.setIdAnulacionabono(null);
					abonoListOldAbono = em.merge(abonoListOldAbono);
				}
			}
			for (Abono abonoListNewAbono : abonoListNew) {
				if (!abonoListOld.contains(abonoListNewAbono)) {
					Abono oldIdAnulacionabonoOfAbonoListNewAbono = abonoListNewAbono
							.getIdAnulacionabono();
					abonoListNewAbono.setIdAnulacionabono(abono);
					abonoListNewAbono = em.merge(abonoListNewAbono);
					if (oldIdAnulacionabonoOfAbonoListNewAbono != null
							&& !oldIdAnulacionabonoOfAbonoListNewAbono
									.equals(abono)) {
						oldIdAnulacionabonoOfAbonoListNewAbono.getAbonoList()
								.remove(abonoListNewAbono);
						oldIdAnulacionabonoOfAbonoListNewAbono = em
								.merge(oldIdAnulacionabonoOfAbonoListNewAbono);
					}
				}
			}
			for (Abonoformadepago abonoformadepagoListNewAbonoformadepago : abonoformadepagoListNew) {
				if (!abonoformadepagoListOld
						.contains(abonoformadepagoListNewAbonoformadepago)) {
					Abono oldIdAbonoOfAbonoformadepagoListNewAbonoformadepago = abonoformadepagoListNewAbonoformadepago
							.getIdAbono();
					abonoformadepagoListNewAbonoformadepago.setIdAbono(abono);
					abonoformadepagoListNewAbonoformadepago = em
							.merge(abonoformadepagoListNewAbonoformadepago);
					if (oldIdAbonoOfAbonoformadepagoListNewAbonoformadepago != null
							&& !oldIdAbonoOfAbonoformadepagoListNewAbonoformadepago
									.equals(abono)) {
						oldIdAbonoOfAbonoformadepagoListNewAbonoformadepago
								.getAbonoformadepagoList()
								.remove(abonoformadepagoListNewAbonoformadepago);
						oldIdAbonoOfAbonoformadepagoListNewAbonoformadepago = em
								.merge(oldIdAbonoOfAbonoformadepagoListNewAbonoformadepago);
					}
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = abono.getId();
				if (findAbono(id) == null) {
					throw new NonexistentEntityException("The abono with id "
							+ id + " no longer exists.");
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
			Abono abono;
			try {
				abono = em.getReference(Abono.class, id);
				abono.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException("The abono with id " + id
						+ " no longer exists.", enfe);
			}
			List<String> illegalOrphanMessages = null;
			List<Abonoformadepago> abonoformadepagoListOrphanCheck = abono
					.getAbonoformadepagoList();
			for (Abonoformadepago abonoformadepagoListOrphanCheckAbonoformadepago : abonoformadepagoListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages
						.add("This Abono ("
								+ abono
								+ ") cannot be destroyed since the Abonoformadepago "
								+ abonoformadepagoListOrphanCheckAbonoformadepago
								+ " in its abonoformadepagoList field has a non-nullable idAbono field.");
			}
			if (illegalOrphanMessages != null) {
				throw new IllegalOrphanException(illegalOrphanMessages);
			}
			Ordendeventa idOrdendeventa = abono.getIdOrdendeventa();
			if (idOrdendeventa != null) {
				idOrdendeventa.getAbonoList().remove(abono);
				idOrdendeventa = em.merge(idOrdendeventa);
			}
			Usuario idUsuario = abono.getIdUsuario();
			if (idUsuario != null) {
				idUsuario.getAbonoList().remove(abono);
				idUsuario = em.merge(idUsuario);
			}
			Sesion idSesion = abono.getIdSesion();
			if (idSesion != null) {
				idSesion.getAbonoList().remove(abono);
				idSesion = em.merge(idSesion);
			}
			Abono idAnulacionabono = abono.getIdAnulacionabono();
			if (idAnulacionabono != null) {
				idAnulacionabono.getAbonoList().remove(abono);
				idAnulacionabono = em.merge(idAnulacionabono);
			}
			List<Abono> abonoList = abono.getAbonoList();
			for (Abono abonoListAbono : abonoList) {
				abonoListAbono.setIdAnulacionabono(null);
				abonoListAbono = em.merge(abonoListAbono);
			}
			em.remove(abono);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method findAbonoEntities.
	 * 
	 * @return List<Abono>
	 */
	public List<Abono> findAbonoEntities() {
		return findAbonoEntities(true, -1, -1);
	}

	/**
	 * Method findAbonoEntities.
	 * 
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Abono>
	 */
	public List<Abono> findAbonoEntities(int maxResults, int firstResult) {
		return findAbonoEntities(false, maxResults, firstResult);
	}

	/**
	 * Method findAbonoEntities.
	 * 
	 * @param all
	 *            boolean
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Abono>
	 */
	private List<Abono> findAbonoEntities(boolean all, int maxResults,
			int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Abono.class));
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
	 * Method findAbono.
	 * 
	 * @param id
	 *            Long
	 * @return Abono
	 */
	public Abono findAbono(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Abono.class, id);
		} finally {
			em.close();
		}
	}

	/**
	 * Method getAbonoCount.
	 * 
	 * @return int
	 */
	public int getAbonoCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Abono> rt = cq.from(Abono.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}
