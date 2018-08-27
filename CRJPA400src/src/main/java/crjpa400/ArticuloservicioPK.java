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
public class ArticuloservicioPK implements Serializable {
	/**
	 * Field idArticulo.
	 */
	@Basic(optional = false)
	@Column(name = "ID_ARTICULO", nullable = false)
	private long idArticulo;
	/**
	 * Field idServicio.
	 */
	@Basic(optional = false)
	@Column(name = "ID_SERVICIO", nullable = false)
	private long idServicio;

	/**
	 * Constructor for ArticuloservicioPK.
	 */
	public ArticuloservicioPK() {
	}

	/**
	 * Constructor for ArticuloservicioPK.
	 * 
	 * @param idArticulo
	 *            long
	 * @param idServicio
	 *            long
	 */
	public ArticuloservicioPK(long idArticulo, long idServicio) {
		this.idArticulo = idArticulo;
		this.idServicio = idServicio;
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
	 * Method getIdServicio.
	 * 
	 * @return long
	 */
	public long getIdServicio() {
		return idServicio;
	}

	/**
	 * Method setIdServicio.
	 * 
	 * @param idServicio
	 *            long
	 */
	public void setIdServicio(long idServicio) {
		this.idServicio = idServicio;
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
		hash += (int) idServicio;
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
		if (!(object instanceof ArticuloservicioPK)) {
			return false;
		}
		ArticuloservicioPK other = (ArticuloservicioPK) object;
		if (this.idArticulo != other.idArticulo) {
			return false;
		}
		if (this.idServicio != other.idServicio) {
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
		return "crjpa400.ArticuloservicioPK[ idArticulo=" + idArticulo
				+ ", idServicio=" + idServicio + " ]";
	}

}
