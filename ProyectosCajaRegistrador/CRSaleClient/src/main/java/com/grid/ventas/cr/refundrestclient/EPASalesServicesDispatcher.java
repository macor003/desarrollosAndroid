/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.grid.ventas.cr.refundrestclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.SSLContext;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.AllowAllHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import com.becoblohm.cr.conf.Global;
import com.becoblohm.cr.models.AnulDev;
import com.becoblohm.cr.models.Client;
import com.becoblohm.cr.models.Deposit;
import com.becoblohm.cr.models.Order;
import com.becoblohm.cr.models.Session;
import com.becoblohm.cr.models.Transaction;
import com.becoblohm.cr.models.TransactionSessionWrapper;
import com.becoblohm.cr.net.request.RMIServerRequest;
import com.becoblohm.cr.net.request.ServicesRequest;
import com.becoblohm.cr.net.response.RMIServerResponse;
import com.becoblohm.cr.net.response.ServicesResponse;
import com.grid.ventas.cr.refundrestclient.client.dao.HoldWaitingSaleDao;
import com.grid.ventas.cr.refundrestclient.client.dao.RequestLastOrderDepositDao;
import com.grid.ventas.cr.refundrestclient.client.dao.RequestOrderIdDao;
import com.grid.ventas.cr.refundrestclient.client.dao.RequestSpecialOrderDao;
import com.grid.ventas.cr.refundrestclient.client.dao.RequestTransactionNumbersDao;
import com.grid.ventas.cr.refundrestclient.client.dao.SearchLastNumberTCCabvptcDao;
import com.grid.ventas.cr.refundrestclient.client.dao.SearchLastTransactionTrcmov;

/**
 * Created by eve0017280 on 11/02/16.
 */
public class EPASalesServicesDispatcher {

	private static final Logger logger = LoggerFactory.getLogger(EPASalesServicesDispatcher.class);

	private static EPASalesServicesDispatcher epaSalesServicesDispatcher = new EPASalesServicesDispatcher(true, 75, 90);

	private RestTemplate restTemplate = new RestTemplate();

	private static Map<Integer, String> requestMapper = new HashMap<Integer, String>(16);

	static {
		requestMapper.put(ServicesRequest.PING, "/admin/ping?ping=1024");
		requestMapper.put(ServicesRequest.RESTART, "/admin/restart?restart=1016");
		requestMapper.put(ServicesRequest.REFUND, "/cancellation/searchCancel");
		requestMapper.put(ServicesRequest.CANCELLATION, "/cancellation/searchCancel");
		requestMapper.put(ServicesRequest.IS_CANCELED, "/refund/checkServicesRefund");
		requestMapper.put(ServicesRequest.CLIENT_ORDER, "/clientOrder/checkServicesClientOrder");
		requestMapper.put(ServicesRequest.DELETE_CLIENTORDER, "/clientOrder/deleteServicesClientOrder");
		requestMapper.put(ServicesRequest.REFUND_COMPLETED, "/refund/checkServicesRefundCompleted");
		requestMapper.put(ServicesRequest.REFUND_IN_PROCESS, "/refund/checkServicesRefundInProcess");
		requestMapper.put(ServicesRequest.WAITING_SALE_FIND, "/waitingSale/findWaitingSale");
		requestMapper.put(ServicesRequest.WAITING_SALE_RELEASE, "/waitingSale/releaseWaitingSale");
		requestMapper.put(ServicesRequest.WAITING_SALE_GETAUDIT, "/waitingSale/getTransactionAudit");
		requestMapper.put(ServicesRequest.WAITING_SALE_HOLD, "/waitingSale/holdWaitingSale");
		requestMapper.put(ServicesRequest.ORDERS_REQUEST, "/specialOrder/requestSpecialOrder");
		requestMapper.put(ServicesRequest.ORDERS_REGIST_DEPOSIT, "/specialOrder/registerOperationDeposit");
		requestMapper.put(ServicesRequest.ORDERS_UPDATE_STATUS, "/specialOrder/updateSpecialOrderStatus");
		requestMapper.put(ServicesRequest.REGISTER_CREDIT_PAYMENT, "/specialOrder/requestLastOrderDeposit");
		requestMapper.put(ServicesRequest.ANUL_CREDIT_PAYMENT, "/specialOrder/requestLastOrderDeposit");
		requestMapper.put(ServicesRequest.REPRINT_ORDERS, "/specialOrder/requestLastOrderDeposit");
		requestMapper.put(ServicesRequest.ORDERS_GET_ID, "/specialOrder/requestOrderId");
		requestMapper.put(ServicesRequest.ORDERS_GET_TRANSACTION_NUMBERS, "/specialOrder/requestTransactionNumbers");
		requestMapper.put(ServicesRequest.GET_LAST_TR_NUMBER, "/sale/searchLastTransactionTrcmov");
		requestMapper.put(ServicesRequest.GET_LAST_PAY_CREDIT, "/sale/searchLastNumberTCCabvptc");
		requestMapper.put(ServicesRequest.CHECK_SERVICES_REFUND, "/refund/checkServicesRefund");
		requestMapper.put(ServicesRequest.CHECK_SERVICES_REFUND, "/refund/checkServicesRefund");
		requestMapper.put(ServicesRequest.ORIGINAL_SALE, "/sale/searchOriginSale");
		requestMapper.put(ServicesRequest.UPDATE_REMOTE_REFUND, "/remotesale/updateRemoteDefund");
	}

