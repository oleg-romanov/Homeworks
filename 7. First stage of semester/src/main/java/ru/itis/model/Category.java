package ru.itis.model;

import ru.itis.model.entity.Entity;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Category extends Entity {
    private String name;
    private Category parent;
    private List<Category> child;
}
