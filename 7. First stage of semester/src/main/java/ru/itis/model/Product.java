package ru.itis.model;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Product  {
    private Long id;
    private String name;
    private Long categoryId;
    private String description;
    private Long imageId;
}
