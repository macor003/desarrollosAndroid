/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crjpa;

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
@Table(name = "serialimpresora")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Serialimpresora.findAll", query = "SELECT s FROM Serialimpresora s"),
		@NamedQuery(name = "Serialimpresora.findById", query = "SELECT s FROM Serialimpresora s WHERE s.id = :id"),
		@NamedQuery(name = "Serialimpresora.findBySerial", query = "SELECT s FROM Serialimpresora s WHERE s.serial = :serial"),
		@NamedQuery(name = "Serialimpresora.findByEstaactivo", query = "SELECT s FROM Serialimpresora s WHERE s.estaactivo = :estaactivo") })
public class Serialimpresora implements Serializable {
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
	 * Field serial.
	 */
	@Basic(optional = false)
	@Column(name = "serial")
	private String serial;
	/**
	 * Field estaactivo.
	 */
	@Basic(optional = false)
	@Column(name = "estaactivo")
	private String estaactivo;
	/**
	 * Field ultimafactura.
	 */
	@Basic(optional = false)
	@Column(name = "ultimafactura")
	private Long ultimafactura;
	/**
	 * Field ultimanotacredito.
	 */
	@Basic(optional = false)
	@Column(name = "ultimanotacredito")
	private Long ultimanotacredito;
	/**
	 * Field transaccionList.
	 */
	@OneToMany(mappedBy = "idSerialimpresora")
	private List<Transaccion> transaccionList;
	/**
	 * Field rolloauditoriaList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idSerialimpresora")
	private List<Rolloauditoria> rolloauditoriaList;

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
	 *            String
	 */
	public Serialimpresora(Long id, String serial, String estaactivo) {
		this.id = id;
		this.serial = serial;
		this.estaactivo = estaactivo;
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
	 * @return String
	 */
	public String getEstaactivo() {
		return estaactivo;
	}

	/**
	 * Method setEstaactivo.
	 * 
	 * @param estaactivo
	 *            String
	 */
	public void setEstaactivo(String estaactivo) {
		this.estaactivo = estaactivo;
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
		String enclosed = "|";
		String endStr = "\r\n";

		StringBuilder sb = new StringBuilder();

		sb.append(id);
		sb.append(separator);

		sb.append(enclosed);
		sb.append(serial);
		sb.append(enclosed);

		sb.append(separator);

		sb.append(estaactivo);

		sb.append(separator);
		sb.append(ultimafactura);
		sb.append(separator);
		sb.append(ultimanotacredito);

		sb.append(endStr);
		return sb.toString();
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
