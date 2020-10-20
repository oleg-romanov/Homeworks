package ru.itis.repositories;
import java.sql.SQLException;

public interface CrudRepository<T> {
    void save(T entity) throws SQLException, Exception;
}
