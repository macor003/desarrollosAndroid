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
@Table(name = "CUENTACREDITO")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Cuentacredito.findAll", query = "SELECT c FROM Cuentacredito c"),
		@NamedQuery(name = "Cuentacredito.findById", query = "SELECT c FROM Cuentacredito c WHERE c.id = :id"),
		@NamedQuery(name = "Cuentacredito.findByEstadoTarjeta", query = "SELECT c FROM Cuentacredito c WHERE c.estadoTarjeta = :estadoTarjeta"),
		@NamedQuery(name = "Cuentacredito.findByFechaCreacion", query = "SELECT c FROM Cuentacredito c WHERE c.fechaCreacion = :fechaCreacion"),
		@NamedQuery(name = "Cuentacredito.findByFechaActualizacion", query = "SELECT c FROM Cuentacredito c WHERE c.fechaActualizacion = :fechaActualizacion"),
		@NamedQuery(name = "Cuentacredito.findByFechaExpiracion", query = "SELECT c FROM Cuentacredito c WHERE c.fechaExpiracion = :fechaExpiracion"),
		@NamedQuery(name = "Cuentacredito.findByFechaMora", query = "SELECT c FROM Cuentacredito c WHERE c.fechaMora = :fechaMora"),
		@NamedQuery(name = "Cuentacredito.findByFechaUltimopago", query = "SELECT c FROM Cuentacredito c WHERE c.fechaUltimopago = :fechaUltimopago"),
		@NamedQuery(name = "Cuentacredito.findByLineaCredito", query = "SELECT c FROM Cuentacredito c WHERE c.lineaCredito = :lineaCredito"),
		@NamedQuery(name = "Cuentacredito.findByDeudaPendiente", query = "SELECT c FROM Cuentacredito c WHERE c.deudaPendiente = :deudaPendiente"),
		@NamedQuery(name = "Cuentacredito.findByTipo", query = "SELECT c FROM Cuentacredito c WHERE c.tipo = :tipo"),
		@NamedQuery(name = "Cuentacredito.findByUltimasincronizacion", query = "SELECT c FROM Cuentacredito c WHERE c.ultimasincronizacion = :ultimasincronizacion"),
		@NamedQuery(name = "Cuentacredito.findByEstreplica", query = "SELECT c FROM Cuentacredito c WHERE c.estreplica = :estreplica") })
