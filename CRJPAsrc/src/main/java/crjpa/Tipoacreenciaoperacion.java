/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crjpa;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author eve0014321
 * @version $Revision: 1.0 $
 */
@Entity
@Table(name = "tipoacreenciaoperacion")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Tipoacreenciaoperacion.findAll", query = "SELECT t FROM Tipoacreenciaoperacion t"),
		@NamedQuery(name = "Tipoacreenciaoperacion.findById", query = "SELECT t FROM Tipoacreenciaoperacion t WHERE t.id = :id"),
		@NamedQuery(name = "Tipoacreenciaoperacion.findByEsvisible", query = "SELECT t FROM Tipoacreenciaoperacion t WHERE t.esvisible = :esvisible") })
public class Tipoacreenciaoperacion implements Serializable {
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
	 * Field esvisible.
	 */
	@Basic(optional = false)
	@Column(name = "esvisible")
	private String esvisible;
	/**
	 * Field idTipoacreencia.
	 */
	@JoinColumn(name = "id_tipoacreencia", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Tipoacreencia idTipoacreencia;
	/**
	 * Field idOperacionacreencia.
	 */
	@JoinColumn(name = "id_operacionacreencia", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Operacionacreencia idOperacionacreencia;

	/**
	 * Constructor for Tipoacreenciaoperacion.
	 */
	public Tipoacreenciaoperacion() {
	}

	/**
	 * Constructor for Tipoacreenciaoperacion.
	 * 
	 * @param id
	 *            Long
	 */
	public Tipoacreenciaoperacion(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Tipoacreenciaoperacion.
	 * 
	 * @param id
	 *            Long
	 * @param esvisible
	 *            String
	 */
	public Tipoacreenciaoperacion(Long id, String esvisible) {
		this.id = id;
		this.esvisible = esvisible;
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
	 * Method getEsvisible.
	 * 
	 * @return String
	 */
	public String getEsvisible() {
		return esvisible;
	}

	/**
	 * Method setEsvisible.
	 * 
	 * @param esvisible
	 *            String
	 */
	public void setEsvisible(String esvisible) {
		this.esvisible = esvisible;
	}

	/**
	 * Method getIdTipoacreencia.
	 * 
	 * @return Tipoacreencia
	 */
	public Tipoacreencia getIdTipoacreencia() {
		return idTipoacreencia;
	}

	/**
	 * Method setIdTipoacreencia.
	 * 
	 * @param idTipoacreencia
	 *            Tipoacreencia
	 */
	public void setIdTipoacreencia(Tipoacreencia idTipoacreencia) {
		this.idTipoacreencia = idTipoacreencia;
	}

	/**
	 * Method getIdOperacionacreencia.
	 * 
	 * @return Operacionacreencia
	 */
	public Operacionacreencia getIdOperacionacreencia() {
		return idOperacionacreencia;
	}

	/**
	 * Method setIdOperacionacreencia.
	 * 
	 * @param idOperacionacreencia
	 *            Operacionacreencia
	 */
	public void setIdOperacionacreencia(Operacionacreencia idOperacionacreencia) {
		this.idOperacionacreencia = idOperacionacreencia;
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
		if (!(object instanceof Tipoacreenciaoperacion)) {
			return false;
		}
		Tipoacreenciaoperacion other = (Tipoacreenciaoperacion) object;
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
		String endStr = "\r\n";

		StringBuilder sb = new StringBuilder();

		sb.append(id);
		sb.append(separator);

		sb.append(idTipoacreencia.getId());
		sb.append(separator);

		sb.append(idOperacionacreencia.getId());
		sb.append(separator);

		sb.append(esvisible);

		sb.append(endStr);

		return sb.toString();
	}

}
