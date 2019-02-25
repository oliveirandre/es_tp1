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

	@RequestMapping("/")
	public String home() throws IOException, JSONException {
		return GETQuoteRequest();
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
        
        public static String GETQuoteRequest() throws IOException, JSONException {
            URL url = new URL("http://quotes.rest/qod.json");
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
                JSONObject quote = quotes.getJSONObject(1);
                //JSONObject quotes = new JSONObject(contents.get("quotes").toString());                
                //String quote = json.getString("contents");
                return quote.getJSONObject("quote").toString();
            }
            else {
                return "Error.";
            }
        }
        
}
