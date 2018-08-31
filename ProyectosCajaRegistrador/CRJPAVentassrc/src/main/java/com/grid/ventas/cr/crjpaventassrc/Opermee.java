/*
 * To change this license header, choose License Headers in Project Properties. To change
 * this template file, choose Tools | Templates and open the template in the editor.
 */
package com.grid.ventas.cr.crjpaventassrc;

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
@Table(name = "OPERMEE")
@XmlRootElement
@NamedQueries({@NamedQuery(name = "Opermee.findAll",
                           query = "SELECT o FROM Opermee o"),
        @NamedQuery(name = "Opermee.findByTienomee",
                    query = "SELECT o FROM Opermee o WHERE o.opermeePK.tienomee = :tienomee"),
        @NamedQuery(name = "Opermee.findByDptoomee",
                    query = "SELECT o FROM Opermee o WHERE o.dptoomee = :dptoomee"),
        @NamedQuery(name = "Opermee.findByOperomee",
                    query = "SELECT o FROM Opermee o WHERE o.operomee = :operomee"),
        @NamedQuery(name = "Opermee.findByStatomee",
                    query = "SELECT o FROM Opermee o WHERE o.statomee = :statomee"),
        @NamedQuery(name = "Opermee.findByOdcoomee",
                    query = "SELECT o FROM Opermee o WHERE o.odcoomee = :odcoomee"),
        @NamedQuery(name = "Opermee.findByNrecomee",
                    query = "SELECT o FROM Opermee o WHERE o.opermeePK.nrecomee = :nrecomee"),
        @NamedQuery(name = "Opermee.findByFirmomee",
                    query = "SELECT o FROM Opermee o WHERE o.firmomee = :firmomee"),
        @NamedQuery(name = "Opermee.findByFiaromee",
                    query = "SELECT o FROM Opermee o WHERE o.fiaromee = :fiaromee"),
        @NamedQuery(name = "Opermee.findByFiavomee",
                    query = "SELECT o FROM Opermee o WHERE o.fiavomee = :fiavomee"),
        @NamedQuery(name = "Opermee.findByFeetomee",
                    query = "SELECT o FROM Opermee o WHERE o.feetomee = :feetomee"),
        @NamedQuery(name = "Opermee.findByFeenomee",
                    query = "SELECT o FROM Opermee o WHERE o.feenomee = :feenomee"),
        @NamedQuery(name = "Opermee.findByFep1omee",
                    query = "SELECT o FROM Opermee o WHERE o.fep1omee = :fep1omee"),
        @NamedQuery(name = "Opermee.findByFep2omee",
                    query = "SELECT o FROM Opermee o WHERE o.fep2omee = :fep2omee"),
        @NamedQuery(name = "Opermee.findByCodiomee",
                    query = "SELECT o FROM Opermee o WHERE o.codiomee = :codiomee"),
        @NamedQuery(name = "Opermee.findByFeauomee",
                    query = "SELECT o FROM Opermee o WHERE o.feauomee = :feauomee"),
        @NamedQuery(name = "Opermee.findByFiauomee",
                    query = "SELECT o FROM Opermee o WHERE o.fiauomee = :fiauomee"),
        @NamedQuery(name = "Opermee.findBySta2omee",
                    query = "SELECT o FROM Opermee o WHERE o.sta2omee = :sta2omee"),
        @NamedQuery(name = "Opermee.findByFec2omee",
                    query = "SELECT o FROM Opermee o WHERE o.fec2omee = :fec2omee")})
