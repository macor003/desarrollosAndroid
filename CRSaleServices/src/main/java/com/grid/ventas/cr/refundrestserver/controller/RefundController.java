package com.grid.ventas.cr.refundrestserver.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import javax.persistence.EntityManagerFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.becoblohm.cr.models.AnulDev;
import com.becoblohm.cr.models.Article;
import com.becoblohm.cr.models.Client;
import com.becoblohm.cr.models.Transaction;
import com.becoblohm.cr.models.Transaction.SourceTransactionType;
import com.becoblohm.cr.models.TransactionSessionWrapper;
import com.becoblohm.cr.net.models.Session;
import com.becoblohm.cr.net.response.RMIServerResponse;
import com.becoblohm.cr.net.response.ServicesResponse;
import com.becoblohm.cr.response.ServicesAnulDevResponse;
import com.grid.ventas.cr.refundrestserver.conf.RestFormatter;
import com.grid.ventas.cr.refundrestserver.conf.ServicesConf;
import com.grid.ventas.cr.refundrestserver.service.RefundService;
import com.grid.ventas.cr.refundrestserver.service.RemoteSaleService;

@RestController
@RequestMapping("/refund")
public class RefundController {

    final static Logger logger = LoggerFactory.getLogger(RefundController.class);

    @Autowired
    RefundService refundService;

    @Autowired
    EntityManagerFactory entityManagerFactory;

    @Autowired
    ServicesConf servicesConf;

    /*
     * @Autowired RuleEngine checkServicesRefundRuleEngine;
     */

    @Autowired
    private RestFormatter restFormatter;

    @Autowired
    private RemoteSaleService remoteSaleService;

    @RequestMapping("/changeDevStatus")
    public AnulDev changeDevStatus(AnulDev dev, String fromStatus, String toStatus) {
        logger.debug("Recibiendo peticion de /changeDevStatus");
        AnulDev anulDev = refundService.changeDevStatus(dev, fromStatus, toStatus);
        restFormatter.avoidRecursionAnulDev(anulDev);
        return anulDev;
    }

    @RequestMapping("/checkServicesRefundCompleted")
    public ServicesResponse checkServicesRefundCompleted(@RequestBody AnulDev data) {
        logger.debug("Recibiendo peticion de /checkServicesRefundCompleted");
        boolean valid = true;
        if (servicesConf.isUpdateStatusRemoteRefundActive()) {
            if (data != null && data.getOriginalSale() != null) {
                // Transaccion remota
                if (Integer.parseInt(data.getOriginalSale().getStore()) != servicesConf.getStoreNumber()) {
                    // Es valido realizar la devolucion (evitar duplicados)
                    valid = remoteSaleService.sendUpdateRemoteRefundRequest(data);
                }
            }
        }
        ServicesResponse response = null;
        if (valid) {
            response = refundService.checkServicesRefundCompleted(data);
        }
        if (response != null) {
            AnulDev anulDev = (AnulDev) response.getData();
            restFormatter.avoidRecursionAnulDev(anulDev);
        }
        return response;
    }

    @RequestMapping("/checkServicesRefund")
    public ServicesAnulDevResponse checkServicesRefund(@RequestBody TransactionSessionWrapper transactionSessionWrapper)
            throws Exception {
        logger.debug("Recibiendo peticion de /checkServicesRefund");
        ServicesResponse servicesResponse = checkServicesRefund(transactionSessionWrapper.getTransaction(),
                                                                transactionSessionWrapper.getSession());
        AnulDev anulDev = (AnulDev) servicesResponse.getData();
        restFormatter.avoidRecursionAnulDev(anulDev);
        return new ServicesAnulDevResponse(servicesResponse.getMsg(), anulDev, servicesResponse.getCode());

    }

    public ServicesResponse checkServicesRefund(Transaction subject, Session session) {
        return refundService.checkServicesRefund(subject, session);
    }

    @RequestMapping("/t")
    public ServicesAnulDevResponse test(@RequestBody ServicesAnulDevResponse servicesAnulDevResponse) {
        return servicesAnulDevResponse;
    }

    @RequestMapping("/checkServicesRefundInProcess")
    public RMIServerResponse checkServicesRefundInProcess(@RequestBody AnulDev data) {
        logger.debug("Recibiendo peticion de /checkServicesRefundInProcess");

        RMIServerResponse rmiServerResponse = refundService.checkServicesRefundInProcess(data);
        AnulDev anulDev = (AnulDev) rmiServerResponse.getData();
        restFormatter.avoidRecursionAnulDev(anulDev);
        return rmiServerResponse;

    }

    @RequestMapping("/executeDevCompleted")
    public AnulDev executeDevCompleted(AnulDev dev) {
        return refundService.executeDevCompleted(dev);
    }

    @RequestMapping("/executeDevInProcess")
    public AnulDev executeDevInProcess(AnulDev dev) {
        return refundService.executeDevInProcess(dev);
    }

