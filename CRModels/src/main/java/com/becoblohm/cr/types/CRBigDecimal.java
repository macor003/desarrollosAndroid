/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.types;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CRBigDecimal implements Serializable, Comparable<CRBigDecimal> {

    /**
     * 
     */
    private static final long serialVersionUID = 6542729125162611846L;

    public static final CRBigDecimal ZERO = new CRBigDecimal(BigInteger.ZERO);

    public static final CRBigDecimal ONE = new CRBigDecimal(BigInteger.ONE);

    public static final CRBigDecimal TEN = new CRBigDecimal(BigInteger.TEN);

    public static final CRBigDecimal HUNDRED = new CRBigDecimal(BigInteger.valueOf(100));

    /**
     * Field SCALE. (value is 2)
     */
    private static final int SCALE = 2;

    /**
     * Field ROUNDING.
     */
    private static final RoundingMode ROUNDING = RoundingMode.HALF_EVEN;

    /**
     * Field value.
     */
    private BigDecimal value = BigDecimal.ZERO.setScale(SCALE);

    /**
     * Field scale.
     */
    private int scale = 2;

    public CRBigDecimal() {
        super();
    }

    /**
     * Method getValue.
     * 
     * @return BigDecimal
     */
    public BigDecimal getValue() {
        return value;
    }

    /**
     * Se obtiene la parte entera del numero, Se usa to String para evitar transformar a
     * un entero que no quepa en 2^32 que es el entero corriente
     * 
     * @return La parte entera del Numero
     */
    @JsonIgnore
    public BigInteger getIntegerPart() {
        String[] parts = value.toString().split("\\.");
        BigInteger intWhole = new BigInteger(parts[0]);
        return intWhole;
    }

    /**
     * Constructor for CRBigDecimal.
     * 
     * @param val double
     */
    public CRBigDecimal(double val) {

        this.value = BigDecimal.valueOf(val).setScale(SCALE, ROUNDING).setScale(SCALE);

    }

    /**
     * Constructor for CRBigDecimal.
     * 
     * @param val double
     * @param SCALE int
     */
    public CRBigDecimal(double val, int SCALE) {
        this.value = BigDecimal.valueOf(val).setScale(SCALE, ROUNDING).setScale(SCALE);
        this.scale = SCALE;

    }

    /**
     * Constructor for CRBigDecimal.
     * 
     * @param val BigInteger
     */
    public CRBigDecimal(BigInteger val) {
        if (val != null) {
            this.value = new BigDecimal(val).setScale(SCALE);
        } else {
            this.value = BigDecimal.ZERO.setScale(SCALE);
        }
    }

    /**
     * Constructor for CRBigDecimal.
     * 
     * @param val BigDecimal
     */
    public CRBigDecimal(BigDecimal val) {
        if (val != null) {
            this.value = val.setScale(SCALE, ROUNDING).setScale(SCALE);
        } else {
            this.value = BigDecimal.ZERO.setScale(SCALE);
        }

    }

    /**
     * Constructor for CRBigDecimal.
     * 
     * @param val BigDecimal
     * @param scale int
     */
    public CRBigDecimal(BigDecimal val, int scale) {
        if (val != null) {
            this.value = val.setScale(scale, ROUNDING).setScale(scale);
            this.scale = scale;
        } else {
            this.value = BigDecimal.ZERO.setScale(SCALE);
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
                this.value = new BigDecimal(val).setScale(SCALE, ROUNDING).setScale(SCALE);
            }
        } else {
            this.value = BigDecimal.ZERO.setScale(SCALE);
        }
    }

    /**
     * Constructor for CRBigDecimal.
     * 
     * @param val String
     * @param scale int
     */
    public CRBigDecimal(String val, int scale) {
        if (val != null) {
            this.value = new BigDecimal(val).setScale(scale, ROUNDING).setScale(scale);
            this.scale = scale;
        } else {
            this.value = BigDecimal.ZERO.setScale(scale);
        }
    }

    /**
     * Method hasDecimal.
     * 
     * @return boolean
     */
    public boolean hasDecimal() {

        return this.value.stripTrailingZeros().scale() > 0;

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

        BigDecimal augendtmp = new BigDecimal(augend.doubleValue()).setScale(SCALE, ROUNDING).setScale(SCALE);
        this.value = new BigDecimal(value.doubleValue()).setScale(scale, ROUNDING).setScale(scale);
        CRBigDecimal result = new CRBigDecimal(this.value.add(augendtmp), SCALE);

        return new CRBigDecimal(result.doubleValue(), SCALE);

    }

    /**
     * Method compareTo.
     * 
     * @param val CRBigDecimal
     * @return int
     */
    @Override
    public int compareTo(CRBigDecimal val) {
        return this.value.compareTo(val.getValue());
    }

    /**
     * Method equals.
     * 
     * @param val CRBigDecimal
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof CRBigDecimal) || o == null) {
            return false;
        }
        CRBigDecimal val = (CRBigDecimal) o;
        return this.value.equals(val.getValue());
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    /**
     * Method divide.
     * 
     * @param divisor CRBigDecimal
     * @return CRBigDecimal
     */
    public CRBigDecimal divide(CRBigDecimal divisor) {
        this.value = new BigDecimal(value.doubleValue()).setScale(scale, ROUNDING).setScale(scale);
        BigDecimal divisortmp = new BigDecimal(divisor.doubleValue()).setScale(SCALE, ROUNDING).setScale(SCALE);

        CRBigDecimal result = new CRBigDecimal(this.value.divide(divisortmp, SCALE, ROUNDING));

        return new CRBigDecimal(result.doubleValue(), SCALE);
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

        BigDecimal tmpMultiplicand = new BigDecimal(multiplicand.doubleValue()).setScale(SCALE, ROUNDING)
                .setScale(SCALE);
        CRBigDecimal result = new CRBigDecimal(this.value.multiply(tmpMultiplicand), SCALE);
        return result;

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
    @Override
    public String toString() {
        DecimalFormat tmp = new DecimalFormat("##,###,##0.00", new DecimalFormatSymbols(Locale.getDefault()));
        BigDecimal tmp3 = new BigDecimal(this.value.doubleValue()).setScale(SCALE, RoundingMode.HALF_EVEN);
        return tmp.format(tmp3);
    }

    /**
     * Method valueOf.
     * 
     * @param value String
     * @return CRBigDecimal
     */
    public static CRBigDecimal valueOf(String value) {
        DecimalFormat tmp = new DecimalFormat("##,###,##0.00", new DecimalFormatSymbols(Locale.getDefault()));
        Double res = 0d;
        try {
            res = (Double) tmp.parse(value).doubleValue();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return new CRBigDecimal(res);
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
        return new CRBigDecimal(value);

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
        return new CRBigDecimal(value);

    }

    /**
     * Method valueOf.
     * 
     * @param value long
     * @return CRBigDecimal
     */
    public static CRBigDecimal valueOf(long value) {
        return new CRBigDecimal(value);

    }

    /**
     * Method doubleValue.
     * 
     * @return double
     */
    public double doubleValue() {

        return this.value.doubleValue();
    }

    public boolean isZero() {
        return this.compareTo(CRBigDecimal.ZERO) == 0;
    }

    public boolean greaterThan(CRBigDecimal other) {
        return this.compareTo(other) > 0;
    }

    public boolean lessThan(CRBigDecimal other) {
        return this.compareTo(other) < 0;
    }

    public boolean notEquals(CRBigDecimal other) {
        return this.compareTo(other) != 0;
    }
}
