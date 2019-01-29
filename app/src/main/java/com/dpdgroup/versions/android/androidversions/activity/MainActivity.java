package com.dpdgroup.versions.android.androidversions.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.dpdgroup.versions.android.androidversions.R;
import com.dpdgroup.versions.android.androidversions.model.AndroidVersion;
import com.dpdgroup.versions.android.androidversions.network.AndroidVersionsAPI;
import com.dpdgroup.versions.android.androidversions.network.RetrofitService;
import com.dpdgroup.versions.android.androidversions.network.response.AndroidVersionsResponse;
import com.dpdgroup.versions.android.androidversions.persistence.AppDatabase;
import com.dpdgroup.versions.android.androidversions.persistence.entity.Version;
import com.dpdgroup.versions.android.androidversions.persistence.service.DbService;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    static List<AndroidVersion> savedVersions = new ArrayList<>();
    List<Version> entities = new ArrayList<>();
    TextView hello;
    AppDatabase dbVersions;
    DbService dbService = new DbService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hello = findViewById(R.id.helloText);
        requestAndroidVersions();
        displayList();
    }

    private void requestAndroidVersions() {
        AndroidVersionsAPI apiService = RetrofitService.getAndroidVersions();
        Call<AndroidVersionsResponse> call = apiService.callAndroidVersions();
        call.enqueue(new Callback<AndroidVersionsResponse>() {
            @Override
            public void onResponse(Call<AndroidVersionsResponse> call, Response<AndroidVersionsResponse> response) {
                savedVersions = response.body();
                if (calledResultEqualsEntities(savedVersions, entities)) {
                    Toast.makeText(MainActivity.this, "Database is up-to-date, no new item!", Toast.LENGTH_SHORT).show();
                } else {
                    entities = dbService.getEntitiesFromAndroidVersions(savedVersions);
                    new InsertTask(MainActivity.this, entities).execute();
                    Toast.makeText(MainActivity.this, "Database has been updated with new item(s)!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AndroidVersionsResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private boolean calledResultEqualsEntities(List<AndroidVersion> savedVersions, List<Version> entities) {
        boolean result = true;
        if (savedVersions.size() != entities.size()) {
            return false;
        }
        Collections.sort(savedVersions);
        Collections.sort(entities);
        for (int i = 0; i < savedVersions.size(); i++) {
            if(!savedVersions.get(i).getCodeName().equals(entities.get(i).getCodeName())) {
                result = false;
                break;
            }
        }
        return result;
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
                for (Version v : activityReference.get().entities) {
                    Log.i("entity - Code name", v.getCodeName());
                }


                // create and set the adapter on RecyclerView instance to display list
//                activityReference.get().notesAdapter = new NotesAdapter(notes, activityReference.get());
//                activityReference.get().recyclerView.setAdapter(activityReference.get().notesAdapter);
            }
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
}
