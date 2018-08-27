/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/**
 * Referencias unicas a objetos globales de la aplicacion NO DEBE TENER NINGUNA RUTINA O
 * LOGICA
 */
package com.becoblohm.cr.conf;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.persistence.EntityManagerFactory;
import javax.swing.JLabel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.becoblohm.cr.crjpa.controller.OpcionJpaController;
import com.becoblohm.cr.models.DefaultValues;
import com.becoblohm.cr.models.PaymentMethod;
import com.becoblohm.cr.models.Process;
import com.becoblohm.cr.models.Session;
import com.becoblohm.cr.models.Store;
import com.becoblohm.cr.models.TimeCheck;
import com.becoblohm.cr.models.User;
import com.becoblohm.cr.types.ActiveValues;
import com.becoblohm.cr.types.CRBigDecimal;
import com.becoblohm.cr.utils.CRUtils;
import com.epa.mvc.core.AbstractController;

/**
 * Almacena variables de configuración necesarias para la ejecución y valores constantes
 * corespondientes a las operaciones ejecutadas en cada CR. Algunos valores son leidos de
 * BD durante el arranque de la CR.
 * 
 */
public class Global {

    /**
     * Field VERSION.
     */
    private static String VERSION = "";

    /**
     * Field version_props. (value is ""version.properties"")
     */
    private final static String version_props = "version.properties";

    /**
     * Field devController.
     */
    private static AbstractController devController;

    /**
     * Field statusLabel.
     */
    private static JLabel statusLabel = new JLabel("");

    /**
     * Label con los datos de la version
     */
    private static JLabel versionLabel;

    /**
     * Field isConfLoaded.
     */
    protected static boolean isConfLoaded;

    /**
     * Field devController.
     */
    private static Session currentSession;
    /*
     * 1 Sesion 2 Reporte Z 3 Rollo Auditoria 4 Pago Credito 5 Entrega 6 Entrega FDP 7
     * Transaccion 8 Transaccion Fase 9 Transaccion Conexion 10 Devolucion 11 Transaccion
     * Documento 12 Transaccion Articulo 13 Transaccion Art-Servicio 14 Transaccion FDP 15
     * FDP Vpos 16 Transaccion Cliente 17 Comp No Utilizado 97 Numero Acreencia 98 Numero
     * CreditoEpa 99 Numero Trans
     */

    /**
     * Field conf.
     */
    private static Conf conf;

    private static Map<String, String> servicesPorts;

    private static URL clientPolicy;

    /**
     * Field images.
     */
    private static Images images;

    /**
     * Field emf.
     */
    private static EntityManagerFactory emf;

    /**
     * Field mainFrame.
     */
    private static Frame mainFrame;

    /**
     * Field currentProcess.
     */
    private static Process currentProcess = new Process();

    /**
     * Field lastTransactionNumber.
     */
    private static long lastTransactionNumber;

    /**
     * Field timeCheck.
     */
    private static TimeCheck timeCheck;

    private static boolean isFiscalPrinter;

    private static Collection<PaymentMethod> paymentMethodByPosType;

    /**
     * Field IDSESIONCORRELATIVO. (value is 1)
     */
    public static final long IDSESIONCORRELATIVO = 1;

    /**
     * Field IDREPORTEZCORRELATIVO. (value is 2)
     */
    public static final long IDREPORTEZCORRELATIVO = 2;

    /**
     * Field IDROLLOAUDITORIACORRELATIVO. (value is 3)
     */
    public static final long IDROLLOAUDITORIACORRELATIVO = 3;

    /**
     * Field IDPAGOCREDITOCORRELATIVO. (value is 4)
     */
    public static final long IDPAGOCREDITOCORRELATIVO = 4;

    /**
     * Field IDENTREGACORRELATIVO. (value is 5)
     */
    public static final long IDENTREGACORRELATIVO = 5;

    /**
     * Field IDENTREGAFDPCORRELATIVO. (value is 6)
     */
    public static final long IDENTREGAFDPCORRELATIVO = 6;

    /**
     * Field IDTRANSACCIONCORRELATIVO. (value is 7)
     */
    public static final long IDTRANSACCIONCORRELATIVO = 7;

    /**
     * Field IDTRANSACCIONFASECORRELATIVO. (value is 8)
     */
    public static final long IDTRANSACCIONFASECORRELATIVO = 8;

    /**
     * Field IDTRANSACCIONCONEXIONCORRELATIVO. (value is 9)
     */
    public static final long IDTRANSACCIONCONEXIONCORRELATIVO = 9;

    /**
     * Field IDDEVOLUCIONCORRELATIVO. (value is 10)
     */
    public static final long IDDEVOLUCIONCORRELATIVO = 10;

    /**
     * Field IDTRANSACCIONDOCUMENTOCORRELATIVO. (value is 11)
     */
    public static final long IDTRANSACCIONDOCUMENTOCORRELATIVO = 11;

    /**
     * Field IDTRANSACCIONARTICULOCORRELATIVO. (value is 12)
     */
    public static final long IDTRANSACCIONARTICULOCORRELATIVO = 12;

    /**
     * Field IDTRANSACCIONARTSERVICIOCORRELATIVO. (value is 13)
     */
    public static final long IDTRANSACCIONARTSERVICIOCORRELATIVO = 13;

    /**
     * Field IDTRANSACCIONFDPCORRELATIVO. (value is 14)
     */
    public static final long IDTRANSACCIONFDPCORRELATIVO = 14;

    /**
     * Field IDFDPVPOSCORRELATIVO. (value is 15)
     */
    public static final long IDFDPVPOSCORRELATIVO = 15;

    /**
     * Field IDTRANSACCIONCLIENTECORRELATIVO. (value is 16)
     */
    public static final long IDTRANSACCIONCLIENTECORRELATIVO = 16;

    /**
     * Field IDCOMPNOUTILIZADOCORRELATIVO. (value is 17)
     */
    public static final long IDCOMPNOUTILIZADOCORRELATIVO = 17;

    /**
     * Field NUMEROACREENCIACORRELATIVO. (value is 97)
     */
    public static final long NUMEROACREENCIACORRELATIVO = 97;

    /**
     * Field NUMEROCREDITOEPACORRELATIVO. (value is 98)
     */
    public static final long NUMEROCREDITOEPACORRELATIVO = 98;

    /**
     * Field NUMEROTRANSCORRELATIVO. (value is 99)
     */
    public static final long NUMEROTRANSCORRELATIVO = 99;

    /**
     * Field NUMEROENTREGACORRELATIVO. (value is 5)
     */
    public static final long NUMEROENTREGACORRELATIVO = 5;

    /**
     * Field logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(Global.class);

    public static final long IDAUDITORIA = 18;

    private static Global global;

    private final static String pos_props = "pos.properties";

    private Global() {
        super();
    }

    public static Global getInstance() {
        if (global == null) {
            global = new Global();
            conf = Conf.getInstance();
            conf.loadProps();
            currentSession = new Session();
            currentSession.setCashier(new User());

            clientPolicy = CRUtils.findResource("client.policy", null, null);
            URL rmiUrl = CRUtils.findResource("clientrmi.properties", null, null);
            servicesPorts = new HashMap<String, String>();

            Properties propertyConf = new Properties();
            try {
                propertyConf.load(rmiUrl.openStream());
                servicesPorts.put("sync", propertyConf.getProperty("sync", "1099"));
                servicesPorts.put("syncP", propertyConf.getProperty("syncP", "1099"));
                servicesPorts.put("Servicios", propertyConf.getProperty("Servicios", "1099"));
                servicesPorts.put("ServiciosP", propertyConf.getProperty("ServiciosP", "1099"));
                servicesPorts.put("Acreencias", propertyConf.getProperty("Acreencias", "1099"));
                servicesPorts.put("AcreenciasP", propertyConf.getProperty("AcreenciasP", "1099"));

            } catch (IOException e) {
                propertyConf = null;
            }
            setEmf(conf.getEMFInstance());
        }
        return global;
    }

    public static String getSerialFromFile() {
        Properties props = new Properties();
        java.net.URL url = Thread.currentThread().getContextClassLoader().getResource(pos_props);
        if (url != null) {
            try {
                props.load(url.openStream());
            } catch (IOException e) {
                logger.info(e.getMessage());
            }
        }
        return props.getProperty("POSSERIAL", "000000");
    }

    public static void setMainFrame(Frame mainFrame) {
        Global.mainFrame = mainFrame;
    }

    /**
     * Method getDevController.
     * 
     * 
     * @return AbstractController
     */
    public static AbstractController getDevController() {
        return devController;
    }

    /**
     * Method setDevController.
     * 
     * @param devices AbstractController
     */
    public static void setDevController(AbstractController devices) {
        Global.devController = devices;
    }

    /**
     * Method isTesting.
     * 
     * 
     * @return boolean
     */
    public static boolean isBetaPos() {
        return ActiveValues.get(conf.getProps().getProperty(Conf.CAJAPILOTO)).getValue();
    }

