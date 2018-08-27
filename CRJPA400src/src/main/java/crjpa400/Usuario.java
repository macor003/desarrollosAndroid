/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crjpa400;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
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
@Table(name = "USUARIO")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
		@NamedQuery(name = "Usuario.findById", query = "SELECT u FROM Usuario u WHERE u.id = :id"),
		@NamedQuery(name = "Usuario.findByFicha", query = "SELECT u FROM Usuario u WHERE u.ficha = :ficha"),
		@NamedQuery(name = "Usuario.findByNombre", query = "SELECT u FROM Usuario u WHERE u.nombre = :nombre"),
		@NamedQuery(name = "Usuario.findByClave", query = "SELECT u FROM Usuario u WHERE u.clave = :clave"),
		@NamedQuery(name = "Usuario.findByCodigoBarra", query = "SELECT u FROM Usuario u WHERE u.codigoBarra = :codigoBarra"),
		@NamedQuery(name = "Usuario.findByFecha", query = "SELECT u FROM Usuario u WHERE u.fecha = :fecha"),
		@NamedQuery(name = "Usuario.findByFechaCambioclave", query = "SELECT u FROM Usuario u WHERE u.fechaCambioclave = :fechaCambioclave"),
		@NamedQuery(name = "Usuario.findByUltimasincronizacion", query = "SELECT u FROM Usuario u WHERE u.ultimasincronizacion = :ultimasincronizacion"),
		@NamedQuery(name = "Usuario.findByEstreplica", query = "SELECT u FROM Usuario u WHERE u.estreplica = :estreplica") })
