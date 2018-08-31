/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/**
 * 
 */
package com.becoblohm.cr.sync.singleton;

import javax.persistence.EntityManagerFactory;

import com.becoblohm.cr.conf.Global;
import com.becoblohm.cr.interfaces.ConverterInterface;
import com.becoblohm.cr.sync.converters.ArticuloConverter;
import com.becoblohm.cr.sync.converters.ArticulocodigoexternoConverter;
import com.becoblohm.cr.sync.converters.ArticulounidadventaConverter;
import com.becoblohm.cr.sync.converters.AuditoriaConverter;
import com.becoblohm.cr.sync.converters.CategoriaConverter;
import com.becoblohm.cr.sync.converters.CategoriaLineaincluyeConverter;
import com.becoblohm.cr.sync.converters.CategoriaLinearetencionConverter;
import com.becoblohm.cr.sync.converters.ClienteConverter;
import com.becoblohm.cr.sync.converters.ClientemensajeConverter;
import com.becoblohm.cr.sync.converters.ComprobantefiscalnoutilizadoConverter;
import com.becoblohm.cr.sync.converters.ComprobantefiscalpreimpresoConverter;
import com.becoblohm.cr.sync.converters.CuentacreditoConverter;
import com.becoblohm.cr.sync.converters.DepuracionConverter;
import com.becoblohm.cr.sync.converters.DetallestipoformadepagolineaConverter;
import com.becoblohm.cr.sync.converters.DetalletipoformadepagoConverter;
import com.becoblohm.cr.sync.converters.EntregaConverter;
import com.becoblohm.cr.sync.converters.ExoneradotipoConverter;
import com.becoblohm.cr.sync.converters.FamiliaConverter;
import com.becoblohm.cr.sync.converters.FormadepagoConverter;
import com.becoblohm.cr.sync.converters.FormadepagomonedaConverter;
import com.becoblohm.cr.sync.converters.FormadepagotipoclienteConverter;
import com.becoblohm.cr.sync.converters.GiroactividadeconomicaConverter;
import com.becoblohm.cr.sync.converters.LineaConverter;
import com.becoblohm.cr.sync.converters.MonedaConverter;
import com.becoblohm.cr.sync.converters.MonedadenominacionConverter;
import com.becoblohm.cr.sync.converters.MonedatasacambioConverter;
import com.becoblohm.cr.sync.converters.MorosidadConverter;
import com.becoblohm.cr.sync.converters.MotivorebajaConverter;
import com.becoblohm.cr.sync.converters.OpcionConverter;
import com.becoblohm.cr.sync.converters.OperacionacreenciaConverter;
import com.becoblohm.cr.sync.converters.PerfilConverter;
import com.becoblohm.cr.sync.converters.PerfilprocesoConverter;
import com.becoblohm.cr.sync.converters.PorcentajeimpuestoretencionConverter;
import com.becoblohm.cr.sync.converters.ProcesoConverter;
import com.becoblohm.cr.sync.converters.ProcesopropertyConverter;
import com.becoblohm.cr.sync.converters.PromocionConverter;
import com.becoblohm.cr.sync.converters.PromocionarticuloConverter;
import com.becoblohm.cr.sync.converters.PromocioncategoriaConverter;
import com.becoblohm.cr.sync.converters.PromocionformadepagoConverter;
import com.becoblohm.cr.sync.converters.PromocionlineaConverter;
import com.becoblohm.cr.sync.converters.PromociontipodescuentoConverter;
import com.becoblohm.cr.sync.converters.ReportezConverter;
import com.becoblohm.cr.sync.converters.RolloAuditoriaConverter;
import com.becoblohm.cr.sync.converters.SerialimpresoraConverter;
import com.becoblohm.cr.sync.converters.ServicioConverter;
import com.becoblohm.cr.sync.converters.SesionConverter;
import com.becoblohm.cr.sync.converters.SincronizacionConverter;
import com.becoblohm.cr.sync.converters.TasaimpuestotipoConverter;
import com.becoblohm.cr.sync.converters.TasaimpuestovalorConverter;
import com.becoblohm.cr.sync.converters.TipoacreenciaConverter;
import com.becoblohm.cr.sync.converters.TipoacreenciaoperacionConverter;
import com.becoblohm.cr.sync.converters.TipoclienteConverter;
import com.becoblohm.cr.sync.converters.TipoclientetipodocumentoConverter;
import com.becoblohm.cr.sync.converters.TipodescuentoConverter;
import com.becoblohm.cr.sync.converters.TipodocumentoConverter;
import com.becoblohm.cr.sync.converters.TipoidentificacionclienteConverter;
import com.becoblohm.cr.sync.converters.TransaccionConverter;
import com.becoblohm.cr.sync.converters.UnidadventaConverter;
import com.becoblohm.cr.sync.converters.UsuarioConverter;
import com.becoblohm.cr.sync.converters.UsuarioperfilConverter;

