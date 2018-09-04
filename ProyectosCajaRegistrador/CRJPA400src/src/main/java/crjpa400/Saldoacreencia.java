/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crjpa400;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author eve0014321
 * @version $Revision: 1.0 $
 */
@Entity
@Table(name = "SALDOACREENCIA")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Saldoacreencia.findAll", query = "SELECT s FROM Saldoacreencia s"),
		@NamedQuery(name = "Saldoacreencia.findByIdAcreencia", query = "SELECT s FROM Saldoacreencia s WHERE s.saldoacreenciaPK.idAcreencia = :idAcreencia"),
		@NamedQuery(name = "Saldoacreencia.findByIdTipoacreencia", query = "SELECT s FROM Saldoacreencia s WHERE s.saldoacreenciaPK.idTipoacreencia = :idTipoacreencia"),
		@NamedQuery(name = "Saldoacreencia.findByMontoDisponible", query = "SELECT s FROM Saldoacreencia s WHERE s.montoDisponible = :montoDisponible"),
		@NamedQuery(name = "Saldoacreencia.findByMontoBloqueado", query = "SELECT s FROM Saldoacreencia s WHERE s.montoBloqueado = :montoBloqueado"),
		@NamedQuery(name = "Saldoacreencia.findByEstreplica", query = "SELECT s FROM Saldoacreencia s WHERE s.estreplica = :estreplica") })
public class Saldoacreencia implements Serializable {
	/**
	 * Field serialVersionUID. (value is 1)
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Field saldoacreenciaPK.
	 */
	@EmbeddedId
	protected SaldoacreenciaPK saldoacreenciaPK;
	/**
	 * Field montoDisponible.
	 */
	@Column(name = "MONTO_DISPONIBLE", precision = 13, scale = 2)
	private BigDecimal montoDisponible;
	/**
	 * Field montoBloqueado.
	 */
	@Column(name = "MONTO_BLOQUEADO", precision = 13, scale = 2)
	private BigDecimal montoBloqueado;
	/**
	 * Field estreplica.
	 */
	@Basic(optional = false)
	@Column(name = "ESTREPLICA")
	private char estreplica;
	/**
	 * Field tipoacreencia.
	 */
	@JoinColumn(name = "ID_TIPOACREENCIA", referencedColumnName = "ID", insertable = false, updatable = false)
	@ManyToOne(optional = false)
	private Tipoacreencia tipoacreencia;
	/**
	 * Field acreencia.
	 */
	@JoinColumn(name = "ID_ACREENCIA", referencedColumnName = "ID", insertable = false, updatable = false)
	@ManyToOne(optional = false)
	private Acreencia acreencia;

	/**
	 * Constructor for Saldoacreencia.
	 */
	public Saldoacreencia() {
	}

	/**
	 * Constructor for Saldoacreencia.
	 * 
	 * @param saldoacreenciaPK
	 *            SaldoacreenciaPK
	 */
	public Saldoacreencia(SaldoacreenciaPK saldoacreenciaPK) {
		this.saldoacreenciaPK = saldoacreenciaPK;
	}

	/**
	 * Constructor for Saldoacreencia.
	 * 
	 * @param saldoacreenciaPK
	 *            SaldoacreenciaPK
	 * @param estreplica
	 *            char
	 */
	public Saldoacreencia(SaldoacreenciaPK saldoacreenciaPK, char estreplica) {
		this.saldoacreenciaPK = saldoacreenciaPK;
		this.estreplica = estreplica;
	}

	/**
	 * Constructor for Saldoacreencia.
	 * 
	 * @param idAcreencia
	 *            long
	 * @param idTipoacreencia
	 *            long
	 */
	public Saldoacreencia(long idAcreencia, long idTipoacreencia) {
		this.saldoacreenciaPK = new SaldoacreenciaPK(idAcreencia,
				idTipoacreencia);
	}

	/**
	 * Method getSaldoacreenciaPK.
	 * 
	 * @return SaldoacreenciaPK
	 */
	public SaldoacreenciaPK getSaldoacreenciaPK() {
		return saldoacreenciaPK;
	}

	/**
	 * Method setSaldoacreenciaPK.
	 * 
	 * @param saldoacreenciaPK
	 *            SaldoacreenciaPK
	 */
	public void setSaldoacreenciaPK(SaldoacreenciaPK saldoacreenciaPK) {
		this.saldoacreenciaPK = saldoacreenciaPK;
	}

	/**
	 * Method getMontoDisponible.
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal getMontoDisponible() {
		return montoDisponible;
	}

	/**
	 * Method setMontoDisponible.
	 * 
	 * @param montoDisponible
	 *            BigDecimal
	 */
	public void setMontoDisponible(BigDecimal montoDisponible) {
		this.montoDisponible = montoDisponible;
	}

	/**
	 * Method getMontoBloqueado.
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal getMontoBloqueado() {
		return montoBloqueado;
	}

	/**
	 * Method setMontoBloqueado.
	 * 
	 * @param montoBloqueado
	 *            BigDecimal
	 */
	public void setMontoBloqueado(BigDecimal montoBloqueado) {
		this.montoBloqueado = montoBloqueado;
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
	 * Method getTipoacreencia.
	 * 
	 * @return Tipoacreencia
	 */
	public Tipoacreencia getTipoacreencia() {
		return tipoacreencia;
	}

	/**
	 * Method setTipoacreencia.
	 * 
	 * @param tipoacreencia
	 *            Tipoacreencia
	 */
	public void setTipoacreencia(Tipoacreencia tipoacreencia) {
		this.tipoacreencia = tipoacreencia;
	}

	/**
	 * Method getAcreencia.
	 * 
	 * @return Acreencia
	 */
	public Acreencia getAcreencia() {
		return acreencia;
	}

	/**
	 * Method setAcreencia.
	 * 
	 * @param acreencia
	 *            Acreencia
	 */
	public void setAcreencia(Acreencia acreencia) {
		this.acreencia = acreencia;
	}

	/**
	 * Method hashCode.
	 * 
	 * @return int
	 */
	@Override
	public int hashCode() {
		int hash = 0;
		hash += (saldoacreenciaPK != null ? saldoacreenciaPK.hashCode() : 0);
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
		if (!(object instanceof Saldoacreencia)) {
			return false;
		}
		Saldoacreencia other = (Saldoacreencia) object;
		if ((this.saldoacreenciaPK == null && other.saldoacreenciaPK != null)
				|| (this.saldoacreenciaPK != null && !this.saldoacreenciaPK
						.equals(other.saldoacreenciaPK))) {
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
		return "crjpa400.Saldoacreencia[ saldoacreenciaPK=" + saldoacreenciaPK
				+ " ]";
	}

}
