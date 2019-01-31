package com.dpdgroup.androidversions.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Version implements Comparable<Version> {

    String imageUrl;
    long releaseDate;
    String versionNumber;
    int rowType;
    String codeName;
    int apiLevel;
    boolean isSelected;

    public Version(String imageUrl, long releaseDate, String versionNumber, int rowType, String codeName, int apiLevel) {
        this.imageUrl = imageUrl;
        this.releaseDate = releaseDate;
        this.versionNumber = versionNumber;
        this.rowType = rowType;
        this.codeName = codeName;
        this.apiLevel = apiLevel;
        this.isSelected = false;
    }

    @Override
    public int compareTo(Version o) {
        return this.getApiLevel() - o.getApiLevel();
    }

    public String convertToNiceDateFormat(long startDate) {
        String result = String.valueOf(startDate);
        if (startDate != 0) {
            Date date = new Date(startDate * 1000);
            SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            result = df2.format(date);
        }
        return result;
    }

    @Override
    public String toString() {
        return "Version{" +
                "imageUrl='" + imageUrl + '\'' +
                ", releaseDate=" + releaseDate +
                ", versionNumber='" + versionNumber + '\'' +
                ", rowType=" + rowType +
                ", codeName='" + codeName + '\'' +
                ", apiLevel=" + apiLevel +
                '}';
    }
}
