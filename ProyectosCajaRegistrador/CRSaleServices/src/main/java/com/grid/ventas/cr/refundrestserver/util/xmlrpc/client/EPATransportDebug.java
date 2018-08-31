package com.grid.ventas.cr.refundrestserver.util.xmlrpc.client;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcSunHttpTransport;
import org.apache.xmlrpc.common.XmlRpcStreamRequestConfig;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * This is a custom XML-RPC transport which logs the outgoing and incoming XML-RPC
 * messages.
 */
public class EPATransportDebug extends XmlRpcSunHttpTransport {

    private static final Log log = LogFactory.getLog(EPATransportDebug.class);

    /**
     * Default constructor
     *
     * @param pClient
     * @see org.apache.xmlrpc.client.XmlRpcSunHttpTransport#XmlRpcSunHttpTransport(org.apache.xmlrpc.client.XmlRpcClient)
     */
    public EPATransportDebug(final XmlRpcClient pClient) {
        super(pClient);
    }

    /**
     * Dumps outgoing XML-RPC requests to the log
     */
    @Override
    protected void writeRequest(final ReqWriter pWriter) throws IOException, XmlRpcException, SAXException {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        pWriter.write(baos);
        log.info(baos.toString());
        super.writeRequest(pWriter);
    }

    /**
     * Dumps incoming XML-RPC responses to the log
     */
    @Override
    protected Object readResponse(XmlRpcStreamRequestConfig pConfig, InputStream pStream) throws XmlRpcException {
        final StringBuffer sb = new StringBuffer();

        try {
            final BufferedReader reader = new BufferedReader(new InputStreamReader(pStream));
            String line = reader.readLine();
            while (line != null) {
                sb.append(line);
                line = reader.readLine();
            }
        } catch (final IOException e) {
            log.error("While reading server response" + e);
        }

        log.info(sb.toString());

        final ByteArrayInputStream bais = new ByteArrayInputStream(sb.toString().getBytes());
        return super.readResponse(pConfig, bais);
    }
}
