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
@Table(name = "TIPOIDENTIFICACIONCLIENTE")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Tipoidentificacioncliente.findAll", query = "SELECT t FROM Tipoidentificacioncliente t"),
		@NamedQuery(name = "Tipoidentificacioncliente.findById", query = "SELECT t FROM Tipoidentificacioncliente t WHERE t.id = :id"),
		@NamedQuery(name = "Tipoidentificacioncliente.findByFecha", query = "SELECT t FROM Tipoidentificacioncliente t WHERE t.fecha = :fecha"),
		@NamedQuery(name = "Tipoidentificacioncliente.findByUltimasincronizacion", query = "SELECT t FROM Tipoidentificacioncliente t WHERE t.ultimasincronizacion = :ultimasincronizacion"),
		@NamedQuery(name = "Tipoidentificacioncliente.findByEstreplica", query = "SELECT t FROM Tipoidentificacioncliente t WHERE t.estreplica = :estreplica"),
		@NamedQuery(name = "Tipoidentificacioncliente.findByMascara", query = "SELECT t FROM Tipoidentificacioncliente t WHERE t.mascara = :mascara") })
public class Tipoidentificacioncliente extends CrjpaInterface implements
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
	 * Field mascara.
	 */
	@Basic(optional = false)
	@Column(name = "MASCARA")
	private String mascara;
	/**
	 * Field idTipocliente.
	 */
	@JoinColumn(name = "ID_TIPOCLIENTE", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	private Tipocliente idTipocliente;

	/**
	 * Constructor for Tipoidentificacioncliente.
	 */
	public Tipoidentificacioncliente() {
	}

	/**
	 * Constructor for Tipoidentificacioncliente.
	 * 
	 * @param id
	 *            Long
	 */
	public Tipoidentificacioncliente(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Tipoidentificacioncliente.
	 * 
	 * @param id
	 *            Long
	 * @param fecha
	 *            Date
	 * @param ultimasincronizacion
	 *            Calendar
	 * @param estreplica
	 *            char
	 * @param mascara
	 *            String
	 */
	public Tipoidentificacioncliente(Long id, Date fecha,
			Calendar ultimasincronizacion, char estreplica, String mascara) {
		this.id = id;
		this.fecha = fecha;
		this.ultimasincronizacion = ultimasincronizacion;
		this.estreplica = estreplica;
		this.mascara = mascara;
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
	 * Method getMascara.
	 * 
	 * @return String
	 */
	public String getMascara() {
		return mascara;
	}

	/**
	 * Method setMascara.
	 * 
	 * @param mascara
	 *            String
	 */
	public void setMascara(String mascara) {
		this.mascara = mascara;
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
		if (!(object instanceof Tipoidentificacioncliente)) {
			return false;
		}
		Tipoidentificacioncliente other = (Tipoidentificacioncliente) object;
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
		return "crjpa400.Tipoidentificacioncliente[ id=" + id + " ]";
	}

}
