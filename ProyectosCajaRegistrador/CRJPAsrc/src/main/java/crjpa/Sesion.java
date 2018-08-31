/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates and open the template in the editor.
 */
package crjpa;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * 
 * @author eve0014321
 * @version $Revision: 1.0 $
 */
@Entity
@Table(name = "sesion")
@XmlRootElement
@NamedQueries({@NamedQuery(name = "Sesion.findAll",
                           query = "SELECT s FROM Sesion s"),
        @NamedQuery(name = "Sesion.findById",
                    query = "SELECT s FROM Sesion s WHERE s.id = :id"),
        @NamedQuery(name = "Sesion.findByIdCaja",
                    query = "SELECT s FROM Sesion s WHERE s.idCaja = :idCaja"),
        @NamedQuery(name = "Sesion.findByVentas",
                    query = "SELECT s FROM Sesion s WHERE s.ventas = :ventas"),
        @NamedQuery(name = "Sesion.findByAnulaciones",
                    query = "SELECT s FROM Sesion s WHERE s.anulaciones = :anulaciones"),
        @NamedQuery(name = "Sesion.findByDevoluciones",
                    query = "SELECT s FROM Sesion s WHERE s.devoluciones = :devoluciones"),
        @NamedQuery(name = "Sesion.findByFechaInicio",
                    query = "SELECT s FROM Sesion s WHERE s.fechaInicio = :fechaInicio"),
        @NamedQuery(name = "Sesion.findByFechaCierre",
                    query = "SELECT s FROM Sesion s WHERE s.fechaCierre = :fechaCierre"),
        @NamedQuery(name = "Sesion.findByFechaCierreVpos",
                    query = "SELECT s FROM Sesion s WHERE s.fechaCierreVpos = :fechaCierreVpos"),
        @NamedQuery(name = "Sesion.findByFechaUltimoReportez",
                    query = "SELECT s FROM Sesion s WHERE s.fechaUltimoReportez = :fechaUltimoReportez"),
        @NamedQuery(name = "Sesion.findByEstasincronizado",
                    query = "SELECT s FROM Sesion s WHERE s.estasincronizado = :estasincronizado")})
public class Sesion implements Serializable {

    /**
     * Field serialVersionUID. (value is 1)
     */
    private static final long serialVersionUID = 1L;

    /**
     * Field id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,
                    generator = "1")
    @TableGenerator(name = "1",
                    table = "correlativo",
                    pkColumnName = "id",
                    valueColumnName = "valor",
                    pkColumnValue = "1",
                    allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "id",
            nullable = false)
    private Long id;

    /**
     * Field idCaja.
     */
    @Basic(optional = false)
    @Column(name = "id_caja",
            nullable = false)
    private int idCaja;

    /**
     * Field ventas.
     */
    @Basic(optional = false)
    @Column(name = "ventas",
            nullable = false)
    private int ventas;

    /**
     * Field anulaciones.
     */
    @Basic(optional = false)
    @Column(name = "anulaciones",
            nullable = false)
    private int anulaciones;

    /**
     * Field devoluciones.
     */
    @Basic(optional = false)
    @Column(name = "devoluciones",
            nullable = false)
    private int devoluciones;

