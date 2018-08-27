/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epa.cr.legacy;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.becoblohm.cr.models.Tax;
import com.becoblohm.cr.types.ActiveValues;
import com.becoblohm.cr.types.CRBigDecimal;

import crjpa400.Tasaimpuestotipo;
import crjpa400.Tasaimpuestovalor;

/**
 * 
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class TasaimpuestovalorJpaController extends AbstractJPAController {

	/**
	 * Field ID_EXCENTO. (value is 73)
	 */
	private static final long ID_EXCENTO = 73L;
	/**
	 * Field entityName.
	 */
	private static String entityName = "Tasaimpuestovalor";
	/**
	 * Field emf.
	 */
	private EntityManagerFactory emf;
	/**
	 * Field entity.
	 */
	private Class entity = crjpa400.Tasaimpuestovalor.class;

	/**
	 * Constructor for TasaimpuestovalorJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public TasaimpuestovalorJpaController(EntityManagerFactory emf) {
		super(AbstractJPAController.SERVERSOURCE, entityName);
		this.emf = emf;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Method findTasaivaValorByDateAndRate.
	 * 
	 * @param date
	 *            Date
	 * @param rate
	 *            BigDecimal
	 * @return Tax
	 */
	public Tax findTasaivaValorByDateAndRate(Date date, BigDecimal rate) {

		Date dateInicial = cleanDate(date, true);
		Date dateFinal = cleanDate(date, false);
		System.out.println("Fecha inicial -> " + dateInicial);
		System.out.println("Fecha final -> " + dateFinal);

		EntityManager em = this.emf.createEntityManager();
		Query query = em
				.createQuery("SELECT t FROM Tasaimpuestovalor t WHERE  t.tasa=:tasa AND t.fechaInicio BETWEEN :fechaInicial AND :fechaFinal");
		query.setParameter("fechaInicial", dateInicial);
		query.setParameter("fechaFinal", dateFinal);
		query.setParameter("tasa", rate);

		// Query query =
		// em.createNativeQuery("SELECT * FROM CR400V2.tasaimpuestovalor t WHERE CAST(t.fecha_inicio as DATE) = ? AND t.tasa= ?");
		// query.setParameter(1,date);
		// query.setParameter(2,rate);
		query.setMaxResults(1);
		Tasaimpuestovalor obj = (Tasaimpuestovalor) query.getSingleResult();
		return fromJpa(obj);
	}

	/**
	 * Method findTasaImpExcento.
	 * 
	 * @return Tax
	 */
	public Tax findTasaImpExcento() {
		EntityManager em = this.emf.createEntityManager();
		Query query = em.createQuery("SELECT t FROM Tasaimpuestovalor t WHERE  t.id = :id");

		OpcionJpaController opcionJpaController = new OpcionJpaController(emf);
		String idExcentoStr = opcionJpaController.findById(ID_EXCENTO);

		query.setParameter("id", Long.valueOf(idExcentoStr));
		query.setMaxResults(1);
		Tasaimpuestovalor obj = (Tasaimpuestovalor) query.getSingleResult();
		return fromJpa(obj);
	}

	/**
	 * Method fromJpa.
	 * 
	 * @param tasaIvaValor
	 *            Tasaimpuestovalor
	 * @return Tax
	 */
	public static Tax fromJpa(Tasaimpuestovalor tasaIvaValor) {
		Tax result = new Tax();

		result.setId(tasaIvaValor.getIdTasaimpuestotipo().getId());
		result.setActive(ActiveValues.get(String.valueOf(tasaIvaValor.getIdTasaimpuestotipo().getEstavigente()))
				.getValue());
		result.setName(tasaIvaValor.getIdTasaimpuestotipo().getNombre());
		result.setCode(tasaIvaValor.getIdTasaimpuestotipo().getCodigo());
		result.setTaxDate(tasaIvaValor.getFechaInicio());
		result.setTaxRate(new CRBigDecimal(tasaIvaValor.getTasa().doubleValue()));

		return result;
	}

	/**
	 * Method toJpa.
	 * 
	 * @param tax
	 *            Tax
	 * @return Tasaimpuestovalor
	 */
	public Tasaimpuestovalor toJpa(Tax tax) {
		Tasaimpuestovalor tasaimpuestoValor = new Tasaimpuestovalor();

		tasaimpuestoValor.setId(tax.getId());
		tasaimpuestoValor.setFechaInicio(tax.getTaxDate());

		Tasaimpuestotipo tasaImpuestotipo = new Tasaimpuestotipo();
		tasaImpuestotipo.setNombre(tax.getName());

		tasaimpuestoValor.setIdTasaimpuestotipo(tasaImpuestotipo);

		tasaimpuestoValor.setTasa(tax.getTaxRate().getValue());

		return tasaimpuestoValor;
	}

	/**
	 * Method findTasaivaValorByidTipo.
	 * 
	 * @param idivaTipo
	 *            Long
	 * @return Tasaimpuestovalor
	 */
	public Tasaimpuestovalor findTasaivaValorByidTipo(Long idivaTipo) {
		EntityManager em = this.emf.createEntityManager();

		Query query = em.createQuery("SELECT t FROM " + entity.getSimpleName()
				+ " t WHERE t.idTasaimpuestotipo=:tasa order by t.fechaInicio DESC");
		// query.setParameter("fecha",new Date());
		query.setParameter("tasa", new Tasaimpuestotipo(idivaTipo));
		query.setMaxResults(1);
		Tasaimpuestovalor obj = (Tasaimpuestovalor) query.getSingleResult();
		return obj;
	}

	/**
	 * @param p_getxReportDate
	 * 
	 * @param param
	 *            boolean
	 * @return Date
	 */
	private Date cleanDate(Date p_getxReportDate, boolean param) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(p_getxReportDate);
		if (param) {
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
		} else {
			cal.set(Calendar.HOUR_OF_DAY, 23);
			cal.set(Calendar.MINUTE, 59);
			cal.set(Calendar.SECOND, 59);
			cal.set(Calendar.MILLISECOND, 59);
		}
		return cal.getTime();
	}

}
