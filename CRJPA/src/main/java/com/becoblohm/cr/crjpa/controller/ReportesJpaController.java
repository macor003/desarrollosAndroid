/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.crjpa.controller;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.persistence.EntityManagerFactory;

import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.becoblohm.cr.models.CreditsOperation;
import com.becoblohm.cr.models.CreditsOperationType;
import com.becoblohm.cr.models.Transaction.SourceTransactionType;
import com.becoblohm.cr.report.FiscalDocumentRangeList;
import com.becoblohm.cr.types.ActiveValues;
import com.becoblohm.cr.types.CRBigDecimal;
import com.becoblohm.cr.utils.CRUtils;
import com.becoblohm.cr.utils.Utils;
import com.becoblohm.cr.utils.report.DocumentsTotalsContainer;
import com.becoblohm.cr.utils.report.PrintingUtils;
import com.becoblohm.cr.utils.report.ReportPrintingBehaviour;
import com.becoblohm.cr.utils.report.ReportsDetailsContainer;

import crjpa.Acreenciamovimiento;
import crjpa.Formadepago;
import crjpa.Formadepagomoneda;
import crjpa.Moneda;
import crjpa.Operacionacreencia;
import crjpa.Tipodocumento;
import crjpa.Transaccion;
import crjpa.Transaccionarticulo;
import crjpa.Transacciondocumento;
import crjpa.Transaccionformadepago;

/**
 */
public class ReportesJpaController extends AbstractJPAController {

    private static final long serialVersionUID = 1L;

    /**
     * Field emf.
     */
    private final EntityManagerFactory emf;

    /**
     * Field storeName.
     */
    private String storeName;

    /**
     * Field rif.
     */
    private String rif;

    /**
     * Field nit.
     */
    private String nit;

    /**
     * Field address.
     */
    private String address;

    /**
     * Field store.
     */
    private String store;

    /**
     * Field cashierID.
     */
    private String cashierID;

    /**
     * Field cashierName.
     */
    private String cashierName;

    /**
     * Field date.
     */
    private String date;

    /**
     * Field time.
     */
    private String time;

    /**
     * Field pos.
     */
    private String pos;

    /**
     * Field entityName.
     */
    private static String entityName = "Reportex";

    /**
     * Field detailsMap.
     */
    private Map<String, ReportsDetailsContainer> detailsMap;

    /**
     * Field isDetailZ.
     */
    private boolean isDetailZ;

    /**
     * Field totalDonation.
     */
    private CRBigDecimal totalDonation;

    private CRBigDecimal creditsTotal;

    private long creditsPaymentMethod;

    private boolean isFormControlActive;

    private long defaultCurrencyId;

    /**
     * Constructor for ReportesJpaController.
     * 
     * @param emf EntityManagerFactory
     */
    public ReportesJpaController(EntityManagerFactory emf) {
        super(AbstractJPAController.POSSOURCE, entityName);
        this.emf = emf;
        // sessionController=new SesionJpaController(emf);
    }

    /**
     * Method createBodyXorZReport.
     * 
     * @param initialDate Date
     * @param finalDate Date
     * @param ficha long
     * @param isZreport boolean
     * @return ArrayList<String>
     */
    public ArrayList<String> createBodyXorZReport(Date initialDate, Date finalDate, long ficha,
                                                  boolean isZreport) {
        return createBodyXorZReport(initialDate, finalDate, ficha, isZreport, false);
    }

