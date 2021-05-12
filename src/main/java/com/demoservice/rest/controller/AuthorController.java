package com.demoservice.rest.controller;

import com.demoservice.rest.payload.common.RestRequest;
import com.demoservice.rest.payload.common.RestResponse;
import com.demoservice.constants.RestConstants;
import com.demoservice.rest.payload.author.AuthorRequestDto;
import com.demoservice.rest.payload.author.AuthorResponseDto;
import com.demoservice.rest.payload.author.AuthorSearchCriteria;
import com.demoservice.service.author.AuthorSearchingService;
import com.demoservice.service.author.AuthorCRUDService;
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
@RequestMapping(RestConstants.API + RestConstants.AUTHORS)
public class AuthorController {

    @Autowired
    private AuthorCRUDService authorCRUDService;
    @Autowired
    private AuthorSearchingService authorSearchingService;

    @PostMapping()
    public ResponseEntity<RestResponse<Object>> createAuthor(@RequestBody @Valid RestRequest<AuthorRequestDto> requestData) {
        AuthorResponseDto response = authorCRUDService.create(requestData.getBody());
        return ResponseEntity.status(HttpStatus.OK)
                .body(RestResponse.builder().body(response).build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestResponse<Object>> getAuthorById(@PathVariable Long id) {
        AuthorResponseDto response = authorCRUDService.getById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(RestResponse.builder().body(response).build());
    }

    @GetMapping()
    public ResponseEntity<RestResponse<Object>> getAuthors() {
        List<AuthorResponseDto> responses = authorCRUDService.getAll();
        return ResponseEntity.status(HttpStatus.OK)
                .body(RestResponse.builder().body(responses).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestResponse<Object>> updateAuthor(@PathVariable Long id, @RequestBody @Valid RestRequest<AuthorRequestDto> requestData) {
        AuthorResponseDto response = authorCRUDService.updateById(id, requestData.getBody());
        return ResponseEntity.status(HttpStatus.OK)
                .body(RestResponse.builder().body(response).build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<RestResponse<Object>> deleteAuthor(@PathVariable Long id) {
        authorCRUDService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(RestResponse.builder().build());
    }

    @PostMapping(RestConstants.AUTHORS_SEARCH_BY_EMAIL_AND_PHONE_NUMBER)
    public ResponseEntity<RestResponse<Object>> searchByEmailAndPhoneNumber(@RequestBody @Valid RestRequest<AuthorSearchCriteria> requestData) {
        List<AuthorResponseDto> responses = authorSearchingService.searchByNameAndEmail(requestData.getBody());

        return ResponseEntity.status(HttpStatus.OK)
                .body(RestResponse.builder()
                        .body(responses)
                        .build());
    }
}
