package com.grid.ventas.cr.refundrestserver.controller;

import java.util.Arrays;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.becoblohm.cr.models.Article;
import com.becoblohm.cr.models.Session;
import com.becoblohm.cr.models.Transaction;
import com.becoblohm.cr.models.User;
import com.becoblohm.cr.net.response.RMIServerResponse;
import com.becoblohm.cr.response.FindWaitingSaleResponse;
import com.grid.ventas.cr.refundrestclient.client.dao.HoldWaitingSaleDao;
import com.grid.ventas.cr.refundrestserver.service.WaitingSaleService;

/**
 * Created by eve0017280 on 15/02/16.
 */
@RestController
@RequestMapping("/waitingSale")
public class WaitingSaleController {

    final static Logger logger = LoggerFactory.getLogger(SpecialOrderController.class);

    @Autowired
    private WaitingSaleService waitingSaleService;

    private static boolean enable = true;

    @RequestMapping("/toggleService")
    public RMIServerResponse toggleService() {
        enable = !enable;
        return new RMIServerResponse("", enable, HttpServletResponse.SC_OK);
    }

    @RequestMapping("/findWaitingSale")
    public FindWaitingSaleResponse findWaitingSale() {
        FindWaitingSaleResponse response = new FindWaitingSaleResponse();
        if (enable) {
            response = waitingSaleService.findWaitingSale();
        } else {
            response.setMsg("");
            response.setData(null);
            response.setCode(HttpServletResponse.SC_FORBIDDEN);
        }
        return response;
    }

    @RequestMapping("/releaseWaitingSale")
    public RMIServerResponse releaseWaitingSale(@RequestBody Transaction tr) {
        RMIServerResponse response = new RMIServerResponse("", null);
        if (enable) {
            response = waitingSaleService.releaseWaitingSale(tr);
        } else {
            response.setCode(HttpServletResponse.SC_FORBIDDEN);
        }
        return response;
    }

    @RequestMapping("/holdWaitingSale")
    public RMIServerResponse holdWaitingSale(@RequestBody HoldWaitingSaleDao holdWaitingSaleDao) {
        RMIServerResponse response = new RMIServerResponse("", null);
        if (enable) {
            Transaction transaction = holdWaitingSaleDao.getData();
            Session session = holdWaitingSaleDao.getSession();
            response = waitingSaleService.holdWaitingSale(transaction, session);
        } else {
            response.setCode(HttpServletResponse.SC_FORBIDDEN);
        }
        return response;
    }

    @RequestMapping("/getTransactionAudit")
    public RMIServerResponse getTransactionAudit(Long tr) {
        RMIServerResponse response = new RMIServerResponse("", null);
        if (enable) {
            response = waitingSaleService.getTransactionAudit(tr);
        } else {
            response.setCode(HttpServletResponse.SC_FORBIDDEN);
        }
        return response;
    }

    @RequestMapping("/testwaitingsale")
    public FindWaitingSaleResponse testWaitingSale() {
        
        return waitingSaleService.findWaitingSale();
    }

    @RequestMapping("/p")
    public HoldWaitingSaleDao holdWaitingSale() {
        Transaction data = new Transaction();
        data.setSaleOrigin(Transaction.Source.Pos);
        Article article = new Article();
        article.setCode("202000");
        article.setId(1090112l);
        data.setArticles(Arrays.asList(article));
        data.setPrinterSerial("21");
        Session session = new Session();
        User cashier = new User();
        cashier.setId(202l);
        session.setPosId("90");
        session.setCashier(cashier);
        return new HoldWaitingSaleDao(data, session);
    }

    @RequestMapping("/p2")
    public HoldWaitingSaleDao holdWaitingSale2(@RequestBody HoldWaitingSaleDao holdWaitingSaleDao) {
        HoldWaitingSaleDao holdWaitingSaleDao1 = holdWaitingSaleDao;
        return holdWaitingSaleDao1;
    }
}
