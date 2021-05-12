package com.demoservice.rest.payload.author;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthorDto {
    @NotBlank
    private Long id;

    @NotBlank
    private String name;

    private String email;

    private String phoneNumber;

    private String address;

    private String information;
}
