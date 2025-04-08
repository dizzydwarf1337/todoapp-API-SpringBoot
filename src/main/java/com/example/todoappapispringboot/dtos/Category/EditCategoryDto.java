package com.example.todoappapispringboot.dtos.Category;

public class EditCategoryDto {
    private String categoryId;
    private String categoryName;

    public String getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
    public String getCategoryName() {
        return categoryName;
    }
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
