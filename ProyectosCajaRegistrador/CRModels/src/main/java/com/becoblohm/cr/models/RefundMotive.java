package com.becoblohm.cr.models;

import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * @author eve0018536 (Mario Ortega)
 *
 */
public class RefundMotive {

    private static final long serialVersionUID = 1L;

    private long id;

    private BigInteger motiveCode;

    private String motiveDescription;

    private boolean active;

    private String motiveType;

    private Timestamp date;

    public RefundMotive() {
        super();
    }

    public RefundMotive(long id, BigInteger motiveCode, String motiveDescription, boolean active,
                        String motiveType, Timestamp date) {
        super();
        this.id = id;
        this.motiveCode = motiveCode;
        this.motiveDescription = motiveDescription;
        this.active = active;
        this.motiveType = motiveType;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigInteger getMotiveCode() {
        return motiveCode;
    }

    public void setMotiveCode(BigInteger motiveCode) {
        this.motiveCode = motiveCode;
    }

    public String getMotiveDescription() {
        return motiveDescription;
    }

    public void setMotiveDescription(String motiveDescription) {
        this.motiveDescription = motiveDescription;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getMotiveType() {
        return motiveType;
    }

    public void setMotiveType(String motiveType) {
        this.motiveType = motiveType;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

}
