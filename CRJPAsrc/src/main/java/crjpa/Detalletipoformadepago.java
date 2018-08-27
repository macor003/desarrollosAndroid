/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crjpa;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * 
 * @author eve0014321
 * @version $Revision: 1.0 $
 */
@Entity
@Table(name = "detalletipoformadepago")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Detalletipoformadepago.findAll", query = "SELECT d FROM Detalletipoformadepago d"),
		@NamedQuery(name = "Detalletipoformadepago.findById", query = "SELECT d FROM Detalletipoformadepago d WHERE d.id = :id"),
		@NamedQuery(name = "Detalletipoformadepago.findByDescripcion", query = "SELECT d FROM Detalletipoformadepago d WHERE d.descripcion = :descripcion"),
		@NamedQuery(name = "Detalletipoformadepago.findByEstaactivo", query = "SELECT d FROM Detalletipoformadepago d WHERE d.estaactivo = :estaactivo") })
public class Detalletipoformadepago implements Serializable {
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
	 * Field detalletipoformadepagolineaList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idDetalletipoformadepago")
	private List<Detalletipoformadepagolinea> detalletipoformadepagolineaList;
	/**
	 * Field idFormadepago.
	 */
	@JoinColumn(name = "id_formadepago", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Formadepago idFormadepago;

	/**
	 * Constructor for Detalletipoformadepago.
	 */
	public Detalletipoformadepago() {
	}

	/**
	 * Constructor for Detalletipoformadepago.
	 * 
	 * @param id
	 *            Long
	 */
	public Detalletipoformadepago(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Detalletipoformadepago.
	 * 
	 * @param id
	 *            Long
	 * @param descripcion
	 *            String
	 * @param estaactivo
	 *            String
	 */
	public Detalletipoformadepago(Long id, String descripcion, String estaactivo) {
		this.id = id;
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
	 * Method getDetalletipoformadepagolineaList.
	 * 
	 * @return List<Detalletipoformadepagolinea>
	 */
	@XmlTransient
	public List<Detalletipoformadepagolinea> getDetalletipoformadepagolineaList() {
		return detalletipoformadepagolineaList;
	}

	/**
	 * Method setDetalletipoformadepagolineaList.
	 * 
	 * @param detalletipoformadepagolineaList
	 *            List<Detalletipoformadepagolinea>
	 */
	public void setDetalletipoformadepagolineaList(List<Detalletipoformadepagolinea> detalletipoformadepagolineaList) {
		this.detalletipoformadepagolineaList = detalletipoformadepagolineaList;
	}

	/**
	 * Method getIdFormadepago.
	 * 
	 * @return Formadepago
	 */
	public Formadepago getIdFormadepago() {
		return idFormadepago;
	}

	/**
	 * Method setIdFormadepago.
	 * 
	 * @param idFormadepago
	 *            Formadepago
	 */
	public void setIdFormadepago(Formadepago idFormadepago) {
		this.idFormadepago = idFormadepago;
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
		if (!(object instanceof Detalletipoformadepago)) {
			return false;
		}
		Detalletipoformadepago other = (Detalletipoformadepago) object;
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

		sb.append(idFormadepago.getId());
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
