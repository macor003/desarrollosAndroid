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
@Table(name = "CONTROLDEFORMULARIOS")
@XmlRootElement
@NamedQueries({@NamedQuery(name = "Controldeformularios.findAll",
                           query = "SELECT c FROM Controldeformularios c"),
        @NamedQuery(name = "Controldeformularios.findByTienda",
                    query = "SELECT c FROM Controldeformularios c WHERE c.controldeformulariosPK.tienda = :tienda"),
        @NamedQuery(name = "Controldeformularios.findByFecha",
                    query = "SELECT c FROM Controldeformularios c WHERE c.controldeformulariosPK.fecha = :fecha"),
        @NamedQuery(name = "Controldeformularios.findByCaja",
                    query = "SELECT c FROM Controldeformularios c WHERE c.controldeformulariosPK.caja = :caja"),
        @NamedQuery(name = "Controldeformularios.findByTipodocumento",
                    query = "SELECT c FROM Controldeformularios c WHERE c.controldeformulariosPK.tipodocumento = :tipodocumento"),
        @NamedQuery(name = "Controldeformularios.findByNumcompdesde",
                    query = "SELECT c FROM Controldeformularios c WHERE c.numcompdesde = :numcompdesde"),
        @NamedQuery(name = "Controldeformularios.findByNumcomphasta",
                    query = "SELECT c FROM Controldeformularios c WHERE c.numcomphasta = :numcomphasta"),
        @NamedQuery(name = "Controldeformularios.findByNumcompactual",
                    query = "SELECT c FROM Controldeformularios c WHERE c.numcompactual = :numcompactual"),
        @NamedQuery(name = "Controldeformularios.findByEstado",
                    query = "SELECT c FROM Controldeformularios c WHERE c.estado = :estado"),
        @NamedQuery(name = "Controldeformularios.findByObservacion",
                    query = "SELECT c FROM Controldeformularios c WHERE c.observacion = :observacion"),
        @NamedQuery(name = "Controldeformularios.findByFechaactualizacion",
                    query = "SELECT c FROM Controldeformularios c WHERE c.fechaactualizacion = :fechaactualizacion"),
        @NamedQuery(name = "Controldeformularios.findBySerie",
                    query = "SELECT c FROM Controldeformularios c WHERE c.serie = :serie"),
        @NamedQuery(name = "Controldeformularios.findByIdsincroniza",
                    query = "SELECT c FROM Controldeformularios c WHERE c.idsincroniza = :idsincroniza")})
public class Controldeformularios implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    protected ControldeformulariosPK controldeformulariosPK;

    @Basic(optional = false)
    @Column(name = "NUMCOMPDESDE")
    private long numcompdesde;

    @Basic(optional = false)
    @Column(name = "NUMCOMPHASTA")
    private long numcomphasta;

    @Basic(optional = false)
    @Column(name = "NUMCOMPACTUAL")
    private long numcompactual;

    @Basic(optional = false)
    @Column(name = "ESTADO")
    private Character estado;

    @Basic(optional = false)
    @Column(name = "OBSERVACION")
    private String observacion;

    @Basic(optional = false)
    @Column(name = "FECHAACTUALIZACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaactualizacion;

    @Basic(optional = false)
    @Column(name = "SERIE")
    private String serie;

    @Basic(optional = false)
    @Column(name = "IDSINCRONIZA")
    private long idsincroniza;

    public Controldeformularios() {
    }

    public Controldeformularios(ControldeformulariosPK controldeformulariosPK) {
        this.controldeformulariosPK = controldeformulariosPK;
    }

    public Controldeformularios(ControldeformulariosPK controldeformulariosPK, long numcompdesde,
                                long numcomphasta, long numcompactual, Character estado, String observacion,
                                Date fechaactualizacion, String serie, long idsincroniza) {
        this.controldeformulariosPK = controldeformulariosPK;
        this.numcompdesde = numcompdesde;
        this.numcomphasta = numcomphasta;
        this.numcompactual = numcompactual;
        this.estado = estado;
        this.observacion = observacion;
        this.fechaactualizacion = fechaactualizacion;
        this.serie = serie;
        this.idsincroniza = idsincroniza;
    }

    public Controldeformularios(short tienda, Date fecha, short caja, short tipodocumento) {
        this.controldeformulariosPK = new ControldeformulariosPK(tienda, fecha, caja, tipodocumento);
    }

    public ControldeformulariosPK getControldeformulariosPK() {
        return controldeformulariosPK;
    }

    public void setControldeformulariosPK(ControldeformulariosPK controldeformulariosPK) {
        this.controldeformulariosPK = controldeformulariosPK;
    }

    public long getNumcompdesde() {
        return numcompdesde;
    }

    public void setNumcompdesde(long numcompdesde) {
        this.numcompdesde = numcompdesde;
    }

    public long getNumcomphasta() {
        return numcomphasta;
    }

    public void setNumcomphasta(long numcomphasta) {
        this.numcomphasta = numcomphasta;
    }

    public long getNumcompactual() {
        return numcompactual;
    }

    public void setNumcompactual(long numcompactual) {
        this.numcompactual = numcompactual;
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Date getFechaactualizacion() {
        return fechaactualizacion;
    }

    public void setFechaactualizacion(Date fechaactualizacion) {
        this.fechaactualizacion = fechaactualizacion;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public long getIdsincroniza() {
        return idsincroniza;
    }

    public void setIdsincroniza(long idsincroniza) {
        this.idsincroniza = idsincroniza;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (controldeformulariosPK != null ? controldeformulariosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Controldeformularios)) {
            return false;
        }
        Controldeformularios other = (Controldeformularios) object;
        if ((this.controldeformulariosPK == null && other.controldeformulariosPK != null)
                || (this.controldeformulariosPK != null
                        && !this.controldeformulariosPK.equals(other.controldeformulariosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.grid.ventas.cr.crjpacrsrc.Controldeformularios[ controldeformulariosPK="
                + controldeformulariosPK + " ]";
    }

}
