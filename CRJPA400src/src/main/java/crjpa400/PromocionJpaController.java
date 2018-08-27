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
public class PromocionJpaController implements Serializable {

	/**
	 * Constructor for PromocionJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public PromocionJpaController(EntityManagerFactory emf) {
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
	 * @param promocion
	 *            Promocion
	 * @throws PreexistingEntityException
	 * @throws Exception
	 */
	public void create(Promocion promocion) throws PreexistingEntityException,
			Exception {
		if (promocion.getPromocionfamiliaList() == null) {
			promocion
					.setPromocionfamiliaList(new ArrayList<Promocionfamilia>());
		}
		if (promocion.getPromocioncategoriaList() == null) {
			promocion
					.setPromocioncategoriaList(new ArrayList<Promocioncategoria>());
		}
		if (promocion.getPromocionlineaList() == null) {
			promocion.setPromocionlineaList(new ArrayList<Promocionlinea>());
		}
		if (promocion.getTransaccionarticuloList() == null) {
			promocion
					.setTransaccionarticuloList(new ArrayList<Transaccionarticulo>());
		}
		if (promocion.getPromociontipodescuentoList() == null) {
			promocion
					.setPromociontipodescuentoList(new ArrayList<Promociontipodescuento>());
		}
		if (promocion.getPromocionarticuloList() == null) {
			promocion
					.setPromocionarticuloList(new ArrayList<Promocionarticulo>());
		}
		if (promocion.getPromocionformadepagoList() == null) {
			promocion
					.setPromocionformadepagoList(new ArrayList<Promocionformadepago>());
		}
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			List<Promocionfamilia> attachedPromocionfamiliaList = new ArrayList<Promocionfamilia>();
			for (Promocionfamilia promocionfamiliaListPromocionfamiliaToAttach : promocion
					.getPromocionfamiliaList()) {
				promocionfamiliaListPromocionfamiliaToAttach = em
						.getReference(
								promocionfamiliaListPromocionfamiliaToAttach
										.getClass(),
								promocionfamiliaListPromocionfamiliaToAttach
										.getId());
				attachedPromocionfamiliaList
						.add(promocionfamiliaListPromocionfamiliaToAttach);
			}
			promocion.setPromocionfamiliaList(attachedPromocionfamiliaList);
			List<Promocioncategoria> attachedPromocioncategoriaList = new ArrayList<Promocioncategoria>();
			for (Promocioncategoria promocioncategoriaListPromocioncategoriaToAttach : promocion
					.getPromocioncategoriaList()) {
				promocioncategoriaListPromocioncategoriaToAttach = em
						.getReference(
								promocioncategoriaListPromocioncategoriaToAttach
										.getClass(),
								promocioncategoriaListPromocioncategoriaToAttach
										.getId());
				attachedPromocioncategoriaList
						.add(promocioncategoriaListPromocioncategoriaToAttach);
			}
			promocion.setPromocioncategoriaList(attachedPromocioncategoriaList);
			List<Promocionlinea> attachedPromocionlineaList = new ArrayList<Promocionlinea>();
			for (Promocionlinea promocionlineaListPromocionlineaToAttach : promocion
					.getPromocionlineaList()) {
				promocionlineaListPromocionlineaToAttach = em.getReference(
						promocionlineaListPromocionlineaToAttach.getClass(),
						promocionlineaListPromocionlineaToAttach.getId());
				attachedPromocionlineaList
						.add(promocionlineaListPromocionlineaToAttach);
			}
			promocion.setPromocionlineaList(attachedPromocionlineaList);
			List<Transaccionarticulo> attachedTransaccionarticuloList = new ArrayList<Transaccionarticulo>();
			for (Transaccionarticulo transaccionarticuloListTransaccionarticuloToAttach : promocion
					.getTransaccionarticuloList()) {
				transaccionarticuloListTransaccionarticuloToAttach = em
						.getReference(
								transaccionarticuloListTransaccionarticuloToAttach
										.getClass(),
								transaccionarticuloListTransaccionarticuloToAttach
										.getId());
				attachedTransaccionarticuloList
						.add(transaccionarticuloListTransaccionarticuloToAttach);
			}
			promocion
					.setTransaccionarticuloList(attachedTransaccionarticuloList);
			List<Promociontipodescuento> attachedPromociontipodescuentoList = new ArrayList<Promociontipodescuento>();
			for (Promociontipodescuento promociontipodescuentoListPromociontipodescuentoToAttach : promocion
					.getPromociontipodescuentoList()) {
				promociontipodescuentoListPromociontipodescuentoToAttach = em
						.getReference(
								promociontipodescuentoListPromociontipodescuentoToAttach
										.getClass(),
								promociontipodescuentoListPromociontipodescuentoToAttach
										.getId());
				attachedPromociontipodescuentoList
						.add(promociontipodescuentoListPromociontipodescuentoToAttach);
			}
			promocion
					.setPromociontipodescuentoList(attachedPromociontipodescuentoList);
			List<Promocionarticulo> attachedPromocionarticuloList = new ArrayList<Promocionarticulo>();
			for (Promocionarticulo promocionarticuloListPromocionarticuloToAttach : promocion
					.getPromocionarticuloList()) {
				promocionarticuloListPromocionarticuloToAttach = em
						.getReference(
								promocionarticuloListPromocionarticuloToAttach
										.getClass(),
								promocionarticuloListPromocionarticuloToAttach
										.getId());
				attachedPromocionarticuloList
						.add(promocionarticuloListPromocionarticuloToAttach);
			}
			promocion.setPromocionarticuloList(attachedPromocionarticuloList);
			List<Promocionformadepago> attachedPromocionformadepagoList = new ArrayList<Promocionformadepago>();
			for (Promocionformadepago promocionformadepagoListPromocionformadepagoToAttach : promocion
					.getPromocionformadepagoList()) {
				promocionformadepagoListPromocionformadepagoToAttach = em
						.getReference(
								promocionformadepagoListPromocionformadepagoToAttach
										.getClass(),
								promocionformadepagoListPromocionformadepagoToAttach
										.getId());
				attachedPromocionformadepagoList
						.add(promocionformadepagoListPromocionformadepagoToAttach);
			}
			promocion
					.setPromocionformadepagoList(attachedPromocionformadepagoList);
			em.persist(promocion);
			for (Promocionfamilia promocionfamiliaListPromocionfamilia : promocion
					.getPromocionfamiliaList()) {
				Promocion oldIdPromocionOfPromocionfamiliaListPromocionfamilia = promocionfamiliaListPromocionfamilia
						.getIdPromocion();
				promocionfamiliaListPromocionfamilia.setIdPromocion(promocion);
				promocionfamiliaListPromocionfamilia = em
						.merge(promocionfamiliaListPromocionfamilia);
				if (oldIdPromocionOfPromocionfamiliaListPromocionfamilia != null) {
					oldIdPromocionOfPromocionfamiliaListPromocionfamilia
							.getPromocionfamiliaList().remove(
									promocionfamiliaListPromocionfamilia);
					oldIdPromocionOfPromocionfamiliaListPromocionfamilia = em
							.merge(oldIdPromocionOfPromocionfamiliaListPromocionfamilia);
				}
			}
			for (Promocioncategoria promocioncategoriaListPromocioncategoria : promocion
					.getPromocioncategoriaList()) {
				Promocion oldIdPromocionOfPromocioncategoriaListPromocioncategoria = promocioncategoriaListPromocioncategoria
						.getIdPromocion();
				promocioncategoriaListPromocioncategoria
						.setIdPromocion(promocion);
				promocioncategoriaListPromocioncategoria = em
						.merge(promocioncategoriaListPromocioncategoria);
				if (oldIdPromocionOfPromocioncategoriaListPromocioncategoria != null) {
					oldIdPromocionOfPromocioncategoriaListPromocioncategoria
							.getPromocioncategoriaList().remove(
									promocioncategoriaListPromocioncategoria);
					oldIdPromocionOfPromocioncategoriaListPromocioncategoria = em
							.merge(oldIdPromocionOfPromocioncategoriaListPromocioncategoria);
				}
			}
			for (Promocionlinea promocionlineaListPromocionlinea : promocion
					.getPromocionlineaList()) {
				Promocion oldIdPromocionOfPromocionlineaListPromocionlinea = promocionlineaListPromocionlinea
						.getIdPromocion();
				promocionlineaListPromocionlinea.setIdPromocion(promocion);
				promocionlineaListPromocionlinea = em
						.merge(promocionlineaListPromocionlinea);
				if (oldIdPromocionOfPromocionlineaListPromocionlinea != null) {
					oldIdPromocionOfPromocionlineaListPromocionlinea
							.getPromocionlineaList().remove(
									promocionlineaListPromocionlinea);
					oldIdPromocionOfPromocionlineaListPromocionlinea = em
							.merge(oldIdPromocionOfPromocionlineaListPromocionlinea);
				}
			}
			for (Transaccionarticulo transaccionarticuloListTransaccionarticulo : promocion
					.getTransaccionarticuloList()) {
				Promocion oldIdPromocionOfTransaccionarticuloListTransaccionarticulo = transaccionarticuloListTransaccionarticulo
						.getIdPromocion();
				transaccionarticuloListTransaccionarticulo
						.setIdPromocion(promocion);
				transaccionarticuloListTransaccionarticulo = em
						.merge(transaccionarticuloListTransaccionarticulo);
				if (oldIdPromocionOfTransaccionarticuloListTransaccionarticulo != null) {
					oldIdPromocionOfTransaccionarticuloListTransaccionarticulo
							.getTransaccionarticuloList().remove(
									transaccionarticuloListTransaccionarticulo);
					oldIdPromocionOfTransaccionarticuloListTransaccionarticulo = em
							.merge(oldIdPromocionOfTransaccionarticuloListTransaccionarticulo);
				}
			}
			for (Promociontipodescuento promociontipodescuentoListPromociontipodescuento : promocion
					.getPromociontipodescuentoList()) {
				Promocion oldIdPromocionOfPromociontipodescuentoListPromociontipodescuento = promociontipodescuentoListPromociontipodescuento
						.getIdPromocion();
				promociontipodescuentoListPromociontipodescuento
						.setIdPromocion(promocion);
				promociontipodescuentoListPromociontipodescuento = em
						.merge(promociontipodescuentoListPromociontipodescuento);
				if (oldIdPromocionOfPromociontipodescuentoListPromociontipodescuento != null) {
					oldIdPromocionOfPromociontipodescuentoListPromociontipodescuento
							.getPromociontipodescuentoList()
							.remove(promociontipodescuentoListPromociontipodescuento);
					oldIdPromocionOfPromociontipodescuentoListPromociontipodescuento = em
							.merge(oldIdPromocionOfPromociontipodescuentoListPromociontipodescuento);
				}
			}
			for (Promocionarticulo promocionarticuloListPromocionarticulo : promocion
					.getPromocionarticuloList()) {
				Promocion oldIdPromocionOfPromocionarticuloListPromocionarticulo = promocionarticuloListPromocionarticulo
						.getIdPromocion();
				promocionarticuloListPromocionarticulo
						.setIdPromocion(promocion);
				promocionarticuloListPromocionarticulo = em
						.merge(promocionarticuloListPromocionarticulo);
				if (oldIdPromocionOfPromocionarticuloListPromocionarticulo != null) {
					oldIdPromocionOfPromocionarticuloListPromocionarticulo
							.getPromocionarticuloList().remove(
									promocionarticuloListPromocionarticulo);
					oldIdPromocionOfPromocionarticuloListPromocionarticulo = em
							.merge(oldIdPromocionOfPromocionarticuloListPromocionarticulo);
				}
			}
			for (Promocionformadepago promocionformadepagoListPromocionformadepago : promocion
					.getPromocionformadepagoList()) {
				Promocion oldIdPromocionOfPromocionformadepagoListPromocionformadepago = promocionformadepagoListPromocionformadepago
						.getIdPromocion();
				promocionformadepagoListPromocionformadepago
						.setIdPromocion(promocion);
				promocionformadepagoListPromocionformadepago = em
						.merge(promocionformadepagoListPromocionformadepago);
				if (oldIdPromocionOfPromocionformadepagoListPromocionformadepago != null) {
					oldIdPromocionOfPromocionformadepagoListPromocionformadepago
							.getPromocionformadepagoList()
							.remove(promocionformadepagoListPromocionformadepago);
					oldIdPromocionOfPromocionformadepagoListPromocionformadepago = em
							.merge(oldIdPromocionOfPromocionformadepagoListPromocionformadepago);
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findPromocion(promocion.getId()) != null) {
				throw new PreexistingEntityException("Promocion " + promocion
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
	 * @param promocion
	 *            Promocion
	 * @throws IllegalOrphanException
	 * @throws NonexistentEntityException
	 * @throws Exception
	 */
	public void edit(Promocion promocion) throws IllegalOrphanException,
			NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Promocion persistentPromocion = em.find(Promocion.class,
					promocion.getId());
			List<Promocionfamilia> promocionfamiliaListOld = persistentPromocion
					.getPromocionfamiliaList();
			List<Promocionfamilia> promocionfamiliaListNew = promocion
					.getPromocionfamiliaList();
			List<Promocioncategoria> promocioncategoriaListOld = persistentPromocion
					.getPromocioncategoriaList();
			List<Promocioncategoria> promocioncategoriaListNew = promocion
					.getPromocioncategoriaList();
			List<Promocionlinea> promocionlineaListOld = persistentPromocion
					.getPromocionlineaList();
			List<Promocionlinea> promocionlineaListNew = promocion
					.getPromocionlineaList();
			List<Transaccionarticulo> transaccionarticuloListOld = persistentPromocion
					.getTransaccionarticuloList();
			List<Transaccionarticulo> transaccionarticuloListNew = promocion
					.getTransaccionarticuloList();
			List<Promociontipodescuento> promociontipodescuentoListOld = persistentPromocion
					.getPromociontipodescuentoList();
			List<Promociontipodescuento> promociontipodescuentoListNew = promocion
					.getPromociontipodescuentoList();
			List<Promocionarticulo> promocionarticuloListOld = persistentPromocion
					.getPromocionarticuloList();
			List<Promocionarticulo> promocionarticuloListNew = promocion
					.getPromocionarticuloList();
			List<Promocionformadepago> promocionformadepagoListOld = persistentPromocion
					.getPromocionformadepagoList();
			List<Promocionformadepago> promocionformadepagoListNew = promocion
					.getPromocionformadepagoList();
			List<String> illegalOrphanMessages = null;
			for (Promocionfamilia promocionfamiliaListOldPromocionfamilia : promocionfamiliaListOld) {
				if (!promocionfamiliaListNew
						.contains(promocionfamiliaListOldPromocionfamilia)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages
							.add("You must retain Promocionfamilia "
									+ promocionfamiliaListOldPromocionfamilia
									+ " since its idPromocion field is not nullable.");
				}
			}
			for (Promocioncategoria promocioncategoriaListOldPromocioncategoria : promocioncategoriaListOld) {
				if (!promocioncategoriaListNew
						.contains(promocioncategoriaListOldPromocioncategoria)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages
							.add("You must retain Promocioncategoria "
									+ promocioncategoriaListOldPromocioncategoria
									+ " since its idPromocion field is not nullable.");
				}
			}
			for (Promocionlinea promocionlineaListOldPromocionlinea : promocionlineaListOld) {
				if (!promocionlineaListNew
						.contains(promocionlineaListOldPromocionlinea)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages.add("You must retain Promocionlinea "
							+ promocionlineaListOldPromocionlinea
							+ " since its idPromocion field is not nullable.");
				}
			}
			for (Promociontipodescuento promociontipodescuentoListOldPromociontipodescuento : promociontipodescuentoListOld) {
				if (!promociontipodescuentoListNew
						.contains(promociontipodescuentoListOldPromociontipodescuento)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages
							.add("You must retain Promociontipodescuento "
									+ promociontipodescuentoListOldPromociontipodescuento
									+ " since its idPromocion field is not nullable.");
				}
			}
			for (Promocionarticulo promocionarticuloListOldPromocionarticulo : promocionarticuloListOld) {
				if (!promocionarticuloListNew
						.contains(promocionarticuloListOldPromocionarticulo)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages
							.add("You must retain Promocionarticulo "
									+ promocionarticuloListOldPromocionarticulo
									+ " since its idPromocion field is not nullable.");
				}
			}
			for (Promocionformadepago promocionformadepagoListOldPromocionformadepago : promocionformadepagoListOld) {
				if (!promocionformadepagoListNew
						.contains(promocionformadepagoListOldPromocionformadepago)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages
							.add("You must retain Promocionformadepago "
									+ promocionformadepagoListOldPromocionformadepago
									+ " since its idPromocion field is not nullable.");
				}
			}
			if (illegalOrphanMessages != null) {
				throw new IllegalOrphanException(illegalOrphanMessages);
			}
			List<Promocionfamilia> attachedPromocionfamiliaListNew = new ArrayList<Promocionfamilia>();
			for (Promocionfamilia promocionfamiliaListNewPromocionfamiliaToAttach : promocionfamiliaListNew) {
				promocionfamiliaListNewPromocionfamiliaToAttach = em
						.getReference(
								promocionfamiliaListNewPromocionfamiliaToAttach
										.getClass(),
								promocionfamiliaListNewPromocionfamiliaToAttach
										.getId());
				attachedPromocionfamiliaListNew
						.add(promocionfamiliaListNewPromocionfamiliaToAttach);
			}
			promocionfamiliaListNew = attachedPromocionfamiliaListNew;
			promocion.setPromocionfamiliaList(promocionfamiliaListNew);
			List<Promocioncategoria> attachedPromocioncategoriaListNew = new ArrayList<Promocioncategoria>();
			for (Promocioncategoria promocioncategoriaListNewPromocioncategoriaToAttach : promocioncategoriaListNew) {
				promocioncategoriaListNewPromocioncategoriaToAttach = em
						.getReference(
								promocioncategoriaListNewPromocioncategoriaToAttach
										.getClass(),
								promocioncategoriaListNewPromocioncategoriaToAttach
										.getId());
				attachedPromocioncategoriaListNew
						.add(promocioncategoriaListNewPromocioncategoriaToAttach);
			}
			promocioncategoriaListNew = attachedPromocioncategoriaListNew;
			promocion.setPromocioncategoriaList(promocioncategoriaListNew);
			List<Promocionlinea> attachedPromocionlineaListNew = new ArrayList<Promocionlinea>();
			for (Promocionlinea promocionlineaListNewPromocionlineaToAttach : promocionlineaListNew) {
				promocionlineaListNewPromocionlineaToAttach = em.getReference(
						promocionlineaListNewPromocionlineaToAttach.getClass(),
						promocionlineaListNewPromocionlineaToAttach.getId());
				attachedPromocionlineaListNew
						.add(promocionlineaListNewPromocionlineaToAttach);
			}
			promocionlineaListNew = attachedPromocionlineaListNew;
			promocion.setPromocionlineaList(promocionlineaListNew);
			List<Transaccionarticulo> attachedTransaccionarticuloListNew = new ArrayList<Transaccionarticulo>();
			for (Transaccionarticulo transaccionarticuloListNewTransaccionarticuloToAttach : transaccionarticuloListNew) {
				transaccionarticuloListNewTransaccionarticuloToAttach = em
						.getReference(
								transaccionarticuloListNewTransaccionarticuloToAttach
										.getClass(),
								transaccionarticuloListNewTransaccionarticuloToAttach
										.getId());
				attachedTransaccionarticuloListNew
						.add(transaccionarticuloListNewTransaccionarticuloToAttach);
			}
			transaccionarticuloListNew = attachedTransaccionarticuloListNew;
			promocion.setTransaccionarticuloList(transaccionarticuloListNew);
			List<Promociontipodescuento> attachedPromociontipodescuentoListNew = new ArrayList<Promociontipodescuento>();
			for (Promociontipodescuento promociontipodescuentoListNewPromociontipodescuentoToAttach : promociontipodescuentoListNew) {
				promociontipodescuentoListNewPromociontipodescuentoToAttach = em
						.getReference(
								promociontipodescuentoListNewPromociontipodescuentoToAttach
										.getClass(),
								promociontipodescuentoListNewPromociontipodescuentoToAttach
										.getId());
				attachedPromociontipodescuentoListNew
						.add(promociontipodescuentoListNewPromociontipodescuentoToAttach);
			}
			promociontipodescuentoListNew = attachedPromociontipodescuentoListNew;
			promocion
					.setPromociontipodescuentoList(promociontipodescuentoListNew);
			List<Promocionarticulo> attachedPromocionarticuloListNew = new ArrayList<Promocionarticulo>();
			for (Promocionarticulo promocionarticuloListNewPromocionarticuloToAttach : promocionarticuloListNew) {
				promocionarticuloListNewPromocionarticuloToAttach = em
						.getReference(
								promocionarticuloListNewPromocionarticuloToAttach
										.getClass(),
								promocionarticuloListNewPromocionarticuloToAttach
										.getId());
				attachedPromocionarticuloListNew
						.add(promocionarticuloListNewPromocionarticuloToAttach);
			}
			promocionarticuloListNew = attachedPromocionarticuloListNew;
			promocion.setPromocionarticuloList(promocionarticuloListNew);
			List<Promocionformadepago> attachedPromocionformadepagoListNew = new ArrayList<Promocionformadepago>();
			for (Promocionformadepago promocionformadepagoListNewPromocionformadepagoToAttach : promocionformadepagoListNew) {
				promocionformadepagoListNewPromocionformadepagoToAttach = em
						.getReference(
								promocionformadepagoListNewPromocionformadepagoToAttach
										.getClass(),
								promocionformadepagoListNewPromocionformadepagoToAttach
										.getId());
				attachedPromocionformadepagoListNew
						.add(promocionformadepagoListNewPromocionformadepagoToAttach);
			}
			promocionformadepagoListNew = attachedPromocionformadepagoListNew;
			promocion.setPromocionformadepagoList(promocionformadepagoListNew);
			promocion = em.merge(promocion);
			for (Promocionfamilia promocionfamiliaListNewPromocionfamilia : promocionfamiliaListNew) {
				if (!promocionfamiliaListOld
						.contains(promocionfamiliaListNewPromocionfamilia)) {
					Promocion oldIdPromocionOfPromocionfamiliaListNewPromocionfamilia = promocionfamiliaListNewPromocionfamilia
							.getIdPromocion();
					promocionfamiliaListNewPromocionfamilia
							.setIdPromocion(promocion);
					promocionfamiliaListNewPromocionfamilia = em
							.merge(promocionfamiliaListNewPromocionfamilia);
					if (oldIdPromocionOfPromocionfamiliaListNewPromocionfamilia != null
							&& !oldIdPromocionOfPromocionfamiliaListNewPromocionfamilia
									.equals(promocion)) {
						oldIdPromocionOfPromocionfamiliaListNewPromocionfamilia
								.getPromocionfamiliaList()
								.remove(promocionfamiliaListNewPromocionfamilia);
						oldIdPromocionOfPromocionfamiliaListNewPromocionfamilia = em
								.merge(oldIdPromocionOfPromocionfamiliaListNewPromocionfamilia);
					}
				}
			}
			for (Promocioncategoria promocioncategoriaListNewPromocioncategoria : promocioncategoriaListNew) {
				if (!promocioncategoriaListOld
						.contains(promocioncategoriaListNewPromocioncategoria)) {
					Promocion oldIdPromocionOfPromocioncategoriaListNewPromocioncategoria = promocioncategoriaListNewPromocioncategoria
							.getIdPromocion();
					promocioncategoriaListNewPromocioncategoria
							.setIdPromocion(promocion);
					promocioncategoriaListNewPromocioncategoria = em
							.merge(promocioncategoriaListNewPromocioncategoria);
					if (oldIdPromocionOfPromocioncategoriaListNewPromocioncategoria != null
							&& !oldIdPromocionOfPromocioncategoriaListNewPromocioncategoria
									.equals(promocion)) {
						oldIdPromocionOfPromocioncategoriaListNewPromocioncategoria
								.getPromocioncategoriaList()
								.remove(promocioncategoriaListNewPromocioncategoria);
						oldIdPromocionOfPromocioncategoriaListNewPromocioncategoria = em
								.merge(oldIdPromocionOfPromocioncategoriaListNewPromocioncategoria);
					}
				}
			}
			for (Promocionlinea promocionlineaListNewPromocionlinea : promocionlineaListNew) {
				if (!promocionlineaListOld
						.contains(promocionlineaListNewPromocionlinea)) {
					Promocion oldIdPromocionOfPromocionlineaListNewPromocionlinea = promocionlineaListNewPromocionlinea
							.getIdPromocion();
					promocionlineaListNewPromocionlinea
							.setIdPromocion(promocion);
					promocionlineaListNewPromocionlinea = em
							.merge(promocionlineaListNewPromocionlinea);
					if (oldIdPromocionOfPromocionlineaListNewPromocionlinea != null
							&& !oldIdPromocionOfPromocionlineaListNewPromocionlinea
									.equals(promocion)) {
						oldIdPromocionOfPromocionlineaListNewPromocionlinea
								.getPromocionlineaList().remove(
										promocionlineaListNewPromocionlinea);
						oldIdPromocionOfPromocionlineaListNewPromocionlinea = em
								.merge(oldIdPromocionOfPromocionlineaListNewPromocionlinea);
					}
				}
			}
			for (Transaccionarticulo transaccionarticuloListOldTransaccionarticulo : transaccionarticuloListOld) {
				if (!transaccionarticuloListNew
						.contains(transaccionarticuloListOldTransaccionarticulo)) {
					transaccionarticuloListOldTransaccionarticulo
							.setIdPromocion(null);
					transaccionarticuloListOldTransaccionarticulo = em
							.merge(transaccionarticuloListOldTransaccionarticulo);
				}
			}
			for (Transaccionarticulo transaccionarticuloListNewTransaccionarticulo : transaccionarticuloListNew) {
				if (!transaccionarticuloListOld
						.contains(transaccionarticuloListNewTransaccionarticulo)) {
					Promocion oldIdPromocionOfTransaccionarticuloListNewTransaccionarticulo = transaccionarticuloListNewTransaccionarticulo
							.getIdPromocion();
					transaccionarticuloListNewTransaccionarticulo
							.setIdPromocion(promocion);
					transaccionarticuloListNewTransaccionarticulo = em
							.merge(transaccionarticuloListNewTransaccionarticulo);
					if (oldIdPromocionOfTransaccionarticuloListNewTransaccionarticulo != null
							&& !oldIdPromocionOfTransaccionarticuloListNewTransaccionarticulo
									.equals(promocion)) {
						oldIdPromocionOfTransaccionarticuloListNewTransaccionarticulo
								.getTransaccionarticuloList()
								.remove(transaccionarticuloListNewTransaccionarticulo);
						oldIdPromocionOfTransaccionarticuloListNewTransaccionarticulo = em
								.merge(oldIdPromocionOfTransaccionarticuloListNewTransaccionarticulo);
					}
				}
			}
			for (Promociontipodescuento promociontipodescuentoListNewPromociontipodescuento : promociontipodescuentoListNew) {
				if (!promociontipodescuentoListOld
						.contains(promociontipodescuentoListNewPromociontipodescuento)) {
					Promocion oldIdPromocionOfPromociontipodescuentoListNewPromociontipodescuento = promociontipodescuentoListNewPromociontipodescuento
							.getIdPromocion();
					promociontipodescuentoListNewPromociontipodescuento
							.setIdPromocion(promocion);
					promociontipodescuentoListNewPromociontipodescuento = em
							.merge(promociontipodescuentoListNewPromociontipodescuento);
					if (oldIdPromocionOfPromociontipodescuentoListNewPromociontipodescuento != null
							&& !oldIdPromocionOfPromociontipodescuentoListNewPromociontipodescuento
									.equals(promocion)) {
						oldIdPromocionOfPromociontipodescuentoListNewPromociontipodescuento
								.getPromociontipodescuentoList()
								.remove(promociontipodescuentoListNewPromociontipodescuento);
						oldIdPromocionOfPromociontipodescuentoListNewPromociontipodescuento = em
								.merge(oldIdPromocionOfPromociontipodescuentoListNewPromociontipodescuento);
					}
				}
			}
			for (Promocionarticulo promocionarticuloListNewPromocionarticulo : promocionarticuloListNew) {
				if (!promocionarticuloListOld
						.contains(promocionarticuloListNewPromocionarticulo)) {
					Promocion oldIdPromocionOfPromocionarticuloListNewPromocionarticulo = promocionarticuloListNewPromocionarticulo
							.getIdPromocion();
					promocionarticuloListNewPromocionarticulo
							.setIdPromocion(promocion);
					promocionarticuloListNewPromocionarticulo = em
							.merge(promocionarticuloListNewPromocionarticulo);
					if (oldIdPromocionOfPromocionarticuloListNewPromocionarticulo != null
							&& !oldIdPromocionOfPromocionarticuloListNewPromocionarticulo
									.equals(promocion)) {
						oldIdPromocionOfPromocionarticuloListNewPromocionarticulo
								.getPromocionarticuloList()
								.remove(promocionarticuloListNewPromocionarticulo);
						oldIdPromocionOfPromocionarticuloListNewPromocionarticulo = em
								.merge(oldIdPromocionOfPromocionarticuloListNewPromocionarticulo);
					}
				}
			}
			for (Promocionformadepago promocionformadepagoListNewPromocionformadepago : promocionformadepagoListNew) {
				if (!promocionformadepagoListOld
						.contains(promocionformadepagoListNewPromocionformadepago)) {
					Promocion oldIdPromocionOfPromocionformadepagoListNewPromocionformadepago = promocionformadepagoListNewPromocionformadepago
							.getIdPromocion();
					promocionformadepagoListNewPromocionformadepago
							.setIdPromocion(promocion);
					promocionformadepagoListNewPromocionformadepago = em
							.merge(promocionformadepagoListNewPromocionformadepago);
					if (oldIdPromocionOfPromocionformadepagoListNewPromocionformadepago != null
							&& !oldIdPromocionOfPromocionformadepagoListNewPromocionformadepago
									.equals(promocion)) {
						oldIdPromocionOfPromocionformadepagoListNewPromocionformadepago
								.getPromocionformadepagoList()
								.remove(promocionformadepagoListNewPromocionformadepago);
						oldIdPromocionOfPromocionformadepagoListNewPromocionformadepago = em
								.merge(oldIdPromocionOfPromocionformadepagoListNewPromocionformadepago);
					}
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = promocion.getId();
				if (findPromocion(id) == null) {
					throw new NonexistentEntityException(
							"The promocion with id " + id
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
			Promocion promocion;
			try {
				promocion = em.getReference(Promocion.class, id);
				promocion.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException("The promocion with id "
						+ id + " no longer exists.", enfe);
			}
			List<String> illegalOrphanMessages = null;
			List<Promocionfamilia> promocionfamiliaListOrphanCheck = promocion
					.getPromocionfamiliaList();
			for (Promocionfamilia promocionfamiliaListOrphanCheckPromocionfamilia : promocionfamiliaListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages
						.add("This Promocion ("
								+ promocion
								+ ") cannot be destroyed since the Promocionfamilia "
								+ promocionfamiliaListOrphanCheckPromocionfamilia
								+ " in its promocionfamiliaList field has a non-nullable idPromocion field.");
			}
			List<Promocioncategoria> promocioncategoriaListOrphanCheck = promocion
					.getPromocioncategoriaList();
			for (Promocioncategoria promocioncategoriaListOrphanCheckPromocioncategoria : promocioncategoriaListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages
						.add("This Promocion ("
								+ promocion
								+ ") cannot be destroyed since the Promocioncategoria "
								+ promocioncategoriaListOrphanCheckPromocioncategoria
								+ " in its promocioncategoriaList field has a non-nullable idPromocion field.");
			}
			List<Promocionlinea> promocionlineaListOrphanCheck = promocion
					.getPromocionlineaList();
			for (Promocionlinea promocionlineaListOrphanCheckPromocionlinea : promocionlineaListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages
						.add("This Promocion ("
								+ promocion
								+ ") cannot be destroyed since the Promocionlinea "
								+ promocionlineaListOrphanCheckPromocionlinea
								+ " in its promocionlineaList field has a non-nullable idPromocion field.");
			}
			List<Promociontipodescuento> promociontipodescuentoListOrphanCheck = promocion
					.getPromociontipodescuentoList();
			for (Promociontipodescuento promociontipodescuentoListOrphanCheckPromociontipodescuento : promociontipodescuentoListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages
						.add("This Promocion ("
								+ promocion
								+ ") cannot be destroyed since the Promociontipodescuento "
								+ promociontipodescuentoListOrphanCheckPromociontipodescuento
								+ " in its promociontipodescuentoList field has a non-nullable idPromocion field.");
			}
			List<Promocionarticulo> promocionarticuloListOrphanCheck = promocion
					.getPromocionarticuloList();
			for (Promocionarticulo promocionarticuloListOrphanCheckPromocionarticulo : promocionarticuloListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages
						.add("This Promocion ("
								+ promocion
								+ ") cannot be destroyed since the Promocionarticulo "
								+ promocionarticuloListOrphanCheckPromocionarticulo
								+ " in its promocionarticuloList field has a non-nullable idPromocion field.");
			}
			List<Promocionformadepago> promocionformadepagoListOrphanCheck = promocion
					.getPromocionformadepagoList();
			for (Promocionformadepago promocionformadepagoListOrphanCheckPromocionformadepago : promocionformadepagoListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages
						.add("This Promocion ("
								+ promocion
								+ ") cannot be destroyed since the Promocionformadepago "
								+ promocionformadepagoListOrphanCheckPromocionformadepago
								+ " in its promocionformadepagoList field has a non-nullable idPromocion field.");
			}
			if (illegalOrphanMessages != null) {
				throw new IllegalOrphanException(illegalOrphanMessages);
			}
			List<Transaccionarticulo> transaccionarticuloList = promocion
					.getTransaccionarticuloList();
			for (Transaccionarticulo transaccionarticuloListTransaccionarticulo : transaccionarticuloList) {
				transaccionarticuloListTransaccionarticulo.setIdPromocion(null);
				transaccionarticuloListTransaccionarticulo = em
						.merge(transaccionarticuloListTransaccionarticulo);
			}
			em.remove(promocion);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method findPromocionEntities.
	 * 
	 * @return List<Promocion>
	 */
	public List<Promocion> findPromocionEntities() {
		return findPromocionEntities(true, -1, -1);
	}

	/**
	 * Method findPromocionEntities.
	 * 
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Promocion>
	 */
	public List<Promocion> findPromocionEntities(int maxResults, int firstResult) {
		return findPromocionEntities(false, maxResults, firstResult);
	}

	/**
	 * Method findPromocionEntities.
	 * 
	 * @param all
	 *            boolean
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Promocion>
	 */
	private List<Promocion> findPromocionEntities(boolean all, int maxResults,
			int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Promocion.class));
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
	 * Method findPromocion.
	 * 
	 * @param id
	 *            Long
	 * @return Promocion
	 */
	public Promocion findPromocion(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Promocion.class, id);
		} finally {
			em.close();
		}
	}

	/**
	 * Method getPromocionCount.
	 * 
	 * @return int
	 */
	public int getPromocionCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Promocion> rt = cq.from(Promocion.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}
