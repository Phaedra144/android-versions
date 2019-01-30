package com.dpdgroup.androidversions.network;

import com.dpdgroup.androidversions.network.response.VersionResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface VersionAPI {

    @GET(" ")
    Call<VersionResponse> callAndroidVersions();
}
