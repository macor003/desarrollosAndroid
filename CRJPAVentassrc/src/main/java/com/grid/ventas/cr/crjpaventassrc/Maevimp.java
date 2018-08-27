/*
 * To change this license header, choose License Headers in Project Properties. To change
 * this template file, choose Tools | Templates and open the template in the editor.
 */
package com.grid.ventas.cr.crjpaventassrc;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

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
@Table(name = "MAEVIMP")
@XmlRootElement
@NamedQueries({@NamedQuery(name = "Maevimp.findAll",
                           query = "SELECT m FROM Maevimp m"),
        @NamedQuery(name = "Maevimp.findByTienmimp",
                    query = "SELECT m FROM Maevimp m WHERE m.maevimpPK.tienmimp = :tienmimp"),
        @NamedQuery(name = "Maevimp.findByFecimimp",
                    query = "SELECT m FROM Maevimp m WHERE m.maevimpPK.fecimimp = :fecimimp"),
        @NamedQuery(name = "Maevimp.findByFecfmimp",
                    query = "SELECT m FROM Maevimp m WHERE m.fecfmimp = :fecfmimp"),
        @NamedQuery(name = "Maevimp.findByAivamimp",
                    query = "SELECT m FROM Maevimp m WHERE m.aivamimp = :aivamimp")})
public class Maevimp implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    protected MaevimpPK maevimpPK;

    @Column(name = "FECFMIMP")
    @Temporal(TemporalType.DATE)
    private Date fecfmimp;

    // @Max(value=?) @Min(value=?)//if you know range of your decimal fields consider
    // using these annotations to enforce field validation
    @Column(name = "AIVAMIMP")
    private BigDecimal aivamimp;

    public Maevimp() {
    }

    public Maevimp(MaevimpPK maevimpPK) {
        this.maevimpPK = maevimpPK;
    }

    public Maevimp(String tienmimp, Date fecimimp) {
        this.maevimpPK = new MaevimpPK(tienmimp, fecimimp);
    }

    public MaevimpPK getMaevimpPK() {
        return maevimpPK;
    }

    public void setMaevimpPK(MaevimpPK maevimpPK) {
        this.maevimpPK = maevimpPK;
    }

    public Date getFecfmimp() {
        return fecfmimp;
    }

    public void setFecfmimp(Date fecfmimp) {
        this.fecfmimp = fecfmimp;
    }

    public BigDecimal getAivamimp() {
        return aivamimp;
    }

    public void setAivamimp(BigDecimal aivamimp) {
        this.aivamimp = aivamimp;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (maevimpPK != null ? maevimpPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Maevimp)) {
            return false;
        }
        Maevimp other = (Maevimp) object;
        if ((this.maevimpPK == null && other.maevimpPK != null)
                || (this.maevimpPK != null && !this.maevimpPK.equals(other.maevimpPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.grid.ventas.cr.crjpaventassrc.Maevimp[ maevimpPK=" + maevimpPK + " ]";
    }

}
