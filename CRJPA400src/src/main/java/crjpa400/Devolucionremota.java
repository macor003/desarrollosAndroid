package crjpa400;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author eve0017280
 */
@Entity
@Table(name = "DEVOLUCIONREMOTA")
@NamedQueries({
        @NamedQuery(name = "Devolucionremota.findAll", query = "SELECT d FROM Devolucionremota d"),
        @NamedQuery(name = "Devolucionremota.findById", query = "SELECT d FROM Devolucionremota d WHERE d.id = :id"),
        @NamedQuery(name = "Devolucionremota.findByIdTransaccion", query = "SELECT d FROM Devolucionremota d WHERE d.idTransaccion = :idTransaccion"),
        @NamedQuery(name = "Devolucionremota.findByTiendadevolucion", query = "SELECT d FROM Devolucionremota d WHERE d.tiendadevolucion = :tiendadevolucion"),
        @NamedQuery(name = "Devolucionremota.findByTiendaventa", query = "SELECT d FROM Devolucionremota d WHERE d.tiendaventa = :tiendaventa"),
        @NamedQuery(name = "Devolucionremota.findByCajaventa", query = "SELECT d FROM Devolucionremota d WHERE d.cajaventa = :cajaventa"),
        @NamedQuery(name = "Devolucionremota.findByTransaccionventa", query = "SELECT d FROM Devolucionremota d WHERE d.transaccionventa = :transaccionventa"),
        @NamedQuery(name = "Devolucionremota.findByIdArticulo", query = "SELECT d FROM Devolucionremota d WHERE d.idArticulo = :idArticulo"),
        @NamedQuery(name = "Devolucionremota.findByCantidad", query = "SELECT d FROM Devolucionremota d WHERE d.cantidad = :cantidad"),
        @NamedQuery(name = "Devolucionremota.findByFecha", query = "SELECT d FROM Devolucionremota d WHERE d.fecha = :fecha")})
public class Devolucionremota implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "ID_TRANSACCION")
    private BigInteger idTransaccion;
    @Basic(optional = false)
    @Column(name = "TIENDADEVOLUCION")
    private String tiendadevolucion;
    @Basic(optional = false)
    @Column(name = "TIENDAVENTA")
    private String tiendaventa;
    @Basic(optional = false)
    @Column(name = "CAJAVENTA")
    private long cajaventa;
    @Basic(optional = false)
    @Column(name = "TRANSACCIONVENTA")
    private long transaccionventa;
    @Basic(optional = false)
    @Column(name = "ID_ARTICULO")
    private long idArticulo;
    @Basic(optional = false)
    @Column(name = "CANTIDAD")
    private BigDecimal cantidad;
    @Basic(optional = false)
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    public Devolucionremota() {
    }

    public Devolucionremota(Long id) {
        this.id = id;
    }

    public Devolucionremota(Long id, String tiendadevolucion, String tiendaventa, long cajaventa, long transaccionventa, long idArticulo, BigDecimal cantidad, Date fecha) {
        this.id = id;
        this.tiendadevolucion = tiendadevolucion;
        this.tiendaventa = tiendaventa;
        this.cajaventa = cajaventa;
        this.transaccionventa = transaccionventa;
        this.idArticulo = idArticulo;
        this.cantidad = cantidad;
        this.fecha = fecha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigInteger getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(BigInteger idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public String getTiendadevolucion() {
        return tiendadevolucion;
    }

    public void setTiendadevolucion(String tiendadevolucion) {
        this.tiendadevolucion = tiendadevolucion;
    }

    public String getTiendaventa() {
        return tiendaventa;
    }

    public void setTiendaventa(String tiendaventa) {
        this.tiendaventa = tiendaventa;
    }

    public long getCajaventa() {
        return cajaventa;
    }

    public void setCajaventa(long cajaventa) {
        this.cajaventa = cajaventa;
    }

    public long getTransaccionventa() {
        return transaccionventa;
    }

    public void setTransaccionventa(long transaccionventa) {
        this.transaccionventa = transaccionventa;
    }

    public long getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(long idArticulo) {
        this.idArticulo = idArticulo;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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
        if (!(object instanceof Devolucionremota)) {
            return false;
        }
        Devolucionremota other = (Devolucionremota) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Devolucionremota[ id=" + id + " ]";
    }

}
