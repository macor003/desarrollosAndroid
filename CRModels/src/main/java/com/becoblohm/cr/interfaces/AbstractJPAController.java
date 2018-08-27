/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.interfaces;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.metamodel.Type;
import javax.print.attribute.standard.Severity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.becoblohm.cr.models.Transaction.TransactionState;
import com.becoblohm.cr.response.SyncResponse;
import com.becoblohm.cr.sync.models.DataModel;
import com.becoblohm.cr.sync.models.IdContainer;

/**
 */
public abstract class AbstractJPAController {

	/**
	 * Field source.
	 */
	private final int source;

	/**
	 * Field entityName.
	 */
	private final String entityName;

	/**
	 * Field POSSOURCE. Indica si el origen de la sincronizacion es la caja
	 */
	public static int POSSOURCE = 1;

	/**
	 * Field SERVERSOURCE. Indica si el origen de la sincronizacion es el servidor
	 */
	public static int SERVERSOURCE = 2;

	/**
	 * Field posNumber.
	 */
	private String posNumber;

	/**
	 * Field dontMerge.
	 * <p>
	 * Contiene una lista de las entidades que tienes acciones de actualizacion en
	 * base de datos.
	 */
	private static ArrayList<String> dontMerge;

	static {
		dontMerge = new ArrayList<String>();
		dontMerge.add("transaccion");
	}

	/**
	 * Field sdf.
	 */
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss.SSS");

	/**
	 * Field sdf2.
	 */
	public static SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * Field logger.
	 */
	protected static final Logger logger = LoggerFactory.getLogger(AbstractJPAController.class.getName());

	/**
	 * Constructor for AbstractJPAController.
	 * 
	 * @param source
	 *            int
	 * @param entityName
	 *            String
	 */
	public AbstractJPAController(int source, String entityName) {
		this.source = source;
		this.entityName = entityName;
	}

