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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "ORDENDEVENTATRANSACCION")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Ordendeventatransaccion.findAll", query = "SELECT o FROM Ordendeventatransaccion o"),
		@NamedQuery(name = "Ordendeventatransaccion.findById", query = "SELECT o FROM Ordendeventatransaccion o WHERE o.id = :id"),
		@NamedQuery(name = "Ordendeventatransaccion.findByIdTransaccion", query = "SELECT o FROM Ordendeventatransaccion o WHERE o.idTransaccion = :idTransaccion"),
		@NamedQuery(name = "Ordendeventatransaccion.findByCaja", query = "SELECT o FROM Ordendeventatransaccion o WHERE o.caja = :caja"),
		@NamedQuery(name = "Ordendeventatransaccion.findByEstaactivo", query = "SELECT o FROM Ordendeventatransaccion o WHERE o.estaactivo = :estaactivo"),
		@NamedQuery(name = "Ordendeventatransaccion.findByEstreplica", query = "SELECT o FROM Ordendeventatransaccion o WHERE o.estreplica = :estreplica") })
public class Ordendeventatransaccion implements Serializable {
	/**
	 * Field serialVersionUID. (value is 1)
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Field id.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID", nullable = false)
	private Long id;
	/**
	 * Field idTransaccion.
	 */
	@Basic(optional = false)
	@Column(name = "ID_TRANSACCION", nullable = false)
	private long idTransaccion;
	/**
	 * Field caja.
	 */
	@Basic(optional = false)
	@Column(name = "CAJA", nullable = false)
	private int caja;
	/**
	 * Field estaactivo.
	 */
	@Basic(optional = false)
	@Column(name = "ESTAACTIVO", nullable = false)
	private char estaactivo;
	/**
	 * Field estreplica.
	 */
	@Basic(optional = false)
	@Column(name = "ESTREPLICA")
	private char estreplica;
	/**
	 * Field idOrdendeventa.
	 */
	@JoinColumn(name = "ID_ORDENDEVENTA", referencedColumnName = "ID", nullable = false)
	@ManyToOne(optional = false)
	private Ordendeventa idOrdendeventa;

	/**
	 * Constructor for Ordendeventatransaccion.
	 */
	public Ordendeventatransaccion() {
	}

	/**
	 * Constructor for Ordendeventatransaccion.
	 * 
	 * @param id
	 *            Long
	 */
	public Ordendeventatransaccion(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Ordendeventatransaccion.
	 * 
	 * @param id
	 *            Long
	 * @param idTransaccion
	 *            long
	 * @param caja
	 *            int
	 * @param estaactivo
	 *            char
	 * @param estreplica
	 *            char
	 */
	public Ordendeventatransaccion(Long id, long idTransaccion, int caja,
			char estaactivo, char estreplica) {
		this.id = id;
		this.idTransaccion = idTransaccion;
		this.caja = caja;
		this.estaactivo = estaactivo;
		this.estreplica = estreplica;
	}

	/**
	 * Method getId.
	 * 
	 * @return Long
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Method setId.
	 * 
	 * @param id
	 *            Long
	 */
	public void setId(Long id) {
		this.id = id;
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
	 * Method getCaja.
	 * 
	 * @return int
	 */
	public int getCaja() {
		return caja;
	}

	/**
	 * Method setCaja.
	 * 
	 * @param caja
	 *            int
	 */
	public void setCaja(int caja) {
		this.caja = caja;
	}

	/**
	 * Method getEstaactivo.
	 * 
	 * @return char
	 */
	public char getEstaactivo() {
		return estaactivo;
	}

	/**
	 * Method setEstaactivo.
	 * 
	 * @param estaactivo
	 *            char
	 */
	public void setEstaactivo(char estaactivo) {
		this.estaactivo = estaactivo;
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
	 * Method getIdOrdendeventa.
	 * 
	 * @return Ordendeventa
	 */
	public Ordendeventa getIdOrdendeventa() {
		return idOrdendeventa;
	}

	/**
	 * Method setIdOrdendeventa.
	 * 
	 * @param idOrdendeventa
	 *            Ordendeventa
	 */
	public void setIdOrdendeventa(Ordendeventa idOrdendeventa) {
		this.idOrdendeventa = idOrdendeventa;
	}

	/**
	 * Method hashCode.
	 * 
	 * @return int
	 */
	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
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
		if (!(object instanceof Ordendeventatransaccion)) {
			return false;
		}
		Ordendeventatransaccion other = (Ordendeventatransaccion) object;
		if ((this.id == null && other.id != null)
				|| (this.id != null && !this.id.equals(other.id))) {
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
		return "crjpa400.Ordendeventatransaccion[ id=" + id + " ]";
	}

}
