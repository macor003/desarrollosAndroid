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
public class FormadepagoJpaController implements Serializable {

	/**
	 * Constructor for FormadepagoJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
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
	 * @param formadepago
	 *            Formadepago
	 * @throws PreexistingEntityException
	 * @throws Exception
	 */
	public void create(Formadepago formadepago)
			throws PreexistingEntityException, Exception {
		if (formadepago.getFormadepagomovacreenciaList() == null) {
			formadepago
					.setFormadepagomovacreenciaList(new ArrayList<Formadepagomovacreencia>());
		}
		if (formadepago.getTransaccionformadepagoList() == null) {
			formadepago
					.setTransaccionformadepagoList(new ArrayList<Transaccionformadepago>());
		}
		if (formadepago.getPorcentajeimpuestoretencionList() == null) {
			formadepago
					.setPorcentajeimpuestoretencionList(new ArrayList<Porcentajeimpuestoretencion>());
		}
		if (formadepago.getDetalletipoformadepagoList() == null) {
			formadepago
					.setDetalletipoformadepagoList(new ArrayList<Detalletipoformadepago>());
		}
		if (formadepago.getMorosidadList() == null) {
			formadepago.setMorosidadList(new ArrayList<Morosidad>());
		}
		if (formadepago.getEntregaformadepagoList() == null) {
			formadepago
					.setEntregaformadepagoList(new ArrayList<Entregaformadepago>());
		}
//		if (formadepago.getCategorialinearetencionList() == null) {
//			formadepago
//					.setCategorialinearetencionList(new ArrayList<Categorialinearetencion>());
//		}
		if (formadepago.getAcreenciamovimientoformadepagoList() == null) {
			formadepago
					.setAcreenciamovimientoformadepagoList(new ArrayList<Acreenciamovimientoformadepago>());
		}
		if (formadepago.getPromocionformadepagoList() == null) {
			formadepago
					.setPromocionformadepagoList(new ArrayList<Promocionformadepago>());
		}
		if (formadepago.getAbonoformadepagoList() == null) {
			formadepago
					.setAbonoformadepagoList(new ArrayList<Abonoformadepago>());
		}
		if (formadepago.getAcreenciamovimientoList() == null) {
			formadepago
					.setAcreenciamovimientoList(new ArrayList<Acreenciamovimiento>());
		}
		if (formadepago.getFormadepagomonedaList() == null) {
			formadepago
					.setFormadepagomonedaList(new ArrayList<Formadepagomoneda>());
		}
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			List<Formadepagomovacreencia> attachedFormadepagomovacreenciaList = new ArrayList<Formadepagomovacreencia>();
			for (Formadepagomovacreencia formadepagomovacreenciaListFormadepagomovacreenciaToAttach : formadepago
					.getFormadepagomovacreenciaList()) {
				formadepagomovacreenciaListFormadepagomovacreenciaToAttach = em
						.getReference(
								formadepagomovacreenciaListFormadepagomovacreenciaToAttach
										.getClass(),
								formadepagomovacreenciaListFormadepagomovacreenciaToAttach
										.getId());
				attachedFormadepagomovacreenciaList
						.add(formadepagomovacreenciaListFormadepagomovacreenciaToAttach);
			}
			formadepago
					.setFormadepagomovacreenciaList(attachedFormadepagomovacreenciaList);
			List<Transaccionformadepago> attachedTransaccionformadepagoList = new ArrayList<Transaccionformadepago>();
			for (Transaccionformadepago transaccionformadepagoListTransaccionformadepagoToAttach : formadepago
					.getTransaccionformadepagoList()) {
				transaccionformadepagoListTransaccionformadepagoToAttach = em
						.getReference(
								transaccionformadepagoListTransaccionformadepagoToAttach
										.getClass(),
								transaccionformadepagoListTransaccionformadepagoToAttach
										.getId());
				attachedTransaccionformadepagoList
						.add(transaccionformadepagoListTransaccionformadepagoToAttach);
			}
			formadepago
					.setTransaccionformadepagoList(attachedTransaccionformadepagoList);
			List<Porcentajeimpuestoretencion> attachedPorcentajeimpuestoretencionList = new ArrayList<Porcentajeimpuestoretencion>();
			for (Porcentajeimpuestoretencion porcentajeimpuestoretencionListPorcentajeimpuestoretencionToAttach : formadepago
					.getPorcentajeimpuestoretencionList()) {
				porcentajeimpuestoretencionListPorcentajeimpuestoretencionToAttach = em
						.getReference(
								porcentajeimpuestoretencionListPorcentajeimpuestoretencionToAttach
										.getClass(),
								porcentajeimpuestoretencionListPorcentajeimpuestoretencionToAttach
										.getId());
				attachedPorcentajeimpuestoretencionList
						.add(porcentajeimpuestoretencionListPorcentajeimpuestoretencionToAttach);
			}
			formadepago
					.setPorcentajeimpuestoretencionList(attachedPorcentajeimpuestoretencionList);
			List<Detalletipoformadepago> attachedDetalletipoformadepagoList = new ArrayList<Detalletipoformadepago>();
			for (Detalletipoformadepago detalletipoformadepagoListDetalletipoformadepagoToAttach : formadepago
					.getDetalletipoformadepagoList()) {
				detalletipoformadepagoListDetalletipoformadepagoToAttach = em
						.getReference(
								detalletipoformadepagoListDetalletipoformadepagoToAttach
										.getClass(),
								detalletipoformadepagoListDetalletipoformadepagoToAttach
										.getId());
				attachedDetalletipoformadepagoList
						.add(detalletipoformadepagoListDetalletipoformadepagoToAttach);
			}
			formadepago
					.setDetalletipoformadepagoList(attachedDetalletipoformadepagoList);
			List<Morosidad> attachedMorosidadList = new ArrayList<Morosidad>();
			for (Morosidad morosidadListMorosidadToAttach : formadepago
					.getMorosidadList()) {
				morosidadListMorosidadToAttach = em.getReference(
						morosidadListMorosidadToAttach.getClass(),
						morosidadListMorosidadToAttach.getId());
				attachedMorosidadList.add(morosidadListMorosidadToAttach);
			}
			formadepago.setMorosidadList(attachedMorosidadList);
			List<Entregaformadepago> attachedEntregaformadepagoList = new ArrayList<Entregaformadepago>();
			for (Entregaformadepago entregaformadepagoListEntregaformadepagoToAttach : formadepago
					.getEntregaformadepagoList()) {
				entregaformadepagoListEntregaformadepagoToAttach = em
						.getReference(
								entregaformadepagoListEntregaformadepagoToAttach
										.getClass(),
								entregaformadepagoListEntregaformadepagoToAttach
										.getId());
				attachedEntregaformadepagoList
						.add(entregaformadepagoListEntregaformadepagoToAttach);
			}
			formadepago
					.setEntregaformadepagoList(attachedEntregaformadepagoList);
			List<Categorialinearetencion> attachedCategorialinearetencionList = new ArrayList<Categorialinearetencion>();
//			for (Categorialinearetencion categorialinearetencionListCategorialinearetencionToAttach : formadepago
//					.getCategorialinearetencionList()) {
//				categorialinearetencionListCategorialinearetencionToAttach = em
//						.getReference(
//								categorialinearetencionListCategorialinearetencionToAttach
//										.getClass(),
//								categorialinearetencionListCategorialinearetencionToAttach
//										.getId());
//				attachedCategorialinearetencionList
//						.add(categorialinearetencionListCategorialinearetencionToAttach);
//			}
//			formadepago
//					.setCategorialinearetencionList(attachedCategorialinearetencionList);
			List<Acreenciamovimientoformadepago> attachedAcreenciamovimientoformadepagoList = new ArrayList<Acreenciamovimientoformadepago>();
			for (Acreenciamovimientoformadepago acreenciamovimientoformadepagoListAcreenciamovimientoformadepagoToAttach : formadepago
					.getAcreenciamovimientoformadepagoList()) {
				acreenciamovimientoformadepagoListAcreenciamovimientoformadepagoToAttach = em
						.getReference(
								acreenciamovimientoformadepagoListAcreenciamovimientoformadepagoToAttach
										.getClass(),
								acreenciamovimientoformadepagoListAcreenciamovimientoformadepagoToAttach
										.getId());
				attachedAcreenciamovimientoformadepagoList
						.add(acreenciamovimientoformadepagoListAcreenciamovimientoformadepagoToAttach);
			}
			formadepago
					.setAcreenciamovimientoformadepagoList(attachedAcreenciamovimientoformadepagoList);
			List<Promocionformadepago> attachedPromocionformadepagoList = new ArrayList<Promocionformadepago>();
			for (Promocionformadepago promocionformadepagoListPromocionformadepagoToAttach : formadepago
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
			formadepago
					.setPromocionformadepagoList(attachedPromocionformadepagoList);
			List<Abonoformadepago> attachedAbonoformadepagoList = new ArrayList<Abonoformadepago>();
			for (Abonoformadepago abonoformadepagoListAbonoformadepagoToAttach : formadepago
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
			formadepago.setAbonoformadepagoList(attachedAbonoformadepagoList);
			List<Acreenciamovimiento> attachedAcreenciamovimientoList = new ArrayList<Acreenciamovimiento>();
			for (Acreenciamovimiento acreenciamovimientoListAcreenciamovimientoToAttach : formadepago
					.getAcreenciamovimientoList()) {
				acreenciamovimientoListAcreenciamovimientoToAttach = em
						.getReference(
								acreenciamovimientoListAcreenciamovimientoToAttach
										.getClass(),
								acreenciamovimientoListAcreenciamovimientoToAttach
										.getIpaId());
				attachedAcreenciamovimientoList
						.add(acreenciamovimientoListAcreenciamovimientoToAttach);
			}
			formadepago
					.setAcreenciamovimientoList(attachedAcreenciamovimientoList);
			List<Formadepagomoneda> attachedFormadepagomonedaList = new ArrayList<Formadepagomoneda>();
			for (Formadepagomoneda formadepagomonedaListFormadepagomonedaToAttach : formadepago
					.getFormadepagomonedaList()) {
				formadepagomonedaListFormadepagomonedaToAttach = em
						.getReference(
								formadepagomonedaListFormadepagomonedaToAttach
										.getClass(),
								formadepagomonedaListFormadepagomonedaToAttach
										.getId());
				attachedFormadepagomonedaList
						.add(formadepagomonedaListFormadepagomonedaToAttach);
			}
			formadepago.setFormadepagomonedaList(attachedFormadepagomonedaList);
			em.persist(formadepago);
			for (Formadepagomovacreencia formadepagomovacreenciaListFormadepagomovacreencia : formadepago
					.getFormadepagomovacreenciaList()) {
				Formadepago oldIdFormadepagoOfFormadepagomovacreenciaListFormadepagomovacreencia = formadepagomovacreenciaListFormadepagomovacreencia
						.getIdFormadepago();
				formadepagomovacreenciaListFormadepagomovacreencia
						.setIdFormadepago(formadepago);
				formadepagomovacreenciaListFormadepagomovacreencia = em
						.merge(formadepagomovacreenciaListFormadepagomovacreencia);
				if (oldIdFormadepagoOfFormadepagomovacreenciaListFormadepagomovacreencia != null) {
					oldIdFormadepagoOfFormadepagomovacreenciaListFormadepagomovacreencia
							.getFormadepagomovacreenciaList()
							.remove(formadepagomovacreenciaListFormadepagomovacreencia);
					oldIdFormadepagoOfFormadepagomovacreenciaListFormadepagomovacreencia = em
							.merge(oldIdFormadepagoOfFormadepagomovacreenciaListFormadepagomovacreencia);
				}
			}
			for (Transaccionformadepago transaccionformadepagoListTransaccionformadepago : formadepago
					.getTransaccionformadepagoList()) {
				Formadepago oldIdFormadepagoOfTransaccionformadepagoListTransaccionformadepago = transaccionformadepagoListTransaccionformadepago
						.getIdFormadepago();
				transaccionformadepagoListTransaccionformadepago
						.setIdFormadepago(formadepago);
				transaccionformadepagoListTransaccionformadepago = em
						.merge(transaccionformadepagoListTransaccionformadepago);
				if (oldIdFormadepagoOfTransaccionformadepagoListTransaccionformadepago != null) {
					oldIdFormadepagoOfTransaccionformadepagoListTransaccionformadepago
							.getTransaccionformadepagoList()
							.remove(transaccionformadepagoListTransaccionformadepago);
					oldIdFormadepagoOfTransaccionformadepagoListTransaccionformadepago = em
							.merge(oldIdFormadepagoOfTransaccionformadepagoListTransaccionformadepago);
				}
			}
			for (Porcentajeimpuestoretencion porcentajeimpuestoretencionListPorcentajeimpuestoretencion : formadepago
					.getPorcentajeimpuestoretencionList()) {
				Formadepago oldIdFormadepagoOfPorcentajeimpuestoretencionListPorcentajeimpuestoretencion = porcentajeimpuestoretencionListPorcentajeimpuestoretencion
						.getIdFormadepago();
				porcentajeimpuestoretencionListPorcentajeimpuestoretencion
						.setIdFormadepago(formadepago);
				porcentajeimpuestoretencionListPorcentajeimpuestoretencion = em
						.merge(porcentajeimpuestoretencionListPorcentajeimpuestoretencion);
				if (oldIdFormadepagoOfPorcentajeimpuestoretencionListPorcentajeimpuestoretencion != null) {
					oldIdFormadepagoOfPorcentajeimpuestoretencionListPorcentajeimpuestoretencion
							.getPorcentajeimpuestoretencionList()
							.remove(porcentajeimpuestoretencionListPorcentajeimpuestoretencion);
					oldIdFormadepagoOfPorcentajeimpuestoretencionListPorcentajeimpuestoretencion = em
							.merge(oldIdFormadepagoOfPorcentajeimpuestoretencionListPorcentajeimpuestoretencion);
				}
			}
			for (Detalletipoformadepago detalletipoformadepagoListDetalletipoformadepago : formadepago
					.getDetalletipoformadepagoList()) {
				Formadepago oldIdFormadepagoOfDetalletipoformadepagoListDetalletipoformadepago = detalletipoformadepagoListDetalletipoformadepago
						.getIdFormadepago();
				detalletipoformadepagoListDetalletipoformadepago
						.setIdFormadepago(formadepago);
				detalletipoformadepagoListDetalletipoformadepago = em
						.merge(detalletipoformadepagoListDetalletipoformadepago);
				if (oldIdFormadepagoOfDetalletipoformadepagoListDetalletipoformadepago != null) {
					oldIdFormadepagoOfDetalletipoformadepagoListDetalletipoformadepago
							.getDetalletipoformadepagoList()
							.remove(detalletipoformadepagoListDetalletipoformadepago);
					oldIdFormadepagoOfDetalletipoformadepagoListDetalletipoformadepago = em
							.merge(oldIdFormadepagoOfDetalletipoformadepagoListDetalletipoformadepago);
				}
			}
			for (Morosidad morosidadListMorosidad : formadepago
					.getMorosidadList()) {
				Formadepago oldIdFormadepagoOfMorosidadListMorosidad = morosidadListMorosidad
						.getIdFormadepago();
				morosidadListMorosidad.setIdFormadepago(formadepago);
				morosidadListMorosidad = em.merge(morosidadListMorosidad);
				if (oldIdFormadepagoOfMorosidadListMorosidad != null) {
					oldIdFormadepagoOfMorosidadListMorosidad.getMorosidadList()
							.remove(morosidadListMorosidad);
					oldIdFormadepagoOfMorosidadListMorosidad = em
							.merge(oldIdFormadepagoOfMorosidadListMorosidad);
				}
			}
			for (Entregaformadepago entregaformadepagoListEntregaformadepago : formadepago
					.getEntregaformadepagoList()) {
				Formadepago oldIdFormadepagoOfEntregaformadepagoListEntregaformadepago = entregaformadepagoListEntregaformadepago
						.getIdFormadepago();
				entregaformadepagoListEntregaformadepago
						.setIdFormadepago(formadepago);
				entregaformadepagoListEntregaformadepago = em
						.merge(entregaformadepagoListEntregaformadepago);
				if (oldIdFormadepagoOfEntregaformadepagoListEntregaformadepago != null) {
					oldIdFormadepagoOfEntregaformadepagoListEntregaformadepago
							.getEntregaformadepagoList().remove(
									entregaformadepagoListEntregaformadepago);
					oldIdFormadepagoOfEntregaformadepagoListEntregaformadepago = em
							.merge(oldIdFormadepagoOfEntregaformadepagoListEntregaformadepago);
				}
			}
//			for (Categorialinearetencion categorialinearetencionListCategorialinearetencion : formadepago
//					.getCategorialinearetencionList()) {
//				Formadepago oldIdFormadepagoOfCategorialinearetencionListCategorialinearetencion = categorialinearetencionListCategorialinearetencion
//						.getIdFormadepago();
//				categorialinearetencionListCategorialinearetencion
//						.setIdFormadepago(formadepago);
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
			for (Acreenciamovimientoformadepago acreenciamovimientoformadepagoListAcreenciamovimientoformadepago : formadepago
					.getAcreenciamovimientoformadepagoList()) {
				Formadepago oldIdFormadepagoOfAcreenciamovimientoformadepagoListAcreenciamovimientoformadepago = acreenciamovimientoformadepagoListAcreenciamovimientoformadepago
						.getIdFormadepago();
				acreenciamovimientoformadepagoListAcreenciamovimientoformadepago
						.setIdFormadepago(formadepago);
				acreenciamovimientoformadepagoListAcreenciamovimientoformadepago = em
						.merge(acreenciamovimientoformadepagoListAcreenciamovimientoformadepago);
				if (oldIdFormadepagoOfAcreenciamovimientoformadepagoListAcreenciamovimientoformadepago != null) {
					oldIdFormadepagoOfAcreenciamovimientoformadepagoListAcreenciamovimientoformadepago
							.getAcreenciamovimientoformadepagoList()
							.remove(acreenciamovimientoformadepagoListAcreenciamovimientoformadepago);
					oldIdFormadepagoOfAcreenciamovimientoformadepagoListAcreenciamovimientoformadepago = em
							.merge(oldIdFormadepagoOfAcreenciamovimientoformadepagoListAcreenciamovimientoformadepago);
				}
			}
			for (Promocionformadepago promocionformadepagoListPromocionformadepago : formadepago
					.getPromocionformadepagoList()) {
				Formadepago oldIdFormadepagoOfPromocionformadepagoListPromocionformadepago = promocionformadepagoListPromocionformadepago
						.getIdFormadepago();
				promocionformadepagoListPromocionformadepago
						.setIdFormadepago(formadepago);
				promocionformadepagoListPromocionformadepago = em
						.merge(promocionformadepagoListPromocionformadepago);
				if (oldIdFormadepagoOfPromocionformadepagoListPromocionformadepago != null) {
					oldIdFormadepagoOfPromocionformadepagoListPromocionformadepago
							.getPromocionformadepagoList()
							.remove(promocionformadepagoListPromocionformadepago);
					oldIdFormadepagoOfPromocionformadepagoListPromocionformadepago = em
							.merge(oldIdFormadepagoOfPromocionformadepagoListPromocionformadepago);
				}
			}
			for (Abonoformadepago abonoformadepagoListAbonoformadepago : formadepago
					.getAbonoformadepagoList()) {
				Formadepago oldIdFormadepagoOfAbonoformadepagoListAbonoformadepago = abonoformadepagoListAbonoformadepago
						.getIdFormadepago();
				abonoformadepagoListAbonoformadepago
						.setIdFormadepago(formadepago);
				abonoformadepagoListAbonoformadepago = em
						.merge(abonoformadepagoListAbonoformadepago);
				if (oldIdFormadepagoOfAbonoformadepagoListAbonoformadepago != null) {
					oldIdFormadepagoOfAbonoformadepagoListAbonoformadepago
							.getAbonoformadepagoList().remove(
									abonoformadepagoListAbonoformadepago);
					oldIdFormadepagoOfAbonoformadepagoListAbonoformadepago = em
							.merge(oldIdFormadepagoOfAbonoformadepagoListAbonoformadepago);
				}
			}
			for (Acreenciamovimiento acreenciamovimientoListAcreenciamovimiento : formadepago
					.getAcreenciamovimientoList()) {
				Formadepago oldIdFormadepagoOfAcreenciamovimientoListAcreenciamovimiento = acreenciamovimientoListAcreenciamovimiento
						.getIdFormadepago();
				acreenciamovimientoListAcreenciamovimiento
						.setIdFormadepago(formadepago);
				acreenciamovimientoListAcreenciamovimiento = em
						.merge(acreenciamovimientoListAcreenciamovimiento);
				if (oldIdFormadepagoOfAcreenciamovimientoListAcreenciamovimiento != null) {
					oldIdFormadepagoOfAcreenciamovimientoListAcreenciamovimiento
							.getAcreenciamovimientoList().remove(
									acreenciamovimientoListAcreenciamovimiento);
					oldIdFormadepagoOfAcreenciamovimientoListAcreenciamovimiento = em
							.merge(oldIdFormadepagoOfAcreenciamovimientoListAcreenciamovimiento);
				}
			}
			for (Formadepagomoneda formadepagomonedaListFormadepagomoneda : formadepago
					.getFormadepagomonedaList()) {
				Formadepago oldIdFormadepagoOfFormadepagomonedaListFormadepagomoneda = formadepagomonedaListFormadepagomoneda
						.getIdFormadepago();
				formadepagomonedaListFormadepagomoneda
						.setIdFormadepago(formadepago);
				formadepagomonedaListFormadepagomoneda = em
						.merge(formadepagomonedaListFormadepagomoneda);
				if (oldIdFormadepagoOfFormadepagomonedaListFormadepagomoneda != null) {
					oldIdFormadepagoOfFormadepagomonedaListFormadepagomoneda
							.getFormadepagomonedaList().remove(
									formadepagomonedaListFormadepagomoneda);
					oldIdFormadepagoOfFormadepagomonedaListFormadepagomoneda = em
							.merge(oldIdFormadepagoOfFormadepagomonedaListFormadepagomoneda);
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findFormadepago(formadepago.getId()) != null) {
				throw new PreexistingEntityException("Formadepago "
						+ formadepago + " already exists.", ex);
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
	public void edit(Formadepago formadepago) throws IllegalOrphanException,
			NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Formadepago persistentFormadepago = em.find(Formadepago.class,
					formadepago.getId());
			List<Formadepagomovacreencia> formadepagomovacreenciaListOld = persistentFormadepago
					.getFormadepagomovacreenciaList();
			List<Formadepagomovacreencia> formadepagomovacreenciaListNew = formadepago
					.getFormadepagomovacreenciaList();
			List<Transaccionformadepago> transaccionformadepagoListOld = persistentFormadepago
					.getTransaccionformadepagoList();
			List<Transaccionformadepago> transaccionformadepagoListNew = formadepago
					.getTransaccionformadepagoList();
			List<Porcentajeimpuestoretencion> porcentajeimpuestoretencionListOld = persistentFormadepago
					.getPorcentajeimpuestoretencionList();
			List<Porcentajeimpuestoretencion> porcentajeimpuestoretencionListNew = formadepago
					.getPorcentajeimpuestoretencionList();
			List<Detalletipoformadepago> detalletipoformadepagoListOld = persistentFormadepago
					.getDetalletipoformadepagoList();
			List<Detalletipoformadepago> detalletipoformadepagoListNew = formadepago
					.getDetalletipoformadepagoList();
			List<Morosidad> morosidadListOld = persistentFormadepago
					.getMorosidadList();
			List<Morosidad> morosidadListNew = formadepago.getMorosidadList();
			List<Entregaformadepago> entregaformadepagoListOld = persistentFormadepago
					.getEntregaformadepagoList();
			List<Entregaformadepago> entregaformadepagoListNew = formadepago
					.getEntregaformadepagoList();
//			List<Categorialinearetencion> categorialinearetencionListOld = persistentFormadepago
//					.getCategorialinearetencionList();
//			List<Categorialinearetencion> categorialinearetencionListNew = formadepago
//					.getCategorialinearetencionList();
			List<Acreenciamovimientoformadepago> acreenciamovimientoformadepagoListOld = persistentFormadepago
					.getAcreenciamovimientoformadepagoList();
			List<Acreenciamovimientoformadepago> acreenciamovimientoformadepagoListNew = formadepago
					.getAcreenciamovimientoformadepagoList();
			List<Promocionformadepago> promocionformadepagoListOld = persistentFormadepago
					.getPromocionformadepagoList();
			List<Promocionformadepago> promocionformadepagoListNew = formadepago
					.getPromocionformadepagoList();
			List<Abonoformadepago> abonoformadepagoListOld = persistentFormadepago
					.getAbonoformadepagoList();
			List<Abonoformadepago> abonoformadepagoListNew = formadepago
					.getAbonoformadepagoList();
			List<Acreenciamovimiento> acreenciamovimientoListOld = persistentFormadepago
					.getAcreenciamovimientoList();
			List<Acreenciamovimiento> acreenciamovimientoListNew = formadepago
					.getAcreenciamovimientoList();
			List<Formadepagomoneda> formadepagomonedaListOld = persistentFormadepago
					.getFormadepagomonedaList();
			List<Formadepagomoneda> formadepagomonedaListNew = formadepago
					.getFormadepagomonedaList();
			List<String> illegalOrphanMessages = null;
			for (Transaccionformadepago transaccionformadepagoListOldTransaccionformadepago : transaccionformadepagoListOld) {
				if (!transaccionformadepagoListNew
						.contains(transaccionformadepagoListOldTransaccionformadepago)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages
							.add("You must retain Transaccionformadepago "
									+ transaccionformadepagoListOldTransaccionformadepago
									+ " since its idFormadepago field is not nullable.");
				}
			}
			for (Porcentajeimpuestoretencion porcentajeimpuestoretencionListOldPorcentajeimpuestoretencion : porcentajeimpuestoretencionListOld) {
				if (!porcentajeimpuestoretencionListNew
						.contains(porcentajeimpuestoretencionListOldPorcentajeimpuestoretencion)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages
							.add("You must retain Porcentajeimpuestoretencion "
									+ porcentajeimpuestoretencionListOldPorcentajeimpuestoretencion
									+ " since its idFormadepago field is not nullable.");
				}
			}
			for (Detalletipoformadepago detalletipoformadepagoListOldDetalletipoformadepago : detalletipoformadepagoListOld) {
				if (!detalletipoformadepagoListNew
						.contains(detalletipoformadepagoListOldDetalletipoformadepago)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages
							.add("You must retain Detalletipoformadepago "
									+ detalletipoformadepagoListOldDetalletipoformadepago
									+ " since its idFormadepago field is not nullable.");
				}
			}
			for (Morosidad morosidadListOldMorosidad : morosidadListOld) {
				if (!morosidadListNew.contains(morosidadListOldMorosidad)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages
							.add("You must retain Morosidad "
									+ morosidadListOldMorosidad
									+ " since its idFormadepago field is not nullable.");
				}
			}
			for (Entregaformadepago entregaformadepagoListOldEntregaformadepago : entregaformadepagoListOld) {
				if (!entregaformadepagoListNew
						.contains(entregaformadepagoListOldEntregaformadepago)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages
							.add("You must retain Entregaformadepago "
									+ entregaformadepagoListOldEntregaformadepago
									+ " since its idFormadepago field is not nullable.");
				}
			}
//			for (Categorialinearetencion categorialinearetencionListOldCategorialinearetencion : categorialinearetencionListOld) {
//				if (!categorialinearetencionListNew
//						.contains(categorialinearetencionListOldCategorialinearetencion)) {
//					if (illegalOrphanMessages == null) {
//						illegalOrphanMessages = new ArrayList<String>();
//					}
//					illegalOrphanMessages
//							.add("You must retain Categorialinearetencion "
//									+ categorialinearetencionListOldCategorialinearetencion
//									+ " since its idFormadepago field is not nullable.");
//				}
//			}
			for (Acreenciamovimientoformadepago acreenciamovimientoformadepagoListOldAcreenciamovimientoformadepago : acreenciamovimientoformadepagoListOld) {
				if (!acreenciamovimientoformadepagoListNew
						.contains(acreenciamovimientoformadepagoListOldAcreenciamovimientoformadepago)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages
							.add("You must retain Acreenciamovimientoformadepago "
									+ acreenciamovimientoformadepagoListOldAcreenciamovimientoformadepago
									+ " since its idFormadepago field is not nullable.");
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
									+ " since its idFormadepago field is not nullable.");
				}
			}
			for (Abonoformadepago abonoformadepagoListOldAbonoformadepago : abonoformadepagoListOld) {
				if (!abonoformadepagoListNew
						.contains(abonoformadepagoListOldAbonoformadepago)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages
							.add("You must retain Abonoformadepago "
									+ abonoformadepagoListOldAbonoformadepago
									+ " since its idFormadepago field is not nullable.");
				}
			}
			for (Acreenciamovimiento acreenciamovimientoListOldAcreenciamovimiento : acreenciamovimientoListOld) {
				if (!acreenciamovimientoListNew
						.contains(acreenciamovimientoListOldAcreenciamovimiento)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages
							.add("You must retain Acreenciamovimiento "
									+ acreenciamovimientoListOldAcreenciamovimiento
									+ " since its idFormadepago field is not nullable.");
				}
			}
			for (Formadepagomoneda formadepagomonedaListOldFormadepagomoneda : formadepagomonedaListOld) {
				if (!formadepagomonedaListNew
						.contains(formadepagomonedaListOldFormadepagomoneda)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages
							.add("You must retain Formadepagomoneda "
									+ formadepagomonedaListOldFormadepagomoneda
									+ " since its idFormadepago field is not nullable.");
				}
			}
			if (illegalOrphanMessages != null) {
				throw new IllegalOrphanException(illegalOrphanMessages);
			}
			List<Formadepagomovacreencia> attachedFormadepagomovacreenciaListNew = new ArrayList<Formadepagomovacreencia>();
			for (Formadepagomovacreencia formadepagomovacreenciaListNewFormadepagomovacreenciaToAttach : formadepagomovacreenciaListNew) {
				formadepagomovacreenciaListNewFormadepagomovacreenciaToAttach = em
						.getReference(
								formadepagomovacreenciaListNewFormadepagomovacreenciaToAttach
										.getClass(),
								formadepagomovacreenciaListNewFormadepagomovacreenciaToAttach
										.getId());
				attachedFormadepagomovacreenciaListNew
						.add(formadepagomovacreenciaListNewFormadepagomovacreenciaToAttach);
			}
			formadepagomovacreenciaListNew = attachedFormadepagomovacreenciaListNew;
			formadepago
					.setFormadepagomovacreenciaList(formadepagomovacreenciaListNew);
			List<Transaccionformadepago> attachedTransaccionformadepagoListNew = new ArrayList<Transaccionformadepago>();
			for (Transaccionformadepago transaccionformadepagoListNewTransaccionformadepagoToAttach : transaccionformadepagoListNew) {
				transaccionformadepagoListNewTransaccionformadepagoToAttach = em
						.getReference(
								transaccionformadepagoListNewTransaccionformadepagoToAttach
										.getClass(),
								transaccionformadepagoListNewTransaccionformadepagoToAttach
										.getId());
				attachedTransaccionformadepagoListNew
						.add(transaccionformadepagoListNewTransaccionformadepagoToAttach);
			}
			transaccionformadepagoListNew = attachedTransaccionformadepagoListNew;
			formadepago
					.setTransaccionformadepagoList(transaccionformadepagoListNew);
			List<Porcentajeimpuestoretencion> attachedPorcentajeimpuestoretencionListNew = new ArrayList<Porcentajeimpuestoretencion>();
			for (Porcentajeimpuestoretencion porcentajeimpuestoretencionListNewPorcentajeimpuestoretencionToAttach : porcentajeimpuestoretencionListNew) {
				porcentajeimpuestoretencionListNewPorcentajeimpuestoretencionToAttach = em
						.getReference(
								porcentajeimpuestoretencionListNewPorcentajeimpuestoretencionToAttach
										.getClass(),
								porcentajeimpuestoretencionListNewPorcentajeimpuestoretencionToAttach
										.getId());
				attachedPorcentajeimpuestoretencionListNew
						.add(porcentajeimpuestoretencionListNewPorcentajeimpuestoretencionToAttach);
			}
			porcentajeimpuestoretencionListNew = attachedPorcentajeimpuestoretencionListNew;
			formadepago
					.setPorcentajeimpuestoretencionList(porcentajeimpuestoretencionListNew);
			List<Detalletipoformadepago> attachedDetalletipoformadepagoListNew = new ArrayList<Detalletipoformadepago>();
			for (Detalletipoformadepago detalletipoformadepagoListNewDetalletipoformadepagoToAttach : detalletipoformadepagoListNew) {
				detalletipoformadepagoListNewDetalletipoformadepagoToAttach = em
						.getReference(
								detalletipoformadepagoListNewDetalletipoformadepagoToAttach
										.getClass(),
								detalletipoformadepagoListNewDetalletipoformadepagoToAttach
										.getId());
				attachedDetalletipoformadepagoListNew
						.add(detalletipoformadepagoListNewDetalletipoformadepagoToAttach);
			}
			detalletipoformadepagoListNew = attachedDetalletipoformadepagoListNew;
			formadepago
					.setDetalletipoformadepagoList(detalletipoformadepagoListNew);
			List<Morosidad> attachedMorosidadListNew = new ArrayList<Morosidad>();
			for (Morosidad morosidadListNewMorosidadToAttach : morosidadListNew) {
				morosidadListNewMorosidadToAttach = em.getReference(
						morosidadListNewMorosidadToAttach.getClass(),
						morosidadListNewMorosidadToAttach.getId());
				attachedMorosidadListNew.add(morosidadListNewMorosidadToAttach);
			}
			morosidadListNew = attachedMorosidadListNew;
			formadepago.setMorosidadList(morosidadListNew);
			List<Entregaformadepago> attachedEntregaformadepagoListNew = new ArrayList<Entregaformadepago>();
			for (Entregaformadepago entregaformadepagoListNewEntregaformadepagoToAttach : entregaformadepagoListNew) {
				entregaformadepagoListNewEntregaformadepagoToAttach = em
						.getReference(
								entregaformadepagoListNewEntregaformadepagoToAttach
										.getClass(),
								entregaformadepagoListNewEntregaformadepagoToAttach
										.getId());
				attachedEntregaformadepagoListNew
						.add(entregaformadepagoListNewEntregaformadepagoToAttach);
			}
			entregaformadepagoListNew = attachedEntregaformadepagoListNew;
			formadepago.setEntregaformadepagoList(entregaformadepagoListNew);
			List<Categorialinearetencion> attachedCategorialinearetencionListNew = new ArrayList<Categorialinearetencion>();
//			for (Categorialinearetencion categorialinearetencionListNewCategorialinearetencionToAttach : categorialinearetencionListNew) {
//				categorialinearetencionListNewCategorialinearetencionToAttach = em
//						.getReference(
//								categorialinearetencionListNewCategorialinearetencionToAttach
//										.getClass(),
//								categorialinearetencionListNewCategorialinearetencionToAttach
//										.getId());
//				attachedCategorialinearetencionListNew
//						.add(categorialinearetencionListNewCategorialinearetencionToAttach);
//			}
//			categorialinearetencionListNew = attachedCategorialinearetencionListNew;
//			formadepago
//					.setCategorialinearetencionList(categorialinearetencionListNew);
			List<Acreenciamovimientoformadepago> attachedAcreenciamovimientoformadepagoListNew = new ArrayList<Acreenciamovimientoformadepago>();
			for (Acreenciamovimientoformadepago acreenciamovimientoformadepagoListNewAcreenciamovimientoformadepagoToAttach : acreenciamovimientoformadepagoListNew) {
				acreenciamovimientoformadepagoListNewAcreenciamovimientoformadepagoToAttach = em
						.getReference(
								acreenciamovimientoformadepagoListNewAcreenciamovimientoformadepagoToAttach
										.getClass(),
								acreenciamovimientoformadepagoListNewAcreenciamovimientoformadepagoToAttach
										.getId());
				attachedAcreenciamovimientoformadepagoListNew
						.add(acreenciamovimientoformadepagoListNewAcreenciamovimientoformadepagoToAttach);
			}
			acreenciamovimientoformadepagoListNew = attachedAcreenciamovimientoformadepagoListNew;
			formadepago
					.setAcreenciamovimientoformadepagoList(acreenciamovimientoformadepagoListNew);
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
			formadepago
					.setPromocionformadepagoList(promocionformadepagoListNew);
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
			formadepago.setAbonoformadepagoList(abonoformadepagoListNew);
			List<Acreenciamovimiento> attachedAcreenciamovimientoListNew = new ArrayList<Acreenciamovimiento>();
			for (Acreenciamovimiento acreenciamovimientoListNewAcreenciamovimientoToAttach : acreenciamovimientoListNew) {
				acreenciamovimientoListNewAcreenciamovimientoToAttach = em
						.getReference(
								acreenciamovimientoListNewAcreenciamovimientoToAttach
										.getClass(),
								acreenciamovimientoListNewAcreenciamovimientoToAttach
										.getIpaId());
				attachedAcreenciamovimientoListNew
						.add(acreenciamovimientoListNewAcreenciamovimientoToAttach);
			}
			acreenciamovimientoListNew = attachedAcreenciamovimientoListNew;
			formadepago.setAcreenciamovimientoList(acreenciamovimientoListNew);
			List<Formadepagomoneda> attachedFormadepagomonedaListNew = new ArrayList<Formadepagomoneda>();
			for (Formadepagomoneda formadepagomonedaListNewFormadepagomonedaToAttach : formadepagomonedaListNew) {
				formadepagomonedaListNewFormadepagomonedaToAttach = em
						.getReference(
								formadepagomonedaListNewFormadepagomonedaToAttach
										.getClass(),
								formadepagomonedaListNewFormadepagomonedaToAttach
										.getId());
				attachedFormadepagomonedaListNew
						.add(formadepagomonedaListNewFormadepagomonedaToAttach);
			}
			formadepagomonedaListNew = attachedFormadepagomonedaListNew;
			formadepago.setFormadepagomonedaList(formadepagomonedaListNew);
			formadepago = em.merge(formadepago);
			for (Formadepagomovacreencia formadepagomovacreenciaListOldFormadepagomovacreencia : formadepagomovacreenciaListOld) {
				if (!formadepagomovacreenciaListNew
						.contains(formadepagomovacreenciaListOldFormadepagomovacreencia)) {
					formadepagomovacreenciaListOldFormadepagomovacreencia
							.setIdFormadepago(null);
					formadepagomovacreenciaListOldFormadepagomovacreencia = em
							.merge(formadepagomovacreenciaListOldFormadepagomovacreencia);
				}
			}
			for (Formadepagomovacreencia formadepagomovacreenciaListNewFormadepagomovacreencia : formadepagomovacreenciaListNew) {
				if (!formadepagomovacreenciaListOld
						.contains(formadepagomovacreenciaListNewFormadepagomovacreencia)) {
					Formadepago oldIdFormadepagoOfFormadepagomovacreenciaListNewFormadepagomovacreencia = formadepagomovacreenciaListNewFormadepagomovacreencia
							.getIdFormadepago();
					formadepagomovacreenciaListNewFormadepagomovacreencia
							.setIdFormadepago(formadepago);
					formadepagomovacreenciaListNewFormadepagomovacreencia = em
							.merge(formadepagomovacreenciaListNewFormadepagomovacreencia);
					if (oldIdFormadepagoOfFormadepagomovacreenciaListNewFormadepagomovacreencia != null
							&& !oldIdFormadepagoOfFormadepagomovacreenciaListNewFormadepagomovacreencia
									.equals(formadepago)) {
						oldIdFormadepagoOfFormadepagomovacreenciaListNewFormadepagomovacreencia
								.getFormadepagomovacreenciaList()
								.remove(formadepagomovacreenciaListNewFormadepagomovacreencia);
						oldIdFormadepagoOfFormadepagomovacreenciaListNewFormadepagomovacreencia = em
								.merge(oldIdFormadepagoOfFormadepagomovacreenciaListNewFormadepagomovacreencia);
					}
				}
			}
			for (Transaccionformadepago transaccionformadepagoListNewTransaccionformadepago : transaccionformadepagoListNew) {
				if (!transaccionformadepagoListOld
						.contains(transaccionformadepagoListNewTransaccionformadepago)) {
					Formadepago oldIdFormadepagoOfTransaccionformadepagoListNewTransaccionformadepago = transaccionformadepagoListNewTransaccionformadepago
							.getIdFormadepago();
					transaccionformadepagoListNewTransaccionformadepago
							.setIdFormadepago(formadepago);
					transaccionformadepagoListNewTransaccionformadepago = em
							.merge(transaccionformadepagoListNewTransaccionformadepago);
					if (oldIdFormadepagoOfTransaccionformadepagoListNewTransaccionformadepago != null
							&& !oldIdFormadepagoOfTransaccionformadepagoListNewTransaccionformadepago
									.equals(formadepago)) {
						oldIdFormadepagoOfTransaccionformadepagoListNewTransaccionformadepago
								.getTransaccionformadepagoList()
								.remove(transaccionformadepagoListNewTransaccionformadepago);
						oldIdFormadepagoOfTransaccionformadepagoListNewTransaccionformadepago = em
								.merge(oldIdFormadepagoOfTransaccionformadepagoListNewTransaccionformadepago);
					}
				}
			}
			for (Porcentajeimpuestoretencion porcentajeimpuestoretencionListNewPorcentajeimpuestoretencion : porcentajeimpuestoretencionListNew) {
				if (!porcentajeimpuestoretencionListOld
						.contains(porcentajeimpuestoretencionListNewPorcentajeimpuestoretencion)) {
					Formadepago oldIdFormadepagoOfPorcentajeimpuestoretencionListNewPorcentajeimpuestoretencion = porcentajeimpuestoretencionListNewPorcentajeimpuestoretencion
							.getIdFormadepago();
					porcentajeimpuestoretencionListNewPorcentajeimpuestoretencion
							.setIdFormadepago(formadepago);
					porcentajeimpuestoretencionListNewPorcentajeimpuestoretencion = em
							.merge(porcentajeimpuestoretencionListNewPorcentajeimpuestoretencion);
					if (oldIdFormadepagoOfPorcentajeimpuestoretencionListNewPorcentajeimpuestoretencion != null
							&& !oldIdFormadepagoOfPorcentajeimpuestoretencionListNewPorcentajeimpuestoretencion
									.equals(formadepago)) {
						oldIdFormadepagoOfPorcentajeimpuestoretencionListNewPorcentajeimpuestoretencion
								.getPorcentajeimpuestoretencionList()
								.remove(porcentajeimpuestoretencionListNewPorcentajeimpuestoretencion);
						oldIdFormadepagoOfPorcentajeimpuestoretencionListNewPorcentajeimpuestoretencion = em
								.merge(oldIdFormadepagoOfPorcentajeimpuestoretencionListNewPorcentajeimpuestoretencion);
					}
				}
			}
			for (Detalletipoformadepago detalletipoformadepagoListNewDetalletipoformadepago : detalletipoformadepagoListNew) {
				if (!detalletipoformadepagoListOld
						.contains(detalletipoformadepagoListNewDetalletipoformadepago)) {
					Formadepago oldIdFormadepagoOfDetalletipoformadepagoListNewDetalletipoformadepago = detalletipoformadepagoListNewDetalletipoformadepago
							.getIdFormadepago();
					detalletipoformadepagoListNewDetalletipoformadepago
							.setIdFormadepago(formadepago);
					detalletipoformadepagoListNewDetalletipoformadepago = em
							.merge(detalletipoformadepagoListNewDetalletipoformadepago);
					if (oldIdFormadepagoOfDetalletipoformadepagoListNewDetalletipoformadepago != null
							&& !oldIdFormadepagoOfDetalletipoformadepagoListNewDetalletipoformadepago
									.equals(formadepago)) {
						oldIdFormadepagoOfDetalletipoformadepagoListNewDetalletipoformadepago
								.getDetalletipoformadepagoList()
								.remove(detalletipoformadepagoListNewDetalletipoformadepago);
						oldIdFormadepagoOfDetalletipoformadepagoListNewDetalletipoformadepago = em
								.merge(oldIdFormadepagoOfDetalletipoformadepagoListNewDetalletipoformadepago);
					}
				}
			}
			for (Morosidad morosidadListNewMorosidad : morosidadListNew) {
				if (!morosidadListOld.contains(morosidadListNewMorosidad)) {
					Formadepago oldIdFormadepagoOfMorosidadListNewMorosidad = morosidadListNewMorosidad
							.getIdFormadepago();
					morosidadListNewMorosidad.setIdFormadepago(formadepago);
					morosidadListNewMorosidad = em
							.merge(morosidadListNewMorosidad);
					if (oldIdFormadepagoOfMorosidadListNewMorosidad != null
							&& !oldIdFormadepagoOfMorosidadListNewMorosidad
									.equals(formadepago)) {
						oldIdFormadepagoOfMorosidadListNewMorosidad
								.getMorosidadList().remove(
										morosidadListNewMorosidad);
						oldIdFormadepagoOfMorosidadListNewMorosidad = em
								.merge(oldIdFormadepagoOfMorosidadListNewMorosidad);
					}
				}
			}
			for (Entregaformadepago entregaformadepagoListNewEntregaformadepago : entregaformadepagoListNew) {
				if (!entregaformadepagoListOld
						.contains(entregaformadepagoListNewEntregaformadepago)) {
					Formadepago oldIdFormadepagoOfEntregaformadepagoListNewEntregaformadepago = entregaformadepagoListNewEntregaformadepago
							.getIdFormadepago();
					entregaformadepagoListNewEntregaformadepago
							.setIdFormadepago(formadepago);
					entregaformadepagoListNewEntregaformadepago = em
							.merge(entregaformadepagoListNewEntregaformadepago);
					if (oldIdFormadepagoOfEntregaformadepagoListNewEntregaformadepago != null
							&& !oldIdFormadepagoOfEntregaformadepagoListNewEntregaformadepago
									.equals(formadepago)) {
						oldIdFormadepagoOfEntregaformadepagoListNewEntregaformadepago
								.getEntregaformadepagoList()
								.remove(entregaformadepagoListNewEntregaformadepago);
						oldIdFormadepagoOfEntregaformadepagoListNewEntregaformadepago = em
								.merge(oldIdFormadepagoOfEntregaformadepagoListNewEntregaformadepago);
					}
				}
			}
//			for (Categorialinearetencion categorialinearetencionListNewCategorialinearetencion : categorialinearetencionListNew) {
//				if (!categorialinearetencionListOld
//						.contains(categorialinearetencionListNewCategorialinearetencion)) {
//					Formadepago oldIdFormadepagoOfCategorialinearetencionListNewCategorialinearetencion = categorialinearetencionListNewCategorialinearetencion
//							.getIdFormadepago();
//					categorialinearetencionListNewCategorialinearetencion
//							.setIdFormadepago(formadepago);
//					categorialinearetencionListNewCategorialinearetencion = em
//							.merge(categorialinearetencionListNewCategorialinearetencion);
//					if (oldIdFormadepagoOfCategorialinearetencionListNewCategorialinearetencion != null
//							&& !oldIdFormadepagoOfCategorialinearetencionListNewCategorialinearetencion
//									.equals(formadepago)) {
//						oldIdFormadepagoOfCategorialinearetencionListNewCategorialinearetencion
//								.getCategorialinearetencionList()
//								.remove(categorialinearetencionListNewCategorialinearetencion);
//						oldIdFormadepagoOfCategorialinearetencionListNewCategorialinearetencion = em
//								.merge(oldIdFormadepagoOfCategorialinearetencionListNewCategorialinearetencion);
//					}
//				}
//			}
			for (Acreenciamovimientoformadepago acreenciamovimientoformadepagoListNewAcreenciamovimientoformadepago : acreenciamovimientoformadepagoListNew) {
				if (!acreenciamovimientoformadepagoListOld
						.contains(acreenciamovimientoformadepagoListNewAcreenciamovimientoformadepago)) {
					Formadepago oldIdFormadepagoOfAcreenciamovimientoformadepagoListNewAcreenciamovimientoformadepago = acreenciamovimientoformadepagoListNewAcreenciamovimientoformadepago
							.getIdFormadepago();
					acreenciamovimientoformadepagoListNewAcreenciamovimientoformadepago
							.setIdFormadepago(formadepago);
					acreenciamovimientoformadepagoListNewAcreenciamovimientoformadepago = em
							.merge(acreenciamovimientoformadepagoListNewAcreenciamovimientoformadepago);
					if (oldIdFormadepagoOfAcreenciamovimientoformadepagoListNewAcreenciamovimientoformadepago != null
							&& !oldIdFormadepagoOfAcreenciamovimientoformadepagoListNewAcreenciamovimientoformadepago
									.equals(formadepago)) {
						oldIdFormadepagoOfAcreenciamovimientoformadepagoListNewAcreenciamovimientoformadepago
								.getAcreenciamovimientoformadepagoList()
								.remove(acreenciamovimientoformadepagoListNewAcreenciamovimientoformadepago);
						oldIdFormadepagoOfAcreenciamovimientoformadepagoListNewAcreenciamovimientoformadepago = em
								.merge(oldIdFormadepagoOfAcreenciamovimientoformadepagoListNewAcreenciamovimientoformadepago);
					}
				}
			}
			for (Promocionformadepago promocionformadepagoListNewPromocionformadepago : promocionformadepagoListNew) {
				if (!promocionformadepagoListOld
						.contains(promocionformadepagoListNewPromocionformadepago)) {
					Formadepago oldIdFormadepagoOfPromocionformadepagoListNewPromocionformadepago = promocionformadepagoListNewPromocionformadepago
							.getIdFormadepago();
					promocionformadepagoListNewPromocionformadepago
							.setIdFormadepago(formadepago);
					promocionformadepagoListNewPromocionformadepago = em
							.merge(promocionformadepagoListNewPromocionformadepago);
					if (oldIdFormadepagoOfPromocionformadepagoListNewPromocionformadepago != null
							&& !oldIdFormadepagoOfPromocionformadepagoListNewPromocionformadepago
									.equals(formadepago)) {
						oldIdFormadepagoOfPromocionformadepagoListNewPromocionformadepago
								.getPromocionformadepagoList()
								.remove(promocionformadepagoListNewPromocionformadepago);
						oldIdFormadepagoOfPromocionformadepagoListNewPromocionformadepago = em
								.merge(oldIdFormadepagoOfPromocionformadepagoListNewPromocionformadepago);
					}
				}
			}
			for (Abonoformadepago abonoformadepagoListNewAbonoformadepago : abonoformadepagoListNew) {
				if (!abonoformadepagoListOld
						.contains(abonoformadepagoListNewAbonoformadepago)) {
					Formadepago oldIdFormadepagoOfAbonoformadepagoListNewAbonoformadepago = abonoformadepagoListNewAbonoformadepago
							.getIdFormadepago();
					abonoformadepagoListNewAbonoformadepago
							.setIdFormadepago(formadepago);
					abonoformadepagoListNewAbonoformadepago = em
							.merge(abonoformadepagoListNewAbonoformadepago);
					if (oldIdFormadepagoOfAbonoformadepagoListNewAbonoformadepago != null
							&& !oldIdFormadepagoOfAbonoformadepagoListNewAbonoformadepago
									.equals(formadepago)) {
						oldIdFormadepagoOfAbonoformadepagoListNewAbonoformadepago
								.getAbonoformadepagoList()
								.remove(abonoformadepagoListNewAbonoformadepago);
						oldIdFormadepagoOfAbonoformadepagoListNewAbonoformadepago = em
								.merge(oldIdFormadepagoOfAbonoformadepagoListNewAbonoformadepago);
					}
				}
			}
			for (Acreenciamovimiento acreenciamovimientoListNewAcreenciamovimiento : acreenciamovimientoListNew) {
				if (!acreenciamovimientoListOld
						.contains(acreenciamovimientoListNewAcreenciamovimiento)) {
					Formadepago oldIdFormadepagoOfAcreenciamovimientoListNewAcreenciamovimiento = acreenciamovimientoListNewAcreenciamovimiento
							.getIdFormadepago();
					acreenciamovimientoListNewAcreenciamovimiento
							.setIdFormadepago(formadepago);
					acreenciamovimientoListNewAcreenciamovimiento = em
							.merge(acreenciamovimientoListNewAcreenciamovimiento);
					if (oldIdFormadepagoOfAcreenciamovimientoListNewAcreenciamovimiento != null
							&& !oldIdFormadepagoOfAcreenciamovimientoListNewAcreenciamovimiento
									.equals(formadepago)) {
						oldIdFormadepagoOfAcreenciamovimientoListNewAcreenciamovimiento
								.getAcreenciamovimientoList()
								.remove(acreenciamovimientoListNewAcreenciamovimiento);
						oldIdFormadepagoOfAcreenciamovimientoListNewAcreenciamovimiento = em
								.merge(oldIdFormadepagoOfAcreenciamovimientoListNewAcreenciamovimiento);
					}
				}
			}
			for (Formadepagomoneda formadepagomonedaListNewFormadepagomoneda : formadepagomonedaListNew) {
				if (!formadepagomonedaListOld
						.contains(formadepagomonedaListNewFormadepagomoneda)) {
					Formadepago oldIdFormadepagoOfFormadepagomonedaListNewFormadepagomoneda = formadepagomonedaListNewFormadepagomoneda
							.getIdFormadepago();
					formadepagomonedaListNewFormadepagomoneda
							.setIdFormadepago(formadepago);
					formadepagomonedaListNewFormadepagomoneda = em
							.merge(formadepagomonedaListNewFormadepagomoneda);
					if (oldIdFormadepagoOfFormadepagomonedaListNewFormadepagomoneda != null
							&& !oldIdFormadepagoOfFormadepagomonedaListNewFormadepagomoneda
									.equals(formadepago)) {
						oldIdFormadepagoOfFormadepagomonedaListNewFormadepagomoneda
								.getFormadepagomonedaList()
								.remove(formadepagomonedaListNewFormadepagomoneda);
						oldIdFormadepagoOfFormadepagomonedaListNewFormadepagomoneda = em
								.merge(oldIdFormadepagoOfFormadepagomonedaListNewFormadepagomoneda);
					}
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = formadepago.getId();
				if (findFormadepago(id) == null) {
					throw new NonexistentEntityException(
							"The formadepago with id " + id
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
			Formadepago formadepago;
			try {
				formadepago = em.getReference(Formadepago.class, id);
				formadepago.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException("The formadepago with id "
						+ id + " no longer exists.", enfe);
			}
			List<String> illegalOrphanMessages = null;
			List<Transaccionformadepago> transaccionformadepagoListOrphanCheck = formadepago
					.getTransaccionformadepagoList();
			for (Transaccionformadepago transaccionformadepagoListOrphanCheckTransaccionformadepago : transaccionformadepagoListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages
						.add("This Formadepago ("
								+ formadepago
								+ ") cannot be destroyed since the Transaccionformadepago "
								+ transaccionformadepagoListOrphanCheckTransaccionformadepago
								+ " in its transaccionformadepagoList field has a non-nullable idFormadepago field.");
			}
			List<Porcentajeimpuestoretencion> porcentajeimpuestoretencionListOrphanCheck = formadepago
					.getPorcentajeimpuestoretencionList();
			for (Porcentajeimpuestoretencion porcentajeimpuestoretencionListOrphanCheckPorcentajeimpuestoretencion : porcentajeimpuestoretencionListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages
						.add("This Formadepago ("
								+ formadepago
								+ ") cannot be destroyed since the Porcentajeimpuestoretencion "
								+ porcentajeimpuestoretencionListOrphanCheckPorcentajeimpuestoretencion
								+ " in its porcentajeimpuestoretencionList field has a non-nullable idFormadepago field.");
			}
			List<Detalletipoformadepago> detalletipoformadepagoListOrphanCheck = formadepago
					.getDetalletipoformadepagoList();
			for (Detalletipoformadepago detalletipoformadepagoListOrphanCheckDetalletipoformadepago : detalletipoformadepagoListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages
						.add("This Formadepago ("
								+ formadepago
								+ ") cannot be destroyed since the Detalletipoformadepago "
								+ detalletipoformadepagoListOrphanCheckDetalletipoformadepago
								+ " in its detalletipoformadepagoList field has a non-nullable idFormadepago field.");
			}
			List<Morosidad> morosidadListOrphanCheck = formadepago
					.getMorosidadList();
			for (Morosidad morosidadListOrphanCheckMorosidad : morosidadListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages
						.add("This Formadepago ("
								+ formadepago
								+ ") cannot be destroyed since the Morosidad "
								+ morosidadListOrphanCheckMorosidad
								+ " in its morosidadList field has a non-nullable idFormadepago field.");
			}
			List<Entregaformadepago> entregaformadepagoListOrphanCheck = formadepago
					.getEntregaformadepagoList();
			for (Entregaformadepago entregaformadepagoListOrphanCheckEntregaformadepago : entregaformadepagoListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages
						.add("This Formadepago ("
								+ formadepago
								+ ") cannot be destroyed since the Entregaformadepago "
								+ entregaformadepagoListOrphanCheckEntregaformadepago
								+ " in its entregaformadepagoList field has a non-nullable idFormadepago field.");
			}
//			List<Categorialinearetencion> categorialinearetencionListOrphanCheck = formadepago
//					.getCategorialinearetencionList();
//			for (Categorialinearetencion categorialinearetencionListOrphanCheckCategorialinearetencion : categorialinearetencionListOrphanCheck) {
//				if (illegalOrphanMessages == null) {
//					illegalOrphanMessages = new ArrayList<String>();
//				}
//				illegalOrphanMessages
//						.add("This Formadepago ("
//								+ formadepago
//								+ ") cannot be destroyed since the Categorialinearetencion "
//								+ categorialinearetencionListOrphanCheckCategorialinearetencion
//								+ " in its categorialinearetencionList field has a non-nullable idFormadepago field.");
//			}
			List<Acreenciamovimientoformadepago> acreenciamovimientoformadepagoListOrphanCheck = formadepago
					.getAcreenciamovimientoformadepagoList();
			for (Acreenciamovimientoformadepago acreenciamovimientoformadepagoListOrphanCheckAcreenciamovimientoformadepago : acreenciamovimientoformadepagoListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages
						.add("This Formadepago ("
								+ formadepago
								+ ") cannot be destroyed since the Acreenciamovimientoformadepago "
								+ acreenciamovimientoformadepagoListOrphanCheckAcreenciamovimientoformadepago
								+ " in its acreenciamovimientoformadepagoList field has a non-nullable idFormadepago field.");
			}
			List<Promocionformadepago> promocionformadepagoListOrphanCheck = formadepago
					.getPromocionformadepagoList();
			for (Promocionformadepago promocionformadepagoListOrphanCheckPromocionformadepago : promocionformadepagoListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages
						.add("This Formadepago ("
								+ formadepago
								+ ") cannot be destroyed since the Promocionformadepago "
								+ promocionformadepagoListOrphanCheckPromocionformadepago
								+ " in its promocionformadepagoList field has a non-nullable idFormadepago field.");
			}
			List<Abonoformadepago> abonoformadepagoListOrphanCheck = formadepago
					.getAbonoformadepagoList();
			for (Abonoformadepago abonoformadepagoListOrphanCheckAbonoformadepago : abonoformadepagoListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages
						.add("This Formadepago ("
								+ formadepago
								+ ") cannot be destroyed since the Abonoformadepago "
								+ abonoformadepagoListOrphanCheckAbonoformadepago
								+ " in its abonoformadepagoList field has a non-nullable idFormadepago field.");
			}
			List<Acreenciamovimiento> acreenciamovimientoListOrphanCheck = formadepago
					.getAcreenciamovimientoList();
			for (Acreenciamovimiento acreenciamovimientoListOrphanCheckAcreenciamovimiento : acreenciamovimientoListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages
						.add("This Formadepago ("
								+ formadepago
								+ ") cannot be destroyed since the Acreenciamovimiento "
								+ acreenciamovimientoListOrphanCheckAcreenciamovimiento
								+ " in its acreenciamovimientoList field has a non-nullable idFormadepago field.");
			}
			List<Formadepagomoneda> formadepagomonedaListOrphanCheck = formadepago
					.getFormadepagomonedaList();
			for (Formadepagomoneda formadepagomonedaListOrphanCheckFormadepagomoneda : formadepagomonedaListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages
						.add("This Formadepago ("
								+ formadepago
								+ ") cannot be destroyed since the Formadepagomoneda "
								+ formadepagomonedaListOrphanCheckFormadepagomoneda
								+ " in its formadepagomonedaList field has a non-nullable idFormadepago field.");
			}
			if (illegalOrphanMessages != null) {
				throw new IllegalOrphanException(illegalOrphanMessages);
			}
			List<Formadepagomovacreencia> formadepagomovacreenciaList = formadepago
					.getFormadepagomovacreenciaList();
			for (Formadepagomovacreencia formadepagomovacreenciaListFormadepagomovacreencia : formadepagomovacreenciaList) {
				formadepagomovacreenciaListFormadepagomovacreencia
						.setIdFormadepago(null);
				formadepagomovacreenciaListFormadepagomovacreencia = em
						.merge(formadepagomovacreenciaListFormadepagomovacreencia);
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
	public List<Formadepago> findFormadepagoEntities(int maxResults,
			int firstResult) {
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
	private List<Formadepago> findFormadepagoEntities(boolean all,
			int maxResults, int firstResult) {
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
