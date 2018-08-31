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
@Table(name = "OPEVCPR")
@XmlRootElement
@NamedQueries({@NamedQuery(name = "Opevcpr.findAll",
                           query = "SELECT o FROM Opevcpr o"),
        @NamedQuery(name = "Opevcpr.findByStatocpr",
                    query = "SELECT o FROM Opevcpr o WHERE o.statocpr = :statocpr"),
        @NamedQuery(name = "Opevcpr.findByEmprocpr",
                    query = "SELECT o FROM Opevcpr o WHERE o.opevcprPK.emprocpr = :emprocpr"),
        @NamedQuery(name = "Opevcpr.findByCtrlocpr",
                    query = "SELECT o FROM Opevcpr o WHERE o.opevcprPK.ctrlocpr = :ctrlocpr"),
        @NamedQuery(name = "Opevcpr.findByTienocpr",
                    query = "SELECT o FROM Opevcpr o WHERE o.opevcprPK.tienocpr = :tienocpr"),
        @NamedQuery(name = "Opevcpr.findByCdadocpr",
                    query = "SELECT o FROM Opevcpr o WHERE o.opevcprPK.cdadocpr = :cdadocpr"),
        @NamedQuery(name = "Opevcpr.findByCopaocpr",
                    query = "SELECT o FROM Opevcpr o WHERE o.copaocpr = :copaocpr"),
        @NamedQuery(name = "Opevcpr.findByFecpocpr",
                    query = "SELECT o FROM Opevcpr o WHERE o.fecpocpr = :fecpocpr"),
        @NamedQuery(name = "Opevcpr.findByHoraocpr",
                    query = "SELECT o FROM Opevcpr o WHERE o.horaocpr = :horaocpr"),
        @NamedQuery(name = "Opevcpr.findByUsuaocpr",
                    query = "SELECT o FROM Opevcpr o WHERE o.usuaocpr = :usuaocpr")})
public class Opevcpr implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    protected OpevcprPK opevcprPK;

    @Basic(optional = false)
    @Column(name = "STATOCPR")
    private Character statocpr;

    // @Max(value=?) @Min(value=?)//if you know range of your decimal fields consider
    // using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "COPAOCPR")
    private BigDecimal copaocpr;

    @Basic(optional = false)
    @Column(name = "FECPOCPR")
    @Temporal(TemporalType.DATE)
    private Date fecpocpr;

    @Basic(optional = false)
    @Column(name = "HORAOCPR")
    @Temporal(TemporalType.TIME)
    private Date horaocpr;

    @Basic(optional = false)
    @Column(name = "USUAOCPR")
    private String usuaocpr;

    public Opevcpr() {
    }

    public Opevcpr(OpevcprPK opevcprPK) {
        this.opevcprPK = opevcprPK;
    }

    public Opevcpr(OpevcprPK opevcprPK, Character statocpr, BigDecimal copaocpr, Date fecpocpr, Date horaocpr,
                   String usuaocpr) {
        this.opevcprPK = opevcprPK;
        this.statocpr = statocpr;
        this.copaocpr = copaocpr;
        this.fecpocpr = fecpocpr;
        this.horaocpr = horaocpr;
        this.usuaocpr = usuaocpr;
    }

    public Opevcpr(String emprocpr, String ctrlocpr, String tienocpr, int cdadocpr) {
        this.opevcprPK = new OpevcprPK(emprocpr, ctrlocpr, tienocpr, cdadocpr);
    }

    public OpevcprPK getOpevcprPK() {
        return opevcprPK;
    }

    public void setOpevcprPK(OpevcprPK opevcprPK) {
        this.opevcprPK = opevcprPK;
    }

    public Character getStatocpr() {
        return statocpr;
    }

    public void setStatocpr(Character statocpr) {
        this.statocpr = statocpr;
    }

    public BigDecimal getCopaocpr() {
        return copaocpr;
    }

    public void setCopaocpr(BigDecimal copaocpr) {
        this.copaocpr = copaocpr;
    }

    public Date getFecpocpr() {
        return fecpocpr;
    }

    public void setFecpocpr(Date fecpocpr) {
        this.fecpocpr = fecpocpr;
    }

    public Date getHoraocpr() {
        return horaocpr;
    }

    public void setHoraocpr(Date horaocpr) {
        this.horaocpr = horaocpr;
    }

    public String getUsuaocpr() {
        return usuaocpr;
    }

    public void setUsuaocpr(String usuaocpr) {
        this.usuaocpr = usuaocpr;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (opevcprPK != null ? opevcprPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Opevcpr)) {
            return false;
        }
        Opevcpr other = (Opevcpr) object;
        if ((this.opevcprPK == null && other.opevcprPK != null)
                || (this.opevcprPK != null && !this.opevcprPK.equals(other.opevcprPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.grid.ventas.cr.crjpaventassrc.Opevcpr[ opevcprPK=" + opevcprPK + " ]";
    }

}
