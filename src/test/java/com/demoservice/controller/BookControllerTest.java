package com.demoservice.controller;

import com.demoservice.DemoServiceApplicationTests;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class BookControllerTest extends DemoServiceApplicationTests {

    private String emptyAuthorJSON = "books/search_book_by_author_return_empty.json";
    private String notEmptyAuthorJSON = "books/search_book_by_author_return_not_empty.json";
    private String createBookJSON = "books/create_book.json";
    private String updateBookJSON = "books/update_book.json";

    @Test
    @Order(1)
    @WithMockUser(username = userName, password = password, roles = role)
    public void searchByAuthor_returnEmpty() throws Exception {
        String requestBody = convertFileToString(emptyAuthorJSON);
        mockMvc.perform(post(API_BOOKS_SEARCH_BY_AUTHOR)
                .content(requestBody)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.header.respCode", is(SUCCESS_CODE)))
                .andExpect(jsonPath("$.header.respDesc", is(SUCCESS_DESC)))
                .andExpect(jsonPath("$.body").isArray())
                .andExpect(jsonPath("$.body", hasSize(0)));
    }

    @Test
    @Order(2)
    @WithMockUser(username = userName, password = password, roles = role)
    public void searchByAuthor_returnNotEmpty() throws Exception {
        String requestBody = convertFileToString(notEmptyAuthorJSON);
        mockMvc.perform(post(API_BOOKS_SEARCH_BY_AUTHOR)
                .content(requestBody)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.header.respCode", is(SUCCESS_CODE)))
                .andExpect(jsonPath("$.header.respDesc", is(SUCCESS_DESC)))
                .andExpect(jsonPath("$.body").isArray())
                .andExpect(jsonPath("$.body", hasSize(5)));
    }

    @Test
    @Order(3)
    @WithMockUser(username = userName, password = password, roles = role)
    public void getBookById_successful() throws Exception {
        Long id = 1l;
        mockMvc.perform(get(API_BOOKS + "/" + id)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.header.respCode", is(SUCCESS_CODE)))
                .andExpect(jsonPath("$.header.respDesc", is(SUCCESS_DESC)))
                .andExpect(jsonPath("$.body.name", is("Book 1")));
    }

    @Test
    @Order(4)
    @WithMockUser(username = userName, password = password, roles = role)
    public void getBooks_successful() throws Exception {
        mockMvc.perform(get(API_BOOKS)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.header.respCode", is(SUCCESS_CODE)))
                .andExpect(jsonPath("$.header.respDesc", is(SUCCESS_DESC)))
                .andExpect(jsonPath("$.body").isArray())
                .andExpect(jsonPath("$.body", hasSize(5)));
    }

    @Test
    @Order(5)
    @WithMockUser(username = userName, password = password, roles = role)
    public void createBook_successful() throws Exception {
        String requestBody = convertFileToString(createBookJSON);
        mockMvc.perform(post(API_BOOKS)
                .content(requestBody)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.header.respCode", is(SUCCESS_CODE)))
                .andExpect(jsonPath("$.header.respDesc", is(SUCCESS_DESC)))
                .andExpect(jsonPath("$.body.id", is(6)))
                .andExpect(jsonPath("$.body.name", is("Book 6")));
    }

    @Test
    @Order(6)
    @WithMockUser(username = userName, password = password, roles = role)
    public void updateBook_successful() throws Exception {
        String requestBody = convertFileToString(updateBookJSON);
        Long id = 1l;
        mockMvc.perform(put(API_BOOKS + "/" + id)
                .content(requestBody)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.header.respCode", is(SUCCESS_CODE)))
                .andExpect(jsonPath("$.header.respDesc", is(SUCCESS_DESC)))
                .andExpect(jsonPath("$.body.name", is("New Book 6")));
    }

    @Test
    @Order(7)
    @WithMockUser(username = userName, password = password, roles = role)
    public void deleteBookById_successful() throws Exception {
        Long id = 1l;
        mockMvc.perform(get(API_BOOKS + "/" + id)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.header.respCode", is(SUCCESS_CODE)))
                .andExpect(jsonPath("$.header.respDesc", is(SUCCESS_DESC)));
    }
}
