package com.epa.cr.legacy;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.becoblohm.cr.models.AnulDev;
import com.becoblohm.cr.models.RefundHistory;
import com.becoblohm.cr.types.CRBigDecimal;

import crjpa400.Historialdevolucion;

/**
 * @author eve0018536
 *
 */
public class HistorialdevolucionJpaController extends AbstractJPAController {

    private static String entityName = "Historialdevolucion";

    private crjpa400.HistorialdevolucionJpaController jpaHistoryController;

    private EntityManagerFactory emf;

    public HistorialdevolucionJpaController(EntityManagerFactory emf) {
        super(AbstractJPAController.SERVERSOURCE, entityName);
        this.emf = emf;
        this.jpaHistoryController = new crjpa400.HistorialdevolucionJpaController(emf);
    }

    public RefundHistory fromJPA(crjpa400.Historialdevolucion jpaHistorialDevolucion) {
        RefundHistory refundHistory = new RefundHistory();
        refundHistory.setId(jpaHistorialDevolucion.getId());
        refundHistory.setIdTransactionRefund(jpaHistorialDevolucion.getIdTrDevolucion());
        refundHistory.setStoreRefund(jpaHistorialDevolucion.getTiendaDevolucion());
        refundHistory.setPosRefund(jpaHistorialDevolucion.getCajaDevolucion());
        refundHistory.setTrRefundNumber(jpaHistorialDevolucion.getTransaccionDevolucion());
        refundHistory.setStoreSale(jpaHistorialDevolucion.getTiendaVenta());
        refundHistory.setPosSale(jpaHistorialDevolucion.getCajaVenta());
        refundHistory.setTrSaleNumber(jpaHistorialDevolucion.getTransaccionVenta());
        refundHistory.setRefundDate(jpaHistorialDevolucion.getFechaDevolucion());
        refundHistory.setRemainingAmount(new CRBigDecimal(jpaHistorialDevolucion.getMontoPendiente()));

        return refundHistory;

    }

    public static Historialdevolucion toJPA(AnulDev refundTransaction) {
        Historialdevolucion historialDev = new Historialdevolucion();
        historialDev.setIdTrDevolucion(new BigInteger(String.valueOf(refundTransaction.getId())));
        historialDev.setTiendaDevolucion(refundTransaction.getStore());
        historialDev.setCajaDevolucion(new BigInteger(String.valueOf(refundTransaction.getPosId())));
        historialDev.setTransaccionDevolucion(new BigInteger(String.valueOf(refundTransaction.getNumber())));
        historialDev.setFechaDevolucion(refundTransaction.getDate());
        historialDev.setTiendaVenta(refundTransaction.getOriginalSale().getStore());
        historialDev.setCajaVenta(new BigInteger(refundTransaction.getOriginalSale().getPosId()));
        historialDev.setTransaccionVenta(new BigInteger(refundTransaction.getOriginalSale().getNumber()));
        historialDev.setFechaDevolucion(refundTransaction.getDate());
        historialDev.setMontoPendiente(refundTransaction.getAmountToRefund().getValue()
                .subtract(refundTransaction.getTotalPay().getValue()));

        return historialDev;

    }

    public RefundHistory findLastRefundHistory(String store, String posId, String tr) {
        RefundHistory history = null;
        EntityManager em = jpaHistoryController.getEntityManager();

        try {

            Query query = em
                    .createQuery("select h from Historialdevolucion h where  h.tiendaVenta = :store and  h.cajaVenta = :posId and h.transaccionVenta = :tr ");

            query.setParameter("store", store);
            query.setParameter("posId", new BigInteger(posId));
            query.setParameter("tr", new BigInteger(tr));

            List<Historialdevolucion> tmp = query.getResultList();

            if (tmp.size() == 0) {
                history = null;
            } else {
                history = fromJPA(tmp.get(tmp.size() - 1));
            }

            em.clear();
        } catch (javax.persistence.NoResultException ex) {
            history = null;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }

        return history;

    }

}
