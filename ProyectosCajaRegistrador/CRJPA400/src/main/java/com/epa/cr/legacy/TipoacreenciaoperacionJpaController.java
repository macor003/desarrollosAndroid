/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.epa.cr.legacy;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import com.becoblohm.cr.crjpa400.controller.exceptions.JpaException;
import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.becoblohm.cr.legacy.models.CreditsTypes;
import com.becoblohm.cr.models.CreditsOperation;
import com.becoblohm.cr.models.CreditsOperationType;
import com.becoblohm.cr.models.CreditsType;
import com.becoblohm.cr.types.ActiveValues;

import crjpa400.Operacionacreencia;
import crjpa400.Tipoacreencia;
import crjpa400.Tipoacreenciaoperacion;

public class TipoacreenciaoperacionJpaController extends AbstractJPAController {

    EntityManagerFactory emf;

    private crjpa400.TipoacreenciaoperacionJpaController controller;

    private static String entityName = "Tipoacreenciaoperacion";

    private static final long serialVersionUID = 1L;

    public TipoacreenciaoperacionJpaController(EntityManagerFactory p_emf) {
        super(AbstractJPAController.SERVERSOURCE, entityName);
        this.emf = p_emf;
        this.controller = new crjpa400.TipoacreenciaoperacionJpaController(p_emf);
    }

    /**
     * Method fromJPA.
     * 
     * @param obj Tipoacreenciaoperacion
     * @return CreditsOperationType
     */
    CreditsOperationType fromJPA(Tipoacreenciaoperacion obj) {
        CreditsOperationType cot = setNoReverseFromJPA(obj);
        cot.setReverse(setNoReverseFromJPA(obj.getContraPartida()));
        return cot;

    }

    /**
     * <p> The CreditsTypes (CR.TIPOACREENCIA) object needs to be set outside this JPA
     * because the need of its Entity Manager Factory targeted to the CR database schema
     * </p>
     * 
     * @param obj
     * @return CreditsOperationType - The entity's body without setting its counterpart to
     *         avoid the infinite recursion in the object building state
     */
    private CreditsOperationType setNoReverseFromJPA(Tipoacreenciaoperacion obj) {
        CreditsOperationType cot = new CreditsOperationType();
        CreditsOperation cop = new CreditsOperation();
        CreditsType ct = new CreditsType();
        CreditsTypes creditsTypesCR = new CreditsTypes(obj.getCreditsTypes());
        OperacionacreenciasJpaController controller = new OperacionacreenciasJpaController(emf);
        Operacionacreencia op = controller
                .findOperacionacreencias(obj.getIdOperacionacreencia().getId().intValue());
        TipoacreenciaJpaController controllerTipo = new TipoacreenciaJpaController(emf);

        ct = controllerTipo.fromJPA(obj.getIdTipoacreencia());
        cop = controller.fromJPA(op);

        cot.setCreditsOperation(cop);
        cot.setCreditsType(ct);
        cot.setId(obj.getId());
        cot.setVisible(ActiveValues.get(String.valueOf(obj.getEsvisible())).getValue());
        cot.setCondition(obj.getCondition());
        cot.setCreditsTypesCR(creditsTypesCR);

        return cot;
    }

    /**
     * Method toJPA.
     * 
     * @param op CreditsOperationType
     * @return Tipoacreenciaoperacion
     */
    protected Tipoacreenciaoperacion toJPA(CreditsOperationType op) {

        Tipoacreenciaoperacion tipo = new Tipoacreenciaoperacion();
        OperacionacreenciaJpaController opcontroller = new OperacionacreenciaJpaController(emf);
        Operacionacreencia opacr = opcontroller.findOperacionacreencia(op.getCreditsOperation().getId());
        Tipoacreencia tipoacr = new Tipoacreencia(op.getCreditsType().getId());
        tipo.setId(op.getId());
        tipo.setIdOperacionacreencia(opacr);
        tipo.setIdTipoacreencia(tipoacr);
        tipo.setCreditsTypes(op.getCreditsTypesCR().getType());
        tipo.setCondition(op.getCondition());

        if (op.getId() > 0) {
            Tipoacreenciaoperacion tmp = this.controller.findTipoacreenciaoperacion(op.getId());
            tipo.setMovimientoacreenciaList(tmp.getMovimientoacreenciaList());
        }

        return tipo;
    }

    /**
     * Method findTipoacreenciasoperacion.
     * 
     * @param idTipoacreencia Long
     * @param idOperacionacreencia Long
     * @return CreditsOperationType
     */
    public CreditsOperationType findTipoacreenciasoperacion(Long idTipoacreencia, Long idOperacionacreencia) {
        EntityManager em = this.emf.createEntityManager();
        Tipoacreenciaoperacion singleResult = null;
        CreditsOperationType result = null;
        try {
            Query query = em
                    .createQuery("SELECT t FROM Tipoacreenciaoperacion t WHERE t.idTipoacreencia.id = :idTipoacreencia and t.idOperacionacreencia.id = :idOperacionacreencia");
            query.setParameter("idTipoacreencia", idTipoacreencia);
            query.setParameter("idOperacionacreencia", idOperacionacreencia);
            query.setMaxResults(1);
            singleResult = (Tipoacreenciaoperacion) query.getSingleResult();
            result = fromJPA(singleResult);
        } catch (Exception ex) {
            singleResult = null;
            ex.printStackTrace();
        } finally {
            em.close();
        }
        return result;

    }

    /**
     * Method findTipoacreenciasoperacion.
     * 
     * @param operationTypeId int
     * @return CreditsOperationType
     */
    public CreditsOperationType findTipoacreenciasoperacion(int operationTypeId) {
        CreditsOperationType cot = null;
        Tipoacreenciaoperacion tipoacreenciasoperacion = controller
                .findTipoacreenciaoperacion((long) operationTypeId);
        if (tipoacreenciasoperacion != null) {
            cot = fromJPA(tipoacreenciasoperacion);
        }
        return cot;
    }

    /**
     * <p> Method getCreditsOperationTypeList </p> <p> Find all the rows from
     * CR400.TIPOACREENCIAOPERACION for a specific credits type </p>
     * 
     * @param long creditsTypeID
     * @throws JpaException
     */
    public List<CreditsOperationType> getCreditsOperationTypeList(long creditsTypeID) throws JpaException {
        EntityManager em = this.emf.createEntityManager();
        List<CreditsOperationType> creditsOpTypeList = new ArrayList<CreditsOperationType>();

        try {
            Query query = em
                    .createQuery("SELECT t Tipoacreenciaoperacion WHERE t.idTipoacreencia = :idTipoacreencia ORDER BY t.idOperacionacreencia ASC");
            query.setParameter("idTipoacreencia", creditsTypeID);
            creditsOpTypeList = query.getResultList();
        } catch (Exception e) {
            throw new JpaException(
                    "ERROR trying to get the credits operation type list for the creditsTypeID: " + creditsTypeID);
        } finally {
            em.close();
        }
        return creditsOpTypeList;
    }
}
