package com.example.todoappapispringboot.dtos.Category;

import com.example.todoappapispringboot.models.Category;

public class CategoryDto {
    private String id;
    private String name;
    public CategoryDto(Category category) {
        this.id = category.getId().toString();
        this.name=category.getTitle();
    }

}
