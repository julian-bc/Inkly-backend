package top.inkly.notification_service.infrastructure.output.mail.adapter;

import org.springframework.stereotype.Component;
import top.inkly.notification_service.domain.models.union.EmailNotificationModel;
import top.inkly.notification_service.domain.ports.output.MailSenderPort;

@Component
public class MailSenderAdapter implements MailSenderPort {

    @Override
    public void executeSendEmail(EmailNotificationModel notification) {

    }

}
