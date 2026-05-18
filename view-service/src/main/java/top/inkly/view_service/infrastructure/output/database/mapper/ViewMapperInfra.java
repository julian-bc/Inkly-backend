package top.inkly.view_service.infrastructure.output.database.mapper;

import org.mapstruct.Mapper;
import top.inkly.view_service.domain.models.ViewModel;
import top.inkly.view_service.infrastructure.output.database.document.ViewItem;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ViewMapperInfra {
    List<ViewModel> toDomain(List<ViewItem> viewItemList);
    ViewModel toDomain(ViewItem viewItem);
    ViewItem toInfra(ViewModel viewModel);
}
