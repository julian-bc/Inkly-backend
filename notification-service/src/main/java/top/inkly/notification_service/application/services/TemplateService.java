package top.inkly.notification_service.application.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import top.inkly.notification_service.domain.exceptions.NotFoundTemplateException;
import top.inkly.notification_service.domain.models.EmailTemplateModel;
import top.inkly.notification_service.domain.ports.input.TemplateUseCases;
import top.inkly.notification_service.domain.ports.output.EmailTemplateRepositoryport;

@Service
@RequiredArgsConstructor
public class TemplateService implements TemplateUseCases {

    private final EmailTemplateRepositoryport templateRepository;

    @Override
    public void registerTemplate(EmailTemplateModel template) {
        templateRepository.saveTemplate(template);
    }

    @Override
    public void updateTemplate(EmailTemplateModel template, String templateName) {
        EmailTemplateModel toUpdateTemplate = getTemplateByNameIdentifier(templateName);
        mapTemplateValues(toUpdateTemplate, template);
        templateRepository.saveTemplate(toUpdateTemplate);
    }

    @Override
    public EmailTemplateModel getTemplateByNameIdentifier(String templateName) {
        return templateRepository.findTemplateByName(templateName)
                .orElseThrow(() -> new NotFoundTemplateException(templateName));
    }

    @Override
    public void deleteTemplateById(String templateId) {
        templateRepository.findTemplateById(templateId)
                .orElseThrow(() -> new NotFoundTemplateException(templateId));
        templateRepository.deleteTemplateById(templateId);
    }


    private void mapTemplateValues(EmailTemplateModel target, EmailTemplateModel source) {
        if (source.getTemplateLocation() != null) target.setTemplateLocation(source.getTemplateLocation());
        if (source.getTemplateSubject() != null) target.setTemplateSubject(source.getTemplateLocation());
    }

}