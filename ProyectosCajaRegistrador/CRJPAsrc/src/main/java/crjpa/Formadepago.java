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
@Table(name = "formadepago")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Formadepago.findAll", query = "SELECT f FROM Formadepago f"),
		@NamedQuery(name = "Formadepago.findById", query = "SELECT f FROM Formadepago f WHERE f.id = :id"),
		@NamedQuery(name = "Formadepago.findByCodigo", query = "SELECT f FROM Formadepago f WHERE f.codigo = :codigo"),
		@NamedQuery(name = "Formadepago.findByNombre", query = "SELECT f FROM Formadepago f WHERE f.nombre = :nombre"),
		@NamedQuery(name = "Formadepago.findByDescripcion", query = "SELECT f FROM Formadepago f WHERE f.descripcion = :descripcion"),
		@NamedQuery(name = "Formadepago.findByPrioridadDonacion", query = "SELECT f FROM Formadepago f WHERE f.prioridadDonacion = :prioridadDonacion"),
		@NamedQuery(name = "Formadepago.findByPermiteVuelto", query = "SELECT f FROM Formadepago f WHERE f.permiteVuelto = :permiteVuelto"),
		@NamedQuery(name = "Formadepago.findByMontoMinimo", query = "SELECT f FROM Formadepago f WHERE f.montoMinimo = :montoMinimo"),
		@NamedQuery(name = "Formadepago.findByMontoMaximo", query = "SELECT f FROM Formadepago f WHERE f.montoMaximo = :montoMaximo"),
		@NamedQuery(name = "Formadepago.findByFecha", query = "SELECT f FROM Formadepago f WHERE f.fecha = :fecha"),
		@NamedQuery(name = "Formadepago.findByRequiereAutorizacion", query = "SELECT f FROM Formadepago f WHERE f.requiereAutorizacion = :requiereAutorizacion"),
		@NamedQuery(name = "Formadepago.findByTipoFormaDePago", query = "SELECT f FROM Formadepago f WHERE f.tipoFormaDePago = :tipoFormaDePago"),
		@NamedQuery(name = "Formadepago.findByIconoFormaDePago", query = "SELECT f FROM Formadepago f WHERE f.iconoFormaDePago = :iconoFormaDePago"),
		@NamedQuery(name = "Formadepago.findByTipoTarjeta", query = "SELECT f FROM Formadepago f WHERE f.tipoTarjeta = :tipoTarjeta"),
		@NamedQuery(name = "Formadepago.findByPermiteReverso", query = "SELECT f FROM Formadepago f WHERE f.permiteReverso = :permiteReverso"),
		@NamedQuery(name = "Formadepago.findByPermiteReasignarCliente", query = "SELECT f FROM Formadepago f WHERE f.permiteReasignarCliente = :permiteReasignarCliente"),
		@NamedQuery(name = "Formadepago.findByEstaactivo", query = "SELECT f FROM Formadepago f WHERE f.estaactivo = :estaactivo") })
