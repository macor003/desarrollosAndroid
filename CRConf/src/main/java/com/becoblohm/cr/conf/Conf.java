/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.conf;

import static org.eclipse.persistence.config.PersistenceUnitProperties.JDBC_PASSWORD;
import static org.eclipse.persistence.config.PersistenceUnitProperties.JDBC_URL;
import static org.eclipse.persistence.config.PersistenceUnitProperties.JDBC_USER;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.becoblohm.cr.crjpa.controller.FormadepagoJpaController;
import com.becoblohm.cr.crjpa.controller.MonedaJpaController;
import com.becoblohm.cr.crjpa.controller.OpcionJpaController;
import com.becoblohm.cr.crjpa.controller.ProcesoJpaController;
import com.becoblohm.cr.crjpa.controller.TasaimpuestotipoJpaController;
import com.becoblohm.cr.crjpa.controller.TipodescuentoJpaController;
import com.becoblohm.cr.crjpa.controller.TipodocumentoJpaController;
import com.becoblohm.cr.crjpa.controller.UnidadventaJpaController;
import com.becoblohm.cr.models.Article;
import com.becoblohm.cr.models.DefaultValues;
import com.becoblohm.cr.models.DeliveryCondition;
import com.becoblohm.cr.models.DiscountType;
import com.becoblohm.cr.models.DocumentType;
import com.becoblohm.cr.models.Money;
import com.becoblohm.cr.models.Payment;
import com.becoblohm.cr.models.SaleUnit;
import com.becoblohm.cr.models.Tax;
import com.becoblohm.cr.models.Transaction;
import com.becoblohm.cr.models.Transaction.Source;
import com.becoblohm.cr.models.Transaction.SourceTransactionType;
import com.becoblohm.cr.types.ActiveValues;
import com.becoblohm.cr.types.CRBigDecimal;
import com.becoblohm.cr.utils.CRUtils;

class Conf {

	/* KEYS */
	/**
	 * Contiene el id de tienda especificado en la tabla CRPOS.opcion en el registro
	 * con id = {@value}
	 */
	public static final String TIENDA = "1";

	/**
	 * Field CAJA. (value is ""2"")
	 */
	public static final String CAJA = "2";

	/**
	 * Field LOCALE. (value is ""3"")
	 */
	public static final String LOCALE = "3";

	/**
	 * Field CAJAPILOTO. (value is ""4"")
	 */
	public static final String CAJAPILOTO = "4";

	/**
	 * Field TIPOCAJA. (value is ""5"")
	 */
	public static final String TIPOCAJA = "5";

	/**
	 * Field MONEDAPRINCIPAL. (value is ""6"")
	 */
	public static final String MONEDAPRINCIPAL = "6";

	/**
	 * Field TIPODOCUMENTO. (value is ""7"")
	 */
	public static final String TIPODOCUMENTO = "7";

	/**
	 * Field UFI. (value is ""8"")
	 */
	public static final String UFI = "8";

	/**
	 * Field NUFI. (value is ""9"")
	 */
	public static final String NUFI = "9";

	/**
	 * Field PORCUFI. (value is ""10"")
	 */
	public static final String PORCUFI = "10";

	/**
	 * Field RMI. (value is ""11"")
	 */
	public static final String RMI = "11";

	/**
	 * Field REST. (value is ""175"")
	 */
	public static final String REST = "175";

	/**
	 * Field EJPRINTEDONPAPER. (value is 176)
	 */
	public static final String EJ_PRINTED_ON_PAPER = "176";

	/**
	 * Field EJ_SAVED_IN_FILE. (value is 177)
	 */
	public static final String EJ_SAVED_IN_FILE = "177";

	/**
	 * Field PORC_MIN_TAX_DEDUCTION. (value is 178)
	 */
	public static final String PORC_MIN_TAX_DEDUCTION = "178";

	/**
	 * Field PORC_MAX_TAX_DEDUCTION. (value is 179)
	 */
	public static final String PORC_MAX_TAX_DEDUCTION = "179";

	/**
	 * Field PORC_MAX_TAX_DEDUCTION. (value is 179)
	 */
	public static final String LIMITE_DE_ARTICULO = "180";

	/**
	 * Field RMI_ID_REFUND. (value is ""1"")
	 */
	public static final String RMI_ID_REFUND = "1";

	/**
	 * Field RMI_ID_CANCELLATION. (value is ""1"")
	 */
	public static final String RMI_ID_CANCELLATION = "1";

	/**
	 * Field RMI_ID_CLIENT_ORDER. (value is ""2"")
	 */
	public static final String RMI_ID_CLIENT_ORDER = "2";

	/**
	 * Field RMI_ID_DELETE_CLIENT_ORDER. (value is ""2"")
	 */
	public static final String RMI_ID_DELETE_CLIENT_ORDER = "2";

	/**
	 * Field RMI_ID_CHECK_SALE_FROM_SPECIAL_ORDER. (value is ""3"")
	 */
	public static final String RMI_ID_CHECK_SALE_FROM_SPECIAL_ORDER = "3";

	/**
	 * Field RMI_ID_CREDITS. (value is ""9"")
	 */
	public static final String RMI_ID_CREDITS = "9";

	/**
	 * Field RMI_ID_ORDERS. (value is ""10"")
	 */
	public static final String RMI_ID_ORDERS = "10";

	/**
	 * Field RMI_ID_WAITINGSALE. (value is ""11"")
	 */
	public static final String RMI_ID_WAITINGSALE = "11";

	/**
	 * Field RMI_ID_SYNC. (value is ""12"")
	 */
	public static final String RMI_ID_SYNC = "12";

	/**
	 * Field SERVICETYPE. (value is ""12"")
	 */
	public static final String SERVICETYPE = "12";

	/**
	 * Field DISCOUNTTYPE. (value is ""13"")
	 */
	public static final String DISCOUNTTYPE = "13";

	/**
	 * Field FDP_RET_EXO. (value is ""14"")
	 */
	public static final String FDP_RET_EXO = "14";

	/**
	 * Field FDP_POR_DEFECTO. (value is ""15"")
	 */
	public static final String FDP_POR_DEFECTO = "15";

	/**
	 * Field PLUGINSORDER. (value is ""16"")
	 */
	public static final String PLUGINSORDER = "16";

	/**
	 * Field UFIMAXIMOACRENCIAS. (value is ""17"")
	 */
	public static final String UFIMAXIMOACRENCIAS = "17";

	/**
	 * Field AUTO_DEPOSIT_CREDITS. (value is ""18"")
	 */
	public static final String AUTO_DEPOSIT_CREDITS = "18";

	/**
	 * Field FDP_ACREENCIAS. (value is ""19"")
	 */
	public static final String FDP_ACREENCIAS = "19";

	public static final String GIFT_CARD_COUNTERPART = "224";

	/**
	 * Field REVERSARPAGO. (value is ""20"")
	 */
	public static final String REVERSARPAGO = "20";

	/**
	 * Field TIPOPROCESODEPOSITOACREENCIAS. (value is ""21"")
	 */
	public static final String TIPOPROCESODEPOSITOACREENCIAS = "21";

