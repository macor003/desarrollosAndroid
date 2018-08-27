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

/**
 * 
 * @author eve0014321
 * @version $Revision: 1.0 $
 */
@Entity
@Table(name = "FORMADEPAGOPUNTOAGIL")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Formadepagopuntoagil.findAll", query = "SELECT f FROM Formadepagopuntoagil f"),
		@NamedQuery(name = "Formadepagopuntoagil.findById", query = "SELECT f FROM Formadepagopuntoagil f WHERE f.id = :id"),
		@NamedQuery(name = "Formadepagopuntoagil.findBySecuencia", query = "SELECT f FROM Formadepagopuntoagil f WHERE f.secuencia = :secuencia"),
		@NamedQuery(name = "Formadepagopuntoagil.findByVtid", query = "SELECT f FROM Formadepagopuntoagil f WHERE f.vtid = :vtid"),
		@NamedQuery(name = "Formadepagopuntoagil.findByTitular", query = "SELECT f FROM Formadepagopuntoagil f WHERE f.titular = :titular"),
		@NamedQuery(name = "Formadepagopuntoagil.findByTarjeta", query = "SELECT f FROM Formadepagopuntoagil f WHERE f.tarjeta = :tarjeta"),
		@NamedQuery(name = "Formadepagopuntoagil.findByCodigoRespuesta", query = "SELECT f FROM Formadepagopuntoagil f WHERE f.codigoRespuesta = :codigoRespuesta"),
		@NamedQuery(name = "Formadepagopuntoagil.findByMensajeRespuesta", query = "SELECT f FROM Formadepagopuntoagil f WHERE f.mensajeRespuesta = :mensajeRespuesta"),
		@NamedQuery(name = "Formadepagopuntoagil.findByArchivo", query = "SELECT f FROM Formadepagopuntoagil f WHERE f.archivo = :archivo"),
		@NamedQuery(name = "Formadepagopuntoagil.findByFecha", query = "SELECT f FROM Formadepagopuntoagil f WHERE f.fecha = :fecha"),
		@NamedQuery(name = "Formadepagopuntoagil.findByEstreplica", query = "SELECT f FROM Formadepagopuntoagil f WHERE f.estreplica = :estreplica") })
