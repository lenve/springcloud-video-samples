package org.javaboy.resilience4j2;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@CircuitBreaker(name = "cbA", fallbackMethod = "error")
public class HelloService {
    @Autowired
    RestTemplate restTemplate;

    public String hello() {
        return restTemplate.getForObject("http://localhost:1113/hello", String.class);
    }

    public String error(Throwable t) {
        return "error";
    }
}
