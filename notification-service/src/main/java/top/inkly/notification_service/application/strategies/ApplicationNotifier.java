package top.inkly.notification_service.application.strategies;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import top.inkly.notification_service.domain.models.union.InAppNotificationModel;
import top.inkly.notification_service.domain.models.union.NotificationData;
import top.inkly.notification_service.domain.ports.output.SentNotificationRepositoryPort;

import static top.inkly.shared.domain.notification.types.NotificationTypes.IN_APP;

@Service(IN_APP)
@RequiredArgsConstructor
public class ApplicationNotifier implements Notifier {

    private final SentNotificationRepositoryPort notificationRepository;

    @Override
    public void sendNotification(NotificationData notification) {
        notificationRepository.saveSentNotification((InAppNotificationModel) notification);
    }

}
