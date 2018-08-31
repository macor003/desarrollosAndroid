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
@Table(name = "LOGEXEPCIONES")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Logexepciones.findAll", query = "SELECT l FROM Logexepciones l"),
		@NamedQuery(name = "Logexepciones.findById", query = "SELECT l FROM Logexepciones l WHERE l.id = :id"),
		@NamedQuery(name = "Logexepciones.findByObjeto", query = "SELECT l FROM Logexepciones l WHERE l.objeto = :objeto"),
		@NamedQuery(name = "Logexepciones.findBySqlEstado", query = "SELECT l FROM Logexepciones l WHERE l.sqlEstado = :sqlEstado"),
		@NamedQuery(name = "Logexepciones.findBySqlCode", query = "SELECT l FROM Logexepciones l WHERE l.sqlCode = :sqlCode"),
		@NamedQuery(name = "Logexepciones.findByMensaje", query = "SELECT l FROM Logexepciones l WHERE l.mensaje = :mensaje"),
		@NamedQuery(name = "Logexepciones.findByFecha", query = "SELECT l FROM Logexepciones l WHERE l.fecha = :fecha"),
		@NamedQuery(name = "Logexepciones.findByEstreplica", query = "SELECT l FROM Logexepciones l WHERE l.estreplica = :estreplica") })
public class Logexepciones implements Serializable {
	/**
	 * Field serialVersionUID. (value is 1)
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Field id.
	 */
	@Id
	@Basic(optional = false)
	@Column(name = "ID")
	private Long id;
	/**
	 * Field objeto.
	 */
	@Basic(optional = false)
	@Column(name = "OBJETO")
	private String objeto;
	/**
	 * Field sqlEstado.
	 */
	@Basic(optional = false)
	@Column(name = "SQL_ESTADO")
	private String sqlEstado;
	/**
	 * Field sqlCode.
	 */
	@Basic(optional = false)
	@Column(name = "SQL_CODE")
	private int sqlCode;
	/**
	 * Field mensaje.
	 */
	@Basic(optional = false)
	@Column(name = "MENSAJE")
	private String mensaje;
	/**
	 * Field fecha.
	 */
	@Basic(optional = false)
	@Column(name = "FECHA")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	/**
	 * Field estreplica.
	 */
	@Basic(optional = false)
	@Column(name = "ESTREPLICA")
	private char estreplica;

	/**
	 * Constructor for Logexepciones.
	 */
	public Logexepciones() {
	}

	/**
	 * Constructor for Logexepciones.
	 * 
	 * @param id
	 *            Long
	 */
	public Logexepciones(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Logexepciones.
	 * 
	 * @param id
	 *            Long
	 * @param objeto
	 *            String
	 * @param sqlEstado
	 *            String
	 * @param sqlCode
	 *            int
	 * @param mensaje
	 *            String
	 * @param fecha
	 *            Date
	 * @param estreplica
	 *            char
	 */
	public Logexepciones(Long id, String objeto, String sqlEstado, int sqlCode,
			String mensaje, Date fecha, char estreplica) {
		this.id = id;
		this.objeto = objeto;
		this.sqlEstado = sqlEstado;
		this.sqlCode = sqlCode;
		this.mensaje = mensaje;
		this.fecha = fecha;
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
	 * Method getObjeto.
	 * 
	 * @return String
	 */
	public String getObjeto() {
		return objeto;
	}

	/**
	 * Method setObjeto.
	 * 
	 * @param objeto
	 *            String
	 */
	public void setObjeto(String objeto) {
		this.objeto = objeto;
	}

	/**
	 * Method getSqlEstado.
	 * 
	 * @return String
	 */
	public String getSqlEstado() {
		return sqlEstado;
	}

	/**
	 * Method setSqlEstado.
	 * 
	 * @param sqlEstado
	 *            String
	 */
	public void setSqlEstado(String sqlEstado) {
		this.sqlEstado = sqlEstado;
	}

	/**
	 * Method getSqlCode.
	 * 
	 * @return int
	 */
	public int getSqlCode() {
		return sqlCode;
	}

	/**
	 * Method setSqlCode.
	 * 
	 * @param sqlCode
	 *            int
	 */
	public void setSqlCode(int sqlCode) {
		this.sqlCode = sqlCode;
	}

	/**
	 * Method getMensaje.
	 * 
	 * @return String
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * Method setMensaje.
	 * 
	 * @param mensaje
	 *            String
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
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
		if (!(object instanceof Logexepciones)) {
			return false;
		}
		Logexepciones other = (Logexepciones) object;
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
		return "crjpa400.Logexepciones[ id=" + id + " ]";
	}

}
