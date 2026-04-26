package top.inkly.view_service.domain.repository;

import top.inkly.shared.domain.PaginationRequest;
import top.inkly.shared.domain.PaginationResult;
import top.inkly.view_service.domain.models.ViewModel;

public interface ViewRepository {
    PaginationResult<ViewModel> findAllWithNewsReaders(PaginationRequest request);
    ViewModel findByBookTitle(String bookTitle);
    void save(ViewModel newView);
    void deleteByBookId(String bookId);
    void updateAllCounters();
}
