package top.inkly.user_service.infrastructure.database.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import top.inkly.user_service.infrastructure.database.entities.RoleEntity;

public interface RoleJpaRepository extends JpaRepository<RoleEntity, Integer> {
}
