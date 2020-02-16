package org.javaboy.openfeign;

import org.javaboy.commons.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@RestController
public class HelloController {
    @Autowired
    HelloService helloService;

    @GetMapping("/hello")
    public String hello() throws UnsupportedEncodingException {
        String s = helloService.hello2("江南一点雨");
        System.out.println(s);
        User user = new User();
        user.setId(1);
        user.setUsername("javaboy");
        user.setPassword("123");
        User u = helloService.addUser2(user);
        System.out.println(u);
        helloService.deleteUser2(1);
        helloService.getUserByName(URLEncoder.encode("江南一点雨", "UTF-8"));
        return helloService.hello();
    }
}
