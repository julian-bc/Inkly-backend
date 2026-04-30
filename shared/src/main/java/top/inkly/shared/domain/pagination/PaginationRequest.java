package top.inkly.shared.domain.pagination;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PaginationRequest {
    private int pageNumber;
    private int pageSize;
}