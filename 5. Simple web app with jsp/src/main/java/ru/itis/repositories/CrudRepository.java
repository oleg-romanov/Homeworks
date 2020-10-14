package ru.itis.repositories;

import java.sql.SQLException;
import java.util.List;

public interface CrudRepository<T> {
    List<T> findAll();
    void save(T entity) throws SQLException, Exception;
}
