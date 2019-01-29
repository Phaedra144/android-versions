package com.dpdgroup.versions.android.androidversions.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AndroidVersion {

    String imageUrl;
    int relaseDate;
    String versionNumber;
    int rowType;
    String codeName;
    int apiLevel;
}
