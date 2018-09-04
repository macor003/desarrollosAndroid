/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crjpa400;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * 
 * @author eve0014321
 * @version $Revision: 1.0 $
 */
@Entity
@Table(name = "CAJA")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Caja.findAll", query = "SELECT c FROM Caja c"),
		@NamedQuery(name = "Caja.findById", query = "SELECT c FROM Caja c WHERE c.id = :id"),
		@NamedQuery(name = "Caja.findByIp", query = "SELECT c FROM Caja c WHERE c.ip = :ip"),
		@NamedQuery(name = "Caja.findByTipoCaja", query = "SELECT c FROM Caja c WHERE c.tipoCaja = :tipoCaja"),
		@NamedQuery(name = "Caja.findByEstreplica", query = "SELECT c FROM Caja c WHERE c.estreplica = :estreplica") })
public class Caja implements Serializable {
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
	 * Field ip.
	 */
	@Column(name = "IP", length = 45)
	private String ip;
	/**
	 * Field tipoCaja.
	 */
	@Basic(optional = false)
	@Column(name = "TIPO_CAJA", nullable = false)
	private int tipoCaja;
	/**
	 * Field estreplica.
	 */
	@Basic(optional = false)
	@Column(name = "ESTREPLICA")
	private char estreplica;
	/**
	 * Field sesionList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idCaja")
	private List<Sesion> sesionList;
	/**
	 * Field comprobantefiscalpreimpresoList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idCaja")
	private List<Comprobantefiscalpreimpreso> comprobantefiscalpreimpresoList;

	/**
	 * Constructor for Caja.
	 */
	public Caja() {
	}

	/**
	 * Constructor for Caja.
	 * 
	 * @param id
	 *            Long
	 */
	public Caja(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Caja.
	 * 
	 * @param id
	 *            Long
	 * @param tipoCaja
	 *            int
	 * @param estreplica
	 *            char
	 */
	public Caja(Long id, int tipoCaja, char estreplica) {
		this.id = id;
		this.tipoCaja = tipoCaja;
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
	 * Method getIp.
	 * 
	 * @return String
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * Method setIp.
	 * 
	 * @param ip
	 *            String
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * Method getTipoCaja.
	 * 
	 * @return int
	 */
	public int getTipoCaja() {
		return tipoCaja;
	}

	/**
	 * Method setTipoCaja.
	 * 
	 * @param tipoCaja
	 *            int
	 */
	public void setTipoCaja(int tipoCaja) {
		this.tipoCaja = tipoCaja;
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
	 * Method getSesionList.
	 * 
	 * @return List<Sesion>
	 */
	@XmlTransient
	public List<Sesion> getSesionList() {
		return sesionList;
	}

	/**
	 * Method setSesionList.
	 * 
	 * @param sesionList
	 *            List<Sesion>
	 */
	public void setSesionList(List<Sesion> sesionList) {
		this.sesionList = sesionList;
	}

	/**
	 * Method getComprobantefiscalpreimpresoList.
	 * 
	 * @return List<Comprobantefiscalpreimpreso>
	 */
	@XmlTransient
	public List<Comprobantefiscalpreimpreso> getComprobantefiscalpreimpresoList() {
		return comprobantefiscalpreimpresoList;
	}

	/**
	 * Method setComprobantefiscalpreimpresoList.
	 * 
	 * @param comprobantefiscalpreimpresoList
	 *            List<Comprobantefiscalpreimpreso>
	 */
	public void setComprobantefiscalpreimpresoList(
			List<Comprobantefiscalpreimpreso> comprobantefiscalpreimpresoList) {
		this.comprobantefiscalpreimpresoList = comprobantefiscalpreimpresoList;
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
		if (!(object instanceof Caja)) {
			return false;
		}
		Caja other = (Caja) object;
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
		return "crjpa400.Caja[ id=" + id + " ]";
	}

}
