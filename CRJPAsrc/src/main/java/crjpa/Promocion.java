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
@Table(name = "promocion")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Promocion.findAll", query = "SELECT p FROM Promocion p"),
		@NamedQuery(name = "Promocion.findById", query = "SELECT p FROM Promocion p WHERE p.id = :id"),
		@NamedQuery(name = "Promocion.findByNombre", query = "SELECT p FROM Promocion p WHERE p.nombre = :nombre"),
		@NamedQuery(name = "Promocion.findByFechaInicio", query = "SELECT p FROM Promocion p WHERE p.fechaInicio = :fechaInicio"),
		@NamedQuery(name = "Promocion.findByFechaFin", query = "SELECT p FROM Promocion p WHERE p.fechaFin = :fechaFin"),
		@NamedQuery(name = "Promocion.findByPrioridad", query = "SELECT p FROM Promocion p WHERE p.prioridad = :prioridad"),
		@NamedQuery(name = "Promocion.findByEsmultiple", query = "SELECT p FROM Promocion p WHERE p.esmultiple = :esmultiple"),
		@NamedQuery(name = "Promocion.findByValorMaximo", query = "SELECT p FROM Promocion p WHERE p.valorMaximo = :valorMaximo"),
		@NamedQuery(name = "Promocion.findByValorMinimo", query = "SELECT p FROM Promocion p WHERE p.valorMinimo = :valorMinimo"),
		@NamedQuery(name = "Promocion.findByMensaje", query = "SELECT p FROM Promocion p WHERE p.mensaje = :mensaje"),
		@NamedQuery(name = "Promocion.findByCodigo", query = "SELECT p FROM Promocion p WHERE p.codigo = :codigo"),
		@NamedQuery(name = "Promocion.findByAplicaDescuento", query = "SELECT p FROM Promocion p WHERE p.aplicaDescuento = :aplicaDescuento"),
		@NamedQuery(name = "Promocion.findByCantidadTransacciones", query = "SELECT p FROM Promocion p WHERE p.cantidadTransacciones = :cantidadTransacciones"),
		@NamedQuery(name = "Promocion.findByPorcentajeDescuento", query = "SELECT p FROM Promocion p WHERE p.porcentajeDescuento = :porcentajeDescuento"),
		@NamedQuery(name = "Promocion.findByEstaactivo", query = "SELECT p FROM Promocion p WHERE p.estaactivo = :estaactivo") })
public class Promocion implements Serializable {
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
	 * Field nombre.
	 */
	@Basic(optional = false)
	@Column(name = "nombre")
	private String nombre;
	/**
	 * Field fechaInicio.
	 */
	@Basic(optional = false)
	@Column(name = "fecha_inicio")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaInicio;
	/**
	 * Field fechaFin.
	 */
	@Basic(optional = false)
	@Column(name = "fecha_fin")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaFin;
	/**
	 * Field prioridad.
	 */
	@Basic(optional = false)
	@Column(name = "prioridad")
	private int prioridad;
	/**
	 * Field esmultiple.
	 */
	@Basic(optional = false)
	@Column(name = "esmultiple")
	private String esmultiple;
	/**
	 * Field valorMaximo.
	 */
	@Basic(optional = false)
	@Column(name = "valor_maximo")
	private long valorMaximo;
	/**
	 * Field valorMinimo.
	 */
	@Basic(optional = false)
	@Column(name = "valor_minimo")
	private long valorMinimo;
	/**
	 * Field mensaje.
	 */
	@Column(name = "mensaje")
	private String mensaje;
	/**
	 * Field codigo.
	 */
	@Basic(optional = false)
	@Column(name = "codigo")
	private long codigo;
	/**
	 * Field aplicaDescuento.
	 */
	@Basic(optional = false)
	@Column(name = "aplica_descuento")
	private String aplicaDescuento;
	/**
	 * Field cantidadTransacciones.
	 */
	@Basic(optional = false)
	@Column(name = "cantidad_transacciones")
	private long cantidadTransacciones;
	// @Max(value=?) @Min(value=?)//if you know range of your decimal fields
	// consider using these annotations to enforce field validation
	/**
	 * Field porcentajeDescuento.
	 */
	@Basic(optional = false)
	@Column(name = "porcentaje_descuento")
	private BigDecimal porcentajeDescuento;
	/**
	 * Field estaactivo.
	 */
	@Basic(optional = false)
	@Column(name = "estaactivo")
	private String estaactivo;
	/**
	 * Field promocionformadepagoList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idPromocion")
	private List<Promocionformadepago> promocionformadepagoList;
	/**
	 * Field promocionlineaList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idPromocion")
	private List<Promocionlinea> promocionlineaList;
	/**
	 * Field promociontipodescuentoList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idPromocion")
	private List<Promociontipodescuento> promociontipodescuentoList;
	/**
	 * Field promocionarticuloList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idPromocion")
	private List<Promocionarticulo> promocionarticuloList;
	/**
	 * Field promocioncategoriaList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idPromocion")
	private List<Promocioncategoria> promocioncategoriaList;
	/**
	 * Field transaccionarticuloList.
	 */
	@OneToMany(mappedBy = "idPromocion")
	private List<Transaccionarticulo> transaccionarticuloList;
	/**
	 * Field promocionfamiliaList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idPromocion")
	private List<Promocionfamilia> promocionfamiliaList;

	/**
	 * Constructor for Promocion.
	 */
	public Promocion() {
	}

