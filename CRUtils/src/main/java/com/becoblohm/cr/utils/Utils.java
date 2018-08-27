/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.utils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author eve0014321
 * @version $Revision: 1.0 $
 */
public class Utils {

	/**
	 * @param montoStr
	 * @param LONGITUD_MAXIMA
	 * 
	 * 
	 * @return String
	 */
	public static String validaLongitudMonto(String montoStr, int LONGITUD_MAXIMA) {

		String cerosx20 = "000000000000000000";
		if (montoStr.length() > LONGITUD_MAXIMA) {
			montoStr = montoStr.substring(montoStr.length() - LONGITUD_MAXIMA, montoStr.length());
		} else {
			montoStr = cerosx20 + montoStr;
			montoStr = montoStr.substring(montoStr.length() - LONGITUD_MAXIMA, montoStr.length());
		}

		return montoStr;
	}

	/**
	 * Metodo para convertir de entero a String hexadecimal
	 * 
	 * @param source
	 * 
	 * 
	 * @return String
	 */
	public static String intToHexString(int source) {

		String returnStr = Integer.toHexString(source);
		try {// Se completa la cadena a Hexadecimal 0x00 para que el Engine la
				// Reconozca
			if (returnStr.length() > 1) {
				returnStr = "0x" + returnStr;
			} else {// Completar con 0x0
				returnStr = "0x0" + returnStr;
			}
		} catch (Exception ex) {
			returnStr = "0x00";
		}

		return returnStr;

	}

	/**
	 * @param byteSrc
	 * 
	 * 
	 * @return String
	 */

	public static String byteToHexString(byte byteSrc) {

		String returnStr = Integer.toHexString(byteSrc).toUpperCase();
		try {// Se completa la cadena a Hexadecimal 0x00 para que el Engine la
				// Reconozca
			if (returnStr.length() > 1) {
				returnStr = "0x" + returnStr;
			} else {// Completar con 0x0
				returnStr = "0x0" + returnStr;
			}
		} catch (Exception ex) {
			returnStr = "0x00";
		}

		return returnStr;
	}

	/**
	 * Llena de ceros "0" a la izquierda los montos para ser entendidos por la
	 * unidad fiscal
	 * 
	 * @param montoStr
	 * @param LONGITUD_MAXIMA
	 * 
	 * 
	 * @return String
	 */
	public static String validaMonto(String montoStr, int LONGITUD_MAXIMA) {

		String ceros = "00000000000000000000";
		if (montoStr.length() > LONGITUD_MAXIMA) {
			montoStr = montoStr.substring(montoStr.length() - LONGITUD_MAXIMA, montoStr.length());
		} else {
			montoStr = ceros + montoStr;
			montoStr = montoStr.substring(montoStr.length() - LONGITUD_MAXIMA, montoStr.length());
		}

		return montoStr;
	}

	/**
	 * Complementa a cada valor hexadecimal el prefijo 0x<HH>
	 * 
	 * @param hexa
	 * 
	 * 
	 * @return String
	 */
	public static String completaHexString(String hexa) {

		hexa = hexa.toUpperCase();
		try {// Se completa la cadena a Hexadecimal para que el Engine la
				// Reconozca
			if (hexa.length() > 1) {
				hexa = "0x" + hexa;
			} else {// Completar con 0x0
				hexa = "0x0" + hexa;
			}
		} catch (Exception ex) {
			hexa = "0x00";
		}

		return hexa;

	}

	/**
	 * Convierte una cadena de caracteres en un String Hexadecimal (Su
	 * representacion en Hexadecimal)
	 * 
	 * @param cadena
	 *            Cadena a Convertir
	 * 
	 * 
	 * @return cadenaBuilt Cadena convertida a String Hexadecimal
	 */
	public static String stringToHexString(String cadena) {

		int i = 0;
		int z = cadena.length();
		String cadenaBuilt = "";
		while (i != z) {
			cadenaBuilt += completaHexString(Integer.toHexString(cadena.charAt(i)));
			i++;
		}

		return cadenaBuilt;
	}

