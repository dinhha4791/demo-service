package com.demoservice.rest.payload.author;

import com.demoservice.rest.payload.book.BookDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthorResponseDto {
    @NotBlank
    private Long id;

    @NotBlank
    private String name;

    private String email;

    private String phoneNumber;

    private String address;

    private String information;

    @EqualsAndHashCode.Exclude
    private Set<BookDto> books;
}
