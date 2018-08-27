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
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "ABONO")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Abono.findAll", query = "SELECT a FROM Abono a"),
		@NamedQuery(name = "Abono.findById", query = "SELECT a FROM Abono a WHERE a.id = :id"),
		@NamedQuery(name = "Abono.findByNumero", query = "SELECT a FROM Abono a WHERE a.numero = :numero"),
		@NamedQuery(name = "Abono.findByCajero", query = "SELECT a FROM Abono a WHERE a.cajero = :cajero"),
		@NamedQuery(name = "Abono.findByFecha", query = "SELECT a FROM Abono a WHERE a.fecha = :fecha"),
		@NamedQuery(name = "Abono.findByTotal", query = "SELECT a FROM Abono a WHERE a.total = :total"),
		@NamedQuery(name = "Abono.findByVuelto", query = "SELECT a FROM Abono a WHERE a.vuelto = :vuelto"),
		@NamedQuery(name = "Abono.findByTipo", query = "SELECT a FROM Abono a WHERE a.tipo = :tipo"),
		@NamedQuery(name = "Abono.findByEstado", query = "SELECT a FROM Abono a WHERE a.estado = :estado"),
		@NamedQuery(name = "Abono.findByEstreplica", query = "SELECT a FROM Abono a WHERE a.estreplica = :estreplica"),
		@NamedQuery(name = "Abono.findByRegistradoporcaja", query = "SELECT a FROM Abono a WHERE a.registradoPorCaja = :registradoPorCaja") })
