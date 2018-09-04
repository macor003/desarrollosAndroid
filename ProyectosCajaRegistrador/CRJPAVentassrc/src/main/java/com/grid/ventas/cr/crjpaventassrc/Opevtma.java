/*
 * To change this license header, choose License Headers in Project Properties. To change
 * this template file, choose Tools | Templates and open the template in the editor.
 */
package com.grid.ventas.cr.crjpaventassrc;

import java.io.Serializable;
import java.math.BigDecimal;

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
@Table(name = "OPEVTMA")
@XmlRootElement
@NamedQueries({@NamedQuery(name = "Opevtma.findAll",
                           query = "SELECT o FROM Opevtma o"),
        @NamedQuery(name = "Opevtma.findByTienotma",
                    query = "SELECT o FROM Opevtma o WHERE o.opevtmaPK.tienotma = :tienotma"),
        @NamedQuery(name = "Opevtma.findByCajaotma",
                    query = "SELECT o FROM Opevtma o WHERE o.opevtmaPK.cajaotma = :cajaotma"),
        @NamedQuery(name = "Opevtma.findByTranotma",
                    query = "SELECT o FROM Opevtma o WHERE o.opevtmaPK.tranotma = :tranotma"),
        @NamedQuery(name = "Opevtma.findByNumpotma",
                    query = "SELECT o FROM Opevtma o WHERE o.opevtmaPK.numpotma = :numpotma"),
        @NamedQuery(name = "Opevtma.findByTipootma",
                    query = "SELECT o FROM Opevtma o WHERE o.opevtmaPK.tipootma = :tipootma"),
        @NamedQuery(name = "Opevtma.findByBancotma",
                    query = "SELECT o FROM Opevtma o WHERE o.opevtmaPK.bancotma = :bancotma"),
        @NamedQuery(name = "Opevtma.findByMontotma",
                    query = "SELECT o FROM Opevtma o WHERE o.montotma = :montotma"),
        @NamedQuery(name = "Opevtma.findByDocuotma",
                    query = "SELECT o FROM Opevtma o WHERE o.opevtmaPK.docuotma = :docuotma"),
        @NamedQuery(name = "Opevtma.findByCeduotma",
                    query = "SELECT o FROM Opevtma o WHERE o.ceduotma = :ceduotma"),
        @NamedQuery(name = "Opevtma.findByCuenotma",
                    query = "SELECT o FROM Opevtma o WHERE o.cuenotma = :cuenotma"),
        @NamedQuery(name = "Opevtma.findByMotbotma",
                    query = "SELECT o FROM Opevtma o WHERE o.motbotma = :motbotma"),
        @NamedQuery(name = "Opevtma.findByStafotma",
                    query = "SELECT o FROM Opevtma o WHERE o.stafotma = :stafotma"),
        @NamedQuery(name = "Opevtma.findByTserotma",
                    query = "SELECT o FROM Opevtma o WHERE o.opevtmaPK.tserotma = :tserotma")})
public class Opevtma implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    protected OpevtmaPK opevtmaPK;

    // @Max(value=?) @Min(value=?)//if you know range of your decimal fields consider
    // using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "MONTOTMA")
    private BigDecimal montotma;

    @Basic(optional = false)
    @Column(name = "CEDUOTMA")
    private String ceduotma;

    @Basic(optional = false)
    @Column(name = "CUENOTMA")
    private String cuenotma;

    @Basic(optional = false)
    @Column(name = "MOTBOTMA")
    private short motbotma;

    @Basic(optional = false)
    @Column(name = "STAFOTMA")
    private short stafotma;

    public Opevtma() {
    }

    public Opevtma(OpevtmaPK opevtmaPK) {
        this.opevtmaPK = opevtmaPK;
    }

    public Opevtma(OpevtmaPK opevtmaPK, BigDecimal montotma, String ceduotma, String cuenotma, short motbotma,
                   short stafotma) {
        this.opevtmaPK = opevtmaPK;
        this.montotma = montotma;
        this.ceduotma = ceduotma;
        this.cuenotma = cuenotma;
        this.motbotma = motbotma;
        this.stafotma = stafotma;
    }

    public Opevtma(String tienotma, short cajaotma, int tranotma, int numpotma, short tipootma, String bancotma,
                   String docuotma, String tserotma) {
        this.opevtmaPK = new OpevtmaPK(tienotma, cajaotma, tranotma, numpotma, tipootma, bancotma, docuotma,
                tserotma);
    }

    public OpevtmaPK getOpevtmaPK() {
        return opevtmaPK;
    }

    public void setOpevtmaPK(OpevtmaPK opevtmaPK) {
        this.opevtmaPK = opevtmaPK;
    }

    public BigDecimal getMontotma() {
        return montotma;
    }

    public void setMontotma(BigDecimal montotma) {
        this.montotma = montotma;
    }

    public String getCeduotma() {
        return ceduotma;
    }

    public void setCeduotma(String ceduotma) {
        this.ceduotma = ceduotma;
    }

    public String getCuenotma() {
        return cuenotma;
    }

    public void setCuenotma(String cuenotma) {
        this.cuenotma = cuenotma;
    }

    public short getMotbotma() {
        return motbotma;
    }

    public void setMotbotma(short motbotma) {
        this.motbotma = motbotma;
    }

    public short getStafotma() {
        return stafotma;
    }

    public void setStafotma(short stafotma) {
        this.stafotma = stafotma;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (opevtmaPK != null ? opevtmaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Opevtma)) {
            return false;
        }
        Opevtma other = (Opevtma) object;
        if ((this.opevtmaPK == null && other.opevtmaPK != null)
                || (this.opevtmaPK != null && !this.opevtmaPK.equals(other.opevtmaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.grid.ventas.cr.crjpaventassrc.Opevtma[ opevtmaPK=" + opevtmaPK + " ]";
    }

}
