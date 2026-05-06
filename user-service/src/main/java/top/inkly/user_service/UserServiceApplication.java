package top.inkly.user_service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import top.inkly.shared.domain.models.user.RoleModel;
import top.inkly.shared.domain.models.user.RoleNames;
import top.inkly.user_service.application.services.impl.UserService;
import top.inkly.user_service.domain.models.UserModel;
import top.inkly.user_service.infrastructure.output.database.entities.RoleEntity;
import top.inkly.user_service.infrastructure.output.database.repositories.RoleJpaRepository;
import top.inkly.user_service.infrastructure.output.database.repositories.UserJpaRepository;

import java.time.LocalDateTime;

@Slf4j
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner initData(
            RoleJpaRepository roleJpaRepository,
            UserJpaRepository userJpaRepository,
            UserService userService
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

            if (!userJpaRepository.existsByEmail("admin@inkly.top")) {
                UserModel admin = new UserModel();

                admin.setUserName("admin_inkly");
                admin.setEmail("admin@inkly.top");
                admin.setPassword("Inkly_Admin_1234");
                admin.setRole(new RoleModel(1, RoleNames.INKLY_ADMIN));
                admin.setEmailVerified(true);
                admin.setPasswordVerified(true);
                admin.setEnable(true);
                admin.setCreatedAt(LocalDateTime.now());

                userService.createUser(admin);

                log.info("¡Base de datos inicializada con administrador {}!", admin.getEmail());

            }
        };
    }
}
