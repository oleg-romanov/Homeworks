package ru.itis.repositories;

import java.util.List;
import java.util.Optional;

/**
 * 02.10.2020
 * 4. Simple Web Application
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public interface CrudRepository<T> {
    void save(T entity);
    List<T> findAll();
    Optional<T> findByEmail(String email);
}
