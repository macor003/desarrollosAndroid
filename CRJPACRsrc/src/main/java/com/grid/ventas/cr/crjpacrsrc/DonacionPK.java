/*
 * To change this license header, choose License Headers in Project Properties. To change
 * this template file, choose Tools | Templates and open the template in the editor.
 */
package com.grid.ventas.cr.crjpacrsrc;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author eve0017909
 */
@Embeddable
public class DonacionPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "NUMTIENDA")
    private short numtienda;

    @Basic(optional = false)
    @Column(name = "NUMCAJA")
    private short numcaja;

    @Basic(optional = false)
    @Column(name = "NUMTRANSACCION")
    private int numtransaccion;

    @Basic(optional = false)
    @Column(name = "CODFORMADEPAGO")
    private String codformadepago;

    @Basic(optional = false)
    @Column(name = "CORRELATIVOITEM")
    private short correlativoitem;

    @Basic(optional = false)
    @Column(name = "FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecha;

    public DonacionPK() {
    }

    public DonacionPK(short numtienda, short numcaja, int numtransaccion, String codformadepago,
                      short correlativoitem, Date fecha) {
        this.numtienda = numtienda;
        this.numcaja = numcaja;
        this.numtransaccion = numtransaccion;
        this.codformadepago = codformadepago;
        this.correlativoitem = correlativoitem;
        this.fecha = fecha;
    }

    public short getNumtienda() {
        return numtienda;
    }

    public void setNumtienda(short numtienda) {
        this.numtienda = numtienda;
    }

    public short getNumcaja() {
        return numcaja;
    }

    public void setNumcaja(short numcaja) {
        this.numcaja = numcaja;
    }

    public int getNumtransaccion() {
        return numtransaccion;
    }

    public void setNumtransaccion(int numtransaccion) {
        this.numtransaccion = numtransaccion;
    }

    public String getCodformadepago() {
        return codformadepago;
    }

    public void setCodformadepago(String codformadepago) {
        this.codformadepago = codformadepago;
    }

    public short getCorrelativoitem() {
        return correlativoitem;
    }

    public void setCorrelativoitem(short correlativoitem) {
        this.correlativoitem = correlativoitem;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) numtienda;
        hash += (int) numcaja;
        hash += (int) numtransaccion;
        hash += (codformadepago != null ? codformadepago.hashCode() : 0);
        hash += (int) correlativoitem;
        hash += (fecha != null ? fecha.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DonacionPK)) {
            return false;
        }
        DonacionPK other = (DonacionPK) object;
        if (this.numtienda != other.numtienda) {
            return false;
        }
        if (this.numcaja != other.numcaja) {
            return false;
        }
        if (this.numtransaccion != other.numtransaccion) {
            return false;
        }
        if ((this.codformadepago == null && other.codformadepago != null)
                || (this.codformadepago != null && !this.codformadepago.equals(other.codformadepago))) {
            return false;
        }
        if (this.correlativoitem != other.correlativoitem) {
            return false;
        }
        if ((this.fecha == null && other.fecha != null)
                || (this.fecha != null && !this.fecha.equals(other.fecha))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.grid.ventas.cr.crjpacrsrc.DonacionPK[ numtienda=" + numtienda + ", numcaja=" + numcaja
                + ", numtransaccion=" + numtransaccion + ", codformadepago=" + codformadepago
                + ", correlativoitem=" + correlativoitem + ", fecha=" + fecha + " ]";
    }

}
