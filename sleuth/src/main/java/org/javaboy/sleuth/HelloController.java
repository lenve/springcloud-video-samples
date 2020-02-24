package org.javaboy.sleuth;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HelloController {
    private static final Log log = LogFactory.getLog(HelloController.class);
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    HelloService helloService;

    @GetMapping("/hello")
    public String hello() {
        log.info("hello spring cloud sleuth");
        return "hello spring cloud sleuth";
    }

    @GetMapping("/hello2")
    public String hello2() throws InterruptedException {
        log.info("hello2");
        Thread.sleep(500);
        return restTemplate.getForObject("http://localhost:8080/hello3", String.class);
    }

    @GetMapping("/hello3")
    public String hello3() throws InterruptedException {
        log.info("hello3");
        Thread.sleep(500);
        return "hello 3";
    }

    @GetMapping("/hello4")
    public String hello4() {
        log.info("hello4");
        return helloService.backgroundFun();
    }
}
