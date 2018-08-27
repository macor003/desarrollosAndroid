/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crjpa400;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author eve0018536
 */
@Entity
@Table(name = "HISTORIALDEVOLUCION")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Historialdevolucion.findAll", query = "SELECT h FROM Historialdevolucion h"),
		@NamedQuery(name = "Historialdevolucion.findById", query = "SELECT h FROM Historialdevolucion h WHERE h.id = :id"),
		@NamedQuery(name = "Historialdevolucion.findByIdTrDevolucion", query = "SELECT h FROM Historialdevolucion h WHERE h.idTrDevolucion = :idTrDevolucion"),
		@NamedQuery(name = "Historialdevolucion.findByTiendaDevolucion", query = "SELECT h FROM Historialdevolucion h WHERE h.tiendaDevolucion = :tiendaDevolucion"),
		@NamedQuery(name = "Historialdevolucion.findByCajaDevolucion", query = "SELECT h FROM Historialdevolucion h WHERE h.cajaDevolucion = :cajaDevolucion"),
		@NamedQuery(name = "Historialdevolucion.findByTransaccionDevolucion", query = "SELECT h FROM Historialdevolucion h WHERE h.transaccionDevolucion = :transaccionDevolucion"),
		@NamedQuery(name = "Historialdevolucion.findByFechaDevolucion", query = "SELECT h FROM Historialdevolucion h WHERE h.fechaDevolucion = :fechaDevolucion"),
		@NamedQuery(name = "Historialdevolucion.findByMontoPendiente", query = "SELECT h FROM Historialdevolucion h WHERE h.montoPendiente = :montoPendiente"),
		@NamedQuery(name = "Historialdevolucion.findByTiendaVenta", query = "SELECT h FROM Historialdevolucion h WHERE h.tiendaVenta = :tiendaVenta"),
		@NamedQuery(name = "Historialdevolucion.findByCajaVenta", query = "SELECT h FROM Historialdevolucion h WHERE h.cajaVenta = :cajaVenta"),
		@NamedQuery(name = "Historialdevolucion.findByTransaccionVenta", query = "SELECT h FROM Historialdevolucion h WHERE h.transaccionVenta = :transaccionVenta"),
		@NamedQuery(name = "Historialdevolucion.findByEstreplica", query = "SELECT h FROM Historialdevolucion h WHERE h.estreplica = :estreplica") })
public class Historialdevolucion implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID")
	private Long id;
	@Column(name = "ID_TR_DEVOLUCION")
	private BigInteger idTrDevolucion;
	@Column(name = "TIENDA_DEVOLUCION")
	private String tiendaDevolucion;
	@Column(name = "CAJA_DEVOLUCION")
	private BigInteger cajaDevolucion;
	@Column(name = "TRANSACCION_DEVOLUCION")
	private BigInteger transaccionDevolucion;
	@Basic(optional = false)
	@Column(name = "FECHA_DEVOLUCION")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaDevolucion;
	@Basic(optional = false)
	@Column(name = "MONTO_PENDIENTE")
	private BigDecimal montoPendiente;

	@Column(name = "TIENDA_VENTA")
	private String tiendaVenta;
	@Column(name = "CAJA_VENTA")
	private BigInteger cajaVenta;
	@Column(name = "TRANSACCION_VENTA")
	private BigInteger transaccionVenta;
	@Column(name = "ESTREPLICA")
	private Character estreplica;

	public Historialdevolucion() {
	}

	public Historialdevolucion(Long id) {
		this.id = id;
	}

	public Historialdevolucion(Long id, Date fechaDevolucion,
			BigDecimal montoPendiente) {
		this.id = id;
		this.fechaDevolucion = fechaDevolucion;
		this.montoPendiente = montoPendiente;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigInteger getIdTrDevolucion() {
		return idTrDevolucion;
	}

	public void setIdTrDevolucion(BigInteger idTrDevolucion) {
		this.idTrDevolucion = idTrDevolucion;
	}

	public String getTiendaDevolucion() {
		return tiendaDevolucion;
	}

	public void setTiendaDevolucion(String tiendaDevolucion) {
		this.tiendaDevolucion = tiendaDevolucion;
	}

	public BigInteger getCajaDevolucion() {
		return cajaDevolucion;
	}

	public void setCajaDevolucion(BigInteger cajaDevolucion) {
		this.cajaDevolucion = cajaDevolucion;
	}

	public BigInteger getTransaccionDevolucion() {
		return transaccionDevolucion;
	}

	public void setTransaccionDevolucion(BigInteger transaccionDevolucion) {
		this.transaccionDevolucion = transaccionDevolucion;
	}

	public Date getFechaDevolucion() {
		return fechaDevolucion;
	}

	public void setFechaDevolucion(Date fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}

	public BigDecimal getMontoPendiente() {
		return montoPendiente;
	}

	public void setMontoPendiente(BigDecimal montoPendiente) {
		this.montoPendiente = montoPendiente;
	}

	public String getTiendaVenta() {
		return tiendaVenta;
	}

	public void setTiendaVenta(String tiendaVenta) {
		this.tiendaVenta = tiendaVenta;
	}

	public BigInteger getCajaVenta() {
		return cajaVenta;
	}

	public void setCajaVenta(BigInteger cajaVenta) {
		this.cajaVenta = cajaVenta;
	}

	public BigInteger getTransaccionVenta() {
		return transaccionVenta;
	}

	public void setTransaccionVenta(BigInteger transaccionVenta) {
		this.transaccionVenta = transaccionVenta;
	}

	public Character getEstreplica() {
		return estreplica;
	}

	public void setEstreplica(Character estreplica) {
		this.estreplica = estreplica;
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
		if (!(object instanceof Historialdevolucion)) {
			return false;
		}
		Historialdevolucion other = (Historialdevolucion) object;
		if ((this.id == null && other.id != null)
				|| (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "crjpa400.Historialdevolucion[ id=" + id + " ]";
	}

}
