package com.demoservice.rest.payload.author;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthorSearchCriteria {
    @NotBlank
    private String email;

    @NotBlank
    private String phoneNumber;

    @NotNull
    private Boolean requireMatch; // require to search by the exactly word(s).
}
