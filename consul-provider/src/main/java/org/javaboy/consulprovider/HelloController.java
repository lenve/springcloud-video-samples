package org.javaboy.consulprovider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Value("${server.port}")
    Integer port;

    @GetMapping("/hello")
    public String hello() {
        return "hello>>" + port;
    }
}
