/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package query.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.eclipse.persistence.config.PersistenceUnitProperties;
import org.eclipse.persistence.config.SessionCustomizer;
import org.eclipse.persistence.exceptions.DescriptorException;
import org.eclipse.persistence.sessions.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.becoblohm.cr.models.Transaction.SourceTransactionType;
import com.becoblohm.cr.models.Transaction.TransactionState;

public class Pruebas {

	private EntityManagerFactory emf;
	private EntityManager em;

	@Test
	public void test1() throws Exception {
		try {
			HashMap<String, Object> properties = new HashMap<String, Object>();
			properties.put(PersistenceUnitProperties.SESSION_CUSTOMIZER, EnableIntegrityChecker.class.getName());

			EntityManagerFactory emf = Persistence.createEntityManagerFactory("CRJPAPU", properties);

			EntityManager em = emf.createEntityManager();
		} catch (javax.persistence.PersistenceException ex) {
			if (ex.getCause() instanceof DescriptorException) {
				// System.err.println(((DescriptorException)ex.getCause()).getMessage());
			} else {
				ex.printStackTrace();
			}
		}
	}

	@Test
	public void test2() throws Exception {

		long idSession = 150080000000000001L;
		String q = "select (select COALESCE(SUM(monto_moneda_local),0) as pos from "
				+ "CRPOS.transaccion as TR ,CRPOS.transaccionformadepago as TRF "
				+ "where TR.id = TRF.id_transaccion and TRF.id_formadepago=1 and id_sesion="
				+ idSession
				+ " and fecha>(select MAX(fecha) from CRPOS.entrega where id_sesion='"
				+ idSession
				+ "')"
				+ " and estado='"
				+ TransactionState.COMPLETE.getValue()
				+ "' and tipo IN ('"
				+ SourceTransactionType.Sale.getValue()
				+ "','"
				+ SourceTransactionType.CreditsEpaPay.getValue()
				+ "')),"
				+ "(select COALESCE(SUM(monto_moneda_local),0) as neg from "
				+ " CRPOS.transaccion as TR ,CRPOS.transaccionformadepago as TRF "
				+ "where TR.id = TRF.id_transaccion and TRF.id_formadepago=1 and "
				+ "id_sesion="
				+ idSession
				+ " and estado='"
				+ TransactionState.COMPLETE.getValue()
				+ "' and tipo IN ('"
				+ SourceTransactionType.Cancellation.getValue()
				+ "','"
				+ SourceTransactionType.CreditsEpaCancellation.getValue()
				+ "','"
				+ SourceTransactionType.CancellationOrder.getValue()
				+ "'))"
				+ "from CRPOS.sesion where id="
				+ idSession;
		// Query query = em.createQuery(q3,Long.class);
		try {
			Query query = em.createNativeQuery(q);
			// query.setParameter("sesion", sesion);
			Object[] tmp = (Object[]) query.getSingleResult();
			System.out.println(tmp[0]);
			System.out.println(tmp[1]);
			em.clear();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		// return trList;
	}

	@Test
	public void findByAuditRoll() {
		long idRollo = 150080000000000001L;
		String q = "SELECT MAX(id), MIN(id), tipo FROM transaccion t " + "WHERE t.id_rolloauditoria = " + idRollo
				+ " AND t.estado = '" + TransactionState.COMPLETE.getValue() + "' GROUP BY tipo";

		Map<String, ArrayList<Long>> resultList = new HashMap<String, ArrayList<Long>>();
		ArrayList<Long> values;
		try {
			// EntityManager em = emf.createEntityManager();
			// Query query =
			// em.createQuery("SELECT t FROM Transaccion t WHERE t.idRolloauditoria.id = :rollo and t.estado = '"+TransactionState.COMPLETE.getValue()+"'");
			// Query query = em.createQuery(q);
			// query.setParameter("rollo", idPaperRoll);
			// List<Transaccion>tmp= query.getResultList();
			// for(Transaccion transTmp:tmp){
			// trList.add(fromJPA(transTmp));
			// }

			Query query = em.createNativeQuery(q);
			// Object[] tmp= (Object[])query.getSingleResult();
			List<Object[]> tmp = query.getResultList();
			for (Object[] transTmp : tmp) {
				values = new ArrayList<Long>();
				values.add((Long) transTmp[1]);
				values.add((Long) transTmp[0]);
				resultList.put(transTmp[2].toString(), values);
			}
			for (String key : resultList.keySet()) {
				System.out.println(key);
				System.out.println(resultList.get(key).get(0));
				System.out.println(resultList.get(key).get(1));
			}
			System.out.println("listo");
			em.clear();

		} finally {

		}
	}

	public static class EnableIntegrityChecker implements SessionCustomizer {

		public void customize(Session session) throws Exception {
			session.getIntegrityChecker().checkDatabase();
			session.getIntegrityChecker().setShouldCatchExceptions(false);
			session.getIntegrityChecker().setShouldCheckInstantiationPolicy(false);
		}
	}

	@Before
	public void setUp() throws Exception {
		emf = Persistence.createEntityManagerFactory("CRJPAPU");

		em = emf.createEntityManager();

	}

	/**
	 * Perform post-test clean-up.
	 * 
	 * 
	 * 
	 * @generatedBy CodePro at 14/04/11 10:19 AM
	 * @throws Exception
	 *             if the clean-up fails for some reason
	 */
	@After
	public void tearDown() throws Exception {

	}

}
