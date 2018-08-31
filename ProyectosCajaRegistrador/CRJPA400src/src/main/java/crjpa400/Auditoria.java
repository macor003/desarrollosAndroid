/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crjpa400;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "AUDITORIA")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Auditoria.findAll", query = "SELECT a FROM Auditoria a") })
public class Auditoria implements Serializable {
	/**
	 * Field serialVersionUID. (value is 1)
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Field id.
	 */
	@Id
	@Basic(optional = false)
	@Column(name = "ID")
	private Long id;

	@Column(name = "IDPROCESO")
	private int idProceso;

	@Column(name = "TIENDA", length = 10)
	private String tienda;

	@Column(name = "CAJA", length = 10)
	private String caja;

	@Column(name = "TIPOCAJA", length = 30)
	private String tipocaja;

	@Column(name = "PROCESO", length = 254)
	private String proceso;

	@Column(name = "FICHACAJERO")
	private int fichacajero;

	@Column(name = "FICHAAUTORIZANTE")
	private int fichaautorizante;

	@Column(name = "FECHA", length = 254)
	private String fecha;

	@Column(name = "HORA", length = 254)
	private String hora;

	@Column(name = "IDTABLA")
	private Long idTabla;

	@Column(name = "TABLA", length = 254)
	private String tabla;

	@Column(name = "TIPOTRANSACCION", length = 254)
	private String tipotransaccion;

	@Column(name = "TIPOAUTORIZACION", length = 254)
	private String tipoautorizacion;

	@Column(name = "CLASE", length = 254)
	private String clase;

	@Column(name = "DESCRIPCION", length = 2000)
	private String descripcion;

	@Column(name = "TIPOLOG")
	private String tipolog;

	public Auditoria() {
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the idProceso
	 */
	public int getIdProceso() {
		return idProceso;
	}

	/**
	 * @param idProceso
	 *            the idProceso to set
	 */
	public void setIdProceso(int idProceso) {
		this.idProceso = idProceso;
	}

	/**
	 * @return the tienda
	 */
	public String getTienda() {
		return tienda;
	}

	/**
	 * @param tienda
	 *            the tienda to set
	 */
	public void setTienda(String tienda) {
		this.tienda = tienda;
	}

	/**
	 * @return the caja
	 */
	public String getCaja() {
		return caja;
	}

	/**
	 * @param caja
	 *            the caja to set
	 */
	public void setCaja(String caja) {
		this.caja = caja;
	}

	/**
	 * @return the tipocaja
	 */
	public String getTipocaja() {
		return tipocaja;
	}

	/**
	 * @param tipocaja
	 *            the tipocaja to set
	 */
	public void setTipocaja(String tipocaja) {
		this.tipocaja = tipocaja;
	}

	/**
	 * @return the proceso
	 */
	public String getProceso() {
		return proceso;
	}

	/**
	 * @param proceso
	 *            the proceso to set
	 */
	public void setProceso(String proceso) {
		this.proceso = proceso;
	}

	/**
	 * @return the fichacajero
	 */
	public int getFichacajero() {
		return fichacajero;
	}

	/**
	 * @param fichacajero
	 *            the fichacajero to set
	 */
	public void setFichacajero(int fichacajero) {
		this.fichacajero = fichacajero;
	}

	/**
	 * @return the fichaautorizante
	 */
	public int getFichaautorizante() {
		return fichaautorizante;
	}

	/**
	 * @param fichaautorizante
	 *            the fichaautorizante to set
	 */
	public void setFichaautorizante(int fichaautorizante) {
		this.fichaautorizante = fichaautorizante;
	}

	/**
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * @param fecha
	 *            the fecha to set
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return the hora
	 */
	public String getHora() {
		return hora;
	}

	/**
	 * @param hora
	 *            the hora to set
	 */
	public void setHora(String hora) {
		this.hora = hora;
	}

	/**
	 * @return the idTabla
	 */
	public Long getIdTabla() {
		return idTabla;
	}

	/**
	 * @param idTabla
	 *            the idTabla to set
	 */
	public void setIdTabla(Long idTabla) {
		this.idTabla = idTabla;
	}

	/**
	 * @return the tabla
	 */
	public String getTabla() {
		return tabla;
	}

	/**
	 * @param tabla
	 *            the tabla to set
	 */
	public void setTabla(String tabla) {
		this.tabla = tabla;
	}

	/**
	 * @return the tipotransaccion
	 */
	public String getTipotransaccion() {
		return tipotransaccion;
	}

	/**
	 * @param tipotransaccion
	 *            the tipotransaccion to set
	 */
	public void setTipotransaccion(String tipotransaccion) {
		this.tipotransaccion = tipotransaccion;
	}

	/**
	 * @return the tipoautorizacion
	 */
	public String getTipoautorizacion() {
		return tipoautorizacion;
	}

	/**
	 * @param tipoautorizacion
	 *            the tipoautorizacion to set
	 */
	public void setTipoautorizacion(String tipoautorizacion) {
		this.tipoautorizacion = tipoautorizacion;
	}

	/**
	 * @return the clase
	 */
	public String getClase() {
		return clase;
	}

	/**
	 * @param clase
	 *            the clase to set
	 */
	public void setClase(String clase) {
		this.clase = clase;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion
	 *            the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the tipolog
	 */
	public String getTipolog() {
		return tipolog;
	}

	/**
	 * @param tipolog
	 *            the tipolog to set
	 */
	public void setTipolog(String tipolog) {
		this.tipolog = tipolog;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Auditoria)) {
			return false;
		}
		Auditoria other = (Auditoria) object;
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
		return "crjpa400.Auditoria[ id=" + id + " ]";
	}

}
