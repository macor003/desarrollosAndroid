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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author eve0017909
 */
@Entity
@Table(name = "ERRORMAQUINAFISCAL")
@XmlRootElement
@NamedQueries({@NamedQuery(name = "Errormaquinafiscal.findAll",
                           query = "SELECT e FROM Errormaquinafiscal e"),
        @NamedQuery(name = "Errormaquinafiscal.findByMaquinafiscal",
                    query = "SELECT e FROM Errormaquinafiscal e WHERE e.errormaquinafiscalPK.maquinafiscal = :maquinafiscal"),
        @NamedQuery(name = "Errormaquinafiscal.findByTienda",
                    query = "SELECT e FROM Errormaquinafiscal e WHERE e.errormaquinafiscalPK.tienda = :tienda"),
        @NamedQuery(name = "Errormaquinafiscal.findByCaja",
                    query = "SELECT e FROM Errormaquinafiscal e WHERE e.errormaquinafiscalPK.caja = :caja"),
        @NamedQuery(name = "Errormaquinafiscal.findByTipodocumento",
                    query = "SELECT e FROM Errormaquinafiscal e WHERE e.errormaquinafiscalPK.tipodocumento = :tipodocumento"),
        @NamedQuery(name = "Errormaquinafiscal.findByFecha",
                    query = "SELECT e FROM Errormaquinafiscal e WHERE e.fecha = :fecha"),
        @NamedQuery(name = "Errormaquinafiscal.findByNumcomprobante",
                    query = "SELECT e FROM Errormaquinafiscal e WHERE e.errormaquinafiscalPK.numcomprobante = :numcomprobante"),
        @NamedQuery(name = "Errormaquinafiscal.findBySerie",
                    query = "SELECT e FROM Errormaquinafiscal e WHERE e.errormaquinafiscalPK.serie = :serie")})
public class Errormaquinafiscal implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    protected ErrormaquinafiscalPK errormaquinafiscalPK;

    @Basic(optional = false)
    @Column(name = "FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecha;

    public Errormaquinafiscal() {
    }

    public Errormaquinafiscal(ErrormaquinafiscalPK errormaquinafiscalPK) {
        this.errormaquinafiscalPK = errormaquinafiscalPK;
    }

    public Errormaquinafiscal(ErrormaquinafiscalPK errormaquinafiscalPK, Date fecha) {
        this.errormaquinafiscalPK = errormaquinafiscalPK;
        this.fecha = fecha;
    }

    public Errormaquinafiscal(String maquinafiscal, short tienda, short caja, String tipodocumento,
                              long numcomprobante, String serie) {
        this.errormaquinafiscalPK = new ErrormaquinafiscalPK(maquinafiscal, tienda, caja, tipodocumento,
                numcomprobante, serie);
    }

    public ErrormaquinafiscalPK getErrormaquinafiscalPK() {
        return errormaquinafiscalPK;
    }

    public void setErrormaquinafiscalPK(ErrormaquinafiscalPK errormaquinafiscalPK) {
        this.errormaquinafiscalPK = errormaquinafiscalPK;
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
        hash += (errormaquinafiscalPK != null ? errormaquinafiscalPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Errormaquinafiscal)) {
            return false;
        }
        Errormaquinafiscal other = (Errormaquinafiscal) object;
        if ((this.errormaquinafiscalPK == null && other.errormaquinafiscalPK != null)
                || (this.errormaquinafiscalPK != null
                        && !this.errormaquinafiscalPK.equals(other.errormaquinafiscalPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.grid.ventas.cr.crjpacrsrc.Errormaquinafiscal[ errormaquinafiscalPK=" + errormaquinafiscalPK
                + " ]";
    }

}
