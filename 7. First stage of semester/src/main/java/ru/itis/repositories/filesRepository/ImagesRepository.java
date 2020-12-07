package ru.itis.repositories.filesRepository;

import ru.itis.model.Image;
import ru.itis.repositories.CrudRepository;

public interface ImagesRepository extends CrudRepository<Image> {
    Image findById(Long id);
    Image findByName(String name);
}
