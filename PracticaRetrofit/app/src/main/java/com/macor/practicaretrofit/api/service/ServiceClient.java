package com.macor.practicaretrofit.api.service;

import com.becoblohm.cr.net.response.RMIServerResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ServiceClient {

    @GET("clientOrder/searchCommands")
    Call<RMIServerResponse> searchCommands();
}
