package com.grid.ventas.cr.refundrestserver.conf;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;

/**
 * Created by eve0017280 on 20/05/16.
 */
// @Configuration
public class SecurityServerConfig {

    /*
     * @Bean public EmbeddedServletContainerFactory servletContainer() {
     * TomcatEmbeddedServletContainerFactory tomcat = new
     * TomcatEmbeddedServletContainerFactory();
     * tomcat.addAdditionalTomcatConnectors(createSslConnector()); return tomcat; }
     * private Connector createSslConnector() { Connector connector = new
     * Connector("org.apache.coyote.http11.Http11NioProtocol"); Http11NioProtocol protocol
     * = (Http11NioProtocol) connector.getProtocolHandler(); try { File keystore = new
     * ClassPathResource("keystore.p12").getFile(); File truststore = new
     * ClassPathResource("keystore.p12").getFile(); connector.setScheme("https");
     * connector.setSecure(true); connector.setPort(8443); protocol.setSSLEnabled(true);
     * protocol.setKeystoreType("PKCS12"); protocol.setCiphers(
     * "TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256,TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA,TLS_RSA_WITH_AES_128_CBC_SHA256,TLS_RSA_WITH_AES_128_CBC_SHA"
     * ); protocol.setTrustMaxCertLength("128");
     * protocol.setKeystoreFile(keystore.getAbsolutePath());
     * protocol.setKeystorePass("Epa12345678");
     * protocol.setTruststoreFile(truststore.getAbsolutePath());
     * protocol.setTruststorePass("Epa12345678"); protocol.setKeyAlias("epa"); return
     * connector; } catch (IOException ex) { throw new IllegalStateException(
     * "can't access keystore: [" + "keystore" + "] or truststore: [" + "keystore" + "]",
     * ex); } }
     */
}
