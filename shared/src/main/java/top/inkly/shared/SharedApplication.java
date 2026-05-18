package top.inkly.shared;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SharedApplication {

	public static void main(String[] args) {
		SpringApplication.run(SharedApplication.class, args);
	}

}
