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
@Table(name = "ARTICULOSERVICIO")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Articuloservicio.findAll", query = "SELECT a FROM Articuloservicio a"),
		@NamedQuery(name = "Articuloservicio.findByIdArticulo", query = "SELECT a FROM Articuloservicio a WHERE a.articuloservicioPK.idArticulo = :idArticulo"),
		@NamedQuery(name = "Articuloservicio.findByIdServicio", query = "SELECT a FROM Articuloservicio a WHERE a.articuloservicioPK.idServicio = :idServicio"),
		@NamedQuery(name = "Articuloservicio.findByUltimasincronizacion", query = "SELECT a FROM Articuloservicio a WHERE a.ultimasincronizacion = :ultimasincronizacion"),
		@NamedQuery(name = "Articuloservicio.findByEstreplica", query = "SELECT a FROM Articuloservicio a WHERE a.estreplica = :estreplica") })
public class Articuloservicio extends CrjpaInterface implements Serializable {
	/**
	 * Field serialVersionUID. (value is 1)
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Field articuloservicioPK.
	 */
	@EmbeddedId
	protected ArticuloservicioPK articuloservicioPK;
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
	 * Field servicio.
	 */
	@JoinColumn(name = "ID_SERVICIO", referencedColumnName = "ID", insertable = false, updatable = false, nullable = false)
	@ManyToOne(optional = false)
	private Servicio servicio;
	/**
	 * Field articulo.
	 */
	@JoinColumn(name = "ID_ARTICULO", referencedColumnName = "ID", insertable = false, updatable = false, nullable = false)
	@ManyToOne(optional = false)
	private Articulo articulo;

	/**
	 * Constructor for Articuloservicio.
	 */
	public Articuloservicio() {
	}

	/**
	 * Constructor for Articuloservicio.
	 * 
	 * @param articuloservicioPK
	 *            ArticuloservicioPK
	 */
	public Articuloservicio(ArticuloservicioPK articuloservicioPK) {
		this.articuloservicioPK = articuloservicioPK;
	}

	/**
	 * Constructor for Articuloservicio.
	 * 
	 * @param articuloservicioPK
	 *            ArticuloservicioPK
	 * @param ultimasincronizacion
	 *            Calendar
	 * @param estreplica
	 *            char
	 */
	public Articuloservicio(ArticuloservicioPK articuloservicioPK,
			Calendar ultimasincronizacion, char estreplica) {
		this.articuloservicioPK = articuloservicioPK;
		this.ultimasincronizacion = ultimasincronizacion;
		this.estreplica = estreplica;
	}

	/**
	 * Constructor for Articuloservicio.
	 * 
	 * @param idArticulo
	 *            long
	 * @param idServicio
	 *            long
	 */
	public Articuloservicio(long idArticulo, long idServicio) {
		this.articuloservicioPK = new ArticuloservicioPK(idArticulo, idServicio);
	}

	/**
	 * Method getArticuloservicioPK.
	 * 
	 * @return ArticuloservicioPK
	 */
	public ArticuloservicioPK getArticuloservicioPK() {
		return articuloservicioPK;
	}

	/**
	 * Method setArticuloservicioPK.
	 * 
	 * @param articuloservicioPK
	 *            ArticuloservicioPK
	 */
	public void setArticuloservicioPK(ArticuloservicioPK articuloservicioPK) {
		this.articuloservicioPK = articuloservicioPK;
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
	 * Method getServicio.
	 * 
	 * @return Servicio
	 */
	public Servicio getServicio() {
		return servicio;
	}

	/**
	 * Method setServicio.
	 * 
	 * @param servicio
	 *            Servicio
	 */
	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
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
	 * Method hashCode.
	 * 
	 * @return int
	 */
	@Override
	public int hashCode() {
		int hash = 0;
		hash += (articuloservicioPK != null ? articuloservicioPK.hashCode() : 0);
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
		if (!(object instanceof Articuloservicio)) {
			return false;
		}
		Articuloservicio other = (Articuloservicio) object;
		if ((this.articuloservicioPK == null && other.articuloservicioPK != null)
				|| (this.articuloservicioPK != null && !this.articuloservicioPK
						.equals(other.articuloservicioPK))) {
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
		return "crjpa400.Articuloservicio[ articuloservicioPK="
				+ articuloservicioPK + " ]";
	}

}
