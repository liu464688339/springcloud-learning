package springcloud.gateway.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class SpringcloudGatewayZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudGatewayZuulApplication.class, args);
	}

}
