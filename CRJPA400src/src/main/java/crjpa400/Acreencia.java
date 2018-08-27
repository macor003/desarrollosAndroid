/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crjpa400;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * 
 * @author eve0014321
 * @version $Revision: 1.0 $
 */
@Entity
@Table(name = "ACREENCIA")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Acreencia.findAll", query = "SELECT a FROM Acreencia a"),
		@NamedQuery(name = "Acreencia.findById", query = "SELECT a FROM Acreencia a WHERE a.id = :id"),
		@NamedQuery(name = "Acreencia.findByEstado", query = "SELECT a FROM Acreencia a WHERE a.estado = :estado"),
		@NamedQuery(name = "Acreencia.findByPassword", query = "SELECT a FROM Acreencia a WHERE a.password = :password"),
		@NamedQuery(name = "Acreencia.findByOperacion", query = "SELECT a FROM Acreencia a WHERE a.operacion = :operacion"),
		@NamedQuery(name = "Acreencia.findByEstreplica", query = "SELECT a FROM Acreencia a WHERE a.estreplica = :estreplica") })
public class Acreencia implements Serializable {
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
	 * Field estado.
	 */
	@Basic(optional = false)
	@Column(name = "ESTADO", nullable = false)
	private char estado;
	/**
	 * Field password.
	 */
	@Column(name = "PASSWORD", length = 32)
	private String password;
	/**
	 * Field operacion.
	 */
	@Basic(optional = false)
	@Column(name = "OPERACION", nullable = false)
	private int operacion;
	/**
	 * Field estreplica.
	 */
	@Basic(optional = false)
	@Column(name = "ESTREPLICA")
	private char estreplica;
	/**
	 * Field acreenciamovimientosaldoList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idAcreencia")
	private List<Acreenciamovimientosaldo> acreenciamovimientosaldoList;
	/**
	 * Field numeroIdentificacionCliente.
	 */
	@JoinColumn(name = "NUMERO_IDENTIFICACION_CLIENTE", referencedColumnName = "NUMERO_IDENTIFICACION_CLIENTE", nullable = false)
	@OneToOne(optional = false)
	private Cliente numeroIdentificacionCliente;
	/**
	 * Field movimientoacreenciaList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idAcreencia")
	private List<Movimientoacreencia> movimientoacreenciaList;
	/**
	 * Field saldoacreenciaList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "acreencia")
	private List<Saldoacreencia> saldoacreenciaList;

	/**
	 * Constructor for Acreencia.
	 */
	public Acreencia() {
	}

	/**
	 * Constructor for Acreencia.
	 * 
	 * @param id
	 *            Long
	 */
	public Acreencia(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Acreencia.
	 * 
	 * @param id
	 *            Long
	 * @param estado
	 *            char
	 * @param operacion
	 *            int
	 * @param estreplica
	 *            char
	 */
	public Acreencia(Long id, char estado, int operacion, char estreplica) {
		this.id = id;
		this.estado = estado;
		this.operacion = operacion;
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
	 * Method getEstado.
	 * 
	 * @return char
	 */
	public char getEstado() {
		return estado;
	}

	/**
	 * Method setEstado.
	 * 
	 * @param estado
	 *            char
	 */
	public void setEstado(char estado) {
		this.estado = estado;
	}

	/**
	 * Method getPassword.
	 * 
	 * @return String
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Method setPassword.
	 * 
	 * @param password
	 *            String
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Method getOperacion.
	 * 
	 * @return int
	 */
	public int getOperacion() {
		return operacion;
	}

	/**
	 * Method setOperacion.
	 * 
	 * @param operacion
	 *            int
	 */
	public void setOperacion(int operacion) {
		this.operacion = operacion;
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
	 * Method getAcreenciamovimientosaldoList.
	 * 
	 * @return List<Acreenciamovimientosaldo>
	 */
	@XmlTransient
	public List<Acreenciamovimientosaldo> getAcreenciamovimientosaldoList() {
		return acreenciamovimientosaldoList;
	}

	/**
	 * Method setAcreenciamovimientosaldoList.
	 * 
	 * @param acreenciamovimientosaldoList
	 *            List<Acreenciamovimientosaldo>
	 */
	public void setAcreenciamovimientosaldoList(
			List<Acreenciamovimientosaldo> acreenciamovimientosaldoList) {
		this.acreenciamovimientosaldoList = acreenciamovimientosaldoList;
	}

	/**
	 * Method getNumeroIdentificacionCliente.
	 * 
	 * @return Cliente
	 */
	public Cliente getNumeroIdentificacionCliente() {
		return numeroIdentificacionCliente;
	}

	/**
	 * Method setNumeroIdentificacionCliente.
	 * 
	 * @param numeroIdentificacionCliente
	 *            Cliente
	 */
	public void setNumeroIdentificacionCliente(
			Cliente numeroIdentificacionCliente) {
		this.numeroIdentificacionCliente = numeroIdentificacionCliente;
	}

	/**
	 * Method getMovimientoacreenciaList.
	 * 
	 * @return List<Movimientoacreencia>
	 */
	@XmlTransient
	public List<Movimientoacreencia> getMovimientoacreenciaList() {
		return movimientoacreenciaList;
	}

	/**
	 * Method setMovimientoacreenciaList.
	 * 
	 * @param movimientoacreenciaList
	 *            List<Movimientoacreencia>
	 */
	public void setMovimientoacreenciaList(
			List<Movimientoacreencia> movimientoacreenciaList) {
		this.movimientoacreenciaList = movimientoacreenciaList;
	}

	/**
	 * Method getSaldoacreenciaList.
	 * 
	 * @return List<Saldoacreencia>
	 */
	@XmlTransient
	public List<Saldoacreencia> getSaldoacreenciaList() {
		return saldoacreenciaList;
	}

	/**
	 * Method setSaldoacreenciaList.
	 * 
	 * @param saldoacreenciaList
	 *            List<Saldoacreencia>
	 */
	public void setSaldoacreenciaList(List<Saldoacreencia> saldoacreenciaList) {
		this.saldoacreenciaList = saldoacreenciaList;
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
		if (!(object instanceof Acreencia)) {
			return false;
		}
		Acreencia other = (Acreencia) object;
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
		return "crjpa400.Acreencia[ id=" + id + " ]";
	}

}
