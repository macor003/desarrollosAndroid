package com.epa.cr.legacy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by epauser on 04/05/16.
 */

import com.becoblohm.cr.models.Order;
import com.becoblohm.cr.models.Transaction;

import crjpa400.Articulo;
import crjpa400.Convertidortipodescto;
import crjpa400.Ordendeventa;
import crjpa400.Tipodescuento;
import crjpa400.Transaccion;

/**
 * Created by epauser on 04/05/16.
 */
public class CacheEPA {

	private static final Logger logger = LoggerFactory.getLogger(CacheEPA.class);

	public static HashMap<String, List<Order>> ordenDeVentaActivaByClient = new HashMap<String, List<Order>>(1000);
	public static HashMap<String, Transaction> transacciones = new HashMap<String, Transaction>(5000);
	public static HashMap<Short, List<Convertidortipodescto>> convertidortipodesctos = new HashMap<Short, List<Convertidortipodescto>>(
			16);
	public static HashMap<Long, Tipodescuento> tiposDescuentos = new HashMap<Long, Tipodescuento>(16);

	public static List<Order> get(String s) {
		return ordenDeVentaActivaByClient.get(s);
	}

	public static void put(String s, List<Order> orders) {
		ordenDeVentaActivaByClient.put(s, orders);
	}

	// @Scheduled(fixedRate = 5000)
	public static void reset() {
		ordenDeVentaActivaByClient = new HashMap<String, List<Order>>(1000);
		logger.info("Borrando cache");
	}

	public CacheEPA(EntityManagerFactory entityManagerFactory, Long getIdExoneradotipoNull) {
		// cachingOrders(entityManagerFactory, getIdExoneradotipoNull);
		// cachingTrans(entityManagerFactory, getIdExoneradotipoNull);
		cachingArticulo(entityManagerFactory);
		cachingConvertidortipodescto(entityManagerFactory, getIdExoneradotipoNull);
		cachingTipodescuento(entityManagerFactory, getIdExoneradotipoNull);
	}

