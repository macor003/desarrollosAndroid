/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epa.cr.legacy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.becoblohm.cr.models.CategoryLineRetention;
import com.becoblohm.cr.models.PaymentMethod;
import com.becoblohm.cr.models.Retention;
import com.becoblohm.cr.types.CRBigDecimal;

import crjpa400.Categorialinearetencion;
import crjpa400.Porcentajeimpuestoretencion;


/**
 * 
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class PorcentajeimpuestoretencionJpaController extends AbstractJPAController {

	/**
	 * Field entityName.
	 */
	private static String entityName = "Porcentajeimpuestoretencion";
	
	/**
	 * Field jpacontroller.
	 */
	crjpa400.PorcentajeimpuestoretencionJpaController jpacontroller;
	private EntityManagerFactory emf;

	/**
	 * Constructor for PorcentajeimpuestoretencionJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public PorcentajeimpuestoretencionJpaController(EntityManagerFactory emf) {
		super(AbstractJPAController.SERVERSOURCE, entityName);
		
		this.emf = emf;
		this.jpacontroller = new crjpa400.PorcentajeimpuestoretencionJpaController(emf);
	}

	/**
	 * Method fromJPA.
	 * 
	 * @param affected Object
	 * @return Retention
	 */
	public Retention fromJPA(Porcentajeimpuestoretencion porcRetention) {
		CategoriaLinearetencionJpaController categoryLineController = new CategoriaLinearetencionJpaController(this.emf);
		PaymentMethod paymentMethod = new PaymentMethod();
		Retention retention = new Retention();
		
		paymentMethod.setId(porcRetention.getIdFormadepago().getId().intValue());
		paymentMethod.setDescription(porcRetention.getIdFormadepago().getDescripcion());
		paymentMethod.setCode(porcRetention.getIdFormadepago().getCodigo());
		paymentMethod.setName(porcRetention.getIdFormadepago().getNombre());
		paymentMethod.setPaymentType(porcRetention.getIdFormadepago().getId().intValue());
		paymentMethod.setPaymentType(porcRetention.getIdFormadepago().getTipoFormaDePago());

		retention.setPorcemaxret(new CRBigDecimal(porcRetention.getPorcentajeMaxRetencion().doubleValue()));
		retention.setPorcenminret(new CRBigDecimal(porcRetention.getPorcentajeMinRetencion().doubleValue()));
		retention.setTipoartretencion(String.valueOf(porcRetention.getTipoArtRetencion()));
		retention.setFormadepago(paymentMethod);
		ArrayList<CategoryLineRetention> retentionLineList = new ArrayList<CategoryLineRetention>();
		for(Categorialinearetencion temp:porcRetention.getCategorialinearetencionList()) {
			retentionLineList.add(categoryLineController.fromJPA(temp));
		}
		retention.setCategoryLineRetencion(retentionLineList);
		
		if (porcRetention.getId() != null && porcRetention.getId() > 0){
			retention.setId(porcRetention.getId());
		}
		retention.setGroup(porcRetention.getGrupo());
		
		return retention;
	}
	
	public Retention findByPaymentMethodAndDate(BigDecimal paymentMethodId, Date transactionDate){
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("select p from Porcentajeimpuestoretencion p where p.fecha <= :fecha and p.idFormadepago.id = :formadepago order by p.fecha desc",Porcentajeimpuestoretencion.class);

		query.setParameter("formadepago", paymentMethodId);
		query.setParameter("fecha", transactionDate);
		query.setMaxResults(1);
		List<Porcentajeimpuestoretencion> resultList = query.getResultList();
		if(!resultList.isEmpty()){
			return fromJPA(resultList.get(0));
		}
		return null;
	}
	
}
