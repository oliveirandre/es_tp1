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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import model.Weather;
import model.Quote;
import model.WeatherType;
import model.ClassWind;
import org.springframework.stereotype.Component;
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
    
    public static ArrayList<Weather> getWeatherData(String response,String local,WeatherRepository w, ClassWindRepository classWindRepository,WeatherTypeRepository weatherTypeRepository) throws IOException, JSONException, ParseException{
        JSONObject ipma = new JSONObject(response);
        
        String updateAt = "";
        String forecastDate = "";
        String idWeatherType = "";
        String descType = "";
        String tMin = "";
        String tMax = "";
        String classWindSpeed = "";
        String descWind = "";
        String predWindDir = "";
        String precipitaProb = "";
        String latitude = "";
        String longitude = "";
        JSONArray d = new JSONArray(ipma.get("data").toString());
        String str = d.toString().substring(1, d.toString().length()-1);

        List<String> checkLocal = w.checkLocal(local);
        ArrayList<Weather> weather = new ArrayList<>();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"); //2018-01-26T09:02:03
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
        try{
            Date createdAt;
            if(checkLocal != null){ //Data to update the database
                List<Date> l = w.createData(local);
                createdAt = l.get(0);
                w.deleteLocal(local);
            }else { //Data to create a new information in database           
                Date now = new Date();
                String n = sdfDate.format(now);
                createdAt = sdfDate.parse(n);                  
            }
            for (int i = 0; i < d.length(); i++ ) {
                JSONObject ob = new JSONObject(d.getJSONObject(i).toString());
                updateAt = ipma.getString("dataUpdate");
                forecastDate = ob.get("forecastDate").toString() instanceof String ? ob.get("forecastDate").toString() : ""; 
                idWeatherType = ob.get("idWeatherType").toString() instanceof String ? ob.get("idWeatherType").toString() : "";
                descType = weatherTypeRepository.getDescription(idWeatherType);
                tMin = ob.get("tMin").toString() instanceof String ? ob.get("tMin").toString() : "";
                tMax = ob.get("tMax").toString() instanceof String ? ob.get("tMax").toString() : "";
                classWindSpeed = ob.get("classWindSpeed").toString() instanceof String ? ob.get("classWindSpeed").toString() : "";
                descWind = classWindRepository.getDescription(classWindSpeed);
                predWindDir = ob.get("predWindDir").toString() instanceof String ? ob.get("predWindDir").toString() : "";
                precipitaProb = ob.get("precipitaProb").toString() instanceof String ? ob.get("precipitaProb").toString() : "";
                latitude = ob.get("latitude").toString() instanceof String ? ob.get("latitude").toString() : "";
                longitude = ob.get("longitude").toString() instanceof String ? ob.get("longitude").toString() : "";
                weather.add( new Weather(local,forecastDate,new WeatherType(idWeatherType,descType),tMin,tMax,new ClassWind(classWindSpeed,descWind),predWindDir,precipitaProb,latitude,longitude,createdAt, format.parse(updateAt)) );
            }
        }catch(Exception e){
            System.err.println("Something's wrong processing the data. Error : "+e);
        }

        weather.forEach((we) -> {w.saveAndFlush(we);});
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
    public static Map<Integer,String> getAllCities(String response) throws JSONException 
    {
        Map<Integer,String> cities = new HashMap();
        JSONObject json = new JSONObject(response);
        JSONArray data = new JSONArray(json.get("data").toString());
        for (int i = 0; i < data.length(); i++) {
            JSONObject object = data.getJSONObject(i);
            cities.put((int)object.get("globalIdLocal"),object.get("local").toString());
        }
        return cities;    
    }
    
    ///////////////////////// Classificação do vento ////////////////////////////////////////////
    public static ArrayList<ClassWind> getClassWind(String response) throws JSONException
    {
        ArrayList<ClassWind> wind = new ArrayList<>();
        JSONObject json = new JSONObject(response);
        System.err.println("Json : "+json);
        JSONArray data = new JSONArray(json.get("data").toString());
        for (int i = 0; i < data.length(); i++) {
            JSONObject object = data.getJSONObject(i);
            System.out.println("Object Wind : "+object);
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
            System.err.println("Object : "+object);
            type.add( new WeatherType( String.valueOf(object.get("idWeatherType")), (String) object.get("descIdWeatherTypePT")));
        }
        return type; 
    }
}
