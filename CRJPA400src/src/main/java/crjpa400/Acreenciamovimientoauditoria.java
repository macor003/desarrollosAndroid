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
@Table(name = "acreenciamovimientoauditoria")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Acreenciamovimientoauditoria.findAll", query = "SELECT t FROM Acreenciamovimientoauditoria t"),
		@NamedQuery(name = "Acreenciamovimientoauditoria.findById", query = "SELECT t FROM Acreenciamovimientoauditoria t WHERE t.id = :id") })
public class Acreenciamovimientoauditoria implements Serializable {
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
	 * ID de acreenciamovimiento asociado a esta auditoria
	 */
	@JoinColumn(name = "id_acreenciamovimiento", referencedColumnName = "ipa_id", nullable = false)
	@ManyToOne(optional = false)
	private Acreenciamovimiento idAcreenciamovimiento;
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
	@Temporal(TemporalType.TIME)
	private Date fecha;

	/**
	 * Constructor for Transaccionfase.
	 */
	public Acreenciamovimientoauditoria() {
	}

	/**
	 * Constructor for Transaccionauditoria
	 * 
	 * @param idTransaccion
	 * @param idProceso
	 * @param idUsuario
	 * @param fecha
	 */
	public Acreenciamovimientoauditoria(Acreenciamovimiento idAcreenciamovimiento, Proceso idProceso, Usuario idUsuario, Date fecha) {
		this.idAcreenciamovimiento = idAcreenciamovimiento;
		this.idProceso = idProceso;
		this.idUsuario = idUsuario;
		this.fecha = fecha;
	}

	public Acreenciamovimiento getIdAcreenciamovimiento() {
		return idAcreenciamovimiento;
	}

	public void setIdTransaccion(Acreenciamovimiento idAcreenciamovimiento) {
		this.idAcreenciamovimiento = idAcreenciamovimiento;
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
		if (!(object instanceof Acreenciamovimientoauditoria)) {
			return false;
		}
		Acreenciamovimientoauditoria other = (Acreenciamovimientoauditoria) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "crjpa400.Acreenciamovimientoauditoria[ id=" + id + " ]";
	}

}
