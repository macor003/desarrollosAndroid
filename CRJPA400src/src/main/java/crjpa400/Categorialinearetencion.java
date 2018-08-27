/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
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
 * @version $Revision: 1.0 $
 */
@Entity
@Table(name = "CATEGORIALINEARETENCION")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Categorialinearetencion.findAll", query = "SELECT c FROM Categorialinearetencion c"),
		@NamedQuery(name = "Categorialinearetencion.findById", query = "SELECT c FROM Categorialinearetencion c WHERE c.id = :id"),
		@NamedQuery(name = "Categorialinearetencion.findByFecha", query = "SELECT c FROM Categorialinearetencion c WHERE c.fecha = :fecha"),
		@NamedQuery(name = "Categorialinearetencion.findByUltimasincronizacion", query = "SELECT c FROM Categorialinearetencion c WHERE c.ultimasincronizacion = :ultimasincronizacion"),
		@NamedQuery(name = "Categorialinearetencion.findByEstreplica", query = "SELECT c FROM Categorialinearetencion c WHERE c.estreplica = :estreplica") })
public class Categorialinearetencion extends CrjpaInterface implements
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
	 * Field idPorcentajeimpuestoretencion.
	 */
	@JoinColumn(name = "ID_PORCENTAJEIMPUESTORETENCION", referencedColumnName = "ID", nullable = false)
	@ManyToOne(optional = false)
	private Porcentajeimpuestoretencion idPorcentajeimpuestoretencion;
	/**
	 * Field idLinea.
	 */
	@JoinColumn(name = "ID_LINEA", referencedColumnName = "ID", nullable = false)
	@ManyToOne(optional = false)
	private Linea idLinea;

	/**
	 * Constructor for Categorialinearetencion.
	 */
	public Categorialinearetencion() {
	}

	/**
	 * Constructor for Categorialinearetencion.
	 * 
	 * @param id Long
	 */
	public Categorialinearetencion(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Categorialinearetencion.
	 * 
	 * @param id Long
	 * @param fecha Date
	 * @param ultimasincronizacion Calendar
	 * @param estreplica char
	 */
	public Categorialinearetencion(Long id, Date fecha,
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
	 * @param id Long
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
	 * @param fecha Date
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
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
	 * @param ultimasincronizacion Calendar
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
	 * @param estreplica char
	 */
	public void setEstreplica(char estreplica) {
		this.estreplica = estreplica;
	}

	/**
	 * Method getIdPorcentajeimpuestoretencion.
	 * 
	 * @return Formadepago
	 */
	public Porcentajeimpuestoretencion getIdPorcentajeimpuestoretencion() {
		return idPorcentajeimpuestoretencion;
	}

	/**
	 * Method setIdPorcentajeimpuestoretencion.
	 * 
	 * @param idFormadepago Formadepago
	 */
	public void setIdPorcentajeimpuestoretencion(Porcentajeimpuestoretencion idPorcentajeimpuestoretencion) {
		this.idPorcentajeimpuestoretencion = idPorcentajeimpuestoretencion;
	}

	/**
	 * Method getIdLinea.
	 * 
	 * @return Linea
	 */
	public Linea getIdLinea() {
		return idLinea;
	}

	/**
	 * Method setIdLinea.
	 * 
	 * @param idLinea Linea
	 */
	public void setIdLinea(Linea idLinea) {
		this.idLinea = idLinea;
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
	 * @param object Object
	 * @return boolean
	 */
	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Categorialinearetencion)) {
			return false;
		}
		Categorialinearetencion other = (Categorialinearetencion) object;
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
		return "crjpa400.Categorialinearetencion[ id=" + id + " ]";
	}

}