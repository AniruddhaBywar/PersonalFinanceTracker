package com.cdac.dto;

import java.time.LocalDate;

import com.cdac.entities.TransactionType;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // includes @Getter, @Setter, @ToString, @EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionReqDTO {

    @NotNull(message = "Amount is required")
    private Double amount;

    @NotNull(message = "Transaction type is required")
    private TransactionType type;

    private LocalDate date;

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotNull(message = "Account ID is required")
    private Long accountId;

    @NotNull(message = "Category ID is required")
    private Long categoryId;

    private String description;
}
