package top.inkly.notification_service.infrastructure.input.mapper;

import org.mapstruct.Mapper;
import top.inkly.notification_service.domain.models.EmailTemplateModel;
import top.inkly.notification_service.infrastructure.input.dto.TemplatePatchRequestDTO;
import top.inkly.notification_service.infrastructure.input.dto.TemplatePostRequestDTO;

@Mapper(componentModel = "spring")
public interface TemplateInputMapper {

    EmailTemplateModel toModel(TemplatePostRequestDTO dto);
    EmailTemplateModel toModel(TemplatePatchRequestDTO dto);

}
