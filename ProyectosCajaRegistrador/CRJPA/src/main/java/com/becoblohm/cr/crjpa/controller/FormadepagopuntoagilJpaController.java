/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates and open the template in the editor.
 */
package com.becoblohm.cr.crjpa.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.becoblohm.cr.crjpa.controller.exceptions.JpaException;
import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.becoblohm.cr.models.VPosAdditionalData;
import com.becoblohm.cr.types.ActiveValues;

import crjpa.Formadepagopuntoagil;
import crjpa.Transaccionformadepago;
import crjpa.exceptions.PreexistingEntityException;

/**
 * 
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class FormadepagopuntoagilJpaController extends AbstractJPAController {// extends
                                                                              // crjpa.FormadepagopuntoagilJpaController
                                                                              // {

    /**
     * Field serialVersionUID. (value is 1)
     */
    private static final long serialVersionUID = 1L;

    /**
     * Field emf.
     */
    private final EntityManagerFactory emf;

    /**
     * Field jpacontroller.
     */
    private final crjpa.FormadepagopuntoagilJpaController jpacontroller;

    /**
     * Field entityName.
     */
    private static String entityName = "Formadepagopuntoagil";

    /**
     * Constructor for FormadepagopuntoagilJpaController.
     * 
     * @param emf EntityManagerFactory
     */
    public FormadepagopuntoagilJpaController(EntityManagerFactory emf) {
        super(AbstractJPAController.POSSOURCE, entityName);
        this.emf = emf;
        this.jpacontroller = new crjpa.FormadepagopuntoagilJpaController(emf);
    }

    /**
     * EDIT `modeloCR`.`formadepago_puntoagil`;select * from
     * modeloCR.formadepago_puntoagil where fecha=(select max(fecha) from
     * modeloCR.formadepago_puntoagil where codigo_respuesta='00');
     * 
     * @param vtid String
     * @param codigoRespuesta String
     * @return List<VPosAdditionalData>
     */

    public List<VPosAdditionalData> findFormadepagopuntoagil(String vtid, String codigoRespuesta) {
        List<VPosAdditionalData> data = new ArrayList<VPosAdditionalData>();
        List<Formadepagopuntoagil> dataJPA = new ArrayList<Formadepagopuntoagil>();
        EntityManager em = jpacontroller.getEntityManager();
        Query query = em
                .createQuery("SELECT f FROM Formadepagopuntoagil f WHERE f.fecha=(SELECT max(a.fecha) FROM Formadepagopuntoagil a WHERE a.vtid = :vtid AND  a.codigoRespuesta = :codigo)");
        query.setParameter("codigo", codigoRespuesta);
        query.setParameter("vtid", vtid);
        try {
            dataJPA = query.getResultList();
            if (dataJPA != null) {
                for (Formadepagopuntoagil formadepagopuntoagil : dataJPA) {
                    data.add(fromJPA(formadepagopuntoagil));
                }
            }
            return data;
        } catch (NoResultException ex) {
            return null;
        } finally {
            em.close();
        }
    }

    /**
     * Method findFormadepagopuntoagil.
     * 
     * @param vtid String
     * @param numseq int
     * @return VPosAdditionalData
     */
    public VPosAdditionalData findFormadepagopuntoagil(String vtid, int numseq) {
        EntityManager em = jpacontroller.getEntityManager();
        Formadepagopuntoagil fdppa = new Formadepagopuntoagil();
        Query query = em
                .createQuery("SELECT f FROM Formadepagopuntoagil f WHERE f.vtid = :vtid AND f.secuencia = :numseq");
        query.setParameter("vtid", vtid);
        query.setParameter("numseq", numseq);
        VPosAdditionalData result = null;
        try {
            fdppa = (Formadepagopuntoagil) query.getSingleResult();
            result = fromJPA(fdppa);
        } catch (Exception e) {
            // e.printStackTrace();
        } finally {
            em.close();
        }
        return result;
    }

    /**
     * Method findLastFormadepagopuntoagilByVtid.
     * 
     * @param vtid String
     * @return VPosAdditionalData
     */
    public VPosAdditionalData findLastFormadepagopuntoagilByVtid(String vtid) {
        EntityManager em = jpacontroller.getEntityManager();
        Formadepagopuntoagil fdppa = new Formadepagopuntoagil();
        Query query = em
                .createQuery("SELECT ff FROM Formadepagopuntoagil ff WHERE ff.secuencia = (SELECT max(f.secuencia) FROM Formadepagopuntoagil f WHERE f.vtid = :vtid)");
        query.setParameter("vtid", vtid);
        VPosAdditionalData result = null;
        try {
            fdppa = (Formadepagopuntoagil) query.getSingleResult();
            result = fromJPA(fdppa);
        } catch (Exception e) {
            // e.printStackTrace();
        } finally {
            em.close();
        }
        return result;
    }

    /**
     * Method findFormadepagopuntoagilByNumseq.
     * 
     * @param numseq int
     * @return VPosAdditionalData
     */
    @Deprecated
    public VPosAdditionalData findFormadepagopuntoagilByNumseq(int numseq) {
        EntityManager em = jpacontroller.getEntityManager();
        Formadepagopuntoagil fdppa = new Formadepagopuntoagil();
        Query query = em.createQuery("SELECT f FROM Formadepagopuntoagil f WHERE f.secuencia = :numseq");
        query.setParameter("numseq", numseq);
        try {
            fdppa = (Formadepagopuntoagil) query.getSingleResult();

            VPosAdditionalData response = fromJPA(fdppa);
            response.setPmPermitReverse(ActiveValues
                    .get(fdppa.getIdTransaccionformadepago().getIdFormadepago().getPermiteReverso()).getValue());

            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    /**
     * Method findFormadepagopuntoagil.
     * 
     * @param id long
     * @return VPosAdditionalData
     */
    public VPosAdditionalData findFormadepagopuntoagil(long id) {

        return fromJPA(jpacontroller.findFormadepagopuntoagil(id));

    }

    /**
     * Method toJPA.
     * 
     * @param data VPosAdditionalData
     * @return Formadepagopuntoagil
     */
    private Formadepagopuntoagil toJPA(VPosAdditionalData data) {
        Formadepagopuntoagil fdppa = new Formadepagopuntoagil();
        if (data != null) {
            fdppa.setArchivo(data.getFile());
            fdppa.setCodigoRespuesta(data.getResponseId());
            fdppa.setId(null);
            fdppa.setIdTransaccionformadepago(new Transaccionformadepago(data.getIdTransactionPaymentMethod()));
            fdppa.setMensajeRespuesta(data.getResponseMessage());
            fdppa.setSecuencia(data.getSeqNum());
            fdppa.setTarjeta(data.getCardNumber());
            fdppa.setTitular(data.getOwnerId());
            fdppa.setVtid(data.getVtid());

        }
        return fdppa;
    }

    /**
     * Method fromJPA.
     * 
     * @param fdppa Formadepagopuntoagil
     * @return VPosAdditionalData
     */
    private VPosAdditionalData fromJPA(Formadepagopuntoagil fdppa) {
        VPosAdditionalData data = new VPosAdditionalData();

        if (data != null) {
            data.setCardNumber(fdppa.getTarjeta());
            data.setDate(fdppa.getFecha());
            data.setFile(fdppa.getArchivo());
            data.setId(fdppa.getId());
            data.setIdTransactionPaymentMethod(fdppa.getIdTransaccionformadepago().getId());
            data.setOwnerId(fdppa.getTitular());
            data.setResponseId(fdppa.getCodigoRespuesta());
            data.setResponseMessage(fdppa.getMensajeRespuesta());
            data.setSeqNum(fdppa.getSecuencia());
            data.setVtid(fdppa.getVtid());
            data.setPmPermitReverse(ActiveValues
                    .get(fdppa.getIdTransaccionformadepago().getIdFormadepago().getPermiteReverso()).getValue());
            data.setCashierId(fdppa.getIdTransaccionformadepago().getIdTransaccion().getIdSesion().getIdUsuario()
                    .getFicha());
        }
        return data;
    }

    /**
     * Method create.
     * 
     * @param data VPosAdditionalData
     * @throws JpaException
     */
    public void create(VPosAdditionalData data) throws JpaException {

        Formadepagopuntoagil fdppa = toJPA(data);
        crjpa.FormadepagopuntoagilJpaController controller = new crjpa.FormadepagopuntoagilJpaController(this.emf);
        if (fdppa != null) {
            try {
                controller.create(fdppa);
            } catch (PreexistingEntityException e) {

                e.printStackTrace();
            } catch (Exception e) {

                e.printStackTrace();
                throw new JpaException();
            }
        }
    }

    /**
     * Method destroy.
     * 
     * @param id long
     * @throws JpaException
     */
    public void destroy(long id) throws JpaException {
        try {
            this.jpacontroller.destroy(id);
        } catch (Exception e) {

            e.printStackTrace();
            throw new JpaException();
        }
    }

    /**
     * Method findByTransactionPaymentId.
     * 
     * @param idformadepagopuntoagil long
     * @return VPosAdditionalData
     */
    public VPosAdditionalData findByTransactionPaymentId(long idformadepagopuntoagil) {
        VPosAdditionalData data = null;
        Formadepagopuntoagil dataJPA = new Formadepagopuntoagil();
        EntityManager em = jpacontroller.getEntityManager();
        Query query = em
                .createQuery("SELECT f FROM Formadepagopuntoagil f WHERE f.idTransaccionformadepago.id = :id");
        query.setParameter("id", idformadepagopuntoagil);
        query.setMaxResults(1);
        try {
            dataJPA = (Formadepagopuntoagil) query.getSingleResult();
            if (dataJPA != null) {
                data = fromJPA(dataJPA);
            }
            return data;
        } catch (NoResultException ex) {
            return null;
        } finally {
            em.close();
        }

    }

    /**
     * Method findByTransactionId.
     * 
     * @param idtransaccion long
     * @return VPosAdditionalData
     */
    public VPosAdditionalData findByTransactionId(long idtransaccion) {
        VPosAdditionalData data = null;
        Formadepagopuntoagil dataJPA = new Formadepagopuntoagil();
        EntityManager em = jpacontroller.getEntityManager();
        Query query = em
                .createQuery("SELECT f FROM Formadepagopuntoagil f WHERE f.idTransaccionformadepago.idTransaccion.id = :id");
        query.setParameter("id", idtransaccion);
        query.setMaxResults(1);
        try {
            dataJPA = (Formadepagopuntoagil) query.getSingleResult();
            if (dataJPA != null) {
                data = fromJPA(dataJPA);
            }
            return data;
        } catch (NoResultException ex) {
            return null;
        } finally {
            em.close();
        }

    }

    // public List<VPosAdditionalData> findByTransactionPaymentId(long
    // idTransaccionformadepago) {
    // List<VPosAdditionalData> data = new ArrayList<VPosAdditionalData>();
    // List<Formadepagopuntoagil> dataJPA = new
    // ArrayList<Formadepagopuntoagil>();
    // EntityManager em = jpacontroller.getEntityManager();
    // Query query =
    // em.createQuery("SELECT f FROM Formadepagopuntoagil f WHERE
    // f.idTransaccionformadepago.id = :id");
    // query.setParameter("id", idTransaccionformadepago);
    // try {
    // dataJPA = query.getResultList();
    // if(dataJPA != null ){
    // for (Formadepagopuntoagil formadepagopuntoagil : dataJPA) {
    // data.add(fromJPA(formadepagopuntoagil));
    // }
    // }
    // return data;
    // } finally {
    // em.close();
    // }
    //
    // }

}
