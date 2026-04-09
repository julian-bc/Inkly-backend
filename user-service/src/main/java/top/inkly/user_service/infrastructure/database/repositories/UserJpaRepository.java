package top.inkly.user_service.infrastructure.database.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import top.inkly.user_service.infrastructure.database.entities.UserEntity;

import java.util.UUID;

public interface UserJpaRepository extends JpaRepository<UserEntity, UUID> {
    boolean existsByEmail(String email);
}
