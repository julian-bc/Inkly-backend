package top.inkly.notification_service.infrastructure.input.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.inkly.notification_service.domain.filters.TemplateFiltersModel;
import top.inkly.notification_service.domain.ports.input.TemplateUseCases;
import top.inkly.notification_service.infrastructure.input.dto.TemplateGetResponseDTO;
import top.inkly.notification_service.infrastructure.input.dto.TemplatePatchRequestDTO;
import top.inkly.notification_service.infrastructure.input.dto.TemplatePostRequestDTO;
import top.inkly.notification_service.infrastructure.input.mapper.TemplateInputMapper;
import top.inkly.shared.domain.pagination.PageResponse;
import top.inkly.shared.domain.pagination.PaginationRequest;

@RestController
@RequestMapping("/templates")
@RequiredArgsConstructor
public class NotificationTemplatesRestController {

    private final TemplateUseCases templateService;
    private final TemplateInputMapper mapper;

    @PostMapping
    ResponseEntity<Void> createTemplate(@RequestBody TemplatePostRequestDTO templateDTO) {
        templateService.registerTemplate(mapper.toModel(templateDTO));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/{templateName}")
    ResponseEntity<Void> modifyTemplate(@RequestBody TemplatePatchRequestDTO templateDTO, @PathVariable String templateName) {
        templateService.updateTemplate(mapper.toModel(templateDTO), templateName);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping
    ResponseEntity<PageResponse<TemplateGetResponseDTO>> getPageTemplates(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String templateName,
            @RequestParam(required = false) String templateSubject
    ) {
        TemplateFiltersModel filters = TemplateFiltersModel.builder()
                .templateName(templateName)
                .templateSubject(templateSubject)
                .build();
        return ResponseEntity.ok(
                mapper.toDTO(templateService.getPageTemplates(new PaginationRequest(page, size), filters)));
    }

    @DeleteMapping("/{templateId}")
    ResponseEntity<Void> deleteTemplate(@PathVariable String templateId) {
        templateService.deleteTemplateById(templateId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
