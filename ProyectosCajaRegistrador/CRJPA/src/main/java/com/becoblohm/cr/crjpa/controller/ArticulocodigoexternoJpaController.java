/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.crjpa.controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.becoblohm.cr.interfaces.AbstractJPAController;

import crjpa.Articulo;
import crjpa.Articulocodigoexterno;

/**
 */
public class ArticulocodigoexternoJpaController extends AbstractJPAController {

    /**
     * 
     */
    private crjpa.ArticulocodigoexternoJpaController jpacontroller;

    /**
     * Field emf.
     */
    private EntityManagerFactory emf;

    /**
     * Field serialVersionUID. (value is 1)
     */
    private static final long serialVersionUID = 1L;

    /**
     * Field entityName.
     */
    private static String entityName = "Articulocodigoexterno";

    /*
     * Metodos propios
     */
    /**
     * Constructor for ArticulocodigoexternoJpaController.
     * 
     * @param emf EntityManagerFactory
     */
    public ArticulocodigoexternoJpaController(EntityManagerFactory emf) {
        super(AbstractJPAController.POSSOURCE, entityName);
        this.emf = emf;
        this.jpacontroller = new crjpa.ArticulocodigoexternoJpaController(emf);
    }

    /*
     * EntityManager em = jpacontroller.getEntityManager(); Query query = em .createQuery(
     * "SELECT c FROM Cliente c WHERE c.numeroIdentificacionCliente = :numeroIdentificacionCliente"
     * ); query.setParameter("numeroIdentificacionCliente", numeroId); Client result =
     * null; try { cliente = (Cliente) query.getSingleResult(); } catch (NoResultException
     * e) { System.out.println(e.getMessage()); result = null; } catch (Exception e) {
     * e.printStackTrace(); result = null; } finally { if (cliente != null) { result =
     * fromJPA(cliente); } if (em != null) { em.clear(); em.close(); } } return result;
     */

    /**
     * Method findArticuloEntitiesByCodigoext.
     * 
     * @param codExt String
     * @return crjpa.Articulo
     */
    protected crjpa.Articulo findArticuloEntitiesByCodigoext(String codExt, boolean onlyActive) {
        EntityManager em = this.jpacontroller.getEntityManager();
        Query query;
        if (onlyActive) {
            query = em
                    .createQuery("SELECT c FROM Articulocodigoexterno c WHERE c.codigoExterno = :codigo and c.estaactivo=:estado");
            query.setParameter("codigo", codExt);
            query.setParameter("estado", "S");
        } else {
            query = em.createQuery("SELECT c FROM Articulocodigoexterno c WHERE c.codigoExterno = :codigo");
            query.setParameter("codigo", codExt);
        }

        Articulo result = null;
        try {
            result = ((Articulocodigoexterno) query.getSingleResult()).getIdArticulo();
        } catch (NoResultException e) {
            System.out.println(e.getMessage());
            result = null;
        } catch (Exception e) {
            e.printStackTrace();
            result = null;
        } finally {
            this.jpacontroller.getEntityManager().close();
        }
        return result;
    }

    // XXX CAMBIO DE CODIGO EXTERNO A STRING
    /**
     * Method findArticuloEntitiesByCodigoext2.
     * 
     * @param codExt String
     * @return crjpa.Articulo
     */
    protected crjpa.Articulo findArticuloEntitiesByCodigoext2(String codExt) {
        try {
            Query query = this.jpacontroller.getEntityManager()
                    .createNamedQuery("Articulocodigoexterno.findByCodigoExterno");
            query.setParameter("codigoExterno", codExt);
            Articulo result = ((Articulocodigoexterno) query.getSingleResult()).getIdArticulo();
            if (result != null) {
                return result;
            } else {
                return null;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            this.jpacontroller.getEntityManager().close();
        }
    }

}