    /**
     * Method isNoIOMode.
     * 
     * @return boolean
     */
    public static boolean isNoIOMode() {
        return true;
    }

    public static boolean totalDeliveryIncludeCash() {
        String totalDIC = conf.getProps().getProperty(Conf.TOTAL_DELIVERY_INCLUDE_CASH);
        return totalDIC.equalsIgnoreCase("s");
    }

    /**
     * Method getAdditionalMessageDonation.
     * 
     * @return String
     */
    public static String getAdditionalMessageDonation() {
        String str = conf.getProps().getProperty(Conf.MESSAGE_ADDITIONAL_DONATION);
        return str;
    }

    /**
     * Method getProperty.
     * 
     * @param key String
     * @return String
     */
    public static String getProperty(String key) {
        return conf.getProps().getProperty(key);

    }

    /**
     * Method isSelfCheckOutMode.
     * 
     * @return boolean
     */
    public static boolean isSelfCheckOutMode() {
        boolean result = false;
        switch (Integer.valueOf((conf.getProps().getProperty(Conf.TIPOCAJA)))) {
            case Conf.APP_TYPE_SELFCHECKOUT:
            case Conf.APP_TYPE_SELFCHECKOUT_TESTING:
                result = true;
                break;
            default:
                break;
        }
        return result;
    }

    /**
     * Method isXReportDetalled.
     * 
     * @return boolean
     */
    public static boolean isXReportDetalled() {
        return ActiveValues.get(conf.getProps().getProperty(Conf.XDETALLED)).getValue();
    }

    /**
     * Method getStoreName.
     * 
     * @return String
     */
    public static String getStoreName() {
        return (conf.getProps().getProperty(Conf.RAZON));
    }

    /**
     * Method getBusinessUnity.
     * 
     * @return String
     */
    public static String getBusinessUnity() {
        return (conf.getProps().getProperty(Conf.BUSINESSUNITY));
    }

    /**
     * Method getOperationUnity.
     * 
     * @return String
     */
    public static String getOperationUnity() {
        return (conf.getProps().getProperty(Conf.OPERATIONUNITY));
    }

    /**
     * Method getRIF.
     * 
     * @return String
     */
    public static String getRIF() {
        return (conf.getProps().getProperty(Conf.RIF));
    }

    /**
     * Method getNIT.
     * 
     * @return String
     */
    public static String getNIT() {
        return (conf.getProps().getProperty(Conf.NIT));
    }

    /**
     * Method getPorcentajeEnRebajas.
     * 
     * @return String
     */
    public static String getPorcentajeEnRebajas() {
        return (conf.getProps().getProperty(Conf.PORCENTAJESREBAJAS));
    }

    /**
     * Method getProfileIdSelfCheckoutCashier.
     * 
     * @return String
     */
    public static String getProfileIdSelfCheckoutCashier() {
        return (conf.getProps().getProperty(Conf.PERFILCAJEROAUTOPAGO, "4"));

    }

    /**
     * Method getStoreAddress.
     * 
     * @return String
     */
    public static String getStoreAddress() {
        return (conf.getProps().getProperty(Conf.STOREADDRESS));
    }

    /**
     * Method getDaysToDownloadClients.
     * 
     * @return int
     */
    public static int getDaysToDownloadClients() {
        return Integer.parseInt((conf.getProps().getProperty(Conf.DAYSTODOWNLOADCLIENTS, "5")));
    }

    /**
     * Method getClientTypeIdToValideWithWebService.
     * 
     * @return Collection<String>
     */
    public static Collection<String> getClientTypeIdToValideWithWebService() {
        return CRUtils.buildPropertiesVector(conf.getProps().getProperty(Conf.IDTIPOCLIENTEVALIDAWEBSERVICE, "1"));

    }

    /**
     * Method getLocale.
     * 
     * 
     * @return Locale
     */
    public static Locale getLocale() {
        return Conf.APP_LOCALE_LANG;
    }

    /**
     * Method getErrorsResourceBundle.
     * 
     * 
     * @return ResourceBundle
     */
    public static ResourceBundle getErrorsResourceBundle() {
        return conf.errorsBundle;
    }

    /**
     * Method getMessagesResourceBundle.
     * 
     * @return ResourceBundle
     */
    public static ResourceBundle getMessagesResourceBundle() {
        return conf.messagesBundle;
    }

    /**
     * Method getLabelsResourceBundle.
     * 
     * @return ResourceBundle
     */
    public static ResourceBundle getLabelsResourceBundle() {
        return conf.labelsBundle;
    }

    /**
     * Method getLabelsResourceBundle.
     * 
     * @return ResourceBundle
     */
    public static ResourceBundle getImagesResourceBundle() {
        return conf.imagesBundle;
    }

    /**
     * Method getIconsResourceBundle.
     * 
     * @return ResourceBundle
     */
    public static ResourceBundle getIconsResourceBundle() {
        return conf.iconsBundle;
    }

    /**
     * Method getPrinterTemplateBundle.
     * 
     * @return ResourceBundle
     */
    public static ResourceBundle getPrinterTemplateBundle() {
        return conf.printerTemplateBundle;
    }

    /**
     * Method getActivityFont.
     * 
     * 
     * @return Font
     */
    public static Font getBigFont() {
        return Conf.APP_BIG_FONT;
    }

    /**
     * Method getBigHeadingFont.
     * 
     * @return Font
     */
    public static Font getBigHeadingFont() {
        return Conf.APP_BIG_HEADING_FONT;
    }

    /**
     * Method getSmallFont.
     * 
     * @return Font
     */
    public static Font getSmallFont() {
        return Conf.APP_SMALL_FONT;
    }

    /**
     * Method getRegularFont.
     * 
     * @return Font
     */
    public static Font getRegularFont() {
        return Conf.APP_REGULAR_FONT;
    }

    /**
     * Method getSmallHeadingFont.
     * 
     * @return Font
     */
    public static Font getSmallHeadingFont() {
        return Conf.APP_SMALL_HEADING_FONT;
    }

    /**
     * Method getRegularHeadingFont.
     * 
     * @return Font
     */
    public static Font getRegularHeadingFont() {
        return Conf.APP_REGULAR_HEADING_FONT;
    }

    /**
     * Method getBiggerHeadingFont.
     * 
     * @return Font
     */
    public static Font getBiggerHeadingFont() {
        return Conf.APP_BIGGER_HEADING_FONT;
    }

    /**
     * @param images the images to set
     */
    public static void setImages(Images images) {
        Global.images = images;
    }

    /**
     * 
     * @return the images
     */
    public static Images getImages() {
        return images;
    }

    /**
     * Method getNumberFormat.
     * 
     * @return NumberFormat
     */
    public static NumberFormat getNumberFormat() {
        return NumberFormat.getNumberInstance(getLocale());
    }

    /**
     * Method getIntegerFormat.
     * 
     * @return NumberFormat
     */
    public static NumberFormat getIntegerFormat() {
        return NumberFormat.getIntegerInstance();
    }

    /**
     * Method getDecimalFormat.
     * 
     * @return DecimalFormat
     */
    public static DecimalFormat getDecimalFormat() {
        return new DecimalFormat("##,###,##0.00", new DecimalFormatSymbols(getLocale()));

    }

    /**
     * Method getDateFormat.
     * 
     * @return SimpleDateFormat
     */
    public static SimpleDateFormat getDateFormat() {
        return Conf.DATE_FORMAT;
    }

    /**
     * Method getHourFormat.
     * 
     * @return SimpleDateFormat
     */
    public static SimpleDateFormat getHourFormat() {
        return Conf.HOURS_FORMAT;
    }

    /**
     * Method getStore.
     * 
     * @return String
     */
    public static String getStore() {
        return (conf.getProps().getProperty(Conf.TIENDA));
    }

    /**
     * Method getPosId.
     * 
     * @return String
     */
    public static String getPosId() {
        return (conf.getProps().getProperty(Conf.CAJA));
    }

    /**
     * Method getCardNumberFormat.
     * 
     * @return String
     */
    public static String getCardNumberFormat() {
        return (conf.getProps().getProperty(Conf.CARDNUMBERFORMAT));
    }

    /**
     * Method getCustomerIdFormat.
     * 
     * @return String
     */
    public static String getCustomerIdFormat() {
        return (conf.getProps().getProperty(Conf.CUSTOMERIDFORMAT));
    }

    /**
     * Method getConformNumberFormat.
     * 
     * @return String
     */
    public static String getConformNumberFormat() {
        return (conf.getProps().getProperty(Conf.CONFORMNUMBER));
    }

    /**
     * Method getConf.
     * 
     * @return Conf
     */
    public static Conf getConf() {
        return conf;
    }

    /**
     * Method setEmf.
     * 
     * @param emf EntityManagerFactory
     */
    public static void setEmf(EntityManagerFactory emf) {
        Global.emf = emf;
    }

