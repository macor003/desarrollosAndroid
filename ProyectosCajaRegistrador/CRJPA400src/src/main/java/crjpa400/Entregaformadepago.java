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
import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author eve0014321
 * @version $Revision: 1.0 $
 */
@Entity
@Table(name = "ENTREGAFORMADEPAGO")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Entregaformadepago.findAll", query = "SELECT e FROM Entregaformadepago e"),
		@NamedQuery(name = "Entregaformadepago.findById", query = "SELECT e FROM Entregaformadepago e WHERE e.id = :id"),
		@NamedQuery(name = "Entregaformadepago.findByCodigoMonedaDenominacion", query = "SELECT e FROM Entregaformadepago e WHERE e.codigoMonedaDenominacion = :codigoMonedaDenominacion"),
		@NamedQuery(name = "Entregaformadepago.findByMonto", query = "SELECT e FROM Entregaformadepago e WHERE e.monto = :monto"),
		@NamedQuery(name = "Entregaformadepago.findByBilletes", query = "SELECT e FROM Entregaformadepago e WHERE e.billetes = :billetes"),
		@NamedQuery(name = "Entregaformadepago.findByMonedas", query = "SELECT e FROM Entregaformadepago e WHERE e.monedas = :monedas"),
		@NamedQuery(name = "Entregaformadepago.findByFecha", query = "SELECT e FROM Entregaformadepago e WHERE e.fecha = :fecha"),
		@NamedQuery(name = "Entregaformadepago.findByEstaactivo", query = "SELECT e FROM Entregaformadepago e WHERE e.estaactivo = :estaactivo"),
		@NamedQuery(name = "Entregaformadepago.findByEstreplica", query = "SELECT e FROM Entregaformadepago e WHERE e.estreplica = :estreplica") })
public class Entregaformadepago implements Serializable {
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
	 * Field codigoMonedaDenominacion.
	 */
	@Basic(optional = false)
	@Column(name = "CODIGO_MONEDA_DENOMINACION", nullable = false)
	private int codigoMonedaDenominacion;
	// @Max(value=?) @Min(value=?)//if you know range of your decimal fields
	// consider using these annotations to enforce field validation
	/**
	 * Field monto.
	 */
	@Basic(optional = false)
	@Column(name = "MONTO", nullable = false, precision = 13, scale = 2)
	private BigDecimal monto;
	/**
	 * Field billetes.
	 */
	@Basic(optional = false)
	@Column(name = "BILLETES", nullable = false)
	private int billetes;
	/**
	 * Field monedas.
	 */
	@Basic(optional = false)
	@Column(name = "MONEDAS", nullable = false)
	private int monedas;
	/**
	 * Field fecha.
	 */
	@Basic(optional = false)
	@Column(name = "FECHA", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	/**
	 * Field estaactivo.
	 */
	@Basic(optional = false)
	@Column(name = "ESTAACTIVO", nullable = false)
	private char estaactivo;
	/**
	 * Field estreplica.
	 */
	@Basic(optional = false)
	@Column(name = "ESTREPLICA")
	private char estreplica;
	/**
	 * Field idFormadepago.
	 */
	@JoinColumn(name = "ID_FORMADEPAGO", referencedColumnName = "ID", nullable = false)
	@ManyToOne(optional = false)
	private Formadepago idFormadepago;
	/**
	 * Field idMonedadenominacion.
	 */
	@JoinColumn(name = "ID_MONEDADENOMINACION", referencedColumnName = "ID", nullable = false)
	@ManyToOne(optional = false)
	private Monedadenominacion idMonedadenominacion;
	/**
	 * Field idEntrega.
	 */
	@JoinColumn(name = "ID_ENTREGA", referencedColumnName = "ID", nullable = false)
	@ManyToOne(optional = false)
	private Entrega idEntrega;

	/**
	 * Constructor for Entregaformadepago.
	 */
	public Entregaformadepago() {
	}

	/**
	 * Constructor for Entregaformadepago.
	 * 
	 * @param id
	 *            Long
	 */
	public Entregaformadepago(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Entregaformadepago.
	 * 
	 * @param id
	 *            Long
	 * @param codigoMonedaDenominacion
	 *            int
	 * @param monto
	 *            BigDecimal
	 * @param billetes
	 *            int
	 * @param monedas
	 *            int
	 * @param fecha
	 *            Date
	 * @param estaactivo
	 *            char
	 * @param estreplica
	 *            char
	 */
	public Entregaformadepago(Long id, int codigoMonedaDenominacion,
			BigDecimal monto, int billetes, int monedas, Date fecha,
			char estaactivo, char estreplica) {
		this.id = id;
		this.codigoMonedaDenominacion = codigoMonedaDenominacion;
		this.monto = monto;
		this.billetes = billetes;
		this.monedas = monedas;
		this.fecha = fecha;
		this.estaactivo = estaactivo;
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
	 * Method getCodigoMonedaDenominacion.
	 * 
	 * @return int
	 */
	public int getCodigoMonedaDenominacion() {
		return codigoMonedaDenominacion;
	}

	/**
	 * Method setCodigoMonedaDenominacion.
	 * 
	 * @param codigoMonedaDenominacion
	 *            int
	 */
	public void setCodigoMonedaDenominacion(int codigoMonedaDenominacion) {
		this.codigoMonedaDenominacion = codigoMonedaDenominacion;
	}

	/**
	 * Method getMonto.
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal getMonto() {
		return monto;
	}

	/**
	 * Method setMonto.
	 * 
	 * @param monto
	 *            BigDecimal
	 */
	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	/**
	 * Method getBilletes.
	 * 
	 * @return int
	 */
	public int getBilletes() {
		return billetes;
	}

	/**
	 * Method setBilletes.
	 * 
	 * @param billetes
	 *            int
	 */
	public void setBilletes(int billetes) {
		this.billetes = billetes;
	}

	/**
	 * Method getMonedas.
	 * 
	 * @return int
	 */
	public int getMonedas() {
		return monedas;
	}

	/**
	 * Method setMonedas.
	 * 
	 * @param monedas
	 *            int
	 */
	public void setMonedas(int monedas) {
		this.monedas = monedas;
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
	 * Method getIdMonedadenominacion.
	 * 
	 * @return Monedadenominacion
	 */
	public Monedadenominacion getIdMonedadenominacion() {
		return idMonedadenominacion;
	}

	/**
	 * Method setIdMonedadenominacion.
	 * 
	 * @param idMonedadenominacion
	 *            Monedadenominacion
	 */
	public void setIdMonedadenominacion(Monedadenominacion idMonedadenominacion) {
		this.idMonedadenominacion = idMonedadenominacion;
	}

	/**
	 * Method getIdEntrega.
	 * 
	 * @return Entrega
	 */
	public Entrega getIdEntrega() {
		return idEntrega;
	}

	/**
	 * Method setIdEntrega.
	 * 
	 * @param idEntrega
	 *            Entrega
	 */
	public void setIdEntrega(Entrega idEntrega) {
		this.idEntrega = idEntrega;
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
		if (!(object instanceof Entregaformadepago)) {
			return false;
		}
		Entregaformadepago other = (Entregaformadepago) object;
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
		return "crjpa400.Entregaformadepago[ id=" + id + " ]";
	}

}
