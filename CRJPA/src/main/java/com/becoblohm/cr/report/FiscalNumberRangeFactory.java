package com.becoblohm.cr.report;

import com.becoblohm.cr.models.Transaction;

public class FiscalNumberRangeFactory {

    public static FiscalNumberRange createNewRange(Transaction tr, boolean isFormControlActive) {
        if (isFormControlActive) {
            ResolutionFiscalRange newResolutionRange = new ResolutionFiscalRange();
            newResolutionRange.setEnd(tr);
            newResolutionRange.setStart(tr);
            return newResolutionRange;
        } else {
            SimpleFiscalNumberRange newRange = new SimpleFiscalNumberRange();
            newRange.setEnd((long) tr.getFiscalId());
            newRange.setStart((long) tr.getFiscalId());
            return newRange;
        }
    }
}
