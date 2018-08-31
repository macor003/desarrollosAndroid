package com.becoblohm.cr.conf.controller;

import com.becoblohm.cr.conf.Global;
import com.becoblohm.cr.conf.controller.AuditoriaJpaController;
import com.becoblohm.cr.conf.model.Audit;

public class AuditController {

    private Audit model;

    private AuditoriaJpaController jpaController;

    public Audit getModel() {
        return model;
    }

    public void setModel(Audit model) {
        this.model = model;
    }

    public AuditController(Audit m) {
        this.model = m;
        jpaController = new AuditoriaJpaController(Global.getEntityManagerFactory());
    }

    public void addAudit() {
        jpaController.addAudit(model);
    }
}
