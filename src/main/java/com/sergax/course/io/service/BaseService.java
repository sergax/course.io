package com.sergax.course.io.service;

import java.util.Set;

public interface BaseService<T, V> {
    T getByName(V v);
    Set<T> getAll();
    V create(T t);
    V update(T t);
}

