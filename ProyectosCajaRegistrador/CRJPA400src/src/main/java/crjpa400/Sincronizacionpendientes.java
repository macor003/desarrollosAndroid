/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crjpa400;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import crjpa400.interfaces.CrjpaInterface;

/**
 * 
 * @author eve0014321
 * @version $Revision: 1.0 $
 */
@Entity
@Table(name = "SINCRONIZACIONPENDIENTES")
@XmlRootElement
public class Sincronizacionpendientes extends CrjpaInterface implements
		Serializable {
	/**
	 * Field serialVersionUID. (value is 1)
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Id de la tabla.
	 */
	@EmbeddedId
	protected SincronizacionpendientesPK sincronizacionpendientesPK;
	/**
	 * Field fecha.
	 */
	@Basic(optional = false)
	@Column(name = "FECHA", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar fecha;
	/**
	 * Field mensajeError.
	 */
	@Basic(optional = false)
	@Column(name = "MENSAJE_ERROR", nullable = false, length = 300)
	private String mensajeError;

	/**
	 * Constructor for Sincronizacionpendientes.
	 */
	public Sincronizacionpendientes() {
	}

	/**
	 * Constructor for Sincronizacionpendientes.
	 * 
	 * @param id
	 *            long
	 * @param idSincronizacion
	 *            long
	 * @param idCaja
	 *            long
	 * @param idTabla
	 *            String
	 * @param fecha
	 *            Calendar
	 * @param mensajeError
	 *            String
	 */
	public Sincronizacionpendientes(long idSincronizacion, long idCaja,
			String idTabla, Calendar fecha, String mensajeError) {
		this.sincronizacionpendientesPK = new SincronizacionpendientesPK(
				idCaja, idSincronizacion, idTabla);
		this.fecha = fecha;
		this.mensajeError = mensajeError;
	}

	/**
	 * Constructor for Sincronizacionpendientes.
	 * 
	 * @param id
	 *            long
	 */
	public Sincronizacionpendientes(long idSincronizacion, long idCaja,
			String idTabla) {
		this.sincronizacionpendientesPK = new SincronizacionpendientesPK(
				idCaja, idSincronizacion, idTabla);
	}

	/**
	 * Method getSincronizacionpendientesPK.
	 * 
	 * @return SincronizacionpendientesPK
	 */
	public SincronizacionpendientesPK getSincronizacionpendientesPK() {
		return this.sincronizacionpendientesPK;
	}

	/**
	 * Method setSincronizacionpendientesPK.
	 * 
	 * @param sincronizacionpendientesPK
	 *            SincronizacionpendientesPK
	 */
	public void setSincronizacionpendientesPK(
			SincronizacionpendientesPK sincronizacionpendientesPK) {
		this.sincronizacionpendientesPK = sincronizacionpendientesPK;
	}

	/**
	 * Method getFecha.
	 * 
	 * @return Calendar
	 */
	public Calendar getFecha() {
		return fecha;
	}

	/**
	 * Method setFecha.
	 * 
	 * @param fecha
	 *            Calendar
	 */
	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
	}

	/**
	 * Method getMensajeError.
	 * 
	 * @return String
	 */
	public final String getMensajeError() {
		return mensajeError;
	}

	/**
	 * Method setMensajeError.
	 * 
	 * @param mensajeError
	 *            String
	 */
	public final void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}

	/**
	 * Method hashCode.
	 * 
	 * @return int
	 */
	@Override
	public int hashCode() {
		int hash = 0;
		hash += (this.sincronizacionpendientesPK != null ? this.sincronizacionpendientesPK
				.hashCode() : 0);
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
		if (!(object instanceof Sincronizacionpendientes)) {
			return false;
		}
		Sincronizacionpendientes other = (Sincronizacionpendientes) object;
		if ((this.sincronizacionpendientesPK == null && other.sincronizacionpendientesPK != null)
				|| (this.sincronizacionpendientesPK != null && !this.sincronizacionpendientesPK
						.equals(other.sincronizacionpendientesPK))) {
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
		return "crjpa400.Sincronizacionporcaja[ id="
				+ this.sincronizacionpendientesPK + " ]";
	}

	/**
	 * Method getUltimasincronizacion.
	 * 
	 * @return Calendar
	 */
	@Override
	public Calendar getUltimasincronizacion() {
		return null;
	}

}
