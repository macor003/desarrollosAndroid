/**
 * 
 */
package crjpa400;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author eve0018536@epa.com
 */
@Entity
@Table(name = "motivodevolucion")
@NamedQueries({
		@NamedQuery(name = "Motivodevolucion.findAll", query = "SELECT m FROM Motivodevolucion m"),
		@NamedQuery(name = "Motivodevolucion.findById", query = "SELECT m FROM Motivodevolucion m WHERE m.id = :id"),
		@NamedQuery(name = "Motivodevolucion.findByCodigoMotivo", query = "SELECT m FROM Motivodevolucion m WHERE m.codigoMotivo = :codigoMotivo"),
		@NamedQuery(name = "Motivodevolucion.findByDescripcionMotivo", query = "SELECT m FROM Motivodevolucion m WHERE m.descripcionMotivo = :descripcionMotivo"),
		@NamedQuery(name = "Motivodevolucion.findByEstaactivo", query = "SELECT m FROM Motivodevolucion m WHERE m.estaactivo = :estaactivo"),
		@NamedQuery(name = "Motivodevolucion.findByTipoMotivo", query = "SELECT m FROM Motivodevolucion m WHERE m.tipoMotivo = :tipoMotivo"),
		@NamedQuery(name = "Motivodevolucion.findByFecha", query = "SELECT m FROM Motivodevolucion m WHERE m.fecha = :fecha") })
public class Motivodevolucion implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@Column(name = "ID")
	private Long id;
	@Basic(optional = false)
	@Column(name = "CODIGO_MOTIVO")
	private int codigoMotivo;
	@Basic(optional = false)
	@Column(name = "DESCRIPCION_MOTIVO")
	private String descripcionMotivo;
	@Basic(optional = false)
	@Column(name = "ESTAACTIVO")
	private String estaactivo;
	@Basic(optional = false)
	@Column(name = "TIPO_MOTIVO")
	private String tipoMotivo;
	@Basic(optional = false)
	@Column(name = "FECHA")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;

	public Motivodevolucion() {
	}

	public Motivodevolucion(Long id) {
		this.id = id;
	}

	public Motivodevolucion(Long id, int codigoMotivo, String descripcionMotivo,
			String estaactivo, String tipoMotivo, Date fecha) {
		this.id = id;
		this.codigoMotivo = codigoMotivo;
		this.descripcionMotivo = descripcionMotivo;
		this.estaactivo = estaactivo;
		this.tipoMotivo = tipoMotivo;
		this.fecha = fecha;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getCodigoMotivo() {
		return codigoMotivo;
	}

	public void setCodigoMotivo(int codigoMotivo) {
		this.codigoMotivo = codigoMotivo;
	}

	public String getDescripcionMotivo() {
		return descripcionMotivo;
	}

	public void setDescripcionMotivo(String descripcionMotivo) {
		this.descripcionMotivo = descripcionMotivo;
	}

	public String getEstaactivo() {
		return estaactivo;
	}

	public void setEstaactivo(String estaactivo) {
		this.estaactivo = estaactivo;
	}

	public String getTipoMotivo() {
		return tipoMotivo;
	}

	public void setTipoMotivo(String tipoMotivo) {
		this.tipoMotivo = tipoMotivo;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
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
		if (!(object instanceof Motivodevolucion)) {
			return false;
		}
		Motivodevolucion other = (Motivodevolucion) object;
		if ((this.id == null && other.id != null)
				|| (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "javaapplication1.Motivodevolucion[ id=" + id + " ]";
	}

}
