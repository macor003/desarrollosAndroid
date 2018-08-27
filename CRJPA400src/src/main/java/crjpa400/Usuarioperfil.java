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
@Table(name = "USUARIOPERFIL")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Usuarioperfil.findAll", query = "SELECT u FROM Usuarioperfil u"),
		@NamedQuery(name = "Usuarioperfil.findById", query = "SELECT u FROM Usuarioperfil u WHERE u.id = :id"),
		@NamedQuery(name = "Usuarioperfil.findByFecha", query = "SELECT u FROM Usuarioperfil u WHERE u.fecha = :fecha"),
		@NamedQuery(name = "Usuarioperfil.findByUltimasincronizacion", query = "SELECT u FROM Usuarioperfil u WHERE u.ultimasincronizacion = :ultimasincronizacion"),
		@NamedQuery(name = "Usuarioperfil.findByEstreplica", query = "SELECT u FROM Usuarioperfil u WHERE u.estreplica = :estreplica") })
public class Usuarioperfil extends CrjpaInterface implements Serializable {
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
	 * Field fecha.
	 */
	@Basic(optional = false)
	@Column(name = "FECHA", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
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
	 * Field idUsuario.
	 */
	@JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID", nullable = false)
	@ManyToOne(optional = false)
	private Usuario idUsuario;
	/**
	 * Field idPerfil.
	 */
	@JoinColumn(name = "ID_PERFIL", referencedColumnName = "ID", nullable = false)
	@ManyToOne(optional = false)
	private Perfil idPerfil;

	/**
	 * Constructor for Usuarioperfil.
	 */
	public Usuarioperfil() {
	}

	/**
	 * Constructor for Usuarioperfil.
	 * 
	 * @param id
	 *            Long
	 */
	public Usuarioperfil(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Usuarioperfil.
	 * 
	 * @param id
	 *            Long
	 * @param fecha
	 *            Date
	 * @param ultimasincronizacion
	 *            Calendar
	 * @param estreplica
	 *            char
	 */
	public Usuarioperfil(Long id, Date fecha, Calendar ultimasincronizacion,
			char estreplica) {
		this.id = id;
		this.fecha = fecha;
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
	 * Method getIdUsuario.
	 * 
	 * @return Usuario
	 */
	public Usuario getIdUsuario() {
		return idUsuario;
	}

	/**
	 * Method setIdUsuario.
	 * 
	 * @param idUsuario
	 *            Usuario
	 */
	public void setIdUsuario(Usuario idUsuario) {
		this.idUsuario = idUsuario;
	}

	/**
	 * Method getIdPerfil.
	 * 
	 * @return Perfil
	 */
	public Perfil getIdPerfil() {
		return idPerfil;
	}

	/**
	 * Method setIdPerfil.
	 * 
	 * @param idPerfil
	 *            Perfil
	 */
	public void setIdPerfil(Perfil idPerfil) {
		this.idPerfil = idPerfil;
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
		if (!(object instanceof Usuarioperfil)) {
			return false;
		}
		Usuarioperfil other = (Usuarioperfil) object;
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
		return "crjpa400.Usuarioperfil[ id=" + id + " ]";
	}

}
