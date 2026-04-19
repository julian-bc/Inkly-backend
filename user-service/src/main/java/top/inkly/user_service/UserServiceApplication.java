package top.inkly.user_service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import top.inkly.user_service.domain.models.enums.RoleNames;
import top.inkly.user_service.infrastructure.output.database.entities.RoleEntity;
import top.inkly.user_service.infrastructure.output.database.entities.UserEntity;
import top.inkly.user_service.infrastructure.output.database.repositories.RoleJpaRepository;
import top.inkly.user_service.infrastructure.output.database.repositories.UserJpaRepository;

import java.time.LocalDateTime;
import java.util.UUID;

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
				roleJpaRepository.save(new RoleEntity(1, RoleNames.INKLY_ADMIN.name()));
				roleJpaRepository.save(new RoleEntity(2, RoleNames.INKLY_USER.name()));
				log.info("¡Base de datos inicializada con {} roles!", roleJpaRepository.count());
			} else {
				log.info("Roles ya existentes, no se insertan nuevamente.");
			}

			RoleEntity adminRole = roleJpaRepository.findById(1).orElseThrow();

			if (!userJpaRepository.existsByEmail("admin@inkly.top")) {
				UserEntity admin = new UserEntity();

                admin.setUserId(UUID.randomUUID());
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
