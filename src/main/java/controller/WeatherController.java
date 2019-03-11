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
import java.util.Date;

import model.City;
import model.Weather;

import org.json.JSONException;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import repository.WeatherRepository;
import repository.CityRepository;
import repository.ClassWindRepository;
import repository.WeatherTypeRepository;

import service.Producer;


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
    
    @Autowired
    CityRepository cityRepository;
    
    @Autowired
    private Producer producer;

    private static final String TOPIC = "Weather_Topic";
    
    private String LOCAL = "Aveiro"; //Default
    private String LOCAL_ID = "1010500"; //Default
    
    @ModelAttribute
    public void getInformations() throws IOException, JSONException{
        getData.getWeatherTypeRequest().forEach((weather) -> {weatherTypeRepository.saveAndFlush(weather);});
        getData.getClassWindRequest().forEach((wind) -> {classWindRepository.saveAndFlush(wind);});
        getData.getCitiesRequest().forEach((city) -> {cityRepository.saveAndFlush(city);});
        
    }
    
    @GetMapping(value = "/publish/{local}")
    public void sendMessageToKafkaTopic(@PathVariable(value = "local") String Local) throws JSONException, IOException, ParseException {
        
        getData g = new getData(weatherRepository,classWindRepository,weatherTypeRepository, cityRepository);
        
        LOCAL = Local;
        LOCAL_ID = cityRepository.getId(LOCAL);
        String result = "";
        if(LOCAL_ID == null){
            result = "No results about "+LOCAL;
        }
        ArrayList<Weather> weather = g.getWeatherRequest(LOCAL, LOCAL_ID);
        result = weather.stream().map((s) -> s.toString()).reduce(result, String::concat);
        
        // The producer send this information to the consumers
        String resultToSend = new JSONObject()
          .put("title", "Daily Weather Forecast up to 5 days from "+LOCAL)
          .put("content", new JSONObject().put("data", result)).toString();
        
        producer.sendMessage(resultToSend);
    }
        @GetMapping(value = "/{local}")
    public String testLocal(@PathVariable(value = "local") String Local) throws JSONException, IOException, ParseException {
        
        getData g = new getData(weatherRepository,classWindRepository,weatherTypeRepository, cityRepository);
        
        LOCAL = Local;
        LOCAL_ID = cityRepository.getId(LOCAL);
        String result = "";
        if(LOCAL_ID == null){
            result = "No results about "+LOCAL;
        }
        ArrayList<Weather> weather = g.getWeatherRequest(LOCAL, LOCAL_ID);
        result = weather.stream().map((s) -> s.toString()).reduce(result, String::concat);
        
        // The producer send this information to the consumers
        String resultToSend = new JSONObject()
          .put("title", "Daily Weather Forecast up to 5 days from "+LOCAL)
          .put("content", new JSONObject().put("data", result)).toString();
        
        return resultToSend;
    }
    
    // Return all the cities that have information about the weather
    @GetMapping("/cities")
    public ArrayList<City> cities() throws IOException, JSONException {
        return getData.getCitiesRequest();
    }
    
    @Scheduled(cron="0 0/5 * * * ?") //Update the database every 5 minutes
    //@Scheduled(fixed=5000) //Update the database every 5000 milliseconds ->> this shouldn't be uncommented, this is just to show to the teacher that works
    public void getWeather() throws IOException, JSONException, ParseException
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        System.out.println("\n Updated database : "+ dateFormat.format(new Date())+"\n");
        getData g = new getData(weatherRepository,classWindRepository,weatherTypeRepository, cityRepository);
        getData.getWeatherRequest(LOCAL,LOCAL_ID);
    }
    
}
