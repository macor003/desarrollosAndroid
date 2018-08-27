/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crjpa400;

import crjpa400.exceptions.NonexistentEntityException;
import crjpa400.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author eve0014321
 */
public class FormadepagotipoclienteJpaController implements Serializable {

    public FormadepagotipoclienteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Formadepagotipocliente formadepagotipocliente) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Formadepago idFormadepago = formadepagotipocliente.getIdFormadepago();
            if (idFormadepago != null) {
                idFormadepago = em.getReference(idFormadepago.getClass(), idFormadepago.getId());
                formadepagotipocliente.setIdFormadepago(idFormadepago);
            }
            Tipocliente idTipocliente = formadepagotipocliente.getIdTipocliente();
            if (idTipocliente != null) {
                idTipocliente = em.getReference(idTipocliente.getClass(), idTipocliente.getId());
                formadepagotipocliente.setIdTipocliente(idTipocliente);
            }
            em.persist(formadepagotipocliente);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findFormadepagotipocliente(formadepagotipocliente.getId()) != null) {
                throw new PreexistingEntityException("Formadepagotipocliente " + formadepagotipocliente + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Formadepagotipocliente formadepagotipocliente) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Formadepagotipocliente persistentFormadepagotipocliente = em.find(Formadepagotipocliente.class, formadepagotipocliente.getId());
            Formadepago idFormadepagoOld = persistentFormadepagotipocliente.getIdFormadepago();
            Formadepago idFormadepagoNew = formadepagotipocliente.getIdFormadepago();
            Tipocliente idTipoclienteOld = persistentFormadepagotipocliente.getIdTipocliente();
            Tipocliente idTipoclienteNew = formadepagotipocliente.getIdTipocliente();
            if (idFormadepagoNew != null) {
                idFormadepagoNew = em.getReference(idFormadepagoNew.getClass(), idFormadepagoNew.getId());
                formadepagotipocliente.setIdFormadepago(idFormadepagoNew);
            }
            if (idTipoclienteNew != null) {
                idTipoclienteNew = em.getReference(idTipoclienteNew.getClass(), idTipoclienteNew.getId());
                formadepagotipocliente.setIdTipocliente(idTipoclienteNew);
            }
            formadepagotipocliente = em.merge(formadepagotipocliente);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = formadepagotipocliente.getId();
                if (findFormadepagotipocliente(id) == null) {
                    throw new NonexistentEntityException("The formadepagotipocliente with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Formadepagotipocliente formadepagotipocliente;
            try {
                formadepagotipocliente = em.getReference(Formadepagotipocliente.class, id);
                formadepagotipocliente.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The formadepagotipocliente with id " + id + " no longer exists.", enfe);
            }
            em.remove(formadepagotipocliente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Formadepagotipocliente> findFormadepagotipoclienteEntities() {
        return findFormadepagotipoclienteEntities(true, -1, -1);
    }

    public List<Formadepagotipocliente> findFormadepagotipoclienteEntities(int maxResults, int firstResult) {
        return findFormadepagotipoclienteEntities(false, maxResults, firstResult);
    }

    private List<Formadepagotipocliente> findFormadepagotipoclienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Formadepagotipocliente.class));
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

    public Formadepagotipocliente findFormadepagotipocliente(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Formadepagotipocliente.class, id);
        } finally {
            em.close();
        }
    }

    public int getFormadepagotipoclienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Formadepagotipocliente> rt = cq.from(Formadepagotipocliente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
