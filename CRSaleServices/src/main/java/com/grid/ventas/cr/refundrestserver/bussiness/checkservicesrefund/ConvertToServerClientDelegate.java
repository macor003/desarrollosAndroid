package com.grid.ventas.cr.refundrestserver.bussiness.checkservicesrefund;

import biz.epa.ruleengine.AbstractAction;
import biz.epa.ruleengine.RuleEngineContext;
import com.becoblohm.cr.models.AnulDev;
import com.becoblohm.cr.net.response.ServicesResponse;
import com.becoblohm.cr.sync.converters.ClienteConverter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManagerFactory;

/**
 * Created by eve0017280 on 04/04/16.
 */
public class ConvertToServerClientDelegate extends AbstractAction {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    protected void doExecute(RuleEngineContext ruleEngineContext) throws Exception {
        AnulDev anulDev = (AnulDev) ruleEngineContext.get("anulDev");

        ClienteConverter converter = new ClienteConverter();
        try {
            anulDev.setClient(converter.fromServer(anulDev.getClient(), entityManagerFactory, null));
        } catch (Exception e) {
            ServicesResponse response = null;
            response.setCode(-1);
            response.setMsg("Error interno del servicio de devoluciones");
            ruleEngineContext.put("result", response);
            // logger.error("Problemas al convertir cliente con SyncConverter->", e);
        }
    }
}
