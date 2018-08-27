/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package crjpa;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author eve0014321
 */
@Entity
@Table(name = "formadepagotipocliente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Formadepagotipocliente.findAll", query = "SELECT f FROM Formadepagotipocliente f"),
    @NamedQuery(name = "Formadepagotipocliente.findById", query = "SELECT f FROM Formadepagotipocliente f WHERE f.id = :id"),
    @NamedQuery(name = "Formadepagotipocliente.findByEstaactivo", query = "SELECT f FROM Formadepagotipocliente f WHERE f.estaactivo = :estaactivo")})
public class Formadepagotipocliente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "estaactivo")
    private String estaactivo;
    @JoinColumn(name = "id_tipocliente", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Tipocliente idTipocliente;
    @JoinColumn(name = "id_formadepago", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Formadepago idFormadepago;

    public Formadepagotipocliente() {
    }

    public Formadepagotipocliente(Long id) {
        this.id = id;
    }

    public Formadepagotipocliente(Long id, String estaactivo) {
        this.id = id;
        this.estaactivo = estaactivo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEstaactivo() {
        return estaactivo;
    }

    public void setEstaactivo(String estaactivo) {
        this.estaactivo = estaactivo;
    }

    public Tipocliente getIdTipocliente() {
        return idTipocliente;
    }

    public void setIdTipocliente(Tipocliente idTipocliente) {
        this.idTipocliente = idTipocliente;
    }

    public Formadepago getIdFormadepago() {
        return idFormadepago;
    }

    public void setIdFormadepago(Formadepago idFormadepago) {
        this.idFormadepago = idFormadepago;
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
        return "crjpa.Formadepagotipocliente[ id=" + id + " ]";
    }
    
}
