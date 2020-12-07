package ru.itis.repositories.categoryRepository;

import ru.itis.model.Category;
import ru.itis.model.User;
import ru.itis.repositories.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category> {
    Optional<Category> findById(Long id);
    Boolean deleteCategoryById(Long id);
    Boolean updateCategory(Long id, Category category);
}
