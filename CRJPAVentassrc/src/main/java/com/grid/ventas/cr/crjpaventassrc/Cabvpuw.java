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
@Table(name = "CABVPUW")
@XmlRootElement
@NamedQueries({@NamedQuery(name = "Cabvpuw.findAll",
                           query = "SELECT c FROM Cabvpuw c"),
        @NamedQuery(name = "Cabvpuw.findByIdtccntc",
                    query = "SELECT c FROM Cabvpuw c WHERE c.cabvpuwPK.idtccntc = :idtccntc"),
        @NamedQuery(name = "Cabvpuw.findByNaficntc",
                    query = "SELECT c FROM Cabvpuw c WHERE c.naficntc = :naficntc"),
        @NamedQuery(name = "Cabvpuw.findByFichcntc",
                    query = "SELECT c FROM Cabvpuw c WHERE c.fichcntc = :fichcntc"),
        @NamedQuery(name = "Cabvpuw.findByFecncntc",
                    query = "SELECT c FROM Cabvpuw c WHERE c.fecncntc = :fecncntc"),
        @NamedQuery(name = "Cabvpuw.findByFingcntc",
                    query = "SELECT c FROM Cabvpuw c WHERE c.fingcntc = :fingcntc"),
        @NamedQuery(name = "Cabvpuw.findByCeducntc",
                    query = "SELECT c FROM Cabvpuw c WHERE c.ceducntc = :ceducntc"),
        @NamedQuery(name = "Cabvpuw.findByNomccntc",
                    query = "SELECT c FROM Cabvpuw c WHERE c.nomccntc = :nomccntc"),
        @NamedQuery(name = "Cabvpuw.findByNomlcntc",
                    query = "SELECT c FROM Cabvpuw c WHERE c.nomlcntc = :nomlcntc"),
        @NamedQuery(name = "Cabvpuw.findByDirccntc",
                    query = "SELECT c FROM Cabvpuw c WHERE c.dirccntc = :dirccntc"),
        @NamedQuery(name = "Cabvpuw.findByDrc1cntc",
                    query = "SELECT c FROM Cabvpuw c WHERE c.drc1cntc = :drc1cntc"),
        @NamedQuery(name = "Cabvpuw.findByDrc2cntc",
                    query = "SELECT c FROM Cabvpuw c WHERE c.drc2cntc = :drc2cntc"),
        @NamedQuery(name = "Cabvpuw.findByDirpcntc",
                    query = "SELECT c FROM Cabvpuw c WHERE c.dirpcntc = :dirpcntc"),
        @NamedQuery(name = "Cabvpuw.findByDrp1cntc",
                    query = "SELECT c FROM Cabvpuw c WHERE c.drp1cntc = :drp1cntc"),
        @NamedQuery(name = "Cabvpuw.findByDrp2cntc",
                    query = "SELECT c FROM Cabvpuw c WHERE c.drp2cntc = :drp2cntc"),
        @NamedQuery(name = "Cabvpuw.findByCedocntc",
                    query = "SELECT c FROM Cabvpuw c WHERE c.cedocntc = :cedocntc"),
        @NamedQuery(name = "Cabvpuw.findByCedpcntc",
                    query = "SELECT c FROM Cabvpuw c WHERE c.cedpcntc = :cedpcntc"),
        @NamedQuery(name = "Cabvpuw.findByTelhcntc",
                    query = "SELECT c FROM Cabvpuw c WHERE c.telhcntc = :telhcntc"),
        @NamedQuery(name = "Cabvpuw.findByNacicntc",
                    query = "SELECT c FROM Cabvpuw c WHERE c.nacicntc = :nacicntc"),
        @NamedQuery(name = "Cabvpuw.findByIdiocntc",
                    query = "SELECT c FROM Cabvpuw c WHERE c.idiocntc = :idiocntc"),
        @NamedQuery(name = "Cabvpuw.findByTiencntc",
                    query = "SELECT c FROM Cabvpuw c WHERE c.cabvpuwPK.tiencntc = :tiencntc"),
        @NamedQuery(name = "Cabvpuw.findByIdeccntc",
                    query = "SELECT c FROM Cabvpuw c WHERE c.ideccntc = :ideccntc"),
        @NamedQuery(name = "Cabvpuw.findByIdepcntc",
                    query = "SELECT c FROM Cabvpuw c WHERE c.idepcntc = :idepcntc"),
        @NamedQuery(name = "Cabvpuw.findByTipicntc",
                    query = "SELECT c FROM Cabvpuw c WHERE c.tipicntc = :tipicntc"),
        @NamedQuery(name = "Cabvpuw.findByEcivcntc",
                    query = "SELECT c FROM Cabvpuw c WHERE c.ecivcntc = :ecivcntc"),
        @NamedQuery(name = "Cabvpuw.findByLinccntc",
                    query = "SELECT c FROM Cabvpuw c WHERE c.linccntc = :linccntc"),
        @NamedQuery(name = "Cabvpuw.findByDcorcntc",
                    query = "SELECT c FROM Cabvpuw c WHERE c.dcorcntc = :dcorcntc"),
        @NamedQuery(name = "Cabvpuw.findByMismcntc",
                    query = "SELECT c FROM Cabvpuw c WHERE c.mismcntc = :mismcntc"),
        @NamedQuery(name = "Cabvpuw.findByTedocntc",
                    query = "SELECT c FROM Cabvpuw c WHERE c.tedocntc = :tedocntc"),
        @NamedQuery(name = "Cabvpuw.findByFexpcntc",
                    query = "SELECT c FROM Cabvpuw c WHERE c.fexpcntc = :fexpcntc"),
        @NamedQuery(name = "Cabvpuw.findByIndccntc",
                    query = "SELECT c FROM Cabvpuw c WHERE c.indccntc = :indccntc"),
        @NamedQuery(name = "Cabvpuw.findByFecocntc",
                    query = "SELECT c FROM Cabvpuw c WHERE c.fecocntc = :fecocntc"),
        @NamedQuery(name = "Cabvpuw.findByEmpocntc",
                    query = "SELECT c FROM Cabvpuw c WHERE c.empocntc = :empocntc"),
        @NamedQuery(name = "Cabvpuw.findByTelocntc",
                    query = "SELECT c FROM Cabvpuw c WHERE c.telocntc = :telocntc"),
        @NamedQuery(name = "Cabvpuw.findByTiptcntc",
                    query = "SELECT c FROM Cabvpuw c WHERE c.tiptcntc = :tiptcntc"),
        @NamedQuery(name = "Cabvpuw.findByTippcntc",
                    query = "SELECT c FROM Cabvpuw c WHERE c.tippcntc = :tippcntc"),
        @NamedQuery(name = "Cabvpuw.findByClaccntc",
                    query = "SELECT c FROM Cabvpuw c WHERE c.claccntc = :claccntc"),
        @NamedQuery(name = "Cabvpuw.findByStatcntc",
                    query = "SELECT c FROM Cabvpuw c WHERE c.statcntc = :statcntc"),
        @NamedQuery(name = "Cabvpuw.findByFechcntc",
                    query = "SELECT c FROM Cabvpuw c WHERE c.fechcntc = :fechcntc"),
        @NamedQuery(name = "Cabvpuw.findByTelccntc",
                    query = "SELECT c FROM Cabvpuw c WHERE c.telccntc = :telccntc"),
        @NamedQuery(name = "Cabvpuw.findByNumtcntc",
                    query = "SELECT c FROM Cabvpuw c WHERE c.numtcntc = :numtcntc"),
        @NamedQuery(name = "Cabvpuw.findByRe1ncntc",
                    query = "SELECT c FROM Cabvpuw c WHERE c.re1ncntc = :re1ncntc"),
        @NamedQuery(name = "Cabvpuw.findByRe1tcntc",
                    query = "SELECT c FROM Cabvpuw c WHERE c.re1tcntc = :re1tcntc"),
        @NamedQuery(name = "Cabvpuw.findByRe1ccntc",
                    query = "SELECT c FROM Cabvpuw c WHERE c.re1ccntc = :re1ccntc"),
        @NamedQuery(name = "Cabvpuw.findByRe2ncntc",
                    query = "SELECT c FROM Cabvpuw c WHERE c.re2ncntc = :re2ncntc"),
        @NamedQuery(name = "Cabvpuw.findByRe2tcntc",
                    query = "SELECT c FROM Cabvpuw c WHERE c.re2tcntc = :re2tcntc"),
        @NamedQuery(name = "Cabvpuw.findByRe2ccntc",
                    query = "SELECT c FROM Cabvpuw c WHERE c.re2ccntc = :re2ccntc")})
