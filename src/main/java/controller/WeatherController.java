/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import processing.getData;
import java.io.IOException;
import java.text.ParseException;
import java.util.Collection;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import repository.WeatherRepository;
import model.Weather;
import org.springframework.web.bind.annotation.GetMapping;


/**
 *
 * @author danielmartins
 */
@RestController
@RequestMapping("/weather")
public class WeatherController {

    // Return the Weather for the next 5 days
    @RequestMapping("/test")
    public Weather[] testRESTAPI() throws IOException, JSONException, ParseException {
        Weather[] weather = getData.getWeatherRequest();
        return weather;
    }
    
    // Return all the cities that have information about the weather
    @GetMapping("/cities")
    public Collection<String> cities() throws IOException, JSONException {
        return getData.getCitiesRequest().values();
    }
    
    
}
