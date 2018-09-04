/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crjpa400;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author eve0018536@epa.com
 */
@Entity
@Table(name = "devolucion")
@NamedQueries({
		@NamedQuery(name = "Devolucion.findAll", query = "SELECT d FROM Devolucion d"),
		@NamedQuery(name = "Devolucion.findById", query = "SELECT d FROM Devolucion d WHERE d.id = :id"),
		@NamedQuery(name = "Devolucion.findByIdTransaccion", query = "SELECT d FROM Devolucion d WHERE d.idTransaccion = :idTransaccion"),
		@NamedQuery(name = "Devolucion.findByCajaVenta", query = "SELECT d FROM Devolucion d WHERE d.cajaVenta = :cajaVenta"),
		@NamedQuery(name = "Devolucion.findByTransaccionVenta", query = "SELECT d FROM Devolucion d WHERE d.transaccionVenta = :transaccionVenta"),
		@NamedQuery(name = "Devolucion.findByTiendaVenta", query = "SELECT d FROM Devolucion d WHERE d.tiendaVenta = :tiendaVenta"),
		@NamedQuery(name = "Devolucion.findByFechaVenta", query = "SELECT d FROM Devolucion d WHERE d.fechaVenta = :fechaVenta"),
		@NamedQuery(name = "Devolucion.findByIdRma", query = "SELECT d FROM Devolucion d WHERE d.idRma = :idRma"),
		@NamedQuery(name = "Devolucion.findByNumeroIdentificacionCliente", query = "SELECT d FROM Devolucion d WHERE d.numeroIdentificacionCliente = :numeroIdentificacionCliente"),
		@NamedQuery(name = "Devolucion.findByFecha", query = "SELECT d FROM Devolucion d WHERE d.fecha = :fecha"),
		@NamedQuery(name = "Devolucion.findByTiendaDev", query = "SELECT d FROM Devolucion d WHERE d.tiendaDev = :tiendaDev"),
		@NamedQuery(name = "Devolucion.findByFechaDev", query = "SELECT d FROM Devolucion d WHERE d.fechaDev = :fechaDev"),
		@NamedQuery(name = "Devolucion.findByEstatusDev", query = "SELECT d FROM Devolucion d WHERE d.estatusDev = :estatusDev"),
		@NamedQuery(name = "Devolucion.findByIdUsuario", query = "SELECT d FROM Devolucion d WHERE d.idUsuario = :idUsuario"),
		@NamedQuery(name = "Devolucion.findByEstreplica", query = "SELECT d FROM Devolucion d WHERE d.estreplica = :estreplica") })
public class Devolucion implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@Column(name = "ID")
	private Long id;
	@Column(name = "ID_TRANSACCION")
	private BigInteger idTransaccion;
	@Basic(optional = false)
	@Column(name = "CAJA_VENTA")
	private int cajaVenta;
	@Basic(optional = false)
	@Column(name = "TRANSACCION_VENTA")
	private int transaccionVenta;
	@Basic(optional = false)
	@Column(name = "TIENDA_VENTA")
	private int tiendaVenta;
	@Basic(optional = false)
	@Column(name = "FECHA_VENTA")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaVenta;
	@Basic(optional = false)
	@Column(name = "ID_RMA")
	private long idRma;
	@Basic(optional = false)
	@Column(name = "NUMERO_IDENTIFICACION_CLIENTE")
	private String numeroIdentificacionCliente;
	@Basic(optional = false)
	@Column(name = "FECHA")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	@Basic(optional = false)
	@Column(name = "TIENDA_DEV")
	private int tiendaDev;
	@Basic(optional = false)
	@Column(name = "FECHA_DEV")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaDev;
	@Basic(optional = false)
	@Column(name = "ESTATUS_DEV")
	private Character estatusDev;
	@Basic(optional = false)
	@Column(name = "ID_USUARIO")
	private String idUsuario;
	@Column(name = "ESTREPLICA")
	private Character estreplica;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idDevolucion", fetch = FetchType.LAZY)
	private List<Devolucionarticulo> devolucionarticuloList;

	public Devolucion() {
	}

	public Devolucion(Long id) {
		this.id = id;
	}

	public Devolucion(Long id, int cajaVenta, int transaccionVenta,
			int tiendaVenta, Date fechaVenta, long idRma,
			String numeroIdentificacionCliente, Date fecha, int tiendaDev,
			Date fechaDev, Character estatusDev, String idUsuario, Character estreplica) {
		this.id = id;
		this.cajaVenta = cajaVenta;
		this.transaccionVenta = transaccionVenta;
		this.tiendaVenta = tiendaVenta;
		this.fechaVenta = fechaVenta;
		this.idRma = idRma;
		this.numeroIdentificacionCliente = numeroIdentificacionCliente;
		this.fecha = fecha;
		this.tiendaDev = tiendaDev;
		this.fechaDev = fechaDev;
		this.estatusDev = estatusDev;
		this.idUsuario = idUsuario;
		this.estreplica = estreplica;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigInteger getIdTransaccion() {
		return idTransaccion;
	}

	public void setIdTransaccion(BigInteger idTransaccion) {
		this.idTransaccion = idTransaccion;
	}

	public int getCajaVenta() {
		return cajaVenta;
	}

	public void setCajaVenta(int cajaVenta) {
		this.cajaVenta = cajaVenta;
	}

	public int getTransaccionVenta() {
		return transaccionVenta;
	}

	public void setTransaccionVenta(int transaccionVenta) {
		this.transaccionVenta = transaccionVenta;
	}

	public int getTiendaVenta() {
		return tiendaVenta;
	}

	public void setTiendaVenta(int tiendaVenta) {
		this.tiendaVenta = tiendaVenta;
	}

	public Date getFechaVenta() {
		return fechaVenta;
	}

	public void setFechaVenta(Date fechaVenta) {
		this.fechaVenta = fechaVenta;
	}

	public long getIdRma() {
		return idRma;
	}

	public void setIdRma(long idRma) {
		this.idRma = idRma;
	}

	public String getNumeroIdentificacionCliente() {
		return numeroIdentificacionCliente;
	}

	public void setNumeroIdentificacionCliente(
			String numeroIdentificacionCliente) {
		this.numeroIdentificacionCliente = numeroIdentificacionCliente;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getTiendaDev() {
		return tiendaDev;
	}

	public void setTiendaDev(int tiendaDev) {
		this.tiendaDev = tiendaDev;
	}

	public Date getFechaDev() {
		return fechaDev;
	}

	public void setFechaDev(Date fechaDev) {
		this.fechaDev = fechaDev;
	}

	public Character getEstatusDev() {
		return estatusDev;
	}

	public void setEstatusDev(Character estatusDev) {
		this.estatusDev = estatusDev;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Character getEstreplica() {
		return estreplica;
	}

	public void setEstreplica(Character estreplica) {
		this.estreplica = estreplica;
	}

	public List<Devolucionarticulo> getDevolucionarticuloList() {
		return devolucionarticuloList;
	}

	public void setDevolucionarticuloList(
			List<Devolucionarticulo> devolucionarticuloList) {
		this.devolucionarticuloList = devolucionarticuloList;
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
		if (!(object instanceof Devolucion)) {
			return false;
		}
		Devolucion other = (Devolucion) object;
		if ((this.id == null && other.id != null)
				|| (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "App.Devolucion[ id=" + id + " ]";
	}

}