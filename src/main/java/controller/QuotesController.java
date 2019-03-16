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
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    
    private String QUOTE = "Failures are only failures when we don’t learn from them, because when we learn from them they become lessons."; //Default
    private String AUTHOR = "Jay Shetty"; //Default
    
    @ModelAttribute
    public void getInformations() throws IOException, JSONException{
        quoteRepository.save(new Quote(QUOTE, AUTHOR));
    }    
    
    @CrossOrigin(origins = "http://localhost:9000")
    @RequestMapping("/quotesAuthorTime")
    public String quote() throws IOException, JSONException {
        getData g = new getData(quoteRepository);
        Quote quote = g.getQuoteRequest();
        String time = g.getTimeRequest();
        quoteRepository.save(new Quote(quote.getQuote(), quote.getAuthor()));
        JSONObject json = new JSONObject();
        json.put("quote", quote.getQuote());
        json.put("author", quote.getAuthor());
        return json.toString();
    }
}