    /**
     * Method createBodyXorZReport.
     * 
     * @param initialDate Date
     * @param finalDate Date
     * @param ficha long
     * @param isZReport boolean
     * @param showpercibido boolean
     * @return ArrayList<String>
     */
    public ArrayList<String> createBodyXorZReport(Date initialDate, Date finalDate, long ficha, boolean isZReport,
                                                  boolean showpercibido) {

        boolean existeTrans = false;
        ArrayList<String> reportLines = new ArrayList<String>();
        TransaccionJpaController jpaTransaccion = new TransaccionJpaController(emf);
        MonedaJpaController jpaMoneda = new MonedaJpaController(emf);
        Moneda defaultCurrency = jpaMoneda.findMoneda(this.defaultCurrencyId);
        List<Transaccion> tmp = new ArrayList<Transaccion>();
        tmp = jpaTransaccion.findByDate(initialDate, finalDate, ficha, isZReport);
        totalDonation = CRBigDecimal.ZERO;

        if (tmp.size() > 0) {
            existeTrans = true;
        }
        // if (existeTrans) {
        TreeMap<String, DocumentsTotalsContainer> docs = new TreeMap<String, DocumentsTotalsContainer>();
        TreeMap<String, FiscalDocumentRangeList> docRanges = new TreeMap<String, FiscalDocumentRangeList>();

        TipodocumentoJpaController jpaController = new TipodocumentoJpaController(this.emf);
        List<Tipodocumento> tipoDoc = jpaController.findTipodocumentoEntitiesJPA();

        for (Iterator<Tipodocumento> iterator = tipoDoc.iterator(); iterator.hasNext();) {
            createContainersByDocType(docs, docRanges, iterator);
        }

        if (existeTrans) {
            getDocumentTypeTransactions(tmp, docRanges, docs);
            computeCreditsTotal(initialDate, finalDate, ficha, isZReport);
        }

        addDetailInfo(reportLines, true, isZReport);

        printDocumentRanges(reportLines, docRanges);

        printDocumentTotals(showpercibido, reportLines, defaultCurrency, docs);

        reportLines.add(Utils.crearSeparador("-"));
        if (!isZReport) {
            reportLines.add(" TOT. PARCIAL DEL DIA (TOT. X) ");
        } else {
            reportLines.add(" TOT. DEL DIA (TOT. Z) ");
        }
        reportLines.add(Utils.crearSeparador("-"));

        CRBigDecimal totalTotal = CRBigDecimal.ZERO;
        for (String key : docs.keySet()) {
            DocumentsTotalsContainer details = docs.get(key);
            reportLines.add(PrintingUtils
                    .alinear(key + "  " + defaultCurrency.getSimbolo() + " " + details.getTotalDiario(), "  "));
            totalTotal = totalTotal.add(details.getTotalDiario());
        }

        if (isZReport) {
            if (CRUtils.isSameDay(initialDate, finalDate)) {
                reportLines.add("TOT. DEL DIA (TOT. Z): " + defaultCurrency.getSimbolo() + " " + totalTotal);
            } else {
                reportLines.add("TOT. MENSUAL (GRAN TOT. Z): " + defaultCurrency.getSimbolo() + " " + totalTotal);
            }
        } else {
            reportLines.add("TOT. PARCIAL DEL DIA (TOT. X): " + defaultCurrency.getSimbolo() + " " + totalTotal);
        }
        reportLines.add(Utils.crearSeparador("-"));

        return reportLines;
    }

