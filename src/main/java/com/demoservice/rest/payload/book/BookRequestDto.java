package com.demoservice.rest.payload.book;

import com.demoservice.rest.payload.bookauthor.BookAuthorRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookRequestDto {
    @NotBlank
    private String name;

    private String description;

    @NotEmpty
    @EqualsAndHashCode.Exclude
    private Set<BookAuthorRequestDto> authors = new HashSet<>();
}
