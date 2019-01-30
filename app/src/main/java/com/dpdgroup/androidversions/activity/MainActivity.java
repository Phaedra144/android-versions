package com.dpdgroup.androidversions.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import com.dpdgroup.androidversions.R;
import com.dpdgroup.androidversions.adapter.AndroidVersionAdapter;
import com.dpdgroup.androidversions.model.AndroidVersion;
import com.dpdgroup.androidversions.network.AndroidVersionsAPI;
import com.dpdgroup.androidversions.network.RetrofitService;
import com.dpdgroup.androidversions.network.response.AndroidVersionsResponse;
import com.dpdgroup.androidversions.persistence.AppDatabase;
import com.dpdgroup.androidversions.persistence.entity.Version;
import com.dpdgroup.androidversions.persistence.service.DbService;

import java.lang.ref.WeakReference;
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

    static List<AndroidVersion> savedVersions = new ArrayList<>();
    List<Version> entities = new ArrayList<>();
    AppDatabase dbVersions;
    DbService dbService = new DbService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                saveToDataBase();
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

    private void saveToDataBase() {
        if (dbService.calledResultEqualsEntities(savedVersions, entities)) {
            Toast.makeText(MainActivity.this, "Database is up-to-date, no new item!", Toast.LENGTH_SHORT).show();
        } else {
            entities = dbService.getEntitiesFromAndroidVersions(savedVersions);
            new InsertTask(MainActivity.this, entities).execute();
            Toast.makeText(MainActivity.this, "Database has been updated with new item(s)!", Toast.LENGTH_SHORT).show();
        }
    }

    private static class InsertTask extends AsyncTask<Void, Void, Boolean> {
        private WeakReference<MainActivity> activityReference;
        private List<Version> versions;
        // only retain a weak reference to the activity

        InsertTask(MainActivity context, List<Version> versions) {
            activityReference = new WeakReference<>(context);
            this.versions = versions;
        }
        // doInBackground methods runs on a worker thread

        @Override
        protected Boolean doInBackground(Void... objs) {
            activityReference.get().dbVersions.getVersionDao().deleteTable();
            activityReference.get().dbVersions.getVersionDao().insertAll(versions);
            return true;
        }

        // onPostExecute runs on main thread
        @Override
        protected void onPostExecute(Boolean bool) {
        }
    }

    private void displayList() {
        // initialize database instance
        dbVersions = AppDatabase.getInstance(MainActivity.this);
        // fetch list of notes in background thread
        new RetrieveTask(this).execute();
    }

    private static class RetrieveTask extends AsyncTask<Void, Void, List<Version>> {
        private WeakReference<MainActivity> activityReference;

        // only retain a weak reference to the activity
        RetrieveTask(MainActivity context) {
            activityReference = new WeakReference<>(context);
        }

        @Override
        protected List<Version> doInBackground(Void... voids) {
            if (activityReference.get() != null)
                return activityReference.get().dbVersions.getVersionDao().getAll();
            else
                return null;
        }

        @Override
        protected void onPostExecute(List<Version> versions) {
            if (versions != null && versions.size() > 0) {
                activityReference.get().entities = versions;
            }
        }
    }
}