    private void printDocumentTotals(boolean showpercibido, ArrayList<String> reportLines, Moneda defaultCurrency,
                                     TreeMap<String, DocumentsTotalsContainer> docs) {
        reportLines.add(Utils.crearSeparador("-"));
        reportLines.add(" TOT. DE VENTAS GRAVADAS ");
        reportLines.add(Utils.crearSeparador("-"));
        for (String key : docs.keySet()) {
            DocumentsTotalsContainer details = docs.get(key);

            reportLines.add(PrintingUtils
                    .alinear(key + "  " + defaultCurrency.getSimbolo() + " " + details.getTotalGravado(), "  "));

            details.setTotalDiario(details.getTotalDiario().add(details.getTotalGravado()));
        }

        reportLines.add(Utils.crearSeparador("-"));
        reportLines.add(" TOT. DE VENTAS EXENTAS ");
        reportLines.add(Utils.crearSeparador("-"));
        for (String key : docs.keySet()) {

            DocumentsTotalsContainer details = docs.get(key);

            reportLines.add(PrintingUtils
                    .alinear(key + "  " + defaultCurrency.getSimbolo() + " " + details.getTotalExento(), "  "));
            details.setTotalDiario(details.getTotalDiario().add(details.getTotalExento()));
        }

        reportLines.add(Utils.crearSeparador("-"));
        reportLines.add(" TOT. DE I.V.A. ");
        reportLines.add(Utils.crearSeparador("-"));
        for (String key : docs.keySet()) {
            DocumentsTotalsContainer details = docs.get(key);

            reportLines.add(PrintingUtils
                    .alinear(key + "  " + defaultCurrency.getSimbolo() + " " + details.getTotalImpuesto(), "  "));
            details.setTotalDiario(details.getTotalDiario().add(details.getTotalImpuesto()));
        }

        if (showpercibido) {
            reportLines.add(Utils.crearSeparador("-"));
            reportLines.add("TOT. DE I.V.A. PERCIBIDO");
            reportLines.add(Utils.crearSeparador("-"));
            for (String key : docs.keySet()) {
                // DocumentsTotalsContainer details = docs.get(key);
                // TODO Magroberth - por ahora es asi porque Luis dijo que no
                // eramos una tienda grande
                reportLines.add(PrintingUtils
                        .alinear(key + "  " + defaultCurrency.getSimbolo() + " " + CRBigDecimal.ZERO, "  "));
            }
            // TODO Magroberth - por ahora es asi porque Luis dijo que no eramos
            // una tienda grande
            reportLines.add(PrintingUtils
                    .alinear("TOT. DE PERCIBIDO:" + "  " + defaultCurrency.getSimbolo() + " " + CRBigDecimal.ZERO,
                             "  "));
        }
    }

    private void printDocumentRanges(ArrayList<String> reportLines,
                                     TreeMap<String, FiscalDocumentRangeList> docRanges) {
        reportLines.add(Utils.crearSeparador(" "));
        reportLines.add(Utils.centrarCadena("DETALLE DE DOCUMENTOS", 38));
        reportLines.add(Utils.crearSeparador("-"));
        for (String key : docRanges.keySet()) {
            FiscalDocumentRangeList range = docRanges.get(key);
            reportLines.add(range.printRanges());
        }
    }

    private void computeCreditsTotal(Date initialDate, Date finalDate, long employeeId, boolean isZReport) {
        creditsTotal = CRBigDecimal.ZERO;
        AcreenciamovimientoJpaController creditsMovementJpa = new AcreenciamovimientoJpaController(emf);
        List<Acreenciamovimiento> creditsMovements = creditsMovementJpa.findByDate(initialDate, finalDate,
                                                                                   employeeId, isZReport);

        for (Acreenciamovimiento movement : creditsMovements) {
            CRBigDecimal amount = new CRBigDecimal(movement.getMontoMonedaLocal().doubleValue());
            Operacionacreencia operation = movement.getIdOperacionacreencia();
            long operationId = operation.getId();
            long paymentMthod = movement.getIdFormadepago().getId();

            if (operationId == 1 && paymentMthod != creditsPaymentMethod) {
                creditsTotal = creditsTotal.add(amount);
            } else if (operationId == 2 || operationId == 3) {
                creditsTotal = creditsTotal.subtract(amount);
            }
        }
    }

    private void createContainersByDocType(TreeMap<String, DocumentsTotalsContainer> docs,
                                           TreeMap<String, FiscalDocumentRangeList> docRanges,
                                           Iterator<Tipodocumento> iterator) {
        Tipodocumento tipodoc = iterator.next();

        if (ActiveValues.get(tipodoc.getEsfiscal()).getValue()) {
            String docType = tipodoc.getNombre();

            /*
             * si es nota de credito(si es fiscal y es su misma contrapartida)
             */
            if (tipodoc.getIdContrapartida() != null && tipodoc.getId() == tipodoc.getIdContrapartida().getId()) {
                docs.put(docType + "-DEV", new DocumentsTotalsContainer());
                docs.put(docType + "-ANU", new DocumentsTotalsContainer());

            } else { // si es factura

                docs.put(docType, new DocumentsTotalsContainer());
            }

            docRanges.put(docType, new FiscalDocumentRangeList(this.isFormControlActive, docType));
        }
    }

