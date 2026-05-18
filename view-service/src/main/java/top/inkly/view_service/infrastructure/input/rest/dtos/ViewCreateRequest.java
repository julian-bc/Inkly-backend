package top.inkly.view_service.infrastructure.input.rest.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ViewCreateRequest {
    private String bookId;
    private String title;
    private UUID authorId;
}
