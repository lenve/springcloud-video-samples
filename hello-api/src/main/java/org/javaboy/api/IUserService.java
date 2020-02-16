package org.javaboy.api;

import org.javaboy.commons.User;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

public interface IUserService {
    @GetMapping("/hello")
    String hello();//这里的方法名无所谓，随意取

    @GetMapping("/hello2")
    String hello2(@RequestParam("name") String name);

    @PostMapping("/user2")
    User addUser2(@RequestBody User user);

    @DeleteMapping("/user2/{id}")
    void deleteUser2(@PathVariable("id") Integer id);

    @GetMapping("/user3")
    void getUserByName(@RequestHeader("name") String name) throws UnsupportedEncodingException;
}
