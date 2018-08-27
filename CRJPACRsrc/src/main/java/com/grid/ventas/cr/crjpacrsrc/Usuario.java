/*
 * To change this license header, choose License Headers in Project Properties. To change
 * this template file, choose Tools | Templates and open the template in the editor.
 */
package com.grid.ventas.cr.crjpacrsrc;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
 * @author eve0017909
 */
@Entity
@Table(name = "USUARIO")
@XmlRootElement
@NamedQueries({@NamedQuery(name = "Usuario.findAll",
                           query = "SELECT u FROM Usuario u"),
        @NamedQuery(name = "Usuario.findByNumtienda",
                    query = "SELECT u FROM Usuario u WHERE u.usuarioPK.numtienda = :numtienda"),
        @NamedQuery(name = "Usuario.findByNumficha",
                    query = "SELECT u FROM Usuario u WHERE u.usuarioPK.numficha = :numficha"),
        @NamedQuery(name = "Usuario.findByCodigobarra",
                    query = "SELECT u FROM Usuario u WHERE u.codigobarra = :codigobarra"),
        @NamedQuery(name = "Usuario.findByCodperfil",
                    query = "SELECT u FROM Usuario u WHERE u.codperfil = :codperfil"),
        @NamedQuery(name = "Usuario.findByClave",
                    query = "SELECT u FROM Usuario u WHERE u.clave = :clave"),
        @NamedQuery(name = "Usuario.findByNivelauditoria",
                    query = "SELECT u FROM Usuario u WHERE u.nivelauditoria = :nivelauditoria"),
        @NamedQuery(name = "Usuario.findByNombre",
                    query = "SELECT u FROM Usuario u WHERE u.nombre = :nombre"),
        @NamedQuery(name = "Usuario.findByPuedecambiarclave",
                    query = "SELECT u FROM Usuario u WHERE u.puedecambiarclave = :puedecambiarclave"),
        @NamedQuery(name = "Usuario.findByIndicacambiarclave",
                    query = "SELECT u FROM Usuario u WHERE u.indicacambiarclave = :indicacambiarclave"),
        @NamedQuery(name = "Usuario.findByFechacreacion",
                    query = "SELECT u FROM Usuario u WHERE u.fechacreacion = :fechacreacion"),
        @NamedQuery(name = "Usuario.findByUltimocambioclave",
                    query = "SELECT u FROM Usuario u WHERE u.ultimocambioclave = :ultimocambioclave"),
        @NamedQuery(name = "Usuario.findByTiempovigenciaclave",
                    query = "SELECT u FROM Usuario u WHERE u.tiempovigenciaclave = :tiempovigenciaclave"),
        @NamedQuery(name = "Usuario.findByRegvigente",
                    query = "SELECT u FROM Usuario u WHERE u.regvigente = :regvigente"),
        @NamedQuery(name = "Usuario.findByActualizacion",
                    query = "SELECT u FROM Usuario u WHERE u.actualizacion = :actualizacion")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    protected UsuarioPK usuarioPK;

    @Basic(optional = false)
    @Column(name = "CODIGOBARRA")
    private String codigobarra;

    @Basic(optional = false)
    @Column(name = "CODPERFIL")
    private String codperfil;

    @Basic(optional = false)
    @Column(name = "CLAVE")
    private String clave;

    @Basic(optional = false)
    @Column(name = "NIVELAUDITORIA")
    private Character nivelauditoria;

    @Column(name = "NOMBRE")
    private String nombre;

    @Basic(optional = false)
    @Column(name = "PUEDECAMBIARCLAVE")
    private Character puedecambiarclave;

    @Basic(optional = false)
    @Column(name = "INDICACAMBIARCLAVE")
    private Character indicacambiarclave;

    @Basic(optional = false)
    @Column(name = "FECHACREACION")
    @Temporal(TemporalType.DATE)
    private Date fechacreacion;

    @Basic(optional = false)
    @Column(name = "ULTIMOCAMBIOCLAVE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimocambioclave;

    @Basic(optional = false)
    @Column(name = "TIEMPOVIGENCIACLAVE")
    private short tiempovigenciaclave;

    @Basic(optional = false)
    @Column(name = "REGVIGENTE")
    private Character regvigente;

    @Basic(optional = false)
    @Column(name = "ACTUALIZACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date actualizacion;

    @OneToMany(cascade = CascadeType.ALL,
               mappedBy = "usuario",
               fetch = FetchType.LAZY)
    private List<Detalletransaccion> detalletransaccionList;

    public Usuario() {
    }

    public Usuario(UsuarioPK usuarioPK) {
        this.usuarioPK = usuarioPK;
    }

    public Usuario(UsuarioPK usuarioPK, String codigobarra, String codperfil, String clave,
                   Character nivelauditoria, Character puedecambiarclave, Character indicacambiarclave,
                   Date fechacreacion, Date ultimocambioclave, short tiempovigenciaclave, Character regvigente,
                   Date actualizacion) {
        this.usuarioPK = usuarioPK;
        this.codigobarra = codigobarra;
        this.codperfil = codperfil;
        this.clave = clave;
        this.nivelauditoria = nivelauditoria;
        this.puedecambiarclave = puedecambiarclave;
        this.indicacambiarclave = indicacambiarclave;
        this.fechacreacion = fechacreacion;
        this.ultimocambioclave = ultimocambioclave;
        this.tiempovigenciaclave = tiempovigenciaclave;
        this.regvigente = regvigente;
        this.actualizacion = actualizacion;
    }

    public Usuario(short numtienda, String numficha) {
        this.usuarioPK = new UsuarioPK(numtienda, numficha);
    }

    public UsuarioPK getUsuarioPK() {
        return usuarioPK;
    }

    public void setUsuarioPK(UsuarioPK usuarioPK) {
        this.usuarioPK = usuarioPK;
    }

    public String getCodigobarra() {
        return codigobarra;
    }

    public void setCodigobarra(String codigobarra) {
        this.codigobarra = codigobarra;
    }

    public String getCodperfil() {
        return codperfil;
    }

    public void setCodperfil(String codperfil) {
        this.codperfil = codperfil;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Character getNivelauditoria() {
        return nivelauditoria;
    }

    public void setNivelauditoria(Character nivelauditoria) {
        this.nivelauditoria = nivelauditoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Character getPuedecambiarclave() {
        return puedecambiarclave;
    }

    public void setPuedecambiarclave(Character puedecambiarclave) {
        this.puedecambiarclave = puedecambiarclave;
    }

    public Character getIndicacambiarclave() {
        return indicacambiarclave;
    }

    public void setIndicacambiarclave(Character indicacambiarclave) {
        this.indicacambiarclave = indicacambiarclave;
    }

    public Date getFechacreacion() {
        return fechacreacion;
    }

    public void setFechacreacion(Date fechacreacion) {
        this.fechacreacion = fechacreacion;
    }

    public Date getUltimocambioclave() {
        return ultimocambioclave;
    }

    public void setUltimocambioclave(Date ultimocambioclave) {
        this.ultimocambioclave = ultimocambioclave;
    }

    public short getTiempovigenciaclave() {
        return tiempovigenciaclave;
    }

    public void setTiempovigenciaclave(short tiempovigenciaclave) {
        this.tiempovigenciaclave = tiempovigenciaclave;
    }

    public Character getRegvigente() {
        return regvigente;
    }

    public void setRegvigente(Character regvigente) {
        this.regvigente = regvigente;
    }

    public Date getActualizacion() {
        return actualizacion;
    }

    public void setActualizacion(Date actualizacion) {
        this.actualizacion = actualizacion;
    }

    @XmlTransient
    public List<Detalletransaccion> getDetalletransaccionList() {
        return detalletransaccionList;
    }

    public void setDetalletransaccionList(List<Detalletransaccion> detalletransaccionList) {
        this.detalletransaccionList = detalletransaccionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuarioPK != null ? usuarioPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.usuarioPK == null && other.usuarioPK != null)
                || (this.usuarioPK != null && !this.usuarioPK.equals(other.usuarioPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.grid.ventas.cr.crjpacrsrc.Usuario[ usuarioPK=" + usuarioPK + " ]";
    }

}
