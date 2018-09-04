/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.epa.cr.legacy;

import java.util.ArrayList;

import javax.persistence.EntityManagerFactory;

import com.becoblohm.cr.crjpa400.controller.exceptions.IllegalOrphanException;
import com.becoblohm.cr.crjpa400.controller.exceptions.NonexistentEntityException;
import com.becoblohm.cr.crjpa400.controller.exceptions.PreexistingEntityException;
import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.becoblohm.cr.models.Deposit;
import com.becoblohm.cr.models.Deposit.SourceStatus;
import com.becoblohm.cr.models.Deposit.SourceType;
import com.becoblohm.cr.models.Order;
import com.becoblohm.cr.models.Payment;
import com.becoblohm.cr.models.Session;
import com.becoblohm.cr.types.ActiveValues;
import com.becoblohm.cr.types.CRBigDecimal;

import crjpa400.Abono;
import crjpa400.Abonoformadepago;
import crjpa400.Sesion;
import crjpa400.Usuario;

/**
 */
public class AbonoJpaController extends AbstractJPAController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5180313454870717903L;
	/**
	 * Field emf.
	 */
	private EntityManagerFactory emf;
	/**
	 * Field jpaController.
	 */
	private crjpa400.AbonoJpaController jpaController;
	/**
	 * Field entityName.
	 */
	private static String entityName = "Abono";

	/**
	 * Constructor for AbonoJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public AbonoJpaController(EntityManagerFactory emf) {
		super(AbstractJPAController.SERVERSOURCE, entityName);
		this.emf = emf;
		this.jpaController = new crjpa400.AbonoJpaController(emf);
	}

	/**
	 * Method fromJpa.
	 * 
	 * @param abono
	 *            Abono
	 * @return Deposit
	 */
	public Deposit fromJpa(Abono abono) {
		Deposit result = new Deposit();

		result.setId(abono.getId());
		SesionJpaController sesionJpaController = new SesionJpaController(this.emf);
		result.setSession(sesionJpaController.fromJPA(abono.getIdSesion()));
		if (abono.getIdAnulacionabono() != null) {
			result.setOriginalDeposit(fromJpa(abono.getIdAnulacionabono()));
		}
		result.setOrderNumber(abono.getIdOrdendeventa().getNumero());
		result.setOrderId(abono.getIdOrdendeventa().getId());
		result.setNumber(abono.getNumero());
		result.setDate(abono.getFecha());
		result.setStatus(SourceStatus.get(String.valueOf(abono.getEstado())));
		result.setAmount(new CRBigDecimal(abono.getTotal().doubleValue()));
		result.setDifference(new CRBigDecimal(abono.getVuelto().doubleValue()));
		result.setType(SourceType.get(abono.getTipo()));
		result.setPosId(String.valueOf(abono.getIdSesion().getIdCaja().getId()));

		ArrayList<Payment> payments = new ArrayList<Payment>();
		AbonoFormadepagoJpaController abonoFormaDePagoJpa = new AbonoFormadepagoJpaController(this.emf);
		for (Abonoformadepago abonoFormadepago : abono.getAbonoformadepagoList()) {
			payments.add(abonoFormaDePagoJpa.fromJPA(abonoFormadepago));
		}

		result.setPayments(payments);
		UsuarioJpaController usuarioJpaController = new UsuarioJpaController(this.emf);
		result.setUser(usuarioJpaController.findUsuario(new Long(abono.getCajero())));
		result.setFromPos(ActiveValues.get(String.valueOf(abono.getRegistradoPorCaja())).getValue());

		return result;
	}

	/**
	 * Method toJpa.
	 * 
	 * @param orden
	 *            Order
	 * @param session
	 *            Session
	 * @param deposit
	 *            Deposit
	 * @return Abono
	 */
	public Abono toJpa(Order orden, Session session, Deposit deposit) {
		Abono result = new Abono();

		result.setId(deposit.getId());

		result.setIdSesion(new Sesion(session.getId()));

		if (deposit.getOriginalDeposit() != null) {
			result.setIdAnulacionabono(jpaController.findAbono(deposit.getOriginalDeposit().getId()));
		}

		result.setIdUsuario(new Usuario(session.getCashier().getId()));
		OrdendeventaJpaController ordenDeVentaJpaController = new OrdendeventaJpaController(this.emf);

		result.setIdOrdendeventa(ordenDeVentaJpaController.toJpa(orden));

		result.setNumero(deposit.getNumber());
		result.setFecha(deposit.getDate());
		// TODO falta eliminar codigo duro de este campo
		result.setEstado(deposit.getStatus().getValue().charAt(0));
		result.setTotal(deposit.getAmount().getValue());
		result.setVuelto(deposit.getDifference().getValue());
		result.setTipo(deposit.getType().getValue());

		result.setCajero(deposit.getSession().getCashier().getIdEPA());

		result.setAbonoformadepagoList(new ArrayList<Abonoformadepago>());

		if (deposit.getId() > 0) {

			Abono oldAbono = jpaController.findAbono(deposit.getId());
			result.setAbonoformadepagoList(oldAbono.getAbonoformadepagoList());
			result.setAbonoList(oldAbono.getAbonoList());
		}

		result.setRegistradoPorCaja(ActiveValues.get(deposit.isFromPos()).getString().charAt(0));

		return result;

	}

	/**
	 * Method create.
	 * 
	 * @param orden
	 *            Order
	 * @param session
	 *            Session
	 * @param deposit
	 *            Deposit
	 * @throws PreexistingEntityException
	 * @throws Exception
	 */
	public void create(Order orden, Session session, Deposit deposit) throws PreexistingEntityException, Exception {
		Abono abono = toJpa(orden, session, deposit);
		abono.setNumero(orden.getDeposits().size() + 1);
		jpaController.create(abono);
		deposit.setId(abono.getId());
	}

	/**
	 * Method edit.
	 * 
	 * @param orden
	 *            Order
	 * @param session
	 *            Session
	 * @param deposit
	 *            Deposit
	 * @throws IllegalOrphanException
	 * @throws NonexistentEntityException
	 * @throws Exception
	 */
	public void edit(Order orden, Session session, Deposit deposit) throws IllegalOrphanException,
			NonexistentEntityException, Exception {
		// jpaController.edit(jpaController.findAbono(deposit.getId()));

		jpaController.edit(toJpa(orden, session, deposit));

	}

	/**
	 * Method destroy.
	 * 
	 * @param id
	 *            long
	 * @throws PreexistingEntityException
	 * @throws Exception
	 */
	public void destroy(long id) throws PreexistingEntityException, Exception {
		jpaController.destroy(id);
	}

	/**
	 * Method findAbono.
	 * 
	 * @param id
	 *            long
	 * @return Deposit
	 */
	public Deposit findAbono(long id) {
		return fromJpa(jpaController.findAbono(id));
	}

	/**
	 * Method findAbonoJpa.
	 * 
	 * @param id
	 *            long
	 * @return Abono
	 */
	public Abono findAbonoJpa(long id) {
		return jpaController.findAbono(id);
	}
}
