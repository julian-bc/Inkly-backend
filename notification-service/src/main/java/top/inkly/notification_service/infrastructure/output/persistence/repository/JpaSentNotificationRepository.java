package top.inkly.notification_service.infrastructure.output.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import top.inkly.notification_service.infrastructure.output.persistence.entity.SentNotificationEntity;

import java.util.List;

public interface JpaSentNotificationRepository extends JpaRepository<SentNotificationEntity, String> {

    List<SentNotificationEntity> findAllByUserIdOrderByCreatedAtDesc(String userId);

    Integer countByShownEqualsAndUserIdEquals(boolean shown, String userId);
}
