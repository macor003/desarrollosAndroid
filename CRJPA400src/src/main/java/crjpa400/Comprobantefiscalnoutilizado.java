/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crjpa400;

import java.io.Serializable;
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
 * 
 * @author eve0014321
 * @version $Revision: 1.0 $
 */
@Entity
@Table(name = "COMPROBANTEFISCALNOUTILIZADO")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Comprobantefiscalnoutilizado.findAll", query = "SELECT c FROM Comprobantefiscalnoutilizado c"),
		@NamedQuery(name = "Comprobantefiscalnoutilizado.findById", query = "SELECT c FROM Comprobantefiscalnoutilizado c WHERE c.id = :id"),
		@NamedQuery(name = "Comprobantefiscalnoutilizado.findByNumeroComprobante", query = "SELECT c FROM Comprobantefiscalnoutilizado c WHERE c.numeroComprobante = :numeroComprobante"),
		@NamedQuery(name = "Comprobantefiscalnoutilizado.findByFecha", query = "SELECT c FROM Comprobantefiscalnoutilizado c WHERE c.fecha = :fecha"),
		@NamedQuery(name = "Comprobantefiscalnoutilizado.findByIdSerialimpresora", query = "SELECT c FROM Comprobantefiscalnoutilizado c WHERE c.idSerialimpresora = :idSerialimpresora"),
		@NamedQuery(name = "Comprobantefiscalnoutilizado.findByIdSesion", query = "SELECT c FROM Comprobantefiscalnoutilizado c WHERE c.idSesion = :idSesion"),
		@NamedQuery(name = "Comprobantefiscalnoutilizado.findByAutorizante", query = "SELECT c FROM Comprobantefiscalnoutilizado c WHERE c.autorizante = :autorizante"),
		@NamedQuery(name = "Comprobantefiscalnoutilizado.findByEstasincronizado", query = "SELECT c FROM Comprobantefiscalnoutilizado c WHERE c.estasincronizado = :estasincronizado"),
		@NamedQuery(name = "Comprobantefiscalnoutilizado.findByEstreplica", query = "SELECT c FROM Comprobantefiscalnoutilizado c WHERE c.estreplica = :estreplica") })
public class Comprobantefiscalnoutilizado implements Serializable {
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
	 * Field numeroComprobante.
	 */
	@Basic(optional = false)
	@Column(name = "NUMERO_COMPROBANTE", nullable = false)
	private long numeroComprobante;
	/**
	 * Field fecha.
	 */
	@Basic(optional = false)
	@Column(name = "FECHA", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	/**
	 * Field idSerialimpresora.
	 */
	@Basic(optional = false)
	@Column(name = "ID_SERIALIMPRESORA", nullable = false)
	private long idSerialimpresora;
	/**
	 * Field idSesion.
	 */
	@Basic(optional = false)
	@Column(name = "ID_SESION", nullable = false)
	private long idSesion;
	/**
	 * Field autorizante.
	 */
	@Basic(optional = false)
	@Column(name = "AUTORIZANTE", nullable = false)
	private long autorizante;
	/**
	 * Field estasincronizado.
	 */
	@Basic(optional = false)
	@Column(name = "ESTASINCRONIZADO")
	private char estasincronizado;
	/**
	 * Field estreplica.
	 */
	@Basic(optional = false)
	@Column(name = "ESTREPLICA")
	private char estreplica;
	/**
	 * Field idComprobantefiscalpreimpreso.
	 */
	@JoinColumn(name = "ID_COMPROBANTEFISCALPREIMPRESO", referencedColumnName = "ID", nullable = false)
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
	 * @param estasincronizado
	 *            char
	 * @param estreplica
	 *            char
	 */
	public Comprobantefiscalnoutilizado(Long id, long numeroComprobante,
			Date fecha, long idSerialimpresora, long idSesion,
			long autorizante, char estasincronizado, char estreplica) {
		this.id = id;
		this.numeroComprobante = numeroComprobante;
		this.fecha = fecha;
		this.idSerialimpresora = idSerialimpresora;
		this.idSesion = idSesion;
		this.autorizante = autorizante;
		this.estasincronizado = estasincronizado;
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
	 * Method getEstasincronizado.
	 * 
	 * @return char
	 */
	public char getEstasincronizado() {
		return estasincronizado;
	}

	/**
	 * Method setEstasincronizado.
	 * 
	 * @param estasincronizado
	 *            char
	 */
	public void setEstasincronizado(char estasincronizado) {
		this.estasincronizado = estasincronizado;
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
	public void setIdComprobantefiscalpreimpreso(
			Comprobantefiscalpreimpreso idComprobantefiscalpreimpreso) {
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
		return "crjpa400.Comprobantefiscalnoutilizado[ id=" + id + " ]";
	}

}
