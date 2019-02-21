package hello;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
public class Application {

	@RequestMapping("/")
	public String home() {
		return "Boas Dinis!";
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
        
        public static void GETImageRequest() throws IOException {
            URL url = new URL("https://source.unsplash.com/random");
            String readLine = null;
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("userID", readLine);
        }

}
