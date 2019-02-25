package hello;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.json.*;

@SpringBootApplication
@RestController
public class Application {

	@RequestMapping("/quote")
	public String home() throws IOException, JSONException {
            String quote = GETQuoteRequest();
            JSONObject time = GETTimeRequest();
            //return time.toString();
            String str = time.get("currentDateTime").toString();
            return quote + "\n  " + str;
	}
	@RequestMapping("/weather")
	public String time() throws IOException, JSONException {
		return getWeatherRequest();
	}
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
        
        public static String GETQuoteRequest() throws IOException, JSONException {
            URL url = new URL("http://quotes.rest/qod.json?category=management");
            String readLine = null;
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(
                   new InputStreamReader(connection.getInputStream()));
                StringBuffer response = new StringBuffer();
                while((readLine = in.readLine()) != null) {
                    response.append(readLine);
                }
                in.close();
                JSONObject json = new JSONObject(response.toString());
                JSONObject contents = new JSONObject(json.get("contents").toString());
                JSONArray quotes = new JSONArray(contents.get("quotes").toString());
                //JSONObject quote = quotes.getJSONObject(1);
                //JSONObject quotes = new JSONObject(contents.get("quotes").toString());                
                //String quote = json.getString("contents");
                String str = quotes.toString();
                str = str.substring(1, str.length()-1);
                JSONObject object = new JSONObject(str);
                System.out.println(response.toString());
                return object.get("quote").toString() + " by " + object.get("author").toString();
            }
            else {
                return "Error.";
            }
        }
        public static JSONObject GETTimeRequest() throws IOException, JSONException {
            URL url = new URL("http://worldclockapi.com/api/json/utc/now");
            String readLine = null;
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(
                   new InputStreamReader(connection.getInputStream()));
                StringBuffer response = new StringBuffer();
                while((readLine = in.readLine()) != null) {
                    response.append(readLine);
                }
                in.close();
                System.out.print(response.toString());
                return new JSONObject(response.toString());
            }
            else {
                return null;
            }
        }
        public static String getWeatherRequest() throws IOException, JSONException {
            // GET the global ID Local from a Portuguese City
            URL url = new URL("http://api.ipma.pt/open-data/distrits-islands.json");
            String readLine = null;
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(
                   new InputStreamReader(connection.getInputStream()));
                StringBuffer response = new StringBuffer();
                while((readLine = in.readLine()) != null) {
                    response.append(readLine);
                }
                in.close();
                JSONObject json = new JSONObject(response.toString());
                JSONArray data = new JSONArray(json.get("data").toString());
                String str = data.toString();
                str = str.substring(1, str.length()-1);
                JSONObject object = new JSONObject(str);
                
                // Use the global ID Local to get the parameters about the weather in that City
                URL time = new URL("http://api.ipma.pt/open-data/forecast/meteorology/cities/daily/"+object.get("globalIdLocal").toString()+".json"); //Aveiro
                String line = null;
                HttpURLConnection conn = (HttpURLConnection) time.openConnection();
                conn.setRequestMethod("GET");
                int code = conn.getResponseCode();

                if (code == HttpURLConnection.HTTP_OK) {
                    BufferedReader read = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuffer resp = new StringBuffer();
                    while((line = read.readLine()) != null) {
                        resp.append(line);
                    }
                    read.close();
                    JSONObject ipma = new JSONObject(resp.toString());
                    JSONArray d = new JSONArray(ipma.get("data").toString());
                    String st = d.toString().substring(1, d.toString().length()-1);
                    JSONObject ob = new JSONObject(st);
                    return object.get("local").toString() + "   Temperatura Máxima : " +ob.get("tMax").toString()+"ºC\n    Temperatura Minima : "+ob.get("tMin").toString()+"ºC\n";
                }
                else{return "Error.";}
            } else { return "Error."; }
            
        }
        
}
//JPA