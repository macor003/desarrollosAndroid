/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crjpa;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
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
import javax.persistence.TableGenerator;
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
@Table(name = "entrega")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Entrega.findAll", query = "SELECT e FROM Entrega e"),
		@NamedQuery(name = "Entrega.findById", query = "SELECT e FROM Entrega e WHERE e.id = :id"),
		@NamedQuery(name = "Entrega.findByNumero", query = "SELECT e FROM Entrega e WHERE e.numero = :numero"),
		@NamedQuery(name = "Entrega.findByFecha", query = "SELECT e FROM Entrega e WHERE e.fecha = :fecha"),
		@NamedQuery(name = "Entrega.findByMontoRecaudado", query = "SELECT e FROM Entrega e WHERE e.montoRecaudado = :montoRecaudado"),
		@NamedQuery(name = "Entrega.findByMontoFondo", query = "SELECT e FROM Entrega e WHERE e.montoFondo = :montoFondo"),
		@NamedQuery(name = "Entrega.findByEstaactivo", query = "SELECT e FROM Entrega e WHERE e.estaactivo = :estaactivo"),
		@NamedQuery(name = "Entrega.findByEnlinea", query = "SELECT e FROM Entrega e WHERE e.enlinea = :enlinea"),
		@NamedQuery(name = "Entrega.findByTipoEntrega", query = "SELECT e FROM Entrega e WHERE e.tipoEntrega = :tipoEntrega"),
		@NamedQuery(name = "Entrega.findByEstasincronizado", query = "SELECT e FROM Entrega e WHERE e.estasincronizado = :estasincronizado"),
		@NamedQuery(name = "Entrega.findByTransaccionExcedida", query = "SELECT e FROM Entrega e WHERE e.transaccionExcedida = :transaccionExcedida") })
