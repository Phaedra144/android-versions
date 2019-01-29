package com.dpdgroup.versions.android.androidversions.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.dpdgroup.versions.android.androidversions.R;
import com.dpdgroup.versions.android.androidversions.model.AndroidVersion;
import com.dpdgroup.versions.android.androidversions.network.AndroidVersionsAPI;
import com.dpdgroup.versions.android.androidversions.network.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TextView hello;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hello = findViewById(R.id.helloText);
        requestAndroidVersions();
    }

    private void requestAndroidVersions() {
        AndroidVersionsAPI apiService = RetrofitService.getAndroidVersions();
        Call<List<AndroidVersion>> call = apiService.callAndroidVersions();
        call.enqueue(new Callback<List<AndroidVersion>>() {
            @Override
            public void onResponse(Call<List<AndroidVersion>> call, Response<List<AndroidVersion>> response) {
                ArrayList<AndroidVersion> androidVersions = (ArrayList<AndroidVersion>) response.body();
                hello.setText(androidVersions.get(0).getCodeName());
            }

            @Override
            public void onFailure(Call<List<AndroidVersion>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
