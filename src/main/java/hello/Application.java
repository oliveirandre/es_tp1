package hello;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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
                return quote + "\n" + str;
	}
	@RequestMapping("/time")
	public String time() throws IOException, JSONException {
		return GETTimeRequest();
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
        public static String GETTimeRequest() throws IOException, JSONException {
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
                System.out.println("ResponseCode :"+responseCode);
                JSONObject json = new JSONObject(response.toString());
                System.out.print(json);
                JSONArray data = new JSONArray(json.get("data").toString());
                System.out.print(data);
                String str = data.toString();
                str = str.substring(1, str.length()-1);
                System.out.print(str);
                JSONObject object = new JSONObject(str);
                
                URL url1 = new URL("http://api.ipma.pt/open-data/forecast/meteorology/cities/daily/"+object.get("globalIdLocal").toString()+".json"); //Aveiro
                String readLine1 = null;
                HttpURLConnection connection1 = (HttpURLConnection) url1.openConnection();
                connection1.setRequestMethod("GET");
                int responseCode1 = connection1.getResponseCode();

                if (responseCode1 == HttpURLConnection.HTTP_OK) {
                    BufferedReader in1 = new BufferedReader(
                       new InputStreamReader(connection1.getInputStream()));
                    StringBuffer response1 = new StringBuffer();
                    while((readLine1 = in1.readLine()) != null) {
                        response1.append(readLine1);
                    }
                    in1.close();
                    System.out.println("ResponseCode :"+responseCode);
                    JSONObject json1 = new JSONObject(response1.toString());
                    System.out.print(json1);
                    JSONArray data1 = new JSONArray(json1.get("data").toString());
                    System.out.print(data1);
                    String str1 = data1.toString();
                    str1 = str1.substring(1, str1.length()-1);
                    System.out.print(str1);
                    JSONObject object1 = new JSONObject(str1);
                    return object.get("local").toString() + "   Temperatura Máxima : " +object1.get("tMax").toString()+"ºC\n    Temperatura Minima : "+object1.get("tMin").toString()+"ºC\n";
                }
                else{return "Error.";}
            } else { return "Error."; }
            
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
}
//JPA