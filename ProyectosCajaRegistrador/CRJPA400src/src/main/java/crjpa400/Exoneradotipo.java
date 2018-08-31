/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crjpa400;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import crjpa400.interfaces.CrjpaInterface;

/**
 * 
 * @author eve0014321
 * @version $Revision: 1.0 $
 */
@Entity
@Table(name = "EXONERADOTIPO")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Exoneradotipo.findAll", query = "SELECT e FROM Exoneradotipo e"),
		@NamedQuery(name = "Exoneradotipo.findById", query = "SELECT e FROM Exoneradotipo e WHERE e.id = :id"),
		@NamedQuery(name = "Exoneradotipo.findByDescripcion", query = "SELECT e FROM Exoneradotipo e WHERE e.descripcion = :descripcion"),
		@NamedQuery(name = "Exoneradotipo.findByCalculaImpuesto", query = "SELECT e FROM Exoneradotipo e WHERE e.calculaImpuesto = :calculaImpuesto"),
		@NamedQuery(name = "Exoneradotipo.findByAplicaParcial", query = "SELECT e FROM Exoneradotipo e WHERE e.aplicaParcial = :aplicaParcial"),
		@NamedQuery(name = "Exoneradotipo.findByUltimasincronizacion", query = "SELECT e FROM Exoneradotipo e WHERE e.ultimasincronizacion = :ultimasincronizacion"),
		@NamedQuery(name = "Exoneradotipo.findByEstreplica", query = "SELECT e FROM Exoneradotipo e WHERE e.estreplica = :estreplica") })
public class Exoneradotipo extends CrjpaInterface implements Serializable {
	/**
	 * Field serialVersionUID. (value is 1)
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Field id.
	 */
	@Id
	@Basic(optional = false)
	@Column(name = "ID", nullable = false)
	private Long id;
	/**
	 * Field descripcion.
	 */
	@Basic(optional = false)
	@Column(name = "DESCRIPCION", nullable = false, length = 45)
	private String descripcion;
	/**
	 * Field calculaImpuesto.
	 */
	@Basic(optional = false)
	@Column(name = "CALCULA_IMPUESTO", nullable = false)
	private char calculaImpuesto;
	/**
	 * Field aplicaParcial.
	 */
	@Basic(optional = false)
	@Column(name = "APLICA_PARCIAL", nullable = false)
	private char aplicaParcial;
	/**
	 * Field ultimasincronizacion.
	 */
	@Basic(optional = false)
	@Column(name = "ULTIMASINCRONIZACION", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar ultimasincronizacion;
	/**
	 * Field estreplica.
	 */
	@Basic(optional = false)
	@Column(name = "ESTREPLICA")
	private char estreplica;
	/**
	 * Field exoneradoarticuloList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "exoneradotipo")
	private List<Exoneradoarticulo> exoneradoarticuloList;
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
	 *            char
	 * @param aplicaParcial
	 *            char
	 * @param ultimasincronizacion
	 *            Calendar
	 * @param estreplica
	 *            char
	 */
	public Exoneradotipo(Long id, String descripcion, char calculaImpuesto,
			char aplicaParcial, Calendar ultimasincronizacion, char estreplica) {
		this.id = id;
		this.descripcion = descripcion;
		this.calculaImpuesto = calculaImpuesto;
		this.aplicaParcial = aplicaParcial;
		this.ultimasincronizacion = ultimasincronizacion;
		this.estreplica = estreplica;
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
	 * @return char
	 */
	public char getCalculaImpuesto() {
		return calculaImpuesto;
	}

	/**
	 * Method setCalculaImpuesto.
	 * 
	 * @param calculaImpuesto
	 *            char
	 */
	public void setCalculaImpuesto(char calculaImpuesto) {
		this.calculaImpuesto = calculaImpuesto;
	}

	/**
	 * Method getAplicaParcial.
	 * 
	 * @return char
	 */
	public char getAplicaParcial() {
		return aplicaParcial;
	}

	/**
	 * Method setAplicaParcial.
	 * 
	 * @param aplicaParcial
	 *            char
	 */
	public void setAplicaParcial(char aplicaParcial) {
		this.aplicaParcial = aplicaParcial;
	}

	/**
	 * Method getUltimasincronizacion.
	 * 
	 * @return Calendar
	 */
	public Calendar getUltimasincronizacion() {
		return ultimasincronizacion;
	}

	/**
	 * Method setUltimasincronizacion.
	 * 
	 * @param ultimasincronizacion
	 *            Calendar
	 */
	public void setUltimasincronizacion(Calendar ultimasincronizacion) {
		this.ultimasincronizacion = ultimasincronizacion;
	}

	/**
	 * Method getEstreplica.
	 * 
	 * @return char
	 */
	public char getEstreplica() {
		return estreplica;
	}

	/**
	 * Method setEstreplica.
	 * 
	 * @param estreplica
	 *            char
	 */
	public void setEstreplica(char estreplica) {
		this.estreplica = estreplica;
	}

	/**
	 * Method getExoneradoarticuloList.
	 * 
	 * @return List<Exoneradoarticulo>
	 */
	@XmlTransient
	public List<Exoneradoarticulo> getExoneradoarticuloList() {
		return exoneradoarticuloList;
	}

	/**
	 * Method setExoneradoarticuloList.
	 * 
	 * @param exoneradoarticuloList
	 *            List<Exoneradoarticulo>
	 */
	public void setExoneradoarticuloList(
			List<Exoneradoarticulo> exoneradoarticuloList) {
		this.exoneradoarticuloList = exoneradoarticuloList;
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
		if ((this.id == null && other.id != null)
				|| (this.id != null && !this.id.equals(other.id))) {
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
		return "crjpa400.Exoneradotipo[ id=" + id + " ]";
	}

}
