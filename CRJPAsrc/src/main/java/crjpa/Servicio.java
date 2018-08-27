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
import javax.persistence.ManyToMany;
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
@Table(name = "servicio")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Servicio.findAll", query = "SELECT s FROM Servicio s"),
		@NamedQuery(name = "Servicio.findById", query = "SELECT s FROM Servicio s WHERE s.id = :id"),
		@NamedQuery(name = "Servicio.findByDescripcion", query = "SELECT s FROM Servicio s WHERE s.descripcion = :descripcion") })
public class Servicio implements Serializable {
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
	 * Field articuloList.
	 */
	@ManyToMany(mappedBy = "servicioList")
	private List<Articulo> articuloList;
	/**
	 * Field transaccionarticuloservicioList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idServicio")
	private List<Transaccionarticuloservicio> transaccionarticuloservicioList;

	/**
	 * Constructor for Servicio.
	 */
	public Servicio() {
	}

	/**
	 * Constructor for Servicio.
	 * 
	 * @param id
	 *            Long
	 */
	public Servicio(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Servicio.
	 * 
	 * @param id
	 *            Long
	 * @param descripcion
	 *            String
	 */
	public Servicio(Long id, String descripcion) {
		this.id = id;
		this.descripcion = descripcion;
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
	 * Method getArticuloList.
	 * 
	 * @return List<Articulo>
	 */
	@XmlTransient
	public List<Articulo> getArticuloList() {
		return articuloList;
	}

	/**
	 * Method setArticuloList.
	 * 
	 * @param articuloList
	 *            List<Articulo>
	 */
	public void setArticuloList(List<Articulo> articuloList) {
		this.articuloList = articuloList;
	}

	/**
	 * Method getTransaccionarticuloservicioList.
	 * 
	 * @return List<Transaccionarticuloservicio>
	 */
	@XmlTransient
	public List<Transaccionarticuloservicio> getTransaccionarticuloservicioList() {
		return transaccionarticuloservicioList;
	}

	/**
	 * Method setTransaccionarticuloservicioList.
	 * 
	 * @param transaccionarticuloservicioList
	 *            List<Transaccionarticuloservicio>
	 */
	public void setTransaccionarticuloservicioList(List<Transaccionarticuloservicio> transaccionarticuloservicioList) {
		this.transaccionarticuloservicioList = transaccionarticuloservicioList;
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
		if (!(object instanceof Servicio)) {
			return false;
		}
		Servicio other = (Servicio) object;
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

		sb.append(enclosed);
		sb.append(descripcion);
		sb.append(enclosed);

		sb.append(endStr);

		return sb.toString();
	}

}
