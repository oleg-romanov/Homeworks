package ru.itis.model;

import ru.itis.model.entity.Entity;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Product extends Entity {
    private String name;
    private Category category;
    private List<ProductVariant> variants;
    private String description;
}
