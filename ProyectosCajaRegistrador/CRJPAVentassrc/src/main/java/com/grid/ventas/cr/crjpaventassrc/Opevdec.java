/*
 * To change this license header, choose License Headers in Project Properties. To change
 * this template file, choose Tools | Templates and open the template in the editor.
 */
package com.grid.ventas.cr.crjpaventassrc;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "OPEVDEC")
@XmlRootElement
@NamedQueries({@NamedQuery(name = "Opevdec.findAll",
                           query = "SELECT o FROM Opevdec o"),
        @NamedQuery(name = "Opevdec.findByNtievdec",
                    query = "SELECT o FROM Opevdec o WHERE o.opevdecPK.ntievdec = :ntievdec"),
        @NamedQuery(name = "Opevdec.findByNcajvdec",
                    query = "SELECT o FROM Opevdec o WHERE o.opevdecPK.ncajvdec = :ncajvdec"),
        @NamedQuery(name = "Opevdec.findByNopevdec",
                    query = "SELECT o FROM Opevdec o WHERE o.opevdecPK.nopevdec = :nopevdec"),
        @NamedQuery(name = "Opevdec.findByNacrvdec",
                    query = "SELECT o FROM Opevdec o WHERE o.opevdecPK.nacrvdec = :nacrvdec"),
        @NamedQuery(name = "Opevdec.findByFechvdec",
                    query = "SELECT o FROM Opevdec o WHERE o.opevdecPK.fechvdec = :fechvdec"),
        @NamedQuery(name = "Opevdec.findByCdfpvdec",
                    query = "SELECT o FROM Opevdec o WHERE o.opevdecPK.cdfpvdec = :cdfpvdec"),
        @NamedQuery(name = "Opevdec.findByCorrvdec",
                    query = "SELECT o FROM Opevdec o WHERE o.opevdecPK.corrvdec = :corrvdec"),
        @NamedQuery(name = "Opevdec.findByMontvdec",
                    query = "SELECT o FROM Opevdec o WHERE o.montvdec = :montvdec"),
        @NamedQuery(name = "Opevdec.findByCbcovdec",
                    query = "SELECT o FROM Opevdec o WHERE o.cbcovdec = :cbcovdec"),
        @NamedQuery(name = "Opevdec.findByNdtovdec",
                    query = "SELECT o FROM Opevdec o WHERE o.ndtovdec = :ndtovdec"),
        @NamedQuery(name = "Opevdec.findByNctavdec",
                    query = "SELECT o FROM Opevdec o WHERE o.nctavdec = :nctavdec"),
        @NamedQuery(name = "Opevdec.findByCclivdec",
                    query = "SELECT o FROM Opevdec o WHERE o.opevdecPK.cclivdec = :cclivdec"),
        @NamedQuery(name = "Opevdec.findBySregvdec",
                    query = "SELECT o FROM Opevdec o WHERE o.sregvdec = :sregvdec"),
        @NamedQuery(name = "Opevdec.findByNcfovdec",
                    query = "SELECT o FROM Opevdec o WHERE o.ncfovdec = :ncfovdec"),
        @NamedQuery(name = "Opevdec.findByNrefvdec",
                    query = "SELECT o FROM Opevdec o WHERE o.nrefvdec = :nrefvdec"),
        @NamedQuery(name = "Opevdec.findByCeduvdec",
                    query = "SELECT o FROM Opevdec o WHERE o.ceduvdec = :ceduvdec"),
        @NamedQuery(name = "Opevdec.findByTacrvdec",
                    query = "SELECT o FROM Opevdec o WHERE o.tacrvdec = :tacrvdec"),
        @NamedQuery(name = "Opevdec.findByConfvdec",
                    query = "SELECT o FROM Opevdec o WHERE o.confvdec = :confvdec"),
        @NamedQuery(name = "Opevdec.findByFectvdec",
                    query = "SELECT o FROM Opevdec o WHERE o.fectvdec = :fectvdec"),
        @NamedQuery(name = "Opevdec.findByFeccvdec",
                    query = "SELECT o FROM Opevdec o WHERE o.feccvdec = :feccvdec"),
        @NamedQuery(name = "Opevdec.findByEspgvdec",
                    query = "SELECT o FROM Opevdec o WHERE o.espgvdec = :espgvdec"),
        @NamedQuery(name = "Opevdec.findByTpdmvdec",
                    query = "SELECT o FROM Opevdec o WHERE o.tpdmvdec = :tpdmvdec"),
        @NamedQuery(name = "Opevdec.findByNmdivdec",
                    query = "SELECT o FROM Opevdec o WHERE o.nmdivdec = :nmdivdec"),
        @NamedQuery(name = "Opevdec.findByCoprvdec",
                    query = "SELECT o FROM Opevdec o WHERE o.coprvdec = :coprvdec")})
