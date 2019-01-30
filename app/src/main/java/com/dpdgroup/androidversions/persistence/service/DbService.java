package com.dpdgroup.androidversions.persistence.service;

import com.dpdgroup.androidversions.model.Version;
import com.dpdgroup.androidversions.persistence.entity.VersionEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class DbService {

    public DbService() {
    }

    public List<VersionEntity> getVersionEntitiesFromVersions(List<Version> versions) {
        List<VersionEntity> entities = new ArrayList<>();
        for (Version version : versions) {
            VersionEntity versionEntity = new VersionEntity(version.getImageUrl(), version.getReleaseDate(), version.getVersionNumber(), version.getRowType(), version.getCodeName(), version.getApiLevel());
            entities.add(versionEntity);
        }
        return entities;
    }

    public boolean calledResultEqualsEntities(List<Version> savedVersions, List<VersionEntity> entities) {
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
