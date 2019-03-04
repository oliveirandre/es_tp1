/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

/**
 *
 * @author danielmartins
 * idWeatherType: código para tempo significativo
 * descWeatherTypePT: Descrição em Português
 */
@Entity
@Table(name = "weatherType")
public class WeatherType implements Serializable {
   @Id
   private String idWeatherType;
   
   @NotBlank
   private String descWeatherType;
}
