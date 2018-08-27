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
import javax.persistence.Lob;
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
@Table(name = "procesoproperty")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Procesoproperty.findAll", query = "SELECT p FROM Procesoproperty p"),
		@NamedQuery(name = "Procesoproperty.findById", query = "SELECT p FROM Procesoproperty p WHERE p.id = :id"),
		@NamedQuery(name = "Procesoproperty.findByDescripcion", query = "SELECT p FROM Procesoproperty p WHERE p.descripcion = :descripcion"),
		@NamedQuery(name = "Procesoproperty.findByEstaactivo", query = "SELECT p FROM Procesoproperty p WHERE p.estaactivo = :estaactivo") })
public class Procesoproperty implements Serializable {
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
	 * Field valor.
	 */
	@Basic(optional = false)
	@Lob
	@Column(name = "valor")
	private String valor;
	/**
	 * Field descripcion.
	 */
	@Basic(optional = false)
	@Column(name = "descripcion")
	private String descripcion;
	/**
	 * Field estaactivo.
	 */
	@Basic(optional = false)
	@Column(name = "estaactivo")
	private String estaactivo;
	/**
	 * Field idProceso.
	 */
	@JoinColumn(name = "id_proceso", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Proceso idProceso;

	/**
	 * Constructor for Procesoproperty.
	 */
	public Procesoproperty() {
	}

	/**
	 * Constructor for Procesoproperty.
	 * 
	 * @param id
	 *            Long
	 */
	public Procesoproperty(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Procesoproperty.
	 * 
	 * @param id
	 *            Long
	 * @param valor
	 *            String
	 * @param descripcion
	 *            String
	 * @param estaactivo
	 *            String
	 */
	public Procesoproperty(Long id, String valor, String descripcion, String estaactivo) {
		this.id = id;
		this.valor = valor;
		this.descripcion = descripcion;
		this.estaactivo = estaactivo;
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
	 * Method getValor.
	 * 
	 * @return String
	 */
	public String getValor() {
		return valor;
	}

	/**
	 * Method setValor.
	 * 
	 * @param valor
	 *            String
	 */
	public void setValor(String valor) {
		this.valor = valor;
	}

	/**
	 * Method getDescripcion.
	 * 
	 * @return String
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Method setDescripcion.
	 * 
	 * @param descripcion
	 *            String
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
	 * Method getIdProceso.
	 * 
	 * @return Proceso
	 */
	public Proceso getIdProceso() {
		return idProceso;
	}

	/**
	 * Method setIdProceso.
	 * 
	 * @param idProceso
	 *            Proceso
	 */
	public void setIdProceso(Proceso idProceso) {
		this.idProceso = idProceso;
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
		if (!(object instanceof Procesoproperty)) {
			return false;
		}
		Procesoproperty other = (Procesoproperty) object;
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
		String enclosed = "|";
		String endStr = "\r\n";

		StringBuilder sb = new StringBuilder();

		sb.append(id);
		sb.append(separator);

		sb.append(idProceso.getId());
		sb.append(separator);

		sb.append(enclosed);
		sb.append(valor);
		sb.append(enclosed);
		sb.append(separator);

		sb.append(enclosed);
		sb.append(descripcion);
		sb.append(enclosed);
		sb.append(separator);

		sb.append(estaactivo);
		sb.append(endStr);
		return sb.toString();
	}

}
