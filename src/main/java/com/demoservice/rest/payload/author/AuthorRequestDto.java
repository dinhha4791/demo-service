package com.demoservice.rest.payload.author;

import com.demoservice.rest.payload.bookauthor.BookAuthorRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthorRequestDto {
    @NotBlank
    private String name;

    @Email
    private String email;

    @Pattern(regexp="(^$|[0-9]{10})")
    private String phoneNumber;

    private String address;

    private String information;

    private Set<BookAuthorRequestDto> bookIds = new HashSet<>();
}
