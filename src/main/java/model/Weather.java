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
import javax.validation.constraints.Min;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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
@Table(name  = "weather", schema="ES")
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, 
        allowGetters = true)
public class Weather implements Serializable {
    @Id
    @Column(name="id")
    @Min(1)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name="id_city",nullable=false, columnDefinition = "text")
    private City globalIDLocal;

    @NotBlank
    @Column(name = "forecastDate")
    @Size(min = 1, max = 255)
    private String forecastDate;
    
    @ManyToOne    
    @JoinColumn(name = "weatherType", nullable = false, columnDefinition = "text")
    private WeatherType idWeatherType;
    
    @NotBlank
    @Column(name = "min")
    @Size(min = 1, max = 255)
    private String tMin;

    @NotBlank
    @Column(name = "max")
    @Size(min = 1, max = 255)
    private String tMax;
    
    @ManyToOne   
    @JoinColumn(name = "wind_speed", nullable = false, columnDefinition = "text")
    private ClassWind classWindSpeed;

    @NotBlank
    @Column(name = "windDir")
    @Size(min = 1, max = 255)
    private String classWindDir;
    
    @NotBlank
    @Column(name = "probPrec")
    @Size(min = 1, max = 255)
    private String probPrecipita;

    @NotBlank
    @Column(name = "latitude")
    @Size(min = 1, max = 255)
    private String latitude;
    
    @NotBlank
    @Column(name = "longitude")
    @Size(min = 1, max = 255)
    private String longitude;
    
    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

    public Weather(){}
    public Weather(City globalIDLocal, String forecastDate, WeatherType idWeatherType, String tMin, String tMax, ClassWind classWindSpeed, String classWindDir, String probPrecipita, String latitude, String longitude, Date updatedAt) {
        this.globalIDLocal = globalIDLocal;
        this.forecastDate = forecastDate;
        this.idWeatherType = idWeatherType;
        this.tMin = tMin;
        this.tMax = tMax;
        this.classWindSpeed = classWindSpeed;
        this.classWindDir = classWindDir;
        this.probPrecipita = probPrecipita;
        this.latitude = latitude;
        this.longitude = longitude;
        this.updatedAt = updatedAt;
    }

    public Weather(City globalIDLocal, String forecastDate, WeatherType idWeatherType, String tMin, String tMax, ClassWind classWindSpeed, String classWindDir, String probPrecipita, String latitude, String longitude, Date createdAt, Date updatedAt) {
        this.globalIDLocal = globalIDLocal;
        this.forecastDate = forecastDate;
        this.idWeatherType = idWeatherType;
        this.tMin = tMin;
        this.tMax = tMax;
        this.classWindSpeed = classWindSpeed;
        this.classWindDir = classWindDir;
        this.probPrecipita = probPrecipita;
        this.latitude = latitude;
        this.longitude = longitude;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }
    
    public City getGlobalIDLocal() {
        return globalIDLocal;
    }

    public String getForecastDate() {
        return forecastDate;
    }

    public WeatherType getIdWeatherType() {
        return idWeatherType;
    }

    public String getTMin() {
        return tMin;
    }

    public String getTMax() {
        return tMax;
    }

    public ClassWind getClassWindSpeed() {
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
    
    public void setGlobalIDLocal(City globalIDLocal) {
        this.globalIDLocal = globalIDLocal;
    }

    public void setForecastDate(String forecastDate) {
        this.forecastDate = forecastDate;
    }

    public void setIdWeatherType(WeatherType idWeatherType) {
        this.idWeatherType = idWeatherType;
    }

    public void setTMin(String tMin) {
        this.tMin = tMin;
    }

    public void setTMax(String tMax) {
        this.tMax = tMax;
    }

    public void setClassWindSpeed(ClassWind classWindSpeed) {
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

    @Override
    public String toString() {
        return "{ \"globalIDLocal\" : " +globalIDLocal.toString() + ", \"forecastDate\" : \"" + forecastDate + "\" , \"idWeatherType\" : " + idWeatherType.toString() + " , \"tMin\" : \"" + tMin + "\" , \"tMax\" : \"" + tMax + "\" , \"classWindSpeed\" : " + classWindSpeed.toString() + " , \"classWindDir\" : \"" + classWindDir + "\" , \"probPrecipita\" : \"" + probPrecipita + "\" , \"latitude\" : \"" + latitude + "\" , \"longitude\" : \"" + longitude + "\" , \"createdAt\" : \" " + createdAt + "\" , \"updatedAt\" : \"" + updatedAt + "\"}";
    }
    
    
    
}
