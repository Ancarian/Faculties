package dev.chermenin.dao.specs;

import dev.chermenin.model.impl.BaseObject;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

@AllArgsConstructor
public class CustomSpecification<T extends BaseObject> implements Specification<T> {
    private SearchCriteria criteria;

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        if (criteria.getOperation().equalsIgnoreCase(">")) {
            return greaterThanOrEqualTo(root, builder);

        } else if (criteria.getOperation().equalsIgnoreCase("<")) {
            return lessThanOrEqualTo(root, builder);

        } else if (criteria.getOperation().equalsIgnoreCase(":")) {
            return equalTo(root, builder);
        }
        return null;
    }

    private Predicate equalTo(Root<T> root, CriteriaBuilder builder) {
        if (criteria.getJoin() != null) {
            final Join<T, ? extends BaseObject> join = root.join(criteria.getJoin(), JoinType.LEFT);
            if (join.get(criteria.getKey()).getJavaType() == String.class) {
                return builder.like(
                        join.get(criteria.getKey()), "%" + criteria.getValue() + "%");
            } else {
                return builder.equal(
                        join.get(criteria.getKey()), criteria.getValue());
            }
        }
        if (root.get(criteria.getKey()).getJavaType() == String.class) {
            return builder.like(
                    root.get(criteria.getKey()), "%" + criteria.getValue() + "%");
        } else {
            return builder.equal(root.get(criteria.getKey()), criteria.getValue());
        }
    }

    private Predicate lessThanOrEqualTo(Root<T> root, CriteriaBuilder builder) {
        if (criteria.getJoin() != null) {
            final Join<T, ? extends BaseObject> join = root.join(criteria.getJoin(), JoinType.LEFT);
            return builder.lessThanOrEqualTo(
                    join.get(criteria.getKey()), criteria.getValue().toString());
        }
        return builder.lessThanOrEqualTo(
                root.get(criteria.getKey()), criteria.getValue().toString());
    }

    private Predicate greaterThanOrEqualTo(Root<T> root, CriteriaBuilder builder) {
        if (criteria.getJoin() != null) {
            final Join<T, ? extends BaseObject> join = root.join(criteria.getJoin(), JoinType.LEFT);
            return builder.greaterThanOrEqualTo(
                    join.get(criteria.getKey()), criteria.getValue().toString());
        }
        return builder.greaterThanOrEqualTo(
                root.get(criteria.getKey()), criteria.getValue().toString());
    }
}
