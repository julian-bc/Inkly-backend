package top.inkly.user_service.infrastructure.output.database.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import top.inkly.user_service.infrastructure.output.database.entities.UserEntity;

import java.util.Optional;
import java.util.UUID;

public interface UserJpaRepository extends JpaRepository<UserEntity, UUID>, JpaSpecificationExecutor<UserEntity> {

    boolean existsByEmail(String email);

    boolean existsByUserNameOrEmail(String userName, String email);

    @Query("SELECT u FROM UserEntity u WHERE u.userName = :value OR u.email = :value")
    Optional<UserEntity> findByUserNameOrEmail(@Param("value") String usernameOrEmail);
}
