package com.epa.cr.legacy;

import com.becoblohm.cr.interfaces.AbstractJPAController;
import crjpa400.Devolucionremota;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by eve0017280 on 05/04/16.
 */
public class DevolucionRemotaJpaController extends AbstractJPAController {

    /**
     * Field emf.
     */
    private EntityManagerFactory emf = null;
    /**
     * Field entityName.
     */
    private static String entityName = "DevolucionRemota";
    /**
     * Field emf.
     */
    private EntityManager em = null;

    /**
     * Constructor for DevolucionJpaController.
     *
     * @param em EntityManager
     */
    public DevolucionRemotaJpaController(EntityManager em) {
        super(AbstractJPAController.SERVERSOURCE, entityName);
        this.em = em;
    }

    public void save(List<Devolucionremota> remoteRefunds) throws Exception {
        try {
            em.getTransaction().begin();
            for (int i = 0; i < remoteRefunds.size(); i++) {
                Devolucionremota devolucionremota = remoteRefunds.get(i);
                em.persist(devolucionremota);
            }
            em.getTransaction().commit();
        } catch(Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    public List<Devolucionremota> findByTransaction(String tienda, Long caja,
                                                    Long transaccion) throws Exception {
        List<Devolucionremota> resultList = null;
        try {
            TypedQuery<Devolucionremota> query = em
                    .createQuery("SELECT D FROM Devolucionremota d WHERE d.tiendaventa = :TIENDA AND d.cajaventa = :CAJA AND d.transaccionventa = :TRANSACCION", Devolucionremota.class);
            query.setParameter("TIENDA", tienda);
            query.setParameter("CAJA", caja);
            query.setParameter("TRANSACCION", transaccion);
            resultList = query.getResultList();
            //return typesafeAdd(resultList, new ArrayList<Devolucionremota>(), Devolucionremota.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
        return resultList;
    }

    public static <T, C extends Collection<T>> C typesafeAdd(Iterable<?> from,
                                                             C to, Class<T> listClass) {
        for (Object item : from) {
            to.add(listClass.cast(item));
        }
        return to;
    }
}
