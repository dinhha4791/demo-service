package com.demoservice.service.author;

import com.demoservice.entity.Book;
import com.demoservice.rest.payload.author.AuthorRequestDto;
import com.demoservice.rest.payload.author.AuthorResponseDto;
import com.demoservice.entity.Author;
import com.demoservice.service.common.LoggingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class AuthorCRUDService extends AuthorCRUDServiceAbstract<Author, AuthorRequestDto, AuthorResponseDto> {
    private static final String AUTHOR_SERVICE = "AuthorCRUDService";
    @Autowired
    private LoggingService loggingService;

    @Override
    public AuthorResponseDto create(AuthorRequestDto authorRequestDto) {
        loggingService.logInfo(AUTHOR_SERVICE, "START create author");

        Author author = mappingService.convertToEntity(authorRequestDto, Author.class);
        Set<Book> books = authorRequestDto.getBookIds()
                .stream()
                .map(bookDto -> {
                    Book book = bookCRUDService.getBookById(bookDto.getId());
                    book.getAuthors().add(author);
                    return book;
                })
                .collect(Collectors.toSet());

        author.setBooks(books);
        authorRepository.saveAndFlush(author);
        AuthorResponseDto response = mappingService.convertToDto(author, AuthorResponseDto.class);

        loggingService.logInfo(AUTHOR_SERVICE, "START create author");
        return response;
    }

    @Override
    public AuthorResponseDto getById(Long id) {
        loggingService.logInfo(AUTHOR_SERVICE, "START getById with id: " + id);

        Author author = getAuthorById(id);
        AuthorResponseDto response = mappingService.convertToDto(author, AuthorResponseDto.class);

        loggingService.logInfo(AUTHOR_SERVICE, "END getById");
        return response;
    }

    @Override
    public List<AuthorResponseDto> getAll() {
        loggingService.logInfo(AUTHOR_SERVICE, "END getAll");

        List<Author> authors = authorRepository.findAll();
        List<AuthorResponseDto> responses = new ArrayList<>();
        for(Author author : authors) {
            AuthorResponseDto dto = mappingService.convertToDto(author, AuthorResponseDto.class);
            responses.add(dto);
        }

        loggingService.logInfo(AUTHOR_SERVICE, "END getAll");
        return responses;
    }

    @Override
    public AuthorResponseDto updateById(Long id, AuthorRequestDto authorRequestDto) {
        loggingService.logInfo(AUTHOR_SERVICE, "START updateById with id: " + id);

        Author author = getAuthorById(id);
        mappingService.updateEntityFromDto(authorRequestDto, author);
        authorRepository.saveAndFlush(author);
        AuthorResponseDto response = mappingService.convertToDto(author, AuthorResponseDto.class);

        loggingService.logInfo(AUTHOR_SERVICE, "END updateById");
        return response;
    }

    @Override
    public void deleteById(Long id) {
        loggingService.logInfo(AUTHOR_SERVICE, "START deleteById with id: " + id);

        authorRepository.deleteById(id);

        loggingService.logInfo(AUTHOR_SERVICE, "END deleteById");
    }
}
