/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crjpa;

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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author eve0014321
 * @version $Revision: 1.0 $
 */
@Entity
@Table(name = "reportez")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Reportez.findAll", query = "SELECT r FROM Reportez r"),
		@NamedQuery(name = "Reportez.findById", query = "SELECT r FROM Reportez r WHERE r.id = :id"),
		@NamedQuery(name = "Reportez.findByTienda", query = "SELECT r FROM Reportez r WHERE r.tienda = :tienda"),
		@NamedQuery(name = "Reportez.findByFecha", query = "SELECT r FROM Reportez r WHERE r.fecha = :fecha"),
		@NamedQuery(name = "Reportez.findByNumeroReporte", query = "SELECT r FROM Reportez r WHERE r.numeroReporte = :numeroReporte"),
		@NamedQuery(name = "Reportez.findBySerialImpresora", query = "SELECT r FROM Reportez r WHERE r.serialImpresora = :serialImpresora"),
		@NamedQuery(name = "Reportez.findByFechaCreacion", query = "SELECT r FROM Reportez r WHERE r.fechaCreacion = :fechaCreacion"),
		@NamedQuery(name = "Reportez.findByHoraCreacion", query = "SELECT r FROM Reportez r WHERE r.horaCreacion = :horaCreacion"),
		@NamedQuery(name = "Reportez.findByFacturasEmitidas", query = "SELECT r FROM Reportez r WHERE r.facturasEmitidas = :facturasEmitidas"),
		@NamedQuery(name = "Reportez.findByUltimaFacturaEmitida", query = "SELECT r FROM Reportez r WHERE r.ultimaFacturaEmitida = :ultimaFacturaEmitida"),
		@NamedQuery(name = "Reportez.findByFechaUltimaFacturaEmitida", query = "SELECT r FROM Reportez r WHERE r.fechaUltimaFacturaEmitida = :fechaUltimaFacturaEmitida"),
		@NamedQuery(name = "Reportez.findByHoraUltimaFacturaEmitida", query = "SELECT r FROM Reportez r WHERE r.horaUltimaFacturaEmitida = :horaUltimaFacturaEmitida"),
		@NamedQuery(name = "Reportez.findByNotaCreditoEmitida", query = "SELECT r FROM Reportez r WHERE r.notaCreditoEmitida = :notaCreditoEmitida"),
		@NamedQuery(name = "Reportez.findByUltimaNotaCreditoEmitida", query = "SELECT r FROM Reportez r WHERE r.ultimaNotaCreditoEmitida = :ultimaNotaCreditoEmitida"),
		@NamedQuery(name = "Reportez.findByFechaUltimaNotaCreditoEmitida", query = "SELECT r FROM Reportez r WHERE r.fechaUltimaNotaCreditoEmitida = :fechaUltimaNotaCreditoEmitida"),
		@NamedQuery(name = "Reportez.findByHoraUltimaNotaCreditoEmitida", query = "SELECT r FROM Reportez r WHERE r.horaUltimaNotaCreditoEmitida = :horaUltimaNotaCreditoEmitida"),
		@NamedQuery(name = "Reportez.findByMontoExcentoFactura", query = "SELECT r FROM Reportez r WHERE r.montoExcentoFactura = :montoExcentoFactura"),
		@NamedQuery(name = "Reportez.findByBaseImponibleGFacturas", query = "SELECT r FROM Reportez r WHERE r.baseImponibleGFacturas = :baseImponibleGFacturas"),
		@NamedQuery(name = "Reportez.findByBaseImponibleRFacturas", query = "SELECT r FROM Reportez r WHERE r.baseImponibleRFacturas = :baseImponibleRFacturas"),
		@NamedQuery(name = "Reportez.findByBaseImponibleAFacturas", query = "SELECT r FROM Reportez r WHERE r.baseImponibleAFacturas = :baseImponibleAFacturas"),
		@NamedQuery(name = "Reportez.findByImpuestoGFacturas", query = "SELECT r FROM Reportez r WHERE r.impuestoGFacturas = :impuestoGFacturas"),
		@NamedQuery(name = "Reportez.findByPorcentajeGFacturas", query = "SELECT r FROM Reportez r WHERE r.porcentajeGFacturas = :porcentajeGFacturas"),
		@NamedQuery(name = "Reportez.findByImpuestoRFacturas", query = "SELECT r FROM Reportez r WHERE r.impuestoRFacturas = :impuestoRFacturas"),
		@NamedQuery(name = "Reportez.findByPorcentajeRFacturas", query = "SELECT r FROM Reportez r WHERE r.porcentajeRFacturas = :porcentajeRFacturas"),
		@NamedQuery(name = "Reportez.findByImpuestoAFacturas", query = "SELECT r FROM Reportez r WHERE r.impuestoAFacturas = :impuestoAFacturas"),
		@NamedQuery(name = "Reportez.findByPorcentajeAFacturas", query = "SELECT r FROM Reportez r WHERE r.porcentajeAFacturas = :porcentajeAFacturas"),
		@NamedQuery(name = "Reportez.findByMontoExcentoNc", query = "SELECT r FROM Reportez r WHERE r.montoExcentoNc = :montoExcentoNc"),
		@NamedQuery(name = "Reportez.findByBaseImponibleGNc", query = "SELECT r FROM Reportez r WHERE r.baseImponibleGNc = :baseImponibleGNc"),
		@NamedQuery(name = "Reportez.findByImpuestoGNc", query = "SELECT r FROM Reportez r WHERE r.impuestoGNc = :impuestoGNc"),
		@NamedQuery(name = "Reportez.findByPorcentajeGNc", query = "SELECT r FROM Reportez r WHERE r.porcentajeGNc = :porcentajeGNc"),
		@NamedQuery(name = "Reportez.findByBaseImponibleRNc", query = "SELECT r FROM Reportez r WHERE r.baseImponibleRNc = :baseImponibleRNc"),
		@NamedQuery(name = "Reportez.findByImpuestoRNc", query = "SELECT r FROM Reportez r WHERE r.impuestoRNc = :impuestoRNc"),
		@NamedQuery(name = "Reportez.findByPorcentajeRNc", query = "SELECT r FROM Reportez r WHERE r.porcentajeRNc = :porcentajeRNc"),
		@NamedQuery(name = "Reportez.findByBaseImponibleANc", query = "SELECT r FROM Reportez r WHERE r.baseImponibleANc = :baseImponibleANc"),
		@NamedQuery(name = "Reportez.findByImpuestoANc", query = "SELECT r FROM Reportez r WHERE r.impuestoANc = :impuestoANc"),
		@NamedQuery(name = "Reportez.findByPorcentajeANc", query = "SELECT r FROM Reportez r WHERE r.porcentajeANc = :porcentajeANc"),
		@NamedQuery(name = "Reportez.findByEstasincronizado", query = "SELECT r FROM Reportez r WHERE r.estasincronizado = :estasincronizado"),
		@NamedQuery(name = "Reportez.findByTipo", query = "SELECT r FROM Reportez r WHERE r.tipo = :tipo") })
