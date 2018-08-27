/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crjpa;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
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

/**
 * 
 * @author eve0014321
 * @version $Revision: 1.0 $
 */
@Entity
@Table(name = "comprobantefiscalpreimpreso")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Comprobantefiscalpreimpreso.findAll", query = "SELECT c FROM Comprobantefiscalpreimpreso c"),
		@NamedQuery(name = "Comprobantefiscalpreimpreso.findById", query = "SELECT c FROM Comprobantefiscalpreimpreso c WHERE c.id = :id"),
		@NamedQuery(name = "Comprobantefiscalpreimpreso.findBySerie", query = "SELECT c FROM Comprobantefiscalpreimpreso c WHERE c.serie = :serie"),
		@NamedQuery(name = "Comprobantefiscalpreimpreso.findByTipoDocumento", query = "SELECT c FROM Comprobantefiscalpreimpreso c WHERE c.tipoDocumento = :tipoDocumento"),
		@NamedQuery(name = "Comprobantefiscalpreimpreso.findByInicioSerie", query = "SELECT c FROM Comprobantefiscalpreimpreso c WHERE c.inicioSerie = :inicioSerie"),
		@NamedQuery(name = "Comprobantefiscalpreimpreso.findByFinalSerie", query = "SELECT c FROM Comprobantefiscalpreimpreso c WHERE c.finalSerie = :finalSerie"),
		@NamedQuery(name = "Comprobantefiscalpreimpreso.findByNumCompactual", query = "SELECT c FROM Comprobantefiscalpreimpreso c WHERE c.numCompactual = :numCompactual"),
		@NamedQuery(name = "Comprobantefiscalpreimpreso.findByEstado", query = "SELECT c FROM Comprobantefiscalpreimpreso c WHERE c.estado = :estado"),
		@NamedQuery(name = "Comprobantefiscalpreimpreso.findByObservacion", query = "SELECT c FROM Comprobantefiscalpreimpreso c WHERE c.observacion = :observacion"),
		@NamedQuery(name = "Comprobantefiscalpreimpreso.findByEstasincronizado", query = "SELECT c FROM Comprobantefiscalpreimpreso c WHERE c.estasincronizado = :estasincronizado"),
		@NamedQuery(name = "Comprobantefiscalpreimpreso.findByResolucion", query = "SELECT c FROM Comprobantefiscalpreimpreso c WHERE c.resolucion = :resolucion"),
		@NamedQuery(name = "Comprobantefiscalpreimpreso.findByFechaAutorizacion", query = "SELECT c FROM Comprobantefiscalpreimpreso c WHERE c.fechaAutorizacion = :fechaAutorizacion"),
		@NamedQuery(name = "Comprobantefiscalpreimpreso.findByFechaIngreso", query = "SELECT c FROM Comprobantefiscalpreimpreso c WHERE c.fechaIngreso = :fechaIngreso"),
		@NamedQuery(name = "Comprobantefiscalpreimpreso.findByFechaVencimiento", query = "SELECT c FROM Comprobantefiscalpreimpreso c WHERE c.fechaVencimiento = :fechaVencimiento"),
		@NamedQuery(name = "Comprobantefiscalpreimpreso.findByPorcentajeAlertaConsumo", query = "SELECT c FROM Comprobantefiscalpreimpreso c WHERE c.porcentajeAlertaConsumo = :porcentajeAlertaConsumo")})
