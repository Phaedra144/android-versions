package com.dpdgroup.versions.android.androidversions.persistence;

import android.content.Context;

import com.dpdgroup.versions.android.androidversions.persistence.dao.VersionDao;
import com.dpdgroup.versions.android.androidversions.persistence.entity.Version;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Version.class}, version = 5, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract VersionDao getVersionDao();

    private static AppDatabase appDb;

    public static AppDatabase getInstance(Context context) {
        if (null == appDb) {
            appDb = buildDatabaseInstance(context);
        }
        return appDb;
    }

    private static AppDatabase buildDatabaseInstance(Context context) {
        return Room.databaseBuilder(context,
                AppDatabase.class,
                "VERSIONS")
                .allowMainThreadQueries().fallbackToDestructiveMigration().build();
    }

    public void cleanUp(){
        appDb = null;
    }
}