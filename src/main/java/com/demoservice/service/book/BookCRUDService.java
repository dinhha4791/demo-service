package com.demoservice.service.book;

import com.demoservice.entity.Author;
import com.demoservice.rest.payload.book.BookRequestDto;
import com.demoservice.rest.payload.book.BookResponseDto;
import com.demoservice.entity.Book;
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
@Transactional(propagation = Propagation.REQUIRED)
public class BookCRUDService extends BookCRUDServiceAbstract<Book, BookRequestDto, BookResponseDto> {
    private static final String BOOK_SERVICE = "BookCRUDService";
    @Autowired
    private LoggingService loggingService;

    @Override
    public BookResponseDto create(BookRequestDto dto) {
        loggingService.logInfo(BOOK_SERVICE, "START create book");

        Book book = mappingService.convertToEntity(dto, Book.class);
        bookRepository.saveAndFlush(book);
        BookResponseDto response = mappingService.convertToDto(book, BookResponseDto.class);

        loggingService.logInfo(BOOK_SERVICE, "END create book");
        return response;
    }

    @Override
    public BookResponseDto getById(Long id) {
        loggingService.logInfo(BOOK_SERVICE, "START getById with id: " + id);

        Book book = getBookById(id);
        BookResponseDto response = mappingService.convertToDto(book, BookResponseDto.class);

        loggingService.logInfo(BOOK_SERVICE, "END getById");
        return response;
    }

    @Override
    public List<BookResponseDto> getAll() {
        loggingService.logInfo(BOOK_SERVICE, "START getAll");

        List<Book> books = bookRepository.findAll();
        List<BookResponseDto> responses = new ArrayList<>();
        for(Book book : books) {
            BookResponseDto response =  mappingService.convertToDto(book, BookResponseDto.class);
            responses.add(response);
        }

        loggingService.logInfo(BOOK_SERVICE, "END getAll");
        return responses;
    }

    @Override
    public BookResponseDto updateById(Long id, BookRequestDto bookRequestDto) {
        loggingService.logInfo(BOOK_SERVICE, "START updateById with id: " + id);

        Book book = getBookById(id);
        book.getAuthors().clear();
        bookRepository.save(book);

        Set<Author> authors = bookRequestDto.getAuthors().stream()
                .map(author -> authorCRUDService.getAuthorById(author.getId()))
                .collect(Collectors.toSet());
        mappingService.updateEntityFromDto(bookRequestDto, book);
        book.setAuthors(authors);
        bookRepository.saveAndFlush(book);

        loggingService.logInfo(BOOK_SERVICE, "END updateById");
        return mappingService.convertToDto(book, BookResponseDto.class);
    }

    @Override
    public void deleteById(Long id) {
        loggingService.logInfo(BOOK_SERVICE, "START deleteById with id: " + id);

        bookRepository.deleteById(id);

        loggingService.logInfo(BOOK_SERVICE, "END deleteById");
    }
}
