package top.inkly.notification_service.domain.ports.output;

import top.inkly.notification_service.domain.models.union.InAppNotificationModel;

public interface NotificationRepositoryPort {

    void createNotification(InAppNotificationModel notification);

    void findNotificationByUserId(String userId);

    void updateNotificationShown(String notificationId);

}
