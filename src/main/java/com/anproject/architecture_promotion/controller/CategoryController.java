package com.anproject.architecture_promotion.controller;

import com.anproject.architecture_promotion.dto.request.CategoryRequestDTO;
import com.anproject.architecture_promotion.dto.request.CategoryUpdateDTO;
import com.anproject.architecture_promotion.dto.response.CategoryResponseDTO;
import com.anproject.architecture_promotion.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;

    }

    @PostMapping("/save")
    public ResponseEntity<Void> saveCategory(@Valid @RequestBody CategoryRequestDTO categoryRequestDTO) {
        categoryService.saveCategory(categoryRequestDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateCategory(@PathVariable Long id,
                                               @Valid @RequestBody CategoryUpdateDTO categoryUpdateDTO) {
        categoryUpdateDTO.setId(id);
        categoryService.updateCategory(categoryUpdateDTO);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<CategoryResponseDTO> getCategoryById(@PathVariable Long id) {
        CategoryResponseDTO categoryResponseDTO = categoryService.getCategoryById(id);
        return new ResponseEntity<>(categoryResponseDTO, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CategoryResponseDTO>> getAllCategories() {
        List<CategoryResponseDTO> categoryResponseDTOList = categoryService.getAllCategories();
        return new ResponseEntity<>(categoryResponseDTOList, HttpStatus.OK);

    }


}
