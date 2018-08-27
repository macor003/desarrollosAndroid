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
@Table(name = "OPEVMCP")
@XmlRootElement
@NamedQueries({@NamedQuery(name = "Opevmcp.findAll",
                           query = "SELECT o FROM Opevmcp o"),
        @NamedQuery(name = "Opevmcp.findByTdamvmcp",
                    query = "SELECT o FROM Opevmcp o WHERE o.opevmcpPK.tdamvmcp = :tdamvmcp"),
        @NamedQuery(name = "Opevmcp.findByTpdmvmcp",
                    query = "SELECT o FROM Opevmcp o WHERE o.opevmcpPK.tpdmvmcp = :tpdmvmcp"),
        @NamedQuery(name = "Opevmcp.findByNmdivmcp",
                    query = "SELECT o FROM Opevmcp o WHERE o.opevmcpPK.nmdivmcp = :nmdivmcp"),
        @NamedQuery(name = "Opevmcp.findByFecmvmcp",
                    query = "SELECT o FROM Opevmcp o WHERE o.fecmvmcp = :fecmvmcp"),
        @NamedQuery(name = "Opevmcp.findByConcvmcp",
                    query = "SELECT o FROM Opevmcp o WHERE o.concvmcp = :concvmcp"),
        @NamedQuery(name = "Opevmcp.findByCodfvmcp",
                    query = "SELECT o FROM Opevmcp o WHERE o.codfvmcp = :codfvmcp"),
        @NamedQuery(name = "Opevmcp.findByCoddvmcp",
                    query = "SELECT o FROM Opevmcp o WHERE o.coddvmcp = :coddvmcp"),
        @NamedQuery(name = "Opevmcp.findByNmdevmcp",
                    query = "SELECT o FROM Opevmcp o WHERE o.nmdevmcp = :nmdevmcp"),
        @NamedQuery(name = "Opevmcp.findByMontvmcp",
                    query = "SELECT o FROM Opevmcp o WHERE o.montvmcp = :montvmcp"),
        @NamedQuery(name = "Opevmcp.findByRespvmcp",
                    query = "SELECT o FROM Opevmcp o WHERE o.respvmcp = :respvmcp"),
        @NamedQuery(name = "Opevmcp.findByUsuavmcp",
                    query = "SELECT o FROM Opevmcp o WHERE o.usuavmcp = :usuavmcp"),
        @NamedQuery(name = "Opevmcp.findByEsrgvmcp",
                    query = "SELECT o FROM Opevmcp o WHERE o.esrgvmcp = :esrgvmcp"),
        @NamedQuery(name = "Opevmcp.findByBancvmcp",
                    query = "SELECT o FROM Opevmcp o WHERE o.bancvmcp = :bancvmcp"),
        @NamedQuery(name = "Opevmcp.findByFichvmcp",
                    query = "SELECT o FROM Opevmcp o WHERE o.fichvmcp = :fichvmcp"),
        @NamedQuery(name = "Opevmcp.findByFecdvmcp",
                    query = "SELECT o FROM Opevmcp o WHERE o.fecdvmcp = :fecdvmcp"),
        @NamedQuery(name = "Opevmcp.findByTdatvmcp",
                    query = "SELECT o FROM Opevmcp o WHERE o.tdatvmcp = :tdatvmcp"),
        @NamedQuery(name = "Opevmcp.findByMondvmcp",
                    query = "SELECT o FROM Opevmcp o WHERE o.mondvmcp = :mondvmcp"),
        @NamedQuery(name = "Opevmcp.findByTdevmcp",
                    query = "SELECT o FROM Opevmcp o WHERE o.tdevmcp = :tdevmcp"),
        @NamedQuery(name = "Opevmcp.findByNdevmcp",
                    query = "SELECT o FROM Opevmcp o WHERE o.ndevmcp = :ndevmcp")})
