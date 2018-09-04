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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "TEMCABVACR")
@XmlRootElement
@NamedQueries({@NamedQuery(name = "Temcabvacr.findAll",
                           query = "SELECT t FROM Temcabvacr t"),
        @NamedQuery(name = "Temcabvacr.findById",
                    query = "SELECT t FROM Temcabvacr t WHERE t.id = :id"),
        @NamedQuery(name = "Temcabvacr.findByNumacacr",
                    query = "SELECT t FROM Temcabvacr t WHERE t.numacacr = :numacacr"),
        @NamedQuery(name = "Temcabvacr.findByClientacacr",
                    query = "SELECT t FROM Temcabvacr t WHERE t.clientacacr = :clientacacr"),
        @NamedQuery(name = "Temcabvacr.findBySaldacacr",
                    query = "SELECT t FROM Temcabvacr t WHERE t.saldacacr = :saldacacr"),
        @NamedQuery(name = "Temcabvacr.findByEdoacacr",
                    query = "SELECT t FROM Temcabvacr t WHERE t.edoacacr = :edoacacr"),
        @NamedQuery(name = "Temcabvacr.findBySaldtrcacr",
                    query = "SELECT t FROM Temcabvacr t WHERE t.saldtrcacr = :saldtrcacr"),
        @NamedQuery(name = "Temcabvacr.findByReplcacr",
                    query = "SELECT t FROM Temcabvacr t WHERE t.replcacr = :replcacr"),
        @NamedQuery(name = "Temcabvacr.findByFechareg",
                    query = "SELECT t FROM Temcabvacr t WHERE t.fechareg = :fechareg")})
public class Temcabvacr implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;

    @Basic(optional = false)
    @Column(name = "NUMACACR")
    private long numacacr;

    @Basic(optional = false)
    @Column(name = "CLIENTACACR")
    private String clientacacr;

    // @Max(value=?) @Min(value=?)//if you know range of your decimal fields consider
    // using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "SALDACACR")
    private BigDecimal saldacacr;

    @Basic(optional = false)
    @Column(name = "EDOACACR")
    private Character edoacacr;

    @Basic(optional = false)
    @Column(name = "SALDTRCACR")
    private BigDecimal saldtrcacr;

    @Column(name = "REPLCACR")
    private Character replcacr;

    @Column(name = "FECHAREG")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechareg;

    public Temcabvacr() {
    }

    public Temcabvacr(Long id) {
        this.id = id;
    }

    public Temcabvacr(Long id, long numacacr, String clientacacr, BigDecimal saldacacr, Character edoacacr,
                      BigDecimal saldtrcacr) {
        this.id = id;
        this.numacacr = numacacr;
        this.clientacacr = clientacacr;
        this.saldacacr = saldacacr;
        this.edoacacr = edoacacr;
        this.saldtrcacr = saldtrcacr;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getNumacacr() {
        return numacacr;
    }

    public void setNumacacr(long numacacr) {
        this.numacacr = numacacr;
    }

    public String getClientacacr() {
        return clientacacr;
    }

    public void setClientacacr(String clientacacr) {
        this.clientacacr = clientacacr;
    }

    public BigDecimal getSaldacacr() {
        return saldacacr;
    }

    public void setSaldacacr(BigDecimal saldacacr) {
        this.saldacacr = saldacacr;
    }

    public Character getEdoacacr() {
        return edoacacr;
    }

    public void setEdoacacr(Character edoacacr) {
        this.edoacacr = edoacacr;
    }

    public BigDecimal getSaldtrcacr() {
        return saldtrcacr;
    }

    public void setSaldtrcacr(BigDecimal saldtrcacr) {
        this.saldtrcacr = saldtrcacr;
    }

    public Character getReplcacr() {
        return replcacr;
    }

    public void setReplcacr(Character replcacr) {
        this.replcacr = replcacr;
    }

    public Date getFechareg() {
        return fechareg;
    }

    public void setFechareg(Date fechareg) {
        this.fechareg = fechareg;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Temcabvacr)) {
            return false;
        }
        Temcabvacr other = (Temcabvacr) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.grid.ventas.cr.crjpaventassrc.Temcabvacr[ id=" + id + " ]";
    }

}
