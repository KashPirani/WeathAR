package com.google.ar.core.examples.unity.helloar;

import java.io.Serializable;

public class CurrentWeatherObj implements Serializable {
    double _tempMax;
    double _tempMin;
    String _genDescription;
    int _weatherId;
    int _humidity;
    int _clouds;
    double _pressure;
    double _windSpeed;
    double _rain;
    double _snow;
    String _dateTime;
    String _precipitation;
//    public CurrentWeatherObj(double tempMax, double tempMin, String generalDescription,
//                             String precipitation, int humidity, int clouds, double pressure,
//                             double windSpeed, double rain, double snow, String dateTime) {
//
//        this._tempMax = tempMax;
//        this._tempMin = tempMin;
//        this._genDescription = generalDescription;
//        this._precipitation = precipitation;
//        this._humidity = humidity;
//        this._clouds = clouds;
//        this._pressure = pressure;
//        this._windSpeed = windSpeed;
//        this._rain = rain;
//        this._snow = snow;
//        this._dateTime = dateTime;
//
//    }

    public CurrentWeatherObj() {
    }

    public String getPrecipitation() { return _precipitation;}

    public double getRain() {
        return _rain;
    }

    public double getSnow() {
        return _snow;
    }

    public double getTempMax() {
        return _tempMax;
    }

    public int getWeatherId() {
        return _weatherId;
    }

    public double getTempMin() {
        return _tempMin;
    }

    public String getGenDescription() {
        return _genDescription;
    }

    public int getHumidity() {
        return _humidity;
    }

    public int getClouds() {
        return _clouds;
    }

    public double getPressure() {
        return _pressure;
    }

    public double getWindSpeed() {
        return _windSpeed;
    }

    public String getDateTime() {
        return _dateTime;
    }

    public void setTempMax(double tempMax) {
        this._tempMax = tempMax;
    }

    public void setTempMin(double tempMin) {
        this. _tempMin = tempMin;
    }

    public void setGenDescription(String genDescription) {
        this._genDescription = genDescription;
    }

    public void setHumidity(int humidity) {
        this._humidity = humidity;
    }

    public void setClouds(int clouds) {
        this._clouds = clouds;
    }

    public void setPressure(double pressure) {
        this._pressure = pressure;
    }

    public void setWindSpeed(double windSpeed) {
        this._windSpeed = windSpeed;
    }

    public void setDateTime(String dateTime) {
        this._dateTime = dateTime;
    }
    public void setRain(double rain) {
        this._rain = rain;
    }

    public void setSnow(double snow) {
        this._snow = snow;
    }

    public void setWeatherId(int weatherId) {
        this._weatherId = weatherId;
    }

    public void setPrecipitation(String precipitation){this._precipitation = precipitation;}

}