public class Formadepago implements Serializable {
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
	/**
	 * Field descripcion.
	 */
	@Basic(optional = false)
	@Column(name = "descripcion")
	private String descripcion;
	/**
	 * Field prioridadDonacion.
	 */
	@Basic(optional = false)
	@Column(name = "prioridad_donacion")
	private int prioridadDonacion;
	/**
	 * Field permiteVuelto.
	 */
	@Basic(optional = false)
	@Column(name = "permite_vuelto")
	private String permiteVuelto;
	// @Max(value=?) @Min(value=?)//if you know range of your decimal fields
	// consider using these annotations to enforce field validation
	/**
	 * Field montoMinimo.
	 */
	@Basic(optional = false)
	@Column(name = "monto_minimo")
	private BigDecimal montoMinimo;
	/**
	 * Field montoMaximo.
	 */
	@Basic(optional = false)
	@Column(name = "monto_maximo")
	private BigDecimal montoMaximo;
	/**
	 * Field fecha.
	 */
	@Basic(optional = false)
	@Column(name = "fecha")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	/**
	 * Field requiereAutorizacion.
	 */
	@Basic(optional = false)
	@Column(name = "requiere_autorizacion")
	private String requiereAutorizacion;
	/**
	 * Field tipoFormaDePago.
	 */
	@Basic(optional = false)
	@Column(name = "tipo_forma_de_pago")
	private int tipoFormaDePago;
	/**
	 * Field iconoFormaDePago.
	 */
	@Column(name = "icono_forma_de_pago")
	private String iconoFormaDePago;
	/**
	 * Field tipoTarjeta.
	 */
	@Column(name = "tipo_tarjeta")
	private String tipoTarjeta;
	/**
	 * Field permiteReverso.
	 */
	@Basic(optional = false)
	@Column(name = "permite_reverso")
	private String permiteReverso;
	/**
	 * Field permiteReasignarCliente.
	 */
	@Basic(optional = false)
	@Column(name = "permite_reasignar_cliente")
	private String permiteReasignarCliente;
	/**
	 * Field estaactivo.
	 */
	@Basic(optional = false)
	@Column(name = "estaactivo")
	private String estaactivo;
	/**
	 * Field promocionformadepagoList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idFormaDePago")
	private List<Promocionformadepago> promocionformadepagoList;
	/**
	 * Field formadepagomonedaList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idFormadepago")
	private List<Formadepagomoneda> formadepagomonedaList;
	/**
	 * Field detalletipoformadepagoList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idFormadepago")
	private List<Detalletipoformadepago> detalletipoformadepagoList;
	/**
	 * Field entregaformadepagoList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idFormadepago")
	private List<Entregaformadepago> entregaformadepagoList;
	/**
	 * Field transaccionformadepagoList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idFormadepago")
	private List<Transaccionformadepago> transaccionformadepagoList;
	/**
	 * Field morosidadList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idFormadepago")
	private List<Morosidad> morosidadList;
//	/**
//	 * Field categorialinearetencionList.
//	 */
//	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idFormadepago")
//	private List<Categorialinearetencion> categorialinearetencionList;
	/**
	 * Field porcentajeimpuestoretencionList.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idFormadepago")
	private List<Porcentajeimpuestoretencion> porcentajeimpuestoretencionList;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idFormadepago")
    private List<Formadepagotipocliente> formadepagotipoclienteList;
	/**
	 * Field monedadenominacionList.
	 */
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "idFormadepago")
    private List<Monedadenominacion> monedadenominacionList;
	/**
	 * Field tipodocumentoList.
	 */
	@OneToMany(mappedBy = "idContrapartida")
	private List<Formadepago> formadepagos;
	/**
	 * Field idContrapartida.
	 */
	@JoinColumn(name = "id_contrapartida", referencedColumnName = "id")
	@ManyToOne
	private Formadepago idContrapartida;

	/**
	 * Constructor for Formadepago.
	 */
	public Formadepago() {
	}

	/**
	 * Constructor for Formadepago.
	 * 
	 * @param id
	 *            Long
	 */
	public Formadepago(Long id) {
		this.id = id;
	}

	/**
	 * Constructor for Formadepago.
	 * 
	 * @param id
	 *            Long
	 * @param codigo
	 *            int
	 * @param nombre
	 *            String
	 * @param descripcion
	 *            String
	 * @param prioridadDonacion
	 *            int
	 * @param permiteVuelto
	 *            String
	 * @param montoMinimo
	 *            BigDecimal
	 * @param montoMaximo
	 *            BigDecimal
	 * @param fecha
	 *            Date
	 * @param requiereAutorizacion
	 *            String
	 * @param tipoFormaDePago
	 *            int
	 * @param permiteReverso
	 *            String
	 * @param permiteReasignarCliente
	 *            String
	 * @param estaactivo
	 *            String
	 */
	public Formadepago(Long id, int codigo, String nombre, String descripcion, int prioridadDonacion,
			String permiteVuelto, BigDecimal montoMinimo, BigDecimal montoMaximo, Date fecha,
			String requiereAutorizacion, int tipoFormaDePago, String permiteReverso, String permiteReasignarCliente,
			String estaactivo) {
		this.id = id;
		this.codigo = codigo;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.prioridadDonacion = prioridadDonacion;
		this.permiteVuelto = permiteVuelto;
		this.montoMinimo = montoMinimo;
		this.montoMaximo = montoMaximo;
		this.fecha = fecha;
		this.requiereAutorizacion = requiereAutorizacion;
		this.tipoFormaDePago = tipoFormaDePago;
		this.permiteReverso = permiteReverso;
		this.permiteReasignarCliente = permiteReasignarCliente;
		this.estaactivo = estaactivo;
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
	 * Method getPrioridadDonacion.
	 * 
	 * @return int
	 */
	public int getPrioridadDonacion() {
		return prioridadDonacion;
	}

	/**
	 * Method setPrioridadDonacion.
	 * 
	 * @param prioridadDonacion
	 *            int
	 */
	public void setPrioridadDonacion(int prioridadDonacion) {
		this.prioridadDonacion = prioridadDonacion;
	}

	/**
	 * Method getPermiteVuelto.
	 * 
	 * @return String
	 */
	public String getPermiteVuelto() {
		return permiteVuelto;
	}

	/**
	 * Method setPermiteVuelto.
	 * 
	 * @param permiteVuelto
	 *            String
	 */
	public void setPermiteVuelto(String permiteVuelto) {
		this.permiteVuelto = permiteVuelto;
	}

	/**
	 * Method getMontoMinimo.
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal getMontoMinimo() {
		return montoMinimo;
	}

	/**
	 * Method setMontoMinimo.
	 * 
	 * @param montoMinimo
	 *            BigDecimal
	 */
	public void setMontoMinimo(BigDecimal montoMinimo) {
		this.montoMinimo = montoMinimo;
	}

	/**
	 * Method getMontoMaximo.
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal getMontoMaximo() {
		return montoMaximo;
	}

	/**
	 * Method setMontoMaximo.
	 * 
	 * @param montoMaximo
	 *            BigDecimal
	 */
	public void setMontoMaximo(BigDecimal montoMaximo) {
		this.montoMaximo = montoMaximo;
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
	 * Method getRequiereAutorizacion.
	 * 
	 * @return String
	 */
	public String getRequiereAutorizacion() {
		return requiereAutorizacion;
	}

	/**
	 * Method setRequiereAutorizacion.
	 * 
	 * @param requiereAutorizacion
	 *            String
	 */
	public void setRequiereAutorizacion(String requiereAutorizacion) {
		this.requiereAutorizacion = requiereAutorizacion;
	}

	/**
	 * Method getTipoFormaDePago.
	 * 
	 * @return int
	 */
	public int getTipoFormaDePago() {
		return tipoFormaDePago;
	}

	/**
	 * Method setTipoFormaDePago.
	 * 
	 * @param tipoFormaDePago
	 *            int
	 */
	public void setTipoFormaDePago(int tipoFormaDePago) {
		this.tipoFormaDePago = tipoFormaDePago;
	}

	/**
	 * Method getIconoFormaDePago.
	 * 
	 * @return String
	 */
	public String getIconoFormaDePago() {
		return iconoFormaDePago;
	}

	/**
	 * Method setIconoFormaDePago.
	 * 
	 * @param iconoFormaDePago
	 *            String
	 */
	public void setIconoFormaDePago(String iconoFormaDePago) {
		this.iconoFormaDePago = iconoFormaDePago;
	}

	/**
	 * Method getTipoTarjeta.
	 * 
	 * @return String
	 */
	public String getTipoTarjeta() {
		return tipoTarjeta;
	}

	/**
	 * Method setTipoTarjeta.
	 * 
	 * @param tipoTarjeta
	 *            String
	 */
	public void setTipoTarjeta(String tipoTarjeta) {
		this.tipoTarjeta = tipoTarjeta;
	}

	/**
	 * Method getPermiteReverso.
	 * 
	 * @return String
	 */
	public String getPermiteReverso() {
		return permiteReverso;
	}

	/**
	 * Method setPermiteReverso.
	 * 
	 * @param permiteReverso
	 *            String
	 */
	public void setPermiteReverso(String permiteReverso) {
		this.permiteReverso = permiteReverso;
	}

	/**
	 * Method getPermiteReasignarCliente.
	 * 
	 * @return String
	 */
	public String getPermiteReasignarCliente() {
		return permiteReasignarCliente;
	}

	/**
	 * Method setPermiteReasignarCliente.
	 * 
	 * @param permiteReasignarCliente
	 *            String
	 */
	public void setPermiteReasignarCliente(String permiteReasignarCliente) {
		this.permiteReasignarCliente = permiteReasignarCliente;
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
	 * Method getPromocionformadepagoList.
	 * 
	 * @return List<Promocionformadepago>
	 */
	@XmlTransient
	public List<Promocionformadepago> getPromocionformadepagoList() {
		return promocionformadepagoList;
	}

	/**
	 * Method setPromocionformadepagoList.
	 * 
	 * @param promocionformadepagoList
	 *            List<Promocionformadepago>
	 */
	public void setPromocionformadepagoList(List<Promocionformadepago> promocionformadepagoList) {
		this.promocionformadepagoList = promocionformadepagoList;
	}

	@XmlTransient
    public List<Formadepagotipocliente> getFormadepagotipoclienteList() {
        return formadepagotipoclienteList;
    }

    public void setFormadepagotipoclienteList(List<Formadepagotipocliente> formadepagotipoclienteList) {
        this.formadepagotipoclienteList = formadepagotipoclienteList;
    }
    
	/**
	 * Method getFormadepagomonedaList.
	 * 
	 * @return List<Formadepagomoneda>
	 */
	@XmlTransient
	public List<Formadepagomoneda> getFormadepagomonedaList() {
		return formadepagomonedaList;
	}

	/**
	 * Method setFormadepagomonedaList.
	 * 
	 * @param formadepagomonedaList
	 *            List<Formadepagomoneda>
	 */
	public void setFormadepagomonedaList(List<Formadepagomoneda> formadepagomonedaList) {
		this.formadepagomonedaList = formadepagomonedaList;
	}

	/**
	 * Method getDetalletipoformadepagoList.
	 * 
	 * @return List<Detalletipoformadepago>
	 */
	@XmlTransient
	public List<Detalletipoformadepago> getDetalletipoformadepagoList() {
		return detalletipoformadepagoList;
	}

	/**
	 * Method setDetalletipoformadepagoList.
	 * 
	 * @param detalletipoformadepagoList
	 *            List<Detalletipoformadepago>
	 */
	public void setDetalletipoformadepagoList(List<Detalletipoformadepago> detalletipoformadepagoList) {
		this.detalletipoformadepagoList = detalletipoformadepagoList;
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
	 * Method getTransaccionformadepagoList.
	 * 
	 * @return List<Transaccionformadepago>
	 */
	@XmlTransient
	public List<Transaccionformadepago> getTransaccionformadepagoList() {
		return transaccionformadepagoList;
	}

	/**
	 * Method setTransaccionformadepagoList.
	 * 
	 * @param transaccionformadepagoList
	 *            List<Transaccionformadepago>
	 */
	public void setTransaccionformadepagoList(List<Transaccionformadepago> transaccionformadepagoList) {
		this.transaccionformadepagoList = transaccionformadepagoList;
	}

	/**
	 * Method getMorosidadList.
	 * 
	 * @return List<Morosidad>
	 */
	@XmlTransient
	public List<Morosidad> getMorosidadList() {
		return morosidadList;
	}

	/**
	 * Method setMorosidadList.
	 * 
	 * @param morosidadList
	 *            List<Morosidad>
	 */
	public void setMorosidadList(List<Morosidad> morosidadList) {
		this.morosidadList = morosidadList;
	}
	
	
    public List<Monedadenominacion> getMonedadenominacionList() {
        return monedadenominacionList;
    }

    public void setMonedadenominacionList(List<Monedadenominacion> monedadenominacionList) {
        this.monedadenominacionList = monedadenominacionList;
    }
	

//	/**
//	 * Method getCategorialinearetencionList.
//	 * 
//	 * @return List<Categorialinearetencion>
//	 */
//	@XmlTransient
//	public List<Categorialinearetencion> getCategorialinearetencionList() {
//		return categorialinearetencionList;
//	}
//
//	/**
//	 * Method setCategorialinearetencionList.
//	 * 
//	 * @param categorialinearetencionList
//	 *            List<Categorialinearetencion>
//	 */
//	public void setCategorialinearetencionList(List<Categorialinearetencion> categorialinearetencionList) {
//		this.categorialinearetencionList = categorialinearetencionList;
//	}

	/**
	 * Method getIdContrapartida.
	 * 
	 * @return Formadepago
	 */
	public Formadepago getIdContrapartida() {
		return idContrapartida;
	}

	/**
	 * Method setIdContrapartida.
	 * 
	 * @param idContrapartida
	 *            Formadepago
	 */
	public void setIdContrapartida(Formadepago idContrapartida) {
		this.idContrapartida = idContrapartida;
	}

	/**
	 * Method getPorcentajeimpuestoretencionList.
	 * 
	 * @return List<Porcentajeimpuestoretencion>
	 */
	@XmlTransient
	public List<Porcentajeimpuestoretencion> getPorcentajeimpuestoretencionList() {
		return porcentajeimpuestoretencionList;
	}

	/**
	 * Method setPorcentajeimpuestoretencionList.
	 * 
	 * @param porcentajeimpuestoretencionList
	 *            List<Porcentajeimpuestoretencion>
	 */
	public void setPorcentajeimpuestoretencionList(List<Porcentajeimpuestoretencion> porcentajeimpuestoretencionList) {
		this.porcentajeimpuestoretencionList = porcentajeimpuestoretencionList;
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
		if (!(object instanceof Formadepago)) {
			return false;
		}
		Formadepago other = (Formadepago) object;
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

		sb.append(codigo);
		sb.append(separator);

		sb.append(enclosed);
		sb.append(nombre);
		sb.append(enclosed);
		sb.append(separator);

		sb.append(enclosed);
		sb.append(descripcion);
		sb.append(enclosed);
		sb.append(separator);

		sb.append(prioridadDonacion);
		sb.append(separator);

		sb.append(permiteVuelto);
		sb.append(separator);

		sb.append(montoMinimo);
		sb.append(separator);

		sb.append(montoMaximo);
		sb.append(separator);

		sb.append(fecha == null ? nullStr : simpleDateFormat.format(fecha));
		sb.append(separator);

		sb.append(requiereAutorizacion);
		sb.append(separator);

		sb.append(tipoFormaDePago);
		sb.append(separator);

		if (iconoFormaDePago == null) {
			sb.append(nullStr);
		} else {
			sb.append(enclosed);
			sb.append(iconoFormaDePago);
			sb.append(enclosed);
		}
		sb.append(separator);

		if (tipoTarjeta == null) {
			sb.append(nullStr);
		} else {
			sb.append(enclosed);
			sb.append(tipoTarjeta);
			sb.append(enclosed);
		}
		sb.append(separator);

		sb.append(permiteReverso);
		sb.append(separator);

		sb.append(permiteReasignarCliente);
		sb.append(separator);

		sb.append(estaactivo);
		sb.append(separator);

		if (idContrapartida == null) {
			sb.append(nullStr);
		} else {
			sb.append(idContrapartida.getId());
		}

		sb.append(endStr);

		return sb.toString();
	}

}
