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
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * 
 * @author eve0014321
 * @version $Revision: 1.0 $
 */
@Entity
@Table(name = "ACREENCIAMOVIMIENTOSALDO")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Acreenciamovimientosaldo.findAll", query = "SELECT a FROM Acreenciamovimientosaldo a"),
		@NamedQuery(name = "Acreenciamovimientosaldo.findById", query = "SELECT a FROM Acreenciamovimientosaldo a WHERE a.id = :id"),
		@NamedQuery(name = "Acreenciamovimientosaldo.findByCuentacontable", query = "SELECT a FROM Acreenciamovimientosaldo a WHERE a.cuentacontable = :cuentacontable"),
		@NamedQuery(name = "Acreenciamovimientosaldo.findByFecha", query = "SELECT a FROM Acreenciamovimientosaldo a WHERE a.fecha = :fecha"),
		@NamedQuery(name = "Acreenciamovimientosaldo.findByNombreunidadnegocio", query = "SELECT a FROM Acreenciamovimientosaldo a WHERE a.nombreunidadnegocio = :nombreunidadnegocio"),
		@NamedQuery(name = "Acreenciamovimientosaldo.findByNombreunidadoperativa", query = "SELECT a FROM Acreenciamovimientosaldo a WHERE a.nombreunidadoperativa = :nombreunidadoperativa"),
		@NamedQuery(name = "Acreenciamovimientosaldo.findByTienda", query = "SELECT a FROM Acreenciamovimientosaldo a WHERE a.tienda = :tienda"),
		@NamedQuery(name = "Acreenciamovimientosaldo.findByOperacion", query = "SELECT a FROM Acreenciamovimientosaldo a WHERE a.operacion = :operacion"),
		@NamedQuery(name = "Acreenciamovimientosaldo.findByAnulaoperacion", query = "SELECT a FROM Acreenciamovimientosaldo a WHERE a.anulaoperacion = :anulaoperacion"),
		@NamedQuery(name = "Acreenciamovimientosaldo.findByCaja", query = "SELECT a FROM Acreenciamovimientosaldo a WHERE a.caja = :caja"),
		@NamedQuery(name = "Acreenciamovimientosaldo.findByTransaccion", query = "SELECT a FROM Acreenciamovimientosaldo a WHERE a.transaccion = :transaccion"),
		@NamedQuery(name = "Acreenciamovimientosaldo.findByCajero", query = "SELECT a FROM Acreenciamovimientosaldo a WHERE a.cajero = :cajero"),
		@NamedQuery(name = "Acreenciamovimientosaldo.findByMontoMonedaLocal", query = "SELECT a FROM Acreenciamovimientosaldo a WHERE a.montoMonedaLocal = :montoMonedaLocal"),
		@NamedQuery(name = "Acreenciamovimientosaldo.findBySaldocontable", query = "SELECT a FROM Acreenciamovimientosaldo a WHERE a.saldocontable = :saldocontable"),
		@NamedQuery(name = "Acreenciamovimientosaldo.findBySaldodisponible", query = "SELECT a FROM Acreenciamovimientosaldo a WHERE a.saldodisponible = :saldodisponible"),
		@NamedQuery(name = "Acreenciamovimientosaldo.findByEstreplica", query = "SELECT a FROM Acreenciamovimientosaldo a WHERE a.estreplica = :estreplica") })
