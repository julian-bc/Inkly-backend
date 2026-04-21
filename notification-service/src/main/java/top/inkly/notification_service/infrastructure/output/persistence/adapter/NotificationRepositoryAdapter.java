package top.inkly.notification_service.infrastructure.output.persistence.adapter;

import org.springframework.stereotype.Component;
import top.inkly.notification_service.domain.models.InAppNotificationModel;
import top.inkly.notification_service.domain.ports.output.NotificationRepositoryPort;

@Component
public class NotificationRepositoryAdapter implements NotificationRepositoryPort {

    @Override
    public void createNotification(InAppNotificationModel notification) {

    }

    @Override
    public void findNotificationByUserId(String userId) {

    }

    @Override
    public void updateNotificationShown(String notificationId) {

    }

}
