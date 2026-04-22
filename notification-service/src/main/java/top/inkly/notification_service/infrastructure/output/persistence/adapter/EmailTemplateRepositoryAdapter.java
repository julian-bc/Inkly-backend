package top.inkly.notification_service.infrastructure.output.persistence.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import top.inkly.notification_service.domain.models.EmailTemplateModel;
import top.inkly.notification_service.domain.ports.output.EmailTemplateRepositoryport;
import top.inkly.notification_service.infrastructure.output.persistence.mapper.NotificationPersistenceMapper;
import top.inkly.notification_service.infrastructure.output.persistence.repository.JpaEmailTemplateRepository;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EmailTemplateRepositoryAdapter implements EmailTemplateRepositoryport {

    private final JpaEmailTemplateRepository templateRepository;
    private final NotificationPersistenceMapper mapper;

    @Override
    public void saveTemplate(EmailTemplateModel template) {
        templateRepository.save(mapper.toEntity(template));
    }

    @Override
    public Optional<EmailTemplateModel> findTemplateByName(String templateName) {
        return templateRepository.findByTemplateName(templateName);
    }

    @Override
    public void deleteTemplateById(String templateId) {
        templateRepository.deleteById(templateId);
    }
}
