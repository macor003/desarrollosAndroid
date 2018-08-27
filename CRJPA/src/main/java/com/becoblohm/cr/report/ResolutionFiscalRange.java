package com.becoblohm.cr.report;

import java.util.Comparator;

import com.becoblohm.cr.models.PrePrintedFiscalReceipt;
import com.becoblohm.cr.models.Transaction;

public class ResolutionFiscalRange extends FiscalNumberRange<Transaction> {

    @Override
    public Comparator<Transaction> getComparator() {

        return new Comparator<Transaction>() {

            @Override
            public int compare(Transaction o1, Transaction o2) {
                PrePrintedFiscalReceipt o1Resolution = o1.getResolution(), o2Resolution = o2.getResolution();
                if (resolutionsAreEqual(o1Resolution, o2Resolution)
                        && isNextNumber(o1.getFiscalId(), o2.getFiscalId())) {
                    return 1;
                } else {
                    return -1;
                }
            }

            private boolean resolutionsAreEqual(PrePrintedFiscalReceipt res1, PrePrintedFiscalReceipt res2) {
                return (res1.getResolucion().equals(res2.getResolucion()))
                        && res1.getSerie().equals(res2.getSerie());
            }

            private boolean isNextNumber(int o1FiscalId, int o2FiscalId) {
                return o1FiscalId >= o2FiscalId;
            }
        };
    }

    @Override
    public String printRange() {
        return "R:" + this.getStart().getResolution().getResolucion() + "SERIE:"
                + this.getStart().getResolution().getSerie() + " \nDESDE: " + this.getStart().getFiscalId()
                + " HASTA: " + this.getEnd().getFiscalId();
    }

    @Override
    public Transaction getComparableInfo(Transaction tr) {
        return tr;
    }

}
