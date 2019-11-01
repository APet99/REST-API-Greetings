package com.example.demo;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@Component
public class ScheduledTasks {

    RestTemplate restTemplate = new RestTemplate();

//    @Scheduled(fixedRate = 5000)
//    public void periodicTask1() {
//        System.out.println("The time now is " + new Date());
//    }

    @Scheduled(cron = "5  * * * * *")
    public void getGreeting() {
        String url = "http://localhost:8080/greeting";
        Greeting g = restTemplate.getForObject(url, Greeting.class);
        System.out.println(g.getContent());
    }

    //@Scheduled(fixedRate =  7000)
    public void postGreeting(){
        String url = "http://localhost:8080/createGreeting";
        Greeting g = restTemplate.postForObject(url, "Hello World", Greeting.class);
        System.out.println(g.getContent());
    }

    @Scheduled(cron = "0 * * * * *")
    public void updateGreeting(){
        String url = "http://localhost:8080/greeting";
        Greeting g = restTemplate.getForObject(url, Greeting.class);
        String request = "Hello World";
        if (g.getContent().equals(request)){
            request = "Bye World";
        }
        String putUrl = "http://localhost:8080/updateGreeting";
        restTemplate.put(putUrl, request);
        //getGreeting();
    }
}