public class Cuentacredito extends CrjpaInterface implements Serializable {
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
	 * Field estadoTarjeta.
	 */
	@Column(name = "ESTADO_TARJETA")
	private Character estadoTarjeta;
	/**
	 * Field fechaCreacion.
	 */
	@Basic(optional = false)
	@Column(name = "FECHA_CREACION", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date fechaCreacion;
	/**
	 * Field fechaActualizacion.
	 */
	@Basic(optional = false)
	@Column(name = "FECHA_ACTUALIZACION", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date fechaActualizacion;
	/**
	 * Field fechaExpiracion.
	 */
	@Basic(optional = false)
	@Column(name = "FECHA_EXPIRACION", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date fechaExpiracion;
	/**
	 * Field fechaMora.
	 */
	@Basic(optional = false)
	@Column(name = "FECHA_MORA", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date fechaMora;
	/**
	 * Field fechaUltimopago.
	 */
	@Basic(optional = false)
	@Column(name = "FECHA_ULTIMOPAGO", nullable = true)
	@Temporal(TemporalType.DATE)
	private Date fechaUltimopago;
	// @Max(value=?) @Min(value=?)//if you know range of your decimal fields
	// consider using these annotations to enforce field validation
	/**
	 * Field lineaCredito.
	 */
	@Basic(optional = false)
	@Column(name = "LINEA_CREDITO", nullable = false, precision = 13, scale = 2)
	private BigDecimal lineaCredito;
	/**
	 * Field deudaPendiente.
	 */
	@Basic(optional = false)
	@Column(name = "DEUDA_PENDIENTE", nullable = true, precision = 13, scale = 2)
	private BigDecimal deudaPendiente;
	/**
	 * Field tipo.
	 */
	@Column(name = "TIPO", length = 20)
	private String tipo;
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
	 * Field numeroIdentificacionCliente.
	 */
	@JoinColumn(name = "NUMERO_IDENTIFICACION_CLIENTE", referencedColumnName = "NUMERO_IDENTIFICACION_CLIENTE", nullable = false)
	@ManyToOne(optional = false)
	private Cliente numeroIdentificacionCliente;
	/**
	 * Field pagocreditoList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idCuentacredito")
	private List<Pagocredito> pagocreditoList;

	/**
	 * Constructor for Cuentacredito.
	 */
	public Cuentacredito() {
	}

	/**
	 * Constructor for Cuentacredito.
	 * 
	 * @param id
	 *            Long
	 */
	public Cuentacredito(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Cuentacredito.
	 * 
	 * @param id
	 *            Long
	 * @param fechaCreacion
	 *            Date
	 * @param fechaActualizacion
	 *            Date
	 * @param fechaExpiracion
	 *            Date
	 * @param fechaMora
	 *            Date
	 * @param fechaUltimopago
	 *            Date
	 * @param lineaCredito
	 *            BigDecimal
	 * @param deudaPendiente
	 *            BigDecimal
	 * @param ultimasincronizacion
	 *            Calendar
	 * @param estreplica
	 *            char
	 */
	public Cuentacredito(Long id, Date fechaCreacion, Date fechaActualizacion,
			Date fechaExpiracion, Date fechaMora, Date fechaUltimopago,
			BigDecimal lineaCredito, BigDecimal deudaPendiente,
			Calendar ultimasincronizacion, char estreplica) {
		this.id = id;
		this.fechaCreacion = fechaCreacion;
		this.fechaActualizacion = fechaActualizacion;
		this.fechaExpiracion = fechaExpiracion;
		this.fechaMora = fechaMora;
		this.fechaUltimopago = fechaUltimopago;
		this.lineaCredito = lineaCredito;
		this.deudaPendiente = deudaPendiente;
		this.ultimasincronizacion = ultimasincronizacion;
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
	 * Method getEstadoTarjeta.
	 * 
	 * @return Character
	 */
	public Character getEstadoTarjeta() {
		return estadoTarjeta;
	}

	/**
	 * Method setEstadoTarjeta.
	 * 
	 * @param estadoTarjeta
	 *            Character
	 */
	public void setEstadoTarjeta(Character estadoTarjeta) {
		this.estadoTarjeta = estadoTarjeta;
	}

	/**
	 * Method getFechaCreacion.
	 * 
	 * @return Date
	 */
	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	/**
	 * Method setFechaCreacion.
	 * 
	 * @param fechaCreacion
	 *            Date
	 */
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	/**
	 * Method getFechaActualizacion.
	 * 
	 * @return Date
	 */
	public Date getFechaActualizacion() {
		return fechaActualizacion;
	}

	/**
	 * Method setFechaActualizacion.
	 * 
	 * @param fechaActualizacion
	 *            Date
	 */
	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	/**
	 * Method getFechaExpiracion.
	 * 
	 * @return Date
	 */
	public Date getFechaExpiracion() {
		return fechaExpiracion;
	}

	/**
	 * Method setFechaExpiracion.
	 * 
	 * @param fechaExpiracion
	 *            Date
	 */
	public void setFechaExpiracion(Date fechaExpiracion) {
		this.fechaExpiracion = fechaExpiracion;
	}

	/**
	 * Method getFechaMora.
	 * 
	 * @return Date
	 */
	public Date getFechaMora() {
		return fechaMora;
	}

	/**
	 * Method setFechaMora.
	 * 
	 * @param fechaMora
	 *            Date
	 */
	public void setFechaMora(Date fechaMora) {
		this.fechaMora = fechaMora;
	}

	/**
	 * Method getFechaUltimopago.
	 * 
	 * @return Date
	 */
	public Date getFechaUltimopago() {
		return fechaUltimopago;
	}

	/**
	 * Method setFechaUltimopago.
	 * 
	 * @param fechaUltimopago
	 *            Date
	 */
	public void setFechaUltimopago(Date fechaUltimopago) {
		this.fechaUltimopago = fechaUltimopago;
	}

	/**
	 * Method getLineaCredito.
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal getLineaCredito() {
		return lineaCredito;
	}

	/**
	 * Method setLineaCredito.
	 * 
	 * @param lineaCredito
	 *            BigDecimal
	 */
	public void setLineaCredito(BigDecimal lineaCredito) {
		this.lineaCredito = lineaCredito;
	}

	/**
	 * Method getDeudaPendiente.
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal getDeudaPendiente() {
		return deudaPendiente;
	}

	/**
	 * Method setDeudaPendiente.
	 * 
	 * @param deudaPendiente
	 *            BigDecimal
	 */
	public void setDeudaPendiente(BigDecimal deudaPendiente) {
		this.deudaPendiente = deudaPendiente;
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
	 * Method getUltimasincronizacion.
	 * 
	 * @return Calendar
	 */
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
		if (!(object instanceof Cuentacredito)) {
			return false;
		}
		Cuentacredito other = (Cuentacredito) object;
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
		return "crjpa400.Cuentacredito[ id=" + id + " ]";
	}

}