public class Abono implements Serializable {
	/**
	 * Field serialVersionUID. (value is 1)
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Field id.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID", nullable = false)
	private Long id;
	/**
	 * Field numero.
	 */
	@Basic(optional = false)
	@Column(name = "NUMERO", nullable = false)
	private long numero;
	/**
	 * Field cajero.
	 */
	@Basic(optional = false)
	@Column(name = "CAJERO", nullable = false)
	private int cajero;
	/**
	 * Field fecha.
	 */
	@Basic(optional = false)
	@Column(name = "FECHA", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	// @Max(value=?) @Min(value=?)//if you know range of your decimal fields
	// consider using these annotations to enforce field validation
	/**
	 * Field total.
	 */
	@Basic(optional = false)
	@Column(name = "TOTAL", nullable = false, precision = 13, scale = 2)
	private BigDecimal total;
	/**
	 * Field vuelto.
	 */
	@Basic(optional = false)
	@Column(name = "VUELTO", nullable = false, precision = 13, scale = 2)
	private BigDecimal vuelto;
	/**
	 * Field tipo.
	 */
	@Basic(optional = false)
	@Column(name = "TIPO", nullable = false, length = 2)
	private String tipo;
	/**
	 * Field estado.
	 */
	@Basic(optional = false)
	@Column(name = "ESTADO", nullable = false)
	private char estado;
	/**
	 * Field estreplica.
	 */
	@Basic(optional = false)
	@Column(name = "ESTREPLICA")
	private char estreplica;
	/**
	 * Field idOrdendeventa.
	 */
	@JoinColumn(name = "ID_ORDENDEVENTA", referencedColumnName = "ID", nullable = false)
	@ManyToOne(optional = false)
	private Ordendeventa idOrdendeventa;
	/**
	 * Field idUsuario.
	 */
	@JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID", nullable = false)
	@ManyToOne(optional = false)
	private Usuario idUsuario;
	/**
	 * Field idSesion.
	 */
	@JoinColumn(name = "ID_SESION", referencedColumnName = "ID", nullable = false)
	@ManyToOne(optional = false)
	private Sesion idSesion;
	/**
	 * Field abonoList.
	 */
	@OneToMany(mappedBy = "idAnulacionabono")
	private List<Abono> abonoList;
	/**
	 * Field idAnulacionabono.
	 */
	@JoinColumn(name = "ID_ANULACIONABONO", referencedColumnName = "ID")
	@ManyToOne
	private Abono idAnulacionabono;
	/**
	 * Field abonoformadepagoList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idAbono")
	private List<Abonoformadepago> abonoformadepagoList;
	/**
	 * Field registradoPorCaja.
	 */
	@Basic(optional = false)
	@Column(name = "REGISTRADOPORCAJA", nullable = false)
	private char registradoPorCaja;

	/**
	 * Constructor for Abono.
	 */
	public Abono() {
	}

	/**
	 * Constructor for Abono.
	 * 
	 * @param id
	 *            Long
	 */
	public Abono(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Abono.
	 * 
	 * @param id
	 *            Long
	 * @param numero
	 *            long
	 * @param cajero
	 *            int
	 * @param fecha
	 *            Date
	 * @param total
	 *            BigDecimal
	 * @param vuelto
	 *            BigDecimal
	 * @param tipo
	 *            String
	 * @param estaactivo
	 *            char
	 * @param estreplica
	 *            char
	 */
	public Abono(Long id, long numero, int cajero, Date fecha,
			BigDecimal total, BigDecimal vuelto, String tipo, char estaactivo,
			char estreplica) {
		this.id = id;
		this.numero = numero;
		this.cajero = cajero;
		this.fecha = fecha;
		this.total = total;
		this.vuelto = vuelto;
		this.tipo = tipo;
		this.estado = estaactivo;
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
	 * Method getNumero.
	 * 
	 * @return long
	 */
	public long getNumero() {
		return numero;
	}

	/**
	 * Method setNumero.
	 * 
	 * @param numero
	 *            long
	 */
	public void setNumero(long numero) {
		this.numero = numero;
	}

	/**
	 * Method getCajero.
	 * 
	 * @return int
	 */
	public int getCajero() {
		return cajero;
	}

	/**
	 * Method setCajero.
	 * 
	 * @param cajero
	 *            int
	 */
	public void setCajero(int cajero) {
		this.cajero = cajero;
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
	 * Method getTotal.
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal getTotal() {
		return total;
	}

	/**
	 * Method setTotal.
	 * 
	 * @param total
	 *            BigDecimal
	 */
	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	/**
	 * Method getVuelto.
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal getVuelto() {
		return vuelto;
	}

	/**
	 * Method setVuelto.
	 * 
	 * @param vuelto
	 *            BigDecimal
	 */
	public void setVuelto(BigDecimal vuelto) {
		this.vuelto = vuelto;
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
	 * Method getEstado.
	 * 
	 * @return char
	 */
	public char getEstado() {
		return estado;
	}

	/**
	 * Method setEstado.
	 * 
	 * @param estado
	 *            char
	 */
	public void setEstado(char estado) {
		this.estado = estado;
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
	 * Method getIdOrdendeventa.
	 * 
	 * @return Ordendeventa
	 */
	public Ordendeventa getIdOrdendeventa() {
		return idOrdendeventa;
	}

	/**
	 * Method setIdOrdendeventa.
	 * 
	 * @param idOrdendeventa
	 *            Ordendeventa
	 */
	public void setIdOrdendeventa(Ordendeventa idOrdendeventa) {
		this.idOrdendeventa = idOrdendeventa;
	}

	/**
	 * Method getIdUsuario.
	 * 
	 * @return Usuario
	 */
	public Usuario getIdUsuario() {
		return idUsuario;
	}

	/**
	 * Method setIdUsuario.
	 * 
	 * @param idUsuario
	 *            Usuario
	 */
	public void setIdUsuario(Usuario idUsuario) {
		this.idUsuario = idUsuario;
	}

	/**
	 * Method getIdSesion.
	 * 
	 * @return Sesion
	 */
	public Sesion getIdSesion() {
		return idSesion;
	}

	/**
	 * Method setIdSesion.
	 * 
	 * @param idSesion
	 *            Sesion
	 */
	public void setIdSesion(Sesion idSesion) {
		this.idSesion = idSesion;
	}

	/**
	 * Method getAbonoList.
	 * 
	 * @return List<Abono>
	 */
	@XmlTransient
	public List<Abono> getAbonoList() {
		return abonoList;
	}

	/**
	 * Method setAbonoList.
	 * 
	 * @param abonoList
	 *            List<Abono>
	 */
	public void setAbonoList(List<Abono> abonoList) {
		this.abonoList = abonoList;
	}

	/**
	 * Method getIdAnulacionabono.
	 * 
	 * @return Abono
	 */
	public Abono getIdAnulacionabono() {
		return idAnulacionabono;
	}

	/**
	 * Method setIdAnulacionabono.
	 * 
	 * @param idAnulacionabono
	 *            Abono
	 */
	public void setIdAnulacionabono(Abono idAnulacionabono) {
		this.idAnulacionabono = idAnulacionabono;
	}

	/**
	 * Method getAbonoformadepagoList.
	 * 
	 * @return List<Abonoformadepago>
	 */
	@XmlTransient
	public List<Abonoformadepago> getAbonoformadepagoList() {
		return abonoformadepagoList;
	}

	/**
	 * Method setAbonoformadepagoList.
	 * 
	 * @param abonoformadepagoList
	 *            List<Abonoformadepago>
	 */
	public void setAbonoformadepagoList(
			List<Abonoformadepago> abonoformadepagoList) {
		this.abonoformadepagoList = abonoformadepagoList;
	}

	/**
	 * 
	 * @return the registradoPorCaja
	 */
	public char getRegistradoPorCaja() {
		return registradoPorCaja;
	}

	/**
	 * @param registradoPorCaja
	 *            the registradoPorCaja to set
	 */
	public void setRegistradoPorCaja(char registradoPorCaja) {
		this.registradoPorCaja = registradoPorCaja;
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
		if (!(object instanceof Abono)) {
			return false;
		}
		Abono other = (Abono) object;
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
		return "crjpa400.Abono[ id=" + id + " ]";
	}

}
