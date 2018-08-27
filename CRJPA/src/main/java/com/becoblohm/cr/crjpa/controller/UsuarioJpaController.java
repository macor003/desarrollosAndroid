/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates and open the template in the editor.
 */
package com.becoblohm.cr.crjpa.controller;

import java.util.Collection;
import java.util.Iterator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import com.becoblohm.cr.crjpa.controller.exceptions.JpaException;
import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.becoblohm.cr.models.User;
import com.becoblohm.cr.models.UserAuthorization;

import crjpa.Usuario;
import crjpa.Usuarioperfil;

/**
 * 
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class UsuarioJpaController extends AbstractJPAController {

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
    private crjpa.UsuarioJpaController jpaController;

    /**
     * Field entityName.
     */
    private static String entityName = "Usuario";

    /**
     * Constructor for UsuarioJpaController.
     * 
     * @param emf EntityManagerFactory
     */
    public UsuarioJpaController(EntityManagerFactory emf) {
        super(AbstractJPAController.POSSOURCE, entityName);
        this.emf = emf;
        this.jpaController = new crjpa.UsuarioJpaController(emf);
    }

    /**
     * Method findUsuarioById.
     * 
     * @param id long
     * @return User
     */
    public User findUsuarioById(long id) {
        EntityManager em = jpaController.getEntityManager();
        crjpa.Usuario singleResult = null;
        User result = null;
        try {
            Query query = em.createNamedQuery("Usuario.findByFicha");
            query.setParameter("ficha", id);
            query.setMaxResults(1);
            singleResult = (crjpa.Usuario) query.getSingleResult();
            result = fromJPA(singleResult);
        } catch (Exception ex) {
            singleResult = null;
            result = null;
            ex.printStackTrace();

        } finally {
            em.close();
        }
        return result;

    }

    /**
     * Method findUsuarioJpaById.
     * 
     * @param id long
     * @return crjpa.Usuario
     */
    public crjpa.Usuario findUsuarioJpaById(long id) {
        EntityManager em = jpaController.getEntityManager();
        crjpa.Usuario singleResult = null;
        try {
            Query query = em.createNamedQuery("Usuario.findByFicha");
            query.setParameter("ficha", id);
            query.setMaxResults(1);
            singleResult = (crjpa.Usuario) query.getSingleResult();

        } catch (Exception ex) {
            singleResult = null;
            ex.printStackTrace();
        } finally {
            em.close();
        }
        return singleResult;

    }

    /**
     * Method findUsuarioByBarcode.
     * 
     * @param barcode String
     * @return UserAuthorization
     */
    public UserAuthorization findUsuarioByBarcode(String barcode) {
        EntityManager em = jpaController.getEntityManager();
        User singleResult = null;
        UsuarioperfilJpaController usuarioPerfilJpa = new UsuarioperfilJpaController(this.emf);
        UserAuthorization result = new UserAuthorization();
        try {
            Query query = em.createNamedQuery("Usuario.findByCodigoBarra");
            query.setParameter("codigoBarra", barcode);
            query.setMaxResults(1);
            Usuario tmp = (Usuario) query.getSingleResult();
            singleResult = fromJPA(tmp);
            result = (UserAuthorization) singleResult;

            UsuarioperfilJpaController jpa = new UsuarioperfilJpaController(emf);

            Collection<Usuarioperfil> usuarioperfilList = jpa.findAllByIdUsuario(tmp);
            for (Iterator iterator = usuarioperfilList.iterator(); iterator.hasNext();) {
                Usuarioperfil type = (Usuarioperfil) iterator.next();
                System.out.println("añadiendo perfil " + type.getIdPerfil().getId().toString());
                result.getProfiles().add(type.getIdPerfil().getId().toString());
            }
            System.err.println("perfil " + usuarioperfilList.size());
            result.setLastPasswordChange(tmp.getFechaCambioClave());
        } catch (Exception ex) {
            singleResult = null;
            result = null;
            ex.printStackTrace();
        } finally {
            em.close();
        }
        return result;

    }

    /**
     * Method findUsuarioByFingerPrint.
     * 
     * @param huella byte[]
     * @return User
     */
    public User findUsuarioByFingerPrint(byte[] huella) {

        EntityManager em = jpaController.getEntityManager();
        crjpa.Usuario singleResult = null;
        try {
            Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.huella = :huella");
            query.setParameter("huella", huella);
            query.setMaxResults(1);
            singleResult = (crjpa.Usuario) query.getSingleResult();
        } catch (Exception ex) {
            singleResult = null;
            ex.printStackTrace();
        } finally {
            em.close();
        }
        return fromJPA(singleResult);

    }

    /**
     * Method fromJPA.
     * 
     * @param userJpa Usuario
     * @return User
     */
    public User fromJPA(Usuario userJpa) {
        UserAuthorization user = new UserAuthorization();
        user.setId(userJpa.getId());
        user.setIdEPA(userJpa.getFicha());
        user.setIdDocument(user.getIdDocument());
        user.setName(userJpa.getNombre());
        user.setFinger(userJpa.getHuella());
        user.setPass(userJpa.getClave());
        user.setBarcode(userJpa.getCodigoBarra());
        user.setLastPasswordChange(userJpa.getFechaCambioClave());
        return user;
    }

    /**
     * Method toJPA.
     * 
     * @param user User
     * @return Usuario
     */
    public Usuario toJPA(User user) {
        Usuario usuario = new Usuario();
        usuario.setId(new Long(user.getId()));
        usuario.setFicha(user.getIdEPA());
        usuario.setNombre(user.getName());
        usuario.setClave(user.getPass());
        usuario.setHuella(user.getFinger());
        usuario.setFechaCambioClave(user.getLastPasswordChange());
        usuario.setEstasincronizado("N");
        if (user.getId() > 0) {
            Usuario tmp = jpaController.findUsuario(usuario.getId());
            usuario.setCodigoBarra(tmp.getCodigoBarra());
            usuario.setEntregaList(tmp.getEntregaList());
            usuario.setRolloauditoriaList(tmp.getRolloauditoriaList());
            usuario.setSesionList(tmp.getSesionList());
            usuario.setTransaccionList(tmp.getTransaccionList());
            usuario.setUsuarioperfilList(tmp.getUsuarioperfilList());
        }

        return usuario;
    }

    /**
     * Method edit.
     * 
     * @param user User
     * @throws JpaException
     */
    public void edit(User user) throws JpaException {
        EntityManager em = jpaController.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(toJPA(user));
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new JpaException();
        }
    }
}
