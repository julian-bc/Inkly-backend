package top.inkly.notification_service.domain.ports.output;

import top.inkly.notification_service.domain.models.EmailNotificationModel;

public interface MailSenderPort {

    void executeSendEmail(EmailNotificationModel notification);

}
