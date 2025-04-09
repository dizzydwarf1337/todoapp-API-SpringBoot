package com.example.todoappapispringboot.controllers;

import com.example.todoappapispringboot.dtos.ApiResponse;
import com.example.todoappapispringboot.dtos.Category.CategoryDto;
import com.example.todoappapispringboot.dtos.Category.CreateCategoryDto;
import com.example.todoappapispringboot.dtos.Category.EditCategoryDto;
import com.example.todoappapispringboot.dtos.DeleteId;
import com.example.todoappapispringboot.services.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("api/categories")
public class CategoryController extends BaseController{

    private final CategoryService categoryService;
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<ApiResponse<CategoryDto>> GetCategoryById(@PathVariable String categoryId){
        try{
            var category = categoryService.GetCategoryById(UUID.fromString(categoryId));
            return handleResponse(ApiResponse.success(category));
        }
        catch (Exception e){
            return handleResponse(ApiResponse.failure(e.getMessage()));
        }
    }

    @GetMapping("user/{userId}")
    public ResponseEntity<ApiResponse<List<CategoryDto>>>GetUserCategories(@PathVariable String userId) {
        try {
            var categories = categoryService.GetCategoriesByUserId(UUID.fromString(userId));
            return handleResponse(ApiResponse.success(categories));
        }
        catch(Exception e){
            return handleResponse(ApiResponse.failure(e.getMessage()));
        }
    }
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<String>> CreateCategory(@RequestBody CreateCategoryDto categoryDto) {
        try{
            categoryService.AddCategory(categoryDto);
            return handleResponse(ApiResponse.success("Category created"));
        }
        catch(Exception e){
            return handleResponse(ApiResponse.failure(e.getMessage()));
        }
    }
    @DeleteMapping("/delete")
    public ResponseEntity<ApiResponse<String>> DeleteCategory(@RequestBody DeleteId categoryId){
        try{
            categoryService.DeleteCategory(UUID.fromString(categoryId.getId()));
            return handleResponse(ApiResponse.success("Category deleted successfully"));
        }
        catch (Exception e) {
            return handleResponse(ApiResponse.failure(e.getMessage()));
        }
    }
    @PutMapping("/edit")
    public ResponseEntity<ApiResponse<String>> EditCategory(@RequestBody EditCategoryDto editCategoryDto) {
        try{
            categoryService.EditCategory(editCategoryDto);
            return handleResponse(ApiResponse.success("Category edited successfully"));
        }
        catch (Exception e) {
            return handleResponse(ApiResponse.failure(e.getMessage()));
        }
    }
}
