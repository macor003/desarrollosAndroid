/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package crjpa;

import java.io.Serializable;
import java.text.SimpleDateFormat;
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

/**
 * @version $Revision: 1.0 $
 */
@Entity
@Table(name = "categorialinearetencion")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Categorialinearetencion.findAll", query = "SELECT c FROM Categorialinearetencion c"),
		@NamedQuery(name = "Categorialinearetencion.findById", query = "SELECT c FROM Categorialinearetencion c WHERE c.id = :id"),
		@NamedQuery(name = "Categorialinearetencion.findByFecha", query = "SELECT c FROM Categorialinearetencion c WHERE c.fecha = :fecha") })
public class Categorialinearetencion implements Serializable {
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
	 * Field fecha.
	 */
	@Basic(optional = false)
	@Column(name = "fecha")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	/**
	 * Field idLinea.
	 */
	@JoinColumn(name = "id_linea", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Linea idLinea;
	/**
	 * Field idPorcentajeimpuestoretencion.
	 */
	@JoinColumn(name = "id_porcentajeimpuestoretencion", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Porcentajeimpuestoretencion idPorcentajeimpuestoretencion;
	

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
	 */
	public Categorialinearetencion(Long id, Date fecha) {
		this.id = id;
		this.fecha = fecha;
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
	 * Method getIdPorcentajeimpuestoretencion.
	 * 
	 * @return Porcentajeimpuestoretencion
	 */
	public Porcentajeimpuestoretencion getIdPorcentajeimpuestoretencion() {
		return idPorcentajeimpuestoretencion;
	}

	/**
	 * Method setIdPorcentajeimpuestoretencion.
	 * 
	 * @param idPorcentajeimpuestoretencion Porcentajeimpuestoretencion
	 */
	public void setIdPorcentajeimpuestoretencion(Porcentajeimpuestoretencion idPorcentajeimpuestoretencion) {
		this.idPorcentajeimpuestoretencion = idPorcentajeimpuestoretencion;
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
		String separator = "@@";
		String nullStr = "\\N";
		String endStr = "\r\n";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		StringBuilder sb = new StringBuilder();

		sb.append(id);
		sb.append(separator);

		sb.append(idLinea.getId());
		sb.append(separator);

		sb.append(idPorcentajeimpuestoretencion.getId());
		sb.append(separator);

		sb.append(fecha == null ? nullStr : simpleDateFormat.format(fecha));
		sb.append(endStr);

		return sb.toString();
	}

}
