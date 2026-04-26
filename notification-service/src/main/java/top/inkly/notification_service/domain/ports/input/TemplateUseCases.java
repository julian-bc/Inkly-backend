package top.inkly.notification_service.domain.ports.input;

import top.inkly.notification_service.domain.filters.TemplateFiltersModel;
import top.inkly.notification_service.domain.models.EmailTemplateModel;
import top.inkly.shared.domain.pagination.PageResponse;
import top.inkly.shared.domain.pagination.PaginationRequest;

public interface TemplateUseCases {

    void registerTemplate(EmailTemplateModel template);

    void updateTemplate(EmailTemplateModel template, String templateName);

    PageResponse<EmailTemplateModel> getPageTemplates(PaginationRequest paginationRequest, TemplateFiltersModel filters);

    void deleteTemplateById(String templateId);

}
