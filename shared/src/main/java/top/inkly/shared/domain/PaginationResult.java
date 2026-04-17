package top.inkly.shared.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class PaginationResult<T> {
    private List<T> content;
    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private int totalPages;

    public MetaData toMetaData() {
        return MetaData.builder()
                .numberPage(pageNumber)
                .pageSize(pageSize)
                .totalItems(totalElements)
                .totalPages(totalPages)
                .build();
    }
}