	/**
	 * Constructor for Promocion.
	 * 
	 * @param id
	 *            Long
	 */
	public Promocion(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Promocion.
	 * 
	 * @param id
	 *            Long
	 * @param nombre
	 *            String
	 * @param fechaInicio
	 *            Date
	 * @param fechaFin
	 *            Date
	 * @param prioridad
	 *            int
	 * @param esmultiple
	 *            String
	 * @param valorMaximo
	 *            long
	 * @param valorMinimo
	 *            long
	 * @param codigo
	 *            long
	 * @param aplicaDescuento
	 *            String
	 * @param cantidadTransacciones
	 *            long
	 * @param porcentajeDescuento
	 *            BigDecimal
	 * @param estaactivo
	 *            String
	 */
	public Promocion(Long id, String nombre, Date fechaInicio, Date fechaFin, int prioridad, String esmultiple,
			long valorMaximo, long valorMinimo, long codigo, String aplicaDescuento, long cantidadTransacciones,
			BigDecimal porcentajeDescuento, String estaactivo) {
		this.id = id;
		this.nombre = nombre;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.prioridad = prioridad;
		this.esmultiple = esmultiple;
		this.valorMaximo = valorMaximo;
		this.valorMinimo = valorMinimo;
		this.codigo = codigo;
		this.aplicaDescuento = aplicaDescuento;
		this.cantidadTransacciones = cantidadTransacciones;
		this.porcentajeDescuento = porcentajeDescuento;
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
	 * Method getFechaInicio.
	 * 
	 * @return Date
	 */
	public Date getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * Method setFechaInicio.
	 * 
	 * @param fechaInicio
	 *            Date
	 */
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	/**
	 * Method getFechaFin.
	 * 
	 * @return Date
	 */
	public Date getFechaFin() {
		return fechaFin;
	}

	/**
	 * Method setFechaFin.
	 * 
	 * @param fechaFin
	 *            Date
	 */
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	/**
	 * Method getPrioridad.
	 * 
	 * @return int
	 */
	public int getPrioridad() {
		return prioridad;
	}

	/**
	 * Method setPrioridad.
	 * 
	 * @param prioridad
	 *            int
	 */
	public void setPrioridad(int prioridad) {
		this.prioridad = prioridad;
	}

	/**
	 * Method getEsmultiple.
	 * 
	 * @return String
	 */
	public String getEsmultiple() {
		return esmultiple;
	}

	/**
	 * Method setEsmultiple.
	 * 
	 * @param esmultiple
	 *            String
	 */
	public void setEsmultiple(String esmultiple) {
		this.esmultiple = esmultiple;
	}

	/**
	 * Method getValorMaximo.
	 * 
	 * @return long
	 */
	public long getValorMaximo() {
		return valorMaximo;
	}

	/**
	 * Method setValorMaximo.
	 * 
	 * @param valorMaximo
	 *            long
	 */
	public void setValorMaximo(long valorMaximo) {
		this.valorMaximo = valorMaximo;
	}

	/**
	 * Method getValorMinimo.
	 * 
	 * @return long
	 */
	public long getValorMinimo() {
		return valorMinimo;
	}

	/**
	 * Method setValorMinimo.
	 * 
	 * @param valorMinimo
	 *            long
	 */
	public void setValorMinimo(long valorMinimo) {
		this.valorMinimo = valorMinimo;
	}

	/**
	 * Method getMensaje.
	 * 
	 * @return String
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * Method setMensaje.
	 * 
	 * @param mensaje
	 *            String
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
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
	 * Method getAplicaDescuento.
	 * 
	 * @return String
	 */
	public String getAplicaDescuento() {
		return aplicaDescuento;
	}

	/**
	 * Method setAplicaDescuento.
	 * 
	 * @param aplicaDescuento
	 *            String
	 */
	public void setAplicaDescuento(String aplicaDescuento) {
		this.aplicaDescuento = aplicaDescuento;
	}

	/**
	 * Method getCantidadTransacciones.
	 * 
	 * @return long
	 */
	public long getCantidadTransacciones() {
		return cantidadTransacciones;
	}

	/**
	 * Method setCantidadTransacciones.
	 * 
	 * @param cantidadTransacciones
	 *            long
	 */
	public void setCantidadTransacciones(long cantidadTransacciones) {
		this.cantidadTransacciones = cantidadTransacciones;
	}

	/**
	 * Method getPorcentajeDescuento.
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal getPorcentajeDescuento() {
		return porcentajeDescuento;
	}

	/**
	 * Method setPorcentajeDescuento.
	 * 
	 * @param porcentajeDescuento
	 *            BigDecimal
	 */
	public void setPorcentajeDescuento(BigDecimal porcentajeDescuento) {
		this.porcentajeDescuento = porcentajeDescuento;
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
	 * Method getPromocionformadepagoList.
	 * 
	 * @return List<Promocionformadepago>
	 */
	@XmlTransient
	public List<Promocionformadepago> getPromocionformadepagoList() {
		return promocionformadepagoList;
	}

	/**
	 * Method setPromocionformadepagoList.
	 * 
	 * @param promocionformadepagoList
	 *            List<Promocionformadepago>
	 */
	public void setPromocionformadepagoList(List<Promocionformadepago> promocionformadepagoList) {
		this.promocionformadepagoList = promocionformadepagoList;
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
	 * Method getPromocionfamiliaList.
	 * 
	 * @return List<Promocionfamilia>
	 */
	@XmlTransient
	public List<Promocionfamilia> getPromocionfamiliaList() {
		return promocionfamiliaList;
	}

	/**
	 * Method setPromocionfamiliaList.
	 * 
	 * @param promocionfamiliaList
	 *            List<Promocionfamilia>
	 */
	public void setPromocionfamiliaList(List<Promocionfamilia> promocionfamiliaList) {
		this.promocionfamiliaList = promocionfamiliaList;
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
		if (!(object instanceof Promocion)) {
			return false;
		}
		Promocion other = (Promocion) object;
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
		sb.append(nombre);
		sb.append(enclosed);
		sb.append(separator);

		sb.append(fechaInicio == null ? nullStr : simpleDateFormat.format(fechaInicio));
		sb.append(separator);

		sb.append(fechaFin == null ? nullStr : simpleDateFormat.format(fechaFin));
		sb.append(separator);

		sb.append(prioridad);
		sb.append(separator);

		sb.append(esmultiple);
		sb.append(separator);

		sb.append(valorMaximo);
		sb.append(separator);

		sb.append(valorMinimo);
		sb.append(separator);

		if (mensaje == null) {
			sb.append(nullStr);
		} else {
			sb.append(mensaje);
		}
		sb.append(separator);

		sb.append(codigo);
		sb.append(separator);

		sb.append(aplicaDescuento);
		sb.append(separator);

		sb.append(cantidadTransacciones);
		sb.append(separator);

		sb.append(porcentajeDescuento);
		sb.append(separator);

		sb.append(estaactivo);
		sb.append(endStr);
		return sb.toString();
	}

}
