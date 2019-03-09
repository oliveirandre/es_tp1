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
import java.util.HashMap;
import model.ClassWind;
import model.Quote;
import org.json.JSONException;
import org.json.JSONObject;
import model.Weather;
import model.WeatherType;
import repository.ClassWindRepository;
import repository.WeatherRepository;
import repository.WeatherTypeRepository;
import java.util.Date;
import org.springframework.stereotype.Component;
/**
 *
 * @author danielmartins
 * The class getData is only for get the information from the REST API
 */
@Component
public class getData {
    
    public static ArrayList<Weather> getWeatherRequest(WeatherRepository we, ClassWindRepository c,WeatherTypeRepository wt) throws IOException, JSONException, ParseException {
        
        ArrayList<Weather> w = new ArrayList<Weather>();
        // Use the global ID Local to get the parameters about the weather in that City
        String str = getData.getLocalIDRequest();
        if (str.equals("ERROR")){
            return null;
        }
        
        try{
            JSONObject object = new JSONObject(str);
            String id = object.get("globalIdLocal").toString();
            URL weather = new URL("http://api.ipma.pt/open-data/forecast/meteorology/cities/daily/"+id+".json"); //Aveiro


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
                w = processingData.getWeatherData(response.toString(),object.get("local").toString(),we,c,wt);
            }        
        }catch(JSONException | IOException | ParseException e){
            System.err.println(e);
        }
        return w;
    }
    public static String getLocalIDRequest() throws IOException, JSONException {
        
        // GET the global ID Local from a Portuguese City
        URL url = new URL("http://api.ipma.pt/open-data/distrits-islands.json");
        String readLine = null;
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer response = new StringBuffer();

            while((readLine = in.readLine()) != null) {
                response.append(readLine);
            }
            in.close();
            return processingData.getLocalIDData(response.toString());

        } else { 
            return "ERROR"; 
        } //TODO : access the database
    }
    public static Quote getQuoteRequest() throws IOException, JSONException {
        
        //Get the quote based on a category
        URL url = new URL("http://quotes.rest/qod.json?category=management");
        String readLine = null;
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer response = new StringBuffer();
            while((readLine = in.readLine()) != null) {
                response.append(readLine);
            }
            in.close();
            return processingData.getQuoteData(response.toString());
        }
        else {
            return null;
        }
    }
    public static String getTimeRequest() throws IOException, JSONException {
        URL url = new URL("http://worldclockapi.com/api/json/utc/now");
        String readLine = null;
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader( new InputStreamReader(connection.getInputStream()) );
            StringBuffer response = new StringBuffer();
            while((readLine = in.readLine()) != null) {
                response.append(readLine);
            }
            in.close();
            return processingData.getTimeData(response.toString());
        }
        else {
            return "ERROR";
        }
    }
    
    
////////////////////////////////////////////////////// Funções auxiliares na classificação do Weather ////////////////////////////////
    
    /////////////// Lista de identificadores para as capitais distrito e ilhass //////////////
    public static HashMap<Integer,String> getCitiesRequest() throws IOException, JSONException
    {
        // GET all the cities
        URL url = new URL("http://api.ipma.pt/open-data/distrits-islands.json");
        String readLine = null;
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer response = new StringBuffer();

            while((readLine = in.readLine()) != null) {
                response.append(readLine);
            }
            in.close();
            return (HashMap<Integer, String>) processingData.getAllCities(response.toString());
        }
        else {
            return new HashMap<Integer,String>();
        }
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
