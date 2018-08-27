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
@Table(name = "CABVDES")
@XmlRootElement
@NamedQueries({@NamedQuery(name = "Cabvdes.findAll",
                           query = "SELECT c FROM Cabvdes c"),
        @NamedQuery(name = "Cabvdes.findByTiencdes",
                    query = "SELECT c FROM Cabvdes c WHERE c.cabvdesPK.tiencdes = :tiencdes"),
        @NamedQuery(name = "Cabvdes.findByNcorcdes",
                    query = "SELECT c FROM Cabvdes c WHERE c.cabvdesPK.ncorcdes = :ncorcdes"),
        @NamedQuery(name = "Cabvdes.findByFemicdes",
                    query = "SELECT c FROM Cabvdes c WHERE c.cabvdesPK.femicdes = :femicdes"),
        @NamedQuery(name = "Cabvdes.findByTrancdes",
                    query = "SELECT c FROM Cabvdes c WHERE c.cabvdesPK.trancdes = :trancdes"),
        @NamedQuery(name = "Cabvdes.findByCajacdes",
                    query = "SELECT c FROM Cabvdes c WHERE c.cajacdes = :cajacdes"),
        @NamedQuery(name = "Cabvdes.findByCltecdes",
                    query = "SELECT c FROM Cabvdes c WHERE c.cltecdes = :cltecdes"),
        @NamedQuery(name = "Cabvdes.findByNsercdes",
                    query = "SELECT c FROM Cabvdes c WHERE c.nsercdes = :nsercdes"),
        @NamedQuery(name = "Cabvdes.findByTdescdes",
                    query = "SELECT c FROM Cabvdes c WHERE c.cabvdesPK.tdescdes = :tdescdes"),
        @NamedQuery(name = "Cabvdes.findByHinicdes",
                    query = "SELECT c FROM Cabvdes c WHERE c.hinicdes = :hinicdes"),
        @NamedQuery(name = "Cabvdes.findByHfincdes",
                    query = "SELECT c FROM Cabvdes c WHERE c.hfincdes = :hfincdes"),
        @NamedQuery(name = "Cabvdes.findByTmpocdes",
                    query = "SELECT c FROM Cabvdes c WHERE c.tmpocdes = :tmpocdes"),
        @NamedQuery(name = "Cabvdes.findByStstcdes",
                    query = "SELECT c FROM Cabvdes c WHERE c.ststcdes = :ststcdes"),
        @NamedQuery(name = "Cabvdes.findByFactcdes",
                    query = "SELECT c FROM Cabvdes c WHERE c.factcdes = :factcdes"),
        @NamedQuery(name = "Cabvdes.findByHactcdes",
                    query = "SELECT c FROM Cabvdes c WHERE c.hactcdes = :hactcdes"),
        @NamedQuery(name = "Cabvdes.findByUactcdes",
                    query = "SELECT c FROM Cabvdes c WHERE c.uactcdes = :uactcdes")})
public class Cabvdes implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    protected CabvdesPK cabvdesPK;

    @Basic(optional = false)
    @Column(name = "CAJACDES")
    private short cajacdes;

    @Basic(optional = false)
    @Column(name = "CLTECDES")
    private String cltecdes;

    @Basic(optional = false)
    @Column(name = "NSERCDES")
    private int nsercdes;

    @Basic(optional = false)
    @Column(name = "HINICDES")
    @Temporal(TemporalType.TIME)
    private Date hinicdes;

    @Basic(optional = false)
    @Column(name = "HFINCDES")
    @Temporal(TemporalType.TIME)
    private Date hfincdes;

    @Basic(optional = false)
    @Column(name = "TMPOCDES")
    private short tmpocdes;

    @Basic(optional = false)
    @Column(name = "STSTCDES")
    private Character ststcdes;

    @Basic(optional = false)
    @Column(name = "FACTCDES")
    @Temporal(TemporalType.DATE)
    private Date factcdes;

    @Basic(optional = false)
    @Column(name = "HACTCDES")
    @Temporal(TemporalType.TIME)
    private Date hactcdes;

    @Basic(optional = false)
    @Column(name = "UACTCDES")
    private String uactcdes;

    public Cabvdes() {
    }

    public Cabvdes(CabvdesPK cabvdesPK) {
        this.cabvdesPK = cabvdesPK;
    }

    public Cabvdes(CabvdesPK cabvdesPK, short cajacdes, String cltecdes, int nsercdes, Date hinicdes,
                   Date hfincdes, short tmpocdes, Character ststcdes, Date factcdes, Date hactcdes,
                   String uactcdes) {
        this.cabvdesPK = cabvdesPK;
        this.cajacdes = cajacdes;
        this.cltecdes = cltecdes;
        this.nsercdes = nsercdes;
        this.hinicdes = hinicdes;
        this.hfincdes = hfincdes;
        this.tmpocdes = tmpocdes;
        this.ststcdes = ststcdes;
        this.factcdes = factcdes;
        this.hactcdes = hactcdes;
        this.uactcdes = uactcdes;
    }

    public Cabvdes(String tiencdes, int ncorcdes, Date femicdes, int trancdes, Character tdescdes) {
        this.cabvdesPK = new CabvdesPK(tiencdes, ncorcdes, femicdes, trancdes, tdescdes);
    }

    public CabvdesPK getCabvdesPK() {
        return cabvdesPK;
    }

    public void setCabvdesPK(CabvdesPK cabvdesPK) {
        this.cabvdesPK = cabvdesPK;
    }

    public short getCajacdes() {
        return cajacdes;
    }

    public void setCajacdes(short cajacdes) {
        this.cajacdes = cajacdes;
    }

    public String getCltecdes() {
        return cltecdes;
    }

    public void setCltecdes(String cltecdes) {
        this.cltecdes = cltecdes;
    }

    public int getNsercdes() {
        return nsercdes;
    }

    public void setNsercdes(int nsercdes) {
        this.nsercdes = nsercdes;
    }

    public Date getHinicdes() {
        return hinicdes;
    }

    public void setHinicdes(Date hinicdes) {
        this.hinicdes = hinicdes;
    }

    public Date getHfincdes() {
        return hfincdes;
    }

    public void setHfincdes(Date hfincdes) {
        this.hfincdes = hfincdes;
    }

    public short getTmpocdes() {
        return tmpocdes;
    }

    public void setTmpocdes(short tmpocdes) {
        this.tmpocdes = tmpocdes;
    }

    public Character getStstcdes() {
        return ststcdes;
    }

    public void setStstcdes(Character ststcdes) {
        this.ststcdes = ststcdes;
    }

    public Date getFactcdes() {
        return factcdes;
    }

    public void setFactcdes(Date factcdes) {
        this.factcdes = factcdes;
    }

    public Date getHactcdes() {
        return hactcdes;
    }

    public void setHactcdes(Date hactcdes) {
        this.hactcdes = hactcdes;
    }

    public String getUactcdes() {
        return uactcdes;
    }

    public void setUactcdes(String uactcdes) {
        this.uactcdes = uactcdes;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cabvdesPK != null ? cabvdesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cabvdes)) {
            return false;
        }
        Cabvdes other = (Cabvdes) object;
        if ((this.cabvdesPK == null && other.cabvdesPK != null)
                || (this.cabvdesPK != null && !this.cabvdesPK.equals(other.cabvdesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.grid.ventas.cr.crjpaventassrc.Cabvdes[ cabvdesPK=" + cabvdesPK + " ]";
    }

}
