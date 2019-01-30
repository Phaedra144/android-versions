package com.dpdgroup.androidversions.asynctask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.dpdgroup.androidversions.activity.MainActivity;
import com.dpdgroup.androidversions.model.Version;
import com.dpdgroup.androidversions.persistence.AppDatabase;
import com.dpdgroup.androidversions.persistence.entity.VersionEntity;
import com.dpdgroup.androidversions.persistence.service.DbService;

import java.util.ArrayList;
import java.util.List;

public class RetrieveTask extends AsyncTask<Void, Void, List<VersionEntity>> {

    private AppDatabase dbVersions;
    private MainActivity context;
    private DbService dbService;

    // only retain a weak reference to the activity
    public RetrieveTask(AppDatabase dbVersions, DbService dbService, MainActivity context) {
        this.dbVersions = dbVersions;
        this.context = context;
        this.dbService = dbService;
    }

    @Override
    protected List<VersionEntity> doInBackground(Void... voids) {
            return dbVersions.getVersionDao().getAll();
    }

    @Override
    protected void onPostExecute(List<VersionEntity> versionEntities) {
        if (versionEntities != null && versionEntities.size() > 0) {
            context.initRecycleView((ArrayList<Version>) dbService.getVersionFromVersionEntities(versionEntities));
        } else {
            context.requestAndroidVersions();
        }
    }
}
