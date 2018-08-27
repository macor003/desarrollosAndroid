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

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author eve0011737
 * @version $Revision: 1.0 $
 */
@Entity
@Table(name = "ESTADOORDENDEVENTA")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Estadoordendeventa.findAll", query = "SELECT e FROM Estadoordendeventa e"),
		@NamedQuery(name = "Estadoordendeventa.findById", query = "SELECT e FROM Estadoordendeventa e WHERE e.id = :id"),
		@NamedQuery(name = "Estadoordendeventa.findByDescripcion", query = "SELECT e FROM Estadoordendeventa e WHERE e.descripcion = :descripcion"),
		@NamedQuery(name = "Estadoordendeventa.findByOrdenvisible", query = "SELECT e FROM Estadoordendeventa e WHERE e.ordenVisible = :ordenVisible"),
		@NamedQuery(name = "Estadoordendeventa.findByRecibeabonos", query = "SELECT e FROM Estadoordendeventa e WHERE e.recibeAbonos = :recibeAbonos"),
		@NamedQuery(name = "Estadoordendeventa.findByRecibeanulacionabonos", query = "SELECT e FROM Estadoordendeventa e WHERE e.recibeAnulacionAbonos = :recibeAnulacionAbonos"),
		@NamedQuery(name = "Estadoordendeventa.findByOrdenpuedeanularse", query = "SELECT e FROM Estadoordendeventa e WHERE e.ordenPuedeAnularse = :ordenPuedeAnularse"),
		@NamedQuery(name = "Estadoordendeventa.findByOrdenpuedefacturarse", query = "SELECT e FROM Estadoordendeventa e WHERE e.ordenPuedeFacturarse = :ordenPuedeFacturarse"),
		@NamedQuery(name = "Estadoordendeventa.findByPermiteretencionimp", query = "SELECT e FROM Estadoordendeventa e WHERE e.permiteRetencionImp = :permiteRetencionImp"),
		@NamedQuery(name = "Estadoordendeventa.findByPermitecambiocondicionentrega", query = "SELECT e FROM Estadoordendeventa e WHERE e.permiteCambioCondicionEntrega = :permiteCambioCondicionEntrega"),
		@NamedQuery(name = "Estadoordendeventa.findByUltimasincronizacion", query = "SELECT e FROM Estadoordendeventa e WHERE e.ultimasincronizacion = :ultimasincronizacion"),
		@NamedQuery(name = "Estadoordendeventa.findByEstreplica", query = "SELECT e FROM Estadoordendeventa e WHERE e.estreplica = :estreplica") })
