package com.fhc.nacosprovider.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fuhongchao
 * @create 2020/6/13 10:09
 */
@RestController
public class DemoController {

    @GetMapping("/hello")
    public String greet(){

        return "hello nacos!";
    }
}
