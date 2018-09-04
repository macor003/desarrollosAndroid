/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crjpa400;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import crjpa400.exceptions.IllegalOrphanException;
import crjpa400.exceptions.NonexistentEntityException;
import crjpa400.exceptions.PreexistingEntityException;

/**
 * 
 * @author eve0014321
 * @version $Revision: 1.0 $
 */
public class LineaJpaController implements Serializable {

	/**
	 * Constructor for LineaJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public LineaJpaController(EntityManagerFactory emf) {
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
	 * @param linea
	 *            Linea
	 * @throws PreexistingEntityException
	 * @throws Exception
	 */
	public void create(Linea linea) throws PreexistingEntityException,
			Exception {
		if (linea.getCategorialineaincluyeList() == null) {
			linea.setCategorialineaincluyeList(new ArrayList<Categorialineaincluye>());
		}
		if (linea.getPromocionlineaList() == null) {
			linea.setPromocionlineaList(new ArrayList<Promocionlinea>());
		}
		if (linea.getFamiliaList() == null) {
			linea.setFamiliaList(new ArrayList<Familia>());
		}
		if (linea.getArticuloList() == null) {
			linea.setArticuloList(new ArrayList<Articulo>());
		}
		if (linea.getCategorialinearetencionList() == null) {
			linea.setCategorialinearetencionList(new ArrayList<Categorialinearetencion>());
		}
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Categoria idCategoria = linea.getIdCategoria();
			if (idCategoria != null) {
				idCategoria = em.getReference(idCategoria.getClass(),
						idCategoria.getId());
				linea.setIdCategoria(idCategoria);
			}
			List<Categorialineaincluye> attachedCategorialineaincluyeList = new ArrayList<Categorialineaincluye>();
			for (Categorialineaincluye categorialineaincluyeListCategorialineaincluyeToAttach : linea
					.getCategorialineaincluyeList()) {
				categorialineaincluyeListCategorialineaincluyeToAttach = em
						.getReference(
								categorialineaincluyeListCategorialineaincluyeToAttach
										.getClass(),
								categorialineaincluyeListCategorialineaincluyeToAttach
										.getId());
				attachedCategorialineaincluyeList
						.add(categorialineaincluyeListCategorialineaincluyeToAttach);
			}
			linea.setCategorialineaincluyeList(attachedCategorialineaincluyeList);
			List<Promocionlinea> attachedPromocionlineaList = new ArrayList<Promocionlinea>();
			for (Promocionlinea promocionlineaListPromocionlineaToAttach : linea
					.getPromocionlineaList()) {
				promocionlineaListPromocionlineaToAttach = em.getReference(
						promocionlineaListPromocionlineaToAttach.getClass(),
						promocionlineaListPromocionlineaToAttach.getId());
				attachedPromocionlineaList
						.add(promocionlineaListPromocionlineaToAttach);
			}
			linea.setPromocionlineaList(attachedPromocionlineaList);
			List<Familia> attachedFamiliaList = new ArrayList<Familia>();
			for (Familia familiaListFamiliaToAttach : linea.getFamiliaList()) {
				familiaListFamiliaToAttach = em.getReference(
						familiaListFamiliaToAttach.getClass(),
						familiaListFamiliaToAttach.getId());
				attachedFamiliaList.add(familiaListFamiliaToAttach);
			}
			linea.setFamiliaList(attachedFamiliaList);
			List<Articulo> attachedArticuloList = new ArrayList<Articulo>();
			for (Articulo articuloListArticuloToAttach : linea
					.getArticuloList()) {
				articuloListArticuloToAttach = em.getReference(
						articuloListArticuloToAttach.getClass(),
						articuloListArticuloToAttach.getId());
				attachedArticuloList.add(articuloListArticuloToAttach);
			}
			linea.setArticuloList(attachedArticuloList);
			List<Categorialinearetencion> attachedCategorialinearetencionList = new ArrayList<Categorialinearetencion>();
			for (Categorialinearetencion categorialinearetencionListCategorialinearetencionToAttach : linea
					.getCategorialinearetencionList()) {
				categorialinearetencionListCategorialinearetencionToAttach = em
						.getReference(
								categorialinearetencionListCategorialinearetencionToAttach
										.getClass(),
								categorialinearetencionListCategorialinearetencionToAttach
										.getId());
				attachedCategorialinearetencionList
						.add(categorialinearetencionListCategorialinearetencionToAttach);
			}
			linea.setCategorialinearetencionList(attachedCategorialinearetencionList);
			em.persist(linea);
			if (idCategoria != null) {
				idCategoria.getLineaList().add(linea);
				idCategoria = em.merge(idCategoria);
			}
			for (Categorialineaincluye categorialineaincluyeListCategorialineaincluye : linea
					.getCategorialineaincluyeList()) {
				Linea oldIdLineaOfCategorialineaincluyeListCategorialineaincluye = categorialineaincluyeListCategorialineaincluye
						.getIdLinea();
				categorialineaincluyeListCategorialineaincluye
						.setIdLinea(linea);
				categorialineaincluyeListCategorialineaincluye = em
						.merge(categorialineaincluyeListCategorialineaincluye);
				if (oldIdLineaOfCategorialineaincluyeListCategorialineaincluye != null) {
					oldIdLineaOfCategorialineaincluyeListCategorialineaincluye
							.getCategorialineaincluyeList()
							.remove(categorialineaincluyeListCategorialineaincluye);
					oldIdLineaOfCategorialineaincluyeListCategorialineaincluye = em
							.merge(oldIdLineaOfCategorialineaincluyeListCategorialineaincluye);
				}
			}
			for (Promocionlinea promocionlineaListPromocionlinea : linea
					.getPromocionlineaList()) {
				Linea oldIdLineaOfPromocionlineaListPromocionlinea = promocionlineaListPromocionlinea
						.getIdLinea();
				promocionlineaListPromocionlinea.setIdLinea(linea);
				promocionlineaListPromocionlinea = em
						.merge(promocionlineaListPromocionlinea);
				if (oldIdLineaOfPromocionlineaListPromocionlinea != null) {
					oldIdLineaOfPromocionlineaListPromocionlinea
							.getPromocionlineaList().remove(
									promocionlineaListPromocionlinea);
					oldIdLineaOfPromocionlineaListPromocionlinea = em
							.merge(oldIdLineaOfPromocionlineaListPromocionlinea);
				}
			}
			for (Familia familiaListFamilia : linea.getFamiliaList()) {
				Linea oldIdLineaOfFamiliaListFamilia = familiaListFamilia
						.getIdLinea();
				familiaListFamilia.setIdLinea(linea);
				familiaListFamilia = em.merge(familiaListFamilia);
				if (oldIdLineaOfFamiliaListFamilia != null) {
					oldIdLineaOfFamiliaListFamilia.getFamiliaList().remove(
							familiaListFamilia);
					oldIdLineaOfFamiliaListFamilia = em
							.merge(oldIdLineaOfFamiliaListFamilia);
				}
			}
			for (Articulo articuloListArticulo : linea.getArticuloList()) {
				Linea oldIdLineaOfArticuloListArticulo = articuloListArticulo
						.getIdLinea();
				articuloListArticulo.setIdLinea(linea);
				articuloListArticulo = em.merge(articuloListArticulo);
				if (oldIdLineaOfArticuloListArticulo != null) {
					oldIdLineaOfArticuloListArticulo.getArticuloList().remove(
							articuloListArticulo);
					oldIdLineaOfArticuloListArticulo = em
							.merge(oldIdLineaOfArticuloListArticulo);
				}
			}
			for (Categorialinearetencion categorialinearetencionListCategorialinearetencion : linea
					.getCategorialinearetencionList()) {
				Linea oldIdLineaOfCategorialinearetencionListCategorialinearetencion = categorialinearetencionListCategorialinearetencion
						.getIdLinea();
				categorialinearetencionListCategorialinearetencion
						.setIdLinea(linea);
				categorialinearetencionListCategorialinearetencion = em
						.merge(categorialinearetencionListCategorialinearetencion);
				if (oldIdLineaOfCategorialinearetencionListCategorialinearetencion != null) {
					oldIdLineaOfCategorialinearetencionListCategorialinearetencion
							.getCategorialinearetencionList()
							.remove(categorialinearetencionListCategorialinearetencion);
					oldIdLineaOfCategorialinearetencionListCategorialinearetencion = em
							.merge(oldIdLineaOfCategorialinearetencionListCategorialinearetencion);
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findLinea(linea.getId()) != null) {
				throw new PreexistingEntityException("Linea " + linea
						+ " already exists.", ex);
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
	 * @param linea
	 *            Linea
	 * @throws IllegalOrphanException
	 * @throws NonexistentEntityException
	 * @throws Exception
	 */
	public void edit(Linea linea) throws IllegalOrphanException,
			NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Linea persistentLinea = em.find(Linea.class, linea.getId());
			Categoria idCategoriaOld = persistentLinea.getIdCategoria();
			Categoria idCategoriaNew = linea.getIdCategoria();
			List<Categorialineaincluye> categorialineaincluyeListOld = persistentLinea
					.getCategorialineaincluyeList();
			List<Categorialineaincluye> categorialineaincluyeListNew = linea
					.getCategorialineaincluyeList();
			List<Promocionlinea> promocionlineaListOld = persistentLinea
					.getPromocionlineaList();
			List<Promocionlinea> promocionlineaListNew = linea
					.getPromocionlineaList();
			List<Familia> familiaListOld = persistentLinea.getFamiliaList();
			List<Familia> familiaListNew = linea.getFamiliaList();
			List<Articulo> articuloListOld = persistentLinea.getArticuloList();
			List<Articulo> articuloListNew = linea.getArticuloList();
			List<Categorialinearetencion> categorialinearetencionListOld = persistentLinea
					.getCategorialinearetencionList();
			List<Categorialinearetencion> categorialinearetencionListNew = linea
					.getCategorialinearetencionList();
			List<String> illegalOrphanMessages = null;
			for (Categorialineaincluye categorialineaincluyeListOldCategorialineaincluye : categorialineaincluyeListOld) {
				if (!categorialineaincluyeListNew
						.contains(categorialineaincluyeListOldCategorialineaincluye)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages
							.add("You must retain Categorialineaincluye "
									+ categorialineaincluyeListOldCategorialineaincluye
									+ " since its idLinea field is not nullable.");
				}
			}
			for (Promocionlinea promocionlineaListOldPromocionlinea : promocionlineaListOld) {
				if (!promocionlineaListNew
						.contains(promocionlineaListOldPromocionlinea)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages.add("You must retain Promocionlinea "
							+ promocionlineaListOldPromocionlinea
							+ " since its idLinea field is not nullable.");
				}
			}
			for (Familia familiaListOldFamilia : familiaListOld) {
				if (!familiaListNew.contains(familiaListOldFamilia)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages.add("You must retain Familia "
							+ familiaListOldFamilia
							+ " since its idLinea field is not nullable.");
				}
			}
			for (Articulo articuloListOldArticulo : articuloListOld) {
				if (!articuloListNew.contains(articuloListOldArticulo)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages.add("You must retain Articulo "
							+ articuloListOldArticulo
							+ " since its idLinea field is not nullable.");
				}
			}
			for (Categorialinearetencion categorialinearetencionListOldCategorialinearetencion : categorialinearetencionListOld) {
				if (!categorialinearetencionListNew
						.contains(categorialinearetencionListOldCategorialinearetencion)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages
							.add("You must retain Categorialinearetencion "
									+ categorialinearetencionListOldCategorialinearetencion
									+ " since its idLinea field is not nullable.");
				}
			}
			if (illegalOrphanMessages != null) {
				throw new IllegalOrphanException(illegalOrphanMessages);
			}
			if (idCategoriaNew != null) {
				idCategoriaNew = em.getReference(idCategoriaNew.getClass(),
						idCategoriaNew.getId());
				linea.setIdCategoria(idCategoriaNew);
			}
			List<Categorialineaincluye> attachedCategorialineaincluyeListNew = new ArrayList<Categorialineaincluye>();
			for (Categorialineaincluye categorialineaincluyeListNewCategorialineaincluyeToAttach : categorialineaincluyeListNew) {
				categorialineaincluyeListNewCategorialineaincluyeToAttach = em
						.getReference(
								categorialineaincluyeListNewCategorialineaincluyeToAttach
										.getClass(),
								categorialineaincluyeListNewCategorialineaincluyeToAttach
										.getId());
				attachedCategorialineaincluyeListNew
						.add(categorialineaincluyeListNewCategorialineaincluyeToAttach);
			}
			categorialineaincluyeListNew = attachedCategorialineaincluyeListNew;
			linea.setCategorialineaincluyeList(categorialineaincluyeListNew);
			List<Promocionlinea> attachedPromocionlineaListNew = new ArrayList<Promocionlinea>();
			for (Promocionlinea promocionlineaListNewPromocionlineaToAttach : promocionlineaListNew) {
				promocionlineaListNewPromocionlineaToAttach = em.getReference(
						promocionlineaListNewPromocionlineaToAttach.getClass(),
						promocionlineaListNewPromocionlineaToAttach.getId());
				attachedPromocionlineaListNew
						.add(promocionlineaListNewPromocionlineaToAttach);
			}
			promocionlineaListNew = attachedPromocionlineaListNew;
			linea.setPromocionlineaList(promocionlineaListNew);
			List<Familia> attachedFamiliaListNew = new ArrayList<Familia>();
			for (Familia familiaListNewFamiliaToAttach : familiaListNew) {
				familiaListNewFamiliaToAttach = em.getReference(
						familiaListNewFamiliaToAttach.getClass(),
						familiaListNewFamiliaToAttach.getId());
				attachedFamiliaListNew.add(familiaListNewFamiliaToAttach);
			}
			familiaListNew = attachedFamiliaListNew;
			linea.setFamiliaList(familiaListNew);
			List<Articulo> attachedArticuloListNew = new ArrayList<Articulo>();
			for (Articulo articuloListNewArticuloToAttach : articuloListNew) {
				articuloListNewArticuloToAttach = em.getReference(
						articuloListNewArticuloToAttach.getClass(),
						articuloListNewArticuloToAttach.getId());
				attachedArticuloListNew.add(articuloListNewArticuloToAttach);
			}
			articuloListNew = attachedArticuloListNew;
			linea.setArticuloList(articuloListNew);
			List<Categorialinearetencion> attachedCategorialinearetencionListNew = new ArrayList<Categorialinearetencion>();
			for (Categorialinearetencion categorialinearetencionListNewCategorialinearetencionToAttach : categorialinearetencionListNew) {
				categorialinearetencionListNewCategorialinearetencionToAttach = em
						.getReference(
								categorialinearetencionListNewCategorialinearetencionToAttach
										.getClass(),
								categorialinearetencionListNewCategorialinearetencionToAttach
										.getId());
				attachedCategorialinearetencionListNew
						.add(categorialinearetencionListNewCategorialinearetencionToAttach);
			}
			categorialinearetencionListNew = attachedCategorialinearetencionListNew;
			linea.setCategorialinearetencionList(categorialinearetencionListNew);
			linea = em.merge(linea);
			if (idCategoriaOld != null
					&& !idCategoriaOld.equals(idCategoriaNew)) {
				idCategoriaOld.getLineaList().remove(linea);
				idCategoriaOld = em.merge(idCategoriaOld);
			}
			if (idCategoriaNew != null
					&& !idCategoriaNew.equals(idCategoriaOld)) {
				idCategoriaNew.getLineaList().add(linea);
				idCategoriaNew = em.merge(idCategoriaNew);
			}
			for (Categorialineaincluye categorialineaincluyeListNewCategorialineaincluye : categorialineaincluyeListNew) {
				if (!categorialineaincluyeListOld
						.contains(categorialineaincluyeListNewCategorialineaincluye)) {
					Linea oldIdLineaOfCategorialineaincluyeListNewCategorialineaincluye = categorialineaincluyeListNewCategorialineaincluye
							.getIdLinea();
					categorialineaincluyeListNewCategorialineaincluye
							.setIdLinea(linea);
					categorialineaincluyeListNewCategorialineaincluye = em
							.merge(categorialineaincluyeListNewCategorialineaincluye);
					if (oldIdLineaOfCategorialineaincluyeListNewCategorialineaincluye != null
							&& !oldIdLineaOfCategorialineaincluyeListNewCategorialineaincluye
									.equals(linea)) {
						oldIdLineaOfCategorialineaincluyeListNewCategorialineaincluye
								.getCategorialineaincluyeList()
								.remove(categorialineaincluyeListNewCategorialineaincluye);
						oldIdLineaOfCategorialineaincluyeListNewCategorialineaincluye = em
								.merge(oldIdLineaOfCategorialineaincluyeListNewCategorialineaincluye);
					}
				}
			}
			for (Promocionlinea promocionlineaListNewPromocionlinea : promocionlineaListNew) {
				if (!promocionlineaListOld
						.contains(promocionlineaListNewPromocionlinea)) {
					Linea oldIdLineaOfPromocionlineaListNewPromocionlinea = promocionlineaListNewPromocionlinea
							.getIdLinea();
					promocionlineaListNewPromocionlinea.setIdLinea(linea);
					promocionlineaListNewPromocionlinea = em
							.merge(promocionlineaListNewPromocionlinea);
					if (oldIdLineaOfPromocionlineaListNewPromocionlinea != null
							&& !oldIdLineaOfPromocionlineaListNewPromocionlinea
									.equals(linea)) {
						oldIdLineaOfPromocionlineaListNewPromocionlinea
								.getPromocionlineaList().remove(
										promocionlineaListNewPromocionlinea);
						oldIdLineaOfPromocionlineaListNewPromocionlinea = em
								.merge(oldIdLineaOfPromocionlineaListNewPromocionlinea);
					}
				}
			}
			for (Familia familiaListNewFamilia : familiaListNew) {
				if (!familiaListOld.contains(familiaListNewFamilia)) {
					Linea oldIdLineaOfFamiliaListNewFamilia = familiaListNewFamilia
							.getIdLinea();
					familiaListNewFamilia.setIdLinea(linea);
					familiaListNewFamilia = em.merge(familiaListNewFamilia);
					if (oldIdLineaOfFamiliaListNewFamilia != null
							&& !oldIdLineaOfFamiliaListNewFamilia.equals(linea)) {
						oldIdLineaOfFamiliaListNewFamilia.getFamiliaList()
								.remove(familiaListNewFamilia);
						oldIdLineaOfFamiliaListNewFamilia = em
								.merge(oldIdLineaOfFamiliaListNewFamilia);
					}
				}
			}
			for (Articulo articuloListNewArticulo : articuloListNew) {
				if (!articuloListOld.contains(articuloListNewArticulo)) {
					Linea oldIdLineaOfArticuloListNewArticulo = articuloListNewArticulo
							.getIdLinea();
					articuloListNewArticulo.setIdLinea(linea);
					articuloListNewArticulo = em.merge(articuloListNewArticulo);
					if (oldIdLineaOfArticuloListNewArticulo != null
							&& !oldIdLineaOfArticuloListNewArticulo
									.equals(linea)) {
						oldIdLineaOfArticuloListNewArticulo.getArticuloList()
								.remove(articuloListNewArticulo);
						oldIdLineaOfArticuloListNewArticulo = em
								.merge(oldIdLineaOfArticuloListNewArticulo);
					}
				}
			}
			for (Categorialinearetencion categorialinearetencionListNewCategorialinearetencion : categorialinearetencionListNew) {
				if (!categorialinearetencionListOld
						.contains(categorialinearetencionListNewCategorialinearetencion)) {
					Linea oldIdLineaOfCategorialinearetencionListNewCategorialinearetencion = categorialinearetencionListNewCategorialinearetencion
							.getIdLinea();
					categorialinearetencionListNewCategorialinearetencion
							.setIdLinea(linea);
					categorialinearetencionListNewCategorialinearetencion = em
							.merge(categorialinearetencionListNewCategorialinearetencion);
					if (oldIdLineaOfCategorialinearetencionListNewCategorialinearetencion != null
							&& !oldIdLineaOfCategorialinearetencionListNewCategorialinearetencion
									.equals(linea)) {
						oldIdLineaOfCategorialinearetencionListNewCategorialinearetencion
								.getCategorialinearetencionList()
								.remove(categorialinearetencionListNewCategorialinearetencion);
						oldIdLineaOfCategorialinearetencionListNewCategorialinearetencion = em
								.merge(oldIdLineaOfCategorialinearetencionListNewCategorialinearetencion);
					}
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = linea.getId();
				if (findLinea(id) == null) {
					throw new NonexistentEntityException("The linea with id "
							+ id + " no longer exists.");
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
	public void destroy(Long id) throws IllegalOrphanException,
			NonexistentEntityException {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Linea linea;
			try {
				linea = em.getReference(Linea.class, id);
				linea.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException("The linea with id " + id
						+ " no longer exists.", enfe);
			}
			List<String> illegalOrphanMessages = null;
			List<Categorialineaincluye> categorialineaincluyeListOrphanCheck = linea
					.getCategorialineaincluyeList();
			for (Categorialineaincluye categorialineaincluyeListOrphanCheckCategorialineaincluye : categorialineaincluyeListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages
						.add("This Linea ("
								+ linea
								+ ") cannot be destroyed since the Categorialineaincluye "
								+ categorialineaincluyeListOrphanCheckCategorialineaincluye
								+ " in its categorialineaincluyeList field has a non-nullable idLinea field.");
			}
			List<Promocionlinea> promocionlineaListOrphanCheck = linea
					.getPromocionlineaList();
			for (Promocionlinea promocionlineaListOrphanCheckPromocionlinea : promocionlineaListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages
						.add("This Linea ("
								+ linea
								+ ") cannot be destroyed since the Promocionlinea "
								+ promocionlineaListOrphanCheckPromocionlinea
								+ " in its promocionlineaList field has a non-nullable idLinea field.");
			}
			List<Familia> familiaListOrphanCheck = linea.getFamiliaList();
			for (Familia familiaListOrphanCheckFamilia : familiaListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages
						.add("This Linea ("
								+ linea
								+ ") cannot be destroyed since the Familia "
								+ familiaListOrphanCheckFamilia
								+ " in its familiaList field has a non-nullable idLinea field.");
			}
			List<Articulo> articuloListOrphanCheck = linea.getArticuloList();
			for (Articulo articuloListOrphanCheckArticulo : articuloListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages
						.add("This Linea ("
								+ linea
								+ ") cannot be destroyed since the Articulo "
								+ articuloListOrphanCheckArticulo
								+ " in its articuloList field has a non-nullable idLinea field.");
			}
			List<Categorialinearetencion> categorialinearetencionListOrphanCheck = linea
					.getCategorialinearetencionList();
			for (Categorialinearetencion categorialinearetencionListOrphanCheckCategorialinearetencion : categorialinearetencionListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages
						.add("This Linea ("
								+ linea
								+ ") cannot be destroyed since the Categorialinearetencion "
								+ categorialinearetencionListOrphanCheckCategorialinearetencion
								+ " in its categorialinearetencionList field has a non-nullable idLinea field.");
			}
			if (illegalOrphanMessages != null) {
				throw new IllegalOrphanException(illegalOrphanMessages);
			}
			Categoria idCategoria = linea.getIdCategoria();
			if (idCategoria != null) {
				idCategoria.getLineaList().remove(linea);
				idCategoria = em.merge(idCategoria);
			}
			em.remove(linea);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method findLineaEntities.
	 * 
	 * @return List<Linea>
	 */
	public List<Linea> findLineaEntities() {
		return findLineaEntities(true, -1, -1);
	}

	/**
	 * Method findLineaEntities.
	 * 
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Linea>
	 */
	public List<Linea> findLineaEntities(int maxResults, int firstResult) {
		return findLineaEntities(false, maxResults, firstResult);
	}

	/**
	 * Method findLineaEntities.
	 * 
	 * @param all
	 *            boolean
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Linea>
	 */
	private List<Linea> findLineaEntities(boolean all, int maxResults,
			int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Linea.class));
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
	 * Method findLinea.
	 * 
	 * @param id
	 *            Long
	 * @return Linea
	 */
	public Linea findLinea(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Linea.class, id);
		} finally {
			em.close();
		}
	}

	/**
	 * Method getLineaCount.
	 * 
	 * @return int
	 */
	public int getLineaCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Linea> rt = cq.from(Linea.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}
