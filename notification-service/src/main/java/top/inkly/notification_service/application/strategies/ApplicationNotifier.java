package top.inkly.notification_service.application.strategies;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import top.inkly.notification_service.domain.models.InAppNotificationModel;
import top.inkly.notification_service.domain.models.NotificationData;
import top.inkly.notification_service.domain.ports.output.NotificationRepositoryPort;

import static top.inkly.shared.domain.notification.types.NotificationTypes.IN_APP;

@Service(IN_APP)
@RequiredArgsConstructor
public class ApplicationNotifier implements Notifier {

    private final NotificationRepositoryPort notificationRepository;

    @Override
    public void sendNotification(NotificationData notification) {
        notificationRepository.createNotification((InAppNotificationModel) notification);
    }

}
