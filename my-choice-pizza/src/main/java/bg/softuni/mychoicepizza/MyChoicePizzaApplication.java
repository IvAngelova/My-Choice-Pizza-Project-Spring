package bg.softuni.mychoicepizza;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MyChoicePizzaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyChoicePizzaApplication.class, args);
    }

}
