package com.dpdgroup.androidversions.asynctask;

import android.os.AsyncTask;

import com.dpdgroup.androidversions.activity.MainActivity;
import com.dpdgroup.androidversions.persistence.AppDatabase;
import com.dpdgroup.androidversions.persistence.entity.Version;

import java.lang.ref.WeakReference;
import java.util.List;

public class RetrieveTask extends AsyncTask<Void, Void, List<Version>> {
    private WeakReference<MainActivity> activityReference;
    private List<Version> entities;
    private AppDatabase dbVersions;

    // only retain a weak reference to the activity
    public RetrieveTask(MainActivity context, List<Version> versions, AppDatabase dbVersions) {
        activityReference = new WeakReference<>(context);
        this.entities = versions;
        this.dbVersions = dbVersions;
    }

    @Override
    protected List<Version> doInBackground(Void... voids) {
        if (activityReference.get() != null)
            return dbVersions.getVersionDao().getAll();
        else
            return null;
    }

    @Override
    protected void onPostExecute(List<Version> versions) {
        if (versions != null && versions.size() > 0) {
            entities = versions;
        }
    }
}
