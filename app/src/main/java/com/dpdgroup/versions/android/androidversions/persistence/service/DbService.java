package com.dpdgroup.versions.android.androidversions.persistence.service;

import com.dpdgroup.versions.android.androidversions.model.AndroidVersion;
import com.dpdgroup.versions.android.androidversions.persistence.entity.Version;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class DbService {

    public DbService() {
    }

    public List<AndroidVersion> convertEntitiesToModels(List<Version> versionList) {
        List<AndroidVersion> androidVersionList = new ArrayList<>();
        for (Version version : versionList) {
            AndroidVersion androidVersion = new AndroidVersion();
            androidVersion.setApiLevel(version.getApiLevel());
            androidVersion.setCodeName(version.getCodeName());
            androidVersion.setImageUrl(version.getImageUrl());
            androidVersion.setRelaseDate(version.getRelaseDate());
            androidVersion.setRowType(version.getRowType());
            androidVersion.setVersionNumber(version.getVersionNumber());
            androidVersionList.add(androidVersion);
        }
        return androidVersionList;
    }
}
