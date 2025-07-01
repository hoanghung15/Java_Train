package org.example.ex4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableWebSecurity
@SpringBootApplication
public class Ex4Application {

    public static void main(String[] args) {
        SpringApplication.run(Ex4Application.class, args);
    }

}
