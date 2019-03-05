/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package processing;

import java.io.IOException;
import java.text.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import model.Weather;
import model.Quote;


/**
 *
 * @author danielmartins
 * The class processingData is only for processing the information from the REST API
 */
public class processingData {
    public static Weather[] getWeatherData(String response,String local) throws IOException, JSONException, ParseException{
        JSONObject ipma = new JSONObject(response);
        
        String updateAt;
        updateAt = ipma.getString("dataUpdate");
        
        JSONArray d = new JSONArray(ipma.get("data").toString());
        String str = d.toString().substring(1, d.toString().length()-1);

        Weather [] w = new Weather[5];
        for (int i = 0; i < 5; i++) {
            JSONObject ob = new JSONObject(d.getJSONObject(i).toString());
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"); //2018-01-26T09:02:03

            //w[i] = new Weather(local,ob.get("forecastDate").toString(),ob.get("idWeatherType").toString(),ob.get("tMin").toString(),ob.get("tMax").toString(),
            //    ob.get("classWindSpeed").toString(), ob.get("predWindDir").toString(),ob.get("precipitaProb").toString(),ob.get("latitude").toString(),
            //    ob.get("longitude").toString(), format.parse(updateAt));
        }
        
        return w;
    }
    public static String getLocalIDData(String response) throws JSONException {
        JSONObject json = new JSONObject(response);
        JSONArray data = new JSONArray(json.get("data").toString());
        String str = data.toString();
        str = str.substring(1, str.length()-1);
        JSONObject object = new JSONObject(str);

        return object.toString();
    }
    public static String getQuoteData(String response) throws IOException, JSONException {
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
        return "    Quote : "+object.get("quote").toString() + " by " + object.get("author").toString();
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
    public static Map<String,String> getClassWind(String response) throws JSONException
    {
        Map<String,String> wind = new HashMap();
        JSONObject json = new JSONObject(response);
        JSONArray data = new JSONArray(json.get("data").toString());
        for (int i = 0; i < data.length(); i++) {
            JSONObject object = data.getJSONObject(i);
            wind.put( (String) object.get("classWindSpeed"), (String) object.get("descClassWindSpeedDailyPT"));
        }
        return wind; 
    }
    
    ///////////////////////// Lista de identificadores do tempo significativo ////////////////////
    public static Map<Integer,String> getWeatherType(String response) throws JSONException
    {
        Map<Integer,String> type = new HashMap();
        JSONObject json = new JSONObject(response);
        JSONArray data = new JSONArray(json.get("data").toString());
        for (int i = 0; i < data.length(); i++) {
            JSONObject object = data.getJSONObject(i);
            type.put( (int) object.get("idWeatherType"), (String) object.get("descIdWeatherTypePT"));
        }
        return type; 
    }
}
