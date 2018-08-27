/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crjpa400;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import crjpa400.interfaces.CrjpaInterface;

/**
 * 
 * @author eve0014321
 * @version $Revision: 1.0 $
 */
@Entity
@Table(name = "FORMADEPAGO")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Formadepago.findAll", query = "SELECT f FROM Formadepago f"),
		@NamedQuery(name = "Formadepago.findById", query = "SELECT f FROM Formadepago f WHERE f.id = :id"),
		@NamedQuery(name = "Formadepago.findByCodigo", query = "SELECT f FROM Formadepago f WHERE f.codigo = :codigo"),
		@NamedQuery(name = "Formadepago.findByNombre", query = "SELECT f FROM Formadepago f WHERE f.nombre = :nombre"),
		@NamedQuery(name = "Formadepago.findByDescripcion", query = "SELECT f FROM Formadepago f WHERE f.descripcion = :descripcion"),
		@NamedQuery(name = "Formadepago.findByPrioridadDonacion", query = "SELECT f FROM Formadepago f WHERE f.prioridadDonacion = :prioridadDonacion"),
		@NamedQuery(name = "Formadepago.findByPermiteVuelto", query = "SELECT f FROM Formadepago f WHERE f.permiteVuelto = :permiteVuelto"),
		@NamedQuery(name = "Formadepago.findByMontoMinimo", query = "SELECT f FROM Formadepago f WHERE f.montoMinimo = :montoMinimo"),
		@NamedQuery(name = "Formadepago.findByMontoMaximo", query = "SELECT f FROM Formadepago f WHERE f.montoMaximo = :montoMaximo"),
		@NamedQuery(name = "Formadepago.findByFecha", query = "SELECT f FROM Formadepago f WHERE f.fecha = :fecha"),
		@NamedQuery(name = "Formadepago.findByRequiereAutorizacion", query = "SELECT f FROM Formadepago f WHERE f.requiereAutorizacion = :requiereAutorizacion"),
		@NamedQuery(name = "Formadepago.findByTipoFormaDePago", query = "SELECT f FROM Formadepago f WHERE f.tipoFormaDePago = :tipoFormaDePago"),
		@NamedQuery(name = "Formadepago.findByIconoFormaDePago", query = "SELECT f FROM Formadepago f WHERE f.iconoFormaDePago = :iconoFormaDePago"),
		@NamedQuery(name = "Formadepago.findByTipoTarjeta", query = "SELECT f FROM Formadepago f WHERE f.tipoTarjeta = :tipoTarjeta"),
		@NamedQuery(name = "Formadepago.findByPermiteReverso", query = "SELECT f FROM Formadepago f WHERE f.permiteReverso = :permiteReverso"),
		@NamedQuery(name = "Formadepago.findByPermiteReasignarCliente", query = "SELECT f FROM Formadepago f WHERE f.permiteReasignarCliente = :permiteReasignarCliente"),
		@NamedQuery(name = "Formadepago.findByEstaactivo", query = "SELECT f FROM Formadepago f WHERE f.estaactivo = :estaactivo"),
		@NamedQuery(name = "Formadepago.findByUltimasincronizacion", query = "SELECT f FROM Formadepago f WHERE f.ultimasincronizacion = :ultimasincronizacion"),
		@NamedQuery(name = "Formadepago.findByEstreplica", query = "SELECT f FROM Formadepago f WHERE f.estreplica = :estreplica"),
		@NamedQuery(name = "Formadepago.findByIdOriginal", query = "SELECT f FROM Formadepago f WHERE f.idOriginal = :idOriginal"),
		@NamedQuery(name = "Formadepago.findByTipoCaja", query = "SELECT f FROM Formadepago f WHERE f.tipoCaja = :tipoCaja") })