    /**
     * Method getPrincipalCurrency.
     * 
     * @return long
     */
    public static long getPrincipalCurrency() {
        return new Long(conf.getProps().getProperty(Conf.MONEDAPRINCIPAL));
    }

    /**
     * Method getEntityManagerFactory.
     * 
     * @return EntityManagerFactory
     */
    public static EntityManagerFactory getEntityManagerFactory() {
        return conf.getEMFInstance();
    }

    /**
     * Method getEntityManagerFactory400.
     * 
     * @return EntityManagerFactory
     */
    public static EntityManagerFactory getEntityManagerFactory400() {
        return conf.getEMF400Instance();
    }

    /**
     * Method getDefaultDocumentType_Id.
     * 
     * @return long
     */
    public static long getDefaultDocumentType_Id() {
        return new Long(conf.getProps().getProperty(Conf.TIPODOCUMENTO));
    }

    /**
     * @return the currentSession
     */
    public static Session getCurrentSession() {
        return currentSession;
    }

    /**
     * Method getUFI.
     * 
     * @return String
     */
    public static String getUFI() {
        return conf.getProps().getProperty(Conf.UFI);
    }

    /**
     * Method getNUFI.
     * 
     * @return String
     */
    public static String getNUFI() {
        return conf.getProps().getProperty(Conf.NUFI);
    }

    /**
     * Method getPorcUFI.
     * 
     * @return String
     */
    public static String getPorcUFI() {
        return conf.getProps().getProperty(Conf.PORCUFI);
    }

    /**
     * Method getCashierFund.
     * 
     * @return String
     */
    public static String getCashierFund() {
        return conf.getProps().getProperty(Conf.FONDO_CAJERO, "1");
    }

    /**
     * Method getMaxCashierFund.
     * 
     * @return String
     */
    public static String getMaxCashierFund() {
        return conf.getProps().getProperty(Conf.FONDOMAXIMOCAJERO, "40");
    }

    /**
     * Method getServiceTypeRefund.
     * 
     * @return int
     */
    public static int getServiceTypeRefund() {
        return Integer.parseInt(conf.getServicesType().get("Refund"));
    }

    /**
     * Method getServiceTypeRefundCompleted.
     * 
     * @return int
     */
    public static int getServiceTypeRefundCompleted() {
        return Integer.parseInt(conf.getServicesType().get("RefundCompleted"));
    }

    /**
     * Method getServiceTypeCancellation.
     * 
     * @return int
     */
    public static int getServiceTypeCancellation() {
        return Integer.parseInt(conf.getServicesType().get("Cancellation"));
    }

    /**
     * Method getServiceTypeClientOrder.
     * 
     * @return int
     */
    public static int getServiceTypeClientOrder() {
        return Integer.parseInt(conf.getServicesType().get("ClientOrder"));
    }

    /**
     * Method getServiceTypeDeleteClientOrder.
     * 
     * @return int
     */
    public static int getServiceTypeDeleteClientOrder() {
        return Integer.parseInt(conf.getServicesType().get("DeleteClientOrder"));
    }

    /**
     * Method getServiceTypeSyncUpload.
     * 
     * @return int
     */
    public static int getServiceTypeSyncUpload() {
        return Integer.parseInt(conf.getServicesType().get("SyncUpload"));
    }

    /**
     * Method getServiceTypeSyncDownload.
     * 
     * @return int
     */
    public static int getServiceTypeSyncDownload() {
        return Integer.parseInt(conf.getServicesType().get("SyncDownload"));
    }

    /**
     * Method getEfectivoFDP.
     * 
     * @return int
     */
    public static int getEfectivoFDP() {
        String key = "Efectivo";
        return getPaymentMethodType(key);
    }

    /**
     * Method getElectronicaFDP.
     * 
     * @return int
     */
    public static int getElectronicaFDP() {
        String key = "Electronica";
        return getPaymentMethodType(key);
    }

    /**
     * Method getBilleteEpaFDP.
     * 
     * @return int
     */
    public static int getBilleteEpaFDP() {
        String key = "Billeteepa";
        return getPaymentMethodType(key);
    }

    /**
     * Method getChequeFDP.
     * 
     * @return int
     */
    public static int getChequeFDP() {
        String key = "Cheque";
        return getPaymentMethodType(key);
    }

    /**
     * Method getRetencionFDP.
     * 
     * @return int
     */
    public static int getRetencionFDP() {
        String key = "Retencion";
        return getPaymentMethodType(key);
    }

    /**
     * Method getAcreenciaFDP.
     * 
     * @return int
     */
    public static int getAcreenciaFDP() {
        String key = "Acreencia";
        return getPaymentMethodType(key);
    }

    /**
     * Method getCargoCuentaFDP.
     * 
     * @return int
     */
    public static int getCargoCuentaFDP() {
        String key = "Cargoacuenta";
        return getPaymentMethodType(key);
    }

    /**
     * Method getAcreenciaPedidoFDP.
     * 
     * @return int
     */
    public static int getAcreenciaPedidoFDP() {
        String key = "Acreenciapedido";
        return getPaymentMethodType(key);
    }

    /**
     * Method getCreditoCorporativoFDP.
     * 
     * @return int
     */
    public static int getCreditoCorporativoFDP() {
        String key = "Creditocorporativo";
        return getPaymentMethodType(key);
    }

    /**
     * Method getCreditoEpaFDP.
     * 
     * @return int
     */
    public static int getCreditoEpaFDP() {
        String key = "Creditoepa";
        return getPaymentMethodType(key);
    }

    /**
     * Method getExoneracionFDP.
     * 
     * @return int
     */
    public static int getExoneracionFDP() {
        String key = "Exoneracion";
        return getPaymentMethodType(key);
    }

    /**
     * Method getSinRecaudacionFDP.
     * 
     * @return int
     */
    public static int getSinRecaudacionFDP() {
        String key = "Sinrecaudacion";
        return getPaymentMethodType(key);
    }

    /**
     * Method getExencionFDP.
     * 
     * @return int
     */
    public static int getExencionFDP() {
        String key = "Exencion";
        return getPaymentMethodType(key);
    }

    /**
     * Method getPaymentMethodType.
     * 
     * @param key String
     * @return int
     */
    private static int getPaymentMethodType(String key) {
        String value = conf.getPaymentMethodType().get(key);

        if (value == null) {
            logger.error("La FDP: " + key + " No pudo ser instanciada");
            return -1;
        }

        return Integer.parseInt(value);
    }

    /**
     * Method getRMI_IP_SYNC.
     * 
     * @return String
     */
    public static String getRMI_IP_SYNC() {
        return conf.getServiceRMI().get(Conf.RMI_ID_SYNC).elementAt(1);
    }

    /**
     * Method getRMI_Service_SYNC.
     * 
     * @return String
     */
    public static String getRMI_Service_SYNC() {
        return conf.getServiceRMI().get(Conf.RMI_ID_SYNC).elementAt(0);
    }

    /**
     * Method getRMI_IP_REFUND.
     * 
     * @return String
     */
    public static String getRMI_IP_REFUND() {
        return conf.getServiceREST().get(Conf.RMI_ID_REFUND).elementAt(1);
    }

    /**
     * Method getRMI_IP_CANCELLATION.
     * 
     * @return String
     */
    public static String getRMI_IP_CANCELLATION() {
        return conf.getServiceREST().get(Conf.RMI_ID_CANCELLATION).elementAt(1);
    }

    /**
     * Method getRMI_IP_CLIENT_ORDER.
     * 
     * @return String
     */
    public static String getRMI_IP_CLIENT_ORDER() {
        return conf.getServiceREST().get(Conf.RMI_ID_CLIENT_ORDER).elementAt(1);
    }

    /**
     * Method getRMI_IP_CHECK_SALE_FROM_SPECIAL_ORDER.
     * 
     * @return String
     */
    public static String getRMI_IP_CHECK_SALE_FROM_SPECIAL_ORDER() {
        return conf.getServiceREST().get(Conf.RMI_ID_CHECK_SALE_FROM_SPECIAL_ORDER).elementAt(1);
    }

    /**
     * Method getRMI_IP_CREDITS.
     * 
     * @return String
     */
    public static String getRMI_IP_CREDITS() {
        return conf.getServiceRMI().get(Conf.RMI_ID_CREDITS).elementAt(1);
    }

    /**
     * Method getRMI_Service_REFUND.
     * 
     * @return String
     */
    public static String getRMI_Service_REFUND() {
        return conf.getServiceREST().get(Conf.RMI_ID_REFUND).elementAt(0);
    }

    /**
     * Method getRMI_IP_ORDERS.
     * 
     * @return String
     */
    public static String getRMI_IP_ORDERS() {
        return conf.getServiceREST().get(Conf.RMI_ID_ORDERS).elementAt(1);
    }

    /**
     * Method getRMI_Service_ORDERS.
     * 
     * @return String
     */
    public static String getRMI_Service_ORDERS() {
        return conf.getServiceREST().get(Conf.RMI_ID_ORDERS).elementAt(0);
    }

