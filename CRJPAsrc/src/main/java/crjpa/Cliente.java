/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crjpa;

import java.io.Serializable;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "cliente")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c"),
		@NamedQuery(name = "Cliente.findByNumeroIdentificacionCliente", query = "SELECT c FROM Cliente c WHERE c.numeroIdentificacionCliente = :numeroIdentificacionCliente"),
		@NamedQuery(name = "Cliente.findByFecha", query = "SELECT c FROM Cliente c WHERE c.fecha = :fecha"),
		@NamedQuery(name = "Cliente.findByFechaActualizacion", query = "SELECT c FROM Cliente c WHERE c.fechaActualizacion = :fechaActualizacion"),
		@NamedQuery(name = "Cliente.findByNombre", query = "SELECT c FROM Cliente c WHERE c.nombre = :nombre"),
		@NamedQuery(name = "Cliente.findByNumeroIdentificacionTributario", query = "SELECT c FROM Cliente c WHERE c.numeroIdentificacionTributario = :numeroIdentificacionTributario"),
		@NamedQuery(name = "Cliente.findByDireccion", query = "SELECT c FROM Cliente c WHERE c.direccion = :direccion"),
		@NamedQuery(name = "Cliente.findByDireccionFiscal", query = "SELECT c FROM Cliente c WHERE c.direccionFiscal = :direccionFiscal"),
		@NamedQuery(name = "Cliente.findByTelefono", query = "SELECT c FROM Cliente c WHERE c.telefono = :telefono"),
		@NamedQuery(name = "Cliente.findByCorreoElectronico", query = "SELECT c FROM Cliente c WHERE c.correoElectronico = :correoElectronico"),
		@NamedQuery(name = "Cliente.findByEsagenteretencion", query = "SELECT c FROM Cliente c WHERE c.esagenteretencion = :esagenteretencion"),
		@NamedQuery(name = "Cliente.findByEsdiplomatico", query = "SELECT c FROM Cliente c WHERE c.esdiplomatico = :esdiplomatico"),
		@NamedQuery(name = "Cliente.findByFicha", query = "SELECT c FROM Cliente c WHERE c.ficha = :ficha"),
		@NamedQuery(name = "Cliente.findByEstasincronizado", query = "SELECT c FROM Cliente c WHERE c.estasincronizado = :estasincronizado"),
		@NamedQuery(name = "Cliente.findByEsgrande", query = "SELECT c FROM Cliente c WHERE c.esgrande = :esgrande") })
