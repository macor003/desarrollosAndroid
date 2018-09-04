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
@Table(name = "MOVIMIENTOACREENCIA")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Movimientoacreencia.findAll", query = "SELECT m FROM Movimientoacreencia m"),
		@NamedQuery(name = "Movimientoacreencia.findById", query = "SELECT m FROM Movimientoacreencia m WHERE m.id = :id"),
		@NamedQuery(name = "Movimientoacreencia.findByIdTransaccion", query = "SELECT m FROM Movimientoacreencia m WHERE m.idTransaccion = :idTransaccion"),
		@NamedQuery(name = "Movimientoacreencia.findByTienda", query = "SELECT m FROM Movimientoacreencia m WHERE m.tienda = :tienda"),
		@NamedQuery(name = "Movimientoacreencia.findByMonto", query = "SELECT m FROM Movimientoacreencia m WHERE m.monto = :monto"),
		@NamedQuery(name = "Movimientoacreencia.findByCaja", query = "SELECT m FROM Movimientoacreencia m WHERE m.caja = :caja"),
		@NamedQuery(name = "Movimientoacreencia.findByNumeroop", query = "SELECT m FROM Movimientoacreencia m WHERE m.numeroop = :numeroop"),
		@NamedQuery(name = "Movimientoacreencia.findByFecha", query = "SELECT m FROM Movimientoacreencia m WHERE m.fecha = :fecha"),
		@NamedQuery(name = "Movimientoacreencia.findByCajero", query = "SELECT m FROM Movimientoacreencia m WHERE m.cajero = :cajero"),
		@NamedQuery(name = "Movimientoacreencia.findByEstado", query = "SELECT m FROM Movimientoacreencia m WHERE m.estado = :estado"),
		@NamedQuery(name = "Movimientoacreencia.findByDevolucioncheque", query = "SELECT m FROM Movimientoacreencia m WHERE m.devolucioncheque = :devolucioncheque"),
		@NamedQuery(name = "Movimientoacreencia.findByAnulaoperacion", query = "SELECT m FROM Movimientoacreencia m WHERE m.anulaoperacion = :anulaoperacion"),
		@NamedQuery(name = "Movimientoacreencia.findByEstreplica", query = "SELECT m FROM Movimientoacreencia m WHERE m.estreplica = :estreplica"),
		@NamedQuery(name = "Movimientoacreencia.findBySaldo", query = "SELECT m FROM Movimientoacreencia m WHERE m.saldo = :saldo"),
		@NamedQuery(name = "Movimientoacreencia.findByBloqueado", query = "SELECT m FROM Movimientoacreencia m WHERE m.bloqueado = :bloqueado"),
		@NamedQuery(name = "Movimientoacreencia.updateStatus", query = "UPDATE Movimientoacreencia mt SET mt.ipaStatus= ?1 WHERE mt.ipaId= ?2") })
public class Movimientoacreencia implements Serializable {
	private static final long serialVersionUID = 2L;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID", nullable = false)
	private Long id;

	@Column(name = "ID_TRANSACCION")
	private BigInteger idTransaccion;

	@Column(name = "TIENDA")
	private Integer tienda;

	@Column(name = "MONTO", precision = 13, scale = 2)
	private BigDecimal monto;

	@Column(name = "CAJA")
	private Integer caja;

	@Column(name = "NUMEROOP")
	private Integer numeroop;

	@Column(name = "FECHA")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;

	@Basic(optional = false)
	@Column(name = "CAJERO", nullable = false)
	private int cajero;

	@Basic(optional = false)
	@Column(name = "ESTADO", nullable = false)
	private char estado;

	@Basic(optional = false)
	@Column(name = "DEVOLUCIONCHEQUE", nullable = false)
	private char devolucioncheque;

	@Column(name = "ANULAOPERACION")
	private Integer anulaoperacion;

	@Basic(optional = false)
	@Column(name = "ESTREPLICA")
	private char estreplica;

	@Column(name = "SALDO")
	private BigDecimal saldo;

	@Column(name = "BLOQUEADO")
	private BigDecimal bloqueado;

