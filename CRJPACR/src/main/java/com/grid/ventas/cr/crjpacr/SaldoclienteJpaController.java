/*
 * To change this license header, choose License Headers in Project Properties. To change
 * this template file, choose Tools | Templates and open the template in the editor.
 */
package com.grid.ventas.cr.crjpacr;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.grid.ventas.cr.crjpacr.exceptions.IllegalOrphanException;
import com.grid.ventas.cr.crjpacr.exceptions.NonexistentEntityException;
import com.grid.ventas.cr.crjpacr.exceptions.PreexistingEntityException;
import com.grid.ventas.cr.crjpacrsrc.Afiliado;
import com.grid.ventas.cr.crjpacrsrc.Saldocliente;

/**
 *
 * @author eve0017909
 */
public class SaldoclienteJpaController implements Serializable {

    public SaldoclienteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Saldocliente saldocliente)
            throws IllegalOrphanException, PreexistingEntityException, Exception {
        List<String> illegalOrphanMessages = null;
        Afiliado afiliadoOrphanCheck = saldocliente.getAfiliado();
        if (afiliadoOrphanCheck != null) {
            Saldocliente oldSaldoclienteOfAfiliado = afiliadoOrphanCheck.getSaldocliente();
            if (oldSaldoclienteOfAfiliado != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Afiliado " + afiliadoOrphanCheck
                        + " already has an item of type Saldocliente whose afiliado column cannot be null. Please make another selection for the afiliado field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Afiliado afiliado = saldocliente.getAfiliado();
            if (afiliado != null) {
                afiliado = em.getReference(afiliado.getClass(), afiliado.getCodafiliado());
                saldocliente.setAfiliado(afiliado);
            }
            em.persist(saldocliente);
            if (afiliado != null) {
                afiliado.setSaldocliente(saldocliente);
                afiliado = em.merge(afiliado);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSaldocliente(saldocliente.getCodcliente()) != null) {
                throw new PreexistingEntityException("Saldocliente " + saldocliente + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Saldocliente saldocliente)
            throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Saldocliente persistentSaldocliente = em.find(Saldocliente.class, saldocliente.getCodcliente());
            Afiliado afiliadoOld = persistentSaldocliente.getAfiliado();
            Afiliado afiliadoNew = saldocliente.getAfiliado();
            List<String> illegalOrphanMessages = null;
            if (afiliadoNew != null && !afiliadoNew.equals(afiliadoOld)) {
                Saldocliente oldSaldoclienteOfAfiliado = afiliadoNew.getSaldocliente();
                if (oldSaldoclienteOfAfiliado != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Afiliado " + afiliadoNew
                            + " already has an item of type Saldocliente whose afiliado column cannot be null. Please make another selection for the afiliado field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (afiliadoNew != null) {
                afiliadoNew = em.getReference(afiliadoNew.getClass(), afiliadoNew.getCodafiliado());
                saldocliente.setAfiliado(afiliadoNew);
            }
            saldocliente = em.merge(saldocliente);
            if (afiliadoOld != null && !afiliadoOld.equals(afiliadoNew)) {
                afiliadoOld.setSaldocliente(null);
                afiliadoOld = em.merge(afiliadoOld);
            }
            if (afiliadoNew != null && !afiliadoNew.equals(afiliadoOld)) {
                afiliadoNew.setSaldocliente(saldocliente);
                afiliadoNew = em.merge(afiliadoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = saldocliente.getCodcliente();
                if (findSaldocliente(id) == null) {
                    throw new NonexistentEntityException("The saldocliente with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Saldocliente saldocliente;
            try {
                saldocliente = em.getReference(Saldocliente.class, id);
                saldocliente.getCodcliente();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The saldocliente with id " + id + " no longer exists.",
                        enfe);
            }
            Afiliado afiliado = saldocliente.getAfiliado();
            if (afiliado != null) {
                afiliado.setSaldocliente(null);
                afiliado = em.merge(afiliado);
            }
            em.remove(saldocliente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Saldocliente> findSaldoclienteEntities() {
        return findSaldoclienteEntities(true, -1, -1);
    }

    public List<Saldocliente> findSaldoclienteEntities(int maxResults, int firstResult) {
        return findSaldoclienteEntities(false, maxResults, firstResult);
    }

    private List<Saldocliente> findSaldoclienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Saldocliente.class));
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

    public Saldocliente findSaldocliente(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Saldocliente.class, id);
        } finally {
            em.close();
        }
    }

    public int getSaldoclienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Saldocliente> rt = cq.from(Saldocliente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
