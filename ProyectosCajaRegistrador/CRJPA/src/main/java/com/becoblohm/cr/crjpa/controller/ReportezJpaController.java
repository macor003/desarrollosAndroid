/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.crjpa.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.becoblohm.cr.crjpa.controller.exceptions.JpaException;
import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.becoblohm.cr.models.Transaction;
import com.becoblohm.cr.models.Transaction.SourceTransactionType;
import com.becoblohm.cr.models.Transaction.TransactionState;
import com.becoblohm.cr.models.ZDigitalReport;
import com.becoblohm.cr.models.ZDigitalReportDetails;
import com.becoblohm.cr.types.CRBigDecimal;
import com.becoblohm.cr.utils.CRUtils;

import crjpa.Formadepago;
import crjpa.Formadepagomoneda;
import crjpa.Reportez;
import crjpa.Transaccion;
import crjpa.Transaccionformadepago;
import crjpa.exceptions.PreexistingEntityException;

/**
 */
public class ReportezJpaController extends AbstractJPAController {

    /**
     * Field jpacontroller.
     */
    crjpa.ReportezJpaController jpacontroller;

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Field sessionController.
     */
    private final SesionJpaController sessionController;

    /**
     * Field emf.
     */
    private final EntityManagerFactory emf;

    /**
     * Field entityName.
     */
    private static String entityName = "Reportez";

    /**
     * Constructor for ReporteZJpaController.
     * 
     * @param emf EntityManagerFactory
     */
    public ReportezJpaController(EntityManagerFactory emf) {
        super(AbstractJPAController.POSSOURCE, entityName);
        this.emf = emf;
        jpacontroller = new crjpa.ReportezJpaController(emf);
        sessionController = new SesionJpaController(emf);
    }

