package kz.strongteam.strongteamnews.services.interfaces;

import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface BaseService <T, K> {
    T getById(UUID id);

    List<T> getAll(Pageable pageable);

    T save(K k);

    T update(UUID id, K k);

    void delete(UUID id);
}
