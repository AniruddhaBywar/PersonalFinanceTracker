package com.cdac.dto;

import java.time.YearMonth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BudgetRespDTO {

    private Long budgetId;
    private Double amount;
    private YearMonth month;
    private Long userId;
    private String userName;
    private Long categoryId;
    private String categoryName;
}
