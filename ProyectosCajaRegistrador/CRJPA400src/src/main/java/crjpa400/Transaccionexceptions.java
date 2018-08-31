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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author eve0014321
 * @version $Revision: 1.0 $
 */
@Entity
@Table(name = "TRANSACCIONEXCEPTIONS")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Transaccionexceptions.findAll", query = "SELECT t FROM Transaccionexceptions t"),
		@NamedQuery(name = "Transaccionexceptions.findByIdTransaccion", query = "SELECT t FROM Transaccionexceptions t WHERE t.transaccionexceptionsPK.idTransaccion = :idTransaccion"),
		@NamedQuery(name = "Transaccionexceptions.findByTienda", query = "SELECT t FROM Transaccionexceptions t WHERE t.transaccionexceptionsPK.tienda = :tienda"),
		@NamedQuery(name = "Transaccionexceptions.findByIdCaja", query = "SELECT t FROM Transaccionexceptions t WHERE t.transaccionexceptionsPK.idCaja = :idCaja"),
		@NamedQuery(name = "Transaccionexceptions.findByNumero", query = "SELECT t FROM Transaccionexceptions t WHERE t.transaccionexceptionsPK.numero = :numero"),
		@NamedQuery(name = "Transaccionexceptions.findByFechaTransaccion", query = "SELECT t FROM Transaccionexceptions t WHERE t.transaccionexceptionsPK.fechaTransaccion = :fechaTransaccion"),
		@NamedQuery(name = "Transaccionexceptions.findByEstatus", query = "SELECT t FROM Transaccionexceptions t WHERE t.estatus = :estatus"),
		@NamedQuery(name = "Transaccionexceptions.findByEstreplica", query = "SELECT t FROM Transaccionexceptions t WHERE t.estreplica = :estreplica") })
public class Transaccionexceptions implements Serializable {
	/**
	 * Field serialVersionUID. (value is 1)
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Field transaccionexceptionsPK.
	 */
	@EmbeddedId
	protected TransaccionexceptionsPK transaccionexceptionsPK;
	/**
	 * Field estatus.
	 */
	@Basic(optional = false)
	@Column(name = "ESTATUS", nullable = false)
	private char estatus;
	/**
	 * Field estreplica.
	 */
	@Basic(optional = false)
	@Column(name = "ESTREPLICA")
	private char estreplica;
	/**
	 * Field idException.
	 */
	@JoinColumn(name = "ID_EXCEPTION", referencedColumnName = "ID", nullable = false)
	@ManyToOne(optional = false)
	private Exceptions idException;

	/**
	 * Constructor for Transaccionexceptions.
	 */
	public Transaccionexceptions() {
	}

	/**
	 * Constructor for Transaccionexceptions.
	 * 
	 * @param transaccionexceptionsPK
	 *            TransaccionexceptionsPK
	 */
	public Transaccionexceptions(TransaccionexceptionsPK transaccionexceptionsPK) {
		this.transaccionexceptionsPK = transaccionexceptionsPK;
	}

	/**
	 * Constructor for Transaccionexceptions.
	 * 
	 * @param transaccionexceptionsPK
	 *            TransaccionexceptionsPK
	 * @param estatus
	 *            char
	 * @param estreplica
	 *            char
	 */
	public Transaccionexceptions(
			TransaccionexceptionsPK transaccionexceptionsPK, char estatus,
			char estreplica) {
		this.transaccionexceptionsPK = transaccionexceptionsPK;
		this.estatus = estatus;
		this.estreplica = estreplica;
	}

	/**
	 * Constructor for Transaccionexceptions.
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
	public Transaccionexceptions(long idTransaccion, int tienda, long idCaja,
			long numero, Date fechaTransaccion) {
		this.transaccionexceptionsPK = new TransaccionexceptionsPK(
				idTransaccion, tienda, idCaja, numero, fechaTransaccion);
	}

	/**
	 * Method getTransaccionexceptionsPK.
	 * 
	 * @return TransaccionexceptionsPK
	 */
	public TransaccionexceptionsPK getTransaccionexceptionsPK() {
		return transaccionexceptionsPK;
	}

	/**
	 * Method setTransaccionexceptionsPK.
	 * 
	 * @param transaccionexceptionsPK
	 *            TransaccionexceptionsPK
	 */
	public void setTransaccionexceptionsPK(
			TransaccionexceptionsPK transaccionexceptionsPK) {
		this.transaccionexceptionsPK = transaccionexceptionsPK;
	}

	/**
	 * Method getEstatus.
	 * 
	 * @return char
	 */
	public char getEstatus() {
		return estatus;
	}

	/**
	 * Method setEstatus.
	 * 
	 * @param estatus
	 *            char
	 */
	public void setEstatus(char estatus) {
		this.estatus = estatus;
	}

	/**
	 * Method getEstreplica.
	 * 
	 * @return char
	 */
	public char getEstreplica() {
		return estreplica;
	}

	/**
	 * Method setEstreplica.
	 * 
	 * @param estreplica
	 *            char
	 */
	public void setEstreplica(char estreplica) {
		this.estreplica = estreplica;
	}

	/**
	 * Method getIdException.
	 * 
	 * @return Exceptions
	 */
	public Exceptions getIdException() {
		return idException;
	}

	/**
	 * Method setIdException.
	 * 
	 * @param idException
	 *            Exceptions
	 */
	public void setIdException(Exceptions idException) {
		this.idException = idException;
	}

	/**
	 * Method hashCode.
	 * 
	 * @return int
	 */
	@Override
	public int hashCode() {
		int hash = 0;
		hash += (transaccionexceptionsPK != null ? transaccionexceptionsPK
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
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Transaccionexceptions)) {
			return false;
		}
		Transaccionexceptions other = (Transaccionexceptions) object;
		if ((this.transaccionexceptionsPK == null && other.transaccionexceptionsPK != null)
				|| (this.transaccionexceptionsPK != null && !this.transaccionexceptionsPK
						.equals(other.transaccionexceptionsPK))) {
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
		return "crjpa400.Transaccionexceptions[ transaccionexceptionsPK="
				+ transaccionexceptionsPK + " ]";
	}

}
