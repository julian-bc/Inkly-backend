package top.inkly.view_service.infrastructure.output.database.document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import top.inkly.view_service.domain.models.BookModel;

@Document
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ViewItem {
    @Id
    private String viewId;
    private Long readerCounter;
    private Long newReaders;
    private BookModel book;
}
