package com.becoblohm.cr.response;

import com.becoblohm.cr.models.AnulDev;
import com.becoblohm.cr.net.response.RMIServerResponse;

public class ServicesAnulDevResponse extends RMIServerResponse {

    /**
     * Field msg.
     */
    private String msg;

    /**
     * Field data.
     */
    private AnulDev data;

    /**
     * Field code.
     */
    private int code;

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for ServicesResponse.
     * 
     * @param msg String
     * @param data Object
     */
    public ServicesAnulDevResponse(String msg, AnulDev data, int code) {
        super(msg, data, code);
        this.msg = msg;
        this.data = data;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public AnulDev getData() {
        return data;
    }

    public void setData(AnulDev data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ServicesAnulDevResponse() {
    }
}