public class Comprobantefiscalpreimpreso implements Serializable {
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
	 * Field serie.
	 */
	@Basic(optional = false)
	@Column(name = "serie")
	private String serie;
	/**
	 * Field tipoDocumento.
	 */
	@Basic(optional = false)
	@Column(name = "tipo_documento")
	private char tipoDocumento;
	/**
	 * Field inicioSerie.
	 */
	@Basic(optional = false)
	@Column(name = "inicio_serie")
	private long inicioSerie;
	/**
	 * Field finalSerie.
	 */
	@Basic(optional = false)
	@Column(name = "final_serie")
	private long finalSerie;
	/**
	 * Field numCompactual.
	 */
	@Column(name = "num_compactual")
	private BigInteger numCompactual;
	/**
	 * Field estado.
	 */
	@Column(name = "estado")
	private Character estado;
	/**
	 * Field observacion.
	 */
	@Column(name = "observacion")
	private String observacion;
	/**
	 * Field comprobantefiscalnoutilizadoList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idComprobantefiscalpreimpreso")
	private List<Comprobantefiscalnoutilizado> comprobantefiscalnoutilizadoList;
	/**
	 * Field transacciondocumentoList.
	 */
	@OneToMany(mappedBy = "idComprobantefiscalpreimpreso")
	private List<Transacciondocumento> transacciondocumentoList;
	/**
	 * Field idTipodocumento.
	 */
	@JoinColumn(name = "id_tipodocumento", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Tipodocumento idTipodocumento;
	
	/**
	 * Field estasincronizado.
	 */
	@Basic(optional = false)
	@Column(name = "estasincronizado", nullable = false, length = 2)
	private String estasincronizado;

	/**
	 * Field resolucion.
	 */
	@Basic(optional = false)
	@Column(name = "resolucion", nullable = false, length = 25)
	private String resolucion;
	/**
	 * Field fechaAutorizacion.
	 */
	@Basic(optional = false)
	@Column(name = "fecha_autorizacion", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date fechaAutorizacion;
	/**
	 * Field fechaIngreso.
	 */
	@Basic(optional = false)
	@Column(name = "fecha_ingreso", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date fechaIngreso;
	/**
	 * Field fechaVencimiento.
	 */
	@Basic(optional = false)
	@Column(name = "fecha_vencimiento", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date fechaVencimiento;
	/**
	 * Field porcentajeAlertaConsumo.
	 */
	@Basic(optional = false)
	@Column(name = "porcentaje_alerta_consumo", nullable = false)
	private int porcentajeAlertaConsumo;
	
	
	
	/**
	 * Constructor for Comprobantefiscalpreimpreso.
	 */
	public Comprobantefiscalpreimpreso() {
	}

	/**
	 * Constructor for Comprobantefiscalpreimpreso.
	 * 
	 * @param id
	 *            Long
	 */
	public Comprobantefiscalpreimpreso(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Comprobantefiscalpreimpreso.
	 * 
	 * @param id
	 *            Long
	 * @param serie
	 *            String
	 * @param tipoDocumento
	 *            char
	 * @param inicioSerie
	 *            long
	 * @param finalSerie
	 *            long
	 */
	public Comprobantefiscalpreimpreso(Long id, String serie, char tipoDocumento, long inicioSerie, long finalSerie) {
		this.id = id;
		this.serie = serie;
		this.tipoDocumento = tipoDocumento;
		this.inicioSerie = inicioSerie;
		this.finalSerie = finalSerie;
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
	 * Method getSerie.
	 * 
	 * @return String
	 */
	public String getSerie() {
		return serie;
	}

	/**
	 * Method setSerie.
	 * 
	 * @param serie
	 *            String
	 */
	public void setSerie(String serie) {
		this.serie = serie;
	}

	/**
	 * Method getTipoDocumento.
	 * 
	 * @return char
	 */
	public char getTipoDocumento() {
		return tipoDocumento;
	}

	/**
	 * Method setTipoDocumento.
	 * 
	 * @param tipoDocumento
	 *            char
	 */
	public void setTipoDocumento(char tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	/**
	 * Method getInicioSerie.
	 * 
	 * @return long
	 */
	public long getInicioSerie() {
		return inicioSerie;
	}

	/**
	 * Method setInicioSerie.
	 * 
	 * @param inicioSerie
	 *            long
	 */
	public void setInicioSerie(long inicioSerie) {
		this.inicioSerie = inicioSerie;
	}

	/**
	 * Method getFinalSerie.
	 * 
	 * @return long
	 */
	public long getFinalSerie() {
		return finalSerie;
	}

	/**
	 * Method setFinalSerie.
	 * 
	 * @param finalSerie
	 *            long
	 */
	public void setFinalSerie(long finalSerie) {
		this.finalSerie = finalSerie;
	}

	/**
	 * Method getNumCompactual.
	 * 
	 * @return BigInteger
	 */
	public BigInteger getNumCompactual() {
		return numCompactual;
	}

	/**
	 * Method setNumCompactual.
	 * 
	 * @param numCompactual
	 *            BigInteger
	 */
	public void setNumCompactual(BigInteger numCompactual) {
		this.numCompactual = numCompactual;
	}

	/**
	 * Method getEstado.
	 * 
	 * @return Character
	 */
	public Character getEstado() {
		return estado;
	}

	/**
	 * Method setEstado.
	 * 
	 * @param estado
	 *            Character
	 */
	public void setEstado(Character estado) {
		this.estado = estado;
	}

	/**
	 * Method getObservacion.
	 * 
	 * @return String
	 */
	public String getObservacion() {
		return observacion;
	}

	/**
	 * Method setObservacion.
	 * 
	 * @param observacion
	 *            String
	 */
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	/**
	 * Method getComprobantefiscalnoutilizadoList.
	 * 
	 * @return List<Comprobantefiscalnoutilizado>
	 */
	@XmlTransient
	public List<Comprobantefiscalnoutilizado> getComprobantefiscalnoutilizadoList() {
		return comprobantefiscalnoutilizadoList;
	}

	/**
	 * Method setComprobantefiscalnoutilizadoList.
	 * 
	 * @param comprobantefiscalnoutilizadoList
	 *            List<Comprobantefiscalnoutilizado>
	 */
	public void setComprobantefiscalnoutilizadoList(List<Comprobantefiscalnoutilizado> comprobantefiscalnoutilizadoList) {
		this.comprobantefiscalnoutilizadoList = comprobantefiscalnoutilizadoList;
	}

	/**
	 * Method getTransacciondocumentoList.
	 * 
	 * @return List<Transacciondocumento>
	 */
	@XmlTransient
	public List<Transacciondocumento> getTransacciondocumentoList() {
		return transacciondocumentoList;
	}

	/**
	 * Method setTransacciondocumentoList.
	 * 
	 * @param transacciondocumentoList
	 *            List<Transacciondocumento>
	 */
	public void setTransacciondocumentoList(List<Transacciondocumento> transacciondocumentoList) {
		this.transacciondocumentoList = transacciondocumentoList;
	}

	/**
	 * Method getIdTipodocumento.
	 * 
	 * @return Tipodocumento
	 */
	public Tipodocumento getIdTipodocumento() {
		return idTipodocumento;
	}

	/**
	 * Method setIdTipodocumento.
	 * 
	 * @param idTipodocumento
	 *            Tipodocumento
	 */
	public void setIdTipodocumento(Tipodocumento idTipodocumento) {
		this.idTipodocumento = idTipodocumento;
	}
	/**
	 * Method getEstasincronizado.
	 * 
	 * @return String
	 */
	public String getEstasincronizado() {
		return estasincronizado;
	}

	/**
	 * Method setEstasincronizado.
	 * 
	 * @param estasincronizado
	 *            String
	 */
	public void setEstasincronizado(String estasincronizado) {
		this.estasincronizado = estasincronizado;
	}
	
	

	/**
	 * @return the resolucion
	 */
	public String getResolucion() {
		return resolucion;
	}

	/**
	 * @param resolucion the resolucion to set
	 */
	public void setResolucion(String resolucion) {
		this.resolucion = resolucion;
	}

	/**
	 * @return the fechaAutorizacion
	 */
	public Date getFechaAutorizacion() {
		return fechaAutorizacion;
	}

	/**
	 * @param fechaAutorizacion the fechaAutorizacion to set
	 */
	public void setFechaAutorizacion(Date fechaAutorizacion) {
		this.fechaAutorizacion = fechaAutorizacion;
	}

	/**
	 * @return the fechaIngreso
	 */
	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	/**
	 * @param fechaIngreso the fechaIngreso to set
	 */
	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	/**
	 * @return the fechaVencimiento
	 */
	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	/**
	 * @param fechaVencimiento the fechaVencimiento to set
	 */
	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	/**
	 * @return the porcentajeAlertaConsumo
	 */
	public int getPorcentajeAlertaConsumo() {
		return porcentajeAlertaConsumo;
	}

	/**
	 * @param porcentajeAlertaConsumo the porcentajeAlertaConsumo to set
	 */
	public void setPorcentajeAlertaConsumo(int porcentajeAlertaConsumo) {
		this.porcentajeAlertaConsumo = porcentajeAlertaConsumo;
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
		if (!(object instanceof Comprobantefiscalpreimpreso)) {
			return false;
		}
		Comprobantefiscalpreimpreso other = (Comprobantefiscalpreimpreso) object;
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
		return "crjpa.Comprobantefiscalpreimpreso[ id=" + id + " ]";
	}

}
