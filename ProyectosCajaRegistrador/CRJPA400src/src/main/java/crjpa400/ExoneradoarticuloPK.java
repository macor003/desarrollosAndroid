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
public class ExoneradoarticuloPK implements Serializable {
	/**
	 * Field idArticulo.
	 */
	@Basic(optional = false)
	@Column(name = "ID_ARTICULO", nullable = false)
	private long idArticulo;
	/**
	 * Field idExoneradotipo.
	 */
	@Basic(optional = false)
	@Column(name = "ID_EXONERADOTIPO", nullable = false)
	private long idExoneradotipo;

	/**
	 * Constructor for ExoneradoarticuloPK.
	 */
	public ExoneradoarticuloPK() {
	}

	/**
	 * Constructor for ExoneradoarticuloPK.
	 * 
	 * @param idArticulo
	 *            long
	 * @param idExoneradotipo
	 *            long
	 */
	public ExoneradoarticuloPK(long idArticulo, long idExoneradotipo) {
		this.idArticulo = idArticulo;
		this.idExoneradotipo = idExoneradotipo;
	}

	/**
	 * Method getIdArticulo.
	 * 
	 * @return long
	 */
	public long getIdArticulo() {
		return idArticulo;
	}

	/**
	 * Method setIdArticulo.
	 * 
	 * @param idArticulo
	 *            long
	 */
	public void setIdArticulo(long idArticulo) {
		this.idArticulo = idArticulo;
	}

	/**
	 * Method getIdExoneradotipo.
	 * 
	 * @return long
	 */
	public long getIdExoneradotipo() {
		return idExoneradotipo;
	}

	/**
	 * Method setIdExoneradotipo.
	 * 
	 * @param idExoneradotipo
	 *            long
	 */
	public void setIdExoneradotipo(long idExoneradotipo) {
		this.idExoneradotipo = idExoneradotipo;
	}

	/**
	 * Method hashCode.
	 * 
	 * @return int
	 */
	@Override
	public int hashCode() {
		int hash = 0;
		hash += (int) idArticulo;
		hash += (int) idExoneradotipo;
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
		if (!(object instanceof ExoneradoarticuloPK)) {
			return false;
		}
		ExoneradoarticuloPK other = (ExoneradoarticuloPK) object;
		if (this.idArticulo != other.idArticulo) {
			return false;
		}
		if (this.idExoneradotipo != other.idExoneradotipo) {
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
		return "crjpa400.ExoneradoarticuloPK[ idArticulo=" + idArticulo
				+ ", idExoneradotipo=" + idExoneradotipo + " ]";
	}

}
