package com.dpdgroup.versions.android.androidversions.persistence.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import lombok.Getter;

@Getter
@Entity
public class Version implements Comparable<Version> {

    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "image_url")
    public String imageUrl;
    @ColumnInfo(name = "relase_date")
    public int relaseDate;
    @ColumnInfo(name = "version_number")
    public String versionNumber;
    @ColumnInfo(name = "row_type")
    public int rowType;
    @ColumnInfo(name = "code_name")
    public String codeName;
    @ColumnInfo(name = "api_level")
    public int apiLevel;

    public Version(String imageUrl, int relaseDate, String versionNumber, int rowType, String codeName, int apiLevel) {
        this.imageUrl = imageUrl;
        this.relaseDate = relaseDate;
        this.versionNumber = versionNumber;
        this.rowType = rowType;
        this.codeName = codeName;
        this.apiLevel = apiLevel;
    }

    @Override
    public int compareTo(Version o) {
        return (this.getRelaseDate() - o.getRelaseDate());
    }
}
