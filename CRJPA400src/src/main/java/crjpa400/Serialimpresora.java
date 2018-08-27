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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import crjpa400.interfaces.CrjpaInterface;

/**
 * 
 * @author eve0014321
 * @version $Revision: 1.0 $
 */
@Entity
@Table(name = "SERIALIMPRESORA")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Serialimpresora.findAll", query = "SELECT s FROM Serialimpresora s"),
		@NamedQuery(name = "Serialimpresora.findById", query = "SELECT s FROM Serialimpresora s WHERE s.id = :id"),
		@NamedQuery(name = "Serialimpresora.findBySerial", query = "SELECT s FROM Serialimpresora s WHERE s.serial = :serial"),
		@NamedQuery(name = "Serialimpresora.findByEstaactivo", query = "SELECT s FROM Serialimpresora s WHERE s.estaactivo = :estaactivo"),
		@NamedQuery(name = "Serialimpresora.findByUltimasincronizacion", query = "SELECT s FROM Serialimpresora s WHERE s.ultimasincronizacion = :ultimasincronizacion"),
		@NamedQuery(name = "Serialimpresora.findByEstreplica", query = "SELECT s FROM Serialimpresora s WHERE s.estreplica = :estreplica") })
public class Serialimpresora extends CrjpaInterface implements Serializable {
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
	 * Field serial.
	 */
	@Basic(optional = false)
	@Column(name = "SERIAL", nullable = false, length = 20)
	private String serial;
	/**
	 * Field estaactivo.
	 */
	@Basic(optional = false)
	@Column(name = "ESTAACTIVO", nullable = false)
	private char estaactivo;
	/**
	 * Field ultimafactura.
	 */
	@Basic(optional = false)
	@Column(name = "ULTIMA_FACTURA")
	private Long ultimafactura;
	/**
	 * Field ultimanotacredito.
	 */
	@Basic(optional = false)
	@Column(name = "ULTIMA_NOTACREDITO")
	private Long ultimanotacredito;
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
	 * Field rolloauditoriaList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idSerialimpresora")
	private List<Rolloauditoria> rolloauditoriaList;
	/**
	 * Field transaccionList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idSerialimpresora")
	private List<Transaccion> transaccionList;

	/**
	 * Constructor for Serialimpresora.
	 */
	public Serialimpresora() {
	}

	/**
	 * Constructor for Serialimpresora.
	 * 
	 * @param id
	 *            Long
	 */
	public Serialimpresora(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Serialimpresora.
	 * 
	 * @param id
	 *            Long
	 * @param serial
	 *            String
	 * @param estaactivo
	 *            char
	 * @param ultimasincronizacion
	 *            Calendar
	 * @param estreplica
	 *            char
	 */
	public Serialimpresora(Long id, String serial, char estaactivo,
			Calendar ultimasincronizacion, char estreplica) {
		this.id = id;
		this.serial = serial;
		this.estaactivo = estaactivo;
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
	 * Method getSerial.
	 * 
	 * @return String
	 */
	public String getSerial() {
		return serial;
	}

	/**
	 * Method setSerial.
	 * 
	 * @param serial
	 *            String
	 */
	public void setSerial(String serial) {
		this.serial = serial;
	}

	/**
	 * Method getEstaactivo.
	 * 
	 * @return char
	 */
	public char getEstaactivo() {
		return estaactivo;
	}

	/**
	 * Method setEstaactivo.
	 * 
	 * @param estaactivo
	 *            char
	 */
	public void setEstaactivo(char estaactivo) {
		this.estaactivo = estaactivo;
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
	 * Method getRolloauditoriaList.
	 * 
	 * @return List<Rolloauditoria>
	 */
	@XmlTransient
	public List<Rolloauditoria> getRolloauditoriaList() {
		return rolloauditoriaList;
	}

	/**
	 * Method setRolloauditoriaList.
	 * 
	 * @param rolloauditoriaList
	 *            List<Rolloauditoria>
	 */
	public void setRolloauditoriaList(List<Rolloauditoria> rolloauditoriaList) {
		this.rolloauditoriaList = rolloauditoriaList;
	}

	/**
	 * Method getTransaccionList.
	 * 
	 * @return List<Transaccion>
	 */
	@XmlTransient
	public List<Transaccion> getTransaccionList() {
		return transaccionList;
	}

	/**
	 * Method setTransaccionList.
	 * 
	 * @param transaccionList
	 *            List<Transaccion>
	 */
	public void setTransaccionList(List<Transaccion> transaccionList) {
		this.transaccionList = transaccionList;
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
		if (!(object instanceof Serialimpresora)) {
			return false;
		}
		Serialimpresora other = (Serialimpresora) object;
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
		return "crjpa400.Serialimpresora[ id=" + id + " ]";
	}

	/**
	 * @param ultimafactura
	 *            the ultimafactura to set
	 */
	public void setUltimafactura(Long ultimafactura) {
		this.ultimafactura = ultimafactura;
	}

	/**
	 * 
	 * @return the ultimafactura
	 */
	public Long getUltimafactura() {
		return ultimafactura;
	}

	/**
	 * @param ultimanotacredito
	 *            the ultimanotacredito to set
	 */
	public void setUltimanotacredito(Long ultimanotacredito) {
		this.ultimanotacredito = ultimanotacredito;
	}

	/**
	 * 
	 * @return the ultimanotacredito
	 */
	public Long getUltimanotacredito() {
		return ultimanotacredito;
	}

}
