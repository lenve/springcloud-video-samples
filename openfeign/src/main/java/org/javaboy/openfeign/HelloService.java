package org.javaboy.openfeign;

import org.javaboy.commons.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient("provider")
public interface HelloService {

    @GetMapping("/hello")
    String hello();//这里的方法名无所谓，随意取

    @GetMapping("/hello2")
    String hello2(@RequestParam("name") String name);

    @PostMapping("/user2")
    User addUser(@RequestBody User user);

    @DeleteMapping("/user2/{id}")
    void deleteUserById(@PathVariable("id") Integer id);

    @GetMapping("/user3")
    void getUserByName(@RequestHeader("name") String name);
}
