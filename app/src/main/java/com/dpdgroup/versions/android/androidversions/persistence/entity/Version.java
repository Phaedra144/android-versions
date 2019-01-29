package com.dpdgroup.versions.android.androidversions.persistence.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import lombok.Getter;

@Getter
@Entity
public class Version {

    @PrimaryKey
    int id;
    @ColumnInfo(name = "image_url")
    String imageUrl;
    @ColumnInfo(name = "relase_date")
    int relaseDate;
    @ColumnInfo(name = "version_number")
    String versionNumber;
    @ColumnInfo(name = "row_type")
    int rowType;
    @ColumnInfo(name = "code_name")
    String codeName;
    @ColumnInfo(name = "api_level")
    int apiLevel;
}
