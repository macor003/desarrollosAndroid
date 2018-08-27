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
@Table(name = "SINCRONIZACIONPORCAJA")
@XmlRootElement
public class Sincronizacionporcaja extends CrjpaInterface implements
		Serializable {
	/**
	 * Field serialVersionUID. (value is 1)
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Id de la tabla.
	 */
	@EmbeddedId
	protected SincronizacionporcajaPK sincronizacionporcajaPK;
	/**
	 * Fecha en que se sincronizo la ultima vez.
	 */
	@Basic(optional = false)
	@Column(name = "FECHA", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar fecha;
	/**
	 * Mensaje de error de la sincronizacion en caso de ocurrir.
	 */
	@Basic(optional = false)
	@Column(name = "MENSAJE_ERROR")
	private Long mensajeError;

	/**
	 * Constructor for Sincronizacionporcaja.
	 */
	public Sincronizacionporcaja() {
	}

	/**
	 * Constructor de Sincronizacionporcaja.
	 * 
	 * @param id
	 *            long id de la tabla sincronizacion por caja
	 * @param idSincronizacion
	 *            long id de la tabla sincronizacion a la que hace referencia el
	 *            registro
	 * @param idCaja
	 *            long id de la caja que realiza el registro
	 * @param fecha
	 *            Calendar fecha que se sincronizo por ultima vez el registro de
	 *            la tabla sincronizacion de la caja
	 */
	public Sincronizacionporcaja(
			SincronizacionporcajaPK sincronizacionporcajaPK, Calendar fecha) {
		this.sincronizacionporcajaPK = sincronizacionporcajaPK;
		this.fecha = fecha;
	}

	/**
	 * Constructor de Sincronizacionporcaja.
	 * 
	 * @param id
	 *            long id de la tabla sincronizacion por caja
	 */
	public Sincronizacionporcaja(long idCaja, long idSincronizacion) {
		this.sincronizacionporcajaPK = new SincronizacionporcajaPK(idCaja,
				idSincronizacion);
	}

	/**
	 * Metodo getId.
	 * 
	 * @return long id de la tabla sincronizacion por caja
	 */
	public SincronizacionporcajaPK getId() {
		return sincronizacionporcajaPK;
	}

	/**
	 * Metodo setId.
	 * 
	 * @param id
	 *            long id de la tabla sincronizacion por caja
	 */
	public void setId(SincronizacionporcajaPK sincronizacionporcajaPK) {
		this.sincronizacionporcajaPK = sincronizacionporcajaPK;
	}

	/**
	 * Metodo getFecha.
	 * 
	 * @return Calendar Fecha en que sincronizo por ultima vez el registro de la
	 *         tabla sincronizacion en la caja
	 */
	public Calendar getFecha() {
		return fecha;
	}

	/**
	 * Metodo setFecha.
	 * 
	 * @param fecha
	 *            Calendar Fecha en que sincronizo por ultima vez el registro de
	 *            la tabla sincronizacion en la caja
	 */
	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
	}

	/**
	 * Metodo hashCode.
	 * 
	 * @return int
	 */
	@Override
	public int hashCode() {
		int hash = 0;
		hash += (sincronizacionporcajaPK != null ? sincronizacionporcajaPK
				.hashCode() : 0);
		return hash;
	}

	/**
	 * Metodo equals.
	 * 
	 * @param object
	 *            Object
	 * @return boolean
	 */
	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Sincronizacionporcaja)) {
			return false;
		}
		Sincronizacionporcaja other = (Sincronizacionporcaja) object;
		if ((this.sincronizacionporcajaPK == null && other.sincronizacionporcajaPK != null)
				|| (this.sincronizacionporcajaPK != null && !this.sincronizacionporcajaPK
						.equals(other.sincronizacionporcajaPK))) {
			return false;
		}
		return true;
	}

	/**
	 * Metodo toString.
	 * 
	 * @return String
	 */
	@Override
	public String toString() {
		return "crjpa400.Sincronizacionporcaja[ id="
				+ sincronizacionporcajaPK.toString() + " ]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see crjpa400.interfaces.CrjpaInterface#getUltimasincronizacion()
	 */
	/**
	 * Method getUltimasincronizacion.
	 * 
	 * @return Calendar
	 */
	@Override
	public Calendar getUltimasincronizacion() {
		return null;
	}

	/**
	 * Metodo setMensajeError.
	 * 
	 * @param mensajeError
	 *            mensaje que describe el error ocurrido en la caja en caso de
	 *            ocurrir
	 */
	public void setMensajeError(Long mensajeError) {
		this.mensajeError = mensajeError;
	}

	/**
	 * Metodo getMensajeError.
	 * 
	 * @return the mensajeError mensaje que describe el error ocurrido en la
	 *         caja en caso de ocurrir
	 */
	public Long getMensajeError() {
		return mensajeError;
	}

}
