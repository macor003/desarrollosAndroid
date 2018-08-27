/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package crjpa400;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @author eve0014321
 * @version $Revision: 1.0 $
 */
@Entity
@Table(name = "TRANSACCIONFORMADEPAGO")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Transaccionformadepago.findAll", query = "SELECT t FROM Transaccionformadepago t"),
		@NamedQuery(name = "Transaccionformadepago.findById", query = "SELECT t FROM Transaccionformadepago t WHERE t.id = :id"),
		@NamedQuery(name = "Transaccionformadepago.findByItem", query = "SELECT t FROM Transaccionformadepago t WHERE t.item = :item"),
		@NamedQuery(name = "Transaccionformadepago.findByMontoMonedaLocal", query = "SELECT t FROM Transaccionformadepago t WHERE t.montoMonedaLocal = :montoMonedaLocal"),
		@NamedQuery(name = "Transaccionformadepago.findByMontoMoneda", query = "SELECT t FROM Transaccionformadepago t WHERE t.montoMoneda = :montoMoneda"),
		@NamedQuery(name = "Transaccionformadepago.findByDocumento", query = "SELECT t FROM Transaccionformadepago t WHERE t.documento = :documento"),
		@NamedQuery(name = "Transaccionformadepago.findByCuenta", query = "SELECT t FROM Transaccionformadepago t WHERE t.cuenta = :cuenta"),
		@NamedQuery(name = "Transaccionformadepago.findByConformacion", query = "SELECT t FROM Transaccionformadepago t WHERE t.conformacion = :conformacion"),
		@NamedQuery(name = "Transaccionformadepago.findByTitular", query = "SELECT t FROM Transaccionformadepago t WHERE t.titular = :titular"),
		@NamedQuery(name = "Transaccionformadepago.findByEstaactivo", query = "SELECT t FROM Transaccionformadepago t WHERE t.estaactivo = :estaactivo"),
		@NamedQuery(name = "Transaccionformadepago.findByEstreplica", query = "SELECT t FROM Transaccionformadepago t WHERE t.estreplica = :estreplica") })
public class Transaccionformadepago implements Serializable {
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
	@Basic(optional = false)
	@Column(name = "DOCUMENTO", nullable = false, length = 20)
	private String documento;
	/**
	 * Field cuenta.
	 */
	@Basic(optional = false)
	@Column(name = "CUENTA", nullable = false, length = 20)
	private String cuenta;
	/**
	 * Field conformacion.
	 */
	@Basic(optional = false)
	@Column(name = "CONFORMACION", nullable = false, length = 11)
	private String conformacion;
	/**
	 * Field titular.
	 */
	@Basic(optional = false)
	@Column(name = "TITULAR", nullable = false, length = 25)
	private String titular;
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
	 * Field idTransaccion.
	 */
	@JoinColumn(name = "ID_TRANSACCION", referencedColumnName = "ID", nullable = false)
	@ManyToOne(optional = false)
	private Transaccion idTransaccion;
	/**
	 * Field idFormadepago.
	 */
	@JoinColumn(name = "ID_FORMADEPAGO", referencedColumnName = "ID", nullable = false)
	@ManyToOne(optional = false)
	private Formadepago idFormadepago;
	/**
	 * Field idMoneda.
	 */
	@JoinColumn(name = "ID_MONEDA", referencedColumnName = "ID", nullable = false)
	@ManyToOne(optional = false)
	private Moneda idMoneda;
	/**
	 * Field formadepagopuntoagilList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idTransaccionformadepago")
	private List<Formadepagopuntoagil> formadepagopuntoagilList;
	/**
	 * Field porcentajeRetencion.
	 */
	@Basic(optional = false)
	@Column(name = "PORCENTAJE_RETENCION", nullable = false, precision = 13, scale = 2)
	private BigDecimal porcentajeRetencion;
	/**
	 * Field idPorcentajeimpuestoretencion.
	 */
	@JoinColumn(name = "ID_PORCENTAJEIMPUESTORETENCION", referencedColumnName = "ID", nullable = false)
	@ManyToOne(optional = false)
	private Porcentajeimpuestoretencion idPorcentajeimpuestoretencion;
	
	/**
	 * Constructor for Transaccionformadepago.
	 */
	public Transaccionformadepago() {
	}

