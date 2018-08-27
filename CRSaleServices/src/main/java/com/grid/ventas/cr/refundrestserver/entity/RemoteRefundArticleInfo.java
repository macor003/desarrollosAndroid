package com.grid.ventas.cr.refundrestserver.entity;

import com.becoblohm.cr.types.CRBigDecimal;

/**
 * Created by eve0017280 on 07/04/16.
 */
public class RemoteRefundArticleInfo {

    private Long id;

    private CRBigDecimal items;

    public RemoteRefundArticleInfo(Long id, CRBigDecimal items) {
        this.id = id;
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public CRBigDecimal getItems() {
        return items;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setItems(CRBigDecimal items) {
        this.items = items;
    }
}
