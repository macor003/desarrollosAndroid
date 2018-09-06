package com.macor.comandas.api.service;

import com.becoblohm.cr.net.response.RMIServerResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ServiceClient {

    @GET("clientOrder/searchCommands")
    Call<RMIServerResponse> searchCommands();
}
