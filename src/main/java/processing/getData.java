/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package processing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Random;
import model.City;
import model.ClassWind;
import model.Quote;
import org.json.JSONException;
import model.Weather;
import model.WeatherType;
import repository.ClassWindRepository;
import repository.WeatherRepository;
import repository.WeatherTypeRepository;
import org.springframework.stereotype.Component;
import repository.CityRepository;
import repository.QuoteRepository;
/**
 *
 * @author danielmartins
 * The class getData is only for get the information from the REST API
 */
@Component
public class getData {

    private static WeatherRepository weatherRepository;
    private static WeatherTypeRepository weatherTypeRepository;
    private static CityRepository cityRepository;
    private static ClassWindRepository classWindRepository;
    
    public getData(WeatherRepository we, ClassWindRepository cw,WeatherTypeRepository wt,CityRepository ci) {
        this.weatherRepository = we;
        this.classWindRepository = cw;
        this.weatherTypeRepository = wt;
        this.cityRepository = ci;
    }
    
    
    public static ArrayList<Weather> getWeatherRequest(String local, String localId) throws IOException, JSONException, ParseException {
        
        ArrayList<Weather> w = new ArrayList<>();
        processingData pd = new processingData(weatherRepository,classWindRepository,weatherTypeRepository,cityRepository);
        
        try{
            URL weather = new URL("http://api.ipma.pt/open-data/forecast/meteorology/cities/daily/"+localId+".json"); //Aveiro


            HttpURLConnection conn = (HttpURLConnection) weather.openConnection();
            conn.setRequestMethod("GET");
            int code = conn.getResponseCode();
            String readLine = null;

            if (code == HttpURLConnection.HTTP_OK) {
                StringBuilder response;
                try (BufferedReader read = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                    response = new StringBuilder();
                    while((readLine = read.readLine()) != null) {
                        response.append(readLine);
                    }
                }
                w = pd.getWeatherData(response.toString(),local);
                w.forEach((we) -> {weatherRepository.saveAndFlush(we);}); //After get the information from REST API, update the database
            } else { // Get the data from database
                w = (ArrayList<Weather>) weatherRepository.getWeatherFromALocal(local);
            }       
        }catch(JSONException | IOException | ParseException e){
            System.err.println(e);
        }
        
        return w;
    }
    public static Quote getQuoteRequest(QuoteRepository quoteR) throws IOException, JSONException {
        
        //Get the quote based on a category
        int size = quoteR.size();
        Random r = new Random();
        int result = r.nextInt(size);
        Quote q = quoteR.getQuote(result); //If was impossible get a new quote from REST API, their is a quote random from database
        try {
            URL url = new URL("http://quotes.rest/qod.json?category=management");
            String readLine = null;
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                StringBuilder response;
                try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    response = new StringBuilder();
                    while((readLine = in.readLine()) != null) {
                        response.append(readLine);
                    }
                }
                q = processingData.getQuoteData(response.toString());
            }            
        }catch(IOException | JSONException e){
            System.err.println(e);
        }
        return q;
    }
    public static String getTimeRequest() throws IOException, JSONException {
        URL url = new URL("http://worldclockapi.com/api/json/utc/now");
        String readLine = null;
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            StringBuilder response;
            try (BufferedReader in = new BufferedReader( new InputStreamReader(connection.getInputStream()) )) {
                response = new StringBuilder();
                while((readLine = in.readLine()) != null) {
                    response.append(readLine);
                }
            }
            return processingData.getTimeData(response.toString());
        }
        else {
            return "ERROR";
        }
    }
    
    
////////////////////////////////////////////////////// Funções auxiliares na classificação do Weather ////////////////////////////////
    
    /////////////// Lista de identificadores para as capitais distrito e ilhass //////////////
    public static ArrayList<City> getCitiesRequest() throws IOException, JSONException
    {
        ArrayList<City> cities = new ArrayList<>();
        try{
            URL url = new URL("http://api.ipma.pt/open-data/distrits-islands.json");
            String readLine = null;
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                StringBuilder response;
                try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    response = new StringBuilder();
                    while((readLine = in.readLine()) != null) {
                        response.append(readLine);
                    }
                }
                cities = processingData.getAllCities(response.toString());
            }       
        } catch(IOException | JSONException e){
            System.err.println("Something has gone wrong in getting the cities. ERROR : "+e);
        }
        return cities;
    }
    
    ///////////////////////// Lista de classes relativa à intensidade vento ////////////////////
    public static ArrayList<ClassWind> getClassWindRequest() throws IOException, JSONException
    {
        ArrayList<ClassWind> wind = new ArrayList<>();
        try{
            URL url = new URL("http://api.ipma.pt/open-data/wind-speed-daily-classe.json");
            String readLine = null;
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                StringBuilder response;
                try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    response = new StringBuilder();
                    while((readLine = in.readLine()) != null) {
                        response.append(readLine);
                    }
                }
                wind = (ArrayList<ClassWind>) processingData.getClassWind(response.toString());
            }        
        }catch(IOException | JSONException e){
            System.err.println(e);
        }
        return wind;
    }
    
    ///////////////////////// Lista de identificadores do tempo significativo ////////////////////
    public static ArrayList<WeatherType> getWeatherTypeRequest() throws IOException, JSONException
    {
        ArrayList<WeatherType> type = new ArrayList<>();
        try
        {
            URL url = new URL("http://api.ipma.pt/open-data/weather-type-classe.json");
            String readLine = null;
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                StringBuilder response;
                try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    response = new StringBuilder();
                    while((readLine = in.readLine()) != null) {
                        response.append(readLine);
                    }
                }
                type = (ArrayList<WeatherType>) processingData.getWeatherType(response.toString());

            }
        }catch(IOException | JSONException e){
                System.err.println(e);
        }
        return type;
    }
}
