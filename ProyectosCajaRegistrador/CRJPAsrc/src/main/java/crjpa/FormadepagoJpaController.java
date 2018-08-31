/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
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
 * @version $Revision: 1.0 $
 */
public class FormadepagoJpaController implements Serializable {

	/**
	 * Constructor for FormadepagoJpaController.
	 * 
	 * @param emf EntityManagerFactory
	 */
	public FormadepagoJpaController(EntityManagerFactory emf) {
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
	 * @param formadepago Formadepago
	 * @throws PreexistingEntityException
	 * @throws Exception
	 */
	public void create(Formadepago formadepago) throws PreexistingEntityException, Exception {
		if (formadepago.getPromocionformadepagoList() == null) {
			formadepago.setPromocionformadepagoList(new ArrayList<Promocionformadepago>());
		}
		if (formadepago.getFormadepagomonedaList() == null) {
			formadepago.setFormadepagomonedaList(new ArrayList<Formadepagomoneda>());
		}
		if (formadepago.getDetalletipoformadepagoList() == null) {
			formadepago.setDetalletipoformadepagoList(new ArrayList<Detalletipoformadepago>());
		}
		if (formadepago.getEntregaformadepagoList() == null) {
			formadepago.setEntregaformadepagoList(new ArrayList<Entregaformadepago>());
		}
		if (formadepago.getTransaccionformadepagoList() == null) {
			formadepago.setTransaccionformadepagoList(new ArrayList<Transaccionformadepago>());
		}
		if (formadepago.getMorosidadList() == null) {
			formadepago.setMorosidadList(new ArrayList<Morosidad>());
		}
//		if (formadepago.getCategorialinearetencionList() == null) {
//			formadepago.setCategorialinearetencionList(new ArrayList<Categorialinearetencion>());
//		}
		if (formadepago.getPorcentajeimpuestoretencionList() == null) {
			formadepago.setPorcentajeimpuestoretencionList(new ArrayList<Porcentajeimpuestoretencion>());
		}
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			List<Promocionformadepago> attachedPromocionformadepagoList = new ArrayList<Promocionformadepago>();
			for (Promocionformadepago promocionformadepagoListPromocionformadepagoToAttach : formadepago
					.getPromocionformadepagoList()) {
				promocionformadepagoListPromocionformadepagoToAttach = em.getReference(
						promocionformadepagoListPromocionformadepagoToAttach.getClass(),
						promocionformadepagoListPromocionformadepagoToAttach.getId());
				attachedPromocionformadepagoList.add(promocionformadepagoListPromocionformadepagoToAttach);
			}
			formadepago.setPromocionformadepagoList(attachedPromocionformadepagoList);
			List<Formadepagomoneda> attachedFormadepagomonedaList = new ArrayList<Formadepagomoneda>();
			for (Formadepagomoneda formadepagomonedaListFormadepagomonedaToAttach : formadepago
					.getFormadepagomonedaList()) {
				formadepagomonedaListFormadepagomonedaToAttach = em.getReference(
						formadepagomonedaListFormadepagomonedaToAttach.getClass(),
						formadepagomonedaListFormadepagomonedaToAttach.getId());
				attachedFormadepagomonedaList.add(formadepagomonedaListFormadepagomonedaToAttach);
			}
			formadepago.setFormadepagomonedaList(attachedFormadepagomonedaList);
			List<Detalletipoformadepago> attachedDetalletipoformadepagoList = new ArrayList<Detalletipoformadepago>();
			for (Detalletipoformadepago detalletipoformadepagoListDetalletipoformadepagoToAttach : formadepago
					.getDetalletipoformadepagoList()) {
				detalletipoformadepagoListDetalletipoformadepagoToAttach = em.getReference(
						detalletipoformadepagoListDetalletipoformadepagoToAttach.getClass(),
						detalletipoformadepagoListDetalletipoformadepagoToAttach.getId());
				attachedDetalletipoformadepagoList.add(detalletipoformadepagoListDetalletipoformadepagoToAttach);
			}
			formadepago.setDetalletipoformadepagoList(attachedDetalletipoformadepagoList);
			List<Entregaformadepago> attachedEntregaformadepagoList = new ArrayList<Entregaformadepago>();
			for (Entregaformadepago entregaformadepagoListEntregaformadepagoToAttach : formadepago
					.getEntregaformadepagoList()) {
				entregaformadepagoListEntregaformadepagoToAttach = em.getReference(
						entregaformadepagoListEntregaformadepagoToAttach.getClass(),
						entregaformadepagoListEntregaformadepagoToAttach.getId());
				attachedEntregaformadepagoList.add(entregaformadepagoListEntregaformadepagoToAttach);
			}
			formadepago.setEntregaformadepagoList(attachedEntregaformadepagoList);
			List<Transaccionformadepago> attachedTransaccionformadepagoList = new ArrayList<Transaccionformadepago>();
			for (Transaccionformadepago transaccionformadepagoListTransaccionformadepagoToAttach : formadepago
					.getTransaccionformadepagoList()) {
				transaccionformadepagoListTransaccionformadepagoToAttach = em.getReference(
						transaccionformadepagoListTransaccionformadepagoToAttach.getClass(),
						transaccionformadepagoListTransaccionformadepagoToAttach.getId());
				attachedTransaccionformadepagoList.add(transaccionformadepagoListTransaccionformadepagoToAttach);
			}
			formadepago.setTransaccionformadepagoList(attachedTransaccionformadepagoList);
			List<Morosidad> attachedMorosidadList = new ArrayList<Morosidad>();
			for (Morosidad morosidadListMorosidadToAttach : formadepago.getMorosidadList()) {
				morosidadListMorosidadToAttach = em.getReference(morosidadListMorosidadToAttach.getClass(),
						morosidadListMorosidadToAttach.getId());
				attachedMorosidadList.add(morosidadListMorosidadToAttach);
			}
			formadepago.setMorosidadList(attachedMorosidadList);
			List<Categorialinearetencion> attachedCategorialinearetencionList = new ArrayList<Categorialinearetencion>();
//			for (Categorialinearetencion categorialinearetencionListCategorialinearetencionToAttach : formadepago
//					.getCategorialinearetencionList()) {
//				categorialinearetencionListCategorialinearetencionToAttach = em.getReference(
//						categorialinearetencionListCategorialinearetencionToAttach.getClass(),
//						categorialinearetencionListCategorialinearetencionToAttach.getId());
//				attachedCategorialinearetencionList.add(categorialinearetencionListCategorialinearetencionToAttach);
//			}
//			formadepago.setCategorialinearetencionList(attachedCategorialinearetencionList);
			List<Porcentajeimpuestoretencion> attachedPorcentajeimpuestoretencionList = new ArrayList<Porcentajeimpuestoretencion>();
			for (Porcentajeimpuestoretencion porcentajeimpuestoretencionListPorcentajeimpuestoretencionToAttach : formadepago
					.getPorcentajeimpuestoretencionList()) {
				porcentajeimpuestoretencionListPorcentajeimpuestoretencionToAttach = em.getReference(
						porcentajeimpuestoretencionListPorcentajeimpuestoretencionToAttach.getClass(),
						porcentajeimpuestoretencionListPorcentajeimpuestoretencionToAttach.getId());
				attachedPorcentajeimpuestoretencionList
						.add(porcentajeimpuestoretencionListPorcentajeimpuestoretencionToAttach);
			}
			formadepago.setPorcentajeimpuestoretencionList(attachedPorcentajeimpuestoretencionList);
			em.persist(formadepago);
			for (Promocionformadepago promocionformadepagoListPromocionformadepago : formadepago
					.getPromocionformadepagoList()) {
				Formadepago oldIdFormaDePagoOfPromocionformadepagoListPromocionformadepago = promocionformadepagoListPromocionformadepago
						.getIdFormaDePago();
				promocionformadepagoListPromocionformadepago.setIdFormaDePago(formadepago);
				promocionformadepagoListPromocionformadepago = em.merge(promocionformadepagoListPromocionformadepago);
				if (oldIdFormaDePagoOfPromocionformadepagoListPromocionformadepago != null) {
					oldIdFormaDePagoOfPromocionformadepagoListPromocionformadepago.getPromocionformadepagoList()
							.remove(promocionformadepagoListPromocionformadepago);
					oldIdFormaDePagoOfPromocionformadepagoListPromocionformadepago = em
							.merge(oldIdFormaDePagoOfPromocionformadepagoListPromocionformadepago);
				}
			}
			for (Formadepagomoneda formadepagomonedaListFormadepagomoneda : formadepago.getFormadepagomonedaList()) {
				Formadepago oldIdFormadepagoOfFormadepagomonedaListFormadepagomoneda = formadepagomonedaListFormadepagomoneda
						.getIdFormadepago();
				formadepagomonedaListFormadepagomoneda.setIdFormadepago(formadepago);
				formadepagomonedaListFormadepagomoneda = em.merge(formadepagomonedaListFormadepagomoneda);
				if (oldIdFormadepagoOfFormadepagomonedaListFormadepagomoneda != null) {
					oldIdFormadepagoOfFormadepagomonedaListFormadepagomoneda.getFormadepagomonedaList().remove(
							formadepagomonedaListFormadepagomoneda);
					oldIdFormadepagoOfFormadepagomonedaListFormadepagomoneda = em
							.merge(oldIdFormadepagoOfFormadepagomonedaListFormadepagomoneda);
				}
			}
			for (Detalletipoformadepago detalletipoformadepagoListDetalletipoformadepago : formadepago
					.getDetalletipoformadepagoList()) {
				Formadepago oldIdFormadepagoOfDetalletipoformadepagoListDetalletipoformadepago = detalletipoformadepagoListDetalletipoformadepago
						.getIdFormadepago();
				detalletipoformadepagoListDetalletipoformadepago.setIdFormadepago(formadepago);
				detalletipoformadepagoListDetalletipoformadepago = em
						.merge(detalletipoformadepagoListDetalletipoformadepago);
				if (oldIdFormadepagoOfDetalletipoformadepagoListDetalletipoformadepago != null) {
					oldIdFormadepagoOfDetalletipoformadepagoListDetalletipoformadepago.getDetalletipoformadepagoList()
							.remove(detalletipoformadepagoListDetalletipoformadepago);
					oldIdFormadepagoOfDetalletipoformadepagoListDetalletipoformadepago = em
							.merge(oldIdFormadepagoOfDetalletipoformadepagoListDetalletipoformadepago);
				}
			}
			for (Entregaformadepago entregaformadepagoListEntregaformadepago : formadepago.getEntregaformadepagoList()) {
				Formadepago oldIdFormadepagoOfEntregaformadepagoListEntregaformadepago = entregaformadepagoListEntregaformadepago
						.getIdFormadepago();
				entregaformadepagoListEntregaformadepago.setIdFormadepago(formadepago);
				entregaformadepagoListEntregaformadepago = em.merge(entregaformadepagoListEntregaformadepago);
				if (oldIdFormadepagoOfEntregaformadepagoListEntregaformadepago != null) {
					oldIdFormadepagoOfEntregaformadepagoListEntregaformadepago.getEntregaformadepagoList().remove(
							entregaformadepagoListEntregaformadepago);
					oldIdFormadepagoOfEntregaformadepagoListEntregaformadepago = em
							.merge(oldIdFormadepagoOfEntregaformadepagoListEntregaformadepago);
				}
			}
			for (Transaccionformadepago transaccionformadepagoListTransaccionformadepago : formadepago
					.getTransaccionformadepagoList()) {
				Formadepago oldIdFormadepagoOfTransaccionformadepagoListTransaccionformadepago = transaccionformadepagoListTransaccionformadepago
						.getIdFormadepago();
				transaccionformadepagoListTransaccionformadepago.setIdFormadepago(formadepago);
				transaccionformadepagoListTransaccionformadepago = em
						.merge(transaccionformadepagoListTransaccionformadepago);
				if (oldIdFormadepagoOfTransaccionformadepagoListTransaccionformadepago != null) {
					oldIdFormadepagoOfTransaccionformadepagoListTransaccionformadepago.getTransaccionformadepagoList()
							.remove(transaccionformadepagoListTransaccionformadepago);
					oldIdFormadepagoOfTransaccionformadepagoListTransaccionformadepago = em
							.merge(oldIdFormadepagoOfTransaccionformadepagoListTransaccionformadepago);
				}
			}
			for (Morosidad morosidadListMorosidad : formadepago.getMorosidadList()) {
				Formadepago oldIdFormadepagoOfMorosidadListMorosidad = morosidadListMorosidad.getIdFormadepago();
				morosidadListMorosidad.setIdFormadepago(formadepago);
				morosidadListMorosidad = em.merge(morosidadListMorosidad);
				if (oldIdFormadepagoOfMorosidadListMorosidad != null) {
					oldIdFormadepagoOfMorosidadListMorosidad.getMorosidadList().remove(morosidadListMorosidad);
					oldIdFormadepagoOfMorosidadListMorosidad = em.merge(oldIdFormadepagoOfMorosidadListMorosidad);
				}
			}
//			for (Categorialinearetencion categorialinearetencionListCategorialinearetencion : formadepago
//					.getCategorialinearetencionList()) {
//				Formadepago oldIdFormadepagoOfCategorialinearetencionListCategorialinearetencion = categorialinearetencionListCategorialinearetencion
//						.getIdFormadepago();
//				categorialinearetencionListCategorialinearetencion.setIdFormadepago(formadepago);
//				categorialinearetencionListCategorialinearetencion = em
//						.merge(categorialinearetencionListCategorialinearetencion);
//				if (oldIdFormadepagoOfCategorialinearetencionListCategorialinearetencion != null) {
//					oldIdFormadepagoOfCategorialinearetencionListCategorialinearetencion
//							.getCategorialinearetencionList()
//							.remove(categorialinearetencionListCategorialinearetencion);
//					oldIdFormadepagoOfCategorialinearetencionListCategorialinearetencion = em
//							.merge(oldIdFormadepagoOfCategorialinearetencionListCategorialinearetencion);
//				}
//			}
			for (Porcentajeimpuestoretencion porcentajeimpuestoretencionListPorcentajeimpuestoretencion : formadepago
					.getPorcentajeimpuestoretencionList()) {
				Formadepago oldIdFormadepagoOfPorcentajeimpuestoretencionListPorcentajeimpuestoretencion = porcentajeimpuestoretencionListPorcentajeimpuestoretencion
						.getIdFormadepago();
				porcentajeimpuestoretencionListPorcentajeimpuestoretencion.setIdFormadepago(formadepago);
				porcentajeimpuestoretencionListPorcentajeimpuestoretencion = em
						.merge(porcentajeimpuestoretencionListPorcentajeimpuestoretencion);
				if (oldIdFormadepagoOfPorcentajeimpuestoretencionListPorcentajeimpuestoretencion != null) {
					oldIdFormadepagoOfPorcentajeimpuestoretencionListPorcentajeimpuestoretencion
							.getPorcentajeimpuestoretencionList().remove(
									porcentajeimpuestoretencionListPorcentajeimpuestoretencion);
					oldIdFormadepagoOfPorcentajeimpuestoretencionListPorcentajeimpuestoretencion = em
							.merge(oldIdFormadepagoOfPorcentajeimpuestoretencionListPorcentajeimpuestoretencion);
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findFormadepago(formadepago.getId()) != null) {
				throw new PreexistingEntityException("Formadepago " + formadepago + " already exists.", ex);
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
	 * @param formadepago
	 *            Formadepago
	 * @throws IllegalOrphanException
	 * @throws NonexistentEntityException
	 * @throws Exception
	 */
	public void edit(Formadepago formadepago) throws IllegalOrphanException, NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Formadepago persistentFormadepago = em.find(Formadepago.class, formadepago.getId());
			List<Promocionformadepago> promocionformadepagoListOld = persistentFormadepago
					.getPromocionformadepagoList();
			List<Promocionformadepago> promocionformadepagoListNew = formadepago.getPromocionformadepagoList();
			List<Formadepagomoneda> formadepagomonedaListOld = persistentFormadepago.getFormadepagomonedaList();
			List<Formadepagomoneda> formadepagomonedaListNew = formadepago.getFormadepagomonedaList();
			List<Detalletipoformadepago> detalletipoformadepagoListOld = persistentFormadepago
					.getDetalletipoformadepagoList();
			List<Detalletipoformadepago> detalletipoformadepagoListNew = formadepago.getDetalletipoformadepagoList();
			List<Entregaformadepago> entregaformadepagoListOld = persistentFormadepago.getEntregaformadepagoList();
			List<Entregaformadepago> entregaformadepagoListNew = formadepago.getEntregaformadepagoList();
			List<Transaccionformadepago> transaccionformadepagoListOld = persistentFormadepago
					.getTransaccionformadepagoList();
			List<Transaccionformadepago> transaccionformadepagoListNew = formadepago.getTransaccionformadepagoList();
			List<Morosidad> morosidadListOld = persistentFormadepago.getMorosidadList();
			List<Morosidad> morosidadListNew = formadepago.getMorosidadList();
//			List<Categorialinearetencion> categorialinearetencionListOld = persistentFormadepago
//					.getCategorialinearetencionList();
//			List<Categorialinearetencion> categorialinearetencionListNew = formadepago.getCategorialinearetencionList();
			List<Porcentajeimpuestoretencion> porcentajeimpuestoretencionListOld = persistentFormadepago
					.getPorcentajeimpuestoretencionList();
			List<Porcentajeimpuestoretencion> porcentajeimpuestoretencionListNew = formadepago
					.getPorcentajeimpuestoretencionList();
			List<String> illegalOrphanMessages = null;
			for (Promocionformadepago promocionformadepagoListOldPromocionformadepago : promocionformadepagoListOld) {
				if (!promocionformadepagoListNew.contains(promocionformadepagoListOldPromocionformadepago)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages.add("You must retain Promocionformadepago "
							+ promocionformadepagoListOldPromocionformadepago
							+ " since its idFormaDePago field is not nullable.");
				}
			}
			for (Formadepagomoneda formadepagomonedaListOldFormadepagomoneda : formadepagomonedaListOld) {
				if (!formadepagomonedaListNew.contains(formadepagomonedaListOldFormadepagomoneda)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages.add("You must retain Formadepagomoneda "
							+ formadepagomonedaListOldFormadepagomoneda
							+ " since its idFormadepago field is not nullable.");
				}
			}
			for (Detalletipoformadepago detalletipoformadepagoListOldDetalletipoformadepago : detalletipoformadepagoListOld) {
				if (!detalletipoformadepagoListNew.contains(detalletipoformadepagoListOldDetalletipoformadepago)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages.add("You must retain Detalletipoformadepago "
							+ detalletipoformadepagoListOldDetalletipoformadepago
							+ " since its idFormadepago field is not nullable.");
				}
			}
			for (Entregaformadepago entregaformadepagoListOldEntregaformadepago : entregaformadepagoListOld) {
				if (!entregaformadepagoListNew.contains(entregaformadepagoListOldEntregaformadepago)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages.add("You must retain Entregaformadepago "
							+ entregaformadepagoListOldEntregaformadepago
							+ " since its idFormadepago field is not nullable.");
				}
			}
			for (Transaccionformadepago transaccionformadepagoListOldTransaccionformadepago : transaccionformadepagoListOld) {
				if (!transaccionformadepagoListNew.contains(transaccionformadepagoListOldTransaccionformadepago)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages.add("You must retain Transaccionformadepago "
							+ transaccionformadepagoListOldTransaccionformadepago
							+ " since its idFormadepago field is not nullable.");
				}
			}
			for (Morosidad morosidadListOldMorosidad : morosidadListOld) {
				if (!morosidadListNew.contains(morosidadListOldMorosidad)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages.add("You must retain Morosidad " + morosidadListOldMorosidad
							+ " since its idFormadepago field is not nullable.");
				}
			}
//			for (Categorialinearetencion categorialinearetencionListOldCategorialinearetencion : categorialinearetencionListOld) {
//				if (!categorialinearetencionListNew.contains(categorialinearetencionListOldCategorialinearetencion)) {
//					if (illegalOrphanMessages == null) {
//						illegalOrphanMessages = new ArrayList<String>();
//					}
//					illegalOrphanMessages.add("You must retain Categorialinearetencion "
//							+ categorialinearetencionListOldCategorialinearetencion
//							+ " since its idFormadepago field is not nullable.");
//				}
//			}
			for (Porcentajeimpuestoretencion porcentajeimpuestoretencionListOldPorcentajeimpuestoretencion : porcentajeimpuestoretencionListOld) {
				if (!porcentajeimpuestoretencionListNew
						.contains(porcentajeimpuestoretencionListOldPorcentajeimpuestoretencion)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages.add("You must retain Porcentajeimpuestoretencion "
							+ porcentajeimpuestoretencionListOldPorcentajeimpuestoretencion
							+ " since its idFormadepago field is not nullable.");
				}
			}
			if (illegalOrphanMessages != null) {
				throw new IllegalOrphanException(illegalOrphanMessages);
			}
			List<Promocionformadepago> attachedPromocionformadepagoListNew = new ArrayList<Promocionformadepago>();
			for (Promocionformadepago promocionformadepagoListNewPromocionformadepagoToAttach : promocionformadepagoListNew) {
				promocionformadepagoListNewPromocionformadepagoToAttach = em.getReference(
						promocionformadepagoListNewPromocionformadepagoToAttach.getClass(),
						promocionformadepagoListNewPromocionformadepagoToAttach.getId());
				attachedPromocionformadepagoListNew.add(promocionformadepagoListNewPromocionformadepagoToAttach);
			}
			promocionformadepagoListNew = attachedPromocionformadepagoListNew;
			formadepago.setPromocionformadepagoList(promocionformadepagoListNew);
			List<Formadepagomoneda> attachedFormadepagomonedaListNew = new ArrayList<Formadepagomoneda>();
			for (Formadepagomoneda formadepagomonedaListNewFormadepagomonedaToAttach : formadepagomonedaListNew) {
				formadepagomonedaListNewFormadepagomonedaToAttach = em.getReference(
						formadepagomonedaListNewFormadepagomonedaToAttach.getClass(),
						formadepagomonedaListNewFormadepagomonedaToAttach.getId());
				attachedFormadepagomonedaListNew.add(formadepagomonedaListNewFormadepagomonedaToAttach);
			}
			formadepagomonedaListNew = attachedFormadepagomonedaListNew;
			formadepago.setFormadepagomonedaList(formadepagomonedaListNew);
			List<Detalletipoformadepago> attachedDetalletipoformadepagoListNew = new ArrayList<Detalletipoformadepago>();
			for (Detalletipoformadepago detalletipoformadepagoListNewDetalletipoformadepagoToAttach : detalletipoformadepagoListNew) {
				detalletipoformadepagoListNewDetalletipoformadepagoToAttach = em.getReference(
						detalletipoformadepagoListNewDetalletipoformadepagoToAttach.getClass(),
						detalletipoformadepagoListNewDetalletipoformadepagoToAttach.getId());
				attachedDetalletipoformadepagoListNew.add(detalletipoformadepagoListNewDetalletipoformadepagoToAttach);
			}
			detalletipoformadepagoListNew = attachedDetalletipoformadepagoListNew;
			formadepago.setDetalletipoformadepagoList(detalletipoformadepagoListNew);
			List<Entregaformadepago> attachedEntregaformadepagoListNew = new ArrayList<Entregaformadepago>();
			for (Entregaformadepago entregaformadepagoListNewEntregaformadepagoToAttach : entregaformadepagoListNew) {
				entregaformadepagoListNewEntregaformadepagoToAttach = em.getReference(
						entregaformadepagoListNewEntregaformadepagoToAttach.getClass(),
						entregaformadepagoListNewEntregaformadepagoToAttach.getId());
				attachedEntregaformadepagoListNew.add(entregaformadepagoListNewEntregaformadepagoToAttach);
			}
			entregaformadepagoListNew = attachedEntregaformadepagoListNew;
			formadepago.setEntregaformadepagoList(entregaformadepagoListNew);
			List<Transaccionformadepago> attachedTransaccionformadepagoListNew = new ArrayList<Transaccionformadepago>();
			for (Transaccionformadepago transaccionformadepagoListNewTransaccionformadepagoToAttach : transaccionformadepagoListNew) {
				transaccionformadepagoListNewTransaccionformadepagoToAttach = em.getReference(
						transaccionformadepagoListNewTransaccionformadepagoToAttach.getClass(),
						transaccionformadepagoListNewTransaccionformadepagoToAttach.getId());
				attachedTransaccionformadepagoListNew.add(transaccionformadepagoListNewTransaccionformadepagoToAttach);
			}
			transaccionformadepagoListNew = attachedTransaccionformadepagoListNew;
			formadepago.setTransaccionformadepagoList(transaccionformadepagoListNew);
			List<Morosidad> attachedMorosidadListNew = new ArrayList<Morosidad>();
			for (Morosidad morosidadListNewMorosidadToAttach : morosidadListNew) {
				morosidadListNewMorosidadToAttach = em.getReference(morosidadListNewMorosidadToAttach.getClass(),
						morosidadListNewMorosidadToAttach.getId());
				attachedMorosidadListNew.add(morosidadListNewMorosidadToAttach);
			}
			morosidadListNew = attachedMorosidadListNew;
			formadepago.setMorosidadList(morosidadListNew);
			List<Categorialinearetencion> attachedCategorialinearetencionListNew = new ArrayList<Categorialinearetencion>();
//			for (Categorialinearetencion categorialinearetencionListNewCategorialinearetencionToAttach : categorialinearetencionListNew) {
//				categorialinearetencionListNewCategorialinearetencionToAttach = em.getReference(
//						categorialinearetencionListNewCategorialinearetencionToAttach.getClass(),
//						categorialinearetencionListNewCategorialinearetencionToAttach.getId());
//				attachedCategorialinearetencionListNew
//						.add(categorialinearetencionListNewCategorialinearetencionToAttach);
//			}
//			categorialinearetencionListNew = attachedCategorialinearetencionListNew;
//			formadepago.setCategorialinearetencionList(categorialinearetencionListNew);
			List<Porcentajeimpuestoretencion> attachedPorcentajeimpuestoretencionListNew = new ArrayList<Porcentajeimpuestoretencion>();
			for (Porcentajeimpuestoretencion porcentajeimpuestoretencionListNewPorcentajeimpuestoretencionToAttach : porcentajeimpuestoretencionListNew) {
				porcentajeimpuestoretencionListNewPorcentajeimpuestoretencionToAttach = em.getReference(
						porcentajeimpuestoretencionListNewPorcentajeimpuestoretencionToAttach.getClass(),
						porcentajeimpuestoretencionListNewPorcentajeimpuestoretencionToAttach.getId());
				attachedPorcentajeimpuestoretencionListNew
						.add(porcentajeimpuestoretencionListNewPorcentajeimpuestoretencionToAttach);
			}
			porcentajeimpuestoretencionListNew = attachedPorcentajeimpuestoretencionListNew;
			formadepago.setPorcentajeimpuestoretencionList(porcentajeimpuestoretencionListNew);
			formadepago = em.merge(formadepago);
			for (Promocionformadepago promocionformadepagoListNewPromocionformadepago : promocionformadepagoListNew) {
				if (!promocionformadepagoListOld.contains(promocionformadepagoListNewPromocionformadepago)) {
					Formadepago oldIdFormaDePagoOfPromocionformadepagoListNewPromocionformadepago = promocionformadepagoListNewPromocionformadepago
							.getIdFormaDePago();
					promocionformadepagoListNewPromocionformadepago.setIdFormaDePago(formadepago);
					promocionformadepagoListNewPromocionformadepago = em
							.merge(promocionformadepagoListNewPromocionformadepago);
					if (oldIdFormaDePagoOfPromocionformadepagoListNewPromocionformadepago != null
							&& !oldIdFormaDePagoOfPromocionformadepagoListNewPromocionformadepago.equals(formadepago)) {
						oldIdFormaDePagoOfPromocionformadepagoListNewPromocionformadepago.getPromocionformadepagoList()
								.remove(promocionformadepagoListNewPromocionformadepago);
						oldIdFormaDePagoOfPromocionformadepagoListNewPromocionformadepago = em
								.merge(oldIdFormaDePagoOfPromocionformadepagoListNewPromocionformadepago);
					}
				}
			}
			for (Formadepagomoneda formadepagomonedaListNewFormadepagomoneda : formadepagomonedaListNew) {
				if (!formadepagomonedaListOld.contains(formadepagomonedaListNewFormadepagomoneda)) {
					Formadepago oldIdFormadepagoOfFormadepagomonedaListNewFormadepagomoneda = formadepagomonedaListNewFormadepagomoneda
							.getIdFormadepago();
					formadepagomonedaListNewFormadepagomoneda.setIdFormadepago(formadepago);
					formadepagomonedaListNewFormadepagomoneda = em.merge(formadepagomonedaListNewFormadepagomoneda);
					if (oldIdFormadepagoOfFormadepagomonedaListNewFormadepagomoneda != null
							&& !oldIdFormadepagoOfFormadepagomonedaListNewFormadepagomoneda.equals(formadepago)) {
						oldIdFormadepagoOfFormadepagomonedaListNewFormadepagomoneda.getFormadepagomonedaList().remove(
								formadepagomonedaListNewFormadepagomoneda);
						oldIdFormadepagoOfFormadepagomonedaListNewFormadepagomoneda = em
								.merge(oldIdFormadepagoOfFormadepagomonedaListNewFormadepagomoneda);
					}
				}
			}
			for (Detalletipoformadepago detalletipoformadepagoListNewDetalletipoformadepago : detalletipoformadepagoListNew) {
				if (!detalletipoformadepagoListOld.contains(detalletipoformadepagoListNewDetalletipoformadepago)) {
					Formadepago oldIdFormadepagoOfDetalletipoformadepagoListNewDetalletipoformadepago = detalletipoformadepagoListNewDetalletipoformadepago
							.getIdFormadepago();
					detalletipoformadepagoListNewDetalletipoformadepago.setIdFormadepago(formadepago);
					detalletipoformadepagoListNewDetalletipoformadepago = em
							.merge(detalletipoformadepagoListNewDetalletipoformadepago);
					if (oldIdFormadepagoOfDetalletipoformadepagoListNewDetalletipoformadepago != null
							&& !oldIdFormadepagoOfDetalletipoformadepagoListNewDetalletipoformadepago
									.equals(formadepago)) {
						oldIdFormadepagoOfDetalletipoformadepagoListNewDetalletipoformadepago
								.getDetalletipoformadepagoList().remove(
										detalletipoformadepagoListNewDetalletipoformadepago);
						oldIdFormadepagoOfDetalletipoformadepagoListNewDetalletipoformadepago = em
								.merge(oldIdFormadepagoOfDetalletipoformadepagoListNewDetalletipoformadepago);
					}
				}
			}
			for (Entregaformadepago entregaformadepagoListNewEntregaformadepago : entregaformadepagoListNew) {
				if (!entregaformadepagoListOld.contains(entregaformadepagoListNewEntregaformadepago)) {
					Formadepago oldIdFormadepagoOfEntregaformadepagoListNewEntregaformadepago = entregaformadepagoListNewEntregaformadepago
							.getIdFormadepago();
					entregaformadepagoListNewEntregaformadepago.setIdFormadepago(formadepago);
					entregaformadepagoListNewEntregaformadepago = em.merge(entregaformadepagoListNewEntregaformadepago);
					if (oldIdFormadepagoOfEntregaformadepagoListNewEntregaformadepago != null
							&& !oldIdFormadepagoOfEntregaformadepagoListNewEntregaformadepago.equals(formadepago)) {
						oldIdFormadepagoOfEntregaformadepagoListNewEntregaformadepago.getEntregaformadepagoList()
								.remove(entregaformadepagoListNewEntregaformadepago);
						oldIdFormadepagoOfEntregaformadepagoListNewEntregaformadepago = em
								.merge(oldIdFormadepagoOfEntregaformadepagoListNewEntregaformadepago);
					}
				}
			}
			for (Transaccionformadepago transaccionformadepagoListNewTransaccionformadepago : transaccionformadepagoListNew) {
				if (!transaccionformadepagoListOld.contains(transaccionformadepagoListNewTransaccionformadepago)) {
					Formadepago oldIdFormadepagoOfTransaccionformadepagoListNewTransaccionformadepago = transaccionformadepagoListNewTransaccionformadepago
							.getIdFormadepago();
					transaccionformadepagoListNewTransaccionformadepago.setIdFormadepago(formadepago);
					transaccionformadepagoListNewTransaccionformadepago = em
							.merge(transaccionformadepagoListNewTransaccionformadepago);
					if (oldIdFormadepagoOfTransaccionformadepagoListNewTransaccionformadepago != null
							&& !oldIdFormadepagoOfTransaccionformadepagoListNewTransaccionformadepago
									.equals(formadepago)) {
						oldIdFormadepagoOfTransaccionformadepagoListNewTransaccionformadepago
								.getTransaccionformadepagoList().remove(
										transaccionformadepagoListNewTransaccionformadepago);
						oldIdFormadepagoOfTransaccionformadepagoListNewTransaccionformadepago = em
								.merge(oldIdFormadepagoOfTransaccionformadepagoListNewTransaccionformadepago);
					}
				}
			}
			for (Morosidad morosidadListNewMorosidad : morosidadListNew) {
				if (!morosidadListOld.contains(morosidadListNewMorosidad)) {
					Formadepago oldIdFormadepagoOfMorosidadListNewMorosidad = morosidadListNewMorosidad
							.getIdFormadepago();
					morosidadListNewMorosidad.setIdFormadepago(formadepago);
					morosidadListNewMorosidad = em.merge(morosidadListNewMorosidad);
					if (oldIdFormadepagoOfMorosidadListNewMorosidad != null
							&& !oldIdFormadepagoOfMorosidadListNewMorosidad.equals(formadepago)) {
						oldIdFormadepagoOfMorosidadListNewMorosidad.getMorosidadList()
								.remove(morosidadListNewMorosidad);
						oldIdFormadepagoOfMorosidadListNewMorosidad = em
								.merge(oldIdFormadepagoOfMorosidadListNewMorosidad);
					}
				}
			}
//			for (Categorialinearetencion categorialinearetencionListNewCategorialinearetencion : categorialinearetencionListNew) {
//				if (!categorialinearetencionListOld.contains(categorialinearetencionListNewCategorialinearetencion)) {
//					Formadepago oldIdFormadepagoOfCategorialinearetencionListNewCategorialinearetencion = categorialinearetencionListNewCategorialinearetencion
//							.getIdFormadepago();
//					categorialinearetencionListNewCategorialinearetencion.setIdFormadepago(formadepago);
//					categorialinearetencionListNewCategorialinearetencion = em
//							.merge(categorialinearetencionListNewCategorialinearetencion);
//					if (oldIdFormadepagoOfCategorialinearetencionListNewCategorialinearetencion != null
//							&& !oldIdFormadepagoOfCategorialinearetencionListNewCategorialinearetencion
//									.equals(formadepago)) {
//						oldIdFormadepagoOfCategorialinearetencionListNewCategorialinearetencion
//								.getCategorialinearetencionList().remove(
//										categorialinearetencionListNewCategorialinearetencion);
//						oldIdFormadepagoOfCategorialinearetencionListNewCategorialinearetencion = em
//								.merge(oldIdFormadepagoOfCategorialinearetencionListNewCategorialinearetencion);
//					}
//				}
//			}
			for (Porcentajeimpuestoretencion porcentajeimpuestoretencionListNewPorcentajeimpuestoretencion : porcentajeimpuestoretencionListNew) {
				if (!porcentajeimpuestoretencionListOld
						.contains(porcentajeimpuestoretencionListNewPorcentajeimpuestoretencion)) {
					Formadepago oldIdFormadepagoOfPorcentajeimpuestoretencionListNewPorcentajeimpuestoretencion = porcentajeimpuestoretencionListNewPorcentajeimpuestoretencion
							.getIdFormadepago();
					porcentajeimpuestoretencionListNewPorcentajeimpuestoretencion.setIdFormadepago(formadepago);
					porcentajeimpuestoretencionListNewPorcentajeimpuestoretencion = em
							.merge(porcentajeimpuestoretencionListNewPorcentajeimpuestoretencion);
					if (oldIdFormadepagoOfPorcentajeimpuestoretencionListNewPorcentajeimpuestoretencion != null
							&& !oldIdFormadepagoOfPorcentajeimpuestoretencionListNewPorcentajeimpuestoretencion
									.equals(formadepago)) {
						oldIdFormadepagoOfPorcentajeimpuestoretencionListNewPorcentajeimpuestoretencion
								.getPorcentajeimpuestoretencionList().remove(
										porcentajeimpuestoretencionListNewPorcentajeimpuestoretencion);
						oldIdFormadepagoOfPorcentajeimpuestoretencionListNewPorcentajeimpuestoretencion = em
								.merge(oldIdFormadepagoOfPorcentajeimpuestoretencionListNewPorcentajeimpuestoretencion);
					}
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = formadepago.getId();
				if (findFormadepago(id) == null) {
					throw new NonexistentEntityException("The formadepago with id " + id + " no longer exists.");
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
			Formadepago formadepago;
			try {
				formadepago = em.getReference(Formadepago.class, id);
				formadepago.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException("The formadepago with id " + id + " no longer exists.", enfe);
			}
			List<String> illegalOrphanMessages = null;
			List<Promocionformadepago> promocionformadepagoListOrphanCheck = formadepago.getPromocionformadepagoList();
			for (Promocionformadepago promocionformadepagoListOrphanCheckPromocionformadepago : promocionformadepagoListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages.add("This Formadepago (" + formadepago
						+ ") cannot be destroyed since the Promocionformadepago "
						+ promocionformadepagoListOrphanCheckPromocionformadepago
						+ " in its promocionformadepagoList field has a non-nullable idFormaDePago field.");
			}
			List<Formadepagomoneda> formadepagomonedaListOrphanCheck = formadepago.getFormadepagomonedaList();
			for (Formadepagomoneda formadepagomonedaListOrphanCheckFormadepagomoneda : formadepagomonedaListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages.add("This Formadepago (" + formadepago
						+ ") cannot be destroyed since the Formadepagomoneda "
						+ formadepagomonedaListOrphanCheckFormadepagomoneda
						+ " in its formadepagomonedaList field has a non-nullable idFormadepago field.");
			}
			List<Detalletipoformadepago> detalletipoformadepagoListOrphanCheck = formadepago
					.getDetalletipoformadepagoList();
			for (Detalletipoformadepago detalletipoformadepagoListOrphanCheckDetalletipoformadepago : detalletipoformadepagoListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages.add("This Formadepago (" + formadepago
						+ ") cannot be destroyed since the Detalletipoformadepago "
						+ detalletipoformadepagoListOrphanCheckDetalletipoformadepago
						+ " in its detalletipoformadepagoList field has a non-nullable idFormadepago field.");
			}
			List<Entregaformadepago> entregaformadepagoListOrphanCheck = formadepago.getEntregaformadepagoList();
			for (Entregaformadepago entregaformadepagoListOrphanCheckEntregaformadepago : entregaformadepagoListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages.add("This Formadepago (" + formadepago
						+ ") cannot be destroyed since the Entregaformadepago "
						+ entregaformadepagoListOrphanCheckEntregaformadepago
						+ " in its entregaformadepagoList field has a non-nullable idFormadepago field.");
			}
			List<Transaccionformadepago> transaccionformadepagoListOrphanCheck = formadepago
					.getTransaccionformadepagoList();
			for (Transaccionformadepago transaccionformadepagoListOrphanCheckTransaccionformadepago : transaccionformadepagoListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages.add("This Formadepago (" + formadepago
						+ ") cannot be destroyed since the Transaccionformadepago "
						+ transaccionformadepagoListOrphanCheckTransaccionformadepago
						+ " in its transaccionformadepagoList field has a non-nullable idFormadepago field.");
			}
			List<Morosidad> morosidadListOrphanCheck = formadepago.getMorosidadList();
			for (Morosidad morosidadListOrphanCheckMorosidad : morosidadListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages.add("This Formadepago (" + formadepago
						+ ") cannot be destroyed since the Morosidad " + morosidadListOrphanCheckMorosidad
						+ " in its morosidadList field has a non-nullable idFormadepago field.");
			}
//			List<Categorialinearetencion> categorialinearetencionListOrphanCheck = formadepago
//					.getCategorialinearetencionList();
//			for (Categorialinearetencion categorialinearetencionListOrphanCheckCategorialinearetencion : categorialinearetencionListOrphanCheck) {
//				if (illegalOrphanMessages == null) {
//					illegalOrphanMessages = new ArrayList<String>();
//				}
//				illegalOrphanMessages.add("This Formadepago (" + formadepago
//						+ ") cannot be destroyed since the Categorialinearetencion "
//						+ categorialinearetencionListOrphanCheckCategorialinearetencion
//						+ " in its categorialinearetencionList field has a non-nullable idFormadepago field.");
//			}
			List<Porcentajeimpuestoretencion> porcentajeimpuestoretencionListOrphanCheck = formadepago
					.getPorcentajeimpuestoretencionList();
			for (Porcentajeimpuestoretencion porcentajeimpuestoretencionListOrphanCheckPorcentajeimpuestoretencion : porcentajeimpuestoretencionListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages.add("This Formadepago (" + formadepago
						+ ") cannot be destroyed since the Porcentajeimpuestoretencion "
						+ porcentajeimpuestoretencionListOrphanCheckPorcentajeimpuestoretencion
						+ " in its porcentajeimpuestoretencionList field has a non-nullable idFormadepago field.");
			}
			if (illegalOrphanMessages != null) {
				throw new IllegalOrphanException(illegalOrphanMessages);
			}
			em.remove(formadepago);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method findFormadepagoEntities.
	 * 
	 * @return List<Formadepago>
	 */
	public List<Formadepago> findFormadepagoEntities() {
		return findFormadepagoEntities(true, -1, -1);
	}

	/**
	 * Method findFormadepagoEntities.
	 * 
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Formadepago>
	 */
	public List<Formadepago> findFormadepagoEntities(int maxResults, int firstResult) {
		return findFormadepagoEntities(false, maxResults, firstResult);
	}

	/**
	 * Method findFormadepagoEntities.
	 * 
	 * @param all
	 *            boolean
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Formadepago>
	 */
	private List<Formadepago> findFormadepagoEntities(boolean all, int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Formadepago.class));
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
	 * Method findFormadepago.
	 * 
	 * @param id
	 *            Long
	 * @return Formadepago
	 */
	public Formadepago findFormadepago(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Formadepago.class, id);
		} finally {
			em.close();
		}
	}

	/**
	 * Method getFormadepagoCount.
	 * 
	 * @return int
	 */
	public int getFormadepagoCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Formadepago> rt = cq.from(Formadepago.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}
