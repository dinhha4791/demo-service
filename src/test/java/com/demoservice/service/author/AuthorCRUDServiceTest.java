package com.demoservice.service.author;

import com.demoservice.DemoServiceApplicationTests;
import com.demoservice.rest.payload.author.AuthorRequestDto;
import com.demoservice.rest.payload.author.AuthorResponseDto;
import com.demoservice.rest.payload.common.RestRequest;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthorCRUDServiceTest extends DemoServiceApplicationTests {
    @Autowired
    private AuthorCRUDService authorCRUDService;
    private String createAuthorJSON = "authors/create_author.json";
    private String updateAuthorJSON = "authors/update_author.json";

    @Test
    @Order(1)
    public void getAuthorById_successful() throws Exception {
        AuthorResponseDto result = authorCRUDService.getById(1l);
        assertEquals(result.getId(), 1);
        assertEquals(result.getName(), "Author 1");
    }

    @Test
    @Order(2)
    public void getAuthors_successful() throws Exception {
        List<AuthorResponseDto> result = authorCRUDService.getAll();
        assertEquals(result.size(), 5);
    }

    @Test
    @Order(3)
    public void createBook_successful() throws Exception {
        RestRequest<AuthorRequestDto> authorRequestDto = createAuthorRequestDto(createAuthorJSON);
        AuthorResponseDto result = authorCRUDService.create(authorRequestDto.getBody());
        assertEquals(result.getId(), 6);
        assertEquals(result.getName(), "Author 6");
        assertEquals(result.getEmail(), "Email6@gmail.com");
    }

    @Test
    @Order(4)
    public void updateBookById_successful() throws Exception {
        RestRequest<AuthorRequestDto> authorRequestDto = createAuthorRequestDto(updateAuthorJSON);
        AuthorResponseDto result = authorCRUDService.updateById(1l, authorRequestDto.getBody());
        assertEquals(result.getId(), 1);
        assertEquals(result.getName(), "New Author 555");
        assertEquals(result.getEmail(), "newEmail@gmail.com");
    }

    @Test
    @Order(5)
    public void deleteById_successful() throws Exception {
        authorCRUDService.deleteById(5l);
        List<AuthorResponseDto> result = authorCRUDService.getAll();
        assertEquals(result.size(), 5);
    }

    private RestRequest<AuthorRequestDto> createAuthorRequestDto(String file) throws Exception {
            ObjectMapper objectMapper = new ObjectMapper();
            String stringValue = convertFileToString(file);

            return objectMapper.readValue(stringValue, new TypeReference<RestRequest<AuthorRequestDto>>() {});
    }
}
