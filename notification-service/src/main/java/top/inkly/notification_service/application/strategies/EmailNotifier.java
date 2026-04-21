package top.inkly.notification_service.application.strategies;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import static top.inkly.shared.domain.notification.types.NotificationTypes.EMAIL;

import top.inkly.notification_service.domain.models.EmailNotificationModel;
import top.inkly.notification_service.domain.models.NotificationData;
import top.inkly.notification_service.domain.ports.output.MailSenderPort;

@Service(EMAIL)
@RequiredArgsConstructor
public class EmailNotifier implements Notifier {

    private final MailSenderPort mailSender;

    @Override
    public void sendNotification(NotificationData notification) {
        mailSender.executeSendEmail((EmailNotificationModel) notification);
    }

}
