package crjpa;

import java.io.Serializable;

/**
 */
public class SincPendientesId implements Serializable {

	/**
	 * Field serialVersionUID. (value is 1)
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Field idTabla.
	 */
	private String idTabla;
	/**
	 * Field idSincronziacion.
	 */
	private Long idSincronziacion;

	/**
	 * Constructor for SincPendientesId.
	 */
	public SincPendientesId() {
	}

	/**
	 * Method getId.
	 * 
	 * @return String
	 */
	public String getId() {
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
	 * Method getIdSincronziacion.
	 * 
	 * @return Long
	 */
	public Long getIdSincronziacion() {
		return idSincronziacion;
	}

	/**
	 * Method setIdSincronziacion.
	 * 
	 * @param idSincronziacion
	 *            Long
	 */
	public void setIdSincronziacion(Long idSincronziacion) {
		this.idSincronziacion = idSincronziacion;
	}

	/**
	 * Method equals.
	 * 
	 * @param obj
	 *            Object
	 * @return boolean
	 */
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof SincPendientesId)) {
			return false;
		}

		SincPendientesId pk = (SincPendientesId) obj;
		return pk.idTabla.equals(this.idTabla) && pk.idSincronziacion.equals(this.idSincronziacion);
	}

}