    private void getDocumentTypeTransactions(List<Transaccion> tmp,
                                             TreeMap<String, FiscalDocumentRangeList> docRanges,
                                             TreeMap<String, DocumentsTotalsContainer> docs) {

        TransaccionJpaController trJPAC = new TransaccionJpaController(emf);

        for (Transaccion transTmp : tmp) {
            addToDonation(transTmp);

            List<Transaccionarticulo> art = transTmp.getTransaccionarticuloList();
            ArrayList<Transaccionarticulo> articles = new ArrayList<Transaccionarticulo>();
            CRBigDecimal totalGrav = new CRBigDecimal(BigDecimal.ZERO.doubleValue());
            CRBigDecimal totalImp = new CRBigDecimal(BigInteger.ZERO.doubleValue());
            CRBigDecimal totalExent = new CRBigDecimal(BigDecimal.ZERO.doubleValue());

            for (Transaccionarticulo tmpArt : art) {
                articles.add(tmpArt);
                if (tmpArt.getMontoImpuesto().compareTo(BigDecimal.ZERO) <= 0) {
                    totalExent = totalExent.add(new CRBigDecimal(tmpArt.getTotalBase().doubleValue()));
                }
            }
            if (transTmp.getTotalImpuesto().compareTo(BigDecimal.ZERO) > 0) {
                totalGrav = new CRBigDecimal(transTmp.getTotalBase().doubleValue()).subtract(totalExent);
                totalImp = new CRBigDecimal(transTmp.getTotalImpuesto().doubleValue());
            } else {
                if (totalExent.compareTo(CRBigDecimal.ZERO) <= 0) {
                    totalExent = totalExent.add(new CRBigDecimal(transTmp.getTotalBase().doubleValue()));
                }
            }

            for (Iterator<Transacciondocumento> iterator = transTmp.getTransacciondocumentoList()
                    .iterator(); iterator.hasNext();) {
                Transacciondocumento doc = iterator.next();

                if (ActiveValues.get(doc.getIdTipodocumento().getEsfiscal()).getValue()) {
                    String docTotalKey = "", docRangesKey = "";

                    docTotalKey = setDocTotalKey(transTmp, doc);

                    docRangesKey = doc.getIdTipodocumento().getNombre();

                    if (docs.containsKey(docTotalKey)) {

                        Tipodocumento currentDoc = doc.getIdTipodocumento();
                        DocumentsTotalsContainer container = docs.get(docTotalKey);
                        addContainerInfo(transTmp, totalGrav, totalImp, totalExent, currentDoc, container);

                        docs.put(docTotalKey, container);
                    }

                    if (docRanges.containsKey(docRangesKey)) {
                        FiscalDocumentRangeList rangeList = docRanges.get(docRangesKey);
                        rangeList.add(trJPAC.fromJPA(transTmp));
                        docRanges.put(docRangesKey, rangeList);
                    }

                }
            }
        }
    }

    private String setDocTotalKey(Transaccion transTmp, Transacciondocumento doc) {
        String docTotalKey;
        boolean isCancellationOrRefund = ((doc.getIdTipodocumento().getIdContrapartida() != null)
                && (doc.getIdTipodocumento().getId() == doc.getIdTipodocumento().getIdContrapartida().getId()));

        if (isCancellationOrRefund) {
            if (transTmp.getTipo().equals(SourceTransactionType.Cancellation.getValue())) {
                docTotalKey = doc.getIdTipodocumento().getNombre() + "-ANU";
            } else {
                docTotalKey = doc.getIdTipodocumento().getNombre() + "-DEV";
            }
        } else {
            docTotalKey = doc.getIdTipodocumento().getNombre();
        }
        return docTotalKey;
    }