	private void cachingArticulo(EntityManagerFactory entityManagerFactory) {
		List result;
		StringBuilder idToFind = new StringBuilder("(");
		logger.info("Iniciando cache Articulo");
		long l = System.currentTimeMillis();
		EntityManager em = entityManagerFactory.createEntityManager();

		try {
			Query query = em
					// .createNativeQuery("SELECT * from CR400.ARTICULO a fetch first 5000 rows
					// only", Articulo.class);
					.createNativeQuery(
							"select count(*) as c, ta.id_articulo from CR400.TRANSACCIONARTICULO ta group by ta.id_articulo order by c desc limit 50");
			result = query.getResultList();
			if (result.size() > 0) {
				for (Object o : result) {
					Object id = ((Object[]) o)[1];
					idToFind.append(id).append(", ");
				}
				idToFind.delete(idToFind.length() - 2, idToFind.length()).append(")");
				String idsToFind = idToFind.toString();
				logger.info("Articulos en cache: " + idsToFind);
				Query nativeQuery = em.createNativeQuery("SELECT * from CR400.ARTICULO a where a.id in " + idsToFind,
						Articulo.class);
				List<Articulo> resultList = nativeQuery.getResultList();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (em != null && em.isOpen()) {
				em.close();
			}
		}
		l = System.currentTimeMillis() - l;
		logger.info("Finalizando cache Articulo en " + l + " ms.");

	}

	private void cachingTipodescuento(EntityManagerFactory entityManagerFactory, Long getIdExoneradotipoNull) {
		logger.info("Iniciando cache Tipodescuento");
		long l = System.currentTimeMillis();
		List<Tipodescuento> result = new ArrayList<Tipodescuento>();
		EntityManager em = entityManagerFactory.createEntityManager();

		try {
			Query query = em.createNativeQuery("SELECT * from CR400.TIPODESCUENTO", Tipodescuento.class);
			// query.setParameter("id", id);
			// query.setMaxResults(1);
			result = query.getResultList();

			if (result != null) {
				TransaccionJpaController transaccionJpaController = new TransaccionJpaController(entityManagerFactory);

				for (int i = 0; i < result.size(); i++) {
					Tipodescuento tipodescuento = result.get(i);
					tiposDescuentos.put(tipodescuento.getId(), tipodescuento);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (em != null && em.isOpen()) {
				em.close();
			}
			l = System.currentTimeMillis() - l;
			logger.info("Finalizando cache Tipodescuento con " + tiposDescuentos.size() + " en " + l + " ms.");
		}
	}

	private void cachingConvertidortipodescto(EntityManagerFactory entityManagerFactory, Long getIdExoneradotipoNull) {
		logger.info("Iniciando cache Convertidortipodescto");
		long l = System.currentTimeMillis();
		List<Convertidortipodescto> result = new ArrayList<Convertidortipodescto>();
		EntityManager em = entityManagerFactory.createEntityManager();

		try {
			Query query = em.createNativeQuery("SELECT * from CR400.CONVERTIDORTIPODESCTO",
					Convertidortipodescto.class);
			// query.setParameter("id", id);
			// query.setMaxResults(1);
			result = query.getResultList();
			if (result != null) {
				for (Convertidortipodescto convertidortipodescto : result) {
					short idTrdmov = convertidortipodescto.getIdTrdmov();
					List<Convertidortipodescto> convertidortipodesctoList = convertidortipodesctos.get(idTrdmov);
					if (convertidortipodesctoList != null) {
						ArrayList<Convertidortipodescto> aux = new ArrayList<Convertidortipodescto>(
								convertidortipodesctoList);
						aux.add(convertidortipodescto);
						convertidortipodesctos.put(idTrdmov, aux);
					} else {
						convertidortipodesctos.put(idTrdmov, Arrays.asList(convertidortipodescto));
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (em != null && em.isOpen()) {
				em.close();
			}
			l = System.currentTimeMillis() - l;
			logger.info("Finalizando cache Convertidortipodescto con " + convertidortipodesctos.size() + " en " + l
					+ " ms.");
		}
	}

	private void cachingOrders(EntityManagerFactory entityManagerFactory, Long getIdExoneradotipoNull) {
		EntityManager em = entityManagerFactory.createEntityManager();
		try {
			System.out.println("Iniciando cache");
			Query query = em.createNativeQuery(
					"select * from cr400.ordendeventa where fecha between  current date - 1 day and current date",
					Ordendeventa.class);
			// Query query = em.createNativeQuery("select * from cr400.ordendeventa",
			// Ordendeventa.class);
			List<Ordendeventa> resultList = query.getResultList();

			if (resultList != null) {
				OrdendeventaJpaController ordendeventaJpaController = new OrdendeventaJpaController(
						entityManagerFactory);

				// List<Order> orders = new ArrayList<Order>(resultList.size());
				for (Ordendeventa order : resultList) {
					Order order1 = ordendeventaJpaController.fromJpa(order, getIdExoneradotipoNull);
					String numeroIdentificacionCliente = order.getNumeroIdentificacionCliente()
							.getNumeroIdentificacionCliente().trim();
					List<Order> orders = ordenDeVentaActivaByClient.get(numeroIdentificacionCliente);
					if (orders != null) {
						ArrayList<Order> aux = new ArrayList<Order>(orders);
						aux.add(order1);
						ordenDeVentaActivaByClient.put(numeroIdentificacionCliente, aux);
					} else {
						ordenDeVentaActivaByClient.put(numeroIdentificacionCliente, Arrays.asList(order1));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (em.isOpen()) {
				em.close();
			}
		}
	}

	private void cachingTrans(EntityManagerFactory entityManagerFactory, Long getIdExoneradotipoNull) {
		EntityManager em;
		System.out.println("ordenDeVentaActivaByClient = " + ordenDeVentaActivaByClient.size());

		em = entityManagerFactory.createEntityManager();
		try {

			System.out.println("Iniciando cache");
			Query query = em.createNativeQuery(
					"select * from cr400.transaccion where fecha >  CURRENT_TIMESTAMP - 5 MINUTE ", Transaccion.class);
			// Query query = em.createNativeQuery("select * from cr400.ordendeventa",
			// Ordendeventa.class);
			List<Transaccion> resultList = query.getResultList();

			if (resultList != null) {
				TransaccionJpaController transaccionJpaController = new TransaccionJpaController(entityManagerFactory);

				for (int i = 0; i < resultList.size(); i++) {
					Transaccion transaccion = resultList.get(i);
					Transaction transaction = transaccionJpaController.fromJPA(transaccion, getIdExoneradotipoNull);
					String key = transaction.getPosId().trim() + transaction.getNumber();
					transacciones.put(key, transaction);
				}
			}
			/*
			 * EntityManager em = jpaTransactionController.getEntityManager(); try {
			 * 
			 * Query query = em
			 * .createQuery("select t from Transaccion t where t.idSesion.idCaja.id = :posId and t.numero = :numero and t.estado = '"
			 * + TransactionState.COMPLETE.getValue() + " ' ");
			 * 
			 * query.setParameter("posId", new Long(posId)); query.setParameter("numero",
			 * new Long(numero));
			 * 
			 * query.setMaxResults(1); Transaccion tmp = (Transaccion)
			 * query.getSingleResult(); result = fromJPA(tmp, getIdExoneradotipoNull);
			 * em.clear(); } catch (javax.persistence.NoResultException ex) { result = null;
			 * } finally { if (em != null && em.isOpen()) { em.close(); } }
			 */
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (em.isOpen()) {
				em.close();
			}
		}
		System.out.println("transacciones = " + transacciones.size());
	}

	public static void putTransaccion(String key, Transaction transaccion) {
		transacciones.put(key, transaccion);
	}

	public static Transaction getTransaccion(String key) {
		return transacciones.get(key);
	}

	public static List<Convertidortipodescto> getConvertidortipodescto(Short key) {
		return convertidortipodesctos.get(key);
	}

	public static Tipodescuento getTiposDescuentos(Long key) {
		return tiposDescuentos.get(key);
	}
}
