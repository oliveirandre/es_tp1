/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package processing;

import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import weather.Weather;

/**
 *
 * @author danielmartins
 * The class processingData is only for processing the information from the REST API
 */
public class processingData {
    public static Weather getWeatherData(String response,String local) throws IOException, JSONException{
            
        JSONObject ipma = new JSONObject(response);
        JSONArray d = new JSONArray(ipma.get("data").toString());
        String str = d.toString().substring(1, d.toString().length()-1);
        JSONObject ob = new JSONObject(str);
        System.out.print("Weather : "+ob.toString());
        return new Weather(local,ob.get("tMax").toString(),ob.get("precipitaProb").toString(),ob.get("latitude").toString(),
                ob.get("idWeatherType").toString(),ob.get("tMin").toString(),ob.get("predWindDir").toString(),
                ob.get("classWindSpeed").toString(),ob.get("longitude").toString(),ob.get("forecastDate").toString());
    }
    public static String getLocalIDData(String response) throws IOException, JSONException {
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
        return "    Quote : "+object.get("quote").toString() + " by " + object.get("author").toString();
    }
    public static String getTimeData(String response) throws IOException, JSONException {
        JSONObject json = new JSONObject(response);
        String str = json.get("currentDateTime").toString();
        return str;
    }
}