    /**
     * Method getRMI_IP_WAITINGSALE.
     * 
     * @return String
     */
    public static String getRMI_IP_WAITINGSALE() {
        return conf.getServiceREST().get(Conf.RMI_ID_WAITINGSALE).elementAt(1);
    }

    /**
     * Method getRMI_Service_WAITINGSALE.
     * 
     * @return String
     */
    public static String getRMI_Service_WAITINGSALE() {
        return conf.getServiceREST().get(Conf.RMI_ID_WAITINGSALE).elementAt(0);
    }

    /**
     * Method getRMI_Service_CANCELLATION.
     * 
     * @return String
     */
    public static String getRMI_Service_CANCELLATION() {
        return conf.getServiceREST().get(Conf.RMI_ID_CANCELLATION).elementAt(0);
    }

    /**
     * Method getRMI_Service_CLIENT_ORDER.
     * 
     * @return String
     */
    public static String getRMI_Service_CLIENT_ORDER() {
        return conf.getServiceREST().get(Conf.RMI_ID_CLIENT_ORDER).elementAt(0);
    }

    /**
     * Method getRMI_Service_DELETE_CLIENT_ORDER.
     * 
     * @return String
     */
    public static String getRMI_Service_DELETE_CLIENT_ORDER() {
        return conf.getServiceREST().get(Conf.RMI_ID_DELETE_CLIENT_ORDER).elementAt(1);
    }

    /**
     * Method getRMI_Service_CHECK_SALE_FROM_SPECIAL_ORDER.
     * 
     * @return String
     */
    public static String getRMI_Service_CHECK_SALE_FROM_SPECIAL_ORDER() {
        return conf.getServiceREST().get(Conf.RMI_ID_CHECK_SALE_FROM_SPECIAL_ORDER).elementAt(0);
    }

    /**
     * Method getRMI_Service_CREDTIS.
     * 
     * @return String
     */
    public static String getRMI_Service_CREDTIS() {
        return conf.getServiceRMI().get(Conf.RMI_ID_CREDITS).elementAt(0);
    }

    /**
     * Method getRMIServices.
     * 
     * @return HashMap<Integer,Vector<String>>
     */
    public static HashMap<Integer, Vector<String>> getRMIServices() {
        return conf.getServiceRMI();
    }

    public static HashMap<Integer, Vector<String>> getRESTServices() {
        return conf.getServiceREST();
    }

    /**
     * Method getDiscountType.
     * 
     * @return long
     */
    public static long getDiscountType() {
        return new Long(conf.getProps().getProperty(Conf.DISCOUNTTYPE));
    }

    /**
     * Method getDefaulFdpRetention.
     * 
     * @return long
     */
    public static long getDefaulFdpRetention() {
        return new Long(Long.parseLong((String) CRUtils
                .buildPropertiesVector(conf.getProps().getProperty(Conf.FDP_RET_EXO)).elementAt(0)));
    }

    /**
     * Method getDefaultFdpExoneration.
     * 
     * @return long
     */
    public static long getDefaultFdpExoneration() {
        return new Long(Long.parseLong((String) CRUtils
                .buildPropertiesVector(conf.getProps().getProperty(Conf.FDP_RET_EXO)).elementAt(1)));
    }

    /**
     * Method getDefaultFDP.
     * 
     * @return int
     */
    public static int getDefaultFDP() {
        return new Integer(conf.getProps().getProperty(Conf.FDP_POR_DEFECTO));
    }

    /**
     * Method getPluginsOrder.
     * 
     * @return Vector<String>
     */
    public static Vector<String> getPluginsOrder() {
        return CRUtils.buildPropertiesVector(conf.getProps().getProperty(Conf.PLUGINSORDER));
    }

    /**
     * Method getDefaultValues.
     * 
     * @return DefaultValues
     */
    public static DefaultValues getDefaultValues() {
        return Conf.DEFAULTVALUES;
    }

    /**
     * Method getMaxCreditsToRefund.
     * 
     * @return CRBigDecimal
     */
    public static CRBigDecimal getMaxCreditsToRefund() {
        return new CRBigDecimal(Double.valueOf(getUFI()))
                .multiply(new CRBigDecimal(Double.valueOf(conf.getProps().getProperty(Conf.UFIMAXIMOACRENCIAS))));
    }

    /**
     * Method isAutoDepositCreditsActive.
     * 
     * @return boolean
     */
    public static boolean isAutoDepositCreditsActive() {
        return ActiveValues.get(conf.getProps().getProperty(Conf.AUTO_DEPOSIT_CREDITS, "N")).getValue();
    }

    /**
     * Method getDimensionDialogButtons.
     * 
     * @return Dimension
     */
    public static Dimension getDimensionDialogButtons() {
        return conf.getDimDialogButtons();
    }

    /**
     * Method isAutoReverseActive.
     * 
     * @return boolean
     */
    public static boolean isAutoReverseActive() {
        return ActiveValues.get(conf.getProps().getProperty(Conf.REVERSARPAGO)).getValue();
    }

    /**
     * Method isAutoReverseinRefund.
     * 
     * @return boolean
     */
    public static boolean isAutoReverseinRefund() {
        return ActiveValues.get(conf.getProps().getProperty(Conf.REVERSARPAGODEVOLUCION)).getValue();
    }

    /**
     * Method getDefaulCreditsDepositTypeOperation.
     * 
     * @return int
     */
    public static int getDefaulCreditsDepositTypeOperation() {
        return new Integer(conf.getProps().getProperty(Conf.TIPOPROCESODEPOSITOACREENCIAS));
    }

    /**
     * Method getDefaulCreditsConsumeTypeOperation.
     * 
     * @return int
     */
    public static int getDefaulCreditsConsumeTypeOperation() {
        return new Integer(conf.getProps().getProperty(Conf.TIPOPROCESOCONSUMOACREENCIAS));
    }

    /**
     * Method getPasswordStringValidator.
     * 
     * @return String
     */
    public static String getPasswordStringValidator() {
        return conf.getProps().getProperty(Conf.VALIDACIONPASSWORD);
    }

    /**
     * Method getNotEmptyStringValidator.
     * 
     * @return String
     */
    public static String getNotEmptyStringValidator() {
        return "^(?=\\s*\\S).*$";
    }

    /**
     * Method getNumberStringValidator.
     * 
     * @return String
     */
    public static String getNumberStringValidator() {
        return conf.getProps().getProperty(Conf.VALIDACIONNUMEROS);
    }

    /**
     * Method getPorcentDepositOrder.
     * 
     * @return CRBigDecimal
     */
    public static CRBigDecimal getPorcentDepositOrder() {
        return new CRBigDecimal(Double.valueOf(conf.getProps().getProperty(Conf.PORCENTAJEABONOINICIAL)));
    }

    /**
     * Method getChargeToAccountFDPId.
     * 
     * @return long
     */
    public static long getChargeToAccountFDPId() {
        return new Long(Long.parseLong(conf.getProps().getProperty(Conf.FDP_CARGO_CUENTA)));
    }

    /**
     * Method getCreditsFDPId.
     * 
     * @return long
     */
    public static long getCreditsFDPId() {
        return new Integer(conf.getProps().getProperty(Conf.FDP_ACREENCIAS));
    }

    public static Long getGiftCardCounterPart() {
        return new Long(conf.getProps().getProperty(Conf.GIFT_CARD_COUNTERPART));
    }

    /**
     * Method getCreditsClass.
     * 
     * @return String
     */
    public static String getCreditsClass() {
        return conf.getProps().getProperty(Conf.CLASEACREENCIAS);
    }

    /**
     * Method getProcesoFDP.
     * 
     * @return HashMap<String,Vector<String>>
     */
    public static HashMap<String, Vector<String>> getProcesoFDP() {
        return CRUtils.buildPropertiesHashVectorDecision(conf.getProps().getProperty(Conf.PROCESS));
    }

    /**
     * Method getObligatoryFieldsByClientType.
     * 
     * @return HashMap<String,Vector<String>>
     */
    public static HashMap<String, Vector<String>> getObligatoryFieldsByClientType() {
        return CRUtils.buildPropertiesHashVectorDecision(conf.getProps()
                .getProperty(Conf.CAMPOSOBLIGATORIOPORCLIENTE, "N@1:J@1"));
    }

    /**
     * Method getFieldsShowedByClientType.
     * 
     * @return HashMap<String,Vector<String>>
     */
    public static HashMap<String, Vector<String>> getFieldsShowedByClientType() {
        return CRUtils.buildPropertiesHashVectorDecision(conf.getProps()
                .getProperty(Conf.CAMPOSMOSTRADOSPORCLIENTE, "N@1:J@1"));
    }

    /**
     * Method getIssuesMonthlyZReport.
     * 
     * @return boolean
     */
    public static boolean getIssuesMonthlyZReport() {
        return new Boolean(Conf.ISSUES_ZREPORT_MONTHLY);
    }

    /**
     * Method getMainFrame.
     * 
     * @return Frame
     */
    public static Frame getMainFrame() {
        return mainFrame;
    }

