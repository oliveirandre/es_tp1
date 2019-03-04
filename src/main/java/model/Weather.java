/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import javax.validation.constraints.NotBlank;

/**
 *
 * @author danielmartins
 * forecastDate: data da previsão
 * dataUpdate: data de atualização
 * globalIdLocal: identificador do local
 * idWeatherType: código relativo ao tipo de tempo
 * tMin: temperatura mínima diária
 * tMax: temperatura máxima diária
 * classWindSpeed: classe da intensidade do vento
 * predWindDir: rumo predominante do vento (N, NE, E, SE, S, SW, W, NW)
 * probPrecipita: probabilidade da precipitação
 * latitude: latitude
 * longitude: longitude
*/
@Entity
@Table(name = "weather")
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, 
        allowGetters = true)
public class Weather implements Serializable {
    @Id
    private String globalIDLocal;

    @NotBlank
    private String forecastDate;
    
    @NotBlank
    private String idWeatherType;
    
    @NotBlank
    private String tMin;

    @NotBlank
    private String tMax;
    
    @NotBlank
    private String classWindSpeed;

    @NotBlank
    private String classWindDir;
    
    @NotBlank
    private String probPrecipita;

    @NotBlank
    private String latitude;
    
    @NotBlank
    private String longitude;
    
    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

    public String getGlobalIDLocal() {
        return globalIDLocal;
    }

    public String getForecastDate() {
        return forecastDate;
    }

    public String getIdWeatherType() {
        return idWeatherType;
    }

    public String gettMin() {
        return tMin;
    }

    public String gettMax() {
        return tMax;
    }

    public String getClassWindSpeed() {
        return classWindSpeed;
    }

    public String getClassWindDir() {
        return classWindDir;
    }

    public String getProbPrecipita() {
        return probPrecipita;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setGlobalIDLocal(String globalIDLocal) {
        this.globalIDLocal = globalIDLocal;
    }

    public void setForecastDate(String forecastDate) {
        this.forecastDate = forecastDate;
    }

    public void setIdWeatherType(String idWeatherType) {
        this.idWeatherType = idWeatherType;
    }

    public void settMin(String tMin) {
        this.tMin = tMin;
    }

    public void settMax(String tMax) {
        this.tMax = tMax;
    }

    public void setClassWindSpeed(String classWindSpeed) {
        this.classWindSpeed = classWindSpeed;
    }

    public void setClassWindDir(String classWindDir) {
        this.classWindDir = classWindDir;
    }

    public void setProbPrecipita(String probPrecipita) {
        this.probPrecipita = probPrecipita;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    
    
}
