package com.example.demo.service;

import java.util.Optional;

public interface Iservice<T> {
    Iterable<T> findAll();
    Optional<T> findById(Long id);
    void delete(Long id);
    T save(T t);
}
