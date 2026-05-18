package top.inkly.notification_service.application.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import top.inkly.notification_service.application.factory.NotifierFactory;
import top.inkly.notification_service.application.strategies.Notifier;
import top.inkly.notification_service.domain.models.NotificationModel;
import top.inkly.notification_service.domain.ports.input.NotifierUseCases;

@Service
@RequiredArgsConstructor
public class NotificationSenderService implements NotifierUseCases {

    private final NotifierFactory notifierFactory;

    @Override
    public void executeSendNotification(NotificationModel notification) {
        Notifier notifier = notifierFactory.getNotifier(notification.getNotificationType());
        notifier.sendNotification(notification.getNotificationData());
    }

}