	/**
	 * Method storeSyncEntities.
	 * <p>
	 * Almacena en base de datos el objeto JPA
	 * 
	 * @param dmObject
	 *            Object
	 * @param pos
	 *            String
	 * @param emf
	 *            EntityManagerFactory
	 * @return SyncResponse
	 */
	public SyncResponse storeSyncEntities(Object dmObject, String pos, EntityManagerFactory emf) {
		setPosNumber(pos);
		Object obj;
		IdContainer idValue;
		DataModel dm = null;
		EntityManager em = emf.createEntityManager();

		if (dmObject instanceof DataModel) { // Cuando viene del servidor
			dm = (DataModel) dmObject;
			obj = dm.getEntity();
			idValue = new IdContainer();
			idValue.setClassName(dm.getTableName());
			idValue.setIdValue(dm.getSyncDate());
			IdContainer tmp = getIdValue(obj, em);
			idValue.setUpIdName(tmp.getUpIdName());
			idValue.setUpIdValue(tmp.getUpIdValue());
		} else {
			obj = dmObject;
			idValue = getIdValue(obj, em);
		}
		SyncResponse response = new SyncResponse();
		try {
			if ((dm != null && dm.isCanStore()) || dm == null) {
				em.getTransaction().begin();
				em.persist(obj);
				em.getTransaction().commit();
			}
			response.setLastId(idValue);
			response.setErrorLevel(Severity.REPORT);
			response.setMessage("Registro grabado");
			updateStatus(idValue, em);

		} catch (Exception e1) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			if (!dontMerge.contains(idValue.getClassName().toLowerCase())) {
				try {
					if ((dm != null && dm.isCanStore()) || dm == null) {
						em.getTransaction().begin();
						em.merge(obj);
						em.getTransaction().commit();
					}
					response.setErrorLevel(Severity.REPORT);
					response.setMessage("Registro editado");
					updateStatus(idValue, em);
					response.setLastId(idValue);
				} catch (Exception e2) {
					logger.error("ERROR (" + entityName + ")->" + e2.getMessage(), e2);
					e2.printStackTrace();
					response.setErrorLevel(Severity.ERROR);
					response.setMessage(e2.getMessage());
					if (source == SERVERSOURCE)
						response.setSinchronized(false);
				}
			} else {
				if (em.contains(obj)) {
					response.setLastId(idValue);
					response.setErrorLevel(Severity.REPORT);
					response.setMessage("Registro duplicado");
					updateStatus(idValue, em);
				}
			}
		}
		return response;
	}

	/**
	 * Method updateStatus.
	 * <p>
	 * Invoca el metodo {@link #updateServerStatus(IdContainer, EntityManager)}
	 * 
	 * @param idValue
	 *            IdContainer
	 * @param em
	 *            EntityManager
	 */
	private void updateStatus(IdContainer idValue, EntityManager em) {
		if (source == POSSOURCE) {
			updateServerStatus(idValue, em);
		}

	}

	/**
	 * Method queryUploadSyncEntities.
	 * <p>
	 * Consulta en base de datos los registros que se van a sincronizar hacia el
	 * servidor.
	 * 
	 * @param objectName
	 *            String Nombre de la tabla que se esta sincronizando
	 * @param maxUnits
	 *            Integer Cantidad maxima de registros a sincronizar
	 * @param DAYSTODOWNLOADCLIENTS
	 * @param emf
	 *            EntityManagerFactory
	 * @param syncId
	 *            long
	 * @return List<Object> Registros consultados
	 */
	public List<Object> queryUploadSyncEntities(String objectName, Integer maxUnits, Integer DAYSTODOWNLOADCLIENTS,
			EntityManagerFactory emf, long syncId) throws Exception {
		EntityManager em = emf.createEntityManager();
		List<Object> tmp = null;
		Calendar cal = Calendar.getInstance();
		Query query = null;
		String q = "SELECT x FROM " + entityName + " x  WHERE x.estasincronizado='N'";
		if (entityName.equalsIgnoreCase("transaccion")) {
			q += " AND x.estado='" + TransactionState.COMPLETE.getValue()
					+ "' AND (x.tipo='PAGOCREDITO' OR x.tipo='ANULACREDITO' OR (( x.tipo<>'PAGOCREDITO' AND x.tipo<>'ANULACREDITO' AND SIZE(x.transaccionarticuloList)>0))) AND SIZE(x.transaccionformadepagoList)>0";
		} else if (entityName.equalsIgnoreCase("cliente")) {
			cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) - DAYSTODOWNLOADCLIENTS);
			q = "SELECT c.* from sincronizacionpendientes as s 	inner join cliente as c on c.numero_identificacion_cliente = s.tabla_id where s.id_sincronziacion = "
					+ syncId;
			query = em.createNativeQuery(q, Class.forName("crjpa." + entityName));
		} else if (entityName.equalsIgnoreCase("acreenciamovimiento")) {
			q += " AND x.ipaStatus = 'A'";
		}
		if (query == null) {
			query = em.createQuery(q);
		}

		query.setMaxResults(maxUnits);
		tmp = query.getResultList();
		if (tmp != null && tmp.size() <= 0) {
			tmp = null;
		}
		return tmp;
	}

	/**
	 * Method queryDownloadSyncEntities.
	 * <p>
	 * Consulta en el servidor los registros solicitados por la caja
	 * 
	 * @param objectName
	 *            String
	 * @param maxUnits
	 *            Integer Cantidad maxima de registros
	 * @param lastSync
	 *            String Fehca en que la caja sincronizo por ultima vez la tabla
	 * @param lastId
	 *            IdContainer
	 * @param posId
	 *            String
	 * @param emf
	 *            EntityManagerFactory
	 * @return List<Object> Registros consultados
	 */
	public List<Object> queryDownloadSyncEntities(String objectName, Integer maxUnits, String lastSync,
			IdContainer lastId, String posId, EntityManagerFactory emf) {
		ArrayList<Object> resultList = new ArrayList<Object>();
		try {
			Calendar cal = bigdecimalToCalendar(lastSync);
			String fecha = sdf2.format(((Calendar) cal.clone()).getTime());
			fecha = fecha + "." + lastSync.substring(15, lastSync.length());
			EntityManager em = emf.createEntityManager();
			String q;
			if (fecha.startsWith("1970")) {
				q = "SELECT * FROM " + entityName + " x  WHERE x.ultimasincronizacion>TIMESTAMP('" + fecha
						+ "') ORDER BY x.ultimasincronizacion ASC FETCH FIRST 1 ROWS ONLY";
			} else {
				q = "SELECT * FROM " + entityName + " x  WHERE x.ultimasincronizacion>=TIMESTAMP('" + fecha
						+ "') ORDER BY x.ultimasincronizacion ASC FETCH FIRST " + maxUnits + " ROWS ONLY";
			}
			Query query = em.createNativeQuery(q, Class.forName("crjpa400." + entityName));
			List<Object> tmp = query.getResultList();
			em.close();
			for (int i = 0; (i < tmp.size()); i++) {
				Object obj = tmp.get(i);
				resultList.add(obj);
			}
		} catch (Exception ex) {
			logger.error("(" + entityName + ")->" + ex.getMessage(), ex);
		}
		return resultList;
	}

	/**
	 * Method getIdValue.
	 * <p>
	 * Se encarga de obtener del objeto el valor de su clave primaria
	 * 
	 * @param obj
	 *            Objeto JPA al que se le va a buscar la clave primaria
	 * @param em
	 *            EntityManager
	 * @return IdContainer Objeto que contiene la informacion de la clave primaria
	 * @see com.becoblohm.cr.sync.models.IdContainer
	 */
	private IdContainer getIdValue(Object obj, EntityManager em) {
		IdContainer value = new IdContainer();
		try {
			value.setClassName(em.getMetamodel().entity(obj.getClass()).getName());
			String idFieldName = "";
			if (source != POSSOURCE) {// de subida
				Type<?> type = null;
				type = em.getMetamodel().entity(obj.getClass()).getIdType();
				idFieldName = em.getMetamodel().entity(obj.getClass()).getId(type.getJavaType()).getName();
				Field idField;
				idField = obj.getClass().getDeclaredField(idFieldName);
				idField.setAccessible(true);
				value.setIdValue(idField.get(obj));
			} else {// de bajada
				Type<?> type = em.getMetamodel().entity(obj.getClass()).getIdType();
				String upIdName = em.getMetamodel().entity(obj.getClass()).getId(type.getJavaType()).getName();
				value.setUpIdName(upIdName);

				Field idField;
				idField = obj.getClass().getDeclaredField(value.getUpIdName());
				idField.setAccessible(true);
				value.setUpIdValue(idField.get(obj));
			}
		} catch (SecurityException e) {
			logger.error("(" + value.getClassName() + ")->" + e.getMessage(), e);
		} catch (NoSuchFieldException e) {
			logger.error("(" + value.getClassName() + ")->" + e.getMessage(), e);
		} catch (IllegalArgumentException e) {
			logger.error("(" + value.getClassName() + ")->" + e.getMessage(), e);
		} catch (IllegalAccessException e) {
			logger.error("(" + value.getClassName() + ")->" + e.getMessage(), e);
		}
		return value;
	}

	/**
	 * Method updatePosStatus.
	 * <p>
	 * Solo de subida. Actualiza el campo estaSincronizado del registro que se ha
	 * almacenado en el servidor.
	 * 
	 * @param idValue
	 *            IdContainer Objeto que contiene la clave primaria del registro que
	 *            se esta sincronizando
	 * @param emf
	 *            EntityManagerFactory
	 * @param syncId
	 *            long Id de la tabla sincronizacion
	 */
	public synchronized void updatePosStatus(Object idValue, EntityManagerFactory emf, long syncId, String entityName)
			throws ClassNotFoundException, Exception {
		EntityManager tempEm = emf.createEntityManager();
		tempEm.getTransaction().begin();
		Query q = null;
		Query q2 = null;
		if (entityName.equalsIgnoreCase("sincronizacionpendientes")) {
			HashMap<String, Object> temp = (HashMap<String, Object>) idValue;
			Iterator<Entry<String, Object>> idsIterator = temp.entrySet().iterator();
			Entry<String, Object> idEntry = idsIterator.next();
			String updateQuery = "UPDATE " + entityName + " x SET x.estasincronizado='S'";
			String whereCondition = " WHERE x." + idEntry.getKey() + "=:" + idEntry.getKey();
			while (idsIterator.hasNext()) {
				idEntry = idsIterator.next();
				whereCondition += " AND x." + idEntry.getKey() + "=:" + idEntry.getKey();
			}

			q = tempEm.createQuery(updateQuery + whereCondition);
			idsIterator = temp.entrySet().iterator();
			while (idsIterator.hasNext()) {
				idEntry = idsIterator.next();
				updateQuery += " AND x." + idEntry.getKey() + "=:" + idEntry.getKey();
				q.setParameter(idEntry.getKey(), idEntry.getValue());
			}

			q2 = tempEm.createNativeQuery("DELETE FROM sincronizacionpendientes " + whereCondition);

		} else {
			Type<?> type = null;
			type = tempEm.getMetamodel().entity(Class.forName("crjpa." + entityName)).getIdType();
			String tmpIdFieldName = tempEm.getMetamodel().entity(Class.forName("crjpa." + entityName))
					.getId(type.getJavaType()).getName();
			q = tempEm.createQuery(
					"UPDATE " + entityName + " x SET x.estasincronizado='S' WHERE x." + tmpIdFieldName + "=:id");
			q.setParameter("id", idValue);
			q2 = tempEm.createNativeQuery("DELETE FROM sincronizacionpendientes WHERE tabla_id='" + idValue
					+ "' AND id_sincronziacion = " + syncId);
		}
		q.executeUpdate();
		q2.executeUpdate();
		tempEm.getTransaction().commit();
	}

	/**
	 * Method updateServerStatus.
	 * <p>
	 * Actualiza la fecha de ultima sincronizacion en la tabla CRPOS.sincronizacion
	 * 
	 * @param idValue
	 *            IdContainer
	 * @param em
	 *            EntityManager
	 */
	private void updateServerStatus(IdContainer idValue, EntityManager em) {
		em.getTransaction().begin();
		Calendar tmp = ((Calendar) idValue.getIdValue());
		String fecha = sdf.format(tmp.getTime());
		String statement = "UPDATE sincronizacion SET ultimasincronizacion=" + fecha
				+ ", estasincronizado = 'N' WHERE nombre='" + idValue.getClassName() + "'";

		try {
			Query q = em.createNativeQuery(statement);
			q.executeUpdate();
			em.getTransaction().commit();
		} catch (Exception e) {
			logger.error("(" + idValue.getClassName() + ")->" + e.getMessage(), e);
		}
	}

	/**
	 * Method bigdecimalToCalendar.
	 * <p>
	 * 
	 * @param date
	 *            String La fecha a convertir
	 * @return Calendar
	 */
	private Calendar bigdecimalToCalendar(String date) {

		Calendar calendar = Calendar.getInstance();
		String dateStr = date;
		String year = dateStr.substring(0, 4);
		String month = dateStr.substring(4, 6);
		String day = dateStr.substring(6, 8);
		String hour = dateStr.substring(8, 10);
		String minute = dateStr.substring(10, 12);
		String second = dateStr.substring(12, 14);
		String milli = dateStr.substring(15, dateStr.length());

		calendar.set(Calendar.YEAR, Integer.parseInt(year));
		calendar.set(Calendar.MONTH, Integer.parseInt(month) - 1);
		calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(day));
		calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hour));
		calendar.set(Calendar.MINUTE, Integer.parseInt(minute));
		calendar.set(Calendar.SECOND, Integer.parseInt(second));

		return calendar;
	}

	/**
	 * Method setPosNumber.
	 * 
	 * @param posNumber
	 *            String
	 */
	public void setPosNumber(String posNumber) {
		this.posNumber = posNumber;
	}

	/**
	 * Method getPosNumber.
	 * 
	 * @return String
	 */
	public String getPosNumber() {
		return posNumber;
	}

	/**
	 * Method getEntityName.
	 * 
	 * @return String
	 */
	public String getEntityName() {
		return this.entityName;
	}

}
