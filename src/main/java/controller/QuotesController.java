/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import processing.getData;
import java.io.IOException;
import org.json.JSONException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author danielmartins
 */
@RestController
@RequestMapping("/quotes")
public class QuotesController {
    	@RequestMapping("/quotesAuthorTime")
	public String quote() throws IOException, JSONException {
            String quote = getData.getQuoteRequest();
            String time = getData.getTimeRequest();
            return quote +" "+time;
	}
}
