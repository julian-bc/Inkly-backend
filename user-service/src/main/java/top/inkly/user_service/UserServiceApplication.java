package top.inkly.user_service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import top.inkly.user_service.infrastructure.database.entities.RoleEntity;
import top.inkly.user_service.infrastructure.database.entities.UserEntity;
import top.inkly.user_service.infrastructure.database.repositories.RoleJpaRepository;
import top.inkly.user_service.infrastructure.database.repositories.UserJpaRepository;

import java.time.LocalDateTime;

@Slf4j
@SpringBootApplication
@EnableDiscoveryClient
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner initData(
			RoleJpaRepository roleJpaRepository,
			UserJpaRepository userJpaRepository
	) {
		return args -> {
			log.info("Iniciando carga de datos, tabla roles");

			if (roleJpaRepository.count() == 0) {
				roleJpaRepository.save(new RoleEntity(1, "ADMIN"));
				roleJpaRepository.save(new RoleEntity(2, "USER"));
				log.info("¡Base de datos inicializada con {} roles!", roleJpaRepository.count());
			} else {
				log.info("Roles ya existentes, no se insertan nuevamente.");
			}

			RoleEntity adminRole = roleJpaRepository.findById(1).orElseThrow();

			if (!userJpaRepository.existsByEmail("admin@inkly.top")) {
				UserEntity admin = new UserEntity();

				admin.setUserName("Admin Inkly");
				admin.setEmail("admin@inkly.top");
				admin.setPassword("admin1234");
				admin.setRole(adminRole);
				admin.setEmailVerified(true);
				admin.setPasswordVerified(true);
				admin.setEnable(true);
				admin.setCreatedAt(LocalDateTime.now());

				userJpaRepository.save(admin);

				log.info("¡Base de datos inicializada con administrador {}!", admin.getEmail());
			}
		};
	}
}
