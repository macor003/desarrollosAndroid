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
@Table(name = "TIPOCLIENTETIPODOCUMENTO")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Tipoclientetipodocumento.findAll", query = "SELECT t FROM Tipoclientetipodocumento t"),
		@NamedQuery(name = "Tipoclientetipodocumento.findById", query = "SELECT t FROM Tipoclientetipodocumento t WHERE t.id = :id"),
		@NamedQuery(name = "Tipoclientetipodocumento.findByFecha", query = "SELECT t FROM Tipoclientetipodocumento t WHERE t.fecha = :fecha"),
		@NamedQuery(name = "Tipoclientetipodocumento.findByUltimasincronizacion", query = "SELECT t FROM Tipoclientetipodocumento t WHERE t.ultimasincronizacion = :ultimasincronizacion"),
		@NamedQuery(name = "Tipoclientetipodocumento.findByEstreplica", query = "SELECT t FROM Tipoclientetipodocumento t WHERE t.estreplica = :estreplica") })
public class Tipoclientetipodocumento extends CrjpaInterface implements
		Serializable {
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
	 * Field idTipocliente.
	 */
	@JoinColumn(name = "ID_TIPOCLIENTE", referencedColumnName = "ID", nullable = false)
	@ManyToOne(optional = false)
	private Tipocliente idTipocliente;
	/**
	 * Field idTipodocumento.
	 */
	@JoinColumn(name = "ID_TIPODOCUMENTO", referencedColumnName = "ID", nullable = false)
	@ManyToOne(optional = false)
	private Tipodocumento idTipodocumento;

	/**
	 * Constructor for Tipoclientetipodocumento.
	 */
	public Tipoclientetipodocumento() {
	}

	/**
	 * Constructor for Tipoclientetipodocumento.
	 * 
	 * @param id
	 *            Long
	 */
	public Tipoclientetipodocumento(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Tipoclientetipodocumento.
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
	public Tipoclientetipodocumento(Long id, Date fecha,
			Calendar ultimasincronizacion, char estreplica) {
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
	 * Method getIdTipodocumento.
	 * 
	 * @return Tipodocumento
	 */
	public Tipodocumento getIdTipodocumento() {
		return idTipodocumento;
	}

	/**
	 * Method setIdTipodocumento.
	 * 
	 * @param idTipodocumento
	 *            Tipodocumento
	 */
	public void setIdTipodocumento(Tipodocumento idTipodocumento) {
		this.idTipodocumento = idTipodocumento;
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
		if (!(object instanceof Tipoclientetipodocumento)) {
			return false;
		}
		Tipoclientetipodocumento other = (Tipoclientetipodocumento) object;
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
		return "crjpa400.Tipoclientetipodocumento[ id=" + id + " ]";
	}

}
