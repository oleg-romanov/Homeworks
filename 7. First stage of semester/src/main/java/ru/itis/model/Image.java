package ru.itis.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Image {
    private Long id;
    private String name;
    private String type;
    private Long size;
}
