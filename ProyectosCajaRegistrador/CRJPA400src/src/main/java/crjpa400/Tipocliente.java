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
import java.util.Date;
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
@Table(name = "TIPOCLIENTE")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Tipocliente.findAll", query = "SELECT t FROM Tipocliente t"),
		@NamedQuery(name = "Tipocliente.findById", query = "SELECT t FROM Tipocliente t WHERE t.id = :id"),
		@NamedQuery(name = "Tipocliente.findBySimbolo", query = "SELECT t FROM Tipocliente t WHERE t.simbolo = :simbolo"),
		@NamedQuery(name = "Tipocliente.findByNombre", query = "SELECT t FROM Tipocliente t WHERE t.nombre = :nombre"),
		@NamedQuery(name = "Tipocliente.findByMascara", query = "SELECT t FROM Tipocliente t WHERE t.mascara = :mascara"),
		@NamedQuery(name = "Tipocliente.findByEstaactivo", query = "SELECT t FROM Tipocliente t WHERE t.estaactivo = :estaactivo"),
		@NamedQuery(name = "Tipocliente.findByFecha", query = "SELECT t FROM Tipocliente t WHERE t.fecha = :fecha"),
		@NamedQuery(name = "Tipocliente.findByConstanteValidacion", query = "SELECT t FROM Tipocliente t WHERE t.constanteValidacion = :constanteValidacion"),
		@NamedQuery(name = "Tipocliente.findByEscontribuyente", query = "SELECT t FROM Tipocliente t WHERE t.escontribuyente = :escontribuyente"),
		@NamedQuery(name = "Tipocliente.findByUltimasincronizacion", query = "SELECT t FROM Tipocliente t WHERE t.ultimasincronizacion = :ultimasincronizacion"),
		@NamedQuery(name = "Tipocliente.findByEstreplica", query = "SELECT t FROM Tipocliente t WHERE t.estreplica = :estreplica") })
