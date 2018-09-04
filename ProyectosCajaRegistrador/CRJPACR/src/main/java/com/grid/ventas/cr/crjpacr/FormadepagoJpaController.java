/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.grid.ventas.cr.crjpacr;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.grid.ventas.cr.crjpacr.exceptions.IllegalOrphanException;
import com.grid.ventas.cr.crjpacr.exceptions.NonexistentEntityException;
import com.grid.ventas.cr.crjpacr.exceptions.PreexistingEntityException;
import com.grid.ventas.cr.crjpacrsrc.Formadepago;

/**
 *
 * @author eve0017909
 */
public class FormadepagoJpaController extends AbstractJPAController {

    /*
     * FIELDS DECLARATION
     */
    private final EntityManagerFactory emf;

    private static String entityName = "Formadepago";

    private static final Logger log = LoggerFactory.getLogger(FormadepagoJpaController.class);

    public FormadepagoJpaController(EntityManagerFactory emf) {
        super(AbstractJPAController.SERVERSOURCE, entityName);
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Formadepago formadepago) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(formadepago);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findFormadepago(formadepago.getCodformadepago()) != null) {
                throw new PreexistingEntityException("CR.Formadepago " + formadepago + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Formadepago formadepago) throws IllegalOrphanException,
            NonexistentEntityException,
            Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            formadepago = em.merge(formadepago);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = formadepago.getCodformadepago();
                if (findFormadepago(id) == null) {
                    throw new NonexistentEntityException(
                            "The CR.formadepago with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Formadepago formadepago;
            try {
                formadepago = em.getReference(Formadepago.class, id);
                formadepago.getCodformadepago();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The CR.formadepago with id " + id + " no longer exists.",
                        enfe);
            }
            em.remove(formadepago);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Formadepago> findFormadepagoEntities() {
        return findFormadepagoEntities(true, -1, -1);
    }

    public List<Formadepago> findFormadepagoEntities(int maxResults, int firstResult) {
        return findFormadepagoEntities(false, maxResults, firstResult);
    }

    private List<Formadepago> findFormadepagoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Formadepago.class));
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

    public Formadepago findFormadepago(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Formadepago.class, id);
        } finally {
            em.close();
        }
    }

    public int getFormadepagoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Formadepago> rt = cq.from(Formadepago.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public Formadepago findPaymentMethodByType(short codeCR400) {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createNamedQuery("Formadepago.findByTipoformadepago");
            query.setParameter("tipoformadepago", codeCR400);
            List<Formadepago> result = query.getResultList();
            return result.get(0);
        } catch (Exception e) {
            log.error("ERROR trying to get the payment method code " + codeCR400
                    + " from CR.FORMADEPAGO. The result list size is zero");
            throw new NoResultException(
                    "ERROR trying to get the payment method code " + codeCR400 + " from CR.FORMADEPAGO");
        } finally {
            em.close();
        }
    }
}
