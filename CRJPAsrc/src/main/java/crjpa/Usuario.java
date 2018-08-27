/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crjpa;

import java.io.Serializable;
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

/**
 * 
 * @author eve0014321
 * @version $Revision: 1.0 $
 */
@Entity
@Table(name = "usuario")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
		@NamedQuery(name = "Usuario.findById", query = "SELECT u FROM Usuario u WHERE u.id = :id"),
		@NamedQuery(name = "Usuario.findByFicha", query = "SELECT u FROM Usuario u WHERE u.ficha = :ficha"),
		@NamedQuery(name = "Usuario.findByNombre", query = "SELECT u FROM Usuario u WHERE u.nombre = :nombre"),
		@NamedQuery(name = "Usuario.findByClave", query = "SELECT u FROM Usuario u WHERE u.clave = :clave"),
		@NamedQuery(name = "Usuario.findByCodigoBarra", query = "SELECT u FROM Usuario u WHERE u.codigoBarra = :codigoBarra"),
		@NamedQuery(name = "Usuario.findByFecha", query = "SELECT u FROM Usuario u WHERE u.fecha = :fecha"),
		@NamedQuery(name = "Usuario.findByFechaCambioClave", query = "SELECT u FROM Usuario u WHERE u.fechaCambioClave = :fechaCambioClave"),
		@NamedQuery(name = "Usuario.findByEstasincronizado", query = "SELECT u FROM Usuario u WHERE u.estasincronizado = :estasincronizado") })
public class Usuario implements Serializable {
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
	 * Field ficha.
	 */
	@Basic(optional = false)
	@Column(name = "ficha")
	private int ficha;
	/**
	 * Field nombre.
	 */
	@Basic(optional = false)
	@Column(name = "nombre")
	private String nombre;
	/**
	 * Field clave.
	 */
	@Basic(optional = false)
	@Column(name = "clave")
	private String clave;
	/**
	 * Field codigoBarra.
	 */
	@Column(name = "codigo_barra")
	private String codigoBarra;
	/**
	 * Field fecha.
	 */
	@Basic(optional = false)
	@Column(name = "fecha")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	/**
	 * Field fechaCambioClave.
	 */
	@Basic(optional = false)
	@Column(name = "fecha_cambio_clave")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaCambioClave;
	/**
	 * Field huella.
	 */
	@Lob
	@Column(name = "huella")
	private byte[] huella;
	/**
	 * Field estasincronizado.
	 */
	@Basic(optional = false)
	@Column(name = "estasincronizado")
	private String estasincronizado;
	/**
	 * Field entregaList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuarioautorizante")
	private List<Entrega> entregaList;
	/**
	 * Field sesionList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
	private List<Sesion> sesionList;
	/**
	 * Field transaccionList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
	private List<Transaccion> transaccionList;
	/**
	 * Field usuarioperfilList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
	private List<Usuarioperfil> usuarioperfilList;
	/**
	 * Field rolloauditoriaList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
	private List<Rolloauditoria> rolloauditoriaList;

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
	 * @param fechaCambioClave
	 *            Date
	 * @param estasincronizado
	 *            String
	 */
	public Usuario(Long id, int ficha, String nombre, String clave, Date fecha, Date fechaCambioClave,
			String estasincronizado) {
		this.id = id;
		this.ficha = ficha;
		this.nombre = nombre;
		this.clave = clave;
		this.fecha = fecha;
		this.fechaCambioClave = fechaCambioClave;
		this.estasincronizado = estasincronizado;
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
	 * Method getFechaCambioClave.
	 * 
	 * @return Date
	 */
	public Date getFechaCambioClave() {
		return fechaCambioClave;
	}

	/**
	 * Method setFechaCambioClave.
	 * 
	 * @param fechaCambioClave
	 *            Date
	 */
	public void setFechaCambioClave(Date fechaCambioClave) {
		this.fechaCambioClave = fechaCambioClave;
	}

	/**
	 * Method getHuella.
	 * 
	 * @return byte[]
	 */
	public byte[] getHuella() {
		return huella;
	}

	/**
	 * Method setHuella.
	 * 
	 * @param huella
	 *            byte[]
	 */
	public void setHuella(byte[] huella) {
		this.huella = huella;
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
		return "crjpa.Usuario[ id=" + id + " ]";
	}

}
