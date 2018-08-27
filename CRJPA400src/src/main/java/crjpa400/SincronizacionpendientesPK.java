/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crjpa400;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * 
 * @author eve0014321
 * @version $Revision: 1.0 $
 */
@Embeddable
public class SincronizacionpendientesPK implements Serializable {
	/**
	 * Field idCaja.
	 */
	@Basic(optional = false)
	@Column(name = "ID_CAJA")
	private Long idCaja;
	/**
	 * Field idSincronizacion.
	 */
	@Basic(optional = false)
	@Column(name = "ID_SINCRONIZACION")
	private Long idSincronizacion;
	/**
	 * Field idTabla.
	 */
	@Basic(optional = false)
	@Column(name = "ID_TABLA")
	private String idTabla;

	/**
	 * Constructor for SincronizacionpendientesPK.
	 */
	public SincronizacionpendientesPK() {
	}

	/**
	 * Constructor for SincronizacionpendientesPK.
	 * 
	 * @param idCaja
	 *            long
	 * @param idSincronizacion
	 *            long
	 */
	public SincronizacionpendientesPK(Long idCaja, Long idSincronizacion,
			String idTabla) {
		this.idCaja = idCaja;
		this.idSincronizacion = idSincronizacion;
		this.idTabla = idTabla;
	}

	/**
	 * Method getIdCaja.
	 * 
	 * @return long
	 */
	public Long getIdCaja() {
		return idCaja;
	}

	/**
	 * Method setIdCaja.
	 * 
	 * @param idCaja
	 *            long
	 */
	public void setIdCaja(Long idCaja) {
		this.idCaja = idCaja;
	}

	/**
	 * Method getIdSincronizacion.
	 * 
	 * @return long
	 */
	public Long getIdSincronizacion() {
		return idSincronizacion;
	}

	/**
	 * Method setIdSincronizacion.
	 * 
	 * @param idSincronizacion
	 *            long
	 */
	public void setIdSincronizacion(Long idSincronizacion) {
		this.idSincronizacion = idSincronizacion;
	}

	/**
	 * Method getIdSincronizacion.
	 * 
	 * @return String
	 */
	public String getIdTabla() {
		return idTabla;
	}

	/**
	 * Method setIdTabla.
	 * 
	 * @param idTabla
	 *            String
	 */
	public void setIdTabla(String idTabla) {
		this.idTabla = idTabla;
	}

	/**
	 * Method hashCode.
	 * 
	 * @return int
	 */
	@Override
	public int hashCode() {
		int hash = 0;
		hash += idCaja;
		hash += idSincronizacion;
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
		if (!(object instanceof SincronizacionpendientesPK)) {
			return false;
		}
		SincronizacionpendientesPK other = (SincronizacionpendientesPK) object;
		if (this.idCaja != other.idCaja) {
			return false;
		}
		if (this.idSincronizacion != other.idSincronizacion) {
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
		return "crjpa400.SincronizacionpendientesPK[ idCaja=" + idCaja
				+ ", idSincronizacion=" + idSincronizacion + " ]";
	}

}
