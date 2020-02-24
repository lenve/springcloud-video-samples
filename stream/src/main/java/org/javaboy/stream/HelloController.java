package org.javaboy.stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestController
public class HelloController {
    public final static Logger logger = LoggerFactory.getLogger(HelloController.class);
    @Autowired
    MyChannel myChannel;

    @GetMapping("/hello")
    public void hello() {
        logger.info("send msg:" + new Date());
        myChannel.output().send(MessageBuilder.withPayload("hello spring cloud stream!").setHeader("x-delay", 3000).build());
    }
}