    @RequestMapping("/getRefunds")
    public Collection<AnulDev> getRefunds(Client client, Session session) {
        logger.debug("Recibiendo peticion de /getRefunds");
        Collection<AnulDev> resultanudev = new ArrayList<AnulDev>();

        if (servicesConf.isNewRefundTable()) {
            resultanudev = refundService.getRefundsFromNewTables(client, session);
        } else {
            resultanudev = refundService.getRefunds(client, session);
        }

        return resultanudev;
    }

    @RequestMapping("/rr")
    public AnulDev refund() {

        AnulDev anulDev = new AnulDev();
        Transaction os = new Transaction();
        os.setStore("03");
        os.setPosId("13");
        os.setNumber("152730");

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(0);
        int dia = 29;
        int mes = 3;
        int ano = 2016;
        cal.set(ano, mes, dia);
        Date date = cal.getTime();

        os.setDate(date);
        anulDev.setOriginalSale(os);
        anulDev.setStore("13");
        Collection<Article> arts = new ArrayList<Article>();
        
        Article art = new Article();        
        art.setId(202002l);
        art.setCode("0223184");
        arts.add(art);

        art = new Article();
        art.setId(202002l);
        art.setCode("0273010");
        arts.add(art);

        anulDev.setArticles(arts);

        anulDev.setDate(date);

        Session session = new Session();
        session.setPosId("013");
        session.setStoreId("03");
        return anulDev;

    }

    @RequestMapping("/testrefund")
    public ServicesAnulDevResponse testRefund() {

        Transaction trans = new Transaction();
        Session ses = new Session();

        trans.setStore("3");
        trans.setNumber("32588");
        trans.setPosId("1");
        trans.setTransactionType(SourceTransactionType.Refund);

        ses.setStoreId("3");

        ServicesResponse servicesResponse = checkServicesRefund(trans, ses);
        AnulDev anulDev = (AnulDev) servicesResponse.getData();
        restFormatter.avoidRecursionAnulDev(anulDev);
        return new ServicesAnulDevResponse(servicesResponse.getMsg(), anulDev, servicesResponse.getCode());
    }

    @RequestMapping("/testrefundbyclient")
    public Collection<AnulDev> testRefundByClient() {

        Client client = new Client();
        Session ses = new Session();

        client.setIdNumber("5215451-3");
        ses.setStoreId("3");

        return refundService.getRefundsFromNewTables(client, ses);
    }

    @RequestMapping("/testupdatestatus")
    public AnulDev testUpdateStatus() {

        AnulDev dev = new AnulDev();
        dev.getOriginalSale().setStore("3");
        dev.getOriginalSale().setNumber("32588");
        dev.getOriginalSale().setPosId("1");
        
        Collection<Article> arts = new ArrayList<Article>();
        Article art = new Article();
        art.setId(1251408l);
        art.setCode("0253028");
        arts.add(art);
        
        dev.setArticles(arts);       
        

        return refundService.executeDevInProcess(dev);
    }

    @RequestMapping("/tsw")
    TransactionSessionWrapper generateTransactionSessionWrapper() {
        TransactionSessionWrapper transactionSessionWrapper = new TransactionSessionWrapper();
        Session session = new Session();
        transactionSessionWrapper.setSession(session);
        AnulDev transaction = new AnulDev();
        transactionSessionWrapper.setTransaction(transaction);
        return transactionSessionWrapper;
    }

    /*
     * @RequestMapping("/ping")
     * @RequestMapping("/ping(@RequestParam") public Ping ping(@RequestParam(value =
     * "name", defaultValue = "World") String name) { return new
     * Ping(counter.incrementAndGet(), String.format(template, name)); }
     * @RequestMapping(value = "/c", method = RequestMethod.POST)
     * @RequestMapping("/c") public ServicesResponse c(@RequestBody ServicesResponse
     * servicesResponse) { //System.out.println("servicesResponse = " + servicesResponse);
     * return servicesResponse; }
     */

    /*
     * @RequestMapping("/changeDevStatus") public AnulDev changeDevStatus(AnulDev dev,
     * String fromStatus, String toStatus) { }
     * @RequestMapping("/checkServicesRefundCompleted") public ServicesResponse
     * checkServicesRefundCompleted(AnulDev data) { }
     * @RequestMapping("/checkServicesRefund") public ServicesResponse
     * checkServicesRefund(Transaction subject, com.becoblohm.cr.net.models.Session
     * session) { }
     * @RequestMapping("/checkServicesRefundInProcess") public RMIServerResponse
     * checkServicesRefundInProcess(AnulDev data) { }
     * @RequestMapping("/executeDevCompleted") public AnulDev executeDevCompleted(AnulDev
     * dev) { }
     * @RequestMapping("/executeDevInProcess") public AnulDev executeDevInProcess(AnulDev
     * dev) { }
     * @RequestMapping("/getRefunds") public Collection<AnulDev> getRefunds(Client client,
     * Session session) { }
     */
}