	/**
	 * Field TIPOPROCESOCONSUMOACREENCIAS. (value is ""22"")
	 */
	public static final String TIPOPROCESOCONSUMOACREENCIAS = "22";

	/**
	 * Field VALIDACIONPASSWORD. (value is ""23"")
	 */
	public static final String VALIDACIONPASSWORD = "23";

	/**
	 * Field VALIDACIONMONTOS. (value is ""24"")
	 */
	public static final String VALIDACIONMONTOS = "24";

	/**
	 * Field VALIDACIONNUMEROS. (value is ""25"")
	 */
	public static final String VALIDACIONNUMEROS = "25";

	/**
	 * Field FDP_CARGO_CUENTA. (value is ""26"")
	 */
	public static final String FDP_CARGO_CUENTA = "26";

	/**
	 * Field XDETALLED. (value is ""27"")
	 */
	public static final String XDETALLED = "27";

	/**
	 * Field RAZON. (value is ""28"")
	 */
	public static final String RAZON = "28";

	/**
	 * Field RIF. (value is ""29"")
	 */
	public static final String RIF = "29";

	/**
	 * Field NIT. (value is ""30"")
	 */
	public static final String NIT = "30";

	/**
	 * Field STOREADDRESS. (value is ""31"")
	 */
	public static final String STOREADDRESS = "31";

	/**
	 * Field CLASEACREENCIAS. (value is ""32"")
	 */
	public static final String CLASEACREENCIAS = "32";

	/**
	 * Field PROCESS. (value is ""33"")
	 */
	public static final String PROCESS = "33";

	/**
	 * Field TIPOPROCESODEPOSITOACREENCIASINTERNOS. (value is ""34"")
	 */
	public static final String TIPOPROCESODEPOSITOACREENCIASINTERNOS = "34";

	/**
	 * Field TIPOPROCESOCONSUMOACREENCIASINTERNOS. (value is ""35"")
	 */
	public static final String TIPOPROCESOCONSUMOACREENCIASINTERNOS = "35";

	/**
	 * Field VALIDACIONNUMEROSCEPA. (value is ""36"")
	 */
	public static final String VALIDACIONNUMEROSCEPA = "36";

	/**
	 * Field EMITE_REPORTEZ_MENSUAL. (value is ""37"")
	 */
	public static final String EMITE_REPORTEZ_MENSUAL = "37";

	/**
	 * Field PORCENTAJEABONOINICIAL. (value is ""38"")
	 */
	public static final String PORCENTAJEABONOINICIAL = "38";

	/**
	 * Field TIPODEPOSITOABONOPE. (value is ""39"")
	 */
	public static final String TIPODEPOSITOABONOPE = "39";

	/**
	 * Field TIPOANULACIONABONOPE. (value is ""40"")
	 */
	public static final String TIPOANULACIONABONOPE = "40";

	/**
	 * Field DEPOSITOACREENCIASPEDIDOESPECIAL. (value is ""41"")
	 */
	public static final String DEPOSITOACREENCIASPEDIDOESPECIAL = "41";

	/**
	 * Field FORMADEPAGOPEDIDOSESPECIALES. (value is ""42"")
	 */
	public static final String FORMADEPAGOPEDIDOSESPECIALES = "42";

	/**
	 * Field IPWEBASERVICE. (value is ""43"")
	 */
	public static final String IPWEBASERVICE = "43";

	/**
	 * Field TELEPHONEFORMAT. (value is ""44"")
	 */
	public static final String TELEPHONEFORMAT = "44";

	/**
	 * Field DEVOLUCIONACREENCIASPEDIDOESPECIAL. (value is ""45"")
	 */
	public static final String DEVOLUCIONACREENCIASPEDIDOESPECIAL = "45";

	/**
	 * Field EMAILFORMAT. (value is ""46"")
	 */
	public static final String EMAILFORMAT = "46";

	/**
	 * Field CARGOPORSERVICIO. (value is ""47"")
	 */
	public static final String CARGOPORSERVICIO = "47";

	/**
	 * Field VALIDATETAXPAYERID. (value is ""48"")
	 */
	public static final String VALIDATETAXPAYERID = "48";

	/**
	 * Field CONSUMOACREENCIASPEDIDOESPECIAL. (value is ""49"")
	 */
	public static final String CONSUMOACREENCIASPEDIDOESPECIAL = "49";

	/**
	 * Field PORCENTAJESREBAJAS. (value is ""50"")
	 */
	public static final String PORCENTAJESREBAJAS = "50";

	/**
	 * Field PERMITETPOSDEDOCUMENTO. (value is ""51"")
	 */
	public static final String PERMITETPOSDEDOCUMENTO = "51";

	/**
	 * Field CLIENTSYNC. (value is ""52"")
	 */
	public static final String CLIENTSYNC = "52";

	/**
	 * Field CUSTOMERVALIDATENAME. (value is ""53"")
	 */
	public static final String CUSTOMERVALIDATENAME = "53";

	/**
	 * Field TIMEWINDOW. (value is ""55"")
	 */
	public static final String TIMEWINDOW = "55";

	/**
	 * Field DONATIONOPTIONS. (value is ""56"")
	 */
	public static final String DONATIONOPTIONS = "56";

	/**
	 * Field CARDNUMBERFORMAT. (value is ""57"")
	 */
	public static final String CARDNUMBERFORMAT = "57";

	/**
	 * Field CUSTOMERIDFORMAT. (value is ""58"")
	 */
	public static final String CUSTOMERIDFORMAT = "58";

	/**
	 * Field CONFORMNUMBER. (value is ""59"")
	 */
	public static final String CONFORMNUMBER = "59";

	// public static final String TIPODOCUMENTOACREENCIA = "60";
	// public static final String TIPODOCUMENTOMERCHANT = "61";

	/**
	 * Field ALLOWACUMULATEPAY. (value is ""62"")
	 */
	public static final String ALLOWACUMULATEPAY = "62";

	/**
	 * Field CAMPOSOBLIGATORIOPORCLIENTE. (value is ""63"")
	 */
	public static final String CAMPOSOBLIGATORIOPORCLIENTE = "63";

	/**
	 * Field CAMPOSMOSTRADOSPORCLIENTE. (value is ""64"")
	 */
	public static final String CAMPOSMOSTRADOSPORCLIENTE = "64";

	/**
	 * Field CHECKTYPEACCOUNT. (value is ""66"")
	 */
	public static final String CHECKTYPEACCOUNT = "66";

	/**
	 * Field CHECKACCOUNT. (value is ""67"")
	 */
	public static final String CHECKACCOUNT = "67";

	/**
	 * Field CHECKBANKACCOUNT. (value is ""68"")
	 */
	public static final String CHECKBANKACCOUNT = "68";

	/**
	 * Field PERMITEFRENTECHEQUE. (value is ""69"")
	 */
	public static final String PERMITEFRENTECHEQUE = "69";

	/**
	 * Field PERMITEDORSOCHEQUE. (value is ""70"")
	 */
	public static final String PERMITEDORSOCHEQUE = "70";

