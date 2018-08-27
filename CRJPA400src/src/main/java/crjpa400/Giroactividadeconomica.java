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
@Table(name = "GIROACTIVIDADECONOMICA")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Giroactividadeconomica.findAll", query = "SELECT g FROM Giroactividadeconomica g"),
		@NamedQuery(name = "Giroactividadeconomica.findById", query = "SELECT g FROM Giroactividadeconomica g WHERE g.id = :id"),
		@NamedQuery(name = "Giroactividadeconomica.findByCodigo", query = "SELECT g FROM Giroactividadeconomica g WHERE g.codigo = :codigo"),
		@NamedQuery(name = "Giroactividadeconomica.findByDescripcion", query = "SELECT g FROM Giroactividadeconomica g WHERE g.descripcion = :descripcion"),
		@NamedQuery(name = "Giroactividadeconomica.findByCategoria", query = "SELECT g FROM Giroactividadeconomica g WHERE g.categoria = :categoria"),
		@NamedQuery(name = "Giroactividadeconomica.findByFecha", query = "SELECT g FROM Giroactividadeconomica g WHERE g.fecha = :fecha"),
		@NamedQuery(name = "Giroactividadeconomica.findByUltimasincronizacion", query = "SELECT g FROM Giroactividadeconomica g WHERE g.ultimasincronizacion = :ultimasincronizacion"),
		@NamedQuery(name = "Giroactividadeconomica.findByEstreplica", query = "SELECT g FROM Giroactividadeconomica g WHERE g.estreplica = :estreplica") })
public class Giroactividadeconomica extends CrjpaInterface implements
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
	/**
	 * Field codigo.
	 */
	@Basic(optional = false)
	@Column(name = "CODIGO", nullable = false)
	private int codigo;
	/**
	 * Field descripcion.
	 */
	@Basic(optional = false)
	@Column(name = "DESCRIPCION", nullable = false, length = 200)
	private String descripcion;
	/**
	 * Field categoria.
	 */
	@Basic(optional = false)
	@Column(name = "CATEGORIA", nullable = false)
	private int categoria;
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
	@Column(name = "ULTIMASINCRONIZACION")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar ultimasincronizacion;
	/**
	 * Field estreplica.
	 */
	@Basic(optional = false)
	@Column(name = "ESTREPLICA")
	private char estreplica;
	/**
	 * Field categorialineaincluyeList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idGiroactividadeconomica")
	private List<Categorialineaincluye> categorialineaincluyeList;
	/**
	 * Field transaccionclienteList.
	 */
	@OneToMany(mappedBy = "idGiroactividadeconomica")
	private List<Transaccioncliente> transaccionclienteList;
	/**
	 * Field clienteList.
	 */
	@OneToMany(mappedBy = "idGiroactividadeconomica")
	private List<Cliente> clienteList;

	/**
	 * Constructor for Giroactividadeconomica.
	 */
	public Giroactividadeconomica() {
	}

	/**
	 * Constructor for Giroactividadeconomica.
	 * 
	 * @param id
	 *            Long
	 */
	public Giroactividadeconomica(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Giroactividadeconomica.
	 * 
	 * @param id
	 *            Long
	 * @param codigo
	 *            int
	 * @param descripcion
	 *            String
	 * @param categoria
	 *            int
	 * @param fecha
	 *            Date
	 * @param ultimasincronizacion
	 *            Calendar
	 * @param estreplica
	 *            char
	 */
	public Giroactividadeconomica(Long id, int codigo, String descripcion,
			int categoria, Date fecha, Calendar ultimasincronizacion,
			char estreplica) {
		this.id = id;
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.categoria = categoria;
		this.fecha = fecha;
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
	 * @return int
	 */
	public int getCodigo() {
		return codigo;
	}

	/**
	 * Method setCodigo.
	 * 
	 * @param codigo
	 *            int
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	/**
	 * Method getDescripcion.
	 * 
	 * @return String
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Method setDescripcion.
	 * 
	 * @param descripcion
	 *            String
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Method getCategoria.
	 * 
	 * @return int
	 */
	public int getCategoria() {
		return categoria;
	}

	/**
	 * Method setCategoria.
	 * 
	 * @param categoria
	 *            int
	 */
	public void setCategoria(int categoria) {
		this.categoria = categoria;
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
	 * Method getTransaccionclienteList.
	 * 
	 * @return List<Transaccioncliente>
	 */
	@XmlTransient
	public List<Transaccioncliente> getTransaccionclienteList() {
		return transaccionclienteList;
	}

	/**
	 * Method setTransaccionclienteList.
	 * 
	 * @param transaccionclienteList
	 *            List<Transaccioncliente>
	 */
	public void setTransaccionclienteList(
			List<Transaccioncliente> transaccionclienteList) {
		this.transaccionclienteList = transaccionclienteList;
	}

	/**
	 * Method getClienteList.
	 * 
	 * @return List<Cliente>
	 */
	@XmlTransient
	public List<Cliente> getClienteList() {
		return clienteList;
	}

	/**
	 * Method setClienteList.
	 * 
	 * @param clienteList
	 *            List<Cliente>
	 */
	public void setClienteList(List<Cliente> clienteList) {
		this.clienteList = clienteList;
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
		if (!(object instanceof Giroactividadeconomica)) {
			return false;
		}
		Giroactividadeconomica other = (Giroactividadeconomica) object;
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
		return "crjpa400.Giroactividadeconomica[ id=" + id + " ]";
	}

}
