package dev.chermenin.dao.specs;


import dev.chermenin.model.impl.BaseObject;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class SpecificationBuilder<T extends BaseObject> {

    private final List<SearchCriteria> params;

    public SpecificationBuilder() {
        params = new ArrayList<>();
    }

    public void with(String key, String operation, String value, String join) {
        params.add(new SearchCriteria(key, operation, value, join));
    }

    public Specification<T> build() {
        if (params.size() == 0) {
            return null;
        }

        List<Specification<T>> specs = new ArrayList<>();
        for (SearchCriteria param : params) {
            specs.add(new CustomSpecification<>(param));
        }

        Specification<T> result = specs.get(0);
        for (int i = 1; i < specs.size(); i++) {
            result = Specification.where(result).and(specs.get(i));
        }
        return result;
    }
}