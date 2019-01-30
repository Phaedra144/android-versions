package com.dpdgroup.androidversions.persistence.service;

import com.dpdgroup.androidversions.model.AndroidVersion;
import com.dpdgroup.androidversions.persistence.entity.Version;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class DbService {

    public DbService() {
    }

    public List<Version> getEntitiesFromAndroidVersions(List<AndroidVersion> androidVersions) {
        List<Version> entities = new ArrayList<>();
        for (AndroidVersion androidVersion : androidVersions) {
            Version version = new Version(androidVersion.getImageUrl(), androidVersion.getReleaseDate(), androidVersion.getVersionNumber(), androidVersion.getRowType(), androidVersion.getCodeName(), androidVersion.getApiLevel());
            entities.add(version);
        }
        return entities;
    }

    public boolean calledResultEqualsEntities(List<AndroidVersion> savedVersions, List<Version> entities) {
        boolean result = true;
        if (savedVersions.size() != entities.size()) {
            return false;
        }
        Collections.sort(savedVersions);
        Collections.sort(entities);
        for (int i = 0; i < savedVersions.size(); i++) {
            if (!savedVersions.get(i).getCodeName().equals(entities.get(i).getCodeName())) {
                result = false;
                break;
            }
        }
        return result;
    }
}
