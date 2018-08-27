/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crjpa400;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * 
 * @author eve0014321
 * @version $Revision: 1.0 $
 */
@Entity
@Table(name = "TIPOORDENDEVENTA")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Tipoordendeventa.findAll", query = "SELECT t FROM Tipoordendeventa t"),
		@NamedQuery(name = "Tipoordendeventa.findById", query = "SELECT t FROM Tipoordendeventa t WHERE t.id = :id"),
		@NamedQuery(name = "Tipoordendeventa.findByDescripcion", query = "SELECT t FROM Tipoordendeventa t WHERE t.descripcion = :descripcion"),
		@NamedQuery(name = "Tipoordendeventa.findByEstreplica", query = "SELECT t FROM Tipoordendeventa t WHERE t.estreplica = :estreplica") })
public class Tipoordendeventa implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9181431550580530927L;
	/**
	 * Field id.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID", nullable = false)
	private Long id;
	/**
	 * Field descripcion.
	 */
	@Basic(optional = false)
	@Column(name = "DESCRIPCION", nullable = false, length = 30)
	private String descripcion;
	/**
	 * Field estreplica.
	 */
	@Basic(optional = false)
	@Column(name = "ESTREPLICA")
	private char estreplica;
	/**
	 * Field ordendeventaList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoordendeventa")
	private List<Ordendeventa> ordendeventaList;
	/**
	 * Field puedeCambiarCantidadArticulos.
	 */
	@Basic(optional = false)
	@Column(name = "PUEDE_CAMBIAR_CANTIDAD_ARTICULOS", nullable = false)
	private char puedeCambiarCantidadArticulos;
	/**
	 * Field imprimirNumeroOrdenFactura.
	 */
	@Basic(optional = false)
	@Column(name = "IMPRIMIR_NUMERO_ORDEN_FACTURA", nullable = false)
	private char imprimirNumeroOrdenFactura;
	/**
	 * Field permiteRebajaPorCaja.
	 */
	@Basic(optional = false)
	@Column(name = "PERMITE_REBAJA_POR_CAJA", nullable = false)
	private char permiteRebajaPorCaja;
	/**
	 * Field permiteAgregarNuevosArticulos.
	 */
	@Basic(optional = false)
	@Column(name = "PERMITE_AGREGAR_NUEVOS_ARTICULOS", nullable = false)
	private char permiteAgregarNuevosArticulos;
	/**
	 * Field buscarMejorPrecioEnCaja.
	 */
	@Basic(optional = false)
	@Column(name = "BUSCAR_MEJOR_PRECIO_EN_CAJA", nullable = false)
	private char buscarMejorPrecioEnCaja;
	/**
	 * Field origenFDPAbonos.
	 */
	@Basic(optional = false)
	@Column(name = "ORIGEN_FDP_ABONOS", nullable = false)
	private char origenFDPAbonos;
	/**
	 * Field origenFDPDefecto.
	 */
	@Basic(optional = false)
	@Column(name = "ORIGEN_FDP_DEFECTO", nullable = false)
	private char origenFDPDefecto;
	/**
	 * Field origenFDPCaja.
	 */
	@Basic(optional = false)
	@Column(name = "ORIGEN_FDP_CAJA", nullable = false)
	private char origenFDPCaja;
	/**
	 * Field idOrigenordendeventa.
	 */
	//@JoinColumn(name = "ID_ORIGENORDENDEVENTA", referencedColumnName = "ID", nullable = false)
	@Basic(optional = false)
	@Column(name = "ID_ORIGENORDENDEVENTA", nullable = false)
	private Long idOrigenordendeventa;

	/**
	 * Constructor for Tipoordendeventa.
	 */
	public Tipoordendeventa() {
	}

	/**
	 * Constructor for Tipoordendeventa.
	 * 
	 * @param id
	 *            Long
	 */
	public Tipoordendeventa(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Tipoordendeventa.
	 * 
	 * @param id
	 *            Long
	 * @param descripcion
	 *            String
	 * @param estreplica
	 *            char
	 */
	public Tipoordendeventa(Long id, String descripcion, char estreplica) {
		this.id = id;
		this.descripcion = descripcion;
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
	 * Method getOrdendeventaList.
	 * 
	 * @return List<Ordendeventa>
	 */
	@XmlTransient
	public List<Ordendeventa> getOrdendeventaList() {
		return ordendeventaList;
	}

	/**
	 * Method setOrdendeventaList.
	 * 
	 * @param ordendeventaList
	 *            List<Ordendeventa>
	 */
	public void setOrdendeventaList(List<Ordendeventa> ordendeventaList) {
		this.ordendeventaList = ordendeventaList;
	}

	/**
	 * Method getPuedeCambiarCantidadArticulos.
	 * 
	 * @return char
	 */
	public char getPuedeCambiarCantidadArticulos() {
		return puedeCambiarCantidadArticulos;
	}

	/**
	 * Method getImprimirNumeroOrdenFactura.
	 * 
	 * @return char
	 */
	public char getImprimirNumeroOrdenFactura() {
		return imprimirNumeroOrdenFactura;
	}

	/**
	 * Method getPermiteRebajaPorCaja.
	 * 
	 * @return char
	 */
	public char getPermiteRebajaPorCaja() {
		return permiteRebajaPorCaja;
	}

	/**
	 * Method getPermiteAgregarNuevosArticulos.
	 * 
	 * @return char
	 */
	public char getPermiteAgregarNuevosArticulos() {
		return permiteAgregarNuevosArticulos;
	}

	/**
	 * Method getBuscarMejorPrecioEnCaja.
	 * 
	 * @return char
	 */
	public char getBuscarMejorPrecioEnCaja() {
		return buscarMejorPrecioEnCaja;
	}

	/**
	 * Method getOrigenFDPAbonos.
	 * 
	 * @return char
	 */
	public char getOrigenFDPAbonos() {
		return origenFDPAbonos;
	}

	/**
	 * Method getOrigenFDPDefecto.
	 * 
	 * @return char
	 */
	public char getOrigenFDPDefecto() {
		return origenFDPDefecto;
	}

	/**
	 * Method getOrigenFDPCaja.
	 * 
	 * @return char
	 */
	public char getOrigenFDPCaja() {
		return origenFDPCaja;
	}

	/**
	 * Method setPuedeCambiarCantidadArticulos.
	 * 
	 * @param puedeCambiarCantidadArticulos
	 *            char
	 */
	public void setPuedeCambiarCantidadArticulos(
			char puedeCambiarCantidadArticulos) {
		this.puedeCambiarCantidadArticulos = puedeCambiarCantidadArticulos;
	}

	/**
	 * Method setImprimirNumeroOrdenFactura.
	 * 
	 * @param imprimirNumeroOrdenFactura
	 *            char
	 */
	public void setImprimirNumeroOrdenFactura(char imprimirNumeroOrdenFactura) {
		this.imprimirNumeroOrdenFactura = imprimirNumeroOrdenFactura;
	}

	/**
	 * Method setPermiteRebajaPorCaja.
	 * 
	 * @param permiteRebajaPorCaja
	 *            char
	 */
	public void setPermiteRebajaPorCaja(char permiteRebajaPorCaja) {
		this.permiteRebajaPorCaja = permiteRebajaPorCaja;
	}

	/**
	 * Method setPermiteAgregarNuevosArticulos.
	 * 
	 * @param permiteAgregarNuevosArticulos
	 *            char
	 */
	public void setPermiteAgregarNuevosArticulos(
			char permiteAgregarNuevosArticulos) {
		this.permiteAgregarNuevosArticulos = permiteAgregarNuevosArticulos;
	}

	/**
	 * Method setBuscarMejorPrecioEnCaja.
	 * 
	 * @param buscarMejorPrecioEnCaja
	 *            char
	 */
	public void setBuscarMejorPrecioEnCaja(char buscarMejorPrecioEnCaja) {
		this.buscarMejorPrecioEnCaja = buscarMejorPrecioEnCaja;
	}

	/**
	 * Method setOrigenFDPAbonos.
	 * 
	 * @param origenFDPAbonos
	 *            char
	 */
	public void setOrigenFDPAbonos(char origenFDPAbonos) {
		this.origenFDPAbonos = origenFDPAbonos;
	}

	/**
	 * Method setOrigenFDPDefecto.
	 * 
	 * @param origenFDPDefecto
	 *            char
	 */
	public void setOrigenFDPDefecto(char origenFDPDefecto) {
		this.origenFDPDefecto = origenFDPDefecto;
	}

	/**
	 * Method setOrigenFDPCaja.
	 * 
	 * @param origenFDPCaja
	 *            char
	 */
	public void setOrigenFDPCaja(char origenFDPCaja) {
		this.origenFDPCaja = origenFDPCaja;
	}
	
	/**
	 * @return the idOrigenordendeventa
	 */
	public Long getIdOrigenordendeventa() {
		return idOrigenordendeventa;
	}

	/**
	 * @param idOrigenordendeventa the idOrigenordendeventa to set
	 */
	public void setIdOrigenordendeventa(Long idOrigenordendeventa) {
		this.idOrigenordendeventa = idOrigenordendeventa;
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
		if (!(object instanceof Tipoordendeventa)) {
			return false;
		}
		Tipoordendeventa other = (Tipoordendeventa) object;
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
		return "crjpa400.Tipoordendeventa[ id=" + id + " ]";
	}

}
