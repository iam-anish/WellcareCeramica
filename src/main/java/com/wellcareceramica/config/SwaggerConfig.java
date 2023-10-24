package com.wellcareceramica.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.Arrays;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2);
        docket.apiInfo(getApiInfo());

        ApiSelectorBuilder select = docket.select();
        select.apis(RequestHandlerSelectors.any());
        select.paths(PathSelectors.any());
        Docket build = select.build();

        return build;
    }

    private ApiInfo getApiInfo() {
        ApiInfo apiInfo = new ApiInfo(
                "Electronic Store Project Backend : APIS",
                "This is backend project created by Anish Mathakiya",
                "1.0.0V",
                "https://www.nldnslvn.com",
                new Contact("Anish Mathakiya","https://www.linkedin.com/in/anish-mathakiya-53063a216/","anumathakiya073@gmail.com"),
                "License of APIS",
                "https://www.nldnslvn.com",
                new ArrayList<>()
        );
        return apiInfo;
    }
}
