package hello;

import controller.IndexController;
import controller.QuotesController;
import controller.WeatherController;
import service.Producer;
import service.Consumer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan(basePackageClasses = IndexController.class)
@ComponentScan(basePackageClasses = WeatherController.class)
@ComponentScan(basePackageClasses = QuotesController.class)
@ComponentScan(basePackageClasses = Producer.class)
@ComponentScan(basePackageClasses = Consumer.class)
@ComponentScan("processing")
@EnableJpaAuditing
@EnableScheduling
@EnableJpaRepositories("repository")
@EntityScan("model")
public class Application {
    
    public static void main(String[] args) {       
        SpringApplication.run(Application.class, args);
    }

    
}