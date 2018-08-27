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
public class SaldoacreenciaPK implements Serializable {
	/**
	 * Field idAcreencia.
	 */
	@Basic(optional = false)
	@Column(name = "ID_ACREENCIA")
	private long idAcreencia;
	/**
	 * Field idTipoacreencia.
	 */
	@Basic(optional = false)
	@Column(name = "ID_TIPOACREENCIA")
	private long idTipoacreencia;

	/**
	 * Constructor for SaldoacreenciaPK.
	 */
	public SaldoacreenciaPK() {
	}

	/**
	 * Constructor for SaldoacreenciaPK.
	 * 
	 * @param idAcreencia
	 *            long
	 * @param idTipoacreencia
	 *            long
	 */
	public SaldoacreenciaPK(long idAcreencia, long idTipoacreencia) {
		this.idAcreencia = idAcreencia;
		this.idTipoacreencia = idTipoacreencia;
	}

	/**
	 * Method getIdAcreencia.
	 * 
	 * @return long
	 */
	public long getIdAcreencia() {
		return idAcreencia;
	}

	/**
	 * Method setIdAcreencia.
	 * 
	 * @param idAcreencia
	 *            long
	 */
	public void setIdAcreencia(long idAcreencia) {
		this.idAcreencia = idAcreencia;
	}

	/**
	 * Method getIdTipoacreencia.
	 * 
	 * @return long
	 */
	public long getIdTipoacreencia() {
		return idTipoacreencia;
	}

	/**
	 * Method setIdTipoacreencia.
	 * 
	 * @param idTipoacreencia
	 *            long
	 */
	public void setIdTipoacreencia(long idTipoacreencia) {
		this.idTipoacreencia = idTipoacreencia;
	}

	/**
	 * Method hashCode.
	 * 
	 * @return int
	 */
	@Override
	public int hashCode() {
		int hash = 0;
		hash += (int) idAcreencia;
		hash += (int) idTipoacreencia;
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
		if (!(object instanceof SaldoacreenciaPK)) {
			return false;
		}
		SaldoacreenciaPK other = (SaldoacreenciaPK) object;
		if (this.idAcreencia != other.idAcreencia) {
			return false;
		}
		if (this.idTipoacreencia != other.idTipoacreencia) {
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
		return "crjpa400.SaldoacreenciaPK[ idAcreencia=" + idAcreencia
				+ ", idTipoacreencia=" + idTipoacreencia + " ]";
	}

}
