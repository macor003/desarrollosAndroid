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
public class AcreenciamovimientoPK implements Serializable {
	/**
	 * Field idAcreenciamovimientosaldo.
	 */
	@Basic(optional = false)
	@Column(name = "ID_ACREENCIAMOVIMIENTOSALDO", nullable = false)
	private long idAcreenciamovimientosaldo;
	/**
	 * Field idAcreenciamovimientoformadepago.
	 */
	@Basic(optional = false)
	@Column(name = "ID_ACREENCIAMOVIMIENTOFORMADEPAGO", nullable = false)
	private long idAcreenciamovimientoformadepago;

	/**
	 * Constructor for AcreenciamovimientoPK.
	 */
	public AcreenciamovimientoPK() {
	}

	/**
	 * Constructor for AcreenciamovimientoPK.
	 * 
	 * @param idAcreenciamovimientosaldo
	 *            long
	 * @param idAcreenciamovimientoformadepago
	 *            long
	 */
	public AcreenciamovimientoPK(long idAcreenciamovimientosaldo,
			long idAcreenciamovimientoformadepago) {
		this.idAcreenciamovimientosaldo = idAcreenciamovimientosaldo;
		this.idAcreenciamovimientoformadepago = idAcreenciamovimientoformadepago;
	}

	/**
	 * Method getIdAcreenciamovimientosaldo.
	 * 
	 * @return long
	 */
	public long getIdAcreenciamovimientosaldo() {
		return idAcreenciamovimientosaldo;
	}

	/**
	 * Method setIdAcreenciamovimientosaldo.
	 * 
	 * @param idAcreenciamovimientosaldo
	 *            long
	 */
	public void setIdAcreenciamovimientosaldo(long idAcreenciamovimientosaldo) {
		this.idAcreenciamovimientosaldo = idAcreenciamovimientosaldo;
	}

	/**
	 * Method getIdAcreenciamovimientoformadepago.
	 * 
	 * @return long
	 */
	public long getIdAcreenciamovimientoformadepago() {
		return idAcreenciamovimientoformadepago;
	}

	/**
	 * Method setIdAcreenciamovimientoformadepago.
	 * 
	 * @param idAcreenciamovimientoformadepago
	 *            long
	 */
	public void setIdAcreenciamovimientoformadepago(
			long idAcreenciamovimientoformadepago) {
		this.idAcreenciamovimientoformadepago = idAcreenciamovimientoformadepago;
	}

	/**
	 * Method hashCode.
	 * 
	 * @return int
	 */
	@Override
	public int hashCode() {
		int hash = 0;
		hash += (int) idAcreenciamovimientosaldo;
		hash += (int) idAcreenciamovimientoformadepago;
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
		if (!(object instanceof AcreenciamovimientoPK)) {
			return false;
		}
		AcreenciamovimientoPK other = (AcreenciamovimientoPK) object;
		if (this.idAcreenciamovimientosaldo != other.idAcreenciamovimientosaldo) {
			return false;
		}
		if (this.idAcreenciamovimientoformadepago != other.idAcreenciamovimientoformadepago) {
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
		return "crjpa400.AcreenciamovimientoPK[ idAcreenciamovimientosaldo="
				+ idAcreenciamovimientosaldo
				+ ", idAcreenciamovimientoformadepago="
				+ idAcreenciamovimientoformadepago + " ]";
	}

}