	/**
	 * Valida la longitud de las cadenas de texto a enviar en campos descripcion
	 * si es mayor la longitud las trunca hasta el m�ximo permitido
	 * 
	 * @param texto
	 * @param longitud
	 * 
	 * 
	 * @return descripcion Retorna cadena validada
	 */
	public static String validaLongitudTexto(String texto, int longitud) {

		String descripcion = texto;
		try {
			if (descripcion.length() < longitud) {
				descripcion = Utils.completarBlanco(descripcion, longitud);
			} else {
				descripcion = descripcion.substring(0, longitud);
			}
		} catch (Exception ex) {
			descripcion = Utils.completarBlanco(descripcion, longitud);
		}

		return descripcion;
	}

	/**
	 * Complementa las cadenas de texto con espacios en blanco para que sean
	 * reconocidas por la unidad fiscal
	 * 
	 * @param texto
	 *            Texto a complementar
	 * @param lenght
	 *            Longitud maxima del campo descripcion
	 * 
	 * 
	 * @return texto Texto complementado con espacios en blanco
	 */

	public static String completarBlanco(String texto, int lenght) {
		return completarBlanco(texto, lenght, 0);
	}

	/**
	 * Method completar.
	 * 
	 * @param texto
	 *            String
	 * @param lenght
	 *            int
	 * @param align
	 *            int
	 * @param c
	 *            char
	 * @return String
	 */
	public static String completar(String texto, int lenght, int align, char c) {

		// String
		// blancosx80="                                                                                ";
		// texto=texto+blancosx80;
		// texto=texto.substring(0,lenght);
		String aligntext;
		switch (align) {
		case 0:
			aligntext = "-";
			break;

		default:
			aligntext = "";
			break;
		}

		String tmp = String.format("%" + aligntext + lenght + "s", texto).replace(' ', c);

		texto = tmp;

		return texto;

	}

	/**
	 * Method completarBlanco.
	 * 
	 * @param texto
	 *            String
	 * @param lenght
	 *            int
	 * @param align
	 *            int
	 * @return String
	 */
	public static String completarBlanco(String texto, int lenght, int align) {

		return completar(texto, lenght, align, ' ');

	}

	/**
	 * Method crearSeparador.
	 * 
	 * @param c
	 *            String
	 * @return String
	 */
	public static String crearSeparador(String c) {

		return completar("", 38, 0, c.charAt(0));

	}

	/**
	 * Method crearSeparador.
	 * 
	 * @param c
	 *            String
	 * @param length
	 *            int
	 * @return String
	 */
	public static String crearSeparador(String c, int length) {

		return completar("", length, 0, c.charAt(0));

	}

	/**
	 * Convierte los numeros en el formato reconocido por la Unidad Fiscal (Ej.
	 * 100,50 = 10050)
	 * 
	 * @param numero
	 *            Numero a convertir
	 * 
	 * 
	 * @return MONTO Numero transformado
	 */
	public static String formatearNumero(double numero) {

		// Formatear N�mero xxxxxx,xx
		DecimalFormat decimalFormat = new DecimalFormat("###0.00");
		String MONTO = decimalFormat.format(numero);
		// Eliminar Car�cter Coma. Buscar M�todo m�s eficiente
		if (MONTO.indexOf(",") > 0) {
			MONTO = MONTO.substring(0, MONTO.indexOf(",")) + MONTO.substring(MONTO.indexOf(",") + 1, MONTO.length());
		} else if (MONTO.indexOf(".") > 0) {
			MONTO = MONTO.substring(0, MONTO.indexOf(".")) + MONTO.substring(MONTO.indexOf(".") + 1, MONTO.length());
		}
		// logger.debug("-->"+MONTO);

		return MONTO;
	}

