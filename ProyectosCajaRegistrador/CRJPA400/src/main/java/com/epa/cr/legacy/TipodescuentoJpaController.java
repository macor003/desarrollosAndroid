/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates and open the template in the editor.
 */
package com.epa.cr.legacy;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.becoblohm.cr.models.DiscountType;
import com.becoblohm.cr.types.ActiveValues;

import crjpa400.Tipodescuento;

/**
 * 
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class TipodescuentoJpaController extends AbstractJPAController {

    /**
     * Field entityName.
     */
    private static String entityName = "Tipodescuento";

    /**
     * Field emf.
     */
    private final EntityManagerFactory emf;

    /**
     * Constructor for TipodescuentoJpaController.
     * 
     * @param emf EntityManagerFactory
     */
    public TipodescuentoJpaController(EntityManagerFactory emf) {
        super(AbstractJPAController.SERVERSOURCE, entityName);
        this.emf = emf;
    }

    /**
     * Field serialVersionUID. (value is 1)
     */
    private static final long serialVersionUID = 1L;

    /**
     * Method toJpa.
     * 
     * @param discountType DiscountType
     * @return Tipodescuento
     */
    public Tipodescuento toJpa(DiscountType discountType) {

        Tipodescuento tipoDescuento = new Tipodescuento();
        tipoDescuento.setCodigo(discountType.getCode());
        tipoDescuento.setDescripcion(discountType.getDescription());

        tipoDescuento.setEsporcentaje(ActiveValues.get(discountType.isPercent()).getString().charAt(0));
        tipoDescuento.setEstaactivo(ActiveValues.get(discountType.isActive()).getString().charAt(0));

        // TODO pendient por definir contenido de campo FacturacionAutomatica

        tipoDescuento.setFecha(discountType.getDate());
        tipoDescuento.setId(discountType.getId());
        tipoDescuento.setNombre(discountType.getName());
        tipoDescuento
                .setFacturacionAutomatica(ActiveValues.get(discountType.isAutomaticSale()).getString().charAt(0));

        return tipoDescuento;
    }

    /**
     * Method fromJpa.
     * 
     * @param idTipodescuento Tipodescuento
     * @return DiscountType
     */
    public DiscountType fromJpa(Tipodescuento idTipodescuento) {

        DiscountType discountType = new DiscountType();

        discountType.setActive(ActiveValues.valueOf(String.valueOf(idTipodescuento.getEstaactivo())).getValue());
        discountType.setAutomaticSale(ActiveValues
                .valueOf(String.valueOf(idTipodescuento.getFacturacionAutomatica())).getValue());
        discountType.setCode(idTipodescuento.getCodigo());
        discountType.setDate(idTipodescuento.getFecha());
        discountType.setDescription(idTipodescuento.getDescripcion());
        discountType.setId(idTipodescuento.getId());
        discountType.setName(idTipodescuento.getNombre());
        discountType
                .setPercent(ActiveValues.valueOf(String.valueOf(idTipodescuento.getEsporcentaje())).getValue());

        return discountType;
    }

    /**
     * Method getEntityManager.
     * 
     * @return EntityManager
     */
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    /**
     * Method findTipodescuento.
     * 
     * @param id Long
     * @return Tipodescuento
     */
    public Tipodescuento findTipodescuento(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tipodescuento.class, id);
        } finally {
            em.close();
        }
    }

    public Tipodescuento findTipodescuentoJPA(Long id) {
        return CacheEPA.getTiposDescuentos(id);
    }
}
