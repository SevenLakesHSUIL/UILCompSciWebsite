package main;

import main.data.Division;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@Controller
public class Application {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/downloads")
    public String downloads() {
        return "downloads";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    Division.DivisionFormatter divisionFormatter() {
        return new Division.DivisionFormatter();
    }
}