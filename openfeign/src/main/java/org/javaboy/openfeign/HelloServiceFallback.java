package org.javaboy.openfeign;

import org.javaboy.commons.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;

@Component
@RequestMapping("/javaboy")//防止请求地址重复
public class HelloServiceFallback implements HelloService {
    @Override
    public String hello() {
        return "error";
    }

    @Override
    public String hello2(String name) {
        return "error2";
    }

    @Override
    public User addUser2(User user) {
        return null;
    }

    @Override
    public void deleteUser2(Integer id) {

    }

    @Override
    public void getUserByName(String name) throws UnsupportedEncodingException {

    }
}
