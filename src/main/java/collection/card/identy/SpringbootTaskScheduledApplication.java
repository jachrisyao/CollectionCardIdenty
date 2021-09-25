package collection.card.identy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SpringbootTaskScheduledApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringbootTaskScheduledApplication.class, args);
    }
}
