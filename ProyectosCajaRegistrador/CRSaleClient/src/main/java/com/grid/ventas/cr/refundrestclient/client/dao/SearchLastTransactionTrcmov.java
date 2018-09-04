package com.grid.ventas.cr.refundrestclient.client.dao;

public class SearchLastTransactionTrcmov {
    private final String storeId;
    private final String posId;

    public SearchLastTransactionTrcmov() {
        storeId = null;
        posId = null;
    }

    public SearchLastTransactionTrcmov(String storeId, String posId) {
        this.storeId = storeId;
        this.posId = posId;
    }

    public String getStoreId() {
        return storeId;
    }

    public String getPosId() {
        return posId;
    }
}