	/**
	 * Field MASCARADUI. (value is ""71"")
	 */
	public static final String MASCARADUI = "71";

	/**
	 * Field MONTORETENCION. (value is ""72"")
	 */
	public static final String MONTORETENCION = "72";

	/**
	 * Field IMPEXENTO_POR_DEFECTO. (value is ""73"")
	 */
	public static final String IMPEXENTO_POR_DEFECTO = "73";

	/**
	 * Field FONDO_CAJERO. (value is ""74"")
	 */
	public static final String FONDO_CAJERO = "74";

	/**
	 * Field REVERSARPAGODEVOLUCION. (value is ""75"")
	 */
	public static final String REVERSARPAGODEVOLUCION = "75";

	/**
	 * Field CORPORATEPAYID. (value is ""78"")
	 */
	public static final String CORPORATEPAYID = "78";

	/**
	 * Field BUSINESSUNITY. (value is ""79"")
	 */
	public static final String BUSINESSUNITY = "79";

	/**
	 * Field OPERATIONUNITY. (value is ""80"")
	 */
	public static final String OPERATIONUNITY = "80";

	/**
	 * Field PMNOPERMITBYTELEMARKET. (value is ""86"")
	 */
	public static final String PMNOPERMITBYTELEMARKET = "86";

	/**
	 * Field PERMITEREIMPRIMIRZ. (value is ""87"")
	 */
	public static final String PERMITEREIMPRIMIRZ = "87";

	/**
	 * Field STOREOFFICE. (value is ""88"")
	 */
	public static final String STOREOFFICE = "88";

	/**
	 * Field STOREOFFICEADDRESS. (value is ""89"")
	 */
	public static final String STOREOFFICEADDRESS = "89";

	/**
	 * Field FONDOMAXIMOCAJERO. (value is ""90"")
	 */
	public static final String FONDOMAXIMOCAJERO = "90";

	/**
	 * Field STOREOFFICEPHONE. (value is ""91"")
	 */
	public static final String STOREOFFICEPHONE = "91";

	/**
	 * Field STOREPHONE. (value is ""92"")
	 */
	public static final String STOREPHONE = "92";

	/**
	 * Field MUESTRAVUELTOCERO. (value is ""94"")
	 */
	public static final String MUESTRAVUELTOCERO = "94";

	/**
	 * Field LONGITUDCODBARRADOCUMENTOS. (value is ""95"")
	 */
	public static final String LONGITUDCODBARRADOCUMENTOS = "95";

	/**
	 * Field FDPCHEQUE. (value is ""96"")
	 */
	public static final String FDPCHEQUE = "96";

	/**
	 * Field LONGITUDESPACIOMONTOCHEQUE. (value is ""97"")
	 */
	public static final String LONGITUDESPACIOMONTOCHEQUE = "97";

	/**
	 * Field IDTIPOACREENCIAOPERACIONSINVALICACIONDEUFIS. (value is ""98"")
	 */
	public static final String IDTIPOACREENCIAOPERACIONSINVALICACIONDEUFIS = "98";

	/**
	 * Field LONGITUDESPACIOMONTOCHEQUEALTO. (value is ""99"")
	 */
	public static final String LONGITUDESPACIOMONTOCHEQUEALTO = "99";

	/**
	 * Field LINEAPERMITESUBIRPRECIO. (value is ""100"")
	 */
	public static final String LINEAPERMITESUBIRPRECIO = "100";

	/**
	 * Field APAGACAJA. (value is ""102"")
	 */
	public static final String APAGACAJA = "102";

	/**
	 * Field ANULACIONACREENCIASPEDIDOESPECIAL. (value is ""103"")
	 */
	public static final String ANULACIONACREENCIASPEDIDOESPECIAL = "103";

	/**
	 * Field VALIDACIONPASSWORDCAJERO. (value is ""104"")
	 */
	public static final String VALIDACIONPASSWORDCAJERO = "104";

	/**
	 * Field PERFILCAJEROAUTOPAGO. (value is ""105"")
	 */
	public static final String PERFILCAJEROAUTOPAGO = "105";

	/**
	 * Field REBAJAACTIVA. (value is ""106"")
	 */
	public static final String REBAJAACTIVA = "106";

	/**
	 * Field URLCAJAPRINCIPAL. (value is ""107"")
	 */
	public static final String URLCAJAPRINCIPAL = "107";

	/**
	 * Field SERVIDORESNTP. (value is ""108"")
	 */
	public static final String SERVIDORESNTP = "108";

	/**
	 * Field IDTIPOCLIENTEVALIDAWEBSERVICE. (value is ""109"")
	 */
	public static final String IDTIPOCLIENTEVALIDAWEBSERVICE = "109";

	/**
	 * Field FDPTYPE. (value is ""110"")
	 */
	public static final String FDPTYPE = "110";

	/**
	 * Field POSNUMEROMAXTRANSACCION. (value is ""111"")
	 */
	public static final String POSNUMEROMAXTRANSACCION = "111";

	/**
	 * Field DEFAULT_TAX_ID. (value is ""112"")
	 */
	public static final String DEFAULT_TAX_ID = "112";

	/**
	 * Field DEFAULT_SALE_UNIT. (value is ""113"")
	 */
	public static final String DEFAULT_SALE_UNIT = "113";

	/**
	 * Field FDPPOROPERACIONACREENCIAS. (value is ""114"")
	 */
	public static final String FDPPOROPERACIONACREENCIAS = "114";

	/**
	 * Field OPCIONESDEMENUACTIVAS. (value is ""117"")
	 */
	public static final String OPCIONESDEMENUACTIVAS = "117";

	/**
	 * Field PAGOCREDITOEPAACTIVO. (value is ""118"")
	 */
	public static final String PAGOCREDITOEPAACTIVO = "118";

	/**
	 * Field CONDICIONESDEENTREGADESHABILITADAS. (value is ""119"")
	 */
	public static final String CONDICIONESDEENTREGADESHABILITADAS = "119";

	/**
	 * Field DIASDEVIGENCIACOTIZACIONES. (value is ""120"")
	 */
	public static final String DIASDEVIGENCIACOTIZACIONES = "120";

	/**
	 * Field DONACIONACTIVO. (value is ""121"")
	 */
	public static final String DONACIONACTIVO = "121";

	/**
	 * Field VPOSPERMITIDO. (value is ""122"")
	 */
	public static final String VPOSPERMITIDO = "122";

	/**
	 * Field FECHAUPDATE. (value is ""123"")
	 */
	public static final String FECHAUPDATE = "123";

	/**
	 * Field MOTIVOSREBAJACOLABORADORES. (value is ""124"")
	 */
	public static final String MOTIVOSREBAJACOLABORADORES = "124";

	/**
	 * Field VENTANOPRESENCIALACTIVA. (value is ""125"")
	 */
	public static final String VENTANOPRESENCIALACTIVA = "125";

	/**
	 * Field MOTIVOSREBAJACAMBIOCANTIDAD. (value is ""126"")
	 */
	public static final String MOTIVOSREBAJACAMBIOCANTIDAD = "126";

