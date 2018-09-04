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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
 */
@Entity
@Table(name = "REPORTEZ")
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
		@NamedQuery(name = "Reportez.findByMontoExentoFactura", query = "SELECT r FROM Reportez r WHERE r.montoExentoFactura = :montoExentoFactura"),
		@NamedQuery(name = "Reportez.findByBaseImponibleGFacturas", query = "SELECT r FROM Reportez r WHERE r.baseImponibleGFacturas = :baseImponibleGFacturas"),
		@NamedQuery(name = "Reportez.findByBaseImponibleRFacturas", query = "SELECT r FROM Reportez r WHERE r.baseImponibleRFacturas = :baseImponibleRFacturas"),
		@NamedQuery(name = "Reportez.findByBaseImponibleAFacturas", query = "SELECT r FROM Reportez r WHERE r.baseImponibleAFacturas = :baseImponibleAFacturas"),
		@NamedQuery(name = "Reportez.findByImpuestoGFacturas", query = "SELECT r FROM Reportez r WHERE r.impuestoGFacturas = :impuestoGFacturas"),
		@NamedQuery(name = "Reportez.findByPorcentajeGFacturas", query = "SELECT r FROM Reportez r WHERE r.porcentajeGFacturas = :porcentajeGFacturas"),
		@NamedQuery(name = "Reportez.findByImpuestoRFacturas", query = "SELECT r FROM Reportez r WHERE r.impuestoRFacturas = :impuestoRFacturas"),
		@NamedQuery(name = "Reportez.findByPorcentajeRFacturas", query = "SELECT r FROM Reportez r WHERE r.porcentajeRFacturas = :porcentajeRFacturas"),
		@NamedQuery(name = "Reportez.findByImpuestoAFacturas", query = "SELECT r FROM Reportez r WHERE r.impuestoAFacturas = :impuestoAFacturas"),
		@NamedQuery(name = "Reportez.findByPorcentajeAFacturas", query = "SELECT r FROM Reportez r WHERE r.porcentajeAFacturas = :porcentajeAFacturas"),
		@NamedQuery(name = "Reportez.findByMontoExentoNc", query = "SELECT r FROM Reportez r WHERE r.montoExentoNc = :montoExentoNc"),
		@NamedQuery(name = "Reportez.findByBaseImponibleGNc", query = "SELECT r FROM Reportez r WHERE r.baseImponibleGNc = :baseImponibleGNc"),
		@NamedQuery(name = "Reportez.findByImpuestoGNc", query = "SELECT r FROM Reportez r WHERE r.impuestoGNc = :impuestoGNc"),
		@NamedQuery(name = "Reportez.findByPorcentajeGNc", query = "SELECT r FROM Reportez r WHERE r.porcentajeGNc = :porcentajeGNc"),
		@NamedQuery(name = "Reportez.findByBaseImponibleRNc", query = "SELECT r FROM Reportez r WHERE r.baseImponibleRNc = :baseImponibleRNc"),
		@NamedQuery(name = "Reportez.findByImpuestoRNc", query = "SELECT r FROM Reportez r WHERE r.impuestoRNc = :impuestoRNc"),
		@NamedQuery(name = "Reportez.findByPorcentajeRNc", query = "SELECT r FROM Reportez r WHERE r.porcentajeRNc = :porcentajeRNc"),
		@NamedQuery(name = "Reportez.findByBaseImponibleANc", query = "SELECT r FROM Reportez r WHERE r.baseImponibleANc = :baseImponibleANc"),
		@NamedQuery(name = "Reportez.findByImpuestoANc", query = "SELECT r FROM Reportez r WHERE r.impuestoANc = :impuestoANc"),
		@NamedQuery(name = "Reportez.findByPorcentajeANc", query = "SELECT r FROM Reportez r WHERE r.porcentajeANc = :porcentajeANc"),
		@NamedQuery(name = "Reportez.findByTipo", query = "SELECT r FROM Reportez r WHERE r.tipo = :tipo"),
		@NamedQuery(name = "Reportez.findByEstreplica", query = "SELECT r FROM Reportez r WHERE r.estreplica = :estreplica") })
