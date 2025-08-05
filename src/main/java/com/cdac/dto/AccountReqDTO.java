package com.cdac.dto;

import com.cdac.entities.AccountType;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountReqDTO {

    @NotBlank(message = "Account name is required")
    private String name;

    @NotNull(message = "Balance is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "Balance must be non-negative")
    private Double balance;

    @NotNull(message = "Account type is required")
    private AccountType type;

    @NotNull(message = "User ID is required")
    private Long userId;
}
