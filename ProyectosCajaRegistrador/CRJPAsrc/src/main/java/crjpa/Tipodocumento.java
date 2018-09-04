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
@Table(name = "tipodocumento")
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
		@NamedQuery(name = "Tipodocumento.findByEsdevolucionparcial", query = "SELECT t FROM Tipodocumento t WHERE t.esdevolucionparcial = :esdevolucionparcial") })
public class Tipodocumento implements Serializable {
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
	 * Field nombre.
	 */
	@Basic(optional = false)
	@Column(name = "nombre")
	private String nombre;
	/**
	 * Field esfiscal.
	 */
	@Basic(optional = false)
	@Column(name = "esfiscal")
	private String esfiscal;
	/**
	 * Field estaactivo.
	 */
	@Basic(optional = false)
	@Column(name = "estaactivo")
	private String estaactivo;
	/**
	 * Field estacionImpresion.
	 */
	@Basic(optional = false)
	@Column(name = "estacion_impresion")
	private String estacionImpresion;
	/**
	 * Field duplicadoAud.
	 */
	@Basic(optional = false)
	@Column(name = "duplicado_aud")
	private String duplicadoAud;
	/**
	 * Field actividadEconomica.
	 */
	@Basic(optional = false)
	@Column(name = "actividad_economica")
	private String actividadEconomica;
	/**
	 * Field fecha.
	 */
	@Basic(optional = false)
	@Column(name = "fecha")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	/**
	 * Field validaSerial.
	 */
	@Basic(optional = false)
	@Column(name = "valida_serial")
	private String validaSerial;
	/**
	 * Field requiereCliente.
	 */
	@Basic(optional = false)
	@Column(name = "requiere_cliente")
	private String requiereCliente;
	/**
	 * Field requiereNuevoDoc.
	 */
	@Basic(optional = false)
	@Column(name = "requiere_nuevo_doc")
	private String requiereNuevoDoc;
	/**
	 * Field permiteReimpresion.
	 */
	@Basic(optional = false)
	@Column(name = "permite_reimpresion")
	private String permiteReimpresion;
	/**
	 * Field muestraImpuesto.
	 */
	@Basic(optional = false)
	@Column(name = "muestra_impuesto")
	private String muestraImpuesto;
	/**
	 * Field essoporteventa.
	 */
	@Basic(optional = false)
	@Column(name = "essoporteventa")
	private String essoporteventa;
	/**
	 * Field esdevolucionparcial.
	 */
	@Basic(optional = false)
	@Column(name = "esdevolucionparcial")
	private String esdevolucionparcial;
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
	 * Field tipoclientetipodocumentoList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipodocumento")
	private List<Tipoclientetipodocumento> tipoclientetipodocumentoList;
	/**
	 * Field categorialineaincluyeList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipodocumento")
	private List<Categorialineaincluye> categorialineaincluyeList;
	/**
	 * Field tipodocumentoList.
	 */
	@OneToMany(mappedBy = "idContrapartida")
	private List<Tipodocumento> tipodocumentoList;
	/**
	 * Field idContrapartida.
	 */
	@JoinColumn(name = "id_contrapartida", referencedColumnName = "id")
	@ManyToOne
	private Tipodocumento idContrapartida;
	/**
	 * Field tipodocumentoList1.
	 */
	@OneToMany(mappedBy = "idContrapartidadevolucion")
	private List<Tipodocumento> tipodocumentoList1;
	/**
	 * Field idContrapartidadevolucion.
	 */
	@JoinColumn(name = "id_contrapartidadevolucion", referencedColumnName = "id")
	@ManyToOne
	private Tipodocumento idContrapartidadevolucion;

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
	 *            String
	 * @param estaactivo
	 *            String
	 * @param estacionImpresion
	 *            String
	 * @param duplicadoAud
	 *            String
	 * @param actividadEconomica
	 *            String
	 * @param fecha
	 *            Date
	 * @param validaSerial
	 *            String
	 * @param requiereCliente
	 *            String
	 * @param requiereNuevoDoc
	 *            String
	 * @param permiteReimpresion
	 *            String
	 * @param muestraImpuesto
	 *            String
	 * @param essoporteventa
	 *            String
	 * @param esdevolucionparcial
	 *            String
	 */
	public Tipodocumento(Long id, String nombre, String esfiscal, String estaactivo, String estacionImpresion,
			String duplicadoAud, String actividadEconomica, Date fecha, String validaSerial, String requiereCliente,
			String requiereNuevoDoc, String permiteReimpresion, String muestraImpuesto, String essoporteventa,
			String esdevolucionparcial) {
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
	 * @return String
	 */
	public String getEsfiscal() {
		return esfiscal;
	}

	/**
	 * Method setEsfiscal.
	 * 
	 * @param esfiscal
	 *            String
	 */
	public void setEsfiscal(String esfiscal) {
		this.esfiscal = esfiscal;
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
	 * Method getEstacionImpresion.
	 * 
	 * @return String
	 */
	public String getEstacionImpresion() {
		return estacionImpresion;
	}

	/**
	 * Method setEstacionImpresion.
	 * 
	 * @param estacionImpresion
	 *            String
	 */
	public void setEstacionImpresion(String estacionImpresion) {
		this.estacionImpresion = estacionImpresion;
	}

	/**
	 * Method getDuplicadoAud.
	 * 
	 * @return String
	 */
	public String getDuplicadoAud() {
		return duplicadoAud;
	}

	/**
	 * Method setDuplicadoAud.
	 * 
	 * @param duplicadoAud
	 *            String
	 */
	public void setDuplicadoAud(String duplicadoAud) {
		this.duplicadoAud = duplicadoAud;
	}

	/**
	 * Method getActividadEconomica.
	 * 
	 * @return String
	 */
	public String getActividadEconomica() {
		return actividadEconomica;
	}

	/**
	 * Method setActividadEconomica.
	 * 
	 * @param actividadEconomica
	 *            String
	 */
	public void setActividadEconomica(String actividadEconomica) {
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
	 * @return String
	 */
	public String getValidaSerial() {
		return validaSerial;
	}

	/**
	 * Method setValidaSerial.
	 * 
	 * @param validaSerial
	 *            String
	 */
	public void setValidaSerial(String validaSerial) {
		this.validaSerial = validaSerial;
	}

	/**
	 * Method getRequiereCliente.
	 * 
	 * @return String
	 */
	public String getRequiereCliente() {
		return requiereCliente;
	}

	/**
	 * Method setRequiereCliente.
	 * 
	 * @param requiereCliente
	 *            String
	 */
	public void setRequiereCliente(String requiereCliente) {
		this.requiereCliente = requiereCliente;
	}

	/**
	 * Method getRequiereNuevoDoc.
	 * 
	 * @return String
	 */
	public String getRequiereNuevoDoc() {
		return requiereNuevoDoc;
	}

	/**
	 * Method setRequiereNuevoDoc.
	 * 
	 * @param requiereNuevoDoc
	 *            String
	 */
	public void setRequiereNuevoDoc(String requiereNuevoDoc) {
		this.requiereNuevoDoc = requiereNuevoDoc;
	}

	/**
	 * Method getPermiteReimpresion.
	 * 
	 * @return String
	 */
	public String getPermiteReimpresion() {
		return permiteReimpresion;
	}

	/**
	 * Method setPermiteReimpresion.
	 * 
	 * @param permiteReimpresion
	 *            String
	 */
	public void setPermiteReimpresion(String permiteReimpresion) {
		this.permiteReimpresion = permiteReimpresion;
	}

	/**
	 * Method getMuestraImpuesto.
	 * 
	 * @return String
	 */
	public String getMuestraImpuesto() {
		return muestraImpuesto;
	}

	/**
	 * Method setMuestraImpuesto.
	 * 
	 * @param muestraImpuesto
	 *            String
	 */
	public void setMuestraImpuesto(String muestraImpuesto) {
		this.muestraImpuesto = muestraImpuesto;
	}

	/**
	 * Method getEssoporteventa.
	 * 
	 * @return String
	 */
	public String getEssoporteventa() {
		return essoporteventa;
	}

	/**
	 * Method setEssoporteventa.
	 * 
	 * @param essoporteventa
	 *            String
	 */
	public void setEssoporteventa(String essoporteventa) {
		this.essoporteventa = essoporteventa;
	}

	/**
	 * Method getEsdevolucionparcial.
	 * 
	 * @return String
	 */
	public String getEsdevolucionparcial() {
		return esdevolucionparcial;
	}

	/**
	 * Method setEsdevolucionparcial.
	 * 
	 * @param esdevolucionparcial
	 *            String
	 */
	public void setEsdevolucionparcial(String esdevolucionparcial) {
		this.esdevolucionparcial = esdevolucionparcial;
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
	public void setComprobantefiscalpreimpresoList(List<Comprobantefiscalpreimpreso> comprobantefiscalpreimpresoList) {
		this.comprobantefiscalpreimpresoList = comprobantefiscalpreimpresoList;
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
	public void setCategorialineaincluyeList(List<Categorialineaincluye> categorialineaincluyeList) {
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
	public void setIdContrapartidadevolucion(Tipodocumento idContrapartidadevolucion) {
		this.idContrapartidadevolucion = idContrapartidadevolucion;
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

		if (idContrapartida == null) {
			sb.append(nullStr);
		} else {
			sb.append(idContrapartida.getId());
		}

		sb.append(separator);

		sb.append(enclosed);
		sb.append(nombre);
		sb.append(enclosed);
		sb.append(separator);

		sb.append(esfiscal);
		sb.append(separator);

		sb.append(estaactivo);
		sb.append(separator);

		sb.append(estacionImpresion);
		sb.append(separator);

		sb.append(duplicadoAud);
		sb.append(separator);

		sb.append(actividadEconomica);
		sb.append(separator);

		sb.append(fecha == null ? nullStr : simpleDateFormat.format(fecha));
		sb.append(separator);

		sb.append(validaSerial);
		sb.append(separator);

		sb.append(requiereCliente);
		sb.append(separator);

		sb.append(requiereNuevoDoc);
		sb.append(separator);

		sb.append(permiteReimpresion);
		sb.append(separator);

		sb.append(muestraImpuesto);
		sb.append(separator);

		sb.append(essoporteventa);
		sb.append(separator);

		sb.append(esdevolucionparcial);
		sb.append(separator);

		if (idContrapartidadevolucion == null || idContrapartidadevolucion.getId() == null) {
			sb.append(nullStr);
		} else {
			sb.append(idContrapartidadevolucion.getId());
		}
		sb.append(endStr);

		return sb.toString();
	}

}
