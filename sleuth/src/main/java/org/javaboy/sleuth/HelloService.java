package org.javaboy.sleuth;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class HelloService {
    private static final Log log = LogFactory.getLog(HelloController.class);
    @Async
    public String backgroundFun() {
        log.info("backgroundFun");
        return "backgroundFun";
    }

    @Scheduled(cron = "0/10 * * * * ?")
    public void sche1() {
        log.info("start:");
        backgroundFun();
        log.info("end:");
    }
}
