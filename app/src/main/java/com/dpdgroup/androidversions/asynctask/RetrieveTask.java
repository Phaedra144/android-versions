package com.dpdgroup.androidversions.asynctask;

import android.os.AsyncTask;

import com.dpdgroup.androidversions.activity.MainActivity;
import com.dpdgroup.androidversions.persistence.AppDatabase;
import com.dpdgroup.androidversions.persistence.entity.Version;

import java.lang.ref.WeakReference;
import java.util.List;

public class RetrieveTask extends AsyncTask<Void, Void, List<Version>> {
    private List<Version> entities;
    private AppDatabase dbVersions;

    // only retain a weak reference to the activity
    public RetrieveTask(List<Version> versions, AppDatabase dbVersions) {
        this.entities = versions;
        this.dbVersions = dbVersions;
    }

    @Override
    protected List<Version> doInBackground(Void... voids) {
            return dbVersions.getVersionDao().getAll();
    }

    @Override
    protected void onPostExecute(List<Version> versions) {
        if (versions != null && versions.size() > 0) {
            entities = versions;
        }
    }
}
