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
import java.math.BigInteger;
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
@Table(name = "ORDENDEVENTA")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Ordendeventa.findAll", query = "SELECT o FROM Ordendeventa o"),
		@NamedQuery(name = "Ordendeventa.findById", query = "SELECT o FROM Ordendeventa o WHERE o.id = :id"),
		@NamedQuery(name = "Ordendeventa.findByNumero", query = "SELECT o FROM Ordendeventa o WHERE o.numero = :numero"),
		@NamedQuery(name = "Ordendeventa.findByFecha", query = "SELECT o FROM Ordendeventa o WHERE o.fecha = :fecha"),
		@NamedQuery(name = "Ordendeventa.findByMontoBase", query = "SELECT o FROM Ordendeventa o WHERE o.montoBase = :montoBase"),
		@NamedQuery(name = "Ordendeventa.findByMontoImpuesto", query = "SELECT o FROM Ordendeventa o WHERE o.montoImpuesto = :montoImpuesto"),
		@NamedQuery(name = "Ordendeventa.findByTienda", query = "SELECT o FROM Ordendeventa o WHERE o.tienda = :tienda"),
		@NamedQuery(name = "Ordendeventa.findByUsuario", query = "SELECT o FROM Ordendeventa o WHERE o.usuario = :usuario"),
		@NamedQuery(name = "Ordendeventa.findByEstreplica", query = "SELECT o FROM Ordendeventa o WHERE o.estreplica = :estreplica"),
		@NamedQuery(name = "Ordendeventa.findByMontoprimerabono", query = "SELECT o FROM Ordendeventa o WHERE o.montoPrimerAbono = :montoPrimerAbono"),
		@NamedQuery(name = "Ordendeventa.findByArticulosentienda", query = "SELECT o FROM Ordendeventa o WHERE o.articulosEnTienda = :articulosEnTienda"),
		@NamedQuery(name = "Ordendeventa.findByVigencia", query = "SELECT o FROM Ordendeventa o WHERE o.vigencia = :vigencia") })
