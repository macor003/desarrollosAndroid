/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.report;

import java.util.Comparator;

import com.becoblohm.cr.models.Transaction;

public abstract class FiscalNumberRange<T> {

    private T start;

    private T end;

    /**
     * Si el comparador indica que es mayor que ambos = true Si el comparador indica que
     * es igual a ambos = true Si el comparador indica que es menor a ambos = false
     * 
     * @param tr
     * @return
     */
    public boolean canAddValue(Transaction tr) {
        Comparator<T> comparator = this.getComparator();
        T value = this.getComparableInfo(tr);
        return (comparator.compare(value, start) >= 0) && (comparator.compare(value, end) >= 0);
    }

    public void extendRange(Transaction tr) {
        T value = this.getComparableInfo(tr);
        this.setEnd(value);
    }

    public T getStart() {
        return start;
    }

    public void setStart(T start) {
        this.start = start;
    }

    public T getEnd() {
        return end;
    }

    public void setEnd(T end) {
        this.end = end;
    }

    public abstract String printRange();

    public abstract T getComparableInfo(Transaction tr);

    /**
     * 
     * @return un Comparator<T> del tipo a comparar que debe seguir las reglas de < 0 para
     *         indicar que el primer elemento es menor que otro > 0 para indicar que el
     *         primer elemento es mayor al otro = 0 para indicar que son iguales
     */
    public abstract Comparator<T> getComparator();

}
