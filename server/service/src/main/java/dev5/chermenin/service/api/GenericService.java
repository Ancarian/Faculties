package dev5.chermenin.service.api;

import dev5.chermenin.service.dto.Dto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GenericService<T extends Dto, K> {
    T findById(K id);

    List<T> findAll(Pageable pageable);

    void remove(K id);

    void removeAll();

    T save(T id);

    void update(T id);
}

