/*
 * To change this template, choose Tools | Templates and open the template in the editor.
 */
package crjpa;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "auditoria")
@XmlRootElement
@NamedQueries({@NamedQuery(name = "Auditoria.findAll",
                           query = "SELECT a FROM Auditoria a")})
public class Auditoria implements Serializable {

    private static final long serialVersionUID = 6442996278928346934L;

    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;

    @Column(name = "idproceso")
    private int idProceso;

    @Column(name = "tienda")
    private String tienda;

    @Column(name = "caja")
    private String caja;

    @Column(name = "tipocaja")
    private String tipocaja;

    @Column(name = "proceso")
    private String proceso;

    @Column(name = "fichacajero")
    private int fichacajero;

    @Column(name = "fichaautorizante")
    private int fichaautorizante;

    @Column(name = "fecha")
    private String fecha;

    @Column(name = "hora")
    private String hora;

    @Column(name = "idtabla")
    private Long idTabla;

    @Column(name = "tabla")
    private String tabla;

    @Column(name = "tipotransaccion")
    private String tipotransaccion;

    @Column(name = "tipoautorizacion")
    private String tipoautorizacion;

    @Column(name = "clase")
    private String clase;

    @Lob
    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "tipolog")
    private String tipolog;

    @Column(name = "estasincronizado")
    private String estasincronizado;

    public Auditoria() {

    }

    public Long getId() {
        return id;
    }

    public String getTienda() {
        return tienda;
    }

    public void setTienda(String tienda) {
        this.tienda = tienda;
    }

    public String getCaja() {
        return caja;
    }

    public void setCaja(String caja) {
        this.caja = caja;
    }

    public String getTipocaja() {
        return tipocaja;
    }

    public void setTipocaja(String tipocaja) {
        this.tipocaja = tipocaja;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getIdProceso() {
        return idProceso;
    }

    public void setIdProceso(int idProceso) {
        this.idProceso = idProceso;
    }

    public String getProceso() {
        return proceso;
    }

    public void setProceso(String proceso) {
        this.proceso = proceso;
    }

    public int getFichacajero() {
        return fichacajero;
    }

    public void setFichacajero(int fichacajero) {
        this.fichacajero = fichacajero;
    }

    public int getFichaautorizante() {
        return fichaautorizante;
    }

    public void setFichaautorizante(int fichaautorizante) {
        this.fichaautorizante = fichaautorizante;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Long getIdTabla() {
        return idTabla;
    }

    public void setIdTabla(Long idTabla) {
        this.idTabla = idTabla;
    }

    public String getTabla() {
        return tabla;
    }

    public void setTabla(String tabla) {
        this.tabla = tabla;
    }

    public String getTipotransaccion() {
        return tipotransaccion;
    }

    public void setTipotransaccion(String tipotransaccion) {
        this.tipotransaccion = tipotransaccion;
    }

    public String getTipoautorizacion() {
        return tipoautorizacion;
    }

    public void setTipoautorizacion(String tipoautorizacion) {
        this.tipoautorizacion = tipoautorizacion;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipolog() {
        return tipolog;
    }

    public void setTipolog(String tipolog) {
        this.tipolog = tipolog;
    }

    public String getEstasincronizado() {
        return estasincronizado;
    }

    public void setEstasincronizado(String estasincronizado) {
        this.estasincronizado = estasincronizado;
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
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "crjpa.Auditoria[ id=" + id + " ]";
    }

}
