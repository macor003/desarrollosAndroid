/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crjpa400;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * @author eve0014321
 * @version $Revision: 1.0 $
 */
@Embeddable
public class TransaccionexceptionsPK implements Serializable {
	/**
	 * Field idTransaccion.
	 */
	@Basic(optional = false)
	@Column(name = "ID_TRANSACCION")
	private long idTransaccion;
	/**
	 * Field tienda.
	 */
	@Basic(optional = false)
	@Column(name = "TIENDA")
	private int tienda;
	/**
	 * Field idCaja.
	 */
	@Basic(optional = false)
	@Column(name = "ID_CAJA")
	private long idCaja;
	/**
	 * Field numero.
	 */
	@Basic(optional = false)
	@Column(name = "NUMERO")
	private long numero;
	/**
	 * Field fechaTransaccion.
	 */
	@Basic(optional = false)
	@Column(name = "FECHA_TRANSACCION")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaTransaccion;

	/**
	 * Constructor for TransaccionexceptionsPK.
	 */
	public TransaccionexceptionsPK() {
	}

	/**
	 * Constructor for TransaccionexceptionsPK.
	 * 
	 * @param idTransaccion
	 *            long
	 * @param tienda
	 *            int
	 * @param idCaja
	 *            long
	 * @param numero
	 *            long
	 * @param fechaTransaccion
	 *            Date
	 */
	public TransaccionexceptionsPK(long idTransaccion, int tienda, long idCaja,
			long numero, Date fechaTransaccion) {
		this.idTransaccion = idTransaccion;
		this.tienda = tienda;
		this.idCaja = idCaja;
		this.numero = numero;
		this.fechaTransaccion = fechaTransaccion;
	}

	/**
	 * Method getIdTransaccion.
	 * 
	 * @return long
	 */
	public long getIdTransaccion() {
		return idTransaccion;
	}

	/**
	 * Method setIdTransaccion.
	 * 
	 * @param idTransaccion
	 *            long
	 */
	public void setIdTransaccion(long idTransaccion) {
		this.idTransaccion = idTransaccion;
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
	 * Method getIdCaja.
	 * 
	 * @return long
	 */
	public long getIdCaja() {
		return idCaja;
	}

	/**
	 * Method setIdCaja.
	 * 
	 * @param idCaja
	 *            long
	 */
	public void setIdCaja(long idCaja) {
		this.idCaja = idCaja;
	}

	/**
	 * Method getNumero.
	 * 
	 * @return long
	 */
	public long getNumero() {
		return numero;
	}

	/**
	 * Method setNumero.
	 * 
	 * @param numero
	 *            long
	 */
	public void setNumero(long numero) {
		this.numero = numero;
	}

	/**
	 * Method getFechaTransaccion.
	 * 
	 * @return Date
	 */
	public Date getFechaTransaccion() {
		return fechaTransaccion;
	}

	/**
	 * Method setFechaTransaccion.
	 * 
	 * @param fechaTransaccion
	 *            Date
	 */
	public void setFechaTransaccion(Date fechaTransaccion) {
		this.fechaTransaccion = fechaTransaccion;
	}

	/**
	 * Method hashCode.
	 * 
	 * @return int
	 */
	@Override
	public int hashCode() {
		int hash = 0;
		hash += (int) idTransaccion;
		hash += (int) tienda;
		hash += (int) idCaja;
		hash += (int) numero;
		hash += (fechaTransaccion != null ? fechaTransaccion.hashCode() : 0);
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
		if (!(object instanceof TransaccionexceptionsPK)) {
			return false;
		}
		TransaccionexceptionsPK other = (TransaccionexceptionsPK) object;
		if (this.idTransaccion != other.idTransaccion) {
			return false;
		}
		if (this.tienda != other.tienda) {
			return false;
		}
		if (this.idCaja != other.idCaja) {
			return false;
		}
		if (this.numero != other.numero) {
			return false;
		}
		if ((this.fechaTransaccion == null && other.fechaTransaccion != null)
				|| (this.fechaTransaccion != null && !this.fechaTransaccion
						.equals(other.fechaTransaccion))) {
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
		return "crjpa400.TransaccionexceptionsPK[ idTransaccion="
				+ idTransaccion + ", tienda=" + tienda + ", idCaja=" + idCaja
				+ ", numero=" + numero + ", fechaTransaccion="
				+ fechaTransaccion + " ]";
	}

}
