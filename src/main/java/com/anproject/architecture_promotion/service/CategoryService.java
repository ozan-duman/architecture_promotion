package com.anproject.architecture_promotion.service;

import com.anproject.architecture_promotion.dto.request.CategoryRequestDTO;
import com.anproject.architecture_promotion.dto.request.CategoryUpdateDTO;
import com.anproject.architecture_promotion.dto.response.CategoryResponseDTO;
import com.anproject.architecture_promotion.entity.Category;
import com.anproject.architecture_promotion.exception.ApiNotFoundException;
import com.anproject.architecture_promotion.mapper.CategoryMapper;
import com.anproject.architecture_promotion.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }
    // Save a new category
    public CategoryResponseDTO saveCategory(CategoryRequestDTO categoryRequestDto) {
        // Convert DTO to entity
        Category category = categoryMapper.categoryRequestDTOtoCategory(categoryRequestDto);
        // Save the category to the database
        Category savedCategory = categoryRepository.save(category);
        // Return the response DTO
        return categoryMapper.categoryToCategoryResponseDTO(savedCategory);
    }

    // Update an existing category
    public CategoryResponseDTO updateCategory(CategoryUpdateDTO categoryUpdateDto) {
        // Find the existing category by ID
        Optional<Category> existingCategoryOpt = categoryRepository.findById(categoryUpdateDto.getId());
        // If the category is present, update it
        if (existingCategoryOpt.isPresent()) {
            Category existingCategory = existingCategoryOpt.get();
            existingCategory.setCategoryName(categoryUpdateDto.getCategoryName()); // Update the name
            Category updatedCategory = categoryRepository.save(existingCategory); // Save the updated category
            return categoryMapper.categoryToCategoryResponseDTO(updatedCategory); // Return response DTO
        }
        // Return null or an empty response if category not found
        return null; // Alternatively, handle as per your requirement
    }

    // Delete a category by ID
    public void deleteCategory(Long id) {
        if (id == null || !categoryRepository.existsById(id)) {
            throw new ApiNotFoundException("Silinmek istenen kategori bulunamadı.");
        }

        categoryRepository.deleteById(id);
    }

    // Find a category by ID
    public CategoryResponseDTO getCategoryById(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ApiNotFoundException("Kategori bulunamadı."));
        // Find the category and return response DTO if present
        return categoryRepository.findById(categoryId)
                .map(categoryMapper::categoryToCategoryResponseDTO)
                .orElse(null); // Return null if not found
    }

    // Find all categories
    public List<CategoryResponseDTO> getAllCategories() {
        List<Category> categoryList = categoryRepository.findAll();
        return categoryList.stream()
                .map(category -> categoryMapper.categoryToCategoryResponseDTO(category))
                .toList(); // Convert each category to response DTO
    }



}
