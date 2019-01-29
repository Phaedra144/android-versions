package com.dpdgroup.versions.android.androidversions.network;

import com.dpdgroup.versions.android.androidversions.network.response.AndroidVersionsResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AndroidVersionsAPI {

    @GET(" ")
    Call<AndroidVersionsResponse> callAndroidVersions();
}
