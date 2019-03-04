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
 * classWindSpeed: código para classe vento
 * descClassWindSpeedPT: Descrição em Português
 */
@Entity
@Table(name = "classWind")
public class classWind implements Serializable {
    @Id
    private String classWindSpeed;
    @NotBlank 
    private String descClassWindSpeed;
}
