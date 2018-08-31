/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crjpa400;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Calendar;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import crjpa400.interfaces.CrjpaInterface;

/**
 * 
 * @author eve0014321
 * @version $Revision: 1.0 $
 */
@Entity
@Table(name = "PROCESOPROPERTY")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Procesoproperty.findAll", query = "SELECT p FROM Procesoproperty p"),
		@NamedQuery(name = "Procesoproperty.findById", query = "SELECT p FROM Procesoproperty p WHERE p.id = :id"),
		@NamedQuery(name = "Procesoproperty.findByValor", query = "SELECT p FROM Procesoproperty p WHERE p.valor = :valor"),
		@NamedQuery(name = "Procesoproperty.findByDescripcion", query = "SELECT p FROM Procesoproperty p WHERE p.descripcion = :descripcion"),
		@NamedQuery(name = "Procesoproperty.findByIdOriginal", query = "SELECT p FROM Procesoproperty p WHERE p.idOriginal = :idOriginal"),
		@NamedQuery(name = "Procesoproperty.findByTipoCaja", query = "SELECT p FROM Procesoproperty p WHERE p.tipoCaja = :tipoCaja"),
		@NamedQuery(name = "Procesoproperty.findByEstaactivo", query = "SELECT p FROM Procesoproperty p WHERE p.estaactivo = :estaactivo"),
		@NamedQuery(name = "Procesoproperty.findByUltimasincronizacion", query = "SELECT p FROM Procesoproperty p WHERE p.ultimasincronizacion = :ultimasincronizacion"),
		@NamedQuery(name = "Procesoproperty.findByEstreplica", query = "SELECT p FROM Procesoproperty p WHERE p.estreplica = :estreplica") })
public class Procesoproperty extends CrjpaInterface implements Serializable {
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
	 * Field valor.
	 */
	@Basic(optional = false)
	@Column(name = "VALOR", nullable = false, length = 1000)
	private String valor;
	/**
	 * Field descripcion.
	 */
	@Basic(optional = false)
	@Column(name = "DESCRIPCION", nullable = false, length = 40)
	private String descripcion;
	/**
	 * Field idOriginal.
	 */
	@Column(name = "ID_ORIGINAL")
	private BigInteger idOriginal;
	/**
	 * Field tipoCaja.
	 */
	@Column(name = "TIPO_CAJA")
	private Integer tipoCaja;
	/**
	 * Field estaactivo.
	 */
	@Basic(optional = false)
	@Column(name = "ESTAACTIVO", nullable = false)
	private char estaactivo;
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
	 * Field idProceso.
	 */
	@JoinColumn(name = "ID_PROCESO", referencedColumnName = "ID", nullable = false)
	@ManyToOne(optional = false)
	private Proceso idProceso;

	/**
	 * Constructor for Procesoproperty.
	 */
	public Procesoproperty() {
	}

	/**
	 * Constructor for Procesoproperty.
	 * 
	 * @param id
	 *            Long
	 */
	public Procesoproperty(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Procesoproperty.
	 * 
	 * @param id
	 *            Long
	 * @param valor
	 *            String
	 * @param descripcion
	 *            String
	 * @param estaactivo
	 *            char
	 * @param ultimasincronizacion
	 *            Calendar
	 * @param estreplica
	 *            char
	 */
	public Procesoproperty(Long id, String valor, String descripcion,
			char estaactivo, Calendar ultimasincronizacion, char estreplica) {
		this.id = id;
		this.valor = valor;
		this.descripcion = descripcion;
		this.estaactivo = estaactivo;
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
	 * Method getValor.
	 * 
	 * @return String
	 */
	public String getValor() {
		return valor;
	}

	/**
	 * Method setValor.
	 * 
	 * @param valor
	 *            String
	 */
	public void setValor(String valor) {
		this.valor = valor;
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
	 * Method getIdOriginal.
	 * 
	 * @return BigInteger
	 */
	public BigInteger getIdOriginal() {
		return idOriginal;
	}

	/**
	 * Method setIdOriginal.
	 * 
	 * @param idOriginal
	 *            BigInteger
	 */
	public void setIdOriginal(BigInteger idOriginal) {
		this.idOriginal = idOriginal;
	}

	/**
	 * Method getTipoCaja.
	 * 
	 * @return Integer
	 */
	public Integer getTipoCaja() {
		return tipoCaja;
	}

	/**
	 * Method setTipoCaja.
	 * 
	 * @param tipoCaja
	 *            Integer
	 */
	public void setTipoCaja(Integer tipoCaja) {
		this.tipoCaja = tipoCaja;
	}

	/**
	 * Method getEstaactivo.
	 * 
	 * @return char
	 */
	public char getEstaactivo() {
		return estaactivo;
	}

	/**
	 * Method setEstaactivo.
	 * 
	 * @param estaactivo
	 *            char
	 */
	public void setEstaactivo(char estaactivo) {
		this.estaactivo = estaactivo;
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
	 * Method getIdProceso.
	 * 
	 * @return Proceso
	 */
	public Proceso getIdProceso() {
		return idProceso;
	}

	/**
	 * Method setIdProceso.
	 * 
	 * @param idProceso
	 *            Proceso
	 */
	public void setIdProceso(Proceso idProceso) {
		this.idProceso = idProceso;
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
		if (!(object instanceof Procesoproperty)) {
			return false;
		}
		Procesoproperty other = (Procesoproperty) object;
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
		return "crjpa400.Procesoproperty[ id=" + id + " ]";
	}

}
