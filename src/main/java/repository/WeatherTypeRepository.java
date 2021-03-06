/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import model.WeatherType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author danielmartins
 */

@Repository
public interface WeatherTypeRepository extends JpaRepository<WeatherType, Long> {

    /**
     *
     * @param weatherType
     * @return description of the weather type
     */
    @Query("SELECT wt.descWeatherType FROM WeatherType wt WHERE wt.idWeatherType=(:weatherType)")
    String getDescription(@Param("weatherType") String weatherType);
}
