package com.demoservice.service.author;

import com.demoservice.entity.Author;
import com.demoservice.rest.payload.author.AuthorResponseDto;
import com.demoservice.rest.payload.author.AuthorSearchCriteria;
import com.demoservice.service.common.LoggingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorSearchingService extends AuthorSearchingServiceAbstract<Author, AuthorSpecification, AuthorResponseDto> {
    private static final String AUTHOR_SEARCHING_SERVICE = "AuthorSearchingService";
    @Autowired
    private LoggingService loggingService;

    @Override
    public List<Author> search(AuthorSpecification authorSpecification) {
        List<Author> authors = authorRepository.findAll(authorSpecification);

        return authors;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<AuthorResponseDto> searchByNameAndEmail(AuthorSearchCriteria searchCriteria) {
        loggingService.logInfo(AUTHOR_SEARCHING_SERVICE, "START searchByNameAndEmail");

        AuthorSpecification authorSpecification = new AuthorSpecification(searchCriteria);
        List<AuthorResponseDto> responses = search(authorSpecification)
                .stream()
                .map(author -> mappingService.convertToDto(author, AuthorResponseDto.class))
                .collect(Collectors.toList());

        loggingService.logInfo(AUTHOR_SEARCHING_SERVICE, "END searchByNameAndEmail");
        return responses;
    }
}
