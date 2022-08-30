package com.bs.git.repos.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class GitApiErrorDto {
    private String status;
    @JsonProperty(value = "Message")
    private String message;
}
