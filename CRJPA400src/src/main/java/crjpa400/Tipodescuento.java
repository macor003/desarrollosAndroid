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
@Table(name = "TIPODESCUENTO")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Tipodescuento.findAll", query = "SELECT t FROM Tipodescuento t"),
		@NamedQuery(name = "Tipodescuento.findById", query = "SELECT t FROM Tipodescuento t WHERE t.id = :id"),
		@NamedQuery(name = "Tipodescuento.findByCodigo", query = "SELECT t FROM Tipodescuento t WHERE t.codigo = :codigo"),
		@NamedQuery(name = "Tipodescuento.findByNombre", query = "SELECT t FROM Tipodescuento t WHERE t.nombre = :nombre"),
		@NamedQuery(name = "Tipodescuento.findByDescripcion", query = "SELECT t FROM Tipodescuento t WHERE t.descripcion = :descripcion"),
		@NamedQuery(name = "Tipodescuento.findByFecha", query = "SELECT t FROM Tipodescuento t WHERE t.fecha = :fecha"),
		@NamedQuery(name = "Tipodescuento.findByEstaactivo", query = "SELECT t FROM Tipodescuento t WHERE t.estaactivo = :estaactivo"),
		@NamedQuery(name = "Tipodescuento.findByEsporcentaje", query = "SELECT t FROM Tipodescuento t WHERE t.esporcentaje = :esporcentaje"),
		@NamedQuery(name = "Tipodescuento.findByFacturacionAutomatica", query = "SELECT t FROM Tipodescuento t WHERE t.facturacionAutomatica = :facturacionAutomatica"),
		@NamedQuery(name = "Tipodescuento.findByUltimasincronizacion", query = "SELECT t FROM Tipodescuento t WHERE t.ultimasincronizacion = :ultimasincronizacion"),
		@NamedQuery(name = "Tipodescuento.findByEstreplica", query = "SELECT t FROM Tipodescuento t WHERE t.estreplica = :estreplica") })
public class Tipodescuento extends CrjpaInterface implements Serializable {
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
	@Column(name = "NOMBRE", nullable = false, length = 3)
	private String nombre;
	/**
	 * Field descripcion.
	 */
	@Basic(optional = false)
	@Column(name = "DESCRIPCION", nullable = false, length = 30)
	private String descripcion;
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
	 * Field esporcentaje.
	 */
	@Basic(optional = false)
	@Column(name = "ESPORCENTAJE", nullable = false)
	private char esporcentaje;
	/**
	 * Field facturacionAutomatica.
	 */
	@Basic(optional = false)
	@Column(name = "FACTURACION_AUTOMATICA", nullable = false)
	private char facturacionAutomatica;
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
	 * Field ordendeventaarticuloList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipodescuento")
	private List<Ordendeventaarticulo> ordendeventaarticuloList;
	/**
	 * Field transaccionarticuloList.
	 */
	@OneToMany(mappedBy = "idTipodescuento")
	private List<Transaccionarticulo> transaccionarticuloList;
	/**
	 * Field promociontipodescuentoList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipodescuento")
	private List<Promociontipodescuento> promociontipodescuentoList;
	/**
	 * Field convertidortipodesctoList.
	 */
	@OneToMany(mappedBy = "idTipodescuento")
	private List<Convertidortipodescto> convertidortipodesctoList;
	/**
	 * Field motivorebajaList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipodescuento")
	private List<Motivorebaja> motivorebajaList;

	/**
	 * Constructor for Tipodescuento.
	 */
	public Tipodescuento() {
	}

