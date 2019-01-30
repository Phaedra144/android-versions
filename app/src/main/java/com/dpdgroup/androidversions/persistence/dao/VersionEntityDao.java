package com.dpdgroup.androidversions.persistence.dao;

import com.dpdgroup.androidversions.persistence.entity.VersionEntity;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface VersionEntityDao {

    @Query("SELECT * FROM VersionEntity")
    List<VersionEntity> getAll();

    @Insert
    void insertAll(List<VersionEntity> versionEntities);

    @Query("DELETE FROM VersionEntity")
    public void deleteTable();

}
