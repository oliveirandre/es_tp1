/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import model.ClassWind;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author danielmartins
 */

@Repository
public interface ClassWindRepository extends JpaRepository<ClassWind, Long> {
    /**
     *
     * @param weatherType
     * @return description of the class wind
     */
    @Query("SELECT cw.descClassWindSpeed FROM ClassWind cw WHERE cw.classWindSpeed=(:classWind)")
    String getDescription(@Param("classWind") String classWind);    
}
