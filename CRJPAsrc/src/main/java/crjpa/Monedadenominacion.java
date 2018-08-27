/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crjpa;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "monedadenominacion")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Monedadenominacion.findAll", query = "SELECT m FROM Monedadenominacion m"),
		@NamedQuery(name = "Monedadenominacion.findById", query = "SELECT m FROM Monedadenominacion m WHERE m.id = :id"),
		@NamedQuery(name = "Monedadenominacion.findByCodigo", query = "SELECT m FROM Monedadenominacion m WHERE m.codigo = :codigo"),
		@NamedQuery(name = "Monedadenominacion.findByNombre", query = "SELECT m FROM Monedadenominacion m WHERE m.nombre = :nombre"),
		@NamedQuery(name = "Monedadenominacion.findByMultiplo", query = "SELECT m FROM Monedadenominacion m WHERE m.multiplo = :multiplo"),
		@NamedQuery(name = "Monedadenominacion.findByEstaactivo", query = "SELECT m FROM Monedadenominacion m WHERE m.estaactivo = :estaactivo"),
		@NamedQuery(name = "Monedadenominacion.findByFecha", query = "SELECT m FROM Monedadenominacion m WHERE m.fecha = :fecha")
		 , @NamedQuery(name = "Monedadenominacion.findByTienda", query = "SELECT m FROM Monedadenominacion m WHERE m.tienda = :tienda")
	    , @NamedQuery(name = "Monedadenominacion.findByPorcentajeComision", query = "SELECT m FROM Monedadenominacion m WHERE m.porcentajeComision = :porcentajeComision")
	    , @NamedQuery(name = "Monedadenominacion.findByPorcentajeImpuesto", query = "SELECT m FROM Monedadenominacion m WHERE m.porcentajeImpuesto = :porcentajeImpuesto")
	    , @NamedQuery(name = "Monedadenominacion.findByCuentaContableImpuesto", query = "SELECT m FROM Monedadenominacion m WHERE m.cuentaContableImpuesto = :cuentaContableImpuesto")
	    , @NamedQuery(name = "Monedadenominacion.findByCuentaContable", query = "SELECT m FROM Monedadenominacion m WHERE m.cuentaContable = :cuentaContable")
	    , @NamedQuery(name = "Monedadenominacion.findByCuentaContableGastos", query = "SELECT m FROM Monedadenominacion m WHERE m.cuentaContableGastos = :cuentaContableGastos")
	    , @NamedQuery(name = "Monedadenominacion.findByEstadoRegistro", query = "SELECT m FROM Monedadenominacion m WHERE m.estadoRegistro = :estadoRegistro")
	    , @NamedQuery(name = "Monedadenominacion.findByAuxiliar", query = "SELECT m FROM Monedadenominacion m WHERE m.auxiliar = :auxiliar")})
