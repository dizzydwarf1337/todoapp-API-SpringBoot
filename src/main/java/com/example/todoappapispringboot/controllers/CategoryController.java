package com.example.todoappapispringboot.controllers;

import com.example.todoappapispringboot.dtos.Category.CategoryDto;
import com.example.todoappapispringboot.dtos.Category.CreateCategoryDto;
import com.example.todoappapispringboot.dtos.Category.EditCategoryDto;
import com.example.todoappapispringboot.services.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @GetMapping("/{userId}")
    public ResponseEntity<List<CategoryDto>> GetUserCategories(@PathVariable String userId) {
        try {
            var categories = categoryService.GetCategoriesByUserId(userId);
            return ResponseEntity.ok(categories.stream().map(category -> new CategoryDto(category)).collect(Collectors.toList()));
        }
        catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
    @PostMapping("/create")
    public ResponseEntity<String> CreateCategory(@RequestBody CreateCategoryDto categoryDto) {
        try{
            categoryService.AddCategory(categoryDto);
            return ResponseEntity.ok("Category created");
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/delete")
    public ResponseEntity<String> DeleteCategory(@RequestBody String categoryId){
        try{
            categoryService.DeleteCategory(categoryId);
            return ResponseEntity.ok("Category deleted successfully");
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PatchMapping("/edit")
    public ResponseEntity<String> EditCategory(@RequestBody EditCategoryDto editCategoryDto) {
        try{
            categoryService.EditCategory(editCategoryDto);
            return ResponseEntity.ok("Category edited successfully");
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
