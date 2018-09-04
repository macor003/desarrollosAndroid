package com.grid.ventas.cr.refundrestserver.service.impl;

import com.becoblohm.cr.models.Transaction;
import com.becoblohm.cr.net.response.ServicesResponse;
import com.epa.ventas.dao.SADVTADAO;
import com.epa.ventas.dto.SADVTA;
import com.grid.ventas.cr.refundrestserver.service.CancellationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

@Service
public class CancellationServiceImpl implements CancellationService {

    private static final Logger logger = LoggerFactory.getLogger(CancellationServiceImpl.class);

    @Autowired
    private DataSource datasource;

    private String searchCancel(String pos, String transa) {

        String result = "";
        List<SADVTA> dataByTransa = null;
        SADVTADAO persistenceSadvta = new SADVTADAO(datasource);
        try {
            debug("Validando si la transaccion con caja " + pos + ", numero " + transa + " fue anulada");
            dataByTransa = persistenceSadvta.getDataByTransa(Long.valueOf(pos), Long.valueOf(transa));
        } catch (SQLException e) {
            debug("Error al realizar busqueda en SADVTA: " + e);
            result = null;
        }
        if (dataByTransa != null && dataByTransa.size() > 0) {
            StringBuffer sb = new StringBuffer();
            for (SADVTA trasan : dataByTransa) {
                sb.append(trasan.getTransan() + ",");
            }
            if (sb.charAt(sb.length() - 1) == ',') {
                sb.deleteCharAt(sb.length() - 1);
            }
            result = sb.toString();
        }
        return result;
    }

    @Override
    public ServicesResponse searchCancel(Transaction data) {
        debug("Buscando si la transaccion " + data.getNumber() + " fue anulada");

        ServicesResponse response = null;

        String transAnul = searchCancel(data.getPosId(), data.getNumber());

        if (transAnul == null) {
            response = new ServicesResponse("Error al consultar si la transaccion fue anulada.", transAnul);
            response.setCode(-1);
        } else if ("".equals(transAnul)) {
            response = new ServicesResponse("La transaccion no ha sido anulada anteriormente.", transAnul);
            response.setCode(0);
        } else {
            response = new ServicesResponse(
                    "La transaccion fue anulada anteriormente por la transaccion " + transAnul + ".", transAnul);
            response.setCode(-101);
        }

        return response;
    }

    private void debug(String msg) {
        if (logger.isDebugEnabled()) {
            logger.debug(msg);
        }
    }

    private void info(String msg) {
        if (logger.isInfoEnabled()) {
            logger.info(msg);
        }
    }
}
