/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crjpa400;

import java.io.Serializable;
import java.math.BigDecimal;

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
@Table(name = "ABONOFORMADEPAGO")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Abonoformadepago.findAll", query = "SELECT a FROM Abonoformadepago a"),
		@NamedQuery(name = "Abonoformadepago.findById", query = "SELECT a FROM Abonoformadepago a WHERE a.id = :id"),
		@NamedQuery(name = "Abonoformadepago.findByItem", query = "SELECT a FROM Abonoformadepago a WHERE a.item = :item"),
		@NamedQuery(name = "Abonoformadepago.findByMontoMonedaLocal", query = "SELECT a FROM Abonoformadepago a WHERE a.montoMonedaLocal = :montoMonedaLocal"),
		@NamedQuery(name = "Abonoformadepago.findByMontoMoneda", query = "SELECT a FROM Abonoformadepago a WHERE a.montoMoneda = :montoMoneda"),
		@NamedQuery(name = "Abonoformadepago.findByDocumento", query = "SELECT a FROM Abonoformadepago a WHERE a.documento = :documento"),
		@NamedQuery(name = "Abonoformadepago.findByCuenta", query = "SELECT a FROM Abonoformadepago a WHERE a.cuenta = :cuenta"),
		@NamedQuery(name = "Abonoformadepago.findByConformacion", query = "SELECT a FROM Abonoformadepago a WHERE a.conformacion = :conformacion"),
		@NamedQuery(name = "Abonoformadepago.findByTitular", query = "SELECT a FROM Abonoformadepago a WHERE a.titular = :titular"),
		@NamedQuery(name = "Abonoformadepago.findByEstreplica", query = "SELECT a FROM Abonoformadepago a WHERE a.estreplica = :estreplica") })
public class Abonoformadepago implements Serializable {
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
	@Column(name = "ID", nullable = false)
	private Long id;
	/**
	 * Field item.
	 */
	@Basic(optional = false)
	@Column(name = "ITEM", nullable = false)
	private int item;
	// @Max(value=?) @Min(value=?)//if you know range of your decimal fields
	// consider using these annotations to enforce field validation
	/**
	 * Field montoMonedaLocal.
	 */
	@Basic(optional = false)
	@Column(name = "MONTO_MONEDA_LOCAL", nullable = false, precision = 13, scale = 2)
	private BigDecimal montoMonedaLocal;
	/**
	 * Field montoMoneda.
	 */
	@Basic(optional = false)
	@Column(name = "MONTO_MONEDA", nullable = false, precision = 13, scale = 2)
	private BigDecimal montoMoneda;
	/**
	 * Field documento.
	 */
	@Column(name = "DOCUMENTO", length = 10)
	private String documento;
	/**
	 * Field cuenta.
	 */
	@Column(name = "CUENTA", length = 20)
	private String cuenta;
	/**
	 * Field conformacion.
	 */
	@Column(name = "CONFORMACION", length = 11)
	private String conformacion;
	/**
	 * Field titular.
	 */
	@Column(name = "TITULAR", length = 25)
	private String titular;
	/**
	 * Field estreplica.
	 */
	@Basic(optional = false)
	@Column(name = "ESTREPLICA")
	private char estreplica;
	/**
	 * Field idMoneda.
	 */
	@JoinColumn(name = "ID_MONEDA", referencedColumnName = "ID", nullable = false)
	@ManyToOne(optional = false)
	private Moneda idMoneda;
	/**
	 * Field idFormadepago.
	 */
	@JoinColumn(name = "ID_FORMADEPAGO", referencedColumnName = "ID", nullable = false)
	@ManyToOne(optional = false)
	private Formadepago idFormadepago;
	/**
	 * Field idAbono.
	 */
	@JoinColumn(name = "ID_ABONO", referencedColumnName = "ID", nullable = false)
	@ManyToOne(optional = false)
	private Abono idAbono;

	/**
	 * Constructor for Abonoformadepago.
	 */
	public Abonoformadepago() {
	}

