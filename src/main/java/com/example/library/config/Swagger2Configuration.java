package com.example.library.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class Swagger2Configuration {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.library"))
                .paths(PathSelectors.ant("/**"))
                .build()
                .apiInfo(getApiInfo());
    }

    private ApiInfo getApiInfo (){
        Contact contact =  new Contact("Armanay","https://dl.iitu.kz/pluginfile.php/364686/mod_resource/content/1/Project%20Lombok.pdf","kulbayeva.armanaika@mail.ru");

        return new ApiInfo(
                "Swagger2",
                "Swagger2 Api for Library",
                "1.0",
                "Terms of Service Url",
                contact,
                "License",
                "License Url",
                new ArrayList<>());
    }
}
