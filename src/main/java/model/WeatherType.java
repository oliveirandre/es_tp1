/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author danielmartins
 * idWeatherType: código para tempo significativo
 * descWeatherTypePT: Descrição em Português
 */
@Entity
@Table(name = "weather_type", schema="ES")
public class WeatherType implements Serializable {
    @Id
    @Column(name = "weatherType")
    private String idWeatherType;

    @Column(name = "[description]")
    private String descWeatherType;

    
    public WeatherType(String idWeatherType, String descWeatherType) {
        this.idWeatherType = idWeatherType;
        this.descWeatherType = descWeatherType;
    }

    public WeatherType() {
    }

    public String getIdWeatherType() {
        return idWeatherType;
    }

    public String getDescWeatherType() {
        return descWeatherType;
    }

    public void setIdWeatherType(String idWeatherType) {
        this.idWeatherType = idWeatherType;
    }

    public void setDescWeatherType(String descWeatherType) {
        this.descWeatherType = descWeatherType;
    }

    @Override
    public String toString() {
        return "{ \"idWeatherType\" : \"" + idWeatherType + "\" , \"descWeatherType\" : \"" + descWeatherType + "\" }";
    }
   
   
}
