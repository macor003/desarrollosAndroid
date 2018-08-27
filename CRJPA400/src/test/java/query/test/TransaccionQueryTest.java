/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package query.test;

import static org.eclipse.persistence.config.PersistenceUnitProperties.JDBC_PASSWORD;
import static org.eclipse.persistence.config.PersistenceUnitProperties.JDBC_URL;
import static org.eclipse.persistence.config.PersistenceUnitProperties.JDBC_USER;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.queries.ScrollableCursor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.becoblohm.cr.models.Client;
import com.becoblohm.cr.models.ClientType;
import com.becoblohm.cr.models.CreditsMovement;
import com.becoblohm.cr.models.CreditsMovement400;
import com.becoblohm.cr.models.OrderTransaction;
import com.becoblohm.cr.models.Tax;
import com.becoblohm.cr.models.Transaction;
import com.becoblohm.cr.models.VPosAdditionalData;
import com.epa.cr.legacy.AcreenciaMovimientoJpaController;
import com.epa.cr.legacy.ClienteJpaController;
import com.epa.cr.legacy.FormadepagopuntoagilJpaController;
import com.epa.cr.legacy.MovimientoacreenciaJpaController;
import com.epa.cr.legacy.OrdendeventaTransaccionJpaController;
import com.epa.cr.legacy.TasaimpuestovalorJpaController;

import crjpa400.Sesion;

public class TransaccionQueryTest {

    private long startTime;

    private long endTime;

    private EntityManagerFactory emf;

    @Test
    public void testquery() throws Exception {
        emf = Persistence.createEntityManagerFactory("CR400PU");
        EntityManager em = emf.createEntityManager();
        Query q = em.createNativeQuery("SELECT t.* " + "FROM CR400.TRANSACCION as t "
                + "inner join CR400.sesion as s ON s.ID=t.ID_SESION "
                + "exception join VENTAS.trcmov as tr on tr.TDACMV = 15 and tr.TRSCMV =  t.NUMERO "
                + "WHERE t.TIPO IN ('VENTA','ANULACION', 'DEVOLUCION') and t.ESTADO = 'F' and s.ID_CAJA=2");
        List<crjpa400.Transaccion> trList = q.getResultList();
        for (crjpa400.Transaccion tr : trList) {
            System.out.println(tr.getId());
        }
    }

    @Test
    public void findLastSession() {
        Sesion tmp;
        // Session returnValue;
        try {
            crjpa400.SesionJpaController jpaSesionController = new crjpa400.SesionJpaController(emf);
            EntityManager em = jpaSesionController.getEntityManager();
            String queryString = "SELECT * FROM CR400.SESION AS s WHERE s.id_caja = ?1 AND"
                    + " id=(SELECT MAX(t.id) FROM CR400.SESION AS t WHERE t.id_caja = ?1)";
            Query query = em.createQuery(queryString);
            query.setParameter("1", "2");
            query.setMaxResults(1);
            tmp = (Sesion) query.getSingleResult();
            // returnValue=fromJPA(tmp);
        } catch (Exception ex) {
            ex.printStackTrace();
            tmp = null;
        }
        // return tmp;
    }

    @Test
    public void testquery3() throws Exception {
        String estado = "E";

        crjpa400.TransaccionJpaController jpaTransactionController = new crjpa400.TransaccionJpaController(emf);
        ;
        EntityManager em = jpaTransactionController.getEntityManager();

        Query query = em
                .createQuery("select t from Transaccion t where t.estado = :estado and t.fecha >= :fechaInicial AND t.fecha <= :fechaFinal");

        query.setParameter("fechaInicial", new Date());
        query.setParameter("fechaFinal", new Date());
        query.setParameter("estado", estado.charAt(0));

        ArrayList<Transaction> transactionList = new ArrayList<Transaction>();
        List<crjpa400.Transaccion> transList = query.getResultList();

        for (crjpa400.Transaccion tmp : transList) {
            System.out.println(tmp);
        }

        // OrdendeventaJpaController ordenDeVenta = new
        // OrdendeventaJpaController(emf);
        //
        // ArrayList<String> list = new ArrayList<String>();
        // list.add("P");
        // list.add("N");
        // list.add("T");
        // System.out.println("Buscando pedidos especiales");
        // Vector<Order> vector =
        // ordenDeVenta.findOrdenDeVentaActivaByClientId_Status(new Client("",
        // "17892041", "", "", new Long(-1)), list);
        // for (Order order : vector) {
        // System.out.println("Pedido recuperado:");
        // System.out.println("#"+order.getNumber());
        //
        // System.out.println("Articulos:");
        // for (Article articulo : order.getArticles()) {
        // System.out.println("Cod: "+articulo.getCode());
        // System.out.println("Tipo Descuento:
        // "+articulo.getDiscountType().getDescription());
        // }
        // System.out.println("Total: "+order.getTotalCost());
        // System.out.println("Abonado: "+order.getDiference());
        //
        // }
    }

