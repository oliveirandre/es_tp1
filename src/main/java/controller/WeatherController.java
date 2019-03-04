/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import processing.getData;
import java.io.IOException;
import java.util.List;
import model.Note;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import repository.WeatherRepository;
import weather.Weather;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author danielmartins
 */
@RestController
@RequestMapping("/weather")
public class WeatherController {
   
    @Autowired
    WeatherRepository weatherRepository;
    
    @RequestMapping("/test")
    public String testRESTAPI() throws IOException, JSONException {
        Weather w = getData.getWeatherRequest();
        return "    Cidade : "+w.getLocal()+"   Temperatura Máxima : "+w.getTempMax()+"ºC   Temperatura Mínima : "+w.getTempMin()+"ºC";
    }
    
    // Get All Cities
    @GetMapping("/cities")
    public List<Note> getAllCities() {
        return weatherRepository.getAllCities();
    }
    
}
