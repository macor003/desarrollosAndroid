package com.grid.ventas.cr.refundrestclient.client.dao;

import com.becoblohm.cr.models.Order;
import com.becoblohm.cr.models.Session;

public class RequestLastOrderDepositDao {
    private final Order data;
    private final Session session;

    public RequestLastOrderDepositDao() {
        data = null;
        session = null;
    }

    public RequestLastOrderDepositDao(Order data, Session session) {
        this.data = data;
        this.session = session;
    }

    public Order getData() {
        return data;
    }

    public Session getSession() {
        return session;
    }
}
