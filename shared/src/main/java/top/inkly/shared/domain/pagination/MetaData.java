package top.inkly.shared.domain.pagination;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MetaData {
    private int numberPage;
    private int pageSize;
    private long totalItems;
    private int totalPages;
}
