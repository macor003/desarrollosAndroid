package com.becoblohm.cr.types;

import com.becoblohm.cr.models.Tax;

/**
 * 
 * @author eve0017820 Esta clase tiene como intencion poder usar de mejor manera al
 *         momento de imprimir la relacion de impuesto presente y el monto que representa
 *         de un documento
 */
public class TaxAmountWrapper {

    private Tax tax;

    private CRBigDecimal amount;

    public TaxAmountWrapper() {
    }

    public TaxAmountWrapper(Tax tax, CRBigDecimal amount) {
        this.tax = tax;
        this.amount = amount;
    }

    public Tax getTax() {
        return tax;
    }

    public void setTax(Tax tax) {
        this.tax = tax;
    }

    public CRBigDecimal getAmount() {
        return amount;
    }

    public void setAmount(CRBigDecimal amount) {
        this.amount = amount;
    }

}
