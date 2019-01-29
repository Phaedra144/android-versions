package com.dpdgroup.versions.android.androidversions.network;

import com.dpdgroup.versions.android.androidversions.model.AndroidVersion;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AndroidVersionsAPI {

    @GET(" ")
    Call<List<AndroidVersion>> callAndroidVersions();
}
