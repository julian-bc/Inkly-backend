package top.inkly.user_service.infrastructure.input.rest.mapper;

import org.mapstruct.Mapper;
import top.inkly.shared.domain.pagination.PageResponse;
import top.inkly.user_service.domain.models.UserModel;
import top.inkly.user_service.infrastructure.input.rest.dtos.user.CreateUser;
import top.inkly.user_service.infrastructure.input.rest.dtos.user.PatchUser;
import top.inkly.user_service.infrastructure.input.rest.dtos.user.UserResponse;

@Mapper(componentModel = "spring")
public interface UserRestMapper {
    PageResponse<UserResponse> toUserResponse(PageResponse<UserModel> userPageResponse);
    UserResponse toUserResponse(UserModel user);
    UserModel toDomain(CreateUser createUser);
    UserModel toDomain(PatchUser patchUser);
}
