package com.grid.ventas.cr.refundrestserver.util.xmlrpc.client;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.apache.xmlrpc.client.XmlRpcSunHttpTransportFactory;
import org.apache.xmlrpc.client.XmlRpcTransport;
import org.apache.xmlrpc.client.XmlRpcTransportFactory;
import org.apache.xmlrpc.client.util.ClientFactory;

import java.net.URL;

public class EPAXmlRpcClient {

    private XmlRpcClient server = null;

    private ClientFactory factory = null;

    private static final Log log = LogFactory.getLog(EPAXmlRpcClient.class);

    public EPAXmlRpcClient(String url, boolean debug) throws Exception {

        log.info("(1)Contactando al servidor.");

        // Crear la conexi�n al servidor
        XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
        config.setServerURL(new URL(url));
        // config.setServerURL(new
        // URL("http://localhost:8080/serverRPC/xmlrpc"));
        log.info("(2)Configurando el cliente...");
        config.setEnabledForExtensions(true);
        config.setEnabledForExceptions(true);

        config.setConnectionTimeout(60 * 1000);
        config.setReplyTimeout(60 * 1000);

        // debug=true no empaqueta para que se pueda leer el XML
        config.setGzipCompressing(!debug);
        config.setGzipRequesting(!debug);

        config.setContentLengthOptional(true);

        // Utilizado para llamadas directas a una clase.
        server = new XmlRpcClient();

        // Determina si estamos en depuracion
        if (debug) {
            log.info("(2.1)Estableciendo transporte para depurar XML...");
            // Depurar los xml recibidos y enviados
            final XmlRpcTransportFactory DebugTransportFactory = new XmlRpcTransportFactory() {

                public XmlRpcTransport getTransport() {
                    return new EPATransportDebug(server);
                }
            };
            server.setTransportFactory(DebugTransportFactory);
        } else {
            log.info("(2.1)Estableciendo transporte tipo SUN...");
            // Usando el transporte de SUN mejor desempe�o que el de Jakarta
            // Commons
            server.setTransportFactory(new XmlRpcSunHttpTransportFactory(server));
        }
        // set configuration
        server.setConfig(config);

        log.info("(3)Creando el proxy...");
        // Utilizado para llamadas via Interface
        factory = new ClientFactory(server);
        log.info("(4)Enlace configurado.");
    }

    public XmlRpcClient getServer() {
        return server;
    }

    public ClientFactory getFactory() {
        return factory;
    }

    public Log getLog() {
        return log;
    }

}
