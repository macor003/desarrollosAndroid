/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crjpa;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author eve0014321
 * @version $Revision: 1.0 $
 */
@Entity
@Table(name = "transaccioncliente")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Transaccioncliente.findAll", query = "SELECT t FROM Transaccioncliente t"),
		@NamedQuery(name = "Transaccioncliente.findById", query = "SELECT t FROM Transaccioncliente t WHERE t.id = :id"),
		@NamedQuery(name = "Transaccioncliente.findByNombre", query = "SELECT t FROM Transaccioncliente t WHERE t.nombre = :nombre"),
		@NamedQuery(name = "Transaccioncliente.findByDireccion", query = "SELECT t FROM Transaccioncliente t WHERE t.direccion = :direccion"),
		@NamedQuery(name = "Transaccioncliente.findByDireccionFiscal", query = "SELECT t FROM Transaccioncliente t WHERE t.direccionFiscal = :direccionFiscal"),
		@NamedQuery(name = "Transaccioncliente.findByTelefono", query = "SELECT t FROM Transaccioncliente t WHERE t.telefono = :telefono"),
		@NamedQuery(name = "Transaccioncliente.findByEstasincronizado", query = "SELECT t FROM Transaccioncliente t WHERE t.estasincronizado = :estasincronizado") })
public class Transaccioncliente implements Serializable {
	/**
	 * Field serialVersionUID. (value is 1)
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Field id.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "16")
	@TableGenerator(name = "16", table = "correlativo", pkColumnName = "id", valueColumnName = "valor", pkColumnValue = "16", allocationSize = 1)
	@Basic(optional = false)
	@Column(name = "id", nullable = false)
	private Long id;
	/**
	 * Field nombre.
	 */
	@Basic(optional = false)
	@Column(name = "nombre", nullable = false, length = 255)
	private String nombre;
	/**
	 * Field direccion.
	 */
	@Basic(optional = false)
	@Column(name = "direccion", nullable = false, length = 255)
	private String direccion;
	/**
	 * Field direccionFiscal.
	 */
	@Basic(optional = false)
	@Column(name = "direccion_fiscal", nullable = false, length = 255)
	private String direccionFiscal;
	/**
	 * Field telefono.
	 */
	@Basic(optional = false)
	@Column(name = "telefono", nullable = false, length = 15)
	private String telefono;
	/**
	 * Field estasincronizado.
	 */
	@Basic(optional = false)
	@Column(name = "estasincronizado", nullable = false, length = 2)
	private String estasincronizado;
	/**
	 * Field numeroIdentificacionCliente.
	 */
	@JoinColumn(name = "numero_identificacion_cliente", referencedColumnName = "numero_identificacion_cliente", nullable = false)
	@ManyToOne(optional = false)
	private Cliente numeroIdentificacionCliente;
	/**
	 * Field idGiroactividadeconomica.
	 */
	@JoinColumn(name = "id_giroactividadeconomica", referencedColumnName = "id")
	@ManyToOne
	private Giroactividadeconomica idGiroactividadeconomica;
	/**
	 * Field idTransaccion.
	 */
	@JoinColumn(name = "id_transaccion", referencedColumnName = "id", nullable = false)
	@ManyToOne(optional = false)
	private Transaccion idTransaccion;

	/**
	 * Constructor for Transaccioncliente.
	 */
	public Transaccioncliente() {
	}

	/**
	 * Constructor for Transaccioncliente.
	 * 
	 * @param id
	 *            Long
	 */
	public Transaccioncliente(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Transaccioncliente.
	 * 
	 * @param id
	 *            Long
	 * @param nombre
	 *            String
	 * @param direccion
	 *            String
	 * @param direccionFiscal
	 *            String
	 * @param telefono
	 *            String
	 * @param estasincronizado
	 *            String
	 */
	public Transaccioncliente(Long id, String nombre, String direccion, String direccionFiscal, String telefono,
			String estasincronizado) {
		this.id = id;
		this.nombre = nombre;
		this.direccion = direccion;
		this.direccionFiscal = direccionFiscal;
		this.telefono = telefono;
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
	 * Method getIdTransaccion.
	 * 
	 * @return Transaccion
	 */
	public Transaccion getIdTransaccion() {
		return idTransaccion;
	}

	/**
	 * Method setIdTransaccion.
	 * 
	 * @param idTransaccion
	 *            Transaccion
	 */
	public void setIdTransaccion(Transaccion idTransaccion) {
		this.idTransaccion = idTransaccion;
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
		if (!(object instanceof Transaccioncliente)) {
			return false;
		}
		Transaccioncliente other = (Transaccioncliente) object;
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
		return "crjpa.Transaccioncliente[ id=" + id + " ]";
	}

}
