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

/**
 * 
 * @author eve0014321
 * @version $Revision: 1.0 $
 */
@Entity
@Table(name = "ENTREGA")
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
		@NamedQuery(name = "Entrega.findByEstreplica", query = "SELECT e FROM Entrega e WHERE e.estreplica = :estreplica"),
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
	 * Field fecha.
	 */
	@Basic(optional = false)
	@Column(name = "FECHA", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	// @Max(value=?) @Min(value=?)//if you know range of your decimal fields
	// consider using these annotations to enforce field validation
	/**
	 * Field montoRecaudado.
	 */
	@Basic(optional = false)
	@Column(name = "MONTO_RECAUDADO", nullable = false, precision = 13, scale = 2)
	private BigDecimal montoRecaudado;
	/**
	 * Field montoFondo.
	 */
	@Basic(optional = false)
	@Column(name = "MONTO_FONDO", nullable = false, precision = 13, scale = 2)
	private BigDecimal montoFondo;
	/**
	 * Field estaactivo.
	 */
	@Basic(optional = false)
	@Column(name = "ESTAACTIVO", nullable = false)
	private char estaactivo;
	/**
	 * Field enlinea.
	 */
	@Basic(optional = false)
	@Column(name = "ENLINEA", nullable = false)
	private char enlinea;
	/**
	 * Field tipoEntrega.
	 */
	@Basic(optional = false)
	@Column(name = "TIPO_ENTREGA", nullable = false)
	private char tipoEntrega;
	/**
	 * Field estreplica.
	 */
	@Basic(optional = false)
	@Column(name = "ESTREPLICA")
	private char estreplica;
	/**
	 * Field transaccionExcedida.
	 */
	@Column(name = "TRANSACCION_EXCEDIDA")
	private BigInteger transaccionExcedida;
	/**
	 * Field idUsuarioautorizante.
	 */
	@JoinColumn(name = "ID_USUARIOAUTORIZANTE", referencedColumnName = "ID", nullable = false)
	@ManyToOne(optional = false)
	private Usuario idUsuarioautorizante;
	/**
	 * Field idSesion.
	 */
	@JoinColumn(name = "ID_SESION", referencedColumnName = "ID", nullable = false)
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
	 *            char
	 * @param enlinea
	 *            char
	 * @param tipoEntrega
	 *            char
	 * @param estreplica
	 *            char
	 */
	public Entrega(Long id, long numero, Date fecha, BigDecimal montoRecaudado,
			BigDecimal montoFondo, char estaactivo, char enlinea,
			char tipoEntrega, char estreplica) {
		this.id = id;
		this.numero = numero;
		this.fecha = fecha;
		this.montoRecaudado = montoRecaudado;
		this.montoFondo = montoFondo;
		this.estaactivo = estaactivo;
		this.enlinea = enlinea;
		this.tipoEntrega = tipoEntrega;
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
	 * Method getEnlinea.
	 * 
	 * @return char
	 */
	public char getEnlinea() {
		return enlinea;
	}

	/**
	 * Method setEnlinea.
	 * 
	 * @param enlinea
	 *            char
	 */
	public void setEnlinea(char enlinea) {
		this.enlinea = enlinea;
	}

	/**
	 * Method getTipoEntrega.
	 * 
	 * @return char
	 */
	public char getTipoEntrega() {
		return tipoEntrega;
	}

	/**
	 * Method setTipoEntrega.
	 * 
	 * @param tipoEntrega
	 *            char
	 */
	public void setTipoEntrega(char tipoEntrega) {
		this.tipoEntrega = tipoEntrega;
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
	public void setEntregaformadepagoList(
			List<Entregaformadepago> entregaformadepagoList) {
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
		return "crjpa400.Entrega[ id=" + id + " ]";
	}

}
