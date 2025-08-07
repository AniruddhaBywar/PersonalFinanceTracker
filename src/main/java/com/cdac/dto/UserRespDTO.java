package com.cdac.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)

public class UserRespDTO {
    private Long userId;
    private String name;
    private String email;
    private LocalDateTime createdAt;
}
