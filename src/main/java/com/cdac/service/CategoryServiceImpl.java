package com.cdac.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdac.custom_exceptions.ResourceNotFoundException;
import com.cdac.dao.CategoryRepository;
import com.cdac.dao.UserDao;
import com.cdac.dto.CategoryReqDTO;
import com.cdac.dto.CategoryRespDTO;
import com.cdac.entities.Category;
import com.cdac.entities.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private UserDao userDao;
    private final CategoryRepository categoryRepository;

    @Override
    public CategoryRespDTO createCategory(CategoryReqDTO categoryReqDTO) {
        Category category = new Category();
        category.setName(categoryReqDTO.getName());
        category.setType(categoryReqDTO.getType());
        User user = userDao.findById(categoryReqDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + categoryReqDTO.getUserId()));

        category.setUser(user);

        Category saved = categoryRepository.save(category);
        return mapToRespDTO(saved);
    }

    @Override
    public CategoryRespDTO getCategoryById(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with ID: " + categoryId));
        return mapToRespDTO(category);
    }

    @Override
    public List<CategoryRespDTO> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(this::mapToRespDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryRespDTO updateCategory(Long categoryId, CategoryReqDTO categoryReqDTO) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with ID: " + categoryId));
        category.setName(categoryReqDTO.getName());
        category.setType(categoryReqDTO.getType());
        Category updated = categoryRepository.save(category);
        return mapToRespDTO(updated);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        if (!categoryRepository.existsById(categoryId)) {
            throw new ResourceNotFoundException("Category not found with ID: " + categoryId);
        }
        categoryRepository.deleteById(categoryId);
    }

    private CategoryRespDTO mapToRespDTO(Category category) {
        CategoryRespDTO dto = new CategoryRespDTO();
        dto.setCategoryId(category.getCategoryId());
        dto.setName(category.getName());
        dto.setType(category.getType());
        if (category.getUser() != null) {
            dto.setUserId(category.getUser().getUserId());
            dto.setUserName(category.getUser().getName());
        }
        return dto;
    }
}
