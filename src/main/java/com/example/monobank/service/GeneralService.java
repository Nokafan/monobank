package com.example.monobank.service;

import java.util.List;

public interface GeneralService<T> {
    T save(T entity);

    List<T> saveAll(Iterable<T> iterableEntities);

    List<T> getAll();

    T get(Long entityId);
}
