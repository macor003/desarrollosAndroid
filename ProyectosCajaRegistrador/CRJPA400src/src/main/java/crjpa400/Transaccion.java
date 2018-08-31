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

/**
 * 
 * @author eve0014321
 * @version $Revision: 1.0 $
 */
@Entity
@Table(name = "TRANSACCION")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Transaccion.findAll", query = "SELECT t FROM Transaccion t"),
		@NamedQuery(name = "Transaccion.findById", query = "SELECT t FROM Transaccion t WHERE t.id = :id"),
		@NamedQuery(name = "Transaccion.findByNumero", query = "SELECT t FROM Transaccion t WHERE t.numero = :numero"),
		@NamedQuery(name = "Transaccion.findByNumeroFiscal", query = "SELECT t FROM Transaccion t WHERE t.numeroFiscal = :numeroFiscal"),
		@NamedQuery(name = "Transaccion.findByCajero", query = "SELECT t FROM Transaccion t WHERE t.cajero = :cajero"),
		@NamedQuery(name = "Transaccion.findByTipo", query = "SELECT t FROM Transaccion t WHERE t.tipo = :tipo"),
		@NamedQuery(name = "Transaccion.findByEstado", query = "SELECT t FROM Transaccion t WHERE t.estado = :estado"),
		@NamedQuery(name = "Transaccion.findByFecha", query = "SELECT t FROM Transaccion t WHERE t.fecha = :fecha"),
		@NamedQuery(name = "Transaccion.findByTotalBase", query = "SELECT t FROM Transaccion t WHERE t.totalBase = :totalBase"),
		@NamedQuery(name = "Transaccion.findByTotalImpuesto", query = "SELECT t FROM Transaccion t WHERE t.totalImpuesto = :totalImpuesto"),
		@NamedQuery(name = "Transaccion.findByTotalRebaja", query = "SELECT t FROM Transaccion t WHERE t.totalRebaja = :totalRebaja"),
		@NamedQuery(name = "Transaccion.findByVuelto", query = "SELECT t FROM Transaccion t WHERE t.vuelto = :vuelto"),
		@NamedQuery(name = "Transaccion.findByRenglones", query = "SELECT t FROM Transaccion t WHERE t.renglones = :renglones"),
		@NamedQuery(name = "Transaccion.findByDonacion", query = "SELECT t FROM Transaccion t WHERE t.donacion = :donacion"),
		@NamedQuery(name = "Transaccion.findByOrigenTransaccion", query = "SELECT t FROM Transaccion t WHERE t.origenTransaccion = :origenTransaccion"),
		@NamedQuery(name = "Transaccion.findByTotalTransaccion", query = "SELECT t FROM Transaccion t WHERE t.totalTransaccion = :totalTransaccion"),
		@NamedQuery(name = "Transaccion.findByEstreplica", query = "SELECT t FROM Transaccion t WHERE t.estreplica = :estreplica") })