	/**
	 * Method byteArrayToInt.
	 * 
	 * @param b
	 *            byte[]
	 * @param offset
	 *            int
	 * 
	 * @return int
	 */
	public static int byteArrayToInt(byte[] b, int offset) {
		int value = 0;
		int length = b.length;
		for (int i = 0; i < length; i++) {
			int shift = (length - 1 - i) * 8;
			value += (b[i + offset] & 0x000000FF) << shift;

		}
		return value;
	}

	/**
	 * Filtra los caracteres especiales que no pueden ser interpretados por la
	 * impresora fiscal
	 * 
	 * @param mensaje
	 *            Cadena de caracteres a convertir.
	 * 
	 * @param longitud
	 *            int
	 * @param b
	 *            boolean
	 * 
	 * @return Mensaje convertido
	 */
	public static String convertirCadena(String mensaje, int longitud, boolean b) {

		// String
		// stringCaracteresAModificar="�����������Ѱ�`�����������������������";
		// String
		// stringCaracteresCorrectos="aeiouAEIOUnNoo''uUaeiouAEIOUAEIOUaeiou";
		//
		// for(int i=0; i<mensaje.toCharArray().length; i++) {
		// for(int y=0; y<stringCaracteresAModificar.toCharArray().length; y++)
		// {
		// if(mensaje.toLowerCase().charAt(i) ==
		// stringCaracteresAModificar.charAt(y)) {
		// mensaje = mensaje.replace(mensaje.charAt(i),
		// stringCaracteresCorrectos.charAt(y));
		// }
		// }
		// }
		mensaje = CRUtils.removeSpecialChars(mensaje);

		String returnString = validaLongitudTexto(mensaje.toUpperCase(), longitud);

		return returnString;
	}

	/**
	 * Metodo para centrar cadenas de caracteres.
	 * 
	 * @param cadena
	 *            Cadena de texto
	 * @param longitud
	 *            Longitud maxima de cadena
	 * 
	 * 
	 * @return String
	 */
	public static String centrarCadena(String cadena, int longitud) {

		String blancosx80 = "                                                                                ";
		int centro = (longitud / 2) - (cadena.length() / 2);
		cadena = blancosx80.substring(0, centro) + cadena;

		return cadena;
	}

	/**
	 * Method dividirEnLineas.
	 * 
	 * @param tamanioLinea
	 *            int
	 * @param texto
	 *            String
	 * @return Collection
	 */
	public static Collection dividirEnLineas(int tamanioLinea, String texto) {
		ArrayList<String> lineasResultado = new ArrayList<String>();
		int letras = 0;
		boolean dividir = false;
		do {

			// Colocamos las palabras de las lineas
			dividir = ((letras + tamanioLinea) < texto.length());
			String lineaActual = ((letras + tamanioLinea) < texto.length()) ? texto.substring(letras, letras
					+ tamanioLinea) : texto.substring(letras);
			int j = lineaActual.length();

			if (dividir) {
				// Buscamos donde cortar las palabras para la siguiente linea
				while ((j > 1) && (lineaActual.charAt(j - 1) != '.') && (lineaActual.charAt(j - 1) != ' ')
						&& (lineaActual.charAt(j - 1) != ',') && (lineaActual.charAt(j - 1) != ':'))
					j--;
			}

			// Si no se puede cortar la linea, se coloca igual
			if (j <= 1)
				j = lineaActual.length();

			// logger.debug("linea : "+lineaActual);
			lineasResultado.add(lineaActual.substring(0, j));
			letras += j;
		} while (letras < texto.length());
		return lineasResultado;
	}

	/**
	 * Method alinear.
	 * 
	 * @param texto
	 *            String
	 * @param separator
	 *            String
	 * @return String
	 */
	public static String alinear(String texto, String separator) {

		String[] linesplit = texto.split(separator);
		StringBuffer sbTmp = new StringBuffer();
		sbTmp.append(Utils.completarBlanco(linesplit[0], 21, 0));
		sbTmp.append(Utils.completarBlanco(linesplit[1], 17, 1));

		texto = sbTmp.toString();

		return texto;
	}

}
