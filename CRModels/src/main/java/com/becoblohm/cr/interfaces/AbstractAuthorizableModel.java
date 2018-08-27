package com.becoblohm.cr.interfaces;

import com.becoblohm.cr.types.AuditTables;
import com.epa.mvc.core.AbstractModel;

/**
 * Esta interfaz debe ser implementada por todos los modelos cuyas autorizaciones se
 * quieran guardar en las tablas de auditoria
 */
public abstract class AbstractAuthorizableModel extends AbstractModel {

    private AuditTables authorizedTableName;

    public AbstractAuthorizableModel(AuditTables authorizedTableName) {
        if (authorizedTableName == null) {
            throw new IllegalArgumentException("el nombre de la tabla que se autoriza no puede ser nulo");
        }
        this.authorizedTableName = authorizedTableName;
    }

    /**
     * Retorna el id del objeto para que sea guardado en las tablas de auditoria
     * 
     * @return
     */
    public abstract String getAuditID();

    public AuditTables getAuthorizedTableName() {
        return authorizedTableName;
    };

}
