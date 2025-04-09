package com.example.todoappapispringboot.dtos.Category;

public class CreateCategoryDto {
    private String title;
    private String userId;


    public String getTitle(){
        return this.title;
    }
    public String getUserId(){
        return this.userId;
    }
}
