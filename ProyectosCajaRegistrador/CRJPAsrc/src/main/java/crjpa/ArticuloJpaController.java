/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crjpa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import crjpa.exceptions.IllegalOrphanException;
import crjpa.exceptions.NonexistentEntityException;
import crjpa.exceptions.PreexistingEntityException;

/**
 * 
 * @author eve0014321
 * @version $Revision: 1.0 $
 */
public class ArticuloJpaController implements Serializable {

	/**
	 * Constructor for ArticuloJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public ArticuloJpaController(EntityManagerFactory emf) {
		this.emf = emf;
	}

	/**
	 * Field emf.
	 */
	private EntityManagerFactory emf = null;

	/**
	 * Method getEntityManager.
	 * 
	 * @return EntityManager
	 */
	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	/**
	 * Method create.
	 * 
	 * @param articulo
	 *            Articulo
	 * @throws PreexistingEntityException
	 * @throws Exception
	 */
	public void create(Articulo articulo) throws PreexistingEntityException, Exception {
		if (articulo.getServicioList() == null) {
			articulo.setServicioList(new ArrayList<Servicio>());
		}
		if (articulo.getExoneradotipoList() == null) {
			articulo.setExoneradotipoList(new ArrayList<Exoneradotipo>());
		}
		if (articulo.getArticuloList() == null) {
			articulo.setArticuloList(new ArrayList<Articulo>());
		}
		if (articulo.getPromocionarticuloList() == null) {
			articulo.setPromocionarticuloList(new ArrayList<Promocionarticulo>());
		}
		if (articulo.getTransaccionarticuloList() == null) {
			articulo.setTransaccionarticuloList(new ArrayList<Transaccionarticulo>());
		}
		if (articulo.getArticulocodigoexternoList() == null) {
			articulo.setArticulocodigoexternoList(new ArrayList<Articulocodigoexterno>());
		}
		if (articulo.getArticulounidadventaList() == null) {
			articulo.setArticulounidadventaList(new ArrayList<Articulounidadventa>());
		}
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Familia idFamilia = articulo.getIdFamilia();
			if (idFamilia != null) {
				idFamilia = em.getReference(idFamilia.getClass(), idFamilia.getId());
				articulo.setIdFamilia(idFamilia);
			}
			Articulo idArticulocategorizado = articulo.getIdArticulocategorizado();
			if (idArticulocategorizado != null) {
				idArticulocategorizado = em.getReference(idArticulocategorizado.getClass(),
						idArticulocategorizado.getId());
				articulo.setIdArticulocategorizado(idArticulocategorizado);
			}
			Linea idLinea = articulo.getIdLinea();
			if (idLinea != null) {
				idLinea = em.getReference(idLinea.getClass(), idLinea.getId());
				articulo.setIdLinea(idLinea);
			}
			Tasaimpuestotipo idTasaimpuestotipo = articulo.getIdTasaimpuestotipo();
			if (idTasaimpuestotipo != null) {
				idTasaimpuestotipo = em.getReference(idTasaimpuestotipo.getClass(), idTasaimpuestotipo.getId());
				articulo.setIdTasaimpuestotipo(idTasaimpuestotipo);
			}
			List<Servicio> attachedServicioList = new ArrayList<Servicio>();
			for (Servicio servicioListServicioToAttach : articulo.getServicioList()) {
				servicioListServicioToAttach = em.getReference(servicioListServicioToAttach.getClass(),
						servicioListServicioToAttach.getId());
				attachedServicioList.add(servicioListServicioToAttach);
			}
			articulo.setServicioList(attachedServicioList);
			List<Exoneradotipo> attachedExoneradotipoList = new ArrayList<Exoneradotipo>();
			for (Exoneradotipo exoneradotipoListExoneradotipoToAttach : articulo.getExoneradotipoList()) {
				exoneradotipoListExoneradotipoToAttach = em.getReference(
						exoneradotipoListExoneradotipoToAttach.getClass(),
						exoneradotipoListExoneradotipoToAttach.getId());
				attachedExoneradotipoList.add(exoneradotipoListExoneradotipoToAttach);
			}
			articulo.setExoneradotipoList(attachedExoneradotipoList);
			List<Articulo> attachedArticuloList = new ArrayList<Articulo>();
			for (Articulo articuloListArticuloToAttach : articulo.getArticuloList()) {
				articuloListArticuloToAttach = em.getReference(articuloListArticuloToAttach.getClass(),
						articuloListArticuloToAttach.getId());
				attachedArticuloList.add(articuloListArticuloToAttach);
			}
			articulo.setArticuloList(attachedArticuloList);
			List<Promocionarticulo> attachedPromocionarticuloList = new ArrayList<Promocionarticulo>();
			for (Promocionarticulo promocionarticuloListPromocionarticuloToAttach : articulo.getPromocionarticuloList()) {
				promocionarticuloListPromocionarticuloToAttach = em.getReference(
						promocionarticuloListPromocionarticuloToAttach.getClass(),
						promocionarticuloListPromocionarticuloToAttach.getId());
				attachedPromocionarticuloList.add(promocionarticuloListPromocionarticuloToAttach);
			}
			articulo.setPromocionarticuloList(attachedPromocionarticuloList);
			List<Transaccionarticulo> attachedTransaccionarticuloList = new ArrayList<Transaccionarticulo>();
			for (Transaccionarticulo transaccionarticuloListTransaccionarticuloToAttach : articulo
					.getTransaccionarticuloList()) {
				transaccionarticuloListTransaccionarticuloToAttach = em.getReference(
						transaccionarticuloListTransaccionarticuloToAttach.getClass(),
						transaccionarticuloListTransaccionarticuloToAttach.getId());
				attachedTransaccionarticuloList.add(transaccionarticuloListTransaccionarticuloToAttach);
			}
			articulo.setTransaccionarticuloList(attachedTransaccionarticuloList);
			List<Articulocodigoexterno> attachedArticulocodigoexternoList = new ArrayList<Articulocodigoexterno>();
			for (Articulocodigoexterno articulocodigoexternoListArticulocodigoexternoToAttach : articulo
					.getArticulocodigoexternoList()) {
				articulocodigoexternoListArticulocodigoexternoToAttach = em.getReference(
						articulocodigoexternoListArticulocodigoexternoToAttach.getClass(),
						articulocodigoexternoListArticulocodigoexternoToAttach.getId());
				attachedArticulocodigoexternoList.add(articulocodigoexternoListArticulocodigoexternoToAttach);
			}
			articulo.setArticulocodigoexternoList(attachedArticulocodigoexternoList);
			List<Articulounidadventa> attachedArticulounidadventaList = new ArrayList<Articulounidadventa>();
			for (Articulounidadventa articulounidadventaListArticulounidadventaToAttach : articulo
					.getArticulounidadventaList()) {
				articulounidadventaListArticulounidadventaToAttach = em.getReference(
						articulounidadventaListArticulounidadventaToAttach.getClass(),
						articulounidadventaListArticulounidadventaToAttach.getId());
				attachedArticulounidadventaList.add(articulounidadventaListArticulounidadventaToAttach);
			}
			articulo.setArticulounidadventaList(attachedArticulounidadventaList);
			em.persist(articulo);
			if (idFamilia != null) {
				idFamilia.getArticuloList().add(articulo);
				idFamilia = em.merge(idFamilia);
			}
			if (idArticulocategorizado != null) {
				idArticulocategorizado.getArticuloList().add(articulo);
				idArticulocategorizado = em.merge(idArticulocategorizado);
			}
			if (idLinea != null) {
				idLinea.getArticuloList().add(articulo);
				idLinea = em.merge(idLinea);
			}
			if (idTasaimpuestotipo != null) {
				idTasaimpuestotipo.getArticuloList().add(articulo);
				idTasaimpuestotipo = em.merge(idTasaimpuestotipo);
			}
			for (Servicio servicioListServicio : articulo.getServicioList()) {
				servicioListServicio.getArticuloList().add(articulo);
				servicioListServicio = em.merge(servicioListServicio);
			}
			for (Exoneradotipo exoneradotipoListExoneradotipo : articulo.getExoneradotipoList()) {
				exoneradotipoListExoneradotipo.getArticuloList().add(articulo);
				exoneradotipoListExoneradotipo = em.merge(exoneradotipoListExoneradotipo);
			}
			for (Articulo articuloListArticulo : articulo.getArticuloList()) {
				Articulo oldIdArticulocategorizadoOfArticuloListArticulo = articuloListArticulo
						.getIdArticulocategorizado();
				articuloListArticulo.setIdArticulocategorizado(articulo);
				articuloListArticulo = em.merge(articuloListArticulo);
				if (oldIdArticulocategorizadoOfArticuloListArticulo != null) {
					oldIdArticulocategorizadoOfArticuloListArticulo.getArticuloList().remove(articuloListArticulo);
					oldIdArticulocategorizadoOfArticuloListArticulo = em
							.merge(oldIdArticulocategorizadoOfArticuloListArticulo);
				}
			}
			for (Promocionarticulo promocionarticuloListPromocionarticulo : articulo.getPromocionarticuloList()) {
				Articulo oldIdArticuloOfPromocionarticuloListPromocionarticulo = promocionarticuloListPromocionarticulo
						.getIdArticulo();
				promocionarticuloListPromocionarticulo.setIdArticulo(articulo);
				promocionarticuloListPromocionarticulo = em.merge(promocionarticuloListPromocionarticulo);
				if (oldIdArticuloOfPromocionarticuloListPromocionarticulo != null) {
					oldIdArticuloOfPromocionarticuloListPromocionarticulo.getPromocionarticuloList().remove(
							promocionarticuloListPromocionarticulo);
					oldIdArticuloOfPromocionarticuloListPromocionarticulo = em
							.merge(oldIdArticuloOfPromocionarticuloListPromocionarticulo);
				}
			}
			for (Transaccionarticulo transaccionarticuloListTransaccionarticulo : articulo.getTransaccionarticuloList()) {
				Articulo oldIdArticuloOfTransaccionarticuloListTransaccionarticulo = transaccionarticuloListTransaccionarticulo
						.getIdArticulo();
				transaccionarticuloListTransaccionarticulo.setIdArticulo(articulo);
				transaccionarticuloListTransaccionarticulo = em.merge(transaccionarticuloListTransaccionarticulo);
				if (oldIdArticuloOfTransaccionarticuloListTransaccionarticulo != null) {
					oldIdArticuloOfTransaccionarticuloListTransaccionarticulo.getTransaccionarticuloList().remove(
							transaccionarticuloListTransaccionarticulo);
					oldIdArticuloOfTransaccionarticuloListTransaccionarticulo = em
							.merge(oldIdArticuloOfTransaccionarticuloListTransaccionarticulo);
				}
			}
			for (Articulocodigoexterno articulocodigoexternoListArticulocodigoexterno : articulo
					.getArticulocodigoexternoList()) {
				Articulo oldIdArticuloOfArticulocodigoexternoListArticulocodigoexterno = articulocodigoexternoListArticulocodigoexterno
						.getIdArticulo();
				articulocodigoexternoListArticulocodigoexterno.setIdArticulo(articulo);
				articulocodigoexternoListArticulocodigoexterno = em
						.merge(articulocodigoexternoListArticulocodigoexterno);
				if (oldIdArticuloOfArticulocodigoexternoListArticulocodigoexterno != null) {
					oldIdArticuloOfArticulocodigoexternoListArticulocodigoexterno.getArticulocodigoexternoList()
							.remove(articulocodigoexternoListArticulocodigoexterno);
					oldIdArticuloOfArticulocodigoexternoListArticulocodigoexterno = em
							.merge(oldIdArticuloOfArticulocodigoexternoListArticulocodigoexterno);
				}
			}
			for (Articulounidadventa articulounidadventaListArticulounidadventa : articulo.getArticulounidadventaList()) {
				Articulo oldIdArticuloOfArticulounidadventaListArticulounidadventa = articulounidadventaListArticulounidadventa
						.getIdArticulo();
				articulounidadventaListArticulounidadventa.setIdArticulo(articulo);
				articulounidadventaListArticulounidadventa = em.merge(articulounidadventaListArticulounidadventa);
				if (oldIdArticuloOfArticulounidadventaListArticulounidadventa != null) {
					oldIdArticuloOfArticulounidadventaListArticulounidadventa.getArticulounidadventaList().remove(
							articulounidadventaListArticulounidadventa);
					oldIdArticuloOfArticulounidadventaListArticulounidadventa = em
							.merge(oldIdArticuloOfArticulounidadventaListArticulounidadventa);
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findArticulo(articulo.getId()) != null) {
				throw new PreexistingEntityException("Articulo " + articulo + " already exists.", ex);
			}
			throw ex;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method edit.
	 * 
	 * @param articulo
	 *            Articulo
	 * @throws IllegalOrphanException
	 * @throws NonexistentEntityException
	 * @throws Exception
	 */
	public void edit(Articulo articulo) throws IllegalOrphanException, NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Articulo persistentArticulo = em.find(Articulo.class, articulo.getId());
			Familia idFamiliaOld = persistentArticulo.getIdFamilia();
			Familia idFamiliaNew = articulo.getIdFamilia();
			Articulo idArticulocategorizadoOld = persistentArticulo.getIdArticulocategorizado();
			Articulo idArticulocategorizadoNew = articulo.getIdArticulocategorizado();
			Linea idLineaOld = persistentArticulo.getIdLinea();
			Linea idLineaNew = articulo.getIdLinea();
			Tasaimpuestotipo idTasaimpuestotipoOld = persistentArticulo.getIdTasaimpuestotipo();
			Tasaimpuestotipo idTasaimpuestotipoNew = articulo.getIdTasaimpuestotipo();
			List<Servicio> servicioListOld = persistentArticulo.getServicioList();
			List<Servicio> servicioListNew = articulo.getServicioList();
			List<Exoneradotipo> exoneradotipoListOld = persistentArticulo.getExoneradotipoList();
			List<Exoneradotipo> exoneradotipoListNew = articulo.getExoneradotipoList();
			List<Articulo> articuloListOld = persistentArticulo.getArticuloList();
			List<Articulo> articuloListNew = articulo.getArticuloList();
			List<Promocionarticulo> promocionarticuloListOld = persistentArticulo.getPromocionarticuloList();
			List<Promocionarticulo> promocionarticuloListNew = articulo.getPromocionarticuloList();
			List<Transaccionarticulo> transaccionarticuloListOld = persistentArticulo.getTransaccionarticuloList();
			List<Transaccionarticulo> transaccionarticuloListNew = articulo.getTransaccionarticuloList();
			List<Articulocodigoexterno> articulocodigoexternoListOld = persistentArticulo
					.getArticulocodigoexternoList();
			List<Articulocodigoexterno> articulocodigoexternoListNew = articulo.getArticulocodigoexternoList();
			List<Articulounidadventa> articulounidadventaListOld = persistentArticulo.getArticulounidadventaList();
			List<Articulounidadventa> articulounidadventaListNew = articulo.getArticulounidadventaList();
			List<String> illegalOrphanMessages = null;
			for (Promocionarticulo promocionarticuloListOldPromocionarticulo : promocionarticuloListOld) {
				if (!promocionarticuloListNew.contains(promocionarticuloListOldPromocionarticulo)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages.add("You must retain Promocionarticulo "
							+ promocionarticuloListOldPromocionarticulo
							+ " since its idArticulo field is not nullable.");
				}
			}
			for (Transaccionarticulo transaccionarticuloListOldTransaccionarticulo : transaccionarticuloListOld) {
				if (!transaccionarticuloListNew.contains(transaccionarticuloListOldTransaccionarticulo)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages.add("You must retain Transaccionarticulo "
							+ transaccionarticuloListOldTransaccionarticulo
							+ " since its idArticulo field is not nullable.");
				}
			}
			for (Articulocodigoexterno articulocodigoexternoListOldArticulocodigoexterno : articulocodigoexternoListOld) {
				if (!articulocodigoexternoListNew.contains(articulocodigoexternoListOldArticulocodigoexterno)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages.add("You must retain Articulocodigoexterno "
							+ articulocodigoexternoListOldArticulocodigoexterno
							+ " since its idArticulo field is not nullable.");
				}
			}
			for (Articulounidadventa articulounidadventaListOldArticulounidadventa : articulounidadventaListOld) {
				if (!articulounidadventaListNew.contains(articulounidadventaListOldArticulounidadventa)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages.add("You must retain Articulounidadventa "
							+ articulounidadventaListOldArticulounidadventa
							+ " since its idArticulo field is not nullable.");
				}
			}
			if (illegalOrphanMessages != null) {
				throw new IllegalOrphanException(illegalOrphanMessages);
			}
			if (idFamiliaNew != null) {
				idFamiliaNew = em.getReference(idFamiliaNew.getClass(), idFamiliaNew.getId());
				articulo.setIdFamilia(idFamiliaNew);
			}
			if (idArticulocategorizadoNew != null) {
				idArticulocategorizadoNew = em.getReference(idArticulocategorizadoNew.getClass(),
						idArticulocategorizadoNew.getId());
				articulo.setIdArticulocategorizado(idArticulocategorizadoNew);
			}
			if (idLineaNew != null) {
				idLineaNew = em.getReference(idLineaNew.getClass(), idLineaNew.getId());
				articulo.setIdLinea(idLineaNew);
			}
			if (idTasaimpuestotipoNew != null) {
				idTasaimpuestotipoNew = em
						.getReference(idTasaimpuestotipoNew.getClass(), idTasaimpuestotipoNew.getId());
				articulo.setIdTasaimpuestotipo(idTasaimpuestotipoNew);
			}
			List<Servicio> attachedServicioListNew = new ArrayList<Servicio>();
			for (Servicio servicioListNewServicioToAttach : servicioListNew) {
				servicioListNewServicioToAttach = em.getReference(servicioListNewServicioToAttach.getClass(),
						servicioListNewServicioToAttach.getId());
				attachedServicioListNew.add(servicioListNewServicioToAttach);
			}
			servicioListNew = attachedServicioListNew;
			articulo.setServicioList(servicioListNew);
			List<Exoneradotipo> attachedExoneradotipoListNew = new ArrayList<Exoneradotipo>();
			for (Exoneradotipo exoneradotipoListNewExoneradotipoToAttach : exoneradotipoListNew) {
				exoneradotipoListNewExoneradotipoToAttach = em.getReference(
						exoneradotipoListNewExoneradotipoToAttach.getClass(),
						exoneradotipoListNewExoneradotipoToAttach.getId());
				attachedExoneradotipoListNew.add(exoneradotipoListNewExoneradotipoToAttach);
			}
			exoneradotipoListNew = attachedExoneradotipoListNew;
			articulo.setExoneradotipoList(exoneradotipoListNew);
			List<Articulo> attachedArticuloListNew = new ArrayList<Articulo>();
			for (Articulo articuloListNewArticuloToAttach : articuloListNew) {
				articuloListNewArticuloToAttach = em.getReference(articuloListNewArticuloToAttach.getClass(),
						articuloListNewArticuloToAttach.getId());
				attachedArticuloListNew.add(articuloListNewArticuloToAttach);
			}
			articuloListNew = attachedArticuloListNew;
			articulo.setArticuloList(articuloListNew);
			List<Promocionarticulo> attachedPromocionarticuloListNew = new ArrayList<Promocionarticulo>();
			for (Promocionarticulo promocionarticuloListNewPromocionarticuloToAttach : promocionarticuloListNew) {
				promocionarticuloListNewPromocionarticuloToAttach = em.getReference(
						promocionarticuloListNewPromocionarticuloToAttach.getClass(),
						promocionarticuloListNewPromocionarticuloToAttach.getId());
				attachedPromocionarticuloListNew.add(promocionarticuloListNewPromocionarticuloToAttach);
			}
			promocionarticuloListNew = attachedPromocionarticuloListNew;
			articulo.setPromocionarticuloList(promocionarticuloListNew);
			List<Transaccionarticulo> attachedTransaccionarticuloListNew = new ArrayList<Transaccionarticulo>();
			for (Transaccionarticulo transaccionarticuloListNewTransaccionarticuloToAttach : transaccionarticuloListNew) {
				transaccionarticuloListNewTransaccionarticuloToAttach = em.getReference(
						transaccionarticuloListNewTransaccionarticuloToAttach.getClass(),
						transaccionarticuloListNewTransaccionarticuloToAttach.getId());
				attachedTransaccionarticuloListNew.add(transaccionarticuloListNewTransaccionarticuloToAttach);
			}
			transaccionarticuloListNew = attachedTransaccionarticuloListNew;
			articulo.setTransaccionarticuloList(transaccionarticuloListNew);
			List<Articulocodigoexterno> attachedArticulocodigoexternoListNew = new ArrayList<Articulocodigoexterno>();
			for (Articulocodigoexterno articulocodigoexternoListNewArticulocodigoexternoToAttach : articulocodigoexternoListNew) {
				articulocodigoexternoListNewArticulocodigoexternoToAttach = em.getReference(
						articulocodigoexternoListNewArticulocodigoexternoToAttach.getClass(),
						articulocodigoexternoListNewArticulocodigoexternoToAttach.getId());
				attachedArticulocodigoexternoListNew.add(articulocodigoexternoListNewArticulocodigoexternoToAttach);
			}
			articulocodigoexternoListNew = attachedArticulocodigoexternoListNew;
			articulo.setArticulocodigoexternoList(articulocodigoexternoListNew);
			List<Articulounidadventa> attachedArticulounidadventaListNew = new ArrayList<Articulounidadventa>();
			for (Articulounidadventa articulounidadventaListNewArticulounidadventaToAttach : articulounidadventaListNew) {
				articulounidadventaListNewArticulounidadventaToAttach = em.getReference(
						articulounidadventaListNewArticulounidadventaToAttach.getClass(),
						articulounidadventaListNewArticulounidadventaToAttach.getId());
				attachedArticulounidadventaListNew.add(articulounidadventaListNewArticulounidadventaToAttach);
			}
			articulounidadventaListNew = attachedArticulounidadventaListNew;
			articulo.setArticulounidadventaList(articulounidadventaListNew);
			articulo = em.merge(articulo);
			if (idFamiliaOld != null && !idFamiliaOld.equals(idFamiliaNew)) {
				idFamiliaOld.getArticuloList().remove(articulo);
				idFamiliaOld = em.merge(idFamiliaOld);
			}
			if (idFamiliaNew != null && !idFamiliaNew.equals(idFamiliaOld)) {
				idFamiliaNew.getArticuloList().add(articulo);
				idFamiliaNew = em.merge(idFamiliaNew);
			}
			if (idArticulocategorizadoOld != null && !idArticulocategorizadoOld.equals(idArticulocategorizadoNew)) {
				idArticulocategorizadoOld.getArticuloList().remove(articulo);
				idArticulocategorizadoOld = em.merge(idArticulocategorizadoOld);
			}
			if (idArticulocategorizadoNew != null && !idArticulocategorizadoNew.equals(idArticulocategorizadoOld)) {
				idArticulocategorizadoNew.getArticuloList().add(articulo);
				idArticulocategorizadoNew = em.merge(idArticulocategorizadoNew);
			}
			if (idLineaOld != null && !idLineaOld.equals(idLineaNew)) {
				idLineaOld.getArticuloList().remove(articulo);
				idLineaOld = em.merge(idLineaOld);
			}
			if (idLineaNew != null && !idLineaNew.equals(idLineaOld)) {
				idLineaNew.getArticuloList().add(articulo);
				idLineaNew = em.merge(idLineaNew);
			}
			if (idTasaimpuestotipoOld != null && !idTasaimpuestotipoOld.equals(idTasaimpuestotipoNew)) {
				idTasaimpuestotipoOld.getArticuloList().remove(articulo);
				idTasaimpuestotipoOld = em.merge(idTasaimpuestotipoOld);
			}
			if (idTasaimpuestotipoNew != null && !idTasaimpuestotipoNew.equals(idTasaimpuestotipoOld)) {
				idTasaimpuestotipoNew.getArticuloList().add(articulo);
				idTasaimpuestotipoNew = em.merge(idTasaimpuestotipoNew);
			}
			for (Servicio servicioListOldServicio : servicioListOld) {
				if (!servicioListNew.contains(servicioListOldServicio)) {
					servicioListOldServicio.getArticuloList().remove(articulo);
					servicioListOldServicio = em.merge(servicioListOldServicio);
				}
			}
			for (Servicio servicioListNewServicio : servicioListNew) {
				if (!servicioListOld.contains(servicioListNewServicio)) {
					servicioListNewServicio.getArticuloList().add(articulo);
					servicioListNewServicio = em.merge(servicioListNewServicio);
				}
			}
			for (Exoneradotipo exoneradotipoListOldExoneradotipo : exoneradotipoListOld) {
				if (!exoneradotipoListNew.contains(exoneradotipoListOldExoneradotipo)) {
					exoneradotipoListOldExoneradotipo.getArticuloList().remove(articulo);
					exoneradotipoListOldExoneradotipo = em.merge(exoneradotipoListOldExoneradotipo);
				}
			}
			for (Exoneradotipo exoneradotipoListNewExoneradotipo : exoneradotipoListNew) {
				if (!exoneradotipoListOld.contains(exoneradotipoListNewExoneradotipo)) {
					exoneradotipoListNewExoneradotipo.getArticuloList().add(articulo);
					exoneradotipoListNewExoneradotipo = em.merge(exoneradotipoListNewExoneradotipo);
				}
			}
			for (Articulo articuloListOldArticulo : articuloListOld) {
				if (!articuloListNew.contains(articuloListOldArticulo)) {
					articuloListOldArticulo.setIdArticulocategorizado(null);
					articuloListOldArticulo = em.merge(articuloListOldArticulo);
				}
			}
			for (Articulo articuloListNewArticulo : articuloListNew) {
				if (!articuloListOld.contains(articuloListNewArticulo)) {
					Articulo oldIdArticulocategorizadoOfArticuloListNewArticulo = articuloListNewArticulo
							.getIdArticulocategorizado();
					articuloListNewArticulo.setIdArticulocategorizado(articulo);
					articuloListNewArticulo = em.merge(articuloListNewArticulo);
					if (oldIdArticulocategorizadoOfArticuloListNewArticulo != null
							&& !oldIdArticulocategorizadoOfArticuloListNewArticulo.equals(articulo)) {
						oldIdArticulocategorizadoOfArticuloListNewArticulo.getArticuloList().remove(
								articuloListNewArticulo);
						oldIdArticulocategorizadoOfArticuloListNewArticulo = em
								.merge(oldIdArticulocategorizadoOfArticuloListNewArticulo);
					}
				}
			}
			for (Promocionarticulo promocionarticuloListNewPromocionarticulo : promocionarticuloListNew) {
				if (!promocionarticuloListOld.contains(promocionarticuloListNewPromocionarticulo)) {
					Articulo oldIdArticuloOfPromocionarticuloListNewPromocionarticulo = promocionarticuloListNewPromocionarticulo
							.getIdArticulo();
					promocionarticuloListNewPromocionarticulo.setIdArticulo(articulo);
					promocionarticuloListNewPromocionarticulo = em.merge(promocionarticuloListNewPromocionarticulo);
					if (oldIdArticuloOfPromocionarticuloListNewPromocionarticulo != null
							&& !oldIdArticuloOfPromocionarticuloListNewPromocionarticulo.equals(articulo)) {
						oldIdArticuloOfPromocionarticuloListNewPromocionarticulo.getPromocionarticuloList().remove(
								promocionarticuloListNewPromocionarticulo);
						oldIdArticuloOfPromocionarticuloListNewPromocionarticulo = em
								.merge(oldIdArticuloOfPromocionarticuloListNewPromocionarticulo);
					}
				}
			}
			for (Transaccionarticulo transaccionarticuloListNewTransaccionarticulo : transaccionarticuloListNew) {
				if (!transaccionarticuloListOld.contains(transaccionarticuloListNewTransaccionarticulo)) {
					Articulo oldIdArticuloOfTransaccionarticuloListNewTransaccionarticulo = transaccionarticuloListNewTransaccionarticulo
							.getIdArticulo();
					transaccionarticuloListNewTransaccionarticulo.setIdArticulo(articulo);
					transaccionarticuloListNewTransaccionarticulo = em
							.merge(transaccionarticuloListNewTransaccionarticulo);
					if (oldIdArticuloOfTransaccionarticuloListNewTransaccionarticulo != null
							&& !oldIdArticuloOfTransaccionarticuloListNewTransaccionarticulo.equals(articulo)) {
						oldIdArticuloOfTransaccionarticuloListNewTransaccionarticulo.getTransaccionarticuloList()
								.remove(transaccionarticuloListNewTransaccionarticulo);
						oldIdArticuloOfTransaccionarticuloListNewTransaccionarticulo = em
								.merge(oldIdArticuloOfTransaccionarticuloListNewTransaccionarticulo);
					}
				}
			}
			for (Articulocodigoexterno articulocodigoexternoListNewArticulocodigoexterno : articulocodigoexternoListNew) {
				if (!articulocodigoexternoListOld.contains(articulocodigoexternoListNewArticulocodigoexterno)) {
					Articulo oldIdArticuloOfArticulocodigoexternoListNewArticulocodigoexterno = articulocodigoexternoListNewArticulocodigoexterno
							.getIdArticulo();
					articulocodigoexternoListNewArticulocodigoexterno.setIdArticulo(articulo);
					articulocodigoexternoListNewArticulocodigoexterno = em
							.merge(articulocodigoexternoListNewArticulocodigoexterno);
					if (oldIdArticuloOfArticulocodigoexternoListNewArticulocodigoexterno != null
							&& !oldIdArticuloOfArticulocodigoexternoListNewArticulocodigoexterno.equals(articulo)) {
						oldIdArticuloOfArticulocodigoexternoListNewArticulocodigoexterno.getArticulocodigoexternoList()
								.remove(articulocodigoexternoListNewArticulocodigoexterno);
						oldIdArticuloOfArticulocodigoexternoListNewArticulocodigoexterno = em
								.merge(oldIdArticuloOfArticulocodigoexternoListNewArticulocodigoexterno);
					}
				}
			}
			for (Articulounidadventa articulounidadventaListNewArticulounidadventa : articulounidadventaListNew) {
				if (!articulounidadventaListOld.contains(articulounidadventaListNewArticulounidadventa)) {
					Articulo oldIdArticuloOfArticulounidadventaListNewArticulounidadventa = articulounidadventaListNewArticulounidadventa
							.getIdArticulo();
					articulounidadventaListNewArticulounidadventa.setIdArticulo(articulo);
					articulounidadventaListNewArticulounidadventa = em
							.merge(articulounidadventaListNewArticulounidadventa);
					if (oldIdArticuloOfArticulounidadventaListNewArticulounidadventa != null
							&& !oldIdArticuloOfArticulounidadventaListNewArticulounidadventa.equals(articulo)) {
						oldIdArticuloOfArticulounidadventaListNewArticulounidadventa.getArticulounidadventaList()
								.remove(articulounidadventaListNewArticulounidadventa);
						oldIdArticuloOfArticulounidadventaListNewArticulounidadventa = em
								.merge(oldIdArticuloOfArticulounidadventaListNewArticulounidadventa);
					}
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = articulo.getId();
				if (findArticulo(id) == null) {
					throw new NonexistentEntityException("The articulo with id " + id + " no longer exists.");
				}
			}
			throw ex;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method destroy.
	 * 
	 * @param id
	 *            Long
	 * @throws IllegalOrphanException
	 * @throws NonexistentEntityException
	 */
	public void destroy(Long id) throws IllegalOrphanException, NonexistentEntityException {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Articulo articulo;
			try {
				articulo = em.getReference(Articulo.class, id);
				articulo.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException("The articulo with id " + id + " no longer exists.", enfe);
			}
			List<String> illegalOrphanMessages = null;
			List<Promocionarticulo> promocionarticuloListOrphanCheck = articulo.getPromocionarticuloList();
			for (Promocionarticulo promocionarticuloListOrphanCheckPromocionarticulo : promocionarticuloListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages.add("This Articulo (" + articulo
						+ ") cannot be destroyed since the Promocionarticulo "
						+ promocionarticuloListOrphanCheckPromocionarticulo
						+ " in its promocionarticuloList field has a non-nullable idArticulo field.");
			}
			List<Transaccionarticulo> transaccionarticuloListOrphanCheck = articulo.getTransaccionarticuloList();
			for (Transaccionarticulo transaccionarticuloListOrphanCheckTransaccionarticulo : transaccionarticuloListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages.add("This Articulo (" + articulo
						+ ") cannot be destroyed since the Transaccionarticulo "
						+ transaccionarticuloListOrphanCheckTransaccionarticulo
						+ " in its transaccionarticuloList field has a non-nullable idArticulo field.");
			}
			List<Articulocodigoexterno> articulocodigoexternoListOrphanCheck = articulo.getArticulocodigoexternoList();
			for (Articulocodigoexterno articulocodigoexternoListOrphanCheckArticulocodigoexterno : articulocodigoexternoListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages.add("This Articulo (" + articulo
						+ ") cannot be destroyed since the Articulocodigoexterno "
						+ articulocodigoexternoListOrphanCheckArticulocodigoexterno
						+ " in its articulocodigoexternoList field has a non-nullable idArticulo field.");
			}
			List<Articulounidadventa> articulounidadventaListOrphanCheck = articulo.getArticulounidadventaList();
			for (Articulounidadventa articulounidadventaListOrphanCheckArticulounidadventa : articulounidadventaListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages.add("This Articulo (" + articulo
						+ ") cannot be destroyed since the Articulounidadventa "
						+ articulounidadventaListOrphanCheckArticulounidadventa
						+ " in its articulounidadventaList field has a non-nullable idArticulo field.");
			}
			if (illegalOrphanMessages != null) {
				throw new IllegalOrphanException(illegalOrphanMessages);
			}
			Familia idFamilia = articulo.getIdFamilia();
			if (idFamilia != null) {
				idFamilia.getArticuloList().remove(articulo);
				idFamilia = em.merge(idFamilia);
			}
			Articulo idArticulocategorizado = articulo.getIdArticulocategorizado();
			if (idArticulocategorizado != null) {
				idArticulocategorizado.getArticuloList().remove(articulo);
				idArticulocategorizado = em.merge(idArticulocategorizado);
			}
			Linea idLinea = articulo.getIdLinea();
			if (idLinea != null) {
				idLinea.getArticuloList().remove(articulo);
				idLinea = em.merge(idLinea);
			}
			Tasaimpuestotipo idTasaimpuestotipo = articulo.getIdTasaimpuestotipo();
			if (idTasaimpuestotipo != null) {
				idTasaimpuestotipo.getArticuloList().remove(articulo);
				idTasaimpuestotipo = em.merge(idTasaimpuestotipo);
			}
			List<Servicio> servicioList = articulo.getServicioList();
			for (Servicio servicioListServicio : servicioList) {
				servicioListServicio.getArticuloList().remove(articulo);
				servicioListServicio = em.merge(servicioListServicio);
			}
			List<Exoneradotipo> exoneradotipoList = articulo.getExoneradotipoList();
			for (Exoneradotipo exoneradotipoListExoneradotipo : exoneradotipoList) {
				exoneradotipoListExoneradotipo.getArticuloList().remove(articulo);
				exoneradotipoListExoneradotipo = em.merge(exoneradotipoListExoneradotipo);
			}
			List<Articulo> articuloList = articulo.getArticuloList();
			for (Articulo articuloListArticulo : articuloList) {
				articuloListArticulo.setIdArticulocategorizado(null);
				articuloListArticulo = em.merge(articuloListArticulo);
			}
			em.remove(articulo);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method findArticuloEntities.
	 * 
	 * @return List<Articulo>
	 */
	public List<Articulo> findArticuloEntities() {
		return findArticuloEntities(true, -1, -1);
	}

	/**
	 * Method findArticuloEntities.
	 * 
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Articulo>
	 */
	public List<Articulo> findArticuloEntities(int maxResults, int firstResult) {
		return findArticuloEntities(false, maxResults, firstResult);
	}

	/**
	 * Method findArticuloEntities.
	 * 
	 * @param all
	 *            boolean
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Articulo>
	 */
	private List<Articulo> findArticuloEntities(boolean all, int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Articulo.class));
			Query q = em.createQuery(cq);
			if (!all) {
				q.setMaxResults(maxResults);
				q.setFirstResult(firstResult);
			}
			return q.getResultList();
		} finally {
			em.close();
		}
	}

	/**
	 * Method findArticulo.
	 * 
	 * @param id
	 *            Long
	 * @return Articulo
	 */
	public Articulo findArticulo(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Articulo.class, id);
		} finally {
			em.close();
		}
	}

	/**
	 * Method getArticuloCount.
	 * 
	 * @return int
	 */
	public int getArticuloCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Articulo> rt = cq.from(Articulo.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}
