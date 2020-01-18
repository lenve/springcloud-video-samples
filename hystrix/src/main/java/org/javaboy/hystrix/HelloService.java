package org.javaboy.hystrix;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Future;

@Service
public class HelloService {
    @Autowired
    RestTemplate restTemplate;

    /**
     * 在这个方法中，我们将发起一个远程调用，去调用 provider 中提供的 /hello 接口
     * <p>
     * 但是，这个调用可能会失败。
     * <p>
     * 我们在这个方法上添加 @HystrixCommand 注解，配置 fallbackMethod 属性，这个属性表示该方法调用失败时的临时替代方法
     *
     * @return
     */
    @HystrixCommand(fallbackMethod = "error")
    public String hello() {
        return restTemplate.getForObject("http://provider/hello", String.class);
    }

    @HystrixCommand(fallbackMethod = "error")
    public Future<String> hello2() {
        return new AsyncResult<String>() {
            @Override
            public String invoke() {
                return restTemplate.getForObject("http://provider/hello", String.class);
            }
        };
    }

    /**
     * 注意，这个方法名字要和 fallbackMethod 一致
     * 方法返回值也要和对应的方法一致
     *
     * @return
     */
    public String error() {
        return "error";
    }
}
