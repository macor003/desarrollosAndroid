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
@Table(name = "usuarioperfil")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Usuarioperfil.findAll", query = "SELECT u FROM Usuarioperfil u"),
		@NamedQuery(name = "Usuarioperfil.findById", query = "SELECT u FROM Usuarioperfil u WHERE u.id = :id"),
		@NamedQuery(name = "Usuarioperfil.findByFecha", query = "SELECT u FROM Usuarioperfil u WHERE u.fecha = :fecha") })
public class Usuarioperfil implements Serializable {
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
	 * Field fecha.
	 */
	@Basic(optional = false)
	@Column(name = "fecha")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	/**
	 * Field idUsuario.
	 */
	@JoinColumn(name = "id_usuario", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Usuario idUsuario;
	/**
	 * Field idPerfil.
	 */
	@JoinColumn(name = "id_perfil", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Perfil idPerfil;

	/**
	 * Constructor for Usuarioperfil.
	 */
	public Usuarioperfil() {
	}

	/**
	 * Constructor for Usuarioperfil.
	 * 
	 * @param id
	 *            Long
	 */
	public Usuarioperfil(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Usuarioperfil.
	 * 
	 * @param id
	 *            Long
	 * @param fecha
	 *            Date
	 */
	public Usuarioperfil(Long id, Date fecha) {
		this.id = id;
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
	 * Method getIdUsuario.
	 * 
	 * @return Usuario
	 */
	public Usuario getIdUsuario() {
		return idUsuario;
	}

	/**
	 * Method setIdUsuario.
	 * 
	 * @param idUsuario
	 *            Usuario
	 */
	public void setIdUsuario(Usuario idUsuario) {
		this.idUsuario = idUsuario;
	}

	/**
	 * Method getIdPerfil.
	 * 
	 * @return Perfil
	 */
	public Perfil getIdPerfil() {
		return idPerfil;
	}

	/**
	 * Method setIdPerfil.
	 * 
	 * @param idPerfil
	 *            Perfil
	 */
	public void setIdPerfil(Perfil idPerfil) {
		this.idPerfil = idPerfil;
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
		if (!(object instanceof Usuarioperfil)) {
			return false;
		}
		Usuarioperfil other = (Usuarioperfil) object;
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
		String nullStr = "\\N";
		String endStr = "\r\n";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		StringBuilder sb = new StringBuilder();

		sb.append(id);
		sb.append(separator);

		sb.append(idUsuario.getId());
		sb.append(separator);

		sb.append(idPerfil.getId());
		sb.append(separator);

		sb.append(fecha == null ? nullStr : simpleDateFormat.format(fecha));
		sb.append(endStr);

		return sb.toString();
	}

}
