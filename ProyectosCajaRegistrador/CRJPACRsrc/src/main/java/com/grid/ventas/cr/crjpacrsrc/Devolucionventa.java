/*
 * To change this license header, choose License Headers in Project Properties. To change
 * this template file, choose Tools | Templates and open the template in the editor.
 */
package com.grid.ventas.cr.crjpacrsrc;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author eve0017909
 */
@Entity
@Table(name = "DEVOLUCIONVENTA")
@XmlRootElement
@NamedQueries({@NamedQuery(name = "Devolucionventa.findAll",
                           query = "SELECT d FROM Devolucionventa d"),
        @NamedQuery(name = "Devolucionventa.findByNumtiendadevolucion",
                    query = "SELECT d FROM Devolucionventa d WHERE d.devolucionventaPK.numtiendadevolucion = :numtiendadevolucion"),
        @NamedQuery(name = "Devolucionventa.findByFechadevolucion",
                    query = "SELECT d FROM Devolucionventa d WHERE d.devolucionventaPK.fechadevolucion = :fechadevolucion"),
        @NamedQuery(name = "Devolucionventa.findByNumcajadevolucion",
                    query = "SELECT d FROM Devolucionventa d WHERE d.devolucionventaPK.numcajadevolucion = :numcajadevolucion"),
        @NamedQuery(name = "Devolucionventa.findByNumtransacciondev",
                    query = "SELECT d FROM Devolucionventa d WHERE d.devolucionventaPK.numtransacciondev = :numtransacciondev"),
        @NamedQuery(name = "Devolucionventa.findByNumtiendaventa",
                    query = "SELECT d FROM Devolucionventa d WHERE d.devolucionventaPK.numtiendaventa = :numtiendaventa"),
        @NamedQuery(name = "Devolucionventa.findByFechaventa",
                    query = "SELECT d FROM Devolucionventa d WHERE d.devolucionventaPK.fechaventa = :fechaventa"),
        @NamedQuery(name = "Devolucionventa.findByNumcajaventa",
                    query = "SELECT d FROM Devolucionventa d WHERE d.devolucionventaPK.numcajaventa = :numcajaventa"),
        @NamedQuery(name = "Devolucionventa.findByNumtransaccionvta",
                    query = "SELECT d FROM Devolucionventa d WHERE d.devolucionventaPK.numtransaccionvta = :numtransaccionvta"),
        @NamedQuery(name = "Devolucionventa.findByTipotransaccion",
                    query = "SELECT d FROM Devolucionventa d WHERE d.tipotransaccion = :tipotransaccion"),
        @NamedQuery(name = "Devolucionventa.findByRegactualizado",
                    query = "SELECT d FROM Devolucionventa d WHERE d.regactualizado = :regactualizado")})
public class Devolucionventa implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    protected DevolucionventaPK devolucionventaPK;

    @Basic(optional = false)
    @Column(name = "TIPOTRANSACCION")
    private Character tipotransaccion;

    @Basic(optional = false)
    @Column(name = "REGACTUALIZADO")
    private Character regactualizado;

    public Devolucionventa() {
    }

    public Devolucionventa(DevolucionventaPK devolucionventaPK) {
        this.devolucionventaPK = devolucionventaPK;
    }

    public Devolucionventa(DevolucionventaPK devolucionventaPK, Character tipotransaccion,
                           Character regactualizado) {
        this.devolucionventaPK = devolucionventaPK;
        this.tipotransaccion = tipotransaccion;
        this.regactualizado = regactualizado;
    }

    public Devolucionventa(short numtiendadevolucion, Date fechadevolucion, short numcajadevolucion,
                           int numtransacciondev, short numtiendaventa, Date fechaventa, short numcajaventa,
                           int numtransaccionvta) {
        this.devolucionventaPK = new DevolucionventaPK(numtiendadevolucion, fechadevolucion, numcajadevolucion,
                numtransacciondev, numtiendaventa, fechaventa, numcajaventa, numtransaccionvta);
    }

    public DevolucionventaPK getDevolucionventaPK() {
        return devolucionventaPK;
    }

    public void setDevolucionventaPK(DevolucionventaPK devolucionventaPK) {
        this.devolucionventaPK = devolucionventaPK;
    }

    public Character getTipotransaccion() {
        return tipotransaccion;
    }

    public void setTipotransaccion(Character tipotransaccion) {
        this.tipotransaccion = tipotransaccion;
    }

    public Character getRegactualizado() {
        return regactualizado;
    }

    public void setRegactualizado(Character regactualizado) {
        this.regactualizado = regactualizado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (devolucionventaPK != null ? devolucionventaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Devolucionventa)) {
            return false;
        }
        Devolucionventa other = (Devolucionventa) object;
        if ((this.devolucionventaPK == null && other.devolucionventaPK != null)
                || (this.devolucionventaPK != null && !this.devolucionventaPK.equals(other.devolucionventaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.grid.ventas.cr.crjpacrsrc.Devolucionventa[ devolucionventaPK=" + devolucionventaPK + " ]";
    }

}