    private void addToDonation(Transaccion transTmp) {
        if (transTmp.getTipo().equalsIgnoreCase(SourceTransactionType.Sale.getValue().toString())) {
            totalDonation = totalDonation.add(new CRBigDecimal(transTmp.getDonacion().doubleValue()));
        } else if (transTmp.getTipo().equalsIgnoreCase(SourceTransactionType.Cancellation.getValue().toString())) {
            totalDonation = totalDonation.subtract(new CRBigDecimal(transTmp.getDonacion().doubleValue()));
        }
    }

    private CRBigDecimal sumPayments(Transaccion transaction) {
        CRBigDecimal sum = CRBigDecimal.ZERO;

        for (Transaccionformadepago tfdp : transaction.getTransaccionformadepagoList()) {
            sum = sum.add(new CRBigDecimal(tfdp.getMontoMoneda().doubleValue()));
        }

        return sum;
    }

    /**
     * This method adds or modifies the information stored in the container, such as,
     * document type - transaction range numbers and totals, for Cancellations or Refunds
     * it negates the amounts for the final calculations.
     * 
     * @param transTmp
     * @param totalGrav
     * @param totalImp
     * @param totalExent
     * @param currentDoc
     * @param container
     */
    private void addContainerInfo(Transaccion transTmp, CRBigDecimal totalGrav, CRBigDecimal totalImp,
                                  CRBigDecimal totalExent, Tipodocumento currentDoc,
                                  DocumentsTotalsContainer container) {

        String transType = transTmp.getTipo();

        // Si es devolucion o anulacion.
        if (transType.equals(SourceTransactionType.Refund.getValue())
                || transType.equals(SourceTransactionType.Cancellation.getValue())) {
            totalGrav = totalGrav.negate();
            totalImp = totalImp.negate();
            totalExent = totalExent.negate();
        }

        if (ActiveValues.get(currentDoc.getMuestraImpuesto()).getValue()) {
            container.setTotalGravado(container.getTotalGravado().add(totalGrav.add(totalImp))); // mas
                                                                                                 // imp
        } else {
            container.setTotalGravado(container.getTotalGravado().add(totalGrav));
            container.setTotalImpuesto(container.getTotalImpuesto().add(totalImp));
        }

        container.setTotalExento(container.getTotalExento().add(totalExent));
    }

    /**
     * Method adddetailInfo.
     * 
     * @param reportLines ArrayList<String>
     * @param addSeparator boolean
     * @param isZReport TODO
     */
    public void addDetailInfo(ArrayList<String> reportLines, boolean addSeparator, boolean isZReport) {
        createSeparator(reportLines, addSeparator);
        reportLines.add(PrintingUtils.alinear("Fecha: " + this.date + " % Hora: " + this.time, "%"));
        reportLines.add(PrintingUtils.alinear("Tienda: " + this.store + "/" + "Caja: " + this.pos, "/"));
        if (!isZReport) {
            reportLines.add("Cajero: " + this.cashierID);
        }
        createSeparator(reportLines, addSeparator);

    }

    private void createSeparator(ArrayList<String> reportLines, boolean addSeparator) {
        if (addSeparator) {
            reportLines.add(Utils.crearSeparador("*"));
        }
    }

