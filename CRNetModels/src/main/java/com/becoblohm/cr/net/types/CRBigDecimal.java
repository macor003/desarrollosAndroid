/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.net.types;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;

/**
 */
public class CRBigDecimal implements Serializable, Comparable {

    /**
     * 
     */
    private static final long serialVersionUID = 6542729125162611846L;

    /*
     * REDONDEO APLICADO tmp = tmp.setScale(6,CRBigDecimal.ROUND_HALF_UP);
     */

    /**
     * Field ZERO.
     */
    public static CRBigDecimal ZERO = new CRBigDecimal(0);

    /**
     * Field ONE.
     */
    public static CRBigDecimal ONE = new CRBigDecimal(1);

    /**
     * Field TEN.
     */
    public static CRBigDecimal TEN = new CRBigDecimal(10);

    /**
     * Field SCALE.
     */
    private static int SCALE = 2;

    /**
     * Field ROUNDING.
     */
    private static int ROUNDING = BigDecimal.ROUND_HALF_EVEN;

    /**
     * Field value.
     */
    private BigDecimal value = new BigDecimal("0");

    /**
     * Method getValue.
     * 
     * @return BigDecimal
     */
    public BigDecimal getValue() {
        return value;
    }

    /**
     * Constructor for CRBigDecimal.
     */
    public CRBigDecimal() {
    }

    /**
     * Constructor for CRBigDecimal.
     * 
     * @param val double
     */
    public CRBigDecimal(double val) {

        this.value = new BigDecimal(val).setScale(SCALE, ROUNDING);

    }

    /**
     * Constructor for CRBigDecimal.
     * 
     * @param val double
     * @param SCALE int
     */
    public CRBigDecimal(double val, int SCALE) {

        this.value = new BigDecimal(val).setScale(SCALE, ROUNDING);

    }

    /**
     * Constructor for CRBigDecimal.
     * 
     * @param val BigInteger
     */
    public CRBigDecimal(BigInteger val) {
        if (val != null) {
            this.value = new BigDecimal(val);
        } else {
            this.value = new BigDecimal("0");
        }
    }

    /**
     * Constructor for CRBigDecimal.
     * 
     * @param val BigDecimal
     */
    private CRBigDecimal(BigDecimal val) {
        if (val != null) {
            this.value = val.setScale(SCALE, ROUNDING);
        } else {
            this.value = new BigDecimal("0");
        }

    }

    /**
     * Constructor for CRBigDecimal.
     * 
     * @param val String
     */
    public CRBigDecimal(String val) {
        if (val != null) {
            if (StringUtils.isNumeric(val)) {
                this.value = new BigDecimal(val).setScale(SCALE, ROUNDING);
            }
        } else {
            this.value = new BigDecimal("0");
        }
    }

    /**
     * Constructor for CRBigDecimal.
     * 
     * @param val String
     * @param scale int
     */
    public CRBigDecimal(String val, int scale) {
        SCALE = scale;
        if (val != null) {
            this.value = new BigDecimal(val).setScale(SCALE, ROUNDING);
        } else {
            this.value = new BigDecimal("0");
        }
    }

    /**
     * Method abs.
     * 
     * @return CRBigDecimal
     */
    public CRBigDecimal abs() {

        return new CRBigDecimal(this.value.abs());

    }

    /**
     * Method add.
     * 
     * @param augend CRBigDecimal
     * @return CRBigDecimal
     */
    public CRBigDecimal add(CRBigDecimal augend) {

        return new CRBigDecimal(this.value.add(augend.getValue()));
    }

    /**
     * Method compareTo.
     * 
     * @param val Object
     * @return int
     */
    public int compareTo(Object val) {

        return this.value.compareTo(((CRBigDecimal) val).getValue());
    }

    /**
     * Method equals.
     * 
     * @param val CRBigDecimal
     * @return boolean
     */
    public boolean equals(Object o) {
        if (o == null || !(o instanceof CRBigDecimal))
            return false;
        CRBigDecimal val = (CRBigDecimal) o;
        return this.value.equals(val.getValue());
    }

    /**
     * Method divide.
     * 
     * @param divisor CRBigDecimal
     * @return CRBigDecimal
     */
    public CRBigDecimal divide(CRBigDecimal divisor) {

        return new CRBigDecimal(this.value.divide(divisor.getValue(), SCALE, ROUNDING));
    }

    /**
     * Method max.
     * 
     * @param val CRBigDecimal
     * @return CRBigDecimal
     */
    public CRBigDecimal max(CRBigDecimal val) {

        return new CRBigDecimal(this.value.max(val.getValue()));
    }

    /**
     * Method min.
     * 
     * @param val CRBigDecimal
     * @return CRBigDecimal
     */
    public CRBigDecimal min(CRBigDecimal val) {

        return new CRBigDecimal(this.value.min(val.getValue()));
    }

    /**
     * Method multiply.
     * 
     * @param multiplicand CRBigDecimal
     * @return CRBigDecimal
     */
    public CRBigDecimal multiply(CRBigDecimal multiplicand) {

        return new CRBigDecimal(this.value.multiply(multiplicand.getValue()));
    }

    /**
     * Method negate.
     * 
     * @return CRBigDecimal
     */
    public CRBigDecimal negate() {

        return new CRBigDecimal(this.value.negate());
    }

    /**
     * Method toString.
     * 
     * @return String
     */
    public String toString() {
        DecimalFormat tmp = new DecimalFormat("##,###,##0.00", new DecimalFormatSymbols(Locale.getDefault()));
        return tmp.format(this.doubleValue());
        // return this.value.toString();
    }

    /**
     * Method subtract.
     * 
     * @param subtrahend CRBigDecimal
     * @return CRBigDecimal
     */
    public CRBigDecimal subtract(CRBigDecimal subtrahend) {

        return new CRBigDecimal(this.value.subtract(subtrahend.getValue()));
    }

    /**
     * Method valueOf.
     * 
     * @param value Double
     * @return CRBigDecimal
     */
    public static CRBigDecimal valueOf(Double value) {
        return new CRBigDecimal(value.doubleValue());

    }

    /**
     * Method valueOf.
     * 
     * @param value double
     * @return CRBigDecimal
     */
    public static CRBigDecimal valueOf(double value) {
        return new CRBigDecimal(value);

    }

    /**
     * Method valueOf.
     * 
     * @param value int
     * @return CRBigDecimal
     */
    public static CRBigDecimal valueOf(int value) {
        return new CRBigDecimal(value);

    }

    /**
     * Method valueOf.
     * 
     * @param value Long
     * @return CRBigDecimal
     */
    public static CRBigDecimal valueOf(Long value) {
        return new CRBigDecimal(value.doubleValue());

    }

    /**
     * Method valueOf.
     * 
     * @param value long
     * @return CRBigDecimal
     */
    public static CRBigDecimal valueOf(long value) {
        return new CRBigDecimal(String.valueOf(value));

    }

    /**
     * Method doubleValue.
     * 
     * @return double
     */
    public double doubleValue() {

        return this.value.doubleValue();
    }

}
