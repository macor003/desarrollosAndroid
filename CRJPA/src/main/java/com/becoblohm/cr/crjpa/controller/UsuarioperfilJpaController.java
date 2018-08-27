/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates and open the template in the editor.
 */
package com.becoblohm.cr.crjpa.controller;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import com.becoblohm.cr.crjpa.controller.exceptions.JpaException;
import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.becoblohm.cr.models.UserAuthorization;

import crjpa.Perfil;
import crjpa.Usuario;
import crjpa.Usuarioperfil;
import crjpa.exceptions.NonexistentEntityException;

/**
 * 
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class UsuarioperfilJpaController extends AbstractJPAController {

    /**
     * Field serialVersionUID. (value is 1)
     */
    private static final long serialVersionUID = 1L;

    /**
     * Field emf.
     */
    private EntityManagerFactory emf;

    /**
     * Field jpaController.
     */
    private crjpa.UsuarioperfilJpaController jpaController;

    /**
     * Field entityName.
     */
    private static String entityName = "Usuarioperfil";

    /**
     * Constructor for UsuarioperfilJpaController.
     * 
     * @param emf EntityManagerFactory
     */
    public UsuarioperfilJpaController(EntityManagerFactory emf) {
        super(AbstractJPAController.POSSOURCE, entityName);
        this.emf = emf;
        this.jpaController = new crjpa.UsuarioperfilJpaController(emf);
    }

    /**
     * Method fromJPA.
     * 
     * @param userJpa Usuario
     * @return UserAuthorization
     */
    public UserAuthorization fromJPA(Usuario userJpa) {
        UserAuthorization user = new UserAuthorization();
        if (userJpa.getId() > 0) {
            user.setId(userJpa.getId());
        }

        for (Iterator iterator = userJpa.getUsuarioperfilList().iterator(); iterator.hasNext();) {
            Usuarioperfil type = (Usuarioperfil) iterator.next();
            user.addProfile(type.getIdPerfil().getId().toString());
        }
        return user;
    }

    /**
     * Method findByIdEpa.
     * 
     * @param idEPA int
     * @return UserAuthorization
     */
    public UserAuthorization findByIdEpa(int idEPA) {

        UsuarioJpaController usuarioJpaController = new UsuarioJpaController(this.emf);

        Usuario usuario = usuarioJpaController.findUsuarioJpaById(idEPA);

        return fromJPA(usuario);
    }

    /**
     * Method create.
     * 
     * @param userId long
     * @param perfilId long
     * @return boolean
     * @throws JpaException
     */
    public boolean create(long userId, long perfilId) throws JpaException {
        try {
            jpaController.create(toJpa(userId, perfilId));
            return true;
        } catch (NonexistentEntityException e) {
            e.printStackTrace();
            throw new JpaException();
        } catch (Exception e) {
            e.printStackTrace();
            throw new JpaException();
        }
    }

    /**
     * Method toJpa.
     * 
     * @param userId long
     * @param perfilId long
     * @return Usuarioperfil
     */
    private Usuarioperfil toJpa(long userId, long perfilId) {
        Usuarioperfil usuarioPerfil = new Usuarioperfil();

        List<Usuarioperfil> list = jpaController.findUsuarioperfilEntities();
        int max = 0;
        for (Usuarioperfil usuarioperfil2 : list) {
            if (usuarioperfil2.getId() > max) {
                max = usuarioperfil2.getId().intValue();
            }
        }

        usuarioPerfil.setId(new Long(max + 1));
        usuarioPerfil.setIdUsuario(new Usuario(new Long(userId)));
        usuarioPerfil.setIdPerfil(new Perfil(new Long(perfilId)));

        return usuarioPerfil;
    }

    /**
     * Method deleteAllByIdPerfil.
     * 
     * @param profileIdSelfCheckoutCashier String
     * @throws JpaException
     */
    public void deleteAllByIdPerfil(String profileIdSelfCheckoutCashier) throws JpaException {

        EntityManager em = jpaController.getEntityManager();
        Query query = em.createQuery("SELECT u FROM Usuarioperfil u WHERE u.idPerfil.id = :idPerfil");
        query.setParameter("idPerfil", new Long(profileIdSelfCheckoutCashier));
        List<Usuarioperfil> usuarioPerfilList = query.getResultList();

        for (Usuarioperfil usuarioperfil : usuarioPerfilList) {
            try {
                jpaController.destroy(usuarioperfil.getId());
            } catch (NonexistentEntityException e) {
                e.printStackTrace();
                // throw new JpaException();
            } catch (Exception e) {
                throw new JpaException();
            }
        }
    }

    /**
     * Method findAllByIdUsuario.
     * 
     * @param user Usuario
     * @return Collection<Usuarioperfil>
     */
    public Collection<Usuarioperfil> findAllByIdUsuario(Usuario user) {

        EntityManager em = jpaController.getEntityManager();
        Query query = em.createQuery("SELECT u FROM Usuarioperfil u WHERE u.idUsuario.id = :idUsuario");
        query.setParameter("idUsuario", new Long(user.getId()));
        List<Usuarioperfil> usuarioPerfilList = query.getResultList();

        return usuarioPerfilList;
    }

}
