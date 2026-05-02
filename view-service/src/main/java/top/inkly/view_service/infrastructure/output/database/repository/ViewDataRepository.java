package top.inkly.view_service.infrastructure.output.database.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import top.inkly.view_service.infrastructure.output.database.document.ViewItem;

public interface ViewDataRepository extends MongoRepository<ViewItem, String> {
    ViewItem findByBook_BookId(String bookId);
    void deleteByBook_BookId(String bookId);
}
