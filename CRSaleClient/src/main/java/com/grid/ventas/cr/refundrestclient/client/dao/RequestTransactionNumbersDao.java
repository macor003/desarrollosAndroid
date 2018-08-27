package com.grid.ventas.cr.refundrestclient.client.dao;

import com.becoblohm.cr.models.Session;

public class RequestTransactionNumbersDao {
    private final Long data;
    private final Session session;

    public RequestTransactionNumbersDao() {
        data = null;
        session = null;
    }

    public RequestTransactionNumbersDao(Long data, Session session) {
        this.data = data;
        this.session = session;
    }

    public Long getData() {
        return data;
    }

    public Session getSession() {
        return session;
    }
}
