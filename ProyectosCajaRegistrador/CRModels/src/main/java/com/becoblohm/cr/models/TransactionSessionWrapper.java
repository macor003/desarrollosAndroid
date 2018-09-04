package com.becoblohm.cr.models;

import com.becoblohm.cr.net.models.Session;

/**
 * Created by eve0017280 on 26/02/16.
 */
public class TransactionSessionWrapper {

    private Transaction transaction;

    private Session session;

    public TransactionSessionWrapper() {
    }

    public TransactionSessionWrapper(Transaction transaction, Session session) {
        this.transaction = transaction;
        this.session = session;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
