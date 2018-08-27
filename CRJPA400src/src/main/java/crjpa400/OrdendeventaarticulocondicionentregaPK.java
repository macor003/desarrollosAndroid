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
 * @author eve0011737
 * @version $Revision: 1.0 $
 */
@Embeddable
public class OrdendeventaarticulocondicionentregaPK implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4930601504326857265L;
	/**
	 * Field idOrdendeventaarticulo.
	 */
	@Basic(optional = false)
	@Column(name = "ID_ORDENDEVENTAARTICULO", nullable = false)
	private long idOrdendeventaarticulo;
	/**
	 * Field idCondicionentrega.
	 */
	@Basic(optional = false)
	@Column(name = "ID_CONDICIONENTREGA", nullable = false)
	private long idCondicionentrega;

	/**
	 * Constructor for OrdendeventaarticulocondicionentregaPK.
	 */
	public OrdendeventaarticulocondicionentregaPK() {
	}

	/**
	 * @param idOrdendeventaarticulo
	 * @param idCondicionentrega
	 */
	public OrdendeventaarticulocondicionentregaPK(long idOrdendeventaarticulo,
			long idCondicionentrega) {
		this.idOrdendeventaarticulo = idOrdendeventaarticulo;
		this.idCondicionentrega = idCondicionentrega;
	}

	/**
	 * Method getIdOrdendeventaarticulo.
	 * 
	 * @return long
	 */
	public long getIdOrdendeventaarticulo() {
		return idOrdendeventaarticulo;
	}

	/**
	 * Method setIdOrdendeventaarticulo.
	 * 
	 * @param idOrdendeventaarticulo
	 *            long
	 */
	public void setIdOrdendeventaarticulo(long idOrdendeventaarticulo) {
		this.idOrdendeventaarticulo = idOrdendeventaarticulo;
	}

	/**
	 * Method getIdCondicionentrega.
	 * 
	 * @return long
	 */
	public long getIdCondicionentrega() {
		return idCondicionentrega;
	}

	/**
	 * Method setIdCondicionentrega.
	 * 
	 * @param idCondicionentrega
	 *            long
	 */
	public void setIdCondicionentrega(long idCondicionentrega) {
		this.idCondicionentrega = idCondicionentrega;
	}

	/**
	 * Method hashCode.
	 * 
	 * @return int
	 */
	@Override
	public int hashCode() {
		int hash = 0;
		hash += (int) idOrdendeventaarticulo;
		hash += (int) idCondicionentrega;
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
		if (!(object instanceof OrdendeventaarticulocondicionentregaPK)) {
			return false;
		}
		OrdendeventaarticulocondicionentregaPK other = (OrdendeventaarticulocondicionentregaPK) object;
		if (this.idOrdendeventaarticulo != other.idOrdendeventaarticulo) {
			return false;
		}
		if (this.idCondicionentrega != other.idCondicionentrega) {
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
		return "crjpa400.OrdendeventaarticuloPK[ idOrdendeventaarticulo="
				+ idOrdendeventaarticulo + ", idCondicionentrega="
				+ idCondicionentrega + " ]";
	}

}
