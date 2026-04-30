package top.inkly.view_service.infrastructure.output.database.repository.impl;

import com.mongodb.client.result.UpdateResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import top.inkly.shared.domain.pagination.PaginationRequest;
import top.inkly.shared.domain.pagination.PaginationResult;
import top.inkly.view_service.domain.models.ViewModel;
import top.inkly.view_service.domain.repository.ViewRepository;
import top.inkly.view_service.infrastructure.output.database.document.ViewItem;
import top.inkly.view_service.infrastructure.output.database.mapper.ViewMapperInfra;
import top.inkly.view_service.infrastructure.output.database.repository.ViewDataRepository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ViewMongoDBRepository implements ViewRepository {
    private final MongoTemplate mongoTemplate;
    private final ViewDataRepository mongoRepository;
    private final ViewMapperInfra mapper;

    @Override
    public PaginationResult<ViewModel> findAllWithNewsReaders(PaginationRequest request) {
        Pageable pageable = PageRequest.of(
                request.getPageNumber(),
                request.getPageSize()
        );

        Page<ViewItem> page = mongoRepository.findAll(pageable);

        List<ViewModel> views = mapper.toDomain(page.getContent());

        return PaginationResult.<ViewModel>builder()
                .content(views)
                .pageNumber(page.getNumber())
                .pageSize(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .build();
    }

    @Override
    public ViewModel findByBookTitle(String bookTitle) {
        return mapper.toDomain(mongoRepository
                .findByBook_Title(bookTitle));
    }

    @Override
    public void save(ViewModel newView) {
        mongoRepository.save(mapper.toInfra(newView));
    }

    @Override
    public void deleteByBookId(String bookId) {
        mongoRepository.deleteByBook_BookId(bookId);
    }

    @Override
    public void updateAllCounters() {
        Query query = new Query((Criteria.where("newReaders").gt(0)));
        Update update = new Update().set("newReaders", 0);

        UpdateResult result = mongoTemplate.updateMulti(query, update, ViewItem.class);
        log.info("Documentos actualizados= {}", result.getModifiedCount());
    }
}
