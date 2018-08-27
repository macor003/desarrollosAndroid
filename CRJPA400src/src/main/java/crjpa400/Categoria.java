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
@Table(name = "CATEGORIA")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Categoria.findAll", query = "SELECT c FROM Categoria c"),
		@NamedQuery(name = "Categoria.findById", query = "SELECT c FROM Categoria c WHERE c.id = :id"),
		@NamedQuery(name = "Categoria.findByCodigo", query = "SELECT c FROM Categoria c WHERE c.codigo = :codigo"),
		@NamedQuery(name = "Categoria.findByNombre", query = "SELECT c FROM Categoria c WHERE c.nombre = :nombre"),
		@NamedQuery(name = "Categoria.findByTipo", query = "SELECT c FROM Categoria c WHERE c.tipo = :tipo"),
		@NamedQuery(name = "Categoria.findByFecha", query = "SELECT c FROM Categoria c WHERE c.fecha = :fecha"),
		@NamedQuery(name = "Categoria.findByUltimasincronizacion", query = "SELECT c FROM Categoria c WHERE c.ultimasincronizacion = :ultimasincronizacion"),
		@NamedQuery(name = "Categoria.findByEstaactivo", query = "SELECT c FROM Categoria c WHERE c.estaactivo = :estaactivo"),
		@NamedQuery(name = "Categoria.findByEstreplica", query = "SELECT c FROM Categoria c WHERE c.estreplica = :estreplica") })
public class Categoria extends CrjpaInterface implements Serializable {
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
	 * Field tipo.
	 */
	@Basic(optional = false)
	@Column(name = "TIPO", nullable = false)
	private char tipo;
	/**
	 * Field fecha.
	 */
	@Basic(optional = false)
	@Column(name = "FECHA", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	/**
	 * Field ultimasincronizacion.
	 */
	@Basic(optional = false)
	@Column(name = "ULTIMASINCRONIZACION", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar ultimasincronizacion;
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
	 *            char
	 * @param fecha
	 *            Date
	 * @param ultimasincronizacion
	 *            Calendar
	 * @param estaactivo
	 *            char
	 * @param estreplica
	 *            char
	 */
	public Categoria(Long id, String codigo, String nombre, char tipo,
			Date fecha, Calendar ultimasincronizacion, char estaactivo,
			char estreplica) {
		this.id = id;
		this.codigo = codigo;
		this.nombre = nombre;
		this.tipo = tipo;
		this.fecha = fecha;
		this.ultimasincronizacion = ultimasincronizacion;
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
	 * @return char
	 */
	public char getTipo() {
		return tipo;
	}

	/**
	 * Method setTipo.
	 * 
	 * @param tipo
	 *            char
	 */
	public void setTipo(char tipo) {
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
	 * @param ultimasincronizacion
	 *            Calendar
	 */
	public void setUltimasincronizacion(Calendar ultimasincronizacion) {
		this.ultimasincronizacion = ultimasincronizacion;
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
	public void setPromocioncategoriaList(
			List<Promocioncategoria> promocioncategoriaList) {
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
		return "crjpa400.Categoria[ id=" + id + " ]";
	}

}
