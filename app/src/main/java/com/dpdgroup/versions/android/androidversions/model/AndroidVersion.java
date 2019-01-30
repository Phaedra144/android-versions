package com.dpdgroup.versions.android.androidversions.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AndroidVersion implements Comparable<AndroidVersion> {

    String imageUrl;
    long releaseDate;
    String versionNumber;
    int rowType;
    String codeName;
    int apiLevel;

    @Override
    public int compareTo(AndroidVersion o) {
        return this.getApiLevel() - o.getApiLevel();
    }

    public String convertToNiceDateFormat(long startDate) {
        String result = String.valueOf(startDate);
        if (startDate != 0) {
            Date date = new Date(startDate * 1000);
            SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
            result = df2.format(date);
        }
        return result;
    }

    @Override
    public String toString() {
        return "AndroidVersion{" +
                "imageUrl='" + imageUrl + '\'' +
                ", releaseDate=" + releaseDate +
                ", versionNumber='" + versionNumber + '\'' +
                ", rowType=" + rowType +
                ", codeName='" + codeName + '\'' +
                ", apiLevel=" + apiLevel +
                '}';
    }
}
