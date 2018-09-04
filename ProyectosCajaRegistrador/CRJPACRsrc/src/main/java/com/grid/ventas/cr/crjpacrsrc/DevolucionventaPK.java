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
public class DevolucionventaPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "NUMTIENDADEVOLUCION")
    private short numtiendadevolucion;

    @Basic(optional = false)
    @Column(name = "FECHADEVOLUCION")
    @Temporal(TemporalType.DATE)
    private Date fechadevolucion;

    @Basic(optional = false)
    @Column(name = "NUMCAJADEVOLUCION")
    private short numcajadevolucion;

    @Basic(optional = false)
    @Column(name = "NUMTRANSACCIONDEV")
    private int numtransacciondev;

    @Basic(optional = false)
    @Column(name = "NUMTIENDAVENTA")
    private short numtiendaventa;

    @Basic(optional = false)
    @Column(name = "FECHAVENTA")
    @Temporal(TemporalType.DATE)
    private Date fechaventa;

    @Basic(optional = false)
    @Column(name = "NUMCAJAVENTA")
    private short numcajaventa;

    @Basic(optional = false)
    @Column(name = "NUMTRANSACCIONVTA")
    private int numtransaccionvta;

    public DevolucionventaPK() {
    }

    public DevolucionventaPK(short numtiendadevolucion, Date fechadevolucion, short numcajadevolucion,
                             int numtransacciondev, short numtiendaventa, Date fechaventa, short numcajaventa,
                             int numtransaccionvta) {
        this.numtiendadevolucion = numtiendadevolucion;
        this.fechadevolucion = fechadevolucion;
        this.numcajadevolucion = numcajadevolucion;
        this.numtransacciondev = numtransacciondev;
        this.numtiendaventa = numtiendaventa;
        this.fechaventa = fechaventa;
        this.numcajaventa = numcajaventa;
        this.numtransaccionvta = numtransaccionvta;
    }

    public short getNumtiendadevolucion() {
        return numtiendadevolucion;
    }

    public void setNumtiendadevolucion(short numtiendadevolucion) {
        this.numtiendadevolucion = numtiendadevolucion;
    }

    public Date getFechadevolucion() {
        return fechadevolucion;
    }

    public void setFechadevolucion(Date fechadevolucion) {
        this.fechadevolucion = fechadevolucion;
    }

    public short getNumcajadevolucion() {
        return numcajadevolucion;
    }

    public void setNumcajadevolucion(short numcajadevolucion) {
        this.numcajadevolucion = numcajadevolucion;
    }

    public int getNumtransacciondev() {
        return numtransacciondev;
    }

    public void setNumtransacciondev(int numtransacciondev) {
        this.numtransacciondev = numtransacciondev;
    }

    public short getNumtiendaventa() {
        return numtiendaventa;
    }

    public void setNumtiendaventa(short numtiendaventa) {
        this.numtiendaventa = numtiendaventa;
    }

    public Date getFechaventa() {
        return fechaventa;
    }

    public void setFechaventa(Date fechaventa) {
        this.fechaventa = fechaventa;
    }

    public short getNumcajaventa() {
        return numcajaventa;
    }

    public void setNumcajaventa(short numcajaventa) {
        this.numcajaventa = numcajaventa;
    }

    public int getNumtransaccionvta() {
        return numtransaccionvta;
    }

    public void setNumtransaccionvta(int numtransaccionvta) {
        this.numtransaccionvta = numtransaccionvta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) numtiendadevolucion;
        hash += (fechadevolucion != null ? fechadevolucion.hashCode() : 0);
        hash += (int) numcajadevolucion;
        hash += (int) numtransacciondev;
        hash += (int) numtiendaventa;
        hash += (fechaventa != null ? fechaventa.hashCode() : 0);
        hash += (int) numcajaventa;
        hash += (int) numtransaccionvta;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DevolucionventaPK)) {
            return false;
        }
        DevolucionventaPK other = (DevolucionventaPK) object;
        if (this.numtiendadevolucion != other.numtiendadevolucion) {
            return false;
        }
        if ((this.fechadevolucion == null && other.fechadevolucion != null)
                || (this.fechadevolucion != null && !this.fechadevolucion.equals(other.fechadevolucion))) {
            return false;
        }
        if (this.numcajadevolucion != other.numcajadevolucion) {
            return false;
        }
        if (this.numtransacciondev != other.numtransacciondev) {
            return false;
        }
        if (this.numtiendaventa != other.numtiendaventa) {
            return false;
        }
        if ((this.fechaventa == null && other.fechaventa != null)
                || (this.fechaventa != null && !this.fechaventa.equals(other.fechaventa))) {
            return false;
        }
        if (this.numcajaventa != other.numcajaventa) {
            return false;
        }
        if (this.numtransaccionvta != other.numtransaccionvta) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.grid.ventas.cr.crjpacrsrc.DevolucionventaPK[ numtiendadevolucion=" + numtiendadevolucion
                + ", fechadevolucion=" + fechadevolucion + ", numcajadevolucion=" + numcajadevolucion
                + ", numtransacciondev=" + numtransacciondev + ", numtiendaventa=" + numtiendaventa
                + ", fechaventa=" + fechaventa + ", numcajaventa=" + numcajaventa + ", numtransaccionvta="
                + numtransaccionvta + " ]";
    }

}