    @Test
    public void testquery4() throws Exception {

        OrdendeventaTransaccionJpaController ordenDeVenta = new OrdendeventaTransaccionJpaController(emf);

        System.out.println("Buscando ordendeventatransaccion");

        Transaction data = new Transaction();
        data.setNumber("787");
        data.setPosId("10");

        OrderTransaction ordertransaccion = ordenDeVenta.findByTransaction(data, 999l);

        if (ordertransaccion != null) {
            System.out.println("Ordertransaccion encontrado id #" + ordertransaccion.getId());
        }

    }

    @Test
    public void testquery5() throws Exception {

        TasaimpuestovalorJpaController tsiv = new TasaimpuestovalorJpaController(emf);

        Date date = new Date();
        BigDecimal taxRate = new BigDecimal(12.00);
        System.out.println("Buscando en JPA fecha:" + date + " rate: " + taxRate);

        Tax tax = tsiv.findTasaivaValorByDateAndRate(date, taxRate);

        if (tax != null) {
            System.out.println("tax encontrado");
        }
    }

    @Test
    public void testquery6() throws Exception {

        MovimientoacreenciaJpaController jpa = new MovimientoacreenciaJpaController(emf);

        CreditsMovement movement = null;// = jpa.findLastMov("15", "13", 4916,
                                        // 999l);

        if (movement == null) {
            System.out.println("Movimiento no encontrado");
        } else {
            System.out.println("Movimiento encontrado");
        }

    }

    @Test
    public void testquery7() throws Exception {

        AcreenciaMovimientoJpaController jpa = new AcreenciaMovimientoJpaController(emf);

        Collection<CreditsMovement400> movement = jpa.findProcessToAnul("17892041", new Long("78002"));

        if (movement.size() > 0) {
            System.out.println("Movimiento no encontrado");
        } else {
            System.out.println("Movimiento encontrado");
        }

    }

    @Test
    public void testquery8() throws Exception {
        FormadepagopuntoagilJpaController fdppa = new FormadepagopuntoagilJpaController(emf);

        VPosAdditionalData tmp = fdppa.findLastFormadepagopuntoagilByVtid("E015C005");

        if (tmp != null) {
            System.out.println("Registro encontrado");
        } else {
            System.out.println("Registro NO encontrado");
        }

    }

    @Test
    public void testqueryCreateClient() throws Exception {

        ClienteJpaController jpa = new ClienteJpaController(emf);

        Client client = buildClient();

        jpa.createClientNativeQuery(client, "CR400");
    }

