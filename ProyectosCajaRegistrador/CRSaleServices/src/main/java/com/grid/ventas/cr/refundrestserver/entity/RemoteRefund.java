package com.grid.ventas.cr.refundrestserver.entity;

import java.util.List;

/**
 * Created by eve0017280 on 05/04/16.
 */
public class RemoteRefund {

    public static final int VALIDATED = 1;

    public static final int REGISTERED = 2;

    private String tienda;

    private String caja;

    private long transaccion;

    private int msgCode;

    private String msg;

    private List<RemoteRefundArticleInfo> remoteRefundArticleInfoList;

    public RemoteRefund(String tienda, String caja, long transaccion) {
        this.tienda = tienda;
        this.caja = caja;
        this.transaccion = transaccion;
    }

    public String getTienda() {
        return tienda;
    }

    public void setTienda(String tienda) {
        this.tienda = tienda;
    }

    public String getCaja() {
        return caja;
    }

    public void setCaja(String caja) {
        this.caja = caja;
    }

    public Long getTransaccion() {
        return transaccion;
    }

    public void setTransaccion(Long transaccion) {
        this.transaccion = transaccion;
    }

    public void setMsgCode(int msgCode) {
        this.msgCode = msgCode;
    }

    public int getMsgCode() {
        return msgCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<RemoteRefundArticleInfo> getRemoteRefundArticleInfoList() {
        return remoteRefundArticleInfoList;
    }

    public void setRemoteRefundArticleInfoList(List<RemoteRefundArticleInfo> remoteRefundArticleInfoList) {
        this.remoteRefundArticleInfoList = remoteRefundArticleInfoList;
    }
}
