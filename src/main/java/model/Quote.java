/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author andre
 */

@Entity
public class Quote {
    
    @Id
    @GeneratedValue
    private Long id;
    private String quote;
    private String author;
    
    public Quote() {
        
    }
    
    public Quote(String quote, String author) {
        this.quote = quote;
        this.author = author;
    }
    
    @Override
    public String toString() {
        return String.format("Quote{id=%d, quote=%s, author=%s}", id, quote, author);
    }
    
    public String getQuote() {
        return this.quote;
    }
    
    public String getAuthor() {
        return this.author;
    }
}
