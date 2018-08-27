package com.grid.ventas.cr.refundrestclient.client.dao;

public class SearchLastNumberTCCabvptcDao {
    private final String storeId;
    private final String posId;

    public SearchLastNumberTCCabvptcDao() {
        storeId = null;
        posId = null;
    }

    public SearchLastNumberTCCabvptcDao(String storeId, String posId) {
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
