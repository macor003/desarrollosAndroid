/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epa.cr.legacy;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.becoblohm.cr.models.VPosAdditionalData;
import com.becoblohm.cr.types.ActiveValues;

/**
 * 
 * @author eve0014321
 * @version $Revision: 1.0 $
 */
public class FormadepagopuntoagilJpaController extends AbstractJPAController {

	/**
	 * Field emf.
	 */
	private EntityManagerFactory emf = null;
	/**
	 * Field entityName.
	 */
	private static String entityName = "Formadepagopuntoagil";
	/**
	 * Field jpacontroller.
	 */
	private final crjpa400.FormadepagopuntoagilJpaController jpacontroller;

	/**
	 * Constructor for FormadepagopuntoagilJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public FormadepagopuntoagilJpaController(EntityManagerFactory emf) {
		super(AbstractJPAController.SERVERSOURCE, entityName);
		this.emf = emf;
		this.jpacontroller = new crjpa400.FormadepagopuntoagilJpaController(emf);
	}

	/**
	 * Method findLastFormadepagopuntoagilByVtid.
	 * 
	 * @param vtid
	 *            String
	 * @return VPosAdditionalData
	 */
	public VPosAdditionalData findLastFormadepagopuntoagilByVtid(String vtid) {
		EntityManager em = jpacontroller.getEntityManager();
		crjpa400.Formadepagopuntoagil fdppa = new crjpa400.Formadepagopuntoagil();
		Query query = em
				.createQuery("SELECT ff FROM Formadepagopuntoagil ff WHERE ff.secuencia = (SELECT max(f.secuencia) FROM Formadepagopuntoagil f WHERE f.vtid = :vtid)");
		query.setParameter("vtid", vtid);
		VPosAdditionalData result = null;
		try {
			fdppa = (crjpa400.Formadepagopuntoagil) query.getSingleResult();
			result = fromJPA(fdppa);
		} catch (Exception e) {
			// e.printStackTrace();
		} finally {
			em.close();
		}
		return result;
	}

	/**
	 * Method fromJPA.
	 * 
	 * @param fdppa
	 *            crjpa400.Formadepagopuntoagil
	 * @return VPosAdditionalData
	 */
	private VPosAdditionalData fromJPA(crjpa400.Formadepagopuntoagil fdppa) {
		VPosAdditionalData data = new VPosAdditionalData();

		if (data != null) {
			data.setCardNumber(fdppa.getTarjeta());
			data.setDate(fdppa.getFecha());
			data.setFile(fdppa.getArchivo());
			data.setId(fdppa.getId());
			data.setIdTransactionPaymentMethod(fdppa.getIdTransaccionformadepago().getId());
			data.setOwnerId(fdppa.getTitular());
			data.setResponseId(fdppa.getCodigoRespuesta());
			data.setResponseMessage(fdppa.getMensajeRespuesta());
			data.setSeqNum(fdppa.getSecuencia());
			data.setVtid(fdppa.getVtid());
			data.setPmPermitReverse(ActiveValues.get(
					String.valueOf(fdppa.getIdTransaccionformadepago().getIdFormadepago().getPermiteReverso()))
					.getValue());
			data.setCashierId(fdppa.getIdTransaccionformadepago().getIdTransaccion().getIdSesion().getIdUsuario()
					.getFicha());
		}
		return data;
	}
}
