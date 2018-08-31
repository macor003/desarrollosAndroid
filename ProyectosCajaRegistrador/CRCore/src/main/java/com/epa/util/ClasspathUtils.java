/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/**
 * 
 */
package com.epa.util;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author programador6
 * @version $Revision: 1.0 $
 */
public class ClasspathUtils {

	/**
	 * Field logger.
	 */
	private static final Logger logger = LoggerFactory.getLogger(ClasspathUtils.class);
	// Parameters
	/**
	 * Field parameters.
	 */
	private static final Class[] parameters = new Class[] { URL.class };

	/**
	 * Adds the jars in the given directory to classpath
	 * 
	 * @param directory
	 * 
	 * 
	 * @return ClassLoader * @throws IOException / * @throws IOException * @throws
	 *         IOException
	 */
	public static ClassLoader addDirToClasspath(File directory) throws IOException {
		logger.debug("INICIO");
		if (directory.exists()) {
			File[] files = directory.listFiles();
			File file;
			for (int i = 0; i < files.length; i++) {
				file = files[i];
				logger.info(" - " + file.toURI().toURL());
				addURL(file.toURI().toURL());
			}
		} else {
			logger.warn("The directory \"" + directory + "\" does not exist!");
		}

		logger.debug("FIN");
		return null;

	}

	/**
	 * Add URL to CLASSPATH
	 * 
	 * 
	 * 
	 * @param u
	 *            URL
	 * 
	 *            on * @throws IOException * @throws IOException
	 */
	public static void addURL(URL u) throws IOException {
		URLClassLoader sysLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
		URL urls[] = sysLoader.getURLs();
		for (int i = 0; i < urls.length; i++) {

			if (urls[i].toString().equalsIgnoreCase(u.toString())) {
				logger.info("URL " + u + " is already in the CLASSPATH");
				return;
			}
		}
		Class<URLClassLoader> sysclass = URLClassLoader.class;
		try {
			Method method = sysclass.getDeclaredMethod("addURL", parameters);
			method.setAccessible(true);
			method.invoke(sysLoader, new Object[] { u });
		} catch (Throwable t) {
			logger.error("error adding url to classpath");
			throw new IOException("Error, could not add URL to system classloader");
		}
		/*
		 * finally{ logger.info("finalmente revisamos el classpath con los cambios");
		 * sysLoader = (URLClassLoader) ClassLoader.getSystemClassLoader(); URL urls2[]
		 * = sysLoader.getURLs(); for (int i = 0; i < urls2.length; i++) {
		 * logger.info("URL " + urls2[i].toString()); }
		 * 
		 * }
		 */
	}

	/**
	 * @author programador6
	 */
	private static class CRClassLoader extends URLClassLoader {

		/**
		 * Constructor for My_URLClassLoader.
		 */
		public CRClassLoader() {
			super(new URL[] {});
		}

		/**
		 * Method addURL.
		 * 
		 * @param url
		 *            URL
		 */
		@Override
		public void addURL(URL url) {
			super.addURL(url);
		}
	}