public class Transaccion implements Serializable {
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
	 * Field numero.
	 */
	@Basic(optional = false)
	@Column(name = "NUMERO", nullable = false)
	private long numero;
	/**
	 * Field numeroFiscal.
	 */
	@Basic(optional = false)
	@Column(name = "NUMERO_FISCAL", nullable = false)
	private int numeroFiscal;
	/**
	 * Field cajero.
	 */
	@Column(name = "CAJERO")
	private Integer cajero;
	/**
	 * Field tipo.
	 */
	@Basic(optional = false)
	@Column(name = "TIPO", nullable = false, length = 50)
	private String tipo;
	/**
	 * Field estado.
	 */
	@Basic(optional = false)
	@Column(name = "ESTADO", nullable = false)
	private char estado;
	/**
	 * Field fecha.
	 */
	@Basic(optional = false)
	@Column(name = "FECHA", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	// @Max(value=?) @Min(value=?)//if you know range of your decimal fields
	// consider using these annotations to enforce field validation
	/**
	 * Field totalBase.
	 */
	@Basic(optional = false)
	@Column(name = "TOTAL_BASE", nullable = false, precision = 13, scale = 2)
	private BigDecimal totalBase;
	/**
	 * Field totalImpuesto.
	 */
	@Basic(optional = false)
	@Column(name = "TOTAL_IMPUESTO", nullable = false, precision = 13, scale = 2)
	private BigDecimal totalImpuesto;
	/**
	 * Field totalRebaja.
	 */
	@Basic(optional = false)
	@Column(name = "TOTAL_REBAJA", nullable = false, precision = 13, scale = 2)
	private BigDecimal totalRebaja;
	/**
	 * Field vuelto.
	 */
	@Basic(optional = false)
	@Column(name = "VUELTO", nullable = false, precision = 13, scale = 2)
	private BigDecimal vuelto;
	/**
	 * Field renglones.
	 */
	@Basic(optional = false)
	@Column(name = "RENGLONES", nullable = false)
	private int renglones;
	/**
	 * Field donacion.
	 */
	@Basic(optional = false)
	@Column(name = "DONACION", nullable = false, precision = 13, scale = 2)
	private BigDecimal donacion;
	/**
	 * Field origenTransaccion.
	 */
	@Basic(optional = false)
	@Column(name = "ORIGEN_TRANSACCION", nullable = false, length = 50)
	private String origenTransaccion;
	/**
	 * Field totalTransaccion.
	 */
	@Basic(optional = false)
	@Column(name = "TOTAL_TRANSACCION", nullable = false, precision = 13, scale = 2)
	private BigDecimal totalTransaccion;
	/**
	 * Field estreplica.
	 */
	@Basic(optional = false)
	@Column(name = "ESTREPLICA")
	private char estreplica;
	/**
	 * Field transaccionformadepagoList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idTransaccion")
	private List<Transaccionformadepago> transaccionformadepagoList;
	/**
	 * Field numeroIdentificacionCliente.
	 */
	@JoinColumn(name = "NUMERO_IDENTIFICACION_CLIENTE", referencedColumnName = "NUMERO_IDENTIFICACION_CLIENTE")
	@ManyToOne
	private Cliente numeroIdentificacionCliente;
	/**
	 * Field idUsuario.
	 */
	@JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	private Usuario idUsuario;
	/**
	 * Field idRolloauditoria.
	 */
	@JoinColumn(name = "ID_ROLLOAUDITORIA", referencedColumnName = "ID")
	@ManyToOne
	private Rolloauditoria idRolloauditoria;
	/**
	 * Field idSerialimpresora.
	 */
	@JoinColumn(name = "ID_SERIALIMPRESORA", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	private Serialimpresora idSerialimpresora;
	/**
	 * Field idSesion.
	 */
	@JoinColumn(name = "ID_SESION", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	private Sesion idSesion;
	/**
	 * Field transaccionList.
	 */
	@OneToMany(mappedBy = "idAnulaciontransaccion")
	private List<Transaccion> transaccionList;
	/**
	 * Field idAnulaciontransaccion.
	 */
	@JoinColumn(name = "ID_ANULACIONTRANSACCION", referencedColumnName = "ID")
	@ManyToOne
	private Transaccion idAnulaciontransaccion;
	/**
	 * Field transaccionarticuloList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idTransaccion")
	private List<Transaccionarticulo> transaccionarticuloList;
	/**
	 * Field transaccionfaseList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idTransaccion")
	private List<Transaccionfase> transaccionfaseList;
	/**
	 * Field transacciondocumentoList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idTransaccion")
	private List<Transacciondocumento> transacciondocumentoList;
	/**
	 * Field transaccionclienteList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idTransaccion")
	private List<Transaccioncliente> transaccionclienteList;
	/**
	 * Field devolucionList.
	 */
	// @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTransaccion")
	// private List<Devolucion> devolucionList;
	/**
	 * Field pagocreditoList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idTransaccion")
	private List<Pagocredito> pagocreditoList;
	/**
	 * Field idComprobantefiscalpreimpreso.
	 */
	@JoinColumn(name = "ID_COMPROBANTEFISCALPREIMPRESO", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	private Comprobantefiscalpreimpreso idComprobantefiscalpreimpreso;

	/**
	 * Field transaccionclienteList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idTransaccion")
	private List<Transaccionauditoria> transaccionauditoriaList;

	/**
	 * Constructor for Transaccion.
	 */
	public Transaccion() {
	}

	/**
	 * Constructor for Transaccion.
	 * 
	 * @param id
	 *            Long
	 */
	public Transaccion(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Transaccion.
	 * 
	 * @param id
	 *            Long
	 * @param numero
	 *            long
	 * @param numeroFiscal
	 *            int
	 * @param tipo
	 *            String
	 * @param estado
	 *            char
	 * @param fecha
	 *            Date
	 * @param totalBase
	 *            BigDecimal
	 * @param totalImpuesto
	 *            BigDecimal
	 * @param totalRebaja
	 *            BigDecimal
	 * @param vuelto
	 *            BigDecimal
	 * @param renglones
	 *            int
	 * @param donacion
	 *            BigDecimal
	 * @param origenTransaccion
	 *            String
	 * @param totalTransaccion
	 *            BigDecimal
	 * @param estreplica
	 *            char
	 */
	public Transaccion(Long id, long numero, int numeroFiscal, String tipo,
			char estado, Date fecha, BigDecimal totalBase,
			BigDecimal totalImpuesto, BigDecimal totalRebaja, BigDecimal vuelto,
			int renglones, BigDecimal donacion, String origenTransaccion,
			BigDecimal totalTransaccion, char estreplica) {
		this.id = id;
		this.numero = numero;
		this.numeroFiscal = numeroFiscal;
		this.tipo = tipo;
		this.estado = estado;
		this.fecha = fecha;
		this.totalBase = totalBase;
		this.totalImpuesto = totalImpuesto;
		this.totalRebaja = totalRebaja;
		this.vuelto = vuelto;
		this.renglones = renglones;
		this.donacion = donacion;
		this.origenTransaccion = origenTransaccion;
		this.totalTransaccion = totalTransaccion;
		this.estreplica = estreplica;
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
	 * Method getNumero.
	 * 
	 * @return long
	 */
	public long getNumero() {
		return numero;
	}

	/**
	 * Method setNumero.
	 * 
	 * @param numero
	 *            long
	 */
	public void setNumero(long numero) {
		this.numero = numero;
	}

	/**
	 * Method getNumeroFiscal.
	 * 
	 * @return int
	 */
	public int getNumeroFiscal() {
		return numeroFiscal;
	}

	/**
	 * Method setNumeroFiscal.
	 * 
	 * @param numeroFiscal
	 *            int
	 */
	public void setNumeroFiscal(int numeroFiscal) {
		this.numeroFiscal = numeroFiscal;
	}

	/**
	 * Method getCajero.
	 * 
	 * @return Integer
	 */
	public Integer getCajero() {
		return cajero;
	}

	/**
	 * Method setCajero.
	 * 
	 * @param cajero
	 *            Integer
	 */
	public void setCajero(Integer cajero) {
		this.cajero = cajero;
	}

	/**
	 * Method getTipo.
	 * 
	 * @return String
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * Method setTipo.
	 * 
	 * @param tipo
	 *            String
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * Method getEstado.
	 * 
	 * @return char
	 */
	public char getEstado() {
		return estado;
	}

	/**
	 * Method setEstado.
	 * 
	 * @param estado
	 *            char
	 */
	public void setEstado(char estado) {
		this.estado = estado;
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
	 * Method getTotalBase.
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal getTotalBase() {
		return totalBase;
	}

	/**
	 * Method setTotalBase.
	 * 
	 * @param totalBase
	 *            BigDecimal
	 */
	public void setTotalBase(BigDecimal totalBase) {
		this.totalBase = totalBase;
	}

	/**
	 * Method getTotalImpuesto.
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal getTotalImpuesto() {
		return totalImpuesto;
	}

	/**
	 * Method setTotalImpuesto.
	 * 
	 * @param totalImpuesto
	 *            BigDecimal
	 */
	public void setTotalImpuesto(BigDecimal totalImpuesto) {
		this.totalImpuesto = totalImpuesto;
	}

	/**
	 * Method getTotalRebaja.
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal getTotalRebaja() {
		return totalRebaja;
	}

	/**
	 * Method setTotalRebaja.
	 * 
	 * @param totalRebaja
	 *            BigDecimal
	 */
	public void setTotalRebaja(BigDecimal totalRebaja) {
		this.totalRebaja = totalRebaja;
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
	 * @param vuelto
	 *            BigDecimal
	 */
	public void setVuelto(BigDecimal vuelto) {
		this.vuelto = vuelto;
	}

	/**
	 * Method getRenglones.
	 * 
	 * @return int
	 */
	public int getRenglones() {
		return renglones;
	}

	/**
	 * Method setRenglones.
	 * 
	 * @param renglones
	 *            int
	 */
	public void setRenglones(int renglones) {
		this.renglones = renglones;
	}

	/**
	 * Method getDonacion.
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal getDonacion() {
		return donacion;
	}

	/**
	 * Method setDonacion.
	 * 
	 * @param donacion
	 *            BigDecimal
	 */
	public void setDonacion(BigDecimal donacion) {
		this.donacion = donacion;
	}

	/**
	 * Method getOrigenTransaccion.
	 * 
	 * @return String
	 */
	public String getOrigenTransaccion() {
		return origenTransaccion;
	}

	/**
	 * Method setOrigenTransaccion.
	 * 
	 * @param origenTransaccion
	 *            String
	 */
	public void setOrigenTransaccion(String origenTransaccion) {
		this.origenTransaccion = origenTransaccion;
	}

	/**
	 * Method getTotalTransaccion.
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal getTotalTransaccion() {
		return totalTransaccion;
	}

	/**
	 * Method setTotalTransaccion.
	 * 
	 * @param totalTransaccion
	 *            BigDecimal
	 */
	public void setTotalTransaccion(BigDecimal totalTransaccion) {
		this.totalTransaccion = totalTransaccion;
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
	 * Method getNumeroIdentificacionCliente.
	 * 
	 * @return Cliente
	 */
	public Cliente getNumeroIdentificacionCliente() {
		return numeroIdentificacionCliente;
	}

	/**
	 * Method setNumeroIdentificacionCliente.
	 * 
	 * @param numeroIdentificacionCliente
	 *            Cliente
	 */
	public void setNumeroIdentificacionCliente(
			Cliente numeroIdentificacionCliente) {
		this.numeroIdentificacionCliente = numeroIdentificacionCliente;
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
	 * @param idUsuario
	 *            Usuario
	 */
	public void setIdUsuario(Usuario idUsuario) {
		this.idUsuario = idUsuario;
	}

	/**
	 * Method getIdRolloauditoria.
	 * 
	 * @return Rolloauditoria
	 */
	public Rolloauditoria getIdRolloauditoria() {
		return idRolloauditoria;
	}

	/**
	 * Method setIdRolloauditoria.
	 * 
	 * @param idRolloauditoria
	 *            Rolloauditoria
	 */
	public void setIdRolloauditoria(Rolloauditoria idRolloauditoria) {
		this.idRolloauditoria = idRolloauditoria;
	}

	/**
	 * Method getIdSerialimpresora.
	 * 
	 * @return Serialimpresora
	 */
	public Serialimpresora getIdSerialimpresora() {
		return idSerialimpresora;
	}

	/**
	 * Method setIdSerialimpresora.
	 * 
	 * @param idSerialimpresora
	 *            Serialimpresora
	 */
	public void setIdSerialimpresora(Serialimpresora idSerialimpresora) {
		this.idSerialimpresora = idSerialimpresora;
	}

	/**
	 * Method getIdSesion.
	 * 
	 * @return Sesion
	 */
	public Sesion getIdSesion() {
		return idSesion;
	}

	/**
	 * Method setIdSesion.
	 * 
	 * @param idSesion
	 *            Sesion
	 */
	public void setIdSesion(Sesion idSesion) {
		this.idSesion = idSesion;
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
	 * @param transaccionList
	 *            List<Transaccion>
	 */
	public void setTransaccionList(List<Transaccion> transaccionList) {
		this.transaccionList = transaccionList;
	}

	/**
	 * Method getIdAnulaciontransaccion.
	 * 
	 * @return Transaccion
	 */
	public Transaccion getIdAnulaciontransaccion() {
		return idAnulaciontransaccion;
	}

	/**
	 * Method setIdAnulaciontransaccion.
	 * 
	 * @param idAnulaciontransaccion
	 *            Transaccion
	 */
	public void setIdAnulaciontransaccion(Transaccion idAnulaciontransaccion) {
		this.idAnulaciontransaccion = idAnulaciontransaccion;
	}

	/**
	 * Method getTransaccionarticuloList.
	 * 
	 * @return List<Transaccionarticulo>
	 */
	@XmlTransient
	public List<Transaccionarticulo> getTransaccionarticuloList() {
		return transaccionarticuloList;
	}

	/**
	 * Method setTransaccionarticuloList.
	 * 
	 * @param transaccionarticuloList
	 *            List<Transaccionarticulo>
	 */
	public void setTransaccionarticuloList(
			List<Transaccionarticulo> transaccionarticuloList) {
		this.transaccionarticuloList = transaccionarticuloList;
	}

	/**
	 * Method getTransaccionfaseList.
	 * 
	 * @return List<Transaccionfase>
	 */
	@XmlTransient
	public List<Transaccionfase> getTransaccionfaseList() {
		return transaccionfaseList;
	}

	/**
	 * Method setTransaccionfaseList.
	 * 
	 * @param transaccionfaseList
	 *            List<Transaccionfase>
	 */
	public void setTransaccionfaseList(
			List<Transaccionfase> transaccionfaseList) {
		this.transaccionfaseList = transaccionfaseList;
	}

	/**
	 * Method getTransacciondocumentoList.
	 * 
	 * @return List<Transacciondocumento>
	 */
	@XmlTransient
	public List<Transacciondocumento> getTransacciondocumentoList() {
		return transacciondocumentoList;
	}

	/**
	 * Method setTransacciondocumentoList.
	 * 
	 * @param transacciondocumentoList
	 *            List<Transacciondocumento>
	 */
	public void setTransacciondocumentoList(
			List<Transacciondocumento> transacciondocumentoList) {
		this.transacciondocumentoList = transacciondocumentoList;
	}

	/**
	 * Method getTransaccionclienteList.
	 * 
	 * @return List<Transaccioncliente>
	 */
	@XmlTransient
	public List<Transaccioncliente> getTransaccionclienteList() {
		return transaccionclienteList;
	}

	/**
	 * Method setTransaccionclienteList.
	 * 
	 * @param transaccionclienteList
	 *            List<Transaccioncliente>
	 */
	public void setTransaccionclienteList(
			List<Transaccioncliente> transaccionclienteList) {
		this.transaccionclienteList = transaccionclienteList;
	}

	/**
	 * Method getDevolucionList.
	 * 
	 * @reidSerialimpresoraturn List<Devolucion>
	 */
	// @XmlTransient
	// public List<Devolucion> getDevolucionList() {
	// return devolucionList;
	// }
	//
	// /**
	// * Method setDevolucionList.
	// *
	// * @param devolucionList
	// * List<Devolucion>
	// */
	// public void setDevolucionList(List<Devolucion> devolucionList) {
	// this.devolucionList = devolucionList;
	// }

	/**
	 * Method getPagocreditoList.
	 * 
	 * @return List<Pagocredito>
	 */
	@XmlTransient
	public List<Pagocredito> getPagocreditoList() {
		return pagocreditoList;
	}

	/**
	 * Method setPagocreditoList.
	 * 
	 * @param pagocreditoList
	 *            List<Pagocredito>
	 */
	public void setPagocreditoList(List<Pagocredito> pagocreditoList) {
		this.pagocreditoList = pagocreditoList;
	}

	public List<Transaccionauditoria> getTransaccionauditoriaList() {
		return transaccionauditoriaList;
	}

	public void setTransaccionauditoriaList(
			List<Transaccionauditoria> transaccionauditoriaList) {
		this.transaccionauditoriaList = transaccionauditoriaList;
	}

	/**
	 * @return the idComprobantefiscalpreimpreso
	 */
	public Comprobantefiscalpreimpreso getIdComprobantefiscalpreimpreso() {
		return idComprobantefiscalpreimpreso;
	}

	/**
	 * @param idComprobantefiscalpreimpreso
	 *            the idComprobantefiscalpreimpreso to set
	 */
	public void setIdComprobantefiscalpreimpreso(
			Comprobantefiscalpreimpreso idComprobantefiscalpreimpreso) {
		this.idComprobantefiscalpreimpreso = idComprobantefiscalpreimpreso;
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
		if (!(object instanceof Transaccion)) {
			return false;
		}
		Transaccion other = (Transaccion) object;
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
		return "crjpa400.Transaccion[ id=" + id + " ]";
	}

}
