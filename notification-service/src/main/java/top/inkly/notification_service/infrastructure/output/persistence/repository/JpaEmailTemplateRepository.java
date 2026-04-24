package top.inkly.notification_service.infrastructure.output.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import top.inkly.notification_service.infrastructure.output.persistence.entity.EmailTemplateEntity;

import java.util.Optional;

public interface JpaEmailTemplateRepository extends JpaRepository<EmailTemplateEntity, String>, JpaSpecificationExecutor<EmailTemplateEntity> {

    Optional<EmailTemplateEntity> findByTemplateName(String templateName);

}
