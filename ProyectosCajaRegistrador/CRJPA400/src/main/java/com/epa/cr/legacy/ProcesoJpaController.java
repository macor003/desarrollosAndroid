/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epa.cr.legacy;

import java.util.Iterator;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;

import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.becoblohm.cr.models.Process;
import com.becoblohm.cr.types.ActiveValues;

import crjpa400.Perfilproceso;
import crjpa400.Proceso;
import crjpa400.Procesoproperty;

/**
 * 
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class ProcesoJpaController extends AbstractJPAController {

	/**
	 * Field entityName.
	 */
	private static String entityName = "Proceso";
	/**
	 * Field jpaController.
	 */
	private crjpa400.ProcesoJpaController jpaController;

	/**
	 * Constructor for ProcesoJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public ProcesoJpaController(EntityManagerFactory emf) {
		super(AbstractJPAController.SERVERSOURCE, entityName);
		this.jpaController = new crjpa400.ProcesoJpaController(emf);
	}

	/**
	 * Method loadProperties.
	 * 
	 * @param processId
	 *            Long
	 * @return Properties
	 */
	public Properties loadProperties(Long processId) {
		Properties props = new Properties();
		Proceso process = jpaController.findProceso(processId);

		Iterator<Procesoproperty> it = process.getProcesopropertyList().iterator();
		while (it.hasNext()) {
			Procesoproperty op = it.next();
			props.put(op.getId().toString().trim(), op.getValor().trim());
		}
		return props;
	}
	
	public static Process fromJPA(Proceso proc) {
		Process process = new Process();
		process.setId(String.valueOf(proc.getId()));
		process.setRequiresAuthorization(ActiveValues.get(String.valueOf(proc.getSerequiereautorizacion())).getValue());
		process.setAutoAuthorization(ActiveValues.get(String.valueOf(proc.getEsautoautorizado())).getValue());
		process.setDescription(proc.getDescripcion());
		for (Iterator<Perfilproceso> iterator = proc.getPerfilprocesoList().iterator(); iterator.hasNext();) {
			Perfilproceso type = iterator.next();
			if (ActiveValues.get(String.valueOf(type.getEstaactivo())).getValue()) {
				process.addProfile(type.getIdPerfil().getId().toString());
			}
		}
		return process;
	}

}
