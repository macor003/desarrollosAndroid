/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crjpa;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
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
@Table(name = "cuentacredito")
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
		@NamedQuery(name = "Cuentacredito.findByTipo", query = "SELECT c FROM Cuentacredito c WHERE c.tipo = :tipo") })
public class Cuentacredito implements Serializable {
	/**
	 * Field serialVersionUID. (value is 1)
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Field id.
	 */
	@Id
	@Basic(optional = false)
	@Column(name = "id")
	private Long id;
	/**
	 * Field estadoTarjeta.
	 */
	@Column(name = "estado_tarjeta")
	private String estadoTarjeta;
	/**
	 * Field fechaCreacion.
	 */
	@Basic(optional = false)
	@Column(name = "fecha_creacion")
	@Temporal(TemporalType.DATE)
	private Date fechaCreacion;
	/**
	 * Field fechaActualizacion.
	 */
	@Basic(optional = false)
	@Column(name = "fecha_actualizacion")
	@Temporal(TemporalType.DATE)
	private Date fechaActualizacion;
	/**
	 * Field fechaExpiracion.
	 */
	@Basic(optional = false)
	@Column(name = "fecha_expiracion")
	@Temporal(TemporalType.DATE)
	private Date fechaExpiracion;
	/**
	 * Field fechaMora.
	 */
	@Basic(optional = false)
	@Column(name = "fecha_mora")
	@Temporal(TemporalType.DATE)
	private Date fechaMora;
	/**
	 * Field fechaUltimopago.
	 */
	@Basic(optional = false)
	@Column(name = "fecha_ultimopago", nullable = true)
	@Temporal(TemporalType.DATE)
	private Date fechaUltimopago;
	// @Max(value=?) @Min(value=?)//if you know range of your decimal fields
	// consider using these annotations to enforce field validation
	/**
	 * Field lineaCredito.
	 */
	@Basic(optional = false)
	@Column(name = "linea_credito")
	private BigDecimal lineaCredito;
	/**
	 * Field deudaPendiente.
	 */
	@Basic(optional = false)
	@Column(name = "deuda_pendiente", nullable = true)
	private BigDecimal deudaPendiente;
	/**
	 * Field tipo.
	 */
	@Column(name = "tipo")
	private String tipo;
	/**
	 * Field estasincronizado.
	 */
	@Column(name = "estasincronizado", nullable = false, length = 2)
	private String estasincronizado;
	/**
	 * Field pagocreditoList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idCuentacredito")
	private List<Pagocredito> pagocreditoList;
	/**
	 * Field numeroIdentificacionCliente.
	 */
	@JoinColumn(name = "numero_identificacion_cliente", referencedColumnName = "numero_identificacion_cliente")
	@ManyToOne(optional = false)
	private Cliente numeroIdentificacionCliente;

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
	 */
	public Cuentacredito(Long id, Date fechaCreacion, Date fechaActualizacion, Date fechaExpiracion, Date fechaMora,
			Date fechaUltimopago, BigDecimal lineaCredito, BigDecimal deudaPendiente) {
		this.id = id;
		this.fechaCreacion = fechaCreacion;
		this.fechaActualizacion = fechaActualizacion;
		this.fechaExpiracion = fechaExpiracion;
		this.fechaMora = fechaMora;
		this.fechaUltimopago = fechaUltimopago;
		this.lineaCredito = lineaCredito;
		this.deudaPendiente = deudaPendiente;
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
	 * @return String
	 */
	public String getEstadoTarjeta() {
		return estadoTarjeta;
	}

	/**
	 * Method setEstadoTarjeta.
	 * 
	 * @param estadoTarjeta
	 *            String
	 */
	public void setEstadoTarjeta(String estadoTarjeta) {
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
	public void setNumeroIdentificacionCliente(Cliente numeroIdentificacionCliente) {
		this.numeroIdentificacionCliente = numeroIdentificacionCliente;
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
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	// @Override
	// public String toString() {
	// return "crjpa.Cuentacredito[ id=" + id + " ]";
	// }

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
	 * @param estasincronizado
	 *            String
	 */
	public void setEstasincronizado(String estasincronizado) {
		this.estasincronizado = estasincronizado;
	}

	/**
	 * Method toString.
	 * 
	 * @return String
	 */
	@Override
	public String toString() {
		String separator = "@@";
		String enclosed = "|";
		String nullStr = "\\N";
		String endStr = "\r\n";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		StringBuilder sb = new StringBuilder();

		sb.append(id);
		sb.append(separator);

		sb.append(enclosed);
		sb.append(numeroIdentificacionCliente.getNumeroIdentificacionCliente());
		sb.append(enclosed);
		sb.append(separator);

		if (estadoTarjeta == null) {
			sb.append(nullStr);
		} else {
			sb.append(enclosed);
			sb.append(estadoTarjeta);
			sb.append(enclosed);
		}
		sb.append(separator);

		sb.append(fechaCreacion == null ? nullStr : simpleDateFormat.format(fechaCreacion));
		sb.append(separator);

		sb.append(fechaActualizacion == null ? nullStr : simpleDateFormat.format(fechaActualizacion));
		sb.append(separator);

		sb.append(fechaExpiracion == null ? nullStr : simpleDateFormat.format(fechaExpiracion));
		sb.append(separator);

		sb.append(fechaMora == null ? nullStr : simpleDateFormat.format(fechaMora));
		sb.append(separator);

		sb.append(fechaUltimopago == null ? nullStr : simpleDateFormat.format(fechaUltimopago));
		sb.append(separator);

		sb.append(lineaCredito);
		sb.append(separator);

		if (deudaPendiente == null) {
			sb.append(nullStr);
		} else {
			sb.append(deudaPendiente);
		}
		sb.append(separator);

		if (tipo == null) {
			sb.append(nullStr);
		} else {
			sb.append(tipo);
		}
		sb.append(separator);

		if (estasincronizado == null) {
			sb.append("S");
		} else {
			sb.append(estasincronizado);
		}
		sb.append(endStr);

		return sb.toString();
	}

}
