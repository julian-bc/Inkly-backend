package top.inkly.shared.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PaginationRequest {
    private int pageNumber;
    private int pageSize;
}