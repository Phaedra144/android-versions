package com.dpdgroup.androidversions.network;

import com.dpdgroup.androidversions.network.response.AndroidVersionsResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AndroidVersionsAPI {

    @GET(" ")
    Call<AndroidVersionsResponse> callAndroidVersions();
}
