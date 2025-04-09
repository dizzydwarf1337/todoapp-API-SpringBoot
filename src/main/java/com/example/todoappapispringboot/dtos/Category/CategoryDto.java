package com.example.todoappapispringboot.dtos.Category;

import com.example.todoappapispringboot.models.Category;

public class CategoryDto {
    private String id;
    private String name;
    public CategoryDto(Category category) {
        this.id = category.getId().toString();
        this.name=category.getTitle();
    }

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }

}