public class Usuario extends CrjpaInterface implements Serializable {
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
	 * Field ficha.
	 */
	@Basic(optional = false)
	@Column(name = "FICHA", nullable = false)
	private int ficha;
	/**
	 * Field nombre.
	 */
	@Basic(optional = false)
	@Column(name = "NOMBRE", nullable = false, length = 30)
	private String nombre;
	/**
	 * Field clave.
	 */
	@Basic(optional = false)
	@Column(name = "CLAVE", nullable = false, length = 32)
	private String clave;
	/**
	 * Field codigoBarra.
	 */
	@Column(name = "CODIGO_BARRA", length = 32)
	private String codigoBarra;
	/**
	 * Field fecha.
	 */
	@Basic(optional = false)
	@Column(name = "FECHA", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	/**
	 * Field fechaCambioclave.
	 */
	@Basic(optional = false)
	@Column(name = "FECHA_CAMBIOCLAVE", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaCambioclave;
	/**
	 * Field huella.
	 */
	@Lob
	@Column(name = "HUELLA")
	private Serializable huella;
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
	 * Field rolloauditoriaList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
	private List<Rolloauditoria> rolloauditoriaList;
	/**
	 * Field entregaList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuarioautorizante")
	private List<Entrega> entregaList;
	/**
	 * Field transaccionList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
	private List<Transaccion> transaccionList;
	/**
	 * Field sesionList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
	private List<Sesion> sesionList;
	/**
	 * Field abonoList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
	private List<Abono> abonoList;
	/**
	 * Field usuarioperfilList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
	private List<Usuarioperfil> usuarioperfilList;

	/**
	 * Constructor for Usuario.
	 */
	public Usuario() {
	}

	/**
	 * Constructor for Usuario.
	 * 
	 * @param id
	 *            Long
	 */
	public Usuario(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Usuario.
	 * 
	 * @param id
	 *            Long
	 * @param ficha
	 *            int
	 * @param nombre
	 *            String
	 * @param clave
	 *            String
	 * @param fecha
	 *            Date
	 * @param fechaCambioclave
	 *            Date
	 * @param ultimasincronizacion
	 *            Calendar
	 * @param estreplica
	 *            char
	 */
	public Usuario(Long id, int ficha, String nombre, String clave, Date fecha,
			Date fechaCambioclave, Calendar ultimasincronizacion,
			char estreplica) {
		this.id = id;
		this.ficha = ficha;
		this.nombre = nombre;
		this.clave = clave;
		this.fecha = fecha;
		this.fechaCambioclave = fechaCambioclave;
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
	 * Method getFicha.
	 * 
	 * @return int
	 */
	public int getFicha() {
		return ficha;
	}

	/**
	 * Method setFicha.
	 * 
	 * @param ficha
	 *            int
	 */
	public void setFicha(int ficha) {
		this.ficha = ficha;
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
	 * Method getClave.
	 * 
	 * @return String
	 */
	public String getClave() {
		return clave;
	}

	/**
	 * Method setClave.
	 * 
	 * @param clave
	 *            String
	 */
	public void setClave(String clave) {
		this.clave = clave;
	}

	/**
	 * Method getCodigoBarra.
	 * 
	 * @return String
	 */
	public String getCodigoBarra() {
		return codigoBarra;
	}

	/**
	 * Method setCodigoBarra.
	 * 
	 * @param codigoBarra
	 *            String
	 */
	public void setCodigoBarra(String codigoBarra) {
		this.codigoBarra = codigoBarra;
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
	 * Method getFechaCambioclave.
	 * 
	 * @return Date
	 */
	public Date getFechaCambioclave() {
		return fechaCambioclave;
	}

	/**
	 * Method setFechaCambioclave.
	 * 
	 * @param fechaCambioclave
	 *            Date
	 */
	public void setFechaCambioclave(Date fechaCambioclave) {
		this.fechaCambioclave = fechaCambioclave;
	}

	/**
	 * Method getHuella.
	 * 
	 * @return Serializable
	 */
	public Serializable getHuella() {
		return huella;
	}

	/**
	 * Method setHuella.
	 * 
	 * @param huella
	 *            Serializable
	 */
	public void setHuella(Serializable huella) {
		this.huella = huella;
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
	 * Method getRolloauditoriaList.
	 * 
	 * @return List<Rolloauditoria>
	 */
	@XmlTransient
	public List<Rolloauditoria> getRolloauditoriaList() {
		return rolloauditoriaList;
	}

	/**
	 * Method setRolloauditoriaList.
	 * 
	 * @param rolloauditoriaList
	 *            List<Rolloauditoria>
	 */
	public void setRolloauditoriaList(List<Rolloauditoria> rolloauditoriaList) {
		this.rolloauditoriaList = rolloauditoriaList;
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
	 * @param entregaList
	 *            List<Entrega>
	 */
	public void setEntregaList(List<Entrega> entregaList) {
		this.entregaList = entregaList;
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
	 * Method getSesionList.
	 * 
	 * @return List<Sesion>
	 */
	@XmlTransient
	public List<Sesion> getSesionList() {
		return sesionList;
	}

	/**
	 * Method setSesionList.
	 * 
	 * @param sesionList
	 *            List<Sesion>
	 */
	public void setSesionList(List<Sesion> sesionList) {
		this.sesionList = sesionList;
	}

	/**
	 * Method getAbonoList.
	 * 
	 * @return List<Abono>
	 */
	@XmlTransient
	public List<Abono> getAbonoList() {
		return abonoList;
	}

	/**
	 * Method setAbonoList.
	 * 
	 * @param abonoList
	 *            List<Abono>
	 */
	public void setAbonoList(List<Abono> abonoList) {
		this.abonoList = abonoList;
	}

	/**
	 * Method getUsuarioperfilList.
	 * 
	 * @return List<Usuarioperfil>
	 */
	@XmlTransient
	public List<Usuarioperfil> getUsuarioperfilList() {
		return usuarioperfilList;
	}

	/**
	 * Method setUsuarioperfilList.
	 * 
	 * @param usuarioperfilList
	 *            List<Usuarioperfil>
	 */
	public void setUsuarioperfilList(List<Usuarioperfil> usuarioperfilList) {
		this.usuarioperfilList = usuarioperfilList;
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
		if (!(object instanceof Usuario)) {
			return false;
		}
		Usuario other = (Usuario) object;
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
		return "crjpa400.Usuario[ id=" + id + " ]";
	}

}