public class Acreenciamovimientosaldo implements Serializable {
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
	 * Field cuentacontable.
	 */
	@Basic(optional = false)
	@Column(name = "CUENTACONTABLE")
	private String cuentacontable;
	/**
	 * Field fecha.
	 */
	@Basic(optional = false)
	@Column(name = "FECHA")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	/**
	 * Field nombreunidadnegocio.
	 */
	@Basic(optional = false)
	@Column(name = "NOMBREUNIDADNEGOCIO")
	private String nombreunidadnegocio;
	/**
	 * Field nombreunidadoperativa.
	 */
	@Basic(optional = false)
	@Column(name = "NOMBREUNIDADOPERATIVA")
	private String nombreunidadoperativa;
	/**
	 * Field tienda.
	 */
	@Basic(optional = false)
	@Column(name = "TIENDA")
	private int tienda;
	/**
	 * Field operacion.
	 */
	@Basic(optional = false)
	@Column(name = "OPERACION")
	private int operacion;
	/**
	 * Field anulaoperacion.
	 */
	@Column(name = "ANULAOPERACION")
	private Integer anulaoperacion;
	/**
	 * Field caja.
	 */
	@Basic(optional = false)
	@Column(name = "CAJA")
	private int caja;
	/**
	 * Field transaccion.
	 */
	@Column(name = "TRANSACCION")
	private Integer transaccion;
	/**
	 * Field cajero.
	 */
	@Basic(optional = false)
	@Column(name = "CAJERO")
	private int cajero;
	// @Max(value=?) @Min(value=?)//if you know range of your decimal fields
	// consider using these annotations to enforce field validation
	/**
	 * Field montoMonedaLocal.
	 */
	@Basic(optional = false)
	@Column(name = "MONTO_MONEDA_LOCAL")
	private BigDecimal montoMonedaLocal;
	/**
	 * Field saldocontable.
	 */
	@Basic(optional = false)
	@Column(name = "SALDOCONTABLE")
	private BigDecimal saldocontable;
	/**
	 * Field saldodisponible.
	 */
	@Basic(optional = false)
	@Column(name = "SALDODISPONIBLE")
	private BigDecimal saldodisponible;
	/**
	 * Field estreplica.
	 */
	@Basic(optional = false)
	@Column(name = "ESTREPLICA")
	private char estreplica;
	/**
	 * Field idTipoacreencia.
	 */
	@JoinColumn(name = "ID_TIPOACREENCIA", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	private Tipoacreencia idTipoacreencia;
	/**
	 * Field idOperacionacreencia.
	 */
	@JoinColumn(name = "ID_OPERACIONACREENCIA", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	private Operacionacreencia idOperacionacreencia;
	/**
	 * Field idAcreencia.
	 */
	@JoinColumn(name = "ID_ACREENCIA", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	private Acreencia idAcreencia;
	/**
	 * Field acreenciamovimientoformadepagoList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idAcreenciamovimientosaldo")
	private List<Acreenciamovimientoformadepago> acreenciamovimientoformadepagoList;

	/**
	 * Constructor for Acreenciamovimientosaldo.
	 */
	public Acreenciamovimientosaldo() {
	}

	/**
	 * Constructor for Acreenciamovimientosaldo.
	 * 
	 * @param id
	 *            Long
	 */
	public Acreenciamovimientosaldo(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Acreenciamovimientosaldo.
	 * 
	 * @param id
	 *            Long
	 * @param cuentacontable
	 *            String
	 * @param fecha
	 *            Date
	 * @param nombreunidadnegocio
	 *            String
	 * @param nombreunidadoperativa
	 *            String
	 * @param tienda
	 *            int
	 * @param operacion
	 *            int
	 * @param caja
	 *            int
	 * @param cajero
	 *            int
	 * @param montoMonedaLocal
	 *            BigDecimal
	 * @param saldocontable
	 *            BigDecimal
	 * @param saldodisponible
	 *            BigDecimal
	 * @param estreplica
	 *            char
	 */
	public Acreenciamovimientosaldo(Long id, String cuentacontable, Date fecha,
			String nombreunidadnegocio, String nombreunidadoperativa,
			int tienda, int operacion, int caja, int cajero,
			BigDecimal montoMonedaLocal, BigDecimal saldocontable,
			BigDecimal saldodisponible, char estreplica) {
		this.id = id;
		this.cuentacontable = cuentacontable;
		this.fecha = fecha;
		this.nombreunidadnegocio = nombreunidadnegocio;
		this.nombreunidadoperativa = nombreunidadoperativa;
		this.tienda = tienda;
		this.operacion = operacion;
		this.caja = caja;
		this.cajero = cajero;
		this.montoMonedaLocal = montoMonedaLocal;
		this.saldocontable = saldocontable;
		this.saldodisponible = saldodisponible;
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
	 * Method getCuentacontable.
	 * 
	 * @return String
	 */
	public String getCuentacontable() {
		return cuentacontable;
	}

	/**
	 * Method setCuentacontable.
	 * 
	 * @param cuentacontable
	 *            String
	 */
	public void setCuentacontable(String cuentacontable) {
		this.cuentacontable = cuentacontable;
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
	 * Method getNombreunidadnegocio.
	 * 
	 * @return String
	 */
	public String getNombreunidadnegocio() {
		return nombreunidadnegocio;
	}

	/**
	 * Method setNombreunidadnegocio.
	 * 
	 * @param nombreunidadnegocio
	 *            String
	 */
	public void setNombreunidadnegocio(String nombreunidadnegocio) {
		this.nombreunidadnegocio = nombreunidadnegocio;
	}

	/**
	 * Method getNombreunidadoperativa.
	 * 
	 * @return String
	 */
	public String getNombreunidadoperativa() {
		return nombreunidadoperativa;
	}

	/**
	 * Method setNombreunidadoperativa.
	 * 
	 * @param nombreunidadoperativa
	 *            String
	 */
	public void setNombreunidadoperativa(String nombreunidadoperativa) {
		this.nombreunidadoperativa = nombreunidadoperativa;
	}

	/**
	 * Method getTienda.
	 * 
	 * @return int
	 */
	public int getTienda() {
		return tienda;
	}

	/**
	 * Method setTienda.
	 * 
	 * @param tienda
	 *            int
	 */
	public void setTienda(int tienda) {
		this.tienda = tienda;
	}

	/**
	 * Method getOperacion.
	 * 
	 * @return int
	 */
	public int getOperacion() {
		return operacion;
	}

	/**
	 * Method setOperacion.
	 * 
	 * @param operacion
	 *            int
	 */
	public void setOperacion(int operacion) {
		this.operacion = operacion;
	}

	/**
	 * Method getAnulaoperacion.
	 * 
	 * @return Integer
	 */
	public Integer getAnulaoperacion() {
		return anulaoperacion;
	}

	/**
	 * Method setAnulaoperacion.
	 * 
	 * @param anulaoperacion
	 *            Integer
	 */
	public void setAnulaoperacion(Integer anulaoperacion) {
		this.anulaoperacion = anulaoperacion;
	}

	/**
	 * Method getCaja.
	 * 
	 * @return int
	 */
	public int getCaja() {
		return caja;
	}

	/**
	 * Method setCaja.
	 * 
	 * @param caja
	 *            int
	 */
	public void setCaja(int caja) {
		this.caja = caja;
	}

	/**
	 * Method getTransaccion.
	 * 
	 * @return Integer
	 */
	public Integer getTransaccion() {
		return transaccion;
	}

	/**
	 * Method setTransaccion.
	 * 
	 * @param transaccion
	 *            Integer
	 */
	public void setTransaccion(Integer transaccion) {
		this.transaccion = transaccion;
	}

	/**
	 * Method getCajero.
	 * 
	 * @return int
	 */
	public int getCajero() {
		return cajero;
	}

	/**
	 * Method setCajero.
	 * 
	 * @param cajero
	 *            int
	 */
	public void setCajero(int cajero) {
		this.cajero = cajero;
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
	 * Method getSaldocontable.
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal getSaldocontable() {
		return saldocontable;
	}

	/**
	 * Method setSaldocontable.
	 * 
	 * @param saldocontable
	 *            BigDecimal
	 */
	public void setSaldocontable(BigDecimal saldocontable) {
		this.saldocontable = saldocontable;
	}

	/**
	 * Method getSaldodisponible.
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal getSaldodisponible() {
		return saldodisponible;
	}

	/**
	 * Method setSaldodisponible.
	 * 
	 * @param saldodisponible
	 *            BigDecimal
	 */
	public void setSaldodisponible(BigDecimal saldodisponible) {
		this.saldodisponible = saldodisponible;
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
	 * Method getIdTipoacreencia.
	 * 
	 * @return Tipoacreencia
	 */
	public Tipoacreencia getIdTipoacreencia() {
		return idTipoacreencia;
	}

	/**
	 * Method setIdTipoacreencia.
	 * 
	 * @param idTipoacreencia
	 *            Tipoacreencia
	 */
	public void setIdTipoacreencia(Tipoacreencia idTipoacreencia) {
		this.idTipoacreencia = idTipoacreencia;
	}

	/**
	 * Method getIdOperacionacreencia.
	 * 
	 * @return Operacionacreencia
	 */
	public Operacionacreencia getIdOperacionacreencia() {
		return idOperacionacreencia;
	}

	/**
	 * Method setIdOperacionacreencia.
	 * 
	 * @param idOperacionacreencia
	 *            Operacionacreencia
	 */
	public void setIdOperacionacreencia(Operacionacreencia idOperacionacreencia) {
		this.idOperacionacreencia = idOperacionacreencia;
	}

	/**
	 * Method getIdAcreencia.
	 * 
	 * @return Acreencia
	 */
	public Acreencia getIdAcreencia() {
		return idAcreencia;
	}

	/**
	 * Method setIdAcreencia.
	 * 
	 * @param idAcreencia
	 *            Acreencia
	 */
	public void setIdAcreencia(Acreencia idAcreencia) {
		this.idAcreencia = idAcreencia;
	}

	/**
	 * Method getAcreenciamovimientoformadepagoList.
	 * 
	 * @return List<Acreenciamovimientoformadepago>
	 */
	@XmlTransient
	public List<Acreenciamovimientoformadepago> getAcreenciamovimientoformadepagoList() {
		return acreenciamovimientoformadepagoList;
	}

	/**
	 * Method setAcreenciamovimientoformadepagoList.
	 * 
	 * @param acreenciamovimientoformadepagoList
	 *            List<Acreenciamovimientoformadepago>
	 */
	public void setAcreenciamovimientoformadepagoList(
			List<Acreenciamovimientoformadepago> acreenciamovimientoformadepagoList) {
		this.acreenciamovimientoformadepagoList = acreenciamovimientoformadepagoList;
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
		if (!(object instanceof Acreenciamovimientosaldo)) {
			return false;
		}
		Acreenciamovimientosaldo other = (Acreenciamovimientosaldo) object;
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
		return "crjpa400.Acreenciamovimientosaldo[ id=" + id + " ]";
	}

}