    /**
     * Method getDepositTypeForSpecialOrder.
     * 
     * @return String
     */
    public static String getDepositTypeForSpecialOrder() {
        return conf.getProps().getProperty(Conf.TIPODEPOSITOABONOPE);
    }

    /**
     * Method getCancellationDepositTypeForSpecialOrder.
     * 
     * @return String
     */
    public static String getCancellationDepositTypeForSpecialOrder() {
        return conf.getProps().getProperty(Conf.TIPOANULACIONABONOPE);
    }

    /**
     * Method getDimensionSmallButtons.
     * 
     * @return Dimension
     */
    public static Dimension getDimensionSmallButtons() {
        return conf.getDimSmallButtons();
    }

    /**
     * Method getDimensionBigButtons.
     * 
     * @return Dimension
     */
    public static Dimension getDimensionBigButtons() {
        return conf.getDimBigButtons();
    }

    /**
     * Method getDimensionInputs.
     * 
     * @return Dimension
     */
    public static Dimension getDimensionInputs() {
        return conf.getDimInputs();
    }

    /**
     * Method getDimensionSquareButtons.
     * 
     * @return Dimension
     */
    public static Dimension getDimensionSquareButtons() {
        return conf.getDimSquareInputs();
    }

    /**
     * Method getOrdersCreditsDepositTypeOperation.
     * 
     * @return int
     */
    public static int getOrdersCreditsDepositTypeOperation() {
        return Integer.parseInt(conf.getProps().getProperty(Conf.DEPOSITOACREENCIASPEDIDOESPECIAL));
    }

    /**
     * Method getOrdersCreditsRefundTypeOperation.
     * 
     * @return int
     */
    public static int getOrdersCreditsRefundTypeOperation() {
        return Integer.parseInt(conf.getProps().getProperty(Conf.DEVOLUCIONACREENCIASPEDIDOESPECIAL));
    }

    /**
     * Method getOrdersCreditsCancellationTypeOperation.
     * 
     * @return int
     */
    public static int getOrdersCreditsCancellationTypeOperation() {
        return Integer.parseInt(conf.getProps().getProperty(Conf.ANULACIONACREENCIASPEDIDOESPECIAL));
    }

    /**
     * Method getOrdersCreditsConsumeTypeOperation.
     * 
     * @return int
     */
    public static int getOrdersCreditsConsumeTypeOperation() {
        return Integer.parseInt(conf.getProps().getProperty(Conf.CONSUMOACREENCIASPEDIDOESPECIAL));
    }

    /**
     * Method getDefaultPaymentMethodIdForOrders.
     * 
     * @return int
     */
    public static int getDefaultPaymentMethodIdForOrders() {
        return Integer.parseInt(conf.getProps().getProperty(Conf.FORMADEPAGOPEDIDOSESPECIALES));
    }

    /**
     * Method getIPWebService.
     * 
     * @return String
     */
    public static String getIPWebService() {
        return conf.getProps().getProperty(Conf.IPWEBASERVICE);
    }

    /**
     * Method getTelephoneFormat.
     * 
     * @return String
     */
    public static String getTelephoneFormat() {
        return conf.getProps().getProperty(Conf.TELEPHONEFORMAT);
    }

    /**
     * Method getEmailFormat.
     * 
     * @return String
     */
    public static String getEmailFormat() {
        return conf.getProps().getProperty(Conf.EMAILFORMAT);
    }

    public static String getClientAddressFormat() {
        return conf.getProps().getProperty(Conf.ID_ADDRESSFORMAT, "^.{1,250}$");
        // return "^.{1,250}$";
    }

    /**
     * Method getCustomerValidateName.
     * 
     * @return String
     */
    public static String getCustomerValidateName() {
        return conf.getProps().getProperty(Conf.CUSTOMERVALIDATENAME);
    }

    /**
     * Method getValidateTaxpayerId.
     * 
     * @return String
     */
    public static String getValidateTaxpayerId() {
        return conf.getProps().getProperty(Conf.VALIDATETAXPAYERID);
    }

    /**
     * Method getPercentToChargeForSpecialOrder.
     * 
     * @return Integer
     */
    public static Integer getPercentToChargeForSpecialOrder() {
        return Integer.parseInt((String) CRUtils
                .buildPropertiesVector(conf.getProps().getProperty(Conf.CARGOPORSERVICIO)).elementAt(0));
    }

    /**
     * Method getArtCodeChargeForSpecialOrder.
     * 
     * @return String
     */
    public static String getArtCodeChargeForSpecialOrder() {
        return (String) CRUtils.buildPropertiesVector(conf.getProps().getProperty(Conf.CARGOPORSERVICIO))
                .elementAt(1);
    }

    /**
     * Method getPayFormChargeForSpecialOrder.
     * 
     * @return Integer
     */
    public static Integer getPayFormChargeForSpecialOrder() {
        return Integer.parseInt((String) CRUtils
                .buildPropertiesVector(conf.getProps().getProperty(Conf.CARGOPORSERVICIO)).elementAt(2));
    }

    /**
     * Method isConfLoaded.
     * 
     * @return boolean
     */
    public static boolean isConfLoaded() {
        return isConfLoaded;
    }

    /**
     * Method loadConfValues.
     * 
     * @return String
     */
    public static String loadConfValues() {
        String response = conf.loadValues();
        if (response == null) {
            images = Images.getInstance();
        }
        return response;
    }

    /**
     * Method isAllowMultipleDocTypes.
     * 
     * @return boolean
     */
    public static boolean isAllowMultipleDocTypes() {
        String str = conf.getProps().getProperty(Conf.PERMITETPOSDEDOCUMENTO);
        return ActiveValues.get(str).getValue();
    }

    /**
     * Method getTimeWindow.
     * 
     * @return Double
     */
    public static Double getTimeWindow() {
        return Double.valueOf(conf.getProps().getProperty(Conf.TIMEWINDOW, "3"));
    }

    /**
     * Method getEnableSync.
     * 
     * @return String
     */
    public static String getEnableSync() {
        return conf.getProps().getProperty(Conf.CLIENTSYNC, "S");
    }

    /**
     * Method getSuggestedDonations.
     * 
     * @return Collection<String>
     */
    public static Collection<String> getSuggestedDonations() {
        return CRUtils.buildPropertiesVector(conf.getProps().getProperty(Conf.DONATIONOPTIONS, "1,5,10,20"));
    }

    /**
     * Method getPMAllowAcumulate.
     * 
     * @return Collection<String>
     */
    public static Collection<String> getPMAllowAcumulate() {
        return CRUtils.buildPropertiesVector(conf.getProps().getProperty(Conf.ALLOWACUMULATEPAY, "1"));
    }

    /**
     * Method getMinAmountRetention.
     * 
     * @return long
     */
    public static long getMinAmountRetention() {
        return new Long(conf.getProps().getProperty(Conf.MONTORETENCION, "0"));
    }

    /**
     * Method getStoreCheckTypeAccount.
     * 
     * @return String
     */
    public static String getStoreCheckTypeAccount() {
        return conf.getProps().getProperty(Conf.CHECKTYPEACCOUNT, "tipo cuenta");
    }

    /**
     * Method getStoreCheckAccount.
     * 
     * @return String
     */
    public static String getStoreCheckAccount() {
        return conf.getProps().getProperty(Conf.CHECKACCOUNT, "numero cuenta");
    }

    /**
     * Method getCheckBank.
     * 
     * @return String
     */
    public static String getCheckBank() {
        return conf.getProps().getProperty(Conf.CHECKBANKACCOUNT, "banco deposito");
    }

    /**
     * Method isAllowFrontCheckPrint.
     * 
     * @return boolean
     */
    public static boolean isAllowFrontCheckPrint() {
        String str = conf.getProps().getProperty(Conf.PERMITEFRENTECHEQUE, "S");
        return ActiveValues.get(str).getValue();
    }

    /**
     * Method isAllowBackCheckPrint.
     * 
     * @return boolean
     */
    public static boolean isAllowBackCheckPrint() {
        String str = conf.getProps().getProperty(Conf.PERMITEDORSOCHEQUE, "N");
        return ActiveValues.get(str).getValue();
    }

    /**
     * Method getStoreInfo.
     * 
     * @return Store
     */
    public static Store getStoreInfo() {
        return new Store(getStore(), getStoreName(), getStoreAddress(), getStorePhone(), getRIF(),
                getStoreOffice(), getStoreOfficeAddress(), getStoreOfficePhone());
    }

    /**
     * Method getStorePhone.
     * 
     * @return String
     */
    private static String getStorePhone() {
        String str = conf.getProps().getProperty(Conf.STOREPHONE);
        return str;
    }

    /**
     * Method getStoreOfficePhone.
     * 
     * @return String
     */
    private static String getStoreOfficePhone() {
        String str = conf.getProps().getProperty(Conf.STOREOFFICEPHONE);
        return str;
    }

