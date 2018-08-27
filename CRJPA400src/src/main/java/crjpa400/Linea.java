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
 * 
 * @author eve0014321
 * @version $Revision: 1.0 $
 */
@Entity
@Table(name = "LINEA")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Linea.findAll", query = "SELECT l FROM Linea l"),
		@NamedQuery(name = "Linea.findById", query = "SELECT l FROM Linea l WHERE l.id = :id"),
		@NamedQuery(name = "Linea.findByCodigo", query = "SELECT l FROM Linea l WHERE l.codigo = :codigo"),
		@NamedQuery(name = "Linea.findByNombre", query = "SELECT l FROM Linea l WHERE l.nombre = :nombre"),
		@NamedQuery(name = "Linea.findByFecha", query = "SELECT l FROM Linea l WHERE l.fecha = :fecha"),
		@NamedQuery(name = "Linea.findByPermiterebaja", query = "SELECT l FROM Linea l WHERE l.permiterebaja = :permiterebaja"),
		@NamedQuery(name = "Linea.findByEstaactivo", query = "SELECT l FROM Linea l WHERE l.estaactivo = :estaactivo"),
		@NamedQuery(name = "Linea.findByUltimasincronizacion", query = "SELECT l FROM Linea l WHERE l.ultimasincronizacion = :ultimasincronizacion"),
		@NamedQuery(name = "Linea.findByEstreplica", query = "SELECT l FROM Linea l WHERE l.estreplica = :estreplica") })
public class Linea extends CrjpaInterface implements Serializable {
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
	/**
	 * Field codigo.
	 */
	@Basic(optional = false)
	@Column(name = "CODIGO", nullable = false, length = 2)
	private String codigo;
	/**
	 * Field nombre.
	 */
	@Basic(optional = false)
	@Column(name = "NOMBRE", nullable = false, length = 40)
	private String nombre;
	/**
	 * Field fecha.
	 */
	@Basic(optional = false)
	@Column(name = "FECHA", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	/**
	 * Field permiterebaja.
	 */
	@Basic(optional = false)
	@Column(name = "PERMITEREBAJA", nullable = false)
	private char permiterebaja;
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
	 * Field idCategoria.
	 */
	@JoinColumn(name = "ID_CATEGORIA", referencedColumnName = "ID", nullable = false)
	@ManyToOne(optional = false)
	private Categoria idCategoria;
	/**
	 * Field categorialineaincluyeList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idLinea")
	private List<Categorialineaincluye> categorialineaincluyeList;
	/**
	 * Field promocionlineaList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idLinea")
	private List<Promocionlinea> promocionlineaList;
	/**
	 * Field familiaList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idLinea")
	private List<Familia> familiaList;
	/**
	 * Field articuloList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idLinea")
	private List<Articulo> articuloList;
	/**
	 * Field categorialinearetencionList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idLinea")
	private List<Categorialinearetencion> categorialinearetencionList;

	/**
	 * Constructor for Linea.
	 */
	public Linea() {
	}

	/**
	 * Constructor for Linea.
	 * 
	 * @param id
	 *            Long
	 */
	public Linea(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Linea.
	 * 
	 * @param id
	 *            Long
	 * @param codigo
	 *            String
	 * @param nombre
	 *            String
	 * @param fecha
	 *            Date
	 * @param permiterebaja
	 *            char
	 * @param estaactivo
	 *            char
	 * @param ultimasincronizacion
	 *            Calendar
	 * @param estreplica
	 *            char
	 */
	public Linea(Long id, String codigo, String nombre, Date fecha,
			char permiterebaja, char estaactivo, Calendar ultimasincronizacion,
			char estreplica) {
		this.id = id;
		this.codigo = codigo;
		this.nombre = nombre;
		this.fecha = fecha;
		this.permiterebaja = permiterebaja;
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
	 * @param id
	 *            Long
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Method getCodigo.
	 * 
	 * @return String
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * Method setCodigo.
	 * 
	 * @param codigo
	 *            String
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * Method getNombre.
	 * 
	 * @return String
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Method setNombre.
	 * 
	 * @param nombre
	 *            String
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
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
	 * @param fecha
	 *            Date
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/**
	 * Method getPermiterebaja.
	 * 
	 * @return char
	 */
	public char getPermiterebaja() {
		return permiterebaja;
	}

	/**
	 * Method setPermiterebaja.
	 * 
	 * @param permiterebaja
	 *            char
	 */
	public void setPermiterebaja(char permiterebaja) {
		this.permiterebaja = permiterebaja;
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
	 * Method getIdCategoria.
	 * 
	 * @return Categoria
	 */
	public Categoria getIdCategoria() {
		return idCategoria;
	}

	/**
	 * Method setIdCategoria.
	 * 
	 * @param idCategoria
	 *            Categoria
	 */
	public void setIdCategoria(Categoria idCategoria) {
		this.idCategoria = idCategoria;
	}

	/**
	 * Method getCategorialineaincluyeList.
	 * 
	 * @return List<Categorialineaincluye>
	 */
	@XmlTransient
	public List<Categorialineaincluye> getCategorialineaincluyeList() {
		return categorialineaincluyeList;
	}

	/**
	 * Method setCategorialineaincluyeList.
	 * 
	 * @param categorialineaincluyeList
	 *            List<Categorialineaincluye>
	 */
	public void setCategorialineaincluyeList(
			List<Categorialineaincluye> categorialineaincluyeList) {
		this.categorialineaincluyeList = categorialineaincluyeList;
	}

	/**
	 * Method getPromocionlineaList.
	 * 
	 * @return List<Promocionlinea>
	 */
	@XmlTransient
	public List<Promocionlinea> getPromocionlineaList() {
		return promocionlineaList;
	}

	/**
	 * Method setPromocionlineaList.
	 * 
	 * @param promocionlineaList
	 *            List<Promocionlinea>
	 */
	public void setPromocionlineaList(List<Promocionlinea> promocionlineaList) {
		this.promocionlineaList = promocionlineaList;
	}

	/**
	 * Method getFamiliaList.
	 * 
	 * @return List<Familia>
	 */
	@XmlTransient
	public List<Familia> getFamiliaList() {
		return familiaList;
	}

	/**
	 * Method setFamiliaList.
	 * 
	 * @param familiaList
	 *            List<Familia>
	 */
	public void setFamiliaList(List<Familia> familiaList) {
		this.familiaList = familiaList;
	}

	/**
	 * Method getArticuloList.
	 * 
	 * @return List<Articulo>
	 */
	@XmlTransient
	public List<Articulo> getArticuloList() {
		return articuloList;
	}

	/**
	 * Method setArticuloList.
	 * 
	 * @param articuloList
	 *            List<Articulo>
	 */
	public void setArticuloList(List<Articulo> articuloList) {
		this.articuloList = articuloList;
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
	 * @param categorialinearetencionList
	 *            List<Categorialinearetencion>
	 */
	public void setCategorialinearetencionList(
			List<Categorialinearetencion> categorialinearetencionList) {
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
	 * @param object
	 *            Object
	 * @return boolean
	 */
	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Linea)) {
			return false;
		}
		Linea other = (Linea) object;
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
		return "crjpa400.Linea[ id=" + id + " ]";
	}

}
