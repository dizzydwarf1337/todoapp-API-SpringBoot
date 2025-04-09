package com.example.todoappapispringboot.services;

import com.example.todoappapispringboot.dtos.Category.CategoryDto;
import com.example.todoappapispringboot.dtos.Category.CreateCategoryDto;
import com.example.todoappapispringboot.dtos.Category.EditCategoryDto;
import com.example.todoappapispringboot.models.Category;
import com.example.todoappapispringboot.models.User;
import com.example.todoappapispringboot.repositories.CategoryRepository;
import com.example.todoappapispringboot.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public CategoryService(CategoryRepository categoryRepository, UserRepository userRepository) {
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    public CategoryDto GetCategoryById(UUID categoryId){
        var category = categoryRepository.findById(categoryId).orElseThrow(()-> new RuntimeException("Category not found"));
        return new CategoryDto(category);
    }

    public List<CategoryDto> GetCategoriesByUserId(UUID userId){
        var user = userRepository.findById(userId).orElseThrow(()-> new RuntimeException("User not found"));
        var categories =  categoryRepository.findByUserId(userId).orElseThrow(()-> new RuntimeException("Category not found"));
        return categories.stream().map(CategoryDto::new).collect(Collectors.toList());
    }
    public void AddCategory(CreateCategoryDto createCategoryDto){
        User user = userRepository.findById(UUID.fromString(createCategoryDto.getUserId()))
                .orElseThrow(() -> new RuntimeException("User not found with id: " + createCategoryDto.getUserId()));
        var categories = categoryRepository.findByUserId(UUID.fromString(createCategoryDto.getUserId())).orElseThrow(()-> new RuntimeException("Category not found"));
        boolean exists = categories.stream()
                .anyMatch(c -> c.getTitle().equals(createCategoryDto.getTitle()));
        if (exists) throw new RuntimeException("Category already exists");
        Category category = new Category();
        category.setTitle(createCategoryDto.getTitle());
        category.setUser(user);
        categoryRepository.save(category);
    }
    public void DeleteCategory(UUID categoryId){
        categoryRepository.deleteById(categoryId);
    }


    public void EditCategory(EditCategoryDto editCategoryDto){
        Category category = categoryRepository.findById(UUID.fromString(editCategoryDto.getCategoryId())).orElseThrow(() -> new RuntimeException("Category not found with id: " + editCategoryDto.getCategoryId()));
        category.setTitle(editCategoryDto.getCategoryName());
        categoryRepository.save(category);
    }
}