public class Opevdec implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    protected OpevdecPK opevdecPK;

    // @Max(value=?) @Min(value=?)//if you know range of your decimal fields consider
    // using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "MONTVDEC")
    private BigDecimal montvdec;

    @Basic(optional = false)
    @Column(name = "CBCOVDEC")
    private String cbcovdec;

    @Basic(optional = false)
    @Column(name = "NDTOVDEC")
    private String ndtovdec;

    @Basic(optional = false)
    @Column(name = "NCTAVDEC")
    private String nctavdec;

    @Basic(optional = false)
    @Column(name = "SREGVDEC")
    private Character sregvdec;

    @Basic(optional = false)
    @Column(name = "NCFOVDEC")
    private int ncfovdec;

    @Basic(optional = false)
    @Column(name = "NREFVDEC")
    private int nrefvdec;

    @Basic(optional = true)
    @Column(name = "CEDUVDEC")
    private String ceduvdec;

    @Basic(optional = false)
    @Column(name = "TACRVDEC")
    private Character tacrvdec;

    @Basic(optional = false)
    @Column(name = "CONFVDEC")
    private Character confvdec;

    @Column(name = "FECTVDEC")
    @Temporal(TemporalType.DATE)
    private Date fectvdec;

    @Column(name = "FECCVDEC")
    @Temporal(TemporalType.DATE)
    private Date feccvdec;

    @Basic(optional = false)
    @Column(name = "ESPGVDEC")
    private Character espgvdec;

    @Column(name = "TPDMVDEC")
    private String tpdmvdec;

    @Column(name = "NMDIVDEC")
    private Long nmdivdec;

    @Column(name = "COPRVDEC")
    private Integer coprvdec;

    public Opevdec() {
    }

    public Opevdec(OpevdecPK opevdecPK) {
        this.opevdecPK = opevdecPK;
    }

    public Opevdec(OpevdecPK opevdecPK, BigDecimal montvdec, String cbcovdec, String ndtovdec, String nctavdec,
                   Character sregvdec, int ncfovdec, int nrefvdec, String ceduvdec, Character tacrvdec,
                   Character confvdec, Character espgvdec) {
        this.opevdecPK = opevdecPK;
        this.montvdec = montvdec;
        this.cbcovdec = cbcovdec;
        this.ndtovdec = ndtovdec;
        this.nctavdec = nctavdec;
        this.sregvdec = sregvdec;
        this.ncfovdec = ncfovdec;
        this.nrefvdec = nrefvdec;
        this.ceduvdec = ceduvdec;
        this.tacrvdec = tacrvdec;
        this.confvdec = confvdec;
        this.espgvdec = espgvdec;
    }

    public Opevdec(short ntievdec, short ncajvdec, int nopevdec, long nacrvdec, Date fechvdec, String cdfpvdec,
                   short corrvdec, String cclivdec) {
        this.opevdecPK = new OpevdecPK(ntievdec, ncajvdec, nopevdec, nacrvdec, fechvdec, cdfpvdec, corrvdec,
                cclivdec);
    }

    public OpevdecPK getOpevdecPK() {
        return opevdecPK;
    }

    public void setOpevdecPK(OpevdecPK opevdecPK) {
        this.opevdecPK = opevdecPK;
    }

    public BigDecimal getMontvdec() {
        return montvdec;
    }

    public void setMontvdec(BigDecimal montvdec) {
        this.montvdec = montvdec;
    }

    public String getCbcovdec() {
        return cbcovdec;
    }

    public void setCbcovdec(String cbcovdec) {
        this.cbcovdec = cbcovdec;
    }

    public String getNdtovdec() {
        return ndtovdec;
    }

    public void setNdtovdec(String ndtovdec) {
        this.ndtovdec = ndtovdec;
    }

    public String getNctavdec() {
        return nctavdec;
    }

    public void setNctavdec(String nctavdec) {
        this.nctavdec = nctavdec;
    }

    public Character getSregvdec() {
        return sregvdec;
    }

    public void setSregvdec(Character sregvdec) {
        this.sregvdec = sregvdec;
    }

    public int getNcfovdec() {
        return ncfovdec;
    }

    public void setNcfovdec(int ncfovdec) {
        this.ncfovdec = ncfovdec;
    }

    public int getNrefvdec() {
        return nrefvdec;
    }

    public void setNrefvdec(int nrefvdec) {
        this.nrefvdec = nrefvdec;
    }

    public String getCeduvdec() {
        return ceduvdec;
    }

    public void setCeduvdec(String ceduvdec) {
        this.ceduvdec = ceduvdec;
    }

    public Character getTacrvdec() {
        return tacrvdec;
    }

    public void setTacrvdec(Character tacrvdec) {
        this.tacrvdec = tacrvdec;
    }

    public Character getConfvdec() {
        return confvdec;
    }

    public void setConfvdec(Character confvdec) {
        this.confvdec = confvdec;
    }

    public Date getFectvdec() {
        return fectvdec;
    }

    public void setFectvdec(Date fectvdec) {
        this.fectvdec = fectvdec;
    }

    public Date getFeccvdec() {
        return feccvdec;
    }

    public void setFeccvdec(Date feccvdec) {
        this.feccvdec = feccvdec;
    }

    public Character getEspgvdec() {
        return espgvdec;
    }

    public void setEspgvdec(Character espgvdec) {
        this.espgvdec = espgvdec;
    }

    public String getTpdmvdec() {
        return tpdmvdec;
    }

    public void setTpdmvdec(String tpdmvdec) {
        this.tpdmvdec = tpdmvdec;
    }

    public Long getNmdivdec() {
        return nmdivdec;
    }

    public void setNmdivdec(Long nmdivdec) {
        this.nmdivdec = nmdivdec;
    }

    public Integer getCoprvdec() {
        return coprvdec;
    }

    public void setCoprvdec(Integer coprvdec) {
        this.coprvdec = coprvdec;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (opevdecPK != null ? opevdecPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Opevdec)) {
            return false;
        }
        Opevdec other = (Opevdec) object;
        if ((this.opevdecPK == null && other.opevdecPK != null)
                || (this.opevdecPK != null && !this.opevdecPK.equals(other.opevdecPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.grid.ventas.cr.crjpaventassrc.Opevdec[ opevdecPK=" + opevdecPK + " ]";
    }

}
