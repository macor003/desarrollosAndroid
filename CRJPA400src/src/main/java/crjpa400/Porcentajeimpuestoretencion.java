/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package crjpa400;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import crjpa400.interfaces.CrjpaInterface;

/**
 * @version $Revision: 1.0 $
 */
@Entity
@Table(name = "PORCENTAJEIMPUESTORETENCION")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Porcentajeimpuestoretencion.findAll", query = "SELECT p FROM Porcentajeimpuestoretencion p"),
		@NamedQuery(name = "Porcentajeimpuestoretencion.findById", query = "SELECT p FROM Porcentajeimpuestoretencion p WHERE p.id = :id"),
		@NamedQuery(name = "Porcentajeimpuestoretencion.findByPorcentajeMinRetencion", query = "SELECT p FROM Porcentajeimpuestoretencion p WHERE p.porcentajeMinRetencion = :porcentajeMinRetencion"),
		@NamedQuery(name = "Porcentajeimpuestoretencion.findByPorcentajeMaxRetencion", query = "SELECT p FROM Porcentajeimpuestoretencion p WHERE p.porcentajeMaxRetencion = :porcentajeMaxRetencion"),
		@NamedQuery(name = "Porcentajeimpuestoretencion.findByTipoArtRetencion", query = "SELECT p FROM Porcentajeimpuestoretencion p WHERE p.tipoArtRetencion = :tipoArtRetencion"),
		@NamedQuery(name = "Porcentajeimpuestoretencion.findByFecha", query = "SELECT p FROM Porcentajeimpuestoretencion p WHERE p.fecha = :fecha"),
		@NamedQuery(name = "Porcentajeimpuestoretencion.findByEstaactivo", query = "SELECT p FROM Porcentajeimpuestoretencion p WHERE p.estaactivo = :estaactivo"),
		@NamedQuery(name = "Porcentajeimpuestoretencion.findByUltimasincronizacion", query = "SELECT p FROM Porcentajeimpuestoretencion p WHERE p.ultimasincronizacion = :ultimasincronizacion"),
		@NamedQuery(name = "Porcentajeimpuestoretencion.findByEstreplica", query = "SELECT p FROM Porcentajeimpuestoretencion p WHERE p.estreplica = :estreplica"),
		@NamedQuery(name = "Porcentajeimpuestoretencion.findByGrupo", query = "SELECT p FROM Porcentajeimpuestoretencion p WHERE p.grupo = :grupo")})

