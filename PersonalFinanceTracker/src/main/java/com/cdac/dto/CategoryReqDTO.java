package com.cdac.dto;

import com.cdac.entities.CategoryType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CategoryReqDTO {

    @NotBlank(message = "Category name is required")
    private String name;

    @NotNull(message = "Category type is required")
    private CategoryType type;

    @NotNull(message = "User ID is required")
    private Long userId;
}
