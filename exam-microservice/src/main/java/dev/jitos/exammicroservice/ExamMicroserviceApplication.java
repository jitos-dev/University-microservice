package dev.jitos.exammicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ExamMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExamMicroserviceApplication.class, args);
    }

}