    /**
     * Method toJPA.
     * 
     * @param report ZDigitalReport
     * @return Reportez
     */
    public Reportez toJPA(ZDigitalReport report) {
        Reportez jpaReport = new Reportez();

        if (report.getType() == ZDigitalReport.ReportType.Daily) {
            jpaReport.setIdSesion(sessionController.toJPA(report.getCurrentSession()));
            jpaReport.setTienda(Integer.valueOf(report.getCurrentSession().getStoreId()));
        }
        // MISSING tienda
        jpaReport.setFecha(report.getReportDate());
        jpaReport.setNumeroReporte(report.getReportNumber());
        jpaReport.setSerialImpresora(report.getPrinterSerial());
        jpaReport.setFechaCreacion(report.getReportDate());
        jpaReport.setHoraCreacion(report.getReportDate());

        jpaReport.setEstasincronizado("N");

        jpaReport.setFacturasEmitidas(report.getIssuedInvoices());
        jpaReport.setUltimaFacturaEmitida(report.getLastInvoice());
        jpaReport.setFechaUltimaFacturaEmitida(report.getLastInvoiceDate());
        jpaReport.setHoraUltimaFacturaEmitida(report.getLastInvoiceDate());

        jpaReport.setNotaCreditoEmitida(report.getIssuedCreditsNotes());
        jpaReport.setUltimaNotaCreditoEmitida(report.getLastCreditNote());
        jpaReport.setFechaUltimaNotaCreditoEmitida(report.getLastCreditNoteDate());
        jpaReport.setHoraUltimaNotaCreditoEmitida(report.getLastCreditNoteDate());
        jpaReport.setTipo(report.getType().getValue());
        jpaReport.setElectronic_journal(report.getElectronic_journal());

        // -------------------------

        CRBigDecimal exemptInvoicesAmmount = report.getExemptInvoicesAmmount();
        jpaReport.setMontoExcentoFactura(exemptInvoicesAmmount == null ? CRBigDecimal.ZERO.getValue()
                : exemptInvoicesAmmount.getValue());

        ZDigitalReportDetails invoicesDetail_A = report.getInvoicesDetail_A();
        if (invoicesDetail_A == null) {
            jpaReport.setBaseImponibleAFacturas(CRBigDecimal.ZERO.getValue());
            jpaReport.setImpuestoAFacturas(CRBigDecimal.ZERO.getValue());
            jpaReport.setPorcentajeAFacturas(CRBigDecimal.ZERO.getValue());
        } else {
            CRBigDecimal taxableAmmount = invoicesDetail_A.getTaxableAmmount();
            jpaReport.setBaseImponibleAFacturas(taxableAmmount == null ? CRBigDecimal.ZERO.getValue()
                    : taxableAmmount.getValue());

            CRBigDecimal tax = invoicesDetail_A.getTax();
            jpaReport.setImpuestoAFacturas(tax == null ? CRBigDecimal.ZERO.getValue() : tax.getValue());

            CRBigDecimal percent = invoicesDetail_A.getPercent();
            jpaReport.setPorcentajeAFacturas(percent == null ? CRBigDecimal.ZERO.getValue() : percent.getValue());
        }

        ZDigitalReportDetails invoicesDetail_G = report.getInvoicesDetail_G();
        if (invoicesDetail_G == null) {
            jpaReport.setBaseImponibleGFacturas(CRBigDecimal.ZERO.getValue());
            jpaReport.setImpuestoGFacturas(CRBigDecimal.ZERO.getValue());
            jpaReport.setPorcentajeGFacturas(CRBigDecimal.ZERO.getValue());
        } else {
            CRBigDecimal taxableAmmount = invoicesDetail_G.getTaxableAmmount();
            jpaReport.setBaseImponibleGFacturas(taxableAmmount == null ? CRBigDecimal.ZERO.getValue()
                    : taxableAmmount.getValue());

            CRBigDecimal tax = invoicesDetail_G.getTax();
            jpaReport.setImpuestoGFacturas(tax == null ? CRBigDecimal.ZERO.getValue() : tax.getValue());

            CRBigDecimal percent = invoicesDetail_G.getPercent();
            jpaReport.setPorcentajeGFacturas(percent == null ? CRBigDecimal.ZERO.getValue() : percent.getValue());
        }

        ZDigitalReportDetails invoicesDetail_R = report.getInvoicesDetail_R();
        if (invoicesDetail_R == null) {
            jpaReport.setBaseImponibleRFacturas(CRBigDecimal.ZERO.getValue());
            jpaReport.setImpuestoRFacturas(CRBigDecimal.ZERO.getValue());
            jpaReport.setPorcentajeRFacturas(CRBigDecimal.ZERO.getValue());
        } else {
            CRBigDecimal taxableAmmount = invoicesDetail_R.getTaxableAmmount();
            jpaReport.setBaseImponibleRFacturas(taxableAmmount == null ? CRBigDecimal.ZERO.getValue()
                    : taxableAmmount.getValue());

            CRBigDecimal tax = invoicesDetail_R.getTax();
            jpaReport.setImpuestoRFacturas(tax == null ? CRBigDecimal.ZERO.getValue() : tax.getValue());

            CRBigDecimal percent = invoicesDetail_R.getPercent();
            jpaReport.setPorcentajeRFacturas(percent == null ? CRBigDecimal.ZERO.getValue() : percent.getValue());
        }

        /* Credits Notes */
        CRBigDecimal exemptCreditsNotesAmmount = report.getExemptCreditsNotesAmmount();
        jpaReport.setMontoExcentoNc(exemptCreditsNotesAmmount == null ? CRBigDecimal.ZERO.getValue()
                : exemptCreditsNotesAmmount.getValue());

        ZDigitalReportDetails creditsNotesDetail_A = report.getCreditsNotesDetail_A();
        if (creditsNotesDetail_A == null) {
            jpaReport.setBaseImponibleANc(CRBigDecimal.ZERO.getValue());
            jpaReport.setImpuestoANc(CRBigDecimal.ZERO.getValue());
            jpaReport.setPorcentajeANc(CRBigDecimal.ZERO.getValue());
        } else {
            CRBigDecimal taxableAmmount = creditsNotesDetail_A.getTaxableAmmount();
            jpaReport.setBaseImponibleANc(taxableAmmount == null ? CRBigDecimal.ZERO.getValue()
                    : taxableAmmount.getValue());

            CRBigDecimal tax = creditsNotesDetail_A.getTax();
            jpaReport.setImpuestoANc(tax == null ? CRBigDecimal.ZERO.getValue() : tax.getValue());

            CRBigDecimal percent = creditsNotesDetail_A.getPercent();
            jpaReport.setPorcentajeANc(percent == null ? CRBigDecimal.ZERO.getValue() : percent.getValue());
        }

        ZDigitalReportDetails creditsNotesDetail_G = report.getCreditsNotesDetail_G();
        if (creditsNotesDetail_G == null) {
            jpaReport.setBaseImponibleGNc(CRBigDecimal.ZERO.getValue());
            jpaReport.setImpuestoGNc(CRBigDecimal.ZERO.getValue());
            jpaReport.setPorcentajeGNc(CRBigDecimal.ZERO.getValue());
        } else {
            CRBigDecimal taxableAmmount = creditsNotesDetail_G.getTaxableAmmount();
            jpaReport.setBaseImponibleGNc(taxableAmmount == null ? CRBigDecimal.ZERO.getValue()
                    : taxableAmmount.getValue());

            CRBigDecimal tax = creditsNotesDetail_G.getTax();
            jpaReport.setImpuestoGNc(tax == null ? CRBigDecimal.ZERO.getValue() : tax.getValue());

            CRBigDecimal percent = creditsNotesDetail_G.getPercent();
            jpaReport.setPorcentajeGNc(percent == null ? CRBigDecimal.ZERO.getValue() : percent.getValue());
        }

        ZDigitalReportDetails creditsNotesDetail_R = report.getCreditsNotesDetail_R();
        if (creditsNotesDetail_R == null) {
            jpaReport.setBaseImponibleRNc(CRBigDecimal.ZERO.getValue());
            jpaReport.setImpuestoRNc(CRBigDecimal.ZERO.getValue());
            jpaReport.setPorcentajeRNc(CRBigDecimal.ZERO.getValue());
        } else {
            CRBigDecimal taxableAmmount = creditsNotesDetail_R.getTaxableAmmount();
            jpaReport.setBaseImponibleRNc(taxableAmmount == null ? CRBigDecimal.ZERO.getValue()
                    : taxableAmmount.getValue());

            CRBigDecimal tax = creditsNotesDetail_R.getTax();
            jpaReport.setImpuestoRNc(tax == null ? CRBigDecimal.ZERO.getValue() : tax.getValue());

            CRBigDecimal percent = creditsNotesDetail_R.getPercent();
            jpaReport.setPorcentajeRNc(percent == null ? CRBigDecimal.ZERO.getValue() : percent.getValue());
        }

        return jpaReport;
    }