public class Reportez implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@Column(name = "ID", nullable = false)
	private Long id;
	@Basic(optional = false)
	@Column(name = "TIENDA", nullable = false)
	private int tienda;
	@Basic(optional = false)
	@Column(name = "FECHA", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date fecha;
	@Basic(optional = false)
	@Column(name = "NUMERO_REPORTE", nullable = false)
	private int numeroReporte;
	@Basic(optional = false)
	@Column(name = "SERIAL_IMPRESORA", nullable = false, length = 12)
	private String serialImpresora;
	@Basic(optional = false)
	@Column(name = "FECHA_CREACION", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date fechaCreacion;
	@Basic(optional = false)
	@Column(name = "HORA_CREACION", nullable = false)
	@Temporal(TemporalType.TIME)
	private Date horaCreacion;
	@Basic(optional = false)
	@Column(name = "FACTURAS_EMITIDAS", nullable = false)
	private int facturasEmitidas;
	@Basic(optional = false)
	@Column(name = "ULTIMA_FACTURA_EMITIDA", nullable = false)
	private int ultimaFacturaEmitida;
	@Basic(optional = false)
	@Column(name = "FECHA_ULTIMA_FACTURA_EMITIDA", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date fechaUltimaFacturaEmitida;
	@Basic(optional = false)
	@Column(name = "HORA_ULTIMA_FACTURA_EMITIDA", nullable = false)
	@Temporal(TemporalType.TIME)
	private Date horaUltimaFacturaEmitida;
	@Basic(optional = false)
	@Column(name = "NOTA_CREDITO_EMITIDA", nullable = false)
	private int notaCreditoEmitida;
	@Basic(optional = false)
	@Column(name = "ULTIMA_NOTA_CREDITO_EMITIDA", nullable = false)
	private int ultimaNotaCreditoEmitida;
	@Basic(optional = false)
	@Column(name = "FECHA_ULTIMA_NOTA_CREDITO_EMITIDA", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date fechaUltimaNotaCreditoEmitida;
	@Basic(optional = false)
	@Column(name = "HORA_ULTIMA_NOTA_CREDITO_EMITIDA", nullable = false)
	@Temporal(TemporalType.TIME)
	private Date horaUltimaNotaCreditoEmitida;
	// @Max(value=?) @Min(value=?)//if you know range of your decimal fields
	// consider using these annotations to enforce field validation
	@Basic(optional = false)
	@Column(name = "MONTO_EXENTO_FACTURA", nullable = false, precision = 13, scale = 2)
	private BigDecimal montoExentoFactura;
	@Basic(optional = false)
	@Column(name = "BASE_IMPONIBLE_G_FACTURAS", nullable = false, precision = 13, scale = 2)
	private BigDecimal baseImponibleGFacturas;
	@Basic(optional = false)
	@Column(name = "BASE_IMPONIBLE_R_FACTURAS", nullable = false, precision = 13, scale = 2)
	private BigDecimal baseImponibleRFacturas;
	@Basic(optional = false)
	@Column(name = "BASE_IMPONIBLE_A_FACTURAS", nullable = false, precision = 13, scale = 2)
	private BigDecimal baseImponibleAFacturas;
	@Basic(optional = false)
	@Column(name = "IMPUESTO_G_FACTURAS", nullable = false, precision = 13, scale = 2)
	private BigDecimal impuestoGFacturas;
	@Basic(optional = false)
	@Column(name = "PORCENTAJE_G_FACTURAS", nullable = false, precision = 13, scale = 2)
	private BigDecimal porcentajeGFacturas;
	@Basic(optional = false)
	@Column(name = "IMPUESTO_R_FACTURAS", nullable = false, precision = 13, scale = 2)
	private BigDecimal impuestoRFacturas;
	@Basic(optional = false)
	@Column(name = "PORCENTAJE_R_FACTURAS", nullable = false, precision = 13, scale = 2)
	private BigDecimal porcentajeRFacturas;
	@Basic(optional = false)
	@Column(name = "IMPUESTO_A_FACTURAS", nullable = false, precision = 13, scale = 2)
	private BigDecimal impuestoAFacturas;
	@Basic(optional = false)
	@Column(name = "PORCENTAJE_A_FACTURAS", nullable = false, precision = 13, scale = 2)
	private BigDecimal porcentajeAFacturas;
	@Basic(optional = false)
	@Column(name = "MONTO_EXENTO_NC", nullable = false, precision = 13, scale = 2)
	private BigDecimal montoExentoNc;
	@Basic(optional = false)
	@Column(name = "BASE_IMPONIBLE_G_NC", nullable = false, precision = 13, scale = 2)
	private BigDecimal baseImponibleGNc;
	@Basic(optional = false)
	@Column(name = "IMPUESTO_G_NC", nullable = false, precision = 13, scale = 2)
	private BigDecimal impuestoGNc;
	@Basic(optional = false)
	@Column(name = "PORCENTAJE_G_NC", nullable = false, precision = 13, scale = 2)
	private BigDecimal porcentajeGNc;
	@Basic(optional = false)
	@Column(name = "BASE_IMPONIBLE_R_NC", nullable = false, precision = 13, scale = 2)
	private BigDecimal baseImponibleRNc;
	@Basic(optional = false)
	@Column(name = "IMPUESTO_R_NC", nullable = false, precision = 13, scale = 2)
	private BigDecimal impuestoRNc;
	@Basic(optional = false)
	@Column(name = "PORCENTAJE_R_NC", nullable = false, precision = 13, scale = 2)
	private BigDecimal porcentajeRNc;
	@Basic(optional = false)
	@Column(name = "BASE_IMPONIBLE_A_NC", nullable = false, precision = 13, scale = 2)
	private BigDecimal baseImponibleANc;
	@Basic(optional = false)
	@Column(name = "IMPUESTO_A_NC", nullable = false, precision = 13, scale = 2)
	private BigDecimal impuestoANc;
	@Basic(optional = false)
	@Column(name = "PORCENTAJE_A_NC", nullable = false, precision = 13, scale = 2)
	private BigDecimal porcentajeANc;
	@Basic(optional = false)
	@Column(name = "TIPO", nullable = false, length = 20)
	private String tipo;
	@Basic(optional = false)
	@Column(name = "ESTREPLICA")
	private char estreplica;
	@JoinColumn(name = "ID_SESION", referencedColumnName = "ID", nullable = false)
	@ManyToOne(optional = false)
	private Sesion idSesion;
	@Lob
	@Column(name = "ELECTRONIC_JOURNAL")
	private byte[] electronic_journal;

	public Reportez() {
	}

	public Reportez(Long id) {
		this.id = id;
	}

	public Reportez(Long id, int tienda, Date fecha, int numeroReporte,
			String serialImpresora, Date fechaCreacion, Date horaCreacion,
			int facturasEmitidas, int ultimaFacturaEmitida,
			Date fechaUltimaFacturaEmitida, Date horaUltimaFacturaEmitida,
			int notaCreditoEmitida, int ultimaNotaCreditoEmitida,
			Date fechaUltimaNotaCreditoEmitida,
			Date horaUltimaNotaCreditoEmitida, BigDecimal montoExentoFactura,
			BigDecimal baseImponibleGFacturas,
			BigDecimal baseImponibleRFacturas,
			BigDecimal baseImponibleAFacturas, BigDecimal impuestoGFacturas,
			BigDecimal porcentajeGFacturas, BigDecimal impuestoRFacturas,
			BigDecimal porcentajeRFacturas, BigDecimal impuestoAFacturas,
			BigDecimal porcentajeAFacturas, BigDecimal montoExentoNc,
			BigDecimal baseImponibleGNc, BigDecimal impuestoGNc,
			BigDecimal porcentajeGNc, BigDecimal baseImponibleRNc,
			BigDecimal impuestoRNc, BigDecimal porcentajeRNc,
			BigDecimal baseImponibleANc, BigDecimal impuestoANc,
			BigDecimal porcentajeANc, String tipo, char estreplica) {
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
		this.montoExentoFactura = montoExentoFactura;
		this.baseImponibleGFacturas = baseImponibleGFacturas;
		this.baseImponibleRFacturas = baseImponibleRFacturas;
		this.baseImponibleAFacturas = baseImponibleAFacturas;
		this.impuestoGFacturas = impuestoGFacturas;
		this.porcentajeGFacturas = porcentajeGFacturas;
		this.impuestoRFacturas = impuestoRFacturas;
		this.porcentajeRFacturas = porcentajeRFacturas;
		this.impuestoAFacturas = impuestoAFacturas;
		this.porcentajeAFacturas = porcentajeAFacturas;
		this.montoExentoNc = montoExentoNc;
		this.baseImponibleGNc = baseImponibleGNc;
		this.impuestoGNc = impuestoGNc;
		this.porcentajeGNc = porcentajeGNc;
		this.baseImponibleRNc = baseImponibleRNc;
		this.impuestoRNc = impuestoRNc;
		this.porcentajeRNc = porcentajeRNc;
		this.baseImponibleANc = baseImponibleANc;
		this.impuestoANc = impuestoANc;
		this.porcentajeANc = porcentajeANc;
		this.tipo = tipo;
		this.estreplica = estreplica;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getTienda() {
		return tienda;
	}

	public void setTienda(int tienda) {
		this.tienda = tienda;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getNumeroReporte() {
		return numeroReporte;
	}

	public void setNumeroReporte(int numeroReporte) {
		this.numeroReporte = numeroReporte;
	}

	public String getSerialImpresora() {
		return serialImpresora;
	}

	public void setSerialImpresora(String serialImpresora) {
		this.serialImpresora = serialImpresora;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getHoraCreacion() {
		return horaCreacion;
	}

	public void setHoraCreacion(Date horaCreacion) {
		this.horaCreacion = horaCreacion;
	}

	public int getFacturasEmitidas() {
		return facturasEmitidas;
	}

	public void setFacturasEmitidas(int facturasEmitidas) {
		this.facturasEmitidas = facturasEmitidas;
	}

	public int getUltimaFacturaEmitida() {
		return ultimaFacturaEmitida;
	}

	public void setUltimaFacturaEmitida(int ultimaFacturaEmitida) {
		this.ultimaFacturaEmitida = ultimaFacturaEmitida;
	}

	public Date getFechaUltimaFacturaEmitida() {
		return fechaUltimaFacturaEmitida;
	}

	public void setFechaUltimaFacturaEmitida(Date fechaUltimaFacturaEmitida) {
		this.fechaUltimaFacturaEmitida = fechaUltimaFacturaEmitida;
	}

	public Date getHoraUltimaFacturaEmitida() {
		return horaUltimaFacturaEmitida;
	}

	public void setHoraUltimaFacturaEmitida(Date horaUltimaFacturaEmitida) {
		this.horaUltimaFacturaEmitida = horaUltimaFacturaEmitida;
	}

	public int getNotaCreditoEmitida() {
		return notaCreditoEmitida;
	}

	public void setNotaCreditoEmitida(int notaCreditoEmitida) {
		this.notaCreditoEmitida = notaCreditoEmitida;
	}

	public int getUltimaNotaCreditoEmitida() {
		return ultimaNotaCreditoEmitida;
	}

	public void setUltimaNotaCreditoEmitida(int ultimaNotaCreditoEmitida) {
		this.ultimaNotaCreditoEmitida = ultimaNotaCreditoEmitida;
	}

	public Date getFechaUltimaNotaCreditoEmitida() {
		return fechaUltimaNotaCreditoEmitida;
	}

	public void setFechaUltimaNotaCreditoEmitida(
			Date fechaUltimaNotaCreditoEmitida) {
		this.fechaUltimaNotaCreditoEmitida = fechaUltimaNotaCreditoEmitida;
	}

	public Date getHoraUltimaNotaCreditoEmitida() {
		return horaUltimaNotaCreditoEmitida;
	}

	public void setHoraUltimaNotaCreditoEmitida(
			Date horaUltimaNotaCreditoEmitida) {
		this.horaUltimaNotaCreditoEmitida = horaUltimaNotaCreditoEmitida;
	}

	public BigDecimal getMontoExentoFactura() {
		return montoExentoFactura;
	}

	public void setMontoExentoFactura(BigDecimal montoExentoFactura) {
		this.montoExentoFactura = montoExentoFactura;
	}

	public BigDecimal getBaseImponibleGFacturas() {
		return baseImponibleGFacturas;
	}

	public void setBaseImponibleGFacturas(BigDecimal baseImponibleGFacturas) {
		this.baseImponibleGFacturas = baseImponibleGFacturas;
	}

	public BigDecimal getBaseImponibleRFacturas() {
		return baseImponibleRFacturas;
	}

	public void setBaseImponibleRFacturas(BigDecimal baseImponibleRFacturas) {
		this.baseImponibleRFacturas = baseImponibleRFacturas;
	}

	public BigDecimal getBaseImponibleAFacturas() {
		return baseImponibleAFacturas;
	}

	public void setBaseImponibleAFacturas(BigDecimal baseImponibleAFacturas) {
		this.baseImponibleAFacturas = baseImponibleAFacturas;
	}

	public BigDecimal getImpuestoGFacturas() {
		return impuestoGFacturas;
	}

	public void setImpuestoGFacturas(BigDecimal impuestoGFacturas) {
		this.impuestoGFacturas = impuestoGFacturas;
	}

	public BigDecimal getPorcentajeGFacturas() {
		return porcentajeGFacturas;
	}

	public void setPorcentajeGFacturas(BigDecimal porcentajeGFacturas) {
		this.porcentajeGFacturas = porcentajeGFacturas;
	}

	public BigDecimal getImpuestoRFacturas() {
		return impuestoRFacturas;
	}

	public void setImpuestoRFacturas(BigDecimal impuestoRFacturas) {
		this.impuestoRFacturas = impuestoRFacturas;
	}

	public BigDecimal getPorcentajeRFacturas() {
		return porcentajeRFacturas;
	}

	public void setPorcentajeRFacturas(BigDecimal porcentajeRFacturas) {
		this.porcentajeRFacturas = porcentajeRFacturas;
	}

	public BigDecimal getImpuestoAFacturas() {
		return impuestoAFacturas;
	}

	public void setImpuestoAFacturas(BigDecimal impuestoAFacturas) {
		this.impuestoAFacturas = impuestoAFacturas;
	}

	public BigDecimal getPorcentajeAFacturas() {
		return porcentajeAFacturas;
	}

	public void setPorcentajeAFacturas(BigDecimal porcentajeAFacturas) {
		this.porcentajeAFacturas = porcentajeAFacturas;
	}

	public BigDecimal getMontoExentoNc() {
		return montoExentoNc;
	}

	public void setMontoExentoNc(BigDecimal montoExentoNc) {
		this.montoExentoNc = montoExentoNc;
	}

	public BigDecimal getBaseImponibleGNc() {
		return baseImponibleGNc;
	}

	public void setBaseImponibleGNc(BigDecimal baseImponibleGNc) {
		this.baseImponibleGNc = baseImponibleGNc;
	}

	public BigDecimal getImpuestoGNc() {
		return impuestoGNc;
	}

	public void setImpuestoGNc(BigDecimal impuestoGNc) {
		this.impuestoGNc = impuestoGNc;
	}

	public BigDecimal getPorcentajeGNc() {
		return porcentajeGNc;
	}

	public void setPorcentajeGNc(BigDecimal porcentajeGNc) {
		this.porcentajeGNc = porcentajeGNc;
	}

	public BigDecimal getBaseImponibleRNc() {
		return baseImponibleRNc;
	}

	public void setBaseImponibleRNc(BigDecimal baseImponibleRNc) {
		this.baseImponibleRNc = baseImponibleRNc;
	}

	public BigDecimal getImpuestoRNc() {
		return impuestoRNc;
	}

	public void setImpuestoRNc(BigDecimal impuestoRNc) {
		this.impuestoRNc = impuestoRNc;
	}

	public BigDecimal getPorcentajeRNc() {
		return porcentajeRNc;
	}

	public void setPorcentajeRNc(BigDecimal porcentajeRNc) {
		this.porcentajeRNc = porcentajeRNc;
	}

	public BigDecimal getBaseImponibleANc() {
		return baseImponibleANc;
	}

	public void setBaseImponibleANc(BigDecimal baseImponibleANc) {
		this.baseImponibleANc = baseImponibleANc;
	}

	public BigDecimal getImpuestoANc() {
		return impuestoANc;
	}

	public void setImpuestoANc(BigDecimal impuestoANc) {
		this.impuestoANc = impuestoANc;
	}

	public BigDecimal getPorcentajeANc() {
		return porcentajeANc;
	}

	public void setPorcentajeANc(BigDecimal porcentajeANc) {
		this.porcentajeANc = porcentajeANc;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public char getEstreplica() {
		return estreplica;
	}

	public void setEstreplica(char estreplica) {
		this.estreplica = estreplica;
	}

	public Sesion getIdSesion() {
		return idSesion;
	}

	public void setIdSesion(Sesion idSesion) {
		this.idSesion = idSesion;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Reportez)) {
			return false;
		}
		Reportez other = (Reportez) object;
		if ((this.id == null && other.id != null)
				|| (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "crjpa400.Reportez[ id=" + id + " ]";
	}

	/**
	 * @param electronic_journal
	 *            the electronic_journal to set
	 */
	public void setElectronic_journal(byte[] electronic_journal) {
		this.electronic_journal = electronic_journal;
	}

	/**
	 * @return the electronic_journal
	 */
	public byte[] getElectronic_journal() {
		return electronic_journal;
	}

}
