/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crjpa;

import java.io.Serializable;
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
 * 
 * @author eve0014321
 * @version $Revision: 1.0 $
 */
@Entity
@Table(name = "linea")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Linea.findAll", query = "SELECT l FROM Linea l"),
		@NamedQuery(name = "Linea.findById", query = "SELECT l FROM Linea l WHERE l.id = :id"),
		@NamedQuery(name = "Linea.findByCodigo", query = "SELECT l FROM Linea l WHERE l.codigo = :codigo"),
		@NamedQuery(name = "Linea.findByNombre", query = "SELECT l FROM Linea l WHERE l.nombre = :nombre"),
		@NamedQuery(name = "Linea.findByFecha", query = "SELECT l FROM Linea l WHERE l.fecha = :fecha"),
		@NamedQuery(name = "Linea.findByPermiterebaja", query = "SELECT l FROM Linea l WHERE l.permiterebaja = :permiterebaja"),
		@NamedQuery(name = "Linea.findByEstaactivo", query = "SELECT l FROM Linea l WHERE l.estaactivo = :estaactivo") })
public class Linea implements Serializable {
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
	/**
	 * Field codigo.
	 */
	@Basic(optional = false)
	@Column(name = "codigo")
	private String codigo;
	/**
	 * Field nombre.
	 */
	@Basic(optional = false)
	@Column(name = "nombre")
	private String nombre;
	/**
	 * Field fecha.
	 */
	@Basic(optional = false)
	@Column(name = "fecha")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	/**
	 * Field permiterebaja.
	 */
	@Basic(optional = false)
	@Column(name = "permiterebaja")
	private String permiterebaja;
	/**
	 * Field estaactivo.
	 */
	@Basic(optional = false)
	@Column(name = "estaactivo")
	private String estaactivo;
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
	 * Field idCategoria.
	 */
	@JoinColumn(name = "id_categoria", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Categoria idCategoria;
	/**
	 * Field categorialineaincluyeList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idLinea")
	private List<Categorialineaincluye> categorialineaincluyeList;
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
	 *            String
	 * @param estaactivo
	 *            String
	 */
	public Linea(Long id, String codigo, String nombre, Date fecha, String permiterebaja, String estaactivo) {
		this.id = id;
		this.codigo = codigo;
		this.nombre = nombre;
		this.fecha = fecha;
		this.permiterebaja = permiterebaja;
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
	 * @return String
	 */
	public String getPermiterebaja() {
		return permiterebaja;
	}

	/**
	 * Method setPermiterebaja.
	 * 
	 * @param permiterebaja
	 *            String
	 */
	public void setPermiterebaja(String permiterebaja) {
		this.permiterebaja = permiterebaja;
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
	 * @param estaactivo
	 *            String
	 */
	public void setEstaactivo(String estaactivo) {
		this.estaactivo = estaactivo;
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
	public void setCategorialineaincluyeList(List<Categorialineaincluye> categorialineaincluyeList) {
		this.categorialineaincluyeList = categorialineaincluyeList;
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
		String enclosed = "|";
		String nullStr = "\\N";
		String endStr = "\r\n";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		StringBuilder sb = new StringBuilder();

		sb.append(id);
		sb.append(separator);

		sb.append(idCategoria.getId());
		sb.append(separator);

		sb.append(enclosed);
		sb.append(codigo);
		sb.append(enclosed);
		sb.append(separator);

		sb.append(enclosed);
		sb.append(nombre);
		sb.append(enclosed);
		sb.append(separator);

		sb.append(fecha == null ? nullStr : simpleDateFormat.format(fecha));
		sb.append(separator);

		sb.append(permiterebaja);
		sb.append(separator);

		sb.append(estaactivo);
		sb.append(endStr);

		return sb.toString();
	}

}