public class Porcentajeimpuestoretencion extends CrjpaInterface implements
		Serializable {
	/**
	 * Field serialVersionUID. (value is 1)
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Field id.
	 */
	@Id
	@Basic(optional = false)
	@Column(name = "ID", nullable = false)
	private Long id;
	// @Max(value=?) @Min(value=?)//if you know range of your decimal fields
	// consider using these annotations to enforce field validation
	/**
	 * Field porcentajeMinRetencion.
	 */
	@Basic(optional = false)
	@Column(name = "PORCENTAJE_MIN_RETENCION", nullable = false, precision = 13, scale = 2)
	private BigDecimal porcentajeMinRetencion;
	/**
	 * Field porcentajeMaxRetencion.
	 */
	@Basic(optional = false)
	@Column(name = "PORCENTAJE_MAX_RETENCION", nullable = false, precision = 13, scale = 2)
	private BigDecimal porcentajeMaxRetencion;
	/**
	 * Field tipoArtRetencion.
	 */
	@Basic(optional = false)
	@Column(name = "TIPO_ART_RETENCION", nullable = false)
	private char tipoArtRetencion;
	/**
	 * Field fecha.
	 */
	@Basic(optional = false)
	@Column(name = "FECHA", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	/**
	 * Field estaactivo.
	 */
	@Basic(optional = false)
	@Column(name = "ESTAACTIVO", nullable = false)
	private char estaactivo;
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
	 * Field idFormadepago.
	 */
	@JoinColumn(name = "ID_FORMADEPAGO", referencedColumnName = "ID", nullable = false)
	@ManyToOne(optional = false)
	private Formadepago idFormadepago;
	/**
	 * Field categorialinearetencionList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idPorcentajeimpuestoretencion", orphanRemoval = true)
	private List<Categorialinearetencion> categorialinearetencionList;
	
	/**
	 * Field grupo.
	 */
	@Basic(optional = false)
	@Column(name = "GRUPO", nullable = false)
	private int grupo;

	/**
	 * Constructor for Porcentajeimpuestoretencion.
	 */
	public Porcentajeimpuestoretencion() {
	}

	/**
	 * Constructor for Porcentajeimpuestoretencion.
	 * 
	 * @param id
	 *            Long
	 */
	public Porcentajeimpuestoretencion(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Porcentajeimpuestoretencion.
	 * 
	 * @param id Long
	 * @param porcentajeMinRetencion BigDecimal
	 * @param porcentajeMaxRetencion BigDecimal
	 * @param tipoArtRetencion char
	 * @param fecha Date
	 * @param estaactivo char
	 * @param ultimasincronizacion Calendar
	 * @param estreplica char
	 */
	public Porcentajeimpuestoretencion(Long id,
			BigDecimal porcentajeMinRetencion,
			BigDecimal porcentajeMaxRetencion, char tipoArtRetencion,
			Date fecha, char estaactivo, Calendar ultimasincronizacion,
			char estreplica) {
		this.id = id;
		this.porcentajeMinRetencion = porcentajeMinRetencion;
		this.porcentajeMaxRetencion = porcentajeMaxRetencion;
		this.tipoArtRetencion = tipoArtRetencion;
		this.fecha = fecha;
		this.estaactivo = estaactivo;
		this.ultimasincronizacion = ultimasincronizacion;
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
	 * @param id Long
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Method getPorcentajeMinRetencion.
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal getPorcentajeMinRetencion() {
		return porcentajeMinRetencion;
	}

	/**
	 * Method setPorcentajeMinRetencion.
	 * 
	 * @param porcentajeMinRetencion BigDecimal
	 */
	public void setPorcentajeMinRetencion(BigDecimal porcentajeMinRetencion) {
		this.porcentajeMinRetencion = porcentajeMinRetencion;
	}

	/**
	 * Method getPorcentajeMaxRetencion.
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal getPorcentajeMaxRetencion() {
		return porcentajeMaxRetencion;
	}

	/**
	 * Method setPorcentajeMaxRetencion.
	 * 
	 * @param porcentajeMaxRetencion BigDecimal
	 */
	public void setPorcentajeMaxRetencion(BigDecimal porcentajeMaxRetencion) {
		this.porcentajeMaxRetencion = porcentajeMaxRetencion;
	}

	/**
	 * Method getTipoArtRetencion.
	 * 
	 * @return char
	 */
	public char getTipoArtRetencion() {
		return tipoArtRetencion;
	}

	/**
	 * Method setTipoArtRetencion.
	 * 
	 * @param tipoArtRetencion char
	 */
	public void setTipoArtRetencion(char tipoArtRetencion) {
		this.tipoArtRetencion = tipoArtRetencion;
	}

	/**
	 * Method getFecha.
	 * 
	 * @return Date
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * Method setFecha.
	 * 
	 * @param fecha Date
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
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
	 * @param estaactivo char
	 */
	public void setEstaactivo(char estaactivo) {
		this.estaactivo = estaactivo;
	}

	/**
	 * Method getUltimasincronizacion.
	 * 
	 * @return Calendar
	 */
	@Override
	public Calendar getUltimasincronizacion() {
		return ultimasincronizacion;
	}

	/**
	 * Method setUltimasincronizacion.
	 * 
	 * @param ultimasincronizacion Calendar
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
	 * @param estreplica char
	 */
	public void setEstreplica(char estreplica) {
		this.estreplica = estreplica;
	}

	/**
	 * Method getIdFormadepago.
	 * 
	 * @return Formadepago
	 */
	public Formadepago getIdFormadepago() {
		return idFormadepago;
	}

	/**
	 * Method setIdFormadepago.
	 * 
	 * @param idFormadepago Formadepago
	 */
	public void setIdFormadepago(Formadepago idFormadepago) {
		this.idFormadepago = idFormadepago;
	}
	
	
	public int getGrupo() {
		return grupo;
	}

	public void setGrupo(int grupo) {
		this.grupo = grupo;
	}

	/**
	 * Method getCategorialinearetencionList.
	 * 
	 * @return List<Categorialinearetencion>
	 */
	@XmlTransient
	public List<Categorialinearetencion> getCategorialinearetencionList() {
		return categorialinearetencionList;
	}

	/**
	 * Method setCategorialinearetencionList.
	 * 
	 * @param categorialinearetencionList List<Categorialinearetencion>
	 */
	public void setCategorialinearetencionList(List<Categorialinearetencion> categorialinearetencionList) {
		this.categorialinearetencionList = categorialinearetencionList;
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
	 * @param object Object
	 * @return boolean
	 */
	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Porcentajeimpuestoretencion)) {
			return false;
		}
		Porcentajeimpuestoretencion other = (Porcentajeimpuestoretencion) object;
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
		return "crjpa400.Porcentajeimpuestoretencion[ id=" + id + " ]";
	}

}
