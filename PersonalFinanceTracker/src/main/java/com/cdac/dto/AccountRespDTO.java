package com.cdac.dto;

import com.cdac.entities.AccountType;
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
public class AccountRespDTO {

    private Long accountId;
    private String name;
    private Double balance;
    private AccountType type;
    private Long userId;
    private String userName;
}
