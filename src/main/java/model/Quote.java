/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author danielmartins
 */

@Entity
@Table(name = "quote", schema="ES")
public class Quote {
    
    @Id
    @GeneratedValue
    @Column(name = "quote_id")
    private int quote_id;
    
    @Column(name = "quote")
    private String quote;
    
   
   @Column(name="author")
    private String author;
    
    public Quote() {
        
    }
    
    public Quote(String quote, String author) {
        this.quote = quote;
        this.author = author;
    }
    
    public int getQuote_id() {
        return quote_id;
    }
    
    public String getQuote() {
        return this.quote;
    }
    
    public String getAuthor() {
        return this.author;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    
    @Override
    public String toString() {
        return String.format("Quote{id=%d, quote=%s, author=%s}", quote_id, quote, author);
    }

    
}
