package sample;

import javax.xml.namespace.QName;
import java.util.Date;

public class Weather {
    private String name;
    private String country;
    private double temp;
    private int humidity;
    private double lon;
    private double lat;
    private Date sunrise;
    private Date sunset;
    private double visibility;

    public Weather(String name, String country, double temp, int humidity, double lon, double lat, Date sunrise, Date sunset, double visibility) {
        this.name = name;
        this.country = country;
        this.temp = temp;
        this.humidity = humidity;
        this.lon = lon;
        this.lat = lat;
        this.sunrise = sunrise;
        this.sunset = sunset;
        this.visibility = visibility;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public Date getSunrise() {
        return sunrise;
    }

    public void setSunrise(Date sunrise) {
        this.sunrise = sunrise;
    }

    public Date getSunset() {
        return sunset;
    }

    public void setSunset(Date sunset) {
        this.sunset = sunset;
    }

    public double getVisibility() {
        return visibility;
    }

    public void setVisibility(double visibility) {
        this.visibility = visibility;
    }
}
