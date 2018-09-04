package com.becoblohm.cr.response;

import java.util.List;

import com.becoblohm.cr.models.Transaction;
import com.becoblohm.cr.net.response.RMIServerResponse;

/**
 * Created by epauser on 09/04/16.
 */
public class FindWaitingSaleResponse extends RMIServerResponse {

    private int code;

    private List<Transaction> data;

    public FindWaitingSaleResponse() {
    }

    public FindWaitingSaleResponse(int code, List<com.becoblohm.cr.models.Transaction> data) {

        this.code = code;
        this.data = data;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public List<Transaction> getData() {
        return data;
    }

    @Override
    public void setCode(int code) {
        this.code = code;
    }

    public void setData(List<Transaction> data) {
        this.data = data;
    }
}
