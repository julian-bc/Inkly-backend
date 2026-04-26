package top.inkly.notification_service.infrastructure.output.persistence.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import top.inkly.notification_service.domain.filters.TemplateFiltersModel;
import top.inkly.notification_service.domain.models.EmailTemplateModel;
import top.inkly.notification_service.domain.ports.output.EmailTemplateRepositoryport;
import top.inkly.notification_service.infrastructure.output.persistence.entity.EmailTemplateEntity;
import top.inkly.notification_service.infrastructure.output.persistence.mapper.NotificationPersistenceMapper;
import top.inkly.notification_service.infrastructure.output.persistence.repository.JpaEmailTemplateRepository;
import top.inkly.notification_service.infrastructure.output.persistence.specification.TemplateSpecification;
import top.inkly.shared.domain.pagination.PaginationRequest;
import top.inkly.shared.domain.pagination.PaginationResult;

import java.util.List;
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
    public PaginationResult<EmailTemplateModel> findTemplates(PaginationRequest paginationRequest, TemplateFiltersModel filters) {
        Pageable pageable = PageRequest.of(
                paginationRequest.getPageNumber(),
                paginationRequest.getPageSize()
        );

        Page<EmailTemplateEntity> pageResult = templateRepository.findAll(TemplateSpecification.withFilters(filters), pageable);
        List<EmailTemplateModel> templates = mapper.toModel(pageResult.getContent());

        return PaginationResult.<EmailTemplateModel>builder()
                .content(templates)
                .pageNumber(pageResult.getNumber())
                .pageSize(pageResult.getSize())
                .totalElements(pageResult.getTotalElements())
                .totalPages(pageResult.getTotalPages())
                .build();
    }

    public Optional<EmailTemplateModel> findTemplateByName(String templateName) {
        return templateRepository.findByTemplateName(templateName).map(mapper::toModel);
    }

    @Override
    public Optional<EmailTemplateModel> findTemplateById(String templateId) {
        return templateRepository.findById(templateId).map(mapper::toModel);
    }

    @Override
    public void deleteTemplateById(String templateId) {
        templateRepository.deleteById(templateId);
    }
}
