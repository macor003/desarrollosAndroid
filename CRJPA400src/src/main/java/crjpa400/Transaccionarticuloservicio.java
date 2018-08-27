/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crjpa400;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "TRANSACCIONARTICULOSERVICIO")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Transaccionarticuloservicio.findAll", query = "SELECT t FROM Transaccionarticuloservicio t"),
		@NamedQuery(name = "Transaccionarticuloservicio.findByTelefono", query = "SELECT t FROM Transaccionarticuloservicio t WHERE t.telefono = :telefono"),
		@NamedQuery(name = "Transaccionarticuloservicio.findByCorreoElectronico", query = "SELECT t FROM Transaccionarticuloservicio t WHERE t.correoElectronico = :correoElectronico"),
		@NamedQuery(name = "Transaccionarticuloservicio.findByEstreplica", query = "SELECT t FROM Transaccionarticuloservicio t WHERE t.estreplica = :estreplica"),
		@NamedQuery(name = "Transaccionarticuloservicio.findById", query = "SELECT t FROM Transaccionarticuloservicio t WHERE t.id = :id") })
public class Transaccionarticuloservicio implements Serializable {
	/**
	 * Field serialVersionUID. (value is 1)
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Field telefono.
	 */
	@Column(name = "TELEFONO", length = 15)
	private String telefono;
	/**
	 * Field correoElectronico.
	 */
	@Column(name = "CORREO_ELECTRONICO", length = 255)
	private String correoElectronico;
	/**
	 * Field estreplica.
	 */
	@Basic(optional = false)
	@Column(name = "ESTREPLICA")
	private char estreplica;
	/**
	 * Field id.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID", nullable = false)
	private Long id;
	/**
	 * Field idServicio.
	 */
	@JoinColumn(name = "ID_SERVICIO", referencedColumnName = "ID", nullable = false)
	@ManyToOne(optional = false)
	private Servicio idServicio;
	/**
	 * Field idTransaccionarticulo.
	 */
	@JoinColumn(name = "ID_TRANSACCIONARTICULO", referencedColumnName = "ID", nullable = false)
	@ManyToOne(optional = false)
	private Transaccionarticulo idTransaccionarticulo;

	/**
	 * Constructor for Transaccionarticuloservicio.
	 */
	public Transaccionarticuloservicio() {
	}

	/**
	 * Constructor for Transaccionarticuloservicio.
	 * 
	 * @param id
	 *            Long
	 */
	public Transaccionarticuloservicio(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Transaccionarticuloservicio.
	 * 
	 * @param id
	 *            Long
	 * @param estreplica
	 *            char
	 */
	public Transaccionarticuloservicio(Long id, char estreplica) {
		this.id = id;
		this.estreplica = estreplica;
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
	 * Method getCorreoElectronico.
	 * 
	 * @return String
	 */
	public String getCorreoElectronico() {
		return correoElectronico;
	}

	/**
	 * Method setCorreoElectronico.
	 * 
	 * @param correoElectronico
	 *            String
	 */
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
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
	 * Method getIdServicio.
	 * 
	 * @return Servicio
	 */
	public Servicio getIdServicio() {
		return idServicio;
	}

	/**
	 * Method setIdServicio.
	 * 
	 * @param idServicio
	 *            Servicio
	 */
	public void setIdServicio(Servicio idServicio) {
		this.idServicio = idServicio;
	}

	/**
	 * Method getIdTransaccionarticulo.
	 * 
	 * @return Transaccionarticulo
	 */
	public Transaccionarticulo getIdTransaccionarticulo() {
		return idTransaccionarticulo;
	}

	/**
	 * Method setIdTransaccionarticulo.
	 * 
	 * @param idTransaccionarticulo
	 *            Transaccionarticulo
	 */
	public void setIdTransaccionarticulo(
			Transaccionarticulo idTransaccionarticulo) {
		this.idTransaccionarticulo = idTransaccionarticulo;
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
		if (!(object instanceof Transaccionarticuloservicio)) {
			return false;
		}
		Transaccionarticuloservicio other = (Transaccionarticuloservicio) object;
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
		return "crjpa400.Transaccionarticuloservicio[ id=" + id + " ]";
	}

}
