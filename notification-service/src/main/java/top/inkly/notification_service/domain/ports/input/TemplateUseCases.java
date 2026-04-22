package top.inkly.notification_service.domain.ports.input;

import top.inkly.notification_service.domain.models.EmailTemplateModel;

public interface TemplateUseCases {

    void registerTemplate(EmailTemplateModel template);

    void updateTemplate(EmailTemplateModel template, String templateName);

    EmailTemplateModel getTemplateByNameIdentifier(String templateName);

    void deleteTemplateById(String templateId);

}
