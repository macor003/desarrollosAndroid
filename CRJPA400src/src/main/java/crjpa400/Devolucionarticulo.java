/**
 * 
 */
package crjpa400;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author eve0018536@epa.com
 */
@Entity
@Table(name = "devolucionarticulo")
@NamedQueries({
		@NamedQuery(name = "Devolucionarticulo.findAll", query = "SELECT d FROM Devolucionarticulo d"),
		@NamedQuery(name = "Devolucionarticulo.findById", query = "SELECT d FROM Devolucionarticulo d WHERE d.id = :id"),
		@NamedQuery(name = "Devolucionarticulo.findByIdTransaccionarticulo", query = "SELECT d FROM Devolucionarticulo d WHERE d.idTransaccionarticulo = :idTransaccionarticulo"),
		@NamedQuery(name = "Devolucionarticulo.findByIdArticulo", query = "SELECT d FROM Devolucionarticulo d WHERE d.idArticulo = :idArticulo"),
		@NamedQuery(name = "Devolucionarticulo.findByCodigoArticulo", query = "SELECT d FROM Devolucionarticulo d WHERE d.codigoArticulo = :codigoArticulo"),
		@NamedQuery(name = "Devolucionarticulo.findByPrecioArticulo", query = "SELECT d FROM Devolucionarticulo d WHERE d.precioArticulo = :precioArticulo"),
		@NamedQuery(name = "Devolucionarticulo.findByMontoRebaja", query = "SELECT d FROM Devolucionarticulo d WHERE d.montoRebaja = :montoRebaja"),
		@NamedQuery(name = "Devolucionarticulo.findByCantidadArticulo", query = "SELECT d FROM Devolucionarticulo d WHERE d.cantidadArticulo = :cantidadArticulo"),
		@NamedQuery(name = "Devolucionarticulo.findByIdMotivodevolucion", query = "SELECT d FROM Devolucionarticulo d WHERE d.idMotivodevolucion = :idMotivodevolucion"),
		@NamedQuery(name = "Devolucionarticulo.findByIdPromocion", query = "SELECT d FROM Devolucionarticulo d WHERE d.idPromocion = :idPromocion"),
		@NamedQuery(name = "Devolucionarticulo.findByIdTipodescuento", query = "SELECT d FROM Devolucionarticulo d WHERE d.idTipodescuento = :idTipodescuento"),
		@NamedQuery(name = "Devolucionarticulo.findByEstatusDev", query = "SELECT d FROM Devolucionarticulo d WHERE d.estatusDev = :estatusDev") })
public class Devolucionarticulo implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@Column(name = "ID")
	private Long id;
	@Basic(optional = false)
	@Column(name = "ID_TRANSACCIONARTICULO")
	private long idTransaccionarticulo;
	@Basic(optional = false)
	@Column(name = "ID_ARTICULO")
	private long idArticulo;
	@Basic(optional = false)
	@Column(name = "CODIGO_ARTICULO")
	private long codigoArticulo;
	// @Max(value=?) @Min(value=?)//if you know range of your decimal fields
	// consider using these annotations to enforce field validation
	@Basic(optional = false)
	@Column(name = "PRECIO_ARTICULO")
	private BigDecimal precioArticulo;
	@Basic(optional = false)
	@Column(name = "MONTO_REBAJA")
	private BigDecimal montoRebaja;
	@Basic(optional = false)
	@Column(name = "CANTIDAD_ARTICULO")
	private long cantidadArticulo;
	@Basic(optional = false)
	@Column(name = "ID_MOTIVODEVOLUCION")
	private long idMotivodevolucion;
	@Basic(optional = false)
	@Column(name = "ID_PROMOCION")
	private long idPromocion;
	@Basic(optional = false)
	@Column(name = "ID_TIPODESCUENTO")
	private long idTipodescuento;
	@Basic(optional = false)
	@Column(name = "ESTATUS_DEV")
	private Character estatusDev;
	@JoinColumn(name = "ID_DEVOLUCION", referencedColumnName = "ID")
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private Devolucion idDevolucion;

	public Devolucionarticulo() {
	}

	public Devolucionarticulo(Long id) {
		this.id = id;
	}

	public Devolucionarticulo(Long id, long idTransaccionarticulo,
			long idArticulo, long codigoArticulo, BigDecimal precioArticulo,
			BigDecimal montoRebaja, long cantidadArticulo,
			long idMotivodevolucion, long idPromocion, long idTipodescuento,
			Character estatusDev) {
		this.id = id;
		this.idTransaccionarticulo = idTransaccionarticulo;
		this.idArticulo = idArticulo;
		this.codigoArticulo = codigoArticulo;
		this.precioArticulo = precioArticulo;
		this.montoRebaja = montoRebaja;
		this.cantidadArticulo = cantidadArticulo;
		this.idMotivodevolucion = idMotivodevolucion;
		this.idPromocion = idPromocion;
		this.idTipodescuento = idTipodescuento;
		this.estatusDev = estatusDev;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getIdTransaccionarticulo() {
		return idTransaccionarticulo;
	}

	public void setIdTransaccionarticulo(long idTransaccionarticulo) {
		this.idTransaccionarticulo = idTransaccionarticulo;
	}

	public long getIdArticulo() {
		return idArticulo;
	}

	public void setIdArticulo(long idArticulo) {
		this.idArticulo = idArticulo;
	}

	public long getCodigoArticulo() {
		return codigoArticulo;
	}

	public void setCodigoArticulo(long codigoArticulo) {
		this.codigoArticulo = codigoArticulo;
	}

	public BigDecimal getPrecioArticulo() {
		return precioArticulo;
	}

	public void setPrecioArticulo(BigDecimal precioArticulo) {
		this.precioArticulo = precioArticulo;
	}

	public BigDecimal getMontoRebaja() {
		return montoRebaja;
	}

	public void setMontoRebaja(BigDecimal montoRebaja) {
		this.montoRebaja = montoRebaja;
	}

	public long getCantidadArticulo() {
		return cantidadArticulo;
	}

	public void setCantidadArticulo(long cantidadArticulo) {
		this.cantidadArticulo = cantidadArticulo;
	}

	public long getIdMotivodevolucion() {
		return idMotivodevolucion;
	}

	public void setIdMotivodevolucion(long idMotivodevolucion) {
		this.idMotivodevolucion = idMotivodevolucion;
	}

	public long getIdPromocion() {
		return idPromocion;
	}

	public void setIdPromocion(long idPromocion) {
		this.idPromocion = idPromocion;
	}

	public long getIdTipodescuento() {
		return idTipodescuento;
	}

	public void setIdTipodescuento(long idTipodescuento) {
		this.idTipodescuento = idTipodescuento;
	}

	public Character getEstatusDev() {
		return estatusDev;
	}

	public void setEstatusDev(Character estatusDev) {
		this.estatusDev = estatusDev;
	}

	public Devolucion getIdDevolucion() {
		return idDevolucion;
	}

	public void setIdDevolucion(Devolucion idDevolucion) {
		this.idDevolucion = idDevolucion;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Devolucionarticulo)) {
			return false;
		}
		Devolucionarticulo other = (Devolucionarticulo) object;
		if ((this.id == null && other.id != null)
				|| (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "JPA.Devolucionarticulo[ id=" + id + " ]";
	}

}
