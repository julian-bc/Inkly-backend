package top.inkly.notification_service.application.strategies;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import static top.inkly.notification_service.application.strategies.types.NotificationTypes.EMAIL;

import top.inkly.notification_service.domain.models.EmailNotificationModel;
import top.inkly.notification_service.domain.ports.output.MailSenderPort;

@Service(EMAIL)
@RequiredArgsConstructor
public class EmailNotifier implements Notifier<EmailNotificationModel> {

    private final MailSenderPort mailSender;

    @Override
    public void sendNotification(EmailNotificationModel notification) {
        mailSender.executeSendEmail(notification);
    }

}
