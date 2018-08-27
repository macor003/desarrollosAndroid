package crjpa;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 */
@Entity
@Table(name = "sincronizacionpendientes")
@IdClass(SincPendientesId.class)
public class Sincronizacionpendientes implements Serializable {

	/**
	 * Field serialVersionUID. (value is 1)
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Field idTabla.
	 */
	@Id
	@Column(name = "tabla_id")
	private String idTabla;

	/**
	 * Field idSincronziacion.
	 */
	@Id
	@JoinColumn(name = "id_sincronziacion", referencedColumnName = "id")
	@ManyToOne
	private Sincronizacion idSincronziacion;

	/**
	 * Field mensajeError.
	 */
	@Basic(optional = false)
	@Column(name = "mensaje_error")
	private String mensajeError;

	/**
	 * Field fecha.
	 */
	@Basic(optional = false)
	@Column(name = "fecha")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;

	/**
	 * Field estasincronizado.
	 */
	@Basic(optional = false)
	@Column(name = "estasincronizado", nullable = false, length = 2)
	private String estasincronizado;

	/**
	 * Method getMensajeError.
	 * 
	 * @return String
	 */
	public String getMensajeError() {
		return mensajeError;
	}

	/**
	 * Method setMensajeError.
	 * 
	 * @param mensajeError
	 *            String
	 */
	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
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
	 * Method getIdSincronziacion.
	 * 
	 * @return Sincronizacion
	 */
	public Sincronizacion getIdSincronziacion() {
		return idSincronziacion;
	}

	/**
	 * Method setIdSincronziacion.
	 * 
	 * @param idSincronziacion
	 *            Sincronizacion
	 */
	public void setIdSincronziacion(Sincronizacion idSincronziacion) {
		this.idSincronziacion = idSincronziacion;
	}

	/**
	 * Method getIdTabla.
	 * 
	 * @return String
	 */
	public String getIdTabla() {
		return idTabla;
	}

	/**
	 * Method setIdTabla.
	 * 
	 * @param idTabla
	 *            String
	 */
	public void setIdTabla(String idTabla) {
		this.idTabla = idTabla;
	}

	/**
	 * Method getEstasincronizado.
	 * 
	 * @return String
	 */
	public final String getEstasincronizado() {
		return estasincronizado;
	}

	/**
	 * Method setEstasincronizado.
	 * 
	 * @param estasincronizado
	 *            String
	 */
	public final void setEstasincronizado(String estasincronizado) {
		this.estasincronizado = estasincronizado;
	}

}
