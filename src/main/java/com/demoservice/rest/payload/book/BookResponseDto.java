package com.demoservice.rest.payload.book;

import com.demoservice.rest.payload.author.AuthorDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookResponseDto {
    private Long id;

    private String name;

    private String description;

    @EqualsAndHashCode.Exclude
    private Set<AuthorDto> authors;
}
