/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import java.util.List;
import model.Note;
import model.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author danielmartins
 */

@Repository
public interface WeatherRepository extends JpaRepository<Weather, Long> {

    public List<Note> getAllCities();
    
}