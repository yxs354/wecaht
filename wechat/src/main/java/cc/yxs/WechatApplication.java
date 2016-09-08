package cc.yxs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class WechatApplication {
	private static ConfigurableApplicationContext context = null;

	public static void main(String[] args) {
		ConfigurableApplicationContext currentContext=SpringApplication.run(WechatApplication.class, args);
		context=currentContext;
	}

	public static ConfigurableApplicationContext getContext() {
		return context;
	}
}
