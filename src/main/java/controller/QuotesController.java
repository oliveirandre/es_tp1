/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import processing.getData;
import java.io.IOException;
import model.Quote;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import repository.QuoteRepository;

/**
 *
 * @author danielmartins
 */
@RestController
@RequestMapping("/quotes")
public class QuotesController {
    
    @Autowired
    QuoteRepository quoteRepository;
    
    @RequestMapping("/quotesAuthorTime")
    public String quote() throws IOException, JSONException {
        Quote quote = getData.getQuoteRequest(quoteRepository);
        String time = getData.getTimeRequest();
        quoteRepository.save(new Quote(quote.getQuote(), quote.getAuthor()));
        return quote +" "+time;
    }
}
