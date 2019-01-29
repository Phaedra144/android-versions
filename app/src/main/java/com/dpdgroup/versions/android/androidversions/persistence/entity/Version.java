package com.dpdgroup.versions.android.androidversions.persistence.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import lombok.Getter;

@Getter
@Entity
public class Version {

    @PrimaryKey
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
}
