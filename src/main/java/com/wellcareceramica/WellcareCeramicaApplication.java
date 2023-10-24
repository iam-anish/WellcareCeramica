package com.wellcareceramica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication()
@EnableWebMvc
public class WellcareCeramicaApplication {

    public static void main(String[] args) {
        SpringApplication.run(WellcareCeramicaApplication.class, args);
    }

}
