package com.dpdgroup.androidversions.asynctask;

import android.os.AsyncTask;

import com.dpdgroup.androidversions.persistence.AppDatabase;
import com.dpdgroup.androidversions.persistence.entity.VersionEntity;

import java.util.List;

public class InsertTask extends AsyncTask<Void, Void, Boolean> {
    private List<VersionEntity> versionEntities;
    private AppDatabase dbVersions;
    // only retain a weak reference to the activity

    public InsertTask(List<VersionEntity> versionEntities, AppDatabase dbVersions) {
        this.versionEntities = versionEntities;
        this.dbVersions = dbVersions;
    }
    // doInBackground methods runs on a worker thread

    @Override
    protected Boolean doInBackground(Void... objs) {
        dbVersions.getVersionDao().deleteTable();
        dbVersions.getVersionDao().insertAll(versionEntities);
        return true;
    }

    // onPostExecute runs on main thread
    @Override
    protected void onPostExecute(Boolean bool) {
    }
}
