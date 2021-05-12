package com.demoservice.service.author;

import com.demoservice.DemoServiceApplicationTests;
import com.demoservice.rest.payload.author.AuthorResponseDto;
import com.demoservice.rest.payload.author.AuthorSearchCriteria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthorSearchingServiceTest extends DemoServiceApplicationTests {
    @Autowired
    private AuthorSearchingService authorSearchingService;

    private AuthorSearchCriteria authorSearchCriteria;

    @BeforeEach
    public void init() {
        authorSearchCriteria = new AuthorSearchCriteria();
        authorSearchCriteria.setEmail("email");
        authorSearchCriteria.setPhoneNumber("2");
    }

    @Test
    public void searchByAuthor_returnEmpty() {
        authorSearchCriteria.setRequireMatch(true);
        List<AuthorResponseDto> result = authorSearchingService.searchByNameAndEmail(authorSearchCriteria);
        assertEquals(result.size() , 0);
    }

    @Test
    public void searchByAuthor_returnNotEmpty() {
        authorSearchCriteria.setRequireMatch(false);
        List<AuthorResponseDto> result = authorSearchingService.searchByNameAndEmail(authorSearchCriteria);
        assertEquals(result.size() , 3);
    }
}
