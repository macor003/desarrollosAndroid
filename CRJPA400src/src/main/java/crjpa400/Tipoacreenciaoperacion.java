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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "TIPOACREENCIAOPERACION")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Tipoacreenciaoperacion.findAll", query = "SELECT t FROM Tipoacreenciaoperacion t"),
		@NamedQuery(name = "Tipoacreenciaoperacion.findById", query = "SELECT t FROM Tipoacreenciaoperacion t WHERE t.id = :id"),
		@NamedQuery(name = "Tipoacreenciaoperacion.findByEsvisible", query = "SELECT t FROM Tipoacreenciaoperacion t WHERE t.esvisible = :esvisible"),
		@NamedQuery(name = "Tipoacreenciaoperacion.findByUltimasincronizacion", query = "SELECT t FROM Tipoacreenciaoperacion t WHERE t.ultimasincronizacion = :ultimasincronizacion"),
		@NamedQuery(name = "Tipoacreenciaoperacion.findByEstreplica", query = "SELECT t FROM Tipoacreenciaoperacion t WHERE t.estreplica = :estreplica") })
public class Tipoacreenciaoperacion extends CrjpaInterface
		implements Serializable {
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
	 * Field esvisible.
	 */
	@Basic(optional = false)
	@Column(name = "ESVISIBLE", nullable = false)
	private char esvisible;
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
	 * Field idTipoacreencia.
	 */
	@JoinColumn(name = "ID_TIPOACREENCIA", referencedColumnName = "ID", nullable = false)
	@ManyToOne(optional = false)
	private Tipoacreencia idTipoacreencia;
	/**
	 * Field idOperacionacreencia.
	 */
	@JoinColumn(name = "ID_OPERACIONACREENCIA", referencedColumnName = "ID", nullable = false)
	@ManyToOne(optional = false)
	private Operacionacreencia idOperacionacreencia;

	/**
	 * Field movimientoacreenciaList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoacreenciaoperacion")
	private List<Movimientoacreencia> movimientoacreenciaList;

	@JoinColumn(name = "ID_TIPOACREENCIAOPERACION", referencedColumnName = "ID", nullable = true)
	@ManyToOne
	private Tipoacreenciaoperacion contraPartida;

	@Basic(optional = true)
	@Column(name = "ID_CRTIPOACREENCIAS")
	private long creditsTypes;

	@Basic(optional = true)
	@Column(name = "CONDICION_DETVACR")
	private char condition;

	/**
	 * Constructor for Tipoacreenciaoperacion.
	 */
	public Tipoacreenciaoperacion() {
	}

	/**
	 * Constructor for Tipoacreenciaoperacion.
	 * 
	 * @param id
	 *            Long
	 */
	public Tipoacreenciaoperacion(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Tipoacreenciaoperacion.
	 * 
	 * @param id
	 *            Long
	 * @param esvisible
	 *            char
	 * @param ultimasincronizacion
	 *            Calendar
	 * @param estreplica
	 *            char
	 */
	public Tipoacreenciaoperacion(Long id, char esvisible,
			Calendar ultimasincronizacion, char estreplica) {
		this.id = id;
		this.esvisible = esvisible;
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
	 * Method getEsvisible.
	 * 
	 * @return char
	 */
	public char getEsvisible() {
		return esvisible;
	}

	/**
	 * Method setEsvisible.
	 * 
	 * @param esvisible
	 *            char
	 */
	public void setEsvisible(char esvisible) {
		this.esvisible = esvisible;
	}

	/**
	 * Method getUltimasincronizacion.
	 * 
	 * @return Calendar
	 */
	@Override
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
	 * Method getIdTipoacreencia.
	 * 
	 * @return Tipoacreencia
	 */
	public Tipoacreencia getIdTipoacreencia() {
		return idTipoacreencia;
	}

	/**
	 * Method setIdTipoacreencia.
	 * 
	 * @param idTipoacreencia
	 *            Tipoacreencia
	 */
	public void setIdTipoacreencia(Tipoacreencia idTipoacreencia) {
		this.idTipoacreencia = idTipoacreencia;
	}

	/**
	 * Method getIdOperacionacreencia.
	 * 
	 * @return Operacionacreencia
	 */
	public Operacionacreencia getIdOperacionacreencia() {
		return idOperacionacreencia;
	}

	/**
	 * Method setIdOperacionacreencia.
	 * 
	 * @param idOperacionacreencia
	 *            Operacionacreencia
	 */
	public void setIdOperacionacreencia(
			Operacionacreencia idOperacionacreencia) {
		this.idOperacionacreencia = idOperacionacreencia;
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

	public long getCreditsTypes() {
		return creditsTypes;
	}

	public void setCreditsTypes(long creditsTypes) {
		this.creditsTypes = creditsTypes;
	}

	public char getCondition() {
		return condition;
	}

	public void setCondition(char condition) {
		this.condition = condition;
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
		if (!(object instanceof Tipoacreenciaoperacion)) {
			return false;
		}
		Tipoacreenciaoperacion other = (Tipoacreenciaoperacion) object;
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
		return "crjpa400src.Tipoacreenciaoperacion[ id=" + id + " ]";
	}

	public Tipoacreenciaoperacion getContraPartida() {
		return contraPartida;
	}

	public void setContraPartida(Tipoacreenciaoperacion contraPartida) {
		this.contraPartida = contraPartida;
	}
}
