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
@Table(name = "TIPODOCUMENTO")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Tipodocumento.findAll", query = "SELECT t FROM Tipodocumento t"),
		@NamedQuery(name = "Tipodocumento.findById", query = "SELECT t FROM Tipodocumento t WHERE t.id = :id"),
		@NamedQuery(name = "Tipodocumento.findByNombre", query = "SELECT t FROM Tipodocumento t WHERE t.nombre = :nombre"),
		@NamedQuery(name = "Tipodocumento.findByEsfiscal", query = "SELECT t FROM Tipodocumento t WHERE t.esfiscal = :esfiscal"),
		@NamedQuery(name = "Tipodocumento.findByEstaactivo", query = "SELECT t FROM Tipodocumento t WHERE t.estaactivo = :estaactivo"),
		@NamedQuery(name = "Tipodocumento.findByEstacionImpresion", query = "SELECT t FROM Tipodocumento t WHERE t.estacionImpresion = :estacionImpresion"),
		@NamedQuery(name = "Tipodocumento.findByDuplicadoAud", query = "SELECT t FROM Tipodocumento t WHERE t.duplicadoAud = :duplicadoAud"),
		@NamedQuery(name = "Tipodocumento.findByActividadEconomica", query = "SELECT t FROM Tipodocumento t WHERE t.actividadEconomica = :actividadEconomica"),
		@NamedQuery(name = "Tipodocumento.findByFecha", query = "SELECT t FROM Tipodocumento t WHERE t.fecha = :fecha"),
		@NamedQuery(name = "Tipodocumento.findByValidaSerial", query = "SELECT t FROM Tipodocumento t WHERE t.validaSerial = :validaSerial"),
		@NamedQuery(name = "Tipodocumento.findByRequiereCliente", query = "SELECT t FROM Tipodocumento t WHERE t.requiereCliente = :requiereCliente"),
		@NamedQuery(name = "Tipodocumento.findByRequiereNuevoDoc", query = "SELECT t FROM Tipodocumento t WHERE t.requiereNuevoDoc = :requiereNuevoDoc"),
		@NamedQuery(name = "Tipodocumento.findByPermiteReimpresion", query = "SELECT t FROM Tipodocumento t WHERE t.permiteReimpresion = :permiteReimpresion"),
		@NamedQuery(name = "Tipodocumento.findByMuestraImpuesto", query = "SELECT t FROM Tipodocumento t WHERE t.muestraImpuesto = :muestraImpuesto"),
		@NamedQuery(name = "Tipodocumento.findByEssoporteventa", query = "SELECT t FROM Tipodocumento t WHERE t.essoporteventa = :essoporteventa"),
		@NamedQuery(name = "Tipodocumento.findByEsdevolucionparcial", query = "SELECT t FROM Tipodocumento t WHERE t.esdevolucionparcial = :esdevolucionparcial"),
		@NamedQuery(name = "Tipodocumento.findByUltimasincronizacion", query = "SELECT t FROM Tipodocumento t WHERE t.ultimasincronizacion = :ultimasincronizacion"),
		@NamedQuery(name = "Tipodocumento.findByEstreplica", query = "SELECT t FROM Tipodocumento t WHERE t.estreplica = :estreplica") })