public class Opermee implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    protected OpermeePK opermeePK;

    @Basic(optional = false)
    @Column(name = "DPTOOMEE")
    private String dptoomee;

    @Basic(optional = false)
    @Column(name = "OPEROMEE")
    private String operomee;

    @Basic(optional = false)
    @Column(name = "STATOMEE")
    private Character statomee;

    @Basic(optional = false)
    @Column(name = "ODCOOMEE")
    private long odcoomee;

    @Basic(optional = false)
    @Column(name = "FIRMOMEE")
    private int firmomee;

    @Basic(optional = false)
    @Column(name = "FIAROMEE")
    private int fiaromee;

    @Basic(optional = false)
    @Column(name = "FIAVOMEE")
    private int fiavomee;

    @Basic(optional = false)
    @Column(name = "FEETOMEE")
    @Temporal(TemporalType.DATE)
    private Date feetomee;

    @Basic(optional = false)
    @Column(name = "FEENOMEE")
    @Temporal(TemporalType.DATE)
    private Date feenomee;

    @Basic(optional = false)
    @Column(name = "FEP1OMEE")
    @Temporal(TemporalType.DATE)
    private Date fep1omee;

    @Basic(optional = false)
    @Column(name = "FEP2OMEE")
    @Temporal(TemporalType.DATE)
    private Date fep2omee;

    @Basic(optional = false)
    @Column(name = "CODIOMEE")
    private short codiomee;

    @Basic(optional = false)
    @Column(name = "FEAUOMEE")
    @Temporal(TemporalType.DATE)
    private Date feauomee;

    @Basic(optional = false)
    @Column(name = "FIAUOMEE")
    private int fiauomee;

    @Basic(optional = false)
    @Column(name = "STA2OMEE")
    private Character sta2omee;

    @Basic(optional = false)
    @Column(name = "FEC2OMEE")
    @Temporal(TemporalType.DATE)
    private Date fec2omee;

    public Opermee() {
    }

    public Opermee(OpermeePK opermeePK) {
        this.opermeePK = opermeePK;
    }

    public Opermee(OpermeePK opermeePK, String dptoomee, String operomee, Character statomee, long odcoomee,
                   int firmomee, int fiaromee, int fiavomee, Date feetomee, Date feenomee, Date fep1omee,
                   Date fep2omee, short codiomee, Date feauomee, int fiauomee, Character sta2omee, Date fec2omee) {
        this.opermeePK = opermeePK;
        this.dptoomee = dptoomee;
        this.operomee = operomee;
        this.statomee = statomee;
        this.odcoomee = odcoomee;
        this.firmomee = firmomee;
        this.fiaromee = fiaromee;
        this.fiavomee = fiavomee;
        this.feetomee = feetomee;
        this.feenomee = feenomee;
        this.fep1omee = fep1omee;
        this.fep2omee = fep2omee;
        this.codiomee = codiomee;
        this.feauomee = feauomee;
        this.fiauomee = fiauomee;
        this.sta2omee = sta2omee;
        this.fec2omee = fec2omee;
    }

    public Opermee(String tienomee, int nrecomee) {
        this.opermeePK = new OpermeePK(tienomee, nrecomee);
    }

    public OpermeePK getOpermeePK() {
        return opermeePK;
    }

    public void setOpermeePK(OpermeePK opermeePK) {
        this.opermeePK = opermeePK;
    }

    public String getDptoomee() {
        return dptoomee;
    }

    public void setDptoomee(String dptoomee) {
        this.dptoomee = dptoomee;
    }

    public String getOperomee() {
        return operomee;
    }

    public void setOperomee(String operomee) {
        this.operomee = operomee;
    }

    public Character getStatomee() {
        return statomee;
    }

    public void setStatomee(Character statomee) {
        this.statomee = statomee;
    }

    public long getOdcoomee() {
        return odcoomee;
    }

    public void setOdcoomee(long odcoomee) {
        this.odcoomee = odcoomee;
    }

    public int getFirmomee() {
        return firmomee;
    }

    public void setFirmomee(int firmomee) {
        this.firmomee = firmomee;
    }

    public int getFiaromee() {
        return fiaromee;
    }

    public void setFiaromee(int fiaromee) {
        this.fiaromee = fiaromee;
    }

    public int getFiavomee() {
        return fiavomee;
    }

    public void setFiavomee(int fiavomee) {
        this.fiavomee = fiavomee;
    }

    public Date getFeetomee() {
        return feetomee;
    }

    public void setFeetomee(Date feetomee) {
        this.feetomee = feetomee;
    }

    public Date getFeenomee() {
        return feenomee;
    }

    public void setFeenomee(Date feenomee) {
        this.feenomee = feenomee;
    }

    public Date getFep1omee() {
        return fep1omee;
    }

    public void setFep1omee(Date fep1omee) {
        this.fep1omee = fep1omee;
    }

    public Date getFep2omee() {
        return fep2omee;
    }

    public void setFep2omee(Date fep2omee) {
        this.fep2omee = fep2omee;
    }

    public short getCodiomee() {
        return codiomee;
    }

    public void setCodiomee(short codiomee) {
        this.codiomee = codiomee;
    }

    public Date getFeauomee() {
        return feauomee;
    }

    public void setFeauomee(Date feauomee) {
        this.feauomee = feauomee;
    }

    public int getFiauomee() {
        return fiauomee;
    }

    public void setFiauomee(int fiauomee) {
        this.fiauomee = fiauomee;
    }

    public Character getSta2omee() {
        return sta2omee;
    }

    public void setSta2omee(Character sta2omee) {
        this.sta2omee = sta2omee;
    }

    public Date getFec2omee() {
        return fec2omee;
    }

    public void setFec2omee(Date fec2omee) {
        this.fec2omee = fec2omee;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (opermeePK != null ? opermeePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Opermee)) {
            return false;
        }
        Opermee other = (Opermee) object;
        if ((this.opermeePK == null && other.opermeePK != null)
                || (this.opermeePK != null && !this.opermeePK.equals(other.opermeePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.grid.ventas.cr.crjpaventassrc.Opermee[ opermeePK=" + opermeePK + " ]";
    }

}
