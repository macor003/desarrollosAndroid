/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crjpa;

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
import javax.persistence.TableGenerator;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author eve0014321
 * @version $Revision: 1.0 $
 */
@Entity
@Table(name = "transaccionarticuloservicio")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Transaccionarticuloservicio.findAll", query = "SELECT t FROM Transaccionarticuloservicio t"),
		@NamedQuery(name = "Transaccionarticuloservicio.findById", query = "SELECT t FROM Transaccionarticuloservicio t WHERE t.id = :id"),
		@NamedQuery(name = "Transaccionarticuloservicio.findByTelefono", query = "SELECT t FROM Transaccionarticuloservicio t WHERE t.telefono = :telefono"),
		@NamedQuery(name = "Transaccionarticuloservicio.findByCorreoElectronico", query = "SELECT t FROM Transaccionarticuloservicio t WHERE t.correoElectronico = :correoElectronico"),
		@NamedQuery(name = "Transaccionarticuloservicio.findByEstasincronizado", query = "SELECT t FROM Transaccionarticuloservicio t WHERE t.estasincronizado = :estasincronizado") })
public class Transaccionarticuloservicio implements Serializable {
	/**
	 * Field serialVersionUID. (value is 1)
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Field id.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "13")
	@TableGenerator(name = "13", table = "correlativo", pkColumnName = "id", valueColumnName = "valor", pkColumnValue = "13", allocationSize = 1)
	@Basic(optional = false)
	@Column(name = "id", nullable = false)
	private Long id;
	/**
	 * Field telefono.
	 */
	@Column(name = "telefono", length = 15)
	private String telefono;
	/**
	 * Field correoElectronico.
	 */
	@Column(name = "correo_electronico", length = 255)
	private String correoElectronico;
	/**
	 * Field estasincronizado.
	 */
	@Basic(optional = false)
	@Column(name = "estasincronizado", nullable = false, length = 2)
	private String estasincronizado;
	/**
	 * Field idTransaccionarticulo.
	 */
	@JoinColumn(name = "id_transaccionarticulo", referencedColumnName = "id", nullable = false)
	@ManyToOne(optional = false)
	private Transaccionarticulo idTransaccionarticulo;
	/**
	 * Field idServicio.
	 */
	@JoinColumn(name = "id_servicio", referencedColumnName = "id", nullable = false)
	@ManyToOne(optional = false)
	private Servicio idServicio;

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
	 * @param estasincronizado
	 *            String
	 */
	public Transaccionarticuloservicio(Long id, String estasincronizado) {
		this.id = id;
		this.estasincronizado = estasincronizado;
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
	 * Method getEstasincronizado.
	 * 
	 * @return String
	 */
	public String getEstasincronizado() {
		return estasincronizado;
	}

	/**
	 * Method setEstasincronizado.
	 * 
	 * @param estasincronizado
	 *            String
	 */
	public void setEstasincronizado(String estasincronizado) {
		this.estasincronizado = estasincronizado;
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
	public void setIdTransaccionarticulo(Transaccionarticulo idTransaccionarticulo) {
		this.idTransaccionarticulo = idTransaccionarticulo;
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
		return "crjpa.Transaccionarticuloservicio[ id=" + id + " ]";
	}

}
