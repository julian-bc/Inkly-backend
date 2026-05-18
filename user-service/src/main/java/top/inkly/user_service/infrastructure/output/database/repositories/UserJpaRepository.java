package top.inkly.user_service.infrastructure.output.database.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import top.inkly.user_service.domain.models.UserModel;
import top.inkly.user_service.infrastructure.output.database.entities.UserEntity;

import java.util.List;
import java.util.UUID;

public interface UserJpaRepository extends JpaRepository<UserEntity, UUID>, JpaSpecificationExecutor<UserEntity> {

    boolean existsByEmail(String email);

    boolean existsByUserNameOrEmail(String userName, String email);
}
