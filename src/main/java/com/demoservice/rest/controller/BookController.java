package com.demoservice.rest.controller;

import com.demoservice.rest.payload.common.RestRequest;
import com.demoservice.rest.payload.common.RestResponse;
import com.demoservice.constants.RestConstants;
import com.demoservice.rest.payload.book.BookRequestDto;
import com.demoservice.rest.payload.book.BookResponseDto;
import com.demoservice.rest.payload.book.BookSearchCriteria;
import com.demoservice.service.book.BookSearchingService;
import com.demoservice.service.book.BookCRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(RestConstants.API + RestConstants.BOOKS)
public class BookController {
    @Autowired
    private BookCRUDService bookCRUDService;
    @Autowired
    private BookSearchingService bookSearchingService;

    @PostMapping()
    public ResponseEntity<RestResponse<Object>> createBook(@RequestBody @Valid RestRequest<BookRequestDto> requestData) {
        BookResponseDto response = bookCRUDService.create(requestData.getBody());

        return ResponseEntity.status(HttpStatus.OK)
                .body(RestResponse.builder().body(response).build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestResponse<Object>> getBookById(@PathVariable Long id) {
        BookResponseDto response = bookCRUDService.getById(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(RestResponse.builder().body(response).build());
    }

    @GetMapping()
    public ResponseEntity<RestResponse<Object>> getBooks() {
        List<BookResponseDto> responses = bookCRUDService.getAll();

        return ResponseEntity.status(HttpStatus.OK)
                .body(RestResponse.builder().body(responses).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestResponse<Object>> updateBook(@PathVariable Long id, @RequestBody @Valid RestRequest<BookRequestDto> requestData) {
        BookResponseDto response = bookCRUDService.updateById(id, requestData.getBody());

        return ResponseEntity.status(HttpStatus.OK)
                .body(RestResponse.builder().body(response).build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<RestResponse<Object>> deleteBook(@PathVariable Long id) {
        bookCRUDService.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(RestResponse.builder().build());
    }

    @PostMapping(RestConstants.BOOKS_SEARCH_BY_AUTHOR)
    public ResponseEntity<RestResponse<Object>> searchByAuthor(@RequestBody @Valid RestRequest<BookSearchCriteria> requestData) {
        List<BookResponseDto> responses = bookSearchingService.searchByAuthor(requestData.getBody());

        return ResponseEntity.status(HttpStatus.OK)
                .body(RestResponse.builder()
                        .body(responses)
                        .build());
    }
}
