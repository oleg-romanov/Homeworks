package ru.itis.model;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Category {
    private Long id;
    private String name;
    private Long imageId;
}