public class Monedadenominacion implements Serializable {
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
	 * Field codigo.
	 */
	@Basic(optional = false)
	@Column(name = "codigo")
	private int codigo;
	/**
	 * Field nombre.
	 */
	@Basic(optional = false)
	@Column(name = "nombre")
	private String nombre;
	// @Max(value=?) @Min(value=?)//if you know range of your decimal fields
	// consider using these annotations to enforce field validation
	/**
	 * Field multiplo.
	 */
	@Basic(optional = false)
	@Column(name = "multiplo")
	private BigDecimal multiplo;
	/**
	 * Field estaactivo.
	 */
	@Basic(optional = false)
	@Column(name = "estaactivo")
	private String estaactivo;
	/**
	 * Field fecha.
	 */
	@Basic(optional = false)
	@Column(name = "fecha")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	/**
	 * Field tienda.
	 */
	@Basic(optional = false)
	@Column(name = "tienda")
    private String tienda;
	/**
	 * Field porcentajeComision.
	 */
	@Basic(optional = false)
    @Column(name = "porcentaje_comision")
    private BigDecimal porcentajeComision;
    /**
	 * Field porcentajeImpuesto.
	 */
	@Basic(optional = false)
    @Column(name = "porcentaje_impuesto")
    private BigDecimal porcentajeImpuesto;
    /**
	 * Field cuentaContableImpuesto.
	 */
	@Basic(optional = false)
    @Column(name = "cuenta_contable_impuesto")
    private String cuentaContableImpuesto;
    /**
	 * Field cuentaContable.
	 */
	@Basic(optional = false)
    @Column(name = "cuenta_contable")
    private String cuentaContable;
    /**
	 * Field cuentaContableGastos.
	 */
	@Basic(optional = false)
    @Column(name = "cuenta_contable_gastos")
    private String cuentaContableGastos;
    /**
	 * Field estadoRegistro.
	 */
	@Basic(optional = false)
    @Column(name = "estado_registro")
    private Character estadoRegistro;
    /**
	 * Field auxiliar.
	 */
	@Basic(optional = false)
    @Column(name = "auxiliar")
    private String auxiliar;
	/**
	 * Field idMoneda.
	 */
	@JoinColumn(name = "id_moneda", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Moneda idMoneda;
	/**
	 * Field idFormadepago.
	 */
    @JoinColumn(name = "id_formadepago", referencedColumnName = "id")
    @ManyToOne(optional=false)
    private Formadepago idFormadepago;

	/**
	 * Field entregaformadepagoList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idMonedadenominacion")
	private List<Entregaformadepago> entregaformadepagoList;

	/**
	 * Constructor for Monedadenominacion.
	 */
	public Monedadenominacion() {
	}

	/**
	 * Constructor for Monedadenominacion.
	 * 
	 * @param id
	 *            Long
	 */
	public Monedadenominacion(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Monedadenominacion.
	 * 
	 * @param id
	 *            Long
	 * @param codigo
	 *            int
	 * @param nombre
	 *            String
	 * @param multiplo
	 *            BigDecimal
	 * @param estaactivo
	 *            String
	 * @param fecha
	 *            Date
	 */
	public Monedadenominacion(Long id, int codigo, String nombre, BigDecimal multiplo, String estaactivo, Date fecha) {
		this.id = id;
		this.codigo = codigo;
		this.nombre = nombre;
		this.multiplo = multiplo;
		this.estaactivo = estaactivo;
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
	 * Method getCodigo.
	 * 
	 * @return int
	 */
	public int getCodigo() {
		return codigo;
	}

	/**
	 * Method setCodigo.
	 * 
	 * @param codigo
	 *            int
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
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
	 * Method getMultiplo.
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal getMultiplo() {
		return multiplo;
	}

	/**
	 * Method setMultiplo.
	 * 
	 * @param multiplo
	 *            BigDecimal
	 */
	public void setMultiplo(BigDecimal multiplo) {
		this.multiplo = multiplo;
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
	 * Method getIdMoneda.
	 * 
	 * @return Moneda
	 */
	public Moneda getIdMoneda() {
		return idMoneda;
	}

	/**
	 * Method setIdMoneda.
	 * 
	 * @param idMoneda
	 *            Moneda
	 */
	public void setIdMoneda(Moneda idMoneda) {
		this.idMoneda = idMoneda;
	}
	

	/**
	 *  Method getTienda.
	 * 
	 * @return the tienda
	 */
	public String getTienda() {
		return tienda;
	}

	/**
	 *   Method setTienda.
	 * 
	 * @param tienda the tienda to set
	 */
	public void setTienda(String tienda) {
		this.tienda = tienda;
	}

	/**
	 *   Method getPorcentajeComision.
	 * 
	 * @return the porcentajeComision
	 */
	public BigDecimal getPorcentajeComision() {
		return porcentajeComision;
	}

	/**
	 *   Method setPorcentajeComision.
	 * 
	 * @param porcentajeComision the porcentajeComision to set
	 */
	public void setPorcentajeComision(BigDecimal porcentajeComision) {
		this.porcentajeComision = porcentajeComision;
	}

	/**
	 * 
	 *   Method getPorcentajeImpuesto.
	 * 
	 * @return the porcentajeImpuesto
	 */
	public BigDecimal getPorcentajeImpuesto() {
		return porcentajeImpuesto;
	}

	/**
	 * 
	 * Method setPorcentajeImpuesto.
	 * 
	 * @param porcentajeImpuesto the porcentajeImpuesto to set
	 */
	public void setPorcentajeImpuesto(BigDecimal porcentajeImpuesto) {
		this.porcentajeImpuesto = porcentajeImpuesto;
	}

	/**
	 * 
	 * Method getCuentaContableImpuesto.
	 * 
	 * @return the cuentaContableImpuesto
	 */
	public String getCuentaContableImpuesto() {
		return cuentaContableImpuesto;
	}

	/**
	 * 
	 * Method setCuentaContableImpuesto.
	 * 
	 * @param cuentaContableImpuesto the cuentaContableImpuesto to set
	 */
	public void setCuentaContableImpuesto(String cuentaContableImpuesto) {
		this.cuentaContableImpuesto = cuentaContableImpuesto;
	}

	/**
	 * 
	 * 	Method getCuentaContable.
	 * 
	 * @return the cuentaContable
	 */
	public String getCuentaContable() {
		return cuentaContable;
	}

	/**
	 * 
	 * Method setCuentaContable.
	 * 
	 * @param cuentaContable the cuentaContable to set
	 */
	public void setCuentaContable(String cuentaContable) {
		this.cuentaContable = cuentaContable;
	}

	/**
	 * 
	 * Method getCuentaContableGastos.
	 * 
	 * @return the cuentaContableGastos
	 */
	public String getCuentaContableGastos() {
		return cuentaContableGastos;
	}

	/**
	 * 
	 * Method setCuentaContableGastos.
	 * 
	 * @param cuentaContableGastos the cuentaContableGastos to set
	 */
	public void setCuentaContableGastos(String cuentaContableGastos) {
		this.cuentaContableGastos = cuentaContableGastos;
	}

	/**
	 * 
	 * Method getEstadoRegistro.
	 * 
	 * @return the estadoRegistro
	 */
	public Character getEstadoRegistro() {
		return estadoRegistro;
	}

	/**
	 * Method setEstadoRegistro.
	 * 
	 * @param estadoRegistro the estadoRegistro to set
	 */
	public void setEstadoRegistro(Character estadoRegistro) {
		this.estadoRegistro = estadoRegistro;
	}

	/**
	 * 
	 * Method getAuxiliar.
	 * 
	 * @return the auxiliar
	 */
	public String getAuxiliar() {
		return auxiliar;
	}

	/**
	 * 
	 * Method setAuxiliar.
	 * 
	 * @param auxiliar the auxiliar to set
	 */
	public void setAuxiliar(String auxiliar) {
		this.auxiliar = auxiliar;
	}

	/**
	 * 
	 * Method getIdFormadepago.
	 * 
	 * @return the idFormadepago
	 */
	public Formadepago getIdFormadepago() {
		return idFormadepago;
	}

	/**
	 * 
	 * Method setIdFormadepago.
	 * 
	 * @param idFormadepago the idFormadepago to set
	 */
	public void setIdFormadepago(Formadepago idFormadepago) {
		this.idFormadepago = idFormadepago;
	}


	/**
	 * Method getEntregaformadepagoList.
	 * 
	 * @return List<Entregaformadepago>
	 */
	@XmlTransient
	public List<Entregaformadepago> getEntregaformadepagoList() {
		return entregaformadepagoList;
	}

	/**
	 * Method setEntregaformadepagoList.
	 * 
	 * @param entregaformadepagoList
	 *            List<Entregaformadepago>
	 */
	public void setEntregaformadepagoList(List<Entregaformadepago> entregaformadepagoList) {
		this.entregaformadepagoList = entregaformadepagoList;
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
		if (!(object instanceof Monedadenominacion)) {
			return false;
		}
		Monedadenominacion other = (Monedadenominacion) object;
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

		sb.append(idMoneda.getId());
		sb.append(separator);

		sb.append(codigo);
		sb.append(separator);

		sb.append(enclosed);
		sb.append(nombre);
		sb.append(enclosed);
		sb.append(separator);

		sb.append(multiplo);
		sb.append(separator);

		sb.append(estaactivo);
		sb.append(separator);

		sb.append(fecha == null ? nullStr : simpleDateFormat.format(fecha));
		sb.append(endStr);

		return sb.toString();
	}

}
