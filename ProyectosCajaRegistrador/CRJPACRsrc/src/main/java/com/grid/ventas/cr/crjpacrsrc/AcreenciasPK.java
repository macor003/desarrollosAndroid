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
public class AcreenciasPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "NUMTIENDA")
    private short numtienda;

    @Basic(optional = false)
    @Column(name = "NUMCAJA")
    private int numcaja;

    @Basic(optional = false)
    @Column(name = "FECHA")
    private String fecha;

    @Basic(optional = false)
    @Column(name = "NUMOPERACION")
    private int numoperacion;

    @Basic(optional = false)
    @Column(name = "NUMACREENCIA")
    private int numacreencia;

    @Basic(optional = false)
    @Column(name = "TIPOACREENCIA")
    private Character tipoacreencia;

    @Basic(optional = false)
    @Column(name = "CODCLIENTE")
    private String codcliente;

    public AcreenciasPK() {
    }

    public AcreenciasPK(short numtienda, int numcaja, String fecha, int numoperacion, int numacreencia,
                        Character tipoacreencia, String codcliente) {
        this.numtienda = numtienda;
        this.numcaja = numcaja;
        this.fecha = fecha;
        this.numoperacion = numoperacion;
        this.numacreencia = numacreencia;
        this.tipoacreencia = tipoacreencia;
        this.codcliente = codcliente;
    }

    public short getNumtienda() {
        return numtienda;
    }

    public void setNumtienda(short numtienda) {
        this.numtienda = numtienda;
    }

    public int getNumcaja() {
        return numcaja;
    }

    public void setNumcaja(int numcaja) {
        this.numcaja = numcaja;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getNumoperacion() {
        return numoperacion;
    }

    public void setNumoperacion(int numoperacion) {
        this.numoperacion = numoperacion;
    }

    public int getNumacreencia() {
        return numacreencia;
    }

    public void setNumacreencia(int numacreencia) {
        this.numacreencia = numacreencia;
    }

    public Character getTipoacreencia() {
        return tipoacreencia;
    }

    public void setTipoacreencia(Character tipoacreencia) {
        this.tipoacreencia = tipoacreencia;
    }

    public String getCodcliente() {
        return codcliente;
    }

    public void setCodcliente(String codcliente) {
        this.codcliente = codcliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) numtienda;
        hash += (int) numcaja;
        hash += (fecha != null ? fecha.hashCode() : 0);
        hash += (int) numoperacion;
        hash += (int) numacreencia;
        hash += (tipoacreencia != null ? tipoacreencia.hashCode() : 0);
        hash += (codcliente != null ? codcliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AcreenciasPK)) {
            return false;
        }
        AcreenciasPK other = (AcreenciasPK) object;
        if (this.numtienda != other.numtienda) {
            return false;
        }
        if (this.numcaja != other.numcaja) {
            return false;
        }
        if ((this.fecha == null && other.fecha != null)
                || (this.fecha != null && !this.fecha.equals(other.fecha))) {
            return false;
        }
        if (this.numoperacion != other.numoperacion) {
            return false;
        }
        if (this.numacreencia != other.numacreencia) {
            return false;
        }
        if ((this.tipoacreencia == null && other.tipoacreencia != null)
                || (this.tipoacreencia != null && !this.tipoacreencia.equals(other.tipoacreencia))) {
            return false;
        }
        if ((this.codcliente == null && other.codcliente != null)
                || (this.codcliente != null && !this.codcliente.equals(other.codcliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.grid.ventas.cr.crjpacrsrc.AcreenciasPK[ numtienda=" + numtienda + ", numcaja=" + numcaja
                + ", fecha=" + fecha + ", numoperacion=" + numoperacion + ", numacreencia=" + numacreencia
                + ", tipoacreencia=" + tipoacreencia + ", codcliente=" + codcliente + " ]";
    }

}
