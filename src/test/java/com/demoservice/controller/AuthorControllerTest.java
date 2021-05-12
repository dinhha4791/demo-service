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

public class AuthorControllerTest extends DemoServiceApplicationTests {

    private String emptyAuthorJSON = "authors/search_author_by_email_and_phone_return_empty.json";
    private String notEmptyAuthorJSON = "authors/search_author_by_email_and_phone_return_not_empty.json";
    private String createAuthorJSON = "authors/create_author.json";
    private String updateAuthorJSON = "authors/update_author.json";

    @Test
    @Order(1)
    @WithMockUser(username = userName, password = password, roles = role)
    public void searchByEmailAndPhoneNumber_returnEmpty() throws Exception {
        String requestBody = convertFileToString(emptyAuthorJSON);
        mockMvc.perform(post(API_AUTHORS_SEARCH_BY_EMAIL_AND_PHONE_NUMBER)
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
    public void searchByEmailAndPhoneNumber_returnNotEmpty() throws Exception {
        String requestBody = convertFileToString(notEmptyAuthorJSON);
        mockMvc.perform(post(API_AUTHORS_SEARCH_BY_EMAIL_AND_PHONE_NUMBER)
                .content(requestBody)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.header.respCode", is(SUCCESS_CODE)))
                .andExpect(jsonPath("$.header.respDesc", is(SUCCESS_DESC)))
                .andExpect(jsonPath("$.body").isArray())
                .andExpect(jsonPath("$.body", hasSize(3)));
    }

    @Test
    @Order(3)
    @WithMockUser(username = userName, password = password, roles = role)
    public void getAuthorById_successful() throws Exception {
        Long id = 1l;
        mockMvc.perform(get(API_AUTHORS + "/" + id)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.header.respCode", is(SUCCESS_CODE)))
                .andExpect(jsonPath("$.header.respDesc", is(SUCCESS_DESC)))
                .andExpect(jsonPath("$.body.name", is("Author 1")));
    }

    @Test
    @Order(4)
    @WithMockUser(username = userName, password = password, roles = role)
    public void getBooks_successful() throws Exception {
        mockMvc.perform(get(API_AUTHORS)
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
    public void createAuthor_successful() throws Exception {
        String requestBody = convertFileToString(createAuthorJSON);
        mockMvc.perform(post(API_AUTHORS)
                .content(requestBody)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.header.respCode", is(SUCCESS_CODE)))
                .andExpect(jsonPath("$.header.respDesc", is(SUCCESS_DESC)))
                .andExpect(jsonPath("$.body.id", is(6)))
                .andExpect(jsonPath("$.body.name", is("Author 6")));
    }

    @Test
    @Order(6)
    @WithMockUser(username = userName, password = password, roles = role)
    public void updateAuthor_successful() throws Exception {
        String requestBody = convertFileToString(updateAuthorJSON);
        Long id = 1l;
        mockMvc.perform(put(API_AUTHORS + "/" + id)
                .content(requestBody)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.header.respCode", is(SUCCESS_CODE)))
                .andExpect(jsonPath("$.header.respDesc", is(SUCCESS_DESC)))
                .andExpect(jsonPath("$.body.name", is("New Author 555")));
    }

    @Test
    @Order(7)
    @WithMockUser(username = userName, password = password, roles = role)
    public void deleteAuthorById_successful() throws Exception {
        Long id = 1l;
        mockMvc.perform(get(API_AUTHORS + "/" + id)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.header.respCode", is(SUCCESS_CODE)))
                .andExpect(jsonPath("$.header.respDesc", is(SUCCESS_DESC)));
    }
}
