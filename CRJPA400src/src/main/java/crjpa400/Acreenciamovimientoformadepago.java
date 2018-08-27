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
@Table(name = "ACREENCIAMOVIMIENTOFORMADEPAGO")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Acreenciamovimientoformadepago.findAll", query = "SELECT a FROM Acreenciamovimientoformadepago a"),
		@NamedQuery(name = "Acreenciamovimientoformadepago.findById", query = "SELECT a FROM Acreenciamovimientoformadepago a WHERE a.id = :id"),
		@NamedQuery(name = "Acreenciamovimientoformadepago.findByTitular", query = "SELECT a FROM Acreenciamovimientoformadepago a WHERE a.titular = :titular"),
		@NamedQuery(name = "Acreenciamovimientoformadepago.findByConformacion", query = "SELECT a FROM Acreenciamovimientoformadepago a WHERE a.conformacion = :conformacion"),
		@NamedQuery(name = "Acreenciamovimientoformadepago.findByCuentabancaria", query = "SELECT a FROM Acreenciamovimientoformadepago a WHERE a.cuentabancaria = :cuentabancaria"),
		@NamedQuery(name = "Acreenciamovimientoformadepago.findByDocumentoformadepago", query = "SELECT a FROM Acreenciamovimientoformadepago a WHERE a.documentoformadepago = :documentoformadepago"),
		@NamedQuery(name = "Acreenciamovimientoformadepago.findByMontoMonedaLocal", query = "SELECT a FROM Acreenciamovimientoformadepago a WHERE a.montoMonedaLocal = :montoMonedaLocal"),
		@NamedQuery(name = "Acreenciamovimientoformadepago.findByMontoMoneda", query = "SELECT a FROM Acreenciamovimientoformadepago a WHERE a.montoMoneda = :montoMoneda"),
		@NamedQuery(name = "Acreenciamovimientoformadepago.findByVuelto", query = "SELECT a FROM Acreenciamovimientoformadepago a WHERE a.vuelto = :vuelto"),
		@NamedQuery(name = "Acreenciamovimientoformadepago.findByEstado", query = "SELECT a FROM Acreenciamovimientoformadepago a WHERE a.estado = :estado"),
		@NamedQuery(name = "Acreenciamovimientoformadepago.findByEstreplica", query = "SELECT a FROM Acreenciamovimientoformadepago a WHERE a.estreplica = :estreplica") })
public class Acreenciamovimientoformadepago implements Serializable {
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
	 * Field titular.
	 */
	@Column(name = "TITULAR")
	private String titular;
	/**
	 * Field conformacion.
	 */
	@Column(name = "CONFORMACION")
	private String conformacion;
	/**
	 * Field cuentabancaria.
	 */
	@Column(name = "CUENTABANCARIA")
	private String cuentabancaria;
	/**
	 * Field documentoformadepago.
	 */
	@Column(name = "DOCUMENTOFORMADEPAGO")
	private String documentoformadepago;
	// @Max(value=?) @Min(value=?)//if you know range of your decimal fields
	// consider using these annotations to enforce field validation
	/**
	 * Field montoMonedaLocal.
	 */
	@Basic(optional = false)
	@Column(name = "MONTO_MONEDA_LOCAL")
	private BigDecimal montoMonedaLocal;
	/**
	 * Field montoMoneda.
	 */
	@Basic(optional = false)
	@Column(name = "MONTO_MONEDA")
	private BigDecimal montoMoneda;
	/**
	 * Field vuelto.
	 */
	@Basic(optional = false)
	@Column(name = "VUELTO")
	private BigDecimal vuelto;
	/**
	 * Field estado.
	 */
	@Basic(optional = false)
	@Column(name = "ESTADO")
	private String estado;
	/**
	 * Field estreplica.
	 */
	@Basic(optional = false)
	@Column(name = "ESTREPLICA")
	private char estreplica;
	/**
	 * Field idAcreenciamovimientosaldo.
	 */
	@JoinColumn(name = "ID_ACREENCIAMOVIMIENTOSALDO", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	private Acreenciamovimientosaldo idAcreenciamovimientosaldo;
	/**
	 * Field idFormadepago.
	 */
	@JoinColumn(name = "ID_FORMADEPAGO", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	private Formadepago idFormadepago;
	/**
	 * Field idMoneda.
	 */
	@JoinColumn(name = "ID_MONEDA", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	private Moneda idMoneda;

	/**
	 * Constructor for Acreenciamovimientoformadepago.
	 */
	public Acreenciamovimientoformadepago() {
	}

	/**
	 * Constructor for Acreenciamovimientoformadepago.
	 * 
	 * @param id
	 *            Long
	 */
	public Acreenciamovimientoformadepago(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Acreenciamovimientoformadepago.
	 * 
	 * @param id
	 *            Long
	 * @param montoMonedaLocal
	 *            BigDecimal
	 * @param montoMoneda
	 *            BigDecimal
	 * @param vuelto
	 *            BigDecimal
	 * @param estado
	 *            String
	 * @param estreplica
	 *            char
	 */
	public Acreenciamovimientoformadepago(Long id, BigDecimal montoMonedaLocal,
			BigDecimal montoMoneda, BigDecimal vuelto, String estado,
			char estreplica) {
		this.id = id;
		this.montoMonedaLocal = montoMonedaLocal;
		this.montoMoneda = montoMoneda;
		this.vuelto = vuelto;
		this.estado = estado;
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
	 * Method getCuentabancaria.
	 * 
	 * @return String
	 */
	public String getCuentabancaria() {
		return cuentabancaria;
	}

	/**
	 * Method setCuentabancaria.
	 * 
	 * @param cuentabancaria
	 *            String
	 */
	public void setCuentabancaria(String cuentabancaria) {
		this.cuentabancaria = cuentabancaria;
	}

	/**
	 * Method getDocumentoformadepago.
	 * 
	 * @return String
	 */
	public String getDocumentoformadepago() {
		return documentoformadepago;
	}

	/**
	 * Method setDocumentoformadepago.
	 * 
	 * @param documentoformadepago
	 *            String
	 */
	public void setDocumentoformadepago(String documentoformadepago) {
		this.documentoformadepago = documentoformadepago;
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
	 * Method getVuelto.
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal getVuelto() {
		return vuelto;
	}

	/**
	 * Method setVuelto.
	 * 
	 * @param vuelto
	 *            BigDecimal
	 */
	public void setVuelto(BigDecimal vuelto) {
		this.vuelto = vuelto;
	}

	/**
	 * Method getEstado.
	 * 
	 * @return String
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * Method setEstado.
	 * 
	 * @param estado
	 *            String
	 */
	public void setEstado(String estado) {
		this.estado = estado;
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
	 * Method getIdAcreenciamovimientosaldo.
	 * 
	 * @return Acreenciamovimientosaldo
	 */
	public Acreenciamovimientosaldo getIdAcreenciamovimientosaldo() {
		return idAcreenciamovimientosaldo;
	}

	/**
	 * Method setIdAcreenciamovimientosaldo.
	 * 
	 * @param idAcreenciamovimientosaldo
	 *            Acreenciamovimientosaldo
	 */
	public void setIdAcreenciamovimientosaldo(
			Acreenciamovimientosaldo idAcreenciamovimientosaldo) {
		this.idAcreenciamovimientosaldo = idAcreenciamovimientosaldo;
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
		if (!(object instanceof Acreenciamovimientoformadepago)) {
			return false;
		}
		Acreenciamovimientoformadepago other = (Acreenciamovimientoformadepago) object;
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
		return "crjpa400.Acreenciamovimientoformadepago[ id=" + id + " ]";
	}

}
