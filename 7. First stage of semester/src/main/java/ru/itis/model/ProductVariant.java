package ru.itis.model;

import ru.itis.model.entity.Entity;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductVariant extends Entity {
    private Color color;
    private List<Image> images;
    private Integer price;
}

