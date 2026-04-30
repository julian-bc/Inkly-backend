package top.inkly.notification_service.application.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import top.inkly.notification_service.domain.exceptions.NotFoundTemplateException;
import top.inkly.notification_service.domain.filters.TemplateFiltersModel;
import top.inkly.notification_service.domain.models.EmailTemplateModel;
import top.inkly.notification_service.domain.ports.input.TemplateUseCases;
import top.inkly.notification_service.domain.ports.output.EmailTemplateRepositoryport;
import top.inkly.shared.domain.pagination.PageResponse;
import top.inkly.shared.domain.pagination.PaginationRequest;
import top.inkly.shared.domain.pagination.PaginationResult;

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
    public PageResponse<EmailTemplateModel> getPageTemplates(PaginationRequest paginationRequest, TemplateFiltersModel filters) {
        PaginationResult<EmailTemplateModel> pageResult = templateRepository.findTemplates(paginationRequest, filters);

        return PageResponse.<EmailTemplateModel>builder()
                .data(pageResult.getContent())
                .meta(pageResult.toMetaData())
                .build();
    }


    @Override
    public void deleteTemplateById(String templateId) {
        templateRepository.findTemplateById(templateId)
                .orElseThrow(() -> new NotFoundTemplateException(templateId));
        templateRepository.deleteTemplateById(templateId);
    }

    private EmailTemplateModel getTemplateByNameIdentifier(String templateName) {
        return templateRepository.findTemplateByName(templateName)
                .orElseThrow(() -> new NotFoundTemplateException(templateName));
    }

    private void mapTemplateValues(EmailTemplateModel target, EmailTemplateModel source) {
        if (source.getTemplateLocation() != null) target.setTemplateLocation(source.getTemplateLocation());
        if (source.getTemplateSubject() != null) target.setTemplateSubject(source.getTemplateLocation());
    }

}