package com.becoblohm.cr.conf;

public class ErrorCodes {

	// Serie 100 Errores en Base de Datos
	/**
	 * Este error se usa cuando al hacer commit final de una transaccion al grabarla
	 * se produce un JpaException ya bien por clave duplicada o por integridad
	 * referencial
	 */
	public static final String TRANSACTION_ROLLBACK = "ERR101";

	/**
	 * Este Error se usa cuando la transaccion no posee los montos correctos en los
	 * articulos Se presento en Costa Rica. El unico calculo mal efectuado es el del
	 * total de los articulos.
	 */
	public static final String WRONG_ARTICLE_AMOUNTS = "ERR102";

	// Serie 200 Errores de Impresora
	/**
	 * Problema de comunicacion con la impresora
	 */
	public static final String PRINTER_OFFLINE = "ERR201";

	/**
	 * Si el proceso de guardado y proceso en base de datos falla La impresora no
	 * podra imprimir ningun documento.
	 */
	public static final String UNPRINTEABLE_DOCUMENT = "ERR202";

	// Serie 300 Errores en Servicio
	// Serie 400 Errores en Sincronizacion
	// Serie 500 Errores de Acreencias
	/**
	 * Este codigo se usa cuando el cliente no esta presente en la caja al momento
	 * de hacer ciertas operaciones de acreencias como reintegro
	 */
	public static final String CLIENT_NOT_FOUND = "ERR501";

	// Serie 600 Errores en asignacion de precio
	/**
	 * Cuando se obtienen las Notas de credito con modificaciones de precio en la
	 * venta orginal Si los valores no son consistentes respecto a la venta original
	 * se debe indicar este mensaje. Revisar clase AnulDevIntegrityCheck para
	 * entender verificaciones para determinar consistencia de una nota de credito.
	 */
	public static final String INCONSISTENT_ANULDEV = "601";

	public static final String OTROS = "XXX";
}
