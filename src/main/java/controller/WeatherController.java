/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import processing.getData;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import model.ClassWind;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import repository.WeatherRepository;
import model.Weather;
import model.WeatherType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import repository.ClassWindRepository;
import repository.WeatherTypeRepository;


/**
 *
 * @author danielmartins
 */
@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    WeatherRepository weatherRepository;
    
    @Autowired
    WeatherTypeRepository weatherTypeRepository;

    @Autowired
    ClassWindRepository classWindRepository;
    
    private static final Logger log = LoggerFactory.getLogger(getData.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    
    @ModelAttribute
    public void getInformations() throws IOException, JSONException{
        getData.getWeatherTypeRequest().forEach((weather) -> {weatherTypeRepository.saveAndFlush(weather);});
        getData.getClassWindRequest().forEach((wind) -> {classWindRepository.saveAndFlush(wind);});
    }
    
    // Return the Weather for the next 5 days
    @RequestMapping("/test")
    public ArrayList<Weather> testRESTAPI() throws IOException, JSONException, ParseException {
        ArrayList<Weather> weather = getData.getWeatherRequest(weatherRepository,classWindRepository,weatherTypeRepository);
        weather.forEach((w) -> {weatherRepository.saveAndFlush(w);});
        return weather;
    }
    
    // Return all the cities that have information about the weather
    @GetMapping("/cities")
    public Collection<String> cities() throws IOException, JSONException {
        return getData.getCitiesRequest().values();
    }
    
    @Scheduled(cron="0 0/5 * * * ?") //Update the database every 5 minutes
    //@Scheduled(fixed=5000) //Update the database every 5000 milliseconds ->> this shouldn't be uncommented, this is just to show to the teacher that works
    public void getWeather() throws IOException, JSONException, ParseException
    {
        System.out.println("Updated database : "+ dateFormat.format(new Date()));
        getData.getWeatherRequest(weatherRepository,classWindRepository,weatherTypeRepository);
    }
    
}
