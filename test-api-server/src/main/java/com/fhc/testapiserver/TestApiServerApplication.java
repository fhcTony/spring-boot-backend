package com.fhc.testapiserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author fuhongchao
 */
@EnableDiscoveryClient
@SpringBootApplication
public class TestApiServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestApiServerApplication.class, args);
    }

}