    /**
     * Method getStoreOffice.
     * 
     * @return String
     */
    private static String getStoreOffice() {
        String str = conf.getProps().getProperty(Conf.STOREOFFICE);
        return str;
    }

    /**
     * Method getStoreOfficeAddress.
     * 
     * @return String
     */
    private static String getStoreOfficeAddress() {
        String str = conf.getProps().getProperty(Conf.STOREOFFICEADDRESS);
        return str;
    }

    /**
     * Method getMascaraDUI.
     * 
     * @return String
     */
    public static String getMascaraDUI() {
        return conf.getProps().getProperty(Conf.MASCARADUI, "");
    }

    /**
     * Method getFdpExoRet.
     * 
     * @return Collection<String>
     */
    public static Collection<String> getFdpExoRet() {
        return CRUtils.buildPropertiesVector(conf.getProps().getProperty(Conf.FDP_RET_EXO, "8,10"));
    }

    /**
     * Method getPosType.
     * 
     * @return String
     */
    public static String getPosType() {
        return conf.getProps().getProperty(Conf.TIPOCAJA, "0");
    }

    public static int getUmbralCapturaHuella() {
        return Integer.parseInt(conf.getProps().getProperty(Conf.UMBRAL_CAPTURA_HUELLA, "0"));
    }

    /**
     * Method getAlicuotasImpuestos.
     * 
     * @return String
     */
    public static String getAlicuotasImpuestos() {
        return conf.getProps().getProperty(Conf.ALICUOTAS_IMPUESTOS, "0");
    }

    /**
     * 
     * @return el url al servicio de cotejo
     */
    public static String getConciliationServiceURL() {
        return conf.getProps().getProperty(Conf.CONCILIATION_SERVICE_URL, "http://EPA/CRCreditsComparison");
    }

    /**
     * 
     * @return la ruta del controlador para el cotejo de movimientos
     */
    public static String getConciliationServicePath() {
        return conf.getProps().getProperty(Conf.CONCILIATION_SERVICE_PATH, "/rectify");
    }

    /**
     * 
     * @return retorna en segundos el tiempo de timeout para el callback del cotejo
     */
    public static Long getConciliationResponseTimeout() {
        return new Long(conf.getProps().getProperty(Conf.CONCILIATION_CALLBACKTIMEOUT, "10"));
    }

    /**
     * 
     * @return regresa en ms el timeout de conexion a servicio
     */
    public static int getConciliationConnectTimeout() {
        return Integer.parseInt(conf.getProps().getProperty(Conf.CONCILIATION_CONNECT_TIMEOUT, "5000"));
    }

    /**
     * 
     * @return retorna en ms el timeout para lectura del servicio
     */
    public static Integer getConciliationReadTimeout() {
        return Integer.parseInt(conf.getProps().getProperty(Conf.CONCILIATION_READ_TIMEOUT, "6000"));
    }

    /**
     * Method getCorporatePayId.
     * 
     * @return long
     */
    public static long getCorporatePayId() {
        return new Long(conf.getProps().getProperty(Conf.CORPORATEPAYID));
    }

    /**
     * 
     * @return the statusLabel
     */
    /*
     * TODO Quitar y manejar todo con el currentProcess
     */
    public static JLabel getStatusLabel() {
        return statusLabel;
    }

    /**
     * Method getPMNoPermitByTelemarket.
     * 
     * @return Vector<String>
     */
    public static Vector<String> getPMNoPermitByTelemarket() {
        return CRUtils.buildPropertiesVector(conf.getProps().getProperty(Conf.PMNOPERMITBYTELEMARKET, "1"));
    }

    /**
     * Method getPMPermitByTelemarket.
     * 
     * @return Vector<String>
     */
    public static Vector<String> getPMPermitByTelemarket() {
        return CRUtils.buildPropertiesVector(conf.getProps().getProperty(Conf.PMPERMITBYTELEMARKET, "1"));
    }

    /**
     * Method isZReportReprintPermit.
     * 
     * @return boolean
     */
    public static boolean isZReportReprintPermit() {
        return ActiveValues.get(conf.getProps().getProperty(Conf.PERMITEREIMPRIMIRZ, "N")).getValue();
    }