    /**
     * Method create.
     * 
     * @param report ZDigitalReport
     * @throws JpaException
     */
    public void create(ZDigitalReport report) throws JpaException {
        try {
            jpacontroller.create(toJPA(report));
        } catch (PreexistingEntityException e) {
            e.printStackTrace();// TODO manejar excepciones
        } catch (Exception e) {
            e.printStackTrace();// TODO manejar excepciones
            throw new JpaException();
        }
    }

    /**
     * Method loadClosureDayReport.
     * 
     * @param initialDate Date
     * @param finalDate Date
     * @return ArrayList<String>
     */
    public ArrayList<String> loadClosureDayReport(Date initialDate, Date finalDate) {

        ArrayList<String> reportLines = new ArrayList<String>();
        List<Transaccion> tmp = new ArrayList<Transaccion>();
        Query query = this.emf.createEntityManager()
                .createQuery("SELECT t FROM Transaccion t WHERE t.fecha >= :fechaInicial AND t.fecha <= :fechaFinal AND t.estado = '"
                        + TransactionState.COMPLETE.getValue() + "'");
        query.setParameter("fechaInicial", initialDate);
        query.setParameter("fechaFinal", finalDate);
        tmp = query.getResultList();

        CRBigDecimal exemptSaleTotal = CRBigDecimal.ZERO;
        CRBigDecimal taxedSaleTotal = CRBigDecimal.ZERO;
        CRBigDecimal saleTaxTotal = CRBigDecimal.ZERO;
        CRBigDecimal cancellationsTotal = CRBigDecimal.ZERO;
        CRBigDecimal refundsTotal = CRBigDecimal.ZERO;
        CRBigDecimal anuldevTaxTotal = CRBigDecimal.ZERO;
        TreeMap<String, DetailsContainer> paymentMap = new TreeMap<String, DetailsContainer>();
        crjpa.FormadepagoJpaController fdpcontroller = new crjpa.FormadepagoJpaController(this.emf);
        List<Formadepago> tmp2 = fdpcontroller.findFormadepagoEntities();

        for (Formadepago paymentMethod : tmp2) {
            for (Formadepagomoneda moneyPayment : paymentMethod.getFormadepagomonedaList()) {
                paymentMap.put(paymentMethod.getDescripcion() + " " + moneyPayment.getIdMoneda().getSimbolo(),
                               new DetailsContainer());
            }
        }

        for (Transaccion transTmp : tmp) {
            CRBigDecimal taxTmp = new CRBigDecimal(transTmp.getTotalImpuesto().doubleValue());
            if (transTmp.getTipo() == SourceTransactionType.Cancellation.getValue()) {
                cancellationsTotal = cancellationsTotal
                        .add(new CRBigDecimal(transTmp.getTotalBase().doubleValue()));
                anuldevTaxTotal = anuldevTaxTotal.add(taxTmp);
            } else if (transTmp.getTipo() == SourceTransactionType.Refund.getValue()) {
                refundsTotal = refundsTotal.add(new CRBigDecimal(transTmp.getTotalBase().doubleValue()));
                anuldevTaxTotal = anuldevTaxTotal.add(taxTmp);
            } else {
                if (taxTmp.compareTo(CRBigDecimal.ZERO) > 0) {
                    taxedSaleTotal = taxedSaleTotal.add(new CRBigDecimal(transTmp.getTotalBase().doubleValue()));
                } else {
                    exemptSaleTotal = exemptSaleTotal.add(new CRBigDecimal(transTmp.getTotalBase().doubleValue()));
                }
                saleTaxTotal = saleTaxTotal.add(taxTmp);
            }

            for (Transaccionformadepago paymentMethodTmp : transTmp.getTransaccionformadepagoList()) {
                String pmName = paymentMethodTmp.getIdFormadepago().getDescripcion() + " "
                        + paymentMethodTmp.getIdMoneda().getSimbolo();
                DetailsContainer container;

                if (paymentMap.containsKey(pmName)) {
                    container = paymentMap.get(pmName);
                } else {
                    container = new DetailsContainer();
                }

                if (transTmp.getTipo().equals(SourceTransactionType.Cancellation.getValue())
                        || transTmp.getTipo().equals(SourceTransactionType.Refund.getValue())) {
                    container.setNegative(new CRBigDecimal(paymentMethodTmp.getMontoMonedaLocal().doubleValue()));
                } else if (transTmp.getTipo().equals(SourceTransactionType.Sale.getValue())) {
                    container.setPositive(new CRBigDecimal(paymentMethodTmp.getMontoMonedaLocal().doubleValue()));
                }
                paymentMap.put(pmName, container);
            }
        }
        reportLines.add("VTAS EXENTAS BRUTAS " + exemptSaleTotal);
        reportLines.add("VTAS GRAVABLES BRUTAS " + taxedSaleTotal);
        reportLines.add("TOT. VENTAS BRUTAS " + exemptSaleTotal.add(taxedSaleTotal));
        reportLines.add("TOT. IMP. VTAS BRUTAS " + saleTaxTotal);
        reportLines.add("------------------------------------------");
        reportLines.add("ANULACIONES " + cancellationsTotal);
        reportLines.add("DEVOLUCIONES " + refundsTotal);
        reportLines.add("TOT. ANUL+DEV " + cancellationsTotal.add(refundsTotal));
        reportLines.add("TOT. IMP. ANUL+DEV " + anuldevTaxTotal);
        reportLines.add("------------------------------------------");
        CRBigDecimal saleTotalTmp = exemptSaleTotal.add(taxedSaleTotal).add(saleTaxTotal).add(cancellationsTotal)
                .add(refundsTotal).add(anuldevTaxTotal);
        reportLines.add("VENTA NETA " + saleTotalTmp);
        reportLines.add("------------------------------------------");
        reportLines.add("");
        reportLines.add("DETALLE DE FORMA DE PAGO");
        reportLines.add("");
        for (Entry<String, DetailsContainer> entry : paymentMap.entrySet()) {
            reportLines.add(entry.getKey());
            reportLines.add("POSITIVO " + entry.getValue().getPositive());
            reportLines.add("NEGATIVO " + entry.getValue().getNegative());
            reportLines.add("NETO     " + entry.getValue().getPositive().add(entry.getValue().getNegative()));
            reportLines.add("");
        }
        return reportLines;
    }

