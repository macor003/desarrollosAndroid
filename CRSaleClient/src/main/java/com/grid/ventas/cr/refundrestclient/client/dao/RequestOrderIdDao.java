package com.grid.ventas.cr.refundrestclient.client.dao;

import com.becoblohm.cr.models.Session;
import com.becoblohm.cr.models.Transaction;

public class RequestOrderIdDao {
    private final Transaction data;
    private final Session session;

    public RequestOrderIdDao() {
        data = null;
        session = null;
    }

    public RequestOrderIdDao(Transaction data, Session session) {
        this.data = data;
        this.session = session;
    }

    public Transaction getData() {
        return data;
    }

    public Session getSession() {
        return session;
    }
}
