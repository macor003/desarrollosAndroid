package com.grid.ventas.cr.refundrestclient.client.dao;

import com.becoblohm.cr.models.Client;
import com.becoblohm.cr.net.models.Session;

public class RequestSpecialOrderDao {
    private final Client data;
    private final Session session;

    public RequestSpecialOrderDao() {
        data = null;
        session = null;
    }

    public RequestSpecialOrderDao(Client data, Session session) {
        this.data = data;
        this.session = session;
    }

    public Client getData() {
        return data;
    }

    public Session getSession() {
        return session;
    }
}
