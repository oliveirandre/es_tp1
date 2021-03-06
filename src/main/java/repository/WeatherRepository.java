/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import model.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author danielmartins
 */

@Repository
public interface WeatherRepository extends JpaRepository<Weather, Long> {
    /**
     *
     * @param local
     * @return number of entries in database given the local
     */
    @Query("SELECT COUNT(w.globalIDLocal) FROM Weather w WHERE w.globalIDLocal IN (SELECT c FROM City c WHERE c.City = :local)")
    public Long checkLocal(@Param("local") String local);
    
    /**
     * @param local
     */
    @Transactional
    @Modifying
    @Query("DELETE FROM Weather e WHERE e.globalIDLocal IN (SELECT c FROM City c WHERE c.City = :local)")
    public void deleteLocal(@Param("local") String local); 
    
    /**
     *
     * @param local
     * @return Date's list when weather's entries was created
     */
    @Query("SELECT w.createdAt FROM Weather w WHERE w.globalIDLocal IN (SELECT c FROM City c WHERE c.City = :local)")
    public List<Date> createData(@Param("local") String local);
    
    /**
     *
     * @param local
     * @return Weather's list of a place
     */
    @Query("SELECT w FROM Weather w WHERE w.globalIDLocal IN (SELECT c FROM City c WHERE c.City = :local)")
    public List<Weather> getWeatherFromALocal(@Param("local") String local);
    
}