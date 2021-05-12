package com.demoservice.service.book;

import com.demoservice.DemoServiceApplicationTests;
import com.demoservice.rest.payload.common.RestRequest;
import com.demoservice.rest.payload.book.BookRequestDto;
import com.demoservice.rest.payload.book.BookResponseDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BookCRUDServiceTest extends DemoServiceApplicationTests {

    @Autowired
    private BookCRUDService bookCRUDService;
    private String createBookJSON = "books/create_book.json";
    private String updateBookJSON = "books/update_book.json";

    @Test
    @Order(1)
    public void getBookById_successful() throws Exception {
        BookResponseDto result = bookCRUDService.getById(1l);
        assertEquals(result.getId(), 1);
        assertEquals(result.getDescription(), "Book Desc 1");
    }

    @Test
    @Order(2)
    public void getBooks_successful() throws Exception {
        List<BookResponseDto> result = bookCRUDService.getAll();
        assertEquals(result.size(), 5);
    }

    @Test
    @Order(3)
    public void createBook_successful() throws Exception {
        RestRequest<BookRequestDto> bookRequestDto = createBookRequestDto(createBookJSON);
        BookResponseDto result = bookCRUDService.create(bookRequestDto.getBody());
        assertEquals(result.getId(), 6);
        assertEquals(result.getDescription(), "Book desc 6");
        assertEquals(result.getAuthors().size(), 2);
    }

    @Test
    @Order(4)
    public void updateBookById_successful() throws Exception {
        RestRequest<BookRequestDto> bookRequestDto = createBookRequestDto(updateBookJSON);
        BookResponseDto result = bookCRUDService.updateById(1l, bookRequestDto.getBody());
        assertEquals(result.getId(), 1);
        assertEquals(result.getName(), "New Book 6");
        assertEquals(result.getDescription(), "New Book desc 6");
    }

    @Test
    @Order(5)
    public void deleteById_successful() throws Exception {
        bookCRUDService.deleteById(5l);
        List<BookResponseDto> result = bookCRUDService.getAll();
        assertEquals(result.size(), 5);
    }

    private RestRequest<BookRequestDto> createBookRequestDto(String file) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String stringValue = convertFileToString(file);

        return objectMapper.readValue(stringValue, new TypeReference<RestRequest<BookRequestDto>>() {});
    }
}
