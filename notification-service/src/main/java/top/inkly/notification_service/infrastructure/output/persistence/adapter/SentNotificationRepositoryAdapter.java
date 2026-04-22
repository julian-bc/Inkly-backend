package top.inkly.notification_service.infrastructure.output.persistence.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import top.inkly.notification_service.domain.models.union.InAppNotificationModel;
import top.inkly.notification_service.domain.ports.output.SentNotificationRepositoryPort;
import top.inkly.notification_service.infrastructure.output.persistence.mapper.NotificationPersistenceMapper;
import top.inkly.notification_service.infrastructure.output.persistence.repository.JpaSentNotificationRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SentNotificationRepositoryAdapter implements SentNotificationRepositoryPort {

    private final JpaSentNotificationRepository notificationRepository;
    private final NotificationPersistenceMapper mapper;

    @Override
    public void saveSentNotification(InAppNotificationModel notification) {
        notificationRepository.save(mapper.toEntity(notification));
    }

    @Override
    public List<InAppNotificationModel> findNotificationByUserId(String userId) {
        return notificationRepository.findAllByUserIdOrderByCreatedAtDesc(userId)
                .stream().map(mapper::toModel).toList();
    }

    @Override
    public Integer countByShownState(boolean shown, String userId) {
        return notificationRepository.countByShownEqualsAndUserIdEquals(shown, userId);
    }

}