	private boolean ssl;

	private int readTimeoutSeconds = 180;

	private int connectTimeoutSeconds = 180;

	public EPASalesServicesDispatcher(boolean ssl, int readTimeoutSeconds, int connectTimeoutSeconds) {

		this.ssl = sslFromFile();
		this.readTimeoutSeconds = readTimeoutSeconds;
		this.connectTimeoutSeconds = connectTimeoutSeconds;
		if (this.ssl) {
			SSLContext sslContext = null;
			try {
				sslContext = SSLContexts.custom().loadTrustMaterial(null, new TrustSelfSignedStrategy()).useTLS()
						.build();
			} catch (NoSuchAlgorithmException e) {
				logger.error(e.getMessage());
			} catch (KeyManagementException e) {
				logger.error(e.getMessage());
			} catch (KeyStoreException e) {
				logger.error(e.getMessage());
			}
			SSLConnectionSocketFactory connectionFactory = new SSLConnectionSocketFactory(sslContext,
					new AllowAllHostnameVerifier());
			HttpClient httpClient = HttpClientBuilder.create().setSslcontext(sslContext)
					.setSSLSocketFactory(connectionFactory).build();

			final ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
			restTemplate = new RestTemplate(requestFactory);

			restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {

				@Override
				public void handleError(ClientHttpResponse response) throws IOException {
					logger.error("Error -> " + response.getRawStatusCode(), response);
				}
			});
		}
	}

	private boolean sslFromFile() {
		boolean active = false;
		try {
			// From ClassLoader, all paths are "absolute" already - there's no
			// context
			// from which they could be relative. Therefore you don't need a
			// leading slash.
			InputStream in = this.getClass().getClassLoader().getResourceAsStream("HTTPSecurity.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String read;
			while ((read = br.readLine()) != null) {
				read = read.trim();
				if (read.contains("ssl=")) {
					String value = read.substring(read.indexOf('=') + 1, read.length());
					if ("true".equals(value)) {
						active = true;
					}
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return active;
	}

	public RMIServerResponse dispatch(RMIServerRequest rmiServerRequest, String ip, String service, Class clazz)
			throws Exception {

		RMIServerResponse result = null;

		String address = buildAddress(rmiServerRequest.getType(), ip, service);
		// debug("Enviando peticion a " + address);

		switch (rmiServerRequest.getType()) {
		case ServicesRequest.PING:
			String response = genericGet(address, ServicesRequest.PING + "");
			RMIServerResponse rmiServerResponse = new RMIServerResponse();
			rmiServerResponse.setMsg(response);
			result = rmiServerResponse;
			break;
		case ServicesRequest.RESTART:
			debug("Enviando peticion a " + address);
			String response1 = genericGet(address, ServicesRequest.RESTART + "");
			RMIServerResponse rmiServerResponse1 = new RMIServerResponse();
			rmiServerResponse1.setMsg(response1);
			result = rmiServerResponse1;
			break;
		case ServicesRequest.REFUND:
		case ServicesRequest.CANCELLATION:
			debug("Enviando peticion a " + address);
			// Se valida si la transaccion fue anulada anteriormente
			// TODO
			Transaction transaction = (Transaction) rmiServerRequest.getData();
			result = genericPost(transaction, ServicesResponse.class, address);
			// result = cancellationClient.searchCancel(data, address);
			// Si no ha sido cancelada previamente la transaccion
			if (result.getCode() != ServicesRequest.IS_CANCELED) {
				debug("fecha -----> " + transaction.getDate());
				com.becoblohm.cr.net.models.Session session = rmiServerRequest.getSession();
				address = buildAddress(ServicesRequest.CHECK_SERVICES_REFUND, ip, service);
				TransactionSessionWrapper transactionSessionWrapper = new TransactionSessionWrapper(transaction,
						session);
				result = genericPost(transactionSessionWrapper, clazz, address);
				// result = refundClient.checkServicesRefund((Transaction)
				// rmiServerRequest.getData(), rmiServerRequest.getSession(),
				// address);
			}
			break;
		case ServicesRequest.CLIENT_ORDER:
			debug("Enviando peticion a " + address);
			Client client = (Client) rmiServerRequest.getData();
			// result = clientOrderClient.checkServicesClientOrder(client,
			// address);
			result = genericPost(client, ServicesResponse.class, address);
			break;
		case ServicesRequest.DELETE_CLIENTORDER:
			debug("Enviando peticion a " + address);
			Client client1 = (Client) rmiServerRequest.getData();
			result = genericPost(client1, ServicesResponse.class, address);
			// result = clientOrderClient.deleteServicesClientOrder(client1,
			// address);
			break;
		case ServicesRequest.REFUND_COMPLETED:
			debug("Enviando peticion a " + address);
			AnulDev anulDev = (AnulDev) rmiServerRequest.getData();
			// result = refundClient.checkServicesRefundCompleted(anulDev,
			// address);
			result = genericPost(anulDev, ServicesResponse.class, address);
			break;
		case ServicesRequest.REFUND_IN_PROCESS:
			debug("Enviando peticion a " + address);
			AnulDev anulDev1 = (AnulDev) rmiServerRequest.getData();
			result = genericPost(anulDev1, ServicesResponse.class, address);
			// result = refundClient.checkServicesRefundInProcess(anulDev1,
			// address);
			break;
		case ServicesRequest.WAITING_SALE_FIND:
			debug("Enviando peticion a " + address);
			// result = waitingSaleClient.findWaitingSale(address);
			String aux = "";
			result = genericPost(aux, clazz, address);
			break;
		case ServicesRequest.WAITING_SALE_RELEASE:
			debug("Enviando peticion a " + address);
			Transaction transaction1 = (Transaction) rmiServerRequest.getData();
			// result = waitingSaleClient.releaseWaitingSale(transaction1,
			// address);
			result = genericPost(transaction1, ServicesResponse.class, address);
			break;
		case ServicesRequest.WAITING_SALE_GETAUDIT:
			debug("Enviando peticion a " + address);
			Long aLong = (Long) rmiServerRequest.getData();
			// result = waitingSaleClient.getTransactionAudit(aLong, address);
			result = genericPost(aLong, ServicesResponse.class, address);

			break;
		case ServicesRequest.WAITING_SALE_HOLD:
			debug("Enviando peticion a " + address);
			debug("poniendo en espera");
			Transaction transaction2 = (Transaction) rmiServerRequest.getData();
			HoldWaitingSaleDao holdWaitingSaleDao = new HoldWaitingSaleDao(transaction2,
					new Session(rmiServerRequest.getSession()));
			result = genericPost(holdWaitingSaleDao, ServicesResponse.class, address);
			break;
		case ServicesRequest.ORDERS_REQUEST:
			// result = new SpecialOrderResponse("", null);
			Client client2 = (Client) rmiServerRequest.getData();
			RequestSpecialOrderDao requestSpecialOrderDao = new RequestSpecialOrderDao(client2,
					rmiServerRequest.getSession());
			// result =
			// specialOrderClient.requestSpecialOrder(requestSpecialOrderDao,
			// address);
			result = genericPost(requestSpecialOrderDao, clazz, address);
			break;
		case ServicesRequest.ORDERS_REGIST_DEPOSIT:
			debug("Enviando peticion a " + address);
			// result = new SpecialOrderResponse("", null);
			Deposit deposit = (Deposit) rmiServerRequest.getData();
			// result = specialOrderClient.registerOperationDeposit(deposit,
			// address);
			result = genericPost(deposit, ServicesResponse.class, address);
			break;
		case ServicesRequest.ORDERS_UPDATE_STATUS:
			debug("Enviando peticion a " + address);
			// result = new SpecialOrderResponse("", null);
			Order order = (Order) rmiServerRequest.getData();
			// result = specialOrderClient.updateSpecialOrderStatus(order,
			// address);
			result = genericPost(order, clazz, address);
			break;
		case ServicesRequest.REGISTER_CREDIT_PAYMENT:
			break;
		case ServicesRequest.ANUL_CREDIT_PAYMENT:
			break;
		case ServicesRequest.REPRINT_ORDERS:
			debug("Enviando peticion a " + address);
			// result = new SpecialOrderResponse("", null);
			Order order1 = (Order) rmiServerRequest.getData();
			debug("Procesando orden: " + order1.getNumber());
			RequestLastOrderDepositDao requestLastOrderDepositDao = new RequestLastOrderDepositDao(order1,
					new Session(rmiServerRequest.getSession()));
			// result =
			// specialOrderClient.requestLastOrderDeposit(requestLastOrderDepositDao,
			// address);
			result = genericPost(requestLastOrderDepositDao, ServicesResponse.class, address);
			break;
		case ServicesRequest.ORDERS_GET_ID:
			debug("Enviando peticion a " + address);
			// result = new SpecialOrderResponse("", null);
			Transaction transaction3 = (Transaction) rmiServerRequest.getData();
			RequestOrderIdDao requestOrderIdDao = new RequestOrderIdDao(transaction3,
					new Session(rmiServerRequest.getSession()));
			// result = specialOrderClient.requestOrderId(requestOrderIdDao,
			// address);
			debug("Buscando ID de orden: " + transaction3.getNumber());
			result = genericPost(requestOrderIdDao, clazz, address);
			break;
		case ServicesRequest.ORDERS_GET_TRANSACTION_NUMBERS:
			debug("Enviando peticion a " + address);
			// result = new SpecialOrderResponse("", null);
			Long aLong1 = (Long) rmiServerRequest.getData();
			RequestTransactionNumbersDao requestTransactionNumbersDao = new RequestTransactionNumbersDao(aLong1,
					new Session(rmiServerRequest.getSession()));
			// result =
			// specialOrderClient.requestTransactionNumbers(requestTransactionNumbersDao,
			// address);
			result = genericPost(requestTransactionNumbersDao, ServicesResponse.class, address);
			break;
		case ServicesRequest.GET_LAST_TR_NUMBER:
			debug("Enviando peticion a " + address);
			String posId = rmiServerRequest.getSession().getPosId();
			debug("Buscando ultimo numero de transaccion: " + posId);
			String storeId = rmiServerRequest.getSession().getStoreId();
			SearchLastTransactionTrcmov searchLastTransactionTrcmov = new SearchLastTransactionTrcmov(storeId, posId);
			result = genericPost(searchLastTransactionTrcmov, ServicesResponse.class, address);
			// result =
			// saleClient.searchLastTransactionTrcmov(searchLastTransactionTrcmov,
			// address);
			break;
		case ServicesRequest.GET_LAST_PAY_CREDIT:
			debug("Enviando peticion a " + address);
			String posId1 = rmiServerRequest.getSession().getPosId();
			debug("Buscando ultimo pago de tarjeta de credito: " + posId1);
			String storeId1 = rmiServerRequest.getSession().getStoreId();
			SearchLastNumberTCCabvptcDao searchLastNumberTCCabvptcDao = new SearchLastNumberTCCabvptcDao(storeId1,
					posId1);
			result = genericPost(searchLastNumberTCCabvptcDao, ServicesResponse.class, address);
			// result =
			// saleClient.searchLastNumberTCCabvptc(searchLastNumberTCCabvptcDao,
			// address);
			break;
		case ServicesRequest.ORIGINAL_SALE:
			debug("Enviando peticion a " + address);
			Transaction originalTransaction = (Transaction) rmiServerRequest.getData();
			com.becoblohm.cr.net.models.Session originalSession = rmiServerRequest.getSession();
			TransactionSessionWrapper transactionSessionWrapper = new TransactionSessionWrapper(originalTransaction,
					originalSession);
			result = genericPost(transactionSessionWrapper, clazz, address);
			break;
		case ServicesRequest.UPDATE_REMOTE_REFUND:
			debug("Enviando peticion a " + address);
			AnulDev anulDevR = (AnulDev) rmiServerRequest.getData();
			result = genericPost(anulDevR, clazz, address);
			break;
		default:
			break;
		}
		return result;
	}

	// "http://127.0.0.1:1092/cancellation/searchCancel"
	private RMIServerResponse genericPost(Object object, Class clazz, String address) throws Exception {
		RMIServerResponse body = null;
		try {
			((SimpleClientHttpRequestFactory) restTemplate.getRequestFactory())
					.setReadTimeout(1000 * readTimeoutSeconds);
			((SimpleClientHttpRequestFactory) restTemplate.getRequestFactory())
					.setConnectTimeout(1000 * connectTimeoutSeconds);
			ResponseEntity<ServicesResponse> servicesResponseResponseEntity = restTemplate
					.postForEntity(new URI(address), object, clazz);
			body = servicesResponseResponseEntity.getBody();
		} catch (URISyntaxException e) {
			logger.error("No se puede establecer conexion POSt hacia " + address);
		} catch (Exception e) {
			logger.error("Exception durante la conexion con el servidor " + e + " ERROR ");
			throw e;
		}
		if (body == null) {
			logger.error("Respuesta NULL desde " + address);
			body = (RMIServerResponse) clazz.newInstance();
		}
		return body;
	}

	private String genericGet(String address, String... params) {
		ResponseEntity<String> responseEntity = null;
		try {
			responseEntity = restTemplate.getForEntity(new URI(address), String.class);
		} catch (URISyntaxException e) {
			logger.error("No se puede establecer conexion GET hacia " + address);
		}
		return responseEntity.getBody();
	}

	private String buildAddress(int type, String ip, String service) {
		String postfix = "";
		String address = "";
		if (ssl) {
			postfix = "s";
		}
		String requestMapping = requestMapper.get(type);
		if (!isNumeric(service)) {
			address = "http" + postfix + "://" + ip + ":" + Global.getServicesPorts().get(service) + requestMapping;
		} else {
			address = "http" + postfix + "://" + ip + ":" + service + requestMapping;
		}
		return address;
	}

	public static boolean isNumeric(String str) {
		return str.matches("-?\\d+(\\.\\d+)?");
	}

	private void debug(String msg) {
		if (logger.isDebugEnabled()) {
			logger.debug(msg);
		}
	}

	private void info(String msg) {
		if (logger.isInfoEnabled()) {
			logger.info(msg);
		}
	}

	public static boolean isOnline(final String ip, final String service) {

		logger.info("Verificando que el servicio REST se encuentra en linea");
		RMIServerRequest rmiServerRequest = new RMIServerRequest();
		int ping = ServicesRequest.PING;
		rmiServerRequest.setType(ping);
		String address = "http://" + ip + "/admin/ping?ping=" + ping;
		RMIServerResponse response;
		String pong = null;
		try {
			response = epaSalesServicesDispatcher.dispatch(rmiServerRequest, ip, service, String.class);
			pong = response.getMsg();
			return ping == Integer.parseInt(pong);
		} catch (Exception e) {
			pong = "0";
			logger.error("No se encuentran en linea los servicios REST");
		}
		return false;

	}

}
