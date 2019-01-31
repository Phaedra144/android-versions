package com.dpdgroup.androidversions.persistence;

import android.content.Context;

import com.dpdgroup.androidversions.persistence.dao.VersionEntityDao;
import com.dpdgroup.androidversions.persistence.entity.VersionEntity;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {VersionEntity.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract VersionEntityDao getVersionDao();

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
                "VERSIONS").build();
    }

    public void cleanUp(){
        appDb = null;
    }
}