public class Cliente implements Serializable {
	/**
	 * Field serialVersionUID. (value is 1)
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Field numeroIdentificacionCliente.
	 */
	@Id
	@Basic(optional = false)
	@Column(name = "numero_identificacion_cliente")
	private String numeroIdentificacionCliente;
	/**
	 * Field fecha.
	 */
	@Basic(optional = false)
	@Column(name = "fecha")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	/**
	 * Field fechaActualizacion.
	 */
	@Basic(optional = false)
	@Column(name = "fecha_actualizacion")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaActualizacion;
	/**
	 * Field nombre.
	 */
	@Basic(optional = false)
	@Column(name = "nombre")
	private String nombre;
	/**
	 * Field numeroIdentificacionTributario.
	 */
	@Basic(optional = false)
	@Column(name = "numero_identificacion_tributario")
	private String numeroIdentificacionTributario;
	/**
	 * Field direccion.
	 */
	@Column(name = "direccion")
	private String direccion;
	/**
	 * Field direccionFiscal.
	 */
	@Column(name = "direccion_fiscal")
	private String direccionFiscal;
	/**
	 * Field telefono.
	 */
	@Column(name = "telefono")
	private String telefono;
	/**
	 * Field correoElectronico.
	 */
	@Column(name = "correo_electronico")
	private String correoElectronico;
	/**
	 * Field esagenteretencion.
	 */
	@Basic(optional = false)
	@Column(name = "esagenteretencion")
	private String esagenteretencion;
	/**
	 * Field esdiplomatico.
	 */
	@Basic(optional = false)
	@Column(name = "esdiplomatico")
	private String esdiplomatico;
	/**
	 * Field ficha.
	 */
	@Column(name = "ficha")
	private BigInteger ficha;
	/**
	 * Field estasincronizado.
	 */
	@Basic(optional = false)
	@Column(name = "estasincronizado")
	private String estasincronizado;
	/**
	 * Field esgrande.
	 */
	@Basic(optional = false)
	@Column(name = "esgrande")
	private String esgrande;
	/**
	 * Field transaccionclienteList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "numeroIdentificacionCliente")
	private List<Transaccioncliente> transaccionclienteList;
	/**
	 * Field idExoneradotipo.
	 */
	@JoinColumn(name = "id_exoneradotipo", referencedColumnName = "id")
	@ManyToOne
	private Exoneradotipo idExoneradotipo;
	/**
	 * Field idTipocliente.
	 */
	@JoinColumn(name = "id_tipocliente", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Tipocliente idTipocliente;
	/**
	 * Field idGiroactividadeconomica.
	 */
	@JoinColumn(name = "id_giroactividadeconomica", referencedColumnName = "id")
	@ManyToOne
	private Giroactividadeconomica idGiroactividadeconomica;
	/**
	 * Field transaccionList.
	 */
	@OneToMany(mappedBy = "numeroIdentificacionCliente")
	private List<Transaccion> transaccionList;
	/**
	 * Field morosidadList.
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "numeroIdentificacionCliente")
	private List<Morosidad> morosidadList;
	/**
	 * Field clientemensajeList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "numeroIdentificacionCliente")
	private List<Clientemensaje> clientemensajeList;
	/**
	 * Field cuentacreditoList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "numeroIdentificacionCliente")
	private List<Cuentacredito> cuentacreditoList;

	/**
	 * Constructor for Cliente.
	 */
	public Cliente() {
	}

	/**
	 * Constructor for Cliente.
	 * 
	 * @param numeroIdentificacionCliente
	 *            String
	 */
	public Cliente(String numeroIdentificacionCliente) {
		this.numeroIdentificacionCliente = numeroIdentificacionCliente;
	}

	/**
	 * Constructor for Cliente.
	 * 
	 * @param numeroIdentificacionCliente
	 *            String
	 * @param fecha
	 *            Date
	 * @param fechaActualizacion
	 *            Date
	 * @param nombre
	 *            String
	 * @param numeroIdentificacionTributario
	 *            String
	 * @param esagenteretencion
	 *            String
	 * @param esdiplomatico
	 *            String
	 * @param estasincronizado
	 *            String
	 * @param esgrande
	 *            String
	 */
	public Cliente(String numeroIdentificacionCliente, Date fecha, Date fechaActualizacion, String nombre,
			String numeroIdentificacionTributario, String esagenteretencion, String esdiplomatico,
			String estasincronizado, String esgrande) {
		this.numeroIdentificacionCliente = numeroIdentificacionCliente;
		this.fecha = fecha;
		this.fechaActualizacion = fechaActualizacion;
		this.nombre = nombre;
		this.numeroIdentificacionTributario = numeroIdentificacionTributario;
		this.esagenteretencion = esagenteretencion;
		this.esdiplomatico = esdiplomatico;
		this.estasincronizado = estasincronizado;
		this.esgrande = esgrande;
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
	 * @param numeroIdentificacionCliente
	 *            String
	 */
	public void setNumeroIdentificacionCliente(String numeroIdentificacionCliente) {
		this.numeroIdentificacionCliente = numeroIdentificacionCliente;
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
	 * Method getNumeroIdentificacionTributario.
	 * 
	 * @return String
	 */
	public String getNumeroIdentificacionTributario() {
		return numeroIdentificacionTributario;
	}

	/**
	 * Method setNumeroIdentificacionTributario.
	 * 
	 * @param numeroIdentificacionTributario
	 *            String
	 */
	public void setNumeroIdentificacionTributario(String numeroIdentificacionTributario) {
		this.numeroIdentificacionTributario = numeroIdentificacionTributario;
	}

	/**
	 * Method getDireccion.
	 * 
	 * @return String
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * Method setDireccion.
	 * 
	 * @param direccion
	 *            String
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * Method getDireccionFiscal.
	 * 
	 * @return String
	 */
	public String getDireccionFiscal() {
		return direccionFiscal;
	}

	/**
	 * Method setDireccionFiscal.
	 * 
	 * @param direccionFiscal
	 *            String
	 */
	public void setDireccionFiscal(String direccionFiscal) {
		this.direccionFiscal = direccionFiscal;
	}

	/**
	 * Method getTelefono.
	 * 
	 * @return String
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * Method setTelefono.
	 * 
	 * @param telefono
	 *            String
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * Method getCorreoElectronico.
	 * 
	 * @return String
	 */
	public String getCorreoElectronico() {
		return correoElectronico;
	}

	/**
	 * Method setCorreoElectronico.
	 * 
	 * @param correoElectronico
	 *            String
	 */
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	/**
	 * Method getEsagenteretencion.
	 * 
	 * @return String
	 */
	public String getEsagenteretencion() {
		return esagenteretencion;
	}

	/**
	 * Method setEsagenteretencion.
	 * 
	 * @param esagenteretencion
	 *            String
	 */
	public void setEsagenteretencion(String esagenteretencion) {
		this.esagenteretencion = esagenteretencion;
	}

	/**
	 * Method getEsdiplomatico.
	 * 
	 * @return String
	 */
	public String getEsdiplomatico() {
		return esdiplomatico;
	}

	/**
	 * Method setEsdiplomatico.
	 * 
	 * @param esdiplomatico
	 *            String
	 */
	public void setEsdiplomatico(String esdiplomatico) {
		this.esdiplomatico = esdiplomatico;
	}

	/**
	 * Method getFicha.
	 * 
	 * @return BigInteger
	 */
	public BigInteger getFicha() {
		return ficha;
	}

	/**
	 * Method setFicha.
	 * 
	 * @param ficha
	 *            BigInteger
	 */
	public void setFicha(BigInteger ficha) {
		this.ficha = ficha;
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
	 * @param estasincronizado
	 *            String
	 */
	public void setEstasincronizado(String estasincronizado) {
		this.estasincronizado = estasincronizado;
	}

	/**
	 * Method getEsgrande.
	 * 
	 * @return String
	 */
	public String getEsgrande() {
		return esgrande;
	}

	/**
	 * Method setEsgrande.
	 * 
	 * @param esgrande
	 *            String
	 */
	public void setEsgrande(String esgrande) {
		this.esgrande = esgrande;
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
	public void setTransaccionclienteList(List<Transaccioncliente> transaccionclienteList) {
		this.transaccionclienteList = transaccionclienteList;
	}

	/**
	 * Method getIdExoneradotipo.
	 * 
	 * @return Exoneradotipo
	 */
	public Exoneradotipo getIdExoneradotipo() {
		return idExoneradotipo;
	}

	/**
	 * Method setIdExoneradotipo.
	 * 
	 * @param idExoneradotipo
	 *            Exoneradotipo
	 */
	public void setIdExoneradotipo(Exoneradotipo idExoneradotipo) {
		this.idExoneradotipo = idExoneradotipo;
	}

	/**
	 * Method getIdTipocliente.
	 * 
	 * @return Tipocliente
	 */
	public Tipocliente getIdTipocliente() {
		return idTipocliente;
	}

	/**
	 * Method setIdTipocliente.
	 * 
	 * @param idTipocliente
	 *            Tipocliente
	 */
	public void setIdTipocliente(Tipocliente idTipocliente) {
		this.idTipocliente = idTipocliente;
	}

	/**
	 * Method getIdGiroactividadeconomica.
	 * 
	 * @return Giroactividadeconomica
	 */
	public Giroactividadeconomica getIdGiroactividadeconomica() {
		return idGiroactividadeconomica;
	}

	/**
	 * Method setIdGiroactividadeconomica.
	 * 
	 * @param idGiroactividadeconomica
	 *            Giroactividadeconomica
	 */
	public void setIdGiroactividadeconomica(Giroactividadeconomica idGiroactividadeconomica) {
		this.idGiroactividadeconomica = idGiroactividadeconomica;
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
	 * Method getClientemensajeList.
	 * 
	 * @return List<Clientemensaje>
	 */
	@XmlTransient
	public List<Clientemensaje> getClientemensajeList() {
		return clientemensajeList;
	}

	/**
	 * Method setClientemensajeList.
	 * 
	 * @param clientemensajeList
	 *            List<Clientemensaje>
	 */
	public void setClientemensajeList(List<Clientemensaje> clientemensajeList) {
		this.clientemensajeList = clientemensajeList;
	}

	/**
	 * Method getCuentacreditoList.
	 * 
	 * @return List<Cuentacredito>
	 */
	@XmlTransient
	public List<Cuentacredito> getCuentacreditoList() {
		return cuentacreditoList;
	}

	/**
	 * Method setCuentacreditoList.
	 * 
	 * @param cuentacreditoList
	 *            List<Cuentacredito>
	 */
	public void setCuentacreditoList(List<Cuentacredito> cuentacreditoList) {
		this.cuentacreditoList = cuentacreditoList;
	}

	/**
	 * Method hashCode.
	 * 
	 * @return int
	 */
	@Override
	public int hashCode() {
		int hash = 0;
		hash += (numeroIdentificacionCliente != null ? numeroIdentificacionCliente.hashCode() : 0);
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
		if (!(object instanceof Cliente)) {
			return false;
		}
		Cliente other = (Cliente) object;
		if ((this.numeroIdentificacionCliente == null && other.numeroIdentificacionCliente != null)
				|| (this.numeroIdentificacionCliente != null && !this.numeroIdentificacionCliente
						.equals(other.numeroIdentificacionCliente))) {
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
		String separator = "@@";
		String enclosed = "|";
		String nullStr = "\\N";
		String endStr = "\r\n";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		StringBuilder sb = new StringBuilder();

		sb.append(enclosed);
		sb.append(numeroIdentificacionCliente);
		sb.append(enclosed);
		sb.append(separator);

		sb.append(idTipocliente.getId());
		sb.append(separator);

		if (idGiroactividadeconomica != null) {
			if (idGiroactividadeconomica.getId() == null) {
				sb.append(nullStr);
			} else {
				if (idGiroactividadeconomica.getId() > 0) {
					sb.append(idGiroactividadeconomica.getId());
				} else {
					sb.append(nullStr);
				}
			}
		}else {
			sb.append(nullStr);
		}
		sb.append(separator);

		if (idExoneradotipo.getId() == null) {
			sb.append(nullStr);
		} else {
			if (idExoneradotipo.getId() > 0 && idExoneradotipo.getId() < 999) {
				sb.append(idExoneradotipo.getId());
			} else {
				sb.append(nullStr);
			}
		}
		sb.append(separator);

		sb.append(fecha == null ? nullStr : simpleDateFormat.format(fecha));
		sb.append(separator);

		sb.append(fechaActualizacion == null ? nullStr : simpleDateFormat.format(fechaActualizacion));
		sb.append(separator);

		sb.append(enclosed);
		sb.append(nombre);
		sb.append(enclosed);
		sb.append(separator);

		sb.append(enclosed);
		sb.append(numeroIdentificacionTributario);
		sb.append(enclosed);
		sb.append(separator);

		if (direccion == null) {
			sb.append(nullStr);
		} else {
			sb.append(enclosed);
			sb.append(direccion);
			sb.append(enclosed);
		}
		sb.append(separator);

		if (direccionFiscal == null) {
			sb.append(nullStr);
		} else {
			sb.append(enclosed);
			sb.append(direccionFiscal);
			sb.append(enclosed);
		}
		sb.append(separator);

		if (telefono == null) {
			sb.append(nullStr);
		} else {
			sb.append(enclosed);
			sb.append(telefono);
			sb.append(enclosed);
		}
		sb.append(separator);

		if (correoElectronico == null) {
			sb.append(nullStr);
		} else {
			sb.append(enclosed);
			sb.append(correoElectronico);
			sb.append(enclosed);
		}
		sb.append(separator);

		sb.append(esagenteretencion);
		sb.append(separator);

		sb.append(esdiplomatico);
		sb.append(separator);

		if (ficha == null) {
			sb.append(nullStr);
		} else {
			sb.append(ficha);
		}
		sb.append(separator);

		sb.append(estasincronizado);
		sb.append(separator);

		sb.append(esgrande);
		sb.append(endStr);

		return sb.toString();
	}

}
