/*
 * To change this license header, choose License Headers in Project Properties. To change
 * this template file, choose Tools | Templates and open the template in the editor.
 */
package com.grid.ventas.cr.crjpaventas;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.becoblohm.cr.models.CreditsMovement;
import com.grid.ventas.cr.crjpaventas.exceptions.NonexistentEntityException;
import com.grid.ventas.cr.crjpaventas.exceptions.PreexistingEntityException;
import com.grid.ventas.cr.crjpaventassrc.Opevdec;
import com.grid.ventas.cr.crjpaventassrc.OpevdecPK;

/**
 *
 * @author eve0017909
 */
public class OpevdecJpaController implements Serializable {

    private static final long serialVersionUID = 9055609472159493016L;

    public OpevdecJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Opevdec opevdec) throws PreexistingEntityException, Exception {
        if (opevdec.getOpevdecPK() == null) {
            opevdec.setOpevdecPK(new OpevdecPK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(opevdec);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findOpevdec(opevdec.getOpevdecPK()) != null) {
                throw new PreexistingEntityException("Opevdec " + opevdec + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Opevdec opevdec) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            opevdec = em.merge(opevdec);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                OpevdecPK id = opevdec.getOpevdecPK();
                if (findOpevdec(id) == null) {
                    throw new NonexistentEntityException("The opevdec with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(OpevdecPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Opevdec opevdec;
            try {
                opevdec = em.getReference(Opevdec.class, id);
                opevdec.getOpevdecPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The opevdec with id " + id + " no longer exists.", enfe);
            }
            em.remove(opevdec);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Opevdec> findOpevdecEntities() {
        return findOpevdecEntities(true, -1, -1);
    }

    public List<Opevdec> findOpevdecEntities(int maxResults, int firstResult) {
        return findOpevdecEntities(false, maxResults, firstResult);
    }

    private List<Opevdec> findOpevdecEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Opevdec.class));
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

    public Opevdec findOpevdec(OpevdecPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Opevdec.class, id);
        } finally {
            em.close();
        }
    }

    public int getOpevdecCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Opevdec> rt = cq.from(Opevdec.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public Opevdec toJPA(CreditsMovement movement) {
        final String CASH = "1111111111";
        final String WHITESPACE = " ";

        OpevdecPK opevdecPK = new OpevdecPK((short) movement.getStore(), (short) movement.getPos(), 1,
                Long.valueOf(movement.getCreditsAccount().getCreditsId()), movement.getDate(), CASH, (short) 1,
                movement.getCreditsAccount().getClient().getIdNumber());
        Opevdec opevdec = new Opevdec(opevdecPK, movement.getAmount().abs().getValue(), WHITESPACE, "null", "null",
                'C', movement.getProcessId(), 0, "null", '1', 'S', 'N');

        return opevdec;
    }

    public int findCheckRequestByAccount(short storeNumber, short posNumber, long creditsAccount,
                                         String clientID) {
        EntityManager em = getEntityManager();
        int resultNumber = 1;

        try {
            Query query = em
                    .createQuery("SELECT o FROM Opevdec o WHERE o.opevdecPK.ntievdec = :ntievdec AND o.opevdecPK.ncajvdec = :ncajvdec AND o.opevdecPK.nacrvdec = :nacrvdec AND o.opevdecPK.cclivdec = :cclivdec");
            query.setParameter("ntievdec", storeNumber);
            query.setParameter("ncajvdec", posNumber);
            query.setParameter("nacrvdec", creditsAccount);
            query.setParameter("cclivdec", clientID.toUpperCase());

            resultNumber = query.getResultList().size();
        } catch (Exception e) {
            throw new NoResultException(
                    "ERROR - There was an error trying to get the max VENTAS.OPEVDEC for the credits account "
                            + creditsAccount);
        } finally {
            em.close();
        }

        return (resultNumber == Integer.MAX_VALUE) ? 1 : (resultNumber + 1);
    }
}