	/**
	 * Field MONTOAJUSTEPICADA. (value is ""127"")
	 */
	public static final String MONTOAJUSTEPICADA = "127";

	/**
	 * Field FDPENTREGAPARCIAL. (value is ""128"")
	 */
	public static final String FDPENTREGAPARCIAL = "128";

	// public static final String MONTOMINIMOAUTORIZACIONPUNTOAGIL = "129";

	/**
	 * Field FDPMONTOMINIMOAUTORIZACION. (value is ""130"")
	 */
	public static final String FDPMONTOMINIMOAUTORIZACION = "130";

	/**
	 * Field INSTALAMOSACTIVO. (value is ""131"")
	 */
	public static final String INSTALAMOSACTIVO = "131";

	// public static final String IDTIPODOCUMENTOANULACION="132";
	// public static final String IDTIPODOCUMENTODEVOLUCION="133";
	/**
	 * Field IDSTIPOSDEDOCUMENTOS. (value is ""134"")
	 */
	public static final String IDSTIPOSDEDOCUMENTOS = "134";

	/**
	 * Field DAYSTODOWNLOADCLIENTS. (value is ""135"")
	 */
	public static final String DAYSTODOWNLOADCLIENTS = "135";

	/**
	 * Field REQUIEREROLLOAUDITORIA. (value is ""136"")
	 */
	public static final String REQUIEREROLLOAUDITORIA = "136";

	/**
	 * Field REVERSARRETENCIONEXONERACIONENDEVOLUCION. (value is ""137"")
	 */
	public static final String REVERSARRETENCIONEXONERACIONENDEVOLUCION = "137";

	/**
	 * Field MASCARASCLIENTEEXCLUYENTES. (value is ""138"")
	 */
	public static final String MASCARASCLIENTEEXCLUYENTES = "138";

	/**
	 * Field CALCULATORCLASS. (value is ""139"")
	 */
	public static final String CALCULATORCLASS = "139";

	/**
	 * Field TIPOTIENDA. (value is ""140"")
	 */
	public static final String TIPOTIENDA = "140";

	/**
	 * Field IDSYNCCLIENTES. (value is ""141"")
	 */
	public static final String IDSYNCCLIENTES = "141";

	/**
	 * Field ID_OPCION_GRUPO_MOTIV_NUEVA_VENTA. (value is ""143"")
	 */
	public static final String ID_OPCION_GRUPO_MOTIV_NUEVA_VENTA = "143";

	/**
	 * Field ID_OPCION_MOTIVOS_UNICOS_POR_DEVOLUCION. (value is ""144"")
	 */
	public static final String ID_OPCION_MOTIVOS_UNICOS_POR_DEVOLUCION = "144";

	/**
	 * Field PMPERMITBYTELEMARKET. (value is ""150"")
	 */
	public static final String PMPERMITBYTELEMARKET = "150";

	/**
	 * Mascara para validar la direccion del cliente. </br>
	 * La mascara esta registrada en la tabla CRPOS.opcion en el id {@value} </br>
	 * La direccion de cliente permite cualquier caracter y no debe exceder los 250
	 * caracteres.
	 */
	public static final String ID_ADDRESSFORMAT = "151";

	/**
	 * Field MAX_CREDITS_REQUEST_RETRIES. (value is ""152"")
	 */
	public static final String MAX_CREDITS_REQUEST_RETRIES = "152";

	public static final String UMBRAL_CAPTURA_HUELLA = "153";

	/**
	 * Contiene el id de tienda especificado en la tabla CRPOS.opcion en el registro
	 * con id = {@value}
	 */
	public static final String IP_LIMIT_ARTICLE_SERVICE = "154";

	/**
	 * Contiene el id de tienda especificado en la tabla CRPOS.opcion en el registro
	 * con id = {@value}
	 */
	public static final String TIME_OUT_ARTICLE_SERVICE = "155";

	/**
	 * Indica si se permite repetir el mismo tipo de descuento para un articulo
	 * particular
	 */
	public static final String ALLOW_REPEATED_DISCOUNT = "157";

	/**
	 * Indica la opcion para consultar los valores de las alicuotas de impuestos en
	 * CRPOS
	 */
	public static final String ALICUOTAS_IMPUESTOS = "158";

	// Configuraciones del servicio de cotejo de acreencias
	/**
	 * Url del servicio
	 */
	public static final String CONCILIATION_SERVICE_URL = "159";

	/*
	 * ruta del controlador para las peticiones de cotejo
	 */
	public static final String CONCILIATION_SERVICE_PATH = "160";

	/*
	 * Timeout para la conexion hacia el servicio de cotejo
	 */
	public static final String CONCILIATION_CONNECT_TIMEOUT = "161";

	/**
	 * Timeout para lectura del servicio de cotejo
	 */
	public static final String CONCILIATION_READ_TIMEOUT = "162";

	/**
	 * Timeout para la ejecucion del callback del cotejo que sucede asincronamente.
	 */
	public static final String CONCILIATION_CALLBACKTIMEOUT = "163";

	/**
	 * Indica cual sera el identificador generico para compras sin cliente
	 */
	public static final String GENERIC_CLIENT_ID = "164";

	public static final String VALID_MONTHS_RETENTION_QUANTITY = "165";

	/**
	 * Field CONTROLDEFORMULARIOACTIVO. (value is 166)
	 */
	public static final String CONTROLDEFORMULARIOACTIVO = "166";

	/**
	 * Field PORCENTAJEUMBRALAVISORESOLUCION. (value is 166)
	 */
	public static final String PORCENTAJEUMBRALAVISORESOLUCION = "168";

	/**
	 * CRPOS.opcion.id = {@value} Min value to activate the possibility to refund
	 * the credits money by check even if the amount is not over the max value
	 * permitted for the UFI
	 */
	public static final String CREDITS_CHECK_MINOR_UFI = "183";

	/**
	 * 
	 */
	public static final String TOTAL_DELIVERY_INCLUDE_CASH = "216";

	/**
	 * 
	 */
	public static final String MESSAGE_ADDITIONAL_DONATION = "231";

	public static final String APPSERVER = "228";

	public static final String DBSERVER = "226";
	
	public static final String STORE_AGIL_ID = "236";

	/*
	 * Opcion para configuracion de modo desarrollo
	 */
	protected static String DEV_MODE = "999";

	/**
	 * Field DATE_FORMAT.
	 */
	protected static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

	/**
	 * Field HOURS_FORMAT.
	 */
	protected static final SimpleDateFormat HOURS_FORMAT = new SimpleDateFormat("HH:mm");
	// DateFormat.getTimeInstance(DateFormat.SHORT).format(now));

	/**
	 * Field APP_MODE_NO_DEVICES. Define si no debo usar los dispositivos
	 */
	protected static final int APP_TYPE_REGULAR = 0;

	/**
	 * Field APP_TYPE_SELFCHECKOUT. (value is 1)
	 */
	protected static final int APP_TYPE_SELFCHECKOUT = 1;

