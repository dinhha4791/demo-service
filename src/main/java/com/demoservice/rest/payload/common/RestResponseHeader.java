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
public class RestResponseHeader {
    private String respCode;
    @NotBlank
    private String respDesc;
    @NotBlank
    private String responseDt;
}