public class Cabvpuw implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    protected CabvpuwPK cabvpuwPK;

    @Basic(optional = false)
    @Column(name = "NAFICNTC")
    private String naficntc;

    @Column(name = "FICHCNTC")
    private String fichcntc;

    @Column(name = "FECNCNTC")
    @Temporal(TemporalType.DATE)
    private Date fecncntc;

    @Basic(optional = false)
    @Column(name = "FINGCNTC")
    @Temporal(TemporalType.DATE)
    private Date fingcntc;

    @Basic(optional = false)
    @Column(name = "CEDUCNTC")
    private String ceducntc;

    @Basic(optional = false)
    @Column(name = "NOMCCNTC")
    private String nomccntc;

    @Basic(optional = false)
    @Column(name = "NOMLCNTC")
    private String nomlcntc;

    @Basic(optional = false)
    @Column(name = "DIRCCNTC")
    private String dirccntc;

    @Column(name = "DRC1CNTC")
    private String drc1cntc;

    @Column(name = "DRC2CNTC")
    private String drc2cntc;

    @Basic(optional = false)
    @Column(name = "DIRPCNTC")
    private String dirpcntc;

    @Column(name = "DRP1CNTC")
    private String drp1cntc;

    @Column(name = "DRP2CNTC")
    private String drp2cntc;

    @Basic(optional = false)
    @Column(name = "CEDOCNTC")
    private int cedocntc;

    @Basic(optional = false)
    @Column(name = "CEDPCNTC")
    private int cedpcntc;

    @Column(name = "TELHCNTC")
    private Long telhcntc;

    @Basic(optional = false)
    @Column(name = "NACICNTC")
    private Character nacicntc;

    @Basic(optional = false)
    @Column(name = "IDIOCNTC")
    private Character idiocntc;

    @Basic(optional = false)
    @Column(name = "IDECCNTC")
    private Character ideccntc;

    @Basic(optional = false)
    @Column(name = "IDEPCNTC")
    private Character idepcntc;

    @Basic(optional = false)
    @Column(name = "TIPICNTC")
    private short tipicntc;

    @Column(name = "ECIVCNTC")
    private Character ecivcntc;

    @Basic(optional = false)
    @Column(name = "LINCCNTC")
    private int linccntc;

    @Basic(optional = false)
    @Column(name = "DCORCNTC")
    private short dcorcntc;

    @Basic(optional = false)
    @Column(name = "MISMCNTC")
    private short mismcntc;

    @Basic(optional = false)
    @Column(name = "TEDOCNTC")
    private short tedocntc;

    @Basic(optional = false)
    @Column(name = "FEXPCNTC")
    @Temporal(TemporalType.DATE)
    private Date fexpcntc;

    @Basic(optional = false)
    @Column(name = "INDCCNTC")
    private short indccntc;

    @Column(name = "FECOCNTC")
    @Temporal(TemporalType.DATE)
    private Date fecocntc;

    @Basic(optional = false)
    @Column(name = "EMPOCNTC")
    private String empocntc;

    @Column(name = "TELOCNTC")
    private Long telocntc;

    @Basic(optional = false)
    @Column(name = "TIPTCNTC")
    private short tiptcntc;

    @Basic(optional = false)
    @Column(name = "TIPPCNTC")
    private short tippcntc;

    @Basic(optional = false)
    @Column(name = "CLACCNTC")
    private String claccntc;

    @Column(name = "STATCNTC")
    private Character statcntc;

    @Column(name = "FECHCNTC")
    @Temporal(TemporalType.DATE)
    private Date fechcntc;

    @Column(name = "TELCCNTC")
    private Long telccntc;

    @Column(name = "NUMTCNTC")
    private Long numtcntc;

    @Column(name = "RE1NCNTC")
    private String re1ncntc;

    @Column(name = "RE1TCNTC")
    private Long re1tcntc;

    @Column(name = "RE1CCNTC")
    private String re1ccntc;

    @Column(name = "RE2NCNTC")
    private String re2ncntc;

    @Column(name = "RE2TCNTC")
    private Long re2tcntc;

    @Column(name = "RE2CCNTC")
    private String re2ccntc;

    public Cabvpuw() {
    }

    public Cabvpuw(CabvpuwPK cabvpuwPK) {
        this.cabvpuwPK = cabvpuwPK;
    }

    public Cabvpuw(CabvpuwPK cabvpuwPK, String naficntc, Date fingcntc, String ceducntc, String nomccntc,
                   String nomlcntc, String dirccntc, String dirpcntc, int cedocntc, int cedpcntc,
                   Character nacicntc, Character idiocntc, Character ideccntc, Character idepcntc, short tipicntc,
                   int linccntc, short dcorcntc, short mismcntc, short tedocntc, Date fexpcntc, short indccntc,
                   String empocntc, short tiptcntc, short tippcntc, String claccntc) {
        this.cabvpuwPK = cabvpuwPK;
        this.naficntc = naficntc;
        this.fingcntc = fingcntc;
        this.ceducntc = ceducntc;
        this.nomccntc = nomccntc;
        this.nomlcntc = nomlcntc;
        this.dirccntc = dirccntc;
        this.dirpcntc = dirpcntc;
        this.cedocntc = cedocntc;
        this.cedpcntc = cedpcntc;
        this.nacicntc = nacicntc;
        this.idiocntc = idiocntc;
        this.ideccntc = ideccntc;
        this.idepcntc = idepcntc;
        this.tipicntc = tipicntc;
        this.linccntc = linccntc;
        this.dcorcntc = dcorcntc;
        this.mismcntc = mismcntc;
        this.tedocntc = tedocntc;
        this.fexpcntc = fexpcntc;
        this.indccntc = indccntc;
        this.empocntc = empocntc;
        this.tiptcntc = tiptcntc;
        this.tippcntc = tippcntc;
        this.claccntc = claccntc;
    }

    public Cabvpuw(int idtccntc, String tiencntc) {
        this.cabvpuwPK = new CabvpuwPK(idtccntc, tiencntc);
    }

    public CabvpuwPK getCabvpuwPK() {
        return cabvpuwPK;
    }

    public void setCabvpuwPK(CabvpuwPK cabvpuwPK) {
        this.cabvpuwPK = cabvpuwPK;
    }

    public String getNaficntc() {
        return naficntc;
    }

    public void setNaficntc(String naficntc) {
        this.naficntc = naficntc;
    }

    public String getFichcntc() {
        return fichcntc;
    }

    public void setFichcntc(String fichcntc) {
        this.fichcntc = fichcntc;
    }

    public Date getFecncntc() {
        return fecncntc;
    }

    public void setFecncntc(Date fecncntc) {
        this.fecncntc = fecncntc;
    }

    public Date getFingcntc() {
        return fingcntc;
    }

    public void setFingcntc(Date fingcntc) {
        this.fingcntc = fingcntc;
    }

    public String getCeducntc() {
        return ceducntc;
    }

    public void setCeducntc(String ceducntc) {
        this.ceducntc = ceducntc;
    }

    public String getNomccntc() {
        return nomccntc;
    }

    public void setNomccntc(String nomccntc) {
        this.nomccntc = nomccntc;
    }

    public String getNomlcntc() {
        return nomlcntc;
    }

    public void setNomlcntc(String nomlcntc) {
        this.nomlcntc = nomlcntc;
    }

    public String getDirccntc() {
        return dirccntc;
    }

    public void setDirccntc(String dirccntc) {
        this.dirccntc = dirccntc;
    }

    public String getDrc1cntc() {
        return drc1cntc;
    }

    public void setDrc1cntc(String drc1cntc) {
        this.drc1cntc = drc1cntc;
    }

    public String getDrc2cntc() {
        return drc2cntc;
    }

    public void setDrc2cntc(String drc2cntc) {
        this.drc2cntc = drc2cntc;
    }

    public String getDirpcntc() {
        return dirpcntc;
    }

    public void setDirpcntc(String dirpcntc) {
        this.dirpcntc = dirpcntc;
    }

    public String getDrp1cntc() {
        return drp1cntc;
    }

    public void setDrp1cntc(String drp1cntc) {
        this.drp1cntc = drp1cntc;
    }

    public String getDrp2cntc() {
        return drp2cntc;
    }

    public void setDrp2cntc(String drp2cntc) {
        this.drp2cntc = drp2cntc;
    }

    public int getCedocntc() {
        return cedocntc;
    }

    public void setCedocntc(int cedocntc) {
        this.cedocntc = cedocntc;
    }

    public int getCedpcntc() {
        return cedpcntc;
    }

    public void setCedpcntc(int cedpcntc) {
        this.cedpcntc = cedpcntc;
    }

    public Long getTelhcntc() {
        return telhcntc;
    }

    public void setTelhcntc(Long telhcntc) {
        this.telhcntc = telhcntc;
    }

    public Character getNacicntc() {
        return nacicntc;
    }

    public void setNacicntc(Character nacicntc) {
        this.nacicntc = nacicntc;
    }

    public Character getIdiocntc() {
        return idiocntc;
    }

    public void setIdiocntc(Character idiocntc) {
        this.idiocntc = idiocntc;
    }

    public Character getIdeccntc() {
        return ideccntc;
    }

    public void setIdeccntc(Character ideccntc) {
        this.ideccntc = ideccntc;
    }

    public Character getIdepcntc() {
        return idepcntc;
    }

    public void setIdepcntc(Character idepcntc) {
        this.idepcntc = idepcntc;
    }

    public short getTipicntc() {
        return tipicntc;
    }

    public void setTipicntc(short tipicntc) {
        this.tipicntc = tipicntc;
    }

    public Character getEcivcntc() {
        return ecivcntc;
    }

    public void setEcivcntc(Character ecivcntc) {
        this.ecivcntc = ecivcntc;
    }

    public int getLinccntc() {
        return linccntc;
    }

    public void setLinccntc(int linccntc) {
        this.linccntc = linccntc;
    }

    public short getDcorcntc() {
        return dcorcntc;
    }

    public void setDcorcntc(short dcorcntc) {
        this.dcorcntc = dcorcntc;
    }

    public short getMismcntc() {
        return mismcntc;
    }

    public void setMismcntc(short mismcntc) {
        this.mismcntc = mismcntc;
    }

    public short getTedocntc() {
        return tedocntc;
    }

    public void setTedocntc(short tedocntc) {
        this.tedocntc = tedocntc;
    }

    public Date getFexpcntc() {
        return fexpcntc;
    }

    public void setFexpcntc(Date fexpcntc) {
        this.fexpcntc = fexpcntc;
    }

    public short getIndccntc() {
        return indccntc;
    }

    public void setIndccntc(short indccntc) {
        this.indccntc = indccntc;
    }

    public Date getFecocntc() {
        return fecocntc;
    }

    public void setFecocntc(Date fecocntc) {
        this.fecocntc = fecocntc;
    }

    public String getEmpocntc() {
        return empocntc;
    }

    public void setEmpocntc(String empocntc) {
        this.empocntc = empocntc;
    }

    public Long getTelocntc() {
        return telocntc;
    }

    public void setTelocntc(Long telocntc) {
        this.telocntc = telocntc;
    }

    public short getTiptcntc() {
        return tiptcntc;
    }

    public void setTiptcntc(short tiptcntc) {
        this.tiptcntc = tiptcntc;
    }

    public short getTippcntc() {
        return tippcntc;
    }

    public void setTippcntc(short tippcntc) {
        this.tippcntc = tippcntc;
    }

    public String getClaccntc() {
        return claccntc;
    }

    public void setClaccntc(String claccntc) {
        this.claccntc = claccntc;
    }

    public Character getStatcntc() {
        return statcntc;
    }

    public void setStatcntc(Character statcntc) {
        this.statcntc = statcntc;
    }

    public Date getFechcntc() {
        return fechcntc;
    }

    public void setFechcntc(Date fechcntc) {
        this.fechcntc = fechcntc;
    }

    public Long getTelccntc() {
        return telccntc;
    }

    public void setTelccntc(Long telccntc) {
        this.telccntc = telccntc;
    }

    public Long getNumtcntc() {
        return numtcntc;
    }

    public void setNumtcntc(Long numtcntc) {
        this.numtcntc = numtcntc;
    }

    public String getRe1ncntc() {
        return re1ncntc;
    }

    public void setRe1ncntc(String re1ncntc) {
        this.re1ncntc = re1ncntc;
    }

    public Long getRe1tcntc() {
        return re1tcntc;
    }

    public void setRe1tcntc(Long re1tcntc) {
        this.re1tcntc = re1tcntc;
    }

    public String getRe1ccntc() {
        return re1ccntc;
    }

    public void setRe1ccntc(String re1ccntc) {
        this.re1ccntc = re1ccntc;
    }

    public String getRe2ncntc() {
        return re2ncntc;
    }

    public void setRe2ncntc(String re2ncntc) {
        this.re2ncntc = re2ncntc;
    }

    public Long getRe2tcntc() {
        return re2tcntc;
    }

    public void setRe2tcntc(Long re2tcntc) {
        this.re2tcntc = re2tcntc;
    }

    public String getRe2ccntc() {
        return re2ccntc;
    }

    public void setRe2ccntc(String re2ccntc) {
        this.re2ccntc = re2ccntc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cabvpuwPK != null ? cabvpuwPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cabvpuw)) {
            return false;
        }
        Cabvpuw other = (Cabvpuw) object;
        if ((this.cabvpuwPK == null && other.cabvpuwPK != null)
                || (this.cabvpuwPK != null && !this.cabvpuwPK.equals(other.cabvpuwPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.grid.ventas.cr.crjpaventassrc.Cabvpuw[ cabvpuwPK=" + cabvpuwPK + " ]";
    }

}