public class Tipocliente extends CrjpaInterface implements Serializable {
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
	 * Field simbolo.
	 */
	@Basic(optional = false)
	@Column(name = "SIMBOLO", nullable = false)
	private char simbolo;
	/**
	 * Field nombre.
	 */
	@Basic(optional = false)
	@Column(name = "NOMBRE", nullable = false, length = 40)
	private String nombre;
	/**
	 * Field mascara.
	 */
	@Basic(optional = false)
	@Column(name = "MASCARA", nullable = false, length = 40)
	private String mascara;
	/**
	 * Field estaactivo.
	 */
	@Basic(optional = false)
	@Column(name = "ESTAACTIVO", nullable = false)
	private char estaactivo;
	/**
	 * Field fecha.
	 */
	@Basic(optional = false)
	@Column(name = "FECHA", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	/**
	 * Field constanteValidacion.
	 */
	@Basic(optional = false)
	@Column(name = "CONSTANTE_VALIDACION", nullable = false)
	private long constanteValidacion;
	/**
	 * Field escontribuyente.
	 */
	@Basic(optional = false)
	@Column(name = "ESCONTRIBUYENTE", nullable = false)
	private char escontribuyente;
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
	 * Field tipoclientetipodocumentoList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipocliente")
	private List<Tipoclientetipodocumento> tipoclientetipodocumentoList;
	/**
	 * Field tipoidentificacionclienteList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipocliente")
	private List<Tipoidentificacioncliente> tipoidentificacionclienteList;
	/**
	 * Field clienteList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipocliente")
	private List<Cliente> clienteList;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipocliente")
    private List<Formadepagotipocliente> formadepagotipoclienteList;
	
	/**
	 * Constructor for Tipocliente.
	 */
	public Tipocliente() {
	}

	/**
	 * Constructor for Tipocliente.
	 * 
	 * @param id
	 *            Long
	 */
	public Tipocliente(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Tipocliente.
	 * 
	 * @param id
	 *            Long
	 * @param simbolo
	 *            char
	 * @param nombre
	 *            String
	 * @param mascara
	 *            String
	 * @param estaactivo
	 *            char
	 * @param fecha
	 *            Date
	 * @param constanteValidacion
	 *            long
	 * @param escontribuyente
	 *            char
	 * @param ultimasincronizacion
	 *            Calendar
	 * @param estreplica
	 *            char
	 */
	public Tipocliente(Long id, char simbolo, String nombre, String mascara,
			char estaactivo, Date fecha, long constanteValidacion,
			char escontribuyente, Calendar ultimasincronizacion, char estreplica) {
		this.id = id;
		this.simbolo = simbolo;
		this.nombre = nombre;
		this.mascara = mascara;
		this.estaactivo = estaactivo;
		this.fecha = fecha;
		this.constanteValidacion = constanteValidacion;
		this.escontribuyente = escontribuyente;
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
	 * Method getSimbolo.
	 * 
	 * @return char
	 */
	public char getSimbolo() {
		return simbolo;
	}

	/**
	 * Method setSimbolo.
	 * 
	 * @param simbolo
	 *            char
	 */
	public void setSimbolo(char simbolo) {
		this.simbolo = simbolo;
	}

	/**
	 * Method getNombre.
	 * 
	 * @return String
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Method setNombre.
	 * 
	 * @param nombre
	 *            String
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Method getMascara.
	 * 
	 * @return String
	 */
	public String getMascara() {
		return mascara;
	}

	/**
	 * Method setMascara.
	 * 
	 * @param mascara
	 *            String
	 */
	public void setMascara(String mascara) {
		this.mascara = mascara;
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
	 * Method getFecha.
	 * 
	 * @return Date
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * Method setFecha.
	 * 
	 * @param fecha
	 *            Date
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/**
	 * Method getConstanteValidacion.
	 * 
	 * @return long
	 */
	public long getConstanteValidacion() {
		return constanteValidacion;
	}

	/**
	 * Method setConstanteValidacion.
	 * 
	 * @param constanteValidacion
	 *            long
	 */
	public void setConstanteValidacion(long constanteValidacion) {
		this.constanteValidacion = constanteValidacion;
	}

	/**
	 * Method getEscontribuyente.
	 * 
	 * @return char
	 */
	public char getEscontribuyente() {
		return escontribuyente;
	}

	/**
	 * Method setEscontribuyente.
	 * 
	 * @param escontribuyente
	 *            char
	 */
	public void setEscontribuyente(char escontribuyente) {
		this.escontribuyente = escontribuyente;
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
	 * Method getTipoclientetipodocumentoList.
	 * 
	 * @return List<Tipoclientetipodocumento>
	 */
	@XmlTransient
	public List<Tipoclientetipodocumento> getTipoclientetipodocumentoList() {
		return tipoclientetipodocumentoList;
	}

	/**
	 * Method setTipoclientetipodocumentoList.
	 * 
	 * @param tipoclientetipodocumentoList
	 *            List<Tipoclientetipodocumento>
	 */
	public void setTipoclientetipodocumentoList(
			List<Tipoclientetipodocumento> tipoclientetipodocumentoList) {
		this.tipoclientetipodocumentoList = tipoclientetipodocumentoList;
	}

	/**
	 * Method getTipoidentificacionclienteList.
	 * 
	 * @return List<Tipoidentificacioncliente>
	 */
	@XmlTransient
	public List<Tipoidentificacioncliente> getTipoidentificacionclienteList() {
		return tipoidentificacionclienteList;
	}

	/**
	 * Method setTipoidentificacionclienteList.
	 * 
	 * @param tipoidentificacionclienteList
	 *            List<Tipoidentificacioncliente>
	 */
	public void setTipoidentificacionclienteList(
			List<Tipoidentificacioncliente> tipoidentificacionclienteList) {
		this.tipoidentificacionclienteList = tipoidentificacionclienteList;
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
	
	@XmlTransient
    public List<Formadepagotipocliente> getFormadepagotipoclienteList() {
        return formadepagotipoclienteList;
    }

    public void setFormadepagotipoclienteList(List<Formadepagotipocliente> formadepagotipoclienteList) {
        this.formadepagotipoclienteList = formadepagotipoclienteList;
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
		if (!(object instanceof Tipocliente)) {
			return false;
		}
		Tipocliente other = (Tipocliente) object;
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
		return "crjpa400.Tipocliente[ id=" + id + " ]";
	}

}
