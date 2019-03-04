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
import weather.Weather;

/**
 *
 * @author danielmartins
 */
@RestController
@RequestMapping("/weather")
public class WeatherController {
    	
    @RequestMapping("/MaxMin")
	public String weather() throws IOException, JSONException {
            Weather w = getData.getWeatherRequest();
            return "    Cidade : "+w.getLocal()+"   Temperatura Máxima : "+w.getTempMax()+"ºC   Temperatura Mínima : "+w.getTempMin()+"ºC";
	}
}
