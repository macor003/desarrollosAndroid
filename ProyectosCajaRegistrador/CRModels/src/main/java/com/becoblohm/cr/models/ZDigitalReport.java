/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.models;

import java.util.Date;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import com.becoblohm.cr.types.CRBigDecimal;
import com.epa.mvc.core.AbstractModel;

/**
 */
public class ZDigitalReport extends AbstractModel {

    /**
     * Constructor for ZDigitalReport.
     */
    public ZDigitalReport() {

    }

    /**
     * Constructor for ZDigitalReport.
     * 
     * @param currentSession Session
     * @param reportDate Date
     * @param reportNumber int
     * @param printerSerial String
     * @param issuedInvoices int
     * @param lastInvoice int
     * @param lastInvoiceDate Date
     * @param issuedCreditsNotes int
     * @param lastCreditNote int
     * @param lastCreditNoteDate Date
     * @param exemptInvoicesAmmount CRBigDecimal
     * @param invoicesDetail_G ZDigitalReportDetails
     * @param invoicesDetail_R ZDigitalReportDetails
     * @param invoicesDetail_A ZDigitalReportDetails
     * @param exemptCreditsNotesAmmount CRBigDecimal
     * @param creditsNotesDetail_G ZDigitalReportDetails
     * @param creditsNotesDetail_R ZDigitalReportDetails
     * @param creditsNotesDetail_A ZDigitalReportDetails
     */
    public ZDigitalReport(Session currentSession, Date reportDate, int reportNumber, String printerSerial,
                          int issuedInvoices, int lastInvoice, Date lastInvoiceDate, int issuedCreditsNotes,
                          int lastCreditNote, Date lastCreditNoteDate, CRBigDecimal exemptInvoicesAmmount,
                          ZDigitalReportDetails invoicesDetail_G, ZDigitalReportDetails invoicesDetail_R,
                          ZDigitalReportDetails invoicesDetail_A, CRBigDecimal exemptCreditsNotesAmmount,
                          ZDigitalReportDetails creditsNotesDetail_G, ZDigitalReportDetails creditsNotesDetail_R,
                          ZDigitalReportDetails creditsNotesDetail_A) {
        // ZDigitalReport.ReportType type) {
        super();
        this.currentSession = currentSession;
        this.reportDate = reportDate;
        this.reportNumber = reportNumber;
        this.printerSerial = printerSerial;
        this.issuedInvoices = issuedInvoices;
        this.lastInvoice = lastInvoice;
        this.lastInvoiceDate = lastInvoiceDate;
        this.issuedCreditsNotes = issuedCreditsNotes;
        this.lastCreditNote = lastCreditNote;
        this.lastCreditNoteDate = lastCreditNoteDate;
        this.exemptInvoicesAmmount = exemptInvoicesAmmount;
        this.invoicesDetail_G = invoicesDetail_G;
        this.invoicesDetail_R = invoicesDetail_R;
        this.invoicesDetail_A = invoicesDetail_A;
        this.exemptCreditsNotesAmmount = exemptCreditsNotesAmmount;
        this.creditsNotesDetail_G = creditsNotesDetail_G;
        this.creditsNotesDetail_R = creditsNotesDetail_R;
        this.creditsNotesDetail_A = creditsNotesDetail_A;
        // this.type=type;
    }

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /*
     * Encabezado En la tabla reporteZ existen los campos fecha y fechacreacion. se asume
     * que son el mismo para el modelo. los campos de fecha y hora relacionados se
     * integran dentro del mismo Date de fecha.
     */
    /**
     * Field currentSession.
     */
    private Session currentSession;

    /**
     * Field reportDate.
     */
    private Date reportDate;

    /**
     * Field reportNumber.
     */
    private int reportNumber;

    /**
     * Field printerSerial.
     */
    private String printerSerial;

    /**
     * Field type.
     */
    private ReportType type;

    /*
     * Pie
     */
    /**
     * Field issuedInvoices.
     */
    private int issuedInvoices;

    /**
     * Field lastInvoice.
     */
    private int lastInvoice;

    /**
     * Field lastInvoiceDate.
     */
    private Date lastInvoiceDate;

    /**
     * Field issuedCreditsNotes.
     */
    private int issuedCreditsNotes;

    /**
     * Field lastCreditNote.
     */
    private int lastCreditNote;

    /**
     * Field lastCreditNoteDate.
     */
    private Date lastCreditNoteDate;

    /**
     * Field electronic_journal.
     */
    private byte[] electronic_journal;

    /*
     * Acumulados del dia
     */
    /**
     * Field exemptInvoicesAmmount.
     */
    private CRBigDecimal exemptInvoicesAmmount;

    /**
     * Field invoicesDetail_G.
     */
    private ZDigitalReportDetails invoicesDetail_G;

    /**
     * Field invoicesDetail_R.
     */
    private ZDigitalReportDetails invoicesDetail_R;

    /**
     * Field invoicesDetail_A.
     */
    private ZDigitalReportDetails invoicesDetail_A;

