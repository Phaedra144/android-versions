package com.dpdgroup.versions.android.androidversions.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.dpdgroup.versions.android.androidversions.model.AndroidVersion;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class DBService {

    SQLiteDatabase database;

    public void createDatabase(Context context, List<AndroidVersion> androidVersions) {
        database = context.openOrCreateDatabase("Versions", MODE_PRIVATE, null);
        database.execSQL("CREATE TABLE IF NOT EXISTS newVersions (imageUrl VARCHAR, relaseDate INTEGER, versionNumber VARCHAR, rowType INTEGER, codeName VARCHAR, apiLevel INTEGER, id INTEGER PRIMARY KEY)");
        database.insert("newVersions", null, insertIntoDB(androidVersions));
    }

    private ContentValues insertIntoDB(List<AndroidVersion> androidVersionList) {
        ContentValues values = new ContentValues();
        for (AndroidVersion androidVersion : androidVersionList) {
            values.put("imageUrl", androidVersion.getImageUrl());
            values.put("relaseDate", androidVersion.getRelaseDate());
            values.put("versionNumber", androidVersion.getVersionNumber());
            values.put("rowType", androidVersion.getRowType());
            values.put("codeName", androidVersion.getCodeName());
            values.put("apiLevel", androidVersion.getApiLevel());
        }
        return values;
    }

    public List<AndroidVersion> listAll() {
        return null;
    }
}
