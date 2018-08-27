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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "EXONERADOARTICULO")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Exoneradoarticulo.findAll", query = "SELECT e FROM Exoneradoarticulo e"),
		@NamedQuery(name = "Exoneradoarticulo.findByIdArticulo", query = "SELECT e FROM Exoneradoarticulo e WHERE e.exoneradoarticuloPK.idArticulo = :idArticulo"),
		@NamedQuery(name = "Exoneradoarticulo.findByIdExoneradotipo", query = "SELECT e FROM Exoneradoarticulo e WHERE e.exoneradoarticuloPK.idExoneradotipo = :idExoneradotipo"),
		@NamedQuery(name = "Exoneradoarticulo.findByUltimasincronizacion", query = "SELECT e FROM Exoneradoarticulo e WHERE e.ultimasincronizacion = :ultimasincronizacion"),
		@NamedQuery(name = "Exoneradoarticulo.findByEstreplica", query = "SELECT e FROM Exoneradoarticulo e WHERE e.estreplica = :estreplica") })
public class Exoneradoarticulo extends CrjpaInterface implements Serializable {
	/**
	 * Field serialVersionUID. (value is 1)
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Field exoneradoarticuloPK.
	 */
	@EmbeddedId
	protected ExoneradoarticuloPK exoneradoarticuloPK;
	/**
	 * Field ultimasincronizacion.
	 */
	@Basic(optional = false)
	@Column(name = "ULTIMASINCRONIZACION", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar ultimasincronizacion;
	/**
	 * Field estreplica.
	 */
	@Basic(optional = false)
	@Column(name = "ESTREPLICA")
	private char estreplica;
	/**
	 * Field exoneradotipo.
	 */
	@JoinColumn(name = "ID_EXONERADOTIPO", referencedColumnName = "ID", insertable = false, updatable = false)
	@ManyToOne(optional = false)
	private Exoneradotipo exoneradotipo;
	/**
	 * Field articulo.
	 */
	@JoinColumn(name = "ID_ARTICULO", referencedColumnName = "ID", insertable = false, updatable = false)
	@ManyToOne(optional = false)
	private Articulo articulo;
	/**
	 * Field estaactivo.
	 */
	@Basic(optional = false)
	@Column(name = "ESTAACTIVO", nullable = false)
	private char estaactivo;

	/**
	 * Constructor for Exoneradoarticulo.
	 */
	public Exoneradoarticulo() {
	}

	/**
	 * Constructor for Exoneradoarticulo.
	 * 
	 * @param exoneradoarticuloPK
	 *            ExoneradoarticuloPK
	 */
	public Exoneradoarticulo(ExoneradoarticuloPK exoneradoarticuloPK) {
		this.exoneradoarticuloPK = exoneradoarticuloPK;
	}

	/**
	 * Constructor for Exoneradoarticulo.
	 * 
	 * @param exoneradoarticuloPK
	 *            ExoneradoarticuloPK
	 * @param ultimasincronizacion
	 *            Calendar
	 * @param estreplica
	 *            char
	 */
	public Exoneradoarticulo(ExoneradoarticuloPK exoneradoarticuloPK,
			Calendar ultimasincronizacion, char estreplica) {
		this.exoneradoarticuloPK = exoneradoarticuloPK;
		this.ultimasincronizacion = ultimasincronizacion;
		this.estreplica = estreplica;
	}

	/**
	 * Constructor for Exoneradoarticulo.
	 * 
	 * @param idArticulo
	 *            long
	 * @param idExoneradotipo
	 *            long
	 */
	public Exoneradoarticulo(long idArticulo, long idExoneradotipo) {
		this.exoneradoarticuloPK = new ExoneradoarticuloPK(idArticulo,
				idExoneradotipo);
	}

	/**
	 * Method getExoneradoarticuloPK.
	 * 
	 * @return ExoneradoarticuloPK
	 */
	public ExoneradoarticuloPK getExoneradoarticuloPK() {
		return exoneradoarticuloPK;
	}

	/**
	 * Method setExoneradoarticuloPK.
	 * 
	 * @param exoneradoarticuloPK
	 *            ExoneradoarticuloPK
	 */
	public void setExoneradoarticuloPK(ExoneradoarticuloPK exoneradoarticuloPK) {
		this.exoneradoarticuloPK = exoneradoarticuloPK;
	}

	/**
	 * Method getUltimasincronizacion.
	 * 
	 * @return Calendar
	 */
	public Calendar getUltimasincronizacion() {
		return ultimasincronizacion;
	}

	/**
	 * Method setUltimasincronizacion.
	 * 
	 * @param ultimasincronizacion
	 *            Calendar
	 */
	public void setUltimasincronizacion(Calendar ultimasincronizacion) {
		this.ultimasincronizacion = ultimasincronizacion;
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
	 * Method getExoneradotipo.
	 * 
	 * @return Exoneradotipo
	 */
	public Exoneradotipo getExoneradotipo() {
		return exoneradotipo;
	}

	/**
	 * Method setExoneradotipo.
	 * 
	 * @param exoneradotipo
	 *            Exoneradotipo
	 */
	public void setExoneradotipo(Exoneradotipo exoneradotipo) {
		this.exoneradotipo = exoneradotipo;
	}

	/**
	 * Method getArticulo.
	 * 
	 * @return Articulo
	 */
	public Articulo getArticulo() {
		return articulo;
	}

	/**
	 * Method setArticulo.
	 * 
	 * @param articulo
	 *            Articulo
	 */
	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
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
	 * Method hashCode.
	 * 
	 * @return int
	 */
	@Override
	public int hashCode() {
		int hash = 0;
		hash += (exoneradoarticuloPK != null ? exoneradoarticuloPK.hashCode()
				: 0);
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
		if (!(object instanceof Exoneradoarticulo)) {
			return false;
		}
		Exoneradoarticulo other = (Exoneradoarticulo) object;
		if ((this.exoneradoarticuloPK == null && other.exoneradoarticuloPK != null)
				|| (this.exoneradoarticuloPK != null && !this.exoneradoarticuloPK
						.equals(other.exoneradoarticuloPK))) {
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
		return "crjpa400.Exoneradoarticulo[ exoneradoarticuloPK="
				+ exoneradoarticuloPK + " ]";
	}

}
