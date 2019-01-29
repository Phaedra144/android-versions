package com.dpdgroup.versions.android.androidversions.persistence;

import com.dpdgroup.versions.android.androidversions.persistence.dao.VersionDao;
import com.dpdgroup.versions.android.androidversions.persistence.entity.Version;
import com.dpdgroup.versions.android.androidversions.persistence.service.DbService;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Version.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract VersionDao versionDao();

}