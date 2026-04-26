package top.inkly.notification_service.domain.ports.output;

import top.inkly.notification_service.domain.filters.TemplateFiltersModel;
import top.inkly.notification_service.domain.models.EmailTemplateModel;
import top.inkly.shared.domain.pagination.PaginationRequest;
import top.inkly.shared.domain.pagination.PaginationResult;

import java.util.Optional;

public interface EmailTemplateRepositoryport {

    void saveTemplate(EmailTemplateModel template);

    PaginationResult<EmailTemplateModel> findTemplates(PaginationRequest paginationRequest, TemplateFiltersModel filters);

    Optional<EmailTemplateModel> findTemplateByName(String templateName);

    Optional<EmailTemplateModel> findTemplateById(String templateId);

    void deleteTemplateById(String templateId);

}
