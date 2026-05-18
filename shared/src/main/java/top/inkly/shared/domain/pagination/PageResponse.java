package top.inkly.shared.domain.pagination;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class PageResponse<T> {
    private List<T> data;
    private MetaData meta;
}
