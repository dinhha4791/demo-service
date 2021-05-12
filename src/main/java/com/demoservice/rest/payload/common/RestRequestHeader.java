package com.demoservice.rest.payload.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RestRequestHeader {
    @NotBlank
    private String appId;
    @NotBlank
    private String requestDt;
}