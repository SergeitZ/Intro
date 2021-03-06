package com.careerdevs.Intro;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    private final AtomicLong counter = new AtomicLong();
    private final static String template = "Hello %s";

    // root route || home route
    @GetMapping("/")
    public String getHello (){
        return "Hello World";
    }

    // dummy data route | introduction to endpoint -> sayHello endpoint
    @GetMapping("/sayHello")
    public Greeting sayHello() {
        return new Greeting(42, "Hello CareerDevs!");
    }

    // query parameters. localhost:8080/greeting?{value}={data}
    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    // dynamic routing | url parameters
    @GetMapping("/hello/{name}")
    public Greeting hello(@PathVariable String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }
}
