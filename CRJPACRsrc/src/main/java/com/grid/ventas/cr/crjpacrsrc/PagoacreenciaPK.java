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
public class PagoacreenciaPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "NUMTIENDA")
    private short numtienda;

    @Basic(optional = false)
    @Column(name = "NUMOPERACION")
    private int numoperacion;

    @Basic(optional = false)
    @Column(name = "NUMACREENCIA")
    private int numacreencia;

    @Basic(optional = false)
    @Column(name = "FECHA")
    private String fecha;

    @Basic(optional = false)
    @Column(name = "CODFORMADEPAGO")
    private String codformadepago;

    @Basic(optional = false)
    @Column(name = "CORRELATIVOITEMS")
    private int correlativoitems;

    @Basic(optional = false)
    @Column(name = "CODCLIENTE")
    private String codcliente;

    public PagoacreenciaPK() {
    }

    public PagoacreenciaPK(short numtienda, int numoperacion, int numacreencia, String fecha,
                           String codformadepago, int correlativoitems, String codcliente) {
        this.numtienda = numtienda;
        this.numoperacion = numoperacion;
        this.numacreencia = numacreencia;
        this.fecha = fecha;
        this.codformadepago = codformadepago;
        this.correlativoitems = correlativoitems;
        this.codcliente = codcliente;
    }

    public short getNumtienda() {
        return numtienda;
    }

    public void setNumtienda(short numtienda) {
        this.numtienda = numtienda;
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCodformadepago() {
        return codformadepago;
    }

    public void setCodformadepago(String codformadepago) {
        this.codformadepago = codformadepago;
    }

    public int getCorrelativoitems() {
        return correlativoitems;
    }

    public void setCorrelativoitems(int correlativoitems) {
        this.correlativoitems = correlativoitems;
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
        hash += (int) numoperacion;
        hash += (int) numacreencia;
        hash += (fecha != null ? fecha.hashCode() : 0);
        hash += (codformadepago != null ? codformadepago.hashCode() : 0);
        hash += (int) correlativoitems;
        hash += (codcliente != null ? codcliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PagoacreenciaPK)) {
            return false;
        }
        PagoacreenciaPK other = (PagoacreenciaPK) object;
        if (this.numtienda != other.numtienda) {
            return false;
        }
        if (this.numoperacion != other.numoperacion) {
            return false;
        }
        if (this.numacreencia != other.numacreencia) {
            return false;
        }
        if ((this.fecha == null && other.fecha != null)
                || (this.fecha != null && !this.fecha.equals(other.fecha))) {
            return false;
        }
        if ((this.codformadepago == null && other.codformadepago != null)
                || (this.codformadepago != null && !this.codformadepago.equals(other.codformadepago))) {
            return false;
        }
        if (this.correlativoitems != other.correlativoitems) {
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
        return "com.grid.ventas.cr.crjpacrsrc.PagoacreenciaPK[ numtienda=" + numtienda + ", numoperacion="
                + numoperacion + ", numacreencia=" + numacreencia + ", fecha=" + fecha + ", codformadepago="
                + codformadepago + ", correlativoitems=" + correlativoitems + ", codcliente=" + codcliente + " ]";
    }

}
