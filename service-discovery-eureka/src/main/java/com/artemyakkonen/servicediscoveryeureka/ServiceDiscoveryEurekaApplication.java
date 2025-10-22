package com.artemyakkonen.servicediscoveryeureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
// http://localhost:8761
public class ServiceDiscoveryEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceDiscoveryEurekaApplication.class, args);
    }

}
