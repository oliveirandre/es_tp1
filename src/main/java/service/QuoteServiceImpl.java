/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import model.Quote;
import repository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 *
 * @author andre
 */

@Service
public class QuoteServiceImpl implements QuoteService {
   
    @Autowired
    private QuoteRepository quoteRepository;
    
    @Override
    public Quote createQuote(Quote quote) {
        return quoteRepository.save(quote);
    }
    
    /*@Override
    public Quote getQuote(Long id) {
        return quoteRepository.findOne(id);
    }*/
    
}
