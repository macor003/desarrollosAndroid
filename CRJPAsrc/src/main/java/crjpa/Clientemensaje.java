/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crjpa;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author eve0014321
 * @version $Revision: 1.0 $
 */
@Entity
@Table(name = "clientemensaje")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Clientemensaje.findAll", query = "SELECT c FROM Clientemensaje c"),
		@NamedQuery(name = "Clientemensaje.findById", query = "SELECT c FROM Clientemensaje c WHERE c.id = :id"),
		@NamedQuery(name = "Clientemensaje.findByMensaje", query = "SELECT c FROM Clientemensaje c WHERE c.mensaje = :mensaje"),
		@NamedQuery(name = "Clientemensaje.findByEstaactivo", query = "SELECT c FROM Clientemensaje c WHERE c.estaactivo = :estaactivo") })
public class Clientemensaje implements Serializable {
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
	 * Field mensaje.
	 */
	@Basic(optional = false)
	@Column(name = "mensaje")
	private String mensaje;
	/**
	 * Field estaactivo.
	 */
	@Basic(optional = false)
	@Column(name = "estaactivo")
	private String estaactivo;
	/**
	 * Field numeroIdentificacionCliente.
	 */
	@JoinColumn(name = "numero_identificacion_cliente", referencedColumnName = "numero_identificacion_cliente")
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
	 *            String
	 */
	public Clientemensaje(Long id, String mensaje, String estaactivo) {
		this.id = id;
		this.mensaje = mensaje;
		this.estaactivo = estaactivo;
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
	 * @return String
	 */
	public String getEstaactivo() {
		return estaactivo;
	}

	/**
	 * Method setEstaactivo.
	 * 
	 * @param estaactivo
	 *            String
	 */
	public void setEstaactivo(String estaactivo) {
		this.estaactivo = estaactivo;
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
		if (!(object instanceof Clientemensaje)) {
			return false;
		}
		Clientemensaje other = (Clientemensaje) object;
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
		return "crjpa.Clientemensaje[ id=" + id + " ]";
	}

}
