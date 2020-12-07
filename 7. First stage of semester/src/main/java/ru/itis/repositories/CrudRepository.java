package ru.itis.repositories;

import ru.itis.model.Image;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T> {
    void save(T entity);
    List<T> findAll();
}
