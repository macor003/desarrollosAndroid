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
 * @author eve0011737
 * @version $Revision: 1.0 $
 */
@Entity
@Table(name = "CONDICIONENTREGA")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Condicionentrega.findAll", query = "SELECT c FROM Condicionentrega c"),
		@NamedQuery(name = "Condicionentrega.findById", query = "SELECT c FROM Condicionentrega c WHERE c.id = :id"),
		@NamedQuery(name = "Condicionentrega.findByTipo", query = "SELECT c FROM Condicionentrega c WHERE c.tipo = :tipo"),
		@NamedQuery(name = "Condicionentrega.findByDescripcion", query = "SELECT c FROM Condicionentrega c WHERE c.descripcion = :descripcion"),
		@NamedQuery(name = "Condicionentrega.findByRequiereidclienterecibe", query = "SELECT c FROM Condicionentrega c WHERE c.requiereIdClienteRecibe = :requiereIdClienteRecibe"),
		@NamedQuery(name = "Condicionentrega.findByRequieretelefono", query = "SELECT c FROM Condicionentrega c WHERE c.requiereTelefono = :requireTelefono"),
		@NamedQuery(name = "Condicionentrega.findByRequieredireccion", query = "SELECT c FROM Condicionentrega c WHERE c.requiereDireccion = :requireDireccion"),
		@NamedQuery(name = "Condicionentrega.findByUltimasincronizacion", query = "SELECT c FROM Condicionentrega c WHERE c.ultimasincronizacion = :ultimasincronizacion"),
		@NamedQuery(name = "Condicionentrega.findByEstreplica", query = "SELECT c FROM Condicionentrega c WHERE c.estreplica = :estreplica"),
		@NamedQuery(name = "Condicionentrega.findByTipodocumento", query = "SELECT c FROM Condicionentrega c WHERE c.tipodocumento = :tipodocumento") })
public class Condicionentrega extends CrjpaInterface implements Serializable {
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
	 * Field tipo.
	 */
	@Basic(optional = false)
	@Column(name = "TIPO", nullable = false)
	private char tipo;
	/**
	 * Field descripcion.
	 */
	@Basic(optional = false)
	@Column(name = "DESCRIPCION", nullable = false, length = 40)
	private String descripcion;
	/**
	 * Field requiereIdClienteRecibe.
	 */
	@Basic(optional = false)
	@Column(name = "REQUIEREIDCLIENTERECIBE", nullable = false)
	private char requiereIdClienteRecibe;
	/**
	 * Field requiereTelefono.
	 */
	@Basic(optional = false)
	@Column(name = "REQUIERETELEFONO", nullable = false)
	private char requiereTelefono;
	/**
	 * Field requiereDireccion.
	 */
	@Basic(optional = false)
	@Column(name = "REQUIEREDIRECCION", nullable = false)
	private char requiereDireccion;
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
	 * Field tipodocumento.
	 */
	@JoinColumn(name = "ID_TIPODOCUMENTO", referencedColumnName = "ID")
	@ManyToOne
	private Tipodocumento tipodocumento;

	/**
	 * Constructor for Condicionentrega.
	 */
	public Condicionentrega() {
	}

	/**
	 * Constructor for Condicionentrega.
	 * 
	 * @param id
	 *            Long
	 */
	public Condicionentrega(Long id) {
		this.id = id;
	}

	/**
	 * @param id
	 * @param descripcion
	 * @param requiereIdCliente
	 * @param requiereTelefono
	 * @param requiereDireccion
	 * @param ultimasincronizacion
	 * @param estreplica
	 */
	public Condicionentrega(Long id, String descripcion,
			char requiereIdCliente, char requiereTelefono,
			char requiereDireccion, Calendar ultimasincronizacion,
			char estreplica) {
		this.id = id;
		this.descripcion = descripcion;
		this.requiereIdClienteRecibe = requiereIdCliente;
		this.requiereTelefono = requiereTelefono;
		this.requiereDireccion = requiereDireccion;
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
	 * Method getTipo.
	 * 
	 * @return char
	 */
	public char getTipo() {
		return tipo;
	}

	/**
	 * Method setTipo.
	 * 
	 * @param tipo
	 *            char
	 */
	public void setTipo(char tipo) {
		this.tipo = tipo;
	}

	/**
	 * Method getDescripcion.
	 * 
	 * @return String
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Method setDescripcion.
	 * 
	 * @param descripcion
	 *            String
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Method getRequiereIdClienteRecibe.
	 * 
	 * @return char
	 */
	public char getRequiereIdClienteRecibe() {
		return requiereIdClienteRecibe;
	}

	/**
	 * Method setRequiereIdClienteRecibe.
	 * 
	 * @param requiereIdCliente
	 *            char
	 */
	public void setRequiereIdClienteRecibe(char requiereIdCliente) {
		this.requiereIdClienteRecibe = requiereIdCliente;
	}

	/**
	 * Method getRequiereTelefono.
	 * 
	 * @return char
	 */
	public char getRequiereTelefono() {
		return requiereTelefono;
	}

	/**
	 * Method setRequiereTelefono.
	 * 
	 * @param requiereTelefono
	 *            char
	 */
	public void setRequiereTelefono(char requiereTelefono) {
		this.requiereTelefono = requiereTelefono;
	}

	/**
	 * Method getRequiereDireccion.
	 * 
	 * @return char
	 */
	public char getRequiereDireccion() {
		return requiereDireccion;
	}

	/**
	 * Method setRequiereDireccion.
	 * 
	 * @param requiereDireccion
	 *            char
	 */
	public void setRequiereDireccion(char requiereDireccion) {
		this.requiereDireccion = requiereDireccion;
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
	 * Method getTipodocumento.
	 * 
	 * @return Tipodocumento
	 */
	public Tipodocumento getTipodocumento() {
		return tipodocumento;
	}

	/**
	 * Method setTipodocumento.
	 * 
	 * @param tipodocumento
	 *            Tipodocumento
	 */
	public void setTipodocumento(Tipodocumento tipodocumento) {
		this.tipodocumento = tipodocumento;
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
		if (!(object instanceof Condicionentrega)) {
			return false;
		}
		Condicionentrega other = (Condicionentrega) object;
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
		return "crjpa400.Condicionentrega[ id=" + id + " ]";
	}

}
