/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crjpa400;

import java.io.Serializable;
import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "TRANSACCIONAUDITORIA")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Transaccionauditoria.findAll", query = "SELECT t FROM Transaccionauditoria t"),
		@NamedQuery(name = "Transaccionauditoria.findById", query = "SELECT t FROM Transaccionauditoria t WHERE t.id = :id") })
public class Transaccionauditoria implements Serializable {
	/**
	 * Field serialVersionUID. (value is 1)
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Id del registro, es autogenerado por la base de datos
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id", nullable = false)
	private Long id;
	/**
	 * ID de la transaccion asociada a esta auditoria
	 */
	@JoinColumn(name = "id_transaccion", referencedColumnName = "id", nullable = false)
	@ManyToOne(optional = false)
	private Transaccion idTransaccion;
	/**
	 * Proceso autorizado
	 */
	@JoinColumn(name = "id_proceso", referencedColumnName = "id", nullable = false)
	@ManyToOne(optional = false)
	private Proceso idProceso;
	/**
	 * Usuario que autorizo el proceso
	 */
	@JoinColumn(name = "id_usuario", referencedColumnName = "id", nullable = false)
	@ManyToOne(optional = false)
	private Usuario idUsuario;
	/**
	 * Fecha y hora en que se autorizo el proceso
	 */
	@Basic(optional = false)
	@Column(name = "fecha", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;

	/**
	 * Constructor for Transaccionfase.
	 */
	public Transaccionauditoria() {
	}

	/**
	 * Constructor for Transaccionauditoria
	 * 
	 * @param idTransaccion
	 * @param idProceso
	 * @param idUsuario
	 * @param fecha
	 */
	public Transaccionauditoria(Transaccion idTransaccion, Proceso idProceso, Usuario idUsuario, Date fecha) {
		this.idTransaccion = idTransaccion;
		this.idProceso = idProceso;
		this.idUsuario = idUsuario;
		this.fecha = fecha;
	}

	public Transaccion getIdTransaccion() {
		return idTransaccion;
	}

	public void setIdTransaccion(Transaccion idTransaccion) {
		this.idTransaccion = idTransaccion;
	}

	public Proceso getIdProceso() {
		return idProceso;
	}

	public void setIdProceso(Proceso idProceso) {
		this.idProceso = idProceso;
	}

	public Usuario getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Usuario idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Long getId() {
		return id;
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

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Transaccionauditoria)) {
			return false;
		}
		Transaccionauditoria other = (Transaccionauditoria) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "crjpa400.Transaccionauditoria[ id=" + id + " ]";
	}

}
