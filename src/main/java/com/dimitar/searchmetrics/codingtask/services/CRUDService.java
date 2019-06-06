package com.dimitar.searchmetrics.codingtask.services;

import java.util.List;
import java.util.Optional;

public interface CRUDService <T> {
    List<?> listAll();
    Iterable<T> findAll();
    Optional<T> getById(Long id);
    Optional<T> saveOrUpdate(T domainObject);
    void delete(Long id);
}
