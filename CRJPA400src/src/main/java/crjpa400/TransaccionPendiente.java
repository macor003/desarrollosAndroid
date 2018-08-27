/**
 * 
 */
package crjpa400;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author eve0015942
 * 
 */
@Entity
@Table(name = "TRANSACCIONPENDIENTE")
@XmlRootElement
public class TransaccionPendiente {
	
	@Id
	@OneToOne(optional=false)
    @JoinColumn(name="ID_TRANSACCION", unique=true, nullable=false, updatable=false,  referencedColumnName = "ID")
	private Transaccion idTransaccion;

	/**
	 * @return the idTransaccion
	 */
	public Transaccion getIdTransaccion() {
		return idTransaccion;
	}

	/**
	 * @param idTransaccion
	 *            the idTransaccion to set
	 */
	public void setIdTransaccion(Transaccion idTransaccion) {
		this.idTransaccion = idTransaccion;
	}

}
