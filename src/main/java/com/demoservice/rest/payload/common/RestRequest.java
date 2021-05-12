package com.demoservice.rest.payload.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RestRequest<T> {
    @NotNull
    @Valid
    private RestRequestHeader header;
    @NotNull @Valid
    private T body;

}