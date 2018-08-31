/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.epa.cr.legacy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import com.becoblohm.cr.crjpa400.controller.exceptions.JpaException;
import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.becoblohm.cr.models.CreditsMovement400;
import com.becoblohm.cr.types.CRBigDecimal;

import crjpa400.Acreencia;
import crjpa400.Acreenciamovimiento;
import crjpa400.AcreenciamovimientoPK;
import crjpa400.Formadepago;
import crjpa400.Moneda;
import crjpa400.Operacionacreencia;
import crjpa400.Tipoacreencia;

/**
 */
public class AcreenciaMovimientoJpaController extends AbstractJPAController {

	/**
	 * Field emf.
	 */
	private final EntityManagerFactory emf;
	/**
	 * Field jpaController.
	 */
	private final crjpa400.AcreenciamovimientoJpaController jpaController;
	/**
	 * Field entityName.
	 */
	private static String entityName = "Acreenciamovimiento";

	/**
	 * Constructor for AcreenciaMovimientoJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public AcreenciaMovimientoJpaController(EntityManagerFactory emf) {
		super(AbstractJPAController.SERVERSOURCE, entityName);
		this.emf = emf;
		jpaController = new crjpa400.AcreenciamovimientoJpaController(emf);
	}

	/**
	 * Method create.
	 * 
	 * @param movimiento
	 *            CreditsMovement400
	 * @throws JpaException
	 */
	public void create(CreditsMovement400 movimiento) throws JpaException {

		try {
			Acreenciamovimiento objeto = toJpa(movimiento);
			jpaController.create(objeto);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new JpaException();
		}

	}

	/**
	 * Method toJpa.
	 * 
	 * @param movimiento
	 *            CreditsMovement400
	 * @return Acreenciamovimiento
	 */
	private Acreenciamovimiento toJpa(CreditsMovement400 movimiento) {

		Acreenciamovimiento result = new Acreenciamovimiento();
		
		// Modificacion Timeout Acreencia
		// seteo de la nueva PK
		result.setIpaId(movimiento.getIpaid());
		// Seteo directamente de los campos que formaban la PK anterior
		result.setIdAcreenciamovimientosaldo(movimiento.getIdAcreenciaMovimientoSaldo());
		result.setIdAcreenciamovimientoformadepago(movimiento.getIdAcreenciaMovimientoFormaDePago());		
		// Seteo de los nuevos campos
		result.setIpaStatus(movimiento.getIpaStatus());
		// Se elimina la creacion de la PK anterior	
		//		AcreenciamovimientoPK acreenciaMovimientoPK = new AcreenciamovimientoPK();
		//		acreenciaMovimientoPK.setIdAcreenciamovimientosaldo(movimiento.getIdAcreenciaMovimientoSaldo());
		//		acreenciaMovimientoPK.setIdAcreenciamovimientoformadepago(movimiento.getIdAcreenciaMovimientoFormaDePago());
		//
		//		result.setAcreenciamovimientoPK(acreenciaMovimientoPK);
		
		Acreencia idAcreencia = new Acreencia(movimiento.getIdAcreencia());
		result.setIdAcreencia(idAcreencia.getId());
		Tipoacreencia idTipoacreencia = new Tipoacreencia(movimiento.getIdTipoAcreencia());
		result.setIdTipoacreencia(idTipoacreencia);
		Operacionacreencia idOperacionacreencia = new Operacionacreencia(movimiento.getIdOperacionAcreencia());
		result.setIdOperacionacreencia(idOperacionacreencia);
		Formadepago idFormadepago = new Formadepago(movimiento.getIdFormadePago());
		result.setIdFormadepago(idFormadepago);
		Moneda idMoneda = new Moneda(movimiento.getIdMoneda());
		result.setIdMoneda(idMoneda);
		result.setDocumentoformadepago(movimiento.getDocumentoFormadePago());
		result.setFecha(movimiento.getFecha());
		result.setNombreunidadnegocio(movimiento.getNombreUnidadNegocio());
		result.setNombreunidadoperativa(movimiento.getNombreUnidadOperativa());
		result.setTienda(movimiento.getTienda());
		result.setOperacion(movimiento.getOperacion());
		result.setAnulaoperacion(movimiento.getAnulaOperacion());
		result.setCaja(movimiento.getCaja());
		result.setCorrelativo(new BigDecimal(movimiento.getCorrelativo()));
		result.setTransaccion(movimiento.getTransaccion());
		result.setCajero(movimiento.getCajero());
		result.setRecibodecaja(movimiento.getReciboDeCaja());
		result.setMontoMonedaLocal(new BigDecimal(String.valueOf(movimiento.getMontoMonedaLocal().doubleValue())));
		result.setMontoMoneda(new BigDecimal(String.valueOf(movimiento.getMontoMoneda().doubleValue())));
		if (movimiento.getVuelto() != null) {
			result.setVuelto(new BigDecimal(movimiento.getVuelto().doubleValue()));
		} else {
			result.setVuelto(BigDecimal.ZERO);
		}

		result.setEstado(movimiento.getEstado());
		result.setControlreplicacion(movimiento.getControlReplicacion());
		result.setNumeroIdentificacionCliente(movimiento.getNumeroIdentificacionCliente());
		if (movimiento.getTransaccion() > 0) {
			result.setTransaccion(movimiento.getTransaccion());
		}

		return result;
	}

	/**
	 * Method findProcessToAnul.
	 * 
	 * @param idNumber
	 *            String
	 * @param transaccion
	 *            long
	 * @return Collection<CreditsMovement400>
	 */
	public Collection<CreditsMovement400> findProcessToAnul(String idNumber, long transaccion) {

		Collection<CreditsMovement400> movement = new ArrayList<CreditsMovement400>();
		EntityManager em = emf.createEntityManager();
		Vector<Acreenciamovimiento> result = null;
		try {

			Query query = em
					.createQuery("SELECT a FROM Acreenciamovimiento a WHERE a.numeroIdentificacionCliente = :numeroIdentificacionCliente and a.transaccion = :transaccion");
			query.setParameter("numeroIdentificacionCliente", idNumber);
			query.setParameter("transaccion", transaccion);
			result = (Vector<Acreenciamovimiento>) query.getResultList();

			if (result.size() > 0) {
				for (Acreenciamovimiento acreenciamovimiento : result) {
					movement.add(fromJpa(acreenciamovimiento));
				}
			}
		} catch (Exception ex) {
			System.out.println("Error buscando cliente ");
			ex.printStackTrace();
			result = null;
		} finally {
			em.close();
		}
		return movement;
	}

	/**
	 * Method fromJpa.
	 * 
	 * @param acreenciaMovimiento
	 *            Acreenciamovimiento
	 * @return CreditsMovement400
	 */
	private CreditsMovement400 fromJpa(Acreenciamovimiento acreenciaMovimiento) {

		CreditsMovement400 result = new CreditsMovement400();

		result.setIdAcreencia(acreenciaMovimiento.getIdAcreencia());
		result.setOperacion(acreenciaMovimiento.getOperacion());
		result.setIdFormadePago(acreenciaMovimiento.getIdFormadepago().getId());
		result.setIdMoneda(acreenciaMovimiento.getIdMoneda().getId());
		result.setMontoMoneda(new CRBigDecimal(acreenciaMovimiento.getMontoMoneda().doubleValue()));
		result.setMontoMonedaLocal(new CRBigDecimal(acreenciaMovimiento.getMontoMonedaLocal().doubleValue()));

		return result;
	}

}
