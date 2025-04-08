package com.example.todoappapispringboot.dtos.Category;

public class CreateCategoryDto {
    private String categoryName;
    private String userId;


    public String getName(){
        return categoryName;
    }
    public String getUserId(){
        return userId;
    }
}
