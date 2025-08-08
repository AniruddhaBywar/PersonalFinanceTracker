package com.cdac.controller;

import com.cdac.dto.CategoryReqDTO;
import com.cdac.dto.CategoryRespDTO;
import com.cdac.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    // 🔹 Create Category
    @PostMapping
    public ResponseEntity<CategoryRespDTO> createCategory(@Valid @RequestBody CategoryReqDTO categoryReqDTO) {
        CategoryRespDTO createdCategory = categoryService.createCategory(categoryReqDTO);
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }

    // 🔹 Get Category by ID
    @GetMapping("/{id}")
    public ResponseEntity<CategoryRespDTO> getCategoryById(@PathVariable("id") Long id) {
        CategoryRespDTO category = categoryService.getCategoryById(id);
        return ResponseEntity.ok(category);
    }

    // 🔹 Get All Categories
    @GetMapping
    public ResponseEntity<List<CategoryRespDTO>> getAllCategories() {
        List<CategoryRespDTO> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    // 🔹 Update Category
    @PutMapping("/{id}")
    public ResponseEntity<CategoryRespDTO> updateCategory(@PathVariable("id") Long id,
                                                          @Valid @RequestBody CategoryReqDTO categoryReqDTO) {
        CategoryRespDTO updatedCategory = categoryService.updateCategory(id, categoryReqDTO);
        return ResponseEntity.ok(updatedCategory);
    }

    // 🔹 Delete Category
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok("Category deleted successfully");
    }
}
