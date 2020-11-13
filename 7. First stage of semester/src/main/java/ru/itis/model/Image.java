package ru.itis.model;

import ru.itis.model.entity.Entity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Image extends Entity {
    private String name;
}
