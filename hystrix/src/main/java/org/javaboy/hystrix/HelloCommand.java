package org.javaboy.hystrix;

import com.netflix.hystrix.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

public class HelloCommand extends HystrixCommand<String> {

    RestTemplate restTemplate;
    String name;

    public HelloCommand(Setter setter, RestTemplate restTemplate,String name) {
        super(setter);
        this.name = name;
        this.restTemplate = restTemplate;
    }

    @Override
    protected String run() throws Exception {
        return restTemplate.getForObject("http://provider/hello2?name={1}", String.class, name);
    }

    @Override
    protected String getCacheKey() {
        return name;
    }

    /**
     * 这个方法就是请求失败的回调
     *
     * @return
     */
    @Override
    protected String getFallback() {
        return "error-extends:"+getExecutionException().getMessage();
    }
}
