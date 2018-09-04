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
@Table(name = "tipodescuento")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Tipodescuento.findAll", query = "SELECT t FROM Tipodescuento t"),
		@NamedQuery(name = "Tipodescuento.findById", query = "SELECT t FROM Tipodescuento t WHERE t.id = :id"),
		@NamedQuery(name = "Tipodescuento.findByCodigo", query = "SELECT t FROM Tipodescuento t WHERE t.codigo = :codigo"),
		@NamedQuery(name = "Tipodescuento.findByNombre", query = "SELECT t FROM Tipodescuento t WHERE t.nombre = :nombre"),
		@NamedQuery(name = "Tipodescuento.findByDescripcion", query = "SELECT t FROM Tipodescuento t WHERE t.descripcion = :descripcion"),
		@NamedQuery(name = "Tipodescuento.findByFecha", query = "SELECT t FROM Tipodescuento t WHERE t.fecha = :fecha"),
		@NamedQuery(name = "Tipodescuento.findByEsporcentaje", query = "SELECT t FROM Tipodescuento t WHERE t.esporcentaje = :esporcentaje"),
		@NamedQuery(name = "Tipodescuento.findByFacturacionAutomatica", query = "SELECT t FROM Tipodescuento t WHERE t.facturacionAutomatica = :facturacionAutomatica"),
		@NamedQuery(name = "Tipodescuento.findByEstaactivo", query = "SELECT t FROM Tipodescuento t WHERE t.estaactivo = :estaactivo") })
public class Tipodescuento implements Serializable {
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
	 * Field descripcion.
	 */
	@Basic(optional = false)
	@Column(name = "descripcion")
	private String descripcion;
	/**
	 * Field fecha.
	 */
	@Basic(optional = false)
	@Column(name = "fecha")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	/**
	 * Field esporcentaje.
	 */
	@Basic(optional = false)
	@Column(name = "esporcentaje")
	private String esporcentaje;
	/**
	 * Field facturacionAutomatica.
	 */
	@Basic(optional = false)
	@Column(name = "facturacion_automatica")
	private String facturacionAutomatica;
	/**
	 * Field estaactivo.
	 */
	@Basic(optional = false)
	@Column(name = "estaactivo")
	private String estaactivo;
	/**
	 * Field promociontipodescuentoList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipodescuento")
	private List<Promociontipodescuento> promociontipodescuentoList;
	/**
	 * Field transaccionarticuloList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipodescuento")
	private List<Transaccionarticulo> transaccionarticuloList;
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
	 * @param esporcentaje
	 *            String
	 * @param facturacionAutomatica
	 *            String
	 * @param estaactivo
	 *            String
	 */
	public Tipodescuento(Long id, String codigo, String nombre, String descripcion, Date fecha, String esporcentaje,
			String facturacionAutomatica, String estaactivo) {
		this.id = id;
		this.codigo = codigo;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.fecha = fecha;
		this.esporcentaje = esporcentaje;
		this.facturacionAutomatica = facturacionAutomatica;
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
	 * Method getEsporcentaje.
	 * 
	 * @return String
	 */
	public String getEsporcentaje() {
		return esporcentaje;
	}

	/**
	 * Method setEsporcentaje.
	 * 
	 * @param esporcentaje
	 *            String
	 */
	public void setEsporcentaje(String esporcentaje) {
		this.esporcentaje = esporcentaje;
	}

	/**
	 * Method getFacturacionAutomatica.
	 * 
	 * @return String
	 */
	public String getFacturacionAutomatica() {
		return facturacionAutomatica;
	}

	/**
	 * Method setFacturacionAutomatica.
	 * 
	 * @param facturacionAutomatica
	 *            String
	 */
	public void setFacturacionAutomatica(String facturacionAutomatica) {
		this.facturacionAutomatica = facturacionAutomatica;
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
	public void setPromociontipodescuentoList(List<Promociontipodescuento> promociontipodescuentoList) {
		this.promociontipodescuentoList = promociontipodescuentoList;
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
	public void setTransaccionarticuloList(List<Transaccionarticulo> transaccionarticuloList) {
		this.transaccionarticuloList = transaccionarticuloList;
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
		String endStr = "\r\n";
		String nullStr = "\\N";
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

		sb.append(enclosed);
		sb.append(descripcion);
		sb.append(enclosed);
		sb.append(separator);

		sb.append(fecha == null ? nullStr : simpleDateFormat.format(fecha));
		sb.append(separator);

		sb.append(esporcentaje);
		sb.append(separator);

		sb.append(facturacionAutomatica);
		sb.append(separator);

		sb.append(estaactivo);

		sb.append(endStr);

		return sb.toString();
	}

}
