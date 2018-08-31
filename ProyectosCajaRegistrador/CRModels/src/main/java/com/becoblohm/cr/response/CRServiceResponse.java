package com.becoblohm.cr.response;

import java.io.Serializable;

import com.becoblohm.cr.net.response.RMIServerResponse;

public class CRServiceResponse<T> extends RMIServerResponse implements Serializable {

    private static final long serialVersionUID = 6912263862384563836L;

    private int code;

    private String message;

    private T payload;

    public CRServiceResponse() {
        this(-1, "", null);
    }

    public CRServiceResponse(int code) {
        this(code, "", null);
    }

    public CRServiceResponse(int code, String message) {
        this(code, message, null);
    }

    public CRServiceResponse(int code, String message, T payload) {
        super();
        this.code = code;
        this.message = message;
        this.payload = payload;
    }

    public boolean isOk() {
        return code == 0;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }

}
