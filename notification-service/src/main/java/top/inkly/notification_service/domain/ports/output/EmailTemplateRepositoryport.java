package top.inkly.notification_service.domain.ports.output;

import top.inkly.notification_service.domain.models.EmailTemplateModel;

import java.util.Optional;

public interface EmailTemplateRepositoryport {

    void saveTemplate(EmailTemplateModel template);

    Optional<EmailTemplateModel> findTemplateByName(String templateName);

    Optional<EmailTemplateModel> findTemplateById(String templateId);

    void deleteTemplateById(String templateId);

}
