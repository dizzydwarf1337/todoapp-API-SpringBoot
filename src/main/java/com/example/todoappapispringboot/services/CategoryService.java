package com.example.todoappapispringboot.services;

import com.example.todoappapispringboot.dtos.Category.CreateCategoryDto;
import com.example.todoappapispringboot.dtos.Category.EditCategoryDto;
import com.example.todoappapispringboot.models.Category;
import com.example.todoappapispringboot.models.User;
import com.example.todoappapispringboot.repositories.CategoryRepository;
import com.example.todoappapispringboot.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public CategoryService(CategoryRepository categoryRepository, UserRepository userRepository) {
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    public List<Category> GetCategoriesByUserId(String userId){
        UUID id = UUID.fromString(userId);
        return categoryRepository.GetCategoriesByUserId(id);
    }
    public void AddCategory(CreateCategoryDto createCategoryDto){
        User user = userRepository.findById(UUID.fromString(createCategoryDto.getUserId()))
                .orElseThrow(() -> new RuntimeException("User not found with id: " + createCategoryDto.getUserId()));
        var categories = categoryRepository.GetCategoriesByUserId(UUID.fromString(createCategoryDto.getUserId()));
        boolean exists = categories.stream()
                .anyMatch(c -> c.getTitle().equals(createCategoryDto.getName()));
        if (exists) return;
        Category category = new Category();
        category.setId(UUID.randomUUID());
        category.setTitle(createCategoryDto.getName());
        category.setUser(user);
        categoryRepository.save(category);
    }
    public void DeleteCategory(String id){
        UUID categoryId = UUID.fromString(id);
        categoryRepository.deleteById(categoryId);
    }


    public void EditCategory(EditCategoryDto editCategoryDto){
        Category category = categoryRepository.findById(UUID.fromString(editCategoryDto.getCategoryId())).orElseThrow(() -> new RuntimeException("Category not found with id: " + editCategoryDto.getCategoryId()));
        category.setTitle(editCategoryDto.getCategoryName());
        categoryRepository.save(category);
    }
}
