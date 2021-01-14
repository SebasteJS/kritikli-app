package com.example.demo.dto;

import javax.validation.constraints.NotEmpty;

public class ImageDto {

    private Long id;

    @NotEmpty
    private String author;

    @NotEmpty
    private String title;

    @NotEmpty
    private String description;

    @NotEmpty
    private String criticalDescription;
}