    /**
     * Method createDetail.
     * 
     * @param initialDate Date
     * @param ficha long
     * @param isZReport boolean
     * @param detailBehaviour ReportPrintingBehaviour
     * @return ArrayList<String>
     */
    public ArrayList<String> createDetail(Date initialDate, long ficha, boolean isZReport,
                                          ReportPrintingBehaviour detailBehaviour) {

        isDetailZ = isZReport;

        TransaccionJpaController jpaTransaccion = new TransaccionJpaController(emf);
        MonedaJpaController jpaMoneda = new MonedaJpaController(emf);

        Moneda defaultCurrency = jpaMoneda.findMoneda(this.defaultCurrencyId);

        /*
         * Se establece la hora para buscar las transacciones hasta el último milisegundo
         * de hoy en caso de ser un reporte X
         */
        Date finalDate = JPAUtils.cleanDate(new Date(), false);
        Calendar cal = Calendar.getInstance();
        Calendar initialCal = Calendar.getInstance();
        initialCal.setTime(initialDate);

        // Date fechaBuscada = cal.getTime();
        Collection<Transaccion> transList;
        if (!isZReport) {
            transList = jpaTransaccion.findByDate(initialDate, initialDate, ficha, isZReport);
        } else {
            // Si la fecha de inicio es hoy
            if ((initialCal.get(Calendar.DAY_OF_YEAR) == cal.get(Calendar.DAY_OF_YEAR))
                    && (initialCal.get(Calendar.YEAR) == cal.get(Calendar.YEAR))) {
                transList = jpaTransaccion.findByDate(initialDate, initialDate, ficha, isZReport);
            } else {
                transList = jpaTransaccion.findByDate(initialDate, finalDate, ficha, isZReport);
            }
        }

        detailsMap = new LinkedHashMap<String, ReportsDetailsContainer>();

        // Un pequeño truco para ordenar las FDP por código
        crjpa.FormadepagoJpaController fdpcontroller = new crjpa.FormadepagoJpaController(this.emf);
        List<Formadepago> tmp2 = fdpcontroller.findFormadepagoEntities();

        Map<Integer, Formadepago> sort = new TreeMap<Integer, Formadepago>();

        for (Formadepago formadepago : tmp2) {
            sort.put(formadepago.getCodigo(), formadepago);
        }

        tmp2 = new ArrayList<Formadepago>();
        tmp2.addAll(sort.values());

        /*
         * Se guardan todas las FDP activas con sus monedas para luego llenar los montos
         */
        CRBigDecimal zero = CRBigDecimal.ZERO;
        for (Formadepago paymentMethod : tmp2) {
            for (Formadepagomoneda moneyPayment : paymentMethod.getFormadepagomonedaList()) {
                if (ActiveValues.get(paymentMethod.getEstaactivo()).getValue()) {
                    putReportDetail(moneyPayment.getIdFormadepago(), defaultCurrency, zero, zero);
                }
            }
        }

        for (Transaccion element : transList) {
            boolean refund = false;
            boolean cancellation = false;
            boolean isCreditsOperation = false;
            Transaccion transaction = null;

            if (element.getTipo().equalsIgnoreCase(SourceTransactionType.Cancellation.getValue().toString())) {
                transaction = element.getIdAnulaciontransaccion();
                cancellation = true;
            } else if (element.getTipo().equalsIgnoreCase(SourceTransactionType.Refund.getValue().toString())) {
                transaction = element;
                refund = true;
            } else if (element.getTipo().equalsIgnoreCase(SourceTransactionType.Credits.getValue().toString())) {
                transaction = element;
                isCreditsOperation = true;
            } else {
                transaction = element;
            }

            Collection<Transaccionformadepago> fdpList = transaction.getTransaccionformadepagoList();
            // De cada transaccion suma el valor por forma de pago.
            for (Transaccionformadepago payment : fdpList) {
                Formadepago paymentMethod = payment.getIdFormadepago();
                String detailKey = paymentMethod + "" + defaultCurrency.getSimbolo();
                CRBigDecimal positive = CRBigDecimal.ZERO;
                CRBigDecimal negative = CRBigDecimal.ZERO;
                CRBigDecimal amount = new CRBigDecimal(payment.getMontoMonedaLocal().doubleValue());
                boolean isCreditsPayment = paymentMethod.getId().equals(creditsPaymentMethod);

                if (!isCreditsOperation && !isCreditsPayment) {
                    if (!refund && !cancellation) {
                        positive = amount;
                    } else {
                        negative = amount;
                    }
                }

                negative = negative.negate();
                ReportsDetailsContainer data = new ReportsDetailsContainer();

                if (detailsMap.containsKey(detailKey)) {
                    updateReportDetail(paymentMethod, defaultCurrency, positive, negative);
                } else {
                    putReportDetail(paymentMethod, defaultCurrency, positive, negative);
                }
            }

            CRBigDecimal change = new CRBigDecimal(transaction.getVuelto().doubleValue());

            // Se ordenan las FDP de la transacción por prioridad de donación.
            Map<Integer, Transaccionformadepago> sortedFdp = new TreeMap<Integer, Transaccionformadepago>();

            for (Transaccionformadepago tfdp : fdpList) {
                sortedFdp.put(tfdp.getIdFormadepago().getPrioridadDonacion(), tfdp);
            }

            List<Transaccionformadepago> fdpPrioridadDonacion = new ArrayList<Transaccionformadepago>();
            fdpPrioridadDonacion.addAll(sortedFdp.values());

            for (Transaccionformadepago payment : fdpPrioridadDonacion) {
                CRBigDecimal negative = CRBigDecimal.ZERO;
                CRBigDecimal montoFdp = new CRBigDecimal(payment.getMontoMonedaLocal().doubleValue());
                boolean isChangeZero = change.compareTo(CRBigDecimal.ZERO) == 0;

                if (!refund && !cancellation) { // Venta
                    // El vuelto se resta del detalle por FDP
                    if (change.compareTo(montoFdp) <= 0 && !isChangeZero) {
                        negative = negative.add(change);
                        change = CRBigDecimal.ZERO;
                    } else if (change.compareTo(montoFdp) > 0 && !isChangeZero) {
                        negative = negative.add(montoFdp);
                        change = change.subtract(montoFdp);
                    }
                } else if (cancellation) {
                    if (change.compareTo(montoFdp) <= 0 && !isChangeZero) {
                        negative = negative.subtract(change);
                        change = CRBigDecimal.ZERO;
                    } else if (change.compareTo(montoFdp) > 0 && !isChangeZero) {
                        negative = negative.subtract(montoFdp);
                        change = change.subtract(montoFdp);
                    }
                }

                negative = negative.negate();

                updateReportDetail(payment.getIdFormadepago(), defaultCurrency, CRBigDecimal.ZERO, negative);

                if (isChangeZero) {
                    break;
                }
            }
        }

        updateReportDetailFromCreditsMovements(initialDate, finalDate, ficha, isZReport, defaultCurrency);

        return detailBehaviour.printDetail(detailsMap, totalDonation, creditsTotal, defaultCurrency.getSimbolo());
    }