    /*
     * Diarios de notas de credito
     */
    /**
     * Field exemptCreditsNotesAmmount.
     */
    private CRBigDecimal exemptCreditsNotesAmmount;

    /**
     * Field creditsNotesDetail_G.
     */
    private ZDigitalReportDetails creditsNotesDetail_G;

    /**
     * Field creditsNotesDetail_R.
     */
    private ZDigitalReportDetails creditsNotesDetail_R;

    /**
     * Field creditsNotesDetail_A.
     */
    private ZDigitalReportDetails creditsNotesDetail_A;

    /**
     */
    public enum ReportType {
        /**
         * Field Monthly.
         */
        Monthly("MENSUAL"),
        /**
         * Field Daily.
         */
        Daily("DIARIO");

        /**
         * Field lookup.
         */
        private static final Map<String, ReportType> lookup = new HashMap<String, ReportType>();

        static {
            for (ReportType s : EnumSet.allOf(ReportType.class))
                lookup.put(s.getValue(), s);
        }

        /**
         * Field value.
         */
        private String value;

        /**
         * Constructor for ReportType.
         * 
         * @param value String
         */
        ReportType(String value) {
            this.setValue(value);
        }

        /**
         * Method get.
         * 
         * @param value String
         * @return ReportType
         */
        public static ReportType get(String value) {
            return lookup.get(value);
        }

        /**
         * @param value the value to set
         */
        public void setValue(String value) {
            this.value = value;
        }

        /**
         * 
         * @return the value
         */
        public String getValue() {
            return value;
        }

    }

    /**
     * Method getReportDate.
     * 
     * @return Date
     */
    public Date getReportDate() {
        return reportDate;
    }

    /**
     * Method setReportDate.
     * 
     * @param reportDate Date
     */
    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    /**
     * Method getReportNumber.
     * 
     * @return int
     */
    public int getReportNumber() {
        return reportNumber;
    }

    /**
     * Method setReportNumber.
     * 
     * @param reportNumber int
     */
    public void setReportNumber(int reportNumber) {
        this.reportNumber = reportNumber;
    }

    /**
     * Method getPrinterSerial.
     * 
     * @return String
     */
    public String getPrinterSerial() {
        return printerSerial;
    }

    /**
     * Method setPrinterSerial.
     * 
     * @param printerSerial String
     */
    public void setPrinterSerial(String printerSerial) {
        this.printerSerial = printerSerial;
    }

    /**
     * Method getIssuedInvoices.
     * 
     * @return int
     */
    public int getIssuedInvoices() {
        return issuedInvoices;
    }

    /**
     * Method setIssuedInvoices.
     * 
     * @param issuedInvoices int
     */
    public void setIssuedInvoices(int issuedInvoices) {
        this.issuedInvoices = issuedInvoices;
    }

    /**
     * Method getLastInvoice.
     * 
     * @return int
     */
    public int getLastInvoice() {
        return lastInvoice;
    }

    /**
     * Method setLastInvoice.
     * 
     * @param lastInvoice int
     */
    public void setLastInvoice(int lastInvoice) {
        this.lastInvoice = lastInvoice;
    }

    /**
     * Method getLastInvoiceDate.
     * 
     * @return Date
     */
    public Date getLastInvoiceDate() {
        return lastInvoiceDate;
    }

    /**
     * Method setLastInvoiceDate.
     * 
     * @param lastInvoiceDate Date
     */
    public void setLastInvoiceDate(Date lastInvoiceDate) {
        this.lastInvoiceDate = lastInvoiceDate;
    }

    /**
     * Method getIssuedCreditsNotes.
     * 
     * @return int
     */
    public int getIssuedCreditsNotes() {
        return issuedCreditsNotes;
    }

    /**
     * Method setIssuedCreditsNotes.
     * 
     * @param issuedCreditsNotes int
     */
    public void setIssuedCreditsNotes(int issuedCreditsNotes) {
        this.issuedCreditsNotes = issuedCreditsNotes;
    }

    /**
     * Method getLastCreditNote.
     * 
     * @return int
     */
    public int getLastCreditNote() {
        return lastCreditNote;
    }

    /**
     * Method setLastCreditNote.
     * 
     * @param lastCreditNote int
     */
    public void setLastCreditNote(int lastCreditNote) {
        this.lastCreditNote = lastCreditNote;
    }

    /**
     * Method getLastCreditNoteDate.
     * 
     * @return Date
     */
    public Date getLastCreditNoteDate() {
        return lastCreditNoteDate;
    }

    /**
     * Method setLastCreditNoteDate.
     * 
     * @param lastCreditNoteDate Date
     */
    public void setLastCreditNoteDate(Date lastCreditNoteDate) {
        this.lastCreditNoteDate = lastCreditNoteDate;
    }

    /**
     * Method getExemptInvoicesAmmount.
     * 
     * @return CRBigDecimal
     */
    public CRBigDecimal getExemptInvoicesAmmount() {
        return exemptInvoicesAmmount;
    }

