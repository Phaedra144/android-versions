package com.dpdgroup.androidversions.activity;

import android.os.Bundle;
import android.widget.Toast;

import com.dpdgroup.androidversions.R;
import com.dpdgroup.androidversions.adapter.AndroidVersionAdapter;
import com.dpdgroup.androidversions.asynctask.InsertTask;
import com.dpdgroup.androidversions.asynctask.RetrieveTask;
import com.dpdgroup.androidversions.model.AndroidVersion;
import com.dpdgroup.androidversions.network.AndroidVersionsAPI;
import com.dpdgroup.androidversions.network.RetrofitService;
import com.dpdgroup.androidversions.network.response.AndroidVersionsResponse;
import com.dpdgroup.androidversions.persistence.AppDatabase;
import com.dpdgroup.androidversions.persistence.entity.Version;
import com.dpdgroup.androidversions.persistence.service.DbService;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    static List<AndroidVersion> savedVersions;
    List<Version> entities;
    AppDatabase dbVersions;
    DbService dbService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        savedVersions = new ArrayList<>();
        entities = new ArrayList<>();
        dbService = new DbService();
        requestAndroidVersions();
        displayList();

        final SwipeRefreshLayout pullToRefresh = findViewById(R.id.pullToRefresh);
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestAndroidVersions();
                pullToRefresh.setRefreshing(false);
            }
        });
    }

    private void requestAndroidVersions() {
        AndroidVersionsAPI apiService = RetrofitService.getAndroidVersions();
        Call<AndroidVersionsResponse> call = apiService.callAndroidVersions();
        call.enqueue(new Callback<AndroidVersionsResponse>() {
            @Override
            public void onResponse(Call<AndroidVersionsResponse> call, Response<AndroidVersionsResponse> response) {
                savedVersions = response.body();
                initRecycleView((ArrayList<AndroidVersion>) savedVersions);
                saveToDatabase();
            }

            @Override
            public void onFailure(Call<AndroidVersionsResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void initRecycleView(ArrayList<AndroidVersion> androidVersions) {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        DividerItemDecoration itemDecorator = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(ContextCompat.getDrawable(this, R.drawable.rectangle));
        recyclerView.addItemDecoration(itemDecorator);
        recyclerView.setAdapter(new AndroidVersionAdapter(androidVersions, MainActivity.this));
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    private void saveToDatabase() {
        if (dbService.calledResultEqualsEntities(savedVersions, entities)) {
            Toast.makeText(MainActivity.this, "Database is up-to-date, no new item!", Toast.LENGTH_SHORT).show();
        } else {
            entities = dbService.getEntitiesFromAndroidVersions(savedVersions);
            new InsertTask(MainActivity.this, entities, dbVersions).execute();
            Toast.makeText(MainActivity.this, "Database has been updated with new item(s)!", Toast.LENGTH_SHORT).show();
        }
    }

    private void displayList() {
        // initialize database instance
        dbVersions = AppDatabase.getInstance(MainActivity.this);
        // fetch list of notes in background thread
        new RetrieveTask(this, entities, dbVersions).execute();
    }
}
