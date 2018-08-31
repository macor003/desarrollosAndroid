/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package crjpa;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "acreenciamovimiento")
@XmlRootElement
@NamedQueries({@NamedQuery(name = "Acreenciamovimiento.findAll",
                           query = "SELECT a FROM Acreenciamovimiento a"),
        // @NamedQuery(name = "Acreenciamovimiento.findByIdAcreenciamovimientosaldo",
        // query = "SELECT a FROM Acreenciamovimiento a WHERE
        // a.acreenciamovimientoPK.idAcreenciamovimientosaldo =
        // :idAcreenciamovimientosaldo"),
        // @NamedQuery(name =
        // "Acreenciamovimiento.findByIdAcreenciamovimientoformadepago", query = "SELECT a
        // FROM Acreenciamovimiento a WHERE
        // a.acreenciamovimientoPK.idAcreenciamovimientoformadepago =
        // :idAcreenciamovimientoformadepago"),
        @NamedQuery(name = "Acreenciamovimiento.findByIdAcreencia",
                    query = "SELECT a FROM Acreenciamovimiento a WHERE a.idAcreencia = :idAcreencia"),
        @NamedQuery(name = "Acreenciamovimiento.findByDocumentoformadepago",
                    query = "SELECT a FROM Acreenciamovimiento a WHERE a.documentoformadepago = :documentoformadepago"),
        @NamedQuery(name = "Acreenciamovimiento.findByFecha",
                    query = "SELECT a FROM Acreenciamovimiento a WHERE a.fecha = :fecha"),
        @NamedQuery(name = "Acreenciamovimiento.findByNombreunidadnegocio",
                    query = "SELECT a FROM Acreenciamovimiento a WHERE a.nombreunidadnegocio = :nombreunidadnegocio"),
        @NamedQuery(name = "Acreenciamovimiento.findByNombreunidadoperativa",
                    query = "SELECT a FROM Acreenciamovimiento a WHERE a.nombreunidadoperativa = :nombreunidadoperativa"),
        @NamedQuery(name = "Acreenciamovimiento.findByTienda",
                    query = "SELECT a FROM Acreenciamovimiento a WHERE a.tienda = :tienda"),
        @NamedQuery(name = "Acreenciamovimiento.findByOperacion",
                    query = "SELECT a FROM Acreenciamovimiento a WHERE a.operacion = :operacion"),
        @NamedQuery(name = "Acreenciamovimiento.findByAnulaoperacion",
                    query = "SELECT a FROM Acreenciamovimiento a WHERE a.anulaoperacion = :anulaoperacion"),
        @NamedQuery(name = "Acreenciamovimiento.findByCaja",
                    query = "SELECT a FROM Acreenciamovimiento a WHERE a.caja = :caja"),
        @NamedQuery(name = "Acreenciamovimiento.findByTransaccion",
                    query = "SELECT a FROM Acreenciamovimiento a WHERE a.transaccion = :transaccion"),
        @NamedQuery(name = "Acreenciamovimiento.findByCajero",
                    query = "SELECT a FROM Acreenciamovimiento a WHERE a.cajero = :cajero"),
        @NamedQuery(name = "Acreenciamovimiento.findByRecibodecaja",
                    query = "SELECT a FROM Acreenciamovimiento a WHERE a.recibodecaja = :recibodecaja"),
        @NamedQuery(name = "Acreenciamovimiento.findByMontoMonedaLocal",
                    query = "SELECT a FROM Acreenciamovimiento a WHERE a.montoMonedaLocal = :montoMonedaLocal"),
        @NamedQuery(name = "Acreenciamovimiento.findByMontoMoneda",
                    query = "SELECT a FROM Acreenciamovimiento a WHERE a.montoMoneda = :montoMoneda"),
        @NamedQuery(name = "Acreenciamovimiento.findByVuelto",
                    query = "SELECT a FROM Acreenciamovimiento a WHERE a.vuelto = :vuelto"),
        @NamedQuery(name = "Acreenciamovimiento.findByEstado",
                    query = "SELECT a FROM Acreenciamovimiento a WHERE a.estado = :estado"),
        @NamedQuery(name = "Acreenciamovimiento.findByControlreplicacion",
                    query = "SELECT a FROM Acreenciamovimiento a WHERE a.controlreplicacion = :controlreplicacion"),
        @NamedQuery(name = "Acreenciamovimiento.findByNumeroIdentificacionCliente",
                    query = "SELECT a FROM Acreenciamovimiento a WHERE a.numeroIdentificacionCliente = :numeroIdentificacionCliente"),
        @NamedQuery(name = "Acreenciamovimiento.findByCorrelativo",
                    query = "SELECT a FROM Acreenciamovimiento a WHERE a.correlativo = :correlativo")})
