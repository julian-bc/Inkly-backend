package top.inkly.view_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;
import top.inkly.shared.infrastructure.config.EnableUserClient;

@EnableScheduling
@EnableDiscoveryClient
@EnableUserClient
@SpringBootApplication
public class ViewServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ViewServiceApplication.class, args);
	}

}