public class Estadoordendeventa implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7969917823106495438L;
	/**
	 * Field id.
	 */
	@Id
	@Basic(optional = false)
	@Column(name = "ID", nullable = false)
	private Long id;
	/**
	 * Field descripcion.
	 */
	@Basic(optional = false)
	@Column(name = "DESCRIPCION", nullable = false, length = 40)
	private String descripcion;
	/**
	 * Field ordenVisible.
	 */
	@Basic(optional = false)
	@Column(name = "ORDENVISIBLE", nullable = false)
	private char ordenVisible;
	/**
	 * Field recibeAbonos.
	 */
	@Basic(optional = false)
	@Column(name = "RECIBEABONOS", nullable = false)
	private char recibeAbonos;
	/**
	 * Field recibeAnulacionAbonos.
	 */
	@Basic(optional = false)
	@Column(name = "RECIBEANULACIONABONOS", nullable = false)
	private char recibeAnulacionAbonos;
	/**
	 * Field ordenPuedeAnularse.
	 */
	@Basic(optional = false)
	@Column(name = "ORDENPUEDEANULARSE", nullable = false)
	private char ordenPuedeAnularse;
	/**
	 * Field ordenPuedeFacturarse.
	 */
	@Basic(optional = false)
	@Column(name = "ORDENPUEDEFACTURARSE", nullable = false)
	private char ordenPuedeFacturarse;
	/**
	 * Field permiteRetencionImp.
	 */
	@Basic(optional = false)
	@Column(name = "PERMITERETENCIONIMP", nullable = false)
	private char permiteRetencionImp;
	/**
	 * Field permiteCambioCondicionEntrega.
	 */
	@Basic(optional = false)
	@Column(name = "PERMITECAMBIOCONDICIONENTREGA", nullable = false)
	private char permiteCambioCondicionEntrega;
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
	 * Field descripcionCorta.
	 */
	@Basic(optional = false)
	@Column(name = "DESCRIPCIONCORTA", nullable = false, length = 10)
	private String descripcionCorta;

	/**
	 * Constructor for Estadoordendeventa.
	 */
	public Estadoordendeventa() {
	}

	/**
	 * Constructor for Estadoordendeventa.
	 * 
	 * @param id
	 *            Long
	 */
	public Estadoordendeventa(Long id) {
		this.id = id;
	}

	/**
	 * @param id
	 * @param descripcion
	 * @param ordenVisible
	 * @param recibeAbonos
	 * @param recibeAnulacionAbonos
	 * @param ordenPuedeAnularse
	 * @param ordenPuedeFacturarse
	 * @param permiteRetencionImp
	 * @param permiteCambioCondicionEntrega
	 * @param ultimasincronizacion
	 * @param estreplica
	 */
	public Estadoordendeventa(Long id, String descripcion, char ordenVisible,
			char recibeAbonos, char recibeAnulacionAbonos,
			char ordenPuedeAnularse, char ordenPuedeFacturarse,
			char permiteRetencionImp, char permiteCambioCondicionEntrega,
			Calendar ultimasincronizacion, char estreplica) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.ordenVisible = ordenVisible;
		this.recibeAbonos = recibeAbonos;
		this.recibeAnulacionAbonos = recibeAnulacionAbonos;
		this.ordenPuedeAnularse = ordenPuedeAnularse;
		this.ordenPuedeFacturarse = ordenPuedeFacturarse;
		this.permiteRetencionImp = permiteRetencionImp;
		this.permiteCambioCondicionEntrega = permiteCambioCondicionEntrega;
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
	 * Method getOrdenVisible.
	 * 
	 * @return char
	 */
	public char getOrdenVisible() {
		return ordenVisible;
	}

	/**
	 * Method setOrdenVisible.
	 * 
	 * @param ordenVisible
	 *            char
	 */
	public void setOrdenVisible(char ordenVisible) {
		this.ordenVisible = ordenVisible;
	}

	/**
	 * Method getRecibeAbonos.
	 * 
	 * @return char
	 */
	public char getRecibeAbonos() {
		return recibeAbonos;
	}

	/**
	 * Method setRecibeAbonos.
	 * 
	 * @param recibeAbonos
	 *            char
	 */
	public void setRecibeAbonos(char recibeAbonos) {
		this.recibeAbonos = recibeAbonos;
	}

	/**
	 * Method getRecibeAnulacionAbonos.
	 * 
	 * @return char
	 */
	public char getRecibeAnulacionAbonos() {
		return recibeAnulacionAbonos;
	}

	/**
	 * Method setRecibeAnulacionAbonos.
	 * 
	 * @param recibeAnulacionAbonos
	 *            char
	 */
	public void setRecibeAnulacionAbonos(char recibeAnulacionAbonos) {
		this.recibeAnulacionAbonos = recibeAnulacionAbonos;
	}

	/**
	 * Method getOrdenPuedeAnularse.
	 * 
	 * @return char
	 */
	public char getOrdenPuedeAnularse() {
		return ordenPuedeAnularse;
	}

	/**
	 * Method setOrdenPuedeAnularse.
	 * 
	 * @param ordenPuedeAnularse
	 *            char
	 */
	public void setOrdenPuedeAnularse(char ordenPuedeAnularse) {
		this.ordenPuedeAnularse = ordenPuedeAnularse;
	}

	/**
	 * Method getOrdenPuedeFacturarse.
	 * 
	 * @return char
	 */
	public char getOrdenPuedeFacturarse() {
		return ordenPuedeFacturarse;
	}

	/**
	 * Method setOrdenPuedeFacturarse.
	 * 
	 * @param ordenPuedeFacturarse
	 *            char
	 */
	public void setOrdenPuedeFacturarse(char ordenPuedeFacturarse) {
		this.ordenPuedeFacturarse = ordenPuedeFacturarse;
	}

	/**
	 * Method getPermiteRetencionImp.
	 * 
	 * @return char
	 */
	public char getPermiteRetencionImp() {
		return permiteRetencionImp;
	}

	/**
	 * Method setPermiteRetencionImp.
	 * 
	 * @param permiteRetencionImp
	 *            char
	 */
	public void setPermiteRetencionImp(char permiteRetencionImp) {
		this.permiteRetencionImp = permiteRetencionImp;
	}

	/**
	 * Method getPermiteCambioCondicionEntrega.
	 * 
	 * @return char
	 */
	public char getPermiteCambioCondicionEntrega() {
		return permiteCambioCondicionEntrega;
	}

	/**
	 * Method setPermiteCambioCondicionEntrega.
	 * 
	 * @param permiteCambioCondicionEntrega
	 *            char
	 */
	public void setPermiteCambioCondicionEntrega(
			char permiteCambioCondicionEntrega) {
		this.permiteCambioCondicionEntrega = permiteCambioCondicionEntrega;
	}

	/**
	 * Method getUltimasincronizacion.
	 * 
	 * @return Calendar
	 */
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
	 * 
	 * @return the descripcionCorta
	 */
	public String getDescripcionCorta() {
		return descripcionCorta;
	}

	/**
	 * @param descripcionCorta
	 *            the descripcionCorta to set
	 */
	public void setDescripcionCorta(String descripcionCorta) {
		this.descripcionCorta = descripcionCorta;
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
		if (!(object instanceof Estadoordendeventa)) {
			return false;
		}
		Estadoordendeventa other = (Estadoordendeventa) object;
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
		return "crjpa400.Estadoordendenventa[ id=" + id + " ]";
	}

}
