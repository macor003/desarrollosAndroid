/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crjpa;

import java.io.Serializable;
import java.text.SimpleDateFormat;
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

/**
 * 
 * @author eve0014321
 * @version $Revision: 1.0 $
 */
@Entity
@Table(name = "tipocliente")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Tipocliente.findAll", query = "SELECT t FROM Tipocliente t"),
		@NamedQuery(name = "Tipocliente.findById", query = "SELECT t FROM Tipocliente t WHERE t.id = :id"),
		@NamedQuery(name = "Tipocliente.findBySimbolo", query = "SELECT t FROM Tipocliente t WHERE t.simbolo = :simbolo"),
		@NamedQuery(name = "Tipocliente.findByNombre", query = "SELECT t FROM Tipocliente t WHERE t.nombre = :nombre"),
		@NamedQuery(name = "Tipocliente.findByMascara", query = "SELECT t FROM Tipocliente t WHERE t.mascara = :mascara"),
		@NamedQuery(name = "Tipocliente.findByFecha", query = "SELECT t FROM Tipocliente t WHERE t.fecha = :fecha"),
		@NamedQuery(name = "Tipocliente.findByConstanteValidacion", query = "SELECT t FROM Tipocliente t WHERE t.constanteValidacion = :constanteValidacion"),
		@NamedQuery(name = "Tipocliente.findByEscontribuyente", query = "SELECT t FROM Tipocliente t WHERE t.escontribuyente = :escontribuyente"),
		@NamedQuery(name = "Tipocliente.findByEstaactivo", query = "SELECT t FROM Tipocliente t WHERE t.estaactivo = :estaactivo") })
public class Tipocliente implements Serializable {
	/**
	 * Field serialVersionUID. (value is 1)
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Field id.
	 */
	@Id
	@Basic(optional = false)
	@Column(name = "id")
	private Long id;
	/**
	 * Field simbolo.
	 */
	@Basic(optional = false)
	@Column(name = "simbolo")
	private char simbolo;
	/**
	 * Field nombre.
	 */
	@Basic(optional = false)
	@Column(name = "nombre")
	private String nombre;
	/**
	 * Field mascara.
	 */
	@Basic(optional = false)
	@Column(name = "mascara")
	private String mascara;
	/**
	 * Field fecha.
	 */
	@Basic(optional = false)
	@Column(name = "fecha")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	/**
	 * Field constanteValidacion.
	 */
	@Basic(optional = false)
	@Column(name = "constante_validacion")
	private long constanteValidacion;
	/**
	 * Field escontribuyente.
	 */
	@Basic(optional = false)
	@Column(name = "escontribuyente")
	private String escontribuyente;
	/**
	 * Field estaactivo.
	 */
	@Basic(optional = false)
	@Column(name = "estaactivo")
	private String estaactivo;
	/**
	 * Field tipoclientetipodocumentoList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipocliente")
	private List<Tipoclientetipodocumento> tipoclientetipodocumentoList;
	// @OneToMany(cascade = CascadeType.REFRESH, mappedBy = "idTipocliente")
	// private List<Cliente> clienteList;
	/**
	 * Field tipoidentificacionclienteList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipocliente")
	private List<Tipoidentificacioncliente> tipoidentificacionclienteList;

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
	 * @param fecha
	 *            Date
	 * @param constanteValidacion
	 *            long
	 * @param escontribuyente
	 *            String
	 * @param estaactivo
	 *            String
	 */
	public Tipocliente(Long id, char simbolo, String nombre, String mascara, Date fecha, long constanteValidacion,
			String escontribuyente, String estaactivo) {
		this.id = id;
		this.simbolo = simbolo;
		this.nombre = nombre;
		this.mascara = mascara;
		this.fecha = fecha;
		this.constanteValidacion = constanteValidacion;
		this.escontribuyente = escontribuyente;
		this.estaactivo = estaactivo;
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
	 * @return String
	 */
	public String getEscontribuyente() {
		return escontribuyente;
	}

	/**
	 * Method setEscontribuyente.
	 * 
	 * @param escontribuyente
	 *            String
	 */
	public void setEscontribuyente(String escontribuyente) {
		this.escontribuyente = escontribuyente;
	}

	/**
	 * Method getEstaactivo.
	 * 
	 * @return String
	 */
	public String getEstaactivo() {
		return estaactivo;
	}

	/**
	 * Method setEstaactivo.
	 * 
	 * @param estaactivo
	 *            String
	 */
	public void setEstaactivo(String estaactivo) {
		this.estaactivo = estaactivo;
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
	public void setTipoclientetipodocumentoList(List<Tipoclientetipodocumento> tipoclientetipodocumentoList) {
		this.tipoclientetipodocumentoList = tipoclientetipodocumentoList;
	}

	// @XmlTransient
	// public List<Cliente> getClienteList() {
	// return clienteList;
	// }
	//
	// public void setClienteList(List<Cliente> clienteList) {
	// this.clienteList = clienteList;
	// }

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
	public void setTipoidentificacionclienteList(List<Tipoidentificacioncliente> tipoidentificacionclienteList) {
		this.tipoidentificacionclienteList = tipoidentificacionclienteList;
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
		String separator = "@@";
		String enclosed = "|";
		String endStr = "\r\n";
		String nullStr = "\\N";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		StringBuilder sb = new StringBuilder();

		sb.append(id);
		sb.append(separator);

		sb.append(enclosed);
		sb.append(simbolo);
		sb.append(enclosed);
		sb.append(separator);

		sb.append(enclosed);
		sb.append(nombre);
		sb.append(enclosed);
		sb.append(separator);

		if (mascara == null) {
			sb.append(nullStr);
		} else {
			sb.append(enclosed);
			sb.append(mascara);
			sb.append(enclosed);
		}
		sb.append(separator);

		sb.append(fecha == null ? nullStr : simpleDateFormat.format(fecha));
		sb.append(separator);

		sb.append(constanteValidacion);
		sb.append(separator);

		sb.append(escontribuyente);
		sb.append(separator);

		sb.append(estaactivo);

		sb.append(endStr);

		return sb.toString();
	}

}
