package top.inkly.notification_service.infrastructure.input.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class TemplatePostRequestDTO {

    private String templateName;
    private String templateSubject;
    private String templateLocation;

}
