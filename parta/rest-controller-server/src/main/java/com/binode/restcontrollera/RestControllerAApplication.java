package com.binode.restcontrollera;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestClient;

import java.util.List;

@SpringBootApplication
public class RestControllerAApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(RestControllerAApplication.class, args);
    }
    @Override
    public void run(String... args) throws Exception {
        RestClient restClient = RestClient.builder()
                .baseUrl("http://localhost:8090")
                .build();

        System.out.println(restClient.get().uri("/books"));
    }


}