	/**
	 * Constructor for Abonoformadepago.
	 * 
	 * @param id
	 *            Long
	 */
	public Abonoformadepago(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Abonoformadepago.
	 * 
	 * @param id
	 *            Long
	 * @param item
	 *            int
	 * @param montoMonedaLocal
	 *            BigDecimal
	 * @param montoMoneda
	 *            BigDecimal
	 * @param estreplica
	 *            char
	 */
	public Abonoformadepago(Long id, int item, BigDecimal montoMonedaLocal,
			BigDecimal montoMoneda, char estreplica) {
		this.id = id;
		this.item = item;
		this.montoMonedaLocal = montoMonedaLocal;
		this.montoMoneda = montoMoneda;
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
	 * Method getItem.
	 * 
	 * @return int
	 */
	public int getItem() {
		return item;
	}

	/**
	 * Method setItem.
	 * 
	 * @param item
	 *            int
	 */
	public void setItem(int item) {
		this.item = item;
	}

	/**
	 * Method getMontoMonedaLocal.
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal getMontoMonedaLocal() {
		return montoMonedaLocal;
	}

	/**
	 * Method setMontoMonedaLocal.
	 * 
	 * @param montoMonedaLocal
	 *            BigDecimal
	 */
	public void setMontoMonedaLocal(BigDecimal montoMonedaLocal) {
		this.montoMonedaLocal = montoMonedaLocal;
	}

	/**
	 * Method getMontoMoneda.
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal getMontoMoneda() {
		return montoMoneda;
	}

	/**
	 * Method setMontoMoneda.
	 * 
	 * @param montoMoneda
	 *            BigDecimal
	 */
	public void setMontoMoneda(BigDecimal montoMoneda) {
		this.montoMoneda = montoMoneda;
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
	 * Method getCuenta.
	 * 
	 * @return String
	 */
	public String getCuenta() {
		return cuenta;
	}

	/**
	 * Method setCuenta.
	 * 
	 * @param cuenta
	 *            String
	 */
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	/**
	 * Method getConformacion.
	 * 
	 * @return String
	 */
	public String getConformacion() {
		return conformacion;
	}

	/**
	 * Method setConformacion.
	 * 
	 * @param conformacion
	 *            String
	 */
	public void setConformacion(String conformacion) {
		this.conformacion = conformacion;
	}

	/**
	 * Method getTitular.
	 * 
	 * @return String
	 */
	public String getTitular() {
		return titular;
	}

	/**
	 * Method setTitular.
	 * 
	 * @param titular
	 *            String
	 */
	public void setTitular(String titular) {
		this.titular = titular;
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
	 * Method getIdMoneda.
	 * 
	 * @return Moneda
	 */
	public Moneda getIdMoneda() {
		return idMoneda;
	}

	/**
	 * Method setIdMoneda.
	 * 
	 * @param idMoneda
	 *            Moneda
	 */
	public void setIdMoneda(Moneda idMoneda) {
		this.idMoneda = idMoneda;
	}

	/**
	 * Method getIdFormadepago.
	 * 
	 * @return Formadepago
	 */
	public Formadepago getIdFormadepago() {
		return idFormadepago;
	}

	/**
	 * Method setIdFormadepago.
	 * 
	 * @param idFormadepago
	 *            Formadepago
	 */
	public void setIdFormadepago(Formadepago idFormadepago) {
		this.idFormadepago = idFormadepago;
	}

	/**
	 * Method getIdAbono.
	 * 
	 * @return Abono
	 */
	public Abono getIdAbono() {
		return idAbono;
	}

	/**
	 * Method setIdAbono.
	 * 
	 * @param idAbono
	 *            Abono
	 */
	public void setIdAbono(Abono idAbono) {
		this.idAbono = idAbono;
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
		if (!(object instanceof Abonoformadepago)) {
			return false;
		}
		Abonoformadepago other = (Abonoformadepago) object;
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
		return "crjpa400.Abonoformadepago[ id=" + id + " ]";
	}

}
