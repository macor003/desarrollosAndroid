/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crjpa;

import java.io.Serializable;
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
@Table(name = "rolloauditoria")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Rolloauditoria.findAll", query = "SELECT r FROM Rolloauditoria r"),
		@NamedQuery(name = "Rolloauditoria.findById", query = "SELECT r FROM Rolloauditoria r WHERE r.id = :id"),
		@NamedQuery(name = "Rolloauditoria.findByCaja", query = "SELECT r FROM Rolloauditoria r WHERE r.caja = :caja"),
		@NamedQuery(name = "Rolloauditoria.findByTienda", query = "SELECT r FROM Rolloauditoria r WHERE r.tienda = :tienda"),
		@NamedQuery(name = "Rolloauditoria.findByPrimeraTransaccion", query = "SELECT r FROM Rolloauditoria r WHERE r.primeraTransaccion = :primeraTransaccion"),
		@NamedQuery(name = "Rolloauditoria.findByUltimaTransaccion", query = "SELECT r FROM Rolloauditoria r WHERE r.ultimaTransaccion = :ultimaTransaccion"),
		@NamedQuery(name = "Rolloauditoria.findByFechaCambio", query = "SELECT r FROM Rolloauditoria r WHERE r.fechaCambio = :fechaCambio"),
		@NamedQuery(name = "Rolloauditoria.findByEstasincronizado", query = "SELECT r FROM Rolloauditoria r WHERE r.estasincronizado = :estasincronizado") })
public class Rolloauditoria implements Serializable {
	/**
	 * Field serialVersionUID. (value is 1)
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Field id.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "3")
	@TableGenerator(name = "3", table = "correlativo", pkColumnName = "id", valueColumnName = "valor", pkColumnValue = "3", allocationSize = 1)
	@Basic(optional = false)
	@Column(name = "id", nullable = false)
	private Long id;
	/**
	 * Field caja.
	 */
	@Basic(optional = false)
	@Column(name = "caja", nullable = false)
	private int caja;
	/**
	 * Field tienda.
	 */
	@Basic(optional = false)
	@Column(name = "tienda", nullable = false)
	private int tienda;
	/**
	 * Field primeraTransaccion.
	 */
	@Column(name = "primera_transaccion")
	private Integer primeraTransaccion;
	/**
	 * Field ultimaTransaccion.
	 */
	@Column(name = "ultima_transaccion")
	private Integer ultimaTransaccion;
	/**
	 * Field fechaCambio.
	 */
	@Basic(optional = false)
	@Column(name = "fecha_cambio", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaCambio;
	/**
	 * Field estasincronizado.
	 */
	@Basic(optional = false)
	@Column(name = "estasincronizado", nullable = false, length = 2)
	private String estasincronizado;
	/**
	 * Field transaccionList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idRolloauditoria")
	private List<Transaccion> transaccionList;
	/**
	 * Field idSerialimpresora.
	 */
	@JoinColumn(name = "id_serialimpresora", referencedColumnName = "id", nullable = false)
	@ManyToOne(optional = false)
	private Serialimpresora idSerialimpresora;
	/**
	 * Field idUsuario.
	 */
	@JoinColumn(name = "id_usuario", referencedColumnName = "id", nullable = false)
	@ManyToOne(optional = false)
	private Usuario idUsuario;

	/**
	 * Constructor for Rolloauditoria.
	 */
	public Rolloauditoria() {
	}

	/**
	 * Constructor for Rolloauditoria.
	 * 
	 * @param id
	 *            Long
	 */
	public Rolloauditoria(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Rolloauditoria.
	 * 
	 * @param id
	 *            Long
	 * @param caja
	 *            int
	 * @param tienda
	 *            int
	 * @param fechaCambio
	 *            Date
	 * @param estasincronizado
	 *            String
	 */
	public Rolloauditoria(Long id, int caja, int tienda, Date fechaCambio, String estasincronizado) {
		this.id = id;
		this.caja = caja;
		this.tienda = tienda;
		this.fechaCambio = fechaCambio;
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
	 * Method getCaja.
	 * 
	 * @return int
	 */
	public int getCaja() {
		return caja;
	}

	/**
	 * Method setCaja.
	 * 
	 * @param caja
	 *            int
	 */
	public void setCaja(int caja) {
		this.caja = caja;
	}

	/**
	 * Method getTienda.
	 * 
	 * @return int
	 */
	public int getTienda() {
		return tienda;
	}

	/**
	 * Method setTienda.
	 * 
	 * @param tienda
	 *            int
	 */
	public void setTienda(int tienda) {
		this.tienda = tienda;
	}

	/**
	 * Method getPrimeraTransaccion.
	 * 
	 * @return Integer
	 */
	public Integer getPrimeraTransaccion() {
		return primeraTransaccion;
	}

	/**
	 * Method setPrimeraTransaccion.
	 * 
	 * @param primeraTransaccion
	 *            Integer
	 */
	public void setPrimeraTransaccion(Integer primeraTransaccion) {
		this.primeraTransaccion = primeraTransaccion;
	}

	/**
	 * Method getUltimaTransaccion.
	 * 
	 * @return Integer
	 */
	public Integer getUltimaTransaccion() {
		return ultimaTransaccion;
	}

	/**
	 * Method setUltimaTransaccion.
	 * 
	 * @param ultimaTransaccion
	 *            Integer
	 */
	public void setUltimaTransaccion(Integer ultimaTransaccion) {
		this.ultimaTransaccion = ultimaTransaccion;
	}

	/**
	 * Method getFechaCambio.
	 * 
	 * @return Date
	 */
	public Date getFechaCambio() {
		return fechaCambio;
	}

	/**
	 * Method setFechaCambio.
	 * 
	 * @param fechaCambio
	 *            Date
	 */
	public void setFechaCambio(Date fechaCambio) {
		this.fechaCambio = fechaCambio;
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
	 * Method getTransaccionList.
	 * 
	 * @return List<Transaccion>
	 */
	@XmlTransient
	public List<Transaccion> getTransaccionList() {
		return transaccionList;
	}

	/**
	 * Method setTransaccionList.
	 * 
	 * @param transaccionList
	 *            List<Transaccion>
	 */
	public void setTransaccionList(List<Transaccion> transaccionList) {
		this.transaccionList = transaccionList;
	}

	/**
	 * Method getIdSerialimpresora.
	 * 
	 * @return Serialimpresora
	 */
	public Serialimpresora getIdSerialimpresora() {
		return idSerialimpresora;
	}

	/**
	 * Method setIdSerialimpresora.
	 * 
	 * @param idSerialimpresora
	 *            Serialimpresora
	 */
	public void setIdSerialimpresora(Serialimpresora idSerialimpresora) {
		this.idSerialimpresora = idSerialimpresora;
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
		if (!(object instanceof Rolloauditoria)) {
			return false;
		}
		Rolloauditoria other = (Rolloauditoria) object;
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
		return "crjpa.Rolloauditoria[ id=" + id + " ]";
	}

}
