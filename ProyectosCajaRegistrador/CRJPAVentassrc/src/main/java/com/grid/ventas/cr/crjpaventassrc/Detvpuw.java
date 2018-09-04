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
@Table(name = "DETVPUW")
@XmlRootElement
@NamedQueries({@NamedQuery(name = "Detvpuw.findAll",
                           query = "SELECT d FROM Detvpuw d"),
        @NamedQuery(name = "Detvpuw.findByIdtpdntc",
                    query = "SELECT d FROM Detvpuw d WHERE d.detvpuwPK.idtpdntc = :idtpdntc"),
        @NamedQuery(name = "Detvpuw.findByIdtcdntc",
                    query = "SELECT d FROM Detvpuw d WHERE d.idtcdntc = :idtcdntc"),
        @NamedQuery(name = "Detvpuw.findByCedpdntc",
                    query = "SELECT d FROM Detvpuw d WHERE d.cedpdntc = :cedpdntc"),
        @NamedQuery(name = "Detvpuw.findByTipidntc",
                    query = "SELECT d FROM Detvpuw d WHERE d.tipidntc = :tipidntc"),
        @NamedQuery(name = "Detvpuw.findByTiendntc",
                    query = "SELECT d FROM Detvpuw d WHERE d.detvpuwPK.tiendntc = :tiendntc"),
        @NamedQuery(name = "Detvpuw.findByNomcdntc",
                    query = "SELECT d FROM Detvpuw d WHERE d.nomcdntc = :nomcdntc"),
        @NamedQuery(name = "Detvpuw.findByTpladntc",
                    query = "SELECT d FROM Detvpuw d WHERE d.tpladntc = :tpladntc"),
        @NamedQuery(name = "Detvpuw.findByPnomdntc",
                    query = "SELECT d FROM Detvpuw d WHERE d.pnomdntc = :pnomdntc"),
        @NamedQuery(name = "Detvpuw.findByPaccdntc",
                    query = "SELECT d FROM Detvpuw d WHERE d.paccdntc = :paccdntc"),
        @NamedQuery(name = "Detvpuw.findByPsoldntc",
                    query = "SELECT d FROM Detvpuw d WHERE d.psoldntc = :psoldntc"),
        @NamedQuery(name = "Detvpuw.findByPrtcdntc",
                    query = "SELECT d FROM Detvpuw d WHERE d.prtcdntc = :prtcdntc"),
        @NamedQuery(name = "Detvpuw.findByEmaldntc",
                    query = "SELECT d FROM Detvpuw d WHERE d.emaldntc = :emaldntc"),
        @NamedQuery(name = "Detvpuw.findByFechdntc",
                    query = "SELECT d FROM Detvpuw d WHERE d.fechdntc = :fechdntc"),
        @NamedQuery(name = "Detvpuw.findByStatdntc",
                    query = "SELECT d FROM Detvpuw d WHERE d.statdntc = :statdntc")})
public class Detvpuw implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    protected DetvpuwPK detvpuwPK;

    @Basic(optional = false)
    @Column(name = "IDTCDNTC")
    private int idtcdntc;

    @Basic(optional = false)
    @Column(name = "CEDPDNTC")
    private String cedpdntc;

    @Basic(optional = false)
    @Column(name = "TIPIDNTC")
    private short tipidntc;

    @Column(name = "NOMCDNTC")
    private String nomcdntc;

    @Basic(optional = false)
    @Column(name = "TPLADNTC")
    private Character tpladntc;

    @Column(name = "PNOMDNTC")
    private String pnomdntc;

    @Basic(optional = false)
    @Column(name = "PACCDNTC")
    private short paccdntc;

    @Basic(optional = false)
    @Column(name = "PSOLDNTC")
    private short psoldntc;

    @Basic(optional = false)
    @Column(name = "PRTCDNTC")
    private String prtcdntc;

    @Column(name = "EMALDNTC")
    private String emaldntc;

    @Column(name = "FECHDNTC")
    @Temporal(TemporalType.DATE)
    private Date fechdntc;

    @Column(name = "STATDNTC")
    private Character statdntc;

    public Detvpuw() {
    }

    public Detvpuw(DetvpuwPK detvpuwPK) {
        this.detvpuwPK = detvpuwPK;
    }

    public Detvpuw(DetvpuwPK detvpuwPK, int idtcdntc, String cedpdntc, short tipidntc, Character tpladntc,
                   short paccdntc, short psoldntc, String prtcdntc) {
        this.detvpuwPK = detvpuwPK;
        this.idtcdntc = idtcdntc;
        this.cedpdntc = cedpdntc;
        this.tipidntc = tipidntc;
        this.tpladntc = tpladntc;
        this.paccdntc = paccdntc;
        this.psoldntc = psoldntc;
        this.prtcdntc = prtcdntc;
    }

    public Detvpuw(int idtpdntc, String tiendntc) {
        this.detvpuwPK = new DetvpuwPK(idtpdntc, tiendntc);
    }

    public DetvpuwPK getDetvpuwPK() {
        return detvpuwPK;
    }

    public void setDetvpuwPK(DetvpuwPK detvpuwPK) {
        this.detvpuwPK = detvpuwPK;
    }

    public int getIdtcdntc() {
        return idtcdntc;
    }

    public void setIdtcdntc(int idtcdntc) {
        this.idtcdntc = idtcdntc;
    }

    public String getCedpdntc() {
        return cedpdntc;
    }

    public void setCedpdntc(String cedpdntc) {
        this.cedpdntc = cedpdntc;
    }

    public short getTipidntc() {
        return tipidntc;
    }

    public void setTipidntc(short tipidntc) {
        this.tipidntc = tipidntc;
    }

    public String getNomcdntc() {
        return nomcdntc;
    }

    public void setNomcdntc(String nomcdntc) {
        this.nomcdntc = nomcdntc;
    }

    public Character getTpladntc() {
        return tpladntc;
    }

    public void setTpladntc(Character tpladntc) {
        this.tpladntc = tpladntc;
    }

    public String getPnomdntc() {
        return pnomdntc;
    }

    public void setPnomdntc(String pnomdntc) {
        this.pnomdntc = pnomdntc;
    }

    public short getPaccdntc() {
        return paccdntc;
    }

    public void setPaccdntc(short paccdntc) {
        this.paccdntc = paccdntc;
    }

    public short getPsoldntc() {
        return psoldntc;
    }

    public void setPsoldntc(short psoldntc) {
        this.psoldntc = psoldntc;
    }

    public String getPrtcdntc() {
        return prtcdntc;
    }

    public void setPrtcdntc(String prtcdntc) {
        this.prtcdntc = prtcdntc;
    }

    public String getEmaldntc() {
        return emaldntc;
    }

    public void setEmaldntc(String emaldntc) {
        this.emaldntc = emaldntc;
    }

    public Date getFechdntc() {
        return fechdntc;
    }

    public void setFechdntc(Date fechdntc) {
        this.fechdntc = fechdntc;
    }

    public Character getStatdntc() {
        return statdntc;
    }

    public void setStatdntc(Character statdntc) {
        this.statdntc = statdntc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detvpuwPK != null ? detvpuwPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detvpuw)) {
            return false;
        }
        Detvpuw other = (Detvpuw) object;
        if ((this.detvpuwPK == null && other.detvpuwPK != null)
                || (this.detvpuwPK != null && !this.detvpuwPK.equals(other.detvpuwPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.grid.ventas.cr.crjpaventassrc.Detvpuw[ detvpuwPK=" + detvpuwPK + " ]";
    }

}
