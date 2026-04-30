package top.inkly.notification_service.infrastructure.output.mail.adapter;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import top.inkly.notification_service.domain.exceptions.FailedMailSenderOperation;
import top.inkly.notification_service.domain.exceptions.NotFoundTemplateException;
import top.inkly.notification_service.domain.models.EmailTemplateModel;
import top.inkly.notification_service.domain.models.union.EmailNotificationModel;
import top.inkly.notification_service.domain.ports.output.EmailTemplateRepositoryport;
import top.inkly.notification_service.domain.ports.output.MailSenderPort;
import top.inkly.notification_service.infrastructure.output.mail.resolver.TemplateResolver;

@Component
@RequiredArgsConstructor
public class MailSenderAdapter implements MailSenderPort {

    private final EmailTemplateRepositoryport templateRepository;
    private final TemplateResolver templateResolver;
    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String senderEmail;

    @Override
    public void executeSendEmail(EmailNotificationModel notification) {
        String notificationTemplateId = notification.getNotificationTemplateId();
        EmailTemplateModel template = templateRepository.findTemplateByName(
                notificationTemplateId).orElseThrow(() -> new NotFoundTemplateException(notificationTemplateId));

        String templateContent = templateResolver.resolve(
                template.getTemplateLocation(),
                notification.getDataValues()
        );

        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom(senderEmail);
            helper.setTo(notification.getEmailReceiver());
            helper.setSubject(template.getTemplateSubject());
            helper.setText(templateContent, true);

            javaMailSender.send(message);
        } catch (MessagingException e) {
            throw new FailedMailSenderOperation(e.getMessage());
        }


    }

}
