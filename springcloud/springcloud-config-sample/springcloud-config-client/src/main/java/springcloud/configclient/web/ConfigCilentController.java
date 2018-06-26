package springcloud.configclient.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class ConfigCilentController {
	
	@Value("${servers.ports}")
    String port;
	
	@Value("${eurekas.instances.hostnames}")
    String hostname;
	
	@Value("${eurekas.clients.service-urls.defaultZones}")
    String defaultZone;
	
	@Value("${springs.applications.names}")
	String applicationName;
	
	@RequestMapping("/Hello")
    String hello() {
        return "Hello " +applicationName+","+ port + ","+hostname+","+defaultZone+"!";
    }
	
	/*@Value("${admin.name}")
	String name;
	
	@Value("${admin.pwd}")
	String pwd;
	
	@RequestMapping("/my")
	String my() {
		return "name:"+name+",pwd:"+pwd;
	}*/
}