    private void updateReportDetailFromCreditsMovements(Date initialDate, Date finalDate, long employeeId,
                                                        boolean isZReport, Moneda defaultCurrency) {
        AcreenciamovimientoJpaController creditsMovementJpa = new AcreenciamovimientoJpaController(emf);
        List<Acreenciamovimiento> creditsMovements = creditsMovementJpa.findByDate(initialDate, finalDate,
                                                                                   employeeId, isZReport);

        for (Acreenciamovimiento movement : creditsMovements) {
            Formadepago paymentMethod = movement.getIdFormadepago();
            String detailKey = paymentMethod + "" + defaultCurrency.getSimbolo();
            CRBigDecimal positive = CRBigDecimal.ZERO;
            CRBigDecimal negative = CRBigDecimal.ZERO;
            CRBigDecimal amount = new CRBigDecimal(movement.getMontoMonedaLocal().doubleValue());
            long operationId = movement.getIdOperacionacreencia().getId();
            boolean isCreditsPayment = paymentMethod.getId().equals(creditsPaymentMethod);

            if (operationId == 1 && !isCreditsPayment) { // Depósito
                positive = amount;
            } else if (operationId == 1 && isCreditsPayment) {
                // Depósito por anulación de consumo
                negative = amount;
            } else if (operationId == 2) { // Anulación
                negative = amount;
            } else if (operationId == 3) { // Reintegro
                negative = amount;
            } else if (operationId == 5) { // Consumo
                positive = amount;
            }

            negative = negative.negate();
            ReportsDetailsContainer data = new ReportsDetailsContainer();

            if (detailsMap.containsKey(detailKey)) {
                updateReportDetail(paymentMethod, defaultCurrency, positive, negative);
            } else {
                putReportDetail(paymentMethod, defaultCurrency, positive, negative);
            }
        }
    }

