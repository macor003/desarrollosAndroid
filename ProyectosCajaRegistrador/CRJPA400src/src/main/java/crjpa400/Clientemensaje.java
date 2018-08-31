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

import crjpa400.interfaces.CrjpaInterface;

/**
 * 
 * @author eve0014321
 * @version $Revision: 1.0 $
 */
@Entity
@Table(name = "CLIENTEMENSAJE")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Clientemensaje.findAll", query = "SELECT c FROM Clientemensaje c"),
		@NamedQuery(name = "Clientemensaje.findById", query = "SELECT c FROM Clientemensaje c WHERE c.id = :id"),
		@NamedQuery(name = "Clientemensaje.findByMensaje", query = "SELECT c FROM Clientemensaje c WHERE c.mensaje = :mensaje"),
		@NamedQuery(name = "Clientemensaje.findByEstaactivo", query = "SELECT c FROM Clientemensaje c WHERE c.estaactivo = :estaactivo"),
		@NamedQuery(name = "Clientemensaje.findByUltimasincronizacion", query = "SELECT c FROM Clientemensaje c WHERE c.ultimasincronizacion = :ultimasincronizacion"),
		@NamedQuery(name = "Clientemensaje.findByEstreplica", query = "SELECT c FROM Clientemensaje c WHERE c.estreplica = :estreplica") })
public class Clientemensaje extends CrjpaInterface implements Serializable {
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
	 * Field mensaje.
	 */
	@Basic(optional = false)
	@Column(name = "MENSAJE", nullable = false, length = 255)
	private String mensaje;
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
	 * Field numeroIdentificacionCliente.
	 */
	@JoinColumn(name = "NUMERO_IDENTIFICACION_CLIENTE", referencedColumnName = "NUMERO_IDENTIFICACION_CLIENTE", nullable = false)
	@ManyToOne(optional = false)
	private Cliente numeroIdentificacionCliente;

	/**
	 * Constructor for Clientemensaje.
	 */
	public Clientemensaje() {
	}

	/**
	 * Constructor for Clientemensaje.
	 * 
	 * @param id
	 *            Long
	 */
	public Clientemensaje(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Clientemensaje.
	 * 
	 * @param id
	 *            Long
	 * @param mensaje
	 *            String
	 * @param estaactivo
	 *            char
	 * @param ultimasincronizacion
	 *            Calendar
	 * @param estreplica
	 *            char
	 */
	public Clientemensaje(Long id, String mensaje, char estaactivo,
			Calendar ultimasincronizacion, char estreplica) {
		this.id = id;
		this.mensaje = mensaje;
		this.estaactivo = estaactivo;
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
	 * Method getMensaje.
	 * 
	 * @return String
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * Method setMensaje.
	 * 
	 * @param mensaje
	 *            String
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
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
		if (!(object instanceof Clientemensaje)) {
			return false;
		}
		Clientemensaje other = (Clientemensaje) object;
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
		return "crjpa400.Clientemensaje[ id=" + id + " ]";
	}

}
