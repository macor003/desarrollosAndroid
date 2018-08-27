/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package crjpa;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
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

/**
 * @version $Revision: 1.0 $
 */
@Entity
@Table(name = "porcentajeimpuestoretencion")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Porcentajeimpuestoretencion.findAll", query = "SELECT p FROM Porcentajeimpuestoretencion p"),
		@NamedQuery(name = "Porcentajeimpuestoretencion.findById", query = "SELECT p FROM Porcentajeimpuestoretencion p WHERE p.id = :id"),
		@NamedQuery(name = "Porcentajeimpuestoretencion.findByPorcentajeMinRetencion", query = "SELECT p FROM Porcentajeimpuestoretencion p WHERE p.porcentajeMinRetencion = :porcentajeMinRetencion"),
		@NamedQuery(name = "Porcentajeimpuestoretencion.findByPorcentajeMaxRetencion", query = "SELECT p FROM Porcentajeimpuestoretencion p WHERE p.porcentajeMaxRetencion = :porcentajeMaxRetencion"),
		@NamedQuery(name = "Porcentajeimpuestoretencion.findByTipoArtRetencion", query = "SELECT p FROM Porcentajeimpuestoretencion p WHERE p.tipoArtRetencion = :tipoArtRetencion"),
		@NamedQuery(name = "Porcentajeimpuestoretencion.findByFecha", query = "SELECT p FROM Porcentajeimpuestoretencion p WHERE p.fecha = :fecha"),
		@NamedQuery(name = "Porcentajeimpuestoretencion.findByEstaactivo", query = "SELECT p FROM Porcentajeimpuestoretencion p WHERE p.estaactivo = :estaactivo ORDER BY p.grupo"),
		@NamedQuery(name = "Porcentajeimpuestoretencion.findByGrupo", query = "SELECT p FROM Porcentajeimpuestoretencion p WHERE p.grupo = :grupo")})
public class Porcentajeimpuestoretencion implements Serializable {
	/**
	 * Field serialVersionUID. (value is 1)
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Field id.
	 */
	@Id
	@Basic(optional = false)
	@Column(name = "id")
	private Long id;
	// @Max(value=?) @Min(value=?)//if you know range of your decimal fields
	// consider using these annotations to enforce field validation
	/**
	 * Field porcentajeMinRetencion.
	 */
	@Basic(optional = false)
	@Column(name = "porcentaje_min_retencion")
	private BigDecimal porcentajeMinRetencion;
	/**
	 * Field porcentajeMaxRetencion.
	 */
	@Basic(optional = false)
	@Column(name = "porcentaje_max_retencion")
	private BigDecimal porcentajeMaxRetencion;
	/**
	 * Field tipoArtRetencion.
	 */
	@Basic(optional = false)
	@Column(name = "tipo_art_retencion")
	private String tipoArtRetencion;
	/**
	 * Field fecha.
	 */
	@Basic(optional = false)
	@Column(name = "fecha")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	/**
	 * Field estaactivo.
	 */
	@Basic(optional = false)
	@Column(name = "estaactivo")
	private String estaactivo;
	/**
	 * Field idFormadepago.
	 */
	@JoinColumn(name = "id_formadepago", referencedColumnName = "id")
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
	@Column(name = "grupo", nullable = false)
	private int grupo;
	
	/**
	 * Constructor for Porcentajeimpuestoretencion.
	 */
	public Porcentajeimpuestoretencion() {
	}

	/**
	 * Constructor for Porcentajeimpuestoretencion.
	 * 
	 * @param id Long
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
	 * @param tipoArtRetencion String
	 * @param fecha Date
	 * @param estaactivo String
	 */
	public Porcentajeimpuestoretencion(Long id, BigDecimal porcentajeMinRetencion, BigDecimal porcentajeMaxRetencion,
			String tipoArtRetencion, Date fecha, String estaactivo) {
		this.id = id;
		this.porcentajeMinRetencion = porcentajeMinRetencion;
		this.porcentajeMaxRetencion = porcentajeMaxRetencion;
		this.tipoArtRetencion = tipoArtRetencion;
		this.fecha = fecha;
		this.estaactivo = estaactivo;
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
	 * @return String
	 */
	public String getTipoArtRetencion() {
		return tipoArtRetencion;
	}

	/**
	 * Method setTipoArtRetencion.
	 * 
	 * @param tipoArtRetencion String
	 */
	public void setTipoArtRetencion(String tipoArtRetencion) {
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
	 * @return String
	 */
	public String getEstaactivo() {
		return estaactivo;
	}

	/**
	 * Method setEstaactivo.
	 * 
	 * @param estaactivo String
	 */
	public void setEstaactivo(String estaactivo) {
		this.estaactivo = estaactivo;
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
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
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
		String separator = "@@";
		String nullStr = "\\N";
		String endStr = "\r\n";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		StringBuilder sb = new StringBuilder();

		sb.append(id);
		sb.append(separator);

		sb.append(idFormadepago.getId());
		sb.append(separator);
		
		sb.append(porcentajeMinRetencion);
		sb.append(separator);

		sb.append(porcentajeMaxRetencion);
		sb.append(separator);

		sb.append(tipoArtRetencion);
		sb.append(separator);

		sb.append(fecha == null ? nullStr : simpleDateFormat.format(fecha));
		sb.append(separator);

		sb.append(estaactivo);
		sb.append(separator);
		
		sb.append(grupo);
		sb.append(endStr);
		return sb.toString();
	}

}