public class Ordendeventa implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2213282754897021031L;

	/**
	 * Field id.
	 */
	@Id
	@Basic(optional = false)
	@Column(name = "ID", nullable = false)
	private Long id;
	/**
	 * Field numero.
	 */
	@Basic(optional = false)
	@Column(name = "NUMERO", nullable = false)
	private Integer numero;
	/**
	 * Field fecha.
	 */
	@Basic(optional = false)
	@Column(name = "FECHA", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date fecha;
	/**
	 * Field montoBase.
	 */
	@Basic(optional = false)
	@Column(name = "MONTO_BASE", nullable = false, precision = 13, scale = 2)
	private BigDecimal montoBase;
	/**
	 * Field montoImpuesto.
	 */
	@Basic(optional = false)
	@Column(name = "MONTO_IMPUESTO", nullable = false, precision = 13, scale = 2)
	private BigDecimal montoImpuesto;
	/**
	 * Field tienda.
	 */
	@Column(name = "TIENDA")
	private Integer tienda;
	/**
	 * Field usuario.
	 */
	@Column(name = "USUARIO", length = 30)
	private String usuario;
	/**
	 * Field estreplica.
	 */
	@Basic(optional = false)
	@Column(name = "ESTREPLICA")
	private char estreplica;
	/**
	 * Field idOrdenDeCompra.
	 */
	@Basic(optional = false)
	@Column(name = "ID_ORDENDECOMPRA", nullable = false)
	private Long idOrdenDeCompra;
	/**
	 * Field diasCredito.
	 */
	@Basic(optional = false)
	@Column(name = "DIASCREDITO", nullable = false)
	private Integer diasCredito;
	/**
	 * Field montoPenalizacion.
	 */
	@Basic(optional = false)
	@Column(name = "MONTO_PENALIZACION", nullable = false, precision = 13, scale = 2)
	private BigDecimal montoPenalizacion;
	/**
	 * Field ordendeventaarticuloList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idOrdendeventa")
	private List<Ordendeventaarticulo> ordendeventaarticuloList;
	/**
	 * Field abonoList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idOrdendeventa")
	private List<Abono> abonoList;
	/**
	 * Field ordendeventatransaccionList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idOrdendeventa")
	private List<Ordendeventatransaccion> ordendeventatransaccionList;
	/**
	 * Field numeroIdentificacionCliente.
	 */
	@JoinColumn(name = "NUMERO_IDENTIFICACION_CLIENTE", referencedColumnName = "NUMERO_IDENTIFICACION_CLIENTE", nullable = false)
	@ManyToOne(optional = false)
	private Cliente numeroIdentificacionCliente;
	/**
	 * Field idTipoordendeventa.
	 */
	@JoinColumn(name = "ID_TIPOORDENDEVENTA", referencedColumnName = "ID", nullable = false)
	@ManyToOne(optional = false)
	private Tipoordendeventa idTipoordendeventa;
	/**
	 * Field fichaResponsableEpa.
	 */
	@Column(name = "FICHA_RESPONSABLE_EPA")
	private BigInteger fichaResponsableEpa;
	/**
	 * Field nombreResponsableEpa.
	 */
	@Column(name = "NOMBRE_RESPONSABLE_EPA")
	private String nombreResponsableEpa;
	/**
	 * Field ordedeventaList.
	 */
	@OneToMany(mappedBy = "idOrdenDeVentaPadre")
	private List<Ordendeventa> ordedeventaList;
	/**
	 * Field idOrdenDeVentaPadre.
	 */
	@JoinColumn(name = "ID_ORDENDEVENTA_PADRE", referencedColumnName = "ID")
	@ManyToOne
	private Ordendeventa idOrdenDeVentaPadre;
	/**
	 * Field idEstadoordendenventa.
	 */
	@JoinColumn(name = "ID_ESTADOORDENDEVENTA", referencedColumnName = "ID")
	@ManyToOne
	private Estadoordendeventa idEstadoordendenventa;
	/**
	 * Field montoPrimerAbono.
	 */
	@Basic(optional = false)
	@Column(name = "MONTO_PRIMER_ABONO", nullable = false, precision = 13, scale = 2)
	private BigDecimal montoPrimerAbono;
	/**
	 * Field articulosEnTienda.
	 */
	@Basic(optional = false)
	@Column(name = "ARTICULOS_EN_TIENDA", nullable = false)
	private char articulosEnTienda;
	/**
	 * Field vigencia.
	 */
	@Basic(optional = false)
	@Column(name = "VIGENCIA", nullable = false)
	private int vigencia;

	/**
	 * Constructor for Ordendeventa.
	 */
	public Ordendeventa() {
	}

	/**
	 * Constructor for Ordendeventa.
	 * 
	 * @param id
	 *            Long
	 */
	public Ordendeventa(Long id) {
		this.id = id;
	}

	/**
	 * @param id
	 * @param numero
	 * @param fecha
	 * @param montoBase
	 * @param montoImpuesto
	 * @param tienda
	 * @param usuario
	 * @param estreplica
	 * @param idOrdenDeCompra
	 * @param diasCredito
	 * @param montoPenalizacion
	 * @param ordendeventaarticuloList
	 * @param abonoList
	 * @param ordendeventatransaccionList
	 * @param numeroIdentificacionCliente
	 * @param idTipoordendeventa
	 * @param fichaResponsableEpa
	 * @param nombreResponsableEpa
	 * @param ordedeventaList
	 * @param idOrdenDeVentaPadre
	 * @param idEstadoordendenventa
	 * @param montoPrimerAbono
	 * @param articulosEnTienda
	 * @param vigencia
	 */
	public Ordendeventa(Long id, Integer numero, Date fecha,
			BigDecimal montoBase, BigDecimal montoImpuesto, Integer tienda,
			String usuario, char estreplica, Long idOrdenDeCompra,
			Integer diasCredito, BigDecimal montoPenalizacion,
			List<Ordendeventaarticulo> ordendeventaarticuloList,
			List<Abono> abonoList,
			List<Ordendeventatransaccion> ordendeventatransaccionList,
			Cliente numeroIdentificacionCliente,
			Tipoordendeventa idTipoordendeventa,
			BigInteger fichaResponsableEpa, String nombreResponsableEpa,
			List<Ordendeventa> ordedeventaList,
			Ordendeventa idOrdenDeVentaPadre,
			Estadoordendeventa idEstadoordendenventa,
			BigDecimal montoPrimerAbono, char articulosEnTienda, int vigencia) {
		super();
		this.id = id;
		this.numero = numero;
		this.fecha = fecha;
		this.montoBase = montoBase;
		this.montoImpuesto = montoImpuesto;
		this.tienda = tienda;
		this.usuario = usuario;
		this.estreplica = estreplica;
		this.idOrdenDeCompra = idOrdenDeCompra;
		this.diasCredito = diasCredito;
		this.montoPenalizacion = montoPenalizacion;
		this.ordendeventaarticuloList = ordendeventaarticuloList;
		this.abonoList = abonoList;
		this.ordendeventatransaccionList = ordendeventatransaccionList;
		this.numeroIdentificacionCliente = numeroIdentificacionCliente;
		this.idTipoordendeventa = idTipoordendeventa;
		this.fichaResponsableEpa = fichaResponsableEpa;
		this.nombreResponsableEpa = nombreResponsableEpa;
		this.ordedeventaList = ordedeventaList;
		this.idOrdenDeVentaPadre = idOrdenDeVentaPadre;
		this.idEstadoordendenventa = idEstadoordendenventa;
		this.montoPrimerAbono = montoPrimerAbono;
		this.articulosEnTienda = articulosEnTienda;
		this.vigencia = vigencia;
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
	 * Method getNumero.
	 * 
	 * @return Integer
	 */
	public Integer getNumero() {
		return numero;
	}

	/**
	 * Method setNumero.
	 * 
	 * @param numero
	 *            Integer
	 */
	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	/**
	 * Method getIdEstadoordendenventa.
	 * 
	 * @return Estadoordendeventa
	 */
	public Estadoordendeventa getIdEstadoordendenventa() {
		return idEstadoordendenventa;
	}

	/**
	 * Method setIdEstadoordendenventa.
	 * 
	 * @param idEstadoordendenventa
	 *            Estadoordendeventa
	 */
	public void setIdEstadoordendenventa(
			Estadoordendeventa idEstadoordendenventa) {
		this.idEstadoordendenventa = idEstadoordendenventa;
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
	 * Method getMontoBase.
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal getMontoBase() {
		return montoBase;
	}

	/**
	 * Method setMontoBase.
	 * 
	 * @param montoBase
	 *            BigDecimal
	 */
	public void setMontoBase(BigDecimal montoBase) {
		this.montoBase = montoBase;
	}

	/**
	 * Method getMontoImpuesto.
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal getMontoImpuesto() {
		return montoImpuesto;
	}

	/**
	 * Method setMontoImpuesto.
	 * 
	 * @param montoImpuesto
	 *            BigDecimal
	 */
	public void setMontoImpuesto(BigDecimal montoImpuesto) {
		this.montoImpuesto = montoImpuesto;
	}

	/**
	 * Method getTienda.
	 * 
	 * @return Integer
	 */
	public Integer getTienda() {
		return tienda;
	}

	/**
	 * Method setTienda.
	 * 
	 * @param tienda
	 *            Integer
	 */
	public void setTienda(Integer tienda) {
		this.tienda = tienda;
	}

	/**
	 * Method getUsuario.
	 * 
	 * @return String
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * Method setUsuario.
	 * 
	 * @param usuario
	 *            String
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
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
	 * Method getOrdendeventaarticuloList.
	 * 
	 * @return List<Ordendeventaarticulo>
	 */
	@XmlTransient
	public List<Ordendeventaarticulo> getOrdendeventaarticuloList() {
		return ordendeventaarticuloList;
	}

	/**
	 * Method setOrdendeventaarticuloList.
	 * 
	 * @param ordendeventaarticuloList
	 *            List<Ordendeventaarticulo>
	 */
	public void setOrdendeventaarticuloList(
			List<Ordendeventaarticulo> ordendeventaarticuloList) {
		this.ordendeventaarticuloList = ordendeventaarticuloList;
	}

	/**
	 * Method getAbonoList.
	 * 
	 * @return List<Abono>
	 */
	@XmlTransient
	public List<Abono> getAbonoList() {
		return abonoList;
	}

	/**
	 * Method setAbonoList.
	 * 
	 * @param abonoList
	 *            List<Abono>
	 */
	public void setAbonoList(List<Abono> abonoList) {
		this.abonoList = abonoList;
	}

	/**
	 * Method getOrdendeventatransaccionList.
	 * 
	 * @return List<Ordendeventatransaccion>
	 */
	@XmlTransient
	public List<Ordendeventatransaccion> getOrdendeventatransaccionList() {
		return ordendeventatransaccionList;
	}

	/**
	 * Method setOrdendeventatransaccionList.
	 * 
	 * @param ordendeventatransaccionList
	 *            List<Ordendeventatransaccion>
	 */
	public void setOrdendeventatransaccionList(
			List<Ordendeventatransaccion> ordendeventatransaccionList) {
		this.ordendeventatransaccionList = ordendeventatransaccionList;
	}

	/**
	 * Method getNumeroIdentificacionCliente.
	 * 
	 * @return Cliente
	 */
	public Cliente getNumeroIdentificacionCliente() {
		return numeroIdentificacionCliente;
	}

	/**
	 * Method setNumeroIdentificacionCliente.
	 * 
	 * @param numeroIdentificacionCliente
	 *            Cliente
	 */
	public void setNumeroIdentificacionCliente(
			Cliente numeroIdentificacionCliente) {
		this.numeroIdentificacionCliente = numeroIdentificacionCliente;
	}

	/**
	 * Method getIdTipoordendeventa.
	 * 
	 * @return Tipoordendeventa
	 */
	public Tipoordendeventa getIdTipoordendeventa() {
		return idTipoordendeventa;
	}

	/**
	 * Method setIdTipoordendeventa.
	 * 
	 * @param idTipoordendeventa
	 *            Tipoordendeventa
	 */
	public void setIdTipoordendeventa(Tipoordendeventa idTipoordendeventa) {
		this.idTipoordendeventa = idTipoordendeventa;
	}

	/**
	 * Method getIdOrdenDeCompra.
	 * 
	 * @return Long
	 */
	public Long getIdOrdenDeCompra() {
		return idOrdenDeCompra;
	}

	/**
	 * Method setIdOrdenDeCompra.
	 * 
	 * @param idOrdenDeCompra
	 *            Long
	 */
	public void setIdOrdenDeCompra(Long idOrdenDeCompra) {
		this.idOrdenDeCompra = idOrdenDeCompra;
	}

	/**
	 * Method getDiasCredito.
	 * 
	 * @return Integer
	 */
	public Integer getDiasCredito() {
		return diasCredito;
	}

	/**
	 * Method setDiasCredito.
	 * 
	 * @param diasCredito
	 *            Integer
	 */
	public void setDiasCredito(Integer diasCredito) {
		this.diasCredito = diasCredito;
	}

	/**
	 * Method getMontoPenalizacion.
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal getMontoPenalizacion() {
		return montoPenalizacion;
	}

	/**
	 * Method setMontoPenalizacion.
	 * 
	 * @param montoPenalizacion
	 *            BigDecimal
	 */
	public void setMontoPenalizacion(BigDecimal montoPenalizacion) {
		this.montoPenalizacion = montoPenalizacion;
	}

	/**
	 * Method getFichaResponsableEpa.
	 * 
	 * @return BigInteger
	 */
	public BigInteger getFichaResponsableEpa() {
		return fichaResponsableEpa;
	}

	/**
	 * Method setFichaResponsableEpa.
	 * 
	 * @param fichaResponsableEpa
	 *            BigInteger
	 */
	public void setFichaResponsableEpa(BigInteger fichaResponsableEpa) {
		this.fichaResponsableEpa = fichaResponsableEpa;
	}

	/**
	 * Method getNombreResponsableEpa.
	 * 
	 * @return String
	 */
	public String getNombreResponsableEpa() {
		return nombreResponsableEpa;
	}

	/**
	 * Method setNombreResponsableEpa.
	 * 
	 * @param nombreResponsableEpa
	 *            String
	 */
	public void setNombreResponsableEpa(String nombreResponsableEpa) {
		this.nombreResponsableEpa = nombreResponsableEpa;
	}

	/**
	 * Method getOrdedeventaList.
	 * 
	 * @return List<Ordendeventa>
	 */
	public List<Ordendeventa> getOrdedeventaList() {
		return ordedeventaList;
	}

	/**
	 * Method setOrdedeventaList.
	 * 
	 * @param ordedeventaList
	 *            List<Ordendeventa>
	 */
	public void setOrdedeventaList(List<Ordendeventa> ordedeventaList) {
		this.ordedeventaList = ordedeventaList;
	}

	/**
	 * Method getIdOrdenDeVentaPadre.
	 * 
	 * @return Ordendeventa
	 */
	public Ordendeventa getIdOrdenDeVentaPadre() {
		return idOrdenDeVentaPadre;
	}

	/**
	 * Method setIdOrdenDeVentaPadre.
	 * 
	 * @param idOrdenDeVentaPadre
	 *            Ordendeventa
	 */
	public void setIdOrdenDeVentaPadre(Ordendeventa idOrdenDeVentaPadre) {
		this.idOrdenDeVentaPadre = idOrdenDeVentaPadre;
	}

	/**
	 * Method getMontoPrimerAbono.
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal getMontoPrimerAbono() {
		return montoPrimerAbono;
	}

	/**
	 * Method getArticulosEnTienda.
	 * 
	 * @return char
	 */
	public char getArticulosEnTienda() {
		return articulosEnTienda;
	}

	/**
	 * Method setMontoPrimerAbono.
	 * 
	 * @param montoPrimerAbono
	 *            BigDecimal
	 */
	public void setMontoPrimerAbono(BigDecimal montoPrimerAbono) {
		this.montoPrimerAbono = montoPrimerAbono;
	}

	/**
	 * Method setArticulosEnTienda.
	 * 
	 * @param articulosEnTienda
	 *            char
	 */
	public void setArticulosEnTienda(char articulosEnTienda) {
		this.articulosEnTienda = articulosEnTienda;
	}

	/**
	 * 
	 * @return the vigencia
	 */
	public int getVigencia() {
		return vigencia;
	}

	/**
	 * @param vigencia
	 *            the vigencia to set
	 */
	public void setVigencia(int vigencia) {
		this.vigencia = vigencia;
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
		if (!(object instanceof Ordendeventa)) {
			return false;
		}
		Ordendeventa other = (Ordendeventa) object;
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
		return "crjpa400.Ordendeventa[ id=" + id + " ]";
	}

}