public class Formadepagopuntoagil implements Serializable {
	/**
	 * Field serialVersionUID. (value is 1)
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Field id.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID", nullable = false)
	private Long id;
	/**
	 * Field secuencia.
	 */
	@Basic(optional = false)
	@Column(name = "SECUENCIA", nullable = false)
	private int secuencia;
	/**
	 * Field vtid.
	 */
	@Basic(optional = false)
	@Column(name = "VTID", nullable = false, length = 10)
	private String vtid;
	/**
	 * Field titular.
	 */
	@Basic(optional = false)
	@Column(name = "TITULAR", nullable = false, length = 30)
	private String titular;
	/**
	 * Field tarjeta.
	 */
	@Basic(optional = false)
	@Column(name = "TARJETA", nullable = false, length = 20)
	private String tarjeta;
	/**
	 * Field codigoRespuesta.
	 */
	@Basic(optional = false)
	@Column(name = "CODIGO_RESPUESTA", nullable = false, length = 4)
	private String codigoRespuesta;
	/**
	 * Field mensajeRespuesta.
	 */
	@Basic(optional = false)
	@Column(name = "MENSAJE_RESPUESTA", nullable = false, length = 255)
	private String mensajeRespuesta;
	/**
	 * Field archivo.
	 */
	@Basic(optional = false)
	@Column(name = "ARCHIVO", nullable = false, length = 255)
	private String archivo;
	/**
	 * Field fecha.
	 */
	@Basic(optional = false)
	@Column(name = "FECHA", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	/**
	 * Field estreplica.
	 */
	@Basic(optional = false)
	@Column(name = "ESTREPLICA")
	private char estreplica;
	/**
	 * Field idTransaccionformadepago.
	 */
	@JoinColumn(name = "ID_TRANSACCIONFORMADEPAGO", referencedColumnName = "ID", nullable = false)
	@ManyToOne(optional = false)
	private Transaccionformadepago idTransaccionformadepago;

	/**
	 * Constructor for Formadepagopuntoagil.
	 */
	public Formadepagopuntoagil() {
	}

	/**
	 * Constructor for Formadepagopuntoagil.
	 * 
	 * @param id
	 *            Long
	 */
	public Formadepagopuntoagil(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Formadepagopuntoagil.
	 * 
	 * @param id
	 *            Long
	 * @param secuencia
	 *            int
	 * @param vtid
	 *            String
	 * @param titular
	 *            String
	 * @param tarjeta
	 *            String
	 * @param codigoRespuesta
	 *            String
	 * @param mensajeRespuesta
	 *            String
	 * @param archivo
	 *            String
	 * @param fecha
	 *            Date
	 * @param estreplica
	 *            char
	 */
	public Formadepagopuntoagil(Long id, int secuencia, String vtid,
			String titular, String tarjeta, String codigoRespuesta,
			String mensajeRespuesta, String archivo, Date fecha, char estreplica) {
		this.id = id;
		this.secuencia = secuencia;
		this.vtid = vtid;
		this.titular = titular;
		this.tarjeta = tarjeta;
		this.codigoRespuesta = codigoRespuesta;
		this.mensajeRespuesta = mensajeRespuesta;
		this.archivo = archivo;
		this.fecha = fecha;
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
	 * Method getSecuencia.
	 * 
	 * @return int
	 */
	public int getSecuencia() {
		return secuencia;
	}

	/**
	 * Method setSecuencia.
	 * 
	 * @param secuencia
	 *            int
	 */
	public void setSecuencia(int secuencia) {
		this.secuencia = secuencia;
	}

	/**
	 * Method getVtid.
	 * 
	 * @return String
	 */
	public String getVtid() {
		return vtid;
	}

	/**
	 * Method setVtid.
	 * 
	 * @param vtid
	 *            String
	 */
	public void setVtid(String vtid) {
		this.vtid = vtid;
	}

	/**
	 * Method getTitular.
	 * 
	 * @return String
	 */
	public String getTitular() {
		return titular;
	}

	/**
	 * Method setTitular.
	 * 
	 * @param titular
	 *            String
	 */
	public void setTitular(String titular) {
		this.titular = titular;
	}

	/**
	 * Method getTarjeta.
	 * 
	 * @return String
	 */
	public String getTarjeta() {
		return tarjeta;
	}

	/**
	 * Method setTarjeta.
	 * 
	 * @param tarjeta
	 *            String
	 */
	public void setTarjeta(String tarjeta) {
		this.tarjeta = tarjeta;
	}

	/**
	 * Method getCodigoRespuesta.
	 * 
	 * @return String
	 */
	public String getCodigoRespuesta() {
		return codigoRespuesta;
	}

	/**
	 * Method setCodigoRespuesta.
	 * 
	 * @param codigoRespuesta
	 *            String
	 */
	public void setCodigoRespuesta(String codigoRespuesta) {
		this.codigoRespuesta = codigoRespuesta;
	}

	/**
	 * Method getMensajeRespuesta.
	 * 
	 * @return String
	 */
	public String getMensajeRespuesta() {
		return mensajeRespuesta;
	}

	/**
	 * Method setMensajeRespuesta.
	 * 
	 * @param mensajeRespuesta
	 *            String
	 */
	public void setMensajeRespuesta(String mensajeRespuesta) {
		this.mensajeRespuesta = mensajeRespuesta;
	}

	/**
	 * Method getArchivo.
	 * 
	 * @return String
	 */
	public String getArchivo() {
		return archivo;
	}

	/**
	 * Method setArchivo.
	 * 
	 * @param archivo
	 *            String
	 */
	public void setArchivo(String archivo) {
		this.archivo = archivo;
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
	 * Method getIdTransaccionformadepago.
	 * 
	 * @return Transaccionformadepago
	 */
	public Transaccionformadepago getIdTransaccionformadepago() {
		return idTransaccionformadepago;
	}

	/**
	 * Method setIdTransaccionformadepago.
	 * 
	 * @param idTransaccionformadepago
	 *            Transaccionformadepago
	 */
	public void setIdTransaccionformadepago(
			Transaccionformadepago idTransaccionformadepago) {
		this.idTransaccionformadepago = idTransaccionformadepago;
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
		if (!(object instanceof Formadepagopuntoagil)) {
			return false;
		}
		Formadepagopuntoagil other = (Formadepagopuntoagil) object;
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
		return "crjpa400.Formadepagopuntoagil[ id=" + id + " ]";
	}

}
