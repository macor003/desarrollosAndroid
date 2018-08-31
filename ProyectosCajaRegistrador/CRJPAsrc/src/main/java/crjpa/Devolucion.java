/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crjpa;

import java.io.Serializable;
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
@Table(name = "devolucion")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Devolucion.findAll", query = "SELECT d FROM Devolucion d"),
		@NamedQuery(name = "Devolucion.findById", query = "SELECT d FROM Devolucion d WHERE d.id = :id"),
		@NamedQuery(name = "Devolucion.findByCajaOrigen", query = "SELECT d FROM Devolucion d WHERE d.cajaOrigen = :cajaOrigen"),
		@NamedQuery(name = "Devolucion.findByTransaccionOrigen", query = "SELECT d FROM Devolucion d WHERE d.transaccionOrigen = :transaccionOrigen"),
		@NamedQuery(name = "Devolucion.findByTiendaOrigen", query = "SELECT d FROM Devolucion d WHERE d.tiendaOrigen = :tiendaOrigen"),
		@NamedQuery(name = "Devolucion.findByFecha", query = "SELECT d FROM Devolucion d WHERE d.fecha = :fecha"),
		@NamedQuery(name = "Devolucion.findByEstasincronizado", query = "SELECT d FROM Devolucion d WHERE d.estasincronizado = :estasincronizado") })
public class Devolucion implements Serializable {
	/**
	 * Field serialVersionUID. (value is 1)
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Field id.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "10")
	@TableGenerator(name = "10", table = "correlativo", pkColumnName = "id", valueColumnName = "valor", pkColumnValue = "10", allocationSize = 1)
	@Basic(optional = false)
	@Column(name = "id", nullable = false)
	private Long id;
	/**
	 * Field cajaOrigen.
	 */
	@Basic(optional = false)
	@Column(name = "caja_origen", nullable = false)
	private int cajaOrigen;
	/**
	 * Field transaccionOrigen.
	 */
	@Basic(optional = false)
	@Column(name = "transaccion_origen", nullable = false)
	private int transaccionOrigen;
	/**
	 * Field tiendaOrigen.
	 */
	@Basic(optional = false)
	@Column(name = "tienda_origen", nullable = false)
	private int tiendaOrigen;
	/**
	 * Field fecha.
	 */
	@Basic(optional = false)
	@Column(name = "fecha", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	/**
	 * Field estasincronizado.
	 */
	@Column(name = "estasincronizado", length = 2)
	private String estasincronizado;
	/**
	 * Field idTransaccion.
	 */
	@JoinColumn(name = "id_transaccion", referencedColumnName = "id", nullable = false)
	@ManyToOne(optional = false)
	private Transaccion idTransaccion;

	/**
	 * Constructor for Devolucion.
	 */
	public Devolucion() {
	}

	/**
	 * Constructor for Devolucion.
	 * 
	 * @param id
	 *            Long
	 */
	public Devolucion(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Devolucion.
	 * 
	 * @param id
	 *            Long
	 * @param cajaOrigen
	 *            int
	 * @param transaccionOrigen
	 *            int
	 * @param tiendaOrigen
	 *            int
	 * @param fecha
	 *            Date
	 */
	public Devolucion(Long id, int cajaOrigen, int transaccionOrigen, int tiendaOrigen, Date fecha) {
		this.id = id;
		this.cajaOrigen = cajaOrigen;
		this.transaccionOrigen = transaccionOrigen;
		this.tiendaOrigen = tiendaOrigen;
		this.fecha = fecha;
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
	 * Method getCajaOrigen.
	 * 
	 * @return int
	 */
	public int getCajaOrigen() {
		return cajaOrigen;
	}

	/**
	 * Method setCajaOrigen.
	 * 
	 * @param cajaOrigen
	 *            int
	 */
	public void setCajaOrigen(int cajaOrigen) {
		this.cajaOrigen = cajaOrigen;
	}

	/**
	 * Method getTransaccionOrigen.
	 * 
	 * @return int
	 */
	public int getTransaccionOrigen() {
		return transaccionOrigen;
	}

	/**
	 * Method setTransaccionOrigen.
	 * 
	 * @param transaccionOrigen
	 *            int
	 */
	public void setTransaccionOrigen(int transaccionOrigen) {
		this.transaccionOrigen = transaccionOrigen;
	}

	/**
	 * Method getTiendaOrigen.
	 * 
	 * @return int
	 */
	public int getTiendaOrigen() {
		return tiendaOrigen;
	}

	/**
	 * Method setTiendaOrigen.
	 * 
	 * @param tiendaOrigen
	 *            int
	 */
	public void setTiendaOrigen(int tiendaOrigen) {
		this.tiendaOrigen = tiendaOrigen;
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
	 * Method getIdTransaccion.
	 * 
	 * @return Transaccion
	 */
	public Transaccion getIdTransaccion() {
		return idTransaccion;
	}

	/**
	 * Method setIdTransaccion.
	 * 
	 * @param idTransaccion
	 *            Transaccion
	 */
	public void setIdTransaccion(Transaccion idTransaccion) {
		this.idTransaccion = idTransaccion;
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
		if (!(object instanceof Devolucion)) {
			return false;
		}
		Devolucion other = (Devolucion) object;
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
		return "crjpa.Devolucion[ id=" + id + " ]";
	}

}
