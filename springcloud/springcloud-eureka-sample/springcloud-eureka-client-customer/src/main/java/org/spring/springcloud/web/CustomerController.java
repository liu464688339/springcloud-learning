package org.spring.springcloud.web;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Customer
 * <p>
 * Created by lh.
 */
@RestController
public class CustomerController {

   
    @Autowired
    private RestTemplate restTemplate; // HTTP 访问操作类
    
    @Autowired
    private HelloService helloService;

    @RequestMapping("/customer")
    public String customer() {
        String providerMsg = restTemplate.getForEntity("http://PROVIDER-SERVICE/provider",
                String.class).getBody();

        return "Hello,Customer! msg from provider : <br/><br/> " + providerMsg;
    }
    
    @RequestMapping(value="/ribbon-consumer-object")
    public String customerForObject() {
    	return helloService.Hello();
    }
}