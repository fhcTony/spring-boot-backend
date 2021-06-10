package com.fhc.springbootoauthserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.fhc.springbootoauthserver.mapper")
public class SpringBootOauthServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootOauthServerApplication.class, args);
    }

}
