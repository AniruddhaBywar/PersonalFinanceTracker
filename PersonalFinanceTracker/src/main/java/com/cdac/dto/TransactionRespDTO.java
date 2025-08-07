package com.cdac.dto;


import java.time.LocalDate;

import com.cdac.entities.TransactionType;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class TransactionRespDTO {

	private Long transactionId;
    private double amount;
    private LocalDate date;
    private TransactionType type;
    private String description;

    private Long accountId;
    private String accountName;

    private Long categoryId;
    private String categoryName;

    private Long userId;
    private String userName;
}