public class Reportez implements Serializable {
	/**
	 * Field serialVersionUID. (value is 1)
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Field id.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "2")
	@TableGenerator(name = "2", table = "correlativo", pkColumnName = "id", valueColumnName = "valor", pkColumnValue = "2", allocationSize = 1)
	@Basic(optional = false)
	@Column(name = "id", nullable = false)
	private Long id;
	/**
	 * Field tienda.
	 */
	@Basic(optional = false)
	@Column(name = "tienda", nullable = false)
	private int tienda;
	/**
	 * Field fecha.
	 */
	@Basic(optional = false)
	@Column(name = "fecha", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date fecha;
	/**
	 * Field numeroReporte.
	 */
	@Basic(optional = false)
	@Column(name = "numero_reporte", nullable = false)
	private int numeroReporte;
	/**
	 * Field serialImpresora.
	 */
	@Basic(optional = false)
	@Column(name = "serial_impresora", nullable = false, length = 12)
	private String serialImpresora;
	/**
	 * Field fechaCreacion.
	 */
	@Basic(optional = false)
	@Column(name = "fecha_creacion", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date fechaCreacion;
	/**
	 * Field horaCreacion.
	 */
	@Basic(optional = false)
	@Column(name = "hora_creacion", nullable = false)
	@Temporal(TemporalType.TIME)
	private Date horaCreacion;
	/**
	 * Field facturasEmitidas.
	 */
	@Basic(optional = false)
	@Column(name = "facturas_emitidas", nullable = false)
	private int facturasEmitidas;
	/**
	 * Field ultimaFacturaEmitida.
	 */
	@Basic(optional = false)
	@Column(name = "ultima_factura_emitida", nullable = false)
	private int ultimaFacturaEmitida;
	/**
	 * Field fechaUltimaFacturaEmitida.
	 */
	@Basic(optional = false)
	@Column(name = "fecha_ultima_factura_emitida", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date fechaUltimaFacturaEmitida;
	/**
	 * Field horaUltimaFacturaEmitida.
	 */
	@Basic(optional = false)
	@Column(name = "hora_ultima_factura_emitida", nullable = false)
	@Temporal(TemporalType.TIME)
	private Date horaUltimaFacturaEmitida;
	/**
	 * Field notaCreditoEmitida.
	 */
	@Basic(optional = false)
	@Column(name = "nota_credito_emitida", nullable = false)
	private int notaCreditoEmitida;
	/**
	 * Field ultimaNotaCreditoEmitida.
	 */
	@Basic(optional = false)
	@Column(name = "ultima_nota_credito_emitida", nullable = false)
	private int ultimaNotaCreditoEmitida;
	/**
	 * Field fechaUltimaNotaCreditoEmitida.
	 */
	@Basic(optional = false)
	@Column(name = "fecha_ultima_nota_credito_emitida", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date fechaUltimaNotaCreditoEmitida;
	/**
	 * Field horaUltimaNotaCreditoEmitida.
	 */
	@Basic(optional = false)
	@Column(name = "hora_ultima_nota_credito_emitida", nullable = false)
	@Temporal(TemporalType.TIME)
	private Date horaUltimaNotaCreditoEmitida;
	// @Max(value=?) @Min(value=?)//if you know range of your decimal fields
	// consider using these annotations to enforce field validation
	/**
	 * Field montoExcentoFactura.
	 */
	@Basic(optional = false)
	@Column(name = "monto_excento_factura", nullable = false, precision = 9, scale = 2)
	private BigDecimal montoExcentoFactura;
	/**
	 * Field baseImponibleGFacturas.
	 */
	@Basic(optional = false)
	@Column(name = "base_imponible_g_facturas", nullable = false, precision = 9, scale = 2)
	private BigDecimal baseImponibleGFacturas;
	/**
	 * Field baseImponibleRFacturas.
	 */
	@Column(name = "base_imponible_r_facturas", precision = 9, scale = 2)
	private BigDecimal baseImponibleRFacturas;
	/**
	 * Field baseImponibleAFacturas.
	 */
	@Basic(optional = false)
	@Column(name = "base_imponible_a_facturas", nullable = false, precision = 9, scale = 2)
	private BigDecimal baseImponibleAFacturas;
	/**
	 * Field impuestoGFacturas.
	 */
	@Basic(optional = false)
	@Column(name = "impuesto_g_facturas", nullable = false, precision = 7, scale = 2)
	private BigDecimal impuestoGFacturas;
	/**
	 * Field porcentajeGFacturas.
	 */
	@Basic(optional = false)
	@Column(name = "porcentaje_g_facturas", nullable = false, precision = 7, scale = 2)
	private BigDecimal porcentajeGFacturas;
	/**
	 * Field impuestoRFacturas.
	 */
	@Basic(optional = false)
	@Column(name = "impuesto_r_facturas", nullable = false, precision = 7, scale = 2)
	private BigDecimal impuestoRFacturas;
	/**
	 * Field porcentajeRFacturas.
	 */
	@Basic(optional = false)
	@Column(name = "porcentaje_r_facturas", nullable = false, precision = 7, scale = 2)
	private BigDecimal porcentajeRFacturas;
	/**
	 * Field impuestoAFacturas.
	 */
	@Basic(optional = false)
	@Column(name = "impuesto_a_facturas", nullable = false, precision = 7, scale = 2)
	private BigDecimal impuestoAFacturas;
	/**
	 * Field porcentajeAFacturas.
	 */
	@Basic(optional = false)
	@Column(name = "porcentaje_a_facturas", nullable = false, precision = 7, scale = 2)
	private BigDecimal porcentajeAFacturas;
	/**
	 * Field montoExcentoNc.
	 */
	@Basic(optional = false)
	@Column(name = "monto_excento_nc", nullable = false, precision = 9, scale = 2)
	private BigDecimal montoExcentoNc;
	/**
	 * Field baseImponibleGNc.
	 */
	@Basic(optional = false)
	@Column(name = "base_imponible_g_nc", nullable = false, precision = 9, scale = 2)
	private BigDecimal baseImponibleGNc;
	/**
	 * Field impuestoGNc.
	 */
	@Basic(optional = false)
	@Column(name = "impuesto_g_nc", nullable = false, precision = 7, scale = 2)
	private BigDecimal impuestoGNc;
	/**
	 * Field porcentajeGNc.
	 */
	@Basic(optional = false)
	@Column(name = "porcentaje_g_nc", nullable = false, precision = 7, scale = 2)
	private BigDecimal porcentajeGNc;
	/**
	 * Field baseImponibleRNc.
	 */
	@Basic(optional = false)
	@Column(name = "base_imponible_r_nc", nullable = false, precision = 9, scale = 2)
	private BigDecimal baseImponibleRNc;
	/**
	 * Field impuestoRNc.
	 */
	@Basic(optional = false)
	@Column(name = "impuesto_r_nc", nullable = false, precision = 7, scale = 2)
	private BigDecimal impuestoRNc;
	/**
	 * Field porcentajeRNc.
	 */
	@Basic(optional = false)
	@Column(name = "porcentaje_r_nc", nullable = false, precision = 7, scale = 2)
	private BigDecimal porcentajeRNc;
	/**
	 * Field baseImponibleANc.
	 */
	@Basic(optional = false)
	@Column(name = "base_imponible_a_nc", nullable = false, precision = 9, scale = 2)
	private BigDecimal baseImponibleANc;
	/**
	 * Field impuestoANc.
	 */
	@Basic(optional = false)
	@Column(name = "impuesto_a_nc", nullable = false, precision = 7, scale = 2)
	private BigDecimal impuestoANc;
	/**
	 * Field porcentajeANc.
	 */
	@Basic(optional = false)
	@Column(name = "porcentaje_a_nc", nullable = false, precision = 7, scale = 2)
	private BigDecimal porcentajeANc;
	/**
	 * Field estasincronizado.
	 */
	@Basic(optional = false)
	@Column(name = "estasincronizado", nullable = false, length = 2)
	private String estasincronizado;
	/**
	 * Field tipo.
	 */
	@Basic(optional = false)
	@Column(name = "tipo", nullable = false, length = 8)
	private String tipo;
	/**
	 * Field electronic_journal.
	 */
	@Lob
	@Column(name = "electronic_journal")
	private byte[] electronic_journal;
	/**
	 * Field idSesion.
	 */
	@JoinColumn(name = "id_sesion", referencedColumnName = "id", nullable = false)
	@ManyToOne(optional = false)
	private Sesion idSesion;

	/**
	 * Constructor for Reportez.
	 */
	public Reportez() {
	}

	/**
	 * Constructor for Reportez.
	 * 
	 * @param id
	 *            Long
	 */
	public Reportez(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Reportez.
	 * 
	 * @param id
	 *            Long
	 * @param tienda
	 *            int
	 * @param fecha
	 *            Date
	 * @param numeroReporte
	 *            int
	 * @param serialImpresora
	 *            String
	 * @param fechaCreacion
	 *            Date
	 * @param horaCreacion
	 *            Date
	 * @param facturasEmitidas
	 *            int
	 * @param ultimaFacturaEmitida
	 *            int
	 * @param fechaUltimaFacturaEmitida
	 *            Date
	 * @param horaUltimaFacturaEmitida
	 *            Date
	 * @param notaCreditoEmitida
	 *            int
	 * @param ultimaNotaCreditoEmitida
	 *            int
	 * @param fechaUltimaNotaCreditoEmitida
	 *            Date
	 * @param horaUltimaNotaCreditoEmitida
	 *            Date
	 * @param montoExcentoFactura
	 *            BigDecimal
	 * @param baseImponibleGFacturas
	 *            BigDecimal
	 * @param baseImponibleAFacturas
	 *            BigDecimal
	 * @param impuestoGFacturas
	 *            BigDecimal
	 * @param porcentajeGFacturas
	 *            BigDecimal
	 * @param impuestoRFacturas
	 *            BigDecimal
	 * @param porcentajeRFacturas
	 *            BigDecimal
	 * @param impuestoAFacturas
	 *            BigDecimal
	 * @param porcentajeAFacturas
	 *            BigDecimal
	 * @param montoExcentoNc
	 *            BigDecimal
	 * @param baseImponibleGNc
	 *            BigDecimal
	 * @param impuestoGNc
	 *            BigDecimal
	 * @param porcentajeGNc
	 *            BigDecimal
	 * @param baseImponibleRNc
	 *            BigDecimal
	 * @param impuestoRNc
	 *            BigDecimal
	 * @param porcentajeRNc
	 *            BigDecimal
	 * @param baseImponibleANc
	 *            BigDecimal
	 * @param impuestoANc
	 *            BigDecimal
	 * @param porcentajeANc
	 *            BigDecimal
	 * @param estasincronizado
	 *            String
	 * @param tipo
	 *            String
	 */
	public Reportez(Long id, int tienda, Date fecha, int numeroReporte, String serialImpresora, Date fechaCreacion,
			Date horaCreacion, int facturasEmitidas, int ultimaFacturaEmitida, Date fechaUltimaFacturaEmitida,
			Date horaUltimaFacturaEmitida, int notaCreditoEmitida, int ultimaNotaCreditoEmitida,
			Date fechaUltimaNotaCreditoEmitida, Date horaUltimaNotaCreditoEmitida, BigDecimal montoExcentoFactura,
			BigDecimal baseImponibleGFacturas, BigDecimal baseImponibleAFacturas, BigDecimal impuestoGFacturas,
			BigDecimal porcentajeGFacturas, BigDecimal impuestoRFacturas, BigDecimal porcentajeRFacturas,
			BigDecimal impuestoAFacturas, BigDecimal porcentajeAFacturas, BigDecimal montoExcentoNc,
			BigDecimal baseImponibleGNc, BigDecimal impuestoGNc, BigDecimal porcentajeGNc, BigDecimal baseImponibleRNc,
			BigDecimal impuestoRNc, BigDecimal porcentajeRNc, BigDecimal baseImponibleANc, BigDecimal impuestoANc,
			BigDecimal porcentajeANc, String estasincronizado, String tipo) {
		this.id = id;
		this.tienda = tienda;
		this.fecha = fecha;
		this.numeroReporte = numeroReporte;
		this.serialImpresora = serialImpresora;
		this.fechaCreacion = fechaCreacion;
		this.horaCreacion = horaCreacion;
		this.facturasEmitidas = facturasEmitidas;
		this.ultimaFacturaEmitida = ultimaFacturaEmitida;
		this.fechaUltimaFacturaEmitida = fechaUltimaFacturaEmitida;
		this.horaUltimaFacturaEmitida = horaUltimaFacturaEmitida;
		this.notaCreditoEmitida = notaCreditoEmitida;
		this.ultimaNotaCreditoEmitida = ultimaNotaCreditoEmitida;
		this.fechaUltimaNotaCreditoEmitida = fechaUltimaNotaCreditoEmitida;
		this.horaUltimaNotaCreditoEmitida = horaUltimaNotaCreditoEmitida;
		this.montoExcentoFactura = montoExcentoFactura;
		this.baseImponibleGFacturas = baseImponibleGFacturas;
		this.baseImponibleAFacturas = baseImponibleAFacturas;
		this.impuestoGFacturas = impuestoGFacturas;
		this.porcentajeGFacturas = porcentajeGFacturas;
		this.impuestoRFacturas = impuestoRFacturas;
		this.porcentajeRFacturas = porcentajeRFacturas;
		this.impuestoAFacturas = impuestoAFacturas;
		this.porcentajeAFacturas = porcentajeAFacturas;
		this.montoExcentoNc = montoExcentoNc;
		this.baseImponibleGNc = baseImponibleGNc;
		this.impuestoGNc = impuestoGNc;
		this.porcentajeGNc = porcentajeGNc;
		this.baseImponibleRNc = baseImponibleRNc;
		this.impuestoRNc = impuestoRNc;
		this.porcentajeRNc = porcentajeRNc;
		this.baseImponibleANc = baseImponibleANc;
		this.impuestoANc = impuestoANc;
		this.porcentajeANc = porcentajeANc;
		this.estasincronizado = estasincronizado;
		this.tipo = tipo;
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
	 * Method getNumeroReporte.
	 * 
	 * @return int
	 */
	public int getNumeroReporte() {
		return numeroReporte;
	}

	/**
	 * Method setNumeroReporte.
	 * 
	 * @param numeroReporte
	 *            int
	 */
	public void setNumeroReporte(int numeroReporte) {
		this.numeroReporte = numeroReporte;
	}

	/**
	 * Method getSerialImpresora.
	 * 
	 * @return String
	 */
	public String getSerialImpresora() {
		return serialImpresora;
	}

	/**
	 * Method setSerialImpresora.
	 * 
	 * @param serialImpresora
	 *            String
	 */
	public void setSerialImpresora(String serialImpresora) {
		this.serialImpresora = serialImpresora;
	}

	/**
	 * Method getFechaCreacion.
	 * 
	 * @return Date
	 */
	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	/**
	 * Method setFechaCreacion.
	 * 
	 * @param fechaCreacion
	 *            Date
	 */
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	/**
	 * Method getHoraCreacion.
	 * 
	 * @return Date
	 */
	public Date getHoraCreacion() {
		return horaCreacion;
	}

	/**
	 * Method setHoraCreacion.
	 * 
	 * @param horaCreacion
	 *            Date
	 */
	public void setHoraCreacion(Date horaCreacion) {
		this.horaCreacion = horaCreacion;
	}

	/**
	 * Method getFacturasEmitidas.
	 * 
	 * @return int
	 */
	public int getFacturasEmitidas() {
		return facturasEmitidas;
	}

	/**
	 * Method setFacturasEmitidas.
	 * 
	 * @param facturasEmitidas
	 *            int
	 */
	public void setFacturasEmitidas(int facturasEmitidas) {
		this.facturasEmitidas = facturasEmitidas;
	}

	/**
	 * Method getUltimaFacturaEmitida.
	 * 
	 * @return int
	 */
	public int getUltimaFacturaEmitida() {
		return ultimaFacturaEmitida;
	}

	/**
	 * Method setUltimaFacturaEmitida.
	 * 
	 * @param ultimaFacturaEmitida
	 *            int
	 */
	public void setUltimaFacturaEmitida(int ultimaFacturaEmitida) {
		this.ultimaFacturaEmitida = ultimaFacturaEmitida;
	}

	/**
	 * Method getFechaUltimaFacturaEmitida.
	 * 
	 * @return Date
	 */
	public Date getFechaUltimaFacturaEmitida() {
		return fechaUltimaFacturaEmitida;
	}

	/**
	 * Method setFechaUltimaFacturaEmitida.
	 * 
	 * @param fechaUltimaFacturaEmitida
	 *            Date
	 */
	public void setFechaUltimaFacturaEmitida(Date fechaUltimaFacturaEmitida) {
		this.fechaUltimaFacturaEmitida = fechaUltimaFacturaEmitida;
	}

	/**
	 * Method getHoraUltimaFacturaEmitida.
	 * 
	 * @return Date
	 */
	public Date getHoraUltimaFacturaEmitida() {
		return horaUltimaFacturaEmitida;
	}

	/**
	 * Method setHoraUltimaFacturaEmitida.
	 * 
	 * @param horaUltimaFacturaEmitida
	 *            Date
	 */
	public void setHoraUltimaFacturaEmitida(Date horaUltimaFacturaEmitida) {
		this.horaUltimaFacturaEmitida = horaUltimaFacturaEmitida;
	}

	/**
	 * Method getNotaCreditoEmitida.
	 * 
	 * @return int
	 */
	public int getNotaCreditoEmitida() {
		return notaCreditoEmitida;
	}

	/**
	 * Method setNotaCreditoEmitida.
	 * 
	 * @param notaCreditoEmitida
	 *            int
	 */
	public void setNotaCreditoEmitida(int notaCreditoEmitida) {
		this.notaCreditoEmitida = notaCreditoEmitida;
	}

	/**
	 * Method getUltimaNotaCreditoEmitida.
	 * 
	 * @return int
	 */
	public int getUltimaNotaCreditoEmitida() {
		return ultimaNotaCreditoEmitida;
	}

	/**
	 * Method setUltimaNotaCreditoEmitida.
	 * 
	 * @param ultimaNotaCreditoEmitida
	 *            int
	 */
	public void setUltimaNotaCreditoEmitida(int ultimaNotaCreditoEmitida) {
		this.ultimaNotaCreditoEmitida = ultimaNotaCreditoEmitida;
	}

	/**
	 * Method getFechaUltimaNotaCreditoEmitida.
	 * 
	 * @return Date
	 */
	public Date getFechaUltimaNotaCreditoEmitida() {
		return fechaUltimaNotaCreditoEmitida;
	}

	/**
	 * Method setFechaUltimaNotaCreditoEmitida.
	 * 
	 * @param fechaUltimaNotaCreditoEmitida
	 *            Date
	 */
	public void setFechaUltimaNotaCreditoEmitida(Date fechaUltimaNotaCreditoEmitida) {
		this.fechaUltimaNotaCreditoEmitida = fechaUltimaNotaCreditoEmitida;
	}

	/**
	 * Method getHoraUltimaNotaCreditoEmitida.
	 * 
	 * @return Date
	 */
	public Date getHoraUltimaNotaCreditoEmitida() {
		return horaUltimaNotaCreditoEmitida;
	}

	/**
	 * Method setHoraUltimaNotaCreditoEmitida.
	 * 
	 * @param horaUltimaNotaCreditoEmitida
	 *            Date
	 */
	public void setHoraUltimaNotaCreditoEmitida(Date horaUltimaNotaCreditoEmitida) {
		this.horaUltimaNotaCreditoEmitida = horaUltimaNotaCreditoEmitida;
	}

	/**
	 * Method getMontoExcentoFactura.
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal getMontoExcentoFactura() {
		return montoExcentoFactura;
	}

	/**
	 * Method setMontoExcentoFactura.
	 * 
	 * @param montoExcentoFactura
	 *            BigDecimal
	 */
	public void setMontoExcentoFactura(BigDecimal montoExcentoFactura) {
		this.montoExcentoFactura = montoExcentoFactura;
	}

	/**
	 * Method getBaseImponibleGFacturas.
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal getBaseImponibleGFacturas() {
		return baseImponibleGFacturas;
	}

	/**
	 * Method setBaseImponibleGFacturas.
	 * 
	 * @param baseImponibleGFacturas
	 *            BigDecimal
	 */
	public void setBaseImponibleGFacturas(BigDecimal baseImponibleGFacturas) {
		this.baseImponibleGFacturas = baseImponibleGFacturas;
	}

	/**
	 * Method getBaseImponibleRFacturas.
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal getBaseImponibleRFacturas() {
		return baseImponibleRFacturas;
	}

	/**
	 * Method setBaseImponibleRFacturas.
	 * 
	 * @param baseImponibleRFacturas
	 *            BigDecimal
	 */
	public void setBaseImponibleRFacturas(BigDecimal baseImponibleRFacturas) {
		this.baseImponibleRFacturas = baseImponibleRFacturas;
	}

	/**
	 * Method getBaseImponibleAFacturas.
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal getBaseImponibleAFacturas() {
		return baseImponibleAFacturas;
	}

	/**
	 * Method setBaseImponibleAFacturas.
	 * 
	 * @param baseImponibleAFacturas
	 *            BigDecimal
	 */
	public void setBaseImponibleAFacturas(BigDecimal baseImponibleAFacturas) {
		this.baseImponibleAFacturas = baseImponibleAFacturas;
	}

	/**
	 * Method getImpuestoGFacturas.
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal getImpuestoGFacturas() {
		return impuestoGFacturas;
	}

	/**
	 * Method setImpuestoGFacturas.
	 * 
	 * @param impuestoGFacturas
	 *            BigDecimal
	 */
	public void setImpuestoGFacturas(BigDecimal impuestoGFacturas) {
		this.impuestoGFacturas = impuestoGFacturas;
	}

	/**
	 * Method getPorcentajeGFacturas.
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal getPorcentajeGFacturas() {
		return porcentajeGFacturas;
	}

	/**
	 * Method setPorcentajeGFacturas.
	 * 
	 * @param porcentajeGFacturas
	 *            BigDecimal
	 */
	public void setPorcentajeGFacturas(BigDecimal porcentajeGFacturas) {
		this.porcentajeGFacturas = porcentajeGFacturas;
	}

	/**
	 * Method getImpuestoRFacturas.
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal getImpuestoRFacturas() {
		return impuestoRFacturas;
	}

	/**
	 * Method setImpuestoRFacturas.
	 * 
	 * @param impuestoRFacturas
	 *            BigDecimal
	 */
	public void setImpuestoRFacturas(BigDecimal impuestoRFacturas) {
		this.impuestoRFacturas = impuestoRFacturas;
	}

	/**
	 * Method getPorcentajeRFacturas.
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal getPorcentajeRFacturas() {
		return porcentajeRFacturas;
	}

	/**
	 * Method setPorcentajeRFacturas.
	 * 
	 * @param porcentajeRFacturas
	 *            BigDecimal
	 */
	public void setPorcentajeRFacturas(BigDecimal porcentajeRFacturas) {
		this.porcentajeRFacturas = porcentajeRFacturas;
	}

	/**
	 * Method getImpuestoAFacturas.
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal getImpuestoAFacturas() {
		return impuestoAFacturas;
	}

	/**
	 * Method setImpuestoAFacturas.
	 * 
	 * @param impuestoAFacturas
	 *            BigDecimal
	 */
	public void setImpuestoAFacturas(BigDecimal impuestoAFacturas) {
		this.impuestoAFacturas = impuestoAFacturas;
	}

	/**
	 * Method getPorcentajeAFacturas.
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal getPorcentajeAFacturas() {
		return porcentajeAFacturas;
	}

	/**
	 * Method setPorcentajeAFacturas.
	 * 
	 * @param porcentajeAFacturas
	 *            BigDecimal
	 */
	public void setPorcentajeAFacturas(BigDecimal porcentajeAFacturas) {
		this.porcentajeAFacturas = porcentajeAFacturas;
	}

	/**
	 * Method getMontoExcentoNc.
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal getMontoExcentoNc() {
		return montoExcentoNc;
	}

	/**
	 * Method setMontoExcentoNc.
	 * 
	 * @param montoExcentoNc
	 *            BigDecimal
	 */
	public void setMontoExcentoNc(BigDecimal montoExcentoNc) {
		this.montoExcentoNc = montoExcentoNc;
	}

	/**
	 * Method getBaseImponibleGNc.
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal getBaseImponibleGNc() {
		return baseImponibleGNc;
	}

	/**
	 * Method setBaseImponibleGNc.
	 * 
	 * @param baseImponibleGNc
	 *            BigDecimal
	 */
	public void setBaseImponibleGNc(BigDecimal baseImponibleGNc) {
		this.baseImponibleGNc = baseImponibleGNc;
	}

	/**
	 * Method getImpuestoGNc.
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal getImpuestoGNc() {
		return impuestoGNc;
	}

	/**
	 * Method setImpuestoGNc.
	 * 
	 * @param impuestoGNc
	 *            BigDecimal
	 */
	public void setImpuestoGNc(BigDecimal impuestoGNc) {
		this.impuestoGNc = impuestoGNc;
	}

	/**
	 * Method getPorcentajeGNc.
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal getPorcentajeGNc() {
		return porcentajeGNc;
	}

	/**
	 * Method setPorcentajeGNc.
	 * 
	 * @param porcentajeGNc
	 *            BigDecimal
	 */
	public void setPorcentajeGNc(BigDecimal porcentajeGNc) {
		this.porcentajeGNc = porcentajeGNc;
	}

	/**
	 * Method getBaseImponibleRNc.
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal getBaseImponibleRNc() {
		return baseImponibleRNc;
	}

	/**
	 * Method setBaseImponibleRNc.
	 * 
	 * @param baseImponibleRNc
	 *            BigDecimal
	 */
	public void setBaseImponibleRNc(BigDecimal baseImponibleRNc) {
		this.baseImponibleRNc = baseImponibleRNc;
	}

	/**
	 * Method getImpuestoRNc.
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal getImpuestoRNc() {
		return impuestoRNc;
	}

	/**
	 * Method setImpuestoRNc.
	 * 
	 * @param impuestoRNc
	 *            BigDecimal
	 */
	public void setImpuestoRNc(BigDecimal impuestoRNc) {
		this.impuestoRNc = impuestoRNc;
	}

	/**
	 * Method getPorcentajeRNc.
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal getPorcentajeRNc() {
		return porcentajeRNc;
	}

	/**
	 * Method setPorcentajeRNc.
	 * 
	 * @param porcentajeRNc
	 *            BigDecimal
	 */
	public void setPorcentajeRNc(BigDecimal porcentajeRNc) {
		this.porcentajeRNc = porcentajeRNc;
	}

	/**
	 * Method getBaseImponibleANc.
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal getBaseImponibleANc() {
		return baseImponibleANc;
	}

	/**
	 * Method setBaseImponibleANc.
	 * 
	 * @param baseImponibleANc
	 *            BigDecimal
	 */
	public void setBaseImponibleANc(BigDecimal baseImponibleANc) {
		this.baseImponibleANc = baseImponibleANc;
	}

	/**
	 * Method getImpuestoANc.
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal getImpuestoANc() {
		return impuestoANc;
	}

	/**
	 * Method setImpuestoANc.
	 * 
	 * @param impuestoANc
	 *            BigDecimal
	 */
	public void setImpuestoANc(BigDecimal impuestoANc) {
		this.impuestoANc = impuestoANc;
	}

	/**
	 * Method getPorcentajeANc.
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal getPorcentajeANc() {
		return porcentajeANc;
	}

	/**
	 * Method setPorcentajeANc.
	 * 
	 * @param porcentajeANc
	 *            BigDecimal
	 */
	public void setPorcentajeANc(BigDecimal porcentajeANc) {
		this.porcentajeANc = porcentajeANc;
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
	 * Method getTipo.
	 * 
	 * @return String
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * Method setTipo.
	 * 
	 * @param tipo
	 *            String
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * Method getIdSesion.
	 * 
	 * @return Sesion
	 */
	public Sesion getIdSesion() {
		return idSesion;
	}

	/**
	 * Method setIdSesion.
	 * 
	 * @param idSesion
	 *            Sesion
	 */
	public void setIdSesion(Sesion idSesion) {
		this.idSesion = idSesion;
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
		if (!(object instanceof Reportez)) {
			return false;
		}
		Reportez other = (Reportez) object;
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
		return "crjpa.Reportez[ id=" + id + " ]";
	}

	/**
	 * Method getElectronic_journal.
	 * 
	 * @return byte[]
	 */
	public byte[] getElectronic_journal() {
		return electronic_journal;
	}

	/**
	 * Method setElectronic_journal.
	 * 
	 * @param electronic_journal
	 *            byte[]
	 */
	public void setElectronic_journal(byte[] electronic_journal) {
		this.electronic_journal = electronic_journal;
	}

}
