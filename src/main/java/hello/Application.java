package hello;

import java.io.IOException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.json.*;
import weather.Weather;

@SpringBootApplication
@RestController
public class Application {

    	public static void main(String[] args) {
            SpringApplication.run(Application.class, args);
	}

	@RequestMapping("/quote")
	public String quote() throws IOException, JSONException {
            String quote = getData.getQuoteRequest();
            String time = getData.getTimeRequest();
            return quote +" "+time;
	}
	@RequestMapping("/weather")
	public String weather() throws IOException, JSONException {
            Weather w = getData.getWeatherRequest();
            return "    Cidade : "+w.getLocal()+"   Temperatura Máxima : "+w.getTempMax()+"ºC   Temperatura Mínima : "+w.getTempMin()+"ºC";
	}
}
//JPA