/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crjpa400;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Calendar;
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
import javax.persistence.OneToOne;
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
@Table(name = "CLIENTE")
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
		@NamedQuery(name = "Cliente.findByUltimasincronizacion", query = "SELECT c FROM Cliente c WHERE c.ultimasincronizacion = :ultimasincronizacion"),
		@NamedQuery(name = "Cliente.findByEsgrande", query = "SELECT c FROM Cliente c WHERE c.esgrande = :esgrande"),
		@NamedQuery(name = "Cliente.findByEstreplica", query = "SELECT c FROM Cliente c WHERE c.estreplica = :estreplica") })
public class Cliente extends CrjpaInterface implements Serializable {
	/**
	 * Field serialVersionUID. (value is 1)
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Field numeroIdentificacionCliente.
	 */
	@Id
	@Basic(optional = false)
	@Column(name = "NUMERO_IDENTIFICACION_CLIENTE", nullable = false, length = 25)
	private String numeroIdentificacionCliente;
	/**
	 * Field fecha.
	 */
	@Basic(optional = false)
	@Column(name = "FECHA", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	/**
	 * Field fechaActualizacion.
	 */
	@Basic(optional = false)
	@Column(name = "FECHA_ACTUALIZACION", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaActualizacion;
	/**
	 * Field nombre.
	 */
	@Column(name = "NOMBRE", nullable = false, length = 255)
	private String nombre;
	/**
	 * Field numeroIdentificacionTributario.
	 */
	@Basic(optional = false)
	@Column(name = "NUMERO_IDENTIFICACION_TRIBUTARIO", nullable = false, length = 25)
	private String numeroIdentificacionTributario;
	/**
	 * Field direccion.
	 */
	@Column(name = "DIRECCION", nullable = false, length = 255)
	private String direccion;
	/**
	 * Field direccionFiscal.
	 */
	@Column(name = "DIRECCION_FISCAL", nullable = false, length = 255)
	private String direccionFiscal;
	/**
	 * Field telefono.
	 */
	@Column(name = "TELEFONO", length = 15)
	private String telefono;
	/**
	 * Field correoElectronico.
	 */
	@Column(name = "CORREO_ELECTRONICO", length = 255)
	private String correoElectronico;
	/**
	 * Field esagenteretencion.
	 */
	@Column(name = "ESAGENTERETENCION")
	private Character esagenteretencion;
	/**
	 * Field esdiplomatico.
	 */
	@Basic(optional = false)
	@Column(name = "ESDIPLOMATICO", nullable = false)
	private char esdiplomatico;
	/**
	 * Field ficha.
	 */
	@Column(name = "FICHA")
	private BigInteger ficha;
	/**
	 * Field ultimasincronizacion.
	 */
	@Basic(optional = false)
	@Column(name = "ULTIMASINCRONIZACION", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar ultimasincronizacion;
	/**
	 * Field esgrande.
	 */
	@Basic(optional = false)
	@Column(name = "ESGRANDE", nullable = false)
	private char esgrande;
	/**
	 * Field estreplica.
	 */
	@Basic(optional = false)
	@Column(name = "ESTREPLICA")
	private char estreplica;
	/**
	 * Field cuentacreditoList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "numeroIdentificacionCliente")
	private List<Cuentacredito> cuentacreditoList;
	/**
	 * Field transaccionList.
	 */
	@OneToMany(mappedBy = "numeroIdentificacionCliente")
	private List<Transaccion> transaccionList;
	/**
	 * Field acreencia.
	 */
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "numeroIdentificacionCliente")
	private Acreencia acreencia;
	/**
	 * Field morosidadList.
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "numeroIdentificacionCliente")
	private List<Morosidad> morosidadList;
	/**
	 * Field transaccionclienteList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "numeroIdentificacionCliente")
	private List<Transaccioncliente> transaccionclienteList;
	/**
	 * Field clientemensajeList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "numeroIdentificacionCliente")
	private List<Clientemensaje> clientemensajeList;
	/**
	 * Field ordendeventaList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "numeroIdentificacionCliente")
	private List<Ordendeventa> ordendeventaList;
	/**
	 * Field idExoneradotipo.
	 */
	@JoinColumn(name = "ID_EXONERADOTIPO", referencedColumnName = "ID")
	@ManyToOne
	private Exoneradotipo idExoneradotipo;
	/**
	 * Field idGiroactividadeconomica.
	 */
	@JoinColumn(name = "ID_GIROACTIVIDADECONOMICA", referencedColumnName = "ID")
	@ManyToOne
	private Giroactividadeconomica idGiroactividadeconomica;
	/**
	 * Field idTipocliente.
	 */
	@JoinColumn(name = "ID_TIPOCLIENTE", referencedColumnName = "ID", nullable = false)
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private Tipocliente idTipocliente;

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
	 * @param esdiplomatico
	 *            char
	 * @param ultimasincronizacion
	 *            Calendar
	 * @param esgrande
	 *            char
	 * @param estreplica
	 *            char
	 */
	public Cliente(String numeroIdentificacionCliente, Date fecha,
			Date fechaActualizacion, String nombre,
			String numeroIdentificacionTributario, char esdiplomatico,
			Calendar ultimasincronizacion, char esgrande, char estreplica) {
		this.numeroIdentificacionCliente = numeroIdentificacionCliente;
		this.fecha = fecha;
		this.fechaActualizacion = fechaActualizacion;
		this.nombre = nombre;
		this.numeroIdentificacionTributario = numeroIdentificacionTributario;
		this.esdiplomatico = esdiplomatico;
		this.ultimasincronizacion = ultimasincronizacion;
		this.esgrande = esgrande;
		this.estreplica = estreplica;
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
	public void setNumeroIdentificacionCliente(
			String numeroIdentificacionCliente) {
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
	public void setNumeroIdentificacionTributario(
			String numeroIdentificacionTributario) {
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
	 * @return Character
	 */
	public Character getEsagenteretencion() {
		return esagenteretencion;
	}

	/**
	 * Method setEsagenteretencion.
	 * 
	 * @param esagenteretencion
	 *            Character
	 */
	public void setEsagenteretencion(Character esagenteretencion) {
		this.esagenteretencion = esagenteretencion;
	}

	/**
	 * Method getEsdiplomatico.
	 * 
	 * @return char
	 */
	public char getEsdiplomatico() {
		return esdiplomatico;
	}

	/**
	 * Method setEsdiplomatico.
	 * 
	 * @param esdiplomatico
	 *            char
	 */
	public void setEsdiplomatico(char esdiplomatico) {
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
	 * Method getEsgrande.
	 * 
	 * @return char
	 */
	public char getEsgrande() {
		return esgrande;
	}

	/**
	 * Method setEsgrande.
	 * 
	 * @param esgrande
	 *            char
	 */
	public void setEsgrande(char esgrande) {
		this.esgrande = esgrande;
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
	 * Method getAcreencia.
	 * 
	 * @return Acreencia
	 */
	public Acreencia getAcreencia() {
		return acreencia;
	}

	/**
	 * Method setAcreencia.
	 * 
	 * @param acreencia
	 *            Acreencia
	 */
	public void setAcreencia(Acreencia acreencia) {
		this.acreencia = acreencia;
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
	 * Method getOrdendeventaList.
	 * 
	 * @return List<Ordendeventa>
	 */
	@XmlTransient
	public List<Ordendeventa> getOrdendeventaList() {
		return ordendeventaList;
	}

	/**
	 * Method setOrdendeventaList.
	 * 
	 * @param ordendeventaList
	 *            List<Ordendeventa>
	 */
	public void setOrdendeventaList(List<Ordendeventa> ordendeventaList) {
		this.ordendeventaList = ordendeventaList;
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
	public void setIdGiroactividadeconomica(
			Giroactividadeconomica idGiroactividadeconomica) {
		this.idGiroactividadeconomica = idGiroactividadeconomica;
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
	 * Method hashCode.
	 * 
	 * @return int
	 */
	@Override
	public int hashCode() {
		int hash = 0;
		hash += (numeroIdentificacionCliente != null ? numeroIdentificacionCliente
				.hashCode() : 0);
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
		return "crjpa400.Cliente[ numeroIdentificacionCliente="
				+ numeroIdentificacionCliente + " ]";
	}

}
