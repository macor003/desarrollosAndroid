package com.becoblohm.cr.types;

public enum AuditTables {
    TRANSACTION("transaccion"), CREDITSMOVEMENT("acreenciamovimiento"), DELIVERY("entrega"), CLIENT("cliente"),
    DEPOSIT("pedido"), SESSION("Sesion"), VPOSADITIONALDATA("Formadepagopuntoagil");

    private String tableName;

    AuditTables(String tableName) {
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }
}
