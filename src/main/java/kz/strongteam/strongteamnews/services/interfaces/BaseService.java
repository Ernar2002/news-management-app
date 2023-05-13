package kz.strongteam.strongteamnews.services.interfaces;

import java.util.List;
import java.util.UUID;

public interface BaseService <T, K> {
    T getById(UUID id);

    List<T> getAll();

    T save(K k);

    T update(UUID id, K k);

    void delete(UUID id);
}