	/**
	 * Constructor for Tipodescuento.
	 * 
	 * @param id
	 *            Long
	 */
	public Tipodescuento(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Tipodescuento.
	 * 
	 * @param id
	 *            Long
	 * @param codigo
	 *            String
	 * @param nombre
	 *            String
	 * @param descripcion
	 *            String
	 * @param fecha
	 *            Date
	 * @param estaactivo
	 *            char
	 * @param esporcentaje
	 *            char
	 * @param facturacionAutomatica
	 *            char
	 * @param ultimasincronizacion
	 *            Calendar
	 * @param estreplica
	 *            char
	 */
	public Tipodescuento(Long id, String codigo, String nombre,
			String descripcion, Date fecha, char estaactivo, char esporcentaje,
			char facturacionAutomatica, Calendar ultimasincronizacion,
			char estreplica) {
		this.id = id;
		this.codigo = codigo;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.fecha = fecha;
		this.estaactivo = estaactivo;
		this.esporcentaje = esporcentaje;
		this.facturacionAutomatica = facturacionAutomatica;
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
	 * Method getEsporcentaje.
	 * 
	 * @return char
	 */
	public char getEsporcentaje() {
		return esporcentaje;
	}

	/**
	 * Method setEsporcentaje.
	 * 
	 * @param esporcentaje
	 *            char
	 */
	public void setEsporcentaje(char esporcentaje) {
		this.esporcentaje = esporcentaje;
	}

	/**
	 * Method getFacturacionAutomatica.
	 * 
	 * @return char
	 */
	public char getFacturacionAutomatica() {
		return facturacionAutomatica;
	}

	/**
	 * Method setFacturacionAutomatica.
	 * 
	 * @param facturacionAutomatica
	 *            char
	 */
	public void setFacturacionAutomatica(char facturacionAutomatica) {
		this.facturacionAutomatica = facturacionAutomatica;
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
	 * Method getOrdendeventaarticuloList.
	 * 
	 * @return List<Ordendeventaarticulo>
	 */
	@XmlTransient
	public List<Ordendeventaarticulo> getOrdendeventaarticuloList() {
		return ordendeventaarticuloList;
	}

	/**
	 * Method setOrdendeventaarticuloList.
	 * 
	 * @param ordendeventaarticuloList
	 *            List<Ordendeventaarticulo>
	 */
	public void setOrdendeventaarticuloList(
			List<Ordendeventaarticulo> ordendeventaarticuloList) {
		this.ordendeventaarticuloList = ordendeventaarticuloList;
	}

	/**
	 * Method getTransaccionarticuloList.
	 * 
	 * @return List<Transaccionarticulo>
	 */
	@XmlTransient
	public List<Transaccionarticulo> getTransaccionarticuloList() {
		return transaccionarticuloList;
	}

	/**
	 * Method setTransaccionarticuloList.
	 * 
	 * @param transaccionarticuloList
	 *            List<Transaccionarticulo>
	 */
	public void setTransaccionarticuloList(
			List<Transaccionarticulo> transaccionarticuloList) {
		this.transaccionarticuloList = transaccionarticuloList;
	}

	/**
	 * Method getPromociontipodescuentoList.
	 * 
	 * @return List<Promociontipodescuento>
	 */
	@XmlTransient
	public List<Promociontipodescuento> getPromociontipodescuentoList() {
		return promociontipodescuentoList;
	}

	/**
	 * Method setPromociontipodescuentoList.
	 * 
	 * @param promociontipodescuentoList
	 *            List<Promociontipodescuento>
	 */
	public void setPromociontipodescuentoList(
			List<Promociontipodescuento> promociontipodescuentoList) {
		this.promociontipodescuentoList = promociontipodescuentoList;
	}

	/**
	 * Method getConvertidortipodesctoList.
	 * 
	 * @return List<Convertidortipodescto>
	 */
	@XmlTransient
	public List<Convertidortipodescto> getConvertidortipodesctoList() {
		return convertidortipodesctoList;
	}

	/**
	 * Method setConvertidortipodesctoList.
	 * 
	 * @param convertidortipodesctoList
	 *            List<Convertidortipodescto>
	 */
	public void setConvertidortipodesctoList(
			List<Convertidortipodescto> convertidortipodesctoList) {
		this.convertidortipodesctoList = convertidortipodesctoList;
	}

	/**
	 * Method getMotivorebajaList.
	 * 
	 * @return List<Motivorebaja>
	 */
	@XmlTransient
	public List<Motivorebaja> getMotivorebajaList() {
		return motivorebajaList;
	}

	/**
	 * Method setMotivorebajaList.
	 * 
	 * @param motivorebajaList
	 *            List<Motivorebaja>
	 */
	public void setMotivorebajaList(List<Motivorebaja> motivorebajaList) {
		this.motivorebajaList = motivorebajaList;
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
		if (!(object instanceof Tipodescuento)) {
			return false;
		}
		Tipodescuento other = (Tipodescuento) object;
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
		return "crjpa400.Tipodescuento[ id=" + id + " ]";
	}

}
