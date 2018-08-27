/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crjpa;

import java.io.Serializable;
import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author eve0014321
 * @version $Revision: 1.0 $
 */
@Entity
@Table(name = "comprobantefiscalnoutilizado")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Comprobantefiscalnoutilizado.findAll", query = "SELECT c FROM Comprobantefiscalnoutilizado c"),
		@NamedQuery(name = "Comprobantefiscalnoutilizado.findById", query = "SELECT c FROM Comprobantefiscalnoutilizado c WHERE c.id = :id"),
		@NamedQuery(name = "Comprobantefiscalnoutilizado.findByNumeroComprobante", query = "SELECT c FROM Comprobantefiscalnoutilizado c WHERE c.numeroComprobante = :numeroComprobante"),
		@NamedQuery(name = "Comprobantefiscalnoutilizado.findByFecha", query = "SELECT c FROM Comprobantefiscalnoutilizado c WHERE c.fecha = :fecha"),
		@NamedQuery(name = "Comprobantefiscalnoutilizado.findByEstasincronizado", query = "SELECT c FROM Comprobantefiscalnoutilizado c WHERE c.estasincronizado = :estasincronizado"),
		@NamedQuery(name = "Comprobantefiscalnoutilizado.findByIdSerialimpresora", query = "SELECT c FROM Comprobantefiscalnoutilizado c WHERE c.idSerialimpresora = :idSerialimpresora"),
		@NamedQuery(name = "Comprobantefiscalnoutilizado.findByIdSesion", query = "SELECT c FROM Comprobantefiscalnoutilizado c WHERE c.idSesion = :idSesion"),
		@NamedQuery(name = "Comprobantefiscalnoutilizado.findByAutorizante", query = "SELECT c FROM Comprobantefiscalnoutilizado c WHERE c.autorizante = :autorizante") })
public class Comprobantefiscalnoutilizado implements Serializable {
	/**
	 * Field serialVersionUID. (value is 1)
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Field id.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "17")
	@TableGenerator(name = "17", table = "correlativo", pkColumnName = "id", valueColumnName = "valor", pkColumnValue = "17", allocationSize = 1)
	@Basic(optional = false)
	@Column(name = "id", nullable = false)
	private Long id;
	/**
	 * Field numeroComprobante.
	 */
	@Basic(optional = false)
	@Column(name = "numero_comprobante", nullable = false)
	private long numeroComprobante;
	/**
	 * Field fecha.
	 */
	@Basic(optional = false)
	@Column(name = "fecha", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	/**
	 * Field estasincronizado.
	 */
	@Column(name = "estasincronizado", length = 2)
	private String estasincronizado;
	/**
	 * Field idSerialimpresora.
	 */
	@Basic(optional = false)
	@Column(name = "id_serialimpresora", nullable = false)
	private long idSerialimpresora;
	/**
	 * Field idSesion.
	 */
	@Basic(optional = false)
	@Column(name = "id_sesion", nullable = false)
	private long idSesion;
	/**
	 * Field autorizante.
	 */
	@Basic(optional = false)
	@Column(name = "autorizante", nullable = false)
	private long autorizante;
	/**
	 * Field idComprobantefiscalpreimpreso.
	 */
	@JoinColumn(name = "id_comprobantefiscalpreimpreso", referencedColumnName = "id", nullable = false)
	@ManyToOne(optional = false)
	private Comprobantefiscalpreimpreso idComprobantefiscalpreimpreso;

	/**
	 * Constructor for Comprobantefiscalnoutilizado.
	 */
	public Comprobantefiscalnoutilizado() {
	}

	/**
	 * Constructor for Comprobantefiscalnoutilizado.
	 * 
	 * @param id
	 *            Long
	 */
	public Comprobantefiscalnoutilizado(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Comprobantefiscalnoutilizado.
	 * 
	 * @param id
	 *            Long
	 * @param numeroComprobante
	 *            long
	 * @param fecha
	 *            Date
	 * @param idSerialimpresora
	 *            long
	 * @param idSesion
	 *            long
	 * @param autorizante
	 *            long
	 */
	public Comprobantefiscalnoutilizado(Long id, long numeroComprobante, Date fecha, long idSerialimpresora,
			long idSesion, long autorizante) {
		this.id = id;
		this.numeroComprobante = numeroComprobante;
		this.fecha = fecha;
		this.idSerialimpresora = idSerialimpresora;
		this.idSesion = idSesion;
		this.autorizante = autorizante;
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
	 * Method getNumeroComprobante.
	 * 
	 * @return long
	 */
	public long getNumeroComprobante() {
		return numeroComprobante;
	}

	/**
	 * Method setNumeroComprobante.
	 * 
	 * @param numeroComprobante
	 *            long
	 */
	public void setNumeroComprobante(long numeroComprobante) {
		this.numeroComprobante = numeroComprobante;
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
	 * Method getIdSerialimpresora.
	 * 
	 * @return long
	 */
	public long getIdSerialimpresora() {
		return idSerialimpresora;
	}

	/**
	 * Method setIdSerialimpresora.
	 * 
	 * @param idSerialimpresora
	 *            long
	 */
	public void setIdSerialimpresora(long idSerialimpresora) {
		this.idSerialimpresora = idSerialimpresora;
	}

	/**
	 * Method getIdSesion.
	 * 
	 * @return long
	 */
	public long getIdSesion() {
		return idSesion;
	}

	/**
	 * Method setIdSesion.
	 * 
	 * @param idSesion
	 *            long
	 */
	public void setIdSesion(long idSesion) {
		this.idSesion = idSesion;
	}

	/**
	 * Method getAutorizante.
	 * 
	 * @return long
	 */
	public long getAutorizante() {
		return autorizante;
	}

	/**
	 * Method setAutorizante.
	 * 
	 * @param autorizante
	 *            long
	 */
	public void setAutorizante(long autorizante) {
		this.autorizante = autorizante;
	}

	/**
	 * Method getIdComprobantefiscalpreimpreso.
	 * 
	 * @return Comprobantefiscalpreimpreso
	 */
	public Comprobantefiscalpreimpreso getIdComprobantefiscalpreimpreso() {
		return idComprobantefiscalpreimpreso;
	}

	/**
	 * Method setIdComprobantefiscalpreimpreso.
	 * 
	 * @param idComprobantefiscalpreimpreso
	 *            Comprobantefiscalpreimpreso
	 */
	public void setIdComprobantefiscalpreimpreso(Comprobantefiscalpreimpreso idComprobantefiscalpreimpreso) {
		this.idComprobantefiscalpreimpreso = idComprobantefiscalpreimpreso;
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
		if (!(object instanceof Comprobantefiscalnoutilizado)) {
			return false;
		}
		Comprobantefiscalnoutilizado other = (Comprobantefiscalnoutilizado) object;
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
		return "crjpa.Comprobantefiscalnoutilizado[ id=" + id + " ]";
	}

}
