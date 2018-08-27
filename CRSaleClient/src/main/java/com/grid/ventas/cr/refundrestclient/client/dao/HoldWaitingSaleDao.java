package com.grid.ventas.cr.refundrestclient.client.dao;

import com.becoblohm.cr.models.Session;
import com.becoblohm.cr.models.Transaction;

public class HoldWaitingSaleDao {
    private Transaction data;
    private Session session;

    public HoldWaitingSaleDao() {
        session = null;
        data = null;
    }

    public HoldWaitingSaleDao(Transaction data, Session session) {
        this.data = data;
        this.session = session;
    }

    public Transaction getData() {
        return data;
    }

    public Session getSession() {
        return session;
    }

    public void setData(Transaction data) {
        this.data = data;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
