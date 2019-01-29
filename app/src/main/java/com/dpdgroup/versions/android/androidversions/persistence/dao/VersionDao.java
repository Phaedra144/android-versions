package com.dpdgroup.versions.android.androidversions.persistence.dao;

import com.dpdgroup.versions.android.androidversions.persistence.entity.Version;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface VersionDao {

    @Query("SELECT * FROM version")
    List<Version> getAll();

    @Insert
    void insertAll(List<Version> versions);

    @Query("DELETE FROM version")
    public void deleteTable();

}
