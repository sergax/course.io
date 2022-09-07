package com.sergax.courseio.service;

import java.util.Set;

public interface BaseService<T, V> {
    T getById(V v);
    Set<T> getAll();
    T create(T t);
    T update(V v, T t);
}

