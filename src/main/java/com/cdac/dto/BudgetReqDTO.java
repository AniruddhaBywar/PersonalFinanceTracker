package com.cdac.dto;

import java.time.YearMonth;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BudgetReqDTO {

    @NotNull(message = "Amount is required")
    private Double amount;

    @NotNull(message = "Month is required")
    private YearMonth month;

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotNull(message = "Category ID is required")
    private Long categoryId;
}
