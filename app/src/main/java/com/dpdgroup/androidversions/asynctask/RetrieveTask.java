package com.dpdgroup.androidversions.asynctask;

import android.os.AsyncTask;

import com.dpdgroup.androidversions.persistence.AppDatabase;
import com.dpdgroup.androidversions.persistence.entity.VersionEntity;

import java.util.List;

public class RetrieveTask extends AsyncTask<Void, Void, List<VersionEntity>> {
    private List<VersionEntity> entities;
    private AppDatabase dbVersions;

    // only retain a weak reference to the activity
    public RetrieveTask(List<VersionEntity> versionEntities, AppDatabase dbVersions) {
        this.entities = versionEntities;
        this.dbVersions = dbVersions;
    }

    @Override
    protected List<VersionEntity> doInBackground(Void... voids) {
            return dbVersions.getVersionDao().getAll();
    }

    @Override
    protected void onPostExecute(List<VersionEntity> versionEntities) {
        if (versionEntities != null && versionEntities.size() > 0) {
            entities = versionEntities;
        }
    }
}
