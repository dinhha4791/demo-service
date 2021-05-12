package com.demoservice.service.book;

import com.demoservice.DemoServiceApplicationTests;
import com.demoservice.rest.payload.book.BookResponseDto;
import com.demoservice.rest.payload.book.BookSearchCriteria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BookSearchingServiceTest extends DemoServiceApplicationTests {

    @Autowired
    private BookSearchingService bookSearchingService;

    private BookSearchCriteria bookSearchCriteria;

    @BeforeEach
    public void init() {
        bookSearchCriteria = new BookSearchCriteria();
        bookSearchCriteria.setAuthorName("Author");
    }

    @Test
    public void searchByAuthor_returnEmpty() {
        bookSearchCriteria.setRequireMatch(true);
        List<BookResponseDto> result = bookSearchingService.searchByAuthor(bookSearchCriteria);
        assertEquals(result.size() , 0);
    }

    @Test
    public void searchByAuthor_returnNotEmpty() {
        bookSearchCriteria.setRequireMatch(false);
        List<BookResponseDto> result = bookSearchingService.searchByAuthor(bookSearchCriteria);
        assertEquals(result.size() , 5);
    }
}