/**
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class ConverterSingleton implements EntityPersistentConstants {

	/**
	 * Field emfPos.
	 */
	private static EntityManagerFactory emfPos;
	/**
	 * Field emfServer.
	 */
	private static EntityManagerFactory emfServer;

	/**
	 * Constructor for ConverterSingleton.
	 */
	private ConverterSingleton() {
		super();
	}

	/**
	 * Method getServerEMF.
	 * 
	 * @return EntityManagerFactory
	 */
	public static EntityManagerFactory getServerEMF() {
		if (emfServer == null) {
			emfServer = Global.getEntityManagerFactory400();
		}
		return emfServer;
	}

	/**
	 * Method getPosEMF.
	 * 
	 * @return EntityManagerFactory
	 */
	public static EntityManagerFactory getPosEMF() {
		if (emfPos == null) {
			emfPos = Global.getEntityManagerFactory();
		}
		return emfPos;
	}

	/**
	 * Method getConverterInterface.
	 * 
	 * @param entityName
	 *            String
	 * @return ConverterInterface
	 */
	public static ConverterInterface getConverterInterface(String entityName) {
		if (entityName.equals(CATEGORIA)) {
			return new CategoriaConverter();
		}

		if (entityName.equals(CATEGORIA_LINEAINCLUYE)) {
			return new CategoriaLineaincluyeConverter();
		}

		if (entityName.equals(CATEGORIA_LINEARETENCION)) {
			return new CategoriaLinearetencionConverter();
		}

		if (entityName.equals(CLIENTE)) {
			return new ClienteConverter();
		}

		if (entityName.equals(CLIENTEMENSAJE)) {
			return new ClientemensajeConverter();
		}

		if (entityName.equals(COMPROBANTEFISCALNOUTILIZADO)) {
			return new ComprobantefiscalnoutilizadoConverter();
		}

		if (entityName.equals(COMPROBANTEFISCALPREIMPRESO)) {
			return new ComprobantefiscalpreimpresoConverter();
		}

		if (entityName.equals(DETALLETIPOFORMADEPAGO)) {
			return new DetalletipoformadepagoConverter();
		}

		if (entityName.equals(DETALLES_TIPO_FDP_LINEAS)) {
			return new DetallestipoformadepagolineaConverter();
		}

		if (entityName.equals(ENTREGA)) {
			return new EntregaConverter();
		}

		if (entityName.equals(EXONERADOTIPO)) {
			return new ExoneradotipoConverter();
		}

		if (entityName.equals(FAMILIA)) {
			return new FamiliaConverter();
		}

		if (entityName.equals(FORMADEPAGO)) {
			return new FormadepagoConverter();
		}

		if (entityName.equals(FORMADEPAGOMONEDA)) {
			return new FormadepagomonedaConverter();
		}

		if (entityName.equals(GIROACTIVIDADECONOMICA)) {
			return new GiroactividadeconomicaConverter();
		}

		if (entityName.equals(LINEA)) {
			return new LineaConverter();
		}

		if (entityName.equals(CUENTACREDITO)) {
			return new CuentacreditoConverter();
		}

		if (entityName.equals(MONEDADENOMINACION)) {
			return new MonedadenominacionConverter();
		}

		if (entityName.equals(MONEDA)) {
			return new MonedaConverter();
		}

		if (entityName.equals(MONEDATASACAMBIO)) {
			return new MonedatasacambioConverter();
		}

		if (entityName.equals(MOTIVOREBAJA)) {
			return new MotivorebajaConverter();
		}

		if (entityName.equals(OPCION)) {
			return new OpcionConverter();
		}

		if (entityName.equals(OPERACIONACREENCIA)) {
			return new OperacionacreenciaConverter();
		}

		if (entityName.equals(PERFIL)) {
			return new PerfilConverter();
		}

		if (entityName.equals(PERFILPROCESO)) {
			return new PerfilprocesoConverter();
		}

		if (entityName.equals(PORCENTAJEIMPUESTORETENCION)) {
			return new PorcentajeimpuestoretencionConverter();
		}

		if (entityName.equals(PROCESO)) {
			return new ProcesoConverter();
		}

		if (entityName.equals(PROCESOPROPERTY)) {
			return new ProcesopropertyConverter();
		}

		if (entityName.equals(PROMOCIONARTICULO)) {
			return new PromocionarticuloConverter();
		}

		if (entityName.equals(PROMOCIONCATEGORIA)) {
			return new PromocioncategoriaConverter();
		}

		if (entityName.equals(PROMOCIONFORMADEPAGO)) {
			return new PromocionformadepagoConverter();
		}

		if (entityName.equals(PROMOCION)) {
			return new PromocionConverter();
		}

		if (entityName.equals(PROMOCIONLINEA)) {
			return new PromocionlineaConverter();
		}

		if (entityName.equals(PROMOCIONTIPODESCUENTO)) {
			return new PromociontipodescuentoConverter();
		}

		if (entityName.equals(REPORTE_Z)) {
			return new ReportezConverter();
		}

		if (entityName.equals(ROLLO_AUDITORIA)) {
			return new RolloAuditoriaConverter();
		}

		if (entityName.equals(SERIALIMPRESORA)) {
			return new SerialimpresoraConverter();
		}

		if (entityName.equals(SERVICIO)) {
			return new ServicioConverter();
		}

		if (entityName.equals(SESION)) {
			return new SesionConverter();
		}

		if (entityName.equals(SINCRONIZACION)) {
			return new SincronizacionConverter();
		}

		if (entityName.equals(TASAIMPUESTOTIPO)) {
			return new TasaimpuestotipoConverter();
		}

		if (entityName.equals(TASAIMPUESTOVALOR)) {
			return new TasaimpuestovalorConverter();
		}

		if (entityName.equals(TIPOACREENCIA)) {
			return new TipoacreenciaConverter();
		}

		if (entityName.equals(TIPOACREENCIAOPERACION)) {
			return new TipoacreenciaoperacionConverter();
		}

		if (entityName.equals(TIPOCLIENTE)) {
			return new TipoclienteConverter();
		}

		if (entityName.equals(TIPOCLIENTETIPODOCUMENTO)) {
			return new TipoclientetipodocumentoConverter();
		}

		if (entityName.equals(TIPODESCUENTO)) {
			return new TipodescuentoConverter();
		}

		if (entityName.equals(TIPODOCUMENTO)) {
			return new TipodocumentoConverter();
		}

		if (entityName.equals(TIPOIDENTIFICACIONCLIENTE)) {
			return new TipoidentificacionclienteConverter();
		}

		if (entityName.equals(TRANSACCION)) {
			return new TransaccionConverter();
		}

		if (entityName.equals(UNIDADVENTA)) {
			return new UnidadventaConverter();
		}

		if (entityName.equals(USUARIOPERFIL)) {
			return new UsuarioperfilConverter();
		}

		if (entityName.equals(CLIENTE)) {
			return new ClienteConverter();
		}

		if (entityName.equals(ARTICULO)) {
			return new ArticuloConverter();
		}

		if (entityName.equals(ARTICULOCODIGOEXTERNO)) {
			return new ArticulocodigoexternoConverter();
		}

		if (entityName.equals(USUARIO)) {
			return new UsuarioConverter();
		}

		if (entityName.equals(AUDITORIA)) {
			return new AuditoriaConverter();
		}

		if (entityName.equals(MOROSIDAD)) {
			return new MorosidadConverter();
		}

		if (entityName.equals(DEPURACION)) {
			return new DepuracionConverter();
		}

		if (entityName.equals(ARTICULO_UNIDAD_VENTA)) {
			return new ArticulounidadventaConverter();
		}

		if (entityName.equals(FORMADEPAGO_TIPOCLIENTE)) {
			return new FormadepagotipoclienteConverter();
		}

		throw new IllegalArgumentException(LA_ENTIDAD_NO_POSEE_CLASE_CONVERTER + " " + entityName);

	}
}
