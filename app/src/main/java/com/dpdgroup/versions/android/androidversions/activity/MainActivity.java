package com.dpdgroup.versions.android.androidversions.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.dpdgroup.versions.android.androidversions.R;
import com.dpdgroup.versions.android.androidversions.model.AndroidVersion;
import com.dpdgroup.versions.android.androidversions.network.AndroidVersionsAPI;
import com.dpdgroup.versions.android.androidversions.network.RetrofitService;
import com.dpdgroup.versions.android.androidversions.network.response.AndroidVersionsResponse;
import com.dpdgroup.versions.android.androidversions.persistence.AppDatabase;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    static List<AndroidVersion> savedVersions = new ArrayList<>();
    TextView hello;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hello = findViewById(R.id.helloText);
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "versions").build();
        savedVersions = db.dbService().convertEntitiesToModels(db.versionDao().getAll());
        requestAndroidVersions();
    }

    private void requestAndroidVersions() {
        AndroidVersionsAPI apiService = RetrofitService.getAndroidVersions();
        Call<AndroidVersionsResponse> call = apiService.callAndroidVersions();
        call.enqueue(new Callback<AndroidVersionsResponse>() {
            @Override
            public void onResponse(Call<AndroidVersionsResponse> call, Response<AndroidVersionsResponse> response) {
                savedVersions = response.body();
                hello.setText(savedVersions.get(0).getCodeName());
            }

            @Override
            public void onFailure(Call<AndroidVersionsResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    protected void onStop() {

        super.onStop();
    }
}
