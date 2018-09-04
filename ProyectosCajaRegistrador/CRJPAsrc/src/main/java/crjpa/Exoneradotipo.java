/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crjpa;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
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
@Table(name = "exoneradotipo")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Exoneradotipo.findAll", query = "SELECT e FROM Exoneradotipo e"),
		@NamedQuery(name = "Exoneradotipo.findById", query = "SELECT e FROM Exoneradotipo e WHERE e.id = :id"),
		@NamedQuery(name = "Exoneradotipo.findByDescripcion", query = "SELECT e FROM Exoneradotipo e WHERE e.descripcion = :descripcion"),
		@NamedQuery(name = "Exoneradotipo.findByCalculaImpuesto", query = "SELECT e FROM Exoneradotipo e WHERE e.calculaImpuesto = :calculaImpuesto"),
		@NamedQuery(name = "Exoneradotipo.findByAplicaParcial", query = "SELECT e FROM Exoneradotipo e WHERE e.aplicaParcial = :aplicaParcial") })
public class Exoneradotipo implements Serializable {
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
	 * Field calculaImpuesto.
	 */
	@Basic(optional = false)
	@Column(name = "calcula_impuesto")
	private String calculaImpuesto;
	/**
	 * Field aplicaParcial.
	 */
	@Basic(optional = false)
	@Column(name = "aplica_parcial")
	private String aplicaParcial;
	/**
	 * Field articuloList.
	 */
	@ManyToMany(mappedBy = "exoneradotipoList")
	private List<Articulo> articuloList;
	/**
	 * Field clienteList.
	 */
	@OneToMany(mappedBy = "idExoneradotipo")
	private List<Cliente> clienteList;

	/**
	 * Constructor for Exoneradotipo.
	 */
	public Exoneradotipo() {
	}

	/**
	 * Constructor for Exoneradotipo.
	 * 
	 * @param id
	 *            Long
	 */
	public Exoneradotipo(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Exoneradotipo.
	 * 
	 * @param id
	 *            Long
	 * @param descripcion
	 *            String
	 * @param calculaImpuesto
	 *            String
	 * @param aplicaParcial
	 *            String
	 */
	public Exoneradotipo(Long id, String descripcion, String calculaImpuesto, String aplicaParcial) {
		this.id = id;
		this.descripcion = descripcion;
		this.calculaImpuesto = calculaImpuesto;
		this.aplicaParcial = aplicaParcial;
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
	 * Method getCalculaImpuesto.
	 * 
	 * @return String
	 */
	public String getCalculaImpuesto() {
		return calculaImpuesto;
	}

	/**
	 * Method setCalculaImpuesto.
	 * 
	 * @param calculaImpuesto
	 *            String
	 */
	public void setCalculaImpuesto(String calculaImpuesto) {
		this.calculaImpuesto = calculaImpuesto;
	}

	/**
	 * Method getAplicaParcial.
	 * 
	 * @return String
	 */
	public String getAplicaParcial() {
		return aplicaParcial;
	}

	/**
	 * Method setAplicaParcial.
	 * 
	 * @param aplicaParcial
	 *            String
	 */
	public void setAplicaParcial(String aplicaParcial) {
		this.aplicaParcial = aplicaParcial;
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
	 * Method getClienteList.
	 * 
	 * @return List<Cliente>
	 */
	@XmlTransient
	public List<Cliente> getClienteList() {
		return clienteList;
	}

	/**
	 * Method setClienteList.
	 * 
	 * @param clienteList
	 *            List<Cliente>
	 */
	public void setClienteList(List<Cliente> clienteList) {
		this.clienteList = clienteList;
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
		if (!(object instanceof Exoneradotipo)) {
			return false;
		}
		Exoneradotipo other = (Exoneradotipo) object;
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
		sb.append(separator);

		sb.append(calculaImpuesto);
		sb.append(separator);

		sb.append(aplicaParcial);
		sb.append(endStr);

		return sb.toString();
	}

}
