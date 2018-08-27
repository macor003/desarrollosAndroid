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
@Table(name = "AUDITORIADETALLE")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Auditoriadetalle.findAll", query = "SELECT a FROM Auditoriadetalle a"),
		@NamedQuery(name = "Auditoriadetalle.findById", query = "SELECT a FROM Auditoriadetalle a WHERE a.id = :id"),
		@NamedQuery(name = "Auditoriadetalle.findByMappedKey", query = "SELECT a FROM Auditoriadetalle a WHERE a.mappedKey = :mappedKey"),
		@NamedQuery(name = "Auditoriadetalle.findByMappedValue", query = "SELECT a FROM Auditoriadetalle a WHERE a.mappedValue = :mappedValue"),
		@NamedQuery(name = "Auditoriadetalle.findByEstreplica", query = "SELECT a FROM Auditoriadetalle a WHERE a.estreplica = :estreplica") })
public class Auditoriadetalle implements Serializable {
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
	 * Field mappedKey.
	 */
	@Basic(optional = false)
	@Column(name = "MAPPED_KEY", nullable = false, length = 254)
	private String mappedKey;
	/**
	 * Field mappedValue.
	 */
	@Basic(optional = false)
	@Column(name = "MAPPED_VALUE", nullable = false, length = 2000)
	private String mappedValue;
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
	 * Constructor for Auditoriadetalle.
	 */
	public Auditoriadetalle() {
	}

	/**
	 * Constructor for Auditoriadetalle.
	 * 
	 * @param id
	 *            Long
	 */
	public Auditoriadetalle(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Auditoriadetalle.
	 * 
	 * @param id
	 *            Long
	 * @param mappedKey
	 *            String
	 * @param mappedValue
	 *            String
	 * @param estreplica
	 *            char
	 */
	public Auditoriadetalle(Long id, String mappedKey, String mappedValue,
			char estreplica) {
		this.id = id;
		this.mappedKey = mappedKey;
		this.mappedValue = mappedValue;
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
	 * Method getMappedKey.
	 * 
	 * @return String
	 */
	public String getMappedKey() {
		return mappedKey;
	}

	/**
	 * Method setMappedKey.
	 * 
	 * @param mappedKey
	 *            String
	 */
	public void setMappedKey(String mappedKey) {
		this.mappedKey = mappedKey;
	}

	/**
	 * Method getMappedValue.
	 * 
	 * @return String
	 */
	public String getMappedValue() {
		return mappedValue;
	}

	/**
	 * Method setMappedValue.
	 * 
	 * @param mappedValue
	 *            String
	 */
	public void setMappedValue(String mappedValue) {
		this.mappedValue = mappedValue;
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
		if (!(object instanceof Auditoriadetalle)) {
			return false;
		}
		Auditoriadetalle other = (Auditoriadetalle) object;
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
		return "crjpa400.Auditoriadetalle[ id=" + id + " ]";
	}

}
