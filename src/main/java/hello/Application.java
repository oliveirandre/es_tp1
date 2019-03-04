package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
<<<<<<< HEAD
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.bind.annotation.RestController;
import controller.IndexController;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@RestController
@EnableJpaAuditing
=======
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaAuditing
@EnableJpaRepositories("repository")
@EntityScan("model")
>>>>>>> 0b5dc69e61213fa71ec4b7864a21a89d58e32e03
public class Application {

    	public static void main(String[] args) {
            SpringApplication.run(Application.class, args);
	}

}