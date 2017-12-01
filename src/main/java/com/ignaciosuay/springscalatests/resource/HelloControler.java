package com.ignaciosuay.springscalatests.resource;

import com.ignaciosuay.springscalatests.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloControler {

    @Autowired
    HelloService helloService;

    @GetMapping("/hello/{name}")
    public String sayHello(@PathVariable String name){
        return helloService.sayHi(name);
    }
}
