package com.dpdgroup.androidversions.persistence.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import lombok.Getter;

@Getter
@Entity
public class VersionEntity implements Comparable<VersionEntity> {

    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "image_url")
    public String imageUrl;
    @ColumnInfo(name = "relase_date")
    public long relaseDate;
    @ColumnInfo(name = "version_number")
    public String versionNumber;
    @ColumnInfo(name = "row_type")
    public int rowType;
    @ColumnInfo(name = "code_name")
    public String codeName;
    @ColumnInfo(name = "api_level")
    public int apiLevel;

    public VersionEntity(String imageUrl, long relaseDate, String versionNumber, int rowType, String codeName, int apiLevel) {
        this.imageUrl = imageUrl;
        this.relaseDate = relaseDate;
        this.versionNumber = versionNumber;
        this.rowType = rowType;
        this.codeName = codeName;
        this.apiLevel = apiLevel;
    }

    @Override
    public int compareTo(VersionEntity o) {
        return this.getApiLevel() - o.getApiLevel();
    }
}
