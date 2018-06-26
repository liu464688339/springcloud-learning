package org.spring.springcloud.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Provider HelloWorld 案例
 * <p>
 * Created by lh
 */
@RestController
public class ProviderController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProviderController.class);

    @Autowired
    private Registration registration;       // 服务注册

    @Autowired
    private DiscoveryClient discoveryClient; // 服务发现客户端

    @RequestMapping("/provider")
    public String provider() {
        ServiceInstance instance = serviceInstance();
        LOGGER.info("provider service, host = " + instance.getHost()
                + ", service_id = " + instance.getServiceId());
        return "Hello,Provider!";
    }
    
    @RequestMapping("/providerForObject")
    public Map<String,String> providerForObject(String name,String pwd) throws Exception {
        
    	ServiceInstance instance = serviceInstance();
        LOGGER.info("----------provider service, host = " + instance.getHost()
                + ", service_id = " + instance.getServiceId());
        Map<String,String> map=new HashMap<String,String>();
        map.put("name", name);
        map.put("pwd", pwd);
        //让处理线程等待几秒钟，来模拟Hystrixs处理服务阻塞时的情况
        //Hystrixs默认的超时时间为2000ms，采用0-3000的随机数，来模拟一定概率触发断路器
        //int sleepTime=new Random().nextInt(3000);
        int sleepTime=new Random().nextInt(50000);
        System.out.println("providerForObject sleepTime:"+sleepTime);
        Thread.sleep(sleepTime);
        return map;
    }

    /**
     * 获取当前服务的服务实例
     *
     * @return ServiceInstance
     */
    public ServiceInstance serviceInstance() {
        List<ServiceInstance> list = discoveryClient.getInstances(registration.getServiceId());
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }
}