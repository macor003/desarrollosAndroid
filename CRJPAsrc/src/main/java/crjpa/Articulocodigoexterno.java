/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crjpa;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

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

/**
 * 
 * @author eve0014321
 * @version $Revision: 1.0 $
 */
@Entity
@Table(name = "articulocodigoexterno")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Articulocodigoexterno.findAll", query = "SELECT a FROM Articulocodigoexterno a"),
		@NamedQuery(name = "Articulocodigoexterno.findById", query = "SELECT a FROM Articulocodigoexterno a WHERE a.id = :id"),
		@NamedQuery(name = "Articulocodigoexterno.findByCodigoExterno", query = "SELECT a FROM Articulocodigoexterno a WHERE a.codigoExterno = :codigoExterno"),
		@NamedQuery(name = "Articulocodigoexterno.findByFecha", query = "SELECT a FROM Articulocodigoexterno a WHERE a.fecha = :fecha") })
public class Articulocodigoexterno implements Serializable {
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
	 * Field codigoExterno.
	 */
	@Basic(optional = false)
	@Column(name = "codigo_externo")
	private String codigoExterno;
	/**
	 * Field fecha.
	 */
	@Basic(optional = false)
	@Column(name = "fecha")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	/**
	 * Field idArticulo.
	 */
	@JoinColumn(name = "id_articulo", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Articulo idArticulo;
	/**
	 * Field estaactivo.
	 */
	@Basic(optional = false)
	@Column(name = "estaactivo")
	private String estaactivo;

	/**
	 * Constructor for Articulocodigoexterno.
	 */
	public Articulocodigoexterno() {
	}

	/**
	 * Constructor for Articulocodigoexterno.
	 * 
	 * @param id
	 *            Long
	 */
	public Articulocodigoexterno(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Articulocodigoexterno.
	 * 
	 * @param id
	 *            Long
	 * @param codigoExterno
	 *            String
	 * @param fecha
	 *            Date
	 */
	public Articulocodigoexterno(Long id, String codigoExterno, Date fecha) {
		this.id = id;
		this.codigoExterno = codigoExterno;
		this.fecha = fecha;
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
	 * Method getCodigoExterno.
	 * 
	 * @return String
	 */
	public String getCodigoExterno() {
		return codigoExterno;
	}

	/**
	 * Method setCodigoExterno.
	 * 
	 * @param codigoExterno
	 *            String
	 */
	public void setCodigoExterno(String codigoExterno) {
		this.codigoExterno = codigoExterno;
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
	 * Method getIdArticulo.
	 * 
	 * @return Articulo
	 */
	public Articulo getIdArticulo() {
		return idArticulo;
	}

	/**
	 * Method setIdArticulo.
	 * 
	 * @param idArticulo
	 *            Articulo
	 */
	public void setIdArticulo(Articulo idArticulo) {
		this.idArticulo = idArticulo;
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
		if (!(object instanceof Articulocodigoexterno)) {
			return false;
		}
		Articulocodigoexterno other = (Articulocodigoexterno) object;
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
		String nullStr = "\\N";
		String endStr = "\r\n";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		StringBuilder sb = new StringBuilder();

		sb.append(id);
		sb.append(separator);

		if (idArticulo.getId() == null) {
			sb.append(nullStr);
		} else {
			sb.append(idArticulo.getId());
		}
		sb.append(separator);

		sb.append(enclosed);
		sb.append(codigoExterno.replaceAll("[^a-zA-Z0-9]+", "").trim());
		sb.append(enclosed);
		sb.append(separator);

		sb.append(fecha == null ? nullStr : simpleDateFormat.format(fecha));
		sb.append(separator);

		sb.append(estaactivo);
		sb.append(endStr);

		return sb.toString();
	}

}
