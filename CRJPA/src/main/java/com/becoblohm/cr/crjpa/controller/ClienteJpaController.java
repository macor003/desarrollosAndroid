/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates and open the template in the editor.
 */
package com.becoblohm.cr.crjpa.controller;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.becoblohm.cr.crjpa.controller.exceptions.JpaException;
import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.becoblohm.cr.models.Client;
import com.becoblohm.cr.models.EconomicActivity;
import com.becoblohm.cr.types.ActiveValues;

import crjpa.Cliente;
import crjpa.Morosidad;
import crjpa.Opcion;

/**
 * 
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class ClienteJpaController extends AbstractJPAController {

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
    private final crjpa.ClienteJpaController jpacontroller;

    /**
     * Field cliente.
     */
    private Cliente cliente;

    /**
     * Field entityName.
     */
    private static String entityName = "Cliente";

    /**
     * Field ID_REGISTRO_NULL_EXO_TIPO_400. (value is 142)
     */
    public static final long ID_REGISTRO_NULL_EXO_TIPO_400 = 142L;

    /**
     * Constructor for ClienteJpaController.
     * 
     * @param emf EntityManagerFactory
     */
    public ClienteJpaController(EntityManagerFactory emf) {
        super(AbstractJPAController.POSSOURCE, entityName);
        this.jpacontroller = new crjpa.ClienteJpaController(emf);
        this.emf = emf;
    }

    /*
     * Metodos implemetados
     */
    /*
     * public List<Cliente> findClienteEntities(String numeroId) { EntityManager em =
     * getEntityManager(); Query query = em.createQuery(
     * "SELECT c FROM Cliente c WHERE c.numeroIdentificacion = :numeroIdentificacion" );
     * query.setParameter("numeroIdentificacion",numeroId); List<Cliente> list =
     * query.getResultList(); return list; }
     */

    /*
     * Metodos implemetados
     */
    /**
     * Method findClienteEntitiesById in CRPOS.
     * 
     * @param numeroId String
     * @return Client
     */
    public Client findClienteEntitiesById(String numeroId) {
        EntityManager em = jpacontroller.getEntityManager();
        Query query = em
                .createQuery("SELECT c FROM Cliente c WHERE c.numeroIdentificacionCliente = :numeroIdentificacionCliente");
        query.setParameter("numeroIdentificacionCliente", numeroId);

        Client result = null;
        try {
            cliente = (Cliente) query.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("c.b.c.crjpa.c.ClienteJpaController - NoResultException -> Client " + numeroId
                    + " not founded in CRPOS");
            result = null;
        } catch (Exception e) {
            e.printStackTrace();
            result = null;
        } finally {
            if (cliente != null) {
                result = fromJPA(cliente);
            }
            if (em != null) {
                em.clear();
                em.close();
            }
        }
        return result;
    }

    /**
     * Method findClienteEntitiesByFiscalId.
     * 
     * @param numeroFiscalId String
     * @return Client
     */
    public Client findClienteEntitiesByFiscalId(String numeroFiscalId) {
        EntityManager em = jpacontroller.getEntityManager();
        Query query = em
                .createQuery("SELECT c FROM Cliente c WHERE c.numeroIdentificacionTributario = :numeroIdentificacionTributario");
        query.setParameter("numeroIdentificacionTributario", numeroFiscalId);

        Client result = null;
        try {
            cliente = (Cliente) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            result = null;
        } finally {
            if (cliente != null) {
                result = fromJPA(cliente);
            }
            if (em != null) {
                em.clear();
                em.close();
            }
        }
        return result;
    }

    // public void create(Cliente cliente) {
    // super.create(cliente);
    // }

    /**
     * Method fromJPA.
     * 
     * @param clientJpa Cliente
     * @return Client
     */
    public Client fromJPA(Cliente clientJpa) {
        ExoneradotipoJpaController exonerationController = new ExoneradotipoJpaController(this.emf);
        TipoclienteJpaController clientTypeController = new TipoclienteJpaController(this.emf);
        ClientemensajeJpaController messageController = new ClientemensajeJpaController(this.emf);
        GiroactividadeconomicaJpaController giro = new GiroactividadeconomicaJpaController(this.emf);
        // TipodocumentoJpaController documentType = new
        // TipodocumentoJpaController(this.emf);
        Client client = new Client();
        if (clientJpa != null) {
            // client.getClientType().setValidateID(clientTypeController.findTipoclienteByCode(clientJpa.getIdTipocliente().getId()).getValidateID());
            client.setAddress(clientJpa.getDireccion());
            client.setClientType(clientTypeController.fromJPA(clientJpa.getIdTipocliente()));
            if (clientJpa.getIdGiroactividadeconomica() != null) {
                client.setEconomicActivity(giro.fromJPA(clientJpa.getIdGiroactividadeconomica()));
            } else {
                client.setEconomicActivity(new EconomicActivity(new Long(0)));
            }

            client.setEmail(clientJpa.getCorreoElectronico());
            if (clientJpa.getFicha() != null) {
                client.setEmployeeId(clientJpa.getFicha().longValue());
            }

            if (clientJpa.getIdExoneradotipo() != null
                    && clientJpa.getIdExoneradotipo().getId().longValue() != getIdExoneradotipoNull()) {
                client.setExonerationType(exonerationController.fromJPA(clientJpa.getIdExoneradotipo()));
            }
            client.setFiscalAddress(clientJpa.getDireccionFiscal());
            client.setFiscalId(clientJpa.getNumeroIdentificacionTributario());
            client.setIdNumber(clientJpa.getNumeroIdentificacionCliente());

            client.setName(clientJpa.getNombre());
            client.setPhone(clientJpa.getTelefono());
            client.setRegisterDate(clientJpa.getFecha());

            if (clientJpa.getEsagenteretencion() != null) {
                client.setRetentionAgent(clientJpa.getEsagenteretencion());
            } else {
                client.setRetentionAgent(client.getRetentionAgent());
            }
            client.setUpdateDate(clientJpa.getFechaActualizacion());
            client.setMessages(messageController.fromJPA(clientJpa.getClientemensajeList()));
            client.setRetentionAgent(clientJpa.getEsagenteretencion());
            // ArrayList<DocumentType>allowedDocumentTypes=new
            // ArrayList<DocumentType>();
            // if(clientJpa.getIdTipocliente().getTipoclientetipodocumentoList()!=null){
            // for(Tipoclientetipodocumento
            // m:clientJpa.getIdTipocliente().getTipoclientetipodocumentoList()){
            // allowedDocumentTypes.add(documentType.fromJPA(m.getIdTipodocumento()));
            // }
            // }
            // client.getClientType().setAllowedDocumentTypes(allowedDocumentTypes);

            ArrayList<Long> nonAllowedPayments = new ArrayList<Long>();
            if (clientJpa.getMorosidadList() != null) {
                for (Morosidad m : clientJpa.getMorosidadList()) {
                    if (ActiveValues.get(m.getEstaactivo()).getValue()) {
                        nonAllowedPayments.add(m.getIdFormadepago().getId());
                    }
                }
            }
            client.setNonAllowedPayments(nonAllowedPayments);

            client.setDiplomatic(ActiveValues.get(clientJpa.getEsdiplomatico()).getValue());
            client.setBigClient(clientJpa.getEsgrande());
        }
        return client;
    }

    /**
     * Method getIdExoneradotipoNull.
     * 
     * @return long
     */
    private long getIdExoneradotipoNull() {
        crjpa.OpcionJpaController opcionJpaController = new crjpa.OpcionJpaController(emf);
        Opcion idExoneradoNull = opcionJpaController.findOpcion(ID_REGISTRO_NULL_EXO_TIPO_400);
        return idExoneradoNull == null ? 0L : Long.valueOf(idExoneradoNull.getValor());
    }

    /**
     * Method toJPA.
     * 
     * @param client Client
     * @return Cliente
     */
    public Cliente toJPA(Client client) {
        Cliente cl = new Cliente();
        TipoclienteJpaController tipoCliente = new TipoclienteJpaController(this.emf);
        GiroactividadeconomicaJpaController giroActividad = new GiroactividadeconomicaJpaController(this.emf);
        ExoneradotipoJpaController exoneradoTipoClientejpa = new ExoneradotipoJpaController(this.emf);

        // cl.setContribuyente("");
        cl.setCorreoElectronico(client.getEmail());
        cl.setDireccion(client.getAddress());
        cl.setDireccionFiscal(client.getFiscalAddress());
        // cl.setEsagenteretencion(client.isRetentionAgent());
        cl.setFecha(client.getRegisterDate());
        cl.setFechaActualizacion(client.getUpdateDate());
        cl.setFicha(BigInteger.valueOf(client.getEmployeeId()));
        // cl.setIdExoneradotipo(null);
        if (client.getClientType().getId() > 0) {
            cl.setIdGiroactividadeconomica(giroActividad
                    .findGiroactividadeconomicaByCode(client.getClientType().getId()));
        } else {
            cl.setIdGiroactividadeconomica(null);
        }
        if (client.getExonerationType() != null && client.getExonerationType().getId() > 0) {
            cl.setIdExoneradotipo(exoneradoTipoClientejpa.findExoneradoTotal());
        } else {
            cl.setIdExoneradotipo(null);
        }
        cl.setIdTipocliente(tipoCliente.findTipocliente(client.getClientType().getId()));
        cl.setNumeroIdentificacionTributario(client.getFiscalId());
        cl.setNombre(client.getName());
        cl.setNumeroIdentificacionCliente(client.getIdNumber());
        cl.setTelefono(client.getPhone());
        cl.setEsagenteretencion(client.getRetentionAgent());
        cl.setEstasincronizado(client.getIsSinc());
        ActiveValues tmp = ActiveValues.get(client.getDiplomatic());
        cl.setEsdiplomatico(tmp.getString());
        cl.setEsgrande(client.getBigClient());

        return cl;

    }

    /**
     * Method create.
     * 
     * @param client Client
     * @throws JpaException
     */
    public void create(Client client) throws JpaException {
        EntityManager tmpEm = null;
        try {

            // Seteando fecha de actualizacion
            client.setUpdateDate(new Date());

            tmpEm = emf.createEntityManager();
            Cliente clienteJpa = toJPA(client);
            tmpEm.getTransaction().begin();
            tmpEm.persist(clienteJpa);
            tmpEm.getTransaction().commit();
            // jpacontroller.create(clienteJpa);
            client = fromJPA(clienteJpa);

        }
        // catch (PreexistingEntityException e) {
        //
        // e.printStackTrace();
        // }
        catch (Exception e) {
            System.out.println("c.b.c.crjpa.c.ClienteJpaController.create - Error persisting client");
            e.printStackTrace();
            throw new JpaException();
        } finally {
            if (tmpEm != null) {
                tmpEm.clear();
                tmpEm.close();
            }
        }
    }

    /**
     * Method merge.
     * 
     * @param clienttmp Client
     * @return boolean
     * @throws JpaException
     */
    public boolean merge(Client clienttmp) throws JpaException {
        boolean result = true;
        try {

            // Seteando fecha de actualizacion
            clienttmp.setUpdateDate(new Date());

            EntityManager em = this.emf.createEntityManager();
            em.getTransaction().begin();

            em.merge(toJPA(clienttmp));

            em.getTransaction().commit();

            em.close();
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
            throw new JpaException();
        }
        return result;
    }

}
