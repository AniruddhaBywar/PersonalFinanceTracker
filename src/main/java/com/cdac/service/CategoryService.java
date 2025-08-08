package com.cdac.service;

import com.cdac.dto.CategoryReqDTO;
import com.cdac.dto.CategoryRespDTO;

import java.util.List;

public interface CategoryService {

    CategoryRespDTO createCategory(CategoryReqDTO categoryReqDTO);

    CategoryRespDTO getCategoryById(Long categoryId);

    List<CategoryRespDTO> getAllCategories();

    CategoryRespDTO updateCategory(Long categoryId, CategoryReqDTO categoryReqDTO);

    void deleteCategory(Long categoryId);
}