    /**
     * Method getVersion.
     * 
     * @return String
     */
    public static JLabel getVersion() {
        Properties props = new Properties();

        java.net.URL url = Thread.currentThread().getContextClassLoader().getResource(version_props);
        if (url != null) {
            try {
                props.load(url.openStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        VERSION = props.getProperty("VERSIONJARS", "Desarrollo");
        /*
         * Se commenta esta linea porque el SAT en Guatemala solo queiere ver la versión
         * que se especificó en el pase a certificación con ellos.
         */
        /*
         * TODO - parametrizar por país para que se muestre en paises distintos a
         * Guatemala.
         */
        // VERSION += "." + props.getProperty("VERSIONLIBS", "Desarrollo");

        versionLabel = new JLabel(VERSION + " " + (isSelfCheckOutMode() ? "SC" : ""));
        return versionLabel;
    }

    /**
     * Method getShowChangeZero.
     * 
     * @return boolean
     */
    public static boolean getShowChangeZero() {

        return ActiveValues.get(conf.getProps().getProperty(Conf.MUESTRAVUELTOCERO, "S")).getValue();
    }

    /**
     * Method getLongitudCodigoBarraDocumentos.
     * 
     * @return int
     */
    public static int getLongitudCodigoBarraDocumentos() {

        return Integer.parseInt(conf.getProps().getProperty(Conf.LONGITUDCODBARRADOCUMENTOS, "20"));
    }

    /**
     * Method getFDPCheck.
     * 
     * @return long
     */
    public static long getFDPCheck() {
        return new Long(conf.getProps().getProperty(Conf.FDPCHEQUE, "5"));
    }

    /**
     * Method getCheckAmountSpace.
     * 
     * @return int
     */
    public static int getCheckAmountSpace() {
        return Integer.parseInt(conf.getProps().getProperty(Conf.LONGITUDESPACIOMONTOCHEQUE, "62"));
    }

    /**
     * Method getCreditsOperationTypesWithoutUfisValidator.
     * 
     * @return Vector<String>
     */
    public static Vector<String> getCreditsOperationTypesWithoutUfisValidator() {
        return CRUtils.buildPropertiesVector(conf.getProps()
                .getProperty(Conf.IDTIPOACREENCIAOPERACIONSINVALICACIONDEUFIS, "8"));
    }

    /**
     * Method getCheckAmountSpaceHeight.
     * 
     * @return int
     */
    public static int getCheckAmountSpaceHeight() {

        return Integer.parseInt(conf.getProps().getProperty(Conf.LONGITUDESPACIOMONTOCHEQUEALTO, "7"));
    }

    /**
     * Method getLineAllowsTopPrice.
     * 
     * @return Collection
     */
    public static Collection getLineAllowsTopPrice() {
        return CRUtils.buildPropertiesVector(conf.getProps().getProperty(Conf.LINEAPERMITESUBIRPRECIO, ""));
    }

    /**
     * Method getCashierPasswordStringValidator.
     * 
     * @return Object
     */
    public static Object getCashierPasswordStringValidator() {
        return conf.getProps().getProperty(Conf.VALIDACIONPASSWORDCAJERO, "^[0-9]{5,9}");
    }

    /**
     * Method isRebateActive.
     * 
     * @return boolean
     */
    public static boolean isRebateActive() {
        return ActiveValues.get(conf.getProps().getProperty(Conf.REBAJAACTIVA, "S")).getValue();
    }

    /**
     * Method getCashierDeliveryUrl.
     * 
     * @return String
     */
    public static String getCashierDeliveryUrl() {
        return conf.getProps().getProperty(Conf.URLCAJAPRINCIPAL,
                                           "http://10.1.12.39:9080/CajaprincipalWeb/indexConexion.jsp");
    }

    /**
     * Method getNTPServers.
     * 
     * @return Collection
     */
    public static Collection getNTPServers() {
        return CRUtils
                .buildPropertiesVector(conf.getProps().getProperty(Conf.SERVIDORESNTP, "10.1.9.1,192.168.101.11"));
    }

    /**
     * Method getPosMaxTrNumber.
     * 
     * @return long
     */
    public static long getPosMaxTrNumber() {

        return Long.valueOf(conf.getProps().getProperty(Conf.POSNUMEROMAXTRANSACCION, "999999"));
    }

    /**
     * Method getCreditsFDP.
     * 
     * @return HashMap<String,Vector<String>>
     */
    public static HashMap<String, Vector<String>> getCreditsFDP() {
        return CRUtils
                .buildPropertiesHashVectorDecision(conf.getProps().getProperty(Conf.FDPPOROPERACIONACREENCIAS));
    }

    /**
     * Method getActiveMenuPlugins.
     * 
     * @return Vector<String>
     */
    public static Vector<String> getActiveMenuPlugins() {
        return CRUtils.buildPropertiesVector(conf.getProps().getProperty(Conf.OPCIONESDEMENUACTIVAS, "1"));
    }

    /**
     * Method isCredistPaymentActive.
     * 
     * @return boolean
     */
    public static boolean isCredistPaymentActive() {
        return ActiveValues.get(conf.getProps().getProperty(Conf.PAGOCREDITOEPAACTIVO, "N")).getValue();
    }

    /**
     * Method getDisableDeliveryConditions.
     * 
     * @return Vector
     */
    public static Vector getDisableDeliveryConditions() {
        return CRUtils
                .buildPropertiesVector(conf.getProps().getProperty(Conf.CONDICIONESDEENTREGADESHABILITADAS, ""));
    }

    /**
     * Method getOrdersValidDays.
     * 
     * @return int
     */
    public static int getOrdersValidDays() {
        return Integer.parseInt(conf.getProps().getProperty(Conf.DIASDEVIGENCIACOTIZACIONES, "1"));
    }

    /**
     * Method isAllowMixRetetionAndDispatch.
     * 
     * @return boolean
     */
    public static boolean isAllowMixRetetionAndDispatch() {
        return true;
    }

    /**
     * Method isDonationActive.
     * 
     * @return boolean
     */
    public static boolean isDonationActive() {
        return ActiveValues.get(conf.getProps().getProperty(Conf.DONACIONACTIVO, "S")).getValue();

    }

    /**
     * Method isvPosPermited.
     * 
     * @return boolean
     */
    public static boolean isvPosPermited() {
        return ActiveValues.get(conf.getProps().getProperty(Conf.VPOSPERMITIDO, "S")).getValue();

    }

    /**
     * Method getRebatesForEmployees.
     * 
     * @return Vector
     */
    public static Vector getRebatesForEmployees() {
        return CRUtils.buildPropertiesVector(conf.getProps().getProperty(Conf.MOTIVOSREBAJACOLABORADORES, "7"));
    }

    /**
     * Method getDateToUpdatePos.
     * 
     * @return String
     */
    public static String getDateToUpdatePos() {
        return (conf.getProps().getProperty(Conf.FECHAUPDATE, "01-01-1999"));
    }

    /**
     * Method getTelemarketEnabled.
     * 
     * @return boolean
     */
    public static boolean getTelemarketEnabled() {
        return ActiveValues.get(conf.getProps().getProperty(Conf.VENTANOPRESENCIALACTIVA, "S")).getValue();

    }

    /**
     * Method getRebateDontAllowsChangeQuantity.
     * 
     * @return Vector
     */
    public static Vector getRebateDontAllowsChangeQuantity() {
        return CRUtils.buildPropertiesVector(conf.getProps().getProperty(Conf.MOTIVOSREBAJACAMBIOCANTIDAD, "5"));
    }

    /*
     * Este metodo devuelte el valor a sumar para el ajuste de las facturas cuando se pica
     * por condicion de entrega
     */
    /**
     * Method getAjustAmout.
     * 
     * @return CRBigDecimal
     */
    public static CRBigDecimal getAjustAmout() {
        String value = conf.getProps().getProperty(Conf.MONTOAJUSTEPICADA, "1");
        CRBigDecimal result = new CRBigDecimal(Double.parseDouble(value) / 100);
        return result;
    }

    /**
     * Method getFDPCashierCommitment.
     * 
     * @return Vector<String>
     */
    public static Vector<String> getFDPCashierCommitment() {
        return CRUtils.buildPropertiesVector(conf.getProps().getProperty(Conf.FDPENTREGAPARCIAL, "1"));
    }

    /**
     * Method getDocumentTypesIds.
     * 
     * @return HashMap<String,String>
     */
    public static HashMap<String, String> getDocumentTypesIds() {
        return CRUtils.buildPropertiesHashDecision(conf.getProps().getProperty(Conf.IDSTIPOSDEDOCUMENTOS));
    }

    /**
     * Method getFDPWithMinAmountToAutorize.
     * 
     * @return HashMap<String,String>
     */
    public static HashMap<String, String> getFDPWithMinAmountToAutorize() {
        return CRUtils.buildPropertiesHashDecision(conf.getProps().getProperty(Conf.FDPMONTOMINIMOAUTORIZACION));
    }

    /**
     * Method isArticleServicesActive.
     * 
     * @return boolean
     */
    public static boolean isArticleServicesActive() {
        return ActiveValues.get(conf.getProps().getProperty(Conf.INSTALAMOSACTIVO, "S")).getValue();
    }

    /**
     * Method isAllowShutDown.
     * 
     * @return boolean
     */
    public static boolean isAllowShutDown() {
        return ActiveValues.get(conf.getProps().getProperty(Conf.APAGACAJA, "S")).getValue();
    }

    public static String getEmptyClient() {
        return "EMPTY";
    }

    /**
     * Method isPaperRollRequired.
     * 
     * @return boolean
     */
    public static boolean isPaperRollRequired() {
        return ActiveValues.get(conf.getProps().getProperty(Conf.REQUIEREROLLOAUDITORIA, "N")).getValue();
    }

    /**
     * Method getTimeCheck.
     * 
     * @return TimeCheck
     */
    public static TimeCheck getTimeCheck() {
        return timeCheck;
    }

    /**
     * @param timeCheck the timeCheck to set
     */
    public static void setTimeCheck(TimeCheck timeCheck) {
        Global.timeCheck = timeCheck;
    }

    /**
     * Method isClientTypeExclusive.
     * 
     * @return boolean
     */
    public static boolean isClientTypeExclusive() {
        return ActiveValues.get(conf.getProps().getProperty(Conf.MASCARASCLIENTEEXCLUYENTES, "S")).getValue();
    }

    /**
     * Method getTransactionCalculatorClass.
     * 
     * @return String
     */
    public static String getTransactionCalculatorClass() {
        return (conf.getProps().getProperty(Conf.CALCULATORCLASS,
                                            "com.becoblohm.cr.types.TransactionCalculatorFiscal"));
    }

    /**
     * Method isReverseExoRetinRefund.
     * 
     * @return boolean
     */
    public static boolean isReverseExoRetinRefund() {
        return ActiveValues.get(conf.getProps().getProperty(Conf.REVERSARRETENCIONEXONERACIONENDEVOLUCION, "N"))
                .getValue();
    }

    /**
     * Method getStoreType.
     * 
     * @return String
     */
    public static String getStoreType() {
        return (conf.getProps().getProperty(Conf.TIPOTIENDA, "NG"));
    }

    /**
     * Method getClientSyncId.
     * 
     * @return long
     */
    public static long getClientSyncId() {
        return Long.valueOf((conf.getProps().getProperty(Conf.IDSYNCCLIENTES, "-1")));
    }

    /**
     * Method isDonationMemoryEnabled.
     * 
     * @return boolean
     */
    public static boolean isDonationMemoryEnabled() {
        return false;
    }

    /**
     * Method getNewSaleMotives.
     * 
     * @return String
     */
    public static String getNewSaleMotives() {
        return conf.getProps().getProperty(Conf.ID_OPCION_GRUPO_MOTIV_NUEVA_VENTA, "");
    }

    /**
     * Method getUniqueMotiveForDev.
     * 
     * @return String
     */
    public static String getUniqueMotiveForDev() {
        return conf.getProps().getProperty(Conf.ID_OPCION_MOTIVOS_UNICOS_POR_DEVOLUCION, "");
    }

    /**
     * @return the servicesPorts
     */
    public static Map<String, String> getServicesPorts() {
        return servicesPorts;
    }

    /**
     * @param servicesPorts the servicesPorts to set
     */
    public static void setServicesPorts(Map<String, String> servicesPorts) {
        Global.servicesPorts = servicesPorts;
    }

    /**
     * Method getMaxCReditsRequestTries.
     * 
     * @return String
     */
    public static String getMaxCReditsRequestTries() {
        return conf.getProps().getProperty(Conf.MAX_CREDITS_REQUEST_RETRIES, "0");
    }

    public static String getIpLimitArticleService() {
        return conf.getProps().getProperty(Conf.IP_LIMIT_ARTICLE_SERVICE, "0");
    }

    public static long getTimeOutArticleService() {
        return Long.parseLong(conf.getProps().getProperty(Conf.TIME_OUT_ARTICLE_SERVICE, "0"));
    }

    public static long isAllowRepeatedDiscount() {
        return Long.parseLong(conf.getProps().getProperty(Conf.ALLOW_REPEATED_DISCOUNT, "0"));
    }

    public static String genericClientId() {
        return conf.getProps().getProperty(Conf.GENERIC_CLIENT_ID, "");
    }

    public static String getLocaleStr() {
        String property = conf.getProps().getProperty(Conf.LOCALE, "VE");
        String country = property.substring(property.lastIndexOf('_') + 1, property.length());
        return country;
    }

    public static long getMonthsRetentionQuantity() {
        long quantity = Long.parseLong(conf.getProps().getProperty(Conf.VALID_MONTHS_RETENTION_QUANTITY, "0"));
        return quantity;
    }

    public static boolean isFormControlActive() {
        return conf.getProps().getProperty(Conf.CONTROLDEFORMULARIOACTIVO, "N").equalsIgnoreCase("S");
    }

    /**
     * Method getExencionByClientType.
     * 
     * @return HashMap<String,String>
     */
    public static HashMap<String, String> getExencionByClientType() {
        return CRUtils.buildPropertiesHashDecision(conf.getProps().getProperty(Conf.EXENCIONBYCLIENTTYPE));
    }

    public static int getPorcentajeUmbralAvisoResolucion() {
        return Integer.parseInt(conf.getProps().getProperty(Conf.PORCENTAJEUMBRALAVISORESOLUCION, "75"));
    }

    public static void setIsFiscalPrinter(boolean isFiscal) {
        isFiscalPrinter = isFiscal;
    }

    public static boolean isFiscalPrinter() {
        return isFiscalPrinter;
    }

    /**
     * Method getExencionByClientType.
     * 
     * @return HashMap<String,String>
     */
    public static String getClientPassportPrefix() {
        return conf.getProps().getProperty(Conf.CLIENT_PASSPORT_PREFIX, "169");
    }

    public static HashMap<String, String> getFormatTaxPayerId() {
        return CRUtils.buildPropertiesHashDecision(conf.getProps().getProperty(Conf.FORMAT_TAXPAYER_ID));
    }

    /**
     * Obtiene el valor maximo de digitos para la cantidad de billetes de las entregas, si
     * el valor es 3 son 999 billetes, 4 son 9999 billetes y sucesivamente.
     * 
     * @return el valor que posea la opcion 171 o 4 (9999 billetes)
     */
    public static int getBillsQuantityFieldLength() {
        return Integer.parseInt(conf.getProps().getProperty(Conf.MAX_BILLS_LENGTH, "4"));
    }

    /**
     * @return the paymentMethodByPosType
     */
    public static Collection<PaymentMethod> getPaymentMethodByPos() {
        return paymentMethodByPosType;
    }

    /**
     * @param paymentMethodFactoryList the paymentMethodByPosType to set
     */
    public static void setPaymentMethodByPos(Collection<PaymentMethod> paymentMethodFactoryList) {
        Global.paymentMethodByPosType = paymentMethodFactoryList;
    }

    public static int getMaxStringCheckLength() {
        return Integer.parseInt(conf.getProps().getProperty(Conf.MAX_CHECK_AMOUNT_WORDS_LENGTH, "60"));
    }

    /**
     * Method getGiftCreditsConsumeTypeOperation obtiene el valor asignado a a la
     * operacion consumo en acreencias correspondiente a tarjeta de regalo.
     * 
     * @return int el valor que posee la opcion 172 o el valor 14
     */
    public static int getGiftCreditsConsumeTypeOperation() {
        return Integer.parseInt(conf.getProps().getProperty(Conf.TIPOPROCESOCONSUMOACREENCIASREGALO, "14"));
    }

    /**
     * Method getGiftCreditsConsumeTypeOperation obtiene el valor asignado a a la
     * operacion anulacion en acreencias correspondiente a tarjeta de regalo.
     * 
     * @return int el valor que posee la opcion 173 o el valor 15
     */
    public static int getGiftCreditsDepositTypeOperation() {
        return Integer.parseInt(conf.getProps().getProperty(Conf.TIPOPROCESODEPOSITOACREENCIASREGALO, "15"));
    }

    /**
     * Method isEjPrintedOnPaper pregunta si se imprime rollo de auditoria sobre papel
     * 
     * @return String S o N
     */
    public static String isEjPrintedOnPaper() {
        return conf.getProps().getProperty(Conf.EJ_PRINTED_ON_PAPER, "N");
    }

    /**
     * Method isEjSavedInFile pregunta si se guarda rollo de auditoria en archivo digital
     * 
     * @return String S o N
     */
    public static String isEjSavedInFile() {
        return conf.getProps().getProperty(Conf.EJ_SAVED_IN_FILE, "N");
    }

    /**
     * Method getPorcMinTaxDeduction() para obtener el porcentaje minimo de exoneracion de
     * impuesto
     * 
     * @return String S o N
     */
    public static double getPorcMinTaxDeduction() {
        return Double.parseDouble(conf.getProps().getProperty(Conf.PORC_MIN_TAX_DEDUCTION, "0"));
    }

    /**
     * Method getPorcMaxTaxDeduction para obtener el porcentaje maximo de exoneracion de
     * impuesto
     * 
     * @return String S o N
     */
    public static double getPorcMaxTaxDeduction() {
        return Double.parseDouble(conf.getProps().getProperty(Conf.PORC_MAX_TAX_DEDUCTION, "0"));
    }

    public static boolean limiteDeArticulosActivo() {
        return ActiveValues.get(conf.getProps().getProperty(Conf.LIMITE_DE_ARTICULO, "N")).getValue();
    }

    // OJO BORRAR!
    public static Integer getConnectTimeout() {
        return 10000;
    }

    public static Long getResponseTimeout() {
        return 10000l;
    }

    public static Integer getReadTimeout() {
        return null;
    }

    public static String getServicePath() {
        return null;
    }

    public static String getServiceURL() {
        return null;
    }

    /**
     * Funcion para convertir un texto plano en MD5
     * 
     * @param String original a ser convertido en MD5
     * @return String MD5(original)
     */
    public static String toMD5(String original) {
        MessageDigest digestBarcode = null;
        try {
            digestBarcode = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        }
        digestBarcode.update(original.getBytes());
        byte[] digest = digestBarcode.digest();
        StringBuffer sb = new StringBuffer();
        for (byte b : digest) {
            sb.append(String.format("%02x", b & 0xff));
        }

        return sb.toString();
    }

    /**
     * <p> Gets the min UFI value for the interval to activate the confirmation to
     * generate a check for less of the permitted UFIs. </p> <p> The default value is 5
     * UFIs </p>
     * 
     * @return CRBigDecimal
     */
    public static CRBigDecimal getCheckUfiMinValue() {
        CRBigDecimal minValue = new CRBigDecimal(Double.valueOf(getUFI()));
        return minValue.multiply(new CRBigDecimal(
                Double.valueOf(conf.getProps().getProperty(Conf.CREDITS_CHECK_MINOR_UFI, "5"))));
    }

    /**
     * Given the ID of an option, it returns if the CRPOS.opcion 183 is active or not
     * 
     * @return boolean
     */
    public static boolean isOptionalCheckRefundActive() {
        OpcionJpaController option = new OpcionJpaController(emf);
        return ActiveValues.get(option.isActive(Long.parseLong(Conf.CREDITS_CHECK_MINOR_UFI))).getValue();
    }

    public static boolean isDevMode() {
        return ActiveValues.get(conf.getProps().getProperty(Conf.DEV_MODE, "N")).getValue();
    }

    public static HashMap<String, Object> getProperties400() {
        return conf.getProperties400();
    }

    public static HashMap<String, Object> getPropertiesPos() {
        return conf.getPropertiesPos();
    }

    public static long getPosTypeOptionID() {
        return Conf.getPosTypeOptionID();
    }

    public static long getPosNumberOptionID() {
        return Conf.getPosNumberOptionID();
    }

    public static boolean isExecuteInitialSync() {
        return Conf.isExecuteInitialSync();
    }

    public static int getBulkSize() {
        return Conf.getBulkSize();
    }

    public static String getServerIP() {
        return Conf.getServerIP();
    }

    public static String getUsernameDB() {
        return Conf.getUsernameDB();
    }

    public static String getPasswordDB() {
        return Conf.getPasswordDB();
    }

    public static String getDriverconnection() {
        return Conf.getDriverconnection();
    }

    public static String getUrlconnection() {
        return Conf.getUrlconnection();
    }

    public static URL getClientPolicy() {
        return clientPolicy;
    }

    public static void setClientPolicy(URL clientPolicy) {
        Global.clientPolicy = clientPolicy;
    }

    public static String getDbUrl() {
        return Conf.getDbUrl();
    }

    public static String getPosDbUrl() {
        return Conf.getPosDbUrl();
    }

    public static String getPosIP() {
        return Conf.getPosIP();
    }

    public static String getPosDbname() {
        return Conf.getPosDbname();
    }

    public static String getPosUsernameDB() {
        return Conf.getPosUsernameDB();
    }

    public static String getPosPasswordDB() {
        return Conf.getPosPasswordDB();
    }

    public static String getDbName() {
        return Conf.getDbname();
    }

    public static String getAppServerIP() {
        return (conf.getProps().getProperty(Conf.APPSERVER, "127.0.0.1"));
    }

    public static String getDbServerIP() {
        return (conf.getProps().getProperty(Conf.DBSERVER, "127.0.0.1"));
    }

    public static String getStoreAgilId() {
        return (conf.getProps().getProperty(Conf.STORE_AGIL_ID, "1000433"));
    }

    public static void getValidationProps() {
        conf.loadProps();
        isConfLoaded = conf.validateBasicProperties();
    }
}