    /**
     */
    private class DetailsContainer {

        /**
         * Field positive.
         */
        private CRBigDecimal positive = CRBigDecimal.ZERO;

        /**
         * Field negative.
         */
        private CRBigDecimal negative = CRBigDecimal.ZERO;

        /**
         * Method getPositive.
         * 
         * @return CRBigDecimal
         */
        public CRBigDecimal getPositive() {
            return positive;
        }

        /**
         * Method setPositive.
         * 
         * @param positive CRBigDecimal
         */
        public void setPositive(CRBigDecimal positive) {
            this.positive = this.positive.add(positive);
        }

        /**
         * Method getNegative.
         * 
         * @return CRBigDecimal
         */
        public CRBigDecimal getNegative() {
            return negative.negate();
        }

        /**
         * Method setNegative.
         * 
         * @param negative CRBigDecimal
         */
        public void setNegative(CRBigDecimal negative) {
            this.negative = this.negative.add(negative);
        }
    }

    /**
     * Method findNextMonthlyZReportDate.
     * 
     * @return Date
     */
    public Date findNextMonthlyZReportDate() {
        // TODO Buscar la fecha del proximo reportez
        // La fecha es null si no hay un mes completo con transacciones
        // En caso de que la fecha del ultimo reportez no sea la del mes
        // anterior al actual, se emitira el reporte del
        // ultimo mes con transacciones finalizadas.
        TransaccionJpaController transJpa = new TransaccionJpaController(this.emf);
        Transaction last = transJpa.getLastTransaction();
        if (last != null) {
            Date lastTransDate = last.getDate();

            if (lastTransDate != null) {
                Reportez lastMonthlyReport;
                try {
                    Query query = emf.createEntityManager()
                            .createQuery("select a from Reportez a where a.id = (select max(b.id) from Reportez b where b.tipo = 'MENSUAL')");
                    lastMonthlyReport = (Reportez) query.getSingleResult();
                } catch (javax.persistence.NoResultException e) {
                    lastMonthlyReport = null;
                }
                if (lastMonthlyReport != null) {
                    Date lastReportDate = lastMonthlyReport.getFecha();
                    if (CRUtils.compareMonths(lastTransDate, lastReportDate) == 0) {
                        if (CRUtils.compareMonths(lastTransDate, new Date()) == 0) {
                            return null;
                        } else {
                            return lastTransDate;
                        }
                    } else if (CRUtils.compareMonths(lastTransDate, lastReportDate) < 0) {
                        return null;
                    } else {
                        if (CRUtils.compareMonths(lastTransDate, new Date()) < 0) {
                            return lastTransDate;
                        }
                    }
                } else {
                    if (CRUtils.compareMonths(lastTransDate, new Date()) == 0) {
                        return null;
                    } else {
                        return lastTransDate;
                    }
                }
            } else {
                return null;
            }
        } else {
            return null;
        }
        return null;
    }

    public int findLastClosureId(String serialNumber) {
        int number = -1;
        Reportez lastReport;
        try {
            Query query = emf.createEntityManager()
                    .createQuery("select a from Reportez a where a.numeroReporte = (select max(b.numeroReporte) from Reportez b where b.serialImpresora = '"
                            + serialNumber + "')");
            lastReport = (Reportez) query.getSingleResult();
            number = lastReport.getNumeroReporte();
        } catch (javax.persistence.NoResultException e) {
            lastReport = null;
        }

        return number;
    }

    /**
     * Valida si si existe un registro en la tabla reportez segun el numero de z y el
     * serial de la impresora
     * 
     * @param numeroReporte int
     * @param serialImpresora String
     * @return true si el registro existe. falso en caso contrario
     */
    public boolean exists(int numeroReporte, String serialImpresora) {
        boolean result = true;
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em
                    .createQuery("SELECT r FROM Reportez r WHERE r.numeroReporte = :numeroReporte AND r.serialImpresora = :serialImpresora");
            query.setParameter("numeroReporte", numeroReporte);
            query.setParameter("serialImpresora", serialImpresora);

            query.getSingleResult();
        } catch (NoResultException e) {
            result = false;
        } finally {
            em.close();
        }

        return result;
    }
}