    /**
     * Method setExemptInvoicesAmmount.
     * 
     * @param exemptInvoicesAmmount CRBigDecimal
     */
    public void setExemptInvoicesAmmount(CRBigDecimal exemptInvoicesAmmount) {
        this.exemptInvoicesAmmount = exemptInvoicesAmmount;
    }

    /**
     * Method getExemptCreditsNotesAmmount.
     * 
     * @return CRBigDecimal
     */
    public CRBigDecimal getExemptCreditsNotesAmmount() {
        return exemptCreditsNotesAmmount;
    }

    /**
     * Method getInvoicesDetail_G.
     * 
     * @return ZDigitalReportDetails
     */
    public ZDigitalReportDetails getInvoicesDetail_G() {
        return invoicesDetail_G;
    }

    /**
     * Method setInvoicesDetail_G.
     * 
     * @param invoicesDetail_G ZDigitalReportDetails
     */
    public void setInvoicesDetail_G(ZDigitalReportDetails invoicesDetail_G) {
        this.invoicesDetail_G = invoicesDetail_G;
    }

    /**
     * Method getInvoicesDetail_R.
     * 
     * @return ZDigitalReportDetails
     */
    public ZDigitalReportDetails getInvoicesDetail_R() {
        return invoicesDetail_R;
    }

    /**
     * Method setInvoicesDetail_R.
     * 
     * @param invoicesDetail_R ZDigitalReportDetails
     */
    public void setInvoicesDetail_R(ZDigitalReportDetails invoicesDetail_R) {
        this.invoicesDetail_R = invoicesDetail_R;
    }

    /**
     * Method getInvoicesDetail_A.
     * 
     * @return ZDigitalReportDetails
     */
    public ZDigitalReportDetails getInvoicesDetail_A() {
        return invoicesDetail_A;
    }

    /**
     * Method setInvoicesDetail_A.
     * 
     * @param invoicesDetail_A ZDigitalReportDetails
     */
    public void setInvoicesDetail_A(ZDigitalReportDetails invoicesDetail_A) {
        this.invoicesDetail_A = invoicesDetail_A;
    }

    /**
     * Method setExemptCreditsNotesAmmount.
     * 
     * @param exemptCreditsNotesAmmount CRBigDecimal
     */
    public void setExemptCreditsNotesAmmount(CRBigDecimal exemptCreditsNotesAmmount) {
        this.exemptCreditsNotesAmmount = exemptCreditsNotesAmmount;
    }

    /**
     * Method getCreditsNotesDetail_G.
     * 
     * @return ZDigitalReportDetails
     */
    public ZDigitalReportDetails getCreditsNotesDetail_G() {
        return creditsNotesDetail_G;
    }

    /**
     * Method setCreditsNotesDetail_G.
     * 
     * @param creditsNotesDetail_G ZDigitalReportDetails
     */
    public void setCreditsNotesDetail_G(ZDigitalReportDetails creditsNotesDetail_G) {
        this.creditsNotesDetail_G = creditsNotesDetail_G;
    }

    /**
     * Method getCreditsNotesDetail_R.
     * 
     * @return ZDigitalReportDetails
     */
    public ZDigitalReportDetails getCreditsNotesDetail_R() {
        return creditsNotesDetail_R;
    }

    /**
     * Method setCreditsNotesDetail_R.
     * 
     * @param creditsNotesDetail_R ZDigitalReportDetails
     */
    public void setCreditsNotesDetail_R(ZDigitalReportDetails creditsNotesDetail_R) {
        this.creditsNotesDetail_R = creditsNotesDetail_R;
    }

    /**
     * Method getCreditsNotesDetail_A.
     * 
     * @return ZDigitalReportDetails
     */
    public ZDigitalReportDetails getCreditsNotesDetail_A() {
        return creditsNotesDetail_A;
    }

    /**
     * Method setCreditsNotesDetail_A.
     * 
     * @param creditsNotesDetail_A ZDigitalReportDetails
     */
    public void setCreditsNotesDetail_A(ZDigitalReportDetails creditsNotesDetail_A) {
        this.creditsNotesDetail_A = creditsNotesDetail_A;
    }

    /**
     * Method getCurrentSession.
     * 
     * @return Session
     */
    public Session getCurrentSession() {
        return currentSession;
    }

    /**
     * Method setCurrentSession.
     * 
     * @param currentSession Session
     */
    public void setCurrentSession(Session currentSession) {
        this.currentSession = currentSession;
    }

    /**
     * Method getType.
     * 
     * @return ReportType
     */
    public ReportType getType() {
        return type;
    }

    /**
     * Method setType.
     * 
     * @param type ReportType
     */
    public void setType(ReportType type) {
        this.type = type;
    }

    /**
     * @param electronic_journal the electronic_journal to set
     */
    public void setElectronic_journal(byte[] electronic_journal) {
        this.electronic_journal = electronic_journal;
    }

    /**
     * 
     * @return the electronic_journal
     */
    public byte[] getElectronic_journal() {
        return electronic_journal;
    }
}
