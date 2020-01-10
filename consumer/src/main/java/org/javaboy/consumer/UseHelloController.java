package org.javaboy.consumer;

import org.javaboy.commons.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
public class UseHelloController {

    @GetMapping("/hello1")
    public String hello1() {
        HttpURLConnection con = null;
        try {
            URL url = new URL("http://localhost:1113/hello");
            con = (HttpURLConnection) url.openConnection();
            if (con.getResponseCode() == 200) {
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String s = br.readLine();
                br.close();
                return s;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "error";
    }

    @Autowired
    @Qualifier("restTemplateOne")
    RestTemplate restTemplateOne;
    @Autowired
    DiscoveryClient discoveryClient;

    @GetMapping("/hello2")
    public String hello2() {
        List<ServiceInstance> list = discoveryClient.getInstances("provider");
        ServiceInstance instance = list.get(0);
        String host = instance.getHost();
        int port = instance.getPort();
        StringBuffer sb = new StringBuffer();
        sb.append("http://")
                .append(host)
                .append(":")
                .append(port)
                .append("/hello");
        String s = restTemplateOne.getForObject(sb.toString(), String.class);
        return s;
    }

    @Autowired
    @Qualifier("restTemplate")
    RestTemplate restTemplate;

    @GetMapping("/hello3")
    public String hello3() {
        return restTemplate.getForObject("http://provider/hello", String.class);
    }

    @GetMapping("/hello4")
    public void hello4() {
        String s1 = restTemplate.getForObject("http://provider/hello2?name={1}", String.class, "javaboy");
        System.out.println(s1);
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://provider/hello2?name={1}", String.class, "javaboy");
        String body = responseEntity.getBody();
        System.out.println("body:" + body);
        HttpStatus statusCode = responseEntity.getStatusCode();
        System.out.println("HttpStatus:" + statusCode);
        int statusCodeValue = responseEntity.getStatusCodeValue();
        System.out.println("statusCodeValue:" + statusCodeValue);
        HttpHeaders headers = responseEntity.getHeaders();
        Set<String> keySet = headers.keySet();
        System.out.println("--------------header-----------");
        for (String s : keySet) {
            System.out.println(s + ":" + headers.get(s));
        }
    }

    @GetMapping("/hello5")
    public void hello5() throws UnsupportedEncodingException {
        String s1 = restTemplate.getForObject("http://provider/hello2?name={1}", String.class, "javaboy");
        System.out.println(s1);
        Map<String, Object> map = new HashMap<>();
        map.put("name", "zhangsan");
        s1 = restTemplate.getForObject("http://provider/hello2?name={name}", String.class, map);
        System.out.println(s1);
        String url = "http://provider/hello2?name=" + URLEncoder.encode("张三", "UTF-8");
        URI uri = URI.create(url);
        s1 = restTemplate.getForObject(uri, String.class);
        System.out.println(s1);
    }

    @GetMapping("/hello6")
    public void hello6() {
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("username", "javaboy");
        map.add("password", "123");
        map.add("id", 99);
        User user = restTemplate.postForObject("http://provider/user1", map, User.class);
        System.out.println(user);

        user.setId(98);
        user = restTemplate.postForObject("http://provider/user2", user, User.class);
        System.out.println(user);
    }

    @GetMapping("/hello7")
    public void hello7() {
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("username", "javaboy");
        map.add("password", "123");
        map.add("id", 99);
        URI uri = restTemplate.postForLocation("http://provider/register", map);
        System.out.println(uri);
        String s = restTemplate.getForObject(uri, String.class);
        System.out.println(s);
    }

    @GetMapping("/hello8")
    public void hello8() {
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("username", "javaboy");
        map.add("password", "123");
        map.add("id", 99);
        restTemplate.put("http://provider/user1", map);
        User user = new User();
        user.setId(98);
        user.setUsername("zhangsan");
        user.setPassword("456");
        restTemplate.put("http://provider/user2", user);
    }

    @GetMapping("/hello9")
    public void hello9() {
        restTemplate.delete("http://provider/user1?id={1}", 99);
        restTemplate.delete("http://provider/user2/{1}", 99);
    }

}
