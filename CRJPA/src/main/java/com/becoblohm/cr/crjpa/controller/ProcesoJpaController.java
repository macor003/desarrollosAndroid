/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates and open the template in the editor.
 */
package com.becoblohm.cr.crjpa.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;

import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.becoblohm.cr.models.Process;
import com.becoblohm.cr.types.ActiveValues;

import crjpa.Perfilproceso;
import crjpa.Proceso;
import crjpa.Procesoproperty;

/**
 * 
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class ProcesoJpaController extends AbstractJPAController {// extends
                                                                 // crjpa.ProcesoJpaController
                                                                 // {

    /**
     * Field serialVersionUID. (value is 1)
     */
    private static final long serialVersionUID = 1L;

    /**
     * Field emf.
     */
    private EntityManagerFactory emf;

    /**
     * Field jpaController.
     */
    private crjpa.ProcesoJpaController jpaController;

    /**
     * Field entityName.
     */
    private static String entityName = "Proceso";

    /**
     * Constructor for ProcesoJpaController.
     * 
     * @param emf EntityManagerFactory
     */
    public ProcesoJpaController(EntityManagerFactory emf) {
        super(AbstractJPAController.POSSOURCE, entityName);
        this.jpaController = new crjpa.ProcesoJpaController(emf);
    }

    /**
     * Method fromJPA.
     * 
     * @param proc Proceso
     * @return Process
     */
    public static Process fromJPA(Proceso proc) {
        Process process = new Process();
        process.setId(String.valueOf(proc.getId()));
        process.setRequiresAuthorization(ActiveValues.get(proc.getSerequiereautorizacion()).getValue());
        process.setAutoAuthorization(ActiveValues.get(proc.getEsautoautorizado()).getValue());
        process.setDescription(proc.getDescripcion());
        for (Iterator iterator = proc.getPerfilprocesoList().iterator(); iterator.hasNext();) {
            Perfilproceso type = (Perfilproceso) iterator.next();
            if (ActiveValues.get(type.getEstaactivo()).getValue()) {
                process.addProfile(type.getIdPerfil().getId().toString());
            }
        }
        return process;
    }

    /**
     * @param p_long
     * 
     * @return Process
     */
    public Process findProcess(Long p_long) {
        try {
            Proceso proceso = this.jpaController.findProceso(p_long);
            if (proceso != null) {
                return fromJPA(proceso);
            } else {
                return null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

    }

    /**
     * Method loadProperties.
     * 
     * @param processId Long
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

    /**
     * Method isLoaded.
     * 
     * @return boolean
     */
    public boolean isLoaded() {
        boolean isLoaded = false;
        Iterator<Proceso> it = jpaController.findProcesoEntities().iterator();
        while (it.hasNext()) {
            Proceso p = it.next();
            if (p.getProcesopropertyList() != null && p.getProcesopropertyList().size() > 0) {
                isLoaded = true;
                break;
            }
        }
        return isLoaded;
    }

    /**
     * Method getProcesses.
     * 
     * @return List<Process>
     */
    public List<Process> getProcesses() {
        List<Process> processes = null;
        try {
            List<Proceso> procesos = this.jpaController.findProcesoEntities();
            if (procesos != null) {
                processes = new ArrayList<Process>();
                for (Iterator iterator = procesos.iterator(); iterator.hasNext();) {
                    Proceso proceso = (Proceso) iterator.next();
                    processes.add(fromJPA(proceso));
                }

                return processes;
            } else {
                return processes;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return processes;
        }
    }

}
