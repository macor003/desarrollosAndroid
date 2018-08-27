/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.epa.cr.legacy;

import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import com.becoblohm.cr.crjpa400.controller.exceptions.JpaException;
import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.becoblohm.cr.models.User;

import crjpa400.Usuario;

/**
 */
public class UsuarioJpaController extends AbstractJPAController {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5083879167519351126L;
	/**
	 * Field jpaController.
	 */
	private crjpa400.UsuarioJpaController jpaController;
	/**
	 * Field entityName.
	 */
	private static String entityName = "Usuario";

	/**
	 * Constructor for UsuarioJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public UsuarioJpaController(EntityManagerFactory emf) {
		super(AbstractJPAController.SERVERSOURCE, entityName);
		this.jpaController = new crjpa400.UsuarioJpaController(emf);
	}

	/**
	 * Method fromJpa.
	 * 
	 * @param usuario
	 *            Usuario
	 * @return User
	 */
	public static User fromJpa(Usuario usuario) {

		User user = new User();

		user.setId(usuario.getId());
		user.setIdDocument(String.valueOf(usuario.getId()));
		user.setIdEPA(usuario.getFicha());
		user.setName(usuario.getNombre());
		return user;
	}

	/**
	 * Method edit.
	 * 
	 * @param user
	 *            User
	 * @throws JpaException
	 */
	public void edit(User user) throws JpaException {
		EntityManager em = jpaController.getEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(toJpa(user));
			em.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			throw new JpaException();

		}
	}

	/**
	 * Method findUsuario.
	 * 
	 * @param id
	 *            Long
	 * @return User
	 */
	public User findUsuario(Long id) {
		EntityManager em = jpaController.getEntityManager();
		crjpa400.Usuario singleResult = null;
		try {
			Query query = em.createNamedQuery("Usuario.findByFicha");
			query.setParameter("ficha", id);
			query.setMaxResults(1);
			singleResult = (crjpa400.Usuario) query.getSingleResult();
		} catch (Exception ex) {
			singleResult = null;
			ex.printStackTrace();
		} finally {
			em.close();
		}
		return fromJpa(singleResult);
	}

	/**
	 * Method toJpa.
	 * 
	 * @param cashier
	 *            User
	 * @return Usuario
	 */
	public Usuario toJpa(User cashier) {
		Usuario usuario = new Usuario();
		usuario.setClave(cashier.getPass());
		usuario.setFechaCambioclave(cashier.getLastPasswordChange());
		usuario.setUltimasincronizacion(Calendar.getInstance());
		usuario.setFicha(cashier.getIdEPA());
		usuario.setHuella(cashier.getFinger());
		usuario.setId(cashier.getId());
		usuario.setNombre(cashier.getName());

		if (cashier.getId() > 0) {
			Usuario tmp = jpaController.findUsuario(cashier.getId());
			usuario.setCodigoBarra(tmp.getCodigoBarra());
			usuario.setFecha(tmp.getFecha());
			usuario.setEntregaList(tmp.getEntregaList());
			usuario.setRolloauditoriaList(tmp.getRolloauditoriaList());
			usuario.setSesionList(tmp.getSesionList());
			usuario.setTransaccionList(tmp.getTransaccionList());
			usuario.setUsuarioperfilList(tmp.getUsuarioperfilList());
		}

		return usuario;
	}
}
