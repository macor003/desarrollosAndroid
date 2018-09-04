/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/**
 * 
 */
package com.becoblohm.cr.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import com.becoblohm.cr.types.CRBigDecimal;
import com.epa.mvc.core.AbstractModel;

/**
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class CommitmentPaymentDetails extends AbstractModel {

    /**
     * 
     */
    private static final long serialVersionUID = 7294371450669108708L;

    /**
     * Field id.
     */
    private Long id;

    /**
     * Field descripcion.
     */
    private String descripcion;

    /**
     * Field totalItems.
     */
    private int totalItems;

    /**
     * Field amount.
     */
    private CRBigDecimal amount = CRBigDecimal.ZERO;

    /**
     * Field estaactivo.
     */
    private String estaactivo;

    /**
     * Field tipoFormadepago.
     */
    private PaymentMethod tipoFormadepago;

    /**
     * Field detailsPDLines.
     */
    private Collection<CommitmentPaymentDetailsLines> detailsPDLines = new Vector<CommitmentPaymentDetailsLines>();

    /**
     * Field parent.
     */
    private CashierCommitment parent;

    /**
     * Constructor for CommitmentPaymentDetails.
     */
    public CommitmentPaymentDetails() {

    }

    /**
     * @param id
     * @param descripcion
     * @param estaactivo
     * @param tipoFormadepago
     */
    public CommitmentPaymentDetails(Long id, String descripcion, String estaactivo,
                                    PaymentMethod tipoFormadepago) {
        super();
        this.id = id;
        this.descripcion = descripcion;
        this.estaactivo = estaactivo;
        this.tipoFormadepago = tipoFormadepago;
    }

    /*
     * Actualizo el modelo
     */
    /**
     * Method updateModel.
     * 
     * @param p_checkInfo Map<String,Object>
     */
    public void updateModel(Map<String, Object> p_checkInfo) {
        int tmpItems = 0;
        CRBigDecimal tmpAmount = CRBigDecimal.ZERO;
        Collection<CommitmentPaymentDetailsLines> detailsTmp = new ArrayList<CommitmentPaymentDetailsLines>();

        for (Iterator iterator = p_checkInfo.values().iterator(); iterator.hasNext();) {
            CommitmentPaymentDetailsLines line = (CommitmentPaymentDetailsLines) iterator.next();

            CRBigDecimal tmpValue = line.getMonto();
            if (tmpValue.compareTo(CRBigDecimal.ZERO) != 0) {
                CommitmentPaymentDetailsLines tmp = new CommitmentPaymentDetailsLines();
                tmp.setDescripcion(line.getDescripcion());
                tmp.setMonto(line.getMonto());

                detailsTmp.add(tmp);
                tmpItems++;
            }
            tmpAmount = tmpAmount.add(tmpValue);

        }
        this.setTotalItems(tmpItems);
        this.setAmount(tmpAmount);
        this.setDetailsPDLines(detailsTmp);
    }

    /**
     * 
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * 
     * @return the estaactivo
     */
    public String getEstaactivo() {
        return estaactivo;
    }

    /**
     * @param estaactivo the estaactivo to set
     */
    public void setEstaactivo(String estaactivo) {
        this.estaactivo = estaactivo;
    }

    /**
     * 
     * @return the tipoFormadepago
     */
    public PaymentMethod getTipoFormadepago() {
        return tipoFormadepago;
    }

    /**
     * @param tipoFormadepago the tipoFormadepago to set
     */
    public void setTipoFormadepago(PaymentMethod tipoFormadepago) {
        this.tipoFormadepago = tipoFormadepago;
    }

    /**
     * 
     * @return the detailsPDLines
     */
    public Collection<CommitmentPaymentDetailsLines> getDetailsPDLines() {
        return detailsPDLines;
    }

    /**
     * @param detailsPDLines the detailsPDLines to set
     */
    public void setDetailsPDLines(Collection<CommitmentPaymentDetailsLines> detailsPDLines) {
        Collection<CommitmentPaymentDetailsLines> tmp = new ArrayList<CommitmentPaymentDetailsLines>();
        tmp.addAll(this.detailsPDLines);
        this.detailsPDLines = detailsPDLines;
        this.fire("detailsPDLines", tmp, detailsPDLines);
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(CRBigDecimal amount) {
        CRBigDecimal tmp = this.amount;

        // PaymentMethod parent = (PaymentMethod)this.paymentParent;
        parent.setMonto_recaudado(parent.getMonto_recaudado().subtract(tmp));
        this.amount = amount;
        parent.setMonto_recaudado(parent.getMonto_recaudado().add(amount));

        this.fire("amount", tmp, amount);
    }

    /**
     * 
     * @return the amount
     */
    public CRBigDecimal getAmount() {
        return amount;
    }

    /**
     * @param total the total to set
     */
    public void setTotalItems(int total) {
        int tmp = this.totalItems;
        this.totalItems = total;
        this.fire("totalItems", tmp, totalItems);
    }

    /**
     * 
     * @return the total
     */
    public int getTotalItems() {
        return totalItems;
    }

    /**
     * @param parent the parent to set
     */
    public void setParent(CashierCommitment parent) {
        this.parent = parent;
    }

    /**
     * 
     * @return the parent
     */
    public CashierCommitment getParent() {
        return parent;
    }

}
