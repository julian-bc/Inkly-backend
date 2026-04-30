package top.inkly.notification_service.infrastructure.output.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import top.inkly.notification_service.domain.models.EmailTemplateModel;
import top.inkly.notification_service.infrastructure.output.persistence.entity.EmailTemplateEntity;

import java.util.Optional;

public interface JpaEmailTemplateRepository extends JpaRepository<EmailTemplateEntity, String> {

    Optional<EmailTemplateEntity> findByTemplateName(String templateName);

}
