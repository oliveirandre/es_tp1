package hello;

import controller.IndexController;
import controller.QuotesController;
import controller.WeatherController;
import model.Quote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import service.QuoteService;

@SpringBootApplication
//@ComponentScan(basePackageClasses = IndexController.class)
//@ComponentScan(basePackageClasses = WeatherController.class)
//@ComponentScan(basePackageClasses = QuotesController.class)
//@EnableJpaAuditing
//@EnableScheduling
//@EnableJpaRepositories("repository")
//@EntityScan("model")
public class Application implements CommandLineRunner {
    
    @Autowired
    private QuoteService service;
    
    public static void main(String[] args) {       
        SpringApplication.run(Application.class, args);
    }   
    
    @Override
    public void run(String... strings) {
        Quote quote = service.createQuote(new Quote("texto", "eu"));
    }
    
}