	/**
	 * Field APP_TYPE_REGULAR_TESTING. (value is 2)
	 */
	protected static final int APP_TYPE_REGULAR_TESTING = 2;

	/**
	 * Field APP_TYPE_SELFCHECKOUT_TESTING. (value is 3)
	 */
	protected static final int APP_TYPE_SELFCHECKOUT_TESTING = 3;

	/**
	 * Field ISSUES_ZREPORT_MONTHLY.
	 */
	protected static boolean ISSUES_ZREPORT_MONTHLY;

	/**
	 * Field servicesType.
	 */
	private HashMap<String, String> servicesType;

	/**
	 * Field paymentMethodType.
	 */
	private HashMap<String, String> paymentMethodType;

	/**
	 * Field serviceRMI.
	 */
	private HashMap<Integer, Vector<String>> serviceRMI;

	private HashMap<Integer, Vector<String>> serviceREST;

	/**
	 * Field PROCESS_FDP.
	 */
	protected HashMap<String, Vector<String>> PROCESS_FDP;

	/**
	 * Field DEFAULTVALUES.
	 */
	protected static final DefaultValues DEFAULTVALUES = new DefaultValues();

	/**
	 * Field dimDialogButtons.
	 */
	private final Dimension dimDialogButtons = new Dimension(100, 40);

	/**
	 * Field dimSmallButtons.
	 */
	private final Dimension dimSmallButtons = new Dimension(65, 65);

	/**
	 * Field dimBigButtons.
	 */
	private final Dimension dimBigButtons = new Dimension(120, 80);

	/**
	 * Field dimInputs.
	 */
	private final Dimension dimInputs = new Dimension(200, 30);

	/**
	 * Field dimSquareButtons.
	 */
	private final Dimension dimSquareButtons = new Dimension(90, 80);

	/**
	 * Field props.
	 */
	private Properties props;

	/**
	 * Field APP_LOCALE_LANG.
	 */
	protected static Locale APP_LOCALE_LANG;

	/**
	 * Field APP_DEFAULT_CURRENCY.
	 */
	protected long APP_DEFAULT_CURRENCY;

	/**
	 * Field APP_DEFAULT_TAXFREE.
	 */
	protected long APP_DEFAULT_TAXFREE;

	/**
	 * Field APP_DEFAULT_DOCUMENT_TYPE.
	 */
	protected long APP_DEFAULT_DOCUMENT_TYPE;

	/**
	 * Field APP_DISCOUNTTYPE.
	 */
	protected long APP_DISCOUNTTYPE;

	/**
	 * Field APP_DEFAULT_FDP.
	 */
	protected int APP_DEFAULT_FDP;

	/**
	 * Field APP_DEFAULT_TAX_RATE.
	 */
	protected long APP_DEFAULT_TAX_RATE;

	/**
	 * Field APP_DEFAULT_SALE_UNIT.
	 */
	protected long APP_DEFAULT_SALE_UNIT;

	/**
	 * Field conf.
	 */
	private static Conf conf;

	/**
	 * Field errorsBundle.
	 */
	protected ResourceBundle errorsBundle = ResourceBundle
			.getBundle(com.becoblohm.cr.i18n.ErrorsResourceBundle.class.getName(), Locale.getDefault());

	/**
	 * Field labelsBundle.
	 */
	protected ResourceBundle labelsBundle = ResourceBundle
			.getBundle(com.becoblohm.cr.i18n.LabelsResourceBundle.class.getName(), Locale.getDefault());

	/**
	 * Field messagesBundle.
	 */
	protected ResourceBundle messagesBundle = ResourceBundle
			.getBundle(com.becoblohm.cr.i18n.MessagesResourceBundle.class.getName(), Locale.getDefault());

	/**
	 * Field messagesBundle.
	 */
	protected ResourceBundle imagesBundle = ResourceBundle
			.getBundle(com.becoblohm.cr.i18n.ImagesResourceBundle.class.getName(), Locale.getDefault());

	/**
	 * Field iconsBundle.
	 */
	protected ResourceBundle iconsBundle = ResourceBundle
			.getBundle(com.becoblohm.cr.i18n.IconsResourceBundle.class.getName(), Locale.getDefault());

	/**
	 * Field printerTemplateBundle.
	 */
	protected ResourceBundle printerTemplateBundle = ResourceBundle
			.getBundle(com.becoblohm.cr.i18n.PrinterTemplateResourceBundle.class.getName(), Locale.getDefault());

	private static String urlconnection;

	private static String driverconnection;

	/**
	 * Field font_size_small. (value is 12)
	 */
	private static final int font_size_small = 14;

	/**
	 * Field font_size_medium. (value is 14)
	 */
	private static final int font_size_medium = 20;

	/**
	 * Field font_size_big. (value is 18)
	 */

	private static final int font_size_xlbig = 30;

	/**
	 * Field font_size_xlbigger. (value is 45)
	 */
	private static final int font_size_xlbigger = 45;

	/**
	 * Field fontFile.
	 */
	private static File fontFile;

	/**
	 * Field APP_ACTIVITY_FONT.
	 */
	protected static Font APP_BIG_FONT = new Font("FreeSans", Font.PLAIN, font_size_xlbig);

	/**
	 * Field APP_BIG_HEADING_FONT.
	 */
	protected static Font APP_BIG_HEADING_FONT = new Font("FreeSans", Font.BOLD, font_size_xlbig);

	/**
	 * Field APP_BIGGER_HEADING_FONT.
	 */
	protected static Font APP_BIGGER_HEADING_FONT = new Font("FreeSans", Font.BOLD, font_size_xlbigger);

	/**
	 * Field APP_REGULAR_HEADING_FONT.
	 */
	protected static Font APP_REGULAR_HEADING_FONT = new Font("FreeSans", Font.BOLD, font_size_medium);

	/**
	 * Field APP_REGULAR_FONT.
	 */
	protected static Font APP_REGULAR_FONT = new Font("FreeSans", Font.PLAIN, font_size_medium);

	/**
	 * Field STATUS_TEXT_FONT.
	 */
	public static Font APP_SMALL_HEADING_FONT = new Font("FreeSans", Font.BOLD, font_size_small);

	/**
	 * Field APP_SMALL_FONT.
	 */
	protected static Font APP_SMALL_FONT = new Font("FreeSans", Font.PLAIN, font_size_small);

	private static final Logger logger = LoggerFactory.getLogger(Conf.class);

	private static String dbUrl;

	private static String posDbUrl;

	private static String serverIP;

	private static String usernameDB;

	private static String passwordDB;

	private static long posTypeOptionID;

	private static long posNumberOptionID;

	private static boolean executeInitialSync;

	private static int bulkSize;

	private static String dbname;

	private static String posDbname;

	private static String posIP;

	private static String posUsernameDB;

	private static String posPasswordDB;

	private static HashMap<String, Object> properties400 = null;

	private static HashMap<String, Object> propertiesPos = null;

	private static EntityManagerFactory EMF = null;

	private static EntityManagerFactory EMF400 = null;

