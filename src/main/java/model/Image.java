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
 */
@Entity
@Table(name = "image")
public class Image implements Serializable {
    @Id
    private String imageID;
    
    @NotBlank
    private String pathImage;
}
