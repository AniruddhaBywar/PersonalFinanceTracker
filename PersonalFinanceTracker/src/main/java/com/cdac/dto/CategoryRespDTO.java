package com.cdac.dto;

import com.cdac.entities.CategoryType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CategoryRespDTO {

    private Long categoryId;
    private String name;
    private CategoryType type;

    private Long userId;
    private String userName;
}