	static {
		properties400 = new HashMap<String, Object>();
		propertiesPos = new HashMap<String, Object>();
	}

	private Conf() {
		super();
	}

	public static Conf getInstance() {
		if (conf == null) {
			conf = new Conf();
			conf.loadInitialValues();
		}
		return conf;
	}

	private void loadInitialValues() {
		URL tmp = CRUtils.findResource("FUTURAN.TTF", null, Conf.class);

		if (tmp != null) {
			try {
				fontFile = new File(tmp.toURI());
				Font tmpfont = Font.createFont(Font.TRUETYPE_FONT, fontFile);
				APP_BIG_FONT = tmpfont.deriveFont(Font.PLAIN, font_size_xlbig);
				APP_BIG_HEADING_FONT = tmpfont.deriveFont(Font.BOLD, font_size_xlbig);
				APP_BIGGER_HEADING_FONT = tmpfont.deriveFont(Font.BOLD, font_size_xlbigger);
				APP_REGULAR_HEADING_FONT = tmpfont.deriveFont(Font.BOLD, font_size_medium);
				APP_REGULAR_FONT = tmpfont.deriveFont(Font.PLAIN, font_size_medium);
			} catch (FontFormatException e) {

				logger.error("Error", e);
			} catch (IOException e) {

				logger.error("Error", e);
			} catch (URISyntaxException e) {
				logger.error("Error", e);
			}
		}

		URL confUrl = CRUtils.findResource("syncinit.properties", null, null);
		Properties syncInitConf = new Properties();
		try {
			syncInitConf.load(confUrl.openStream());
			setDbname(syncInitConf.getProperty("dbname", "CR400"));
			setServerIP(syncInitConf.getProperty("serverip", "servidor400"));
			setUsernameDB(syncInitConf.getProperty("userdb", "userdb2"));
			setPasswordDB(syncInitConf.getProperty("passdb", "userdb2"));
			setPosTypeOptionID(Long.valueOf(syncInitConf.getProperty("posTypeId", "5")));
			setPosNumberOptionID(Long.valueOf(syncInitConf.getProperty("posNumberId", "2")));
			setBulkSize(Integer.valueOf(syncInitConf.getProperty("initialSyncBulkSize", "700")));
			setExecuteInitialSync(Boolean.valueOf(syncInitConf.getProperty("executeInitialSync", "false")));
			setDriverconnection(syncInitConf.getProperty("driverconnection", "com.mysql.jdbc.Driver"));
			setUrlconnection(syncInitConf.getProperty("urlconnection", "jdbc:as400://"));
			setExecuteInitialSync(Boolean.valueOf(syncInitConf.getProperty("executeInitialSync", "false")));
		} catch (Exception e) {
			setDbname("CR400");
			setServerIP("servidor400");
			setUsernameDB("userdb2");
			setPasswordDB("userdb2");
			setPosTypeOptionID(Long.valueOf("5"));
			setPosNumberOptionID(Long.valueOf("2"));
			setBulkSize(Integer.valueOf("700"));
			setExecuteInitialSync(Boolean.valueOf("false"));
			setDriverconnection("com.mysql.jdbc.Driver");
			setUrlconnection("jdbc:as400://");
		}

		URL localConnectionFile = CRUtils.findResource("localconnection.properties", null, null);
		Properties confLocal = new Properties();
		try {
			confLocal.load(localConnectionFile.openStream());
			setPosDbname(confLocal.getProperty("posdbname", "CRPOS"));
			setPosIP(confLocal.getProperty("posip", "localhost"));
			setPosUsernameDB(confLocal.getProperty("posuserdb", "usercr2"));
			setPosPasswordDB(confLocal.getProperty("pospassdb", "usercr2"));
		} catch (Exception e) {
			setPosDbname("CRPOS");
			setPosIP("localhost");
			setPosUsernameDB("usercr2");
			setPosPasswordDB("usercr2");
		}

		setDbUrl(getUrlconnection() + getServerIP() + "/" + getDbname());
		properties400.put(JDBC_URL, getDbUrl());
		properties400.put(JDBC_USER, getUsernameDB());
		properties400.put(JDBC_PASSWORD, getPasswordDB());

		setPosDbUrl("jdbc:mysql://" + getPosIP() + ":3306/" + getPosDbname() + "?rewriteBatchedStatements=true");
		propertiesPos.put(JDBC_URL, getPosDbUrl());
		propertiesPos.put(JDBC_USER, getPosUsernameDB());
		propertiesPos.put(JDBC_PASSWORD, getPosPasswordDB());
	}

	public EntityManagerFactory getEMFInstance() {
		if (EMF == null) {
			EMF = Persistence.createEntityManagerFactory("CRJPAPU", conf.getPropertiesPos());
			logger.info("Conexion creada a " + conf.getPropertiesPos().toString());
		}
		return EMF;
	}

	public EntityManagerFactory getEMF400Instance() {
		if (EMF400 == null) {
			EMF400 = Persistence.createEntityManagerFactory("CR400PU", conf.getProperties400());
			logger.info("Conexion creada a " + conf.getProperties400().toString());
		} else {
		}
		return EMF400;
	}

	public void loadProps() {
		setProps(new Properties());
		OpcionJpaController jpa = new OpcionJpaController(getEMFInstance());
		setProps(jpa.loadOptions());
	}

	/**
	 * Method validateBasicProperties.
	 * 
	 * @return boolean
	 */
	public boolean validateBasicProperties() {

		boolean isLoaded = true;

		crjpa400.OpcionJpaController countOptions = new crjpa400.OpcionJpaController(getEMF400Instance());

		Integer minimalTotalOptions = countOptions.countOptions();

		if (minimalTotalOptions > 0) {

			if (getProps().size() >= minimalTotalOptions) {

				isLoaded = true;

			} else {
				isLoaded = false;
				logger.debug("La cantidad de opciones en la caja no son las indicadas");
			}
		} else {
			logger.debug("No se puede hacer la validacion de los properties basicos por falta de conexion");
		}

		if (isLoaded) {

			ProcesoJpaController processJpaController = new ProcesoJpaController(getEMFInstance());

			isLoaded = processJpaController.isLoaded();
		}

		return isLoaded;
	}

	/**
	 * Method loadValues.
	 * 
	 * @return String
	 */
	public String loadValues() {
		String response;
		response = loadOptions();
		if (response == null) {
			response = loadDefaultTypeDoc();
		}
		if (response == null) {
			response = loadDefaultMoney();
		}
		if (response == null) {
			response = loadDefaultPaymentMethod();
		}
		if (response == null) {
			response = loadDefaultArticle();
		}
		if (response == null) {
			response = loadDefaultTaxFree();
		}
		return response;
	}

