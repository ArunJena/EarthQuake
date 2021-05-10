package com.example.earthquake;

public class EarthQuake {
    private double mag;
    private String city;
    private String date;
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public EarthQuake(double mag, String city, String date, String url) {
        this.mag = mag;
        this.city = city;
        this.date = date;
        this.url = url;
    }

    public double getMag() {
        return mag;
    }

    public void setMag(double mag) {
        this.mag = mag;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
