package org.spring.springcloud.web;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;

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
    
    @RequestMapping(value="/customerForCache1")
    public String customerForCache1(String name,String pwd) {
    	HystrixRequestContext.initializeContext();
    	Map<String, String> map=new HashMap<String, String>();
    	map.put("name", name);
    	map.put("pwd", pwd);
    	helloService.CacheHello1(map);
    	helloService.CacheHello1(map);
    	return helloService.CacheHello1(map);
    }
    
    @RequestMapping(value="/customerForCache2")
    public String customerForCache2(String name,String pwd) {
    	HystrixRequestContext.initializeContext();
    	helloService.CacheHello2(name,pwd);
    	helloService.CacheHello2(name,pwd);
    	return helloService.CacheHello2(name,pwd);
    }
    
    
    @RequestMapping(value="/helloServiceCommand")
    public String helloServiceCommand(String name,String pwd) throws Exception {
    	Map<String,String> map=new HashMap<String,String>();
    	HelloServiceCommand command=new HelloServiceCommand(map);
    	//同步调用
    	String synMsg=command.execute();
    	System.out.println("synMsg:"+synMsg);        
    	Future<String> asynMsg=command.queue();
    	System.out.println("asynMsg:"+asynMsg.get());
    	return null;
    }
    
}