public class Tipodocumento extends CrjpaInterface implements Serializable {
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
	 * Field nombre.
	 */
	@Basic(optional = false)
	@Column(name = "NOMBRE", nullable = false, length = 40)
	private String nombre;
	/**
	 * Field esfiscal.
	 */
	@Basic(optional = false)
	@Column(name = "ESFISCAL", nullable = false)
	private char esfiscal;
	/**
	 * Field estaactivo.
	 */
	@Basic(optional = false)
	@Column(name = "ESTAACTIVO", nullable = false)
	private char estaactivo;
	/**
	 * Field estacionImpresion.
	 */
	@Basic(optional = false)
	@Column(name = "ESTACION_IMPRESION", nullable = false)
	private char estacionImpresion;
	/**
	 * Field duplicadoAud.
	 */
	@Basic(optional = false)
	@Column(name = "DUPLICADO_AUD", nullable = false)
	private char duplicadoAud;
	/**
	 * Field actividadEconomica.
	 */
	@Basic(optional = false)
	@Column(name = "ACTIVIDAD_ECONOMICA", nullable = false)
	private char actividadEconomica;
	/**
	 * Field fecha.
	 */
	@Basic(optional = false)
	@Column(name = "FECHA", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	/**
	 * Field validaSerial.
	 */
	@Basic(optional = false)
	@Column(name = "VALIDA_SERIAL", nullable = false)
	private char validaSerial;
	/**
	 * Field requiereCliente.
	 */
	@Basic(optional = false)
	@Column(name = "REQUIERE_CLIENTE", nullable = false)
	private char requiereCliente;
	/**
	 * Field requiereNuevoDoc.
	 */
	@Basic(optional = false)
	@Column(name = "REQUIERE_NUEVO_DOC", nullable = false)
	private char requiereNuevoDoc;
	/**
	 * Field permiteReimpresion.
	 */
	@Basic(optional = false)
	@Column(name = "PERMITE_REIMPRESION", nullable = false)
	private char permiteReimpresion;
	/**
	 * Field muestraImpuesto.
	 */
	@Basic(optional = false)
	@Column(name = "MUESTRA_IMPUESTO", nullable = false)
	private char muestraImpuesto;
	/**
	 * Field essoporteventa.
	 */
	@Basic(optional = false)
	@Column(name = "ESSOPORTEVENTA", nullable = false)
	private char essoporteventa;
	/**
	 * Field esdevolucionparcial.
	 */
	@Basic(optional = false)
	@Column(name = "ESDEVOLUCIONPARCIAL", nullable = false)
	private char esdevolucionparcial;
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
	 * Field categorialineaincluyeList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipodocumento")
	private List<Categorialineaincluye> categorialineaincluyeList;
	/**
	 * Field tipodocumentoList.
	 */
	@OneToMany(mappedBy = "idContrapartidadevolucion")
	private List<Tipodocumento> tipodocumentoList;
	/**
	 * Field idContrapartidadevolucion.
	 */
	@JoinColumn(name = "ID_CONTRAPARTIDADEVOLUCION", referencedColumnName = "ID")
	@ManyToOne
	private Tipodocumento idContrapartidadevolucion;
	/**
	 * Field tipodocumentoList1.
	 */
	@OneToMany(mappedBy = "idContrapartida")
	private List<Tipodocumento> tipodocumentoList1;
	/**
	 * Field idContrapartida.
	 */
	@JoinColumn(name = "ID_CONTRAPARTIDA", referencedColumnName = "ID")
	@ManyToOne
	private Tipodocumento idContrapartida;
	/**
	 * Field tipoclientetipodocumentoList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipodocumento")
	private List<Tipoclientetipodocumento> tipoclientetipodocumentoList;
	/**
	 * Field transacciondocumentoList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipodocumento")
	private List<Transacciondocumento> transacciondocumentoList;
	/**
	 * Field comprobantefiscalpreimpresoList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipodocumento")
	private List<Comprobantefiscalpreimpreso> comprobantefiscalpreimpresoList;

	/**
	 * Constructor for Tipodocumento.
	 */
	public Tipodocumento() {
	}

	/**
	 * Constructor for Tipodocumento.
	 * 
	 * @param id
	 *            Long
	 */
	public Tipodocumento(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Tipodocumento.
	 * 
	 * @param id
	 *            Long
	 * @param nombre
	 *            String
	 * @param esfiscal
	 *            char
	 * @param estaactivo
	 *            char
	 * @param estacionImpresion
	 *            char
	 * @param duplicadoAud
	 *            char
	 * @param actividadEconomica
	 *            char
	 * @param fecha
	 *            Date
	 * @param validaSerial
	 *            char
	 * @param requiereCliente
	 *            char
	 * @param requiereNuevoDoc
	 *            char
	 * @param permiteReimpresion
	 *            char
	 * @param muestraImpuesto
	 *            char
	 * @param essoporteventa
	 *            char
	 * @param esdevolucionparcial
	 *            char
	 * @param ultimasincronizacion
	 *            Calendar
	 * @param estreplica
	 *            char
	 */
	public Tipodocumento(Long id, String nombre, char esfiscal,
			char estaactivo, char estacionImpresion, char duplicadoAud,
			char actividadEconomica, Date fecha, char validaSerial,
			char requiereCliente, char requiereNuevoDoc,
			char permiteReimpresion, char muestraImpuesto, char essoporteventa,
			char esdevolucionparcial, Calendar ultimasincronizacion,
			char estreplica) {
		this.id = id;
		this.nombre = nombre;
		this.esfiscal = esfiscal;
		this.estaactivo = estaactivo;
		this.estacionImpresion = estacionImpresion;
		this.duplicadoAud = duplicadoAud;
		this.actividadEconomica = actividadEconomica;
		this.fecha = fecha;
		this.validaSerial = validaSerial;
		this.requiereCliente = requiereCliente;
		this.requiereNuevoDoc = requiereNuevoDoc;
		this.permiteReimpresion = permiteReimpresion;
		this.muestraImpuesto = muestraImpuesto;
		this.essoporteventa = essoporteventa;
		this.esdevolucionparcial = esdevolucionparcial;
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
	 * Method getEsfiscal.
	 * 
	 * @return char
	 */
	public char getEsfiscal() {
		return esfiscal;
	}

	/**
	 * Method setEsfiscal.
	 * 
	 * @param esfiscal
	 *            char
	 */
	public void setEsfiscal(char esfiscal) {
		this.esfiscal = esfiscal;
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
	 * Method getEstacionImpresion.
	 * 
	 * @return char
	 */
	public char getEstacionImpresion() {
		return estacionImpresion;
	}

	/**
	 * Method setEstacionImpresion.
	 * 
	 * @param estacionImpresion
	 *            char
	 */
	public void setEstacionImpresion(char estacionImpresion) {
		this.estacionImpresion = estacionImpresion;
	}

	/**
	 * Method getDuplicadoAud.
	 * 
	 * @return char
	 */
	public char getDuplicadoAud() {
		return duplicadoAud;
	}

	/**
	 * Method setDuplicadoAud.
	 * 
	 * @param duplicadoAud
	 *            char
	 */
	public void setDuplicadoAud(char duplicadoAud) {
		this.duplicadoAud = duplicadoAud;
	}

	/**
	 * Method getActividadEconomica.
	 * 
	 * @return char
	 */
	public char getActividadEconomica() {
		return actividadEconomica;
	}

	/**
	 * Method setActividadEconomica.
	 * 
	 * @param actividadEconomica
	 *            char
	 */
	public void setActividadEconomica(char actividadEconomica) {
		this.actividadEconomica = actividadEconomica;
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
	 * Method getValidaSerial.
	 * 
	 * @return char
	 */
	public char getValidaSerial() {
		return validaSerial;
	}

	/**
	 * Method setValidaSerial.
	 * 
	 * @param validaSerial
	 *            char
	 */
	public void setValidaSerial(char validaSerial) {
		this.validaSerial = validaSerial;
	}

	/**
	 * Method getRequiereCliente.
	 * 
	 * @return char
	 */
	public char getRequiereCliente() {
		return requiereCliente;
	}

	/**
	 * Method setRequiereCliente.
	 * 
	 * @param requiereCliente
	 *            char
	 */
	public void setRequiereCliente(char requiereCliente) {
		this.requiereCliente = requiereCliente;
	}

	/**
	 * Method getRequiereNuevoDoc.
	 * 
	 * @return char
	 */
	public char getRequiereNuevoDoc() {
		return requiereNuevoDoc;
	}

	/**
	 * Method setRequiereNuevoDoc.
	 * 
	 * @param requiereNuevoDoc
	 *            char
	 */
	public void setRequiereNuevoDoc(char requiereNuevoDoc) {
		this.requiereNuevoDoc = requiereNuevoDoc;
	}

	/**
	 * Method getPermiteReimpresion.
	 * 
	 * @return char
	 */
	public char getPermiteReimpresion() {
		return permiteReimpresion;
	}

	/**
	 * Method setPermiteReimpresion.
	 * 
	 * @param permiteReimpresion
	 *            char
	 */
	public void setPermiteReimpresion(char permiteReimpresion) {
		this.permiteReimpresion = permiteReimpresion;
	}

	/**
	 * Method getMuestraImpuesto.
	 * 
	 * @return char
	 */
	public char getMuestraImpuesto() {
		return muestraImpuesto;
	}

	/**
	 * Method setMuestraImpuesto.
	 * 
	 * @param muestraImpuesto
	 *            char
	 */
	public void setMuestraImpuesto(char muestraImpuesto) {
		this.muestraImpuesto = muestraImpuesto;
	}

	/**
	 * Method getEssoporteventa.
	 * 
	 * @return char
	 */
	public char getEssoporteventa() {
		return essoporteventa;
	}

	/**
	 * Method setEssoporteventa.
	 * 
	 * @param essoporteventa
	 *            char
	 */
	public void setEssoporteventa(char essoporteventa) {
		this.essoporteventa = essoporteventa;
	}

	/**
	 * Method getEsdevolucionparcial.
	 * 
	 * @return char
	 */
	public char getEsdevolucionparcial() {
		return esdevolucionparcial;
	}

	/**
	 * Method setEsdevolucionparcial.
	 * 
	 * @param esdevolucionparcial
	 *            char
	 */
	public void setEsdevolucionparcial(char esdevolucionparcial) {
		this.esdevolucionparcial = esdevolucionparcial;
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
	 * Method getCategorialineaincluyeList.
	 * 
	 * @return List<Categorialineaincluye>
	 */
	@XmlTransient
	public List<Categorialineaincluye> getCategorialineaincluyeList() {
		return categorialineaincluyeList;
	}

	/**
	 * Method setCategorialineaincluyeList.
	 * 
	 * @param categorialineaincluyeList
	 *            List<Categorialineaincluye>
	 */
	public void setCategorialineaincluyeList(
			List<Categorialineaincluye> categorialineaincluyeList) {
		this.categorialineaincluyeList = categorialineaincluyeList;
	}

	/**
	 * Method getTipodocumentoList.
	 * 
	 * @return List<Tipodocumento>
	 */
	@XmlTransient
	public List<Tipodocumento> getTipodocumentoList() {
		return tipodocumentoList;
	}

	/**
	 * Method setTipodocumentoList.
	 * 
	 * @param tipodocumentoList
	 *            List<Tipodocumento>
	 */
	public void setTipodocumentoList(List<Tipodocumento> tipodocumentoList) {
		this.tipodocumentoList = tipodocumentoList;
	}

	/**
	 * Method getIdContrapartidadevolucion.
	 * 
	 * @return Tipodocumento
	 */
	public Tipodocumento getIdContrapartidadevolucion() {
		return idContrapartidadevolucion;
	}

	/**
	 * Method setIdContrapartidadevolucion.
	 * 
	 * @param idContrapartidadevolucion
	 *            Tipodocumento
	 */
	public void setIdContrapartidadevolucion(
			Tipodocumento idContrapartidadevolucion) {
		this.idContrapartidadevolucion = idContrapartidadevolucion;
	}

	/**
	 * Method getTipodocumentoList1.
	 * 
	 * @return List<Tipodocumento>
	 */
	@XmlTransient
	public List<Tipodocumento> getTipodocumentoList1() {
		return tipodocumentoList1;
	}

	/**
	 * Method setTipodocumentoList1.
	 * 
	 * @param tipodocumentoList1
	 *            List<Tipodocumento>
	 */
	public void setTipodocumentoList1(List<Tipodocumento> tipodocumentoList1) {
		this.tipodocumentoList1 = tipodocumentoList1;
	}

	/**
	 * Method getIdContrapartida.
	 * 
	 * @return Tipodocumento
	 */
	public Tipodocumento getIdContrapartida() {
		return idContrapartida;
	}

	/**
	 * Method setIdContrapartida.
	 * 
	 * @param idContrapartida
	 *            Tipodocumento
	 */
	public void setIdContrapartida(Tipodocumento idContrapartida) {
		this.idContrapartida = idContrapartida;
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
	public void setTransacciondocumentoList(
			List<Transacciondocumento> transacciondocumentoList) {
		this.transacciondocumentoList = transacciondocumentoList;
	}

	/**
	 * Method getComprobantefiscalpreimpresoList.
	 * 
	 * @return List<Comprobantefiscalpreimpreso>
	 */
	@XmlTransient
	public List<Comprobantefiscalpreimpreso> getComprobantefiscalpreimpresoList() {
		return comprobantefiscalpreimpresoList;
	}

	/**
	 * Method setComprobantefiscalpreimpresoList.
	 * 
	 * @param comprobantefiscalpreimpresoList
	 *            List<Comprobantefiscalpreimpreso>
	 */
	public void setComprobantefiscalpreimpresoList(
			List<Comprobantefiscalpreimpreso> comprobantefiscalpreimpresoList) {
		this.comprobantefiscalpreimpresoList = comprobantefiscalpreimpresoList;
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
		if (!(object instanceof Tipodocumento)) {
			return false;
		}
		Tipodocumento other = (Tipodocumento) object;
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
		return "crjpa400.Tipodocumento[ id=" + id + " ]";
	}

}