	/**
	 * Add classPath to this loader's classpath.
	 * <p>
	 * The classpath may contain elements that include a generic file base name. A
	 * generic basename is a filename without the extension that may begin and/or
	 * end with an asterisk. Use of the asterisk denotes a partial match. Any files
	 * with an extension of ".jar" whose base name match the specified basename will
	 * be added to this class loaders classpath. The case of the filename is
	 * ignored. For example "/somedir/*abc" means all files in somedir that end with
	 * "abc.jar", "/somedir/abc*" means all files that start with "abc" and end with
	 * ".jar", and "/somedir/*abc*" means all files that contain "abc" and end with
	 * ".jar".
	 * 
	 */
	/*
	 * public void addClassPath(String cp) { String seps=File.pathSeparator; //
	 * separators
	 * 
	 * if(!File.pathSeparator.equals(";")) { seps+=";"; } // want to accept both
	 * system separator and ';' for(StringTokenizer st=new
	 * StringTokenizer(cp,seps,false); st.hasMoreTokens(); ) { String
	 * pe=st.nextToken(); File fe; String bn=null;
	 * 
	 * if(pe.length()==0) { continue; }
	 * 
	 * fe=new File(pe); if(fe.getName().indexOf('*')!=-1) { bn=fe.getName();
	 * fe=fe.getParentFile(); }
	 * 
	 * if(!fe.isAbsolute() && pe.charAt(0)!='/' && pe.charAt(0)!='\\') { fe=new
	 * File(rootPath,fe.getPath()); } try { fe=fe.getCanonicalFile(); }
	 * catch(IOException thr) {
	 * log.diagln("Skipping non-existent classpath element '" +fe+"' ("+thr+").");
	 * continue; } if(!GenUtil.isBlank(bn)) { fe=new File(fe,bn); }
	 * if(classPathElements.contains(fe.getPath())) {
	 * log.diagln("Skipping duplicate classpath element '"+fe+"'."); continue; }
	 * else { classPathElements.add(fe.getPath()); }
	 * 
	 * if(!GenUtil.isBlank(bn)) { addJars(fe.getParentFile(),bn); } else
	 * if(!fe.exists()) { // s/never be due getCanonicalFile() above
	 * log.diagln("Could not find classpath element '"+fe+"'"); } else
	 * if(fe.isDirectory()) { addURL(createUrl(fe)); } else
	 * if(fe.getName().toLowerCase().endsWith(".zip") ||
	 * fe.getName().toLowerCase().endsWith(".jar")) { addURL(createUrl(fe)); } else
	 * { log.diagln("ClassPath element '"+fe+
	 * "' is not an existing directory and is not a file ending with '.zip' or '.jar'"
	 * ); } } log.diagln("Class loader is using classpath: \""+classPath+"\"."); }
	 * 
	 * /** Adds a set of JAR files using a generic base name to this loader's
	 * classpath. See @link:addClassPath(String) for details of the generic base
	 * name.
	 */
	/*
	 * public void addJars(File dir, String nam) { String[] jars; // matching jar
	 * files
	 * 
	 * if(nam.endsWith(".jar")) { nam=nam.substring(0,(nam.length()-4)); }
	 * 
	 * if(!dir.exists()) {
	 * log.diagln("Could not find directory for Class Path element '"
	 * +dir+File.separator+nam+".jar'"); return; } if(!dir.canRead()) { log.error
	 * ("Could not read directory for Class Path element '"+dir+File.separator
	 * +nam+".jar'"); return; }
	 * 
	 * FileSelector fs=new
	 * FileSelector(true).add("BaseName","EG",nam,true).add("Name"
	 * ,"EW",".jar",true); if((jars=dir.list(fs))==null) {
	 * log.error("Error accessing directory for Class Path element '"
	 * +dir+File.separator+nam+".jar'"); } else if(jars.length==0) {
	 * log.diagln("No JAR files match specification '"+new File(dir,nam)+".jar'"); }
	 * else { log.diagln("Adding files matching specification '"
	 * +dir+File.separator+nam+".jar'");
	 * Arrays.sort(jars,String.CASE_INSENSITIVE_ORDER); for(int xa=0;
	 * xa<jars.length; xa++) { addURL(createUrl(new File(dir,jars[xa]))); } } }
	 * 
	 * private URL createUrl(File fe) { try { URL url=fe.toURI().toURL();
	 * log.diagln("Added URL: '"+url.toString()+"'"); if(classPath.length()>0) {
	 * classPath+=File.pathSeparator; } this.classPath+=fe.getPath(); return url; }
	 * catch(MalformedURLException thr) { log.diagln("Classpath element '"
	 * +fe+"' could not be used to create a valid file system URL"); return null; }
	 * }
	 */

}