public class Opevmcp implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    protected OpevmcpPK opevmcpPK;

    @Basic(optional = false)
    @Column(name = "FECMVMCP")
    @Temporal(TemporalType.DATE)
    private Date fecmvmcp;

    @Basic(optional = false)
    @Column(name = "CONCVMCP")
    private String concvmcp;

    @Basic(optional = false)
    @Column(name = "CODFVMCP")
    private short codfvmcp;

    @Basic(optional = false)
    @Column(name = "CODDVMCP")
    private short coddvmcp;

    @Basic(optional = false)
    @Column(name = "NMDEVMCP")
    private String nmdevmcp;

    // @Max(value=?) @Min(value=?)//if you know range of your decimal fields consider
    // using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "MONTVMCP")
    private BigDecimal montvmcp;

    @Basic(optional = false)
    @Column(name = "RESPVMCP")
    private String respvmcp;

    @Basic(optional = false)
    @Column(name = "USUAVMCP")
    private String usuavmcp;

    @Basic(optional = false)
    @Column(name = "ESRGVMCP")
    private Character esrgvmcp;

    @Basic(optional = false)
    @Column(name = "BANCVMCP")
    private String bancvmcp;

    @Basic(optional = false)
    @Column(name = "FICHVMCP")
    private int fichvmcp;

    @Basic(optional = false)
    @Column(name = "FECDVMCP")
    @Temporal(TemporalType.DATE)
    private Date fecdvmcp;

    @Basic(optional = false)
    @Column(name = "TDATVMCP")
    private String tdatvmcp;

    @Basic(optional = false)
    @Column(name = "MONDVMCP")
    private BigDecimal mondvmcp;

    @Basic(optional = false)
    @Column(name = "TDEVMCP")
    private short tdevmcp;

    @Basic(optional = false)
    @Column(name = "NDEVMCP")
    private BigInteger ndevmcp;

    public Opevmcp() {
    }

    public Opevmcp(OpevmcpPK opevmcpPK) {
        this.opevmcpPK = opevmcpPK;
    }

    public Opevmcp(OpevmcpPK opevmcpPK, Date fecmvmcp, String concvmcp, short codfvmcp, short coddvmcp,
                   String nmdevmcp, BigDecimal montvmcp, String respvmcp, String usuavmcp, Character esrgvmcp,
                   String bancvmcp, int fichvmcp, Date fecdvmcp, String tdatvmcp, BigDecimal mondvmcp,
                   short tdevmcp, BigInteger ndevmcp) {
        this.opevmcpPK = opevmcpPK;
        this.fecmvmcp = fecmvmcp;
        this.concvmcp = concvmcp;
        this.codfvmcp = codfvmcp;
        this.coddvmcp = coddvmcp;
        this.nmdevmcp = nmdevmcp;
        this.montvmcp = montvmcp;
        this.respvmcp = respvmcp;
        this.usuavmcp = usuavmcp;
        this.esrgvmcp = esrgvmcp;
        this.bancvmcp = bancvmcp;
        this.fichvmcp = fichvmcp;
        this.fecdvmcp = fecdvmcp;
        this.tdatvmcp = tdatvmcp;
        this.mondvmcp = mondvmcp;
        this.tdevmcp = tdevmcp;
        this.ndevmcp = ndevmcp;
    }

    public Opevmcp(String tdamvmcp, String tpdmvmcp, long nmdivmcp) {
        this.opevmcpPK = new OpevmcpPK(tdamvmcp, tpdmvmcp, nmdivmcp);
    }

    public OpevmcpPK getOpevmcpPK() {
        return opevmcpPK;
    }

    public void setOpevmcpPK(OpevmcpPK opevmcpPK) {
        this.opevmcpPK = opevmcpPK;
    }

    public Date getFecmvmcp() {
        return fecmvmcp;
    }

    public void setFecmvmcp(Date fecmvmcp) {
        this.fecmvmcp = fecmvmcp;
    }

    public String getConcvmcp() {
        return concvmcp;
    }

    public void setConcvmcp(String concvmcp) {
        this.concvmcp = concvmcp;
    }

    public short getCodfvmcp() {
        return codfvmcp;
    }

    public void setCodfvmcp(short codfvmcp) {
        this.codfvmcp = codfvmcp;
    }

    public short getCoddvmcp() {
        return coddvmcp;
    }

    public void setCoddvmcp(short coddvmcp) {
        this.coddvmcp = coddvmcp;
    }

    public String getNmdevmcp() {
        return nmdevmcp;
    }

    public void setNmdevmcp(String nmdevmcp) {
        this.nmdevmcp = nmdevmcp;
    }

    public BigDecimal getMontvmcp() {
        return montvmcp;
    }

    public void setMontvmcp(BigDecimal montvmcp) {
        this.montvmcp = montvmcp;
    }

    public String getRespvmcp() {
        return respvmcp;
    }

    public void setRespvmcp(String respvmcp) {
        this.respvmcp = respvmcp;
    }

    public String getUsuavmcp() {
        return usuavmcp;
    }

    public void setUsuavmcp(String usuavmcp) {
        this.usuavmcp = usuavmcp;
    }

    public Character getEsrgvmcp() {
        return esrgvmcp;
    }

    public void setEsrgvmcp(Character esrgvmcp) {
        this.esrgvmcp = esrgvmcp;
    }

    public String getBancvmcp() {
        return bancvmcp;
    }

    public void setBancvmcp(String bancvmcp) {
        this.bancvmcp = bancvmcp;
    }

    public int getFichvmcp() {
        return fichvmcp;
    }

    public void setFichvmcp(int fichvmcp) {
        this.fichvmcp = fichvmcp;
    }

    public Date getFecdvmcp() {
        return fecdvmcp;
    }

    public void setFecdvmcp(Date fecdvmcp) {
        this.fecdvmcp = fecdvmcp;
    }

    public String getTdatvmcp() {
        return tdatvmcp;
    }

    public void setTdatvmcp(String tdatvmcp) {
        this.tdatvmcp = tdatvmcp;
    }

    public BigDecimal getMondvmcp() {
        return mondvmcp;
    }

    public void setMondvmcp(BigDecimal mondvmcp) {
        this.mondvmcp = mondvmcp;
    }

    public short getTdevmcp() {
        return tdevmcp;
    }

    public void setTdevmcp(short tdevmcp) {
        this.tdevmcp = tdevmcp;
    }

    public BigInteger getNdevmcp() {
        return ndevmcp;
    }

    public void setNdevmcp(BigInteger ndevmcp) {
        this.ndevmcp = ndevmcp;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (opevmcpPK != null ? opevmcpPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Opevmcp)) {
            return false;
        }
        Opevmcp other = (Opevmcp) object;
        if ((this.opevmcpPK == null && other.opevmcpPK != null)
                || (this.opevmcpPK != null && !this.opevmcpPK.equals(other.opevmcpPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.grid.ventas.cr.crjpaventassrc.Opevmcp[ opevmcpPK=" + opevmcpPK + " ]";
    }

}
