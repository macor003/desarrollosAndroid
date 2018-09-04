/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.models;

import java.util.Date;

import com.becoblohm.cr.types.CRBigDecimal;
import com.epa.mvc.core.AbstractModel;

/**
 */
public class Tax extends AbstractModel {

    /**
     * Constructor for Tax.
     */
    public Tax() {
        super();

    }

    public Tax(String name) {
        this.name = name;
    }

    /**
     * Constructor for Tax.
     * 
     * @param name String
     * @param taxRate CRBigDecimal
     */
    public Tax(String name, CRBigDecimal taxRate) {
        super();
        this.name = name;
        this.taxRate = taxRate;
    }

    /**
     * Method clone.
     * 
     * @return Tax
     */
    public Tax clone() {
        Tax taxfree = new Tax();
        taxfree.name = String.valueOf(this.name);
        taxfree.active = Boolean.valueOf(this.active);
        taxfree.taxDate = new Date(this.taxDate.getTime());
        taxfree.taxRate = new CRBigDecimal(this.taxRate.doubleValue());
        taxfree.id = Long.valueOf(this.id);
        taxfree.code = String.valueOf(this.code);
        return taxfree;
    }

    /**
     * Field name.
     */
    private String name;

    /**
     * Field active.
     */
    private boolean active;

    /**
     * Field taxDate.
     */
    private Date taxDate;

    /**
     * Field taxRate.
     */
    private CRBigDecimal taxRate;

    /**
     * Field id.
     */
    private Long id;

    /**
     * Field code.
     */
    private String code;

    /**
     * Method getName.
     * 
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Method setName.
     * 
     * @param name String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method isActive.
     * 
     * @return boolean
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Method setActive.
     * 
     * @param active boolean
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Method getTaxDate.
     * 
     * @return Date
     */
    public Date getTaxDate() {
        return taxDate;
    }

    /**
     * Method setTaxDate.
     * 
     * @param taxDate Date
     */
    public void setTaxDate(Date taxDate) {
        this.taxDate = taxDate;
    }

    /**
     * Method getTaxRate.
     * 
     * @return CRBigDecimal
     */
    public CRBigDecimal getTaxRate() {
        return taxRate;
    }

    /**
     * Method setTaxRate.
     * 
     * @param taxRate CRBigDecimal
     */
    public void setTaxRate(CRBigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    /**
     * Method setId.
     * 
     * @param id Long
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Method getId.
     * 
     * @return long
     */
    public long getId() {
        return this.id;
    }

    /**
     * Method setCode.
     * 
     * @param codigo String
     */
    public void setCode(String codigo) {
        this.code = codigo;

    }

    /**
     * 
     * @return the code
     */
    public String getCode() {
        return code;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((code == null) ? 0 : code.hashCode());
        result = prime * result + ((taxRate == null) ? 0 : taxRate.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Tax other = (Tax) obj;
        if (code == null) {
            if (other.code != null) {
                return false;
            }
        } else if (!code.equals(other.code)) {
            return false;
        }
        if (taxRate == null) {
            if (other.taxRate != null) {
                return false;
            }
        } else if (!taxRate.equals(other.taxRate)) {
            return false;
        }
        return true;
    }

}
