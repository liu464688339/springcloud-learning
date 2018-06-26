package org.spring.springcloud.web;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Customer
 * <p>
 * Created by lh
 */
@RestController
public class CustomerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private RestTemplate restTemplate; // HTTP 访问操作类

    @RequestMapping(value="/ribbon-consumer")
    public String customer() {
        String providerMsg = restTemplate.getForEntity("http://CLUSTER-PROVIDER-SERVICE/provider",
                String.class).getBody();
        LOGGER.info("msg from provider : <br/><br/> " + providerMsg);
        return "Hello,Customer! msg from provider : <br/><br/> " + providerMsg;
    }
}