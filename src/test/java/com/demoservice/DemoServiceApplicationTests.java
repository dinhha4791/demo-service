package com.demoservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@ActiveProfiles("test")
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = DemoServiceApplication.class)
public class DemoServiceApplicationTests {
	protected static final String userName = "user";
	protected static final String password = "$2a$10$JblP7T7X.Mcaq1J.t9iBkeEIDOKVgjOz/fBLCZyL7LHWAaRO.zyBq";
	protected static final String role = "USER";
	protected static final String API_BOOKS_SEARCH_BY_AUTHOR = "/api/books/search-by-author";
	protected static final String API_AUTHORS_SEARCH_BY_EMAIL_AND_PHONE_NUMBER = "/api/authors/search-by-email-and-phone-number";
	protected static final String API_BOOKS = "/api/books";
	protected static final String API_AUTHORS = "/api/authors";
	protected static final String SUCCESS_CODE = "00";
	protected static final String SUCCESS_DESC = "Success";
	protected static final String FILE_PATH = "test_data/";

	@Autowired
	protected MockMvc mockMvc;

	@Test
	void contextLoads() {
	}

	protected String convertFileToString(String file) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		Resource resource = new ClassPathResource(FILE_PATH + file, this.getClass().getClassLoader());
		return objectMapper.writeValueAsString(objectMapper.readValue(resource.getFile(), Object.class));
	}
}
