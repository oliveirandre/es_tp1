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
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import repository.WeatherRepository;
import model.Weather;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import repository.CityRepository;
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
    
    @Autowired
    CityRepository cityRepository;
    
    private String local = "Aveiro"; //Default
    private String localID = "1010500"; //Default
    
    private static final Logger log = LoggerFactory.getLogger(getData.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    
    @ModelAttribute
    public void getInformations() throws IOException, JSONException{
        getData.getWeatherTypeRequest().forEach((weather) -> {weatherTypeRepository.saveAndFlush(weather);});
        getData.getClassWindRequest().forEach((wind) -> {classWindRepository.saveAndFlush(wind);});
        getData.getCitiesRequest().forEach((city) -> {cityRepository.saveAndFlush(city);});
        
    }
    
    // Return the Weather for the next 5 days
    @RequestMapping("/{local}")
    public String testRESTAPI(@PathVariable(value = "local") String Local) throws IOException, JSONException, ParseException {
        getData g = new getData(weatherRepository,classWindRepository,weatherTypeRepository, cityRepository);
        local = Local;
        localID = cityRepository.getId(local);
        if(localID == null){
            return "No results about "+local;
        }
        //keywords = "nature,water" for example
        //Go to search a random Image "https://source.unsplash.com/1600x900/?"+keywords
        
        ArrayList<Weather> weather = g.getWeatherRequest(local, localID);
        weather.forEach((w) -> {weatherRepository.saveAndFlush(w);});
        String result = "";
        result = weather.stream().map((s) -> s.toString()+"\n").reduce(result, String::concat);
        return result;
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
        System.out.println("\n Updated database : "+ dateFormat.format(new Date())+"\n");
        getData g = new getData(weatherRepository,classWindRepository,weatherTypeRepository, cityRepository);
        getData.getWeatherRequest(local,localID);
    }
    
}
