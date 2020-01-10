package org.javaboy.provider;

import org.javaboy.commons.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Value("${server.port}")
    Integer port;
    @GetMapping("/hello")
    public String hello() {
        return "hello javaboy:" + port;
    }

    @GetMapping("/hello2")
    public String hello2(String name) {
        return "hello " + name;
    }

    @PostMapping("/user1")
    public User addUser1(User user) {
        return user;
    }
    @PostMapping("/user2")
    public User addUser2(@RequestBody User user) {
        return user;
    }

}
