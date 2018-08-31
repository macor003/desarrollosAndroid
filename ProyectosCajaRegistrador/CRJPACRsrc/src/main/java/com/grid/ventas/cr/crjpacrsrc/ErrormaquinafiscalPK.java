/*
 * To change this license header, choose License Headers in Project Properties. To change
 * this template file, choose Tools | Templates and open the template in the editor.
 */
package com.grid.ventas.cr.crjpacrsrc;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author eve0017909
 */
@Embeddable
public class ErrormaquinafiscalPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "MAQUINAFISCAL")
    private String maquinafiscal;

    @Basic(optional = false)
    @Column(name = "TIENDA")
    private short tienda;

    @Basic(optional = false)
    @Column(name = "CAJA")
    private short caja;

    @Basic(optional = false)
    @Column(name = "TIPODOCUMENTO")
    private String tipodocumento;

    @Basic(optional = false)
    @Column(name = "NUMCOMPROBANTE")
    private long numcomprobante;

    @Basic(optional = false)
    @Column(name = "SERIE")
    private String serie;

    public ErrormaquinafiscalPK() {
    }

    public ErrormaquinafiscalPK(String maquinafiscal, short tienda, short caja, String tipodocumento,
                                long numcomprobante, String serie) {
        this.maquinafiscal = maquinafiscal;
        this.tienda = tienda;
        this.caja = caja;
        this.tipodocumento = tipodocumento;
        this.numcomprobante = numcomprobante;
        this.serie = serie;
    }

    public String getMaquinafiscal() {
        return maquinafiscal;
    }

    public void setMaquinafiscal(String maquinafiscal) {
        this.maquinafiscal = maquinafiscal;
    }

    public short getTienda() {
        return tienda;
    }

    public void setTienda(short tienda) {
        this.tienda = tienda;
    }

    public short getCaja() {
        return caja;
    }

    public void setCaja(short caja) {
        this.caja = caja;
    }

    public String getTipodocumento() {
        return tipodocumento;
    }

    public void setTipodocumento(String tipodocumento) {
        this.tipodocumento = tipodocumento;
    }

    public long getNumcomprobante() {
        return numcomprobante;
    }

    public void setNumcomprobante(long numcomprobante) {
        this.numcomprobante = numcomprobante;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (maquinafiscal != null ? maquinafiscal.hashCode() : 0);
        hash += (int) tienda;
        hash += (int) caja;
        hash += (tipodocumento != null ? tipodocumento.hashCode() : 0);
        hash += (int) numcomprobante;
        hash += (serie != null ? serie.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ErrormaquinafiscalPK)) {
            return false;
        }
        ErrormaquinafiscalPK other = (ErrormaquinafiscalPK) object;
        if ((this.maquinafiscal == null && other.maquinafiscal != null)
                || (this.maquinafiscal != null && !this.maquinafiscal.equals(other.maquinafiscal))) {
            return false;
        }
        if (this.tienda != other.tienda) {
            return false;
        }
        if (this.caja != other.caja) {
            return false;
        }
        if ((this.tipodocumento == null && other.tipodocumento != null)
                || (this.tipodocumento != null && !this.tipodocumento.equals(other.tipodocumento))) {
            return false;
        }
        if (this.numcomprobante != other.numcomprobante) {
            return false;
        }
        if ((this.serie == null && other.serie != null)
                || (this.serie != null && !this.serie.equals(other.serie))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.grid.ventas.cr.crjpacrsrc.ErrormaquinafiscalPK[ maquinafiscal=" + maquinafiscal + ", tienda="
                + tienda + ", caja=" + caja + ", tipodocumento=" + tipodocumento + ", numcomprobante="
                + numcomprobante + ", serie=" + serie + " ]";
    }

}