public class Acreenciamovimiento implements Serializable {

    /**
     * Field serialVersionUID. (value is 1)
     */
    private static final long serialVersionUID = 2L;

    /**
     * Field acreenciamovimientoPK.
     */
    // @EmbeddedId
    // protected AcreenciamovimientoPK acreenciamovimientoPK;
    /**
     * Field idAcreencia.
     */
    @Basic(optional = false)
    @Column(name = "id_acreencia",
            nullable = false)
    private long idAcreencia;

    /**
     * Field documentoformadepago.
     */
    @Column(name = "documentoformadepago",
            length = 25)
    private String documentoformadepago;

    /**
     * Field fecha.
     */
    @Basic(optional = false)
    @Column(name = "fecha",
            nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    /**
     * Field nombreunidadnegocio.
     */
    @Basic(optional = false)
    @Column(name = "nombreunidadnegocio",
            nullable = false,
            length = 60)
    private String nombreunidadnegocio;

    /**
     * Field nombreunidadoperativa.
     */
    @Basic(optional = false)
    @Column(name = "nombreunidadoperativa",
            nullable = false,
            length = 60)
    private String nombreunidadoperativa;

    /**
     * Field tienda.
     */
    @Basic(optional = false)
    @Column(name = "tienda",
            nullable = false)
    private int tienda;

    /**
     * Field operacion.
     */
    @Basic(optional = false)
    @Column(name = "operacion",
            nullable = false)
    private int operacion;

    /**
     * Field anulaoperacion.
     */
    @Column(name = "anulaoperacion")
    private Integer anulaoperacion;

    /**
     * Field caja.
     */
    @Basic(optional = false)
    @Column(name = "caja",
            nullable = false)
    private int caja;

    /**
     * Field transaccion.
     */
    @Basic(optional = false)
    @Column(name = "transaccion",
            nullable = false)
    private long transaccion;

    /**
     * Field cajero.
     */
    @Basic(optional = false)
    @Column(name = "cajero",
            nullable = false)
    private int cajero;

    /**
     * Field recibodecaja.
     */
    @Column(name = "recibodecaja")
    private Integer recibodecaja;

    // @Max(value=?) @Min(value=?)//if you know range of your decimal fields
    // consider using these annotations to enforce field validation
    /**
     * Field montoMonedaLocal.
     */
    @Basic(optional = false)
    @Column(name = "monto_moneda_local",
            nullable = false,
            precision = 13,
            scale = 2)
    private BigDecimal montoMonedaLocal;

    /**
     * Field montoMoneda.
     */
    @Basic(optional = false)
    @Column(name = "monto_moneda",
            nullable = false,
            precision = 13,
            scale = 2)
    private BigDecimal montoMoneda;

    /**
     * Field vuelto.
     */
    @Basic(optional = false)
    @Column(name = "vuelto",
            nullable = false,
            precision = 13,
            scale = 2)
    private BigDecimal vuelto;

    /**
     * Field estado.
     */
    @Basic(optional = false)
    @Column(name = "estado",
            nullable = false,
            length = 10)
    private String estado;

    /**
     * Field controlreplicacion.
     */
    @Column(name = "controlreplicacion")
    private String controlreplicacion;

    /**
     * Field numeroIdentificacionCliente.
     */
    @Basic(optional = false)
    @Column(name = "numero_identificacion_cliente",
            nullable = false,
            length = 25)
    private String numeroIdentificacionCliente;

    /**
     * Field correlativo.
     */
    @Basic(optional = false)
    @Column(name = "correlativo",
            nullable = false)
    private BigDecimal correlativo;

    /**
     * Field idMoneda.
     */
    @JoinColumn(name = "id_moneda",
                referencedColumnName = "id",
                nullable = false)
    @ManyToOne(optional = false)
    private Moneda idMoneda;

    /**
     * Field idOperacionacreencia.
     */
    @JoinColumn(name = "id_operacionacreencia",
                referencedColumnName = "id",
                nullable = false)
    @ManyToOne(optional = false)
    private Operacionacreencia idOperacionacreencia;

    /**
     * Field idTipoacreencia.
     */
    @JoinColumn(name = "id_tipoacreencia",
                referencedColumnName = "id",
                nullable = false)
    @ManyToOne(optional = false)
    private Tipoacreencia idTipoacreencia;

    /**
     * Field idFormadepago.
     */
    @JoinColumn(name = "id_formadepago",
                referencedColumnName = "ID",
                nullable = false)
    @ManyToOne(optional = false)
    private Formadepago idFormadepago;

    /**
     * Field estasincronizado.
     */
    @Basic(optional = false)
    @Column(name = "estasincronizado",
            nullable = false,
            length = 2)
    private String estasincronizado;

    /**
     * Field idSesion.
     */
    @Basic(optional = false)
    @Column(name = "id_sesion",
            nullable = false,
            length = 25)
    private long idSesion;

    /**
     * Field ipaStatus.
     */
    @Basic(optional = false)
    @Column(name = "ipa_status")
    private char ipaStatus = 'P';

    /**
     * Field ipaId.
     */
    @Basic(optional = false)
    @Column(name = "ipa_id")
    @Id
    private Long ipaId = 0l;

    /**
     * Field idAcreenciamovimientosaldo.
     */
    @Basic(optional = false)
    @Column(name = "id_acreenciamovimientosaldo",
            nullable = false)
    private long idAcreenciamovimientosaldo;

    /**
     * Field idAcreenciamovimientoformadepago.
     */
    @Basic(optional = false)
    @Column(name = "id_acreenciamovimientoformadepago",
            nullable = false)
    private long idAcreenciamovimientoformadepago;

    /**
     * Constructor for Acreenciamovimiento.
     */
    public Acreenciamovimiento() {
    }

    /**
     * Constructor for Acreenciamovimiento.
     * 
     * @param acreenciamovimientoPK AcreenciamovimientoPK
     */
    // public Acreenciamovimiento(AcreenciamovimientoPK acreenciamovimientoPK) {
    // this.acreenciamovimientoPK = acreenciamovimientoPK;
    // }

    /**
     * Constructor for Acreenciamovimiento.
     *
     * @param idAcreencia long
     * @param fecha Date
     * @param nombreunidadnegocio String
     * @param nombreunidadoperativa String
     * @param tienda int
     * @param operacion int
     * @param caja int
     * @param transaccion long
     * @param cajero int
     * @param montoMonedaLocal BigDecimal
     * @param montoMoneda BigDecimal
     * @param vuelto BigDecimal
     * @param estado String
     * @param numeroIdentificacionCliente String
     * @param correlativo BigDecimal
     */
    public Acreenciamovimiento(Long ipaId, char ipaStatus, long idAcreenciamovimientoformadepago,
                               long idAcreenciamovimientosaldo, long idAcreencia, Date fecha,
                               String nombreunidadnegocio, String nombreunidadoperativa, int tienda, int operacion,
                               int caja, long transaccion, int cajero, BigDecimal montoMonedaLocal,
                               BigDecimal montoMoneda, BigDecimal vuelto, String estado,
                               String numeroIdentificacionCliente, BigDecimal correlativo) {
        // this.acreenciamovimientoPK = acreenciamovimientoPK;
        this.idAcreencia = idAcreencia;
        this.fecha = fecha;
        this.nombreunidadnegocio = nombreunidadnegocio;
        this.nombreunidadoperativa = nombreunidadoperativa;
        this.tienda = tienda;
        this.operacion = operacion;
        this.caja = caja;
        this.transaccion = transaccion;
        this.cajero = cajero;
        this.montoMonedaLocal = montoMonedaLocal;
        this.montoMoneda = montoMoneda;
        this.vuelto = vuelto;
        this.estado = estado;
        this.numeroIdentificacionCliente = numeroIdentificacionCliente;
        this.correlativo = correlativo;
        this.idAcreenciamovimientoformadepago = idAcreenciamovimientoformadepago;
        this.idAcreenciamovimientosaldo = idAcreenciamovimientosaldo;
        this.ipaId = ipaId;
        this.ipaStatus = ipaStatus;
    }

    /**
     * Constructor for Acreenciamovimiento.
     * 
     * @param ipaId long
     */
    public Acreenciamovimiento(long ipaId) {
        this.ipaId = ipaId;
    }

    /**
     * Constructor for Acreenciamovimiento.
     * 
     * @param idAcreenciamovimientosaldo long
     * @param idAcreenciamovimientoformadepago long
     */
    // public Acreenciamovimiento(long idAcreenciamovimientosaldo, long
    // idAcreenciamovimientoformadepago) {
    // this.acreenciamovimientoPK = new AcreenciamovimientoPK(idAcreenciamovimientosaldo,
    // idAcreenciamovimientoformadepago);
    // }

    /**
     * Constructor for Acreenciamovimiento.
     * 
     * @param idAcreenciamovimientosaldo long
     * @param idAcreenciamovimientoformadepago long
     */
    // public Acreenciamovimiento(long idAcreenciamovimientosaldo, long
    // idAcreenciamovimientoformadepago) {
    // this.acreenciamovimientoPK = new AcreenciamovimientoPK(idAcreenciamovimientosaldo,
    // idAcreenciamovimientoformadepago);
    // }

    /**
     * Method getAcreenciamovimientoPK.
     * 
     * @return AcreenciamovimientoPK
     */
    // public AcreenciamovimientoPK getAcreenciamovimientoPK() {
    // return acreenciamovimientoPK;
    // }

    /**
     * Method setAcreenciamovimientoPK.
     * 
     * @param acreenciamovimientoPK AcreenciamovimientoPK
     */
    // public void setAcreenciamovimientoPK(AcreenciamovimientoPK acreenciamovimientoPK) {
    // this.acreenciamovimientoPK = acreenciamovimientoPK;
    // }

    /**
     * Method getIdAcreencia.
     * 
     * @return long
     */
    public long getIdAcreencia() {
        return idAcreencia;
    }

    /**
     * Method setIdAcreencia.
     * 
     * @param idAcreencia long
     */
    public void setIdAcreencia(long idAcreencia) {
        this.idAcreencia = idAcreencia;
    }

    /**
     * Method getDocumentoformadepago.
     * 
     * @return String
     */
    public String getDocumentoformadepago() {
        return documentoformadepago;
    }

    /**
     * Method setDocumentoformadepago.
     * 
     * @param documentoformadepago String
     */
    public void setDocumentoformadepago(String documentoformadepago) {
        this.documentoformadepago = documentoformadepago;
    }

    /**
     * Method getFecha.
     * 
     * @return Date
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * Method setFecha.
     * 
     * @param fecha Date
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
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
     * Method getNombreunidadnegocio.
     * 
     * @return String
     */
    public String getNombreunidadnegocio() {
        return nombreunidadnegocio;
    }

    /**
     * Method setNombreunidadnegocio.
     * 
     * @param nombreunidadnegocio String
     */
    public void setNombreunidadnegocio(String nombreunidadnegocio) {
        this.nombreunidadnegocio = nombreunidadnegocio;
    }

    /**
     * Method getNombreunidadoperativa.
     * 
     * @return String
     */
    public String getNombreunidadoperativa() {
        return nombreunidadoperativa;
    }

    /**
     * Method setNombreunidadoperativa.
     * 
     * @param nombreunidadoperativa String
     */
    public void setNombreunidadoperativa(String nombreunidadoperativa) {
        this.nombreunidadoperativa = nombreunidadoperativa;
    }

    /**
     * Method getTienda.
     * 
     * @return int
     */
    public int getTienda() {
        return tienda;
    }

    /**
     * Method setTienda.
     * 
     * @param tienda int
     */
    public void setTienda(int tienda) {
        this.tienda = tienda;
    }

    /**
     * Method getOperacion.
     * 
     * @return int
     */
    public int getOperacion() {
        return operacion;
    }

    /**
     * Method setOperacion.
     * 
     * @param operacion int
     */
    public void setOperacion(int operacion) {
        this.operacion = operacion;
    }

    /**
     * Method getAnulaoperacion.
     * 
     * @return Integer
     */
    public Integer getAnulaoperacion() {
        return anulaoperacion;
    }

    /**
     * Method setAnulaoperacion.
     * 
     * @param anulaoperacion Integer
     */
    public void setAnulaoperacion(Integer anulaoperacion) {
        this.anulaoperacion = anulaoperacion;
    }

    /**
     * Method getCaja.
     * 
     * @return int
     */
    public int getCaja() {
        return caja;
    }

    /**
     * Method setCaja.
     * 
     * @param caja int
     */
    public void setCaja(int caja) {
        this.caja = caja;
    }

    /**
     * Method getTransaccion.
     * 
     * @return long
     */
    public long getTransaccion() {
        return transaccion;
    }

    /**
     * Method setTransaccion.
     * 
     * @param transaccion long
     */
    public void setTransaccion(long transaccion) {
        this.transaccion = transaccion;
    }

    /**
     * Method getCajero.
     * 
     * @return int
     */
    public int getCajero() {
        return cajero;
    }

    /**
     * Method setCajero.
     * 
     * @param cajero int
     */
    public void setCajero(int cajero) {
        this.cajero = cajero;
    }

    /**
     * Method getRecibodecaja.
     * 
     * @return Integer
     */
    public Integer getRecibodecaja() {
        return recibodecaja;
    }

    /**
     * Method setRecibodecaja.
     * 
     * @param recibodecaja Integer
     */
    public void setRecibodecaja(Integer recibodecaja) {
        this.recibodecaja = recibodecaja;
    }

    /**
     * Method getMontoMonedaLocal.
     * 
     * @return BigDecimal
     */
    public BigDecimal getMontoMonedaLocal() {
        return montoMonedaLocal;
    }

    /**
     * Method setMontoMonedaLocal.
     * 
     * @param montoMonedaLocal BigDecimal
     */
    public void setMontoMonedaLocal(BigDecimal montoMonedaLocal) {
        this.montoMonedaLocal = montoMonedaLocal;
    }

    /**
     * Method getMontoMoneda.
     * 
     * @return BigDecimal
     */
    public BigDecimal getMontoMoneda() {
        return montoMoneda;
    }

    /**
     * Method setMontoMoneda.
     * 
     * @param montoMoneda BigDecimal
     */
    public void setMontoMoneda(BigDecimal montoMoneda) {
        this.montoMoneda = montoMoneda;
    }

    /**
     * Method getVuelto.
     * 
     * @return BigDecimal
     */
    public BigDecimal getVuelto() {
        return vuelto;
    }

    /**
     * Method setVuelto.
     * 
     * @param vuelto BigDecimal
     */
    public void setVuelto(BigDecimal vuelto) {
        this.vuelto = vuelto;
    }

    /**
     * Method getEstado.
     * 
     * @return String
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Method setEstado.
     * 
     * @param estado String
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Method getControlreplicacion.
     * 
     * @return String
     */
    public String getControlreplicacion() {
        return controlreplicacion;
    }

    /**
     * Method setControlreplicacion.
     * 
     * @param controlreplicacion String
     */
    public void setControlreplicacion(String controlreplicacion) {
        this.controlreplicacion = controlreplicacion;
    }

    /**
     * Method getNumeroIdentificacionCliente.
     * 
     * @return String
     */
    public String getNumeroIdentificacionCliente() {
        return numeroIdentificacionCliente;
    }

    /**
     * Method setNumeroIdentificacionCliente.
     * 
     * @param numeroIdentificacionCliente String
     */
    public void setNumeroIdentificacionCliente(String numeroIdentificacionCliente) {
        this.numeroIdentificacionCliente = numeroIdentificacionCliente;
    }

    /**
     * Method getCorrelativo.
     * 
     * @return BigDecimal
     */
    public BigDecimal getCorrelativo() {
        return correlativo;
    }

    /**
     * Method setCorrelativo.
     * 
     * @param correlativo BigDecimal
     */
    public void setCorrelativo(BigDecimal correlativo) {
        this.correlativo = correlativo;
    }

    /**
     * Method getIdMoneda.
     * 
     * @return Moneda
     */
    public Moneda getIdMoneda() {
        return idMoneda;
    }

    /**
     * Method setIdMoneda.
     * 
     * @param idMoneda Moneda
     */
    public void setIdMoneda(Moneda idMoneda) {
        this.idMoneda = idMoneda;
    }

    /**
     * Method getIdOperacionacreencia.
     * 
     * @return Operacionacreencia
     */
    public Operacionacreencia getIdOperacionacreencia() {
        return idOperacionacreencia;
    }

    /**
     * Method setIdOperacionacreencia.
     * 
     * @param idOperacionacreencia Operacionacreencia
     */
    public void setIdOperacionacreencia(Operacionacreencia idOperacionacreencia) {
        this.idOperacionacreencia = idOperacionacreencia;
    }

    /**
     * Method getIdTipoacreencia.
     * 
     * @return Tipoacreencia
     */
    public Tipoacreencia getIdTipoacreencia() {
        return idTipoacreencia;
    }

    /**
     * Method setIdTipoacreencia.
     * 
     * @param idTipoacreencia Tipoacreencia
     */
    public void setIdTipoacreencia(Tipoacreencia idTipoacreencia) {
        this.idTipoacreencia = idTipoacreencia;
    }

    /**
     * Method getIdFormadepago.
     * 
     * @return Formadepago
     */
    public Formadepago getIdFormadepago() {
        return idFormadepago;
    }

    /**
     * Method setIdFormadepago.
     * 
     * @param idFormadepago Formadepago
     */
    public void setIdFormadepago(Formadepago idFormadepago) {
        this.idFormadepago = idFormadepago;
    }

    /**
     * Method hashCode.
     * 
     * @return int
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ipaId != null ? ipaId.hashCode() : 0);
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
        if (!(object instanceof Acreenciamovimiento)) {
            return false;
        }
        Acreenciamovimiento other = (Acreenciamovimiento) object;
        if ((this.ipaId == null && other.ipaId != null)
                || (this.ipaId != null && !this.ipaId.equals(other.ipaId))) {
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
        return "Acreenciamovimiento[ ipaID=" + ipaId + " ]";
    }

    /**
     * Method getIdSesion.
     * 
     * @return long
     */
    public long getIdSesion() {
        return idSesion;
    }

    /**
     * Method setIdSesion.
     * 
     * @param idSesion long
     */
    public void setIdSesion(long idSesion) {
        this.idSesion = idSesion;
    }

    /**
     * Getter ipaStatus.
     * 
     * @return ipaStatus
     */
    public char getIpaStatus() {
        return ipaStatus;
    }

    /**
     * Setter ipaStatus.
     * 
     * @param ipaStatus
     */
    public void setIpaStatus(char ipaStatus) {
        this.ipaStatus = ipaStatus;
    }

    /**
     * Getter ipaId.
     * 
     * @return ipaId
     */
    public Long getIpaId() {
        return ipaId;
    }

    /**
     * Setter ipaId
     * 
     * @param ipaId CR generated id
     */
    public void setIpaId(Long ipaId) {
        this.ipaId = ipaId;
    }

    /**
     * Getter ipaStatus.
     * 
     * @return ipaStatus
     */
    public long getIdAcreenciamovimientosaldo() {
        return idAcreenciamovimientosaldo;
    }

    /**
     * Method setIdAcreenciamovimientosaldo.
     *
     * @param idAcreenciamovimientosaldo long
     */
    public void setIdAcreenciamovimientosaldo(long idAcreenciamovimientosaldo) {
        this.idAcreenciamovimientosaldo = idAcreenciamovimientosaldo;
    }

    /**
     * Getter idAcreenciamovimientoformadepago.
     * 
     * @return idAcreenciamovimientoformadepago
     */
    public long getIdAcreenciamovimientoformadepago() {
        return idAcreenciamovimientoformadepago;
    }

    /**
     * Method setIdAcreenciamovimientoformadepago.
     *
     * @param idAcreenciamovimientoformadepago long
     */
    public void setIdAcreenciamovimientoformadepago(long idAcreenciamovimientoformadepago) {
        this.idAcreenciamovimientoformadepago = idAcreenciamovimientoformadepago;
    }
}