public class Formadepago extends CrjpaInterface implements Serializable {
	/**
	 * Field serialVersionUID. (value is 1)
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Field id.
	 */
	@Id
	@Basic(optional = false)
	@Column(name = "ID", nullable = false)
	private Long id;
	/**
	 * Field codigo.
	 */
	@Basic(optional = false)
	@Column(name = "CODIGO", nullable = false)
	private int codigo;
	/**
	 * Field nombre.
	 */
	@Basic(optional = false)
	@Column(name = "NOMBRE", nullable = false, length = 12)
	private String nombre;
	/**
	 * Field descripcion.
	 */
	@Basic(optional = false)
	@Column(name = "DESCRIPCION", nullable = false, length = 40)
	private String descripcion;
	/**
	 * Field prioridadDonacion.
	 */
	@Basic(optional = false)
	@Column(name = "PRIORIDAD_DONACION", nullable = false)
	private int prioridadDonacion;
	/**
	 * Field permiteVuelto.
	 */
	@Basic(optional = false)
	@Column(name = "PERMITE_VUELTO", nullable = false)
	private char permiteVuelto;
	// @Max(value=?) @Min(value=?)//if you know range of your decimal fields
	// consider using these annotations to enforce field validation
	/**
	 * Field montoMinimo.
	 */
	@Basic(optional = false)
	@Column(name = "MONTO_MINIMO", nullable = false, precision = 13, scale = 2)
	private BigDecimal montoMinimo;
	/**
	 * Field montoMaximo.
	 */
	@Basic(optional = false)
	@Column(name = "MONTO_MAXIMO", nullable = false, precision = 13, scale = 2)
	private BigDecimal montoMaximo;
	/**
	 * Field fecha.
	 */
	@Basic(optional = false)
	@Column(name = "FECHA", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	/**
	 * Field requiereAutorizacion.
	 */
	@Basic(optional = false)
	@Column(name = "REQUIERE_AUTORIZACION", nullable = false)
	private char requiereAutorizacion;
	/**
	 * Field tipoFormaDePago.
	 */
	@Basic(optional = false)
	@Column(name = "TIPO_FORMA_DE_PAGO", nullable = false)
	private int tipoFormaDePago;
	/**
	 * Field iconoFormaDePago.
	 */
	@Column(name = "ICONO_FORMA_DE_PAGO", length = 255)
	private String iconoFormaDePago;
	/**
	 * Field tipoTarjeta.
	 */
	@Column(name = "TIPO_TARJETA", length = 5)
	private String tipoTarjeta;
	/**
	 * Field permiteReverso.
	 */
	@Basic(optional = false)
	@Column(name = "PERMITE_REVERSO", nullable = false)
	private char permiteReverso;
	/**
	 * Field permiteReasignarCliente.
	 */
	@Basic(optional = false)
	@Column(name = "PERMITE_REASIGNAR_CLIENTE", nullable = false)
	private char permiteReasignarCliente;
	/**
	 * Field estaactivo.
	 */
	@Basic(optional = false)
	@Column(name = "ESTAACTIVO", nullable = false)
	private char estaactivo;
	/**
	 * Field ultimasincronizacion.
	 */
	@Basic(optional = false)
	@Column(name = "ULTIMASINCRONIZACION", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar ultimasincronizacion;
	/**
	 * Field estreplica.
	 */
	@Basic(optional = false)
	@Column(name = "ESTREPLICA")
	private char estreplica;
	/**
	 * Field idOriginal.
	 */
	@Column(name = "ID_ORIGINAL")
	private BigInteger idOriginal;
	/**
	 * Field tipoCaja.
	 */
	@Basic(optional = false)
	@Column(name = "TIPO_CAJA")
	private Integer tipoCaja;
	/**
	 * Field formadepagomovacreenciaList.
	 */
	@OneToMany(mappedBy = "idFormadepago")
	private List<Formadepagomovacreencia> formadepagomovacreenciaList;
	/**
	 * Field transaccionformadepagoList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idFormadepago")
	private List<Transaccionformadepago> transaccionformadepagoList;
	/**
	 * Field porcentajeimpuestoretencionList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idFormadepago")
	private List<Porcentajeimpuestoretencion> porcentajeimpuestoretencionList;
	/**
	 * Field detalletipoformadepagoList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idFormadepago")
	private List<Detalletipoformadepago> detalletipoformadepagoList;
	/**
	 * Field morosidadList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idFormadepago")
	private List<Morosidad> morosidadList;
	/**
	 * Field entregaformadepagoList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idFormadepago")
	private List<Entregaformadepago> entregaformadepagoList;
//	/**
//	 * Field categorialinearetencionList.
//	 */
//	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idFormadepago")
//	private List<Categorialinearetencion> categorialinearetencionList;
	/**
	 * Field acreenciamovimientoformadepagoList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idFormadepago")
	private List<Acreenciamovimientoformadepago> acreenciamovimientoformadepagoList;
	/**
	 * Field promocionformadepagoList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idFormadepago")
	private List<Promocionformadepago> promocionformadepagoList;
	/**
	 * Field abonoformadepagoList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idFormadepago")
	private List<Abonoformadepago> abonoformadepagoList;
	/**
	 * Field acreenciamovimientoList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idFormadepago")
	private List<Acreenciamovimiento> acreenciamovimientoList;
	/**
	 * Field formadepagomonedaList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idFormadepago")
	private List<Formadepagomoneda> formadepagomonedaList;
	/**
	 * Field monedadenominacionList.
	 */
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "idFormadepago")
    private List<Monedadenominacion> monedadenominacionList;
	/**
	 * Field tipodocumentoList1.
	 */
	@OneToMany(mappedBy = "idContrapartida")
	private List<Formadepago> tipodocumentoList1;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idFormadepago")
    private List<Formadepagotipocliente> formadepagotipoclienteList;
	
	/**
	 * Field idContrapartida.
	 */
	@JoinColumn(name = "ID_CONTRAPARTIDA", referencedColumnName = "ID")
	@ManyToOne
	private Formadepago idContrapartida;

	/**
	 * Constructor for Formadepago.
	 */
	public Formadepago() {
	}

	/**
	 * Constructor for Formadepago.
	 * 
	 * @param id
	 *            Long
	 */
	public Formadepago(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Formadepago.
	 * 
	 * @param id
	 *            Long
	 * @param codigo
	 *            int
	 * @param nombre
	 *            String
	 * @param descripcion
	 *            String
	 * @param prioridadDonacion
	 *            int
	 * @param permiteVuelto
	 *            char
	 * @param montoMinimo
	 *            BigDecimal
	 * @param montoMaximo
	 *            BigDecimal
	 * @param fecha
	 *            Date
	 * @param requiereAutorizacion
	 *            char
	 * @param tipoFormaDePago
	 *            int
	 * @param permiteReverso
	 *            char
	 * @param permiteReasignarCliente
	 *            char
	 * @param estaactivo
	 *            char
	 * @param ultimasincronizacion
	 *            Calendar
	 * @param estreplica
	 *            char
	 * @param tipoCaja
	 *            int
	 */
	public Formadepago(Long id, int codigo, String nombre, String descripcion,
			int prioridadDonacion, char permiteVuelto, BigDecimal montoMinimo,
			BigDecimal montoMaximo, Date fecha, char requiereAutorizacion,
			int tipoFormaDePago, char permiteReverso,
			char permiteReasignarCliente, char estaactivo,
			Calendar ultimasincronizacion, char estreplica, int tipoCaja) {
		this.id = id;
		this.codigo = codigo;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.prioridadDonacion = prioridadDonacion;
		this.permiteVuelto = permiteVuelto;
		this.montoMinimo = montoMinimo;
		this.montoMaximo = montoMaximo;
		this.fecha = fecha;
		this.requiereAutorizacion = requiereAutorizacion;
		this.tipoFormaDePago = tipoFormaDePago;
		this.permiteReverso = permiteReverso;
		this.permiteReasignarCliente = permiteReasignarCliente;
		this.estaactivo = estaactivo;
		this.ultimasincronizacion = ultimasincronizacion;
		this.estreplica = estreplica;
		this.tipoCaja = tipoCaja;
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
	 * @param id
	 *            Long
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Method getCodigo.
	 * 
	 * @return int
	 */
	public int getCodigo() {
		return codigo;
	}

	/**
	 * Method setCodigo.
	 * 
	 * @param codigo
	 *            int
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	/**
	 * Method getNombre.
	 * 
	 * @return String
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Method setNombre.
	 * 
	 * @param nombre
	 *            String
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Method getDescripcion.
	 * 
	 * @return String
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Method setDescripcion.
	 * 
	 * @param descripcion
	 *            String
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Method getPrioridadDonacion.
	 * 
	 * @return int
	 */
	public int getPrioridadDonacion() {
		return prioridadDonacion;
	}

	/**
	 * Method setPrioridadDonacion.
	 * 
	 * @param prioridadDonacion
	 *            int
	 */
	public void setPrioridadDonacion(int prioridadDonacion) {
		this.prioridadDonacion = prioridadDonacion;
	}

	/**
	 * Method getPermiteVuelto.
	 * 
	 * @return char
	 */
	public char getPermiteVuelto() {
		return permiteVuelto;
	}

	/**
	 * Method setPermiteVuelto.
	 * 
	 * @param permiteVuelto
	 *            char
	 */
	public void setPermiteVuelto(char permiteVuelto) {
		this.permiteVuelto = permiteVuelto;
	}

	/**
	 * Method getMontoMinimo.
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal getMontoMinimo() {
		return montoMinimo;
	}

	/**
	 * Method setMontoMinimo.
	 * 
	 * @param montoMinimo
	 *            BigDecimal
	 */
	public void setMontoMinimo(BigDecimal montoMinimo) {
		this.montoMinimo = montoMinimo;
	}

	/**
	 * Method getMontoMaximo.
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal getMontoMaximo() {
		return montoMaximo;
	}

	/**
	 * Method setMontoMaximo.
	 * 
	 * @param montoMaximo
	 *            BigDecimal
	 */
	public void setMontoMaximo(BigDecimal montoMaximo) {
		this.montoMaximo = montoMaximo;
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
	 * @param fecha
	 *            Date
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/**
	 * Method getRequiereAutorizacion.
	 * 
	 * @return char
	 */
	public char getRequiereAutorizacion() {
		return requiereAutorizacion;
	}

	/**
	 * Method setRequiereAutorizacion.
	 * 
	 * @param requiereAutorizacion
	 *            char
	 */
	public void setRequiereAutorizacion(char requiereAutorizacion) {
		this.requiereAutorizacion = requiereAutorizacion;
	}

	/**
	 * Method getTipoFormaDePago.
	 * 
	 * @return int
	 */
	public int getTipoFormaDePago() {
		return tipoFormaDePago;
	}

	/**
	 * Method setTipoFormaDePago.
	 * 
	 * @param tipoFormaDePago
	 *            int
	 */
	public void setTipoFormaDePago(int tipoFormaDePago) {
		this.tipoFormaDePago = tipoFormaDePago;
	}

	/**
	 * Method getIconoFormaDePago.
	 * 
	 * @return String
	 */
	public String getIconoFormaDePago() {
		return iconoFormaDePago;
	}

	/**
	 * Method setIconoFormaDePago.
	 * 
	 * @param iconoFormaDePago
	 *            String
	 */
	public void setIconoFormaDePago(String iconoFormaDePago) {
		this.iconoFormaDePago = iconoFormaDePago;
	}

	/**
	 * Method getTipoTarjeta.
	 * 
	 * @return String
	 */
	public String getTipoTarjeta() {
		return tipoTarjeta;
	}

	/**
	 * Method setTipoTarjeta.
	 * 
	 * @param tipoTarjeta
	 *            String
	 */
	public void setTipoTarjeta(String tipoTarjeta) {
		this.tipoTarjeta = tipoTarjeta;
	}

	/**
	 * Method getPermiteReverso.
	 * 
	 * @return char
	 */
	public char getPermiteReverso() {
		return permiteReverso;
	}

	/**
	 * Method setPermiteReverso.
	 * 
	 * @param permiteReverso
	 *            char
	 */
	public void setPermiteReverso(char permiteReverso) {
		this.permiteReverso = permiteReverso;
	}

	/**
	 * Method getPermiteReasignarCliente.
	 * 
	 * @return char
	 */
	public char getPermiteReasignarCliente() {
		return permiteReasignarCliente;
	}

	/**
	 * Method setPermiteReasignarCliente.
	 * 
	 * @param permiteReasignarCliente
	 *            char
	 */
	public void setPermiteReasignarCliente(char permiteReasignarCliente) {
		this.permiteReasignarCliente = permiteReasignarCliente;
	}

	/**
	 * Method getEstaactivo.
	 * 
	 * @return char
	 */
	public char getEstaactivo() {
		return estaactivo;
	}

	/**
	 * Method setEstaactivo.
	 * 
	 * @param estaactivo
	 *            char
	 */
	public void setEstaactivo(char estaactivo) {
		this.estaactivo = estaactivo;
	}

	/**
	 * Method getUltimasincronizacion.
	 * 
	 * @return Calendar
	 */
	@Override
	public Calendar getUltimasincronizacion() {
		return ultimasincronizacion;
	}

	/**
	 * Method setUltimasincronizacion.
	 * 
	 * @param ultimasincronizacion
	 *            Calendar
	 */
	public void setUltimasincronizacion(Calendar ultimasincronizacion) {
		this.ultimasincronizacion = ultimasincronizacion;
	}

	/**
	 * Method getEstreplica.
	 * 
	 * @return char
	 */
	public char getEstreplica() {
		return estreplica;
	}

	/**
	 * Method setEstreplica.
	 * 
	 * @param estreplica
	 *            char
	 */
	public void setEstreplica(char estreplica) {
		this.estreplica = estreplica;
	}

	/**
	 * Method getIdOriginal.
	 * 
	 * @return BigInteger
	 */
	public BigInteger getIdOriginal() {
		return idOriginal;
	}

	/**
	 * Method setIdOriginal.
	 * 
	 * @param idOriginal
	 *            BigInteger
	 */
	public void setIdOriginal(BigInteger idOriginal) {
		this.idOriginal = idOriginal;
	}

	/**
	 * Method getTipoCaja.
	 * 
	 * @return Integer
	 */
	public Integer getTipoCaja() {
		return tipoCaja;
	}

	/**
	 * Method setTipoCaja.
	 * 
	 * @param tipoCaja
	 *            Integer
	 */
	public void setTipoCaja(Integer tipoCaja) {
		this.tipoCaja = tipoCaja;
	}

	/**
	 * Method getFormadepagomovacreenciaList.
	 * 
	 * @return List<Formadepagomovacreencia>
	 */
	@XmlTransient
	public List<Formadepagomovacreencia> getFormadepagomovacreenciaList() {
		return formadepagomovacreenciaList;
	}

	/**
	 * Method setFormadepagomovacreenciaList.
	 * 
	 * @param formadepagomovacreenciaList
	 *            List<Formadepagomovacreencia>
	 */
	public void setFormadepagomovacreenciaList(
			List<Formadepagomovacreencia> formadepagomovacreenciaList) {
		this.formadepagomovacreenciaList = formadepagomovacreenciaList;
	}

	/**
	 * Method getTransaccionformadepagoList.
	 * 
	 * @return List<Transaccionformadepago>
	 */
	@XmlTransient
	public List<Transaccionformadepago> getTransaccionformadepagoList() {
		return transaccionformadepagoList;
	}

	/**
	 * Method setTransaccionformadepagoList.
	 * 
	 * @param transaccionformadepagoList
	 *            List<Transaccionformadepago>
	 */
	public void setTransaccionformadepagoList(
			List<Transaccionformadepago> transaccionformadepagoList) {
		this.transaccionformadepagoList = transaccionformadepagoList;
	}

	/**
	 * Method getPorcentajeimpuestoretencionList.
	 * 
	 * @return List<Porcentajeimpuestoretencion>
	 */
	@XmlTransient
	public List<Porcentajeimpuestoretencion> getPorcentajeimpuestoretencionList() {
		return porcentajeimpuestoretencionList;
	}

	/**
	 * Method setPorcentajeimpuestoretencionList.
	 * 
	 * @param porcentajeimpuestoretencionList
	 *            List<Porcentajeimpuestoretencion>
	 */
	public void setPorcentajeimpuestoretencionList(
			List<Porcentajeimpuestoretencion> porcentajeimpuestoretencionList) {
		this.porcentajeimpuestoretencionList = porcentajeimpuestoretencionList;
	}

	/**
	 * Method getDetalletipoformadepagoList.
	 * 
	 * @return List<Detalletipoformadepago>
	 */
	@XmlTransient
	public List<Detalletipoformadepago> getDetalletipoformadepagoList() {
		return detalletipoformadepagoList;
	}

	/**
	 * Method setDetalletipoformadepagoList.
	 * 
	 * @param detalletipoformadepagoList
	 *            List<Detalletipoformadepago>
	 */
	public void setDetalletipoformadepagoList(
			List<Detalletipoformadepago> detalletipoformadepagoList) {
		this.detalletipoformadepagoList = detalletipoformadepagoList;
	}

	/**
	 * Method getMorosidadList.
	 * 
	 * @return List<Morosidad>
	 */
	@XmlTransient
	public List<Morosidad> getMorosidadList() {
		return morosidadList;
	}

	/**
	 * Method setMorosidadList.
	 * 
	 * @param morosidadList
	 *            List<Morosidad>
	 */
	public void setMorosidadList(List<Morosidad> morosidadList) {
		this.morosidadList = morosidadList;
	}

	/**
	 * Method getEntregaformadepagoList.
	 * 
	 * @return List<Entregaformadepago>
	 */
	@XmlTransient
	public List<Entregaformadepago> getEntregaformadepagoList() {
		return entregaformadepagoList;
	}

	/**
	 * Method setEntregaformadepagoList.
	 * 
	 * @param entregaformadepagoList
	 *            List<Entregaformadepago>
	 */
	public void setEntregaformadepagoList(
			List<Entregaformadepago> entregaformadepagoList) {
		this.entregaformadepagoList = entregaformadepagoList;
	}

//	/**
//	 * Method getCategorialinearetencionList.
//	 * 
//	 * @return List<Categorialinearetencion>
//	 */
//	@XmlTransient
//	public List<Categorialinearetencion> getCategorialinearetencionList() {
//		return categorialinearetencionList;
//	}
//
//	/**
//	 * Method setCategorialinearetencionList.
//	 * 
//	 * @param categorialinearetencionList
//	 *            List<Categorialinearetencion>
//	 */
//	public void setCategorialinearetencionList(
//			List<Categorialinearetencion> categorialinearetencionList) {
//		this.categorialinearetencionList = categorialinearetencionList;
//	}

	/**
	 * Method getAcreenciamovimientoformadepagoList.
	 * 
	 * @return List<Acreenciamovimientoformadepago>
	 */
	@XmlTransient
	public List<Acreenciamovimientoformadepago> getAcreenciamovimientoformadepagoList() {
		return acreenciamovimientoformadepagoList;
	}

	/**
	 * Method setAcreenciamovimientoformadepagoList.
	 * 
	 * @param acreenciamovimientoformadepagoList
	 *            List<Acreenciamovimientoformadepago>
	 */
	public void setAcreenciamovimientoformadepagoList(
			List<Acreenciamovimientoformadepago> acreenciamovimientoformadepagoList) {
		this.acreenciamovimientoformadepagoList = acreenciamovimientoformadepagoList;
	}

	/**
	 * Method getPromocionformadepagoList.
	 * 
	 * @return List<Promocionformadepago>
	 */
	@XmlTransient
	public List<Promocionformadepago> getPromocionformadepagoList() {
		return promocionformadepagoList;
	}

	/**
	 * Method setPromocionformadepagoList.
	 * 
	 * @param promocionformadepagoList
	 *            List<Promocionformadepago>
	 */
	public void setPromocionformadepagoList(
			List<Promocionformadepago> promocionformadepagoList) {
		this.promocionformadepagoList = promocionformadepagoList;
	}

	/**
	 * Method getAbonoformadepagoList.
	 * 
	 * @return List<Abonoformadepago>
	 */
	@XmlTransient
	public List<Abonoformadepago> getAbonoformadepagoList() {
		return abonoformadepagoList;
	}

	/**
	 * Method setAbonoformadepagoList.
	 * 
	 * @param abonoformadepagoList
	 *            List<Abonoformadepago>
	 */
	public void setAbonoformadepagoList(
			List<Abonoformadepago> abonoformadepagoList) {
		this.abonoformadepagoList = abonoformadepagoList;
	}

	/**
	 * Method getAcreenciamovimientoList.
	 * 
	 * @return List<Acreenciamovimiento>
	 */
	@XmlTransient
	public List<Acreenciamovimiento> getAcreenciamovimientoList() {
		return acreenciamovimientoList;
	}

	/**
	 * Method setAcreenciamovimientoList.
	 * 
	 * @param acreenciamovimientoList
	 *            List<Acreenciamovimiento>
	 */
	public void setAcreenciamovimientoList(
			List<Acreenciamovimiento> acreenciamovimientoList) {
		this.acreenciamovimientoList = acreenciamovimientoList;
	}

	/**
	 * Method getFormadepagomonedaList.
	 * 
	 * @return List<Formadepagomoneda>
	 */
	@XmlTransient
	public List<Formadepagomoneda> getFormadepagomonedaList() {
		return formadepagomonedaList;
	}

	/**
	 * Method setFormadepagomonedaList.
	 * 
	 * @param formadepagomonedaList
	 *            List<Formadepagomoneda>
	 */
	public void setFormadepagomonedaList(
			List<Formadepagomoneda> formadepagomonedaList) {
		this.formadepagomonedaList = formadepagomonedaList;
	}

	
    public List<Monedadenominacion> getMonedadenominacionList() {
        return monedadenominacionList;
    }

    public void setMonedadenominacionList(List<Monedadenominacion> monedadenominacionList) {
        this.monedadenominacionList = monedadenominacionList;
    }
	
	
	/**
	 * Method getIdContrapartida.
	 * 
	 * @return Formadepago
	 */
	public Formadepago getIdContrapartida() {
		return idContrapartida;
	}

	/**
	 * Method setIdContrapartida.
	 * 
	 * @param idContrapartida
	 *            Formadepago
	 */
	public void setIdContrapartida(Formadepago idContrapartida) {
		this.idContrapartida = idContrapartida;
	}
	
	@XmlTransient
    public List<Formadepagotipocliente> getFormadepagotipoclienteList() {
        return formadepagotipoclienteList;
    }

    public void setFormadepagotipoclienteList(List<Formadepagotipocliente> formadepagotipoclienteList) {
        this.formadepagotipoclienteList = formadepagotipoclienteList;
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
	 * @param object
	 *            Object
	 * @return boolean
	 */
	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Formadepago)) {
			return false;
		}
		Formadepago other = (Formadepago) object;
		if ((this.id == null && other.id != null)
				|| (this.id != null && !this.id.equals(other.id))) {
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
		return "crjpa400.Formadepago[ id=" + id + " ]";
	}

}