	/**
	 * Constructor for Transaccionformadepago.
	 * @param id Long
	 */
	public Transaccionformadepago(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Transaccionformadepago.
	 * 
	 * @param id Long
	 * @param item int
	 * @param montoMonedaLocal BigDecimal
	 * @param montoMoneda BigDecimal
	 * @param documento String
	 * @param cuenta String
	 * @param conformacion String
	 * @param titular String
	 * @param estaactivo char
	 * @param estreplica char
	 */
	public Transaccionformadepago(Long id, int item,
			BigDecimal montoMonedaLocal, BigDecimal montoMoneda,
			String documento, String cuenta, String conformacion,
			String titular, char estaactivo, char estreplica) {
		this.id = id;
		this.item = item;
		this.montoMonedaLocal = montoMonedaLocal;
		this.montoMoneda = montoMoneda;
		this.documento = documento;
		this.cuenta = cuenta;
		this.conformacion = conformacion;
		this.titular = titular;
		this.estaactivo = estaactivo;
		this.estreplica = estreplica;
	}

	/**
	 * Method getId.
	 * @return Long
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Method setId.
	 * @param id Long
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Method getItem.
	 * @return int
	 */
	public int getItem() {
		return item;
	}

	/**
	 * Method setItem.
	 * @param item int
	 */
	public void setItem(int item) {
		this.item = item;
	}

	/**
	 * Method getMontoMonedaLocal.
	 * @return BigDecimal
	 */
	public BigDecimal getMontoMonedaLocal() {
		return montoMonedaLocal;
	}

	/**
	 * Method setMontoMonedaLocal.
	 * @param montoMonedaLocal BigDecimal
	 */
	public void setMontoMonedaLocal(BigDecimal montoMonedaLocal) {
		this.montoMonedaLocal = montoMonedaLocal;
	}

	/**
	 * Method getMontoMoneda.
	 * @return BigDecimal
	 */
	public BigDecimal getMontoMoneda() {
		return montoMoneda;
	}

	/**
	 * Method setMontoMoneda.
	 * @param montoMoneda BigDecimal
	 */
	public void setMontoMoneda(BigDecimal montoMoneda) {
		this.montoMoneda = montoMoneda;
	}

	/**
	 * Method getDocumento.
	 * @return String
	 */
	public String getDocumento() {
		return documento;
	}

	/**
	 * Method setDocumento.
	 * @param documento String
	 */
	public void setDocumento(String documento) {
		this.documento = documento;
	}

	/**
	 * Method getCuenta.
	 * @return String
	 */
	public String getCuenta() {
		return cuenta;
	}

	/**
	 * Method setCuenta.
	 * @param cuenta String
	 */
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	/**
	 * Method getConformacion.
	 * @return String
	 */
	public String getConformacion() {
		return conformacion;
	}

	/**
	 * Method setConformacion.
	 * @param conformacion String
	 */
	public void setConformacion(String conformacion) {
		this.conformacion = conformacion;
	}

	/**
	 * Method getTitular.
	 * @return String
	 */
	public String getTitular() {
		return titular;
	}

	/**
	 * Method setTitular.
	 * @param titular String
	 */
	public void setTitular(String titular) {
		this.titular = titular;
	}

	/**
	 * Method getEstaactivo.
	 * @return char
	 */
	public char getEstaactivo() {
		return estaactivo;
	}

	/**
	 * Method setEstaactivo.
	 * @param estaactivo char
	 */
	public void setEstaactivo(char estaactivo) {
		this.estaactivo = estaactivo;
	}

	/**
	 * Method getEstreplica.
	 * @return char
	 */
	public char getEstreplica() {
		return estreplica;
	}

	/**
	 * Method setEstreplica.
	 * @param estreplica char
	 */
	public void setEstreplica(char estreplica) {
		this.estreplica = estreplica;
	}

	/**
	 * Method getIdTransaccion.
	 * @return Transaccion
	 */
	public Transaccion getIdTransaccion() {
		return idTransaccion;
	}

	/**
	 * Method setIdTransaccion.
	 * @param idTransaccion Transaccion
	 */
	public void setIdTransaccion(Transaccion idTransaccion) {
		this.idTransaccion = idTransaccion;
	}

	/**
	 * Method getIdFormadepago.
	 * @return Formadepago
	 */
	public Formadepago getIdFormadepago() {
		return idFormadepago;
	}

	/**
	 * Method setIdFormadepago.
	 * @param idFormadepago Formadepago
	 */
	public void setIdFormadepago(Formadepago idFormadepago) {
		this.idFormadepago = idFormadepago;
	}

	/**
	 * Method getIdMoneda.
	 * @return Moneda
	 */
	public Moneda getIdMoneda() {
		return idMoneda;
	}

	/**
	 * Method setIdMoneda.
	 * @param idMoneda Moneda
	 */
	public void setIdMoneda(Moneda idMoneda) {
		this.idMoneda = idMoneda;
	}

	/**
	 * Method getFormadepagopuntoagilList.
	 * @return List<Formadepagopuntoagil>
	 */
	@XmlTransient
	public List<Formadepagopuntoagil> getFormadepagopuntoagilList() {
		return formadepagopuntoagilList;
	}

	/**
	 * Method setFormadepagopuntoagilList.
	 * @param formadepagopuntoagilList List<Formadepagopuntoagil>
	 */
	public void setFormadepagopuntoagilList(
			List<Formadepagopuntoagil> formadepagopuntoagilList) {
		this.formadepagopuntoagilList = formadepagopuntoagilList;
	}

	/**
	 * Method getPorcentajeRetencion.
	 * @return BigDecimal
	 */
	public BigDecimal getPorcentajeRetencion() {
		return porcentajeRetencion;
	}

	/**
	 * Method setPorcentajeRetencion.
	 * @param porcentajeRetencion BigDecimal
	 */
	public void setPorcentajeRetencion(BigDecimal porcentajeRetencion) {
		this.porcentajeRetencion = porcentajeRetencion;
	}
	
	/**
	 * Method getIdFormadepago.
	 * @return Formadepago
	 */
	public Porcentajeimpuestoretencion getIdPorcentajeimpuestoretencion() {
		return idPorcentajeimpuestoretencion;
	}

	/**
	 * Method setIdFormadepago.
	 * @param idFormadepago Formadepago
	 */
	public void setIdPorcentajeimpuestoretencion(Porcentajeimpuestoretencion idPorcentajeimpuestoretencion) {
		this.idPorcentajeimpuestoretencion = idPorcentajeimpuestoretencion;
	}

	/**
	 * Method hashCode.
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
	 * @param object Object
	 * @return boolean
	 */
	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Transaccionformadepago)) {
			return false;
		}
		Transaccionformadepago other = (Transaccionformadepago) object;
		if ((this.id == null && other.id != null)
				|| (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	/**
	 * Method toString.
	 * @return String
	 */
	@Override
	public String toString() {
		return "crjpa400.Transaccionformadepago[ id=" + id + " ]";
	}
}