	@OneToMany(mappedBy = "idMovimientoacreencia")
	private List<Formadepagomovacreencia> formadepagomovacreenciaList;

	@JoinColumn(name = "ID_TIPOACREENCIAOPERACION", referencedColumnName = "ID", nullable = false)
	@ManyToOne(optional = false)
	private Tipoacreenciaoperacion idTipoacreenciaoperacion;

	@JoinColumn(name = "ID_ACREENCIA", referencedColumnName = "ID", nullable = false)
	@ManyToOne(optional = false)
	private Acreencia idAcreencia;

	@Basic(optional = false)
	@Column(name = "IPA_STATUS")
	private char ipaStatus = 'A';

	@Id
	@Basic(optional = false)
	@Column(name = "IPA_ID")
	private Long ipaId = 0l;

	@Basic(optional = false)
	@Column(name = "VUELTO", precision = 13, scale = 2)
	private BigDecimal vuelto;

	/**
	 * Constructor for Movimientoacreencia.
	 */
	public Movimientoacreencia() {
	}

	/**
	 * Constructor for Movimientoacreencia.
	 *
	 * @param ipaId
	 *            Long
	 */
	public Movimientoacreencia(Long ipaId) {
		this.ipaId = ipaId;
	}

	/**
	 * Constructor for Movimientoacreencia.
	 *
	 * @param ipaId
	 *            Long
	 * @param cajero
	 *            int
	 * @param estado
	 *            char
	 * @param devolucioncheque
	 *            char
	 * @param estreplica
	 *            char
	 */
	public Movimientoacreencia(Long ipaId, int cajero, char estado,
			char devolucioncheque, char estreplica) {
		this.ipaId = ipaId;
		this.cajero = cajero;
		this.estado = estado;
		this.devolucioncheque = devolucioncheque;
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
	 * Method getIdTransaccion.
	 *
	 * @return BigInteger
	 */
	public BigInteger getIdTransaccion() {
		return idTransaccion;
	}

	/**
	 * Method setIdTransaccion.
	 *
	 * @param idTransaccion
	 *            BigInteger
	 */
	public void setIdTransaccion(BigInteger idTransaccion) {
		this.idTransaccion = idTransaccion;
	}

	/**
	 * Method getTienda.
	 *
	 * @return Integer
	 */
	public Integer getTienda() {
		return tienda;
	}

	/**
	 * Method setTienda.
	 *
	 * @param tienda
	 *            Integer
	 */
	public void setTienda(Integer tienda) {
		this.tienda = tienda;
	}

	/**
	 * Method getMonto.
	 *
	 * @return BigDecimal
	 */
	public BigDecimal getMonto() {
		return monto;
	}

	/**
	 * Method setMonto.
	 *
	 * @param monto
	 *            BigDecimal
	 */
	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	/**
	 * Method getCaja.
	 *
	 * @return Integer
	 */
	public Integer getCaja() {
		return caja;
	}

	/**
	 * Method setCaja.
	 *
	 * @param caja
	 *            Integer
	 */
	public void setCaja(Integer caja) {
		this.caja = caja;
	}

	/**
	 * Method getNumeroop.
	 *
	 * @return Integer
	 */
	public Integer getNumeroop() {
		return numeroop;
	}

	/**
	 * Method setNumeroop.
	 *
	 * @param numeroop
	 *            Integer
	 */
	public void setNumeroop(Integer numeroop) {
		this.numeroop = numeroop;
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
	 * Method getDevolucioncheque.
	 *
	 * @return char
	 */
	public char getDevolucioncheque() {
		return devolucioncheque;
	}

	/**
	 * Method setDevolucioncheque.
	 *
	 * @param devolucioncheque
	 *            char
	 */
	public void setDevolucioncheque(char devolucioncheque) {
		this.devolucioncheque = devolucioncheque;
	}

	/**
	 * Method getAnulaoperacion.
	 *
	 * @return Integer
	 */
	public Integer getAnulaoperacion() {
		return anulaoperacion;
	}

	/**
	 * Method setAnulaoperacion.
	 *
	 * @param anulaoperacion
	 *            Integer
	 */
	public void setAnulaoperacion(Integer anulaoperacion) {
		this.anulaoperacion = anulaoperacion;
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
	 * Method getSaldo.
	 *
	 * @return BigDecimal
	 */
	public BigDecimal getSaldo() {
		return saldo;
	}

	/**
	 * Method setSaldo.
	 *
	 * @param saldo
	 *            BigDecimal
	 */
	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	/**
	 * Method getBloqueado.
	 *
	 * @return BigDecimal
	 */
	public BigDecimal getBloqueado() {
		return bloqueado;
	}

	/**
	 * Method setBloqueado.
	 *
	 * @param bloqueado
	 *            BigDecimal
	 */
	public void setBloqueado(BigDecimal bloqueado) {
		this.bloqueado = bloqueado;
	}

	/**
	 * Method getFormadepagomovacreenciaList.
	 *
	 * @return List<Formadepagomovacreencia>
	 */
	@XmlTransient
	public List<Formadepagomovacreencia> getFormadepagomovacreenciaList() {
		return formadepagomovacreenciaList;
	}

	/**
	 * Method setFormadepagomovacreenciaList.
	 *
	 * @param formadepagomovacreenciaList
	 *            List<Formadepagomovacreencia>
	 */
	public void setFormadepagomovacreenciaList(
			List<Formadepagomovacreencia> formadepagomovacreenciaList) {
		this.formadepagomovacreenciaList = formadepagomovacreenciaList;
	}

	/**
	 * Method getIdTipoacreenciaoperacion.
	 *
	 * @return Tipoacreenciaoperacion
	 */
	public Tipoacreenciaoperacion getIdTipoacreenciaoperacion() {
		return idTipoacreenciaoperacion;
	}

	/**
	 * Method setIdTipoacreenciaoperacion.
	 *
	 * @param idTipoacreenciaoperacion
	 *            Tipoacreenciaoperacion
	 */
	public void setIdTipoacreenciaoperacion(
			Tipoacreenciaoperacion idTipoacreenciaoperacion) {
		this.idTipoacreenciaoperacion = idTipoacreenciaoperacion;
	}

	/**
	 * Method getIdAcreencia.
	 *
	 * @return Acreencia
	 */
	public Acreencia getIdAcreencia() {
		return idAcreencia;
	}

	/**
	 * Method setIdAcreencia.
	 *
	 * @param idAcreencia
	 *            Acreencia
	 */
	public void setIdAcreencia(Acreencia idAcreencia) {
		this.idAcreencia = idAcreencia;
	}

	/**
	 * Getter ipaStatus.
	 * 
	 * @return ipaStatus
	 */
	public char getIpaStatus() {
		return ipaStatus;
	}

	/**
	 * Setter ipaStatus.
	 * 
	 * @param ipaStatus
	 */
	public void setIpaStatus(char ipaStatus) {
		this.ipaStatus = ipaStatus;
	}

	/**
	 * Getter ipaId.
	 * 
	 * @return ipaId
	 */
	public Long getIpaId() {
		return ipaId;
	}

	/**
	 * Setter ipaId
	 * 
	 * @param ipaId
	 *            CR generated id
	 */
	public void setIpaId(Long ipaId) {
		this.ipaId = ipaId;
	}

	public BigDecimal getVuelto() {
		return vuelto;
	}

	public void setVuelto(BigDecimal vuelto) {
		this.vuelto = vuelto;
	}

	/**
	 * Method hashCode.
	 *
	 * @return int
	 */
	@Override
	public int hashCode() {
		int hash = 0;
		hash += (ipaId != null ? ipaId.hashCode() : 0);
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
		if (!(object instanceof Movimientoacreencia)) {
			return false;
		}
		Movimientoacreencia other = (Movimientoacreencia) object;
		if ((this.ipaId == null && other.ipaId != null)
				|| (this.ipaId != null && !this.ipaId.equals(other.ipaId))) {
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
		return "crjpa400.Movimientoacreencia[ ipaId=" + ipaId + " ]";
	}

}
