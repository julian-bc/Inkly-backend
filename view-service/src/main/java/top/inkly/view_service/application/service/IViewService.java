package top.inkly.view_service.application.service;

import top.inkly.shared.domain.pagination.PageResponse;
import top.inkly.shared.domain.pagination.PaginationRequest;
import top.inkly.view_service.domain.models.ViewModel;

import java.util.UUID;

public interface IViewService {
    PageResponse<ViewModel> findViewsWithNewReaders(PaginationRequest request);
    void count(String bookId);
    void createView(String bookId, String bookTitle, UUID authorId);
    void deleteView(String bookId);
    void updateViewsCounters();
}
