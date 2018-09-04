/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.report;

import java.text.DecimalFormat;
import java.util.Comparator;

import com.becoblohm.cr.models.Transaction;

public class SimpleFiscalNumberRange extends FiscalNumberRange<Long> {

    @Override
    public Comparator<Long> getComparator() {

        return new Comparator<Long>() {

            @Override
            public int compare(Long o1, Long o2) {
                if (o1 == o2) {
                    return 0;
                } else if (o1 > o2) {
                    return 1;
                } else {
                    return -1;
                }
            }
        };
    }

    @Override
    public String printRange() {
        DecimalFormat format = new DecimalFormat("000");
        return "DESDE: " + format.format(this.getStart()) + " HASTA: " + format.format(this.getEnd());
    }

    @Override
    public Long getComparableInfo(Transaction tr) {
        return (long) tr.getFiscalId();
    }

}
