/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weather;

/**
 *
 * @author danielmartins
 */
public class Weather {

    private String local;
    private String tempMax;
    private String probabilidadePrec;
    private String latitude;
    private String idWeatherType;
    private String tempMin;
    private String predWindDir;
    private String ClassWindSpeed;
    private String longitude;
    private String forecastDate;

    public Weather(String local, String tempMax, String probabilidadePrec, String latitude, String idWeatherType, String tempMin, String predWindDir, String ClassWindSpeed, String longitude, String forecastDate) {
        this.local = local;
        this.tempMax = tempMax;
        this.probabilidadePrec = probabilidadePrec;
        this.latitude = latitude;
        this.idWeatherType = idWeatherType;
        this.tempMin = tempMin;
        this.predWindDir = predWindDir;
        this.ClassWindSpeed = ClassWindSpeed;
        this.longitude = longitude;
        this.forecastDate = forecastDate;
    }
    public String getLocal() {
        return local;
    }
    
    public String getTempMax() {
        return tempMax;
    }

    public String getProbabilidadePrec() {
        return probabilidadePrec;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getIdWeatherType() {
        return idWeatherType;
    }

    public String getTempMin() {
        return tempMin;
    }

    public String getPredWindDir() {
        return predWindDir;
    }

    public String getClassWindSpeed() {
        return ClassWindSpeed;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getForecastDate() {
        return forecastDate;
    }

    public void setLocal(String local) {
        this.local = local;
    }
    
    public void setTempMax(String tempMax) {
        this.tempMax = tempMax;
    }

    public void setProbabilidadePrec(String probabilidadePrec) {
        this.probabilidadePrec = probabilidadePrec;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setIdWeatherType(String idWeatherType) {
        this.idWeatherType = idWeatherType;
    }

    public void setTempMin(String tempMin) {
        this.tempMin = tempMin;
    }

    public void setPredWindDir(String predWindDir) {
        this.predWindDir = predWindDir;
    }

    public void setClassWindSpeed(String ClassWindSpeed) {
        this.ClassWindSpeed = ClassWindSpeed;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setForecastDate(String forecastDate) {
        this.forecastDate = forecastDate;
    }
    
    
}
