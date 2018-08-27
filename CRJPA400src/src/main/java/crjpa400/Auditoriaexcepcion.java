/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crjpa400;

import java.io.Serializable;

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
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author eve0014321
 * @version $Revision: 1.0 $
 */
@Entity
@Table(name = "AUDITORIAEXCEPCION")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Auditoriaexcepcion.findAll", query = "SELECT a FROM Auditoriaexcepcion a"),
		@NamedQuery(name = "Auditoriaexcepcion.findById", query = "SELECT a FROM Auditoriaexcepcion a WHERE a.id = :id"),
		@NamedQuery(name = "Auditoriaexcepcion.findByI", query = "SELECT a FROM Auditoriaexcepcion a WHERE a.i = :i"),
		@NamedQuery(name = "Auditoriaexcepcion.findByTraceLine", query = "SELECT a FROM Auditoriaexcepcion a WHERE a.traceLine = :traceLine"),
		@NamedQuery(name = "Auditoriaexcepcion.findByEstreplica", query = "SELECT a FROM Auditoriaexcepcion a WHERE a.estreplica = :estreplica") })
public class Auditoriaexcepcion implements Serializable {
	/**
	 * Field serialVersionUID. (value is 1)
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Field id.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID")
	private Long id;
	/**
	 * Field i.
	 */
	@Basic(optional = false)
	@Column(name = "I", nullable = false)
	private short i;
	/**
	 * Field traceLine.
	 */
	@Basic(optional = false)
	@Column(name = "TRACE_LINE", nullable = false, length = 254)
	private String traceLine;
	/**
	 * Field estreplica.
	 */
	@Basic(optional = false)
	@Column(name = "ESTREPLICA")
	private char estreplica;
	/**
	 * Field idAuditoria.
	 */
	@JoinColumn(name = "ID_AUDITORIA", referencedColumnName = "ID")
	@ManyToOne
	private Auditoria idAuditoria;

	/**
	 * Constructor for Auditoriaexcepcion.
	 */
	public Auditoriaexcepcion() {
	}

	/**
	 * Constructor for Auditoriaexcepcion.
	 * 
	 * @param id
	 *            Long
	 */
	public Auditoriaexcepcion(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Auditoriaexcepcion.
	 * 
	 * @param id
	 *            Long
	 * @param i
	 *            short
	 * @param traceLine
	 *            String
	 * @param estreplica
	 *            char
	 */
	public Auditoriaexcepcion(Long id, short i, String traceLine,
			char estreplica) {
		this.id = id;
		this.i = i;
		this.traceLine = traceLine;
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
	 * Method getI.
	 * 
	 * @return short
	 */
	public short getI() {
		return i;
	}

	/**
	 * Method setI.
	 * 
	 * @param i
	 *            short
	 */
	public void setI(short i) {
		this.i = i;
	}

	/**
	 * Method getTraceLine.
	 * 
	 * @return String
	 */
	public String getTraceLine() {
		return traceLine;
	}

	/**
	 * Method setTraceLine.
	 * 
	 * @param traceLine
	 *            String
	 */
	public void setTraceLine(String traceLine) {
		this.traceLine = traceLine;
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
	 * Method getIdAuditoria.
	 * 
	 * @return Auditoria
	 */
	public Auditoria getIdAuditoria() {
		return idAuditoria;
	}

	/**
	 * Method setIdAuditoria.
	 * 
	 * @param idAuditoria
	 *            Auditoria
	 */
	public void setIdAuditoria(Auditoria idAuditoria) {
		this.idAuditoria = idAuditoria;
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
		if (!(object instanceof Auditoriaexcepcion)) {
			return false;
		}
		Auditoriaexcepcion other = (Auditoriaexcepcion) object;
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
		return "crjpa400.Auditoriaexcepcion[ id=" + id + " ]";
	}

}
