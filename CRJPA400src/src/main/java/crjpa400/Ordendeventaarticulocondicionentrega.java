/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crjpa400;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "ORDENDEVENTAARTICULOCONDICIONENTREGA")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Ordendeventaarticulocondicionentrega.findAll", query = "SELECT o FROM Ordendeventaarticulocondicionentrega o"),
		@NamedQuery(name = "Ordendeventaarticulocondicionentrega.findByIdArticulo", query = "SELECT o FROM Ordendeventaarticulocondicionentrega o WHERE o.ordendeventaarticulocondicionentregaPK.idOrdendeventaarticulo = :idOrdendeventaarticulo"),
		@NamedQuery(name = "Ordendeventaarticulocondicionentrega.findByIdServicio", query = "SELECT o FROM Ordendeventaarticulocondicionentrega o WHERE o.ordendeventaarticulocondicionentregaPK.idCondicionentrega = :idCondicionentrega"),
		@NamedQuery(name = "Ordendeventaarticulocondicionentrega.findByNumeroIdentificacionCliente", query = "SELECT o FROM Ordendeventaarticulocondicionentrega o WHERE o.numeroIdentificacionCliente = :numeroIdentificacionCliente"),
		@NamedQuery(name = "Ordendeventaarticulocondicionentrega.findByNombreCliente", query = "SELECT o FROM Ordendeventaarticulocondicionentrega o WHERE o.nombreCliente = :nombreCliente"),
		@NamedQuery(name = "Ordendeventaarticulocondicionentrega.findByTelefono", query = "SELECT o FROM Ordendeventaarticulocondicionentrega o WHERE o.telefono = :telefono"),
		@NamedQuery(name = "Ordendeventaarticulocondicionentrega.findByDireccion", query = "SELECT o FROM Ordendeventaarticulocondicionentrega o WHERE o.direccion = :direccion"),
		@NamedQuery(name = "Ordendeventaarticulocondicionentrega.findByEstreplica", query = "SELECT o FROM Ordendeventaarticulocondicionentrega o WHERE o.estreplica = :estreplica") })
public class Ordendeventaarticulocondicionentrega implements Serializable {
	/**
	 * Field serialVersionUID. (value is 1)
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Field ordendeventaarticulocondicionentregaPK.
	 */
	@EmbeddedId
	protected OrdendeventaarticulocondicionentregaPK ordendeventaarticulocondicionentregaPK;

	/**
	 * Field numeroIdentificacionCliente.
	 */
	@Column(name = "NUMERO_IDENTIFICACION_CLIENTE", length = 25)
	private String numeroIdentificacionCliente;

	/**
	 * Field nombreCliente.
	 */
	@Column(name = "NOMBRE_CLIENTE", length = 255)
	private String nombreCliente;

	/**
	 * Field telefono.
	 */
	@Column(name = "TELEFONO", length = 15)
	private String telefono;

	/**
	 * Field direccion.
	 */
	@Column(name = "DIRECCION", length = 255)
	private String direccion;

	/**
	 * Field estreplica.
	 */
	@Column(name = "ESTREPLICA")
	private char estreplica;

	/**
	 * Field idOrdendeventaarticulo.
	 */
	@JoinColumn(name = "ID_ORDENDEVENTAARTICULO", referencedColumnName = "ID", insertable = false, updatable = false, nullable = false)
	@ManyToOne(optional = false)
	private Ordendeventaarticulo idOrdendeventaarticulo;

	/**
	 * Field idCondicionentrega.
	 */
	@JoinColumn(name = "ID_CONDICIONENTREGA", referencedColumnName = "ID", insertable = false, updatable = false, nullable = false)
	@ManyToOne(optional = false)
	private Condicionentrega idCondicionentrega;

	/**
	 * Constructor for Ordendeventaarticulocondicionentrega.
	 */
	public Ordendeventaarticulocondicionentrega() {
	}

	/**
	 * @param idOrdendeventaarticulo
	 * @param idCondicionentrega
	 */
	public Ordendeventaarticulocondicionentrega(
			Ordendeventaarticulo idOrdendeventaarticulo,
			Condicionentrega idCondicionentrega) {
		this.idOrdendeventaarticulo = idOrdendeventaarticulo;
		this.idCondicionentrega = idCondicionentrega;
	}

	/**
	 * Method getTelefono.
	 * 
	 * @return String
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * Method setTelefono.
	 * 
	 * @param telefono
	 *            String
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
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
	 * Method getNumeroIdentificacionCliente.
	 * 
	 * @return String
	 */
	public String getNumeroIdentificacionCliente() {
		return numeroIdentificacionCliente;
	}

	/**
	 * Method setNumeroIdentificacionCliente.
	 * 
	 * @param numeroIdentificacionCliente
	 *            String
	 */
	public void setNumeroIdentificacionCliente(
			String numeroIdentificacionCliente) {
		this.numeroIdentificacionCliente = numeroIdentificacionCliente;
	}

	/**
	 * Method getNombreCliente.
	 * 
	 * @return String
	 */
	public String getNombreCliente() {
		return nombreCliente;
	}

	/**
	 * Method setNombreCliente.
	 * 
	 * @param nombreCliente
	 *            String
	 */
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	/**
	 * Method getDireccion.
	 * 
	 * @return String
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * Method setDireccion.
	 * 
	 * @param direccion
	 *            String
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * Method getIdOrdendeventaarticulo.
	 * 
	 * @return Ordendeventaarticulo
	 */
	public Ordendeventaarticulo getIdOrdendeventaarticulo() {
		return idOrdendeventaarticulo;
	}

	/**
	 * Method setIdOrdendeventaarticulo.
	 * 
	 * @param idOrdendeventaarticulo
	 *            Ordendeventaarticulo
	 */
	public void setIdOrdendeventaarticulo(
			Ordendeventaarticulo idOrdendeventaarticulo) {
		this.idOrdendeventaarticulo = idOrdendeventaarticulo;
	}

	/**
	 * Method getIdCondicionentrega.
	 * 
	 * @return Condicionentrega
	 */
	public Condicionentrega getIdCondicionentrega() {
		return idCondicionentrega;
	}

	/**
	 * Method setIdCondicionentrega.
	 * 
	 * @param idCondicionentrega
	 *            Condicionentrega
	 */
	public void setIdCondicionentrega(Condicionentrega idCondicionentrega) {
		this.idCondicionentrega = idCondicionentrega;
	}

	/**
	 * Method hashCode.
	 * 
	 * @return int
	 */
	@Override
	public int hashCode() {
		int hash = 0;
		hash += (ordendeventaarticulocondicionentregaPK != null ? ordendeventaarticulocondicionentregaPK
				.hashCode() : 0);
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
		if (!(object instanceof Ordendeventaarticulocondicionentrega)) {
			return false;
		}
		Ordendeventaarticulocondicionentrega other = (Ordendeventaarticulocondicionentrega) object;
		if ((this.ordendeventaarticulocondicionentregaPK == null && other.ordendeventaarticulocondicionentregaPK != null)
				|| (this.ordendeventaarticulocondicionentregaPK != null && !this.ordendeventaarticulocondicionentregaPK
						.equals(other.ordendeventaarticulocondicionentregaPK))) {
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
		return "crjpa400.Ordendeventaarticulocondicionentrega[ ordendeventaarticulocondicionentregaPK="
				+ ordendeventaarticulocondicionentregaPK + " ]";
	}

}
