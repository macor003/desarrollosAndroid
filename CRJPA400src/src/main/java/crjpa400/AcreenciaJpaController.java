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
public class AcreenciaJpaController implements Serializable {

    /**
     * Constructor for AcreenciaJpaController.
     *
     * @param emf
     *            EntityManagerFactory
     */
    public AcreenciaJpaController(EntityManagerFactory emf) {
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
     * @param acreencia
     *            Acreencia
     * @throws IllegalOrphanException
     * @throws PreexistingEntityException
     * @throws Exception
     */
    public void create(Acreencia acreencia) throws IllegalOrphanException,
            PreexistingEntityException, Exception {
        if (acreencia.getAcreenciamovimientosaldoList() == null) {
            acreencia
                    .setAcreenciamovimientosaldoList(new ArrayList<Acreenciamovimientosaldo>());
        }
        if (acreencia.getMovimientoacreenciaList() == null) {
            acreencia
                    .setMovimientoacreenciaList(new ArrayList<Movimientoacreencia>());
        }
        if (acreencia.getSaldoacreenciaList() == null) {
            acreencia.setSaldoacreenciaList(new ArrayList<Saldoacreencia>());
        }
        List<String> illegalOrphanMessages = null;
        Cliente numeroIdentificacionClienteOrphanCheck = acreencia
                .getNumeroIdentificacionCliente();
        if (numeroIdentificacionClienteOrphanCheck != null) {
            Acreencia oldAcreenciaOfNumeroIdentificacionCliente = numeroIdentificacionClienteOrphanCheck
                    .getAcreencia();
            if (oldAcreenciaOfNumeroIdentificacionCliente != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages
                        .add("The Cliente "
                                + numeroIdentificacionClienteOrphanCheck
                                + " already has an item of type Acreencia whose numeroIdentificacionCliente column cannot be null. Please make another selection for the numeroIdentificacionCliente field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente numeroIdentificacionCliente = acreencia
                    .getNumeroIdentificacionCliente();
            if (numeroIdentificacionCliente != null) {
                numeroIdentificacionCliente = em.getReference(
                        numeroIdentificacionCliente.getClass(),
                        numeroIdentificacionCliente
                                .getNumeroIdentificacionCliente());
                acreencia
                        .setNumeroIdentificacionCliente(numeroIdentificacionCliente);
            }
            List<Acreenciamovimientosaldo> attachedAcreenciamovimientosaldoList = new ArrayList<Acreenciamovimientosaldo>();
            for (Acreenciamovimientosaldo acreenciamovimientosaldoListAcreenciamovimientosaldoToAttach : acreencia
                    .getAcreenciamovimientosaldoList()) {
                acreenciamovimientosaldoListAcreenciamovimientosaldoToAttach = em
                        .getReference(
                                acreenciamovimientosaldoListAcreenciamovimientosaldoToAttach
                                        .getClass(),
                                acreenciamovimientosaldoListAcreenciamovimientosaldoToAttach
                                        .getId());
                attachedAcreenciamovimientosaldoList
                        .add(acreenciamovimientosaldoListAcreenciamovimientosaldoToAttach);
            }
            acreencia
                    .setAcreenciamovimientosaldoList(attachedAcreenciamovimientosaldoList);
            List<Movimientoacreencia> attachedMovimientoacreenciaList = new ArrayList<Movimientoacreencia>();
            for (Movimientoacreencia movimientoacreenciaListMovimientoacreenciaToAttach : acreencia
                    .getMovimientoacreenciaList()) {
                movimientoacreenciaListMovimientoacreenciaToAttach = em
                        .getReference(
                                movimientoacreenciaListMovimientoacreenciaToAttach
                                        .getClass(),
                                movimientoacreenciaListMovimientoacreenciaToAttach
                                        .getId());
                attachedMovimientoacreenciaList
                        .add(movimientoacreenciaListMovimientoacreenciaToAttach);
            }
            acreencia
                    .setMovimientoacreenciaList(attachedMovimientoacreenciaList);
            List<Saldoacreencia> attachedSaldoacreenciaList = new ArrayList<Saldoacreencia>();
            for (Saldoacreencia saldoacreenciaListSaldoacreenciaToAttach : acreencia
                    .getSaldoacreenciaList()) {
                saldoacreenciaListSaldoacreenciaToAttach = em.getReference(
                        saldoacreenciaListSaldoacreenciaToAttach.getClass(),
                        saldoacreenciaListSaldoacreenciaToAttach
                                .getSaldoacreenciaPK());
                attachedSaldoacreenciaList
                        .add(saldoacreenciaListSaldoacreenciaToAttach);
            }
            acreencia.setSaldoacreenciaList(attachedSaldoacreenciaList);
            em.persist(acreencia);
            if (numeroIdentificacionCliente != null) {
                numeroIdentificacionCliente.setAcreencia(acreencia);
                numeroIdentificacionCliente = em
                        .merge(numeroIdentificacionCliente);
            }
            for (Acreenciamovimientosaldo acreenciamovimientosaldoListAcreenciamovimientosaldo : acreencia
                    .getAcreenciamovimientosaldoList()) {
                Acreencia oldIdAcreenciaOfAcreenciamovimientosaldoListAcreenciamovimientosaldo = acreenciamovimientosaldoListAcreenciamovimientosaldo
                        .getIdAcreencia();
                acreenciamovimientosaldoListAcreenciamovimientosaldo
                        .setIdAcreencia(acreencia);
                acreenciamovimientosaldoListAcreenciamovimientosaldo = em
                        .merge(acreenciamovimientosaldoListAcreenciamovimientosaldo);
                if (oldIdAcreenciaOfAcreenciamovimientosaldoListAcreenciamovimientosaldo != null) {
                    oldIdAcreenciaOfAcreenciamovimientosaldoListAcreenciamovimientosaldo
                            .getAcreenciamovimientosaldoList()
                            .remove(acreenciamovimientosaldoListAcreenciamovimientosaldo);
                    oldIdAcreenciaOfAcreenciamovimientosaldoListAcreenciamovimientosaldo = em
                            .merge(oldIdAcreenciaOfAcreenciamovimientosaldoListAcreenciamovimientosaldo);
                }
            }
            for (Movimientoacreencia movimientoacreenciaListMovimientoacreencia : acreencia
                    .getMovimientoacreenciaList()) {
                Acreencia oldIdAcreenciaOfMovimientoacreenciaListMovimientoacreencia = movimientoacreenciaListMovimientoacreencia
                        .getIdAcreencia();
                movimientoacreenciaListMovimientoacreencia
                        .setIdAcreencia(acreencia);
                movimientoacreenciaListMovimientoacreencia = em
                        .merge(movimientoacreenciaListMovimientoacreencia);
                if (oldIdAcreenciaOfMovimientoacreenciaListMovimientoacreencia != null) {
                    oldIdAcreenciaOfMovimientoacreenciaListMovimientoacreencia
                            .getMovimientoacreenciaList().remove(
                            movimientoacreenciaListMovimientoacreencia);
                    oldIdAcreenciaOfMovimientoacreenciaListMovimientoacreencia = em
                            .merge(oldIdAcreenciaOfMovimientoacreenciaListMovimientoacreencia);
                }
            }
            for (Saldoacreencia saldoacreenciaListSaldoacreencia : acreencia
                    .getSaldoacreenciaList()) {
                Acreencia oldAcreenciaOfSaldoacreenciaListSaldoacreencia = saldoacreenciaListSaldoacreencia
                        .getAcreencia();
                saldoacreenciaListSaldoacreencia.setAcreencia(acreencia);
                saldoacreenciaListSaldoacreencia = em
                        .merge(saldoacreenciaListSaldoacreencia);
                if (oldAcreenciaOfSaldoacreenciaListSaldoacreencia != null) {
                    oldAcreenciaOfSaldoacreenciaListSaldoacreencia
                            .getSaldoacreenciaList().remove(
                            saldoacreenciaListSaldoacreencia);
                    oldAcreenciaOfSaldoacreenciaListSaldoacreencia = em
                            .merge(oldAcreenciaOfSaldoacreenciaListSaldoacreencia);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAcreencia(acreencia.getId()) != null) {
                throw new PreexistingEntityException("Acreencia " + acreencia
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
     * @param acreencia
     *            Acreencia
     * @throws IllegalOrphanException
     * @throws NonexistentEntityException
     * @throws Exception
     */
    public void edit(Acreencia acreencia) throws IllegalOrphanException,
            NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Acreencia persistentAcreencia = em.find(Acreencia.class,
                    acreencia.getId());
            Cliente numeroIdentificacionClienteOld = persistentAcreencia
                    .getNumeroIdentificacionCliente();
            Cliente numeroIdentificacionClienteNew = acreencia
                    .getNumeroIdentificacionCliente();
            List<Acreenciamovimientosaldo> acreenciamovimientosaldoListOld = persistentAcreencia
                    .getAcreenciamovimientosaldoList();
            List<Acreenciamovimientosaldo> acreenciamovimientosaldoListNew = acreencia
                    .getAcreenciamovimientosaldoList();
            List<Movimientoacreencia> movimientoacreenciaListOld = persistentAcreencia
                    .getMovimientoacreenciaList();
            List<Movimientoacreencia> movimientoacreenciaListNew = acreencia
                    .getMovimientoacreenciaList();
            List<Saldoacreencia> saldoacreenciaListOld = persistentAcreencia
                    .getSaldoacreenciaList();
            List<Saldoacreencia> saldoacreenciaListNew = acreencia
                    .getSaldoacreenciaList();
            List<String> illegalOrphanMessages = null;
            if (numeroIdentificacionClienteNew != null
                    && !numeroIdentificacionClienteNew
                    .equals(numeroIdentificacionClienteOld)) {
                Acreencia oldAcreenciaOfNumeroIdentificacionCliente = numeroIdentificacionClienteNew
                        .getAcreencia();
                if (oldAcreenciaOfNumeroIdentificacionCliente != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages
                            .add("The Cliente "
                                    + numeroIdentificacionClienteNew
                                    + " already has an item of type Acreencia whose numeroIdentificacionCliente column cannot be null. Please make another selection for the numeroIdentificacionCliente field.");
                }
            }
            for (Acreenciamovimientosaldo acreenciamovimientosaldoListOldAcreenciamovimientosaldo : acreenciamovimientosaldoListOld) {
                if (!acreenciamovimientosaldoListNew
                        .contains(acreenciamovimientosaldoListOldAcreenciamovimientosaldo)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages
                            .add("You must retain Acreenciamovimientosaldo "
                                    + acreenciamovimientosaldoListOldAcreenciamovimientosaldo
                                    + " since its idAcreencia field is not nullable.");
                }
            }
            for (Movimientoacreencia movimientoacreenciaListOldMovimientoacreencia : movimientoacreenciaListOld) {
                if (!movimientoacreenciaListNew
                        .contains(movimientoacreenciaListOldMovimientoacreencia)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages
                            .add("You must retain Movimientoacreencia "
                                    + movimientoacreenciaListOldMovimientoacreencia
                                    + " since its idAcreencia field is not nullable.");
                }
            }
            for (Saldoacreencia saldoacreenciaListOldSaldoacreencia : saldoacreenciaListOld) {
                if (!saldoacreenciaListNew
                        .contains(saldoacreenciaListOldSaldoacreencia)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Saldoacreencia "
                            + saldoacreenciaListOldSaldoacreencia
                            + " since its acreencia field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (numeroIdentificacionClienteNew != null) {
                numeroIdentificacionClienteNew = em.getReference(
                        numeroIdentificacionClienteNew.getClass(),
                        numeroIdentificacionClienteNew
                                .getNumeroIdentificacionCliente());
                acreencia
                        .setNumeroIdentificacionCliente(numeroIdentificacionClienteNew);
            }
            List<Acreenciamovimientosaldo> attachedAcreenciamovimientosaldoListNew = new ArrayList<Acreenciamovimientosaldo>();
            for (Acreenciamovimientosaldo acreenciamovimientosaldoListNewAcreenciamovimientosaldoToAttach : acreenciamovimientosaldoListNew) {
                acreenciamovimientosaldoListNewAcreenciamovimientosaldoToAttach = em
                        .getReference(
                                acreenciamovimientosaldoListNewAcreenciamovimientosaldoToAttach
                                        .getClass(),
                                acreenciamovimientosaldoListNewAcreenciamovimientosaldoToAttach
                                        .getId());
                attachedAcreenciamovimientosaldoListNew
                        .add(acreenciamovimientosaldoListNewAcreenciamovimientosaldoToAttach);
            }
            acreenciamovimientosaldoListNew = attachedAcreenciamovimientosaldoListNew;
            acreencia
                    .setAcreenciamovimientosaldoList(acreenciamovimientosaldoListNew);
            List<Movimientoacreencia> attachedMovimientoacreenciaListNew = new ArrayList<Movimientoacreencia>();
            for (Movimientoacreencia movimientoacreenciaListNewMovimientoacreenciaToAttach : movimientoacreenciaListNew) {
                Class classToAttach = movimientoacreenciaListNewMovimientoacreenciaToAttach.getClass();
                Long ipaIdToAttach = movimientoacreenciaListNewMovimientoacreenciaToAttach.getIpaId();
                movimientoacreenciaListNewMovimientoacreenciaToAttach = (Movimientoacreencia) em.getReference(classToAttach, ipaIdToAttach);
                attachedMovimientoacreenciaListNew
                        .add(movimientoacreenciaListNewMovimientoacreenciaToAttach);
            }
            movimientoacreenciaListNew = attachedMovimientoacreenciaListNew;
            acreencia.setMovimientoacreenciaList(movimientoacreenciaListNew);
            List<Saldoacreencia> attachedSaldoacreenciaListNew = new ArrayList<Saldoacreencia>();
            for (Saldoacreencia saldoacreenciaListNewSaldoacreenciaToAttach : saldoacreenciaListNew) {
                saldoacreenciaListNewSaldoacreenciaToAttach = em.getReference(
                        saldoacreenciaListNewSaldoacreenciaToAttach.getClass(),
                        saldoacreenciaListNewSaldoacreenciaToAttach
                                .getSaldoacreenciaPK());
                attachedSaldoacreenciaListNew
                        .add(saldoacreenciaListNewSaldoacreenciaToAttach);
            }
            saldoacreenciaListNew = attachedSaldoacreenciaListNew;
            acreencia.setSaldoacreenciaList(saldoacreenciaListNew);
            acreencia = em.merge(acreencia);
            if (numeroIdentificacionClienteOld != null
                    && !numeroIdentificacionClienteOld
                    .equals(numeroIdentificacionClienteNew)) {
                numeroIdentificacionClienteOld.setAcreencia(null);
                numeroIdentificacionClienteOld = em
                        .merge(numeroIdentificacionClienteOld);
            }
            if (numeroIdentificacionClienteNew != null
                    && !numeroIdentificacionClienteNew
                    .equals(numeroIdentificacionClienteOld)) {
                numeroIdentificacionClienteNew.setAcreencia(acreencia);
                numeroIdentificacionClienteNew = em
                        .merge(numeroIdentificacionClienteNew);
            }
            for (Acreenciamovimientosaldo acreenciamovimientosaldoListNewAcreenciamovimientosaldo : acreenciamovimientosaldoListNew) {
                if (!acreenciamovimientosaldoListOld
                        .contains(acreenciamovimientosaldoListNewAcreenciamovimientosaldo)) {
                    Acreencia oldIdAcreenciaOfAcreenciamovimientosaldoListNewAcreenciamovimientosaldo = acreenciamovimientosaldoListNewAcreenciamovimientosaldo
                            .getIdAcreencia();
                    acreenciamovimientosaldoListNewAcreenciamovimientosaldo
                            .setIdAcreencia(acreencia);
                    acreenciamovimientosaldoListNewAcreenciamovimientosaldo = em
                            .merge(acreenciamovimientosaldoListNewAcreenciamovimientosaldo);
                    if (oldIdAcreenciaOfAcreenciamovimientosaldoListNewAcreenciamovimientosaldo != null
                            && !oldIdAcreenciaOfAcreenciamovimientosaldoListNewAcreenciamovimientosaldo
                            .equals(acreencia)) {
                        oldIdAcreenciaOfAcreenciamovimientosaldoListNewAcreenciamovimientosaldo
                                .getAcreenciamovimientosaldoList()
                                .remove(acreenciamovimientosaldoListNewAcreenciamovimientosaldo);
                        oldIdAcreenciaOfAcreenciamovimientosaldoListNewAcreenciamovimientosaldo = em
                                .merge(oldIdAcreenciaOfAcreenciamovimientosaldoListNewAcreenciamovimientosaldo);
                    }
                }
            }
            for (Movimientoacreencia movimientoacreenciaListNewMovimientoacreencia : movimientoacreenciaListNew) {
                if (!movimientoacreenciaListOld
                        .contains(movimientoacreenciaListNewMovimientoacreencia)) {
                    Acreencia oldIdAcreenciaOfMovimientoacreenciaListNewMovimientoacreencia = movimientoacreenciaListNewMovimientoacreencia
                            .getIdAcreencia();
                    movimientoacreenciaListNewMovimientoacreencia
                            .setIdAcreencia(acreencia);
                    movimientoacreenciaListNewMovimientoacreencia = em
                            .merge(movimientoacreenciaListNewMovimientoacreencia);
                    if (oldIdAcreenciaOfMovimientoacreenciaListNewMovimientoacreencia != null
                            && !oldIdAcreenciaOfMovimientoacreenciaListNewMovimientoacreencia
                            .equals(acreencia)) {
                        oldIdAcreenciaOfMovimientoacreenciaListNewMovimientoacreencia
                                .getMovimientoacreenciaList()
                                .remove(movimientoacreenciaListNewMovimientoacreencia);
                        oldIdAcreenciaOfMovimientoacreenciaListNewMovimientoacreencia = em
                                .merge(oldIdAcreenciaOfMovimientoacreenciaListNewMovimientoacreencia);
                    }
                }
            }
            for (Saldoacreencia saldoacreenciaListNewSaldoacreencia : saldoacreenciaListNew) {
                if (!saldoacreenciaListOld
                        .contains(saldoacreenciaListNewSaldoacreencia)) {
                    Acreencia oldAcreenciaOfSaldoacreenciaListNewSaldoacreencia = saldoacreenciaListNewSaldoacreencia
                            .getAcreencia();
                    saldoacreenciaListNewSaldoacreencia.setAcreencia(acreencia);
                    saldoacreenciaListNewSaldoacreencia = em
                            .merge(saldoacreenciaListNewSaldoacreencia);
                    if (oldAcreenciaOfSaldoacreenciaListNewSaldoacreencia != null
                            && !oldAcreenciaOfSaldoacreenciaListNewSaldoacreencia
                            .equals(acreencia)) {
                        oldAcreenciaOfSaldoacreenciaListNewSaldoacreencia
                                .getSaldoacreenciaList().remove(
                                saldoacreenciaListNewSaldoacreencia);
                        oldAcreenciaOfSaldoacreenciaListNewSaldoacreencia = em
                                .merge(oldAcreenciaOfSaldoacreenciaListNewSaldoacreencia);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = acreencia.getId();
                if (findAcreencia(id) == null) {
                    throw new NonexistentEntityException(
                            "The acreencia with id " + id
                                    + " no longer exists.");
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
            Acreencia acreencia;
            try {
                acreencia = em.getReference(Acreencia.class, id);
                acreencia.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The acreencia with id "
                        + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Acreenciamovimientosaldo> acreenciamovimientosaldoListOrphanCheck = acreencia
                    .getAcreenciamovimientosaldoList();
            for (Acreenciamovimientosaldo acreenciamovimientosaldoListOrphanCheckAcreenciamovimientosaldo : acreenciamovimientosaldoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages
                        .add("This Acreencia ("
                                + acreencia
                                + ") cannot be destroyed since the Acreenciamovimientosaldo "
                                + acreenciamovimientosaldoListOrphanCheckAcreenciamovimientosaldo
                                + " in its acreenciamovimientosaldoList field has a non-nullable idAcreencia field.");
            }
            List<Movimientoacreencia> movimientoacreenciaListOrphanCheck = acreencia
                    .getMovimientoacreenciaList();
            for (Movimientoacreencia movimientoacreenciaListOrphanCheckMovimientoacreencia : movimientoacreenciaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages
                        .add("This Acreencia ("
                                + acreencia
                                + ") cannot be destroyed since the Movimientoacreencia "
                                + movimientoacreenciaListOrphanCheckMovimientoacreencia
                                + " in its movimientoacreenciaList field has a non-nullable idAcreencia field.");
            }
            List<Saldoacreencia> saldoacreenciaListOrphanCheck = acreencia
                    .getSaldoacreenciaList();
            for (Saldoacreencia saldoacreenciaListOrphanCheckSaldoacreencia : saldoacreenciaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages
                        .add("This Acreencia ("
                                + acreencia
                                + ") cannot be destroyed since the Saldoacreencia "
                                + saldoacreenciaListOrphanCheckSaldoacreencia
                                + " in its saldoacreenciaList field has a non-nullable acreencia field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Cliente numeroIdentificacionCliente = acreencia
                    .getNumeroIdentificacionCliente();
            if (numeroIdentificacionCliente != null) {
                numeroIdentificacionCliente.setAcreencia(null);
                numeroIdentificacionCliente = em
                        .merge(numeroIdentificacionCliente);
            }
            em.remove(acreencia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    /**
     * Method findAcreenciaEntities.
     *
     * @return List<Acreencia>
     */
    public List<Acreencia> findAcreenciaEntities() {
        return findAcreenciaEntities(true, -1, -1);
    }

    /**
     * Method findAcreenciaEntities.
     *
     * @param maxResults
     *            int
     * @param firstResult
     *            int
     * @return List<Acreencia>
     */
    public List<Acreencia> findAcreenciaEntities(int maxResults, int firstResult) {
        return findAcreenciaEntities(false, maxResults, firstResult);
    }

    /**
     * Method findAcreenciaEntities.
     *
     * @param all
     *            boolean
     * @param maxResults
     *            int
     * @param firstResult
     *            int
     * @return List<Acreencia>
     */
    private List<Acreencia> findAcreenciaEntities(boolean all, int maxResults,
                                                  int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Acreencia.class));
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
     * Method findAcreencia.
     *
     * @param id
     *            Long
     * @return Acreencia
     */
    public Acreencia findAcreencia(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Acreencia.class, id);
        } finally {
            em.close();
        }
    }

    /**
     * Method getAcreenciaCount.
     *
     * @return int
     */
    public int getAcreenciaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Acreencia> rt = cq.from(Acreencia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
