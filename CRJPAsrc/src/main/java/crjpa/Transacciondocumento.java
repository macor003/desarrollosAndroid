/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crjpa;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author eve0014321
 * @version $Revision: 1.0 $
 */
@Entity
@Table(name = "transacciondocumento")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Transacciondocumento.findAll", query = "SELECT t FROM Transacciondocumento t"),
		@NamedQuery(name = "Transacciondocumento.findById", query = "SELECT t FROM Transacciondocumento t WHERE t.id = :id"),
		@NamedQuery(name = "Transacciondocumento.findByEsimpreso", query = "SELECT t FROM Transacciondocumento t WHERE t.esimpreso = :esimpreso"),
		@NamedQuery(name = "Transacciondocumento.findByNumeroDocumento", query = "SELECT t FROM Transacciondocumento t WHERE t.numeroDocumento = :numeroDocumento"),
		@NamedQuery(name = "Transacciondocumento.findByEstasincronizado", query = "SELECT t FROM Transacciondocumento t WHERE t.estasincronizado = :estasincronizado") })
public class Transacciondocumento implements Serializable {
	/**
	 * Field serialVersionUID. (value is 1)
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Field id.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "11")
	@TableGenerator(name = "11", table = "correlativo", pkColumnName = "id", valueColumnName = "valor", pkColumnValue = "11", allocationSize = 1)
	@Basic(optional = false)
	@Column(name = "id", nullable = false)
	private Long id;
	/**
	 * Field esimpreso.
	 */
	@Basic(optional = false)
	@Column(name = "esimpreso", nullable = false, length = 2)
	private String esimpreso;
	/**
	 * Field numeroDocumento.
	 */
	@Column(name = "numero_documento")
	private BigInteger numeroDocumento;
	/**
	 * Field documento.
	 */
	@Lob
	@Column(name = "documento", length = 65535)
	private String documento;
	/**
	 * Field cliente.
	 */
	@Column(name = "cliente", length = 65535)
	private String cliente;
	/**
	 * Field estasincronizado.
	 */
	@Basic(optional = false)
	@Column(name = "estasincronizado", nullable = false, length = 2)
	private String estasincronizado;
	/**
	 * Field idComprobantefiscalpreimpreso.
	 */
	@JoinColumn(name = "id_comprobantefiscalpreimpreso", referencedColumnName = "id")
	@ManyToOne
	private Comprobantefiscalpreimpreso idComprobantefiscalpreimpreso;
	/**
	 * Field idTransaccion.
	 */
	@JoinColumn(name = "id_transaccion", referencedColumnName = "id", nullable = false)
	@ManyToOne(optional = false)
	private Transaccion idTransaccion;
	/**
	 * Field idTipodocumento.
	 */
	@JoinColumn(name = "id_tipodocumento", referencedColumnName = "id", nullable = false)
	@ManyToOne(optional = false)
	private Tipodocumento idTipodocumento;

	/**
	 * Constructor for Transacciondocumento.
	 */
	public Transacciondocumento() {
	}

	/**
	 * Constructor for Transacciondocumento.
	 * 
	 * @param id
	 *            Long
	 */
	public Transacciondocumento(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Transacciondocumento.
	 * 
	 * @param id
	 *            Long
	 * @param esimpreso
	 *            String
	 * @param estasincronizado
	 *            String
	 */
	public Transacciondocumento(Long id, String esimpreso, String estasincronizado) {
		this.id = id;
		this.esimpreso = esimpreso;
		this.estasincronizado = estasincronizado;
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
	 * Method getEsimpreso.
	 * 
	 * @return String
	 */
	public String getEsimpreso() {
		return esimpreso;
	}

	/**
	 * Method setEsimpreso.
	 * 
	 * @param esimpreso
	 *            String
	 */
	public void setEsimpreso(String esimpreso) {
		this.esimpreso = esimpreso;
	}

	/**
	 * Method getNumeroDocumento.
	 * 
	 * @return BigInteger
	 */
	public BigInteger getNumeroDocumento() {
		return numeroDocumento;
	}

	/**
	 * Method setNumeroDocumento.
	 * 
	 * @param numeroDocumento
	 *            BigInteger
	 */
	public void setNumeroDocumento(BigInteger numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	/**
	 * Method getDocumento.
	 * 
	 * @return String
	 */
	public String getDocumento() {
		return documento;
	}

	/**
	 * Method setDocumento.
	 * 
	 * @param documento
	 *            String
	 */
	public void setDocumento(String documento) {
		this.documento = documento;
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
	 * Method getIdTransaccion.
	 * 
	 * @return Transaccion
	 */
	public Transaccion getIdTransaccion() {
		return idTransaccion;
	}

	/**
	 * Method setIdTransaccion.
	 * 
	 * @param idTransaccion
	 *            Transaccion
	 */
	public void setIdTransaccion(Transaccion idTransaccion) {
		this.idTransaccion = idTransaccion;
	}

	/**
	 * Method getIdTipodocumento.
	 * 
	 * @return Tipodocumento
	 */
	public Tipodocumento getIdTipodocumento() {
		return idTipodocumento;
	}

	/**
	 * Method setIdTipodocumento.
	 * 
	 * @param idTipodocumento
	 *            Tipodocumento
	 */
	public void setIdTipodocumento(Tipodocumento idTipodocumento) {
		this.idTipodocumento = idTipodocumento;
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
		if (!(object instanceof Transacciondocumento)) {
			return false;
		}
		Transacciondocumento other = (Transacciondocumento) object;
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
		return "crjpa.Transacciondocumento[ id=" + id + " ]";
	}

	/**
	 * @return the cliente
	 */
	public String getCliente() {
		return cliente;
	}

	/**
	 * @param cliente the cliente to set
	 */
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

}
