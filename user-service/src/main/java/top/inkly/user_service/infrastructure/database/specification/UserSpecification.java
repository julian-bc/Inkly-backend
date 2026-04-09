package top.inkly.user_service.infrastructure.database.specification;

import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import top.inkly.user_service.application.services.filters.UserFilters;
import top.inkly.user_service.infrastructure.database.entities.UserEntity;

import java.util.ArrayList;
import java.util.List;

public class UserSpecification {
    public static Specification<UserEntity> withFilters(UserFilters filters) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filters.getUserName() != null && !filters.getUserName().isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("userName")), "%" + filters.getUserName().toLowerCase() + "%"));
            }

            if (filters.getEmail() != null && !filters.getEmail().isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("email")), "%" + filters.getEmail().toLowerCase() + "%"));
            }

            if (filters.getEnable() != null) {
                predicates.add(cb.equal(root.get("enable"), filters.getEnable()));
            }

            predicates.add(cb.equal(root.join("role").get("roleName"), "USER"));

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