    private Client buildClient() {

        HashMap cliente = new HashMap();
        cliente.put("identificador", "V123675289");
        cliente.put("direccion", "tulipan san diego");
        cliente.put("tipo", "5");
        cliente.put("diplomatico", "N");
        cliente.put("actividadEconomica", "-1");
        cliente.put("email", "test@test.com");
        cliente.put("descuentoEmpleado", "N");
        cliente.put("ficha", "4124");
        cliente.put("direccionFiscal", "tulipan san diego");
        cliente.put("identificadorFiscal", "V123675289");
        cliente.put("nombre", "LUIS JESUS RODRIGUEZ QUIÑONES");
        cliente.put("telefono", "04124422494");
        cliente.put("agenteDeRetencion", "N");
        cliente.put("pagaImpuesto", "N");

        Client client = new Client();
        client.setIdNumber((String) cliente.get("identificador"));
        client.setAddress((String) cliente.get("direccion"));
        ClientType clientType = new ClientType();
        if (((String) cliente.get("tipo")) != null) {
            clientType.setId(Integer.parseInt((String) cliente.get("tipo")));

            client.setClientType(clientType);
            if (((String) cliente.get("diplomatico")).equalsIgnoreCase("S")) {
                client.setDiplomatic(true);
            } else {
                client.setDiplomatic(false);
            }
        }

        // if (((String) cliente.get("actividadEconomica")) != null) {
        // client.setEconomicActivity(Integer.parseInt((String)
        // cliente.get("actividadEconomica")));
        // }

        client.setEmail((String) cliente.get("email"));
        if ((String) cliente.get("descuentoEmpleado") != null
                && ((String) cliente.get("descuentoEmpleado")).equalsIgnoreCase("S")) {
            client.setEmployeeDiscount(true);
        } else {
            client.setEmployeeDiscount(false);
        }
        if ((String) cliente.get("ficha") != null) {
            client.setEmployeeId(new Long(((String) cliente.get("ficha"))).longValue());
        }

        client.setFiscalAddress((String) cliente.get("direccionFiscal"));
        client.setFiscalId((String) cliente.get("identificadorFiscal"));
        client.setIsSinc("N");
        client.setName((String) cliente.get("nombre"));
        client.setPhone((String) cliente.get("telefono"));
        client.setRegisterDate(new Date());
        client.setRetentionAgent((String) cliente.get("agenteDeRetencion"));

        if ((String) cliente.get("pagaImpuesto") != null
                && ((String) cliente.get("pagaImpuesto")).equalsIgnoreCase("S")) {
            client.setTaxPayer(true);
        } else {
            client.setTaxPayer(false);
        }

        client.setUpdateDate(new Date());

        return client;
    }

    @Test
    public void testquery9() throws Exception {
        String jpql = "SELECT x FROM Articulo x  " + " join fetch x.idTasaimpuestotipo "
                + " join fetch x.articulounidadventaList  " + " left join fetch x.idLinea "
                + " left join fetch x.idFamilia " + " left join fetch x.exoneradoarticuloList "
                + " WHERE x.ultimasincronizacion >= :fecha and x.ultimasincronizacion <= :fechaAct ORDER BY x.idArticulocategorizado DESC, x.id ASC";

        HashMap<String, Object> properties = new HashMap<String, Object>();
        properties.put(JDBC_URL, "jdbc:as400://10.1.12.70/CR400");
        properties.put(JDBC_USER, "userdb2");
        properties.put(JDBC_PASSWORD, "userdb2");
        properties.put("eclipselink.logging.level", "FINE");

        emf = Persistence.createEntityManagerFactory("CR400PU", properties);

        Query query = emf.createEntityManager().createQuery(jpql);

        int pagesize = 200;

        query.setFirstResult(0);
        query.setMaxResults(pagesize);
        query.setParameter("fecha", new Date());
        query.setParameter("fechaAct", new Date());

        query.setHint("eclipselink.read-only", HintValues.TRUE);
        query.setHint("eclipselink.cursor", HintValues.TRUE);
        query.setHint("eclipselink.cursor.page-size", pagesize);
        query.setHint("eclipselink.cursor.scrollable", HintValues.TRUE);

        ScrollableCursor scrollableCursor = (ScrollableCursor) query.getSingleResult();
        List<Object> rows = null;
        int n = scrollableCursor.size();
        System.out.println(n);
        if (pagesize > n) {
            pagesize = n;
        }
        rows = scrollableCursor.next(pagesize);
    }

    @Before
    public void setUp() throws Exception {
        // add additional set up code here
        // Global.setCurrentSession(new Session());
        // emf = Persistence.createEntityManagerFactory("CR400PU");
        startTime = System.nanoTime();

    }

    /**
     * Perform post-test clean-up.
     * 
     * 
     * 
     * @generatedBy CodePro at 14/04/11 10:19 AM
     * @throws Exception if the clean-up fails for some reason
     */
    @After
    public void tearDown() throws Exception {
        // Add additional tear down code here

        endTime = System.nanoTime();
        double elapsedTime = (endTime - startTime);
        double seconds = elapsedTime / 1000000000.0;
        System.out.println("tiempo de prueba " + seconds);
    }

    /**
     * Launch the test.
     * 
     * @param args the command line arguments
     * 
     * @generatedBy CodePro at 14/04/11 10:19 AM
     */
    public static void main(String[] args) {
        new org.junit.runner.JUnitCore().run(TransaccionQueryTest.class);
    }
}
