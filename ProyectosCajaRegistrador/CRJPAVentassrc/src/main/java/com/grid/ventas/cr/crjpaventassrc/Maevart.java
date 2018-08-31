/*
 * To change this license header, choose License Headers in Project Properties. To change
 * this template file, choose Tools | Templates and open the template in the editor.
 */
package com.grid.ventas.cr.crjpaventassrc;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "MAEVART")
@XmlRootElement
@NamedQueries({@NamedQuery(name = "Maevart.findAll",
                           query = "SELECT m FROM Maevart m"),
        @NamedQuery(name = "Maevart.findByIdMaevart",
                    query = "SELECT m FROM Maevart m WHERE m.idMaevart = :idMaevart"),
        @NamedQuery(name = "Maevart.findByIdArticulo",
                    query = "SELECT m FROM Maevart m WHERE m.idArticulo = :idArticulo"),
        @NamedQuery(name = "Maevart.findByCodigoArticulo",
                    query = "SELECT m FROM Maevart m WHERE m.codigoArticulo = :codigoArticulo"),
        @NamedQuery(name = "Maevart.findByEstado",
                    query = "SELECT m FROM Maevart m WHERE m.estado = :estado"),
        @NamedQuery(name = "Maevart.findByCventasmayor",
                    query = "SELECT m FROM Maevart m WHERE m.cventasmayor = :cventasmayor"),
        @NamedQuery(name = "Maevart.findByPventasmayor",
                    query = "SELECT m FROM Maevart m WHERE m.pventasmayor = :pventasmayor"),
        @NamedQuery(name = "Maevart.findByDescuentoempleado",
                    query = "SELECT m FROM Maevart m WHERE m.descuentoempleado = :descuentoempleado"),
        @NamedQuery(name = "Maevart.findByIdArtcategoria",
                    query = "SELECT m FROM Maevart m WHERE m.idArtcategoria = :idArtcategoria"),
        @NamedQuery(name = "Maevart.findByUltimasincronizacion",
                    query = "SELECT m FROM Maevart m WHERE m.ultimasincronizacion = :ultimasincronizacion"),
        @NamedQuery(name = "Maevart.findByEstreplica",
                    query = "SELECT m FROM Maevart m WHERE m.estreplica = :estreplica")})
public class Maevart implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "ID_MAEVART")
    private Long idMaevart;

    @Basic(optional = false)
    @Column(name = "ID_ARTICULO")
    private long idArticulo;

    @Basic(optional = false)
    @Column(name = "CODIGO_ARTICULO")
    private int codigoArticulo;

    @Basic(optional = false)
    @Column(name = "ESTADO")
    private Character estado;

    @Column(name = "CVENTASMAYOR")
    private BigInteger cventasmayor;

    // @Max(value=?) @Min(value=?)//if you know range of your decimal fields consider
    // using these annotations to enforce field validation
    @Column(name = "PVENTASMAYOR")
    private BigDecimal pventasmayor;

    @Basic(optional = false)
    @Column(name = "DESCUENTOEMPLEADO")
    private Character descuentoempleado;

    @Column(name = "ID_ARTCATEGORIA")
    private BigInteger idArtcategoria;

    @Basic(optional = false)
    @Column(name = "ULTIMASINCRONIZACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimasincronizacion;

    @Basic(optional = false)
    @Column(name = "ESTREPLICA")
    private Character estreplica;

    public Maevart() {
    }

    public Maevart(Long idMaevart) {
        this.idMaevart = idMaevart;
    }

    public Maevart(Long idMaevart, long idArticulo, int codigoArticulo, Character estado,
                   Character descuentoempleado, Date ultimasincronizacion, Character estreplica) {
        this.idMaevart = idMaevart;
        this.idArticulo = idArticulo;
        this.codigoArticulo = codigoArticulo;
        this.estado = estado;
        this.descuentoempleado = descuentoempleado;
        this.ultimasincronizacion = ultimasincronizacion;
        this.estreplica = estreplica;
    }

    public Long getIdMaevart() {
        return idMaevart;
    }

    public void setIdMaevart(Long idMaevart) {
        this.idMaevart = idMaevart;
    }

    public long getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(long idArticulo) {
        this.idArticulo = idArticulo;
    }

    public int getCodigoArticulo() {
        return codigoArticulo;
    }

    public void setCodigoArticulo(int codigoArticulo) {
        this.codigoArticulo = codigoArticulo;
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }

    public BigInteger getCventasmayor() {
        return cventasmayor;
    }

    public void setCventasmayor(BigInteger cventasmayor) {
        this.cventasmayor = cventasmayor;
    }

    public BigDecimal getPventasmayor() {
        return pventasmayor;
    }

    public void setPventasmayor(BigDecimal pventasmayor) {
        this.pventasmayor = pventasmayor;
    }

    public Character getDescuentoempleado() {
        return descuentoempleado;
    }

    public void setDescuentoempleado(Character descuentoempleado) {
        this.descuentoempleado = descuentoempleado;
    }

    public BigInteger getIdArtcategoria() {
        return idArtcategoria;
    }

    public void setIdArtcategoria(BigInteger idArtcategoria) {
        this.idArtcategoria = idArtcategoria;
    }

    public Date getUltimasincronizacion() {
        return ultimasincronizacion;
    }

    public void setUltimasincronizacion(Date ultimasincronizacion) {
        this.ultimasincronizacion = ultimasincronizacion;
    }

    public Character getEstreplica() {
        return estreplica;
    }

    public void setEstreplica(Character estreplica) {
        this.estreplica = estreplica;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMaevart != null ? idMaevart.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Maevart)) {
            return false;
        }
        Maevart other = (Maevart) object;
        if ((this.idMaevart == null && other.idMaevart != null)
                || (this.idMaevart != null && !this.idMaevart.equals(other.idMaevart))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.grid.ventas.cr.crjpaventassrc.Maevart[ idMaevart=" + idMaevart + " ]";
    }

}
