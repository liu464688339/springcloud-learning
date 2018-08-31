package org.spring.springcloud.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class HelloServiceCommand extends HystrixCommand<String>{
	
	@Autowired
	private RestTemplate restTemplate; // HTTP 访问操作类
	private final Map<String,String> map;

	protected HelloServiceCommand(Map<String,String> map) {
		super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
		this.map=map;
	}


	@Override
	protected String run() throws Exception {
		String url = "http://PROVIDER-SERVICE/providerForObject?name= {name}&pwd= {pwd}";
		Map<String,String> resultMap = restTemplate.getForObject(url, Map.class, map);
		return "Hello,Customer! msg from providerForObject : <br/><br/> " + resultMap.get("name") + ","
				+ resultMap.get("pwd");
	}
	
	@Override
	protected String getFallback(){
		String msg = "CommandHelloWorld触发Hystrix熔断器，调用降级服务";
		System.err.println("=========="+msg);
		return msg;
	}
}