	/**
	 * Method loadOptions.
	 * 
	 * @return String
	 */
	public String loadOptions() {
		String response = null;
		try {

			String locale = getProps().getProperty(LOCALE);
			if (locale.length() > 0) {
				if (locale.length() > 2) {
					String[] tmp = locale.split("_");
					APP_LOCALE_LANG = new Locale(tmp[0], tmp[1].toUpperCase());
				} else {

					APP_LOCALE_LANG = new Locale(locale.toUpperCase());
				}

			} else {
				APP_LOCALE_LANG = Locale.getDefault();
			}
			APP_DEFAULT_CURRENCY = new Long(getProps().getProperty(MONEDAPRINCIPAL));
			APP_DEFAULT_DOCUMENT_TYPE = new Long(getProps().getProperty(TIPODOCUMENTO));
			APP_DISCOUNTTYPE = new Long(getProps().getProperty(DISCOUNTTYPE));
			APP_DEFAULT_FDP = new Integer(getProps().getProperty(FDP_POR_DEFECTO));
			APP_DEFAULT_TAXFREE = new Integer(getProps().getProperty(IMPEXENTO_POR_DEFECTO, "2"));
			APP_DEFAULT_SALE_UNIT = new Long(getProps().getProperty(DEFAULT_SALE_UNIT));
			;
			APP_DEFAULT_TAX_RATE = new Long(getProps().getProperty(DEFAULT_TAX_ID));
			;

			setServicesType(CRUtils.buildPropertiesHashDecision(getProps().getProperty(SERVICETYPE)));

			setPaymentMethodType(CRUtils.buildPropertiesHashDecision(getProps().getProperty(FDPTYPE)));

			setServiceRMI(CRUtils.buildPropertiesHashVectorDecision(getProps().getProperty(RMI)));

			serviceREST = CRUtils.buildPropertiesHashVectorDecision(getProps().getProperty(REST));

			errorsBundle = ResourceBundle.getBundle(com.becoblohm.cr.i18n.ErrorsResourceBundle.class.getName(),
					Conf.APP_LOCALE_LANG);
			labelsBundle = ResourceBundle.getBundle(com.becoblohm.cr.i18n.LabelsResourceBundle.class.getName(),
					Conf.APP_LOCALE_LANG);
			messagesBundle = ResourceBundle.getBundle(com.becoblohm.cr.i18n.MessagesResourceBundle.class.getName(),
					Conf.APP_LOCALE_LANG);
			imagesBundle = ResourceBundle.getBundle(com.becoblohm.cr.i18n.ImagesResourceBundle.class.getName(),
					Conf.APP_LOCALE_LANG);
			iconsBundle = ResourceBundle.getBundle(com.becoblohm.cr.i18n.IconsResourceBundle.class.getName(),
					Conf.APP_LOCALE_LANG);
			printerTemplateBundle = ResourceBundle.getBundle(
					com.becoblohm.cr.i18n.PrinterTemplateResourceBundle.class.getName(), Conf.APP_LOCALE_LANG);

			ISSUES_ZREPORT_MONTHLY = ActiveValues.get(getProps().getProperty(EMITE_REPORTEZ_MENSUAL)).getValue();
		} catch (Exception ex) {
			ex.printStackTrace();
			response = "Error cargando opciones\nComuniquese con el escritorio de ayuda.";
		}
		return response;
	}

	/**
	 * Method loadDefaultTypeDoc.
	 * 
	 * @return String
	 */
	public String loadDefaultTypeDoc() {
		String response = null;
		try {
			TipodocumentoJpaController jpacontroller = new TipodocumentoJpaController(getEMFInstance());
			DocumentType documentType = jpacontroller.getDefaultDocumentType(APP_DEFAULT_DOCUMENT_TYPE);
			Transaction sale = new Transaction();
			sale.setDocument(documentType);
			if (Global.isSelfCheckOutMode()) {
				sale.setSaleOrigin(Source.PosSC);
			} else {
				sale.setSaleOrigin(Source.Pos);
			}

			sale.setTransactionType(SourceTransactionType.Sale);
			DEFAULTVALUES.setDefaultTransaction(sale);
		} catch (Exception ex) {
			response = "Error cargando tipo de documento por defecto";
		}
		return response;
	}

	/**
	 * Method loadDefaultTaxFree.
	 * 
	 * @return String
	 */
	public String loadDefaultTaxFree() {
		String response = null;
		try {
			TasaimpuestotipoJpaController taxController = new TasaimpuestotipoJpaController(getEMFInstance());
			Tax defaultfree = taxController.findTasaivaTipo(new Long(APP_DEFAULT_TAXFREE));
			DEFAULTVALUES.setTaxfree(defaultfree);
		} catch (Exception ex) {
			response = "Error cargando tipo de impuesto exento";
		}
		return response;
	}

	/**
	 * Method loadDefaultMoney.
	 * 
	 * @return String
	 */
	public String loadDefaultMoney() {
		String response = null;
		try {
			MonedaJpaController jpaController = new MonedaJpaController(getEMFInstance());
			Money defaultMoney = jpaController.findDefaultMoney(APP_DEFAULT_CURRENCY);
			defaultMoney.setCurrencyAmmount(CRBigDecimal.ZERO);
			DEFAULTVALUES.setDefaultMoney(defaultMoney);
		} catch (Exception ex) {
			response = "Error cargando moneda por defecto";
		}
		return response;
	}

	/**
	 * Method loadDefaultPaymentMethod.
	 * 
	 * @return String
	 */
	public String loadDefaultPaymentMethod() {
		String response = null;
		try {
			FormadepagoJpaController paymentMethodJPAController = new FormadepagoJpaController(getEMFInstance());

			Payment defaultPayment = new Payment();
			defaultPayment.setDonation(CRBigDecimal.ZERO);
			defaultPayment.setMoney(DEFAULTVALUES.getDefaultMoney());
			defaultPayment.setName("");
			defaultPayment.setType("");
			defaultPayment.setPayMethod(paymentMethodJPAController
					.getDefaultPaymentMethod(Long.valueOf(APP_DEFAULT_FDP), APP_DEFAULT_CURRENCY));
			DEFAULTVALUES.setDefaultPayment(defaultPayment);
		} catch (Exception ex) {
			response = "Error cargando la forma de pago por defecto";
		}
		return response;
	}

	/**
	 * Method loadDefaultArticle.
	 * 
	 * @return String
	 */
	public String loadDefaultArticle() {
		String response = null;
		try {
			TipodescuentoJpaController td = new TipodescuentoJpaController(getEMFInstance());
			DiscountType discountType = td.getDefault(APP_DISCOUNTTYPE);
			TasaimpuestotipoJpaController taxController = new TasaimpuestotipoJpaController(getEMFInstance());
			Tax tax = taxController.findTasaivaTipo(APP_DEFAULT_TAX_RATE);
			UnidadventaJpaController jpaSaleUnitController = new UnidadventaJpaController(getEMFInstance());
			SaleUnit saleUnit = jpaSaleUnitController.findDefaultSaleUnit(APP_DEFAULT_SALE_UNIT);

			Article art = new Article();
			art.setDeliveryCondition(DeliveryCondition.getDeliveryConditionsHash().get("C"));
			art.setDiscountType(discountType);
			art.setItemCost(CRBigDecimal.ZERO);
			art.setItems(CRBigDecimal.ZERO);
			art.setSaleUnit(saleUnit);
			art.setTax(tax);
			art.setSource(Source.Pos);
			DEFAULTVALUES.setDefaultArticle(art);
		} catch (Exception ex) {
			ex.printStackTrace();
			response = "Error cargando el articulo por defecto";
		}
		return response;
	}