    /**
     * Field fechaInicio.
     */
    @Basic(optional = false)
    @Column(name = "fecha_inicio",
            nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;

    /**
     * Field fechaCierre.
     */
    @Basic(optional = false)
    @Column(name = "fecha_cierre",
            nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCierre;

    /**
     * Field fechaCierreVpos.
     */
    @Basic(optional = false)
    @Column(name = "fecha_cierre_vpos",
            nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCierreVpos;

    /**
     * Field fechaUltimoReportez.
     */
    @Basic(optional = false)
    @Column(name = "fecha_ultimo_reportez",
            nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaUltimoReportez;

    /**
     * Field estasincronizado.
     */
    @Basic(optional = false)
    @Column(name = "estasincronizado",
            nullable = false,
            length = 2)
    private String estasincronizado;

    /**
     * Field versioncr.
     */
    @Basic(optional = false)
    @Column(name = "versioncr",
            nullable = true,
            length = 50)
    private String versioncr;

    @Column(name = "id_tienda")
    private Long idTienda;

    /**
     * Field entregaList.
     */
    @OneToMany(cascade = CascadeType.ALL,
               mappedBy = "idSesion")
    private List<Entrega> entregaList;

    /**
     * Field reportezList.
     */
    @OneToMany(cascade = CascadeType.ALL,
               mappedBy = "idSesion")
    private List<Reportez> reportezList;

    /**
     * Field idUsuario.
     */
    @JoinColumn(name = "id_usuario",
                referencedColumnName = "id",
                nullable = false)
    @ManyToOne(optional = false)
    private Usuario idUsuario;

    /**
     * Field transaccionList.
     */
    @OneToMany(cascade = CascadeType.ALL,
               mappedBy = "idSesion")
    private List<Transaccion> transaccionList;

    /**
     * Constructor for Sesion.
     */
    public Sesion() {
    }

    /**
     * Constructor for Sesion.
     * 
     * @param id Long
     */
    public Sesion(Long id) {
        this.id = id;
    }

    /**
     * Constructor for Sesion.
     * 
     * @param id Long
     * @param idCaja int
     * @param ventas int
     * @param anulaciones int
     * @param devoluciones int
     * @param fechaInicio Date
     * @param estasincronizado String
     */
    public Sesion(Long id, int idCaja, int ventas, int anulaciones, int devoluciones, Date fechaInicio,
                  String estasincronizado, Long idTienda) {
        this.id = id;
        this.idCaja = idCaja;
        this.ventas = ventas;
        this.anulaciones = anulaciones;
        this.devoluciones = devoluciones;
        this.fechaInicio = fechaInicio;
        this.estasincronizado = estasincronizado;
        this.idTienda = idTienda;
    }

    /**
     * Method getId.
     * 
     * @return Long
     */
    public Long getId() {
        return id;
    }

    /**
     * Method setId.
     * 
     * @param id Long
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Method getIdCaja.
     * 
     * @return int
     */
    public int getIdCaja() {
        return idCaja;
    }

    /**
     * Method setIdCaja.
     * 
     * @param idCaja int
     */
    public void setIdCaja(int idCaja) {
        this.idCaja = idCaja;
    }

    /**
     * Method getVentas.
     * 
     * @return int
     */
    public int getVentas() {
        return ventas;
    }

    /**
     * Method setVentas.
     * 
     * @param ventas int
     */
    public void setVentas(int ventas) {
        this.ventas = ventas;
    }

    /**
     * Method getAnulaciones.
     * 
     * @return int
     */
    public int getAnulaciones() {
        return anulaciones;
    }

    /**
     * Method setAnulaciones.
     * 
     * @param anulaciones int
     */
    public void setAnulaciones(int anulaciones) {
        this.anulaciones = anulaciones;
    }

    /**
     * Method getDevoluciones.
     * 
     * @return int
     */
    public int getDevoluciones() {
        return devoluciones;
    }

    /**
     * Method setDevoluciones.
     * 
     * @param devoluciones int
     */
    public void setDevoluciones(int devoluciones) {
        this.devoluciones = devoluciones;
    }

    /**
     * Method getFechaInicio.
     * 
     * @return Date
     */
    public Date getFechaInicio() {
        return fechaInicio;
    }

    /**
     * Method setFechaInicio.
     * 
     * @param fechaInicio Date
     */
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * Method getFechaCierre.
     * 
     * @return Date
     */
    public Date getFechaCierre() {
        return fechaCierre;
    }

    /**
     * Method setFechaCierre.
     * 
     * @param fechaCierre Date
     */
    public void setFechaCierre(Date fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    /**
     * Method getFechaCierreVpos.
     * 
     * @return Date
     */
    public Date getFechaCierreVpos() {
        return fechaCierreVpos;
    }

    /**
     * Method setFechaCierreVpos.
     * 
     * @param fechaCierreVpos Date
     */
    public void setFechaCierreVpos(Date fechaCierreVpos) {
        this.fechaCierreVpos = fechaCierreVpos;
    }

    /**
     * Method getFechaUltimoReportez.
     * 
     * @return Date
     */
    public Date getFechaUltimoReportez() {
        return fechaUltimoReportez;
    }

    /**
     * Method setFechaUltimoReportez.
     * 
     * @param fechaUltimoReportez Date
     */
    public void setFechaUltimoReportez(Date fechaUltimoReportez) {
        this.fechaUltimoReportez = fechaUltimoReportez;
    }

    /**
     * Method getEstasincronizado.
     * 
     * @return String
     */
    public String getEstasincronizado() {
        return estasincronizado;
    }

    /**
     * Method setEstasincronizado.
     * 
     * @param estasincronizado String
     */
    public void setEstasincronizado(String estasincronizado) {
        this.estasincronizado = estasincronizado;
    }

    /**
     * Method getEntregaList.
     * 
     * @return List<Entrega>
     */
    @XmlTransient
    public List<Entrega> getEntregaList() {
        return entregaList;
    }

    /**
     * Method setEntregaList.
     * 
     * @param entregaList List<Entrega>
     */
    public void setEntregaList(List<Entrega> entregaList) {
        this.entregaList = entregaList;
    }

    /**
     * Method getReportezList.
     * 
     * @return List<Reportez>
     */
    @XmlTransient
    public List<Reportez> getReportezList() {
        return reportezList;
    }

    /**
     * Method setReportezList.
     * 
     * @param reportezList List<Reportez>
     */
    public void setReportezList(List<Reportez> reportezList) {
        this.reportezList = reportezList;
    }

    /**
     * Method getIdUsuario.
     * 
     * @return Usuario
     */
    public Usuario getIdUsuario() {
        return idUsuario;
    }

    /**
     * Method setIdUsuario.
     * 
     * @param idUsuario Usuario
     */
    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * Method getTransaccionList.
     * 
     * @return List<Transaccion>
     */
    @XmlTransient
    public List<Transaccion> getTransaccionList() {
        return transaccionList;
    }

    /**
     * Method setTransaccionList.
     * 
     * @param transaccionList List<Transaccion>
     */
    public void setTransaccionList(List<Transaccion> transaccionList) {
        this.transaccionList = transaccionList;
    }

    /**
     * Method hashCode.
     * 
     * @return int
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    /**
     * Method equals.
     * 
     * @param object Object
     * @return boolean
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are
        // not set
        if (!(object instanceof Sesion)) {
            return false;
        }
        Sesion other = (Sesion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    /**
     * Method toString.
     * 
     * @return String
     */
    @Override
    public String toString() {
        return "crjpa.Sesion[ id=" + id + " ]";
    }

    /**
     * Method getVersioncr.
     * 
     * @return String
     */
    public String getVersioncr() {
        return versioncr;
    }

    /**
     * Method setVersioncr.
     * 
     * @param versioncr String
     */
    public void setVersioncr(String versioncr) {
        this.versioncr = versioncr;
    }

    public Long getIdTienda() {
        return idTienda;
    }

    public void setIdTienda(Long idTienda) {
        this.idTienda = idTienda;
    }

}
