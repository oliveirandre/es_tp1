/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 *
 * @author danielmartins
 */
@Entity
@Table(name = "image", schema="ES")
public class Image implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int imageID;
    
    @NotBlank
    private String pathImage;

    public Image() {
    }

    public Image(int imageID, String pathImage) {
        this.imageID = imageID;
        this.pathImage = pathImage;
    }

    public int getImageID() {
        return imageID;
    }

    public String getPathImage() {
        return pathImage;
    }

    public void setPathImage(String pathImage) {
        this.pathImage = pathImage;
    }

    @Override
    public String toString() {
        return "Image{" + "imageID=" + imageID + ", pathImage=" + pathImage + '}';
    }
    
}
