package top.inkly.verification_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import top.inkly.shared.infrastructure.config.EnableUserClient;

@EnableDiscoveryClient
@EnableUserClient
@SpringBootApplication
public class VerificationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(VerificationServiceApplication.class, args);
	}

}
