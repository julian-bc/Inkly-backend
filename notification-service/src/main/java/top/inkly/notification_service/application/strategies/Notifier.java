package top.inkly.notification_service.application.strategies;

import top.inkly.notification_service.domain.models.union.NotificationData;

public interface Notifier {

    void sendNotification(NotificationData notification);

}
