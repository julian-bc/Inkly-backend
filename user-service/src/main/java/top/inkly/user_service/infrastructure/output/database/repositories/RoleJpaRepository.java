package top.inkly.user_service.infrastructure.output.database.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import top.inkly.user_service.infrastructure.output.database.entities.RoleEntity;

public interface RoleJpaRepository extends JpaRepository<RoleEntity, Integer> {
}
