package com.er.greentest.dagger2;

/**
 * Created by THTF on 2017/9/28.
 * Desc:
 */

public class SecondMap {
    private String lat;
    private String lng;

    public SecondMap(String lat, String lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }
}
