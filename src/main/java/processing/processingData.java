/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package processing;

import java.io.IOException;
import java.text.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.City;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import model.Weather;
import model.Quote;
import model.WeatherType;
import model.ClassWind;
import org.springframework.stereotype.Component;
import repository.CityRepository;
import repository.ClassWindRepository;
import repository.WeatherRepository;
import repository.WeatherTypeRepository;


/**
 *
 * @author danielmartins
 * The class processingData is only for processing the information from the REST API
 */
@Component
public class processingData {

    private static WeatherRepository weatherRepository;
    private static WeatherTypeRepository weatherTypeRepository;
    private static CityRepository cityRepository;
    private static ClassWindRepository classWindRepository;
    
    public processingData(WeatherRepository weatherRepository, ClassWindRepository classWindRepository, WeatherTypeRepository weatherTypeRepository, CityRepository cityRepository) {
        this.weatherRepository = weatherRepository;
        this.classWindRepository = classWindRepository;
        this.weatherTypeRepository = weatherTypeRepository;
        this.cityRepository = cityRepository;
    }
    
    
    public static ArrayList<Weather> getWeatherData(String response, String local) throws IOException, JSONException, ParseException{
        JSONObject ipma = new JSONObject(response);

        JSONArray d = new JSONArray(ipma.get("data").toString());
        String str = d.toString().substring(1, d.toString().length()-1);

        Long checkLocal = weatherRepository.checkLocal(local);
        ArrayList<Weather> weather = new ArrayList<>();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"); //2018-01-26T09:02:03
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
        try{
            Date createdAt;
            if(checkLocal > 0){ //Data to update the database
                System.out.println("Database has entries about "+local);
                List<Date> l = weatherRepository.createData(local);
                createdAt = l.get(0);
                weatherRepository.deleteLocal(local);     
            }else { //Data to create a new information in database           
                System.out.println("Database don't have entries about "+local);                
                Date now = new Date();
                String n = sdfDate.format(now);
                createdAt = sdfDate.parse(n);
            }
            for (int i = 0; i < d.length(); i++ ) {
                JSONObject ob = new JSONObject(d.getJSONObject(i).toString());
                String updateAt = ipma.getString("dataUpdate");
                String idLocal = cityRepository.getId(local);
                System.out.println("Result (processing Data) : "+idLocal);
                String forecastDate; 
                forecastDate = ob.get("forecastDate").toString() instanceof String ? ob.get("forecastDate").toString() : "";
                String idWeatherType = ob.get("idWeatherType").toString() instanceof String ? ob.get("idWeatherType").toString() : "";
                String descType = weatherTypeRepository.getDescription(idWeatherType);
                String tMin = ob.get("tMin").toString() instanceof String ? ob.get("tMin").toString() : "";
                String tMax = ob.get("tMax").toString() instanceof String ? ob.get("tMax").toString() : "";
                String classWindSpeed = ob.get("classWindSpeed").toString() instanceof String ? ob.get("classWindSpeed").toString() : "";
                String descWind = classWindRepository.getDescription(classWindSpeed);
                String predWindDir = ob.get("predWindDir").toString() instanceof String ? ob.get("predWindDir").toString() : "";
                String precipitaProb = ob.get("precipitaProb").toString() instanceof String ? ob.get("precipitaProb").toString() : "";
                String latitude = ob.get("latitude").toString() instanceof String ? ob.get("latitude").toString() : "";
                String longitude = ob.get("longitude").toString() instanceof String ? ob.get("longitude").toString() : "";
                weather.add( new Weather(new City(idLocal,local),forecastDate,new WeatherType(idWeatherType,descType),tMin,tMax,new ClassWind(classWindSpeed,descWind),predWindDir,precipitaProb,latitude,longitude,createdAt, format.parse(updateAt)) );
            }
        }catch(ParseException | JSONException e){
            System.err.println("Something's wrong processing the data. Error : "+e);
        }

        weather.forEach((we) -> {weatherRepository.saveAndFlush(we);});
        return weather;
    }
    public static String getLocalIDData(String response) throws JSONException {
        JSONObject json = new JSONObject(response);
        JSONArray data = new JSONArray(json.get("data").toString());
        String str = data.toString();
        str = str.substring(1, str.length()-1);
        JSONObject object = new JSONObject(str);

        return object.toString();
    }
    public static Quote getQuoteData(String response) throws IOException, JSONException {
        JSONObject json = new JSONObject(response);
        JSONObject contents = new JSONObject(json.get("contents").toString());
        JSONArray quotes = new JSONArray(contents.get("quotes").toString());
        //JSONObject quote = quotes.getJSONObject(1);
        //JSONObject quotes = new JSONObject(contents.get("quotes").toString());                
        //String quote = json.getString("contents");
        String str = quotes.toString();
        str = str.substring(1, str.length()-1);
        JSONObject object = new JSONObject(str);
        Quote q = new Quote(object.get("quote").toString(), object.get("author").toString());
        return q;
    }
    public static String getTimeData(String response) throws IOException, JSONException {
        JSONObject json = new JSONObject(response);
        String str = json.get("currentDateTime").toString();
        return str;
    }

////////////////////////////////////////////////////// Funções auxiliares na classificação do Weather ////////////////////////////////
    
    ///////////////////////// IDs de cada cidade ///////////////////////////////////////
    public static ArrayList<City> getAllCities(String response) throws JSONException 
    {
        ArrayList<City> cities = new ArrayList<>();
        JSONObject json = new JSONObject(response);
        JSONArray data = new JSONArray(json.get("data").toString());
        for (int i = 0; i < data.length(); i++) {
            JSONObject object = data.getJSONObject(i);
            cities.add(new City(object.get("globalIdLocal").toString(),object.get("local").toString()));
        }
        return cities;    
    }
    
    ///////////////////////// Classificação do vento ////////////////////////////////////////////
    public static ArrayList<ClassWind> getClassWind(String response) throws JSONException
    {
        ArrayList<ClassWind> wind = new ArrayList<>();
        JSONObject json = new JSONObject(response);
        JSONArray data = new JSONArray(json.get("data").toString());
        for (int i = 0; i < data.length(); i++) {
            JSONObject object = data.getJSONObject(i);
            wind.add( new ClassWind( String.valueOf(object.get("classWindSpeed")), (String) object.get("descClassWindSpeedDailyPT")));
        }
        return wind; 
    }
    
    ///////////////////////// Lista de identificadores do tempo significativo ////////////////////
    public static ArrayList<WeatherType> getWeatherType(String response) throws JSONException
    {
        ArrayList<WeatherType> type = new ArrayList(); 
        JSONObject json = new JSONObject(response);
        JSONArray data = new JSONArray(json.get("data").toString());
        for (int i = 0; i < data.length(); i++) {
            JSONObject object = data.getJSONObject(i);
            type.add( new WeatherType( String.valueOf(object.get("idWeatherType")), (String) object.get("descIdWeatherTypePT")));
        }
        return type; 
    }
}
