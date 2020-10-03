package com.fhc.springbootoauthserver.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fuhongchao
 * @create 2020/5/18 15:09
 */
@RestController
@RefreshScope
@Slf4j
public class DemoController {

    @Value("${app.name:123}")
    private String name;

    @GetMapping("/hello")
    public String hello() {

        return "hello";
    }

    @GetMapping("/order/{id}")
    @PreAuthorize("hasRole('user')")
    public String order(@PathVariable String id) {

        return id;
    }

    @GetMapping("/config")
    public String config() {

        log.info(name);
        return name;
    }
}
