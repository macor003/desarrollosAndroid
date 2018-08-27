/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crjpa400;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
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
@Table(name = "ARTICULO")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Articulo.findAll", query = "SELECT a FROM Articulo a"),
		@NamedQuery(name = "Articulo.findById", query = "SELECT a FROM Articulo a WHERE a.id = :id"),
		@NamedQuery(name = "Articulo.findByCodigo", query = "SELECT a FROM Articulo a WHERE a.codigo = :codigo"),
		@NamedQuery(name = "Articulo.findByNombre", query = "SELECT a FROM Articulo a WHERE a.nombre = :nombre"),
		@NamedQuery(name = "Articulo.findByFecha", query = "SELECT a FROM Articulo a WHERE a.fecha = :fecha"),
		@NamedQuery(name = "Articulo.findByUltimasincronizacion", query = "SELECT a FROM Articulo a WHERE a.ultimasincronizacion = :ultimasincronizacion"),
		@NamedQuery(name = "Articulo.findByEstaactivo", query = "SELECT a FROM Articulo a WHERE a.estaactivo = :estaactivo"),
		@NamedQuery(name = "Articulo.findByEstreplica", query = "SELECT a FROM Articulo a WHERE a.estreplica = :estreplica") })
public class Articulo extends CrjpaInterface implements Serializable {
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
	private BigInteger codigo;
	/**
	 * Field nombre.
	 */
	@Basic(optional = false)
	@Column(name = "NOMBRE", nullable = false, length = 80)
	private String nombre;
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
	 * Field articulocodigoexternoList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idArticulo")
	private List<Articulocodigoexterno> articulocodigoexternoList;
	/**
	 * Field exoneradoarticuloList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "articulo")
	private List<Exoneradoarticulo> exoneradoarticuloList;
	/**
	 * Field ordendeventaarticuloList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idArticulo")
	private List<Ordendeventaarticulo> ordendeventaarticuloList;
	/**
	 * Field transaccionarticuloList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idArticulo")
	private List<Transaccionarticulo> transaccionarticuloList;
	/**
	 * Field articuloservicioList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "articulo")
	private List<Articuloservicio> articuloservicioList;
	/**
	 * Field promocionarticuloList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idArticulo")
	private List<Promocionarticulo> promocionarticuloList;
	/**
	 * Field idFamilia.
	 */
	@JoinColumn(name = "ID_FAMILIA", referencedColumnName = "ID")
	@ManyToOne
	private Familia idFamilia;
	/**
	 * Field idTasaimpuestotipo.
	 */
	@JoinColumn(name = "ID_TASAIMPUESTOTIPO", referencedColumnName = "ID", nullable = false)
	@ManyToOne(optional = false)
	private Tasaimpuestotipo idTasaimpuestotipo;
	/**
	 * Field idLinea.
	 */
	@JoinColumn(name = "ID_LINEA", referencedColumnName = "ID", nullable = false)
	@ManyToOne(optional = false)
	private Linea idLinea;
	/**
	 * Field articuloList.
	 */
	@OneToMany(mappedBy = "idArticulocategorizado")
	private List<Articulo> articuloList;
	/**
	 * Field idArticulocategorizado.
	 */
	@JoinColumn(name = "ID_ARTICULOCATEGORIZADO", referencedColumnName = "ID")
	@ManyToOne
	private Articulo idArticulocategorizado;
	/**
	 * Field articulounidadventaList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idArticulo")
	private List<Articulounidadventa> articulounidadventaList;
	
	@Basic(optional = false)
	@Column(name = "CANTIDAD_MAXIMA_TRANSACCION", nullable = false, precision = 13, scale = 2)
	private BigDecimal cantidadMaximaTransaccion;
	
	@Basic(optional = false)
	@Column(name = "CANTIDAD_MAXIMA_PERIODO", nullable = false, precision = 13, scale = 2)
	private BigDecimal cantidadMaximaPeriodo;
	
	@Basic(optional = false)
	@Column(name = "PERIODO_COMPRA", nullable = false)
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
	 *            BigInteger
	 * @param nombre
	 *            String
	 * @param fecha
	 *            Date
	 * @param ultimasincronizacion
	 *            Calendar
	 * @param estaactivo
	 *            char
	 * @param estreplica
	 *            char
	 */
	public Articulo(Long id, BigInteger codigo, String nombre, Date fecha,
			Calendar ultimasincronizacion, char estaactivo, char estreplica) {
		this.id = id;
		this.codigo = codigo;
		this.nombre = nombre;
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
	 * @return BigInteger
	 */
	public BigInteger getCodigo() {
		return codigo;
	}

	/**
	 * Method setCodigo.
	 * 
	 * @param codigo
	 *            BigInteger
	 */
	public void setCodigo(BigInteger codigo) {
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
	public void setArticulocodigoexternoList(
			List<Articulocodigoexterno> articulocodigoexternoList) {
		this.articulocodigoexternoList = articulocodigoexternoList;
	}

	/**
	 * Method getExoneradoarticuloList.
	 * 
	 * @return List<Exoneradoarticulo>
	 */
	@XmlTransient
	public List<Exoneradoarticulo> getExoneradoarticuloList() {
		return exoneradoarticuloList;
	}

	/**
	 * Method setExoneradoarticuloList.
	 * 
	 * @param exoneradoarticuloList
	 *            List<Exoneradoarticulo>
	 */
	public void setExoneradoarticuloList(
			List<Exoneradoarticulo> exoneradoarticuloList) {
		this.exoneradoarticuloList = exoneradoarticuloList;
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
	 * Method getArticuloservicioList.
	 * 
	 * @return List<Articuloservicio>
	 */
	@XmlTransient
	public List<Articuloservicio> getArticuloservicioList() {
		return articuloservicioList;
	}

	/**
	 * Method setArticuloservicioList.
	 * 
	 * @param articuloservicioList
	 *            List<Articuloservicio>
	 */
	public void setArticuloservicioList(
			List<Articuloservicio> articuloservicioList) {
		this.articuloservicioList = articuloservicioList;
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
	public void setPromocionarticuloList(
			List<Promocionarticulo> promocionarticuloList) {
		this.promocionarticuloList = promocionarticuloList;
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
	public void setArticulounidadventaList(
			List<Articulounidadventa> articulounidadventaList) {
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
		return "crjpa400.Articulo[ id=" + id + " ]";
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
