package com.dasbikash.news_server_data_coordinator_rest_jersey;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
@PropertySources(
        {
                @PropertySource("classpath:application.properties"),
                @PropertySource("classpath:jersey_rest.properties")
        }
    )
public class NewsServerDataCoordinatorRestJerseyApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext =
                SpringApplication.run(NewsServerDataCoordinatorRestJerseyApplication.class, args);
    }
}
