package com.demoservice.rest.payload.bookauthor;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookAuthorResponseDto {
    private Long id;

    private String name;

    private String email;

    private String phoneNumber;

    private String address;

    private String information;
}