public class Entrega implements Serializable {
	/**
	 * Field serialVersionUID. (value is 1)
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Field id.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "5")
	@TableGenerator(name = "5", table = "correlativo", pkColumnName = "id", valueColumnName = "valor", pkColumnValue = "5", allocationSize = 1)
	@Basic(optional = false)
	@Column(name = "id", nullable = false)
	private Long id;
	/**
	 * Field numero.
	 */
	@Basic(optional = false)
	@Column(name = "numero", nullable = false)
	private long numero;
	/**
	 * Field fecha.
	 */
	@Basic(optional = false)
	@Column(name = "fecha", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	// @Max(value=?) @Min(value=?)//if you know range of your decimal fields
	// consider using these annotations to enforce field validation
	/**
	 * Field montoRecaudado.
	 */
	@Basic(optional = false)
	@Column(name = "monto_recaudado", nullable = false, precision = 9, scale = 2)
	private BigDecimal montoRecaudado;
	/**
	 * Field montoFondo.
	 */
	@Basic(optional = false)
	@Column(name = "monto_fondo", nullable = false, precision = 9, scale = 2)
	private BigDecimal montoFondo;
	/**
	 * Field estaactivo.
	 */
	@Basic(optional = false)
	@Column(name = "estaactivo", nullable = false, length = 2)
	private String estaactivo;
	/**
	 * Field enlinea.
	 */
	@Basic(optional = false)
	@Column(name = "enlinea", nullable = false, length = 2)
	private String enlinea;
	/**
	 * Field tipoEntrega.
	 */
	@Basic(optional = false)
	@Column(name = "tipo_entrega", nullable = false, length = 2)
	private String tipoEntrega;
	/**
	 * Field estasincronizado.
	 */
	@Basic(optional = false)
	@Column(name = "estasincronizado", nullable = false, length = 2)
	private String estasincronizado;
	/**
	 * Field transaccionExcedida.
	 */
	@Column(name = "transaccion_excedida")
	private BigInteger transaccionExcedida;
	/**
	 * Field idUsuarioautorizante.
	 */
	@JoinColumn(name = "id_usuarioautorizante", referencedColumnName = "id", nullable = false)
	@ManyToOne(optional = false)
	private Usuario idUsuarioautorizante;
	/**
	 * Field idSesion.
	 */
	@JoinColumn(name = "id_sesion", referencedColumnName = "id", nullable = false)
	@ManyToOne(optional = false)
	private Sesion idSesion;
	/**
	 * Field entregaformadepagoList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idEntrega")
	private List<Entregaformadepago> entregaformadepagoList;

	/**
	 * Constructor for Entrega.
	 */
	public Entrega() {
	}

	/**
	 * Constructor for Entrega.
	 * 
	 * @param id
	 *            Long
	 */
	public Entrega(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Entrega.
	 * 
	 * @param id
	 *            Long
	 * @param numero
	 *            long
	 * @param fecha
	 *            Date
	 * @param montoRecaudado
	 *            BigDecimal
	 * @param montoFondo
	 *            BigDecimal
	 * @param estaactivo
	 *            String
	 * @param enlinea
	 *            String
	 * @param tipoEntrega
	 *            String
	 * @param estasincronizado
	 *            String
	 */
	public Entrega(Long id, long numero, Date fecha, BigDecimal montoRecaudado, BigDecimal montoFondo,
			String estaactivo, String enlinea, String tipoEntrega, String estasincronizado) {
		this.id = id;
		this.numero = numero;
		this.fecha = fecha;
		this.montoRecaudado = montoRecaudado;
		this.montoFondo = montoFondo;
		this.estaactivo = estaactivo;
		this.enlinea = enlinea;
		this.tipoEntrega = tipoEntrega;
		this.estasincronizado = estasincronizado;
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
	 * Method getMontoRecaudado.
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal getMontoRecaudado() {
		return montoRecaudado;
	}

	/**
	 * Method setMontoRecaudado.
	 * 
	 * @param montoRecaudado
	 *            BigDecimal
	 */
	public void setMontoRecaudado(BigDecimal montoRecaudado) {
		this.montoRecaudado = montoRecaudado;
	}

	/**
	 * Method getMontoFondo.
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal getMontoFondo() {
		return montoFondo;
	}

	/**
	 * Method setMontoFondo.
	 * 
	 * @param montoFondo
	 *            BigDecimal
	 */
	public void setMontoFondo(BigDecimal montoFondo) {
		this.montoFondo = montoFondo;
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
	 * Method getEnlinea.
	 * 
	 * @return String
	 */
	public String getEnlinea() {
		return enlinea;
	}

	/**
	 * Method setEnlinea.
	 * 
	 * @param enlinea
	 *            String
	 */
	public void setEnlinea(String enlinea) {
		this.enlinea = enlinea;
	}

	/**
	 * Method getTipoEntrega.
	 * 
	 * @return String
	 */
	public String getTipoEntrega() {
		return tipoEntrega;
	}

	/**
	 * Method setTipoEntrega.
	 * 
	 * @param tipoEntrega
	 *            String
	 */
	public void setTipoEntrega(String tipoEntrega) {
		this.tipoEntrega = tipoEntrega;
	}

	/**
	 * Method getEstasincronizado.
	 * 
	 * @return String
	 */
	public String getEstasincronizado() {
		return estasincronizado;
	}

	/**
	 * Method setEstasincronizado.
	 * 
	 * @param estasincronizado
	 *            String
	 */
	public void setEstasincronizado(String estasincronizado) {
		this.estasincronizado = estasincronizado;
	}

	/**
	 * Method getTransaccionExcedida.
	 * 
	 * @return BigInteger
	 */
	public BigInteger getTransaccionExcedida() {
		return transaccionExcedida;
	}

	/**
	 * Method setTransaccionExcedida.
	 * 
	 * @param transaccionExcedida
	 *            BigInteger
	 */
	public void setTransaccionExcedida(BigInteger transaccionExcedida) {
		this.transaccionExcedida = transaccionExcedida;
	}

	/**
	 * Method getIdUsuarioautorizante.
	 * 
	 * @return Usuario
	 */
	public Usuario getIdUsuarioautorizante() {
		return idUsuarioautorizante;
	}

	/**
	 * Method setIdUsuarioautorizante.
	 * 
	 * @param idUsuarioautorizante
	 *            Usuario
	 */
	public void setIdUsuarioautorizante(Usuario idUsuarioautorizante) {
		this.idUsuarioautorizante = idUsuarioautorizante;
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
	 * Method getEntregaformadepagoList.
	 * 
	 * @return List<Entregaformadepago>
	 */
	@XmlTransient
	public List<Entregaformadepago> getEntregaformadepagoList() {
		return entregaformadepagoList;
	}

	/**
	 * Method setEntregaformadepagoList.
	 * 
	 * @param entregaformadepagoList
	 *            List<Entregaformadepago>
	 */
	public void setEntregaformadepagoList(List<Entregaformadepago> entregaformadepagoList) {
		this.entregaformadepagoList = entregaformadepagoList;
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
		if (!(object instanceof Entrega)) {
			return false;
		}
		Entrega other = (Entrega) object;
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
		return "crjpa.Entrega[ id=" + id + " ]";
	}

}
