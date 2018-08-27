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
public class DetalletransaccionPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "NUMTIENDA")
    private short numtienda;

    @Basic(optional = false)
    @Column(name = "FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecha;

    @Basic(optional = false)
    @Column(name = "NUMCAJAINICIA")
    private short numcajainicia;

    @Basic(optional = false)
    @Column(name = "NUMREGCAJAINICIA")
    private int numregcajainicia;

    @Basic(optional = false)
    @Column(name = "CODPRODUCTO")
    private String codproducto;

    @Basic(optional = false)
    @Column(name = "CODCONDICIONVENTA")
    private String codcondicionventa;

    @Basic(optional = false)
    @Column(name = "CORRELATIVOITEM")
    private short correlativoitem;

    public DetalletransaccionPK() {
    }

    public DetalletransaccionPK(short numtienda, Date fecha, short numcajainicia, int numregcajainicia,
                                String codproducto, String codcondicionventa, short correlativoitem) {
        this.numtienda = numtienda;
        this.fecha = fecha;
        this.numcajainicia = numcajainicia;
        this.numregcajainicia = numregcajainicia;
        this.codproducto = codproducto;
        this.codcondicionventa = codcondicionventa;
        this.correlativoitem = correlativoitem;
    }

    public short getNumtienda() {
        return numtienda;
    }

    public void setNumtienda(short numtienda) {
        this.numtienda = numtienda;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public short getNumcajainicia() {
        return numcajainicia;
    }

    public void setNumcajainicia(short numcajainicia) {
        this.numcajainicia = numcajainicia;
    }

    public int getNumregcajainicia() {
        return numregcajainicia;
    }

    public void setNumregcajainicia(int numregcajainicia) {
        this.numregcajainicia = numregcajainicia;
    }

    public String getCodproducto() {
        return codproducto;
    }

    public void setCodproducto(String codproducto) {
        this.codproducto = codproducto;
    }

    public String getCodcondicionventa() {
        return codcondicionventa;
    }

    public void setCodcondicionventa(String codcondicionventa) {
        this.codcondicionventa = codcondicionventa;
    }

    public short getCorrelativoitem() {
        return correlativoitem;
    }

    public void setCorrelativoitem(short correlativoitem) {
        this.correlativoitem = correlativoitem;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) numtienda;
        hash += (fecha != null ? fecha.hashCode() : 0);
        hash += (int) numcajainicia;
        hash += (int) numregcajainicia;
        hash += (codproducto != null ? codproducto.hashCode() : 0);
        hash += (codcondicionventa != null ? codcondicionventa.hashCode() : 0);
        hash += (int) correlativoitem;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalletransaccionPK)) {
            return false;
        }
        DetalletransaccionPK other = (DetalletransaccionPK) object;
        if (this.numtienda != other.numtienda) {
            return false;
        }
        if ((this.fecha == null && other.fecha != null)
                || (this.fecha != null && !this.fecha.equals(other.fecha))) {
            return false;
        }
        if (this.numcajainicia != other.numcajainicia) {
            return false;
        }
        if (this.numregcajainicia != other.numregcajainicia) {
            return false;
        }
        if ((this.codproducto == null && other.codproducto != null)
                || (this.codproducto != null && !this.codproducto.equals(other.codproducto))) {
            return false;
        }
        if ((this.codcondicionventa == null && other.codcondicionventa != null)
                || (this.codcondicionventa != null && !this.codcondicionventa.equals(other.codcondicionventa))) {
            return false;
        }
        if (this.correlativoitem != other.correlativoitem) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.grid.ventas.cr.crjpacrsrc.DetalletransaccionPK[ numtienda=" + numtienda + ", fecha=" + fecha
                + ", numcajainicia=" + numcajainicia + ", numregcajainicia=" + numregcajainicia + ", codproducto="
                + codproducto + ", codcondicionventa=" + codcondicionventa + ", correlativoitem=" + correlativoitem
                + " ]";
    }

}
