/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import model.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author danielmartins
 */

@Repository
public interface QuoteRepository extends JpaRepository<Quote, Long> {

    /**
     *
     * @param number
     * @return Quote, given a number
     */
    @Query("SELECT q FROM Quote q WHERE q.quote_id=(:number)")
    public Quote getQuote(@Param("number") int number);
    
    /**
     *
     * @return the number of entries in table Quote
     */
    @Query("SELECT COUNT(q) FROM Quote q")
    public int size();
}
