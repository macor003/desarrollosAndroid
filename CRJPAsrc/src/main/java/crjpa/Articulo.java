/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "articulo")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Articulo.findAll", query = "SELECT a FROM Articulo a"),
		@NamedQuery(name = "Articulo.findById", query = "SELECT a FROM Articulo a WHERE a.id = :id"),
		@NamedQuery(name = "Articulo.findByCodigo", query = "SELECT a FROM Articulo a WHERE a.codigo = :codigo"),
		@NamedQuery(name = "Articulo.findByNombre", query = "SELECT a FROM Articulo a WHERE a.nombre = :nombre"),
		@NamedQuery(name = "Articulo.findByFecha", query = "SELECT a FROM Articulo a WHERE a.fecha = :fecha"),
		@NamedQuery(name = "Articulo.findByEstaactivo", query = "SELECT a FROM Articulo a WHERE a.estaactivo = :estaactivo") })
public class Articulo implements Serializable {
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
	private long codigo;
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
	 * Field estaactivo.
	 */
	@Basic(optional = false)
	@Column(name = "estaactivo")
	private String estaactivo;
	/**
	 * Field servicioList.
	 */
	@JoinTable(name = "articuloservicio", joinColumns = { @JoinColumn(name = "id_articulo", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "id_servicio", referencedColumnName = "id") })
	@ManyToMany
	private List<Servicio> servicioList;
	/**
	 * Field exoneradotipoList.
	 */
	@JoinTable(name = "exoneradoarticulo", joinColumns = { @JoinColumn(name = "id_articulo", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "id_exoneradotipo", referencedColumnName = "id") })
	@ManyToMany
	private List<Exoneradotipo> exoneradotipoList;
	/**
	 * Field idFamilia.
	 */
	@JoinColumn(name = "id_familia", referencedColumnName = "id")
	@ManyToOne
	private Familia idFamilia;
	/**
	 * Field articuloList.
	 */
	@OneToMany(mappedBy = "idArticulocategorizado")
	private List<Articulo> articuloList;
	/**
	 * Field idArticulocategorizado.
	 */
	@JoinColumn(name = "id_articulocategorizado", referencedColumnName = "id")
	@ManyToOne
	private Articulo idArticulocategorizado;
	/**
	 * Field idLinea.
	 */
	@JoinColumn(name = "id_linea", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Linea idLinea;
	/**
	 * Field idTasaimpuestotipo.
	 */
	@JoinColumn(name = "id_tasaimpuestotipo", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Tasaimpuestotipo idTasaimpuestotipo;
	/**
	 * Field promocionarticuloList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idArticulo")
	private List<Promocionarticulo> promocionarticuloList;
	/**
	 * Field transaccionarticuloList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idArticulo")
	private List<Transaccionarticulo> transaccionarticuloList;
	/**
	 * Field articulocodigoexternoList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idArticulo")
	private List<Articulocodigoexterno> articulocodigoexternoList;
	/**
	 * Field articulounidadventaList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idArticulo")
	private List<Articulounidadventa> articulounidadventaList;
	
	@Basic(optional = false)
	@Column(name = "cantidad_maxima_transaccion", nullable = false, precision = 13, scale = 2)
	private BigDecimal cantidadMaximaTransaccion;
	
	@Basic(optional = false)
	@Column(name = "cantidad_maxima_periodo", nullable = false, precision = 13, scale = 2)
	private BigDecimal cantidadMaximaPeriodo;
	
	@Basic(optional = false)
	@Column(name = "periodo_compra", nullable = false)
	private int periodoCompra;

	/**
	 * Constructor for Articulo.
	 */
	public Articulo() {
	}

	/**
	 * Constructor for Articulo.
	 * 
	 * @param id
	 *            Long
	 */
	public Articulo(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Articulo.
	 * 
	 * @param id
	 *            Long
	 * @param codigo
	 *            long
	 * @param nombre
	 *            String
	 * @param fecha
	 *            Date
	 * @param estaactivo
	 *            String
	 */
	public Articulo(Long id, long codigo, String nombre, Date fecha, String estaactivo) {
		this.id = id;
		this.codigo = codigo;
		this.nombre = nombre;
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
	 * @return long
	 */
	public long getCodigo() {
		return codigo;
	}

	/**
	 * Method setCodigo.
	 * 
	 * @param codigo
	 *            long
	 */
	public void setCodigo(long codigo) {
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
	 * Method getServicioList.
	 * 
	 * @return List<Servicio>
	 */
	@XmlTransient
	public List<Servicio> getServicioList() {
		return servicioList;
	}

	/**
	 * Method setServicioList.
	 * 
	 * @param servicioList
	 *            List<Servicio>
	 */
	public void setServicioList(List<Servicio> servicioList) {
		this.servicioList = servicioList;
	}

	/**
	 * Method getExoneradotipoList.
	 * 
	 * @return List<Exoneradotipo>
	 */
	@XmlTransient
	public List<Exoneradotipo> getExoneradotipoList() {
		return exoneradotipoList;
	}

	/**
	 * Method setExoneradotipoList.
	 * 
	 * @param exoneradotipoList
	 *            List<Exoneradotipo>
	 */
	public void setExoneradotipoList(List<Exoneradotipo> exoneradotipoList) {
		this.exoneradotipoList = exoneradotipoList;
	}

	/**
	 * Method getIdFamilia.
	 * 
	 * @return Familia
	 */
	public Familia getIdFamilia() {
		return idFamilia;
	}

	/**
	 * Method setIdFamilia.
	 * 
	 * @param idFamilia
	 *            Familia
	 */
	public void setIdFamilia(Familia idFamilia) {
		this.idFamilia = idFamilia;
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
	 * Method getIdArticulocategorizado.
	 * 
	 * @return Articulo
	 */
	public Articulo getIdArticulocategorizado() {
		return idArticulocategorizado;
	}

	/**
	 * Method setIdArticulocategorizado.
	 * 
	 * @param idArticulocategorizado
	 *            Articulo
	 */
	public void setIdArticulocategorizado(Articulo idArticulocategorizado) {
		this.idArticulocategorizado = idArticulocategorizado;
	}

	/**
	 * Method getIdLinea.
	 * 
	 * @return Linea
	 */
	public Linea getIdLinea() {
		return idLinea;
	}

	/**
	 * Method setIdLinea.
	 * 
	 * @param idLinea
	 *            Linea
	 */
	public void setIdLinea(Linea idLinea) {
		this.idLinea = idLinea;
	}

	/**
	 * Method getIdTasaimpuestotipo.
	 * 
	 * @return Tasaimpuestotipo
	 */
	public Tasaimpuestotipo getIdTasaimpuestotipo() {
		return idTasaimpuestotipo;
	}

	/**
	 * Method setIdTasaimpuestotipo.
	 * 
	 * @param idTasaimpuestotipo
	 *            Tasaimpuestotipo
	 */
	public void setIdTasaimpuestotipo(Tasaimpuestotipo idTasaimpuestotipo) {
		this.idTasaimpuestotipo = idTasaimpuestotipo;
	}

	/**
	 * Method getPromocionarticuloList.
	 * 
	 * @return List<Promocionarticulo>
	 */
	@XmlTransient
	public List<Promocionarticulo> getPromocionarticuloList() {
		return promocionarticuloList;
	}

	/**
	 * Method setPromocionarticuloList.
	 * 
	 * @param promocionarticuloList
	 *            List<Promocionarticulo>
	 */
	public void setPromocionarticuloList(List<Promocionarticulo> promocionarticuloList) {
		this.promocionarticuloList = promocionarticuloList;
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
	 * Method getArticulocodigoexternoList.
	 * 
	 * @return List<Articulocodigoexterno>
	 */
	@XmlTransient
	public List<Articulocodigoexterno> getArticulocodigoexternoList() {
		return articulocodigoexternoList;
	}

	/**
	 * Method setArticulocodigoexternoList.
	 * 
	 * @param articulocodigoexternoList
	 *            List<Articulocodigoexterno>
	 */
	public void setArticulocodigoexternoList(List<Articulocodigoexterno> articulocodigoexternoList) {
		this.articulocodigoexternoList = articulocodigoexternoList;
	}

	/**
	 * Method getArticulounidadventaList.
	 * 
	 * @return List<Articulounidadventa>
	 */
	@XmlTransient
	public List<Articulounidadventa> getArticulounidadventaList() {
		return articulounidadventaList;
	}

	/**
	 * Method setArticulounidadventaList.
	 * 
	 * @param articulounidadventaList
	 *            List<Articulounidadventa>
	 */
	public void setArticulounidadventaList(List<Articulounidadventa> articulounidadventaList) {
		this.articulounidadventaList = articulounidadventaList;
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
		if (!(object instanceof Articulo)) {
			return false;
		}
		Articulo other = (Articulo) object;
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
	// public String toString() {
	// return "crjpa.Articulo[ id=" + id + " ]";
	// }
	public String toString() {
		String separator = "@@";
		String enclosed = "|";
		String nullStr = "\\N";
		String endStr = "\r\n";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		StringBuilder sb = new StringBuilder();

		sb.append(id);
		sb.append(separator);

		sb.append(idLinea.getId());
		sb.append(separator);

		sb.append(idTasaimpuestotipo.getId());
		sb.append(separator);

		if (idArticulocategorizado == null || idArticulocategorizado.getId() == null) {
			sb.append(nullStr);
		} else {
			sb.append(idArticulocategorizado.getId());
		}
		sb.append(separator);

		if (idFamilia == null || idFamilia.getId() == null) {
			sb.append(nullStr);
		} else {
			sb.append(idFamilia.getId());
		}
		sb.append(separator);

		sb.append(codigo);
		sb.append(separator);

		sb.append(enclosed);
		sb.append(nombre.trim());
		sb.append(enclosed);
		sb.append(separator);

		sb.append(fecha == null ? nullStr : simpleDateFormat.format(fecha));
		sb.append(separator);

		sb.append(estaactivo);
		sb.append(separator);
		
		sb.append(cantidadMaximaTransaccion);
		sb.append(separator);
		
		sb.append(cantidadMaximaPeriodo);
		sb.append(separator);
		
		sb.append(periodoCompra);
		sb.append(endStr);

		return sb.toString();

	}

	public BigDecimal getCantidadMaximaTransaccion() {
		return cantidadMaximaTransaccion;
	}

	public void setCantidadMaximaTransaccion(BigDecimal cantidadMaximaTransaccion) {
		this.cantidadMaximaTransaccion = cantidadMaximaTransaccion;
	}

	public BigDecimal getCantidadMaximaPeriodo() {
		return cantidadMaximaPeriodo;
	}

	public void setCantidadMaximaPeriodo(BigDecimal cantidadMaximaPeriodo) {
		this.cantidadMaximaPeriodo = cantidadMaximaPeriodo;
	}

	public int getPeriodoCompra() {
		return periodoCompra;
	}

	public void setPeriodoCompra(int periodoCompra) {
		this.periodoCompra = periodoCompra;
	}

}
