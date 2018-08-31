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
@Table(name = "categoria")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Categoria.findAll", query = "SELECT c FROM Categoria c"),
		@NamedQuery(name = "Categoria.findById", query = "SELECT c FROM Categoria c WHERE c.id = :id"),
		@NamedQuery(name = "Categoria.findByCodigo", query = "SELECT c FROM Categoria c WHERE c.codigo = :codigo"),
		@NamedQuery(name = "Categoria.findByNombre", query = "SELECT c FROM Categoria c WHERE c.nombre = :nombre"),
		@NamedQuery(name = "Categoria.findByTipo", query = "SELECT c FROM Categoria c WHERE c.tipo = :tipo"),
		@NamedQuery(name = "Categoria.findByFecha", query = "SELECT c FROM Categoria c WHERE c.fecha = :fecha"),
		@NamedQuery(name = "Categoria.findByEstaactivo", query = "SELECT c FROM Categoria c WHERE c.estaactivo = :estaactivo") })
public class Categoria implements Serializable {
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
	 * Field tipo.
	 */
	@Basic(optional = false)
	@Column(name = "tipo")
	private String tipo;
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
	 * Field lineaList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idCategoria")
	private List<Linea> lineaList;
	/**
	 * Field promocioncategoriaList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idCategoria")
	private List<Promocioncategoria> promocioncategoriaList;

	/**
	 * Constructor for Categoria.
	 */
	public Categoria() {
	}

	/**
	 * Constructor for Categoria.
	 * 
	 * @param id
	 *            Long
	 */
	public Categoria(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Categoria.
	 * 
	 * @param id
	 *            Long
	 * @param codigo
	 *            String
	 * @param nombre
	 *            String
	 * @param tipo
	 *            String
	 * @param fecha
	 *            Date
	 * @param estaactivo
	 *            String
	 */
	public Categoria(Long id, String codigo, String nombre, String tipo, Date fecha, String estaactivo) {
		this.id = id;
		this.codigo = codigo;
		this.nombre = nombre;
		this.tipo = tipo;
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
	 * Method getTipo.
	 * 
	 * @return String
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * Method setTipo.
	 * 
	 * @param tipo
	 *            String
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
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
	 * Method getLineaList.
	 * 
	 * @return List<Linea>
	 */
	@XmlTransient
	public List<Linea> getLineaList() {
		return lineaList;
	}

	/**
	 * Method setLineaList.
	 * 
	 * @param lineaList
	 *            List<Linea>
	 */
	public void setLineaList(List<Linea> lineaList) {
		this.lineaList = lineaList;
	}

	/**
	 * Method getPromocioncategoriaList.
	 * 
	 * @return List<Promocioncategoria>
	 */
	@XmlTransient
	public List<Promocioncategoria> getPromocioncategoriaList() {
		return promocioncategoriaList;
	}

	/**
	 * Method setPromocioncategoriaList.
	 * 
	 * @param promocioncategoriaList
	 *            List<Promocioncategoria>
	 */
	public void setPromocioncategoriaList(List<Promocioncategoria> promocioncategoriaList) {
		this.promocioncategoriaList = promocioncategoriaList;
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
		if (!(object instanceof Categoria)) {
			return false;
		}
		Categoria other = (Categoria) object;
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

		sb.append(enclosed);
		sb.append(codigo);
		sb.append(enclosed);
		sb.append(separator);

		sb.append(enclosed);
		sb.append(nombre);
		sb.append(enclosed);
		sb.append(separator);

		sb.append(tipo);
		sb.append(separator);

		sb.append(fecha == null ? nullStr : simpleDateFormat.format(fecha));
		sb.append(separator);

		sb.append(estaactivo);
		sb.append(endStr);

		return sb.toString();
	}

}
