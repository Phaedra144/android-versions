package com.dpdgroup.androidversions.asynctask;

import android.os.AsyncTask;

import com.dpdgroup.androidversions.persistence.AppDatabase;
import com.dpdgroup.androidversions.persistence.entity.Version;

import java.util.List;

public class InsertTask extends AsyncTask<Void, Void, Boolean> {
    private List<Version> versions;
    private AppDatabase dbVersions;
    // only retain a weak reference to the activity

    public InsertTask(List<Version> versions, AppDatabase dbVersions) {
        this.versions = versions;
        this.dbVersions = dbVersions;
    }
    // doInBackground methods runs on a worker thread

    @Override
    protected Boolean doInBackground(Void... objs) {
        dbVersions.getVersionDao().deleteTable();
        dbVersions.getVersionDao().insertAll(versions);
        return true;
    }

    // onPostExecute runs on main thread
    @Override
    protected void onPostExecute(Boolean bool) {
    }
}
