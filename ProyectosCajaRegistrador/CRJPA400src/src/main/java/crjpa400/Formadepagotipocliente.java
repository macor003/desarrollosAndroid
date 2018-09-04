/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crjpa400;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import crjpa400.interfaces.CrjpaInterface;

/**
 *
 * @author eve0014321
 */
@Entity
@Table(name = "FORMADEPAGOTIPOCLIENTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Formadepagotipocliente.findAll", query = "SELECT f FROM Formadepagotipocliente f"),
    @NamedQuery(name = "Formadepagotipocliente.findByEstaactivo", query = "SELECT f FROM Formadepagotipocliente f WHERE f.estaactivo = :estaactivo"),
    @NamedQuery(name = "Formadepagotipocliente.findByEstreplica", query = "SELECT f FROM Formadepagotipocliente f WHERE f.estreplica = :estreplica"),
    @NamedQuery(name = "Formadepagotipocliente.findById", query = "SELECT f FROM Formadepagotipocliente f WHERE f.id = :id")})
public class Formadepagotipocliente extends CrjpaInterface implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "ESTAACTIVO")
    private Character estaactivo;
    @Basic(optional = false)
    @Column(name = "ESTREPLICA")
    private Character estreplica;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
	@Column(name = "ULTIMASINCRONIZACION", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar ultimasincronizacion;
    @JoinColumn(name = "ID_FORMADEPAGO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Formadepago idFormadepago;
    @JoinColumn(name = "ID_TIPOCLIENTE", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Tipocliente idTipocliente;

    public Formadepagotipocliente() {
    }

    public Formadepagotipocliente(Long id) {
        this.id = id;
    }

    public Formadepagotipocliente(Long id, Character estaactivo, Character estreplica) {
        this.id = id;
        this.estaactivo = estaactivo;
        this.estreplica = estreplica;
    }

    public Character getEstaactivo() {
        return estaactivo;
    }

    public void setEstaactivo(Character estaactivo) {
        this.estaactivo = estaactivo;
    }

    public Character getEstreplica() {
        return estreplica;
    }

    public void setEstreplica(Character estreplica) {
        this.estreplica = estreplica;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Formadepago getIdFormadepago() {
        return idFormadepago;
    }

    public void setIdFormadepago(Formadepago idFormadepago) {
        this.idFormadepago = idFormadepago;
    }

    public Tipocliente getIdTipocliente() {
        return idTipocliente;
    }

    public void setIdTipocliente(Tipocliente idTipocliente) {
        this.idTipocliente = idTipocliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Formadepagotipocliente)) {
            return false;
        }
        Formadepagotipocliente other = (Formadepagotipocliente) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "crjpa400.Formadepagotipocliente[ id=" + id + " ]";
    }

	public Calendar getUltimasincronizacion() {
		return ultimasincronizacion;
	}

	public void setUltimasincronizacion(Calendar ultimasincronizacion) {
		this.ultimasincronizacion = ultimasincronizacion;
	}
    
}
