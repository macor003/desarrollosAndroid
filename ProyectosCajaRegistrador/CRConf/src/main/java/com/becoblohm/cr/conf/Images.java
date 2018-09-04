/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/**
 * Clase para almacenar en buffer las imagenes de manera de cargarlas una sola vez
 */
package com.becoblohm.cr.conf;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.becoblohm.cr.utils.CRUtils;

/**
 * @author programador6
 * @version 0.1
 */
public class Images {

	/**
	 * Field logger.
	 */
	private static final Logger logger = LoggerFactory.getLogger(Images.class);

	/**
	 * Field imgPath.
	 */
	private static final String imgPath = "com/becoblohm/cr/init/resources/";

	/**
	 * Field alreadyLoaded.
	 */
	static boolean alreadyLoaded = false;

	// $codepro.audit.disable whiteSpaceUsage
	/**
	 * Field DeConIco.
	 */
	/**
	 * Field ConIco.
	 */

	/**
	 * Field url.
	 */
	private URL url;

	/**
	 * Field icons.
	 */
	private final Map<String, ImageIcon> icons = new HashMap<String, ImageIcon>();

	/**
	 * Field images.
	 */
	private final Map<String, BufferedImage> images = new HashMap<String, BufferedImage>();

	/**
	 * Field instance.
	 */
	private static Images instance;

	/**
	 * Method getInstance.
	 * 
	 * @return Images
	 */
	public static Images getInstance() {
		if (instance == null) {
			instance = new Images();
		}
		return instance;
	}

	/**
	 * Method Images.
	 */
	private Images() {
		if (!alreadyLoaded) {
			try {
				// Imagenes ----------------

				for (Iterator iterator = Global.getImagesResourceBundle().keySet().iterator(); iterator.hasNext();) {
					String key = (String) iterator.next();

					url = CRUtils.findResource(Images.imgPath + Global.getImagesResourceBundle().getString(key), null,
							null);
					if (url != null) {
						images.put(key, ImageIO.read(url));
					}

				}

				// resize
				// BufferedImage bi = ImageIO.read(url);
				// BufferedImage bi2 = new BufferedImage(bi.getWidth(null),
				// bi.getHeight(null), BufferedImage.TYPE_INT_ARGB);
				//
				// Graphics g = bi2.createGraphics();
				// g.drawImage(bi, 0, 0, ICON_WIDTH, ICON_HEIGHT, null);

				// Iconons -------------------------------------------------->

				for (Iterator iterator = Global.getIconsResourceBundle().keySet().iterator(); iterator.hasNext();) {
					String key = (String) iterator.next();

					url = CRUtils.findResource(Images.imgPath + Global.getIconsResourceBundle().getString(key), null,
							null);
					if (url != null) {
						icons.put(key, new ImageIcon(url));

					}

				}

				alreadyLoaded = true;
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("problema cargando las imagenes");
			}

		}

	}

	/**
	 * Method getIconByName.
	 * 
	 * @param name
	 *            String
	 * 
	 * 
	 * @return ImageIcon
	 */
	public ImageIcon getIconByName(String name) {
		return icons.get(name);

	}

	/**
	 * Method getImgByName.
	 * 
	 * @param name
	 *            String
	 * @return BufferedImage
	 */
	public BufferedImage getImgByName(String name) {
		return images.get(name);
	}

	/**
	 * Method getImgpath.
	 * 
	 * @return String
	 */
	public static String getImgpath() {
		return imgPath;
	}

}
