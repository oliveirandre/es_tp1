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
 * classWindSpeed: código para classe vento
 * descClassWindSpeedPT: Descrição em Português
 */
@Entity
@Table(name = "class_wind", schema="ES")
public class ClassWind implements Serializable {
    @Id
    @Column(name = "wind_speed")
    private String classWindSpeed;
    
    @Column(name = "[description]")  
    private String descClassWindSpeed;

    public ClassWind(String classWindSpeed, String descClassWindSpeed) {
        this.classWindSpeed = classWindSpeed;
        this.descClassWindSpeed = descClassWindSpeed;
    }

    public ClassWind() {
    }
    

    public String getClassWindSpeed() {
        return classWindSpeed;
    }

    public String getDescClassWindSpeed() {
        return descClassWindSpeed;
    }

    public void setClassWindSpeed(String classWindSpeed) {
        this.classWindSpeed = classWindSpeed;
    }

    public void setDescClassWindSpeed(String descClassWindSpeed) {
        this.descClassWindSpeed = descClassWindSpeed;
    }

    @Override
    public String toString() {
        return "ClassWind{" + "classWindSpeed=" + classWindSpeed + ", descClassWindSpeed=" + descClassWindSpeed + '}';
    }
    
    
}
