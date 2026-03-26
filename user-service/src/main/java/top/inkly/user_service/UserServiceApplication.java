package top.inkly.user_service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import top.inkly.user_service.infrastructure.database.entities.RoleEntity;
import top.inkly.user_service.infrastructure.database.repositories.RoleJpaRepository;

@Slf4j
@SpringBootApplication
@EnableDiscoveryClient
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner initRoleTable(RoleJpaRepository jpaRepository) {
		return args -> {
			log.info("Iniciando carga de datos, tabla roles");

			if (jpaRepository.count() == 0) {
				jpaRepository.save(new RoleEntity(1, "ADMIN"));
				jpaRepository.save(new RoleEntity(2, "WRITER"));
				jpaRepository.save(new RoleEntity(3, "USER"));
				log.info("¡Base de datos inicializada con {} roles!", jpaRepository.count());
			} else {
				log.info("Roles ya existentes, no se insertan nuevamente.");
			}
		};
	}
}