    private void putReportDetail(Formadepago paymentMethod, Moneda currency, CRBigDecimal positive,
                                 CRBigDecimal negative) {
        String symbol = currency.getSimbolo();
        String detailKey = paymentMethod + "" + symbol;
        String paymentName = paymentMethod.getNombre();
        ReportsDetailsContainer data = new ReportsDetailsContainer();
        data.setName(paymentName + " " + symbol);
        data.setMonedaSimbolo(symbol);
        data.setFormaDePago(paymentName);
        data.setPositive(positive);
        data.setNegative(negative);
        detailsMap.put(detailKey, data);
    }

    private void updateReportDetail(Formadepago paymentMethod, Moneda currency, CRBigDecimal positive,
                                    CRBigDecimal negative) {
        String symbol = currency.getSimbolo();
        String detailKey = paymentMethod + "" + symbol;
        String paymentName = paymentMethod.getNombre();
        ReportsDetailsContainer data = new ReportsDetailsContainer();
        ReportsDetailsContainer oldData = detailsMap.get(detailKey);

        CRBigDecimal newPositive = oldData.getPositive().add(positive);
        CRBigDecimal newNegative = oldData.getNegative().add(negative);
        data.setName(paymentName + " " + symbol);
        data.setMonedaSimbolo(symbol);
        data.setFormaDePago(paymentName);
        data.setPositive(newPositive);
        data.setNegative(newNegative);
        detailsMap.put(detailKey, data);
    }

    /**
     * Method setStoreName.
     * 
     * @param storeName String
     */
    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    /**
     * Method setRif.
     * 
     * @param rif String
     */
    public void setRif(String rif) {
        this.rif = rif;
    }

    /**
     * Method setNit.
     * 
     * @param nit String
     */
    public void setNit(String nit) {
        this.nit = nit;
    }

    /**
     * Method setAddress.
     * 
     * @param address String
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Method setPosStore.
     * 
     * @param posStore String
     */
    public void setPosStore(String posStore) {
        this.store = posStore;
    }

    /**
     * Method setCashierID.
     * 
     * @param cashierID String
     */
    public void setCashierID(String cashierID) {
        this.cashierID = cashierID;
    }

    /**
     * Method setCashierName.
     * 
     * @param cashierName String
     */
    public void setCashierName(String cashierName) {
        this.cashierName = cashierName;
    }

    /**
     * Method setDate.
     * 
     * @param date String
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Method setTime.
     * 
     * @param time String
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * @param pos the pos to set
     */
    public void setPos(String pos) {
        this.pos = pos;
    }

    /**
     * 
     * @return the pos
     */
    public String getPos() {
        return pos;
    }

    public long getDefaultCurrencyId() {
        return defaultCurrencyId;
    }

    public void setDefaultCurrencyId(long defaultCurrencyId) {
        this.defaultCurrencyId = defaultCurrencyId;
    }

    public long getCreditsPaymentMethod() {
        return creditsPaymentMethod;
    }

    public void setCreditsPaymentMethod(long creditsPaymentMethod) {
        this.creditsPaymentMethod = creditsPaymentMethod;
    }

    public boolean isFormControlActive() {
        return isFormControlActive;
    }

    public void setFormControlActive(boolean isFormControlActive) {
        this.isFormControlActive = isFormControlActive;
    }
}
