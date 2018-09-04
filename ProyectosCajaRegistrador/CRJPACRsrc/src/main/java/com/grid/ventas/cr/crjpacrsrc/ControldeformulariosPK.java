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
public class ControldeformulariosPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "TIENDA")
    private short tienda;

    @Basic(optional = false)
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    @Basic(optional = false)
    @Column(name = "CAJA")
    private short caja;

    @Basic(optional = false)
    @Column(name = "TIPODOCUMENTO")
    private short tipodocumento;

    public ControldeformulariosPK() {
    }

    public ControldeformulariosPK(short tienda, Date fecha, short caja, short tipodocumento) {
        this.tienda = tienda;
        this.fecha = fecha;
        this.caja = caja;
        this.tipodocumento = tipodocumento;
    }

    public short getTienda() {
        return tienda;
    }

    public void setTienda(short tienda) {
        this.tienda = tienda;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public short getCaja() {
        return caja;
    }

    public void setCaja(short caja) {
        this.caja = caja;
    }

    public short getTipodocumento() {
        return tipodocumento;
    }

    public void setTipodocumento(short tipodocumento) {
        this.tipodocumento = tipodocumento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) tienda;
        hash += (fecha != null ? fecha.hashCode() : 0);
        hash += (int) caja;
        hash += (int) tipodocumento;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ControldeformulariosPK)) {
            return false;
        }
        ControldeformulariosPK other = (ControldeformulariosPK) object;
        if (this.tienda != other.tienda) {
            return false;
        }
        if ((this.fecha == null && other.fecha != null)
                || (this.fecha != null && !this.fecha.equals(other.fecha))) {
            return false;
        }
        if (this.caja != other.caja) {
            return false;
        }
        if (this.tipodocumento != other.tipodocumento) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.grid.ventas.cr.crjpacrsrc.ControldeformulariosPK[ tienda=" + tienda + ", fecha=" + fecha
                + ", caja=" + caja + ", tipodocumento=" + tipodocumento + " ]";
    }

}