	/**
	 * @param props
	 *            the props to set
	 */
	public void setProps(Properties props) {
		this.props = props;
	}

	/**
	 * 
	 * @return the props
	 */
	public Properties getProps() {
		return props;
	}

	/**
	 * @param servicesType
	 *            the servicesType to set
	 */
	public void setServicesType(HashMap<String, String> servicesType) {
		this.servicesType = servicesType;
	}

	/**
	 * 
	 * @return the servicesType
	 */
	public HashMap<String, String> getServicesType() {
		return servicesType;
	}

	/**
	 * @param serviceRMI
	 *            the serviceRMI to set
	 */
	public void setServiceRMI(HashMap<Integer, Vector<String>> serviceRMI) {
		this.serviceRMI = serviceRMI;
	}

	public void setServiceREST(HashMap<Integer, Vector<String>> serviceREST) {
		this.serviceREST = serviceREST;
	}

	/**
	 * 
	 * @return the serviceRMI
	 */
	public HashMap<Integer, Vector<String>> getServiceRMI() {
		return serviceRMI;
	}

	public HashMap<Integer, Vector<String>> getServiceREST() {
		return serviceREST;
	}

	/**
	 * 
	 * @return the dimButtons
	 */
	public Dimension getDimDialogButtons() {
		return dimDialogButtons;
	}

	/**
	 * 
	 * @return the dimSmallButtons
	 */
	public Dimension getDimSmallButtons() {
		return dimSmallButtons;
	}

	/**
	 * 
	 * @return the dimInputs
	 */
	public Dimension getDimInputs() {
		return dimInputs;
	}

	/**
	 * Method getDimSquareInputs.
	 * 
	 * @return Dimension
	 */
	public Dimension getDimSquareInputs() {
		return dimSquareButtons;
	}

	/**
	 * Method getDimBigButtons.
	 * 
	 * @return Dimension
	 */
	public Dimension getDimBigButtons() {
		return dimBigButtons;
	}

	/**
	 * @param paymentMethodType
	 *            the paymentMethodType to set
	 */
	public void setPaymentMethodType(HashMap<String, String> paymentMethodType) {
		this.paymentMethodType = paymentMethodType;
	}

	/**
	 * 
	 * @return the paymentMethodType
	 */
	public HashMap<String, String> getPaymentMethodType() {
		return paymentMethodType;
	}

	/**
	 * Field IDSTIPOSDEDOCUMENTOS. (value is ""134"")
	 */
	public static final String EXENCIONBYCLIENTTYPE = "167";

	/**
	 * Field CLIENT_PASSPORT_PREFIX. (value is ""168"")
	 */
	public static final String CLIENT_PASSPORT_PREFIX = "169";

	/**
	 * Field FORMAT_TAXPAYER_ID. (value is ""170"")
	 */
	public static final String FORMAT_TAXPAYER_ID = "170";

	/**
	 * Id de la opcion que tienen la cantidad maxima de billetes a usar en una
	 * entrega parcial.
	 */
	public static final String MAX_BILLS_LENGTH = "171";

	/**
	 * Field TIPOPROCESOCONSUMOACREENCIASREGALO. (value is ""172"")
	 */
	public static final String TIPOPROCESOCONSUMOACREENCIASREGALO = "172";

	/**
	 * Field TIPOPROCESOCONSUMOACREENCIASREGALO. (value is ""173"")
	 */
	public static final String TIPOPROCESODEPOSITOACREENCIASREGALO = "173";

	/**
	 * Id de la opcion que determina cuanto sera el maximo de caracteres por linea
	 * para la impresion del monto en palabras para los cheques.
	 */
	public static final String MAX_CHECK_AMOUNT_WORDS_LENGTH = "174";

	public HashMap<String, Object> getPropertiesPos() {
		return Conf.propertiesPos;
	}

	public HashMap<String, Object> getProperties400() {
		return Conf.properties400;
	}

	public static String getDbUrl() {
		return Conf.dbUrl;
	}

	public static void setDbUrl(String dbUrl) {
		Conf.dbUrl = dbUrl;
	}

	public static String getPosDbUrl() {
		return Conf.posDbUrl;
	}

	public static void setPosDbUrl(String posDbUrl) {
		Conf.posDbUrl = posDbUrl;
	}

	public static String getServerIP() {
		return Conf.serverIP;
	}

	public static void setServerIP(String serverIP) {
		Conf.serverIP = serverIP;
	}

	public static String getUsernameDB() {
		return Conf.usernameDB;
	}

	public static void setUsernameDB(String usernameDB) {
		Conf.usernameDB = usernameDB;
	}

	public static String getPasswordDB() {
		return Conf.passwordDB;
	}

	public static void setPasswordDB(String passwordDB) {
		Conf.passwordDB = passwordDB;
	}

	public static long getPosTypeOptionID() {
		return Conf.posTypeOptionID;
	}

	public static void setPosTypeOptionID(long posTypeOptionID) {
		Conf.posTypeOptionID = posTypeOptionID;
	}

	public static long getPosNumberOptionID() {
		return Conf.posNumberOptionID;
	}

	public static void setPosNumberOptionID(long posNumberOptionID) {
		Conf.posNumberOptionID = posNumberOptionID;
	}

	public static boolean isExecuteInitialSync() {
		return Conf.executeInitialSync;
	}

	public static void setExecuteInitialSync(boolean executeInitialSync) {
		Conf.executeInitialSync = executeInitialSync;
	}

	public static int getBulkSize() {
		return bulkSize;
	}

	public static void setBulkSize(int bulkSize) {
		Conf.bulkSize = bulkSize;
	}

	public static String getDbname() {
		return Conf.dbname;
	}

	public static void setDbname(String dbname) {
		Conf.dbname = dbname;
	}

	public static String getPosDbname() {
		return Conf.posDbname;
	}

	public static void setPosDbname(String posDbname) {
		Conf.posDbname = posDbname;
	}

	public static String getPosIP() {
		return Conf.posIP;
	}

	public static void setPosIP(String posIP) {
		Conf.posIP = posIP;
	}

	public static String getPosUsernameDB() {
		return Conf.posUsernameDB;
	}

	public static void setPosUsernameDB(String posUsernameDB) {
		Conf.posUsernameDB = posUsernameDB;
	}

	public static String getPosPasswordDB() {
		return Conf.posPasswordDB;
	}

	public static void setPosPasswordDB(String posPasswordDB) {
		Conf.posPasswordDB = posPasswordDB;
	}

	public static String getDriverconnection() {
		return Conf.driverconnection;
	}

	public static void setDriverconnection(String driverconnection) {
		Conf.driverconnection = driverconnection;
	}

	public static String getUrlconnection() {
		return Conf.urlconnection;
	}

	public static void setUrlconnection(String urlconnection) {
		Conf.urlconnection = urlconnection;
	}

}
