package com.becoblohm.cr.report;

import java.util.ArrayList;
import java.util.Iterator;

import com.becoblohm.cr.models.Transaction;

public class FiscalDocumentRangeList {

    private ArrayList<FiscalNumberRange> ranges;

    private String docTypeName;

    private boolean isFormControlActive;

    public FiscalDocumentRangeList(boolean isFormControlActive, String docTypeName) {
        ranges = new ArrayList<FiscalNumberRange>();
        this.isFormControlActive = isFormControlActive;
        this.docTypeName = docTypeName;
    }

    public void add(Transaction tr) {
        if (ranges.isEmpty()) {
            ranges.add(FiscalNumberRangeFactory.createNewRange(tr, isFormControlActive));
        } else {
            addToExistingRange(tr);
        }
    }

    private void addToExistingRange(Transaction tr) {
        boolean rangeFound = false;
        for (FiscalNumberRange currentRange : ranges) {
            if (currentRange.canAddValue(tr)) {
                rangeFound = true;
                currentRange.extendRange(tr);
            }
        }

        if (!rangeFound) {
            ranges.add(FiscalNumberRangeFactory.createNewRange(tr, isFormControlActive));
        }
    }

    public String getDocType() {
        return docTypeName;
    }

    public void setDocType(String docTypeName) {
        this.docTypeName = docTypeName;
    }

    public String printRanges() {
        String buffer = docTypeName + "";
        if (ranges.isEmpty()) {
            return docTypeName + " \n DESDE 000 HASTA 000";
        }

        for (FiscalNumberRange currentRange : ranges) {
            buffer += "\n" + currentRange.printRange();
        }
        return buffer;
    }

}
