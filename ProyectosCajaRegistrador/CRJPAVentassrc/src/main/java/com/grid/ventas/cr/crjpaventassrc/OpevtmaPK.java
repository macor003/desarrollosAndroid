/*
 * To change this license header, choose License Headers in Project Properties. To change
 * this template file, choose Tools | Templates and open the template in the editor.
 */
package com.grid.ventas.cr.crjpaventassrc;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author eve0017909
 */
@Embeddable
public class OpevtmaPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "TIENOTMA")
    private String tienotma;

    @Basic(optional = false)
    @Column(name = "CAJAOTMA")
    private short cajaotma;

    @Basic(optional = false)
    @Column(name = "TRANOTMA")
    private int tranotma;

    @Basic(optional = false)
    @Column(name = "NUMPOTMA")
    private int numpotma;

    @Basic(optional = false)
    @Column(name = "TIPOOTMA")
    private short tipootma;

    @Basic(optional = false)
    @Column(name = "BANCOTMA")
    private String bancotma;

    @Basic(optional = false)
    @Column(name = "DOCUOTMA")
    private String docuotma;

    @Basic(optional = false)
    @Column(name = "TSEROTMA")
    private String tserotma;

    public OpevtmaPK() {
    }

    public OpevtmaPK(String tienotma, short cajaotma, int tranotma, int numpotma, short tipootma, String bancotma,
                     String docuotma, String tserotma) {
        this.tienotma = tienotma;
        this.cajaotma = cajaotma;
        this.tranotma = tranotma;
        this.numpotma = numpotma;
        this.tipootma = tipootma;
        this.bancotma = bancotma;
        this.docuotma = docuotma;
        this.tserotma = tserotma;
    }

    public String getTienotma() {
        return tienotma;
    }

    public void setTienotma(String tienotma) {
        this.tienotma = tienotma;
    }

    public short getCajaotma() {
        return cajaotma;
    }

    public void setCajaotma(short cajaotma) {
        this.cajaotma = cajaotma;
    }

    public int getTranotma() {
        return tranotma;
    }

    public void setTranotma(int tranotma) {
        this.tranotma = tranotma;
    }

    public int getNumpotma() {
        return numpotma;
    }

    public void setNumpotma(int numpotma) {
        this.numpotma = numpotma;
    }

    public short getTipootma() {
        return tipootma;
    }

    public void setTipootma(short tipootma) {
        this.tipootma = tipootma;
    }

    public String getBancotma() {
        return bancotma;
    }

    public void setBancotma(String bancotma) {
        this.bancotma = bancotma;
    }

    public String getDocuotma() {
        return docuotma;
    }

    public void setDocuotma(String docuotma) {
        this.docuotma = docuotma;
    }

    public String getTserotma() {
        return tserotma;
    }

    public void setTserotma(String tserotma) {
        this.tserotma = tserotma;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tienotma != null ? tienotma.hashCode() : 0);
        hash += (int) cajaotma;
        hash += (int) tranotma;
        hash += (int) numpotma;
        hash += (int) tipootma;
        hash += (bancotma != null ? bancotma.hashCode() : 0);
        hash += (docuotma != null ? docuotma.hashCode() : 0);
        hash += (tserotma != null ? tserotma.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OpevtmaPK)) {
            return false;
        }
        OpevtmaPK other = (OpevtmaPK) object;
        if ((this.tienotma == null && other.tienotma != null)
                || (this.tienotma != null && !this.tienotma.equals(other.tienotma))) {
            return false;
        }
        if (this.cajaotma != other.cajaotma) {
            return false;
        }
        if (this.tranotma != other.tranotma) {
            return false;
        }
        if (this.numpotma != other.numpotma) {
            return false;
        }
        if (this.tipootma != other.tipootma) {
            return false;
        }
        if ((this.bancotma == null && other.bancotma != null)
                || (this.bancotma != null && !this.bancotma.equals(other.bancotma))) {
            return false;
        }
        if ((this.docuotma == null && other.docuotma != null)
                || (this.docuotma != null && !this.docuotma.equals(other.docuotma))) {
            return false;
        }
        if ((this.tserotma == null && other.tserotma != null)
                || (this.tserotma != null && !this.tserotma.equals(other.tserotma))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.grid.ventas.cr.crjpaventassrc.OpevtmaPK[ tienotma=" + tienotma + ", cajaotma=" + cajaotma
                + ", tranotma=" + tranotma + ", numpotma=" + numpotma + ", tipootma=" + tipootma + ", bancotma="
                + bancotma + ", docuotma=" + docuotma + ", tserotma=" + tserotma + " ]";
    }

}
