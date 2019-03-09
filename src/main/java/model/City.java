/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author danielmartins
 */
@Entity
@Table(name = "city", schema="ES")
public class City implements Serializable {
    @Id
    @Column(name="id_city")
    private String id_city;

    @Column(name = "city")
    private String City;

    public City() {
    }

    
    public City(String idCity, String City) {
        this.id_city = idCity;
        this.City = City;
    }

    public String getIdCity() {
        return id_city;
    }

    public String getCity() {
        return City;
    }

    public void setIdCity(String idCity) {
        this.id_city = idCity;
    }

    public void setCity(String City) {
        this.City = City;
    }

    @Override
    public String toString() {
        return "City{" + "id = " + id_city + ", City = " + City + '}';
    }
    
}
